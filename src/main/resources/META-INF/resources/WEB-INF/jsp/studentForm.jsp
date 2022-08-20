<%@ include file="common/header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<script>
	$(document).ready(function() {
		var updateFlag = '${updateFlag}';
		if (updateFlag == 'readonly') {
			$("#buttonText").text('Update Student');
			$("#mainHeading").text('Update Student Data');
			$("#id").prop('readonly', 'readonly');

		}
	});
	
	
	
	function uploadFile(){
		
		if( $("#selectedCourse").val()=='' ||  $("#excelFile").val()==''){
			alert("Select the required fields first...")
		}
		else{
		var courseId = $("#selectedCourse").val();
		$.ajax({
			
			 url: '${ctx}/addStudentDetailsInBulk?'+courseId,
		        type: "POST",
		       	data: new FormData(document.getElementById("bulkdata")),
		       	enctype: 'multipart/form-data',
		       	processData: false,
		        contentType: false,
		     
				
		success : function(data) {
				if (data == "success") {
				
					
					alert("Data updated successfully.")
				}
				else {
					alert("Choose only .xlsx file formate...")
				}
			},
			error : function(textStatus,errorThrown) {
				console.log("error");
			}
		});
		}
	}
	  
	
</script>

</head>
<body onload = "setActiveClass(selector2)">
	<c:if test="${msg ne null}">
		<p style="color: green">${msg}</p>
	</c:if>

	<div style="padding-left: 13%; padding-right: 13%;">
		<fieldset>

			<!-- Form Name -->
			<legend>

				<b id = "mainHeading">Add New Student</b>


			</legend>
			<br>
			<div style="float: left; width: 41%">

				<form class="well form-horizontal" action="${ctx}${uri}" method="post"
					id="contact_form">


					<!-- Text input-->

					<div class="form-group">
						<label class="col-md-12 control-label">Student Id</label>
						<div class="col-md-12 inputGroupContainer">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-user"></i></span> <input name="id" id="id"
									placeholder="Enter student id provided by college/university"
									value="${obj.id}" class="form-control" type="text" required>
							</div>
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-12 control-label">Name</label>
						<div class="col-md-12 inputGroupContainer">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-user"></i></span> <input name="name"
									placeholder="Name" value="${obj.name}" class="form-control"
									type="text" required>
							</div>
						</div>
					</div>


					<div class="form-group">
						<label class="col-md-12 control-label">Course</label>
						<div class="col-md-12 selectContainer">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-list"></i></span> <select name="courseId"
									class="form-control selectpicker" required>
									<option value="${obj.course.courseId}">Select your
										Course</option>
									<c:forEach items="${courses}" var="course">
										<option value="${course.courseId}">${course.name}
											(Semester - ${course.semester})</option>
									</c:forEach>


								</select>
							</div>
						</div>
					</div>


					<!-- Text input-->



					<!-- Text input-->
					<div class="form-group">
						<label class="col-md-12 control-label">E-Mail</label>
						<div class="col-md-12 inputGroupContainer">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-envelope"></i></span> <input name="emailId"
									placeholder="E-Mail Address" value="${obj.emailId}"
									class="form-control" type="email">
							</div>
						</div>
					</div>


					<!-- Text input-->

					<div class="form-group">
						<label class="col-md-12 control-label">Contact No.</label>
						<div class="col-md-12 inputGroupContainer">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-earphone"></i></span> <input
									name="phoneNumber" value="${obj.phoneNumber}"
									placeholder="(+91)" class="form-control" type="number">
							</div>
						</div>
					</div>

					<!-- Select Basic -->



					<!-- Button -->
					<div style="padding-left: 11%;" class="form-group">
						<label class="col-md-12 control-label"></label>
						<div class="col-md-12">
							<br>
							<button type="submit" class="btn btn-warning">
								<span id="buttonText"> ADD STUDENT </span>
							</button>
						</div>
					</div>
				</form>
			</div>

			<div style="float: right; padding-right: 0%;">
				  
					<label for="fileName">Choose a Excel file:</label> <br>
				<form id="bulkdata" name="bulkdata">
					<input type="file" id="excelFile" name="file"
						accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
						required><br>
					<br> <label class="col-md-12 control-label">Course</label> <select
						name="courseId" id="selectedCourse" required>

						<option value="${obj.course.courseId}">Select your Course</option>
						<c:forEach items="${courses}" var="course">
							<option value="${course.courseId}">${course.name}
								(Semester - ${course.semester})</option>
						</c:forEach>
					</select>
				</form>
				<br>
				<br>
				<p class='btn btn-primary' onclick="uploadFile();">Upload data</p>
				
				<div class = "instrucrion">
				<p style="color:red; font-size:15pt; font-family:'times new roman';"> 
					The first row of excel file must be set as respective column name <br>
					with the following attribute name .....
					<ul style="color:#990000;">
						<li> <strong> id </strong> or <strong> student id</strong> (required)</li>
						<li> <strong> Name </strong> or <strong> student Name</strong> (required) </li>
						<li> <strong> phone number </strong> or <strong> mobile number</strong>or <strong> pNumber</strong>(Optional) </li>
						<li> <strong> email id </strong> or <strong> email</strong>or <strong> emailId</strong>(Optional) </li>
						
					</ul>
					</div>
					

			</div>
		</fieldset>

	</div>

	<!-- /.container -->
</body>
</html>
<%@ include file="common/footer.jsp"%>
