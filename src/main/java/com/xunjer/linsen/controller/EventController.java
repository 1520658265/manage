package com.xunjer.linsen.controller;

import com.xunjer.linsen.common.config.model.ResultModel;
import com.xunjer.linsen.model.EventInfo;
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
@Api(value = "事件管理接口")
public class EventController {

    @Autowired
    private IEventInfoService eventInfoService;

    @RequestMapping(value = "find",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "查询单个事件")
    public ResultModel<EventInfo> find(Integer eventId){
        return eventInfoService.find(eventId);
    }
}
