package com.xunjer.linsen.dao;

import com.xunjer.linsen.common.config.BaseDao.AbstractBaseDao;
import com.xunjer.linsen.model.entity.PaChong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author linsen
 * @date 2020/4/1 20:55
 * @tips 明日复明日 明日何其多
 */
@Repository
public class PaChongDaoImpl extends AbstractBaseDao<PaChong> {

    @Autowired
    @Qualifier("jdbc_param_linsen")
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    protected NamedParameterJdbcTemplate getJdbcTemplate() {
        return null;
    }
}
