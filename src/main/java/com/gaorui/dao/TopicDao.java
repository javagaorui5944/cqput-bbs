package com.gaorui.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by gaorui on 16/10/16.
 */
@Repository
public interface TopicDao {
    List<Map<String, Object>> getTopics(int pageSize);
    int getTopicCount();
    Map<String,Object> ShowTopicDetails(int topicId);
}
