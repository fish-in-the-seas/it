package com.example.zero.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.zero.entity.APIResult;
import com.example.zero.entity.Admin;
import com.example.zero.entity.Sel;
import com.example.zero.service.SelService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/sel")
public class SelController {
	
	@Autowired
	private SelService selService;
	
	//查询界面
    @GetMapping("/list")
    public String list() {
 	   return "sel/list";
    }
    //功能实现
    @PostMapping("/list")
    @ResponseBody  //返回JSON
	public APIResult list(int page,int limit,Integer stu_id) {
   	//分页查询
   	//service层，Mapper层，entity类
		PageInfo pageInfo = selService.getByPage(page, limit,stu_id);
		APIResult result = new APIResult();
		result.setCode(0);
		result.setData(pageInfo);
		return result;
	}
    
    //添加页面
    @GetMapping("/add")
    public String add(Model model,HttpSession session) {
    	Admin admin =(Admin)session.getAttribute("admin");
    	model.addAttribute("admin",admin);
   	 return "sel/add";
    }
  //接受ajax的请求
    @PostMapping("/add")
    @ResponseBody
    public APIResult add(Sel sel) {
   	//1.获取参数
		//2.调用Service层
   	 int res = selService.add(sel);
   	//3.返回数据
		if(res>0) {
		return APIResult.success(null);
		}else {
			return APIResult.error("添加失败");
		}
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
    	 int res = selService.delete(id);
    	//3.返回
 		if(res>0)
 		{
 			return APIResult.success(null);
 		}else {
 			return APIResult.error("删除失败");
 		}
     }
    //修改页面
    @GetMapping("/edit")
  	public String edit(int id,Model model) {
  		//先把当前要修改的选课的信息查询出来
  		Sel sel = selService.getById(id);
  		model.addAttribute("sel",sel);
  		return "sel/edit";
  	}
  //修改选课信息,并进行打分
    @PostMapping("/edit")
 	@ResponseBody
	public APIResult edit(Sel sel) {
		//1.参数
		//2.调用service层
		int res = selService.edit(sel);
		//3.返回数据
		if(res>0) {
			return APIResult.success(null);
		}else 
		{
			return APIResult.error("修改失败");
		}
	}
  //查询界面
    @GetMapping("/listx")
    public String listx() {
 	   return "sel/listx";
    }
    //功能实现
    @PostMapping("/listx")
    @ResponseBody  //返回JSON
	public APIResult listx(int page,int limit,Integer stu_id) {
   	//分页查询
   	//service层，Mapper层，entity类
		PageInfo pageInfo = selService.getByPage(page, limit,stu_id);
		APIResult result = new APIResult();
		result.setCode(0);
		result.setData(pageInfo);
		return result;
	}

}
