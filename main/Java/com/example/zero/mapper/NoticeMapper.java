package com.example.zero.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.zero.entity.Notice;
@Mapper
public interface NoticeMapper {
	
	
	//从数据库中查询出所有的公告信息
	@Select("<script>"
			+"select*"
			+"from notice"
			+"<where>"
			+"<if test='title!=null'>and title like '%${title}%'</if>"
			+ "</where>"
			+ "</script>")
	List<Notice> selectAll(@Param("title")String title);
	
	//根据ID进行删除公告信息
	@Delete("delete from notice where id=#{id}")
	int delete(int id);
	
	//添加公告
	@Insert("insert into notice (title,main,time) values(#{title},#{main},#{time})")
	int insert(Notice notice);
	
	//根据ID查询公告信息
	@Select("select *from notice where id =#{id}")
	Notice selectById(int id);

	//修改公告信息
	@Update("update notice set title=#{title}, main=#{main}, time=#{time} where id=#{id}")
	int update(Notice notice);

}
