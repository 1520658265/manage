package com.xunjer.linsen.model.entity;

import com.xunjer.linsen.common.config.BaseDao.FieldName;
import com.xunjer.linsen.common.config.BaseDao.TableName;
import lombok.Data;

/**
 * @author linsen
 * @date 2020/4/1 20:53
 * @tips 明日复明日 明日何其多
 */
@Data
@TableName(name = "pa_chong")
public class PaChong {

    @FieldName(value = "id",primary = true)
    private Integer id;

    private String name;

    private String author;

    private String press;

    private String score;

    private String comment;

    private String source;

    private String age;

    private String seriesName;

}
