package com.example.zero.service;


import com.example.zero.entity.Sel;
import com.github.pagehelper.PageInfo;

public interface SelService {
    
	//分页查询
	PageInfo getByPage(int page, int limit, Integer stu_id);
    //进行选课
	int add(Sel sel);
	//根据ID删除选课信息
	int delete(int id);
	//根据ID查询选课信息
	Sel getById(int id);
	//对选课信息进行编辑，并对课程进行打分
	int edit(Sel sel);

}
