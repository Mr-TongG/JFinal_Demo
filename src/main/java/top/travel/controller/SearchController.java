package top.travel.controller;

import com.jfinal.core.Controller;
import top.travel.model.HotelModel;
import top.travel.service.HotelService;
import top.travel.service.SightService;

import java.util.List;

public class SearchController extends Controller {
    HotelService hotelService = new HotelService();
    SightService sightService = new SightService();
    String condition;
    String keywords;
    private final int pageSize = 3;      //每页数据条数

    //搜索景点或者旅馆，并做相应的推荐
    public void Search(){
        //Integer pageNumber=getParaToInt("pageNumber");//当前页的页号
        if(getPara("condition")!=null){
            condition = getPara("condition");
            setSessionAttr("conditions",condition);
        }
        else{
            condition = getSessionAttr("conditions");
        }
        if(condition.equals("酒店")){
            //List<HotelModel> list_hotel = hotelService.searchHotel(keywords);
            if(getPara("keywords")!=null){
                keywords = getPara("keywords");
                setSessionAttr("keywords",keywords);
            }
            else{
                keywords = getSessionAttr("keywords");
            }
            setAttr("hotelPage",hotelService.paginate(getParaToInt(0,1),pageSize,keywords));
            //在进行推荐时，避开已经搜索到的酒店
            List<HotelModel> recommendations = hotelService.findAll().subList(6,8);
            setAttr("recommendations",recommendations);
            render("searchOfHotel.html");
        }
        else{
            if(getPara("keywords")!=null){
                keywords = getPara("keywords");
                setSessionAttr("keywords",keywords);
            }
            else{
                keywords = getSessionAttr("keywords");
            }
            setAttr("sightPage",sightService.paginate(getParaToInt(0,1),pageSize,keywords));
            render("searchOfSight.html");
        }
    }
}
