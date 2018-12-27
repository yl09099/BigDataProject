package com.mini.service;


import com.mini.dao.InsertDD;
import com.mini.model.DingOragtion;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by xiaodou on 2018/12/2617:57.
 */

public class FileDeal {

    public static void main(String[] args) throws IOException {
        FileInputStream inputStream = new FileInputStream("E:/aa.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "gbk"));
        DingOragtion dingOragtion = new DingOragtion();
        String string = null;
        while ((string = bufferedReader.readLine()) != null) {
            String newString = string.replace('\"', ' ').replace(',', ':').replace('}',' ');
            String[] str = newString.split(":");
            for (String s : str) {
                System.out.println(s);
            }
            for (int i = 0; i < 1; i++) {
                dingOragtion.setId(str[1].trim());
                dingOragtion.setCreateDeptGroup(str[3].trim());
                dingOragtion.setName(str[5].trim());
                dingOragtion.setParentid(str[7].trim());
                InsertDD.insert(dingOragtion);
            }

        }
        inputStream.close();
        bufferedReader.close();
    }
}
