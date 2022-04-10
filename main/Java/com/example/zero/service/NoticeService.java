package com.example.zero.service;



import com.example.zero.entity.Notice;
import com.github.pagehelper.PageInfo;


public interface NoticeService{
	 //按照分页获取公告
	PageInfo getByPage(int page,int limit, String title);
    //根据ID删除公告
	int delete(int id);
	//增加新的公告
	int add(Notice notice);
	//根据ID查询公告信息
	Notice getById(int id);
	//修改公告信息
	int edit(Notice notice);
	
}
