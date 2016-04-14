package com.test.springActivitiDemo;

import org.activiti.engine.RuntimeService;
import org.springframework.transaction.annotation.Transactional;

public class UserBean {
	private RuntimeService runtimeService;

	@Transactional
	public void hello() {
		// ������ǿ���������ģ������Щ�����ԵĴ���
		// ��ִ�е�Activiti RuntimeService��startProcessInstanceByKey����ʱ��
		// ����ϲ�����һ������
		runtimeService.startProcessInstanceByKey("helloProcess");
	}

	public void setRuntimeService(RuntimeService runtimeService) {
		this.runtimeService = runtimeService;
	}
}
