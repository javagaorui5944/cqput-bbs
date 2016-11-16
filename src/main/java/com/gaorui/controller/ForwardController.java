package com.gaorui.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class ForwardController {



	/**
	 * 返回所有user
	 * @return
     */
	 @RequestMapping(value="/index")
	 public String ShowUser(){

	    	return "common/index";
	    }

}
