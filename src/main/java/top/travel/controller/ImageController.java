package top.travel.controller;

import com.jfinal.core.Controller;
import top.travel.model.HotelImageModel;
import top.travel.service.ImageService;


public class ImageController extends Controller{
    ImageService imageService = new ImageService();

    //通过动态获得酒店的ID，然后执行页面内的action，将图片回显
    public void getHotelImage() {
        int id = getParaToInt("h_id");
        //搜索时用第一张图片替代
        HotelImageModel hotelImage = imageService.findAnHotelImage(id).get(1);
        setAttr("hotelImage",hotelImage);
        render("hotelImage.html");
    }

}
