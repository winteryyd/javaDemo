package com.deppon.demo.java.DesignPatterns.Singleton;
/**  
 *方法二
 * 单例模式的实现：饱汉式,线程安全简单实现   
 */  
public class Singleton2 {

    // 定义私有构造方法（防止通过 new Singleton2()去实例化）
    private Singleton2() {   
    }   

    // 定义一个Singleton2类型的变量（不初始化，注意这里没有使用final关键字）
    private static Singleton2 instance;   

 // 定义一个静态的方法（调用时再初始化SingletonTest，使用synchronized 避免多线程访问时，可能造成重的复初始化问题）
    public synchronized static Singleton2 getInstance() {   
        if (instance == null)   
            instance = new Singleton2();   
        return instance;   
    }   
}