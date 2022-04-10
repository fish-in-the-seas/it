package com.example.zero.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.zero.entity.Notice;
import com.example.zero.entity.Question;

@Mapper
public interface QuestionMapper {
	//从数据库中查询出所有的申报问题信息
		@Select("<script>"
				+"select*"
				+"from question"
				+"<where>"
				+"<if test='stu_id!=null'>and stu_id like '%${stu_id}%'</if>"
				+ "</where>"
				+ "</script>")
		List<Question> selectAll(@Param("stu_id")Integer stu_id);
    //将申报的问题添加到数据库中
		@Insert("insert into question (stu_id,issue,plan,start) values(#{stu_id},#{issue},#{plan},#{start})")
		int insert(Question question);
    //根据ID可以将已经申报的问题进行删除
		@Delete("delete from question where id=#{id}")
		int delete(int id);
	//根据ID查询问题信息
		@Select("select *from question where id =#{id}")
		Question selectById(int id);
	//处理问题信息
		@Update("update question set stu_id=#{stu_id}, issue=#{issue}, plan=#{plan},start=#{start},reply=#{reply} where id=#{id}")
		int update(Question question);
	
}
