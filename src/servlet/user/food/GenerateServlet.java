package servlet.user.food;

import com.alibaba.fastjson.JSON;
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

@WebServlet("/food/generate")
public class GenerateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Hashtable ret = new Hashtable();
        Hashtable data = new Hashtable();
        ret.put("code", 500);
        ret.put("msg", "生成失败");
        try {
            String area = request.getParameter("area");
            String menu = request.getParameter("menu");
            int minPrice = Integer.parseInt(request.getParameter("minPrice"));
            int maxPrice = Integer.parseInt(request.getParameter("maxPrice"));
            int minNum = Integer.parseInt(request.getParameter("minNum"));
            int maxNum = Integer.parseInt(request.getParameter("maxNum"));
            int uid = (int) request.getSession().getAttribute("uid");
            int retFid = -1;
            if (null != menu && menu.equals("public"))
                retFid = new PublicFoodsServiceImpl().generate(uid, area, minPrice, maxPrice, minNum, maxNum);
            else if (null != menu && menu.equals("private")) {
                retFid = new PrivateFoodsServiceImpl().generate(area, minPrice, maxPrice, minNum, maxNum);
            }
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
