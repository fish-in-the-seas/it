package com.example.zero.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.zero.entity.Notice;
import com.example.zero.entity.Question;
import com.example.zero.mapper.QuestionMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class QuestionServiceImpl implements QuestionService{
	
	@Autowired //自动注入，当前类的对象被创建时，将从Spring容器中，寻找与当前属性类型相匹配的对象
	//也可以用@Resource
	private QuestionMapper questionMapper;
	
	@Override
	public PageInfo getByPage(int page, int limit,Integer stu_id) {
		//利用PageHelper完成分页查询，无代码侵入式分页
				//1.启动分页
				PageHelper.startPage(page,limit);
				//2.查询数据
				List questions = questionMapper.selectAll(stu_id);
				//3.整合数据
				PageInfo pageInfo = new PageInfo<>(questions);
				return pageInfo;
	}
    //问题申报
	@Override
	public int add(Question question) {
		return questionMapper.insert(question);
	}
	//根据ID删除已经申报的问题
	@Override
	public int delete(int id) {
		return questionMapper.delete(id);
	}
	//根据ID获取问题信息
	@Override
	public Question getById(int id) {
		
		return questionMapper.selectById(id);
	}
	//对申报的问题进行回复
	@Override
	public int edit(Question question) {
		
		return questionMapper.update(question);
	}
	

}
