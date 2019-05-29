package top.travel.controller;

import com.jfinal.core.Controller;
import top.travel.model.HotelModel;
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
        int r_number_1 = getParaToInt("r_number_1");
        int r_number_2 = getParaToInt("r_number_2");
        int r_number = r_number_1+r_number_2;
        String r_getInTime = getPara("r_getInTime");
        String r_getOutTime = getPara("r_getOutTime");
        //System.out.println(h_id);
        //System.out.println(r_number);
        reservationService.insertReservation(u_id,h_id,r_number,r_getInTime,r_getOutTime);
        //再次查询信息，回到之前的页面
        HotelModel hotel = hotelService.findById(h_id);
        setAttr("hotel",hotel);
        render("showHotel.html");
    }
    //取消预订
    public void CancelReservation(){
        int r_id = getParaToInt();
        reservationService.deleteReservation(r_id);
        showReservation();
    }
    //查询预订记录
    public void showReservation(){
        int id = getParaToInt();
        List<ReservationModel> reservationModel = reservationService.findAllReservation(id);
        setAttr("allReservation",reservationModel);
        render("aboutMe.html");
    }
}
