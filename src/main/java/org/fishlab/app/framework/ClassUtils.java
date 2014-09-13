package org.fishlab.app.framework;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
/**@author wu
 * */
public class ClassUtils {

	/**获取一个类以及超类实现的所有接口
	 * */
	public static List<Class<?>> getInterfacesIncludeSuperClass(final Class<?> clazz) {
		List<Class<?>> types=new ArrayList<Class<?>>();
		for (Class<?> superClass = clazz; superClass != Object.class;
				superClass = superClass.getSuperclass()) {
			for(Class<?> intf: superClass.getInterfaces()){
				if (!types.contains(intf)){
					types.add(intf);
				}
			}
		}
		return types;
	}
	/**获取一个类以及超类实现的所有字段
	 * */
	public static List<Field> getAllFieldsIncludeSuperClass(final Class<?> clazz) {
		List<Field> lf=new ArrayList<Field>();
		for (Class<?> superClass = clazz; 
				superClass != Object.class; 
				superClass = superClass.getSuperclass()) {
				Field[] f=superClass.getDeclaredFields();
				for (int j=0;j<f.length;j++){
					lf.add(f[j]);
				}
		}
		return lf;
	}
	
	
}
