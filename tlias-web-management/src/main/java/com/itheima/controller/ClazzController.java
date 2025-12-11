package com.itheima.controller;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    @GetMapping
    public Result page(String name,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") String begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") String end,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize) {

        PageResult pageResult = clazzService.page(name, begin, end, page, pageSize);
        return Result.success(pageResult);
    }


    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {

        clazzService.deleteById(id);

        return Result.success();
    }

    @PostMapping
    public Result save(@RequestBody Clazz clazz) {

        log.info("新增班级: {}", clazz);

        clazzService.save(clazz);

        return Result.success();
    }

    @GetMapping("/{id}")
    public Result selectById(@PathVariable Integer id) {

        log.info("根据id查询班级信息: {}", id);

        Clazz clazz = clazzService.selectById(id);

        return Result.success(clazz);
    }

    @PutMapping
    public Result update(@RequestBody Clazz clazz){
        clazzService.update(clazz);
        return Result.success();
    }

    @GetMapping("/list")
    public Result selectAll(){

        List<Clazz> clazzList = clazzService.selectAll();

        return Result.success(clazzList);
    }
}
