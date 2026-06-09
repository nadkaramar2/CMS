
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	var table;
	$(document).ready(function() {
		$('#bulkTable').DataTable({
			responsive : true,
			aaSorting: [[1, 'desc']]
		});
		table = $('#bulkDataTable').DataTable({
			scrollX: 100,
			 scroller: true
			});
		table.clear();
	});
</script>
<div class="my-3 my-md-5">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
					<form action="#" method="post" class="card">
					<div class="card-header">
						<h3 class="card-title">Inventory Management</h3>
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
									<th>Card Type</th>
									<th>Sold</th>
									<th>Unsold</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="itr" items="${inventroy}">
									<tr>
										<td>${itr.cardType}</td>
										<td>${itr.sold}</td>
										<td>${itr.unsold}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					</form>
			</div>
		</div>
	</div>
</div>
