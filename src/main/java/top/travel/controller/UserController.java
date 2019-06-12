package top.travel.controller;

import com.jfinal.core.Controller;
import com.jfinal.upload.UploadFile;
import top.travel.model.UserModel;
import top.travel.service.UserService;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserController extends Controller {
    UserService userService = new UserService();
    //个人设置
    public void stepToPersonal(){
        render("personalCenter.html");
    }

    public void stepToUpdatePwd()
    {
        render("updatePwd.html");
    }

    public void reviseUser(){//更新个人信息
        String u_image = upload();
        if(u_image == null)
            u_image = getPara("u_image");
        int u_id = getInt("u_id");
        String u_name = getPara("u_name");
        String u_gender = getPara("u_gender");
        String u_phone = getPara("u_phone");
        String u_abstract = getPara("u_abstract");


        userService.update(u_id, u_name, u_phone, u_gender, u_abstract, u_image);
        UserModel currentUser = userService.findById(u_id).get(0);
        removeSessionAttr("currentUser");
        setSessionAttr("currentUser",currentUser);
        redirect("/");
    }

    public void deleteById()
    {
        String id = getPara();
        userService.deleteById(Integer.parseInt(id));
        redirect("/backStage/userManage");
    }

    public void insertUser()//后台创建用户
    {
        String u_image = upload();
        String u_name = getPara("u_name");
        String u_password = getPara("u_password");
        String u_gender = getPara("u_gender");
        String u_phone = getPara("u_phone");
        String u_abstract = getPara("u_abstract");
        if(userService.registerCheck(u_name, u_password, u_phone))
        {
            userService.backStageInsertUser(u_name, u_password, u_phone, u_gender, u_image, u_abstract);
        }
        redirect("/backStage/userManage");
    }

    public void updatePwd(){//修改密码

        int u_id = getInt("u_id");
        String u_pwd = getPara("u_pwd");
        String o_pwd = getPara("o_pwd");
        String n_pwd = getPara("n_pwd");
        String s_pwd = getPara("s_pwd");
        if(o_pwd.compareTo(u_pwd) != 0)
        {
            setAttr("content","原密码错误，请重新输入");
            render("/travelSite/updatePwd.html");
        }
        else if(!userService.passwordCheck(n_pwd))
        {
            setAttr("content","新密码不符合规范，请重新输入");
            render("/travelSite/updatePwd.html");
        }
        else if(n_pwd.compareTo(s_pwd) != 0)
        {
            setAttr("content","请重新确认新密码");
            render("/travelSite/updatePwd.html");
        }
        else
        {
            userService.updatePwd(u_id, n_pwd);
            UserModel currentUser = userService.findById(u_id).get(0);
            removeSessionAttr("currentUser");
            setSessionAttr("currentUser",currentUser);
            redirect("/");
        }
    }

    public String upload(){
        int maxSize = 10 * 1024 * 1024;              //上传文件大小10M
        //获得上传文件
        UploadFile uploadFile = getFile("u_image","",maxSize,"UTF-8");
        //获得源文件文件名
        String fileName;
        if(uploadFile != null)
            fileName = uploadFile.getFileName();
        else
            return null;
        //得到源文件的后缀
        String extension = fileName.substring(fileName.lastIndexOf("."));
        //以当前系统时间为准的格式
        String format = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System.currentTimeMillis()));
        //文件保存名（到数据库）
        String newName = format+extension;
        //文件更名，把文件放到已经建好的三个文件夹里（hotel/sight/user）
        String newPath = "T://JFinal_Demo//JFinal_Demo//src//main//webapp//travelSite/asserts/images/user"+"/"+newName;  //新文件的完整路径
        File m = new File(uploadFile.getUploadPath()+uploadFile.getFileName());//先读取原文件
        File file = new File(newPath);//预想的文件
        m.renameTo(file);//文件更名为预想的文件
        //对session进行更新，同时更新图片
        return "user/"+newName;
    }
}
