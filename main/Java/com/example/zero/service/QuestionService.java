package com.example.zero.service;


import com.example.zero.entity.Question;
import com.github.pagehelper.PageInfo;

public interface QuestionService {
	//按照分页获取问题
    PageInfo getByPage(int page,int limit, Integer stu_id);
    //申报问题
	int add(Question question);
	//删除已经申报的问题
	int delete(int id);
	//根据ID获取学生信息
	Question getById(int id);
	//处理学生问题
	int edit(Question question);
}
