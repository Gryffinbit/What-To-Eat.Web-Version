package servlet.user.food;

import com.alibaba.fastjson.JSON;
import service.impl.OrderHistoryServiceImpl;
import service.impl.PrivateFoodsServiceImpl;
import service.impl.PublicFoodsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

@WebServlet("/food/recommend")
public class RecommendServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Hashtable ret = new Hashtable();
        Hashtable data = new Hashtable();
        ret.put("code", 500);
        ret.put("msg", "生成失败");
        try {
            int uid = (int) request.getSession().getAttribute("uid");
            int retFid = new OrderHistoryServiceImpl().recommend(uid);
            if (-1 != retFid) {
                data.put("fid", retFid);
                ret.put("code", 200);
                ret.put("msg", "生成成功");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        ret.put("data", data);
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(JSON.toJSONString(ret));
    }
}
