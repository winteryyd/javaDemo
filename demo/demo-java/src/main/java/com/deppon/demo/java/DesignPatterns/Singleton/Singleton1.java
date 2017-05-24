package com.deppon.demo.java.DesignPatterns.Singleton;
/** 
 * 方法一
 * 单例模式的实现：饿汉式,线程安全 但效率比较低 
 */  
public class Singleton1 {  

    // 定义一个私有的构造方法
    private Singleton1() {  
    }  

    // 将自身的实例对象设置为一个属性,并加上Static和final修饰符
    private static final Singleton1 instance = new Singleton1();  

    // 静态方法返回该类的实例
    public static Singleton1 getInstancei() {  
        return instance;  
    }  
  
}