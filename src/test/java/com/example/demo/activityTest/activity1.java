package com.example.demo.activityTest;

import org.activiti.engine.*;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.checkerframework.checker.units.qual.A;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @description: activity 测试类
 * @author: chenxue
 * @create: 2019-10-28 11:01
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class activity1 {

    public static ProcessEngine processEngine  = null;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;

    @Autowired
    private IdentityService identityService;
    //@Before
    public void init(){
        processEngine = ProcessEngines.getDefaultProcessEngine();
        Deployment deployment = processEngine.getRepositoryService()
                .createDeployment()
                .addClasspathResource("processes/myProcess.xml")
                .addClasspathResource("processes/myProcess.png")
                .deploy();
        System.out.println(deployment.getId());
        System.out.println(deployment.getDeploymentTime());
    }

    /**
     * @Description: 同步用户 。一般在新增、修改系统用户时。需要同步activity中的用户和用户组
     * @param
     * @Author: chenxue
     * @Date: 2019/11/1  9:16
     */
    @Test
    public void test3(){
        /*User user = identityService.newUser("2");
        user.setPassword("2");
        identityService.saveUser(user);
        Group group = identityService.newGroup("经理");
        group.setName("经理");
        identityService.saveGroup(group);*/
        identityService.createMembership("1","主管");
        identityService.createMembership("2","经理");


    }


    /**
     * @Description: 部署流程，读取资源 三种读取资源方式 1.classpath读取 2.字符串读取 3.流读取 4.zip批量读取
     * @param
     * @Author: chenxue
     * @Date: 2019/11/1  9:13
     */
    @Test
    public void test(){
        Deployment deployment = processEngine.getRepositoryService()
                .createDeployment()
                .addClasspathResource("processes/myProcess.bpmn")
                .addClasspathResource("processes/myProcess.png")
                .deploy();
        System.out.println(deployment.getId());
        System.out.println(deployment.getDeploymentTime());
    }

    /**
     * @Description: 通过 runtimeService 启动任务
     * @param
     * @Author: chenxue
     * @Date: 2019/11/1  9:15
     */
    @Test
    public void test1(){
        List<Model> list = repositoryService.createModelQuery().list();
        //ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId("50005").singleResult();
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionName("process_1").singleResult();
        /*ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinition.getId());*/
        //发布流程 可以通过流程key 、业务主键、参数一起启动流程任务
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("process_1");
        //ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey(processKey);
        System.out.println(processInstance.getId());
        System.out.println(processInstance.getProcessInstanceId());
        System.out.println(processInstance.getProcessDefinitionId());
    }

    /**
     * @Description: taskService对任务进行领取、完成、删除操作
     * @param
     * @Author: chenxue
     * @Date: 2019/11/1  9:15
     */
    @Test
    public void findPersonTaskList(){
        //根据登录人查询当前任务
        String assignee = "张三";
        List<Task> list = taskService.createTaskQuery().taskAssignee(assignee).list();
        if(list != null && list.size() > 0){
            list.stream().forEach(o -> {
                System.out.println("任务ID:"+o.getId());
                System.out.println("任务的办理人:"+o.getAssignee());
                System.out.println("任务名称:"+o.getName());
                System.out.println("任务的创建时间:"+o.getCreateTime());
                System.out.println("流程实例ID:"+o.getProcessInstanceId());
                System.out.println("#####################################");
            });
        }

        // 根据流程key 、业务主键查询task
        Task task = taskService.createTaskQuery().processDefinitionKey("process_1").taskAssignee("李四").singleResult();
        //领取任务
        if(task != null){
            if(task.getAssignee() == null){
                //无人领取该任务
                taskService.claim(task.getId(),"李四");
                taskService.complete(task.getId());
            }
        }

    }

}
