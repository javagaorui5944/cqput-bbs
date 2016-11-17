package com.gaorui.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
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
    List<Map<String,Object>> ShowTopicComment(@Param("topicId") int topicId);
    int AddTopicComment(@Param("topicId")int topicId,@Param("uIds") List<Integer> uIds,
                       @Param("content") String content,@Param("date") Long date);

    int AddTopic(@Param("uid") int uid,@Param("nid") int nid,@Param("title") String title,
                 @Param("content") String content,
                 @Param("create_time") Long create_time,@Param("update_time") Long update_time);
}
