package com.xunjer.linsen.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xunjer.linsen.common.config.config.BaseDao.FieldName;
import com.xunjer.linsen.common.config.config.BaseDao.TableName;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;

import java.util.Date;

/**
 * @author linsen
 * @date 2020/3/18 17:26
 * @tips 明日复明日 明日何其多
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(name = "user_info")
public class UserInfo {

    @FieldName(value = "userId",primary = true)
    private Integer userId;

    @FieldName(value = "userName")
    private String userName;

    @FieldName(value = "password")
    private String password;

    @FieldName(value = "nickName")
    private String nickName;

    @FieldName(value = "avatar")
    private String avatar;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @FieldName(value = "createDate")
    private Date createDate;

    @FieldName(value = "role")
    private String role;

}
