package com.gaorui.dao;

import java.util.List;
import java.util.Map;

import com.gaorui.bean.UserBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
	 List<UserBean> ShowUser();
	 int GetUserIDByTId(int topicId);
	 List<Map<String,Object>> ShowUserNotice(int uId);
	 UserBean ShowMeUser(int uId);
	 int Res(@Param("login_name")String login_name,@Param("avatar") String  avatar, @Param("email")String  email, @Param("create_time") Long create_time, @Param("update_time") Long update_time);
	 List<Integer> GetIdByName(@Param("login_Name") List<String> login_Name);
}
