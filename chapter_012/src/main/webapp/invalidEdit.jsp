<%@ page import="ru.job4j.crud.User" %>
<%@ page import="ru.job4j.crud.ValidateService" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Создание аккаунта</title>
        <meta charset='utf-8'/>
    </head>
    <body>
        <p>
            Для изменения информации введите корректные данные!
        </p>
        <%User user = ValidateService.getInstance().findById(Integer.valueOf(request.getParameter("id")));%>
        <form action='<%=request.getContextPath()%>/edit' method='post'>
            <p>
                Имя:
                <label>
                    <input type='text' name='name' value='<%=user.getName()%>'/>
                </label>
            </p>
            <p>
                Логин:
                <label>
                    <input type='text' name='login' value='<%=user.getLogin()%>'/>
                </label>
            </p>
            <p>
                Электронная почта:
                <label>
                    <input type='text' name='email' value='<%=user.getEmail()%>'/>
                </label>
            </p>
            <input type='hidden' name='id' value='<%=user.getId()%>'/>
            <input type='submit' value='Сохранить'>
        </form>
    </body>
</html>
