/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import database.DbManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.security.Security;
import java.util.Date;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import support.Utils;

/**
 *
 * @author giorgio
 */
public class SendMailRecoveryPwd extends HttpServlet
{

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        
        Properties props = System.getProperties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        
        final String gUserName = "secondoprogettopw@gmail.com";
        final String gPassword = "Progetto2";
        
        Session session = Session.getDefaultInstance(props, new Authenticator()
            {
                protected PasswordAuthentication getPasswordAuthentication()
                {
                    return new PasswordAuthentication(gUserName, gPassword);
                }
            });  
        
        String userName = (String)request.getAttribute("userName");
        DbManager manager = (DbManager)request.getAttribute("manager");
        
        String userEmail = "";
        String userPassword = "";
        try
        {
            userEmail = manager.getEmailFromUserName(userName);
            userPassword = manager.getPwdFromUserName(userName);
        } 
        catch (SQLException e)
        {
            Utils.toLog(this.getClass().toString() + "\n\t" + e.getLocalizedMessage(), "e");
        }
        
        Message msg = new MimeMessage(session);
        try
        {
            msg.setFrom(new InternetAddress(gUserName + ""));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userEmail, false));
            msg.setSubject("Secondo progetto. Recupero password");
            msg.setText
            (
                "Questa mail ti è stata inviata per recuperare la password.\n" +
                "La tua password è " + userPassword + "\n\n" +
                "Introduzione alla programmazione per il web\n" + new Date()
            );
            msg.setSentDate(new Date());
            
            Transport.send(msg);
            {
                ServletContext sc = getServletContext();
                RequestDispatcher rd = sc.getRequestDispatcher("/successPages/emailSuccess.jsp");
                rd.forward(request,response);
            }
        }
        catch (MessagingException e)
        {
            Utils.toLog(this.getClass().toString() + "\n\t" + e.getLocalizedMessage(), "e");
            {
                ServletContext sc = getServletContext();
                RequestDispatcher rd = sc.getRequestDispatcher("/errorPages/emailError.jsp");
                rd.forward(request,response);
            }
        }
    }
}
