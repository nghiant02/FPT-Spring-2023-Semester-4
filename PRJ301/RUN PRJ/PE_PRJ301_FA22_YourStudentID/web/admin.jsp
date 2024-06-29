<%-- 
    Document   : foodList
    Created on : 30-10-2022
    Author     : hd
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <c:if test="${sessionScope.LOGIN_USER.roleID !="ADMIN"}">
        <c:redirect url="login.jsp">
        </c:redirect>
    </c:if>
    <c:set var="list" value="${sessionScope.LIST_PRODUCT}"/>
    <title>Food List Page</title>
</head>
<body>

    <h1>Welcomes , ${sessionScope.LOGIN_USER.fullName}</h1>
    <form action="MainController">
        <input type="submit" name="action" value="Logout">
    </form>
    <form action="MainController">
        <input type="text" name="txtSearch" placeholder="Input name food to search">
        <input type="submit" name="action" value="Search">
    </form>


    <table border="1">
        <thead>
            <tr>
                <th>No</th>
                <th>ID</th>
                <th>Name</th>
                <th>Price</th>
                <th>Cooking Time</th>
                <th><Description</th>
            <th>Status</th>
            <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="pro" varStatus="counter">
                    <tr>
                        <th>${counter.count}</th>
                        <td>${pro.id}</td>
                        <td>${pro.name}</td>
                        <td>${pro.description}</td>
                        <td>${pro.price}</td>
                        <td>${pro.cookingTime}</td>
                        <td>${pro.status}</td>
                        <td><button>DELETE</button></td>
                    </tr> 
            </c:forEach>

            </tbody>
    </table>


</body>
</html>
