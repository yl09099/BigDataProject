package dataio;

import java.io.*;

public class DataArrayIO {

    public static void main(String[] args) throws IOException {
        //byte[]  returnArr = testByteInputStream(new File("C:\\Users\\Administrator\\Desktop\\1.csv"));
        BufferedReader br = new BufferedReader(new FileReader(new File("C:\\Users\\Administrator\\Desktop\\1.csv")));
        int i =1;
        String stingLine= null;
        while((stingLine=br.readLine())!=null){
            System.out.println(stingLine);
            i++;
        }
        br.close();
    }

    public static byte[] testByteInputStream(File file) {
        byte[] byteArr = new byte[(int) file.length()];
        FileInputStream fis = null;
        ByteArrayOutputStream bos = null;

        try {
            fis = new FileInputStream(file);
            bos = new ByteArrayOutputStream();
            byte[] flushs = new byte[1024];
            int len = 0;
            while ((len = fis.read(flushs)) != -1) {
                bos.write(flushs);
                bos.flush();
            }
            byteArr = bos.toByteArray();
            return byteArr;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
