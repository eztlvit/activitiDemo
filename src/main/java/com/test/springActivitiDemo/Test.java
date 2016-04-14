package com.test.springActivitiDemo;

import java.util.List;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-mvc.xml")
public class Test {
	
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String index() {
		System.out.println("--------------");
		return "index";
	}
	
	@org.junit.Test
	public void processTests(){
	    // ���������ļ�
	    ProcessEngine processEngine = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml").buildProcessEngine();
	    RepositoryService repositoryService = processEngine.getRepositoryService();
	    RuntimeService runtimeService = processEngine.getRuntimeService();
	    repositoryService.createDeployment().addClasspathResource("diagrams/MyProcess.bpmn").deploy();
	    String processId = runtimeService.startProcessInstanceByKey("MyProcess").getId();
	 
	    TaskService taskService = processEngine.getTaskService();
	    //�õ����Ե�����
	    System.out.println("\n***************�������̿�ʼ***************");
	 
	    List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("������Դ��").list();
	    for (Task task : tasks) {
	        System.out.println("������Դ��������name:"+task.getName()+",id:"+task.getId());
	        taskService.claim(task.getId(), "����");
	    }
	 
	    System.out.println("����������������"+taskService.createTaskQuery().taskAssignee("����").count());
	    tasks = taskService.createTaskQuery().taskAssignee("����").list();
	    for (Task task : tasks) {
	        System.out.println("����������name:"+task.getName()+",id:"+task.getId());
	        taskService.complete(task.getId());
	    }
	 
	    System.out.println("����������������"+taskService.createTaskQuery().taskAssignee("����").count());
	    System.out.println("***************�������̽���***************");
	 
	    System.out.println("\n***************һ�����̿�ʼ***************");
	    tasks = taskService.createTaskQuery().taskCandidateGroup("������").list();
	    for (Task task : tasks) {
	        System.out.println("������������name:"+task.getName()+",id:"+task.getId());
	        taskService.claim(task.getId(), "����");
	    }
	 
	    System.out.println("���ĵ�����������"+taskService.createTaskQuery().taskAssignee("����").count());
	    for (Task task : tasks) {
	        System.out.println("���ĵ�����name:"+task.getName()+",id:"+task.getId());
	        taskService.complete(task.getId());
	    }
	 
	    System.out.println("���ĵ�����������"+taskService.createTaskQuery().taskAssignee("����").count());
	    System.out.println("***************һ�����̽���***************");
	 
	    System.out.println("\n***************�������̿�ʼ***************");
	    tasks = taskService.createTaskQuery().taskCandidateGroup("������").list();
	    for (Task task : tasks) {
	        System.out.println("������������name:"+task.getName()+",id:"+task.getId());
	        taskService.claim(task.getId(), "����");
	    }
	 
	    System.out.println("���ĵ�����������"+taskService.createTaskQuery().taskAssignee("����").count());
	    for (Task task : tasks) {
	        System.out.println("���ĵ�����name:"+task.getName()+",id:"+task.getId());
	        taskService.complete(task.getId());
	    }
	 
	    System.out.println("���ĵ�����������"+taskService.createTaskQuery().taskAssignee("����").count());
	    System.out.println("***************�������̽���***************");
	 
	    System.out.println("***************HR�����̿�ʼ***************");
	    tasks = taskService.createTaskQuery().taskCandidateGroup("������Դ��").list();
	    for (Task task : tasks) {
	        System.out.println("������������name:"+task.getName()+",id:"+task.getId());
	        taskService.claim(task.getId(), "����");
	    }
	 
	    System.out.println("���ĵ�����������"+taskService.createTaskQuery().taskAssignee("����").count());
	    for (Task task : tasks) {
	        System.out.println("���ĵ�����name:"+task.getName()+",id:"+task.getId());
	        taskService.complete(task.getId());
	    }
	 
	    System.out.println("���ĵ�����������"+taskService.createTaskQuery().taskAssignee("����").count());
	    System.out.println("***************HR�����̽���***************");
	 
	    System.out.println("\n***************¼�����̿�ʼ***************");
	    tasks = taskService.createTaskQuery().taskCandidateGroup("������Դ��").list();
	    for (Task task : tasks) {
	        System.out.println("������������name:"+task.getName()+",id:"+task.getId());
	        taskService.claim(task.getId(), "����");
	    }
	 
	    System.out.println("���ĵ�����������"+taskService.createTaskQuery().taskAssignee("����").count());
	    for (Task task : tasks) {
	        System.out.println("���ĵ�����name:"+task.getName()+",id:"+task.getId());
	        taskService.complete(task.getId());
	    }
	 
	    System.out.println("���ĵ�����������"+taskService.createTaskQuery().taskAssignee("����").count());
	    System.out.println("***************¼�����̽���***************");
	 
	    HistoryService historyService = processEngine.getHistoryService();
	    HistoricProcessInstance historicProcessInstance = historyService
	            .createHistoricProcessInstanceQuery()
	            .processInstanceId(processId).singleResult();
	    System.out.println("\n���̽���ʱ�䣺"+historicProcessInstance.getEndTime());
	}
	
	private  ApplicationContext applicationContext;  
	
	@Autowired
	public void setApplicationContext(ApplicationContext applicationContext)  {
	  this.applicationContext = applicationContext;
	}
	
	public void testActiviti() {
		RepositoryService repositoryService = (RepositoryService)this.applicationContext.getBean("repositoryService");
		String deploymentId = repositoryService
		.createDeployment()
		.addClasspathResource("hellowrod.bpmn")
		.deploy()
		.getId();
		UserBean userBean = (UserBean) applicationContext.getBean("userBean");
		userBean.hello();
	}
}
