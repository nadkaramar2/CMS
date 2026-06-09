<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<script type="text/javascript">
	$(document).ready(function() {
		$('#bulkTable').DataTable({
			responsive : true
		});
		
		//table.clear();
	});
</script>


<div class="my-3 my-md-5">
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<jsp:include page="customerDetailsLeftMenu.jsp"></jsp:include>
			</div>
			<div class="col-9">
				<form:form action="viewAccountLinkageDetails" method="GET"
					commandName="cardAccountLinkage" name="cardAccountLinkage"
					id="cardAccountLinkage" cssClass="card">

					<div class="card-header">
					<h3 class="card-title">
						<i class="fe fe-user-plus"></i> &nbsp;&nbsp;Linkage Card and
						Account <span class="text-right badge badge-danger" id="strError"></span>
					</h3>
					<div class="text-right" style="display: ${display};" id="errorMsg">
						<c:if test="${not empty exception}">
							<div class="text-right badge badge-danger"
								style="display: ${display};">
								<c:out value="${exception}"></c:out>
							</div>
						</c:if>
						<c:if test="${not empty success}">
							<div class="text-right badge badge-success"
								style="display: ${display};">
								<c:out value="${success}"></c:out>
							</div>
						</c:if>
						<c:if test="${not empty error}">
							<div class="text-right badge badge-danger"
								style="display: ${display};">
								<c:out value="${error}"></c:out>
							</div>
						</c:if>
					</div>
			</div>


			<div class="modal-body">
				<div class="card-body">
					<table id="bulkTable" class="table table-striped table-bordered nowrap" style="width: 100%">
						<thead>
							<tr>
								<th>Account Type</th>
								<th>Account Number</th>
								<th>Card Number</th>
								<th>Card Type</th>
							</tr>
						</thead>
						<tbody>
								<c:forEach var="itr" items="${cardAccountLinkList}">
									<tr>
										<td>${itr.strAccountType}</td>
										<td>${itr.strAccountNumber}</td>
										<td>${itr.strCardNumber}</td>
										<td>${itr.strCardType}</td>
										
									</tr>
								</c:forEach>
							</tbody>
					</table>

					<input type="hidden" value="${leftCustomerMenuID}"
						id="leftCustomerMenuID">
				</div>

			</div>
			</form:form>
		</div>
	</div>
</div>
</div>
