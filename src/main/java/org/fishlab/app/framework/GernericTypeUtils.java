package org.fishlab.app.framework;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

public class GernericTypeUtils {
	/**返回某一类实现泛型接口的实际类型参数列表
	 * */
	public static List<Class<?>> getActualTypesInGenericInterface(Class<?> clazz,Class<?> intf) {
		List<Class<?>> types=new LinkedList<Class<?>>();
		for (Class<?> superClass = clazz; superClass != Object.class; 
				superClass = superClass.getSuperclass()) {
			Type[] genTypes = superClass.getGenericInterfaces();
			if (genTypes != null) {
				for (Type genType : genTypes) {
					//TODO:换一个判断当前泛型属于指定接口的泛型的方法
					if (genType instanceof ParameterizedType
							&&genType.toString().contains(intf.getSimpleName())) {
						Type[] params = ((ParameterizedType) genType) .getActualTypeArguments();
						for (int i = 0; i < params.length;i++) {
							for(int j=0;j<params.length;j++){
								Class<?> t=(Class<?>)params[j];
								if (!types.contains(t))
									types.add( t);
							}
						}
					}
				}
			}
		}
		return types;
	}
}