/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package support;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServlet;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import sun.misc.BASE64Encoder;

/**
 *
 * @author giorgio
 */
public class Utils extends HttpServlet
{
    private static final org.apache.log4j.Logger log = Logger.getLogger(Utils.class);
    public static final String gridBgColor = "#c0c0c0";
    public static final String defaultAvatar = "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";
    public static String userStatus = "0000";
      
    public static String dateToString(Date date)
    {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        if (date != null)
            return df.format(date);
        else
            return null;
    }
    
    public static String timeStampToString(Timestamp ts)
    {
        String s = "";
        if (ts != null)
            s = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(ts);
        return s;
    }
 
    public static void toLog(String message, String level)
    {
        level = level.toLowerCase();
        PropertyConfigurator.configure(Utils.class.getClassLoader().getResource("support/log4j.properties"));
        
        switch(level)
        {
            case "f":
                log.fatal(message);
                break;
            case "e":
                log.error(message);
                break;
            case "w":
                log.warn(message);
                break;
            case "i":
                log.info(message);
                break;
            case "d":
                log.debug(message);
                break;
        }
    }
  
    public static void modifyGroupFolder(String path, String oldName, String newName)
    {
        JOptionPane.showMessageDialog(null, path + "/" + oldName);
        JOptionPane.showMessageDialog(null, path + "/" + newName);
        
        File of = new File(path + "/" + oldName);
        File nf = new File(path + "/" + newName);
            
        of.renameTo(nf);
        
    }
    
    public static void createGroupFolder(String path, String groupName)
    {
        File dir = new File(path + "/" + groupName);
        dir.mkdir();
    }
    
    public static String clobToString(Clob cl) throws SQLException 
    {
        Reader stream = cl.getCharacterStream();
        BufferedReader reader = new BufferedReader(stream);
        StringBuilder sb = new StringBuilder();
        String line = null;
        try
        {
            while ((line=reader.readLine())!=null)
            {
                sb.append(line);
            }
            stream.close();
        }
        catch (IOException ex)
        {
            toLog("Utils.clobToString()\n\t" + ex.getLocalizedMessage(), "e");
        }
        
   
        if (sb.length()>0)
        {
            return sb.toString();
        }
   
        return "";
    }
  
    public static String removeHTML(String s)
    {
        String noHTMLString = s.replaceAll("\\<.*?\\>", "");
        
        noHTMLString = noHTMLString.replaceAll("r", "<br/>");
        noHTMLString = s.replaceAll("n", " ");
        noHTMLString = s.replaceAll("'", "&#39;");
        noHTMLString = s.replaceAll("\"", "&quot;");
        return noHTMLString;
    }
    
    public static String parseNewLine(String s)
    {
        StringBuffer sb = new StringBuffer(s);
        try
        {
        
            int c1 = 0;
            int c2 = 0;
            for (int i = 0; i < sb.length() - 2; i++)
            {
                c1 = (int)sb.charAt(i);
                c2 = (int)sb.charAt(i+1);
                if (c1 == 13 && c2 == 10)
                    sb.replace(i, i + 2, "<br>");
            }
        }
        catch (Exception e)
        {
            Utils.toLog("Utils.parseNreLine()\n\t" + e.getLocalizedMessage(), "e");
        }
        return sb.toString();
        
    }
    
    public static String parseLinks(String string)
    {
        String string1 = removeHTML(string);
        String string2 = parseNewLine(string1);
        
        StringBuffer sb = new StringBuffer(string2);
        String openHref = "<a href=\"http://";
        String closeHref = "\">";
        String closeA = "</a>";
        String tmp = "";
        
        Boolean primo = false;
        
        int s = 0;
        int e = 0;
        
        for (int i = 0; i < sb.length(); i++)
        {
            if ( (sb.charAt(i) == '$') && (sb.charAt(i + 1) == '$') )
            {
                if (!primo)
                {
                    s = i + openHref.length();
                    sb.replace(i, i + 2, openHref);
                    primo = true;
                }
                else
                {
                    e = i;
                    sb.replace(i, i + 2, closeHref);
                    primo = false;
                    tmp = sb.substring(s, e);
                    sb.insert(e + 2, tmp);
                    sb.insert((e + 2) + tmp.length(), closeA);
                }
            }
        }
        
        return sb.toString();
    }
    
    public static String imageToBase64(File f, String type) throws IOException
    {
        String ret = null;
        BufferedImage img = ImageIO.read(f);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        
        try
        {
            ImageIO.write(img, type, bos);
            byte[] bosByte = bos.toByteArray();
            
            BASE64Encoder encoder = new BASE64Encoder();
            ret = encoder.encode(bosByte);
            bos.close();
        }
        catch (IOException e)
        {
            toLog("Utils.imageToBase64()\n\t" + e.getLocalizedMessage(), "e");
        }
        
        return ret;
    }
}
