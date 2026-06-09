<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(document).ready(function() {
		$('#binTable').DataTable({
			responsive : true
		});

		$('#keyTable').DataTable({
			responsive : true
		});
		
		$('#viewBtn').click(function() {
			$('#myModal').modal('show');
		})
		
		$("#genEmboss").click(function() 
		{
			if ($('input[type=radio][name=cardType]:checked').length == 0) {
				$('#errorMsg').text("Please select atleast one");
				return false;
			} else {
				return true;
			}
		});

		$("#downloadBtn").click(function() 
		 {
			if ($('input[type=radio][name=strSelectID]:checked').length == 0) {
				$('#errorMsg').text("Please select atleast one");
				return false;
			}
			else 
			{
				return true;
			}
		});
	});
</script>

<div class="my-3 my-md-5">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<form:form action="addEmbossing" method="post"
					commandName="embossRequest" name="client" id="addInstForm"
					cssClass="card">

					<div class="card-header">
						<h3 class="card-title">Embossing</h3>
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
					<c:choose>
						<c:when test="${embossCardList.size() > 0 }">
								<%-- <div class="card-body">

								<div class="col-md-6 col-lg-6">
									<div class="form-group">
										<label class="form-label">Emboss Type <b>*</b></label>
										<form:select path="flag" id="flag"
											cssClass="form-control selectpicker">
											<form:option value="Y">Instant Card</form:option>
											<form:option value="N">Personalized card</form:option>
										</form:select>


									</div>
								</div>
								
								<div class="col-md-6 col-lg-6">
									<div class="form-group">
										<label class="form-label"></b></label>
										<button type="button" id="viewBtn" name="viewBtn"
							class="btn btn-default" data-dismiss="modal">View</button>
									</div>
								</div>
							</div> --%>
								<div class="card-body" style="width: 100%;" align="left">
									<table id="keyTable"
										class="table table-striped table-bordered nowrap"
										style="width: 100%">
										<thead>
											<tr>
												<th>Select</th>
												<th>Card Type</th>
												<th>Card Issue</th>
												<th>Count</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="itr" items="${embossCardList}">
												<tr>
													<td><label class="custom-control custom-radio"> <form:radiobutton
													path="cardType"
													cssClass="custom-control-input approveID"
													value="${itr.id}|${itr.eflag}|${itr.cardType}" id="id" />
													<div class="custom-control-label"></div>
													</label></td>
													<td>${itr.cardType}</td>
													<td>${itr.flag}</td>
													<td>${itr.count}</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							<div class="card-footer text-right">
								<div class=".d-flex1">
									<button type="submit" id="genEmboss" class="btn btn-primary ml-auto">Generate
										Embossing</button>
								</div>
							</div>
						</c:when>
						<c:otherwise>
							<br>
								&nbsp;&nbsp; Record Not Found For Embossing ....
					</c:otherwise>
					</c:choose>
					<br>
					<br>
					<div class="card-body" style="width: 100%;" align="left">
						<table id="binTable"
							class="table table-striped table-bordered nowrap">
							<thead>
								<tr>
									<!-- <th>Select</th> -->
									<th>Select</th>
									<th>File Name</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="itr" items="${embossList}">
									<tr>
										<td><label class="custom-control custom-radio"> <form:radiobutton
													path="strSelectID"
													cssClass="custom-control-input approveID"
													value="${itr.filePath}" id="strSelectID" />
												<div class="custom-control-label"></div>
										</label></td>
										<td>${itr.fileName}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<div class="modal-footer">
						<button type="submit" id="downloadBtn" name="downloadBtn"
							class="btn btn-default" data-dismiss="modal">Download</button>
					</div>

					<!-- Modal -->
					<div class="modal fade" id="myModal" role="dialog">
						<div class="modal-dialog modal-lg">
							<div class="modal-content">
								<div class="modal-header">
									<p class="text-capitalize">
										Emboss Pending Card Type Count <label id="delBinMsg"></label>
									</p>
								</div>
								<div class="modal-body">



									<div class="card-body">
										<table id="keyTable"
											class="table table-striped table-bordered nowrap"
											style="width: 100%">
											<thead>
												<tr>
													<th>Card Type</th>
													<th>Card Issue</th>
													<th>Count</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="itr" items="${embossCardList}">
													<tr>
														<td>${itr.cardType}</td>
														<td>${itr.flag}</td>
														<td>${itr.count}</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>


								<div class="modal-footer"></div>
							</div>
						</div>
					</div>
					<!-- Modal -->

				</form:form>
			</div>
		</div>
	</div>
</div>
