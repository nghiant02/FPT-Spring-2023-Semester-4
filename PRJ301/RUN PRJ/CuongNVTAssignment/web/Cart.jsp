<%-- 
    Document   : Cart
    Created on : 08-Jul-2022, 17:57:50
    Author     : tucuo
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Your Cart</title>
    </head>
    <body>
        <h1>YOUR CART</h1>
        <h3> Welcome, ${sessionScope.Account.getFullName()}</h3>
        <form method = "POST" action = "CartServlet">
            <input type = "SUBMIT" name = "btAction_Cart" value = "Back" > 
        </form>
        <c:if test = "${empty sessionScope.Cart.getItems()}">
            <h3> Your Cart is current empty.</h3>
        </c:if>
        <c:if test = "${not empty sessionScope.Cart.getItems()}">
            <form action = "DeleteCartServlet" method = "GET" id = "DeleteCart"></form>
            <table border="1">
                <thead>
                    <tr>
                        <th>No. </th>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Author</th>
                        <th>Description</th>
                        <th>Image</th>
                        <th>Price</th>
                        <th>Purchase Quantity</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var = "current" items = "${sessionScope.Cart.getItems()}" varStatus = "status">
                        <tr>
                                <td>${status.count}</td>
                                <td>${current.getKey()}</td>
                                <td>
                                    <c:forEach var = "details" items = "${sessionScope.CartDetails}" >
                                       <c:if test = "${current.getKey() == details.getID()}"> 
                                          ${details.getName()}
                                        </c:if> 
                                    </c:forEach>

                                </td>
                                <td><c:forEach var = "details" items = "${sessionScope.CartDetails}" >
                                       <c:if test = "${current.getKey() == details.getID()}"> 
                                          ${details.getAuthor()}
                                        </c:if> 
                                    </c:forEach></td>
                                <td><c:forEach var = "details" items = "${sessionScope.CartDetails}" >
                                       <c:if test = "${current.getKey() == details.getID()}"> 
                                      ${details.getDescription()}
                                        </c:if> 
                                    </c:forEach></td>
                                <td><c:forEach var = "details" items = "${sessionScope.CartDetails}" >
                                       <c:if test = "${current.getKey() == details.getID()}"> 
                                         ${details.getImgUrl()}
                                        </c:if> 
                                    </c:forEach></td>
                                <td><c:forEach var = "details" items = "${sessionScope.CartDetails}" >
                                       <c:if test = "${current.getKey() == details.getID()}"> 
                                       ${details.getPrice()}
                                        </c:if> 
                                    </c:forEach></td>
                                <td>  ${current.getValue()} </td>
                                <td> <input type ="CHECKBOX" name = "DeleteValue" value ="${current.getKey()}" form = "DeleteCart"> </td>
                        </tr>
                    </c:forEach>
                      <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td> </td>
                                <td> <input type ="SUBMIT" name = "btAction_Cart" value ="Delete" form ="DeleteCart"> </td>
                        </tr>  
                </tbody>
            </table>
        </c:if>  
            <form action = "DispatchServlet" >
                <input type = "SUBMIT" name = "btAction" value = "Check Out">
            </form>
            <c:if test = "${requestScope.Success_CheckOut}" >
                <h3> Check Out Successfully. </h3>   
            </c:if>
    </body>
    
</html>
