package com.xunjer.linsen.controller;

import com.xunjer.linsen.common.model.PageInfo;
import com.xunjer.linsen.common.model.PageList;
import com.xunjer.linsen.common.model.ResultModel;
import com.xunjer.linsen.model.dto.EventDetailInfoDTO;
import com.xunjer.linsen.model.entity.EventInfo;
import com.xunjer.linsen.service.IEventInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linsen
 * @date 2020/3/17 21:41
 * @tips 明日复明日 明日何其多
 */
@RestController
@RequestMapping("event")
@Api(value = "eventController",tags = "事件管理接口")
public class EventController {

    @Autowired
    private IEventInfoService eventInfoService;

    @RequestMapping(value = "find",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "查询事件")
    public ResultModel<PageList<EventDetailInfoDTO>> find(Integer tagId, String title, String beginDate, String endDate, Integer owner, Boolean encryption, PageInfo pageInfo){
        return eventInfoService.findEventInfo(tagId,title,beginDate,endDate,owner,encryption,pageInfo);
    }

    @RequestMapping(value = "add",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "增加事件")
    public ResultModel<Boolean> add(EventInfo eventInfo){
        return eventInfoService.addEventInfo(eventInfo);
    }

    @RequestMapping(value = "update",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "修改事件")
    public ResultModel<Boolean> update(EventInfo eventInfo){
        return eventInfoService.editEventInfo(eventInfo);
    }
}
