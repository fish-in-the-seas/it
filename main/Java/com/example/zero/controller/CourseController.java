package com.example.zero.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.zero.entity.APIResult;
import com.example.zero.entity.Course;
import com.example.zero.entity.Notice;
import com.example.zero.service.CourseService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/course")
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	
	//管理端课程查询界面
    @GetMapping("/list")
    public String list() {
 	   return "course/list";
    }
    
    //查询功能实现
    @PostMapping("/list")
    @ResponseBody  //返回JSON
	public APIResult list(int page,int limit,String cname) {
   	//分页查询
   	//service层，Mapper层，entity类
		PageInfo pageInfo = courseService.getByPage(page, limit,cname);
		APIResult result = new APIResult();
		result.setCode(0);
		result.setData(pageInfo);
		return result;
	}
    
    /**
 	 * 删除操作
 	 * 由于这个操作是通过ajax向服务器发送的，需要返回json格式
 	 */
     @PostMapping("/delete")
 	 @ResponseBody
 	 public APIResult delete(int id) {
    	//1.参数
 		//2.调用service层
    	 int res = courseService.delete(id);
    	//3.返回
 		if(res>0)
 		{
 			return APIResult.success(null);
 		}else {
 			return APIResult.error("删除失败");
 		}
     }
   //添加课程页面
     @GetMapping("/add")
     public String add() {
    	 return "course/add";
     }
     //接受ajax的请求
     @PostMapping("/add")
     @ResponseBody
     public APIResult add(Course course) {
    	//1.获取参数
 		//2.调用Service层
    	 int res = courseService.add(course);
    	//3.返回数据
 		if(res>0) {
 		return APIResult.success(null);
 		}else {
 			return APIResult.error("添加失败");
 		}
     }
     
     //打开编辑页面
  	@GetMapping("/edit")
  	public String edit(int id,Model model) {
  		//先把当前要修改的项目的信息查询出来
  		Course course = courseService.getById(id);
  		model.addAttribute("course",course);
  		return "course/edit";
  	}
    //课程修改功能
  	@PostMapping("/edit")
 	@ResponseBody
	public APIResult edit(Course course) {
		//1.参数
		//2.调用service层
		int res = courseService.edit(course);
		//3.返回数据
		if(res>0) {
			return APIResult.success(null);
		}else 
		{
			return APIResult.error("修改失败");
		}
	}
  //学生端课程查询界面
    @GetMapping("/listx")
    public String listx() {
 	   return "course/listx";
    }
    
    //查询功能实现
    @PostMapping("/listx")
    @ResponseBody  //返回JSON
	public APIResult listx(int page,int limit,String cname) {
   	//分页查询
   	//service层，Mapper层，entity类
		PageInfo pageInfo = courseService.getByPage(page, limit,cname);
		APIResult result = new APIResult();
		result.setCode(0);
		result.setData(pageInfo);
		return result;
	}
    

}
