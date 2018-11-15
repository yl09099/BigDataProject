package com.mini.mr;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Mapper.Context;
import org.junit.Test;

import java.io.IOException;

import static  org.mockito.Mockito.*;

public class TestMaxTemperatureMapper {
    @Test
    public void processValidRecode() throws IOException, InterruptedException {
        MaxTemperatureMapper mapper = new MaxTemperatureMapper();
        IntWritable intWritable = new IntWritable(1);
        Text text = new Text("0043011990999991950051518004+68750+023550FM-12+0382"+"99999V0203201N00261220001CN9999999N9-00111+99999999999");
        Context outputCollector = mock(Context.class);
        mapper.map(intWritable,text,outputCollector);
        verify(outputCollector).write(new Text("1950"),new IntWritable(-11));
    }
    @Test
    public void ignoresMisssingTemperatureRecord() throws IOException, InterruptedException {
        MaxTemperatureMapper mapper = new MaxTemperatureMapper();
        IntWritable intWritable = new IntWritable(1);
        Text text = new Text("0043011990999991950051518004+68750+023550FM-12+0382"+"99999V0203201N00261220001CN9999999N9+99991+99999999999");
        Context outputCollector = mock(Context.class);
        mapper.map(intWritable,text,outputCollector);
        verify(outputCollector,never()).write(any(Text.class),any(IntWritable.class));
    }


}
