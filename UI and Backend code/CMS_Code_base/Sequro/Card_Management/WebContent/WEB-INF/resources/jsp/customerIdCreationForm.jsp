<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
   var successAttrVal = '${success}';
</script>
<script type="text/javascript">
	var deleteBinID;
	var accountCreationData;
	var selectedDoumentType;

	$(document).ready(function() 
	{	
		$("b").css({"display":"contents", "color":"red"});
		
		$('#datetimepicker1').datepicker(		{
			format : "yyyy/mm/dd"
		});

		function removeTime(date = new Date() ) 
		{
			return new Date(date.getFullYear(), date.getMonth(), date.getDate());
		}
		
		$("#strDOB").on('change', function() 
		{
			var date = removeTime(new Date($(this).val()));
			var currentDate = removeTime(new Date(Date.now()));
			if (date >= currentDate) 
			{
				 alert('Invalid Date');
				$(this).val('');
			}

		});
		
		 /* Add Validation PoA */
		$('#strAddressProofDocumentId').change(function()
		{
			selectedPOADocumentType = $(this).val();
		});
		
		/*  Added by shruti for validation  related changes START */  
		$("#strIdentityProofDocumentId").change(function() 
		{
	        $("#strIdentityProofDocumentVal").html("");
	        $("#strIdentityProofDocumentValue").val("");
		}); 
		 
		$("#strIdentityProofDocumentValue").change(function() 
		{
			 $("#strIdentityProofDocumentValErr").html("");
			 var type = $("#strIdentityProofDocumentId").val();
			 var value = $("#strIdentityProofDocumentValue").val();
			 if(type == 1)
			 {
				  var regex = /^[A-Z]{3}[ABCFGHLJPTF]{1}[A-Z]{1}[0-9]{4}[A-Z]{1}/;
				  var result = regex.test(value);
				  if(!result){
					  $("#strIdentityProofDocumentValErr").html("Invalid Pan format!"); 
					 } 
			  } else {
				  var regex1 = /^[0-9]{4}[ -]?[0-9]{4}[ -]?[0-9]{4}$/;
				  var result1 = regex1.test(value);
				  if(!result1){
					  $("#strIdentityProofDocumentValErr").html("Invalid Addhar format!");
				  } 
			  }
		  });  
  		/*  Added by shruti for validation  related changes END */

		$('#strCountry').change(function() 
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
					//console.log("SUCCESS: ", data);
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

		$.validator.addMethod("regex", function(value, element, regexp) {
			var check = false;
			return this.optional(element) || regexp.test(value);
		}, "ss");

		var value = $("#customerIdCreationConfig").validate(
		{
			rules : 
			{
				strTitle : {
					required : true
				},
				strFirstName : {
					required : true,
					regex : /^[a-zA-Z]+$/,
					minlength : 2,
					maxlength : 30
				},
				strMiddleName : {
					required : false,
					regex : /^[a-zA-Z]+$/

				},
				strLastName : {
					required : true,
					regex : /^[a-zA-Z]+$/,
					minlength : 2,
					maxlength : 30
				},
				strPhoneCode : {
					required : true
				},
				strMobileNo : {
					required : true,
					regex : /^[0-9]+$/,
					minlength : 10,
					maxlength : 10
				},
				strGender : {
					required : true
				},
				strDOB : {
					required : true
				},
				strDocumentType : {
					required : true
				},
				strDocumentNumber : {
					required : true,
					regex : /^[a-zA-Z0-9]+$/,
					minlength : 8,
					maxlength : 20
				},
				strAddress1 : {
					required : true,
					regex : /^[a-zA-Z0-9\s]+$/,
					minlength : 2,
					maxlength : 40
				},
				strCountry : {
					required : true
				},
				strState : {
					required : true
				},
				strCity : {
					required : true
				},
				strAddressType : {
					required : true
				},
				strEmailID : {
					required : true,
					email : true,
					minlength : 2,
					maxlength : 90
				},
				strPinCode : {
					required : true,
					regex : /^[0-9]+$/,
					minlength : 6,
					maxlength : 6
				},
				strAddressProofDocumentId : {
					required : true,
				},
				strAddressProofDocumentValue : {
					required : true,
				},
				/*  Added by shruti for validation  related changes START */
				strIdentityProofDocumentId : {
					required : true,
				} , 
				strIdentityProofDocumentValue : {
					required : true,
				}
			},
			/*  Added by shruti for validation  related changes END */
			messages : {
				strTitle : {
					required : "Please Select Title!"
				},
				strFirstName : {
					required : "Please Enter First Name!",
					minlength : "Minmum {0} characters required!",
					maxlength : "Maximum {0} characters allowed!",
					regex : "only Char allow !"
				},
				strMiddleName : {
					required : "Please Enter Middle Name!",
					regex : "only Char allow !"
				},
				strLastName : {
					required : "Please Enter Last Name!",
					minlength : "Minmum {0} characters required!",
					maxlength : "Maximum {0} characters allowed!",
					regex : "only Char allow !"
				},
				strPhoneCode : {
					required : "Please Select Country Code!",
				},
				strMobileNo : {
					required : "Please Enter Mobile No!",
					minlength : "Minmum {0} characters required!",
					maxlength : "Maximum {10} characters allowed!",
					regex : "only Numeric allow !"
				},
				strGender : {
					required : "Required !"
				},
				strDOB : {
					required : "Required !"
				},
				
				strAddress1 : {
					required : "Please Enter Address1!",
					minlength : "Minmum {0} characters required!",
					maxlength : "Maximum {0} characters allowed!",
					regex : "only Alphanumeric allow !"
				},
				strCountry : {
					required : "Required !"
				},
				strState : {
					required : "Required !"
				},
				strCity : {
					required : "Required !"
				},
				strAddressType : {
					required : "Required !"
				},
				strPinCode : {
					required : "Please Enter Pin Code!",
					minlength : "Minmum {0} characters required!",
					maxlength : "Maximum {0} characters allowed!",
					regex : "only Numeric allow !"
				},
				strEmailID : {
					required : "Please Enter Email ID!",
					email : "Invalid Email",
					minlength : "Minmum {0} characters allowed!",
					maxlength : "Maximum {0} characters allowed!"
				},
				strAddressProofDocumentId : {
					required : "Please Select Document Type!",
					minlength : "Minmum {0} characters allowed!",
					maxlength : "Maximum {0} characters allowed!"									   
				},
				strAddressProofDocumentValue : {
					required : "Please Enter Document Value!",
					minlength : "Minmum {0} characters allowed!",
					maxlength : "Maximum {0} characters allowed!"
				},
				/*  Added by shruti for validation  related changes START */
				strIdentityProofDocumentId : {
					required : "Please Select Document Type!",
					minlength : "Minmum {0} characters allowed!",
					maxlength : "Maximum {0} characters allowed!"									   
				},
				strIdentityProofDocumentValue : {
					required : "Please Enter Document Value!",
					minlength : "Minmum {0} characters allowed!",
					maxlength : "Maximum {0} characters allowed!"
				}
				/*  Added by shruti for validation  related changes END */
			},
			submitHandler : function(form)
			{
				form.submit();
			}
		});
	});	
</script>

<div class="my-3 my-md-5">
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<%-- <jsp:include page="accountCreationLeftMenu.jsp"></jsp:include> --%>
				<jsp:include page="customerAccountLeftMenu.jsp"></jsp:include>
			</div>
			<div class="col-lg-9">
				<form:form action="customerIdCreationConfig" method="post" commandName="customerIdCreationConfig" name="customerIdCreationConfig" id="customerIdCreationConfig" cssClass="card">
					<div class="card-header">
						<h3 class="card-title">Personal Information</h3>
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
							<div class="col-md-3 col-lg-3">
								<div class="form-group">
									<label class="form-label">Title<b>*</b></label>
									<form:select path="strTitle" id="strTitle"
										cssClass="form-control  custom-select">
										<form:option value="">Select</form:option>
										<form:option value="Mr">Mr.</form:option>
										<form:option value="Mrs">Mrs.</form:option>
									</form:select>
								</div>
							</div>
						</div> 
							
						<div class="row">
							<div class="col-md-4 col-lg-4">
								<div class="form-group">
									<label class="form-label">First Name<b>*</b></label>
									  <form:input path="strFirstName" id="strFirstName" cssClass="form-control" /> 
								</div>
							</div>

							<div class="col-md-4 col-lg-4">
								<div class="form-group">
									<label class="form-label">Middle Name</label>
									 <form:input path="strMiddleName" id="strMiddleName" cssClass="form-control" />
								</div>
							</div>

							<div class="col-md-4 col-lg-4">
								<div class="form-group">
									<label class="form-label">Last Name<b>*</b></label>
								 	<form:input path="strLastName" id="strLastName" cssClass="form-control" /> 
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-4 col-lg-4">
								<div class="form-group">
									<label class="form-label">Gender<b>*</b></label>
									 <form:select path="strGender" id="strGender" cssClass="form-control custom-select">
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
										<form:input path="strDOB" id="strDOB" cssClass="form-control" />
										<span class="input-group-addon input-group-append" id="basic-addon2"> 
										<span class="input-group-text">
											<i class="fe fe-calendar"></i>
										</span>
										</span>
									</div>
								</div>
							</div>
							
							<div class="col-md-4 col-lg-4">
								<div class="form-group">
									<label class="form-label">Email Id<b>*</b></label>
									 <form:input path="strEmailID" id="strEmailID" cssClass="form-control" /> 
								</div>
							</div>
							
							<div class="col-md-2 col-lg-2">
								<div class="form-group">
									<label class="form-label">Country Code<b>*</b></label>
									<form:select path="strPhoneCode" id="strPhoneCode" cssClass="form-control selectpicker">
										<form:option value="">Select</form:option>
										<c:forEach items="${phoneCodeList}" var="itr">
											<form:option value="${itr.strPhoneCode} - ${itr.strShortName}">${itr.strPhoneCode} - ${itr.strShortName}</form:option>
											<%-- <form:option value="${itr.country_code}">${itr.country_code}</form:option> --%>
										</c:forEach>
									</form:select>
								</div>
							</div>
							
							<div class="col-md-4 col-lg-4">
								<div class="form-group">
									<label class="form-label">Mobile Number<b>*</b></label>
									<form:input path="strMobileNo" id="strMobileNo"
										cssClass="form-control" />
								</div>
							</div>
							
							<div class="col-md-4 col-lg-4">
								<div class="form-group">
									<label class="form-label">Phone Number</label>
									<form:input path="strPhoneNo" id="strPhoneNo"
										cssClass="form-control" />
								</div>
							</div>
							
						</div>
					</div>
						
					<div class="card-header"> 
						<h3 class="card-title">Address Information</h3>
					</div>
					
					<div class="card-body">							
						<div class="row">							
							<div class="col-md-4 col-lg-4">
								<div class="form-group">
									<label class="form-label">Address Line 1<b>*</b></label>
									 <form:input path="strAddress1" id="strAddress1"
										cssClass="form-control" /> 
								</div>
							</div>

							<div class="col-md-4 col-lg-4">
								<div class="form-group">
									<label class="form-label">Address Line 2</label>
									 <form:input path="strAddress2" id="strAddress2"
										cssClass="form-control" /> 
								</div>
							</div>

							<div class="col-md-4 col-lg-4">
								<div class="form-group">
									<label class="form-label">Address Line 3</label>
									<form:input path="strAddress3" id="strAddress3"
										cssClass="form-control" /> 
								</div>
							</div>

						</div>
						
						<div class="row">
							<div class="col-md-3 col-lg-3">
								<div class="form-group">
									<label class="form-label">Country<b>*</b></label>
									 <form:select path="strCountry" id="strCountry"
										cssClass="form-control selectpicker">
										<form:option value="">Select</form:option>
										<c:forEach items="${countryList}" var="itr">
											<form:option value="${itr.strID}">${itr.strCountryName}</form:option>
										</c:forEach>
									</form:select> 
								</div>
							</div>
							<div class="col-md-3 col-lg-3">
								<div class="form-group">
									<label class="form-label">State<b>*</b></label>
									 <form:select path="strState" id="strState"
										cssClass="form-control selectpicker">
										<form:option value="">Select</form:option>
									</form:select> 
								</div>
							</div>
							<div class="col-md-3 col-lg-3">
								<div class="form-group">
									<label class="form-label">City<b>*</b></label>
									 <form:select path="strCity" id="strCity"
										cssClass="form-control selectpicker">
										<form:option value="">Select</form:option>
									</form:select> 
								</div>
							</div>
						 	<div class="col-md-3 col-lg-3">
								<div class="form-group">
									<label class="form-label">Postal Code<b>*</b></label>
									<form:input path="strPinCode" id="strPinCode"
										cssClass="form-control" />
								</div>
							</div> 
						</div>
						
						<!-- <div class="card-footer text-right">
							<div class=".d-flex1">
								<button type="submit" id="submitBtn"
									class="btn btn-primary ml-auto">Submit</button>
								<button type="reset" class="btn btn-primary ml-auto"
									id="resetBtn">Clear</button>
							</div>
						</div>	 -->									
				</div>
				<div class="card-header"> 
						<h3 class="card-title">Address Proof(POA)</h3>
				</div> 
				
				<div class="card-body">
					<div class="row">
						<div class="col-md-6 col-lg-6">
							<div class="form-group">
								<label class="form-label">Document Type<b>*</b></label>
								<form:select path="strAddressProofDocumentId" id="strAddressProofDocumentId"
									cssClass="form-control selectpicker">
									<form:option value="">-- Select Document Type --</form:option>
									 <c:forEach items="${POAList}" var="itr">
										<form:option value="${itr.strDocumentType}">${itr.strDocumentDescr}</form:option>
									</c:forEach>
								</form:select>
							</div>
						</div>
						<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Document Value<b>*</b></label>
									<form:input path="strAddressProofDocumentValue"
										id="strAddressProofDocumentValue" cssClass="form-control" />
									<!-- 	<span id="strAddressProofDocumentValerror" style="color: red"></span> -->
									<!-- <label id="strAddressProofDocumentValerror"
										style="color: red; display: none;">Invalid Adhar!</label> -->
								</div>
						</div>
					</div>	
				</div>
				
				<div class="card-header"> 
						<h3 class="card-title">Identification Proof(POI)</h3>
				</div> 
				<div class="card-body">
				 <div class="row">
						<div class="col-md-6 col-lg-6">
							<div class="form-group">
								<label class="form-label">Document Type<b>*</b></label>
								<form:select path="strIdentityProofDocumentId" id="strIdentityProofDocumentId"
									cssClass="form-control selectpicker">
									<form:option value="">-- Select Document Type--</form:option>
									<c:forEach items="${POIList}" var="itr">
										<form:option value="${itr.strDocumentType}">${itr.strDocumentDescr}</form:option>
									</c:forEach> 
								</form:select>
							</div>
						</div>
						<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Document Value<b>*</b></label>
									<form:input path="strIdentityProofDocumentValue"
										id="strIdentityProofDocumentValue" cssClass="form-control" />
										<!-- <span id="strIdentityProofDocumentValErr" style="color: red"></span> -->
										<!-- <label id="strIdentityProofDocumentValerror"
										style="color: red; display: none;">Invalid Number!</label> -->
								</div>
							</div>
						</div> 

						<!-- <div class="card-footer text-right"> -->
						<div class="text-right">
							<div class=".d-flex1">
								<button type="submit" id="submitBtn"
									class="btn btn-primary ml-auto">Submit</button>
								<button type="reset" class="btn btn-primary ml-auto"
									id="resetBtn">Clear</button>
							</div>
						</div>
				</div>
				
				<form:input type="hidden" path="strAction" id="strAction" cssClass="form-control" />
				<form:input type="hidden" path="strJulianYear" id="strJulianYear" cssClass="form-control" />
				<form:input type="hidden" path="strJulianDate" id="strJulianDate" cssClass="form-control" />
				<form:input type="hidden" path="strCustId" id="strCustId" cssClass="form-control" />
			
				<%-- <input type="hidden" value="${leftAccountMenuID}" id="leftAccountMenuID"> --%>
				<input type="hidden" value="${leftCustomerAccountMenuID}" id="leftCustomerAccountMenuID">
				
			</form:form>
		</div>
	</div>
</div>
</div>

