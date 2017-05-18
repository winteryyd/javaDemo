package com.deppon.demo.java.DesignPatterns.Observer.java;

import java.util.Observable;
import java.util.Observer;

public class Watcher implements Observer{
    private String name;
	
    public Watcher(Observable o){
        o.addObserver(this);
    }
    
    public Watcher(Observable o, String name) {
    	o.addObserver(this);
    	this.name = name;
	}

	@Override
    public void update(Observable o, Object arg) {
        
        System.out.println(name+"状态发生改变：" + ((Watched)o).getData());
    }

}
