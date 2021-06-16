package servlet.user;

import com.alibaba.fastjson.JSON;
import service.impl.AdminUsersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

@WebServlet("/user/info")
public class InfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Hashtable ret = new Hashtable();
        ret.put("code", 500);
        ret.put("msg", "查询失败");
        try {
            int uid = Integer.parseInt(request.getSession().getAttribute("uid").toString());
            AdminUsersServiceImpl adminServiceImpl = new AdminUsersServiceImpl();
            System.out.println("info: "+request.getSession().getAttribute("uid").toString());
            ret.put("code", 200);
            ret.put("msg", "查询成功");
            ret.put("data", adminServiceImpl.getUserById(uid));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(JSON.toJSONString(ret));
    }
}
