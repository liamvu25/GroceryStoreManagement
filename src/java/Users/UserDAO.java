/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Users;

import External.DLC;
import Utils.DBUtils;
import java.io.UnsupportedEncodingException;
import static java.rmi.server.LogStream.log;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author liamvu
 */
public class UserDAO {
    private static final String LOGIN = "SELECT userID, userFullName, roleID, userAddress, userPhone, userBirthday, userEmail, userName, statusID FROM tblUsers WHERE (userName=? OR userEmail = ?) AND password=?";
    private static final String GET_STATUS = "SELECT statusName FROM tblStatus WHERE statusID = ?";
    private static final String GET_ROLEID = "SELECT roleID FROM tblRoles WHERE roleName = ?";
    private static final String CREAT_USER = "INSERT INTO tblUsers(userName, userFullName, userAddress, userPhone, userBirthday, userEmail,password, roleID, statusID) VALUES( ?, ?, ?, ?, ?, ?, ?, 'us','1')";
    private static final String SREACH_USER= "SELECT userID, userName, userFullName, userAddress, userPhone, userBirthday, userEmail, roleName, u.statusID FROM tblUsers u,tblRoles r WHERE u.roleID=r.roleID AND userName like ?";
    private static final String UPDATE_USER="UPDATE tblUsers SET userFullName=?, userAddress =?, userPhone=?, userEmail, roleID=?,userBirthday=?, WHERE userID = ?";
    private static final String CHANGE_STATUS="UPDATE tblUsers SET statusID=? WHERE userID = ?";
    public UserDTO checkLogin(String userName, String password){
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            userName = DLC.decodeUTF8(userName);
            if(conn != null){
                stm = conn.prepareStatement(LOGIN);
                stm.setString(1, userName);
                stm.setString(2, userName);
                stm.setString(3, password);
                rs = stm.executeQuery();
                if (rs.next()){
                    int userID = rs.getInt("userID");
                    String roleID = rs.getString("roleID");
                    Date userBirthday = rs.getDate("userBirthday");
                    String userFullName = rs.getString("userFullName");
                    String userAddress = rs.getString("userAddress");
                    String userPhone = rs.getString("userPhone");
                    String userEmail = rs.getString("userEmail");
                    String Name = rs.getString("userName");
                    int statusID = rs.getInt("statusID");
                     if (statusID == 1){
                         user = new UserDTO(userID, Name, userFullName, userAddress, userPhone, userBirthday, userEmail, "", roleID, this.getStatusName(statusID));
                     }
                }
            }
            
        } catch (UnsupportedEncodingException | ClassNotFoundException | SQLException e) {
            log("Error at UserDAO.checkLogin" + e.toString());
        }finally{
            DBUtils.closeQueryConnection(conn, stm, rs);
        }
        return user;
    }
    private String getStatusName(int statusID) {
        String statusName = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            if (conn == null) {
                conn = DBUtils.getConnection();
            }
            if (conn != null) {
                stm = conn.prepareStatement(GET_STATUS);
                stm.setInt(1, statusID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    statusName = rs.getString("statusName");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            log("Error at UserDAO.getStatusName" + e.toString());
        }finally{
            DBUtils.closeQueryConnection(conn, stm, rs);
        }
        return statusName;
    }
    public String getRoleID(String roleName){
        String roleID = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_ROLEID);
                stm.setString(1, roleName);
                rs = stm.executeQuery();
                if (rs.next()) {
                    roleID = rs.getString("roleID");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            log("Error at UserDAO.getRoleID" + e.toString());
        }finally{
            DBUtils.closeQueryConnection(conn, stm, rs);
        }
        if (roleID == null) {
            if (roleName.equals("ad")) {
                roleID = roleName;
            } else if (roleName.equals("us")) {
                roleID = roleName;
            }
        }
        return roleID;
    }
    public boolean createUser(UserDTO user) throws Exception{
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
             conn = DBUtils.getConnection();
             stm = conn.prepareStatement(CREAT_USER);
             stm.setString(1, user.getUserName());
             stm.setString(2, user.getUserFullName());
             stm.setString(3, user.getUserAddress());
             stm.setString(4, user.getUserPhone());
             stm.setDate(5, user.getUserBirthday());
             stm.setString(6, user.getUserEmail());
             stm.setString(7, user.getUserPassword());
             check = stm.executeUpdate() > 0;
        } catch (ClassNotFoundException | SQLException e) {
            if(e.getMessage().contains("UNIQUE KEY")){
                throw new Exception(e);
            }
            log("Error at updateUser in UserDAO: " + e.toString());
        }finally{
            DBUtils.closeQueryConnection(conn, stm, null);
        }
        return check;
    }
    public List<UserDTO> getListUser(String search){
        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
             conn = DBUtils.getConnection();
             if (conn != null) {
                 stm = conn.prepareStatement(SREACH_USER);
                 stm.setString(1, "%" + search + "%");
                 rs = stm.executeQuery();
                 while (rs.next()) {
                    int userID = rs.getInt("userID");
                    String roleName = rs.getString("roleName");
                    Date birthday = rs.getDate("userBirthday");
                    String userName = rs.getString("userName");
                    String userFullName = rs.getString("userFullName");
                    String userAddress = rs.getString("userAddress");
                    String userPhone = rs.getString("userPhone");
                    String userEmail = rs.getString("userEmail");
                    int statusID = rs.getInt("statusID");
                    UserDTO user = new UserDTO(userID, userName, userFullName, userAddress, userPhone, birthday, userEmail, "*****", roleName, this.getStatusName(statusID));
                    list.add(user);
                }
             }
        } catch (ClassNotFoundException | SQLException e) {
            log("Error at UserDAO.getListUser" + e.toString());
        }finally{
            DBUtils.closeQueryConnection(conn, stm, rs);
        }
        list.sort((u1, u2) -> {
            if (((UserDTO) u1).getRole().charAt(0) == 'A' && ((UserDTO) u2).getRole().charAt(0) == 'A') {
                return 0;
            }
            if (((UserDTO) u1).getRole().charAt(0) == 'A' && ((UserDTO) u2).getRole().charAt(0) != 'A') {
                return -1;
            } else {
                return 1;
            }
            //To change body of generated lambdas, choose Tools | Templates.
        });
         return list;
    }
    public boolean updateUser(UserDTO user){
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
             if (conn != null){
                stm = conn.prepareStatement(UPDATE_USER);
                stm.setString(1, user.getUserFullName());
                stm.setString(2, user.getUserAddress());
                stm.setString(3, user.getUserPhone());
                stm.setString(4, user.getUserEmail());
                stm.setString(5, this.getRoleID(user.getRole()));
                stm.setString(6, user.getUserBirthday().toString());
                check = stm.executeUpdate() > 0;
             }
        } catch (ClassNotFoundException | SQLException e) {
            log("Error at updateUser in UserDAO: " + e.toString());
        }finally{
            DBUtils.closeQueryConnection(conn, stm, null);
        }
        return check;
    }
    public boolean changeUserStatus(UserDTO user){
        Connection conn = null;
        PreparedStatement stm = null;
        boolean check = false;
        try {
            conn = DBUtils.getConnection();
            if (conn != null){
                stm = conn.prepareStatement(CHANGE_STATUS);
                stm.setInt(1, this.getStatusID(user.getStatus()) == 1 ? 0 : 1);
                stm.setInt(2, user.getUserID());
                check = stm.executeUpdate() > 0;
            }
        } catch (ClassNotFoundException | SQLException e) {
            log("Error at UserDAO.changeActivation" + e.toString());
        }finally{
            DBUtils.closeQueryConnection(conn, stm, null);
        }
        return check;
    }
    private int getStatusID(String statusName) {
        int result = 0;
        if (statusName.equals("Active")) {
            result = 1;
        }
        return result;
    }
}
