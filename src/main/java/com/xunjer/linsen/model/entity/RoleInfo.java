package com.xunjer.linsen.model.entity;

import com.xunjer.linsen.common.config.BaseDao.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author linsen
 * @date 2020/3/23 19:27
 * @tips 明日复明日 明日何其多
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(name = "role_info")
public class RoleInfo {

    private Integer roleId;

    private String roleName;

    private String menuName;
}
