/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package filters;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import support.Utils;

/**
 *
 * @author giorgio
 */
public class LoginFilter implements Filter
{
    
    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
        this.filterConfig = filterConfig;
        if (filterConfig != null)
        {
            Utils.toLog("LoginFilter:Initializing filter", "i");
        }
    }
    
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;
        HttpSession session = req.getSession(false);
        
        Utils.toLog(req.getServletPath(), "i");
        if ( (!req.getServletPath().equals("/index.html")) && (!req.getServletPath().equals("/Login")) &&
              !req.getServletPath().equals("/ControlServlet") && (!req.getServletPath().endsWith("css")) &&
              (!req.getServletPath().endsWith("js")) && (!req.getServletPath().equals("/GotoGroup")) &&
              (!req.getServletPath().equals("/Logout")));
        {
            if (session == null || session.getAttribute("user") == null)
            {
                res.sendRedirect(req.getContextPath());
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy()
    {
        filterConfig = null;
    }
}
