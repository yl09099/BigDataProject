package com.mini.mr;

import org.apache.avro.generic.GenericData;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.util.Tool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaodou on 2018/11/1910:51.
 * <p/>
 * 员工表
 * name gender age dept_no
 * Tom male 30 1
 * Tony male 35 2
 * Lily female 28 1
 * Lucy female 32 3
 * 部门表dept数据如下：
 * dept_no dept_name
 * 1 TSD
 * 2 MCD
 * 3 PPD
 */

public class ReducerSideJoin extends Configured implements Tool {

    @Override
    public int run(String[] args) throws Exception {
        Configuration conf = getConf();
        Job job = Job.getInstance(conf, "ReducerSideJoinJob");
        job.setJarByClass(ReducerSideJoin.class);
        int executeCode = job.waitForCompletion(true) ? 0 : 1;
        return executeCode;
    }

    public static class ReducerSideJoinMapper extends Mapper<LongWritable, Text, Text, Text> {
        String employeeValue = "";


        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

            String filePath = ((FileSplit) context.getInputSplit()).getPath().getName();
            String line = value.toString();

            if (filePath.indexOf("employee") != -1) {
                String[] lines = line.split(" ");
                if (lines.length == 4) {
                    String depNO = lines[3];
                    employeeValue = line + " a";
                    context.write(new Text(depNO), new Text(employeeValue));
                }
            } else if (filePath.indexOf("dept") != -1) {
                String[] lines = line.split(" ");
                if (lines.length == 2) {
                    String depNo = lines[0];
                    employeeValue = line + " b";
                    context.write(new Text(depNo), new Text(employeeValue));
                }


            }


        }
    }

    public static class ReducerSideJoinReducer extends Reducer<Text, Text, Text, NullWritable> {

        List<String[]> lista = new ArrayList<String[]>();
        List<String[]> listb = new ArrayList<String[]>();

        @Override
        protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            for (Text textline : values) {
                String[] str = textline.toString().split(" ");
                String flag = str[str.length - 1];
                if (flag.equals("a")) {
                    lista.add(str);
                } else {
                    listb.add(str);
                }
            }

            for (int i = 0; i < lista.size(); i++) {
                if (lista.size() == 0) {
                    continue;
                } else {
                    String[] stra = lista.get(i);
                    for (int j = 0; j < listb.size(); j++) {
                        String strb[] = listb.get(i);
                        String keyValue = stra[0] + " " + stra[1] + " " + stra[2] + " " + stra[3] + " " + strb[1];
                        context.write(new Text(keyValue), NullWritable.get());
                    }

                }

            }


        }
    }
}
