package top.travel.controller.index;

import com.jfinal.core.Controller;

import top.travel.model.PersonModel;
import top.travel.model.UserModel;
import top.travel.service.PersonService;
import top.travel.service.UserService;
import top.travel.util.MyCookie;
import top.travel.util.MySession;

import java.util.List;


public class LoginController extends Controller{

	UserService userService = new UserService();

	public void stepToUserIndex(){
		render("/travelSite/userIndex.html");
	}

	public void stepToLogin(){
		render("/travelSite/login.html");
	}

	public void stepToSignUp(){
		render("/travelSite/signUp.html");
	}

	public void loginIndex(){

		//得到输入框中的账号 密码
		String user = getPara("user");
		String password = getPara("password");
		//根据用户输入的账号进行数据库匹配
		if(userService.loginCheck(user,password))
		{
			UserModel currentUser = userService.findByName(user).get(0);
			setSessionAttr("currentUser",currentUser);
			redirect("/");//登录后前往用户主页
			//render("/travelSite/userIndex.html");//前往登录后用户主页
		}
		else
		{
			setAttr("content" , "用户名或密码错误");
			render("/travelSite/login.html");//重新登录
		}

//		if(findByUser != null){
//			//代表有值 账号正确 匹配密码
//			if(password.equals(findByUser.get("password"))){
//				//这里代表匹配成功 成功之后需要设置Cookie 并查询 t_person 所有数据并返回至 addlist.html 界面
//				List<PersonModel> t_person = personService.findAll();
//
//				//代表登陆成功删除标识Session
//				removeSessionAttr(MySession.getSessionKey());
//				//设置标识 Cookie
//				setCookie(MyCookie.getCookieKey(), MyCookie.getCookie(), 600);
//				//用于在主页中显示用户姓名的Cookie
//				setSessionAttr("User", user);
//				setAttr("personAll" , t_person);
//				redirect("/home/HomePage");
//			}else{
//				//这里代表匹配失败 返回登录界面 并提示密码错误
//				setAttr("msg" , "密码错误");
//				renderFreeMarker("index.html");
//			}
//		}else{
//			//代表其中没有值 登录失败 账号不存在
//			setAttr("msg" , "账号不存在！！");
//			renderFreeMarker("index.html");
//		}

	}

	public void signUp()
	{
		String user = getPara("user");
		String password = getPara("password");
		String phone = getPara("phone");
		//根据用户输入的账号进行数据库匹配
		if(userService.registerCheck(user,password,phone))//注册成功
		{
			setAttr("content","");//不让前端显示异常
			render("/travelSite/login.html");//前往登录界面
		}
		else
		{
			setAttr("content" , "用户名已存在");
			render("/travelSite/signup.html");//重新注册
		}
	}
	//退出
	public void logout(){
		removeSessionAttr("currentUser");
		redirect("/");//前往主页
	}
}
