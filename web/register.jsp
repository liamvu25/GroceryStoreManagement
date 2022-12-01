<%-- 
    Document   : createUser
    Created on : Feb 25, 2022, 12:49:41 AM
    Author     : Admins
--%>

<%@page import="Users.UserError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Page</title>
    </head>
    <body>
        <h1>Create new user!</h1>
        <%
            UserError userError = (UserError)request.getAttribute("USER_ERROR");
            if(userError == null){
                userError = new UserError();
            }
        %>
        
        <form action="MainController" method="POST">
            User ID<input type="text" name="userName" required=""/><%=userError.getUserNameError()%> </br>
            Full Name<input type="text" name="userFullName" required=""/><%=userError.getUserFullNameError()%></br>
            Address<input type="text" name="userAddress", required=""><%=userError.getUserAddressError()%></br>
            Phone<input type="number" name="userPhone", required=""><%=userError.getUserPhoneError()%></br>
            <label for="userBirthday"><b>Birthday</b></label>
            <input type="date" value="${sessionScope.LOGIN_USER.userBirthday}" name="userBirthday" id="userBirthday" required></br>
            email<input type="email" name="userEmail", required=""><%=userError.getUserEmailError()%></br>
            Password<input type="password" name="password"required=""/><%=userError.getUserPasswordError()%></br>
            Confirm<input type="password" name="confirm"required=""/><%=userError.getConfirmError()%></br>
            
            <input type="submit" name="action" value="Register"/>
            <input type="reset" value="Reset"/>
            
        </form>
    </body>
</html>
