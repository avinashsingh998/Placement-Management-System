<%@ include file="common/header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script src="${ctx}/js/jspdf-1.3.4.min.js"></script>
<script src="${ctx}/js/jspdf.plugin.autotable-3.5.6.min.js"></script>


<script>

	// The function below will start the confirmation dialog
	function confirmAction(studentId) {
		
		
		let confirmAction = confirm("Are you want to delete this student record?");
		if (confirmAction) {
			window.location.assign("${ctx}/studentD?id=" + studentId);
		} else {
			return;
		}
	}

	function updateStudentList() {
		
		var courseId = "${course.courseId}";
		var sortBy = $("#sortBy").val();
		var name = $("#searchName").val();
		var placed = $("#placed").val();
		var cId = $("#cId").val();

		window.location.assign("${ctx}/courses?courseId=" + courseId + "&name=" + name
				+ "&sortBy=" + sortBy + "&cId=" + cId + "&placed=" + placed);

	}
	
	
	
	

	function shareData() {
		
		
		
		var doc = new jsPDF('p', 'pt', 'letter');  
	    var htmlstring = '';  
	    var tempVarToCheckPageHeight = 0;  
	    var pageHeight = 0; 
	  
	    pageHeight = doc.internal.pageSize.height;  
	    
	    margins = {  
	        top: 40,  
	        bottom: 00,  
	        left: 4,  
	        right: 10,  
	        width: 700  
	    };  
	   
	     doc.autoTable({  
	        head: [["S.N", "Id", "Name", "Highest Package", "Phone No"]] ,
	         
	        	body: info,
	        
	        theme: 'grid',  
	        columnStyles: {
	           //  1: {columnWidth: 28, fillColor: [103,129,191], fontStyle: 'bold', textColor: (255,255,255)},
	            0: {columnWidth: 30},
	            1: {columnWidth: 78},
	            2: {columnWidth: 180},
	            3: {columnWidth: 120},
	            4: {columnWidth: 120},
		           
	            
		            columnWidth: 'wrap'
	          },

	    rowStyles: {
	          1: {  rowHeight: 20, fillColor: [100,100,167], fontStyle: 'bold', textColor: (255,255,255)}
	          },

	    })  
	    doc.save('studentData.pdf');  

	}
</script>
</head>
<body>
	<script> var info = [];
						var sn = 0;
						</script>
	<nav class="navbar navbar-expand-lg bg-light">
		<div class="container-fluid">

			<h6 style="color: red">Course - ${course.name}(Semester -
				${course.semester})</h6>
			&nbsp &nbsp

			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">

					<li class="nav-item dropdown"><select id="cId"
						onchange="updateStudentList()" class="form-control selectpicker">
							<option value="${company.cId}">${company.name}</option>
							<c:forEach items="${companies}" var="comp">
								<option value="${comp.cId}">${comp.name}</option>
							</c:forEach>
							<option value="">Unselect</option>

					</select></li>
					<li>&nbsp &nbsp</li>


					<li class="nav-item dropdown"><select id="placed"
						onchange="updateStudentList()" class="form-control selectpicker">
							<option value="${placedV}">${placed}</option>
							<option value="true">Only Placed Students</option>
							<option value="false">Only Not Placed Students</option>
							<option value="">All students</option>

					</select></li>
					<li>&nbsp &nbsp</li>

					<li class="nav-item dropdown"><select id="sortBy"
						onchange="updateStudentList()" class="form-control selectpicker">
							<option value="${sortBy}">${sortBy}</option>
							<option value="name">Name</option>
							<option value="id">Id</option>
							<option value="highestPackage">Highest Package</option>
					</select></li>

				</ul>
				<div class="d-flex">
					<input class="form-control me-2" id="searchName" type="search"
						placeholder="Search by name" aria-label="Search">
					<button class="btn btn-outline-success"
						onclick="updateStudentList()">Search</button>
				</div>
			</div>
		</div>
	</nav>


	<c:if test="${msg ne null}">
		<h1 style="color: red; text-align: center;">${msg}</h1>
	</c:if>

	<c:if test="${!students.isEmpty()}">


		<table class="table table-striped" id="studentTable"
		style = "background: rgba(255,255,255,0.61);"
			name="studentTable">
			<tr>
				<th>S.N</th>
				<th>Student Id</th>
				<th>Name</th>
				<th>Highest Package</th>
				<th>Email Id</th>
				<th>Phone number</th>
				<th>Placed</th>
				<th>Actions</th>
			</tr>



			<%!int sn = 0;%>
			<c:forEach items="${students}" var="student">
				<tr
					style="color: #ff0000; font-family: 'times new roman'; font-size: 14pt;">
					<td><%=++sn%></td>
					<td>${student.id}</td>
					<td>${student.name}</td>
					<td>${student.highestPackage}</td>
					<td><a style = "text-decoration: none;" href="mailto:${student.emailId}">
							${student.emailId} </a></td>
					<td>${student.phoneNumber}</td>
					<td><a href='<c:url value = "/studentPU?id=${student.id}"/>'>
							<c:if test="${student.placed eq true}">
								<button class="btn btn-success">Placed</button>
							</c:if> <c:if test="${student.placed eq false}">
								<button class="btn btn-danger">Not Placed</button>
							</c:if>
					</a></td>
					<td><a href='<c:url value = "/studentU?id=${student.id}"/>'><button
								class="btn btn-warning">Update</button></a> <%-- <a	id = "delete" href='<c:url value = "/studentD?id=${student.id}"/>'><button onclick='confirmAction()'  class="btn btn-danger">Delete</button></a> --%>
						&nbsp &nbsp
						<button onclick="confirmAction('${student.id}')"
							class="btn btn-danger">Delete</button></td>
				</tr>
				<script> var temp =[];
				temp.push(++sn, "${student.id}", "${student.name}", "${student.highestPackage}", "${student.phoneNumber}" );
				info.push(temp);
				</script>
			</c:forEach>
			<%
			sn = 0;
			%>
		</table>
		<br>


		<div style="width: 100%; display: flex; text-align: center;">
			<div style="flex-grow: 1;">
				<a style ="text-decoration: none;"
					href="mailto:<c:forEach items="${students}" var="student">
			${student.emailId};
			</c:forEach>">

					<img src="image/mailLogo.png" style="height: 67pt;"
					class="img-fluid" alt="Mail to Everyone ">
					<h5 style="color: black">Mail to Everyone</h5>
				</a>
			</div>

			<div onclick="shareData();" style="flex-grow: 1; padding-top: 15pt; text-align: left;">

				<img src="image/saveData.jpg" style="height: 43pt;" class="img-fluid"
					alt="Download as PDF">
			</div>

		</div>





	</c:if>
</body>
</html>
<%@ include file="common/footer.jsp"%>
