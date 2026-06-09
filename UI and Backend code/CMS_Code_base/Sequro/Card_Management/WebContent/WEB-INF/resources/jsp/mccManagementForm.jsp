<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<script type="text/javascript">
	$(document).ready(function() {
		$('#mccListTable').DataTable({
			responsive : true
		}); 
		
		$('input:checkbox').removeAttr('checked');
		
		getMCC($('#cardNo').val());
		
		$('#selectAll').change(function() {
			if(this.checked) {
				$('input:checkbox').attr('checked','checked');		
			}else {
				$('input:checkbox').removeAttr('checked');
			}
		});
	});

	
	function getMCC(id) {
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "${pageContext.request.contextPath}/getCardMCC",
			data : "id="+ id,
			dataType : 'json',
			contentType : "application/x-www-form-urlencoded",
			timeout : 100000,
			success : function(data) {
				console.log("SUCCESS: ",data);
				$.each( data, function( key, value ) {
					  $('#'+value.lkpkey).attr('checked','checked');
					});
			},
			error : function(e) {
				console.log("ERROR: ",e);
				$('#delBinMsg').addClass(
								"tag tag-red");
				$('#delBinMsg').text("Error");
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
			<div class="col-md-3">
				<jsp:include page="CardDetailsLeftMenu.jsp"></jsp:include>
			</div>
			<div class="col-md-9">
				<form:form action="mccManagement" method="post" commandName="cardTypeModel"
					name="mccForm" id="mccForm" cssClass="card">
					<div class="card-header">
						<h3 class="card-title">NCMC Service Management</h3>
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

					<div class="card-body" align="center">


						<table id="mccListTable"
									class="table table-striped table-bordered nowrap"
									style="width: 100%">
									<thead>
										<tr>
											<th>
												<label class="custom-control custom-checkbox custom-control-inline">
													<input type="checkbox" id="selectAll" class="custom-control-input">
													<span class="custom-control-label"></span>
		                          				</label>
											</th>
											<th>Description</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="itr" items="${mccList}">
											<tr>
												<td> 
												<label class="custom-control custom-checkbox custom-control-inline">
													<form:checkbox  path="strMcc" id="${itr.lkpkey}" class="custom-control-input" value="${itr.lkpkey}"  />
													<span class="custom-control-label"></span>
		                          				</label>
												</td>
												<td>
													<span ><c:out value="${itr.lkpvalue}" /></span>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>


					</div>

					<div class="card-footer text-right">
						<div class="btn-list text-right">
							<button type="submit" id="submitBtn" name="strRequestBtn"
								value="1" class="btn btn-primary">Submit</button>
							<button type="submit" class="btn btn-secondary"
								name="strRequestBtn" value="2" id="resetBtn">Clear</button>
						</div>
					</div>
					<form:hidden path="strSelectID" id="cardNo"/>
					<input type="hidden" value="${leftCardMenuID}" id="leftCardMenuID">
				</form:form>
			</div>
		</div>
	</div>
</div>

