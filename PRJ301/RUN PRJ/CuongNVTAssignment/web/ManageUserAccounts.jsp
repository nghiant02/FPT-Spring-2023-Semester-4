
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage User Accounts</title>
    </head>
    <body>
        <h1>MANAGE USER ACCOUNTS</h1>
        <h3> Your Role:
            <c:if test = "${sessionScope.Account.getRole()}">
                Administrator
            </c:if>
            <c:if test = "${!sessionScope.Account.getRole()}">
                Basic User
            </c:if> 
                <form method = "POST" action = "DispatchServlet">
                    <input type = "SUBMIT" name = "btAction" value = "Back" >
                </form>
        </h3>

        <form action = "ManageUserAccountsServlet" method = "POST">
            Search: <input type = "TEXT" name = "txtSearch" value = "${requestScope.SearchValue}">   
            <input type = "SUBMIT" name = "btAction_ManageUserAccounts" value = "Submit">
            <br> Note: Type "all" if you want to display all User Accounts. 
        </form>
        <c:if test = "${!requestScope.FirstTime}">
            <c:if test = "${sessionScope.Account.getRole()}">
                <c:if test = "${empty requestScope.UserAccounts}">
                    <h2> No Record Matched.</h2>   
                </c:if>
                <c:if test = "${not empty requestScope.UserAccounts}">
                    <form action = "DeleteUserAccountsServlet" method = "POST" id = "DeleteForm">    </form>

                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Username</th>
                                <th>Role</th>
                                <th>Full Name</th>
                                <th>Delete</th>
                                <th>Update</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var = "current" items="${requestScope.UserAccounts}" varStatus = "status" >          

                                <tr>  
                                    <td> ${status.count} </td>
                                    <td> ${current.getUsername()}</td>
                                    <td> <input type="CHECKBOX" name = "Role"  onclick="return false;"
                                                <c:if test="${current.getRole()}">
                                                    checked
                                                </c:if>
                                                >
                                    </td>
                                    <td><input type = "TEXT" name = "FullName" value = "${current.getFullName()}" form = "${current.getUsername()}"></td> 
                                    <td> 
                                        <input type = "CHECKBOX" name = "DeleteValue" 
                                               value = "${current.getUsername()}" form = "DeleteForm">

                                    </td>
                                    <td> <form action = "UpdateUserAccountServlet" method = "POST" id = "${current.getUsername()}"> </form>
                                        <input type = "HIDDEN" name = "Username" value= "${current.getUsername()}" form = "${current.getUsername()}">
                                        <input type = "HIDDEN" name = "SearchValue" value= "${requestScope.SearchValue}" form = "${current.getUsername()}">
                                        <input type = "SUBMIT" name = "btAction_ManageUserAccounts" value = "Update" form = "${current.getUsername()}"></td>
                                    </tr>

                            </c:forEach>
                            <tr>
                                <td>  </td>
                                <td> </td>
                                <td> </td>
                                <td></td> 
                                <td> <input type = "HIDDEN" name = "SearchValue" value= "${requestScope.SearchValue}" form = "DeleteForm">
                                    <input type = "SUBMIT" name = "btAction_ManageUserAccounts" value = "Delete" form = "DeleteForm"></td>
                                <td> </td>
                            </tr>
                        </tbody>
                    </table>
                </c:if>
            </c:if>

            <c:if test = "${!sessionScope.Account.getRole()}">
                <c:if test = "${empty requestScope.UserAccounts}">
                    <h2> No Record Matched.</h2>   
                </c:if>
                <c:if test = "${not empty requestScope.UserAccounts}">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Username</th>
                                <th>Role</th>
                                <th>Full Name</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var = "current" items="${requestScope.UserAccounts}" varStatus = "status" >

                                <tr>
                                    <td> ${status.count} </td>
                                    <td> ${current.getUsername()} </td>
                                    <td> 

                                        <input type="CHECKBOX" name = "Role"  onclick="return false;"
                                               <c:if test="${current.getRole()}">
                                                   checked
                                               </c:if>
                                               >
                                    </td>
                                    <td>${current.getFullName()}></td> 
                                </tr>

                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </c:if>
        </c:if>



        <c:if test = "${not empty requestScope.Fault_DeleteCurrentUser}">
            <h3 style = "color:red;"> ${requestScope.Fault_DeleteCurrentUser}</h3>
        </c:if>
    </body>
</html>
