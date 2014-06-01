/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author giorgio
 */
public class User implements Serializable
{
    private Integer userID;
    private String userName;
    private String userPwd;
    private String userEmail;
    private String userAvatar;
    private Boolean userSiteAdmin;
    private Boolean userMod;
    private String userLastLogin;

    /**
     * @return the userName
     */
    
    public User()
    {
        
    }
    
    public String getUserName()
    {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    /**
     * @return the userPwd
     */
    public String getUserPwd()
    {
        return userPwd;
    }

    /**
     * @param userPwd the userPwd to set
     */
    public void setUserPwd(String userPwd)
    {
        this.userPwd = userPwd;
    }

    /**
     * @return the userEmail
     */
    public String getUserEmail()
    {
        return userEmail;
    }

    /**
     * @param userEmail the userEmail to set
     */
    public void setUserEmail(String userEmail)
    {
        this.userEmail = userEmail;
    }

    /**
     * @return the userAvatar
     */
    public String getUserAvatar()
    {
        return userAvatar;
    }

    /**
     * @param userAvatar the userAvatar to set
     */
    public void setUserAvatar(String userAvatar)
    {
        this.userAvatar = userAvatar;
    }

    /**
     * @return the userMod
     */
    public Boolean getUserMod()
    {
        return userMod;
    }

    /**
     * @param userMod the userMod to set
     */
    public void setUserMod(Boolean userMod)
    {
        this.userMod = userMod;
    }

    /**
     * @return the userID
     */
    public Integer getUserID()
    {
        return userID;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(Integer userID)
    {
        this.userID = userID;
    }

    /**
     * @return the userLastLogin
     */
    public String getUserLastLogin()
    {
        return userLastLogin;
    }

    /**
     * @param userLastLogin the userLastLogin to set
     */
    public void setUserLastLogin(String userLastLogin)
    {
        this.userLastLogin = userLastLogin;
    }
}
