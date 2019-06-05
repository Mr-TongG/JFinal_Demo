package top.travel.controller;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import top.travel.model.*;
import top.travel.service.CommentService;
import top.travel.service.HotelService;
import top.travel.service.ImageService;
import top.travel.service.SightService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CommentController extends Controller {
    CommentService commentService = new CommentService();
    HotelService hotelService = new HotelService();
    SightService sightService = new SightService();
    ImageService imageService = new ImageService();
    public static final int pageSize = 8;
    //用户查询评论
    public void getAllComment(){
        int id;
        if(getPara("u_id")!=null) {
            id = getParaToInt("u_id");
            setSessionAttr("u_id", getPara("u_id"));
        }
        else
            id=Integer.parseInt(getSessionAttr("u_id"));
        System.out.println(id);
        Page<HotelCommModel> hotelCommModelPage = commentService.getHotelComment(getParaToInt(0,1),pageSize,id);
        Page<SightCommModel> sightCommModelPage = commentService.getSightComment(getParaToInt(0,1),pageSize,id);
        setAttr("hotelCommentPage",hotelCommModelPage);
        setAttr("sightCommentPage",sightCommModelPage);
        render("myComments.html");
    }
    //详情页面展示评论
    public void getSightComment(){
        int id = getParaToInt("s_id");
        Page<SightCommModel> sightCommModelPage = commentService.showSightComment(getParaToInt(0,1),pageSize,id);
        setAttr("sightCommentPage",sightCommModelPage);
        render("commonPart/sightComment.html");
    }
    public void getHotelComment(){
        int id = getParaToInt("h_id");
        Page<HotelCommModel> hotelCommModelPage = commentService.showHotelComment(getParaToInt(0,1),pageSize,id);
        setAttr("hotelCommentPage",hotelCommModelPage);
        render("commonPart/hotelComment.html");
    }
    public void insertHotelComment(){
        int u_id = getParaToInt("u_id");
        int h_id = getParaToInt("h_id");
        String h_comment = getPara("h_comment");
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
        commentService.insertHotelComment(u_id,h_comment,h_id,time);
        HotelModel hotelModel = hotelService.findById(h_id);
        List<HotelImageModel> hotelImages = imageService.findAnHotelImage(h_id);
        setAttr("hotel",hotelModel);
        setAttr("hotelImages", hotelImages);
        render("showHotel.html");
    }
    public void insertSightComment(){
        int u_id = getParaToInt("u_id");
        int s_id = getParaToInt("s_id");
        String s_comment = getPara("s_comment");
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
        commentService.insertSightComment(u_id,s_comment,s_id,time);
        SightModel sight = sightService.findById(s_id);
        setAttr("sight",sight);
        render("sight.html");
    }
    public void deleteHotelComment(){
        int hc_id = getParaToInt("hc_id");
        commentService.deleteHotelComment(hc_id);
        getAllComment();
    }
    public void deleteSightComment(){
        int sc_id = getParaToInt("sc_id");
        commentService.deleteSightComment(sc_id);
        getAllComment();
    }
}
