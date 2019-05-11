package top.travel.controller;

import com.jfinal.core.Controller;
import top.travel.model.UserModel;
import top.travel.service.UserService;

public class UserController extends Controller {
    UserService userService = new UserService();
    //个人设置
    public void stepToPersonal(){
        int u_id = getParaToInt();
        UserModel user = userService.findById(u_id).get(0);
        setAttr("user",user);
        render("personalCenter.html");
    }
    public void reviseUser(){
        //int u_id = getParaToInt("u_id");
        String u_name = getPara("u_name");
        String u_gender = getPara("u_gender");
        String u_pwd = getPara("u_pwd");
        String u_phone = getPara("u_phone");
        String u_abstract = getPara("u_abstract");
        String u_image = "";
        userService.revise(u_name,u_image);
        render("userIndex.html");
    }
}
