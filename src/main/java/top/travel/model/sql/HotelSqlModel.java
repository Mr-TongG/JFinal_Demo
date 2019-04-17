package top.travel.model.sql;

import top.travel.model.HotelModel;

import java.util.List;

public class HotelSqlModel {
    private static final HotelModel hotelModel = new HotelModel();

    public HotelModel getHotelModel(){
        return hotelModel;
    }

    public HotelModel findById(int id){
        return hotelModel.findById(id);
    }
    public HotelModel findFirst(int id){
        return hotelModel.findFirst("select * from hotel where h_id ="+id);
    }
    public List<HotelModel> findAll(){
        return hotelModel.findAll();
    }

    public boolean updateById(int h_id, String h_name, int h_price, String h_location, int h_grade,
                                 float h_score, String h_introduction, int s_id, String h_phone){
        hotelModel.set("h_id",h_id);
        hotelModel.set("h_name",h_name);
        hotelModel.set("h_price",h_price);
        hotelModel.set("h_location",h_location);
        hotelModel.set("h_grade",h_grade);
        hotelModel.set("h_score",h_score);
        hotelModel.set("h_introduction",h_introduction);
        hotelModel.set("s_id",s_id);
        hotelModel.set("h_phone",h_phone);
        return hotelModel.update();
    }

    public boolean insertHotel(int h_id, String h_name, int h_price, String h_location, int h_grade,
                               float h_score, String h_introduction, int s_id, String h_phone){
        hotelModel.set("h_id",h_id);
        hotelModel.set("h_name",h_name);
        hotelModel.set("h_price",h_price);
        hotelModel.set("h_location",h_location);
        hotelModel.set("h_grade",h_grade);
        hotelModel.set("h_score",h_score);
        hotelModel.set("h_introduction", h_introduction);
        hotelModel.set("s_id", s_id);
        hotelModel.set("h_phone",h_phone);
        return hotelModel.save();
    }

   public boolean deleteHotel(int id){
        return hotelModel.deleteById(id);
   }
   //搜索名字中包含condition的酒店
   public List<HotelModel> searchHotel(String condition){
        return hotelModel.find("select * from hotel where h_name like %",condition+"%");
   }

}
