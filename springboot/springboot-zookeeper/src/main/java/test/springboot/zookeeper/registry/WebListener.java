package test.springboot.zookeeper.registry;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import test.springboot.zookeeper.registry.ServiceRegistry;
@Component
public class WebListener implements ApplicationListener<EmbeddedServletContainerInitializedEvent>{
	private static Logger logger = LoggerFactory.getLogger(WebListener.class);
	
	private String serverAddress;
	
	private int serverPort;
	
	@Autowired
	private ServiceRegistry serviceRegistry;

	public void onApplicationEvent(EmbeddedServletContainerInitializedEvent event) {
		this.serverPort = event.getEmbeddedServletContainer().getPort();
		try {
			serverAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
        	logger.error("get server host Exception e:", e);
        }
		
		logger.debug("serverAddress:serverPort => {}:{}",serverAddress,serverPort);
		
		ApplicationContext  applicationContext = event.getApplicationContext();
		RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
		Map<RequestMappingInfo, HandlerMethod> infoMap = mapping.getHandlerMethods();
		for(RequestMappingInfo info:infoMap.keySet()){
			String serviceName = info.getName();
			logger.debug("serviceName is :{}",info.getPatternsCondition().getPatterns());
			if(serviceName!=null){
				serviceRegistry.registry(info.getPatternsCondition().getPatterns(), String.format("%s:%d", serverAddress,serverPort));
			}
		}
	}

}
