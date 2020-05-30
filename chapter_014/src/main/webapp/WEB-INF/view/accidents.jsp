<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
    <head>
        <meta charset="utf-8" >
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <title>Events</title>
        <style>
            table.table {
                background-color: rgba(211, 211, 211, 0.13);
            }
        </style>
    </head>
    <body>
        <table class="table">
            <thead>
                <tr>
                    <th>Случай</th>
                    <th>Имя нарушителя</th>
                    <th>Номер автомобиля</th>
                    <th>Город</th>
                </tr>
            </thead>
            <tbody>
            <%--@elvariable id="events" type="java.util.List"--%>
            <c:forEach items="${events}" var="event">
                <tr>
                    <td><c:out value="${event.description}"/></td>
                    <td><c:out value="${event.name}"/></td>
                    <td><c:out value="${event.numberOfCar}"/></td>
                    <td><c:out value="${event.city}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </body>
</html>