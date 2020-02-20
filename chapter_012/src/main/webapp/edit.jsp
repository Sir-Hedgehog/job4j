<%@ page import="ru.job4j.crud.User" %>
<%@ page import="ru.job4j.crud.ValidateService" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
    <head>
        <title>Обновление данных</title>
        <meta charset='utf-8'/>
    </head>
    <body>
    <%User user = ValidateService.getInstance().findById(Integer.valueOf(request.getParameter("id")));%>
        <form action='<%=request.getContextPath()%>/edit?id=<%=user.getId()%>' method='post'>
            <table>
                <tr>
                    <td>Имя:</td>
                    <td>
                        <label>
                            <input type='text' name='name' value='<%=user.getName()%>'/>
                        </label>
                    </td>
                </tr>
                <tr>
                    <td>Логин:</td>
                    <td>
                        <label>
                            <input type='text' name='login' value='<%=user.getLogin()%>'/>
                        </label>
                    </td>
                </tr>
                <tr>
                    <td>Электронная почта:</td>
                    <td>
                        <label>
                            <input type='text' name='email' value='<%=user.getEmail()%>'/>
                        </label>
                    </td>
                </tr>
            </table>
            <input type='hidden' name='id' value='<%=user.getId()%>'/>
            <input type='submit' value='Сохранить'/>
        </form>
    </body>
</html>
