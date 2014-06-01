<%-- 
    Document   : loginError
    Created on : 23-dic-2013, 9.58.43
    Author     : giorgio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
       <%@ include file="/headService.html" %>
    </head>
    <body style="background-color: #ffffff">
        
        <%@ include file="../pageFormat/header.html" %>
        <%@ include file="../pageFormat/openGrid.html" %>
        
        <div class="alert alert-warning" align="center">
             <strong>Nome utente o password errati!</strong>
            <br>
        </div>
        
        <div class="row">
            <div class="col-md-4" style="background-color:#c0c0c0; border:0px"></div>
            <div class="col-md-4" style="background-color:#c0c0c0; border:0px">
                <form class="form-signin" action="ControlServlet" method="post" name="data">
                    
                    <p align="center">Password dimenticata?</p>
                    <p align="center">Inserisci il tuo Nome utente qui sotto.</p>
                    <p align="center">Ti verrà inviata una mail con la tua password.</p>

                    <h2 class="form-signin-heading">User name</h2>
                    <input type="hidden" name="op" value="loginError">
                    <input type="text" class="form-control" placeholder="User name" name="userName" autofocus>
                    <br>
                    <button class="btn btn-lg btn-primary btn-block" name="btn" value="leInvia" type="submit">Invia</button>
                    <button class="btn btn-lg btn-primary btn-block" name="btn" value="leAnnulla" type="submit">Annulla</button>

                </form>
            </div>
            
            <div class="col-md-4" style="background-color:#c0c0c0; border:0px"></div>
        </div>
        
        
        <%@ include file="../pageFormat/closeGrid.html" %>
        <%@ include file="../pageFormat/footer.html" %>
    </body>
    
</html>

