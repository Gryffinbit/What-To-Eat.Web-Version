package servlet.admin.foodManage;

import service.impl.PublicFoodsServiceImpl;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

@WebServlet("/admin/foodManage/showAll")
public class ShowAllServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PublicFoodsServiceImpl pbFdServiceImpl = new PublicFoodsServiceImpl();
        Hashtable ret = new Hashtable();
        ret.put("code", 200);
        ret.put("msg", "查询成功");
        ret.put("data", pbFdServiceImpl.getAllVerifiedFoods());
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(JSON.toJSONString(ret));
    }
}
