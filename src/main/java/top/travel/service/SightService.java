package top.travel.service;
import com.jfinal.plugin.activerecord.Page;
import top.travel.model.SightModel;
import top.travel.model.sql.SightSqlModel;

import java.util.List;

public class SightService {
    SightSqlModel SightSqlModel = new SightSqlModel();
    public SightModel findById(int id) {
        return SightSqlModel.findById(id);
    }

    public List<SightModel> findAll(){
        return SightSqlModel.findAll();
    }

    public boolean updateById(int s_id, String s_name, int s_score, String s_location, String s_openTime,
                              String s_phone, String s_introduction, String s_closeTime, String s_longitude, String s_latitude){
        return SightSqlModel.updateById(s_id, s_name,s_score, s_location, s_openTime, s_phone, s_introduction,s_closeTime,s_longitude,s_latitude);
    }

    public boolean insertHotel(int s_id, String s_name, int s_score, String s_location, String s_openTime,
                               String s_phone, String s_introduction, String s_closeTime, String s_longitude, String s_latitude){
        return SightSqlModel.insertSight(s_id,s_name,s_score, s_location, s_openTime,
                s_phone, s_introduction,s_closeTime,s_longitude,s_latitude);
    }

    public boolean deleteById(int id){
        return SightSqlModel.deleteSight(id);
    }

    public List<SightModel> searchSight(String condition){
        return SightSqlModel.searchSight(condition);
    }

    public Page<SightModel> paginate(Integer pageNumber, int pageSize, String keywords) {
        return SightSqlModel.queryBypaginate(pageNumber,pageSize,keywords);
    }
    public Page<SightModel> queryAll(int pageNumber , int pageSize){
        return SightSqlModel.queryAll(pageNumber,pageSize);
    }
}
