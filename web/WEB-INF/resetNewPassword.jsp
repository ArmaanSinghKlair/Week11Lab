<%-- 
    Document   : resetNewPassword
    Created on : Nov 24, 2020, 4:44:19 PM
    Author     : 839645
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reset password</title>
    </head>
    <body>
        <h1>Enter a new password</h1>
        <form action="/reset" method="post">
            <input type="hidden" name="action" value="resetPassword"/>
            <label for="password">
                <input id="email" type="text" name="password" />
            </label>
            <input type="submit" name="submit" value="Submit"/>
        </form>
            
           <c:if test="${errMsg != null}">
                <section class="errMsg"><c:out value='${errMsg}'></c:out></section>
         </c:if>
                
         <c:if test="${infoMsg != null}">
            <section class="infoMsg"><c:out value='${infoMsg}'></c:out></section>
         </c:if>    
    </body>
</html>
