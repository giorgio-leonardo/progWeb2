/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package listeners;

import database.DbManager;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.logging.Level;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import support.Utils;

public class WebAppContextListener implements ServletContextListener
{

    @Override
    public void contextInitialized(ServletContextEvent sce)
    {
        String dbUrl = (String)sce.getServletContext().getInitParameter("dbUrl");
        
        try
        {
            DbManager manager = new DbManager(dbUrl);
            sce.getServletContext().setAttribute("dbmanager", manager);
        }
        catch(SQLException e)
        {
            Utils.toLog(this.getClass().toString() + "\n\t" + e.getLocalizedMessage(), "e");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce)
    {
        //DbManager.shutdown();
        
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements())
        {
            Driver driver = drivers.nextElement();
            try
            {
                DriverManager.deregisterDriver(driver);
                Utils.toLog("Deregistering jdbc driver: " + driver, "i");
            }
            catch (SQLException e)
            {
                Utils.toLog("Error deregistering driver: " + driver, "e");
            }
        }
    }
    
}
