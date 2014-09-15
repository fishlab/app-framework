package org.fishlab.app.framework;

public interface FactoryBean<T> {
	T getObject() throws Exception;
}
