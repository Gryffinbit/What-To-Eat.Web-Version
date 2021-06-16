package servlet.admin;


import com.alibaba.fastjson.JSON;
import entity.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

@WebServlet("/admin/login")
public class login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        Users user = new Users();
        Hashtable ret = new Hashtable();
        ret.put("code",500);
        ret.put("msg","添加失败");
//        if()

        resp.setContentType("application/json; charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.write(JSON.toJSONString(ret));
    }

}
