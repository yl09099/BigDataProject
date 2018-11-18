package com.mini.mr;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
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
import java.util.StringTokenizer;

public class WordCounter extends Configured implements Tool {

    public static void main(String[] args) {
        try {
            int code = ToolRunner.run(new WordCounter(), args);
            System.exit(code);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int run(String[] strings) throws Exception {
        if (strings.length < 2) {
            System.out.println("输入的参数不够！");
            System.exit(2);
        }
        Configuration conf = getConf();
        FileSystem fileSystem = FileSystem.get(conf);
        boolean exists_flag = fileSystem.exists(new Path(strings[1]));
        if (exists_flag) {
            boolean del_flag = fileSystem.delete(new Path(strings[1]), true);
            if (!del_flag) {
                System.out.println("删除已有文件夹失败！");
                System.exit(3);
            }
        }
        Job jobs = Job.getInstance(conf, "wordCounterJob");
        jobs.setJarByClass(WordCounter.class);
        jobs.setMapperClass(WordMapper.class);
        jobs.setMapOutputKeyClass(Text.class);
        jobs.setMapOutputValueClass(IntWritable.class);
        jobs.setCombinerClass(WordReducer.class);
        jobs.setReducerClass(WordReducer.class);
        jobs.setOutputKeyClass(Text.class);
        jobs.setOutputValueClass(IntWritable.class);
        FileInputFormat.addInputPath(jobs, new Path(strings[0]));
        FileOutputFormat.setOutputPath(jobs, new Path(strings[1]));
        int result = jobs.waitForCompletion(true) ? 0 : 1;
        return result;
    }

    public static class WordMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
        private Text word = new Text();

        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            IntWritable counter = new IntWritable(1);
            StringTokenizer str = new StringTokenizer(value.toString(), ",");
            while (str.hasMoreElements()) {
                word.set((String) str.nextElement());
                context.write(word, counter);
            }

        }
    }

    public static class WordReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

        private IntWritable result = new IntWritable();

        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int num = 0;
            for (IntWritable intw : values) {
                num += intw.get();
            }
            result.set(num);
            context.write(key, result);
        }

    }
}