package org.fishlab.app.framework.unfinished;

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
	
	
	public static List<Class<?>> getSuperClassGenricTypes(final Class<?> clazz) {
		// 返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type。
		List<Class<?>> types=new LinkedList<Class<?>>();
		for (Class<?> superClass = clazz; superClass != Object.class;
				superClass = superClass.getSuperclass()) {
//			System.out.println(superClass.getName());
			System.out.println("extends form "+superClass.getName());
			for(Class<?> intf: superClass.getInterfaces()){
				System.out.println("implements "+intf.getName());
				Type genType = intf.getGenericSuperclass();
				if(genType!=null)
				System.out.println("genType "+genType.getClass().getName());
				if (genType instanceof ParameterizedType) {
					Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
					for(int i=0;i<params.length;i++){
						System.out.println(params[i].getClass());
						types.add(params[i].getClass());
					}
				}
			}
			
			{
				Type genType = superClass.getGenericSuperclass();//获取泛型
				if (genType instanceof ParameterizedType) {
					// 返回表示此类型实际类型参数的 Type 对象的数组。
					Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
					for(int i=0;i<params.length;i++){
						System.out.println(params[i].getClass());
						types.add(params[i].getClass());
					}
				}
			}
			
			Type[] genTypes =superClass.getGenericInterfaces();
			if(genTypes!=null){
				for(Type genType:genTypes){
					if (genType instanceof ParameterizedType) {
						// 返回表示此类型实际类型参数的 Type 对象的数组。
						Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
						for(int i=0;i<params.length;i++){
							System.out.println(params[i].getClass());
							types.add(params[i].getClass());
						}
					}
				}
			}
		}
		

		return types;
	}
	
	public static List<Class<?>> getSuperClassGenricTypes0(final Class<?> clazz) {
		// 返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type。
		Type genType = clazz.getGenericSuperclass();
		if (!(genType instanceof ParameterizedType)) {
			return null;
		}
		List<Class<?>> types=new LinkedList<Class<?>>();
		// 返回表示此类型实际类型参数的 Type 对象的数组。
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		for(int i=0;i<params.length;i++){
			types.add(params[i].getClass());
		}
		return types;
	}
}
