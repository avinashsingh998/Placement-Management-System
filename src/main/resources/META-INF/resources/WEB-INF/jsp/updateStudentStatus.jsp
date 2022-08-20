<%@ include file="common/header.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<script>


	function setDisable() {
		
		var selectedValue = $("#placed").text();
		var selectedId = $("#placed").val();
		if (selectedId == 'false') {
			$("#formField").prop('disabled', true);
			$("#selectCompany").prop('required',false);
			$("#salary").prop('required',false);
		} else {
			$("#formField").prop('disabled', false);
			$("#selectCompany").prop('required',true);
			$("#salary").prop('required',true);
			
		}
	}
		var companyId = new Array();
		var salary = new Array();
		
	function push(){
		if($("#company").val() != '' && $("#salary").val()!=''){
			companyId.push($("#company").val());
			var opt = 'check';
			opt = 'option'+$("#company").val();
			var opti = document.getElementById(opt);
			salary.push($("#salary").val());
			$("#company").prop('value', '');
			$("#salary").prop('value', '');
			opti.setAttribute('disabled', '');
			
			
		}
		console.log(companyId);
		console.log(salary);
		
	}
	
	function addDetails(studentId){
	
		if(companyId.length == 0 && $("#placed").val() == 'true'){
			
			alert("Choose the company/companies name and \nsalary/salaries first...")
			}
			
		else{
			$.ajax({
		
			url : '${ctx}/studentPU',
			type : 'POST',
			data : {
				companyId : companyId,
				salary : salary,
				studentId : studentId
			},
			
				
		success : function(data) {
				if (data == "success") {
					
					alert("Data updated successfully.")
				}
				else {
					alert("Failed")
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
<body onload = "setDisable();">




   

	<div class="well form-horizontal" >
		<h3 style="padding-left: 23%">
			<b>Updates/add companies for Student</b>
		</h3> 
		<br>

		<div 
			style="padding-left: 9%; padding-right: 13%; width: 100%; display: flex; text-align: center;">


			<br>
			<div id='check1' style="background-color: #dddddd; flex-grow: 1;">
				<br> <br> <select style="padding-left: 7%; padding-right: 4%;"
					name="placed" id="placed" onchange="setDisable();"
					class="form-control selectpicker" required>
					<option value="${student.placed}">Select student placement
						status</option>
					<option value="true">Placed</option>
					<option value="false">Not Placed</option>
				</select> <br> <br> <br>
				<button type="button" onclick="addDetails('${student.id}');" class="btn btn-info">Add Details</button>



			</div>

			<div style="flex-grow: 3; flex-direction: row;">
				<fieldset id="formField">
					<div style="background-color: #ffffff; width: 100%; display: flex; text-align: center;">
						<div
							style="width: 100%;display: flex;text-align: center;flex-direction: row;align-content: stretch;justify-content: flex-end;align-items: stretch;">
							<table class="table table-striped">
								<tr>
									<th>Company Name</th>
									<th>Offered salary</th>
							<!-- 		<th> </th>
 -->								</tr>
								<c:forEach items="${student.selectedInCompany}" var="comp">
									<tr>
										<td>${comp.company.name}</td>
										<td>${comp.salary}</td>
									<%-- 	<td><form action="/deleteCompany?cId=${comp.id}&id=${student.id}" method='post'> <button type='submit' style='border:none'><img src="image/cross.jpg" alt="remove" width="20" height="20"></button></form>
									 --%></tr>
								</c:forEach>
								
							
								
								
							</table>
						
								
						</div>
						<div
							style="background-color: #dddddd; flex-grow: 2; flex-direction: row;">
							<br> <br>
						<select name="company" id="company" >
						<option value='' > Select company </option>
						<c:forEach items="${companies}" var="camp">
						<option id="option${camp.cId}" value="${camp.cId}">${camp.name}</option>
						</c:forEach>
						 </select>
						  <br> <br>
						 
						 <label for="salary"> Input offered salary</label>
						
						 <input type="number" id="salary" name="salary">
						  <br> <br>
						  <p class='btn btn-primary' onclick="push();"> Push </p>
						</div>

					</div>
				</fieldset>

			</div>
		</div>
	</div>
	</body>
</html>
<%@ include file="common/footer.jsp"%>
