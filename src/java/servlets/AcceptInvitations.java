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
public class AcceptInvitations extends HttpServlet
{

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession(false);
        DbManager manager = (DbManager)request.getAttribute("manager");
        
        User user = (User)session.getAttribute("user");
        String[] chk = request.getParameterValues("chk");
        
        if (chk != null)
        {
            for(String s: chk)
            {
                Integer i = Integer.parseInt(s);
                try
                {
                    manager.insertUserInGroup(user.getUserID(), i, false);
                    manager.removeUserFromInvitation(user.getUserID(), i);
                }
                catch (SQLException e)
                {
                    Utils.toLog(getClass().getName() + ":\n" + e.getLocalizedMessage(), "e");
                }
            }
        }
        
        ServletContext sc = getServletContext();
        RequestDispatcher rd = sc.getRequestDispatcher("/LandingPage");
        rd.forward(request,response);

    }
   
}
