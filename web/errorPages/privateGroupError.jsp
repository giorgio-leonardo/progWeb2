<%-- 
    Document   : privateGroupError
    Created on : 29-dic-2013, 10.34.26
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
             <strong>Questo è un gruppo privato.</strong>
             <P>Non puoi accedere se non sei loggato ed iscritto al gruppo</p>
            <br>
        </div>
        
        <%@ include file="../pageFormat/closeGrid.html" %>
        <%@ include file="../pageFormat/footer.html" %>
    </body>
</html>
