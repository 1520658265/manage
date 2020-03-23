package com.xunjer.linsen.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xunjer.linsen.common.config.config.BaseDao.FieldName;
import com.xunjer.linsen.common.config.config.BaseDao.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author linsen
 * @date 2020/3/23 19:29
 * @tips 明日复明日 明日何其多
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(name = "module_detail")
public class ModuleDetail {

    @FieldName(value = "id",primary = true)
    private Integer id;

    @FieldName(value = "moduleId")
    private Integer moduleId;

    @FieldName(value = "append")
    private String append;

    @FieldName(value = "appender")
    private Integer appender;

    @FieldName(value = "createDate")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    @FieldName(value = "moduleType")
    private Integer moduleType;
}
