package top.travel.service;

import com.jfinal.plugin.activerecord.Page;
import top.travel.model.HotelCommModel;
import top.travel.model.SightCommModel;
import top.travel.model.sql.CommentSqlModel;

import java.util.List;

public class CommentService{
    public static final CommentSqlModel commentSqlModel = new CommentSqlModel();

    public Page<HotelCommModel> getHotelComment(int pageNumber , int pageSize, int id){
        return commentSqlModel.getHotelComment(pageNumber,pageSize,id);
    }
    public Page<SightCommModel> getSightComment(int pageNumber , int pageSize, int id){
        return commentSqlModel.getSightComment(pageNumber,pageSize,id);
    }
    public Page<HotelCommModel> showHotelComment(int pageNumber , int pageSize , int h_id){return commentSqlModel.showHotelComment(pageNumber,pageSize,h_id);}
    public Page<SightCommModel> showSightComment(int pageNumber , int pageSize , int s_id){return commentSqlModel.showSightComment(pageNumber,pageSize,s_id);}
    public boolean insertHotelComment(int u_id, String h_comment, int h_id, String time){
        return commentSqlModel.insertHotelComment(u_id,h_comment,h_id,time);
    }
    public boolean insertSightComment(int u_id, String s_comment, int s_id, String time){
        return commentSqlModel.insertSightComment(u_id,s_comment,s_id,time);
    }
    public boolean deleteHotelComment(int id){
        return commentSqlModel.deleteHotelComment(id);
    }
    public boolean deleteSightComment(int id){
        return commentSqlModel.deleteSightComment(id);
    }
}
