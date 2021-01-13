package com.hf.job01;

import sun.misc.Launcher;

import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

/**
 * 查看各个类加载器加载了哪些jar
 * @author hfzhang
 * @date 2021/1/5
 */
public class JvmClassLoaderPrintPath {
    public static void main(String[] args) {
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        System.out.println("启动类加载器");
        for(URL url : urLs){
            System.out.println("===> "+url.toExternalForm());
        }

        //扩展类加载器
        printClassLoader("扩展类加载器", JvmClassLoaderPrintPath.class.getClassLoader().getParent());

        //应用类加载器
        printClassLoader("应用类加载器", JvmClassLoaderPrintPath.class.getClassLoader());
    }

    public static void printClassLoader(String name, ClassLoader cl){
        if (cl!=null){
            System.out.println(name + " Classloader -> " + cl.toString());
            printURLForClassloader(cl);
        }else {
            System.out.println(name + " Classloader -> null ");
        }
    }

    private static void printURLForClassloader(ClassLoader cl) {
        Object ucp = insightField(cl, "ucp");
        Object path = insightField(ucp, "path");
        ArrayList pathList = (ArrayList) path;
        for(Object p : pathList){
            System.out.println(" ===> "+p.toString());
        }
    }

    private static Object insightField(Object obj, String fileName) {
        try {
            Field  f = null;
            if(obj instanceof URLClassLoader){
                f = URLClassLoader.class.getDeclaredField(fileName);
            }else {
                f = obj.getClass().getDeclaredField(fileName);
            }
            f.setAccessible(true);
            return f.get(obj);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
