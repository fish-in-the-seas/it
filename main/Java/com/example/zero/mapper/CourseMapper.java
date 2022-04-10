package com.example.zero.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.zero.entity.Course;

@Mapper
public interface CourseMapper {

	//从数据库中查询出所有的课程信息
    @Select("<script>"
			+"select*"
			+"from course"
			+"<where>"
			+"<if test='cname!=null'>and cname like '%${cname}%'</if>"
			+ "</where>"
			+ "</script>")
	List<Course> selectAll(@Param("cname")String cname);
    
    //根据ID进行删除课程信息
  	@Delete("delete from course where id=#{id}")
  	int delete(int id);
  	
  	//添加课程
  	@Insert("insert into course (id,cname,ctime,clocation) values(#{id},#{cname},#{ctime},#{clocation})")
  	int insert(Course course);
  	
  	//根据CID查询课程信息
  	@Select("select * from course where id =#{id}")
  	Course selectById(int id);

  	//修改课程信息
  	@Update("update course set cname=#{cname}, ctime=#{ctime}, clocation=#{clocation} where id=#{id}")
  	int update(Course course);

	

}
