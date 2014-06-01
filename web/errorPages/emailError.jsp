<%-- 
    Document   : emailSuccess
    Created on : 25-dic-2013, 9.58.05
    Author     : giorgio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/headService.html" %>
    </head>
    <body>
        <%@ include file="../pageFormat/header.html" %>
        <%@ include file="../pageFormat/openGrid.html" %>
        
        <div class="alert alert-danger" align="center">
            <strong>Errore nell'invio dell'email</strong>
            <br>
        </div>
        
        <%@ include file="../pageFormat/closeGrid.html" %>
        
        <meta http-equiv="refresh" content="2; url=index.html" />
    </body>
</html>
