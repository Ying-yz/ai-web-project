package com.itheima.controller;

import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.pojo.Student;
import com.itheima.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public Result page(String name,
                       Integer degree,
                       Integer clazzId,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize){

        PageResult<Student> PageResult = studentService.page(name,degree,clazzId,page,pageSize);


        return Result.success(PageResult);
    }

    @PostMapping
    public Result save(@RequestBody Student student){

        studentService.save(student);

        return Result.success();
    }

    @GetMapping("/{id}")
    public Result select(@PathVariable Integer id){

        Student student = studentService.selectById(id);

        return Result.success(student);
    }

    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){

        studentService.deleteById(ids);

        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody Student student){

        studentService.update(student);

        return Result.success();
    }

    @PutMapping("/violation/{id}/{score}")
    public Result violationHandle(@PathVariable Integer id, @PathVariable Integer score){

        studentService.violation(id,score);

        return Result.success();
    }
}
