package com.mini.mr;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;

public class DistinctWord extends Configured implements Tool {


    public static class DistictMapper extends Mapper<LongWritable, Text, Text, NullWritable> {
        private Text splitstr = new Text();

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String[] str = value.toString().split(",");
            for (String string : str) {
                splitstr = new Text(string);
                context.write(splitstr, NullWritable.get());
            }
        }
    }

    public static class DistinctReducer extends Reducer<Text, NullWritable, Text, NullWritable> {
        @Override
        protected void reduce(Text key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
            context.write(key, NullWritable.get());
        }
    }

    @Override
    public int run(String[] strings) throws Exception {
        Configuration conf = getConf();
        Job job = Job.getInstance(conf, "DistinctWord");
        job.setJarByClass(DistinctWord.class);
        job.setMapperClass(DistictMapper.class);
        job.setCombinerClass(DistinctReducer.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NullWritable.class);
        job.setReducerClass(DistinctReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);
        FileInputFormat.addInputPath(job, new Path(strings[0]));
        FileOutputFormat.setOutputPath(job, new Path(strings[1]));
        int result = 0;
        result = job.waitForCompletion(true) ? 0 : 1;
        return result;
    }

    public static void main(String[] args) {
        try {
            int resultCode = ToolRunner.run(new DistinctWord(), args);
            System.exit(resultCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
