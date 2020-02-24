<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
    <head>
        <title>Создание аккаунта</title>
        <meta charset='utf-8'/>
    </head>
    <body>
        <h1>
            Добро пожаловать на наш сайт!
        </h1>
        <p>
            Вы можете:
        </p>
        <p>
            <a href='${pageContext.request.contextPath}/create' title='Создать пользователя'>1. Создать пользователя</a>
        </p>
        <p>
            <a href='${pageContext.request.contextPath}/list' title='Список пользователей'>2. Посмотреть список пользователей</a>
        </p>
        <p>
            Всё бесплатно!
        </p>
    </body>
</html>
