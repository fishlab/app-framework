app-framework
=
是一个很小的应用程序框架，实现了和springframework类似的自动注入。可用于小型应用程序如手机程序。
##用法

* 编写如下测试用例
```java
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
```
* 运行，可看到输出
```console
sample action excuted
```

##应用
* [codegenius](https://github.com/fishlab/codegenius) 核心容器

##目前支持的类型
* Autowired 注解注入
* ApplicationContextAware，注入 ApplicationContext
* FactoryBean<T>,单例工厂模式

##下一步工作
* 完善工厂模式(目前支持单例)
* 再增加几个注解类型以精确注入

