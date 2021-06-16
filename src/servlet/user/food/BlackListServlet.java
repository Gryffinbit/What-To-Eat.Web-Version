package servlet.user.food;

import com.alibaba.fastjson.JSON;
import service.impl.BlackListFoodsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

@WebServlet("/food/blacklist")
public class BlackListServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Hashtable ret = new Hashtable();
        ret.put("code",500);
        ret.put("msg","添加失败");
        try{
            int uid = Integer.parseInt(request.getSession().getAttribute("uid").toString());
            int fid = Integer.parseInt(request.getParameter("fid"));
            System.out.println(uid);
            BlackListFoodsServiceImpl blkListFdServiceImpl = new BlackListFoodsServiceImpl();
            if(blkListFdServiceImpl.add(uid, fid)){
                ret.put("code",200);
                ret.put("msg","添加成功");
            }
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(JSON.toJSONString(ret));
    }
}
