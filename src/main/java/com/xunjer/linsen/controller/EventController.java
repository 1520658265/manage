package com.xunjer.linsen.controller;

import com.xunjer.linsen.common.config.model.ResultModel;
import com.xunjer.linsen.model.EventInfo;
import com.xunjer.linsen.service.IEventInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linsen
 * @date 2020/3/17 21:41
 * @tips 明日复明日 明日何其多
 */
@RestController
@RequestMapping("event")
public class EventController {

    @Autowired
    private IEventInfoService eventInfoService;

    @RequestMapping("find")
    @ResponseBody
    public ResultModel<EventInfo> find(Integer eventId){
        return eventInfoService.find(eventId);
    }
}
