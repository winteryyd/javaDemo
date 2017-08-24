package test.springboot.zookeeper.registry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.Watcher.Event;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ServiceRegistryImpl implements ServiceRegistry, Watcher {

	private static Logger logger = LoggerFactory
			.getLogger(ServiceRegistryImpl.class);

	private static CountDownLatch latch = new CountDownLatch(1);

	private static final int SESSION_TIMEOUT = 5000;

	private ZooKeeper zk;

	public ServiceRegistryImpl() {
	}

	public ServiceRegistryImpl(String zkServers) {
		try {
			zk = new ZooKeeper(zkServers, SESSION_TIMEOUT, this);
			latch.await();
			logger.debug("connected to zookeeper");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void process(WatchedEvent event) {
		if (event.getState() == Event.KeeperState.SyncConnected) {
			latch.countDown();
		}

	}

	public void registry(String serviceName, String serviceAddress) {
		try {
			// 创建根节点（持久节点）
			String registryPath = "/registry";
			if (zk.exists(registryPath, false) == null) {
				zk.create(registryPath, null, ZooDefs.Ids.OPEN_ACL_UNSAFE,
						CreateMode.PERSISTENT);
				logger.debug("create registry node:{}", registryPath);
			}
			// 创建服务节点（持久节点）
			String servicePath = registryPath + "/" + serviceName;
			if (zk.exists(servicePath, false) == null) {
				zk.create(servicePath, null, ZooDefs.Ids.OPEN_ACL_UNSAFE,
						CreateMode.PERSISTENT);
				logger.debug("create service node:{}", servicePath);
			}
			// 创建地址节点（临时顺序节点）
			String addressPath = servicePath + "/address-";
			String addressNode = zk.create(addressPath,
					serviceAddress.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
					CreateMode.EPHEMERAL_SEQUENTIAL);
			logger.debug("create address node:{} => {}", addressNode,
					serviceAddress);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void registry(Set<String> serviceName, String serviceAddress) {
		try {
			
			// 根节点（持久节点）
			String registryPath = "/registry";
			for (String servicePath : serviceName) {
				// 创建服务节点（持久节点）
				String path = registryPath + servicePath;
				List<String> arr = new ArrayList<String>();
				Stat stat = zk.exists(path, null);
				if (stat == null) {
					arr.add(path);
					while (path.lastIndexOf('/') != 0) {
						path = path.substring(0, path.lastIndexOf('/'));
						Stat tmp = zk.exists(path, null);
						if (tmp == null) {
							arr.add(path);
						} else {
							break;
						}
					}
				}
				if (arr.size() != 0) {
					Collections.reverse(arr);
					for (String tmp : arr) {
						zk.create(tmp, null, ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
					}
				}
				// 创建地址节点（临时顺序节点）
				String addressPath = registryPath +servicePath + "/address-";
				String addressNode = zk.create(addressPath,
						serviceAddress.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
						CreateMode.EPHEMERAL_SEQUENTIAL);
				logger.debug("create address node:{} => {}", addressNode,
						serviceAddress);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
