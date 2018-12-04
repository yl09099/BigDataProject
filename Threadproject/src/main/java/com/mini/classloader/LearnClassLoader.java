package com.mini.classloader;


/**
 * 已经知道了BootstrapClassLoader、ExtClassLoader、AppClassLoader实际是
 * 查阅相应的环境属性sun.boot.class.path、java.ext.dirs和java.class.path来加载资源文件的
 */

public class LearnClassLoader {

    public static void main(String[] args) {
        /*ClassLoader classLoader = LearnClassLoader.class.getClassLoader();
        System.out.println(classLoader.toString());
        System.out.println(classLoader.getParent().toString());
        System.out.println(classLoader.getParent().getParent());
        String[] strings = System.getProperty("sun.boot.class.path").split(";");
        for (String c : strings) {
            System.out.println(c);
        }

        String[] strs = System.getProperty("java.ext.dirs").split(";");
        for (String c : strs) {
            System.out.println(c);
        }

        String[] str1s  = System.getProperty("java.class.path").split(";");
        for(String c:str1s){
            System.out.println(c);
        }*/

        ClassLoader classLoader = LearnClassLoader.class.getClassLoader();
        System.out.println(classLoader.toString());

        classLoader = int.class.getClassLoader();
        System.out.println(classLoader.toString());
    }
}
