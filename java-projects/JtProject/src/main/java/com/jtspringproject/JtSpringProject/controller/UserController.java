package com.jtspringproject.JtSpringProject.controller;

import com.jtspringproject.JtSpringProject.models.Cart;
import com.jtspringproject.JtSpringProject.models.Product;
import com.jtspringproject.JtSpringProject.models.User;

import java.io.Console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.jtspringproject.JtSpringProject.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.jtspringproject.JtSpringProject.services.UserService;
import com.jtspringproject.JtSpringProject.services.ProductService;


/**
 * 用户控制器
 * 负责处理所有普通用户相关的HTTP请求，包括：
 * - 用户登录认证
 * - 用户注册
 * - 商品浏览
 * - 购买操作
 * - 测试页面（用于学习Model和ModelAndView的使用）
 *
 * 此控制器不使用路径前缀，直接映射到根路径
 */
@Controller
public class UserController{
	
	// 依赖注入：用户服务，用于处理用户相关业务逻辑
	@Autowired
	private UserService userService;

	// 依赖注入：商品服务，用于处理商品相关业务逻辑
	@Autowired
	private ProductService productService;

	/**
	 * 用户注册页面
	 * 路由：GET /register
	 *
	 * 功能：显示用户注册表单页面
	 *
	 * @return 返回注册页面视图名称
	 */
	@GetMapping("/register")
	public String registerUser()
	{
		return "register";
	}

	/**
	 * 购买页面
	 * 路由：GET /buy
	 *
	 * 功能：显示购买页面
	 *
	 * @return 返回购买页面视图名称
	 */
	@GetMapping("/buy")
	public String buy()
	{
		return "buy";
	}
	

	/**
	 * 用户登录页面（根路径）
	 * 路由：GET /
	 *
	 * 功能：显示用户登录页面，作为应用的默认首页
	 *
	 * @param model Spring MVC模型对象
	 * @return 返回用户登录页面视图名称
	 */
	@GetMapping("/")
	public String userlogin(Model model) {
		
		return "userLogin";
	}

	/**
	 * 用户登录验证
	 * 路由：POST /userloginvalidate
	 *
	 * 功能：
	 * 1. 接收用户名和密码参数
	 * 2. 调用userService.checkLogin()验证用户身份
	 * 3. 验证成功：
	 *    - 创建Cookie保存用户名
	 *    - 查询所有商品列表
	 *    - 返回首页，显示商品列表
	 * 4. 验证失败：
	 *    - 返回登录页面
	 *    - 显示错误消息"请输入正确的邮箱和密码"
	 *
	 * @param username 用户名
	 * @param pass 密码
	 * @param model Spring MVC模型对象
	 * @param res HttpServletResponse对象，用于添加Cookie
	 * @return ModelAndView对象，包含视图名称和数据
	 */
	@RequestMapping(value = "userloginvalidate", method = RequestMethod.POST)
	public ModelAndView userlogin( @RequestParam("username") String username, @RequestParam("password") String pass,Model model,HttpServletResponse res) {
		System.out.println(username);
		System.out.println(pass);
		// 数据库检索
		User u = this.userService.checkLogin(username, pass);
		System.out.println(u.getUsername());

		if(username.equals(u.getUsername())) {

			res.addCookie(new Cookie("username", u.getUsername()));
			ModelAndView mView  = new ModelAndView("index");	
			mView.addObject("user", u);
			List<Product> products = this.productService.getProducts();

			if (products.isEmpty()) {
				mView.addObject("msg", "No products are available");
			} else {
				mView.addObject("products", products);
			}
			return mView;

		}else {
			ModelAndView mView = new ModelAndView("userLogin");
			mView.addObject("msg", "Please enter correct email and password");
			return mView;
		}
		
	}
	
	
	/**
	 * 用户查看商品列表
	 * 路由：GET /user/products
	 *
	 * 功能：
	 * 1. 查询所有商品
	 * 2. 如果商品列表为空，显示"无商品可用"的消息
	 * 3. 如果有商品，将商品列表传递给视图
	 * 4. 返回用户商品列表页面
	 *
	 * @return ModelAndView对象，包含商品列表数据和视图名称
	 */
	@GetMapping("/user/products")
	public ModelAndView getproduct() {

		ModelAndView mView = new ModelAndView("uproduct");

		List<Product> products = this.productService.getProducts();

		if(products.isEmpty()) {
			mView.addObject("msg","No products are available");
		}else {
			mView.addObject("products",products);
		}

		return mView;
	}

