package top.travel.controller;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import top.travel.model.HotelModel;
import top.travel.service.HotelService;
import top.travel.service.ImageService;

import java.util.List;

public class SearchController extends Controller {
    HotelService hotelService = new HotelService();
    ImageService imageService = new ImageService();

    //搜索景点或者旅馆，并做相应的推荐
    public void Search(){
        String condition = getPara("condition");
        //String keywords = getPara("keywords");
        if(condition.equals("酒店")){
            //List<HotelModel> list_hotel = hotelService.searchHotel(keywords);
            paginate();
            List<HotelModel> recommendations = hotelService.findAll();
            //setAttr("list_hotel",list_hotel);
            setAttr("recommendations",recommendations);
            render("searchOfHotel.html");
        }
        else{

        }
    }
    //分页
    public void paginate(){
        String keywords = getPara("keywords");
        Integer pageNumber=getParaToInt("pageNumber");//当前页的页号
        final int pageSize = 3;      //每页数据条数
        //String keywords = getPara("keywords");
        if(pageNumber==null){
            pageNumber=1;
        }
        Page<HotelModel> page= hotelService.paginate(pageNumber,pageSize,keywords);
        setAttr("hotelPage",page);
    }
}
