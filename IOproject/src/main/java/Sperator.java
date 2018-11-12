import java.io.*;

public class Sperator {
    public static void main(String[] args) {
//        System.out.println(File.pathSeparator);
//        System.out.println(File.separator);
//        File src = new File("text.txt");
//        System.out.println(src.getAbsolutePath());
//        System.out.println(src.getParent());
//        System.out.println(src.getPath());
/*        File src = new File("H:\\欧美1");
        FileInputStream
        printName(src);*/
/*        String inFile = "H:\\欧美1\\57VVV.COM_chic-fan1-cd1.rmvb";
        String outFile = "D:\\a.rmvb";
        copyFile(inFile, outFile);*/

        try {
            byteStream();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * 递归方式输出文件夹下的所有文件
     * 传入是一个File对象
     *
     * @param src
     */
    public static void printName(File src) {
        if (src == null || !src.exists()) {
            return;
        }
        System.out.println(src.getAbsolutePath());
        if (src.isDirectory()) {
            for (File rm : src.listFiles()) {
                printName(rm);
            }
        }
    }

    /**
     * 输入流测试
     *
     * @param srcPath
     */
    public static void inputstreamDemo(String srcPath) {
        File movieFile = new File(srcPath);
        InputStream io = null;
        try {
            io = new FileInputStream(movieFile);
            byte[] bytes = new byte[2014];
            int len = 0;
            while ((len = io.read(bytes)) != -1) {
                String str = new String(bytes, 0, len);
                System.out.println(str);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (io != null) {
                try {
                    io.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 输出文件流
     *
     * @param srcPath
     */
    public static void outputstreamDemo(String srcPath) {
        File fileobject = new File(srcPath);
        OutputStream os = null;
        try {
            os = new FileOutputStream(fileobject);
            String fileString = "I am java outputStream!";
            os.write(fileString.getBytes(), 0, fileString.getBytes().length);
            os.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 拷贝一个文件
     *
     * @param inString
     * @param outString
     */
    public static void copyFile(String inString, String outString) {
        File inFile = new File(inString);
        File outFile = new File(outString);
        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            fis = new FileInputStream(inFile);
            fos = new FileOutputStream(outFile);
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = fis.read(bytes)) != -1) {
                fos.write(bytes, 0, len);
            }
            fos.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 拷贝文件夹
     * @param srcPath 源地址
     * @param dest 目标地址
     */
    public static void copyDir(String srcPath,String dest){
        File srcFile = new File(srcPath);
        File destFile = new File(dest);
        if(srcFile.isDirectory()){



        }

    }

    public static void byteStream() throws IOException {
        String msc = "我是一个中国人";
        byte[] bytes = msc.getBytes();
        InputStream is = new BufferedInputStream(new ByteArrayInputStream(bytes));
        int len =0;
        byte[] flush = new byte[1024];
        while((len=is.read(flush))!=-1){
            String msg = new String(flush);
            System.out.println(msg);
    }
    }
}
