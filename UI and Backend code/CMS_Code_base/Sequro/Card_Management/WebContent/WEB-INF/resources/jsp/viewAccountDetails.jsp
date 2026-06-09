<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">

<!-- //Created by prashant for view Account details -->

	$(document).ready(function() 
	{
		$('#datetimepicker1').datepicker(
		{
			format : "yyyy/mm/dd"			
		
		});
		
		/* let countrycode = ${strCountryCode};
		$('#strCountryCode').val(countrycode);
        $('#strState').val(${strState});
		$('#strCity').val(${strCity}); */
		
		
		//$("b").css({"display":"contents", "color":"red"});
		
		//Account info
		$("#strAccountType").attr("readonly", true);
		$("#strAccountNumber").attr("readonly", true);
		$("#strAccountCatogory").attr("readonly", true);
		
		
		//personal Info
		$("#strTitle").attr("readonly", true);
		$("#strFirstName").attr("readonly", true);
		$("#strMiddleName").attr("readonly", true);
		$("#strLastName").attr("readonly", true);
		$("#strGender").attr("readonly", true);
		$("#strDOB").attr("readonly", true);
		$("#strEmailId").attr("readonly", true);
		$("#strMobileNo").attr("readonly", true);
		
		
		//Address information		
		$("#strAddress1").attr("readonly", true);
		$("#strAddress2").attr("readonly", true);
		$("#strAddress3").attr("readonly", true);
		$("#strPincode").attr("readonly", true);
		$("#strCity").attr("readonly", true);
		$("#strState").attr("readonly", true);
		$("#strCountryCode").attr("readonly", true);
		/* $("#strPhoneNo").attr("readonly", true); */
		
		//Account Related Info
		$("#strAccountCreationDate").attr("readonly", true);
		$("#strAccountIssuedDate").attr("readonly", true);
		$("#strAssignedSingleLimit").attr("readonly", true);
		$("#strAssignedDailyLimit").attr("readonly", true);
		$("#strAssignedMonthlyLimit").attr("readonly", true);
		$("#strAssignedYearlyLimit").attr("readonly", true);
		$("#strDailyAvailableLimit").attr("readonly", true);
		$("#strMonthlyAvailableLimit").attr("readonly", true);
		$("#strYearlyAvailableLimit").attr("readonly", true);
		$("#strAssignedCreditLimit").attr("readonly", true);
		$("#strAvailabeCreditLimit").attr("readonly", true);
		
		$('#strCountryCode').change(function() 
		{
			$.ajax(
			{
				type : "POST",
				contentType : "application/json",
				url : "${pageContext.request.contextPath}/getStateList",
				data : "id=" + $(this).val(),
				dataType : 'json',
				contentType : "application/x-www-form-urlencoded",
				timeout : 100000,
				success : function(data)
				{
					console.log("SUCCESS: ", data);
					$('#strState').empty();
					$('#strState').append($("<option></option>").attr("value", "").text("Select"));

					$.each(data,function(key, value) 
					{
						$('#strState').append($("<option></option>").attr("value",value.strID).text(value.strStateName));
					});
				},
				error : function(e) 
				{
					console.log("ERROR: ", e);
				},
				done : function(e) 
				{
					console.log("DONE");
				}
			});
		});

		$('#strState').change(function() 
		{
			$.ajax(
			{
				type : "POST",
				contentType : "application/json",
				url : "${pageContext.request.contextPath}/getCityList",
				data : "id=" + $(this).val(),
				dataType : 'json',
				contentType : "application/x-www-form-urlencoded",
				timeout : 100000,
				success : function(data) 
				{
					console.log("SUCCESS: ", data);
					$('#strCity').empty();
					$('#strCity').append($("<option></option>").attr("value", "").text("Select"));

					$.each(data, function(key, value)
					{
						$('#strCity').append($("<option></option>").attr("value", value.strID).text(value.strCityName));
					});
				},
				error : function(e) 
				{
					console.log("ERROR: ", e);
				},
				done : function(e)
				{
					console.log("DONE");
				}
			});
		});
	});
							
</script>

