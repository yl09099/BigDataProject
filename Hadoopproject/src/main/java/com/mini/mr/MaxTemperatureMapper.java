package com.mini.mr;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;


public class MaxTemperatureMapper extends Mapper<IntWritable, Text, Text, IntWritable> {
    private NcdcRecordParser ncdcRecordParser = new NcdcRecordParser();

    @Override
    protected void map(IntWritable key, Text value, Context context) throws IOException, InterruptedException {
        ncdcRecordParser.parse(value);
        if(ncdcRecordParser.isValidTemperature()){
            context.write(new Text(ncdcRecordParser.getYear()),new IntWritable(ncdcRecordParser.getAritemperature()));
        }
    }

    /* public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {
        ncdcRecordParser.parse(value);
        if (ncdcRecordParser.isValidTemperature()) {
            output.collect(new Text(ncdcRecordParser.getYear()), new IntWritable(ncdcRecordParser.getAritemperature()));
        }
    }*/
}
