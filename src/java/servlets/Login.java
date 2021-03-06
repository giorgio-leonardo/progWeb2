/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import beans.User;
import database.DbManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import support.Utils;

/**
 *
 * @author giorgio
 */
public class Login extends HttpServlet
{

    private DbManager manager;
    
    public void init(ServletConfig config) throws ServletException
    {
        super.init(config);
        this.manager = (DbManager)super.getServletContext().getAttribute("dbmanager");
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        String un;
        String up;
        String btn = request.getParameter("btn");
        
        un = request.getParameter("userName");
        up = request.getParameter("userPwd");
        
        
        if (btn.equals("login"))
        {
            try
            {
                User u = manager.authenticate(un, up);
                if (u == null)
                {
                    forward(request, response, "/errorPages/loginError.jsp");
                }
                else
                {
                    HttpSession session = request.getSession(true);
                    manager.updateLastAccessTime(u.getUserID(), session.getCreationTime());
                    Utils.userStatus = "0001";
                    session.setAttribute("user", u);
                    session.setAttribute("userStatus", Utils.userStatus);
                    request.setAttribute("manager", manager);
                    forward(request, response, "/LandingPage");
                }
            }
            catch (SQLException e)
            {
                Utils.toLog(this.getClass().toString() + "\n\t" + e.getLocalizedMessage(), "e");
            }
        }
        else
        {
            forward(request, response, "/registrationPage.jsp");
        }
    }

    private void forward(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException
    {
        ServletContext sc = getServletContext();
        RequestDispatcher rd = sc.getRequestDispatcher(page);
        rd.forward(request,response);
    }
}
