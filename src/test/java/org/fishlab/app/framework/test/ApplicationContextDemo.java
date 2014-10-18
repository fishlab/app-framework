package org.fishlab.app.framework.test;
import org.fishlab.app.framework.ApplicationContext;
import org.fishlab.app.framework.Autowired;
import org.fishlab.app.framework.FrameworkException;
import org.junit.Test;
public class ApplicationContextDemo {
	public static interface Action{ 
		void execute();
	}
	public static class SampleAction implements Action {
		@Override
		public void execute() {
			System.out.println("sample action excuted");
		}
	}
	public static class SampleActionInvoker {
		@Autowired private Action action;//这里会自动注入
		void excuteActon(){
			this.action.execute();
		}
	}
	@Test
	public void sample() throws FrameworkException{
		ApplicationContext context=new ApplicationContext();
		context.regist(SampleAction.class); //先注册一个实现Action的接口的类
		SampleActionInvoker samp=context.createInstance(SampleActionInvoker.class);//使用ApplicationContext创建实例
		samp.excuteActon();
	}
}
