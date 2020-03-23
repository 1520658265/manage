package com.xunjer.linsen.model;

import com.xunjer.linsen.common.config.config.BaseDao.FieldName;
import com.xunjer.linsen.common.config.config.BaseDao.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author linsen
 * @date 2020/3/23 20:32
 * @tips 明日复明日 明日何其多
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(name = "type_tag_info")
public class TypeTagInfo {

    @FieldName(value = "tagId",primary = true)
    private Integer tagId;

    @FieldName(value = "name")
    private String name;

    @FieldName(value = "type")
    private Integer type;
}
