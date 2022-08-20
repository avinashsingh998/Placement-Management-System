<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="common/header.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script>
			function deleteCompany(cId){
				
				var deleteCompany = confirm("Are you sure want to delete this company record?")
				
				if (deleteCompany) {
					window.location.assign("${ctx}/companyD?cId=" + cId);
				} else {
					return;
				}
			}
			

</script>
</head>
<body onload = "setActiveClass(selector3)">

	<div>
		<c:if test="${msg ne null}">
			<h4 style="color: green; text-align: center;">${msg}</h4>
			<br>
			<br>
		</c:if>

		<div class = "company-main">
			<div class = "company-sec1">
			
			<h2> Add New Company</h2>
				<form class = "company-form" method="post" action="${ctx}/companyS">
					<div class="col-12">
						<label for="cid" class="form-label">Company Name </label> <input
							type="text" id="cid" class="form-control" name="name" required>
					</div>
					<div class="col-12">
						<label for="pn" class="form-label">Phone Number </label> <input
							type="number" id="pn" class="form-control" name="phoneNumber">
					</div>
					<div class="col-12">
						<label for="ei" class="form-label">Email Id</label> <input
							type="email" id='ei' class="form-control" name="emailId">
					</div>

					<div class="col-12">
						<label for="ad" class="form-label">Arrival Date</label> <input
							type="date" id="ad" class="form-control" name="arrivalDate">
					</div>

					<div class="col-12">
						<label for="hp" class="form-label">Highest package the
							company may offer</label> <input type="number" id="hp"
							class="form-control" name="highestPackage" required>
					</div>
					<br>
					<div class="col-12">
						<label for="selectedCourse">Select courses:</label> <select
							name="selectedCourse" id="selectedCourse" multiple required>
							<c:forEach items="${courses}" var="course">
								<option value="${course.courseId}">${course.name}(Semester
									- ${course.semester})</option>
							</c:forEach>
						</select>
					</div>

					<div class="col-12">
						<br>
						<button type="submit" >Add Company</button>
					</div>
				</form>
			</div>

			<div class = "company-sec2">
				<h2>Existing Companies</h2>
				<c:if test="${companies ne null}">
					<table class="table table-striped" >
						<tr>
							<th>Company Name</th>
							<th>Arrival date</th>
							<th>Contact Details</th>
							<th>Highest package</th>
							<th></th>
						</tr>

						<c:forEach items="${companies}" var="comp">
							<tr>
								<td>${comp.name}</td>
								<td> <fmt:formatDate type = "date" value = "${comp.arrivalDate}" /></td>
								<td>${comp.phoneNumber}<br> <a
									href="mailto:${comp.emailId}">${comp.emailId} </a>
								</td>
								<td>${comp.highestPackage}</td>
								<td>  <img src="image/cross.jpg" width="20" height="20" alt="Delete" onclick="deleteCompany(${comp.cId})">
							</tr>
						</c:forEach>




					</table>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>
<%@ include file="common/footer.jsp"%>
