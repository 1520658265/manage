package com.xunjer.linsen.common;

import com.xunjer.linsen.common.model.PageInfo;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

/**
 * @author linsen
 * @date 2020/3/24 15:39
 * @tips 明日复明日 明日何其多
 */
public class PageStr {

    public static String getPageStr(MapSqlParameterSource paramSource, PageInfo p) {
        String sql = "";
        int startIndex = 0;
        int count = 1;
        if (p != null) {
            if(p.getPage()!=null&&p.getRows()!=null){
                startIndex = p.getRows() * (p.getPage() - 1);
                count = p.getRows();
                paramSource.addValue("startIndex", startIndex);
                paramSource.addValue("count", count);
                sql = " LIMIT :startIndex,:count";
            }
        }
        return sql;
    }
}
