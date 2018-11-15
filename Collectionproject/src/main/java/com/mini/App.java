package com.mini;

import java.util.*;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        /*List<String> s = new ArrayList<String>();
        List<String> sr = new LinkedList<String>();
        Vector<String> vector = new Vector<String>();
        s.add("abc");
        sr.add("abc");
        boolean flag = s.containsAll(sr);
        int length = s.size();
        System.out.println(flag + "" + length);

        Map<String, String> maps = new HashMap<String, String>();
        maps.put("123", "张三");
        maps.put("234", "李四");
        String sb = maps.get("123");
        System.out.println(sb);
        Map<String, String> maps1 = new HashMap<String, String>();
        maps1.put("123", "张三");
        maps1.put("234", "李四");
        maps.putAll(maps1);*/

        Set set = new HashSet();
        set.add("123");
        set.add("234");
        set.add("abc");
        set.add("dfs");

        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            String srt = (String)iterator.next();
        }

    }
}
