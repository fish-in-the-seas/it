package com.example.zero.service;

import com.example.zero.entity.Admin;
import com.github.pagehelper.PageInfo;

public interface AdminService {
	
    //登录验证
	Admin login(Admin admin) throws Exception;
	//修改用户信息
	int edit(Admin admin);
	//查询所有用户信息，使用分页查询
	PageInfo getByPage(int page,int limit, Integer code);
	//用户注册
	int add(Admin admin);
	//用户删除
	int delete(int id);
	//通过Id查询用户信息
	Admin getById(int id);
	//用户信息修改
	int change(Admin admin);
	
}
