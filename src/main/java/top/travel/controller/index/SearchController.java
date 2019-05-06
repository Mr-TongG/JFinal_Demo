package top.travel.controller.index;

import com.jfinal.core.Controller;
import top.travel.model.HotelImageModel;
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
        String keywords = getPara("keywords");
        if(condition.equals("酒店")){
            List<HotelModel> list_hotel = hotelService.searchHotel(keywords);
            List<HotelModel> recommendations = hotelService.findAll();
            for(int i = 0; list_hotel.size()>0; i++){
                for(int j = 0; )
            }
            setAttr("list_hotel",list_hotel);
            setAttr("recommendations",recommendations);
            renderFreeMarker("searchOfHotel.html");
        }
        else{

        }
    }
}
