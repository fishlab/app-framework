package org.fishlab.app.framework.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.fishlab.app.framework.ApplicationContext;
import org.fishlab.app.framework.FieldAnnotationHandler;
import org.fishlab.app.framework.FrameworkException;

public class AutowiredAnnotationHandler implements FieldAnnotationHandler{

	@Override
	public void handle(ApplicationContext context, Annotation annotation,
			Object inst,Field field) throws Exception {
		Class<?> type=field.getType();
		Object dep=context.getInstance(type);
		if (dep!=null){
			field.setAccessible(true);
			field.set(inst, dep);
		}else{
			throw new FrameworkException("Unable to get dependency for "+field.getName()+" in "+inst.getClass());
		}
	}

}
