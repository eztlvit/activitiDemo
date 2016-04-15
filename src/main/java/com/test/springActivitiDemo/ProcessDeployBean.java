package com.test.springActivitiDemo;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;

public class ProcessDeployBean {
	
	public void deployProcessModel() {
		// º”‘ÿ≈‰÷√Œƒº˛
	    this.processEngine = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml").buildProcessEngine();
	    RepositoryService repositoryService = processEngine.getRepositoryService();
	    repositoryService.createDeployment().addClasspathResource("/diagrams/*.bpmn").deploy();
	    this.runtimeService = processEngine.getRuntimeService();
	}
	
	private static ProcessDeployBean pdb;
	
	public synchronized static ProcessDeployBean getInstance() {
		if (null == pdb) {
			pdb = new ProcessDeployBean();
		}
		return pdb;
	}
	
	private RuntimeService runtimeService;

	public RuntimeService getRuntimeService() {
		return runtimeService;
	}

	public void setRuntimeService(RuntimeService runtimeService) {
		this.runtimeService = runtimeService;
	}
	
	private ProcessEngine processEngine;

	public ProcessEngine getProcessEngine() {
		return processEngine;
	}

	public void setProcessEngine(ProcessEngine processEngine) {
		this.processEngine = processEngine;
	}
	
}
