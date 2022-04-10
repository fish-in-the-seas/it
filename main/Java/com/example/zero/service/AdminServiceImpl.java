package com.example.zero.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.zero.entity.Admin;
import com.example.zero.mapper.AdminMapper;
import com.example.zero.util.MD5Util;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service  //将当前类创建一个对象，放到Spring容器中
public class AdminServiceImpl implements AdminService{
	
	@Autowired//自动注入，当前类的对象被创建时，将从Spring容器中，寻找与当前属性类型相匹配的对象
	//也可以用@Resource
	private AdminMapper adminMapper;

	@Override
	public Admin login(Admin admin) throws Exception{
		//验证账号和密码的格式
		if (admin.getCode().length()<3||admin.getCode().length()>16) {
			//3-16位
			//结束这个方法,通过抛出异常
			throw new Exception("账号格式不正确");
		}
		if(admin.getPassword().length()<3||admin.getPassword().length()>16)
		{
			//密码格式不对
			throw new Exception("密码格式不正确");
		}
		//2.验证用户名
		Admin selectAdmin = adminMapper.selectByCode(admin.getCode());
		if(selectAdmin == null)
		{
			throw new Exception("账号不存在");
		}
		//3.MD5加密
		admin.setPassword(MD5Util.getMD5(admin.getPassword()));
		//4.验证密码
		if(! admin.getPassword().equals(selectAdmin.getPassword())) {
			throw new Exception("密码错误");
		}
		//登录应该成功
		
		return selectAdmin;
	}

    //修改用户密码
	@Override
	public int edit(Admin admin) {
		//MD5加密
		admin.setPassword(MD5Util.getMD5(admin.getPassword()));
		return adminMapper.edit(admin);
	}
    //实现分页查询
	@Override
	public PageInfo getByPage(int page, int limit,Integer code) {
		//利用PageHelper完成分页查询，无代码侵入式分页
	    //1.启动分页
		PageHelper.startPage(page,limit);
		//2.查询数据
	    List admins = adminMapper.selectAll(code);
	    //3.整合数据
	    PageInfo pageInfo = new PageInfo<>(admins);
	    return pageInfo;
	}
    //用户注册功能
	@Override
	public int add(Admin admin) {
		Admin selectAdmin = adminMapper.selectByCode(admin.getCode());
		//1.判断账号是否已经存在
		if(selectAdmin != null)
		{
			return -1;
		}
		//2.对密码进行加密
		admin.setPassword(MD5Util.getMD5(admin.getPassword()));
		//3.加入到数据库中
		return adminMapper.insert(admin);
	}
    //通过ID删除用户
	@Override
	public int delete(int id) {
		
		return adminMapper.delete(id);
	}
    //通过ID获取用户信息
	@Override
	public Admin getById(int id) {
		
		return adminMapper.selectById(id);
	}
    //修改用户信息
	@Override
	public int change(Admin admin) {
		
		return adminMapper.update(admin);
	}
	
}
