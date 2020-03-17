package com.xunjer.linsen.service.impl;

import com.xunjer.linsen.common.config.model.ResultModel;
import com.xunjer.linsen.dao.EventInfoDaoImpl;
import com.xunjer.linsen.model.EventInfo;
import com.xunjer.linsen.service.IEventInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author linsen
 * @date 2020/3/17 21:39
 * @tips 明日复明日 明日何其多
 */
@Service
public class EventInfoServiceImpl implements IEventInfoService  {

    @Autowired
    private EventInfoDaoImpl eventInfoDao;

    @Override
    public ResultModel<EventInfo> find(Integer eventId) {
        return new ResultModel<>(eventInfoDao.find(eventId));
    }
}
