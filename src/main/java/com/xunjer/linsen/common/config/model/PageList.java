package com.xunjer.linsen.common.config.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author linsen
 * @date 2020/3/17 20:08
 * @tips 明日复明日 明日何其多
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageList<T> {

    private Long total;

    private List<T> list;
}
