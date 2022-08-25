<%@ page import="ru.javawebinar.topjava.model.MealTo" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.javawebinar.topjava.web.MealsStorage" %>
<%@ page import="ru.javawebinar.topjava.util.MealsUtil" %>
<%@ page import="java.time.LocalTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%--
  Created by IntelliJ IDEA.
  User: skosarev
  Date: 24.08.2022
  Time: 20:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style>
    table, th, td {
        border:1px solid black;
        border-collapse: collapse;
    }

    .red {
        color: red;
    }

    .green {
        color: green;
    }
</style>
<head>
    <title>Meals</title>
</head>
<body>
    <h3><a href="index.html">Home</a></h3>
    <hr>
    <h2>Meals:</h2>
    <table style="width:450px">
        <tr>
            <th>Date</th>
            <th>Time</th>
            <th>Description</th>
            <th>Calories</th>
        </tr>
        <%
            List<MealTo> meals = MealsUtil.getFiltered(MealsStorage.meals, LocalTime.MIN, LocalTime.MAX, 2000);
            for (MealTo meal : meals) {
                boolean isExcess = meal.isExcess();
                out.println("<tr>");
                out.print(String.format("<th class=\"%s\">%s</th>", isExcess ? "red" : "green", meal.getDateTime().toLocalDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
                out.print(String.format("<th class=\"%s\">%s</th>", isExcess ? "red" : "green", meal.getDateTime().toLocalTime()));
                out.print(String.format("<th class=\"%s\">%s</th>", isExcess ? "red" : "green", meal.getDescription()));
                out.print(String.format("<th class=\"%s\">%d</th>", isExcess ? "red" : "green", meal.getCalories()));
                out.println("</tr>");
            }
        %>
    </table>
</body>
</html>
