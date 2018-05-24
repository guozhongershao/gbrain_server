package com.musearcher.gbrain.service;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.musearcher.gbrain.bean.UserInfoBean;
import com.musearcher.gbrain.entity.User;
import com.musearcher.gbrain.mapper.UserMapper;


@Service("UserService")
public class UserService extends BaseService<User>{

	@Autowired
	private UserMapper userMapper;

	/**
	 * 
	 * @description 通过用户凭证查询用户完整信息	
	 * @methodName getUserBycredential
	 * @author Wang
	 * @date 2017年3月28日 
	 * @param params
	 * @return User
	 */
	public User getUserBycredential(Map<String, Object> params){
		try {
			return userMapper.getUserBycredential(params);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 
	 * @description 通过用户名查找用户完整信息
	 * @methodName getUserByUserName
	 * @author Wang
	 * @date 2017年3月28日 
	 * @param userName
	 * @return User
	 */
	public User getUserByUserName(String userName){
		try{
			return userMapper.getUserByUserName(userName);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 
	 * @description 更新AccessToken
	 * @methodName refreshUserAccessToken
	 * @author Wang
	 * @date 2017年3月28日 
	 * @param params void
	 */
	public void refreshUserAccessToken(Map<String, Object> params){
		try {
			userMapper.refreshUserAccessTokenByUserName(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @description 暂时没用上
	 * @methodName register
	 * @author Wang
	 * @date 2017年3月30日 
	 * @param params void
	 */
	public void register(Map<String, Object> params) {
		try {
			userMapper.insertUser(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @description 通过userName获取用户profile信息
	 * @methodName getUserInfoByUserName
	 * @author Wang
	 * @date 2017年3月30日 
	 * @param userName
	 * @return UserInfoBean
	 */
	public UserInfoBean getUserInfoByUserName(String userName){
		try {
			return userMapper.getUserInfoByUserName(userName);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
