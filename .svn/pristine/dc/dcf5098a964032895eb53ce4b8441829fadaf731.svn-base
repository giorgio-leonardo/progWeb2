<%-- 
    Document   : landingPage
    Created on : 20-dic-2013, 14.05.32
    Author     : giorgio
--%>

<%@ page contentType = "text/html" pageEncoding = "UTF-8"%>
<%@ page import = "java.util.List"%>
<%@ page import = "beans.Invitation"%>

<!DOCTYPE html>
<html lang="it">
    <head>
        <%@ include file="/head.html" %>
        
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <link rel="stylesheet" href="<c:url value="css/jquery.dataTables.css"/>"/>
        <script type="text/javascript" src='<c:url value="js/jquery.js"/>'></script>
        <script type="text/javascript" src='<c:url value="js/jquery.dataTables.min.js"/>'></script>
    </head>

    <body>
        <%@ include file="/pageFormat/header.html" %>
        <%@ include file="/pageFormat/openGrid.html" %>
        
        <jsp:useBean id = "user" class = "beans.User" scope = "session"/>
        
        <div class="alert alert-success" align="center">
            Benvenuto <strong><jsp:getProperty name = "user" property="userName" /></strong>
            <br>
            La tua ultima visita risale al<br>
            <jsp:getProperty name = "user" property="userLastLogin" />
        </div>
        
        <div class="navbar navbar-inverse">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">Secondo progetto</a>
                </div>    
                <div class="navbar-collapse collapse">
                    <form class="navbar-form navbar-left" action="ControlServlet" role="search" method="post">
                        <input type="hidden" name="op" value="landing">
                        <button type="submit" class="btn btn-default" name="btn" value="creaGruppo">Crea un gruppo</button>
                        <button type="submit" class="btn btn-default" name="btn" value="gruppi">Gruppi a cui partecipi</button>
                        <button type="submit" class="btn btn-default" name="btn" value="moderatorPage">Pagina moderatore</button>
                        <button type="submit" class="btn btn-default" name="btn" value="logout">Logout</button>
                    </form>
                </div>
                    
            </div>
        </div>
        
        <c:choose>
            <c:when test = "${invitations.size() == 0}">
                Non ci sono inviti
            </c:when>
            <c:otherwise>
                 <script>
                    $(document).ready
                    (
                        function()
                        {
                            $("#invitationsTable").dataTable();
                        }
                    );
                </script>
                
                <h3 align="center"><span class="label label-info">Sei stato invitato nei seguenti gruppi</span></h3>
                <br><br>
                <form action = "ControlServlet" method = "post">
                <div class="row" style="background-color: #c0c0c0; border: none">
                    <div class="col-md-10" style="background-color: #c0c0c0; border: none">
                        <input type="hidden" name="op" value="acceptInvitation">
                        <table id = "invitationsTable">
                            <thead>
                                <tr>
                                    <th>
                                        Gruppo
                                    </th>
                                    <th>
                                        Amministratore
                                    </th>
                                     <th>
                                        Accetta invito
                                    </th>
                                </tr>
                            </thead>

                            <tbody>
                                <c:forEach var = "i" items = "${invitations}">
                                    <tr>

                                        <td>
                                            <c:out value = "${i.groupName}" />
                                        </td>
                                        <td>
                                            <c:out value = "${i.userName}" />
                                        </td>
                                        <td>
                                            <input type="checkbox" name="chk" value = "${i.groupID}">
                                        </td>

                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    
                    <div class="col-md-2" style="background-color: #c0c0c0; border: none">
                        <div class="btn-group-vertical">
                            <button type="submit" class="btn btn-default" name="btn" value="Conferma">Conferma</button>
                            <button type="submit" class="btn btn-default" name="btn" value="Annulla">Annulla</button>
                        </div>
                    </div>
                </div>
                </form>
                
            </c:otherwise>
        </c:choose>
       
        <%@ include file="/pageFormat/closeGrid.html" %>
        <%@ include file="/pageFormat/footer.html" %>
        
    </body>
    
</html>
