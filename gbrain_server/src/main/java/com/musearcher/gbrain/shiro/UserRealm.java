package com.musearcher.gbrain.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.musearcher.gbrain.entity.User;
import com.musearcher.gbrain.service.UserService;

public class UserRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;
	// public static final String SESSION_USER_KEY = "admin";

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用,负责在应用程序中决定用户的访问控制的方法
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		/*
		 * System.out.println("test"); User user =
		 * (User)SecurityUtils.getSubject().getSession().getAttribute(UserRealm.
		 * SESSION_USER_KEY); SimpleAuthorizationInfo SAZAInfo = new
		 * SimpleAuthorizationInfo();
		 * SAZAInfo.addRole(user.getUserAuthority().trim());
		 */
		return null;
	}

	/**
	 * 认证方法
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
		// 获取用户名
		String userName = (String) token.getPrincipal();
		// 获取用户信息
		User user = null;
		user = userService.getUserByUserName(userName);
		// 当前 Realm 的 name
		String realmName = getName();
		// credentials: 密码
		// 盐值：取用户信息中唯一的字段来生成盐值，避免由于两个用户原始密码相同，加密后的密码也相同
		ByteSource credentialsSalt = ByteSource.Util.bytes(userName);
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getUserName(), user.getUserPassword(),
				credentialsSalt, realmName);
		return info;
	}

}
