package com.musearcher.gbrain.controller;

import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.musearcher.gbrain.common.utils.MultipartFormDataRequestHandler;


@RestController
@RequestMapping("/user")
public class FileManageController {

	// 配置能接收多种请求类型： method = {RequestMethod.GET, RequestMethod.POST}
	@RequestMapping(value = "fileUpload", method = { RequestMethod.GET, RequestMethod.POST })
	public String fileUpload(HttpServletRequest request, HttpServletResponse response) {
		try {
			Map<String, Map<String, FileItem>> requestContent = MultipartFormDataRequestHandler.handleRequse(request);
			if (requestContent != null) {
				
			}
		} catch (FileUploadException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
