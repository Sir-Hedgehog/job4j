<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Создание аккаунта</title>
        <meta charset='utf-8'/>
    </head>
    <body>
        <p>
            Введите корректные данные!
        </p>
        <form action='<%=request.getContextPath()%>/create' method='post'>
            <table>
                <tr>
                    <td>Введите имя:</td>
                    <td>
                        <label>
                            <input type='text' name='name'/>
                        </label>
                    <td>
                </tr>
                <tr>
                    <td>Введите логин:</td>
                    <td>
                        <label>
                            <input type='text' name='login'/>
                        </label>
                    </td>
                </tr>
                <tr>
                    <td>Введите email:</td>
                    <td>
                        <label>
                            <input type='text' name='email'/>
                        </label>
                    </td>
                </tr>
            </table>
            <input type='submit' value='Добавить'>
        </form>
    </body>
</html>
