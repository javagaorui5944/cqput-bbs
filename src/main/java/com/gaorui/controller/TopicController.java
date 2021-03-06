package com.gaorui.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gaorui.service.IShowNotice;
import com.gaorui.service.IShowTopic;
import com.gaorui.service.IShowUser;
import com.gaorui.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.json.Json;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 * Created by gaorui on 16/10/16.
 */
@Controller
@RequestMapping(value="Topic")
public class TopicController {
    @Autowired
    IShowTopic iShowTopic;
    @Autowired
    IShowNotice iShowNotice;
    @Autowired
    private IShowUser iShowUser;
    /**
     * 返回topic列表,分页
     * @param pageSize 第几页
     * @return
     */
    @RequestMapping(value="ShowTopic")
    @ResponseBody
    public JSONObject ShowTopic
    (@RequestParam(value = "pageSize" , required = false) Integer pageSize){

        if(pageSize ==null)
            pageSize =1;

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


    /**
     * 返回帖子上端具体详情
     * @param topicId
     * @return
     */
    @RequestMapping(value="ShowTopicDetails")
    @ResponseBody
    public JSONObject ShowTopicDetails
            (@RequestParam(value = "topicId" , required = true) int topicId){

        List<Map<String,Object>> listmap_Commnent = iShowTopic.ShowTopicComment(topicId);
        Map<String,Object> map_Details = iShowTopic.ShowTopicDetails(topicId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("listmap_Commnent",listmap_Commnent);
        jsonObject.put("map_Details",map_Details);

        return CommonUtil.constructResponse(1,"topicCount",jsonObject);
    }


    /**
     * 返回贴子评论
     * @param topicId
     * @param
     * @return
     */
    @RequestMapping(value="ShowTopicComment")
    @ResponseBody
    public JSONObject ShowTopicComment
            (@RequestParam(value = "topicId" , required = true) int topicId){


        return CommonUtil.constructResponse(1,"topicCount",iShowTopic.ShowTopicComment(topicId));
    }

    /**
     * 批量插入评论
     * @param commentValue,json序列化后字符串,
     * @return
     */
    @RequestMapping(value = "AddTopicComment")
    @ResponseBody
    public JSONObject AddTopicComment(@RequestBody String commentValue){

                System.out.println("commentValue:"+commentValue);
            if(commentValue.length() == 0)
                return CommonUtil.constructResponse(0,"comment null",null);



            JSONObject commentValueJson = JSON.parseObject(commentValue);
            String content = commentValueJson.getString("content");
            int topicId = commentValueJson.getIntValue("topicId");
            List<String> string_LoginName = CommonUtil.patternLoiginName(content);

            List<Integer> uIds = iShowUser.GetIdByName(string_LoginName);



            int resTopicComment = iShowTopic.AddTopicComment(topicId,uIds,content);
            //私信推送功能后期上RabbitMq
            int resNoticComment = iShowNotice.AddNoticeComment(topicId,1);
            int resNoticAt = iShowNotice.ADDNoticeAt(topicId,uIds,1);
            if(resTopicComment >0 && resNoticComment>0 && resNoticAt>0)
                return CommonUtil.constructResponse(1,"comment add success",null);
            else
                return CommonUtil.constructResponse(0,"comment add fail",null);
    }


    /**
     * 发布主题
     * @param topicValue
     * @return
     */
    @RequestMapping(value = "AddTopic")
    @ResponseBody
    public JSONObject AddTopic(@RequestBody String topicValue){

        if(topicValue.length() == 0)
            return  CommonUtil.constructResponse(0,"topic add null",null);


        JSONObject topicValueJson = JSON.parseObject(topicValue);

       // int nid = topicValueJson.getIntValue("nid");
        String title = topicValueJson.getString("title");
        String content = topicValueJson.getString("content");
        int res = iShowTopic.AddTopic(10,13,title,content);
        if(res>0)
            return CommonUtil.constructResponse(1,"add topic success",null);
        else
        return  CommonUtil.constructResponse(0,"add topic fail",null);
    }

    /**
     * test text/html
     * @param
     * @return
     */
    @RequestMapping(value = "testHtml")
    public void testHtml(HttpServletRequest request, HttpServletResponse response){

        response.setContentType("text/html");
        String S = "<Html>ssss</Html>";
        try {
            PrintWriter pw = response.getWriter();
            pw.print(S);
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
