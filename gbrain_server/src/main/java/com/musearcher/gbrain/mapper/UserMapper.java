package com.musearcher.gbrain.mapper;

import java.util.List;
import java.util.Map;
import com.musearcher.gbrain.bean.UserInfoBean;
import com.musearcher.gbrain.entity.User;
import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User>{

	public User getUserBycredential(Map<String, Object> params) throws Exception;
    public User getUserByUserName(String userName) throws Exception;
    public void refreshUserAccessTokenByUserName(Map<String, Object> params) throws Exception;
    public void insertUser(Map<String, Object> params) throws Exception;
    public UserInfoBean getUserInfoByUserName(String userName) throws Exception;
    public List<UserInfoBean> getFocusedUserList(Map<String,Object> params) throws Exception;
}
