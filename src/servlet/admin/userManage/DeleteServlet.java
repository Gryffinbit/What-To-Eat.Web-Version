package servlet.admin.userManage;

import com.alibaba.fastjson.JSON;
import service.impl.AdminUsersServiceImpl;
import service.impl.PublicFoodsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

@WebServlet("/admin/userManage/delete")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Hashtable ret = new Hashtable();
        ret.put("code", 500);
        ret.put("msg", "删除失败");
        try{
            int uid = Integer.parseInt(request.getParameter("uid"));
            AdminUsersServiceImpl adminServiceImpl = new AdminUsersServiceImpl();
            if(adminServiceImpl.deleteUsers(uid)){
                ret.put("code", 200);
                ret.put("msg", "成功");
            }
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(JSON.toJSONString(ret));
    }
}
