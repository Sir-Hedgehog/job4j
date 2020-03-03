<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
    <head>
        <title>Создание аккаунта</title>
        <meta charset='utf-8'/>
        <style>
            html, body {
                height: 100%;
                text-align: center;
            }
            body {
                display: flex;
                flex-direction: column;
            }
            article {
                 display: flex;
                 flex-direction: column;
                 align-items: center;
                 justify-content: center;
                 flex-grow: 15;
            }
            footer {
                flex-grow: 1;
            }
        </style>
    </head>
    <body>
        <header>
            <h1>
                Добро пожаловать на наш сайт!
            </h1>
        </header>
        <article>
            <h3>
                Вы можете:
            </h3>
            <p>
                <a href='${pageContext.request.contextPath}/create' title='Создать пользователя'>Создать пользователя</a>
            </p>
            <p>
                <a href='${pageContext.request.contextPath}/list' title='Список пользователей'>Посмотреть список пользователей</a>
            </p>
        </article>
        <footer>
            <p>
                Всё бесплатно!
            </p>
        </footer>
    </body>
</html>
