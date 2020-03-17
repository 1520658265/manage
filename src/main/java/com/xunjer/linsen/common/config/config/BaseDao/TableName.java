package com.xunjer.linsen.common.config.config.BaseDao;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * @author linsen
 * @date 2020/3/17 20:12
 * @tips 明日复明日 明日何其多
 */
@Target(ElementType.TYPE)
public @interface TableName {
    String name();
}
