package top.travel.controller.index;

import com.jfinal.core.Controller;
import com.jfinal.upload.UploadFile;
import top.travel.service.UserService;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUploadController extends Controller {
    UserService userService = new UserService();
    public void upload(){
        int maxSize = 10 * 1024 * 1024;              //上传文件大小10M
        //获得上传文件
        UploadFile uploadFile = getFile("u_image","",maxSize,"UTF-8");
        //获得源文件文件名
        String fileName = uploadFile.getFileName();
        //得到源文件的后缀
        String extension = fileName.substring(fileName.lastIndexOf("."));
        //以当前系统时间为准的格式
        String format = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System.currentTimeMillis()));
        //文件保存名（到数据库）
        String newName=format+extension;
        //文件更名，把文件放到已经建好的三个文件夹里（hotel/sight/user）
        String newPath = "T://JFinal_Demo//JFinal_Demo//src//main//webapp//travelSite/asserts/images"+"/"+newName;  //新文件的完整路径
        File m = new File(uploadFile.getUploadPath()+uploadFile.getFileName());//先读取原文件
        File file = new File(newPath);//预想的文件
        m.renameTo(file);//文件更名为预想的文件

        int u_id = getParaToInt("u_id");
        String u_name = getPara("u_name");
        userService.revise(u_id,newName);
        //对session进行更新，同时更新图片
        setSessionAttr("currentUser",userService.findById(u_id).get(0));
        redirect("/");
    }



}
