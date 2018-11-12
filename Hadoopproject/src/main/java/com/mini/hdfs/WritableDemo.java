package com.mini.hdfs;

import org.apache.hadoop.io.Writable;

import java.io.*;

public class WritableDemo {

    /**
     * 序列化
     * @param writable
     * @return
     * @throws IOException
     */
    public byte[] serialize(Writable writable) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);
        writable.write(dos);
        dos.close();
        return bos.toByteArray();
    }

    public void deserialize(Writable writable, byte[] bytes) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        DataInputStream dis = new DataInputStream(bis);
        writable.readFields(dis);
        bis.close();
        dis.close();
    }


}
