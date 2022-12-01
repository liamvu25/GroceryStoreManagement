<%-- 
    Document   : admin_product_change
    Created on : Mar 08, 2022, 9:33:54 AM
    Author     : LiamVu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product settings</title>
    </head>
    <body>
        <form class="search" action="MainController" style="margin:auto;max-width:300px">
            <button type="submit" name="action" value="CreateProduct"><i class="fa fa-plus"></i></button>
            <input type="text" value="Create new product" readonly="">
        </form>
        <form class="search" action="MainController" style="margin:auto;max-width:300px">
            <input type="text" placeholder="Search by Name" name="search">
            <button type="submit" name="action" value="SearchProduct"><i class="fa fa-search"></i></button>
        </form>
        <c:forEach var="grocery" varStatus="counter" items="${requestScope.LIST_ITEMS}">

            <div class="container" style="width:700px;height: 460px;margin-right: 20px;margin-left: 20px;margin-bottom: 20px;">
                <form action="MainController" style="display: inline-block; margin-bottom: 20px;">
                    <div class="row">
                        <div class="col-50">
                            <h3 style="margin: 0%">
                                <input type="text" name="productName" value="${grocery.productName}" style="margin: 0%"/>                              
                            </h3>
                               <input type="text" name="productImage" value="${grocery.image}" style="margin: 0%"/>                        
                            <table border="1">
                                <tbody>
                                    <tr>                                      
                                        <td><b>Category</b></td>
                                        <td>
                                            <select name="category">                         
                                                <option disabled selected>${grocery.category}</option>
                                                <c:forEach var="category" items="${CATEGORIES}">
                                                    <option value="${category}">${category}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Price (VND)</b></td>
                                        <td><input type="number" min="0" step="1000" value="${grocery.price}" name="price"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>importDate (mm/dd/yyy)</b></td>
                                        <td><input type="Date" value="${grocery.importDate}" name="importDate"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>usingDate</b></td>
                                        <td><input type="number" min="0"  value="${grocery.usingDate}" name="usingDate"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Status</b></td>

                                        <td>
                                            <form action="MainController">
                                                <input type="hidden" name="productID" value="${grocery.productID}"/>
                                                <input type="hidden" name="search" value="${param.search}"/>                              
                                                <input type="hidden" name="status" value="${grocery.status}"/>   
                                                <input type="hidden" name="action" value="ChangeStatusProduct"/>
                                                <input id="activation" type="checkbox" ${grocery.status == '1' ? "checked=''" : "unchecked=''"} onChange="this.form.submit()">
                                                <label for="activation">${grocery.status eq '1' ? "Available":"Unavailable"}</br> In storage: ${grocery.quantity}</label>
                                            </form></td>
                                    </tr>
                                    <tr id="quantityInput">
                                        <td><b>Quantity adding</b></td>
                                        <td><input id="quantityIn"type="number" name="quantityAdding" min="0" value="0"></td>
                                    </tr>
                                </tbody>
                                <input type="hidden" name="productName" value="${grocery.productName}"/>
                                <input type="hidden" name="category" value="${grocery.category}"/>
                                <input type="hidden" name="quantity" value="${grocery.quantity}"/>
                                <input type="hidden" name="search" value="${param.search}"/>
                                <input type="hidden" name="productID" value="${grocery.productID}"/>
                                <input type="hidden" name="productImage" value="${grocery.image}"/>
                                <input class="btn" style="" type="submit" name="action" value="ModifyItem"/>
                            </table>

                        </div>
                        <div class="wrapper">
                            <img class="center" src="image/${grocery.image}" style="max-height: 300px;width: auto;max-width: 250px;display: inline-block"/>                          
                        </div>
                    </div>
                </form>
            </div>





        </c:forEach>

    </body>
</html>
