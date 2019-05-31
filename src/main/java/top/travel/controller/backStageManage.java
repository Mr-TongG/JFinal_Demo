package top.travel.controller;

import com.jfinal.core.Controller;
import top.travel.model.HotelModel;
import top.travel.model.SightModel;
import top.travel.model.UserModel;
import top.travel.service.HotelService;
import top.travel.service.SightService;
import top.travel.service.UserService;

import java.util.List;

public class backStageManage extends Controller {
    UserService userService = new UserService();
    SightService sightService = new SightService();
    HotelService hotelService = new HotelService();
    private final static int pageSize = 5;
    public void index(){
        List<UserModel> userModels = userService.findAll();
        List<SightModel> sightModels = sightService.findAll();
        List<HotelModel> hotelModels = hotelService.findAll();
        setAttr("user_number",userModels.size());
        setAttr("hotel_number",hotelModels.size());
        setAttr("sight_number",sightModels.size());
        setAttr("list_hotel",hotelModels);
        setAttr("list_sight",sightModels);
        setAttr("list_user",userModels);
        render("index.html");
    }
    public void userManage(){
        //List<UserModel> userModels = userService.findAll();
        //setAttr("list_user",userModels);
        setAttr("userPage",userService.queryAll(getParaToInt(0,1),pageSize));
        render("userManage.html");
    }
    public void sightManage(){
        //List<SightModel> sightModels = sightService.findAll();
        //setAttr("list_sight",sightModels);
        setAttr("sightPage",sightService.queryAll(getParaToInt(0,1),pageSize));
        render("sightManage.html");
    }
    public void hotelManage(){
        //List<HotelModel> hotelModels = hotelService.findAll();
        //setAttr("list_hotel",hotelModels);
        setAttr("hotelPage",hotelService.queryAll(getParaToInt(0,1),pageSize));
        render("hotelManage.html");
    }
    public void stepTologin(){
        setAttr("content" , "");
        render("login.html");
    }
    public void login(){
        //得到输入框中的账号 密码
        String user = getPara("u_name");
        String password = getPara("u_pwd");
        //根据用户输入的账号进行数据库匹配
        if(userService.loginCheck(user,password))
        {
            UserModel currentUser = userService.findByName(user).get(0);
            setSessionAttr("currentUser",currentUser);
            redirect("/backStage/index");//登录后前往用户主页
        }
        else
        {
            setAttr("content" , "用户名或密码错误");
            render("login.html");//重新登录
        }
    }
    public void logout(){
        removeSessionAttr("currentUser");
        render("login.html");
    }
    public void sightImageManage(){
        setAttr("sightPage",sightService.queryAll(getParaToInt(0,1),pageSize));
        render("sightImageManage.html");
    }
    public void hotelImageManage(){
        setAttr("hotelPage",hotelService.queryAll(getParaToInt(0,1),pageSize));
        render("hotelImageManage.html");
    }
    public void search()//后台搜索
    {
        String condition = getPara("condition");
        String keyword = getPara("keyword");
        if(condition.equals("用户"))
        {
            setAttr("userPage",userService.queryByCondition(getParaToInt(0,1), pageSize, keyword));
            render("userManage.html");
        }
        else if(condition.equals("景点"))
        {
            setAttr("sightPage",sightService.paginate(getParaToInt(0,1), pageSize, keyword));
            render("sightManage.html");
        }
        else
        {
            setAttr("hotelPage",hotelService.paginate(getParaToInt(0,1), pageSize, keyword));
            render("hotelManage.html");
        }
    }
}
