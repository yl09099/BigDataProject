package com.mini.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.io.IOUtils;

import java.io.*;
import java.net.URL;

/**
 * Created by xiaodou on 2018/10/31.
 */
public class HdfsFile {
    private String name;

    static {
        URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
    }

    /**
     * 在hdfs文件系统中创建一个文件
     *
     * @throws IOException
     */
    public static void createFile(String dst, byte[] contents) throws IOException {
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(configuration);
        Path dfsP = new Path(dst);
        FSDataOutputStream fsout = fs.create(dfsP);
        fsout.write(contents);
        fsout.close();
        fs.close();
        System.out.println("文件创建成功！");
    }


    public void readFile() {
        try {
            InputStream in = new URL("hdfs://host/path").openStream();
            IOUtils.copyBytes(in, System.out, 4096, false);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void readFileUseFileSystem() throws IOException {
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);
        Path path = new Path("hdfs://master.com:9000/test");
        FSDataInputStream fin = fs.open(path);
        IOUtils.copyBytes(fin, System.out, 4096, false);
    }

    public static void listFiles() throws IOException {
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);
        Path path = new Path("hdfs://master.com:9000/test");
        FileStatus[] status = fs.listStatus(path);
        Path[] pats = FileUtil.stat2Paths(status);
        for (int i = 0; i < pats.length; i++) {
            System.out.println(pats[i].getName());
        }
    }

    public static void main(String[] args) throws IOException {
//        listFiles();
        //本地文件
        File file = new File("C:\\Users\\Administrator\\Desktop\\1.csv");
        //hadoop文件系统中的文件
        String hadoopPath = "/test/abc.csv";
        //构建一个byte数组
        System.out.println(file.length());
        byte[] bytearr = new byte[(int) file.length()];
        //构建一个byte输出流
        ByteArrayOutputStream bos = null;
        //构建一个文件输入流
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(file);
            bos = new ByteArrayOutputStream();
            byte[] flush = new byte[1024];
            int len = 0;
            while ((len = fis.read(flush)) != -1) {
                bos.write(flush);
                bos.flush();
            }
            //将输出流转化为byte数组
            bytearr = bos.toByteArray();
            createFile(hadoopPath, bytearr);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bos.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
