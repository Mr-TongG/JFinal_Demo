package top.travel.model.sql;

import top.travel.model.ReservationModel;

import java.util.List;

public class ReservationSqlModel {
    private static final ReservationModel reservationModel = new ReservationModel();

    public ReservationModel getReservationModel(){
        return reservationModel;
    }

    public List<ReservationModel> findAll(){
        return reservationModel.findAll();
    }
    //预订酒店（插入预订信息）插入的时候要创建新的对象！！！
    public boolean insertReservation(int u_id, int h_id, int r_number, String r_getInTime, String r_getOutTime){
        return new ReservationModel().
                set("u_id",u_id).
                set("h_id",h_id).
                set("r_number",r_number).
                set("r_getInTime",r_getInTime).
                set("r_getOutTime",r_getOutTime).save();
    }
    //取消预订（删除预订信息）
    public boolean deleteReservation(int id){
        return reservationModel.deleteById(id);
    }
}
