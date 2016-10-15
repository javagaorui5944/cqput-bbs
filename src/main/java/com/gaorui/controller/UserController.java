package com.gaorui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.gaorui.service.IShowUser;
import com.gaorui.util.CommonUtil;



@Controller
@RequestMapping(value="User")
public class UserController {
	@Autowired
	private IShowUser iShowUser;


	/**
	 * 返回所有user
	 * @return
     */
	 @RequestMapping(value="ShowUser")
	 @ResponseBody
	 public JSONObject ShowUser(){

	    	return CommonUtil.constructResponse(1,"user详情",iShowUser.ShowUser());
	    }
	 
	 @RequestMapping(value="Hello")
	 public String Hello(){
	    	
	    	return "Hello";
	    }
}
