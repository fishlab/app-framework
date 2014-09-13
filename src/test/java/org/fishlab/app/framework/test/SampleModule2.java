package org.fishlab.app.framework.test;

import org.fishlab.app.framework.Autowired;

public class SampleModule2 extends SampleModule{
	
	@Autowired
	private SampleModule2 s2;
	
	public void p2(){
		System.out.println(s2==this);
	}

}
