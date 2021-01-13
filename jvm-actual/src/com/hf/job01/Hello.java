package com.hf.job01;

/**
 * 课后作业1    基本类型、四则运算、if和for，分析对应字节码
 * D:\User\workspace\sport\target\test-classes>javap -c com.messi.sport.Hello
 * D:\User\workspace\sport\target\test-classes>javap -c -verbose com.messi.sport.Hello
 * @author hfzhang
 * @date 2021/1/11
 */
public class Hello {

    static {
        System.out.println("初始化 Hello");
    }

    public static void main(String[] args) {
        int a = 1;
        int b = 2;
        int c = 3;

        int result = compute(a,b,c);
        System.out.println("计算结果："+result);
        if(result > 1){
            System.out.println("计算结果大于1");
        }

        for(int i=0;i<result;i++){
            System.out.println("循环"+(i+1)+"次");
        }
    }

    private static int compute(int a, int b, int c) {
        int result = a + b*c/2 - 1;
        return result;
    }
}
