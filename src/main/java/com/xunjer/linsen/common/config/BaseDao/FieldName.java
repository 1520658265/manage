package com.xunjer.linsen.common.config.BaseDao;

import java.lang.annotation.*;

/**
 * @author linsen
 * @date 2020/3/17 20:16
 * @tips 明日复明日 明日何其多
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FieldName {
    String value() ;

    boolean primary() default false;
}
