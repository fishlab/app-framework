package org.fishlab.app.framework.internal;

import org.fishlab.app.framework.ApplicationContext;
import org.fishlab.app.framework.ApplicationContextAware;
import org.fishlab.app.framework.InterfaceHandler;

public class ApplicationContextAwareHandler implements InterfaceHandler{

	@Override
	public void handle(ApplicationContext context,Class<?> clazz, Object inst)
			throws Exception {
		((ApplicationContextAware)inst).setApplicationContext(context);
	}

}
