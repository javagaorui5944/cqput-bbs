package com.gaorui.service.impl;

import com.gaorui.dao.TopicDao;
import com.gaorui.service.IShowTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
