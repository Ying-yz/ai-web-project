package com.itheima.service;

import com.itheima.pojo.JobOption;
import com.itheima.pojo.StudentOption;

import java.util.List;
import java.util.Map;

public interface ReportService {
    JobOption getEmpJobData();

    List<Map<String, Object>> getEmpGenderData();

    List<Map<String, Object>> getStudentDegreeData();

    StudentOption getStudentCountData();
}
