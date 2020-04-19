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
 * @date 2020/3/17 20:11
 * @tips 明日复明日 明日何其多
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(name = "alinsen_event")
public class EventInfo {

    @FieldName(value = "eventId",primary = true)
    private Integer eventId;

    @FieldName(value = "content")
    private String content;

    @FieldName(value = "createDate")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    @FieldName(value = "eventDate")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date eventDate;

    @FieldName(value = "status")
    private Integer status;

    @FieldName(value = "score")
    private Integer score;

    @FieldName(value = "owner")
    private Integer owner;

    @FieldName(value = "tagId")
    private Integer tagId;
}
