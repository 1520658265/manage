package com.xunjer.linsen.service.impl;

import com.xunjer.linsen.common.dictionary.Dictionary;
import com.xunjer.linsen.common.model.PageInfo;
import com.xunjer.linsen.common.model.PageList;
import com.xunjer.linsen.common.model.ResultModel;
import com.xunjer.linsen.dao.EventInfoDaoImpl;
import com.xunjer.linsen.dao.ModuleDetailDaoImpl;
import com.xunjer.linsen.model.dto.EventDetailInfoDTO;
import com.xunjer.linsen.model.entity.EventInfo;
import com.xunjer.linsen.service.IEventInfoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author linsen
 * @date 2020/3/17 21:39
 * @tips 明日复明日 明日何其多
 */
@Service
public class EventInfoServiceImpl implements IEventInfoService  {

    @Autowired
    private EventInfoDaoImpl eventInfoDao;
    @Autowired
    private ModuleDetailDaoImpl detailDao;

    @Override
    public ResultModel<PageList<EventDetailInfoDTO>> findEventInfo(Integer tagId, String title, String beginDate, String endDate, Integer owner, Boolean encryption, PageInfo pageInfo) {
        PageList<EventDetailInfoDTO> pageList = eventInfoDao.select(tagId,title,beginDate,endDate,owner,encryption,pageInfo);
        List<EventDetailInfoDTO> list = pageList.getList();
        list.forEach(s->{
            s.setDetail(detailDao.findByModule(Dictionary.Module_Type.Event.key(),s.getEventId()));
        });
        return new ResultModel<>();
    }

    @Override
    public ResultModel<Boolean> addEventInfo(EventInfo entity) {
        return new ResultModel<>(eventInfoDao.insertSingle(entity)>0);
    }

    @Override
    public ResultModel<Boolean> deleteEventInfo(Integer eventId) {
        return new ResultModel<>(eventInfoDao.deleteSingle(eventId));
    }

    @Override
    public ResultModel<Boolean> editEventInfo(EventInfo entity) {
        return new ResultModel<>(eventInfoDao.updateSingle(entity)>0);
    }
}
