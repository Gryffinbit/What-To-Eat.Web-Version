package filter;

import com.alibaba.fastjson.JSON;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

//@WebFilter(filterName = "UserLoginFilter", urlPatterns = {"/*"})
public class UserLoginFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("loginFilter");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        try{
            if (null != req.getSession().getAttribute("login") && (boolean)req.getSession().getAttribute("login")) {
                chain.doFilter(request, response);
                return;
            }
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        Hashtable ret = new Hashtable();
        ret.put("code", 403);
        ret.put("msg", "未登录");
        res.setContentType("application/json; charset=utf-8");
        PrintWriter writer = res.getWriter();
        writer.write(JSON.toJSONString(ret));
    }
}
