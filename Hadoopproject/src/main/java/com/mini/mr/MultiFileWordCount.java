package com.mini.mr;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.CombineFileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.CombineFileRecordReader;
import org.apache.hadoop.mapreduce.lib.input.CombineFileSplit;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.reduce.IntSumReducer;
import org.apache.hadoop.util.LineReader;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.StringTokenizer;

public class MultiFileWordCount extends Configured implements Tool {
    private void printUsage() {
        System.out.println("Usage : multifilewc <input_dir> <output>");
    }

    public static void main(String[] args) {
        try {
            int resultCode = ToolRunner.run(new MultiFileWordCount(), args);
            System.exit(resultCode);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int run(String[] strings) throws Exception {
        if (strings.length != 2) {
            printUsage();
            return 2;
        }
        Configuration conf = getConf();
        Job job = Job.getInstance(conf, "MultiFileWordCount");
        job.setJarByClass(MultiFileWordCount.class);
        job.setInputFormatClass(MyInputFormat.class);
        job.setMapperClass(MapClass.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        job.setReducerClass(IntSumReducer.class);
        job.setCombinerClass(IntSumReducer.class);
        FileInputFormat.addInputPath(job, new Path(strings[0]));
        FileOutputFormat.setOutputPath(job, new Path(strings[1]));
        int executeCode = job.waitForCompletion(true) ? 0 : 1;
        return executeCode;
    }

    public static class WordOffset implements WritableComparable {
        private long offset;
        private String fileName;


        @Override
        public void write(DataOutput dataOutput) throws IOException {
            dataOutput.writeLong(offset);
            Text.writeString(dataOutput, fileName);
        }


        @Override
        public void readFields(DataInput dataInput) throws IOException {
            this.offset = dataInput.readLong();
            this.fileName = Text.readString(dataInput);
        }

        @Override
        public int compareTo(Object obj) {
            WordOffset that = (WordOffset) obj;
            int f = this.fileName.compareTo(that.fileName);
            if (f == 0) {
                return (int) Math.signum((double) (this.offset - that.offset));
            }
            return f;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof WordOffset) {
                return this.compareTo(obj) == 0;
            }
            return false;
        }

        @Override
        public int hashCode() {
            assert false : "hasCode not designed";
            return 42;
        }
    }

    /**
     *
     */
    public static class MyInputFormat extends CombineFileInputFormat<WordOffset, Text> {
        @Override
        public RecordReader<WordOffset, Text> createRecordReader(InputSplit split, TaskAttemptContext context) throws IOException {
            return new CombineFileRecordReader<WordOffset, Text>((CombineFileSplit) split, context, CombineFileLineRecordReader.class);
        }
    }

    public static class CombineFileLineRecordReader extends RecordReader<WordOffset, Text> {

        private long startOffset;
        private long end;
        private long pos;
        private FileSystem fs;
        private Path path;
        private WordOffset key;
        private Text value;

        private FSDataInputStream fileIn;
        private LineReader reader;

        public CombineFileLineRecordReader(CombineFileSplit split, TaskAttemptContext context, Integer index) throws IOException {
            this.path = split.getPath(index);
            fs = this.path.getFileSystem(context.getConfiguration());
            this.startOffset = split.getOffset(index);
            this.end = startOffset + split.getLength(index);
            boolean skipFirstLine = true;
            fileIn = fs.open(path);
            if (startOffset != 0) {
                skipFirstLine = true;
                --startOffset;
                fileIn.seek(startOffset);
            }
            reader = new LineReader(fileIn);
            if (skipFirstLine) {
                startOffset += reader.readLine(new Text(), 0, (int) Math.min((long) Integer.MAX_VALUE, end - startOffset));
            }
            this.pos = startOffset;
        }

        @Override
        public void initialize(InputSplit inputSplit, TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        }

        @Override
        public boolean nextKeyValue() throws IOException, InterruptedException {
            if (key == null) {
                key = new WordOffset();
                key.fileName = path.getName();
            }
            key.offset = pos;
            if (value == null) {
                value = new Text();
            }
            int newSize = 0;
            if (pos < end) {
                newSize = reader.readLine(value);
                pos = newSize;
            }
            if (newSize == 0) {
                key = null;
                value = null;
                return false;
            } else {
                return true;
            }
        }

        @Override
        public WordOffset getCurrentKey() throws IOException, InterruptedException {
            return key;
        }

        @Override
        public Text getCurrentValue() throws IOException, InterruptedException {
            return value;
        }

        @Override
        public float getProgress() throws IOException, InterruptedException {
            if (startOffset == end) {
                return 0.0f;
            } else {
                return Math.min(1.0f, (pos - startOffset) / (float) (end - startOffset));
            }
        }

        @Override
        public void close() throws IOException {
        }
    }

    public static class MapClass extends Mapper<WordOffset, Text, Text, IntWritable> {

        private final static IntWritable one = new IntWritable(1);
        private Text word = new Text();

        @Override
        public void map(WordOffset key, Text value, Context context)
                throws IOException, InterruptedException {
            String line = value.toString();
            StringTokenizer itr = new StringTokenizer(line);
            while (itr.hasMoreTokens()) {
                word.set(itr.nextToken());
                context.write(word, one);
            }
        }
    }
}
