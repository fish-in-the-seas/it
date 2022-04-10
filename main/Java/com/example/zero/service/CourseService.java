package com.example.zero.service;

import com.example.zero.entity.Course;
import com.github.pagehelper.PageInfo;

public interface CourseService {
     
	//分页查询
	PageInfo getByPage(int page, int limit,String cname);
   //根据ID删除课程
	int delete(int id);
   //添加课程
	int add(Course course);
	//根据ID查询课程信息
	Course getById(int id);
	//修改课程信息
	int edit(Course course);

}
