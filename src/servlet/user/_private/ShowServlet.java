package servlet.user._private;

import com.alibaba.fastjson.JSON;
import service.impl.PrivateFoodsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

@WebServlet("/menu/private/food/show")
public class ShowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Hashtable ret = new Hashtable();
        ret.put("code", 500);
        ret.put("msg", "查询失败");
        try {
            int fid = Integer.parseInt(request.getParameter("fid"));
            PrivateFoodsServiceImpl priFdServiceImpl = new PrivateFoodsServiceImpl();
            ret.put("code", 200);
            ret.put("msg", "查询成功");
            ret.put("data", priFdServiceImpl.getFoodById(fid));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(JSON.toJSONString(ret));
    }
}
