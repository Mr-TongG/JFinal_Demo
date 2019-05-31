package top.travel.controller;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import top.travel.model.HotelCommModel;
import top.travel.model.SightCommModel;
import top.travel.service.CommentService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CommentController extends Controller {
    CommentService commentService = new CommentService();
    public static final int pageSize = 8;
    //用户查询评论
    public void getAllComment(){
        int id = getParaToInt();
        Page<HotelCommModel> hotelCommModelPage = commentService.getHotelComment(getParaToInt(0,1),pageSize,id);
        Page<SightCommModel> sightCommModelPage = commentService.getSightComment(getParaToInt(0,1),pageSize,id);
        setAttr("hotelCommentPage",hotelCommModelPage);
        setAttr("sightCommentPage",sightCommModelPage);
        render("myComment.html");
    }
    public void getSightComment(){
        int id = getParaToInt();
        Page<SightCommModel> sightCommModelPage = commentService.showSightComment(getParaToInt(0,1),pageSize,id);
        setAttr("sightCommentPage",sightCommModelPage);
        render("commonPart/sightComment.html");
    }
    public void getHotelComment(){
        int id = getParaToInt();
        Page<HotelCommModel> hotelCommModelPage = commentService.showHotelComment(getParaToInt(0,1),pageSize,id);
        setAttr("hotelCommentPage",hotelCommModelPage);
        render("commonPart/sightComment.html");
    }
    public void insertHotelComment(){
        int u_id = getParaToInt("u_id");
        int h_id = getParaToInt("h_id");
        String h_comment = getPara("h_comment");
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
        commentService.insertHotelComment(u_id,h_comment,h_id,time);
        render("showHotel.html");
    }
    public void insertSightComment(){
        int u_id = getParaToInt("u_id");
        int s_id = getParaToInt("s_id");
        String s_comment = getPara("s_comment");
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
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
