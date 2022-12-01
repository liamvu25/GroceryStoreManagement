/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Users;

import java.sql.Date;

/**
 *
 * @author liamvu
 */
public class UserDTO implements Comparable<UserDTO>{
    private int userID;
    private String userName;
    private String userFullName;
    private String userAddress;
    private String userPhone;
    private Date userBirthday;
    private String userEmail;
    private String userPassword;
    private String role;
    private String status;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Date getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserDTO() {
        this.userID = -100;
        this.userName = "";
        this.userFullName = "";
        this.userAddress = "";
        this.userPhone = "";
        this.userBirthday = null;
        this.userEmail = "";
        this.userPassword = "";
        this.role = "";
        this.status = "";
    }

    public UserDTO(int userID, String userName, String userFullName, String userAddress, String userPhone, Date userBirthday, String userEmail, String userPassword, String role, String status) {
        this.userID = userID;
        this.userName = userName;
        this.userFullName = userFullName;
        this.userAddress = userAddress;
        this.userPhone = userPhone;
        this.userBirthday = userBirthday;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.role = role;
        this.status = status;
    }
      @Override
    public int compareTo(UserDTO o) {
        if(this.userID > o.userID)
            return 1;
        else if(this.userID < o.userID)
            return -1;
        else return 0;
    }
    
}
