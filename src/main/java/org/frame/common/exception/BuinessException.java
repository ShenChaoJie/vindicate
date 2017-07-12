package org.frame.common.exception;

import org.frame.common.utils.StringUtil;

import lombok.Getter;

/**
 * 
 * @author chaojie.shen
 *
 */
public class BuinessException extends ProjectException{

	private static final long serialVersionUID = -7210725872260928699L;
	
	@Getter
	private String code;
	
	public BuinessException(String code){
		super();
		this.code = code;
	}
	
	public BuinessException(String code,String message){
		super(message);
		this.code = code;
	}
	
	public BuinessException(String code,Exception e){
		super(e);
		this.code = code;
	}
	
	public BuinessException(String code,String message,Exception e){
		super(message,e);
		this.code = code;
	}
	
	public BuinessException(String code,String message,String... arg){
		super(StringUtil.replaceString(message, arg));
		this.code = code;
	}

	
}
