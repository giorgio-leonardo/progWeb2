/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import beans.Group;
import beans.Post;
import beans.User;
import database.DbManager;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
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
public class GoToGroup extends HttpServlet
{
    DbManager manager;
    
    public void init(ServletConfig config) throws ServletException
    {
        super.init(config);
        this.manager = (DbManager)super.getServletContext().getAttribute("dbmanager");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        User user = null;
        HttpSession session = null;
        
        try
        {
            session = request.getSession(false);
            user = (User)session.getAttribute("user");
        }
        catch (NullPointerException e)
        {
            Utils.toLog(e.getLocalizedMessage(), "i");
        }
        
        Group group = new Group();
        List<Post> posts = null;
        List<User> usersInGroup = null;
        List<User> usersNotInGroup = null;
        String lastPostDate = "";
        try
        {
            group = manager.getGroup(Integer.parseInt(request.getParameter("groupID")));
            group.setGroupOldName(group.getGroupName());
            usersInGroup = manager.getUsersInGroup(group.getGroupID(), user.getUserID());
            usersNotInGroup = manager.getUsersNotInGroup(group.getGroupID());
            posts = manager.getPostForGroup(group.getGroupID());
        }
        catch (SQLException e)
        {
            Utils.toLog(getClass().getName() + "\n\t" + e.getLocalizedMessage(), "e");
        }
        
        String groupName = group.getGroupName();
        request.setAttribute("group", group);
        request.setAttribute("posts", posts);
        request.setAttribute("pr", group.getGroupIsPrivate());
        
        ServletContext sc = getServletContext();
        RequestDispatcher rd = null;
        
        String opG = request.getParameter("opG");
        switch(opG)
        {
            case "go":
                rd = sc.getRequestDispatcher("/groupPage.jsp");
                rd.forward(request,response);
                break;
            case "mg":
                request.setAttribute("usersInGroup", usersInGroup);
                request.setAttribute("usersNotInGroup", usersNotInGroup);
                rd = sc.getRequestDispatcher("/modifyGroup.jsp");
                rd.forward(request,response);
                break;
        }
        
        
    }
}
