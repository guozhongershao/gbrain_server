package com.musearcher.gbrain.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.musearcher.gbrain.common.utils.MultipartFormDataRequestHandler;
import com.musearcher.gbrain.entity.User;
import com.musearcher.gbrain.service.UserService;

@RestController
@RequestMapping("/user")
public class SessionManageController {
	// 测试地址：http://localhost:8080/gbrain/test/hello?user=World

	// 配置日志
	private final static Logger logger = Logger.getLogger(SessionManageController.class);

	@Autowired
	private UserService userService;

	// RESTful 风格
	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ResponseBody
	public String helloWorld(@RequestParam("user") String userName, @RequestHeader("Accept-Encoding") String encoding) {
		User user = userService.getUserByUserName("aaaaaaaa");
		return user.getUserName();
	}

	// 普通
	// 配置能接收多种请求类型： method = {RequestMethod.GET, RequestMethod.POST}
	@RequestMapping(value = "upload", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(HttpServletRequest request, HttpServletResponse response) {
		
		// 请求参数及文件
		Map<String, Map<String, FileItem>> requestContent;
		try {
			requestContent = MultipartFormDataRequestHandler.handleRequse(request);
		} catch (IOException | FileUploadException e) {
			e.printStackTrace();
			return null;
		}
		if (requestContent != null) {
			String dealer = request.getHeader("dealer");
			if (dealer != null && dealer.length() > 0) {
				
			}
			
			@SuppressWarnings("unchecked")
			List<FileItem> requestFileds = (List<FileItem>) requestContent.get("params");
			if (requestFileds != null && requestFileds.size() > 0) {
				for (int i = 0; i < requestFileds.size(); i++) {
					FileItem item = requestFileds.get(i);
					String name = item.getFieldName();
					String value = item.getString();
					System.out.println("name : " + name + ";  value :" + value);
				}
			}
			@SuppressWarnings("unchecked")
			List<FileItem> requestFileItems = (List<FileItem>) requestContent.get("files");
			if (requestFileItems != null && requestFileItems.size() > 0) {
				for (int i = 0; i < requestFileItems.size(); i++) {
					FileItem item = requestFileItems.get(i);
					MultipartFormDataRequestHandler.processUploadFile(item);
				}
			}
		}
		logger.info("Content-Type :" + request.getHeader("Accept-Encoding"));
		User user = userService.getUserByUserName("aaaaaaaa");
		return user.getUserName();
	}
}
