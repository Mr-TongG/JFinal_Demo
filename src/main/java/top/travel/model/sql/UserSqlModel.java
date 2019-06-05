package top.travel.model.sql;

import java.util.List;

import com.jfinal.plugin.activerecord.Page;
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
		return userModel.find("select * from user where u_id ="+id);
	}

	public boolean insertUser(String name,String password,String phone,String u_image)
	{
		return new UserModel().set("u_name", name)
				.set("u_pwd",password)
				.set("u_phone",phone)
				.set("u_image",u_image).save();
	}
	public boolean backStageInsertUser(String name,String password,String phone,String gender,String image,String abstraction)
	{
		return new UserModel().set("u_name", name)
				.set("u_pwd",password)
				.set("u_phone",phone)
				.set("u_gender",gender)
				.set("u_image", image)
				.set("u_abstract", abstraction).save();
	}
	public boolean revise(int id ,String u_image){
		System.out.println(id);
		return userModel.findById(id).set("u_image",u_image).update();
	}

	public boolean updateUser(int id,String name,String phone,String gender,String content,String u_image)
	{
		return userModel.findById(id).set("u_name", name).set("u_phone", phone).set("u_gender", gender).set("u_abstract", content).set("u_image",u_image).update();
	}

	public boolean updatePwd(int id,String pwd)
	{
		return userModel.findById(id).set("u_pwd", pwd).update();
	}
	public boolean deleteUser(int id){
		return userModel.deleteById(id);
	}
	//后台管理时候用到
	public Page<UserModel> queryAll(int pageNumber , int pageSize){
		return userModel.paginate(pageNumber,pageSize,"select *","from user");
	}
	public Page<UserModel> queryByCondition(int pageNumber , int pageSize , String keywords){
		return userModel.paginate(pageNumber,pageSize,"select *","from user where u_name like ?","%"+keywords+"%");
	}
}