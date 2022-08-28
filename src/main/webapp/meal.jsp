<%--
  Created by IntelliJ IDEA.
  User: skosa
  Date: 28.08.2022
  Time: 4:36
  To change this template use File | Settings | File Templates.
--%>

<%-- i want to sleep. i will clean up this code later --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style>
    body {
        color: white;
        font-family: Helvetica;
        -webkit-font-smoothing: antialiased;
        background: rgba(71, 147, 227, 1);
    }
    input {
		border-radius: 5px;
        border: 2px solid white;
        width: 50%;
        padding: 12px 20px;
        margin: 8px 0;
        box-sizing: border-box;
    }
</style>
<head>
	<title>Edit meal</title>
</head>
<body>
<form action="/topjava/meals" method="POST">
	Description:
	<br>
	<input type="text" name="description">
	<br>
	Calories:
	<br>
	<input type="text" name="calories">
	<br>
	Excess:
	<br>
	<input type="text" name="excess">
	<br>
	<input style="color: white; background: #4FC3A1" type="submit" value="Submit"/>
</form>
</body>
</html>
