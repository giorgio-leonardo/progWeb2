/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import beans.Group;
import beans.Invitation;
import beans.User;
import database.DbManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class LandingPage extends HttpServlet
{
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession(false);
        User user = (User)session.getAttribute("user");
        if (session.getAttribute("group") != null)
        {
            session.removeAttribute("group");
        }
        
        
        DbManager manager = (DbManager)request.getAttribute("manager");
        try
        {
            List<Invitation> invitations = manager.getUserInvitation(user.getUserID());
            request.setAttribute("invitations", invitations);
        } 
        catch (SQLException e)
        {
            Utils.toLog(this.getClass().toString() + "\n\t" + e.getLocalizedMessage(), "e");
        }
        catch (NullPointerException ne)
        {
            request.setAttribute("invitations", null);
        }
        
        ServletContext sc = getServletContext();
        RequestDispatcher rd = sc.getRequestDispatcher("/landingPage.jsp");
        rd.forward(request,response);
    }
}
