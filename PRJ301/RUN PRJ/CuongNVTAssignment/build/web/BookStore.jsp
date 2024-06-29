<%-- 
    Document   : BookStore
    Created on : 08-Jul-2022, 15:06:19
    Author     : tucuo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Store</title>
    </head>
    <body>
        <h1>BOOK STORE</h1>
        <h3> Welcome, ${sessionScope.Account.getFullName()}</h3>
        <form method = "POST" action = "DispatchServlet">
            <input type = "SUBMIT" name = "btAction" value = "Back" >
            <input type = "SUBMIT" name = "btAction" value = "View your Cart">    
        </form>
        <br>
        <form action = "BookStoreServlet" method = "POST">
            Search: <input type = "TEXT" name = "txtSearch" value = "${requestScope.SearchValue}">
            <input type = "SUBMIT" name = "btAction_BookStore" value ="Search">
            <br> Note: Type "all" if you want to display all Books. 
        </form>
        <c:if test = "${!requestScope.FirstTime}">
            <c:if test = "${empty requestScope.Books}">
                <h2> No Record Matched. </h2>
            </c:if>
            <c:if test = "${not empty requestScope.Books}">
                <form action = "PutItemsToCartServlet" method = "GET" id = "PutItemsToCart" > </form> 
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
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var = "Book" items = "${requestScope.Books}" varStatus = "status">
                            <tr>
                                <td>${status.count}</td>
                                <td>${Book.getID()}</td>
                                <td>${Book.getName()}</td>
                                <td>${Book.getAuthor()}</td>
                                <td>${Book.getDescription()}</td>
                                <td></td>
                                <td>${Book.getPrice()}</td>
                                <td>
                                    <input type = "TEXT" name = "${Book.getID()}_Quantity" value = "${requestScope.QuantityValues.get(Book)}" form = "PutItemsToCart">    

                                </td>
                            </tr>
                        </c:forEach>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td>
                        <input type = "HIDDEN" name = "SearchValue" value = "${requestScope.SearchValue}" form = "PutItemsToCart">
                        <input type = "SUBMIT" name = "btAction_BookStore" value = "Purchase" form = "PutItemsToCart">    

                    </td>
                </tbody>
          
            </table>

        </c:if>
                 <c:if test = "${requestScope.Fault_WrongNumberFormat}">
            <h3 style = "color: red" > Unexpected error occurred. Please type positive numbers only in Purchase Quantity Column.      </h3> 
                </c:if>
        <c:if test = "${requestScope.Success_Purchase}">
            <h3> Purchase successfully. Please check your Cart for more details.</h3>
            </c:if>
    </c:if>
     
            
</body>
</html>
