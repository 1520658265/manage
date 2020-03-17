package com.xunjer.linsen.dao;

import com.xunjer.linsen.common.config.config.BaseDao.BaseDao;
import com.xunjer.linsen.model.EventInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author linsen
 * @date 2020/3/17 21:32
 * @tips 明日复明日 明日何其多
 */
@Repository
public class EventInfoDaoImpl extends BaseDao<EventInfo> {

    @Autowired
    @Qualifier("jdbc_param_picture_book")
    private NamedParameterJdbcTemplate jdbcTemplate;
}
