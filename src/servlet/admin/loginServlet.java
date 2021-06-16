package servlet.admin;

import com.alibaba.fastjson.JSON;
import dao.impl.UsersDaoImpl;
import entity.Users;
import service.impl.AdminUsersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

@WebServlet("/admin/login")
public class loginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Hashtable ret = new Hashtable();
        ret.put("code", 403);
        ret.put("msg", "登录失败");
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        if (new AdminUsersServiceImpl().adminLogin(userName, email, password)) {
            Users user = new UsersDaoImpl().getUserByEmail(email) != null ? new UsersDaoImpl().getUserByEmail(email) : new UsersDaoImpl().getUserByName(userName);
            HttpSession session = req.getSession();
            session.setAttribute("login", true);
            session.setAttribute("uid",user.getUid());
            session.setAttribute("isAdmin", true);
            session.setAttribute("userName", user.getUserName());
            ret.put("code", 200);
            ret.put("msg", "登录成功");
        }
        resp.setContentType("application/json; charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.write(JSON.toJSONString(ret));
    }

}
