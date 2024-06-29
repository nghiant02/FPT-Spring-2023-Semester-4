<%-- 
    Document   : Search
    Created on : 04-Jul-2022, 16:15:35
    Author     : tucuo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome</title>
    </head>
    <body>
        <h1>WELCOME, ${sessionScope.Account.getFullName()}</h1>
        <h3> Your Role:
            <c:if test = "${sessionScope.Account.getRole()}">
                Administrator
            </c:if>
            <c:if test = "${!sessionScope.Account.getRole()}">
                Basic User
            </c:if>    
        </h3>
        <form action = "DispatchServlet" method= "POST">
            <input type ="SUBMIT" name ="btAction" value ="Sign Out"/>
        </form>
        <br>
        <form method="POST" action = "DispatchServlet" id="Option"></form>
        <table border="1">
            <thead>
                <tr>
                    <th>Option</th>
                    <th>Description</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td> <input type = "SUBMIT" name = "btAction" value = "Manage User Accounts" form = "Option"> </td>
                    <td> Manage User Accounts : View(Everyone) / Update & Search(Administrator only)</td>
                </tr>
                <tr>
                    <td> <input type = "SUBMIT" name = "btAction" value = "Go to the Book Store" form = "Option"> </td> </td>
                    <td> Go to the Book Store, the place where you can purchase Books.</td>
                </tr>
            </tbody>
        </table>

      
    </body>
</html>
