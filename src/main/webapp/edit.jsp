<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.sql.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Book</title>
    <link rel="stylesheet" href="css/bootstrap.css"> <!-- Make sure to adjust the path based on your project structure -->
</head>
<body class="container-fluid card" style="width: 40rem;">
    <h2 class="bg-danger text-white text-center">Edit Book</h2>

    <form action="edit" method="post"> <!-- Assuming you have an 'UpdateServlet' to handle the update -->
        <div class="mb-3">
            <label for="bookId" class="form-label">Book ID</label>
            <input type="text" class="form-control" id="bookId" name="bookId" value="<%= request.getParameter("Id") %>" readonly>
        </div>
        <div class="mb-3">
            <label for="bookName" class="form-label">Book Name</label>
            <input type="text" class="form-control" id="bookName" name="bookName" value="<%= request.getParameter("bookName") %>" required>
        </div>
        <div class="mb-3">
            <label for="bookEdition" class="form-label">Book Edition</label>
            <input type="text" class="form-control" id="bookEdition" name="bookEdition" value="<%= request.getParameter("bookEdition") %>" required>
        </div>
        <div class="mb-3">
            <label for="bookAuthor" class="form-label">Book Author</label>
            <input type="text" class="form-control" id="bookAuthor" name="bookAuthor" value="<%= request.getParameter("bookAuthor") %>" required>
        </div>
        <div class="mb-3">
            <label for="bookPrice" class="form-label">Book Price</label>
            <input type="text" class="form-control" id="bookPrice" name="bookPrice" value="<%= request.getParameter("bookPrice") %>" required>
        </div>

        <button type="submit" class="btn btn-primary">Update</button>&nbsp;&nbsp;
        <button formaction='retrive' type="submit" class="btn btn-primary">Back</button>
    </form>
</body>
</html>
