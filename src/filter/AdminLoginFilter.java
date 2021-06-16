package filter;

import com.alibaba.fastjson.JSON;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

@WebFilter(filterName = "AdminLoginFilter", urlPatterns = {"/admin/*"})
public class AdminLoginFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("AdminLoginFilter");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        if(req.getRequestURI().toString().equals("/admin/login")){
            chain.doFilter(request, response);
            return;
        }
        try{
            if (null != req.getSession().getAttribute("isAdmin") && (boolean)req.getSession().getAttribute("isAdmin")) {
                chain.doFilter(request, response);
                return;
            }
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        Hashtable ret = new Hashtable();
        ret.put("code", 403);
        ret.put("msg", "Permission Denied");
        res.setContentType("application/json; charset=utf-8");
        PrintWriter writer = res.getWriter();
        writer.write(JSON.toJSONString(ret));
    }
}
