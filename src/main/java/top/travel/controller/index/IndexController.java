package top.travel.controller.index;
import com.jfinal.core.Controller;

import top.travel.util.MySession;

/**
 * IndexController 指向系统访问首页
 * @author jbolt.cn and Liao
 * @date 2019年3月1日
 */
public class IndexController extends Controller {
	/**
	 * 首页Action
	 * 
	 */
	public void index() {
		
		if(getSessionAttr(MySession.getSessionKey()) != null){
			//代表标识session 跳转页面 并提示 请先登陆
			setAttr("msg", "请先登陆~");
			renderFreeMarker("index.html");
		}else{
			render("index.html");
		}
	}
}