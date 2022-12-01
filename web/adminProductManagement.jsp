<%-- 
    Document   : admin_product_management
    Created on : Mar 08, 2022, 10:00:17 AM
    Author     : LiamVu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">       
        <style><%@include file="css/header.css" %></style>
        <style><%@include file="css/mystyle.css" %></style>
        <style><%@include file="css/searchbar.css" %></style>
        <style><%@include file="css/table.css" %></style>
        <title>ProductManagement Page</title>
    </head>
    <body>
        <%@include  file="headerAdmin.jsp" %>    
        <section id="productManagement">
            <%@include  file="adminProductChange.jsp" %>       
        </section>
    </body>
</html>
