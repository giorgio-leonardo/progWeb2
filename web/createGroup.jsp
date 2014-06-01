<%-- 
    Document   : createGroup
    Created on : 26-dic-2013, 10.19.45
    Author     : giorgio
--%>

<%@page import = "java.util.List"%>
<%@page import = "beans.User"%>
<%@page contentType = "text/html" pageEncoding = "UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/head.html" %>
        
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
         <link rel="stylesheet" href="<c:url value="/css/jquery.dataTables.css"/>"/>
        <script type="text/javascript" src='<c:url value="/js/jquery.js"/>'></script>
        <script type="text/javascript" src='<c:url value="/js/jquery.dataTables.min.js"/>'></script>
    </head>
    
    <body>
        
        <%@ include file="/pageFormat/header.html" %>
        <%@ include file="/pageFormat/openGrid.html" %>

        <script>
            $(document).ready
            (
                function()
                {
                    $("#usersTable").dataTable();
                }
            );
        </script>
        
        <h3 align="center"><span class="label label-info">Crea un gruppo</span></h3>
        <form action = "ControlServlet" method="post">
        <div class="row" style="background-color: #c0c0c0; border: none">
            <div class="col-md-10" style="background-color: #c0c0c0; border: none">
                <input type="hidden" name="op" value="createGroup">
                <div class="input-group">
                    <span class="input-group-addon">Nome del gruppo</span>
                    <input type="text" class="form-control" placeholder="nome gruppo" name="groupName">
                    <span class="input-group-addon">Gruppo aperto <input type="checkbox" name="groupIsOpen"></span>
                    <span class="input-group-addon">Gruppo privato <input type="checkbox" name="groupIsPrivate"></span>
                </div>
                <br><br><br>
                <table id="usersTable">
                <thead>
                    <tr>
                        <th>
                            Nome utente
                        </th>
                        <th>
                            Invita
                        </th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach var = "u" items = "${users}">
                        <tr>
                            <td>
                                <c:out value = "${u.userName}" />
                            </td>    
                            <td>
                                <input type="checkbox" name="chk" value = "${u.userName}">
                            </td>    
                        </tr>
                    </c:forEach>
                </tbody>
                </table>
            </div>
            
            <div class="col-md-2" style="background-color: #c0c0c0; border: none">
                <div class="btn-group-vertical">
                    <button type="submit" class="btn btn-default" name="btn" value="cgrConferma">Conferma</button>
                    <button type="submit" class="btn btn-default" name="btn" value="cgrAnnulla">Annulla</button>
                </div>
            </div>
        </div>
        </form>
        
        <%@ include file="/pageFormat/closeGrid.html" %>
        <%@ include file="/pageFormat/footer.html" %>
    </body>
</html>
