package com.example.zero.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
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
import com.example.zero.entity.Notice;
import com.example.zero.service.AdminService;
import com.example.zero.util.VerCodeUtil;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/admin")
public class AdminController {
 
	@Autowired   //Ioc Spring的依赖倒转
	private AdminService adminService;
	
	
	//用户登录页面
	@GetMapping("/login")
	public String login() {
		return "admin/login";
	}
	//处理用户登录的请求
	@PostMapping("/login")
	public String LoginAuthenticator(Admin admin,String vercode,
			HttpSession session,Model model) {
		//1.获取参数
		//2.处理逻辑
		//2.1验证码是否正确
		if(!vercode.toUpperCase().equals(session.getAttribute("vercode"))) {
			model.addAttribute("error","验证码错误!");
			return "admin/login";
		}
		//2.2调用Service层判断,try\catch,先执行try中的代码，如果不成功则执行catch
		try {
			Admin loginAdmin = adminService.login(admin);
			//登录成功
			//1.保存登录信息
			session.setAttribute("admin", loginAdmin);
			//2.跳转到主页面,重定向
			return "redirect:/";
		   } catch (Exception e) {
			e.printStackTrace();
			//登录失败
			//e.getMessage();就是错误信息
			model.addAttribute("error",e.getMessage());
		}
		return "admin/login";
	}
	
	//验证码
	@GetMapping("/vercode")
	public void vercode(HttpServletResponse response,HttpSession session) {
	   //调用工具类生成一张验证码
		String vercode = VerCodeUtil.createVerCode(response);
		//保存验证码
		session.setAttribute("vercode", vercode);
	}
	//用户退出功能
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		//1.获取参数
		//2.处理逻辑，清除Session中保存的用户信息
		session.invalidate();
		//3.返回数据
		return "redirect:/admin/login";
	}
	//拦截器，对用户登录进行验证
	//当用户访问主页时，先进入拦截器
	//用户登录了，拦截器放行
	//用户没登录，拦截器拦截，跳转到登录页面
	//1.创建一个拦截器
	//在com.example.zero.intercepter包下，定义一个AdminInterceptor类
	//2.配置拦截器
	//在com.example.zero.config包下，定义一个WebConfig类
	
	//用户修改密码界面
	@GetMapping("/edit")
	public String edit(){
		
		return "admin/edit";
	}
	//处理用户密码修改请求
	@PostMapping("/edit")
	@ResponseBody
	public APIResult edit(Admin admin, HttpSession session) {
		//1.参数
		Admin loginAdmin = (Admin) session.getAttribute("admin");
		
		admin.setId(loginAdmin.getId());
		//2.调用service层
		int res = adminService.edit(admin);
		if(res>0) {
			return APIResult.success(null);
		}else {
			return APIResult.error("修改失败");
		}
	}
	
	//用户查询界面
	@GetMapping("/list")
	public String List() {
		return "admin/list";
	}
	
	//查询功能实现，将所有的用户信息查询出来
	@PostMapping("/list")
	@ResponseBody
	public APIResult list(int page,int limit,Integer code) {
    	//分页查询
    	//service层，Mapper层，entity类
		PageInfo pageInfo = adminService.getByPage(page, limit,code);
		APIResult result = new APIResult();
		result.setCode(0);
		result.setData(pageInfo);
		return result;
	}
	
	//用户注册功能界面
		@GetMapping("/add") 
		public String add() {
			return "admin/add";
		}
	//注册操作的提交
	//映射路径
	  @PostMapping("/add")
	  public String add(Admin admin,Model model) {
		    //1.参数
			//2.调用service层
			int res = adminService.add(admin);
			//3.返回数据
			if(res>0) {
				//添加成功
				//跳转到admin/login页面,重定向
				return "redirect:/admin/login";
			}
			else if(res==-1)
			{
				//提示用户名不可用
				//返回添加页面
				model.addAttribute("error","用户名不可用");
			}
			else {
				model.addAttribute("error","未知错误");
			}
			return "admin/add";
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
	    	 int res = adminService.delete(id);
	    	//3.返回
	 		if(res>0)
	 		{
	 			return APIResult.success(null);
	 		}else {
	 			return APIResult.error("删除失败");
	 		}
	     }
	   //打开修改用户信息页面
	    @GetMapping("/change")
	  	public String change(int id,Model model) {
	    	//先把当前要修改的项目的信息查询出来
	  		Admin admin = adminService.getById(id);
	  		model.addAttribute("admin",admin);
	    	 return "admin/change";
	     }
	   //修改公告信息
	    @PostMapping("/change")
	  	@ResponseBody
	 	public APIResult change(Admin admin) {
	 		//1.参数
	 		//2.调用service层
	 		int res = adminService.change(admin);
	 		//3.返回数据
	 		if(res>0) {
	 			return APIResult.success(null);
	 		}else 
	 		{
	 			return APIResult.error("修改失败");
	 		}
	 	}
	     
}
