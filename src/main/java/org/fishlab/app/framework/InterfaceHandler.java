package org.fishlab.app.framework;

public interface InterfaceHandler{
//	boolean isMatch(Class<?> intf);
	void handle(ApplicationContext context,Class<?> clazz,Object inst) throws Exception;
}
