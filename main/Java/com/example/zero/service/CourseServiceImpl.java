package com.example.zero.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.zero.entity.Course;
import com.example.zero.mapper.CourseMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class CourseServiceImpl implements CourseService{
   
	//自动注入，当前类的对象被创建时，将从Spring容器中，寻找与当前属性类型相匹配的对象
	@Autowired
	private CourseMapper courseMapper;
	
	//分页查询
	@Override
	public PageInfo getByPage(int page, int limit,String cname) {
		//利用PageHelper完成分页查询，无代码侵入式分页
		        //1.启动分页
				PageHelper.startPage(page,limit);
				//2.查询数据
				List courses = courseMapper.selectAll(cname);
				//3.整合数据
				PageInfo pageInfo = new PageInfo<>(courses);
				return pageInfo;
	}
    //根据ID删除课程信息
	@Override
	public int delete(int id) {
		
		return courseMapper.delete(id);
	}
	//增加新的课程
	@Override
	public int add(Course course) {
		
		return courseMapper.insert(course);
	}
	//根据ID查询课程信息
	@Override
	public Course getById(int id) {
		
		return courseMapper.selectById(id);
	}
	//修改课程信息
	@Override
	public int edit(Course course) {
		
		return courseMapper.update(course);
	}
	
	

}
