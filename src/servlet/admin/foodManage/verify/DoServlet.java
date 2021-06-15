package servlet.admin.foodManage.verify;

import com.alibaba.fastjson.JSON;
import entity.PublicFoods;
import service.impl.PublicFoodsServiceImpl;
import servlet.admin.foodManage.AddServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

@WebServlet("/admin/foodManage/verify/do")
public class DoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PublicFoods food = new PublicFoods();
        Hashtable ret = new Hashtable();
        ret.put("code",500);
        ret.put("msg","审核失败");
        try{
            int fid = Integer.parseInt(request.getParameter("fid"));
            boolean verify = Boolean.parseBoolean(request.getParameter("verify"));
            PublicFoodsServiceImpl pbFdServiceImpl = new PublicFoodsServiceImpl();
            if(pbFdServiceImpl.doVerify(fid, verify)){
                ret.put("code",200);
                ret.put("msg","审核成功");
            }
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(JSON.toJSONString(ret));
    }
}
