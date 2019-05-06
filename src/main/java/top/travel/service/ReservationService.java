package top.travel.service;

import top.travel.model.ReservationModel;
import top.travel.model.sql.ReservationSqlModel;

import java.util.List;

public class ReservationService {
    private static final ReservationSqlModel reservationSqlModel = new ReservationSqlModel();

    public List<ReservationModel> findAll(){
        return reservationSqlModel.findAll();
    }
    public boolean insertReservation(int u_id, int h_id, int r_number, String r_getInTime, String r_getOutTime){
        return reservationSqlModel.insertReservation(u_id, h_id,r_number,r_getInTime,r_getOutTime);
    }
    public boolean deleteReservation(int id){
        return reservationSqlModel.deleteReservation(id);
    }
}
