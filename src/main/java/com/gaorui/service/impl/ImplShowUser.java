package com.gaorui.service.impl;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.gaorui.bean.UserBean;
import com.gaorui.util.CommonUtil;
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

	@Override
	public int Res(String login_name, String avatar, String email) {

		Long date = null;
		try {
			date = CommonUtil.getSystemTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return userDao.Res(login_name,avatar,email,date,date);
	}

	@Override
	public List<Integer> GetIdByName(List<String> login_Name) {

		return userDao.GetIdByName(login_Name);
	}


}
