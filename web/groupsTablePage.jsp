<%-- 
    Document   : moderatorPage
    Created on : 28-dic-2013, 8.20.46
    Author     : giorgio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang = "it">
    <head>
        <%@ include file="/head.html" %>
        
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <link rel="stylesheet" href="<c:url value="css/jquery.dataTables.css"/>"/>
        <script type="text/javascript" src='<c:url value="js/jquery.js"/>'></script>
        <script type="text/javascript" src='<c:url value="js/jquery.dataTables.min.js"/>'></script>
        <script src="dist/js/bootstrap.min.js"></script>
    </head>
    
    <body>
        <%@ include file="/pageFormat/header.html" %>
        <%@ include file="/pageFormat/openGrid.html" %>
        
        <c:choose>
            <c:when test = "${grMod == true}">
                <h3 align="center"><span class="label label-info">Pagina del moderatore</span></h3>
            </c:when>
            <c:otherwise>
                <h3 align="center"><span class="label label-info">Gruppi a cui partecipi</span></h3>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test = "${groups.size() == 0}">
                Non ci sono gruppi
            </c:when>
            <c:otherwise>
                 <script>
                    $(document).ready
                    (
                        function()
                        {
                            $("#groupsTable").dataTable();
                        }
                    );
                </script>
                
                <br>
                <h3 align="center">Lista dei gruppi</h3>
                <br>
                <form action = "ControlServlet" method = "post">
                <div class="row" style="background-color: #c0c0c0; border: none">
                    <div class="col-md-10" style="background-color: #c0c0c0; border: none">
                        <input type="hidden" name="op" value="groupsTablePage">
                        <table id = "groupsTable">
                            <thead>
                                <tr>
                                    <th>
                                        Gruppo
                                    </th>
                                    <th>
                                        Amministratore
                                    </th>
                                    <th>
                                        Privato
                                        <img src = "<c:url value= "images/red.png" />" />
                                    </th>
                                    <th>
                                        Aperto
                                        <img src = "<c:url value= "images/green.png" />" />
                                    </th>
                                    <th>
                                        Azioni
                                    </th>
                                </tr>
                            </thead>

                            <tbody>
                                <c:forEach var = "g" items = "${groupsTable}">
                                    <tr>

                                        <td>
                                            <strong><c:out value = "${g.groupName}" /></strong>
                                        </td>
                                        <td align="center">
                                            <strong><c:out value = "${g.userName}" /></strong>
                                            <p><img src="${g.userAvatar}" /></p>
                                        </td>
                                        <td>
                                            <c:choose>
                                                
                                                <c:when test = "${g.groupIsPrivate == true}">
                                                    <img src = "<c:url value= "images/red.png" />" />
                                                </c:when>
                                                <c:otherwise>
                                                    <img src = "<c:url value= "images/green.png" />" />
                                                </c:otherwise>
                                            
                                            </c:choose>
                                        </td>
                                         <td>
                                            <c:choose>
                                                
                                                <c:when test = "${g.groupIsOpen == true}">
                                                    <img src = "<c:url value= "images/red.png" />" />
                                                </c:when>
                                                <c:otherwise>
                                                    <img src = "<c:url value= "images/green.png" />" />
                                                </c:otherwise>
                                            
                                            </c:choose>
                                        </td>
                                        <td>
                                            <div class="btn-group">
                                                <button type="button" class="btn btn-default">Azioni</button>
                                                <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                                    <span class="caret"></span>
                                                    <span class="sr-only">Toggle Dropdown</span>
                                                </button>
                                                <ul class="dropdown-menu" role="menu">
                                                    <li><a href="GoToGroup?groupID=${g.groupID}&opG=go">Vai al gruppo</a></li>
                                                    <li class="divider"></li>
                                                    <li><a href="GoToGroup?groupID=${g.groupID}&opG=sp">Stampa PDF</a></li>
                                                    <li><a href="GoToGroup?groupID=${g.groupID}&opG=mg">Modifica gruppo</a></li>
                                                </ul>
                                            </div>
                                        </td>

                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    
                    <div class="col-md-2" style="background-color: #c0c0c0; border: none">
                        <div class="btn-group-vertical">
                            <button type="submit" class="btn btn-default" name="btn" value="annulla">Indietro</button>
                        </div>
                    </div>
                    </div>
                </div>
                </form>
                
            </c:otherwise>
        </c:choose>
                
        <%@ include file="/pageFormat/closeGrid.html" %>
    </body>
</html>
