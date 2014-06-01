/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import beans.User;
import database.DbManager;
import java.io.IOException;
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
public class CreateGroup extends HttpServlet
{

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession(false);
                
        DbManager manager = (DbManager)request.getAttribute("manager");
        String groupsDir = (String)request.getAttribute("groupsDir");
        User user = (User)session.getAttribute("user");
        
        String[] chk = request.getParameterValues("chk");
        String groupName = request.getParameter("groupName");
        String groupIsPrivate = request.getParameter("groupIsPrivate");
        Boolean gip = false;
        
        if (groupIsPrivate != null)
        {   
            gip = true;
        }
        
        String groupsPath = getServletContext().getRealPath("/WEB-INF") + "/" + groupsDir;
        
        Utils.createGroupFolder(groupsPath, groupName);
        try
        {
            manager.insertNewGroup(user.getUserID(), groupName, gip);
            Integer groupID = manager.getGroupIDFromGroupName(groupName);
            manager.insertUserInGroup(user.getUserID(), groupID, true);
            
            if (chk != null)
            {
                manager.insertInvitations(groupID, chk);
            }
        }
        catch (SQLException e)
        {
            Utils.toLog(this.getClass().toString() + "\n\t" + e.getLocalizedMessage(), "e");
        }
        
        ServletContext sc = getServletContext();
        RequestDispatcher rd = sc.getRequestDispatcher("/LandingPage");
        rd.forward(request,response);

    }

}
