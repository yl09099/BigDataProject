package com.mini.dingding;



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
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        DingOragtion dingOragtion  = new DingOragtion();
        String string = null;
        while ((string = bufferedReader.readLine()) != null) {
            String newString=string.replace('"',' ');
            String str[] = string.split(":");
            for(int i=0;i<str.length;i++){
                dingOragtion.setId(str[1].trim());
                dingOragtion.setCreateDeptGroup(str[3].trim());
                dingOragtion.setName(str[5].trim());
                dingOragtion.setParentid(str[7].trim());
                dingOragtion.setAutoAddUser(str[9].trim());
                dingOragtion.setSourceIdentifier(str[11]);
            }

        }
    }
}
