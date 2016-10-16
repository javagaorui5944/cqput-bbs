package com.gaorui.controller;

import com.alibaba.fastjson.JSONObject;
import com.gaorui.service.IShowTopic;
import com.gaorui.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by gaorui on 16/10/16.
 */
@Controller
@RequestMapping(value="Topic")
public class TopicController {
    @Autowired
    IShowTopic iShowTopic;

    /**
     * 返回topic列表,分页
     * @param pageSize 第几页
     * @return
     */
    @RequestMapping(value="ShowTopic")
    @ResponseBody
    public JSONObject ShowTopic
    (@RequestParam(value = "pageSize" , required = false) Integer pageSize){

        if(pageSize ==null){
            pageSize =1;
        }
        return CommonUtil.constructResponse(1,"topic详情",iShowTopic.getTopics(pageSize));
    }

    /**
     * 返回topic size
     * @return
     */
    @RequestMapping(value="ShowTopicCount")
    @ResponseBody
    public JSONObject ShowTopicCount(){

        return CommonUtil.constructResponse(1,"topicCount",iShowTopic.getTopicCount());
    }



    @RequestMapping(value="ShowTopicDetails")
    @ResponseBody
    public JSONObject ShowTopicDetails
            (@RequestParam(value = "topicId" , required = true) int topicId){

        return CommonUtil.constructResponse(1,"topicCount",iShowTopic.ShowTopicDetails(topicId));
    }

}
