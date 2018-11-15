package com.mini.mr;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer.Context;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TestMaxTemperatureReducer {
    @Test
    public void returnMaxTemperatureValue() throws IOException, InterruptedException {
        MaxTemperatureReducer reducer = new MaxTemperatureReducer();
        Text key = new Text("1950");
        List<IntWritable> iterator = new ArrayList<IntWritable>();
        iterator.add(new IntWritable(10));
        iterator.add(new IntWritable(5));
        Context output = mock(Context.class);
        reducer.reduce(key,iterator,output);
        verify(output).write(new Text("1950"),new IntWritable(10));

    }
}
