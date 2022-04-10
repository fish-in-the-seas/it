package com.example.zero.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.zero.entity.Notice;
import com.example.zero.entity.Sel;
import com.example.zero.mapper.SelMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class SelServiceImpl implements SelService{

	@Autowired
	private SelMapper selMapper;
	
	@Override
	public PageInfo getByPage(int page, int limit,Integer stu_id) {
		//利用PageHelper完成分页查询，无代码侵入式分页
				//1.启动分页
				PageHelper.startPage(page,limit);
				//2.查询数据
				List sels = selMapper.selectAll(stu_id);
				//3.整合数据
				PageInfo pageInfo = new PageInfo<>(sels);
				return pageInfo;
	}
    //进行选课
	@Override
	public int add(Sel sel) {
		
		return selMapper.insert(sel);
	}
	//根据ID删除选课信息
	@Override
	public int delete(int id) {
		
		return selMapper.delete(id);
	}
	//根据ID查询选课信息
	@Override
	public Sel getById(int id) {
		
		return selMapper.selectById(id);
	}
	//对选课信息进行修改和课程打分
	@Override
	public int edit(Sel sel) {
		return selMapper.update(sel);
	}
	
	
	

}
