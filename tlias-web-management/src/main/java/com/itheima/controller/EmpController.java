package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryPrama;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {

    private final EmpService empService;

    public EmpController(EmpService empService) {
        this.empService = empService;
    }

    @GetMapping
    public Result page(EmpQueryPrama empQueryPrama) {

        PageResult<Emp> pageResult = empService.page(empQueryPrama);

        return Result.success(pageResult);
    }

    @PostMapping
    public Result save(@RequestBody Emp emp) {

        log.info("新增员工: {}", emp);
        empService.save(emp);

        return Result.success();
    }

    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids) {

        log.info("删除员工: {}", ids);

        empService.delete(ids);

        return Result.success();
    }

    @GetMapping("/{id}")
    public Result selectById(@PathVariable Integer id){

        log.info("根据id查询员工信息: {}", id);

        Emp emp = empService.selectById(id);

        return Result.success(emp);
    }



    @PutMapping
    public Result update(@RequestBody Emp emp) {

        log.info("修改员工信息: {}", emp);

        empService.update(emp);


        return Result.success();

    }
}
