/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import java.io.Serializable;

/**
 *
 * @author giorgio
 */
public class GroupsTable implements Serializable
{
    private Integer groupID;
    private Integer userID;
    private String groupName;
    private Boolean groupIsPrivate;
    private Boolean groupIsOpen;
    private String userName;
    private String userAvatar;
    private String lastPostDate;

    /**
     * @return the groupID
     */
    public Integer getGroupID()
    {
        return groupID;
    }

    /**
     * @param groupID the groupID to set
     */
    public void setGroupID(Integer groupID)
    {
        this.groupID = groupID;
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
     * @return the groupName
     */
    public String getGroupName()
    {
        return groupName;
    }

    /**
     * @param groupName the groupName to set
     */
    public void setGroupName(String groupName)
    {
        this.groupName = groupName;
    }

    /**
     * @return the userName
     */
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
     * @return the groupIsPrivate
     */
    public Boolean getGroupIsPrivate()
    {
        return groupIsPrivate;
    }

    /**
     * @param groupIsPrivate the groupIsPrivate to set
     */
    public void setGroupIsPrivate(Boolean groupIsPrivate)
    {
        this.groupIsPrivate = groupIsPrivate;
    }

    /**
     * @return the groupIsOpen
     */
    public Boolean getGroupIsOpen()
    {
        return groupIsOpen;
    }

    /**
     * @param groupIsOpen the groupIsOpen to set
     */
    public void setGroupIsOpen(Boolean groupIsOpen)
    {
        this.groupIsOpen = groupIsOpen;
    }

    /**
     * @return the lastPostDate
     */
    public String getLastPostDate()
    {
        return lastPostDate;
    }

    /**
     * @param lastPostDate the lastPostDate to set
     */
    public void setLastPostDate(String lastPostDate)
    {
        this.lastPostDate = lastPostDate;
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
}
