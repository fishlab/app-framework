package org.fishlab.app.framework.test;

import org.fishlab.app.framework.ApplicationContext;
import org.fishlab.app.framework.ApplicationContextAware;
import org.fishlab.app.framework.Autowired;

public class SampleModule implements ApplicationContextAware {
	@Autowired
	ApplicationContext ctx;
	
	public void print() {
		System.out.println("context is " + ctx);
	}

//	@Override
	public void setApplicationContext(ApplicationContext context) {
		this.ctx = context;
		System.out.println("set " + ctx);
	}

}
