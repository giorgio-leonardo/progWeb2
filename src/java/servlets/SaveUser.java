/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import beans.User;
import com.oreilly.servlet.MultipartRequest;
import database.DbManager;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import support.Utils;

/**
 *
 * @author giorgio
 */
public class SaveUser extends HttpServlet
{
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        User u = new User();
        
        MultipartRequest multi = (MultipartRequest)request.getAttribute("multi");
        DbManager manager = (DbManager)request.getAttribute("manager");
        String avPath = (String)request.getAttribute("avPath");
                        
        u.setUserName((String)multi.getParameter("userName"));
        u.setUserPwd((String)multi.getParameter("userPwd"));
        u.setUserEmail((String)multi.getParameter("userEmail"));
        if ( (String)multi.getParameter("userMod") != null)
            u.setUserMod(true);
        else
            u.setUserMod(false);
        
        try
        {
            manager.insertNewUser(u);
            Integer userID = manager.getUserIDFromUserName(u.getUserName());
            manager.updateUserAvatar(userID, Utils.defaultAvatar);
        }
        catch(SQLException e)
        {
            Utils.toLog(this.getClass().toString() + "\n\t" + e.getLocalizedMessage(), "e");
        }
        
        Enumeration files = multi.getFileNames();
        if (files.hasMoreElements())
        {
            String name = (String)files.nextElement();
            
            if (( multi.getFilesystemName(name) != null ))
            {
                if ( !multi.getContentType(name).equals("image/png"))
                {
                   {
                        ServletContext sc = getServletContext();
                        RequestDispatcher rd = sc.getRequestDispatcher("/registrationPage.jpg");
                        rd.forward(request,response);
                   }
                }
                else
                {
                    try
                    {
                        File f = null;
                        String userAvatar;
                        f = new File(avPath + multi.getFilesystemName(name));
                        userAvatar = Utils.imageToBase64(f, "png");
                        Integer userID = manager.getUserIDFromUserName(u.getUserName());
                        manager.updateUserAvatar(userID, userAvatar);
                        f.delete();
                    }
                    catch (SQLException e)
                    {
                        Utils.toLog(this.getClass().toString() + "\n\t" + e.getLocalizedMessage(), "e");
                    }
                }
            }
        }
        
        ServletContext sc = getServletContext();
        RequestDispatcher rd = sc.getRequestDispatcher("/index.html");
        rd.forward(request,response);

    }
}
