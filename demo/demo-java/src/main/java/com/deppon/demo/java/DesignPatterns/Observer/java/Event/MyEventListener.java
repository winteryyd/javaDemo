package com.deppon.demo.java.DesignPatterns.Observer.java.Event;

import java.util.EventListener;

/** 
 * 事件监听器，实现java.util.EventListener接口。定义回调方法，将你想要做的事 
 * 放到这个方法下,因为事件源发生相应的事件时会调用这个方法。 
 */  
public class MyEventListener implements EventListener {  
      
    //事件发生后的回调方法  
    public void fireMyEvent(MyEvent e){  
        MyEventSourceObject eObject = (MyEventSourceObject)e.getSource();  
        System.out.println("My name has been changed!");  
        System.out.println("I got a new name,named \""+eObject.getName()+"\"");    }  
}  