package top.travel.controller;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import top.travel.model.SightModel;
import top.travel.service.SightService;


public class SightController extends Controller {
    SightService sightService = new SightService();
    private final int pageSize = 3;      //每页数据条数
    //点击一个景点，会进入该景点的详细页面
    public void showSight(){
        int id = Integer.parseInt(getPara());   //需要进行数据回显 在url中得到id值
        SightModel sight = sightService.findById(id);
        setAttr("sight",sight);
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
}
