package com.musearcher.gbrain.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.musearcher.gbrain.entity.User;
import com.musearcher.gbrain.service.UserService;

@RestController
@RequestMapping("/test")
public class SessionManageController {
	// 测试地址：http://localhost:8080/gbrain/test/hello?user=World

	// 配置日志
	private final static Logger logger = Logger.getLogger(SessionManageController.class);
	
	@Autowired
	private UserService userService;

	// RESTful 风格
	@RequestMapping(value = "hello", method = RequestMethod.GET)
    @ResponseBody
    public String helloWorld(@RequestParam ("user") String userName, @RequestHeader("Accept-Encoding") String encoding) {
    	User user =  userService.getUserByUserName("aaaaaaaa");
    	return user.getUserName();
   }

	// 普通
	@RequestMapping("/login")
	public String login(HttpServletRequest request, HttpServletResponse response){
		logger.info("Content-Type :" + request.getHeader("Accept-Encoding"));
    	User user =  userService.getUserByUserName("aaaaaaaa");
    	return user.getUserName();
	}
}
