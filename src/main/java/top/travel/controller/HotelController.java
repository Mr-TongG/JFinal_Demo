package top.travel.controller;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import top.travel.model.HotelCommModel;
import top.travel.model.HotelImageModel;
import top.travel.model.HotelModel;
import top.travel.service.CommentService;
import top.travel.service.HotelService;
import top.travel.service.ImageService;

import java.util.List;

public class HotelController extends Controller {
    HotelService hotelService = new HotelService();
    ImageService imageService = new ImageService();
    CommentService commentService = new CommentService();
    public static final int pageSize = 8;

    //点击一个旅馆，会进入该旅馆的详细页面
    public void ShowHotel(){
        int id = Integer.parseInt(getPara());   //需要进行数据回显 在url中得到id值
        HotelModel hotel = hotelService.findById(id);
        //详细显示时显示全部的图片并进行轮播
        List<HotelImageModel> hotelImages = imageService.findAnHotelImage(id);
        setAttr("hotel",hotel);
        setAttr("hotelImages", hotelImages);
        //setAttr("hotelCommentPage",hotelCommentPage);
        render("showHotel.html");
    }
    //在主页点击酒店时用这个替代一下
    public void InitHotel(){
        List<HotelModel> list_hotel = hotelService.searchHotel("酒店");
        List<HotelModel> recommendations = hotelService.findAll();
        setAttr("list_hotel",list_hotel);
        setAttr("recommendations",recommendations);
        render("hotel.html");
    }
    //后台插入酒店
    public void insertHotel(){
        String h_name = getPara("h_name");
        int h_price = getParaToInt("h_price");
        int h_grade = getParaToInt("h_grade");
        String h_phone= getPara("h_phone");
        String h_location = getPara("h_location");
        String h_introduction = getPara("h_introduction");
        hotelService.insertHotel(h_name,h_price,h_location,h_grade,h_introduction,h_phone);
        redirect("/backStage/hotelManage");
    }
    //后台删除酒店
    public void deleteHotel(){
        int id = getParaToInt();
        hotelService.deleteById(id);
        redirect("/backStage/hotelManage");
    }
    //后台更新酒店
    public void updateHotel(){
        int h_id = getParaToInt("h_id");
        String h_name = getPara("h_name");
        int h_price = getParaToInt("h_price");
        int h_grade = getParaToInt("h_grade");
        String h_phone= getPara("h_phone");
        String h_location = getPara("h_location");
        String h_introduction = getPara("h_introduction");
        hotelService.updateById(h_id,h_name,h_price,h_location,h_grade,h_introduction,h_phone);
        redirect("/backStage/hotelManage");
    }
}
