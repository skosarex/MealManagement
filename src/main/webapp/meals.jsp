<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="ru.javawebinar.topjava.model.MealTo" %>
<%@ page import="java.util.List" %>

<html>
<style>
    .red {
        color: #ff2f2f;
    }
    .green {
        color: #21a421;
    }
</style>
<head>
    <link rel="stylesheet" href="table.css">
    <title>Meals</title>
</head>
<body>
    <h3><a href="index.html">Home</a></h3>
    <hr>
    <h2>Meals</h2>

    <div class="table-wrapper">
        <table class="fl-table">
            <tr>
                <th>Date</th>
                <th>Time</th>
                <th>Description</th>
                <th>Calories</th>
            </tr>
            <c:forEach var="meal" items="${mealsList}">
                <c:set var="color" value="${meal.isExcess() ? \"red\" : \"green\"}"/>
                <tr>
                    <td class="${color}">${meal.getDateTime().toLocalDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))}</td>
                    <td class="${color}">${meal.getDateTime().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm"))}</td>
                    <td class="${color}">${meal.getDescription()}</td>
                    <td class="${color}">${meal.getCalories()}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
