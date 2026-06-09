<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
$(function () {
    $('#datetimepicker1').datepicker({
    	format: "yyyy/mm/dd"
    });
    
    $('#strCountryCode').change(function() 
    {
    	$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "${pageContext.request.contextPath}/getStateList",
			data: "id="+$(this).val(),
			dataType : 'json',
			contentType : "application/x-www-form-urlencoded",
			timeout : 100000,
			success : function(data) {
				console.log("SUCCESS: ", data);
				
				$('#strState').empty()
				
				 $('#strState').append($("<option></option>")
				  .attr("value","").text("Select")); 
				
				$.each(data, function(key, value) 
			    {  
				     $('#strState')
				         .append($("<option></option>")
				                    .attr("value",value.strID)
				                    .text(value.strStateName)); 
				});
					
			},
			error : function(e) {
				console.log("ERROR: ", e);
			},
			done : function(e) {
				console.log("DONE");
			}
		});
	});
    
    
    $('#strState').change(function() 
    {
    	$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "${pageContext.request.contextPath}/getCityList",
			data: "id="+$(this).val(),
			dataType : 'json',
			contentType : "application/x-www-form-urlencoded",
			timeout : 100000,
			success : function(data) {
				console.log("SUCCESS: ", data);
				
				$('#strCity').empty()
				
				 $('#strCity').append($("<option></option>")
				  .attr("value","").text("Select")); 
				
				$.each(data, function(key, value) {  
				     $('#strCity')
				         .append($("<option></option>")
				                    .attr("value",value.strID)
				                    .text(value.strCityName)); 
				});
					
			},
			error : function(e) {
				console.log("ERROR: ", e);
			},
			done : function(e) {
				console.log("DONE");
			}
		});
	});
    
    
    $.validator.addMethod(
			"regex",
			function(value, element, regexp) {
    		var check = false;
    		return this.optional(element) || regexp.test(value);
		},
		"ss"
	);
	
	var value = $("#creditCardCustomerAccountCreation").validate({
		rules: {
            strFirstName: {
                required: true,
                regex: /^[a-zA-Z]+$/,
                minlength: 2,
                maxlength: 30         
            },
            strMiddleName: {
                required: false,
                regex: /^[a-zA-Z]+$/
                     
            },
            strLastName: {
                required: true,
                regex: /^[a-zA-Z]+$/,
                minlength: 2,
                maxlength: 30         
            },
            strExtAccount: {
                required: true,
                regex: /^[a-zA-Z]+$/,
                minlength: 2,
                maxlength: 30         
            },
            strGender: {
                required: true      
            },
            strDOB: {
                required: true        
            },
            strDocumentType: {
                required: true        
            },
            strDocumentNumber: 
            {
                required: true,
                regex: /^[a-zA-Z0-9]+$/,
                minlength: 8,
                maxlength: 20         
            },
            strAddress1: {
                required: true,
                regex: /^[a-zA-Z0-9\s]+$/,
                minlength: 2,
                maxlength: 40         
            },
            strAddress2: {
                required: true,
                regex: /^[a-zA-Z0-9\s]+$/,
                minlength: 2,
                maxlength: 40         
            },
            strCountryCode: {
                required: true        
            },
            strState: {
                required: true        
            },
            strCity: {
                required: true       
            },
            strAddressType: {
                required: true       
            },
            strMotherMaidenName: {
                required: true,
                regex: /^[a-zA-Z]+$/,
                minlength: 2,
                maxlength: 40         
            },
            strEmailID: {
                required: true,
                email: true,
                minlength: 2,
                maxlength: 90         
            },
            strPinCode: {
                required: true,
                regex: /^[0-9]+$/,
                minlength: 6,
                maxlength: 6         
            },
		},
    	messages: {
            strFirstName: {
                required: "Please Enter First Name!",
                minlength: "Minmum {0} characters required!",
                maxlength: "Maximum {0} characters allowed!",
                regex: "only Char allow !"
            },
            strMiddleName: {
                required: "Please Enter Middle Name!",
                regex: "only Char allow !"
            },
            strLastName: {
                required: "Please Enter Last Name!",
                minlength: "Minmum {0} characters required!",
                maxlength: "Maximum {0} characters allowed!",
                regex: "only Char allow !"
            },
            strExtAccount: {
                required: "Please Enter Ext Account No!",
                minlength: "Minmum {0} characters required!",
                maxlength: "Maximum {0} characters allowed!",
                regex: "only Numeric allow !"
            },
            strGender: {
                required: "Required !"
            },
            strDOB: {
            	required: "Required !"
            },
            strDocumentType: {
            	required: "Required !"
            },
            strDocumentNumber: {
                required: "Please Enter Document Number!",
                minlength: "Minmum {0} characters required!",
                maxlength: "Maximum {0} characters allowed!",
                regex: "only Alphanumeric allow !"
            },
            strAddress1: {
                required: "Please Enter Address1!",
                minlength: "Minmum {0} characters required!",
                maxlength: "Maximum {0} characters allowed!",
                regex: "only Alphanumeric allow !"
            },
            strAddress2: {
                required: "Please Enter Address2!",
                minlength: "Minmum {0} characters required!",
                maxlength: "Maximum {0} characters allowed!",
                regex: "only Alphanumeric allow !"
            },
            strCountryCode: {
                required: "Required !"
            },
            strState: {
                required: "Required !"
            },
            strCity: {
                required: "Required !"
            },
            strAddressType: {
                required: "Required !"
            },
            strMotherMaidenName: {
                required: "Please Enter Mother Maiden Name!",
                minlength: "Minmum {0} characters required!",
                maxlength: "Maximum {0} characters allowed!",
                regex: "only Char allow !"
            },
            strPinCode: {
                required: "Please Enter Pin Code!",
                minlength: "Minmum {0} characters required!",
                maxlength: "Maximum {0} characters allowed!",
                regex: "only Numeric allow !"
            },
            strEmailID: {
                required: "Please Enter Email ID!",
                email: "Invalid Email",
                minlength: "Minmum {0} characters allowed!",
                maxlength: "Maximum {0} characters allowed!"
            }
    	},
		submitHandler: function(form) {
			form.submit();	
		}
	});
    
});
</script>
<div class="my-3 my-md-5">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<form:form action="generateCardAccount" method="POST" commandName="creditCardCustomerAccountCreation"
					name="creditCardCustomerAccountCreation" id="creditCardCustomerAccountCreation" cssClass="card">

					<div class="card-header">
						<h3 class="card-title">Card Account Creation</h3>
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
					<div class="row">
						<div class="col-md-6">
							<div class="card-body">
								<h3 class="card-title">Card Account Creation</h3>
								<div class="row">
									
									<div class="col-md-6 col-lg-6">
										<div class="form-group">
											<label class="form-label">First Name</label>
											<form:input path="strFirstName" id="strFirstName"
												cssClass="form-control" />
										</div>
									</div>
									<div class="col-md-6 col-lg-6">
										<div class="form-group">
											<label class="form-label">Middle Name</label>
											<form:input path="strMiddleName" id="strMiddleName"
												cssClass="form-control" />
										</div>
									</div>
									<div class="col-md-6 col-lg-6">
										<div class="form-group">
											<label class="form-label">Last Name</label>
											<form:input path="strLastName" id="strLastName"
												cssClass="form-control" />
										</div>
									</div>
									
									<div class="col-md-6 col-lg-6">
										<div class="form-group">
											<label class="form-label">External Account Number</label>
											<form:input path="strExtAccount" id="strExtAccount"
												cssClass="form-control" />
										</div>
									</div>
									
									<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Mother Maiden Name</label>
									<form:input path="strMotherMaidenName" id="strMotherMaidenName"
										cssClass="form-control" />
								</div>
							</div>
								</div>
							</div>
						</div>
						
						<div class="col-md-6">
							<div class="card-body">
								<h3 class="card-title">Address</h3>
								<div class="row">
									<div class="col-md-6 col-lg-6">
										<div class="form-group">
											<label class="form-label">Address Line 1</label>
											<form:input path="strAddress1" id="strAddress1"
												cssClass="form-control" />
										</div>
									</div>
									<div class="col-md-6 col-lg-6">
										<div class="form-group">
											<label class="form-label">Address Line 2</label>
											<form:input path="strAddress2" id="strAddress2"
												cssClass="form-control" />
										</div>
									</div>

									<div class="col-md-6 col-lg-6">
										<div class="form-group">
											<label class="form-label">Country</label>
											<form:select path="strCountryCode" id="strCountryCode"
												cssClass="form-control selectpicker">
												<form:option value="">Select</form:option>
												<c:forEach items="${countryList}" var="itr">
													<form:option value="${itr.strID}">${itr.strCountryName}</form:option>
												</c:forEach>
											</form:select>
										</div>
									</div>

									<div class="col-md-6 col-lg-6">
										<div class="form-group">
											<label class="form-label">State</label>
											<form:select path="strState" id="strState"
												cssClass="form-control selectpicker">
												<form:option value="">Select</form:option>
											</form:select>
										</div>
									</div>

									<div class="col-md-6 col-lg-6">
										<div class="form-group">
											<label class="form-label">City</label>
											<form:select path="strCity" id="strCity"
												cssClass="form-control selectpicker">
												<form:option value="">Select</form:option>
											</form:select>
										</div>
									</div>

									<div class="col-md-6 col-lg-6">
										<div class="form-group">
											<label class="form-label">Postal Code</label>
											<form:input path="strPinCode" id="strPinCode"
												cssClass="form-control" />
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-md-6">
							<div class="card-body">
								<h3 class="card-title">Email</h3>
								<div class="row">
									<div class="col-md-6 col-lg-6">
										<div class="form-group">
											<label class="form-label">Email ID</label>
											<form:input path="strEmailID" id="strEmailID"
												cssClass="form-control" />
										</div>
									</div>
									
									<div class="col-md-6 col-lg-6">
										<div class="form-group">
											<label class="form-label">Phone Number</label>
											<form:input path="strPhoneNo" id="strPhoneNo"
												cssClass="form-control" />
										</div>
									</div>
									
								</div>
							</div>
						</div>
					</div>
					
					<div class="card-body">
						<h3 class="card-title">Personal Information</h3>
						<div class="row">
							
							<div class="col-md-6 col-lg-3">
								<div class="form-group">
									<label class="form-label">Date Of Birth</label>
									<div class='input-group date' id='datetimepicker1'>
										<form:input path="strDOB" id="strDOB"
										cssClass="form-control" readonly="true"/>
										<span
											class="input-group-addon input-group-append" id="basic-addon2"> <span
											class="input-group-text"><i class="fe fe-calendar"></i></span>
										</span>
									</div>
								</div>
							</div>
						
							<div class="col-md-6 col-lg-3">
								<div class="form-group">
									<label class="form-label">Gender</label>
									<form:select path="strGender" id="strGender"
										cssClass="form-control custom-select">
										<form:option value="M">Male</form:option>
										<form:option value="F">Female</form:option>
									</form:select>
								</div>
							</div>
							
							<div class="col-md-6 col-lg-3">
								<div class="form-group">
									<label class="form-label">Document Type</label>
									<form:select path="strDocumentType" id="strDocumentType"
										cssClass="form-control custom-select">
										<form:option value="strBvn">BVN</form:option>
									</form:select>
								</div>
							</div>
							
							<div class="col-md-6 col-lg-3">
								<div class="form-group">
									<label class="form-label">Document Value</label>
									<form:input path="strBvnNumber" id="strBvnNumber"
										cssClass="form-control" />
								</div>
							</div>
							</div>
						</div>
						
						<div class="card-body">
						<h3 class="card-title">Card Type Information</h3>
						<div class="row">
							
							<div class="col-md-6 col-lg-3">
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
							
							<div class="col-md-6 col-lg-3">
								<div class="form-group">
									<label class="form-label">Bin <b>*</b></label>
									<form:select path="strCardBin" id="strCardBin"
										cssClass="form-control selectpicker">
										<form:option value=""></form:option>
										<c:forEach items="${binList}" var="itr">
											<form:option value="${itr.strBin}">${itr.strBin}</form:option>
										</c:forEach>
									</form:select>
									<span class="error" id="strBinError"><form:errors
											path="strBin"></form:errors></span>
								</div>
							</div>
							
							<div class="col-md-6 col-lg-3">
								<div class="form-group">
									<label class="form-label">Card Issue Code</label>
									<form:input path="strCardIssueCode" id="strCardIssueCode" readonly="true"
									value = "V"
										cssClass="form-control" />
								</div>
							</div>
							
								<div class="col-md-6 col-lg-3">
								<div class="form-group">
									<label class="form-label">Branch Code</label>
									<form:select path="strBranchCode" id="strBranchCode"
										cssClass="form-control selectpicker">
										<c:forEach items="${branchCodeList}" var="itr">
											<form:option value="${itr.strBranchCode}">${itr.strBranchCode}</form:option>
										</c:forEach>
									</form:select>
								</div>
							</div>
							</div>
						</div>
						
						<div class="card-body">
						<h3 class="card-title">Emboss Information</h3>
						<div class="row">
							
							<div class="col-md-6 col-lg-3">
								<div class="form-group">
									<label class="form-label">Emboss Line 1</label>
									<form:input path="strEmbossLine1" id="strEmbossLine1"
										cssClass="form-control" />
								</div>
							</div>
							
							<div class="col-md-6 col-lg-3">
								<div class="form-group">
									<label class="form-label">Encode First Name</label>
									<form:input path="strEncodeFirstName" id="strEncodeFirstName"
										cssClass="form-control" />
								</div>
							</div>
						
							<div class="col-md-6 col-lg-3">
								<div class="form-group">
									<label class="form-label">Encode Last Name</label>
									<form:input path="strEncodeLastName" id="strEncodeLastName"
										cssClass="form-control" />
								</div>
							</div>
							
							<div class="col-md-6 col-lg-3">
								<div class="form-group">
									<label class="form-label">Function Name</label>
									<form:input path="strFunction" id="strFunction" readonly="true"
									value = "NEWCARDREQ"
										cssClass="form-control" />
								</div>
							</div>
							
							</div>
						</div>
						
						
						<div class="card-body">
						<h3 class="card-title">Account Information</h3>
						<div class="row">
							
							<div class="col-md-6 col-lg-3">
								<div class="form-group">
									<label class="form-label">Debit Account Number</label>
									<form:input path="strDebitAccountNumber" id="strDebitAccountNumber"
										cssClass="form-control" />
								</div>
							</div>	
							
							<div class="col-md-6 col-lg-3">
								<div class="form-group">
									<label class="form-label">Bank Customer Id</label>
									<form:input path="strBankCustId" id="strBankCustId"
										cssClass="form-control" />
								</div>
							</div>
						
							<div class="col-md-6 col-lg-3">
								<div class="form-group">
									<label class="form-label">Institution ID</label>
									<form:input path="strInstititionId" id="strInstititionId"
										cssClass="form-control" />
								</div>
							</div>
							
							<div class="col-md-6 col-lg-3">
								<div class="form-group">
									<label class="form-label">Request Id</label>
									<form:input path="strRequestId" id="strRequestId"
										cssClass="form-control" />
								</div>
							</div>
							</div>
						</div>
						
					<form:hidden path="strSelectID"/>
					<div class="card-footer text-right">
						<div class="d-flex">
							<button type="submit" class="btn btn-primary ml-auto">Submit</button>
						</div>
					</div>
					
				</form:form>
				
			</div>
		</div>
	</div>
</div>
