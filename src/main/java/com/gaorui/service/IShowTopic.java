package com.gaorui.service;

import java.util.List;
import java.util.Map;

/**
 * Created by gaorui on 16/10/16.
 */
public interface IShowTopic {

    List<Map<String,Object>> getTopics(int pageSize);
    int getTopicCount();
    Map<String,Object> ShowTopicDetails(int topicId);
    List<Map<String,Object>> ShowTopicComment(int topicId,int pageSize);

}
