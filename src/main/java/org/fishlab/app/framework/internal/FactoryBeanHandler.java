package org.fishlab.app.framework.internal;

import org.fishlab.app.framework.ApplicationContext;
import org.fishlab.app.framework.FactoryBean;
import org.fishlab.app.framework.InterfaceHandler;

public class FactoryBeanHandler implements InterfaceHandler{

	@Override
	public void handle(ApplicationContext context,Class<?> clazz, Object inst)
			throws Exception {
		Object o=((FactoryBean<?>)inst).getObject();
		context.regist(o);
	}

}
