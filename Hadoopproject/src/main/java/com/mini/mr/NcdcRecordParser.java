package com.mini.mr;
import org.apache.hadoop.io.Text;

import java.util.Hashtable;

public class NcdcRecordParser {
    private static final int MISS_TEMPERATURE = 9999;
    private String year;
    private int aritemperature;
    private String qualty;

    public void parse(String record){
        year = record.substring(15,19);
        String aritemperatureString;
        if(record.charAt(87)=='+'){
            aritemperatureString = record.substring(88,92);

        }else{
            aritemperatureString = record.substring(87,92);
        }
        aritemperature = Integer.parseInt(aritemperatureString);
        qualty = record.substring(92,93);

    }
    public void parse(Text record){
        parse(record.toString());
    }
    public String getYear(){
        return year;
    }
    public int getAritemperature(){
        return aritemperature;
    }
    public boolean isValidTemperature(){
        return aritemperature!=MISS_TEMPERATURE && qualty.matches("[01459]");
    }

}
