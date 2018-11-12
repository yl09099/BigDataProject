package com.mini.mr;

import org.apache.hadoop.conf.Configuration;
import org.junit.Test;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;


public class testMapReduceDemo {
    @Test
    public void testConfiguration1(){
        Configuration conf = new Configuration();
        conf.addResource("configuration-1.xml");
        assertThat(conf.get("color"),is("yellow"));
        assertThat(conf.getInt("size",0),is(10));
        assertThat(conf.get("breadth","wide"),is("wide"));
    }

    @Test
    public void testConfigration2(){
        Configuration conf = new Configuration();
        conf.addResource("configuration-1.xml");
        conf.addResource("configuration-2.xml");
        assertThat(conf.getInt("size",0),is(12));
        assertThat(conf.get("weight"),is("heavy"));
        assertThat(conf.get("size-weight"),is("12,heavy"));
    }




}
