package com.example.zero.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.zero.entity.Admin;

@Mapper
public interface AdminMapper {
    //查询
	@Select("select * from admin where code=#{code}")
	Admin selectByCode(String code);
  
	//修改用户信息
	@Update("update admin set password=#{password} where id=#{id}")
	int edit(Admin admin);
	//查询所有用户信息
	@Select("<script>"
			+"select*"
			+"from admin"
			+"<where>"
			+"<if test='code!=null'>and code like '%${code}%'</if>"
			+ "</where>"
			+ "</script>")
	List<Admin> selectAll(@Param("code")Integer code);
    //将用户注册所填的信息，插入到数据库中
	@Insert("insert into admin (code,password,name) values(#{code},#{password},#{name}) ")
	int insert(Admin admin);
	//根据ID删除用户信息
    @Delete("delete from admin where id=#{id}")
	int delete(int id);
    //根据ID查询用户信息
    @Select("select *from admin where id =#{id}")
	Admin selectById(int id);
    //将修改后的数据更新到数据库中
    @Update("update admin set code=#{code}, password=#{password}, name=#{name}, role=#{role} where id=#{id}")
	int update(Admin admin);
	
	
}
