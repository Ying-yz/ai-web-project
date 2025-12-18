package com.itheima.aop;

import com.alibaba.fastjson.JSONObject;
import com.itheima.mapper.OperateLogMapper;
import com.itheima.pojo.OperateLog;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class LogAspect {
    // 用于获取当前登录人信息（通常从JWT解析）
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(com.itheima.anno.Log)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        // 1. 获取当前操作人 ID
        // 从 request 域中取出我们在过滤器里存入的 "empId"
        Integer empId = (Integer) request.getAttribute("empId");

        // 2. 执行原始业务方法
        long begin = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();

        // 3. 封装日志对象并保存
        OperateLog operateLog = new OperateLog();
        operateLog.setOperateEmpId(empId); // 记录真实的操作人 ID

        operateLog.setOperateTime(LocalDateTime.now());
        operateLog.setClassName(joinPoint.getTarget().getClass().getName());
        operateLog.setMethodName(joinPoint.getSignature().getName());
        operateLog.setMethodParams(Arrays.toString(joinPoint.getArgs()));

        // 使用 Fastjson 序列化返回值
        operateLog.setReturnValue(JSONObject.toJSONString(result));
        operateLog.setCostTime(end - begin);

        // 插入数据库
        operateLogMapper.insert(operateLog);

        return result;
    }
}