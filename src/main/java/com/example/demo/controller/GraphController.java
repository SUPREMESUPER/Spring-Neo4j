package com.example.demo.controller;

import com.example.demo.pojo.CustomerNode;
import com.example.demo.service.GraphService;
import org.apache.http.HttpResponse;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
import java.util.List;

@RestController
@RequestMapping("/account")
public class GraphController {
    private static Logger logger= LoggerFactory.getLogger(GraphController.class);

    @Autowired
    private GraphService graphService;

    /**
     *  添加节点，并添加关系，正常情况下应该由文件导入，此处仅是测试
     * @param name 初始节点名称
     * @param age
     * @param nameTo    添加有关系节点的名称
     * @param remark    关系说明
     */
    @PostMapping(path = "/create")
    public Result addNode(
            @RequestParam(name = "name",defaultValue = "node1") String name,
            @RequestParam(name = "age",defaultValue = "0")Integer age,
            @RequestParam(name = "nameTo",defaultValue = "node2")String nameTo,
            @RequestParam(name = "remark",defaultValue = "朋友") String remark,
            HttpResponse response
    ){
        logger.info("添加节点");
        graphService.addNode(name,age,nameTo,remark);
        return Result.ok();
    }

    /**
     * 删除节点
     * @param id 节点ID，非必须，如果不提供，那么默认全删，否则删除相对应的id
     */
    @GetMapping(path = "/delete")
    public Result deleteNode(
            @RequestParam(name = "id",required = false)Long id,
            @RequestParam(name = "name",required = false) String name
    ){
        if (id !=null){
            graphService.deleteNodeById(id);
        }else if(name !=null & id==null){
            graphService.deleteNodeByName(name);
        }else  {
            graphService.delete();
        }
        return Result <T>;
    }
    /**
     * 根据节点ID，更新节点信息
     * @param id
     * @param name
     * @param age
     */
    @GetMapping(path = "/update")
    public Result updateNode(
            @RequestParam(name = "id",required = false) Long id,
            @RequestParam(name = "name",required = false) String name,
            @RequestParam(name = "age",required = false) Integer age
    ){
        logger.info("更新数据");
        graphService.updateNode(id,name,age);
        return Result.ok();
    }
    /**
     * 根据名字查找相关的所有节点
     * @param name
     */
    @GetMapping(path = "/find")
    public Result findNode(
            @RequestParam(name = "name",required = true) String name
    ){
        logger.info("查找所有的节点"+name);
        List<CustomerNode> customerNodes=graphService.queryNodes(name);
        logger.info(customerNodes.size()+" 返回的数据长度");
        for (CustomerNode customerNode: customerNodes) {
            logger.info("节点名 "+customerNode.getName()+"与节点 "+customerNode.getAge());
        }
        return Result.ok(customerNodes);
    }
}
