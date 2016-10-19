package com.gaorui.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface NoticeDao {
	int AddNoticeComment(@Param("topicId") int topicId, @Param("uId")int uId, @Param("to_UId") int to_UId, @Param("date") long date);
}
