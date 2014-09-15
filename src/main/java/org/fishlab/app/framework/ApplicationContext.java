package org.fishlab.app.framework;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.fishlab.app.framework.internal.*;

public class ApplicationContext {
//	private static String TAG=ApplicationContext.class.getSimpleName();
//	private static Log log=LogFactory.getLog(ApplicationContext.class);
	private Map<Class<?>,Object> instanceStore;
	private Map<Class<?>,InterfaceHandler> interfaceHandlerStore;
	private Map<Class<?>,FieldAnnotationHandler> filedAnnotationHandlerStore;
	public ApplicationContext(){
		this.instanceStore=new HashMap<Class<?>,Object>();
		this.interfaceHandlerStore=new HashMap<Class<?>,InterfaceHandler> ();
		this.filedAnnotationHandlerStore=new HashMap<Class<?>,FieldAnnotationHandler> ();
		this.setupHandler();
	}
	
	public void registInterfaceHandler(Class<?> intf,InterfaceHandler handler){
		this.interfaceHandlerStore.put(intf, handler);
	}
	
	public void registFiledAnnotationHandler(Class<?> intf, FieldAnnotationHandler handler){
		this.filedAnnotationHandlerStore.put(intf,handler);
	}
	
	private void setupHandler() {
		this.interfaceHandlerStore.put(ApplicationContextAware.class, new ApplicationContextAwareHandler());
		
		this.filedAnnotationHandlerStore.put(Autowired.class, new AutowiredAnnotationHandler());
		registInterfaceHandler(FactoryBean.class, new FactoryBeanHandler());
	}

	public <T> T getInstance(Class<? extends T> clazz) throws FrameworkException{
		T inst=(T) this.findInstance(clazz);
		if (inst ==null&&!clazz.isInterface()){
			inst=this.createInstance(clazz);
		}
		return inst;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T findInstance(Class<? extends T> clazz) throws FrameworkException{
		T inst=(T) this.instanceStore.get(clazz);
		if (inst == null){
//			if(clazz.isInterface()){
				//查找该类的子类或者实现了该接口的类
				for(Class<?> cl:this.instanceStore.keySet()){
					if(clazz.isAssignableFrom(cl)){
						return (T) this.instanceStore.get(cl);
					}
				}
//			}
		}
		return inst;
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> findInstances(Class<? extends T> clazz) throws FrameworkException{
		List<T> insts=new ArrayList<T>();
		for (Class<?> cl : this.instanceStore.keySet()) {
			if (clazz.isAssignableFrom(cl)) {
				insts.add((T) this.instanceStore.get(cl));
			}
		}
		return insts;
	}
	
	private void interfaceAware(Class<?> clazz,Object inst) throws FrameworkException {
		List<Class<?>> types=ClassUtils.getInterfacesIncludeSuperClass(clazz);
		for(int i=0;i<types.size();i++){
			Class<?> type=types.get(i);
			InterfaceHandler handler=this.interfaceHandlerStore.get(type);
			if(handler!=null){
				try {
					handler.handle(this,clazz,inst);
				} catch (Exception e) {
					e.printStackTrace();
					throw new FrameworkException("Unable to handle interface "+type.getName());
				}
			}
		}
	};
	
	private FieldAnnotationHandler findFieldAnnotationHandler(Annotation anno){
		for(Class<?> cl:filedAnnotationHandlerStore.keySet()){
			if(cl.isInstance(anno)){
				return this.filedAnnotationHandlerStore.get(cl);
			}
		}
		return null;
	}
	
	private void annotationAware(Class<?> clazz,Object inst) throws FrameworkException {
		List<Field> fields=ClassUtils.getAllFieldsIncludeSuperClass(clazz);
		for(Field field:fields){
//			System.out.println("field "+field.getName());
			Annotation[] annos=field.getDeclaredAnnotations();
			
			for(int i=0;i<annos.length;i++){
				Annotation anno=annos[i];
				
//				System.out.println("anno "+anno.getClass().getName());
				
				FieldAnnotationHandler handler =this.findFieldAnnotationHandler(anno);
				if(handler !=null){
					try {
						handler.handle(this, anno, inst, field);
					} catch (Exception e) {
						e.printStackTrace();
						throw new FrameworkException("Unable to handler annotaition "+anno.getClass().getName());
					}
				}
			}
		}
	
	}
	
	public <T> T createInstance(Class<? extends T> clazz) throws FrameworkException   {
		T inst=null;
		try {
			inst=clazz.newInstance();
			this.instanceStore.put(clazz, inst);
			this.interfaceAware(clazz,inst);
			this.annotationAware(clazz,inst);
		} catch (InstantiationException e) {
			e.printStackTrace();
			throw new FrameworkException(e.getMessage());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new FrameworkException(e.getMessage());
		} 
		return inst;
	}
	/**自动装配*/
	public <T> void awareInstance(T inst) throws FrameworkException   {
		Class<?> clazz = inst.getClass();
//		this.instanceStore.put(clazz, inst);
		this.interfaceAware(clazz, inst);
		this.annotationAware(clazz, inst);
	}
	
	public void regist(Object inst){
		this.instanceStore.put(inst.getClass(), inst);
	}
	public void regist(Class<?> clazz){
		try {
			this.createInstance(clazz);
		} catch (FrameworkException e) {
			e.printStackTrace();
		}
	}
}
