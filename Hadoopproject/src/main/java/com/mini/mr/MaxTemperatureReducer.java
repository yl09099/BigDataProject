package com.mini.mr;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


import java.io.IOException;


public class MaxTemperatureReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
    @Override
    protected void reduce(Text key, Iterable<IntWritable> iterable, Context context) throws IOException, InterruptedException {
        int maxValue = Integer.MIN_VALUE;
        for (IntWritable intWritable : iterable) {
            maxValue = Math.max(maxValue, intWritable.get());
        }
        context.write(key, new IntWritable(maxValue));
    }
    /*
      public class MaxTemperatureReducer extends MapReduceBase implements Reducer<Text, IntWritable, Text,IntWritable> {
      @Override
    public void reduce(Text text, Iterator<IntWritable> iterator, OutputCollector<Text, IntWritable> outputCollector, Reporter reporter) throws IOException {
        int maxValve= Integer.MIN_VALUE;
        while(iterator.hasNext()){
            maxValve = Math.max(maxValve,iterator.next().get());
        }
        outputCollector.collect(text,new IntWritable(maxValve));
    }*/
}
