package top.travel.controller;

import com.jfinal.core.Controller;
import top.travel.model.ReservationModel;
import top.travel.service.HotelService;
import top.travel.service.ReservationService;

import java.util.List;

public class ReservationController extends Controller {
    ReservationService reservationService = new ReservationService();
    HotelService hotelService = new HotelService();
    //预订酒店
    public void reserveHotel(){
        int u_id = getParaToInt("u_id");
        int h_id = getParaToInt("h_id");
        //房间数量
        int r_number_1 = getParaToInt("r_number_1");
        //入住人数
        int r_number_2 = getParaToInt("r_number_2");
        String r_getInTime = getPara("r_getInTime");
        String r_getOutTime = getPara("r_getOutTime");
        reservationService.insertReservation(u_id,h_id,r_number_1,r_getInTime,r_getOutTime);
        //再次查询信息，回到之前的页面
        redirect("/hotel/ShowHotel/"+h_id);
    }
    //取消预订
    public void CancelReservation(){
        int r_id = getParaToInt(0);
        int u_id = getParaToInt("u_id");
        reservationService.deleteReservation(r_id);
        redirect("/reserve/showReservation/"+u_id);
    }
    //查询预订记录
    public void showReservation(){
        int id = getParaToInt();
        List<ReservationModel> reservationModel = reservationService.findAllReservation(id);
        setAttr("allReservation",reservationModel);
        render("aboutMe.html");
    }
}
