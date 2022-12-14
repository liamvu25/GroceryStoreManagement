<%-- 
    Document   : shopping
    Created on : Mar 06, 2021, 12:16:44 AM
    Author     : LiamVu
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">       
        <style><%@include file="css/header.css" %></style>
        <style><%@include file="css/mystyle.css" %></style>
        <style><%@include file="css/searchbar.css" %></style>
        <title>FPT GroceryStore</title>
    </head>
    <body>
        <%@include file="headerUser.jsp"%>     
        <c:if test="${sessionScope.LOGIN_USER.role eq 'ad'}">
            <h1>
                Admin cannot shopping while on duty!
            </h1>
        </c:if>
        <c:if test="${sessionScope.LOGIN_USER.role ne 'ad'}">     

            <form class="search" action="MainController" style="margin:auto;max-width:300px">
                <input type="text" placeholder="Search by Name" name="search">
                <button type="submit" name="action" value="LoadItem"><i class="fa fa-search"></i></button>
            </form>
            <h3><b>${requestScope.ERROR_MESSAGE.errorDetail} ${requestScope.ERROR_MESSAGE.errorMessage}</b></h3>
            <c:forEach var="grocery" varStatus="counter" items="${requestScope.LIST_ITEMS}">
                <form action="MainController" style="display: inline-block; margin-bottom: 20px;">
                    <div class="container" style="width:700px;height: 460px;margin: auto;">
                        <div class="row">
                            <div class="col-50">
                                <h3>${grocery.productName}

                                </h3>
                                <table border="1">
                                    <tbody>
                                        <tr>
                                            <td><b>Category</b></td>
                                            <td>${grocery.category}</td>
                                        </tr>
                                        <tr>
                                            <td><b>Import Date</b></td>
                                            <td>${grocery.importDate}</td>
                                        </tr>
                                        <tr>
                                            <td><b>Using Date</b></td>
                                            <td>${grocery.usingDate}</td>
                                        </tr>
                                        <tr>
                                            <td><b>Price</b></td>
                                            <td>${grocery.price}VND</td>
                                        </tr>
                                        <tr>
                                            <td><b>Status</b></td>
                                            <td>${grocery.status eq '1' ? "Available":"Unavailable"}</br> In storage: ${book.quantity}</td>
                                        </tr>
                                        <c:if test="${grocery.status eq '1'}">
                                            <tr id="quantityInput">
                                                <td>Quantity</td>
                                                <td><input id="quantityIn"type="number" name="quantityToCart" min="0" max="${grocery.quantity-sessionScope.CART.getCart()[grocery.productID].quantity}"  value="0"></td>
                                            </tr>
                                        </c:if>
                                    </tbody>
                                    <input type="hidden" name="productName" value="${grocery.productName}"/>
                                    <input type="hidden" name="catagory" value="${grocery.catagory}"/>
                                    <input type="hidden" name="price" value="${grocery.price}"/>
                                    <input type="hidden" name="quantity" value="${grocery.quantity}"/>
                                    <input type="hidden" name="usingDate" value="${grocery.usingDate}"/>
                                    <input type="hidden" name="quantity" value="${grocery.importDate}"/>
                                    <input type="hidden" name="search" value="${param.search}"/>
                                    
                                    <input type="hidden" name="productID" value="${grocery.productID}"/>
                                    <input class="btn" style="" type="submit" name="action" value="Add to Cart"/>
                                </table>

                            </div>
                            <div class="wrapper">
                                <img class="center" src="image/${grocery.image}" style="max-height: 300px;width: auto;display: inline-block"/>
                            </div>
                        </div>
                    </div>
                </form>
            </c:forEach>
        </c:if>
    </body>
</html>
