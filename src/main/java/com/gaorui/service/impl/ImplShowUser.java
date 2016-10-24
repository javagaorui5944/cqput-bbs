package com.gaorui.service.impl;

import java.util.List;
import java.util.Map;

import com.gaorui.bean.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaorui.dao.UserDao;
import com.gaorui.service.IShowUser;

@Service
public class ImplShowUser implements IShowUser {
	@Autowired  
	private UserDao userDao;
	
	@Override
	public List<UserBean> ShowUser() {
		// TODO Auto-generated method stub
		return userDao.ShowUser();
	}

	@Override
	public List<Map<String, Object>> ShowUserNotice(int uId) {

		return userDao.ShowUserNotice(uId);
	}

	@Override
	public UserBean ShowMeUser(int uId) {
		return userDao.ShowMeUser(uId);
	}


}
