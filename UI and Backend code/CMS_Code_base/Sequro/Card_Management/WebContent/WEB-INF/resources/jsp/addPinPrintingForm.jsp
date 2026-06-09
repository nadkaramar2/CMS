<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(document).ready(function() {
		$('#keyTable').DataTable({
			responsive : true
		});
		
			$("#genEmboss").click(function() {
			if ($('input[type=radio][name=cardType]:checked').length == 0) {
				$('#errorMsg').text("Please select atleast one");
				return false;
			} else {
				return true;
			}
		});

	});
</script>

<div class="my-3 my-md-5">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<form:form action="addPinPrinting" method="post"
					commandName="pinPrintingModel" name="addPinPrintingForm" id="addPinPrintingForm"
					cssClass="card">

					<div class="card-header">
						<h3 class="card-title">Pin Mailer Printing</h3>
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
								<div class="card-body" style="width: 100%;" align="left">
									<table id="keyTable"
										class="table table-striped table-bordered nowrap"
										style="width: 100%">
										<thead>
											<tr>
												<th>Select</th>
												<th>Card Type</th>
												<th>Count</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="itr" items="${pinList}">
												<tr>
													<td><label class="custom-control custom-radio"> <form:radiobutton
													path="cardType"
													cssClass="custom-control-input approveID"
													value="${itr.cardType}|${itr.cardTypeDesc}" id="id" />
													<div class="custom-control-label"></div>
													</label></td>
													<td>${itr.cardTypeDesc}</td>
													<td>${itr.count}</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							<div class="card-footer text-right">
								<div class=".d-flex1">
									<button type="submit" id="genEmboss" class="btn btn-primary ml-auto">Execute
										Pin Mailer Printing</button>
								</div>
							</div>

				</form:form>
			</div>
		</div>
	</div>
</div>
