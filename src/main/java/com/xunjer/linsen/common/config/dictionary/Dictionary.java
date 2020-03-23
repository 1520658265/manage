package com.xunjer.linsen.common.config.dictionary;

import io.swagger.models.auth.In;

/**
 * @author linsen
 * @date 2020/3/23 19:31
 * @tips 明日复明日 明日何其多
 */
public class Dictionary {

    public enum Module_Type{
        Event(1,"事件模块"),

        Mood(2,"心情模块");

        private Integer key;

        private String desc;

        Module_Type(final Integer key,final String desc){
            this.key=key;
            this.desc=desc;
        }
        public Integer key(){
            return this.key;
        }
        public String desc(){
            return this.desc;
        }
    }


    public enum Plan_Status{
        Start(0,"进行中"),

        Runing(1,"已完成"),

        FInish(-1,"失败");

        private Integer key;

        private String desc;

        Plan_Status(final Integer key,final String desc){
            this.key=key;
            this.desc=desc;
        }
        public Integer key(){
            return this.key;
        }
        public String desc(){
            return this.desc;
        }
    }
}
