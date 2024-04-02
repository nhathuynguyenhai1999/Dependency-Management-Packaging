<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 26/03/2024
  Time: 4:54 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Add Product | Chaien Hot Shoes Manage Product |Bootstrap CRUD Data Table for Database with Modal Form</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link href="css/manager.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <h1>Create Product</h1>
    <form action="${pageContext.request.contextPath}/products?action=create" method="post">
        <div class="modal-header">
            <h4 class="modal-title">Add Product</h4>
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        </div>
        <div class="modal-body">
            <div class="form-group">
                <label>Name</label>
                <label>
                    <input name="name" type="text" class="form-control" required>
                </label>
            </div>
            <div class="form-group">
                <label>Image</label>
                <label>
                    <input name="image" type="text" class="form-control" required>
                </label>
            </div>
            <div class="form-group">
                <label>Price</label>
                <label>
                    <input name="price" type="text" class="form-control" required>
                </label>
            </div>
            <div class="form-group">
                <label>Title</label>
                <label>
                    <textarea name="title" class="form-control" required></textarea>
                </label>
            </div>
            <div class="form-group">
                <label>Description</label>
                <label>
                    <textarea name="description" class="form-control" required></textarea>
                </label>
            </div>
            <div class="form-group">
                <label>Category</label>
                <select name="category" class="form-select" aria-label="Default select example">
                    <c:forEach items="${categories}" var="o">
                        <option value="${o.cid}">${o.cname}</option>
                    </c:forEach>
                </select>
            </div>

        </div>
        <div class="modal-footer">
            <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel" onclick="backAction()">
            <input type="submit" class="btn btn-success" value="Add">
        </div>
    </form>
    <script>
        function backAction(){
            window.location.href = '/products';
        }
    </script>
</body>
</html>
