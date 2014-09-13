package org.fishlab.app.framework.test;


import org.fishlab.app.framework.ApplicationContext;
import org.fishlab.app.framework.ApplicationContextAware;
import org.fishlab.app.framework.FactoryBean;
import org.fishlab.app.framework.FrameworkException;
import org.fishlab.app.framework.internal.ApplicationContextAwareHandler;
import org.fishlab.app.framework.internal.FactoryBeanHandler;
import org.junit.Test;

public class TestApplicationContext {

//	@Test
	public void test(){
//		SampleModule2 s2=new SampleModule2();
//		System.out.println(ApplicationContextAware.class.isAssignableFrom(s2.getClass()));
		ApplicationContext ctx=new ApplicationContext();
		try {
			SampleModule2 sample=ctx.getInstance(SampleModule2.class);
			sample.print();
			sample.p2();
		} catch (FrameworkException e) {
			e.printStackTrace();
		}
		
	}
	

	@Test
	public void testFactoryBean(){
		ApplicationContext ctx=new ApplicationContext();
		try {
			ctx.regist(PersonFactory.class);
			Person ps=ctx.getInstance(Person.class);
			System.out.println(ps.getAge());
			
		} catch (FrameworkException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
