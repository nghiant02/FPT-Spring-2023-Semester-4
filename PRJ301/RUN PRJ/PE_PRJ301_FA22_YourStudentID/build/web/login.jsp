<%-- 
    Document   : login
    Created on : 30-10-2022
    Author     : hd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <form action="MainController" method="post">
            <div>Username: <input type="text" name="username"></div>
            <div>Password: <input type="password" name="password"></div>          
            <input type="submit" name="action" value="Login">
        </form>
        <h1>${ERROR_MESSAGE}</h1>

    </body>

</html>

