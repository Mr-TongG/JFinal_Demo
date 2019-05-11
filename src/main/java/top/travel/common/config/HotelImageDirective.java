package top.travel.common.config;

import com.jfinal.kit.HttpKit;
import com.jfinal.template.Directive;
import com.jfinal.template.Env;
import com.jfinal.template.io.Writer;
import com.jfinal.template.stat.Scope;
//自定义action指令
public class HotelImageDirective extends Directive {

    @Override
    public void exec(Env env, Scope scope, Writer writer) {
        String action = exprList.eval(scope).toString();
        System.out.println(action);
        String actionResult = HttpKit.get("http://localhost:8082/image/getHotelImage/" + action);
        write(writer, actionResult);
    }
}
