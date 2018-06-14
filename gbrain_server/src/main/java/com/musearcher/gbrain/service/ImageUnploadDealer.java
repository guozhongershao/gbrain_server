package com.musearcher.gbrain.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;
import com.musearcher.gbrain.base.BaseUploadDealer;
import com.musearcher.gbrain.util.PropertiesUtil;

public class ImageUnploadDealer extends BaseUploadDealer{
	
	// 配置日志
	private final static Logger logger = Logger.getLogger(ImageUnploadDealer.class);
	

	@Override
	public void deal(Map<String, FileItem> params, Map<String, FileItem> files, Map<String, String> options)
			throws Exception{
		List<FileItem> fileItems = new ArrayList<>(files.values());
		PropertiesUtil propertiesUtil = PropertiesUtil.getPropertiesUtil();
		// 文件夹
		String folder = propertiesUtil.readValue("fileHandleConfig.filePath");
		
		for (int i = 0; i < fileItems.size(); i++) {
			FileItem fileItem = fileItems.get(i);
			String fileName = fileItem.getName();
			logger.debug("fileName :" + fileName);
			int index = fileName.lastIndexOf("\\");
			fileName = fileName.substring(index + 1, fileName.length());
			if ("".equals(fileName) && fileItem.getSize() == 0) {
				System.out.println("文件名为空 ...");
				throw new Exception("没有文件上传");
			}
			StringBuffer fullPath = new StringBuffer();
			fullPath.append(folder);
			fullPath.append(File.separator);
			fullPath.append(fileName);
			saveFileToDisk(fullPath.toString(), fileItem);
		}
	}
}
