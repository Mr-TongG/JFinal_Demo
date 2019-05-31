package top.travel.model.sql;

import top.travel.model.HotelImageModel;
import top.travel.model.SightImageModel;

import java.util.List;

public class ImageSqlModel {
    private static final SightImageModel sightImageModel = new SightImageModel();
    private static final HotelImageModel hotelImageModel = new HotelImageModel();

    //根据景点ID找到所有该景点的图片
    public List<SightImageModel> findAnSightImage(int id){
        return sightImageModel.find("select * from sight_image inner join sight on sight.s_id = sight_image.s_id where sight_image.s_id =?",id);
    }

    //根据酒店ID找到所有该酒店的图片
    public List<HotelImageModel> findAnHotelImage(int id){
        return hotelImageModel.find("select * from hotel_image inner join hotel on hotel.h_id = hotel_image.h_id where hotel_image.h_id =?",id);
    }
    public boolean insertHotelImage(String h_image, int h_id){
        return new HotelImageModel()
                .set("h_image",h_image)
                .set("h_id",h_id)
                .save();
    }
    public boolean insertSightImage(String s_image, int s_id){
        return new SightImageModel()
                .set("s_image",s_image)
                .set("s_id",s_id)
                .save();
    }
    public boolean deleteHotelImage(int id){
        return hotelImageModel.deleteById(id);
    }
    public boolean deleteSightImage(int id){
        return sightImageModel.deleteById(id);
    }
}
