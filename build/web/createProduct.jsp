<%-- 
    Document   : createProduct
    Created on : Jul 12, 2021, 10:52:39 PM
    Author     : WilliamTrung
--%>

<%@page import="Shopping.ProductDAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">       
        <style><%@include file="css/header.css" %></style>
        <style><%@include file="css/mystyle.css" %></style>
        <style><%@include file="css/table.css" %></style>  
        <title>Create new Product</title>
    </head>
    <body>
        <%@include  file="headerAdmin.jsp" %>  
        <%
            ProductDAO dao = new ProductDAO();
            request.setAttribute("CATEGORIES", dao.getCategories());
        %>
        <section name="col-60">
            <form action="MainController">
                <div class="container">
                    <label for="productID"><b>Product ID</b></label>
                    <c:if test="${requestScope.ERROR_MESSAGE.id eq 'id'}">
                        ${requestScope.ERROR_MESSAGE.errorDetail} ${requestScope.ERROR_MESSAGE.errorDetail}
                    </c:if>
                    <input type="text" placeholder="Product's id..." name="productID" required>
                    
                    <label for="productName"><b>Product Name</b></label>
                    <c:if test="${requestScope.ERROR_MESSAGE.id eq 'name'}">
                        ${requestScope.ERROR_MESSAGE.errorDetail} ${requestScope.ERROR_MESSAGE.errorDetail}
                    </c:if>
                    <input type="text" placeholder="Product's name..." name="productName" required>
                    
                    <label for="productImage"><b>Product Image</b></label>
                    <c:if test="${requestScope.ERROR_MESSAGE.id eq 'img'}">
                        ${requestScope.ERROR_MESSAGE.errorDetail} ${requestScope.ERROR_MESSAGE.errorDetail}
                    </c:if>
                    <input type="text" placeholder="Navigate an image..." name="productImage" required>
                    
                    <label for="category"><b>Product's Category</b></label>
                    <select name="category">                         
                        <option disabled selected>Choose category</option>
                        <c:forEach var="category" items="${CATAGORIES}">
                            <option value="${catagory}">${catagory}</option>
                        </c:forEach>
                    </select>
                    <label for="price"><b>Price</b></label>
                    <input type="number" step="1000" min="0" name="price" required>
                    <label for="quantity"><b>Product's quantity</b></label>
                    <input type="number" min="1" placeholder="Product's quantity..." name="quantity" required>
                    <label for="importDate"><b>Import Date</b></label>
                    <input type="date" name="importDate" />
                    <label for="usingDate"><b>Using Date</b></label>
                    <input type="number" name="usingDate" step="1" min="0"/>
                    <hr>
                    <button type="reset" >Reset</button>
                    <button type="submit" name="action" value="Create Product">Register</button>
                </div>
            </form>
        </section>  
    </body>
</html>
