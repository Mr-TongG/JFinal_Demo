package top.travel.service;

import com.jfinal.plugin.activerecord.Page;
import top.travel.model.HotelModel;
import top.travel.model.sql.HotelSqlModel;

import java.util.List;

public class HotelService {
    HotelSqlModel hotelSqlModel = new HotelSqlModel();

    public HotelModel findById(int id) {
        return hotelSqlModel.findById(id);
    }
    public HotelModel findByName(String name){ return hotelSqlModel.findByName(name);}
    public List<HotelModel> findAll(){
        return hotelSqlModel.findAll();
    }

    public boolean updateById(int h_id, String h_name, int h_price, String h_location, int h_grade,
                              String h_introduction, String h_phone){
        return hotelSqlModel.updateById(h_id, h_name, h_price, h_location, h_grade, h_introduction, h_phone);
    }

    public boolean insertHotel( String h_name, int h_price, String h_location, int h_grade,
                               String h_introduction, String h_phone){
        return hotelSqlModel.insertHotel(h_name, h_price, h_location, h_grade, h_introduction,h_phone);
    }

    public boolean deleteById(int id){
        return hotelSqlModel.deleteHotel(id);
    }

    public List<HotelModel> searchHotel(String keywords){
        return hotelSqlModel.searchHotel(keywords);
    }
    public Page<HotelModel> paginate(int pageNumber, int pageSize, String keywords){
        return hotelSqlModel.queryBypaginate(pageNumber,pageSize,keywords);

    }
    public Page<HotelModel> queryAll(int pageNumber, int pageSize){
        return hotelSqlModel.queryAll(pageNumber,pageSize);
    }
}
