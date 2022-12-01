/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Users;

/**
 *
 * @author liamvu
 */
public class UserError {
    private String userIDError;
    private String userNameError;
    private String userFullNameError;
    private String userAddressError;
    private String userPhoneError;
    private String userEmailError;
    private String roleError;
    private String userPasswordError;
    private String confirmError;

    public UserError() {
    }

    public UserError(String userIDError, String userNameError, String userFullNameError, String userAddressError, String userPhoneError, String userEmailError, String roleError, String userPasswordError, String confirmError) {
        this.userIDError = userIDError;
        this.userNameError = userNameError;
        this.userFullNameError = userFullNameError;
        this.userAddressError = userAddressError;
        this.userPhoneError = userPhoneError;
        this.userEmailError = userEmailError;
        this.roleError = roleError;
        this.userPasswordError = userPasswordError;
        this.confirmError = confirmError;
    }

    public String getUserIDError() {
        return userIDError;
    }

    public void setUserIDError(String userIDError) {
        this.userIDError = userIDError;
    }

    public String getUserNameError() {
        return userNameError;
    }

    public void setUserNameError(String userNameError) {
        this.userNameError = userNameError;
    }

    public String getUserFullNameError() {
        return userFullNameError;
    }

    public void setUserFullNameError(String userFullNameError) {
        this.userFullNameError = userFullNameError;
    }

    public String getUserAddressError() {
        return userAddressError;
    }

    public void setUserAddressError(String userAddressError) {
        this.userAddressError = userAddressError;
    }

    public String getUserPhoneError() {
        return userPhoneError;
    }

    public void setUserPhoneError(String userPhoneError) {
        this.userPhoneError = userPhoneError;
    }

    public String getUserEmailError() {
        return userEmailError;
    }

    public void setUserEmailError(String userEmailError) {
        this.userEmailError = userEmailError;
    }

    public String getRoleError() {
        return roleError;
    }

    public void setRoleError(String roleError) {
        this.roleError = roleError;
    }

    public String getUserPasswordError() {
        return userPasswordError;
    }

    public void setUserPasswordError(String userPasswordError) {
        this.userPasswordError = userPasswordError;
    }

    public String getConfirmError() {
        return confirmError;
    }

    public void setConfirmError(String confirmError) {
        this.confirmError = confirmError;
    }
    
}
