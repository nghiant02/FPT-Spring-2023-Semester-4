<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
    </head>
    <body>
        <h1>SEARCH KITE</h1>
        <form action="SearchServlet">
            Search:<input type = "TEXT" name = "txtSearch" value ="${requestScope.searchValue}">
            <input type = "SUBMIT" name = "btAction" value = "Search">
            </form>
            <c:if test = "${empty requestScope.kites}">
                CUONG
            </c:if>
            <c:if test = "${not empty requestScope.kites}">
                <table border="1">
                    <thead>
                        <tr>
                            
                            <th>No. </th>
                            <th>Name</th>
                            <th>Size</th>
                            <th>Color</th>
                            <th>Level</th>
                            <th>Outline</th>
                            <th>Status</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var = "kite" items = "${requestScope.kites}" varStatus = "status">
                            
                      <%-- for (KiteDTO kite : requestScope.kites) --%>
                            <tr>
                            <td> ${status.count}</td>
                            <td>${kite.id}</td>
                            <td>${kite.name}</td>
                            <td>${kite.color}</td>
                            <td>${kite.level}</td>
                            <td>${kite.outline}</td>
                            <td>
                             <input type = "CHECKBOX" name = "txtStatus" value = "checked" form = "updateServlet_${kite.id}" 
                                    <c:if test = "${kite.status}">
                                    checked
                                    </c:if>
                                    >
                                    </td>
                            <td> 
                                <input type = "TEXT" name = "txtQuantity" value = "${kite.quantity}" form = "updateServlet_${kite.id}"/> 
                            </td>
                            <td> 
                                <input type = "TEXT" name = "txtPrice" value = "${kite.price}" form = "updateServlet_${kite.id}"/> 
                                
                            </td>
                            <td> <input type = "HIDDEN" name ="txtID" value ="${kite.id}" form = "updateServlet_${kite.id}" > 
                                <input type = "HIDDEN" name ="txtSearch" value ="${param.txtSearch}" form = "updateServlet_${kite.id}" >  
                                <form action = "UpdateServlet" id = "updateServlet_${kite.id}"> </form>
                                 <input type = "SUBMIT" name = "btAction" value = "Update" form = "updateServlet_${kite.id}"/>
                            </td>
                        </tr>
                        </c:forEach>
                        
                        
                        
                     </tbody>
                </table>
             
            </c:if>    
            <c:if test = "${requestScope.error}">
                <h3 style = "color:red"> LOI CMNR </h3>
            </c:if>
    </body>
</html>
