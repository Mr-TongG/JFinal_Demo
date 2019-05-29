package top.travel.controller;

import com.jfinal.core.Controller;
import top.travel.model.HotelCommModel;
import top.travel.model.SightCommModel;
import top.travel.service.CommentService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CommentController extends Controller {
    CommentService commentService = new CommentService();

    public void getAllComment(){
        int id = getParaToInt();
        List<HotelCommModel> hotelCommModels = commentService.getHotelComment(id);
        List<SightCommModel> sightCommModels = commentService.getSightComment(id);
        setAttr("hotelComment",hotelCommModels);
        setAttr("sightComment",sightCommModels);
        render("myComment.html");
    }
    public void insertHotelComment(){
        int u_id = getParaToInt("u_id");
        int h_id = getParaToInt("h_id");
        String h_comment = getPara("h_comment");
        String time = new SimpleDateFormat("yyyy-MM-dd-HH-mm").format(new Date(System.currentTimeMillis()));
        commentService.insertHotelComment(u_id,h_comment,h_id,time);
        render("showHotel.html");
    }
    public void insertSightComment(){
        int u_id = getParaToInt("u_id");
        int s_id = getParaToInt("s_id");
        String s_comment = getPara("s_comment");
        String time = new SimpleDateFormat("yyyy-MM-dd-HH-mm").format(new Date(System.currentTimeMillis()));
        commentService.insertHotelComment(u_id,s_comment,s_id,time);
        render("sight.html");
    }
    public void deleteHotelComment(){
        int id = getParaToInt();
        commentService.deleteHotelComment(id);
        getAllComment();
    }
    public void deleteSightComment(){
        int id = getParaToInt();
        commentService.deleteSightComment(id);
        getAllComment();
    }
}
