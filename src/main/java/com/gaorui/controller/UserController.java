package com.gaorui.controller;

import com.alibaba.fastjson.JSON;
import com.gaorui.util.ParamUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.gaorui.service.IShowUser;
import com.gaorui.util.CommonUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


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

	/**
	 * user个人收到的评论
	 * @return
     */
	 @RequestMapping(value="ShowUserNotice")
	 @ResponseBody
		public JSONObject ShowUserNotice(){

			return CommonUtil.constructResponse(1,"user_Person_Notice",iShowUser.ShowUserNotice(1));
		}
	 
	 @RequestMapping(value="Hello")
	 public String Hello(){
	    	
	    	return "Hello";
	    }

	/**
	 * user个人信息
	 * @return
	 */
	@RequestMapping(value="ShowMeUser")
	@ResponseBody
	public JSONObject ShowMeUser(){

		return CommonUtil.constructResponse(1,"user_Person_Notice",null);
	}

	/**
	 * user个人信息
	 * @return
	 */
	@RequestMapping(value="RegisteredByGithub")
	@ResponseBody
	public JSONObject RegisteredByGithub(String code){



		String me =CommonUtil.sendPost
				("https://github.com/login/oauth/access_token?client_id="+ParamUtil.client_id+"&client_secret="+ParamUtil.client_secret+"&code="+code+"&redirect_uri=http://127.0.0.1:8080/cqput-bbs/User/RegisteredByGithub.do",null);

		String atoke = me.split("&")[0];

		String res = CommonUtil.sendGet("https://api.github.com/user?"+atoke+"");
		JSONObject user = (JSONObject) JSON.parse(res);

		return CommonUtil.constructResponse(1,"user_Person_Notice",user);
	}




}
