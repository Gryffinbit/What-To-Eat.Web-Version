package servlet.user;

import com.alibaba.fastjson.JSON;
import entity.Users;
import service.impl.AdminUsersServiceImpl;
import utils.SHA256;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

@WebServlet("/user/modify")
public class ModifyServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Users user = new Users();
        Hashtable ret = new Hashtable();
        ret.put("code",500);
        ret.put("msg","修改失败");

        int uid = Integer.parseInt(request.getSession().getAttribute("uid").toString());
        user.setUserName(request.getParameter("userName"));
        user.setEmail(request.getParameter("email"));

        AdminUsersServiceImpl adminServiceImpl = new AdminUsersServiceImpl();
        if(adminServiceImpl.modifyUsers(uid, user)){
            ret.put("code",200);
            ret.put("msg","修改成功");
        }
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(JSON.toJSONString(ret));
    }
}
