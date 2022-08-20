<%@ include file="common/header.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Home Page</title>

</head>
<body>


	<c:if test="${msg ne null}">
		<h1 style="color: red; text-align: center;">${msg}</h1>
	</c:if>


	<c:set var="index" value="0" />
	<table style="width: 100%">
		<tr>
			<c:forEach items="${courses}" var="course">


				<c:if test="${index%3==0 }">
		<%= "</tr> <tr>" %>

			</c:if>
			<td>
				<form action="${ctx}/courses" method="get">
					<input type="hidden" id="courseId" name="courseId" value="${course.courseId}">
					<input type="hidden" id="name" name="name" value="">
					<input type="hidden" id="sortBy" name="sortBy" value="Sort By">
					<input type="hidden" id="cId" name="cId" value="">
					<input type="hidden" id="placed" name="placed" value="">
					<button style="border: none; height:50px; width:250px; margin-left: 90px; margin-bottom: 90px; margin-top: 80px;"
						type="submit">
						<div  class = "course-cards col-6, shadow p-3 mb-5">
							<strong>${course.name} </strong><br> Semester - ${course.semester}
							<hr>
							<p style="color: red;">${course.department}</p>
							<hr>
						</div>
					</button>
				</form>
			</td>
			<c:set var="index" value="${index+1}" />

			</c:forEach>
		</tr>
	</table>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
		crossorigin="anonymous"></script>
</body>
</html>
<%@ include file="common/footer.jsp"%>
