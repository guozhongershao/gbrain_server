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
import com.google.gson.JsonObject;
import com.musearcher.gbrain.base.BaseUploadDealer;
import com.musearcher.gbrain.util.MultipartFormDataRequestHandler;


@RestController
@RequestMapping("/file")
public class FileManageController {
	@RequestMapping(value = "upload", method = {RequestMethod.POST })
	public JsonObject fileUpload(HttpServletRequest request, HttpServletResponse response) {
		JsonObject retJson = new JsonObject();
		try {
			// 已经设置了编码拦截器 默认编码 UTF-8
			// response.setCharacterEncoding("UTF-8");
			// response.setContentType("text/javascript;charset=UTF-8");
			// 获取上传处理类
			String dealerName = request.getHeader("dealer");
			// 请求头不包含处理类 
			if (dealerName == null || dealerName.length() <= 0) {
				retJson.addProperty("msg", "无法找到处理方法，请检查上传参数");
				return retJson; //!< * 不能使用 retJson.toString 返回结果会乱码；
			}
		
			// 请求参数及文件
			Map<String, Map<String, FileItem>> requestContent = MultipartFormDataRequestHandler.handleRequse(request);
			
			// 参数
			Map<String, FileItem> params = requestContent.get("params");
			// 文件
			Map<String, FileItem> files = requestContent.get("files");
			if (files.size() == 0){
				retJson.addProperty("msg", "没有上传内容");
				return retJson;
			}
			
			// 包含处理类
			BaseUploadDealer uploadDealer = null;
			// 实际处理类全名
			String actualDealerFullName = "com.musearcher.gbrain.service." + dealerName;
			uploadDealer = (BaseUploadDealer) Class.forName(actualDealerFullName).newInstance();
			uploadDealer.deal(params, files, null);
			retJson.addProperty("msg", "上传成功");
			return retJson;
		}catch (InstantiationException e) {
			e.printStackTrace();
			retJson.addProperty("msg", "系统错误");
			return retJson;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			retJson.addProperty("msg", "系统错误");
			return retJson;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			retJson.addProperty("msg", "无法找到处理方法，请检查上传参数");
			return retJson;
		} catch (IOException e) {
			e.printStackTrace();
			retJson.addProperty("msg", "无法找到处理方法，请检查上传参数");
			return retJson;
		} catch (FileUploadException e) {
			e.printStackTrace();
			retJson.addProperty("msg", "无法找到处理方法，请检查上传参数");
			return retJson;
		} catch (Exception e) {
			e.printStackTrace();
			retJson.addProperty("msg", "系统错误");
			return retJson;
		}
	}
}
