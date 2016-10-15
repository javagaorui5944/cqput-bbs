package com.gaorui.dao;

import java.util.List;
import java.util.Map;

import com.gaorui.bean.UserBean;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
	 List<UserBean> ShowUser();
}
