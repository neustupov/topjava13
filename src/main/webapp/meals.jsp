<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show All Meals</title>
</head>
<body>
<table border=1>
    <thead>
    <tr>
        <th>Description</th>
        <th>Calories</th>
        <th>DataTime</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${mealsList}" var="meal">
        <c:set var="color" value="red"/>
        <c:if test="${!meal.exceed}">
            <c:set var="color" value="green"/>
        </c:if>
        <tr>
            <td style="color: ${color}"><c:out value="${meal.description}"/></td>
            <td style="color: ${color}"><c:out value="${meal.calories}"/></td>
            <td style="color: ${color}"><javatime:format value="${meal.dateTime}" pattern="yyyy-MM-dd"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<h3><a href="index.html">Home</a></h3>
</body>
</html>
