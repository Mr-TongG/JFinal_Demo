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
        //上传文件名（以当前系统时间为准）
        String uploadFileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System.currentTimeMillis()));
        int maxSize = 10 * 1024 * 1024;              //上传文件大小10M
        //获得上传文件
        UploadFile uploadFile = getFile("u_image","",maxSize,"UTF-8");
        //获得源文件文件名
        String fileName = uploadFile.getFileName();
        //得到源文件的后缀
        String extension = fileName.substring(fileName.lastIndexOf("."));
        //文件保存名（到数据库）
        String imgSrc=uploadFileName+extension;
        //文件更名
        File file = new File("/upload/temp/"+fileName);
        if(file.exists())
        {
            file.renameTo(new File("/upload/temp/"+imgSrc));
        }
        String u_name = "admin";
        userService.revise(u_name,imgSrc);
        render("test.html");
    }



}
