package com.musearcher.gbrain.common.utils;


import java.io.File;
import java.io.IOException;
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
	
	public static HashMap<String, Map<String, FileItem>> handleRequse(HttpServletRequest request) throws IOException, FileUploadException {
		DiskFileItemFactory diskFactory = new DiskFileItemFactory();
		// threshold 极限、临界值，即硬盘缓存 1M
		// 超过该临界值，字节流将以临时文件的形式保存在磁盘里而不是内存里
		diskFactory.setSizeThreshold(1 * 1024 * 1024); // 单位 Byte
		
		// repository 贮藏室，即临时文件目录
		// 默认为系统临时文件目录： java.io.tmpdir —— System.getProperty("java.io.tmpdir");
		PropertiesUtil mPropertiesUtil = null;
		mPropertiesUtil = new PropertiesUtil();
		String tempPath = mPropertiesUtil.readValue("fileHandleConfig.tempPath");
		diskFactory.setRepository(new File(tempPath));
		ServletFileUpload upload = new ServletFileUpload(diskFactory);
		// 设置上传文件总量的最大值，最大值=同时上传的多个文件的大小的最大值的和，目前设置为 20M
		upload.setSizeMax(20 * 1024 * 1024);
		// 设置单个文件最大值 10M
		upload.setFileSizeMax(10 * 1024 * 1024); 
		
		HashMap<String, Map<String, FileItem>> requestContent = new HashMap<>();
		HashMap<String, FileItem> params = new HashMap<>();
		HashMap<String, FileItem> files = new HashMap<>();
		
		// 解析HTTP请求消息头
		List<FileItem> fileItems = upload.parseRequest(request);
//		Map<String, List<FileItem>> testMap = upload.parseParameterMap(request);
		Iterator<FileItem> iter = fileItems.iterator();
		while (iter.hasNext()) {
			FileItem item = (FileItem) iter.next();
			if (item.isFormField()) { // a simple form field;
				params.put(item.getFieldName()+ "", item);
			} else { // an uploaded file.
				files.put(item.getName() + "", item);
			}
		}
		requestContent.put("params", params);
		requestContent.put("files", files);
		return requestContent;
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
