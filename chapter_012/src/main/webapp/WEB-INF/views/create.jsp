<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:useBean id='file' scope="request" type="java.lang.String"/>
<!DOCTYPE html>
<html lang="ru">
    <head>
        <title>Создание аккаунта</title>
        <meta charset='utf-8'/>
        <style type='text/css'>
            .checkbox {
                display: inline-block;
                margin-right: 10px;
            }
        </style>
    </head>
    <body>
        <form action='${pageContext.request.contextPath}/upload' method='post' enctype='multipart/form-data'>
            <table>
                <tr>
                    <td>Выберите фото:</td>
                    <td>
                        <div class='checkbox'>
                            <input type='file' name='file'/>
                        </div>
                        <input type='submit' value='Прикрепить'/>
                    </td>
                </tr>
            </table>
        </form>
        <form action='${pageContext.request.contextPath}/create' method='post'>
            <table>
                <tr>
                    <td>Выбранное фото:</td>
                    <td>
                        <div>
                            <label>
                                <input type='text' name='file' value='${file}' readonly/>
                            </label>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>Введите имя:</td>
                    <td>
                        <label>
                            <input type='text' name='name'/>
                        </label>
                    </td>
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
            <input type='hidden' name='file' value='${file}'/>
            <input type='submit' value='Добавить'/>
        </form>
    </body>
</html>
