
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
		
		$('#processBtn').click(function () {
			$('#delBinMsg').text("");
			$.ajax({
				type : "POST",
				contentType : "application/json",
				url : "${pageContext.request.contextPath}/processBatchData",
				data : "id=" + $('#fileName').val(),
				dataType : 'json',
				contentType : "application/x-www-form-urlencoded",
				timeout : 100000,
				success : function(data) {
					console.log("SUCCESS: ", data);
					$('#delBinMsg').addClass("tag tag-green");
					$('#delBinMsg').text('Processing Batch Data...');
				},
				error : function(e) {
					console.log("ERROR: ", e);
					$('#delBinMsg').addClass("tag tag-green");
					$('#delBinMsg').text("Processing Batch Data...");
				},
				done : function(e) {
					console.log("DONE");
				}
			});
		});
		
	});
	function showFileData(id) {
		$('#fileName').val(id);
		$('#delBinMsg').text("");
		$('#myModal').modal('show');
		
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "${pageContext.request.contextPath}/getBatchData",
			data : "id=" + id,
			dataType : 'json',
			contentType : "application/x-www-form-urlencoded",
			timeout : 100000,
			success : function(data) {
				console.log("SUCCESS: ", data);
				
				table.clear();

				$.each(data, function(id, value) {
					var data = JSON.parse(value.lkpkey);
					table.row.add([
						data.serviceBean.inFunction,
						data.serviceBean.citizenID,
						data.serviceBean.cifKey,
						data.serviceBean.inEncodeFirstName,
						data.serviceBean.inEncodeMiddleName,
						data.serviceBean.inEncodeLastName,
						value.resp
			           ]).draw();
				})
			},
			error : function(e) {
				console.log("ERROR: ", e);
				$('#strError').addClass("tag tag-red");
				$('#strError').text("Error");
			},
			done : function(e) {
				console.log("DONE");
			}
		});
		
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "${pageContext.request.contextPath}/getCountBatch",
			data : "id=" + id,
			dataType : 'json',
			contentType : "application/x-www-form-urlencoded",
			timeout : 100000,
			success : function(data) {
				console.log("SUCCESS: ", data);
				if(data <= 0) {
					$('#processBtn').hide();
				}else {
					$('#processBtn').show();
				}
			},
			error : function(e) {
				console.log("ERROR: ", e);
				$('#strError').addClass("tag tag-red");
				$('#strError').text("Error");
			},
			done : function(e) {
				console.log("DONE");
			}
		});
		
	}
	
</script>
<div class="my-3 my-md-5">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<form:form action="addBulkUpload" method="post"
					commandName="bulkUpload" name="addBulkUploadForm"
					id="addBulkUploadForm" enctype="multipart/form-data"
					cssClass="card">

					<div class="card-header">
						<h3 class="card-title">Bulk Upload</h3>
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
						<div class="row">

							<div class="col-md-3">
								<div class="form-group">
									<label class="form-label">Upload File(.txt allow)</label> <input
										type="file" name="file" />
								</div>
							</div>
						</div>
					</div>
					<div class="card-footer text-right">
						<div class=".d-flex1">
							<button type="submit" id="submitBtn"
								class="btn btn-primary ml-auto">Submit</button>
						</div>
					</div>

					<div class="card-body">
						<table id="bulkTable"
							class="table table-striped table-bordered nowrap"
							style="width: 100%">
							<thead>
								<tr>
									<th>File Name</th>
									<th>Created Date</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="itr" items="${bulkList}">
									<tr>
										<td onclick="showFileData('${itr.fileName}')"><a href="#">${itr.fileName}</a></td>
										<td>${itr.createdDate}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>

					<!-- Modal -->
					<div class="modal fade" id="myModal" role="dialog">
						<div class="modal-dialog modal-lg">
							<div class="modal-content">
								<div class="modal-header">
									<p class="text-capitalize">
										Batch Data <label id="delBinMsg"></label>
									</p>
								</div>
								<div class="modal-body">
									<table id="bulkDataTable"
										class="table table-striped table-bordered nowrap"
										style="width: 100%">
										<thead>
											<tr>
												<th >Request</th>
												<th >Citizen ID</th>
												<th >CIF Key</th>
												<th >Encode First Name</th>
												<th >Encode Middle Name</th>
												<th >Encode Last Name</th>
												<th >Status</th>
											</tr>
										</thead>
									</table>

								</div>


								<div class="modal-footer">
									<button type="button" class="btn btn btn-red" id="processBtn">Process</button>
									<button type="button" class="btn btn-default"
										data-dismiss="modal">Close</button>
								</div>
							</div>
						</div>
					</div>
					<!-- Modal -->
					<input type="hidden" id="fileName">
				</form:form>
			</div>
		</div>
	</div>
</div>
