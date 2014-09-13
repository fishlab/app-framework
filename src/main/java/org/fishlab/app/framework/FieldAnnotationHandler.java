package org.fishlab.app.framework;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public interface FieldAnnotationHandler {
	void handle(ApplicationContext context,Annotation annotation,Object inst,Field field) throws Exception;
}
