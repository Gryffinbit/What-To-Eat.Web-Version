package servlet.user._private;

import com.alibaba.fastjson.JSON;
import entity.PrivateFoods;
import service.impl.PrivateFoodsServiceImpl;
import utils.FoodTools;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

@WebServlet("/menu/private/food/add")
public class AddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrivateFoods food = new PrivateFoods();
        Hashtable ret = new Hashtable();
        ret.put("code",500);
        ret.put("msg","添加失败");
        if(FoodTools.setPriFoodPrimByReq(food, request)){
            PrivateFoodsServiceImpl pbPriServiceImpl = new PrivateFoodsServiceImpl();
            if(pbPriServiceImpl.addFood(food)){
                ret.put("code",200);
                ret.put("msg","添加成功");
            }
        }
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(JSON.toJSONString(ret));
    }
}
