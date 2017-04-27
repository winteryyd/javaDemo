package com.deppon.demo.cache.handler;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 264929 on 2016/5/5.
 *
 * dynamic expire handler
 */
public interface DynamicExpireHandler extends Serializable {
    long handler(Date date);
}
