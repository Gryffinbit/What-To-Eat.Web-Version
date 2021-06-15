package servlet.admin.foodManage;

import com.alibaba.fastjson.JSON;
import service.impl.PublicFoodsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

@WebServlet("/admin/foodManage/delete")
public class deleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Hashtable ret = new Hashtable();
        ret.put("code", 500);
        ret.put("msg", "删除失败");
        try{
            int fid = Integer.parseInt(request.getParameter("fid"));
            PublicFoodsServiceImpl pbFdServiceImpl = new PublicFoodsServiceImpl();
            if(pbFdServiceImpl.deleteFoodById(fid)){
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