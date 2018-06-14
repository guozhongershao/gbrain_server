package com.musearcher.gbrain.base;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;

public abstract class BaseUploadDealer {
	
	/**
	 * 实际处理上传请求
	 * @param request	上传请求
	 * @param params	处理配置参数
	 * @return	处理结果 true : 成功 ; false : 失败
	 * @throws IOException
	 * @throws FileUploadException
	 * @author wangjx
	 * @throws Exception 
	 * @since 2018年6月12日 上午11:14:12
	 */
	public abstract void deal(Map<String, FileItem> params, Map<String, FileItem> files ,Map<String, String> options) throws Exception;
	
	/**
	 * 将文件保存到磁盘,以MAP<String, Object> 作为参数，保持其可拓展性；
	 * @param fullPath 将要保存的全路径
	 * @param FileItem 	文件对象
	 * @return true ： 保存成功； false : 保存失败
	 * @throws Exception
	 * @author wangjx
	 * @throws Exception 
	 * @since date 2018年6月9日 下午9:37:30
	 */
	public boolean saveFileToDisk(String fullPath, FileItem fileItem) throws Exception {
//		String currentTime = String.valueOf(new Date().getTime());
		File file = new File(fullPath);
		// 如果选中路径不存在，创建路径
		file.getParentFile().mkdirs(); 
		// 写入文件
		fileItem.write(file); 
		return true;
	}
	
}
