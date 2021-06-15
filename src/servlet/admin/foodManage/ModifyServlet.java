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
import java.util.Hashtable;

@WebServlet("/admin/foodManage/modify")
public class ModifyServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PublicFoods food = new PublicFoods();
        Hashtable ret = new Hashtable();
        ret.put("code",500);
        ret.put("msg","修改失败");
        try{
            int fid = Integer.parseInt(request.getParameter("fid"));
            if(AddServlet.setPrim(food, request)){
                PublicFoodsServiceImpl pbFdServiceImpl = new PublicFoodsServiceImpl();
                if(pbFdServiceImpl.modifyFood(fid, food)){
                    ret.put("code",200);
                    ret.put("msg","修改成功");
                }
            }
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(JSON.toJSONString(ret));
    }
}
