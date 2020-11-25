<%-- 
    Document   : reset
    Created on : Nov 24, 2020, 12:29:44 PM
    Author     : 839645
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reset</title>
        <style>
            .errMsg{
                color: red;
            }
            .infoMsg{
                color: appworkspace;
            }
        </style>
    </head>
    <body>
        <h1>Reset Password</h1>
        <p>Please enter your email address to reset your password</p>
        
        <form action="/reset" method="post">
            <input type="hidden" name="action" value="reset"/>
            <label for="email">
                Email Address: 
                <input id="email" type="email" name="email" value="${email}" />
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
