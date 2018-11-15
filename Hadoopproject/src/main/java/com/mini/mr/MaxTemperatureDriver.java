package com.mini.mr;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class MaxTemperatureDriver extends Configured implements Tool {
    @Override
    public int run(String[] strings) throws Exception {

        Configuration conf = getConf();
        Job job = Job.getInstance(conf, "Max temperature");
        job.setJarByClass(MaxTemperatureDriver.class);
        job.setMapperClass(MaxTemperatureMapper.class);
        job.setCombinerClass(MaxTemperatureReducer.class);
        job.setReducerClass(MaxTemperatureReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.addInputPath(job, new Path(strings[0]));
        FileOutputFormat.setOutputPath(job, new Path(strings[1]));

        return job.waitForCompletion(true) ? 0 : 1;
    }

    public static void main(String[] args) {
        int codeexit = 0;
        if (args.length != 2) {
            System.err.printf("Usage %s [generic options] <input> <outpu>\n");
            ToolRunner.printGenericCommandUsage(System.err);
            System.exit(-1);
        }
        try {
            codeexit = ToolRunner.run(new MaxTemperatureDriver(), args);
            System.exit(codeexit);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
