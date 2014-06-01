/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import beans.GroupsTable;
import beans.User;
import database.DbManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
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
public class GroupsPage extends HttpServlet
{

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession(false);
        User user = (User)session.getAttribute("user");
        
        DbManager manager = (DbManager)request.getAttribute("manager");
        
        try
        {
            List<GroupsTable> groupsTable = manager.getGroups(false, user.getUserID());
            request.setAttribute("grMod", false);
            request.setAttribute("groupsTable", groupsTable);
        }
        catch (SQLException e)
        {
            Utils.toLog(getClass().getName() + ":\n" + e.getLocalizedMessage(), "e");
        }
        
        
        ServletContext sc = getServletContext();
        RequestDispatcher rd = sc.getRequestDispatcher("/groupsTablePage.jsp");
        rd.forward(request,response);
    }
}
