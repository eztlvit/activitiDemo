package com.test.springActivitiDemo;

import org.activiti.engine.delegate.DelegateExecution;

public class JavaService1 implements org.activiti.engine.delegate.JavaDelegate {

	@Override
	public void execute(DelegateExecution arg0) throws Exception {

		arg0.setVariable("result", "1");

	}

}
