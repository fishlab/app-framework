package org.fishlab.app.framework.test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;

import org.fishlab.app.framework.Autowired;
import org.fishlab.app.framework.ClassUtils;
import org.junit.Test;

public class TestAnos {
	@Test
	public void test(){
		List<Field> fields=ClassUtils.getAllFieldsIncludeSuperClass(SampleModule2.class);
		for(Field field:fields){
			System.out.println("field "+field.getName());
			Annotation[] annos=field.getDeclaredAnnotations();
			
			for(int i=0;i<annos.length;i++){
				Annotation anno=annos[i];
				
				System.out.println("anno "+(Autowired.class.isInstance(anno) ) );
			}
		}
	}
	
}
