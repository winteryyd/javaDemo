package test.springboot.zookeeper.registry;

import java.util.Set;

/**
 * 服务注册表
 * @author 266372
 *
 */
public interface ServiceRegistry {
	/**
	 * 注册服务信息
	 * @param serviceName
	 * @param serviceAddress
	 */
	void registry(String serviceName,String serviceAddress);
	
	void registry(Set<String> serviceName,String serviceAddress);
}
