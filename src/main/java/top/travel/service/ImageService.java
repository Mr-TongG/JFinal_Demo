package top.travel.service;

import top.travel.model.HotelImageModel;
import top.travel.model.SightImageModel;
import top.travel.model.sql.ImageSqlModel;

import java.util.List;

public class ImageService {
    public ImageSqlModel imageSqlModel = new ImageSqlModel();
    //根据景点ID找到所有该景点的图片
    public List<SightImageModel> findAnSightImage(int id){
        return imageSqlModel.findAnSightImage(id);
    }
    //根据酒店ID找到所有该酒店的图片
    public List<HotelImageModel> findAnHotelImage(int id){
        return imageSqlModel.findAnHotelImage(id);
    }
}
