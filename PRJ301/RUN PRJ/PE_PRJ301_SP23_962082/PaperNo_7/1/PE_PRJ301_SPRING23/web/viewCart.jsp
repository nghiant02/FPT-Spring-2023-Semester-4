<%-- 
    Document   : viewCart
    Created on : Feb 28, 2023, 9:07:46 PM
    Author     : hd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart Page</title>
    </head>
    <body>
        <c:set var="list" value="${sessionScope.LISTCART}"/>
        <h1>Your Shopping cart</h1>
        <form action="MainController">
            <table border="1">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Product ID</th>
                        <th>Product Name</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Total</th>
                        <th>Update</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${list}" var="pro" varStatus="counter">
                        <tr>
                            <input type="hidden" name="ida" value="${pro.productID}">
                            <th>${counter.count}</th>
                            <td>${pro.productID}</td>
                            <td>${pro.productName}</td>
                            <td>${pro.description}</td>
                            <td>${pro.price}</td>
                            <td><input name="qua" type="number" value="1"></td>
                            <td>${pro.price}</td>
                            <td><input type="submit" name="action" value="Update"></td>
                             
                        </tr> 
                    </c:forEach>
                </tbody>
            </table>
        </form>
                            <a href="shopping.jsp">Add more</a>
    </body>
</html>
