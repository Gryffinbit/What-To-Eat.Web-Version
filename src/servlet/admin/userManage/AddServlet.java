package servlet.admin.userManage;

import com.alibaba.fastjson.JSON;
import entity.PublicFoods;
import entity.Users;
import service.impl.AdminUsersServiceImpl;
import service.impl.PublicFoodsServiceImpl;
import utils.FoodTools;
import utils.SHA256;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Hashtable;

@WebServlet("/admin/userManage/add")
public class AddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Users user = new Users();
        Hashtable ret = new Hashtable();
        ret.put("code",500);
        ret.put("msg","添加失败");
        user.setUserName(request.getParameter("userName"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(new SHA256(request.getParameter("password")).toString());
        user.setAdmin(Boolean.parseBoolean(request.getParameter("isAdmin")));
        user.setRegTime(new Timestamp(System.currentTimeMillis()));

        AdminUsersServiceImpl adminServiceImpl = new AdminUsersServiceImpl();
        if(adminServiceImpl.addUsers(user)){
            ret.put("code",200);
            ret.put("msg","添加成功");
        }
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(JSON.toJSONString(ret));
    }
}
