package com.xunjer.linsen.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author linsen
 * @date 2020/3/17 20:06
 * @tips 明日复明日 明日何其多
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageInfo {

    private Integer page;

    private Integer rows;

    private Integer total;
}
