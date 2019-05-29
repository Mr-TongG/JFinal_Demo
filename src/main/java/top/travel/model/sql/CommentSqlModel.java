package top.travel.model.sql;

import top.travel.model.HotelCommModel;
import top.travel.model.SightCommModel;

import java.util.List;

public class CommentSqlModel {
    public static final HotelCommModel hotelCommModel = new HotelCommModel();
    public static final SightCommModel sightCommModel = new SightCommModel();

    public List<HotelCommModel> getHotelComment(int id){
        String sql = "select * from hotel_comment inner join user on hotel_comment.u_id = user.u_id where user.u_id =?";
        return hotelCommModel.find(sql,id);
    }
    public List<HotelCommModel> showHotelComment(int id){
        String sql = "select * from hotel_comment inner join hotel on hotel_comment.h_id = hotel.h_id where hotel.h_id =?";
        return hotelCommModel.find(sql,id);
    }

    public List<SightCommModel> getSightComment(int id){
        String sql = "select * from sight_comment inner join user on sight_comment.u_id = user.u_id where user.u_id =?";
        return sightCommModel.find(sql,id);
    }

    public boolean insertHotelComment(int u_id, String h_comment, int h_id, String time){
         return new HotelCommModel().set("u_id",u_id)
                .set("h_comment", h_comment)
                .set("h_id",h_id)
                .set("time",time)
                .save();
    }

    public boolean insertSightComment(int u_id, String s_comment, int s_id, String time){
        return new SightCommModel().set("u_id",u_id)
                .set("s_comment", s_comment)
                .set("s_id",s_id)
                .set("time",time)
                .save();
    }

    public boolean deleteHotelComment(int id){
        return hotelCommModel.deleteById(id);
    }
    public boolean deleteSightComment(int id){
        return sightCommModel.deleteById(id);
    }
}
