package com.gaorui.service;

import java.util.List;
import java.util.Map;

import com.gaorui.bean.UserBean;

public interface IShowUser {

	List<UserBean> ShowUser();
	List<Map<String,Object>> ShowUserNotice(int uId);
	UserBean ShowMeUser(int uId);
}
