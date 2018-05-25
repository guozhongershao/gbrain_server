package com.musearcher.gbrain.common.utils;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


public class MultipartFormDataRequestHandler {
	
	public static Map<String, Object> handleRequse(HttpServletRequest request) {
		try {
			DiskFileItemFactory diskFactory = new DiskFileItemFactory();
			// threshold 极限、临界值，即硬盘缓存 1M
			diskFactory.setSizeThreshold(4 * 1024);
			
			// repository 贮藏室，即临时文件目录
			PropertiesUtil mPropertiesUtil = null;
			mPropertiesUtil = new PropertiesUtil();
			String tempPath = mPropertiesUtil.readValue("fileHandleConfig.tempPath");
			
			diskFactory.setRepository(new File(tempPath));
			ServletFileUpload upload = new ServletFileUpload(diskFactory);
			// 设置允许上传的最大文件大小 4M
			upload.setSizeMax(4 * 1024 * 1024);
			Map<String, Object> requestContent = new HashMap<>();
			List<FileItem> requestFileds = new ArrayList<>();
			List<FileItem> requestFileItems = new ArrayList<>();
			// 解析HTTP请求消息头
			List<FileItem> fileItems = upload.parseRequest(request);
			Iterator<FileItem> iter = fileItems.iterator();
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				if (item.isFormField()) {
					System.out.println("处理表单内容 ...");
					requestFileds.add(item);
				} else {
					System.out.println("处理上传的文件 ...");
					requestFileItems.add(item);
				}
			}
			requestContent.put("requestFileds", requestFileds);
			requestContent.put("requestFileItems", requestFileItems);
			return requestContent;
		} catch (FileUploadException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	// 处理上传的文件
	public static void processUploadFile(FileItem item) {
		try {
			// 此时的文件名包含了完整的路径，得注意加工一下
			String filename = item.getName();
			System.out.println("完整的文件名：" + filename);
			int index = filename.lastIndexOf("\\");
			filename = filename.substring(index + 1, filename.length());
			long fileSize = item.getSize();
			if ("".equals(filename) && fileSize == 0) {
				System.out.println("文件名为空 ...");
				return;
			}
			PropertiesUtil mPropertiesUtil = new PropertiesUtil();
			String filePath = mPropertiesUtil.readValue("fileHandleConfig.filePath");
			File uploadFile = new File(filePath + "/" + filename);
			item.write(uploadFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