<div class="my-3 my-md-5">
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<jsp:include page="customerDetailsLeftMenu.jsp"></jsp:include>
			</div>
			<div class="col-9">
				<form:form action="viewAccountDetailsForm" method="GET" commandName="accountDetails" name="accountDetails" id="accountDetails" cssClass="card">
					<div class="card-header">
						<h3 class="card-title">
							<i class="fe fe-user-plus"></i> &nbsp;&nbsp; Customer Account Details <span class="text-right badge badge-danger" id="strError"></span>
						</h3>
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
					
					<div class="card-header">
						<h3 class="card-title">&nbsp;&nbsp; Account Details</h3>
					</div>
					<div class="card-body">
						<div class="row">
							<div class="col-md-4 col-lg-4">
								<div class="form-group">
									<label class="form-label">Account Number <span class="tx-danger"><b>*</b></span></label>
									<form:input path="strAccountNumber" id="strAccountNumber" cssClass="form-control" readonly="true" value="${strAccountNumber}" />
									<span class="error" id="strAccountNumberError"><form:errors path="strAccountNumber"></form:errors></span>
								</div>
							</div>
							<div class="col-md-4 col-lg-4">
								<div class="form-group">
									<label class="form-label">Account Type <span class="tx-danger"><b>*</b></span></label>
									<form:input path="strAccountType" id="strAccountType" cssClass="form-control" readonly="true" value="${strAccountType}" />
									<span class="error" id="strAccountTypeError"><form:errors path="strAccountType"></form:errors></span>
								</div>
							</div>
							<div class="col-md-4 col-lg-4">
								<div class="form-group">
									<label class="form-label">Account Type Catogory<span class="tx-danger"><b>*</b></span></label>
									<form:input path="strAccountCatogory" id="strAccountCatogory" cssClass="form-control" readonly="true" value="${strAccountCatogory}" />
									<span class="error" id="strAccountCatogoryError"><form:errors path="strAccountCatogory"></form:errors></span>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-4 col-lg-4">
								<div class="form-group">
									<label class="form-label">Account Creation Date<b>*</b></label>
									<form:input path="strAccountCreationDate" id="strAccountCreationDate" cssClass="form-control" readonly="true" value="${strAccountCreationDate}" />
								</div>
							</div>
							<div class="col-md-4 col-lg-4">
								<div class="form-group">
									<label class="form-label">Account Issued Date<b>*</b></label>
									<form:input path="strAccountIssuedDate" id="strAccountIssuedDate" cssClass="form-control" readonly="true" value="${strAccountIssuedDate}" />
								</div>
							</div>
							<div class="col-md-4 col-lg-4">
								<div class="form-group">
									<label class="form-label">Available Balance<b>*</b></label>
									<form:input path="strAvailableBalance" id="strAvailableBalance" cssClass="form-control"  readonly="true" value="${strAvailableBalance}" />
								</div>
							</div>
						</div>	
					</div>
					<div class="card-header">
						<h3 class="card-title">&nbsp;&nbsp; Personal Details</h3>
					</div>
					<div class="card-body">
						<div class="row">
							<div class="col-md-4 col-lg-4">
								<div class="form-group">
									<label class="form-label">First Name<b>*</b></label>
									<form:input path="strFirstName" id="strFirstName" cssClass="form-control" readonly="true" value="${strFirstName}" />
								</div>
							</div>
							<div class="col-md-4 col-lg-4">
								<div class="form-group">
									<label class="form-label">Middle Name<b>*</b></label>
									<form:input path="strMiddleName" id="strMiddleName" cssClass="form-control" readonly="true" value="${strMiddleName}" />
								</div>
							</div>
							<div class="col-md-4 col-lg-4">
								<div class="form-group">
									<label class="form-label">Last Name<b>*</b></label>
									<form:input path="strLastName" id="strLastName" cssClass="form-control" readonly="true" value="${strLastName}" />
								</div>
							</div>
							<div class="col-md-4 col-lg-4">
								<div class="form-group">
									<label class="form-label">Gender<b>*</b></label>
									<form:select path="strGender" id="strGender" readonly="true" value="${strGender}" cssClass="form-control custom-select">
										<form:option value="">Select</form:option>
										<form:option value="Male">Male</form:option>
										<form:option value="Female">Female</form:option>
									</form:select>
								</div>
							</div>
							<div class="col-md-4 col-lg-4">
								<div class="form-group">
									<label class="form-label">Date Of Birth<b>*</b></label>
									<div class='input-group date' id='datetimepicker1'>
										<form:input path="strDOB" id="strDOB" cssClass="form-control" readonly="true" value="${strDOB}" disabled="true"/>
										<span class="input-group-addon input-group-append" id="basic-addon2">
											<span class="input-group-text"><i class="fe fe-calendar"></i></span>
										 	<span class="error" id="strDOBError"><form:errors path="strDOB"></form:errors></span>
										</span>
									</div>
								</div>
							</div>
							<div class="col-md-4 col-lg-4">
								<div class="form-group">
									<label class="form-label">Email ID<b>*</b></label>
									<form:input path="strEmailId" id="strEmailId" cssClass="form-control" readonly="true" value="${strEmailId}" />
								</div>
							</div>
							<div class="col-md-4 col-lg-4">
								<div class="form-group">
									<label class="form-label">Address Line 1<b>*</b></label>
									<form:input path="strAddress1" id="strAddress1" cssClass="form-control" readonly="true" value="${strAddress1}" />
								</div>
							</div>
							<div class="col-md-4 col-lg-4">
								<div class="form-group">
									<label class="form-label">Address Line 2<b>*</b></label>
									<form:input path="strAddress2" id="strAddress2" cssClass="form-control" readonly="true" value="${strAddress2}" />
								</div>
							</div>
							<div class="col-md-4 col-lg-4">
								<div class="form-group">
									<label class="form-label">Address Line 3<b>*</b></label>
									<form:input path="strAddress3" id="strAddress3" cssClass="form-control"  readonly="true" value="${strAddress3}" />
								</div>
							</div>
							<div class="col-md-4 col-lg-4">
								<div class="form-group">
									<label class="form-label">Country<b>*</b></label>
									<form:input path="strCountry" id="strCountry" cssClass="form-control"  readonly="true" value="${strCountry}" />
								</div>
							</div>
							<%-- <div class="col-md-4 col-lg-4">
								<div class="form-group">
									<label class="form-label">Country<b>*</b></label>
									<form:select path="strCountryCode" id="strCountryCode" readonly="true" cssClass="form-control selectpicker">
										<form:option value="">Select</form:option>
										<c:forEach items="${countryList}" var="itr">
											<form:option value="${itr.strID}">${itr.strCountryName}</form:option>
										</c:forEach>
									</form:select>
								</div>
							</div> --%>
							<div class="col-md-4 col-lg-4">
								<div class="form-group">
									<label class="form-label">State<b>*</b></label>
									<form:input path="strState" id="strState" cssClass="form-control"  readonly="true" value="${strState}" />
								</div>
							</div>
							<%-- <div class="col-md-4 col-lg-4">
								<div class="form-group">
									<label class="form-label">State<b>*</b></label>
									<form:select path="strState" id="strState" readonly="true" cssClass="form-control selectpicker">
										<form:option value="">Select</form:option>
									</form:select>
								</div>
							</div> --%>
							<div class="col-md-4 col-lg-4">
								<div class="form-group">
									<label class="form-label">City<b>*</b></label>
									<form:input path="strCity" id="strCity" cssClass="form-control"  readonly="true" value="${strCity}" />
								</div>
							</div>
							<%-- <div class="col-md-4 col-lg-4">
								<div class="form-group">
									<label class="form-label">City<b>*</b></label>
									<form:select path="strCity" id="strCity" readonly="true" cssClass="form-control selectpicker">
										<form:option value="">Select</form:option>
									</form:select>
								</div>
							</div> --%>
							<div class="col-md-4 col-lg-4">
								<div class="form-group">
									<label class="form-label">Mobile Number<b>*</b></label>
									<form:input path="strMobileNo" id="strMobileNo" readonly="true" cssClass="form-control" value="${strMobileNo}" />
								</div>
							</div>
							<div class="col-md-4 col-lg-4">
								<div class="form-group">
									<label class="form-label">Postal Code<b>*</b></label>
									<form:input path="strPincode" id="strPincode" readonly="true" cssClass="form-control" value="${strPincode}" />
								</div>
							</div>
						</div>
					</div>

					<div class="card-header">
						<h3 class="card-title">&nbsp;&nbsp; Assigned Limits</h3>
					</div>
					<div class="card-body">
						<div class="row">
							<%-- <div class="col-md-4 col-lg-4">
								<div class="form-group">
									<label class="form-label">Account Creation Date<b>*</b></label>
									<form:input path="strAccountCreationDate" id="strAccountCreationDate" cssClass="form-control" readonly="true" value="${strAccountCreationDate}" />
								</div>
							</div>
							<div class="col-md-4 col-lg-4">
								<div class="form-group">
									<label class="form-label">Account Issued Date<b>*</b></label>
									<form:input path="strAccountIssuedDate" id="strAccountIssuedDate" cssClass="form-control" readonly="true" value="${strAccountIssuedDate}" />
								</div>
							</div> --%>
							<div class="col-md-4 col-lg-4">
								<div class="form-group">
									<label class="form-label">Assigned Single Limits<b>*</b></label>
									<form:input path="strAssignedSingleLimit" id="strAssignedSingleLimit" cssClass="form-control" readonly="true" value="${strAssignedSingleLimit}" />
								</div>
							</div>
							<div class="col-md-4 col-lg-4">
								<div class="form-group">
									<label class="form-label">Assigned Daily Limits<b>*</b></label>
									<form:input path="strAssignedDailyLimit" id="strAssignedDailyLimit" cssClass="form-control" readonly="true" value="${strAssignedDailyLimit}" />
								</div>
							</div>
							<div class="col-md-4 col-lg-4">
								<div class="form-group">
									<label class="form-label">Assigned Monthly Limits<b>*</b></label>
									<form:input path="strAssignedMonthlyLimit" id="strAssignedMonthlyLimit" cssClass="form-control" readonly="true" value="${strAssignedMonthlyLimit}" />
								</div>
							</div>
							<div class="col-md-4 col-lg-4">
								<div class="form-group">
									<label class="form-label">Assigned Yearly Limits<b>*</b></label>
									<form:input path="strAssignedYearlyLimit" id="strAssignedYearlyLimit" cssClass="form-control" readonly="true" value="${strAssignedYearlyLimit}" />
								</div>
							</div>
							<div class="col-md-4 col-lg-4">
								<div class="form-group">
									<label class="form-label">Assigned Credit Limit<b>*</b></label>
									<form:input path="strAssignedCreditLimit" id="strAssignedCreditLimit" cssClass="form-control" readonly="true" value="${strAssignedCreditLimit}" />
								</div>
							</div>
							
						</div>
					</div>
					
					<div class="card-header">
						<h3 class="card-title">&nbsp;&nbsp; Available Limits</h3>
					</div>
					<div class="card-body">
						<div class="row">
							<div class="col-md-4 col-lg-4">
								<div class="form-group">
									<label class="form-label">Available Daily Limits<b>*</b></label>
									<form:input path="strDailyAvailableLimit" id="strDailyAvailableLimit" cssClass="form-control" readonly="true" value="${strDailyAvailableLimit}" />
								</div>
							</div>
							<div class="col-md-4 col-lg-4">
								<div class="form-group">
									<label class="form-label">Available Monthly Limits<b>*</b></label>
									<form:input path="strMonthlyAvailableLimit" id="strMonthlyAvailableLimit" cssClass="form-control" readonly="true" value="${strMonthlyAvailableLimit}" />
								</div>
							</div>
							<div class="col-md-4 col-lg-4">
								<div class="form-group">
									<label class="form-label">Available Yearly Limits<b>*</b></label>
									<form:input path="strYearlyAvailableLimit" id="strYearlyAvailableLimit" cssClass="form-control" readonly="true" value="${strYearlyAvailableLimit}" />
								</div>
							</div>
							
							<div class="col-md-4 col-lg-4">
								<div class="form-group">
									<label class="form-label">Available Credit Limit<b>*</b></label>
									<form:input path="strAvailableCreditLimit" id="strAvailableCreditLimit" cssClass="form-control" readonly="true" value="${strAvailableCreditLimit}" />
								</div>
							</div>
						</div>
					</div>
					
					<div class="card-header">
						<h3 class="card-title">&nbsp;&nbsp; Outstanding</h3>
					</div>
					<div class="card-body">
						<div class="row">
							<div class="col-md-4 col-lg-4">
								<div class="form-group">
									<label class="form-label">Total Principal Outstanding</label>
									<form:input path="strTotalPrincipalOutStanding" id="strTotalPrincipalOutStanding" cssClass="form-control" readonly="true" value="${strTotalPrincipalOutStanding}" />
								</div>
							</div>
							<div class="col-md-4 col-lg-4">
								<div class="form-group">
									<label class="form-label">Total Interest Outstanding</label>
									<form:input path="strTotalInterestOuStanding" id="strTotalInterestOuStanding" cssClass="form-control"  readonly="true" value="${strTotalInterestOuStanding}" />
								</div>
							</div>
						</div>
					</div>
					
					<div class="card-header">
						<!-- <h3 class="card-title">&nbsp;&nbsp; if It is credit card category No</h3> -->
						<h3 class="card-title">&nbsp;&nbsp; Others</h3>
					</div>
					<div class="card-body">
						<div class="row">
							<div class="col-md-4 col-lg-4">
								<div class="form-group">
									<label class="form-label">Number of Balance Loading<b>*</b></label>
									<form:input path="strNoOfBalanceLoading" id="strNoOfBalanceLoading" cssClass="form-control" readonly="true" value="${strNoOfBalanceLoading}" />
								</div>
							</div>
							<div class="col-md-4 col-lg-4">
								<div class="form-group">
									<label class="form-label">Total Balance Loaded<b>*</b></label>
									<form:input path="strTotalBalanceLoaded" id="strTotalBalanceLoaded" cssClass="form-control" readonly="true" value="${strTotalBalanceLoaded}" />
								</div>
							</div>
							<div class="col-md-4 col-lg-4">
								<div class="form-group">
									<label class="form-label">Date of Dormancy<b>*</b></label>
									<form:input path="strDateofDormancy" id="strDateofDormancy" cssClass="form-control"  readonly="true" value="${strDateofDormancy}" />
								</div>
							</div>
						</div>
					</div>
					
					
					<input type="hidden" value="${leftCustomerMenuID}" id="leftCustomerMenuID">
				</form:form>
			</div>
		</div>
	</div>
</div>
