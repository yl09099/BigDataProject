package com.mini.mr;


import org.apache.commons.lang.ObjectUtils;
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
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiaodou on 2018/11/1914:05.
 */

public class MapSideJoin extends Configured implements Tool {
    @Override
    public int run(String[] args) throws Exception {
        Configuration conf = getConf();
        Job job = Job.getInstance(conf, "MapSideJoin");
        job.addCacheFile(new URI(args[0]));
        FileInputFormat.addInputPath(job, new Path(args[1]));
        job.setInputFormatClass(TextInputFormat.class);

        job.setMapperClass(MapSideJoinMap.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NullWritable.class);


        job.setReducerClass(MapSideReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

        job.setNumReduceTasks(1);

        FileOutputFormat.setOutputPath(job, new Path(args[2]));


        job.setJarByClass(MapSideJoin.class);
        int executeCode = job.waitForCompletion(true) ? 0 : 1;
        return executeCode;
    }

    public static class MapSideJoinMap extends Mapper<LongWritable, Text, Text, NullWritable> {
        private Map<Integer, String> deptData = new HashMap<Integer, String>();

        @Override
        protected void setup(Context context) throws IOException, InterruptedException {

            URI[] uri = context.getCacheFiles();
            BufferedReader br = new BufferedReader(new FileReader(uri[0].getPath()));

            String str = null;
            while ((str = br.readLine()) != null) {
                String strs[] = str.split(" ");
                deptData.put(Integer.parseInt(strs[0]), strs[1]);
            }

        }

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String[] values = value.toString().split(" ");
            int depNo = Integer.parseInt(values[3]);
            String depName = deptData.get(values[depNo]);
            String resultData = value.toString() + " " + depName;
            context.write(new Text(resultData), NullWritable.get());
        }
    }

    public static class MapSideReducer extends Reducer<Text, NullWritable, Text, NullWritable> {
        @Override
        protected void reduce(Text key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
            context.write(key, NullWritable.get());
        }
    }
}
