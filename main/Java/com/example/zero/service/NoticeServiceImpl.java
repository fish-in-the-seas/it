package com.example.zero.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.zero.entity.Notice;
import com.example.zero.mapper.NoticeMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


@Service
public class NoticeServiceImpl implements NoticeService{
	
	@Autowired //自动注入，当前类的对象被创建时，将从Spring容器中，寻找与当前属性类型相匹配的对象
	//也可以用@Resource
	private NoticeMapper noticeMapper;

	@Override
	public PageInfo getByPage(int page, int limit,String title) {
		//利用PageHelper完成分页查询，无代码侵入式分页
		//1.启动分页
		PageHelper.startPage(page,limit);
		//2.查询数据
		List notices = noticeMapper.selectAll(title);
		//3.整合数据
		PageInfo pageInfo = new PageInfo<>(notices);
		return pageInfo;
	}
	
	    //根据ID删除公告
		@Override
		public int delete(int id) {
			return noticeMapper.delete(id);
		}
		
		//增加新的公告
		@Override
		public int add(Notice notice) {
			
			return noticeMapper.insert(notice);
		}
		//根据ID查询公告信息
		@Override
		public Notice getById(int id) {
			
			return noticeMapper.selectById(id);
		}
       //修改公告信息
		@Override
		public int edit(Notice notice) {
			
			return noticeMapper.update(notice);
		}
}
