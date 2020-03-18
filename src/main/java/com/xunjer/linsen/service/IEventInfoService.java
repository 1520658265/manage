package com.xunjer.linsen.service;

import com.xunjer.linsen.common.config.model.ResultModel;
import com.xunjer.linsen.model.EventInfo;
import org.springframework.data.domain.Page;

/**
 * @author linsen
 * @date 2020/3/17 21:38
 * @tips 明日复明日 明日何其多
 */
public interface IEventInfoService {

    ResultModel<EventInfo> find(Integer eventId);

    ResultModel<Page<EventInfo>> findEventInfo(Integer eventId,Integer tagId,Integer status,Integer result,String keyword);

    ResultModel<Boolean> addEventInfo(EventInfo entity);

    ResultModel<Boolean> deleteEventInfo(Integer eventId);
}
