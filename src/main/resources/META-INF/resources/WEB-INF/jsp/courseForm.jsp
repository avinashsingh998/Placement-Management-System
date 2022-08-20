<%@ include file="common/header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add New Course</title>
</head>
<body onload = "setActiveClass(selector1)">

	<c:if test="${msg ne null}">
			<h4 style="color:green; text-align:center;"> ${msg} </h4>
		</c:if>
	<form class="row g-3" style="width: 60%; margin-left:21%" method ="post">
		<div class="col-6">
			<label for="courseId" class="form-label">Course Id </label> <input
				type="text" class="form-control" name="courseId" required>
		</div>
		<div class="col-6">
			<label for="name" class="form-label">Course Name</label> <input
				type="text" class="form-control" name="name" required>
		</div>
		<div class="col-6">
			<label for="department" class="form-label">Department</label> <input
				type="text" class="form-control" name="department" >
		</div>

		<div class="col-6">
			<label for="semester" class="form-label">Semester</label> <input
				type="number" class="form-control" name="semester" required>
		</div>

		<div class="col-12">
			<button type="submit" class="btn btn-primary">Add Course</button>
		</div>
	</form>

</body>
</html>
<%@ include file="common/footer.jsp"%>
