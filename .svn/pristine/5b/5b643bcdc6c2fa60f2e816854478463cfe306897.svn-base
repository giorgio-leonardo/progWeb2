/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package database;

import beans.Group;
import beans.Invitation;
import beans.GroupsTable;
import beans.Post;
import beans.User;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import support.Utils;

/**
 *
 * @author giorgio
 */
public class DbManager implements Serializable
{
    private transient Connection conn;
    
    public DbManager(String dbUrl) throws SQLException
    {
        try
        {
            Class.forName("org.apache.derby.jdbc.ClientDriver", true, getClass().getClassLoader());
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.toString(), e);
        }
        
        Connection conn = DriverManager.getConnection(dbUrl);
        
        if (!conn.isClosed())
        {
            conn.close();
            conn = DriverManager.getConnection(dbUrl);
        }
        this.conn = conn;
        
    }
    
    public static void shutdown()
    {
        try
        {
            DriverManager.getConnection("jdbc:derby:;shutdown=true");
        }
        catch(SQLException e)
        {
            Utils.toLog("DbManager\n\t" + e.getLocalizedMessage(), "e");
        }
    }
    
    
    public synchronized User authenticate(String userName, String userPwd) throws SQLException
    {
        User user = null;
        PreparedStatement stm = conn.prepareStatement
        (
            "SELECT * FROM users WHERE userName = ? AND userPwd = ?"
        );
        
        try
        {
            stm.setString(1, userName);
            stm.setString(2, userPwd);
            
            ResultSet rs = stm.executeQuery();
            try
            {
                if(rs.next())
                {
                    user = new User();
                    user.setUserID(rs.getInt("userID"));
                    user.setUserName(rs.getString("userName"));
                    user.setUserPwd(rs.getString("userPwd"));
                    user.setUserEmail(rs.getString("userEmail"));
                    user.setUserAvatar(rs.getString("userAvatar"));
                    user.setUserMod(rs.getBoolean("userMod"));

                    Timestamp ts = rs.getTimestamp("userLastLogin");
                    String d;
                    if(ts == null)
                    {
                        d = "Questa è la prima volta che logghi";
                    }
                    else
                    {
                        d = Utils.timeStampToString(rs.getTimestamp("userLastLogin"));
                    }
                    
                    user.setUserLastLogin(d);
                }
            }
            finally
            {
                rs.close();
            }
        }
        finally
        {
            stm.close();
        }    
        
        return user;
    }
    
    public synchronized String getEmailFromUserName(String userName) throws SQLException
    {
        String ret = null;
        
        PreparedStatement stm = conn.prepareStatement
        (
            "SELECT userEmail FROM users WHERE userName = ?"
        );
        
        stm.setString(1, userName);
        
        try
        {
            ResultSet rs = stm.executeQuery();
            try
            {
                if(rs.next())
                {
                    ret = rs.getString("userEmail");
                }
            }
            finally
            {
                rs.close();
            }
        }
        finally
        {
            stm.close();
        }
        
        return ret;
    }
    
    public synchronized List<GroupsTable> getGroups(Boolean mod, Integer userID) throws SQLException
    {
        
        List<GroupsTable> groupsTable = new ArrayList<GroupsTable>();
        String sql;
        if (mod)
        {
            sql = 
            "SELECT g.groupID AS gGroupID, g.groupAdminID AS gGroupAdminID, " + 
            "       g.groupName AS gGroupName, g.groupIsPrivate AS gGroupIsPrivate, g.groupIsOpen AS gGroupIsOpen, " +
            "       g.groupDataCreation AS gGroupDataCreation, u.userID AS uUserID, u.userName AS uUserName, u.userAvatar AS uUserAvatar " +
            "   FROM groups AS g " +
            "   JOIN users AS u ON g.groupAdminID = u.userID " +
            "   ORDER BY g.groupName";
        }
        else
        {
            sql =
            "SELECT g.groupID AS gGroupID, g.groupAdminID AS gGroupAdminID, " +
            "       g.groupName AS gGroupName, g.groupIsPrivate AS gGroupIsPrivate, g.groupIsOpen AS gGroupIsOpen, " +
            "       g.groupDataCreation AS gGroupDataCreation, u.userID AS uUserID, u.userName AS uUserName, u.userAvatar AS uUserAvatar, " +
            "       gu.groupID AS guGroupID, gu.userID as guUserID " +
            "   FROM groups AS g " +
            "   JOIN users AS u ON g.groupAdminID = u.userID " +
            "   JOIN groupUser AS gu ON gu.groupID = g.groupID " +
            "   WHERE gu.userID = ?" +
            "   ORDER BY g.groupName";
        }
        
        PreparedStatement stm = conn.prepareStatement(sql);
        
        if (!mod)
        {
            stm.setInt(1, userID);
        }
        
        try
        {
            ResultSet rs = stm.executeQuery();
            try
            {
                while (rs.next())
                {
                    GroupsTable mg = new GroupsTable();
                    
                    mg.setGroupID(rs.getInt("gGroupID"));
                    mg.setGroupName(rs.getString("gGroupName"));
                    mg.setUserID(rs.getInt("gGroupAdminID"));
                    mg.setUserName(rs.getString("uUserName"));
                    mg.setGroupIsPrivate(rs.getBoolean("gGroupIsPrivate"));
                    mg.setGroupIsOpen(rs.getBoolean("gGroupIsOpen"));
                    mg.setUserAvatar(rs.getString("uUserAvatar"));
                    
                    String l = this.getLastPostDate(mg.getGroupID());
                    mg.setLastPostDate(l);
                    
                    groupsTable.add(mg);
                }
            }
            finally
            {
                rs.close();
            }
        }
        finally
        {
            stm.close();
        }
        
        return groupsTable;
    }
    
    public synchronized Integer getGroupIDFromGroupName(String groupName) throws SQLException
    {
        Integer ret = 0;
        PreparedStatement stm = conn.prepareStatement
        (
            "SELECT groupID FROM groups WHERE groupName = ?"
        );
        
        stm.setString(1, groupName);
        try
        {
            ResultSet rs = stm.executeQuery();
            try
            {
                if(rs.next())
                {
                    ret = rs.getInt("groupID");
                }
            }
            finally
            {
                rs.close();
            }
        }
        finally
        {
            stm.close();
        }
        
        return ret;
    }
    
    public synchronized Group getGroup(Integer groupID) throws SQLException
    {
        Group g = new Group();
        
        PreparedStatement stm = conn.prepareStatement
        (
            "SELECT * FROM groups WHERE groupID = ?"
        );
        
        stm.setInt(1, groupID);
        
        try
        {
            ResultSet rs = stm.executeQuery();
            try
            {
                if(rs.next())
                {
                    g.setGroupID(groupID);
                    g.setGroupName(rs.getString("groupName"));
                    g.setGroupAdminID(rs.getInt("groupAdminID"));
                    g.setGroupDataCreation(Utils.dateToString(rs.getDate("groupDataCreation")));
                    g.setGroupIsOpen(rs.getBoolean("groupIsOpen"));
                    g.setGroupIsPrivate(rs.getBoolean("groupIsPrivate"));
                }
            }
            finally
            {
                rs.close();
            }
        }
        finally
        {
            stm.close();
        }
        return g;
    }
    
    public synchronized String getLastPostDate(Integer groupID) throws SQLException
    {
        String ret = "";
        
        PreparedStatement stm = conn.prepareStatement
        (
            "SELECT MAX(postDataCreation) AS m FROM post WHERE groupID = ?"
        );
        
        stm.setInt(1, groupID);
        try
        {
            ResultSet rs = stm.executeQuery();
            try
            {
                if (rs.next())
                {
                    ret = Utils.timeStampToString(rs.getTimestamp("m"));
                }
            }
            finally
            {
                rs.close();
            }
        }
        finally
        {
            stm.close();
        }
        
        return ret;
    }
            
    
    public synchronized List<Post> getPostForGroup(Integer groupID) throws SQLException
    {
        List<Post> posts = new ArrayList<Post>();
        PreparedStatement stm = conn.prepareStatement
        (
            "SELECT p.postID AS pPostID, p.groupID AS pGroupID, " +
                    "p.userID AS pUserID, p.postDataCreation AS pDataCreation, p.postText AS pPostText, " +
                    "u.userID, u.userName AS uUserName, u.userAvatar AS uUserAvatar " +
                    "   FROM post AS p " +
                    "   JOIN users AS u ON p.userID = u.userID " +
                    "   WHERE p.groupID = ? " +
                    "   ORDER BY p.postDataCreation ASC"
        );
        
        stm.setInt(1, groupID);
        try
        {
            ResultSet rs = stm.executeQuery();
            try
            {
                while (rs.next())
                {
                    Post p = new Post();
                    
                    p.setPostID(rs.getInt("pPostID"));
                    p.setGroupID(groupID);
                    p.setUserID(rs.getInt("pUserID"));
                    p.setPostDataCreation(Utils.timeStampToString(rs.getTimestamp("pDataCreation")));
                    p.setPostText(rs.getString("pPostText"));
                    p.setUserName(rs.getString("uUserName"));
                    p.setUserAvatar(rs.getString("uUserAvatar"));
                    
                    posts.add(p);
                }
            }
            finally
            {
                rs.close();
            }
        }
        finally
        {
            stm.close();
        }
        
        return posts;
    }
    
    public synchronized String getPwdFromUserName(String userName) throws SQLException
    {
        String ret = null;
        
        PreparedStatement stm = conn.prepareStatement
        (
            "SELECT userPwd FROM users WHERE userName = ?"
        );
        
        stm.setString(1, userName);
        
        try
        {
            ResultSet rs = stm.executeQuery();
            try
            {
                if(rs.next())
                {
                    ret = rs.getString("userPwd");
                }
            }
            finally
            {
                rs.close();
            }
        }
        finally
        {
            stm.close();
        }
        
        return ret;
    }
    
    public synchronized List<User> getUsersExceptMe(Integer userID) throws SQLException
    {
        List<User> users = new ArrayList<User>();
        PreparedStatement stm = conn.prepareStatement
        (
            "SELECT * FROM users WHERE userID <> ?"
        );
        
        stm.setInt(1, userID);
        
        try
        {
            ResultSet rs = stm.executeQuery();
            try
            {
                while(rs.next())
                {
                    User u = new User();
                    
                    u.setUserID(rs.getInt("userID"));
                    u.setUserName(rs.getString("userName"));
                    u.setUserPwd(rs.getString("userPwd"));
                    u.setUserEmail(rs.getString("userEmail"));
                    u.setUserAvatar(rs.getString("userAvatar"));
                    u.setUserMod(rs.getBoolean("userMod"));
                    u.setUserLastLogin(Utils.timeStampToString(rs.getTimestamp("userLastLogin")));
                    
                    users.add(u);
                }
            }
            finally
            {
                rs.close();
            }
        }
        finally
        {
            stm.close();
        }
        
        return users;
    }
    
    public synchronized List<User> getUsersInGroup(Integer groupID, Integer userID) throws SQLException
    {
        List<User> users = new ArrayList<User>();
        
        PreparedStatement stm = conn.prepareStatement
        (
            "SELECT gu.groupID AS guGroupID, gu.userID AS guUserID, gu.userIsAdmin AS guUserIsAdmin, " +
            "       u.userID AS uUserID, u.userName AS uUserName, u.userPwd AS uUserPwd, u.userEmail AS uUserEmail, " +
            "       u.userAvatar AS uUserAvatar, u.userMod AS uUserMod, u.userSiteAdmin AS uUserSiteAdmin, " +
            "       u.userLastLogin AS uLastLogin " +
            "   FROM groupUser AS gu " +
            "   JOIN users AS u ON gu.userID = u.userID " +
            "   WHERE gu.userID <> ? AND gu.groupID = ?"
        );
        
        stm.setInt(1, userID);
        stm.setInt(2, groupID);
        try
        {
            ResultSet rs = stm.executeQuery();
            try
            {
                while (rs.next())
                {
                    User u = new User();
                    
                    u.setUserID(rs.getInt("uUserID"));
                    u.setUserAvatar(rs.getString("uUserAvatar"));
                    u.setUserEmail(rs.getString("uUserEmail"));
                    u.setUserLastLogin(Utils.dateToString(rs.getDate("uLastLogin")));
                    u.setUserName(rs.getString("uUserName"));
                    u.setUserPwd(rs.getString("uUserPwd"));
                    
                    users.add(u);
                }
            }
            finally
            {
                rs.close();
            }
        }
        finally
        {
            stm.close();
        }
        
        return users;
    }
    
    public synchronized Integer getUserIDFromUserName(String userName) throws SQLException
    {
        Integer ret = 0;
        PreparedStatement stm = conn.prepareStatement
        (
            "SELECT userID FROM users WHERE userName = ?"
        );
        
        stm.setString(1, userName);
        try
        {
            ResultSet rs = stm.executeQuery();
            try
            {
                if(rs.next())
                {
                    ret = rs.getInt("userID");
                }
            }
            finally
            {
                rs.close();
            }
        }
        finally
        {
            stm.close();
        }
        
        return ret;
    }
    
    public synchronized List<Invitation> getUserInvitation(Integer userID) throws SQLException
    {
        List<Invitation> invitations = new ArrayList<Invitation>();
        PreparedStatement stm = conn.prepareStatement
        (
            "SELECT i.groupID AS iGroupID, i.userID AS iUserID, g.groupID AS gGroupID, u.userID AS uUserID, g.groupName AS gGroupName, u.userName as uUserName " +
            "   FROM invitation as i " +
            "       JOIN groups AS g ON i.groupID = g.groupID " +
            "       JOIN users AS u ON g.groupAdminID = u.userID" +
            "   WHERE i.userID = ?"
        );
        
        stm.setInt(1, userID);
        
        try
        {
            ResultSet rs = stm.executeQuery();
            try
            {
                while (rs.next())
                {
                    Invitation i = new Invitation();
                    
                    i.setGroupID(rs.getInt("gGroupID"));
                    i.setGroupName(rs.getString("gGroupName"));
                    i.setUserID(rs.getInt("uUserID"));
                    i.setUserName(rs.getString("uUserName"));
                    
                    invitations.add(i);
                    
                }
            }
            finally
            {
                rs.close();
            }
        }
        finally
        {
            stm.close();
        }
        return invitations;
    }
    
    public synchronized List<User> getUsersNotInGroup(Integer groupID) throws SQLException
    {
        List<User> users = new ArrayList<User>();
        
        PreparedStatement stm = conn.prepareStatement
        /*(
            "SELECT gu.groupID AS guGroupID, gu.userID AS guUserID, gu.userIsAdmin AS guUserIsAdmin, " +
            "       u.userID AS uUserID, u.userName AS uUserName, u.userPwd AS uUserPwd, u.userEmail AS uUserEmail, " +
            "       u.userAvatar AS uUserAvatar, u.userMod AS uUserMod, u.userSiteAdmin AS uUserSiteAdmin, " +
            "       u.userLastLogin AS uLastLogin " +
            "   FROM groupUser AS gu " +
            "   JOIN users AS u ON gu.userID = u.userID " +
            "   WHERE gu.userID <> ? AND gu.GroupID <> ?"
        );*/
                
        (
            "SELECT  u.userID AS uUserID, u.userName AS uUserName, u.userPwd AS uUserPwd, u.userEmail AS uUserEmail, " +
            "        u.userAvatar AS uUserAvatar, u.userMod AS uUserMod, u.userSiteAdmin AS uUserSiteAdmin, " +
            "        u.userLastLogin AS uLastLogin " +
            "   FROM users AS u" +
            "   WHERE u.userID NOT IN (SELECT gu.userID FROM groupUser AS gu WHERE gu.groupID = ?)"
        );
        
        stm.setInt(1, groupID);
        
        try
        {
            ResultSet rs = stm.executeQuery();
            try
            {
                while (rs.next())
                {
                    User u = new User();
                    
                    u.setUserID(rs.getInt("uUserID"));
                    u.setUserAvatar(rs.getString("uUserAvatar"));
                    u.setUserEmail(rs.getString("uUserEmail"));
                    u.setUserLastLogin(Utils.dateToString(rs.getDate("uLastLogin")));
                    u.setUserName(rs.getString("uUserName"));
                    u.setUserPwd(rs.getString("uUserPwd"));
                    
                    users.add(u);
                }
            }
            finally
            {
                rs.close();
            }
        }
        finally
        {
            stm.close();
        }
        
        return users;
    }
    
    public synchronized void insertInvitations(Integer groupID, String[] users) throws SQLException
    {
        PreparedStatement stm = conn.prepareStatement
        (
            "INSERT INTO invitation (groupID, userID) values (?, ?)"
        );
        
        try
        {
            for (String s: users)
            {
                Integer userID = getUserIDFromUserName(s);
                stm.setInt(1, groupID);
                stm.setInt(2, userID);
                stm.execute();
            }
        }
        finally
        {
            stm.close();
        }
    }
    
    public synchronized void insertNewGroup(Integer userID, String groupName, Boolean groupIsPrivate) throws SQLException
    {

        java.util.Date javaDate = Calendar.getInstance().getTime();
        java.sql.Date sqlDate = new java.sql.Date(javaDate.getTime());
        
        try
        {
            PreparedStatement stm = conn.prepareStatement
            (
                "INSERT INTO groups (groupAdminID, groupName, groupIsPrivate, groupDataCreation)" +
                "   VALUES (?, ?, ?, ?)"
            );

            stm.setInt(1, userID);
            stm.setString(2, groupName);
            stm.setBoolean(3, groupIsPrivate);
            stm.setDate(4, sqlDate);

            try
            {
                Boolean rs = stm.execute();

            }
            finally
            {
                stm.close();
            }
        }
        catch (Exception e)
        {
            Utils.toLog("DbManager\n\t" + e.getLocalizedMessage(), "e");
        }
    }
    
    public synchronized void insertNewUser(User user) throws SQLException
    {
        PreparedStatement stm;
                
        stm = conn.prepareStatement
        (
            "INSERT INTO users (userName, userPwd, userEmail, userMod)" +
            "   VALUES (?, ?, ?, ?)"
        );

        stm.setString(1, user.getUserName());
        stm.setString(2, user.getUserPwd());
        stm.setString(3, user.getUserEmail());
        stm.setBoolean(4, user.getUserMod());
        
        try
        {
            stm.execute();
        }
        finally
        {
            stm.close();
        }
    }
    
    public synchronized void removeUserFromInvitation(Integer userID, Integer groupID) throws SQLException
    {
        PreparedStatement stm;
        Boolean rs = false;
        
        stm = conn.prepareStatement
        (
            "DELETE FROM invitation WHERE userID = ? AND groupID = ?"
        );
        
        
        stm.setInt(1, userID);
        stm.setInt(2, groupID);
        
        try
        {
            rs = stm.execute();
        }
        finally
        {
            stm.close();
        }
    }
    
    public synchronized void insertUserInGroup(Integer userID, Integer groupID, Boolean isAdmin) throws SQLException
    {
        PreparedStatement stm;
                
        stm = conn.prepareStatement
        (
            "INSERT INTO groupUser (groupID, userID, userIsAdmin)" +
            "   VALUES (?, ?, ?)"
        );

        stm.setInt(1, groupID);
        stm.setInt(2, userID);
        stm.setBoolean(3, isAdmin);
        
        try
        {
            Boolean rs = stm.execute();
        }
        finally
        {
            stm.close();
        }
    }
    
    public synchronized void updateGroup(Integer groupID, String groupName, String groupIsPrivate, String groupIsOpen, String[] chk) throws SQLException
    {
        PreparedStatement stm;
        stm = conn.prepareStatement
        (
            "UPDATE groups SET " +
            "       groupName = ?, groupIsPrivate = ?, groupIsOpen = ? " +
            "WHERE groupID = ?"
        );
        
        stm.setString(1,groupName);
        stm.setBoolean(2, (Boolean.valueOf(groupIsPrivate)));
        stm.setBoolean(3, (Boolean.valueOf(groupIsOpen)));
        stm.setInt(4, groupID);
        
        try
        {
            Integer rs = stm.executeUpdate();
        }
        finally
        {
            stm.close();
        }
        
        if (chk.length > 0)
        {
            this.insertInvitations(groupID, chk);
        }
    }
    
    public synchronized void updateLastAccessTime(Integer userID, long cDate) throws SQLException
    {
        java.util.Date jDate = new Date(cDate);
        java.sql.Timestamp sDate = new java.sql.Timestamp(jDate.getTime());
        
        PreparedStatement stm;
        stm = conn.prepareStatement
        (
            "UPDATE users SET userLastLogin = ? WHERE userID = ?"
        );

        stm.setTimestamp(1, sDate);
        stm.setInt(2, userID);

        try
        {
            Boolean r = stm.execute();
        }
        finally
        {
            stm.close();
        }
    }
    
    public synchronized void updateUserAvatar(Integer userID, String userAvatar) throws SQLException
    {
        userAvatar = "data:image/png;base64," + userAvatar;
        PreparedStatement stm;
        stm = conn.prepareStatement
        (
            "UPDATE users SET userAvatar = ? WHERE userID = ?"
        );
        
        stm.setString(1, userAvatar);
        stm.setInt(2, userID);
        
        try
        {
            Boolean rs = stm.execute();
        }
        finally
        {
            stm.close();
        }
    }
}
