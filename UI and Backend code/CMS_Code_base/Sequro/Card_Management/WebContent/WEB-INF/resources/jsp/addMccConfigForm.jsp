<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">

$(document).ready(function() {
	
	$('input:checkbox').removeAttr('checked');
	
	$('#mccListTable').DataTable({
		responsive : true
	}); 
	
	getMCC($('#strCardType').val());
	
	$('#selectAll').change(function() {
		if(this.checked) {
			$('input:checkbox').attr('checked','checked');		
		}else {
			$('input:checkbox').removeAttr('checked');
		}
	});
	
	$('#strCardType').change(function() {
		$('input:checkbox').removeAttr('checked');
		getMCC($(this).val());
	});
	
});
function getMCC(id) {
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "${pageContext.request.contextPath}/getCardTypeMCC",
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
			<div class="col-12">

				<form:form action="addMCCConfig" method="post"
					commandName="cardTypeModel" name="addMCCConfig" id="addMCCConfig"
					cssClass="card">

					<div class="card-header">
						<h3 class="card-title">
							<i class="fe fe-user-plus"></i> &nbsp;&nbsp;MCC Configuration <span class="text-right badge badge-danger" id="strError"></span>
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
					
					<div class="card-body">
						<div class="row">

							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Card Type <b>*</b></label>
									<form:select path="strCardType" id="strCardType"
										cssClass="form-control selectpicker">
										<form:option value=""></form:option>
										<c:forEach items="${cardTypeList}" var="itr">
											<form:option value="${itr.strID}">${itr.strCardType} : ${itr.strCardDesc}</form:option>
										</c:forEach>
									</form:select>
									<span class="error" id="strCardTypeError"><form:errors
											path="strCardType"></form:errors></span>
								</div>
							</div>
						</div>
						
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
						<div class=".d-flex1">
							<button type="submit" id="submitBtn"
								class="btn btn-primary ml-auto">Submit</button>
							<button type="reset" class="btn btn-primary ml-auto"
								id="resetBtn">Clear</button>
						</div>
					</div>
					
				</form:form>
			</div>
		</div>
	</div>
</div>
