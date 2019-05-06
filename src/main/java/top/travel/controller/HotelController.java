package top.travel.controller;

import com.jfinal.core.Controller;
import top.travel.model.HotelImageModel;
import top.travel.model.HotelModel;
import top.travel.service.HotelService;
import top.travel.service.ImageService;

import java.util.List;

public class HotelController extends Controller {
    HotelService hotelService = new HotelService();
    ImageService imageService = new ImageService();

    //点击一个旅馆，会进入该旅馆的详细页面
    public void ShowHotel(){
        int id = Integer.parseInt(getPara());   //需要进行数据回显 在url中得到id值
        HotelModel hotel = hotelService.findById(id);
        List<HotelImageModel> hotelImages = imageService.findAnHotelImage(id);
        HotelImageModel hotelImage = hotelImages.get(1);
        setAttr("hotel",hotel);
        setAttr("hotelImages", hotelImages);
        setAttr("hotelImage",hotelImage);
        renderFreeMarker("hotel.html");
    }
}
