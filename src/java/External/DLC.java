/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package External;

import Users.UserError;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author liamvu
 */
public class DLC {
    public static String decodeUTF8(String encode) throws UnsupportedEncodingException {
        //String encode = "Nguyá»n ThÃ nh Trung";
        byte[] bytes = encode.getBytes("ISO-8859-1");
        String decode = new String(bytes);
        return decode;
    }
    public static UserError checkValidInfoUser(String userName, String password, String confirmedPassword, String userFullName, String userAddress, String userPhone) {
        UserError userError = new UserError("", "", "", "", "", "", "", "", "");
        boolean check = true;

        if (password == null || password.equals("")) {
            password = "********";
        }
        if (confirmedPassword == null || confirmedPassword.equals("")) {
            confirmedPassword = "********";
        }

        if ( userName.length() > 12||userName.length() < 5) {
            userError.setUserNameError("Username must be [5,12]");
            check = false;
        }
        if(userFullName.length()> 22 || userFullName.length() <5){
            userError.setUserFullNameError("userFullName must be in [5,22]");
        }
        if(userAddress.length()>22 || userAddress.length() <5){
            userError.setUserAddressError("userAddress must be in[5,22]");
        }
        if(userPhone.length() >12 || userPhone.length() < 10){
            userError.setUserPhoneError("Phone number must be in[10,12]");
        }
        
        if ((password.length() < 8 || password.length() > 12) && !password.equals("********")) {
            userError.setUserPasswordError("Password must have length between 8 and 12");
            check = false;
        }
        if (!password.equals("********") && !confirmedPassword.equals(password)) {
            userError.setConfirmError("Confirmed password mismatch!");
            check = false;
        }

        if (check) {
            userError = null;
        }
        return userError;
    }
}
