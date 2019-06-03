package top.travel.model.sql;

import com.jfinal.plugin.activerecord.Page;
import top.travel.model.HotelCommModel;
import top.travel.model.SightCommModel;

import java.util.List;

public class CommentSqlModel {
    public static final HotelCommModel hotelCommModel = new HotelCommModel();
    public static final SightCommModel sightCommModel = new SightCommModel();
    //用户查询
    public Page<HotelCommModel> getHotelComment(int pageNumber , int pageSize , int id){
        //String sql = "select * from hotel_comment inner join user on hotel_comment.u_id = user.u_id where user.u_id =?";
        return hotelCommModel.paginate(pageNumber,pageSize,"select *","from hotel_comment inner join user on hotel_comment.u_id = user.u_id where user.u_id =?",id);
    }
    public Page<SightCommModel> getSightComment(int pageNumber , int pageSize , int id){
        //String sql = "select * from sight_comment inner join sight on sight_comment.u_id = user.u_id where user.u_id =?";
        return sightCommModel.paginate(pageNumber,pageSize,"select *","from sight_comment inner join user on sight_comment.u_id = user.u_id where user.u_id =?",id);
    }
    //景点，酒店查询
    /*public List<HotelCommModel> showHotelComment(int id){
        String sql = "select * from hotel_comment inner join hotel on hotel_comment.h_id = hotel.h_id where hotel.h_id =?";
        return hotelCommModel.find(sql,id);
    }*/
    public Page<HotelCommModel> showHotelComment(int pageNumber , int pageSize , int h_id){
        String from = "from hotel_comment where h_id =?";
        String totalRowSql = "select count(*) " + from;
        String findSql = "select * " + from;
        return hotelCommModel.paginateByFullSql(pageNumber,pageSize,totalRowSql,findSql,h_id);
    }
    public Page<SightCommModel> showSightComment(int pageNumber , int pageSize , int s_id){
        return sightCommModel.paginate(pageNumber,pageSize,"select *","from sight_comment inner join sight on sight_comment.s_id = sight.s_id where sight_comment.s_id =?",s_id);
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
