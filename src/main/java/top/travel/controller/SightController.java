package top.travel.controller;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import top.travel.model.SightCommModel;
import top.travel.model.SightModel;
import top.travel.service.CommentService;
import top.travel.service.SightService;


public class SightController extends Controller {
    SightService sightService = new SightService();
    private final int pageSize = 3;      //每页数据条数
    CommentService commentService = new CommentService();
    //点击一个景点，会进入该景点的详细页面
    public void showSight(){
        int id = getParaToInt();
        SightModel sight = sightService.findById(id);
        setAttr("sight",sight);
        //setAttr("sightCommentPage",sightCommModelPage);
        render("sight.html");
    }
    //在主页点击景点时
    public void InitSight(){
        String keywords="";
        String condition = "景点";
        Page<SightModel> sights = sightService.paginate(getParaToInt(0,1),pageSize,keywords);
        setAttr("sightPage",sights);
        setSessionAttr("conditions",condition);//页面重用的时候防止出错
        setSessionAttr("keywords",keywords);
        render("searchOfSight.html");
    }
    //后台插入景点
    public void insertSight(){
        String s_name = getPara("s_name");
        String s_phone= getPara("s_phone");
        String s_location = getPara("s_location");
        String s_openTime = getPara("s_openTime");
        String s_closeTime = getPara("s_closeTime");
        String s_introduction = getPara("s_introduction");
        sightService.insertSight(s_name,s_location,s_openTime,s_phone,s_introduction,s_closeTime);
        redirect("/backStage/sightManage");
    }
    //后台删除景点
    public void deleteSight(){
        int id = getParaToInt();
        sightService.deleteById(id);
        redirect("/backStage/sightManage");
    }
    //后台更新景点
    public void updateSight(){
        int s_id = getParaToInt("s_id");
        String s_name = getPara("s_name");
        String s_phone= getPara("s_phone");
        String s_location = getPara("s_location");
        String s_openTime = getPara("s_openTime");
        String s_closeTime = getPara("s_closeTime");
        String s_introduction = getPara("s_introduction");
        sightService.updateById(s_id,s_name,s_location,s_openTime,s_phone,s_introduction,s_closeTime);
        redirect("/backStage/sightManage");
    }
}
