
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	var table;
	$(document).ready(function() {
		$('#bulkTable').DataTable({
			responsive : true,
			aaSorting : [ [ 1, 'desc' ] ]
		});
		table = $('#bulkDataTable').DataTable({
			scrollX : 100,
			scroller : true
		});
		table.clear();
	});
</script>
<div class="my-3 my-md-5">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<form:form action="#" method="post" commandName="bulkUpload"
					name="addBulkUploadForm" id="addBulkUploadForm"
					enctype="multipart/form-data" cssClass="card">
					<div class="card-header">
						<h3 class="card-title">Transaction History</h3>
						<h2 id="errorMsg" class="tag tag-red"></h2>
						<div class="messages" style="display: ${display};" id="errorMsg">
							<c:if test="${not empty exception}">
								<div class="badge badge-danger" style="display: ${display};">
									<c:out value="${exception}"></c:out>
								</div>
							</c:if>
							<c:if test="${not empty success}">
								<div class="badge badge-success" style="display: ${display};">
									<c:out value="${success}"></c:out>
								</div>
							</c:if>
							<c:if test="${not empty error}">
								<div class="badge badge-danger" style="display: ${display};">
									<c:out value="${error}"></c:out>
								</div>
							</c:if>
						</div>
					</div>

					<div class="card-body">
						<table id="bulkTable"
							class="table table-striped table-bordered nowrap"
							style="width: 100%">
							<thead>
								<tr>
									<th>Type</th>
									<th>Description</th>
									<th>Reference Id</th>
									<th>Amount</th>
									<th>Date</th>
									<th>Status</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="itr" items="${txnList}">
									<tr>
										<td>${itr.type}</td>
										<td>${itr.description}</td>
										<td>${itr.refid}</td>
										<td>${itr.amount}</td>
										<td>${itr.txndate}</td>
										<td>${itr.status}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>

					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>
