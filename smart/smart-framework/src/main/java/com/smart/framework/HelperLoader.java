package com.smart.framework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smart.framework.helper.AopHelper;
import com.smart.framework.helper.BeanHelper;
import com.smart.framework.helper.ClassHelper;
import com.smart.framework.helper.ControllerHelper;
import com.smart.framework.helper.IocHelper;
import com.smart.framework.util.ClassUtil;

/**
 * 加载相应的 Helper 类
 *
 * @since 1.0.0
 */
public final class HelperLoader {
	private static final Logger logger = LoggerFactory.getLogger(HelperLoader.class);

    public static void init() {
        Class<?>[] classList = {
            ClassHelper.class,
            BeanHelper.class,
            AopHelper.class,
            IocHelper.class,
            ControllerHelper.class
        };
        for (Class<?> cls : classList) {
            ClassUtil.loadClass(cls.getName());
        }
    }
}