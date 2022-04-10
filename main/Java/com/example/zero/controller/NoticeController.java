package com.example.zero.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.zero.entity.APIResult;
import com.example.zero.entity.Notice;
import com.example.zero.service.NoticeService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/notice")
public class NoticeController {
	 @Autowired
     private NoticeService noticeService;
	 //管理员查询界面
       @GetMapping("/list")
       public String list() {
    	   return "notice/list";
       }
     //功能实现
     @PostMapping("/list")
     @ResponseBody  //返回JSON
 	public APIResult list(int page,int limit,String title) {
    	//分页查询
    	//service层，Mapper层，entity类
 		PageInfo pageInfo = noticeService.getByPage(page, limit,title);
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
    	 int res = noticeService.delete(id);
    	//3.返回
 		if(res>0)
 		{
 			return APIResult.success(null);
 		}else {
 			return APIResult.error("删除失败");
 		}
     }
    //添加页面
     @GetMapping("/add")
     public String add() {
    	 return "notice/add";
     }
     //接受ajax的请求
     @PostMapping("/add")
     @ResponseBody
     public APIResult add(Notice notice) {
    	//1.获取参数
 		//2.调用Service层
    	 int res = noticeService.add(notice);
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
 		Notice notice = noticeService.getById(id);
 		model.addAttribute("notice",notice);
 		return "notice/edit";
 	}
     //修改公告信息
    @PostMapping("/edit")
 	@ResponseBody
	public APIResult edit(Notice notice) {
		//1.参数
		//2.调用service层
		int res = noticeService.edit(notice);
		//3.返回数据
		if(res>0) {
			return APIResult.success(null);
		}else 
		{
			return APIResult.error("修改失败");
		}
	}
  //学生查询界面
    @GetMapping("/listx")
    public String listx() {
 	   return "notice/listx";
    }
  //功能实现
  @PostMapping("/listx")
  @ResponseBody  //返回JSON
	public APIResult listx(int page,int limit,String title) {
 	//分页查询
 	//service层，Mapper层，entity类
		PageInfo pageInfo = noticeService.getByPage(page, limit,title);
		APIResult result = new APIResult();
		result.setCode(0);
		result.setData(pageInfo);
		return result;
	}
    	 
 }
