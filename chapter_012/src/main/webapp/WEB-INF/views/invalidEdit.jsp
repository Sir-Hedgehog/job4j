<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="person" scope="request" type="ru.job4j.crud.User"/>
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
        <form action='${pageContext.request.contextPath}/edit' method='post'>
            <p>
                Имя:
                <label>
                    <input type='text' name='name' value='${person.name}'/>
                </label>
            </p>
            <p>
                Логин:
                <label>
                    <input type='text' name='login' value='${person.login}'/>
                </label>
            </p>
            <p>
                Электронная почта:
                <label>
                    <input type='text' name='email' value='${person.email}'/>
                </label>
            </p>
            <input type='hidden' name='id' value='${person.id}'/>
            <input type='submit' value='Сохранить'>
        </form>
    </body>
</html>
