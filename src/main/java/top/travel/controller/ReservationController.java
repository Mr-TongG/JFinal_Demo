package top.travel.controller;

import com.jfinal.core.Controller;
import top.travel.model.HotelModel;
import top.travel.model.sql.HotelSqlModel;
import top.travel.service.ReservationService;

public class ReservationController extends Controller {
    ReservationService reservationService = new ReservationService();
    HotelSqlModel hotelService = new HotelSqlModel();
    //预订酒店
    public void reserveHotel(){
        //int u_id = Integer.parseInt(getPara("u_id"));
        int u_id = 2;
        int h_id = getParaToInt("h_id");
        int r_number_1 = getParaToInt("r_number_1");
        int r_number_2 = getParaToInt("r_number_2");
        int r_number = r_number_1+r_number_2;
        String r_getInTime = getPara("r_getInTime");
        String r_getOutTime = getPara("r_getOutTime");
        System.out.println(h_id);
        System.out.println(r_number);
        reservationService.insertReservation(u_id,h_id,r_number,r_getInTime,r_getOutTime);
        //再次查询信息，回到之前的页面
        HotelModel hotel = hotelService.findById(h_id);
        setAttr("hotel",hotel);
        renderFreeMarker("hotel.html");
    }
    //取消预订
    public void CancelReservation(){
        int r_id = getParaToInt();
        reservationService.deleteReservation(r_id);
        renderFreeMarker("settings.html");
    }
}
