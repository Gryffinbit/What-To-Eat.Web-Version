package servlet.user;

import com.alibaba.fastjson.JSON;
import dao.impl.UsersDaoImpl;
import entity.Users;
import service.impl.AdminUsersServiceImpl;
import service.impl.UsersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Hashtable ret = new Hashtable();
        ret.put("code", 403);
        ret.put("msg", "登录失败");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        if (new UsersServiceImpl().login(userName, email, password)) {
            Users user = new UsersDaoImpl().getUserByEmail(email) != null ? new UsersDaoImpl().getUserByEmail(email) : new UsersDaoImpl().getUserByName(userName);
            HttpSession session = request.getSession();
            session.setAttribute("login", true);
            System.out.println(user.getUid());
            session.setAttribute("uid",user.getUid());
            session.setAttribute("isAdmin", false);
            session.setAttribute("userName", user.getUserName());
            ret.put("code", 200);
            ret.put("msg", "登录成功");
        }
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(JSON.toJSONString(ret));
    }
}
