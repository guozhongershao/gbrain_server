package com.musearcher.gbrain.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
	private UserService userService;
	
    @RequestMapping(value = "hello", method = RequestMethod.GET)
    @ResponseBody
    public String helloWorld(@RequestParam ("user") String userName) {
    	User user =  userService.getUserByUserName("aaaaaaaa");
    	return user.getUserName();
   }
}
