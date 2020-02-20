<%@ page import="ru.job4j.crud.ValidateService" %>
<%@ page import="ru.job4j.crud.User" %>
<%@ page contentType="text/html;charset=UTF-8" %>
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
                     margin-bottom: 0;
                 }
        </style>
    </head>
    <body>
        <table border='1' bgcolor='#99ffcc'>
            <%for (User user : ValidateService.getInstance().findAll()) {%>
                <tr>
                    <td><%=user.getId()%></td>
                    <td><%=user.getName()%></td>
                    <td><%=user.getLogin()%></td>
                    <td><%=user.getEmail()%></td>
                    <td><%=user.getCreateDate()%></td>
                </tr>
            <% } %>
        </table>
        <%for (User user : ValidateService.getInstance().findAll()) {%>
            <form class='submit' action='<%=request.getContextPath()%>/edit' method='get'>
                <input type='hidden' name='id' value='<%=user.getId()%>'/>
                <input type='submit' value='Редактировать'>
            </form>
            <form class='submit' action='<%=request.getContextPath()%>/list' method='post'>
                <input type='hidden' name='id' value='<%=user.getId()%>'/>
                <input type='submit' value='Удалить'>
            </form>
            <br/>
        <% } %>
    </body>
</html>
