package com.itheima.controller;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    @GetMapping
    public Result list() {
        log.info("查询所有部门的信息");

        List<Dept> list = deptService.findAll();

        return Result.success(list);
    }


    @DeleteMapping
    public Result delete(Integer id) throws Exception {

        try {
            deptService.deleteById(id);

            return Result.success();

        } catch (Exception e) {
            // 🚨 捕获到了 "对不起，当前部门下有员工..." 这个异常
            e.printStackTrace(); // 在后台控制台打印报错日志（给程序员看）

            // 👇【关键】把异常里的文字取出来，塞给 Result 返回给前端
            return Result.error(e.getMessage());
        }
    }

    @PostMapping
    public Result add(@RequestBody Dept dept){

        deptService.insert(dept);

        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){

        Dept dept = deptService.infoById(id);

        return Result.success(dept);
    }

    @PutMapping
    public Result update(@RequestBody Dept dept){

        deptService.update(dept);

        return Result.success();

    }




}
