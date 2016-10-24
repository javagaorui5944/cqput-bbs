package com.gaorui.dao;

import java.util.List;
import java.util.Map;

import com.gaorui.bean.UserBean;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
	 List<UserBean> ShowUser();
	 int GetUserIDByTId(int topicId);
	 List<Map<String,Object>> ShowUserNotice(int uId);
	 UserBean ShowMeUser(int uId);
}
