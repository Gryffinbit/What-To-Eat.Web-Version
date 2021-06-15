package servlet.admin.foodManage;

import com.alibaba.fastjson.JSON;
import entity.PublicFoods;
import service.impl.PublicFoodsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Hashtable;

@WebServlet("/admin/foodManage/add")
public class AddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PublicFoods food = new PublicFoods();
        Hashtable ret = new Hashtable();
        ret.put("code",500);
        ret.put("msg","添加失败");
        if(setPrim(food, request)){
            PublicFoodsServiceImpl pbFdServiceImpl = new PublicFoodsServiceImpl();
            if(pbFdServiceImpl.addFood(food)){
                ret.put("code",200);
                ret.put("msg","添加成功");
            }
        }
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(JSON.toJSONString(ret));
    }

    public static boolean setPrim(PublicFoods food, HttpServletRequest request) {
        try {
            String foodName = request.getParameter("foodName");
            String area = request.getParameter("area");
            int minNum = Integer.parseInt(request.getParameter("minNum"));
            int maxNum = Integer.parseInt(request.getParameter("maxNum"));
            int minPrice = Integer.parseInt(request.getParameter("minPrice"));
            int maxPrice = Integer.parseInt(request.getParameter("maxPrice"));
            // int submitter = (int) request.getSession().getAttribute("uid");
            int submitter = 1;
            food.setFoodName(foodName);
            food.setArea(area);
            food.setMinNum(minNum);
            food.setMaxNum(maxNum);
            food.setMinPrice(minPrice);
            food.setMaxPrice(maxPrice);
            food.setSubmitter(submitter);
            food.setModifyTime(new Timestamp(System.currentTimeMillis()));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
