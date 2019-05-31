package top.travel.model.sql;

import com.jfinal.plugin.activerecord.Page;
import top.travel.model.SightModel;

import java.util.List;

public class SightSqlModel {
    private static final SightModel SightModel = new SightModel();

    public SightModel SightModel(){
        return SightModel;
    }

    public SightModel findById(int id){
        return SightModel.findById(id);
    }

    public List<SightModel> findAll(){
        return SightModel.findAll();
    }

    public boolean updateById(int s_id, String s_name, String s_location, String s_introduction,
                              String s_openTime, String s_phone, String s_closeTime){
        return SightModel.findById(s_id).set("s_id",s_id)
        .set("s_name",s_name)
        .set("s_location",s_location)
        .set("s_introduction",s_introduction)
        .set("s_openTime",s_openTime)
        .set("s_phone",s_phone)
        .set("s_id",s_id)
        .set("s_closeTime",s_closeTime).update();
    }

    public boolean insertSight(String s_name, String s_location, String s_introduction,
                               String s_openTime, String s_phone, String s_closeTime){
        return new SightModel()
                .set("s_name",s_name)
                .set("s_location",s_location)
                .set("s_introduction",s_introduction)
                .set("s_openTime",s_openTime)
                .set("s_phone",s_phone)
                .set("s_closeTime",s_closeTime)
                .save();
    }

    public boolean deleteSight(int id){
        return SightModel.deleteById(id);
    }
    //搜索名字中包含condition的景点
    public List<SightModel> searchSight(String condition){
        return SightModel.find("select * from sight where s_name like %",condition+"%");
    }
    public Page<SightModel> queryBypaginate(int pageNumber , int pageSize , String keywords){
        return SightModel.paginate(pageNumber,pageSize,"select *","from sight where s_name like ?","%"+keywords+"%");
    }
    public Page<SightModel> queryAll(int pageNumber , int pageSize){
        return SightModel.paginate(pageNumber,pageSize,"select *","from sight");
    }
}