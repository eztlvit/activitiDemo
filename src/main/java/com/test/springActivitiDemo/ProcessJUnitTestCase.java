package com.test.springActivitiDemo;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-mvc.xml")
public class ProcessJUnitTestCase {
	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private TaskService taskService;
	
	@Autowired
	@Rule
	public ActivitiRule activitiSpringRule;

	@Test
	public void simpleProcessTest() {

		// 部署流程图
//		repositoryService.createDeployment().addClasspathResource(
//				"diagrams/VacationRequest.bpmn");
		// 构建参数
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("employeeName", "Kermit");
		variables.put("numberOfDays", new Integer(4));
		variables.put("vacationMotivation", "I'm really tired!");
		// 根据ID启动流程
		runtimeService.startProcessInstanceByKey("vacationRequest", variables);
		// 获取task
		List<Task> tasks = taskService.createTaskQuery().list();
//		for (Task t : tasks) {
//			if ("技术二面".equalsIgnoreCase(t.getName())) {
//				taskService.complete(t.getId());
//			}
//		}

		tasks = taskService.createTaskQuery().taskCandidateGroup("management")
				.list();
		Task task = tasks.get(0);
		Map<String, Object> taskVariables = new HashMap<String, Object>();
		taskVariables.put("vacationApproved", "false");
		taskVariables.put("managerMotivation", "We have a tight deadline!");
		taskService.complete(task.getId(), taskVariables);
		// assertEquals(runtimeService.createProcessInstanceQuery().count(),
		// runtimeService.createProcessInstanceQuery().count());
	}

	@Rule
	public ActivitiRule activitiRule = new ActivitiRule();

	@Deployment
	public void ruleUsageExample() {
		RuntimeService runtimeService = activitiRule.getRuntimeService();
		runtimeService.startProcessInstanceByKey("ruleUsage");

		TaskService taskService = activitiRule.getTaskService();
		Task task = taskService.createTaskQuery().singleResult();
		assertEquals("My Task", task.getName());

		taskService.complete(task.getId());
		assertEquals(0, runtimeService.createProcessInstanceQuery().count());
	}
}