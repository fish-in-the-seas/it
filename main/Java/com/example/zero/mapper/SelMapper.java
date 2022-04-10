package com.example.zero.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.zero.entity.Notice;
import com.example.zero.entity.Sel;

@Mapper
public interface SelMapper {
    
	//从数据库中查询出所有的公告信息
	@Select("<script>"
			+"select*"
			+"from sel"
			+"<where>"
			+"<if test='stu_id!=null'>and stu_id like '%${stu_id}%'</if>"
			+ "</where>"
			+ "</script>")
	List<Sel> selectAll(@Param("stu_id")Integer stu_id);
   //将新的选课数据加入到数据库中
	@Insert("insert into sel (stu_id,cou_id) values(#{stu_id},#{cou_id})")
	int insert(Sel sel);
	//根据ID删除选课信息
	@Delete("delete from sel where id=#{id}")
	int delete(int id);
	//根据ID查询选课信息
	@Select("select * from sel where id=#{id}")
	Sel selectById(int id);
	//修改选课信息并打分
	@Update("update sel set stu_id=#{stu_id}, cou_id=#{cou_id}, score=#{score} where id=#{id}")
	int update(Sel sel);

	
	

}
