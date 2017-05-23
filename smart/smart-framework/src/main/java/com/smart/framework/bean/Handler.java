package com.smart.framework.bean;

import java.lang.reflect.Method;

/**
 * 封装 Action 信息
 *
 * @since 1.0.0
 */
public class Handler {

    /**
     * Controller 类
     */
    private Class<?> controllerClass;

    /**
     * Action 方法
     */
    private Method actionMethod;

    public Handler(Class<?> controllerClass, Method actionMethod) {
        this.controllerClass = controllerClass;
        this.actionMethod = actionMethod;
    }

    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public Method getActionMethod() {
        return actionMethod;
    }
    
    public String toString(){
		return controllerClass.getName()+"-->"+actionMethod.getName();
    }
}
