package com.mini.mr;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;

public class MaxWord extends Configured implements Tool {
    public static void main(String[] args) {
        try {
            int a = ToolRunner.run(new MaxWord(), args);
            System.exit(a);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int run(String[] strings) throws Exception {
        Configuration conf = getConf();
        Job job = Job.getInstance(conf, "MaxWord");
        job.setJarByClass(MaxWord.class);
        job.setMapperClass(MaxWordMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(LongWritable.class);
        job.setReducerClass(MaxWordReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);
        FileInputFormat.addInputPath(job, new Path(strings[0]));
        FileOutputFormat.setOutputPath(job, new Path(strings[1]));
        int code = job.waitForCompletion(true) ? 0 : 1;
        return code;
    }

    public static class MaxWordMapper extends Mapper<LongWritable, Text, Text, LongWritable> {
        private Text textValue = new Text();
        private long maxValue = 0;

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String[] strArr = value.toString().split(",");
            long strValue = Long.parseLong(strArr[1]);
            if (strValue > maxValue) {
                textValue.set(strArr[0]);
                maxValue = strValue;
            }
        }

        @Override
        protected void cleanup(Context context) throws IOException, InterruptedException {
            context.write(textValue, new LongWritable(maxValue));
        }
    }

    public static class MaxWordReducer extends Reducer<Text, LongWritable, Text, LongWritable> {
        private Text textValue = new Text();
        private long maxVlaue = 0L;

        @Override
        protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {

            long num =0;
            for (LongWritable lw : values) {
                num = Math.max(num, lw.get());
            }
            if(num>maxVlaue){
                textValue = key;
                maxVlaue = num;
            }
        }

        @Override
        protected void cleanup(Context context) throws IOException, InterruptedException {
            context.write(textValue, new LongWritable(maxVlaue));
        }
    }
}
