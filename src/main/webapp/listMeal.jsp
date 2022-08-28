<%--
  Created by IntelliJ IDEA.
  User: skosa
  Date: 28.08.2022
  Time: 2:58
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<style>
    .red {
        color: red;
    }
    .green {
        color: green;
    }
    a {
        border-radius: 5px;
    }
    a.update-button:link, a.update-button:visited {
        background-color: white;
        color: black;
        border: 2px solid green;
        padding: 5px 20px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
    }
    a.update-button:hover, a.update-button:active {
        background-color: green;
        color: white;
    }
    a.delete-button:link, a.delete-button:visited {
        background-color: #f44336;
        color: white;
        padding: 5px 20px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
    }
    a.delete-button:hover, a.delete-button:active {
        background-color: #c20000;
        color: white;
    }
</style>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="table.css">
    <title>Show all Meals</title>
</head>
<body>
<div class="table-wrapper">
    <h1 style="color: white; align: center">Meals table</h1>
    <table border=1 class="fl-table">
        <thead>
        <tr>
            <th>Date</th>
            <th>Time</th>
            <th>Description</th>
            <th>Calories</th>
            <th colspan=2>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${meals}" var="meal">
            <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.MealTo"></jsp:useBean>
            <c:set var="color" value="${meal.isExcess() ? \"red\" : \"green\"}"/>
            <tr class="${color}">
                <td>${meal.getDateTime().toLocalDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))}</td>
                <td>${meal.getDateTime().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm"))}</td>
                <td>${meal.getDescription()}</td>
                <td>${meal.getCalories()}</td>
                <td><a class="update-button" href="meals?action=edit&mealId=<c:out value="${meal.getId()}"/>">Update</a></td>
                <td><a class="delete-button" href="meals?action=delete&mealId=<c:out value="${meal.getId()}"/>">Delete</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<p><a style="width: 80%; margin-left: 85px" class="update-button" href="meals?action=insert">Add a Meal</a></p>
</body>
</html>
