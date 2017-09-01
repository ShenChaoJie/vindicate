package org.frame.controller;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

import lombok.Data;

import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.web.servlet.ShiroHttpSession;
import org.frame.persistence.model.TUser;
import org.frame.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public @Data class LoginController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="login.htm",method=RequestMethod.GET)
	public String loginPre(Model model){
		model.addAttribute("user", new TUser());
		return "/login";
	}
	
	@RequestMapping(value="login.htm",method=RequestMethod.POST)
	public String login(@RequestParam("lname") String lname,@RequestParam("lps") String lps){
		try {
			
			SecurityUtils.getSubject().login(new UsernamePasswordToken(lname, lps));
			
			System.out.println("SESSION ID = " + SecurityUtils.getSubject().getSession().getId()); 
			System.out.println("用户名：" + SecurityUtils.getSubject().getPrincipal()); 
			System.out.println("HOST：" + SecurityUtils.getSubject().getSession().getHost()); 
			System.out.println("TIMEOUT ：" + SecurityUtils.getSubject().getSession().getTimeout()); 
			System.out.println("START：" + SecurityUtils.getSubject().getSession().getStartTimestamp()); 
			System.out.println("LAST：" + SecurityUtils.getSubject().getSession().getLastAccessTime());
			
		} catch (IncorrectCredentialsException e) {
			return "redirect:/error/403.htm";
		}catch (UnknownAccountException e2) {
			return "redirect:/error/403.htm";
		}
		return "redirect:/province/toQueryProvince.htm";
	}
	
	@RequestMapping(value="logout.htm",method=RequestMethod.GET)
	public String logout(){
		SecurityUtils.getSubject().logout();
		return "redirect:login.htm";
	}
	
	@RequestMapping(value="error/403.htm", method=RequestMethod.GET)
	public String unauthorizedRole(){
		return "/error/403";
	}
	
	@RequestMapping(value="error/error.htm", method=RequestMethod.GET)
	public String error(){
		return "/error/error.jsp";
	}
	
	
	public static String getMD5Str(String str) {
		MessageDigest messageDigest = null;

		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			System.out.println("NoSuchAlgorithmException caught!");
			System.exit(-1);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		byte[] byteArray = messageDigest.digest();
		StringBuffer md5StrBuff = new StringBuffer();
		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				md5StrBuff.append("0").append(
						Integer.toHexString(0xFF & byteArray[i]));
			else
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
		}
		return md5StrBuff.toString();
	}

}
