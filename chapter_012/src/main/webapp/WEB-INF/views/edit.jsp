<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="person" scope="request" type="ru.job4j.crud.User"/>
<!DOCTYPE html>
<html lang="ru">
    <head>
        <title>Обновление данных</title>
        <meta charset='utf-8'/>
    </head>
    <body>
        <form action='${pageContext.request.contextPath}/edit?id=${person.id}' method='post'>
            <table>
                <tr>
                    <td>Имя:</td>
                    <td>
                        <label>
                            <input type='text' name='name' value='${person.name}'/>
                        </label>
                    </td>
                </tr>
                <tr>
                    <td>Логин:</td>
                    <td>
                        <label>
                            <input type='text' name='login' value='${person.login}'/>
                        </label>
                    </td>
                </tr>
                <tr>
                    <td>Электронная почта:</td>
                    <td>
                        <label>
                            <input type='text' name='email' value='${person.email}'/>
                        </label>
                    </td>
                </tr>
            </table>
            <input type='hidden' name='id' value='${person.id}'/>
            <input type='submit' value='Сохранить'/>
        </form>
    </body>
</html>
