package org.fishlab.app.framework;

public class FrameworkException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public FrameworkException(){};
	public FrameworkException(String message){
		super(message);
	}
}
