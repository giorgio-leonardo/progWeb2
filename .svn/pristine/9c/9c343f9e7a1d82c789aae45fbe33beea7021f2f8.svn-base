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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
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
public class LoadUsers extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession(false);
        User user = (User)session.getAttribute("user");
        
        DbManager manager = (DbManager)request.getAttribute("manager");
        try
        {
            List<User> users = manager.getUsersExceptMe(user.getUserID());
            request.setAttribute("users", users);
            
            RequestDispatcher rd = request.getRequestDispatcher("/createGroup.jsp");
            rd.forward(request, response);
        }
        catch (SQLException e)
        {
            Utils.toLog(this.getClass().toString() + "\n\t" + e.getLocalizedMessage(), "e");
        }
    }

}
