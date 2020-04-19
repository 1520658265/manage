package com.xunjer.linsen.dao;

import com.xunjer.linsen.common.config.BaseDao.AbstractBaseDao;
import com.xunjer.linsen.model.entity.ModuleDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author linsen
 * @date 2020/3/24 15:56
 * @tips 明日复明日 明日何其多
 */
@Repository
public class ModuleDetailDaoImpl extends AbstractBaseDao<ModuleDetail> {


    @Autowired
    @Qualifier("jdbc_param_linsen")
    private NamedParameterJdbcTemplate jdbcTemplate;

    public List<ModuleDetail> findByModule(Integer moduleType, Integer pid){
        String sql = "select * from module_detail where moduleType=:moduleType and pid =:pid";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("moduleType",moduleType);
        params.addValue("pid",pid);
        return jdbcTemplate.query(sql,params,new BeanPropertyRowMapper<>(ModuleDetail.class));
    }

    @Override
    protected NamedParameterJdbcTemplate getJdbcTemplate() {
        return null;
    }
}
