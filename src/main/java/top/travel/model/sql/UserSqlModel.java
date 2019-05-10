package top.travel.model.sql;

import java.util.List;

import top.travel.model.UserModel;

public class UserSqlModel {
	private static final UserModel userModel = new UserModel();
	public List<UserModel> findAll()//找到所有用户信息
	{
		return userModel.find("select * from user");
	}

	public List<UserModel> findByName(String name)//根据用户名找到用户信息
	{
		return userModel.find("select * from user where u_name = '"+name+"'");
	}

	public List<UserModel> findById(int id)//根据用户编号找到用户信息
	{
		return userModel.find("select * from user where u_id = "+id);
	}

	public boolean insertUser(String name,String password,String phone)
	{
		return new UserModel().set("u_name", name)
				.set("u_pwd",password)
				.set("u_phone",phone).save();
	}

	public boolean updateUser(String name,String phone,String gender,String content)
	{
		int id = findByName(name).get(0).getInt("u_id");
		userModel.findById(id).set("u_name", name).set("u_phone", phone).set("u_gender", gender).set("u_abstract", content);
		return userModel.update();
	}
}