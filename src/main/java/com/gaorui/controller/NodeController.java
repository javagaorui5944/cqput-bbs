package com.gaorui.controller;

import com.alibaba.fastjson.JSONObject;
import com.gaorui.service.IShowNode;
import com.gaorui.service.IShowUser;
import com.gaorui.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value="Node")
public class NodeController {
	@Autowired
	private IShowNode iShowNode;


	/**
	 * 返回所有node节点
	 * @return
     */
	 @RequestMapping(value="ShowNode")
	 @ResponseBody
	 public JSONObject ShowNode(){

	    	return CommonUtil.constructResponse(1,"node详情",iShowNode.ShowNode());
	    }
	 

}
