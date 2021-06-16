package filter;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;


@WebFilter(filterName = "CharacterEncodingFilter", urlPatterns = {"/*"})
public class CharacterEncodingFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("CharacterFilter");
        request.setCharacterEncoding("utf-8");
        ((HttpServletRequest)request).getSession().setAttribute("uid", 1);
        chain.doFilter(request, response);
    }
}
