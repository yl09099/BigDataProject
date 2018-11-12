package com.mini.hdfs;

import com.mini.hdfs.WritableDemo;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class TestWritable {

    @Test
    public void testSerizlize(){
        WritableDemo wd = new WritableDemo();
        try {
           byte[] bytes = wd.serialize(new IntWritable(13));
            assertThat(bytes.length,is(4));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testDeserialize(){
        WritableDemo wd = new WritableDemo();
        IntWritable newWritable = new IntWritable();
        try {
            wd.deserialize(newWritable,wd.serialize(new IntWritable(163)));
            assertThat(newWritable.get(),is(163));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testText(){
        Text t = new Text("hadoop");
        assertThat(t.getLength(),is(6));
        assertThat(t.getBytes().length,is(6));
        assertThat("Out of bounds",t.charAt(100),is(-1));
    }
}
