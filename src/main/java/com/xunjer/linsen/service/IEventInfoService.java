package com.xunjer.linsen.service;

import com.xunjer.linsen.common.model.PageInfo;
import com.xunjer.linsen.common.model.PageList;
import com.xunjer.linsen.common.model.ResultModel;
import com.xunjer.linsen.model.dto.EventDetailInfoDTO;
import com.xunjer.linsen.model.entity.EventInfo;

/**
 * @author linsen
 * @date 2020/3/17 21:38
 * @tips 明日复明日 明日何其多
 */
public interface IEventInfoService {

    ResultModel<PageList<EventDetailInfoDTO>> findEventInfo(Integer tagId, String title, String beginDate, String endDate, Integer owner, Boolean encryption, PageInfo pageInfo);

    ResultModel<Boolean> addEventInfo(EventInfo entity);

    ResultModel<Boolean> deleteEventInfo(Integer eventId);

    ResultModel<Boolean> editEventInfo(EventInfo entity);
}
