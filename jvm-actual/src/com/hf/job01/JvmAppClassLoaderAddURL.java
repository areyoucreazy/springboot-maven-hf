package com.hf.job01;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Properties;

/**
 * 通过反射调用addUrl方法加载文件
 * @author hfzhang
 * @date 2021/1/11
 */
public class JvmAppClassLoaderAddURL {
    public static void main(String[] args) {
        String path = System.getProperty("user.dir");
        String appPath = "file:" + path + "\\jvm-actual\\src";
        System.out.println(appPath);
        URLClassLoader urlClassLoader = (URLClassLoader) JvmAppClassLoaderAddURL.class.getClassLoader();
        try {
            Method addURL = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
            addURL.setAccessible(true);
            URL url = new URL(appPath);
            addURL.invoke(urlClassLoader, url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
