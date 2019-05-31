package top.travel.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Page;
import top.travel.model.UserModel;
import top.travel.model.sql.UserSqlModel;

public class UserService {
	UserSqlModel userSqlModel = new UserSqlModel();
	public List<UserModel> findAll()//找到所有用户信息
	{
		return userSqlModel.findAll();
	}

	public List<UserModel> findByName(String name)//根据用户名找到用户信息
	{
		return userSqlModel.findByName(name);
	}

	public List<UserModel> findById(int id)//根据用户编号找到用户信息
	{
		return userSqlModel.findById(id);
	}

	public boolean loginCheck(String name,String password)//登录判断
	{
		UserModel user = findByName(name).get(0);
		if(user == null)//用户名不存在，重新登录
		{
			return false;
		}
		String p = findByName(name).get(0).getStr("u_pwd");
		if(p.equals(password))//用户名，密码正确
		{
			return true;
		}
		else//密码错误，重新登录
		{
			return false;
		}
	}

	public boolean registerCheck(String name,String password,String phone)//注册判断
	{
		if(findByName(name).size() != 0)//用户名已存在
		{
			return false;
		}
		else
		{
			userSqlModel.insertUser(name, password, phone);
			return true;
		}
	}

	public boolean passwordCheck(String pwd)//密码规范判断
	{
		if(pwd.length() >= 6 && pwd.length() <= 12)
		{
			for(int i = 0;i < pwd.length();i++)
			{
				if((pwd.charAt(i) >= 48 && pwd.charAt(i) <= 57)||(pwd.charAt(i) >= 65 && pwd.charAt(i) <= 90 )||(pwd.charAt(i) >= 97 && pwd.charAt(i) <= 122))
				{

				}
				else
				{
					return false;
				}
			}
			return true;
		}
		else
		{
			return false;
		}
	}
	public void update(int id,String name,String phone,String gender,String content,String image)
	{
		userSqlModel.updateUser(id,name, phone, gender, content ,image);
	}

	public void updatePwd(int id,String pwd)
	{
		userSqlModel.updatePwd(id, pwd);
	}
	public void revise(int id, String u_image){
		userSqlModel.revise(id,u_image);
	}
	//后台管理
	public Page<UserModel> queryAll(int pageNumber , int pageSize){
		return userSqlModel.queryAll(pageNumber,pageSize);
	}
	public Page<UserModel> queryByCondition(int pageNumber , int pageSize , String keywords){
		return userSqlModel.queryByCondition(pageNumber,pageSize,keywords);
	}
	public void backStageInsertUser(String name,String password,String phone,String gender,String image,String abstraction)
	{
		userSqlModel.backStageInsertUser(name, password, phone, gender, image, abstraction);
	}
	public boolean deleteById(int id){
		return userSqlModel.deleteUser(id);
	}
}
