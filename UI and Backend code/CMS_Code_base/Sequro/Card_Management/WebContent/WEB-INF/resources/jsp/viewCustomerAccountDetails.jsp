<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">
 	$(document).ready(function() {
		$('#accountDetailsTbl').DataTable({
			responsive : true
		});
		
		var value = $("#customerIdCreationConfig").validate(
		{
			rules : 
			{
			},
			messages : 
			{
			},
			submitHandler : function(form)
			{
				//alert('search clicked....');
				//form.submit();
				//$('#myModal').modal('show');
				//$('#myModal2').modal('show');
			}
		});
	});
 	
 	function closePopup()
	{
		$('#myModal2').modal('toggle');	
	}
 	function closeAccountStatementPopup()
 	{
 		$('#myModal').modal('toggle');
 	}
 	
 	function viewBalance(acBalance) 
 	{
 		$('#myModal2').modal({backdrop: 'static', keyboard: false}, 'show');
 		$('#ac_balance').val(acBalance);
 	}
 	
 	function viewStatement(accountType, accountNumber) 
 	{
 		$('#myModal').modal({backdrop: 'static', keyboard: false}, 'show');
 		
 		$('#strAccountType').val(accountType);
 		$('#strAccountNumber').val(accountNumber);
 	}
 	
 	function searchbtn()
 	{
 		console.log('---Search clicked-----------');
 		
 		var strfromDate = $('#fromDate').val();
 		console.log('strfromDate::'+strfromDate);
 		
 		$('#strfromDate').val(strfromDate);
 		
 		var strToDate = $('#toDate').val();
 		console.log('strToDate::'+strToDate);
 		
 		$('#strtoDate').val(strToDate);
 		
 		closeAccountStatementPopup();
 		
 		$('#customerIdCreationConfig').submit();
 	}
</script>

<div class="my-3 my-md-5">
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<jsp:include page="customerInformationLeftMenu.jsp"></jsp:include>
			</div>
			<div class="col-9">
				<form:form action="viewCustomerAccountStatement" method="POST" commandName="customerIdCreationConfig" name="customerIdCreationConfig"
					id="customerIdCreationConfig" cssClass="card">
					
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
						<h3 class="card-title">Account Details</h3>
					</div>
					
					<div class="modal-body">
						<div class="card-body">
							<table id="accountDetailsTbl" class="table table-striped table-bordered nowrap" style="width: 100%">
								<thead>
									<tr>
										<!-- <th>Select</th> -->
										<!-- <th>Account Name</th> -->
										<th>A/c Type</th>
										<th>A/c Number</th>
										<th>Status</th>
										<th>Statement</th>
										<th>Balance</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="itr" items="${accountDetailsList}">
										<tr>
											<%-- <td><label class="custom-control custom-radio">
												<form:radiobutton path="strAccountNumber" cssClass="custom-control-input" value="${itr.strAccountNumber}" id="strAccountNumber" />
													<div class="custom-control-label"></div>
											</label></td> --%>											
											<%-- <td>${itr.strFirstName}</td> --%>																					
											<td>${itr.strAccountType}</td>
											<td>${itr.strAccountNumber}</td>
											<td>${itr.strStatus}</td>
											<td><a href=javascript:viewStatement("${itr.strAccountType}","${itr.strAccountNumber}")><i class="fa fa-eye" style="color: cadetblue;">&nbsp;view</i></a>
											<td><a href=javascript:viewBalance("${itr.strClosingBalance}","${itr.strAccountNumber}")><i class="fa fa-eye" style="color: cadetblue;">&nbsp;view</i></a></td>
											<%-- <td><a style='color: blue !important;text-decoration: underline !important;' class=icon href=javascript:viewStatement("${itr.strAccountType}","${itr.strAccountNumber}")>View Account Statement</i></a></td>
											<td><a style='color: blue !important;text-decoration: underline !important;' class=icon href=javascript:viewBalance("${itr.strClosingBalance}")>View Available balance</i></a></td> --%>
										</tr>
									</c:forEach>
								</tbody>
							</table>							
							<input type="hidden" value="${leftCustomerInfoMenuId}" id="leftCustomerInfoMenuId">
						</div>
					</div>
					
					<!-- Modal -->
					<div class="modal fade" id="myModal" role="dialog">
						<div class="modal-dialog modal-lg">
							<div class="modal-content">
								<div class="modal-header">								
									<h5 class="modal-title">Search Account Statement</h5>
        							<button type="button" class="close" aria-label="Close" onclick="closeAccountStatementPopup();"></button>
								</div>								
								<div class="modal-body">
									<div class="col-md-6 col-lg-6">
										<div class="form-group">
											<label class="form-label">From Date <b>*</b></label>
											<div class='input-group date'>
												<form:input path="" id="fromDate" cssClass="form-control" readonly="false" type="date" />
												<span class="input-group-addon input-group-append" id="basic-addon2"> </span>
											</div>
										</div>
									</div>
									<div class="col-md-6 col-lg-6">
										<div class="form-group">
											<label class="form-label">To Date <b>*</b></label>
											<div class='input-group date'>
												<form:input path="" id="toDate" cssClass="form-control" readonly="false" type="date" />
												<span class="input-group-addon input-group-append" id="basic-addon2"> </span>
											</div>
										</div>
									</div>
								</div>
								<div class="modal-footer">
									<div class="text-right" style="margin-bottom: 14px; margin-right: 14px;">
										<div class=".d-flex1">
											<button type="submit" id="submitBtn" class="btn btn-primary ml-auto" onclick="searchbtn()">Search</button>
											<button type="reset" class="btn btn-primary ml-auto" id="resetBtn">Clear</button>
										</div>
									</div>
					      		</div>
							</div>
						</div>
					</div>
				<!-- Modal -->
				
				<!-- Modal -->
				<div class="modal fade" id="myModal2" role="dialog">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title">Account Balance Info</h5>
        							<button type="button" class="close" aria-label="Close" onclick="closePopup();"></button>
							</div>
							<div class="modal-body">
							
								<div class="form-inline">
								    <div class="col-md-12 form-group">
								         <label class="col-sm-6 col-form-label"  for="name">Your Account Balance :</label>
								         <input type="text" class="form-control" name="ac_balance" id="ac_balance" disabled/>
								    </div>
								</div>
								 
							</div>
							<div class="modal-footer">
					      </div>
						</div>
					</div>
				</div>
				<!-- Modal -->
					<form:input type="hidden" path="strAccountType" id="strAccountType" cssClass="form-control" />
					<form:input type="hidden" path="strAccountNumber" id="strAccountNumber" cssClass="form-control" />
					<form:input type="hidden" path="strfromDate" id="strfromDate" cssClass="form-control" />
					<form:input type="hidden" path="strtoDate" id="strtoDate" cssClass="form-control" />
					
				</form:form>
			</div>
		</div>
	</div>
</div>