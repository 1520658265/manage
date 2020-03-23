package com.xunjer.linsen.service;

import com.xunjer.linsen.common.config.model.PageInfo;
import com.xunjer.linsen.common.config.model.PageList;
import com.xunjer.linsen.common.config.model.ResultModel;
import com.xunjer.linsen.model.EventInfo;

/**
 * @author linsen
 * @date 2020/3/17 21:38
 * @tips 明日复明日 明日何其多
 */
public interface IEventInfoService {

    ResultModel<PageList<EventInfo>> findEventInfo(Integer tagId, String title, String beginDate, String endDate, Integer owner, Boolean encryption, PageInfo pageInfo);

    ResultModel<Boolean> addEventInfo(EventInfo entity);

    ResultModel<Boolean> deleteEventInfo(Integer eventId);
}
