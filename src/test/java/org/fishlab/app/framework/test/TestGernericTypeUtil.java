package org.fishlab.app.framework.test;

import java.io.Serializable;
import java.lang.reflect.Type;

import org.fishlab.app.framework.FactoryBean;
import org.fishlab.app.framework.unfinished.GernericTypeUtils;
import org.junit.Test;
class G2 extends Person implements Serializable{
	String g2;
}
class G3 extends G2 implements Serializable{
	int g3;
}
interface ss<RR>{}
class G4 implements FactoryBean<String>,ss<String> {
	@Override
	public String getObject() throws Exception {
		return "none";
	}
}
class G5 extends G4{};
public class TestGernericTypeUtil {
	
	@Test
	public void test() throws Exception{
//		System.out.println(FactoryBean.class.isAssignableFrom(G4.class));
		System.out.println(
//		GernericTypeUtils.getSuperClassGenricTypes(G3.class)
				GernericTypeUtils.getActualTypesInGenericInterface(G5.class, ss.class)
		);
	}
//	@Test
	public void test2(){
		System.out.println(
				org.fishlab.app.framework.ClassUtils.getInterfacesIncludeSuperClass(G3.class)
				);
//		System.out.println(
//		ClassUtils.getAllInterfaces(G3.class)
//		);
	}

}
