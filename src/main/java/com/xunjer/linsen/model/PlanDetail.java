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
 * @date 2020/3/23 19:46
 * @tips 明日复明日 明日何其多
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(name = "plan_detail")
public class PlanDetail {

    @FieldName(value = "id",primary = true)
    private Integer id;

    @FieldName(value = "planId")
    private Integer planId;

    @FieldName(value = "wordDate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date wordDate;

    @FieldName(value = "content")
    private String content;

    @FieldName(value = "status")
    private Integer status;

    @FieldName(value = "score")
    private Integer score;
}
