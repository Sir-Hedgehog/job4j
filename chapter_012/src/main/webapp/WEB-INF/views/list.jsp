<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="clients" scope="request" type="java.util.List"/>
<!DOCTYPE html>
<html lang='ru'>
    <head>
        <title>Список пользователей</title>
        <meta charset='utf-8'/>
        <style type='text/css'>
                 table {
                     float: left;
                     margin-right: 10px;
                 }
                 td {
                     border-bottom: 1px solid #99ffcc;
                     border-right: 1px solid #99ffcc;
                     padding: 2px 5px;
                 }
                 input {
                     margin: 3px;
                 }
                 .submit {
                     display: inline-block;
                     padding: 43px 0;
                     margin: 0;
                 }
        </style>
    </head>
    <body>
        <table border='1' bgcolor='#99ffcc' valign='center'>
            <c:forEach items="${clients}" var="client">
                <tr>
                    <td align="center"><c:out value="${client.id}"/></td>
                    <td align="center"><c:out value="${client.name}"/></td>
                    <td align="center"><c:out value="${client.login}"/></td>
                    <td align="center"><c:out value="${client.email}"/></td>
                    <td align="center"><c:out value="${client.createDate}"/></td>
                    <td align="center">
                        <img src="${pageContext.servletContext.contextPath}/download?name=${client.photoId}" width="100px" height="100px"/>
                    </td>
                    <td align="center"><a href="${pageContext.servletContext.contextPath}/download?name=${client.photoId}">Скачать картинку</a></td>
                </tr>
            </c:forEach>
        </table>
        <c:forEach items="${clients}" var="client">
            <form class='submit' action='${pageContext.request.contextPath}/edit' method='get'>
                <input type='hidden' name='id' value='${client.id}'/>
                <input type='submit' value='Редактировать'>
            </form>
            <form class='submit' action='${pageContext.request.contextPath}/list' method='post'>
                <input type='hidden' name='id' value='${client.id}'/>
                <input type='submit' value='Удалить'>
            </form>
            <br/>
        </c:forEach>
    </body>
</html>
