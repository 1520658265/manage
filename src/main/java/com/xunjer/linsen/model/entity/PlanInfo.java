package com.xunjer.linsen.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xunjer.linsen.common.config.BaseDao.FieldName;
import com.xunjer.linsen.common.config.BaseDao.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author linsen
 * @date 2020/3/23 19:41
 * @tips 明日复明日 明日何其多
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(name = "plan_info")
public class PlanInfo {

    @FieldName(value = "planId",primary = true)
    private Integer planId;

    @FieldName(value = "title")
    private String title;

    @FieldName(value = "createDate")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    @FieldName(value = "beginDate")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date beginDate;

    @FieldName(value = "endDate")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endDate;

    @FieldName(value = "status")
    private Integer status;

    @FieldName(value = "score")
    private Integer score;

    @FieldName(value = "result")
    private String result;
}
