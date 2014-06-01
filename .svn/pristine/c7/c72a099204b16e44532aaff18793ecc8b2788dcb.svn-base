/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import beans.Group;
import com.sun.media.sound.SoftEnvelopeGenerator;
import database.DbManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Enumeration;
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
public class ModifyGroup extends HttpServlet
{

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        Group group = (Group)session.getAttribute("group");
        
        DbManager manager = (DbManager)request.getAttribute("manager");
        String groupsDir =(String)request.getAttribute("groupsDir");
        
        String groupIsOpen = ((request.getParameter("groupIsOpen") == null) ? "false" : "true");
        String groupIsPrivate = ((request.getParameter("groupIsPrivate") == null) ? "false" : "true");
        String groupName = request.getParameter("groupName");
        String oldName = request.getParameter("groupOldName");
        String[] chk = ((request.getParameterValues("chk") == null) ? new String[0] : request.getParameterValues("chk"));

        for (String s: chk)
        {
            Utils.toLog(s, "i");
        }
        
        Integer groupID = Integer.parseInt(request.getParameter("groupID"));

        try
        {
            manager.updateGroup(groupID, groupName, groupIsPrivate, groupIsOpen, chk);
            String path = getServletContext().getRealPath("/WEB-INF") + "/" + groupsDir;
            Utils.modifyGroupFolder(path, oldName, groupName);
        }
        catch (SQLException e)
        {
            Utils.toLog(getClass().getName() + ":\n" + e.getLocalizedMessage(), "e");
        }
        
        ServletContext sc = getServletContext();
        RequestDispatcher rd = sc.getRequestDispatcher("/GroupsPage");
        rd.forward(request,response);
    }
}
