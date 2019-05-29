package top.travel.service;

import top.travel.model.HotelCommModel;
import top.travel.model.SightCommModel;
import top.travel.model.sql.CommentSqlModel;

import java.util.List;

public class CommentService{
    public static final CommentSqlModel commentSqlModel = new CommentSqlModel();

    public List<HotelCommModel> getHotelComment(int id){
        return commentSqlModel.getHotelComment(id);
    }
    public List<SightCommModel> getSightComment(int id){
        return commentSqlModel.getSightComment(id);
    }
    public List<HotelCommModel> showHotelComment(int id){return commentSqlModel.showHotelComment(id);}
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
