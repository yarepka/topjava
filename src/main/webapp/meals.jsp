<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
    <title>Meals</title>
    <link rel="stylesheet" type="text/css" href="./css/table.css">
</head>

<body>
    <div class="content">
        <h3>Meals</h3>
        <hr>

        <table>
            <thead>
                <th>description</th>
                <th>calories</th>
                <th>dateTime</th>
                <th>exceed</th>
            </thead>

            <tbody>
                <c:forEach var="userMeal" items="${userMeals}">
                    <tr>
                        <c:if test="${!userMeal.exceed}">
                            <td style="background-color:#8edc7b;">${userMeal.description}</td>
                            <td style="background-color:#8edc7b;">${userMeal.calories}</td>
                            <td style="background-color:#8edc7b;">${userMeal.dateTime}</td>
                            <td style="background-color:#8edc7b;">${userMeal.exceed}</td>
                        </c:if>

                        <c:if test="${userMeal.exceed}">
                            <td style="background-color:#fa3e3c;">${userMeal.description}</td>
                            <td style="background-color:#fa3e3c;">${userMeal.calories}</td>
                            <td style="background-color:#fa3e3c;">${userMeal.dateTime}</td>
                            <td style="background-color:#fa3e3c;">${userMeal.exceed}</td>
                        </c:if>

                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

</body>

</html>