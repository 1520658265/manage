package com.xunjer.linsen.common.config.BaseDao;

/**
 * @author linsen
 * @date 2020/3/17 20:40
 * @tips 明日复明日 明日何其多
 */
public class LinsenException extends Exception {

    //无参构造方法
    public LinsenException(){

        super();
    }

    //有参的构造方法
    public LinsenException(String message){
        super(message);

    }
}
