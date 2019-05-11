package top.travel.controller;

import com.jfinal.core.Controller;
import top.travel.model.HotelImageModel;
import top.travel.model.SightImageModel;
import top.travel.service.ImageService;



public class ImageController extends Controller{
    ImageService imageService = new ImageService();

    //通过动态获得酒店的ID，然后执行页面内的action，将图片回显
    //景点同理
    public void getHotelImage() {
        int id = getParaToInt();
        //搜索时用第一张图片替代
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
}
