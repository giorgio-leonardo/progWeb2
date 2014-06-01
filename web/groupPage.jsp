<%-- 
    Document   : groupPage
    Created on : 29-dic-2013, 6.25.56
    Author     : giorgio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id = "user" class = "beans.User" scope = "session"/>
<jsp:useBean id = "group" class = "beans.Group" scope = "request"/>
<jsp:useBean id = "post" class = "beans.Post" scope = "request"/>

<!DOCTYPE html>
<html>

    <head>
        <%@ include file="/head.html" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <link rel="stylesheet" href="<c:url value="css/jquery.dataTables.css"/>"/>
        <script type="text/javascript" src='<c:url value="js/jquery.js"/>'></script>
        <script type="text/javascript" src='<c:url value="js/jquery.dataTables.min.js"/>'></script>
    </head>
    
    <body>
        
        <c:choose>
            <c:when test = "${pr == true && user.userID == null}">
                <c:redirect url = "/errorPages/privateGroupError.jsp" />
            </c:when>
            <c:otherwise>
        
        <%@ include file="/pageFormat/header.html" %>
        <%@ include file="/pageFormat/openGrid.html" %>
        
        <h3 align="center"><span class="label label-info"><jsp:getProperty name = "group" property= "groupName" /></span></h3>
        
        <c:choose>
            <c:when test = "${user.userID == null}">
                <h3 align="center"><span class="label label-warning">Stai visualizzando questo gruppo come ospite</span></h3>
            </c:when>
        </c:choose>
        
        <div class="row" style="background-color: #c0c0c0; border: none">
            <div class="col-md-10" style="background-color: #c0c0c0; border: none">

                <c:forEach var = "p" items = "${posts}">
                    <div class="row" style="background-color: #c0c0c0; border: none">
                        <div class="col-md-3" style="background-color: #c0c0c0; border: none">
                            <div class="thumbnail">
                                <div class="caption">
                                    <h3 align = "center">${p.userName}</h3>
                                </div>
                                <img src="${p.userAvatar}">
                                <br>
                                <p align = "center">${p.postDataCreation}</p>
                            </div>
                        </div>
                        <div class="col-md-9" style="background-color: #c0c0c0; border: none">
                            <div style="width:100%; height:197px; background-color:#ffffff; border:1px solid #00000">${p.postText}</div>
                        </div>

                    </div>
                </c:forEach>
            </div>

            <c:choose>
                <c:when test = "${ user.userID != null}">
                    <div class="col-md-2" style="background-color: #c0c0c0; border: none">
                        <form action = "ControlServlet" method = "post">
                            <div class="btn-group-vertical">
                                <input type="hidden" name = "op" value ="groupPage">
                                <button type="submit" class="btn btn-default" name="btn" value="scriviPost">Scrivi post</button>
                                <button type="submit" class="btn btn-default" name="btn" value="stampaPDF">Stampa PDF</button>
                                <button type="submit" class="btn btn-default" name="btn" value="modificaGruppo">Modifica gruppo</button>
                                <button type="submit" class="btn btn-default" name="btn" value="indietro">Indietro</button>
                            </div>
                        </from>
                    </div>
                </c:when>
            </c:choose>
        </div>
        
        <%@ include file="/pageFormat/closeGrid.html" %>
        <%@ include file="/pageFormat/footer.html" %>
        
        </c:otherwise>
        </c:choose>
    </body>
</html>
