package top.travel.model.sql;

import com.jfinal.plugin.activerecord.Page;
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
    public HotelModel findByName(String name){ return hotelModel.findFirst("select * from hotel where h_name ='"+name+"'");}
    public List<HotelModel> findAll(){
        return hotelModel.findAll();
    }

    public boolean updateById(int h_id,String h_name, int h_price, String h_location, int h_grade,
                                 String h_introduction, String h_phone){
        return hotelModel.findById(h_id)
        .set("h_name",h_name)
        .set("h_price",h_price)
        .set("h_location",h_location)
        .set("h_grade",h_grade)
        .set("h_introduction",h_introduction)
        .set("h_phone",h_phone).update();
    }
    //插入的时候记得创建新的对象！！
    public boolean insertHotel(String h_name, int h_price, String h_location, int h_grade,
                               String h_introduction, String h_phone){
        return new HotelModel()
        .set("h_name",h_name)
        .set("h_price",h_price)
        .set("h_location",h_location)
        .set("h_grade",h_grade)
        .set("h_introduction", h_introduction)
        .set("h_phone",h_phone)
        .save();
    }

   public boolean deleteHotel(int id){
        return hotelModel.deleteById(id);
   }
   //搜索名字中包含condition的酒店
   public List<HotelModel> searchHotel(String keywords){
        return hotelModel.find("select * from hotel where h_name like '%" + keywords + "%'");
   }
   //分页查询名字中包含keywords的酒店
    public Page<HotelModel> queryBypaginate(int pageNumber , int pageSize , String keywords){
        return hotelModel.paginate(pageNumber,pageSize,"select *","from hotel where h_name like ?","%"+keywords+"%");
    }
    public Page<HotelModel> queryAll(int pageNumber , int pageSize){
        return hotelModel.paginate(pageNumber,pageSize,"select *","from hotel");
    }
}
