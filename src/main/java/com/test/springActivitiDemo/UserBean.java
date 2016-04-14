package com.test.springActivitiDemo;

import org.activiti.engine.RuntimeService;
import org.springframework.transaction.annotation.Transactional;

public class UserBean {
	private RuntimeService runtimeService;

	@Transactional
	public void hello() {
		// 这里，我们可以在领域模型内做些事务性的处理
		// 当执行到Activiti RuntimeService的startProcessInstanceByKey方法时，
		// 它会合并到这一事务里
		runtimeService.startProcessInstanceByKey("helloProcess");
	}

	public void setRuntimeService(RuntimeService runtimeService) {
		this.runtimeService = runtimeService;
	}
}
