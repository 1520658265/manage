package com.xunjer.linsen.dao;

import com.xunjer.linsen.common.PageStr;
import com.xunjer.linsen.common.config.BaseDao.AbstractBaseDao;
import com.xunjer.linsen.common.model.PageInfo;
import com.xunjer.linsen.common.model.PageList;
import com.xunjer.linsen.model.dto.EventDetailInfoDTO;
import com.xunjer.linsen.model.entity.EventInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author linsen
 * @date 2020/3/17 21:32
 * @tips 明日复明日 明日何其多
 */
@Repository
public class EventInfoDaoImpl extends AbstractBaseDao<EventInfo> {

    @Autowired
    @Qualifier("jdbc_param_linsen")
    private NamedParameterJdbcTemplate jdbcTemplate;

    public PageList<EventDetailInfoDTO> select(Integer tagId, String title, String beginDate, String endDate, Integer owner, Boolean encryption, PageInfo pageInfo){
        String select = " select * from event_info";
        String countSql = " select count(1) from event_info";
        String whereStr = " where 1=1";
        MapSqlParameterSource params = new MapSqlParameterSource();
        if(tagId!=null){
            whereStr += " and tagId=:tagId";
            params.addValue("tagId",tagId);
        }
        if(StringUtils.isNotEmpty(title)){
            whereStr += " and title like :title";
            params.addValue("title","%"+title+"%");
        }
        if(StringUtils.isNotEmpty(beginDate)){
            whereStr += " and eventDate between :beginDate and :endDate ";
            params.addValue("beginDate",beginDate);
            params.addValue("endDate",endDate);
        }
        if(owner!=null){
            whereStr += " and owner=:owner";
            params.addValue("owner",owner);
        }
        if(tagId!=null){
            whereStr += " and tagId=:tagId";
            params.addValue("encryption",encryption);
        }
        countSql += whereStr;
        Long total = jdbcTemplate.queryForObject(countSql,params,Long.class);
        select += whereStr;
        PageStr.getPageStr(params,pageInfo);
        List<EventDetailInfoDTO> list = jdbcTemplate.query(select,params,new BeanPropertyRowMapper<>(EventDetailInfoDTO.class));
        PageList<EventDetailInfoDTO> result = new PageList<>();
        result.setTotal(total);
        result.setList(list);
        return result;
    }

    @Override
    protected NamedParameterJdbcTemplate getJdbcTemplate() {
        return this.jdbcTemplate;
    }
}
