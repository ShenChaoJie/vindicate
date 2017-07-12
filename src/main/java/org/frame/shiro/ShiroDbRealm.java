package org.frame.shiro;

import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.frame.persistence.TPermissionMapper;
import org.frame.persistence.TRoleMapper;
import org.frame.persistence.TUserMapper;
import org.frame.persistence.model.TUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShiroDbRealm extends AuthorizingRealm {

	@Autowired
	private TUserMapper userMapper;
	@Autowired
	private TRoleMapper roleMapper;
	@Autowired
	private TPermissionMapper permissionMapper;
	
	/**
	 * 权限认证
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection paramPrincipalCollection) {
		 //获取登录时输入的用户名 
		ShiroUser shiroUser = (ShiroUser)paramPrincipalCollection.getPrimaryPrincipal();
		
		//到数据库查是否有此对象
		TUser user = userMapper.getUserByUsername(shiroUser.getUsername());
		if(null!=user){
			//权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）  
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(); 
			
			//用户的角色集合  
			Set<String> roleSet = roleMapper.findRoleByUserId(user.getId());
            info.setRoles(roleSet);
            
            //用户权限集合
            Set<String> permissionSet= permissionMapper.findPermissionByUserId(user.getId());
            info.addStringPermissions(permissionSet);
            
            return info;
            
		}
		return null;
	}

	/**
	 * 登录认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken paramAuthenticationToken) throws AuthenticationException {
		//UsernamePasswordToken对象用来存放提交的登录信息
		UsernamePasswordToken token = (UsernamePasswordToken) paramAuthenticationToken;
		//查出是否有此用户  
		TUser user = userMapper.getUserByUsername(token.getUsername());
		
		if(null!=user){
			//return new SimpleAuthenticationInfo(user.getLname(), user.getLps(), getName());
			return new SimpleAuthenticationInfo(new ShiroUser(user.getId().toString(),user.getLname(),getName()),user.getLpswd(),getName());
		}
		return null;
	}

}
