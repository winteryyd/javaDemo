package com.deppon.demo.java.DesignPatterns.Observer.java.Event;

public class EventTest {

	public static void main(String[] args) {
		MyEventSourceObject object = new MyEventSourceObject();  
        //注册监听器  
        object.addMyListener(new MyEventListener(){  
            @Override  
            public void fireMyEvent(MyEvent e) {  
                super.fireMyEvent(e);  
            }  
        });  
        //触发事件  
        object.setName("eric");  
	}

}
