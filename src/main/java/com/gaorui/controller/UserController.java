package com.gaorui.controller;

import com.alibaba.fastjson.JSON;
import com.gaorui.util.ParamUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.gaorui.service.IShowUser;
import com.gaorui.util.CommonUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Timer;
import java.util.TimerTask;


@Controller
@RequestMapping(value="User")
public class UserController {
	@Autowired
	private IShowUser iShowUser;

	private static final int  MAX_PER_DAY = 5;

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
	 
	 @RequestMapping(value="IsLogin")
	 @ResponseBody
	 public JSONObject IsLogin(HttpSession session,HttpServletRequest request){

		 	System.out.println(session.getId());

		 return CommonUtil.constructResponse(1,"session cookie test",session.getId());
	    }

	@RequestMapping(value="Me")
	@ResponseBody
	public JSONObject Me(HttpSession session,HttpServletRequest request){

		Cookie[] cookies = request.getCookies();


		return CommonUtil.constructResponse(1,"session cookie test",cookies);
	}


	/**
	 * user个人信息
	 * @return
	 */
	@RequestMapping(value="ShowMeUser")
	@ResponseBody
	public JSONObject ShowMeUser(HttpSession session){
		 Object json_User =  session.getAttribute("user");
		 System.out.print("json_User:"+json_User);
		 if(json_User==null)
			return CommonUtil.constructResponse(-1,"no login",null);

		 return CommonUtil.constructResponse(1,"user_Person_Notice",json_User);
	}

	/**
	 * 授权github用户登录
	 * @return
	 */
	@RequestMapping(value="RegisteredByGithub")
	@ResponseBody
	public JSONObject RegisteredByGithub(@RequestParam(value = "code" , required = false)String code, HttpSession session, HttpServletResponse response){


		try {
			String me =CommonUtil.sendPost
					("https://github.com/login/oauth/access_token?client_id="+ParamUtil.client_id+"&client_secret="+ParamUtil.client_secret+"&code="+code+"&redirect_uri=http://127.0.0.1:8090/cqput-bbs/User/RegisteredByGithub.do",null);

			String atoke = me.split("&")[0];

			String res = CommonUtil.sendGet("https://api.github.com/user?"+atoke+"");
			JSONObject user = (JSONObject) JSON.parse(res);

			int resRuslt = iShowUser.Res(user.getString("login"),user.getString("avatar_url"),user.getString("email"));
			if(resRuslt>0){
				session.setAttribute("user",user);

				System.out.println("session.setAttribute:"+session.getAttribute("user"));
				response.sendRedirect("http://127.0.0.1:8090/cqput-bbs/view");
				return  null;

			}
			else {
				return CommonUtil.constructResponse(0,"user详情",null);
			}


		}catch (Exception e){

			e.printStackTrace();
			return CommonUtil.constructResponse(0,"user详情",null);


		}

	}


	/**
	 * 5分钟有效验证码存放,值得思考的一个接口
	 * @param phone
	 * @param request
     * @return
     */
	@RequestMapping(value = "sendMessage")
	public JSONObject sendMessage(String phone, HttpServletRequest request){
		//String phone=request.getParameter("phone");
		//int times=userService.messageSendToday(phone);    //二次验证，单个手机号每日发送上限
		int times =1;
		if(times <= MAX_PER_DAY){
			String checkCode= "";
			final HttpSession httpSession=request.getSession();
			httpSession.setAttribute("checkCode",checkCode);
			//CheckCodeMessage checkCodeMessage=new CheckCodeMessage(phone,checkCode);
			try {
			//	HttpSender.batchSend(checkCodeMessage);
				//TimerTask实现5分钟后从session中删除checkCode
				final Timer timer=new Timer();
				timer.schedule(new TimerTask() {
					@Override
					public void run() {
						httpSession.removeAttribute("checkCode");
						timer.cancel();
					}
				},5*60*1000);//5min

			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		return  null;
	}
}
