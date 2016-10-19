package com.gaorui.service.impl;

import com.gaorui.dao.TopicDao;
import com.gaorui.service.IShowTopic;
import com.gaorui.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by gaorui on 16/10/16.
 */
@Service
public class ImplShowTopic implements IShowTopic {

    @Autowired
    private TopicDao topicDao;

    @Override
    public List<Map<String, Object>> getTopics(int pageSize) {

        pageSize = pageSize*10-10;

        return topicDao.getTopics(pageSize);
    }

    @Override
    public int getTopicCount() {

        return topicDao.getTopicCount();
    }

    @Override
    public Map<String, Object> ShowTopicDetails(int topicId) {
        return topicDao.ShowTopicDetails(topicId);
    }

    @Override
    public List<Map<String,Object>> ShowTopicComment(int topicId,int pageSize) {

        pageSize = pageSize*10-10;

        return topicDao.ShowTopicComment(topicId,pageSize);
    }

    @Override
    public int AddTopicComment(int topicId, List<Integer> uIds, String content) {
        int res =0;
        try {
            Long date =  CommonUtil.getSystemTime();
             res =topicDao.AddTopicComment(topicId,uIds,content,date);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return res;
    }


}
