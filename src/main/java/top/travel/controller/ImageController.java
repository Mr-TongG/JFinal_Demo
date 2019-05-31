package top.travel.controller;

import com.jfinal.core.Controller;
import com.jfinal.upload.UploadFile;
import top.travel.model.HotelImageModel;
import top.travel.model.SightImageModel;
import top.travel.service.ImageService;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class ImageController extends Controller{
    ImageService imageService = new ImageService();

    //通过动态获得酒店的ID，然后执行页面内的action，将图片回显
    //景点同理
    public void getHotelImage() {
        int id = getParaToInt();
        //搜索时用第一张图片替代
        System.out.println(id);
        HotelImageModel hotelImage = imageService.findAnHotelImage(id).get(0);
        setAttr("hotelImage",hotelImage);
        render("commonPart/hotelImage.html");
    }
    public void getSightImage() {
        int id = getParaToInt();
        //搜索时用第一张图片替代
        SightImageModel sightImage = imageService.findAnSightImage(id).get(0);
        setAttr("sightImage",sightImage);
        render("commonPart/sightImage.html");
    }
    public void getAllHotelImage(){
        int id = getParaToInt();
        //后台显示全部图片
        List<HotelImageModel> hotelImages = imageService.findAnHotelImage(id);
        setAttr("hotelImages",hotelImages);
        render("backStage/showAllHotelImage.html");
    }
    public void getAllSightImage(){
        int id = getParaToInt();
        //后台显示全部图片
        List<SightImageModel> sightImages = imageService.findAnSightImage(id);
        setAttr("sightImages",sightImages);
        render("backStage/showAllSightImage.html");
    }
    public void deleteHotelImage(){
        int id = getParaToInt();
        imageService.deleteHotelImage(id);
        redirect("/backStage/hotelImageManage");
    }
    public void deleteSightImage(){
        int id = getParaToInt();
        imageService.deleteSightImage(id);
        redirect("/backStage/sightImageManage");
    }
    public void insertHotelImage(){
        String h_image = uploadHotelImage();
        int h_id = getParaToInt("h_id");
        imageService.insertHotelImage(h_image,h_id);
        redirect("/backStage/hotelImageManage");
    }
    public void insertSightImage(){
        String s_image = uploadSightImage();
        int s_id = getParaToInt("s_id");
        imageService.insertSightImage(s_image,s_id);
        redirect("/backStage/sightImageManage");
    }

    public String uploadHotelImage(){
        int maxSize = 10 * 1024 * 1024;              //上传文件大小10M
        //获得上传文件
        UploadFile uploadFile = getFile("h_image","",maxSize,"UTF-8");
        //获得源文件文件名
        String fileName = uploadFile.getFileName();
        //得到源文件的后缀
        String extension = fileName.substring(fileName.lastIndexOf("."));
        //以当前系统时间为准的格式
        String format = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System.currentTimeMillis()));
        //文件保存名（到数据库）
        String newName = format+extension;
        //文件更名，把文件放到已经建好的三个文件夹里（hotel/sight/user）
        String newPath = "T://JFinal_Demo//JFinal_Demo//src//main//webapp//travelSite/asserts/images/hotel"+"/"+newName;  //新文件的完整路径
        File m = new File(uploadFile.getUploadPath()+uploadFile.getFileName());//先读取原文件
        File file = new File(newPath);//预想的文件
        m.renameTo(file);//文件更名为预想的文件
        //对session进行更新，同时更新图片
        return "hotel/"+newName;
    }
    public String uploadSightImage(){
        int maxSize = 10 * 1024 * 1024;              //上传文件大小10M
        //获得上传文件
        UploadFile uploadFile = getFile("s_image","",maxSize,"UTF-8");
        //获得源文件文件名
        String fileName = uploadFile.getFileName();
        //得到源文件的后缀
        String extension = fileName.substring(fileName.lastIndexOf("."));
        //以当前系统时间为准的格式
        String format = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System.currentTimeMillis()));
        //文件保存名（到数据库）
        String newName = format+extension;
        //文件更名，把文件放到已经建好的三个文件夹里（hotel/sight/user）
        String newPath = "T://JFinal_Demo//JFinal_Demo//src//main//webapp//travelSite/asserts/images/sight"+"/"+newName;  //新文件的完整路径
        File m = new File(uploadFile.getUploadPath()+uploadFile.getFileName());//先读取原文件
        File file = new File(newPath);//预想的文件
        m.renameTo(file);//文件更名为预想的文件
        //对session进行更新，同时更新图片
        return "sight/"+newName;
    }
}
