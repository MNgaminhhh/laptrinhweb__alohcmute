<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>User Management</title>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>
<body>

<h2>User List</h2>

<table id="userTable" border="1">
    <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <!-- Add other user fields as needed -->
        </tr>
    </thead>
    <tbody>
        <!-- User data will be populated here using Thymeleaf -->
        <tr th:each="user : ${users}">
            <td th:text="${user.id}"></td>
            <td th:text="${user.name}"></td>
            <!-- Add other user fields as needed -->
        </tr>
    </tbody>
</table>

</body>
</html>
