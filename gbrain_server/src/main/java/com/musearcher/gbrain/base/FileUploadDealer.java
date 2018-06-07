package com.musearcher.gbrain.base;

import org.apache.commons.fileupload.FileItem;

public abstract class FileUploadDealer {
	
	/**
	 * 将文件保存到磁盘
	 * @param path
	 * @param file
	 * @return
	 * @author wangjx
	 * 2018年6月6日 下午5:27:01
	 */
	public boolean saveFileToDisk(String path, FileItem fileItem) {
//		String currentTime = String.valueOf(new Date().getTime());
		
		
//		// 输出照片到具体磁盘地址
//		byte[] buffer = new byte[10 * 1024];
//		OutputStream out = null;
//		File file = null;
//		
//		file = new File(filepath + relativepath);
//		file.getParentFile().mkdirs();
//		file.createNewFile();
//		out = new FileOutputStream(file);
//		int bytesRead = 0;
//		while ((bytesRead = imageInfo.getIs().read(buffer, 0, 10 * 1024)) != -1) {
//			out.write(buffer, 0, bytesRead);
//		}
//		out.close();
		return true;
	}
	
}
