package org.frame.common.exception;

public class ProjectException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5028644188624362237L;
	
	
	public ProjectException(){		
	}
		
	public ProjectException(Exception e){
		super(e);
	}
	
	public ProjectException(String message){
		super(message);
	}
	
	public ProjectException(String message,Exception e){
		super(message,e);
	}

}
