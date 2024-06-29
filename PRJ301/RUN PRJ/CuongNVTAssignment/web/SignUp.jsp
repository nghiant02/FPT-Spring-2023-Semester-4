<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up</title>
    </head>
    <body>   
        <h1>SIGN UP</h1>
        <form action="SignUpServlet" method="POST">
            New Username*: <input type = "TEXT" name ="txtNewUsername" value ="${requestScope.NewUsername}"> (6-20 Characters)<br>
            <c:if test="${not empty requestScope.RFC.getUsername_Fault_Msg()}" >
            <div style = "color:red"> 
                ${requestScope.RFC.getUsername_Fault_Msg()} </br>
            </div>
            </c:if>
            New Password*: <input type ="PASSWORD" name ="txtNewPassword" value =""> (6-20 Characters) <br>
            <c:if test="${not empty requestScope.RFC.getPassword_Fault_Msg()}" >
            <div style = "color:red"> 
                ${requestScope.RFC.getPassword_Fault_Msg()} </br>
            </div>
            </c:if>
            Confirm Password*: <input type ="PASSWORD" name ="txtConfirmPassword" value =""> <br>
            <c:if test="${not empty requestScope.RFC.getConfirm_Fault_Msg()}" >
            <div style = "color:red"> 
                ${requestScope.RFC.getConfirm_Fault_Msg()} </br>
            </div>
            </c:if>
            Your Full Name*: <input type ="TEXT" name = "txtFullName"  value ="${requestScope.FullName}"> (2-40 Characters) <br>
            <c:if test="${not empty requestScope.RFC.getFullName_Fault_Msg()}" >
            <div style = "color:red"> 
                ${requestScope.RFC.getFullName_Fault_Msg()} </br>
            </div>
            </c:if>
            <input type = "SUBMIT" name ="btAction" value = "Submit">
            <input type = "RESET" name = "Reset"> 
        </form> <br>
            <form action = "DispatchServlet" method = "POST" >
                <input type ="SUBMIT" name = "btAction_SignUp" value = "Back to Log In">
            </form>
    </body>
</html>
