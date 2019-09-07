package com.example.demo.activity.service.Impl;

import com.example.demo.activity.service.ActivityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenxue
 * @Description 工作流ServiceImpl
 * @Date 2019/9/7 16:38
 */
@Service
public class ActivityServiceImpl implements ActivityService {
    @Resource
    private RepositoryService repositoryService;
    @Resource
    private RuntimeService runtimeService;
    @Resource
    private TaskService taskService;
    @Override
    public boolean startActivity() {
        System.out.println("开启工作流程...");
        System.out.println("调用流程存储服务，查询部署数量：" + repositoryService.createDeploymentQuery().count());
        Map<String,Object> map = new HashMap<>();
        map.put("apply","zhangsan");
        map.put("approve","lisi");
        ExecutionEntity pil = (ExecutionEntity) runtimeService.startProcessInstanceById("leave",map);
        List<Task> taskList = taskService.createTaskQuery().taskAssignee("zhangsan").list();
        System.out.println(taskList.size());
        //当前任务代办人
        String currentTaskUser = "zhangsan";
        List<Task> tasks = taskService //当前任务办理人
                .createTaskQuery() //创建任务查询对象
                .taskAssignee(currentTaskUser).list();
        if(tasks != null){
            for (Task task : tasks){
                System.out.println("任务ID:" + task.getId());
                System.out.println("任务办理人:" + task.getAssignee());
                System.out.println("任务名称:" + task.getName());
                System.out.println("任务创建时间:" + task.getCreateTime());
                System.out.println("流程实例ID:" + task.getProcessDefinitionId());
                System.out.println("############################################");
            }
        }
        System.out.println("工作流结束...");
        return false;
    }
}
