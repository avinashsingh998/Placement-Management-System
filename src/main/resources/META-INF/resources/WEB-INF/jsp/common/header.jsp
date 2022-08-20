<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
	<c:set var="ctx" value="${pageContext.request.contextPath}" />
	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Placement Management</title>
 <link rel = "icon" href = 
"./image/logo.jpg" 
        type = "image/x-icon">
<link
	href="${ctx}/css/bootstrap.min.css"
	rel="stylesheet"
	>
<link
	href="${ctx}/css/customStyle.css"
	rel="stylesheet">
<script src="${ctx}/js/jquery-1.9.1.min.js"></script>
<script>
	function setActiveClass(selector) {
		
		if(selector){
		$(selector).addClass("active");
		}

	}
</script>
</head>
<body>
	<nav class="navbar" style="background-color: #e3f2fd;">
		<div class="container-fluid customContainer">
		<c:if test =  "${ctx ne ''}">
			<a class="navbar-brand" href='${ctx}'>  <img src="image/home-icon.png"
				alt="" width="40" height="44" class="d-inline-block align-text-top">
				Placement Management Cell
			</a>
		</c:if>
		
		<c:if test = "${ctx eq ''}">
			<a class="navbar-brand" href='/'>  <img src="image/home-icon.png"
				alt="" width="40" height="44" class="d-inline-block align-text-top">
				Placement Management Cell
			</a></c:if>

			<ul class="nav nav-tabs navlist" >
				<li class="nav-item"><a class="nav-link" id="selector1"
					 aria-current="page" href="${ctx}/course">Add
						new course</a></li>
				<li class="nav-item"><a class="nav-link"
					id="selector2" href="${ctx}/studentA"> Add
						new Student </a></li>
				<li class="nav-item"><a class="nav-link"
					 id="selector3" href="${ctx}/company">Add
						new company</a></li>
				<li class="nav-item"><a class="nav-link "
					href="https://invertisuniversity.ac.in" target = " blank" ><img
						src="image/img1.jpg" alt="HOME" width="45" height="38"
						class="d-inline-block align-text-top"> </a></li>
			</ul>
		</div>
	</nav>
	</br>
	</br>
	</br>

</body>
</html>