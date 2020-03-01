<%@ page contentType="text/html;charset=UTF-8" %>
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
                    <td>Выбранное фото:</td>
                    <td>
                        <div>
                            <label>
                                <input type='text' name='file' value='${person.photoId}' readonly/>
                            </label>
                        </div>
                    </td>
                </tr>
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
            <input type='submit' value='Сохранить'/>
        </form>
    </body>
</html>
