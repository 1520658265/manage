package com.xunjer.linsen.common.config.config.BaseDao;

import java.lang.annotation.*;

/**
 * @author linsen
 * @date 2020/3/17 20:12
 * @tips 明日复明日 明日何其多
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TableName {
    String name();
}
