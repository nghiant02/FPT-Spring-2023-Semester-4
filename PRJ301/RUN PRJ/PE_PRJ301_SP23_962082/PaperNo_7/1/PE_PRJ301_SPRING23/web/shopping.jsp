<%-- 
    Document   : index
    Created on : Feb 28, 2023, 9:01:26 PM
    Author     : Hoadnt
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping Page</title>
    </head>
    <c:set var="list" value="${sessionScope.LIST_PRODUCT}"/>
    <c:set var="mess" value="${sessionScope.mess}"/>
    <body>
        <h1>Welcome to Thinh's shop</h1>
        <form action="MainController">
            <input type="submit" name="action" value="Get all">
            <input type="submit" name="action" value="View cart">
        </form>

        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>Product ID</th>
                    <th>Product Name</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Add</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${list}" var="pro" varStatus="counter">
                    <tr>
                        <td>${counter.count}</td>
                        <td>${pro.productID}</td>
                        <td>${pro.productName}</td>
                        <td>${pro.description}</td>
                        <td>${pro.price}</td>
                <form action="MainController">
                    <td><input type="submit" name="action" value="Add">
                        <input type="hidden" name="id" value="${pro.productID}"></td>    
                </form>
            </tr> 
        </c:forEach>
    </tbody>
</table>
<h1>${mess}</h1>
</body>
</html>
