<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">
 	$(document).ready(function() {
		$('#accountCardDetailsTbl').DataTable({
			responsive : true
		});
	});
</script>

<div class="my-3 my-md-5">
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<jsp:include page="customerInformationLeftMenu.jsp"></jsp:include>
			</div>
			<div class="col-9">
				<form:form action="" method="POST" commandName="customerCardDetailsConfig" name="customerCardDetailsConfig"
					id="customerCardDetailsConfig" cssClass="card">
					
					<div class="card-header">
						<h3 class="card-title">Customer Basic Information &nbsp;&nbsp;</h3>
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
					<div class="card-body">
						<div class="row">
							<div class="col-md-6 col-lg-6">
									<div class="form-group">
										<label class="form-label">Customer Id</label>
									  	<form:input path="strCustId" id="strCustId" cssClass="form-control" readonly="true"/> 
									</div>
							</div>
							<div class="col-md-6 col-lg-6">
									<div class="form-group">
										<label class="form-label">Customer Name</label>
									  	<form:input path="strAccountHolderName" id="strAccountHolderName" cssClass="form-control" readonly="true"/> 
									</div>
							</div>
						</div>
					</div>
					
					<div class="card-header">
						<h3 class="card-title">Card Details</h3>
					</div>
					
					<div class="modal-body">
						<div class="card-body">
							<table id="accountCardDetailsTbl" class="table table-striped table-bordered nowrap" style="width: 100%">
								<thead>
									<tr>
										<th>Card Number</th>
										<th>Card Type</th>
										<th>Card Description</th>
										<th>Card Status</th>
										
									</tr>
								</thead>
								<tbody>
									<c:forEach var="itr" items="${accountCardDetailsList}">
										<tr>
											<td>${itr.strCardNumber}</td>
											<td>${itr.strCardType}</td>
											<td>${itr.strCardDescription}</td>
											<td>${itr.strCardStatus}</td>																							
										</tr>
									</c:forEach>
								</tbody>
							</table>							
							<input type="hidden" value="${leftCustomerInfoMenuId}" id="leftCustomerInfoMenuId">
						</div>
					</div>					
					
				</form:form>
			</div>
		</div>
	</div>
</div>