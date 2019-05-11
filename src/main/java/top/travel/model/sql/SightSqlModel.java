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

    public boolean updateById(int s_id, String s_name, float s_score, String s_location, String s_introduce,
                              String s_openTime, String s_phone, String s_closeTime){
        SightModel.set("s_id",s_id);
        SightModel.set("s_name",s_name);
        SightModel.set("s_score",s_score);
        SightModel.set("s_location",s_location);
        SightModel.set("s_introduce",s_introduce);
        SightModel.set("s_openTime",s_openTime);
        SightModel.set("s_phone",s_phone);
        SightModel.set("s_id",s_id);
        SightModel.set("s_closeTime",s_closeTime);
        return SightModel.update();
    }

    public boolean insertSight(int s_id, String s_name, float s_score, String s_location, String s_introduce,
                               String s_openTime, String s_phone, String s_closeTime){
        SightModel.set("s_id",s_id);
        SightModel.set("s_name",s_name);
        SightModel.set("s_score",s_score);
        SightModel.set("s_location",s_location);
        SightModel.set("s_introduce",s_introduce);
        SightModel.set("s_openTime",s_openTime);
        SightModel.set("s_phone",s_phone);
        SightModel.set("s_id",s_id);
        SightModel.set("s_closeTime",s_closeTime);
        return SightModel.save();
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
}