package com.example.demo.activity.controller;

import com.example.demo.activity.service.ActivityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Model;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenxue
 * @Description
 * @Date 2019/9/7 16:40
 */
@RestController
@RequestMapping("/activity")
@Slf4j
public class ActivityController {
    @Autowired
    private ProcessEngine processEngine;
    @Autowired
    private ActivityService activityService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private ObjectMapper objectMapper;
    /**
     * @Description: 启动流程
     * @param
     * @Author: chenxue
     * @Date: 2019/10/11  14:37
     */
    @RequestMapping("/start")
    public boolean startActivity(){
        return activityService.startActivity();
    }

    /**
     * @Description: 启动定义的流程
     * @param
     * @Author: chenxue
     * @Date: 2019/10/11  14:40
     */
    @PostMapping("/startDemo")
    public void startDemo(){
        String key = "helloProcess";
        Map<String,Object> map = new HashMap<>();
        map.put("jobNumber","A001");
        map.put("busData","bus data");
        //以流程定义的key启动流程
        ProcessInstance instance = runtimeService.startProcessInstanceByKey(key,map);
        //验证是否启动成功，通过查询正则运行时的流程实例来判断
        ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();
        //通过流程id来查询
        List<ProcessInstance> list = processInstanceQuery.processInstanceId(instance.getProcessInstanceId()).list();
        log.info("根据流程ID查询条数:{}", list.size());

    }

    /**
     * @Description: 创建新模型
     * @param request
     * @param response
     * @Author: chenxue
     * @Date: 2019/10/29  15:33
     */
    @GetMapping("/create")
    public void newModel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //初始化一个空模型
        Model model = repositoryService.newModel();
        //设置模型信息
        String name = "new-process";
        String description = "";
        int revision = 1;
        String key = "process";
        ObjectNode modelNode = objectMapper.createObjectNode();
        modelNode.put(ModelDataJsonConstants.MODEL_NAME, name);
        modelNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
        modelNode.put(ModelDataJsonConstants.MODEL_REVISION, revision);

        model.setName(name);
        model.setKey(key);
        model.setMetaInfo(modelNode.toString());

        repositoryService.saveModel(model);
        //完善ModelEditorSource
        String id = model.getId();
        ObjectNode editorNode = objectMapper.createObjectNode();
        editorNode.put("id", "canvas");
        editorNode.put("resourceId", "canvas");
        ObjectNode stencilSetNode = objectMapper.createObjectNode();
        stencilSetNode.put("namespace",
                "http://b3mn.org/stencilset/bpmn2.0#");
        editorNode.set("stencilset", stencilSetNode);
        repositoryService.addModelEditorSource(id,editorNode.toString().getBytes("utf-8"));
        response.sendRedirect("/modeler.html?modelId="+id);
    }

    /**
     * @Description: 获取所有模型
     * @param
     * @Author: chenxue
     * @Date: 2019/10/29  15:32
     */
    @RequestMapping("/modelList")
    public Object modelList(){
        RepositoryService repositoryService = processEngine.getRepositoryService();
        List<Model> list = repositoryService.createModelQuery().list();
        return list;
    }
}
