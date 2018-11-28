package dataio;

import java.io.*;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * Created by xiaodou on 2018/11/279:59.
 */

public class GetProperty {

    private static final String DEFAULT_ENCODING = "UTF-8";

    /**
     ** 使用java.util.Properties类的load()方法加载properties文件
     *
     */
    private static void Method1() {
        try {
            // 获取文件流（方法1或2均可）
            InputStream inputStream = new BufferedInputStream(new FileInputStream(new File("src/main/resources/demo/jdbc.properties"))); //方法1
            //            InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties"); //方法2
            Properties prop = new Properties();

            prop.load(new InputStreamReader(inputStream, DEFAULT_ENCODING)); //加载格式化后的流

            String driverClassName = prop.getProperty("driverClassName");

            System.out.println("Method1: " + driverClassName);

        } catch (FileNotFoundException e) {
            System.out.println("properties文件路径有误！");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用class变量的getResourceAsStream()方法
     * 注意：getResourceAsStream()方法的参数路径/包路径+properties文件名+.后缀
     */
    public static void Method2() {
        try {
            InputStream inputStream = GetProperty.class.getResourceAsStream("/demo/jdbc.properties");

            Properties prop = new Properties();
            prop.load(inputStream);

            String driverClassName = prop.getProperty("driverClassName");

            System.out.println("Method2: " + driverClassName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     ** 使用class.getClassLoader()所得到的java.lang.ClassLoader的getResourceAsStream()方法
     ** 注意：getResourceAsStream(name)方法的参数必须是包路径+文件名+.后缀
     *
     */
    public static void Method3() {
        try {
            InputStream inputStream = GetProperty.class.getClassLoader().getResourceAsStream("demo/jdbc.properties");

            Properties prop = new Properties();
            prop.load(inputStream);

            String driverClassName = prop.getProperty("driverClassName");

            System.out.println("Method3: " + driverClassName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     ** 使用java.lang.ClassLoader类的getSystemResourceAsStream()静态方法
     ** getSystemResourceAsStream()方法的参数必须是包路径+文件名+.后缀
     *
     */
    public static void Method4() {
        try {
            InputStream inputStream = ClassLoader.getSystemResourceAsStream("demo/jdbc.properties");

            Properties prop = new Properties();
            prop.load(inputStream);

            String driverClassName = prop.getProperty("driverClassName");

            System.out.println("Method4: " + driverClassName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     ** 使用java.util.ResourceBundle类的getBundle()方法
     ** 注意：注意：这个getBundle()方法的参数相对同目录路径，并去掉.properties后缀，否则将抛异常
     *
     */
    public static void Method5() {
        ResourceBundle resource = ResourceBundle.getBundle("demo");
        String driverClassName = resource.getString("driverClassName");

        System.out.println("Method5: " + driverClassName);
    }

    /**
     ** 使用java.util.PropertyResourceBundle类的构造函数
     *
     */
    public static void Method6() {
        ResourceBundle resource;
        try {
            InputStream inputStream = new BufferedInputStream(new FileInputStream(new File("src/main/resources/demo/jdbc.properties")));
            resource = new PropertyResourceBundle(inputStream);

            System.out.println("Method6: ");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Method1();
        Method2();
        Method3();
        Method4();
        Method5();
        Method6();
    }
}