	/**
	 * 处理新用户注册
	 * 路由：POST /newuserregister
	 *
	 * 功能：
	 * 1. 接收用户注册信息（通过@ModelAttribute自动绑定到User对象）
	 * 2. 检查用户名是否已存在
	 * 3. 用户名不存在：
	 *    - 设置用户角色为ROLE_NORMAL（普通用户）
	 *    - 调用userService.addUser()保存新用户
	 *    - 返回登录页面，提示用户可以登录
	 * 4. 用户名已存在：
	 *    - 返回注册页面
	 *    - 显示错误消息，提示用户名已被占用
	 *
	 * @param user 用户对象，包含注册表单提交的所有信息
	 * @return ModelAndView对象，返回登录页面或注册页面
	 */
	@RequestMapping(value = "newuserregister", method = RequestMethod.POST)
	public ModelAndView newUseRegister(@ModelAttribute User user)
	{
		// Check if username already exists in database
		boolean exists = this.userService.checkUserExists(user.getUsername());

		if(!exists) {
			System.out.println(user.getEmail());
			user.setRole("ROLE_NORMAL");
			this.userService.addUser(user);

			System.out.println("New user created: " + user.getUsername());
			ModelAndView mView = new ModelAndView("userLogin");
			return mView;
		} else {
			System.out.println("New user not created - username taken: " + user.getUsername());
			ModelAndView mView = new ModelAndView("register");
			mView.addObject("msg", user.getUsername() + " is taken. Please choose a different username.");
			return mView;
		}
	}
	
	
	
	// ========================== 学习测试模块 ==========================
	// 以下方法用于演示Spring MVC中Model和ModelAndView的使用方式

	/**
	 * 测试页面1：演示Model的使用
	 * 路由：GET /test
	 *
	 * 功能：用于学习如何使用Model对象向视图传递数据
	 * 演示内容：
	 * 1. 向模型添加简单属性（字符串、整数）
	 * 2. 向模型添加集合对象（List）
	 * 3. 展示如何在视图中访问这些数据
	 *
	 * @param model Spring MVC模型对象，用于向视图传递数据
	 * @return 返回test视图名称
	 */
	@GetMapping("/test")
	public String Test(Model model)
	{
		System.out.println("test page");
		model.addAttribute("author","jay gajera");
		model.addAttribute("id",40);

		List<String> friends = new ArrayList<String>();
		model.addAttribute("f",friends);
		friends.add("xyz");
		friends.add("abc");

		return "test";
	}

	/**
	 * 测试页面2：演示ModelAndView的使用
	 * 路由：GET /test2
	 *
	 * 功能：用于学习如何使用ModelAndView对象
	 * 演示内容：
	 * 1. 创建ModelAndView对象
	 * 2. 使用addObject()方法添加数据到模型
	 * 3. 使用setViewName()方法设置视图名称
	 * 4. 展示如何同时处理数据和视图
	 *
	 * 说明：ModelAndView是Model和View的组合，可以在一个对象中同时设置模型数据和视图名称
	 *
	 * @return ModelAndView对象，包含模型数据和视图名称
	 */
	@GetMapping("/test2")
	public ModelAndView Test2()
	{
		System.out.println("test page");
		//create modelandview object
		ModelAndView mv=new ModelAndView();
		mv.addObject("name","jay gajera 17");
		mv.addObject("id",40);
		mv.setViewName("test2");

		List<Integer> list=new ArrayList<Integer>();
		list.add(10);
		list.add(25);
		mv.addObject("marks",list);
		return mv;


	}


	// ========================== 待实现功能 ==========================
	// 以下是购物车功能的预留代码，尚未实现

//	@GetMapping("carts")
//	public ModelAndView  getCartDetail()
//	{
//		ModelAndView mv= new ModelAndView();
//		List<Cart>carts = cartService.getCarts();
//	}
	  
}
