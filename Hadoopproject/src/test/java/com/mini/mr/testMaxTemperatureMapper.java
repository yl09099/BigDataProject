package com.mini.mr;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.OutputCollector;
import org.junit.Test;

import java.io.IOException;

import static  org.mockito.Mockito.*;

public class testMaxTemperatureMapper {
    @Test
    public void processValidRecode() throws IOException {
        MaxTemperatureMapper mapper = new MaxTemperatureMapper();
        Text text = new Text("0043011990999991950051518004+68750+023550FM-12+0382"+"99999V0203201N00261220001CN9999999N9-00111+99999999999");
        OutputCollector<Text, IntWritable> outputCollector = mock(OutputCollector.class);
        mapper.map(null,text,outputCollector,null);
        verify(outputCollector).collect(new Text("1950"),new IntWritable(-11));
    }
    @Test
    public void ignoresMisssingTemperatureRecord() throws IOException {
        MaxTemperatureMapper mapper = new MaxTemperatureMapper();
        Text text = new Text("0043011990999991950051518004+68750+023550FM-12+0382"+"99999V0203201N00261220001CN9999999N9+99991+99999999999");
        OutputCollector<Text, IntWritable> outputCollector = mock(OutputCollector.class);
        mapper.map(null,text,outputCollector,null);
        verify(outputCollector,never()).collect(any(Text.class),any(IntWritable.class));
    }


}
