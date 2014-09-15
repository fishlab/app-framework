package org.fishlab.app.framework.test;

import org.fishlab.app.framework.FactoryBean;

public class PersonFactory  implements FactoryBean<Person>{

	@Override
	public Person getObject() throws Exception {
		Person ps=new Person();
		ps.setAge(23);
		return ps;
	}
	
}
