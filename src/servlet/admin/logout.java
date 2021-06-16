package servlet.admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/admin/login")
public class logout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");
        PrintWriter out = resp.getWriter();
        req.setCharacterEncoding("UTF-8");
        req.getRequestDispatcher("").include(req, resp);
        HttpSession session = req.getSession();
        // 清除数据
        session.invalidate();
        //成功注销
        out.close();

    }
}
