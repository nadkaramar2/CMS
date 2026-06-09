<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">
$(document).ready(function() {
	$('#accountCreationTable').DataTable({
		responsive : true
	});
	
});


function editFunction(custId,mobileNo) 
{
	$('#strCustId').val(custId);
	console.log(custId);
	$('#strMobileNo').val(mobileNo);
	console.log(mobileNo);
	$('#customerIDCreation').submit();
}

</script>

<div class="my-3 my-md-5">
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<%-- <jsp:include page="accountCreationLeftMenu.jsp"></jsp:include> --%>
				<jsp:include page="customerAccountLeftMenu.jsp"></jsp:include>
			</div>
			<div class="col-md-9">
				<form:form action="editCustomerAccountsDetails" method="GET" commandName="customerIDCreation" name="customerIDCreation" id="customerIDCreation" 
				cssClass="card" >
					<div class="card-header">
						<h3 class="card-title">View or Edit Customer Account &nbsp;&nbsp;</h3>
						<h2 id="errorMsg" class="tag tag-red"></h2>
						<div class="text-right" style="display: ${display};" id="errorMsg">
							 <c:if test="${not empty exception}">
								<div class="text-right badge badge-danger" style="display: ${display};">
									<c:out value="${exception}"></c:out>
								</div>
							</c:if>
							<c:if test="${not empty success}">
								<div class="text-right badge badge-success" style="display: ${display};">
									<c:out value="${success}"></c:out>
								</div>
							</c:if>
							<c:if test="${not empty error}">
								<div class="text-right badge badge-danger" style="display: ${display};">
									<c:out value="${error}"></c:out>
								</div>
							</c:if> 
						</div>
					</div>

					<div class="modal-body">
						<div class="card-body">
							<table id="accountCreationTable" class="table table-striped table-bordered nowrap" style="width: 100%">
								<thead>
									<tr>
										<th>Customer ID</th>
										<th>First Name</th>
										<th>Mobile No</th>
										<th></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="itr" items="${customerAccDetailsList}">
										<tr>
											<td>${itr.strCustId}</td>
											<td>${itr.strFirstName}</td>
											<td>${itr.strMobileNo}</td>
											<td> 
												<a class=icon href=javascript:editFunction("${itr.strCustId}","${itr.strMobileNo}")><i class='fe fe-edit'></i></a>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							 <form:input type="hidden" path="strCustId" id="strCustId" cssClass="form-control" />
							  <form:input type="hidden" path="strMobileNo" id="strMobileNo" cssClass="form-control" /> 
							<%-- <input type="hidden" value="${leftAccountMenuID}" id="leftAccountMenuID"> --%>
							<input type="hidden" value="${leftCustomerAccountMenuID}" id="leftCustomerAccountMenuID">
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>