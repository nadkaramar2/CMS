<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	$(document).ready(function() {
		
		if($('#addFlag').val() != "Y") {
			$("#addressType").attr("readonly", true);	
			$('#addressType option:not(:selected)').attr('disabled', true);	
		}
		
		
		$('#strCountryCode').change(function() {
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
					
					$.each(data, function(key, value) {  
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
	    
	    
	    $('#strState').change(function() {
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
	var value = $("#addressDetailsUpdateForm").validate({
		rules: {
			strAddress1: {
	                required: true,
	                regex: /^[a-zA-Z0-9\s,-]*$/,
	                minlength: 2,
	                maxlength: 50
	        },
	        strAddress2: {
                required: true,
                regex: /^[a-zA-Z0-9\s,-]*$/,
                minlength: 2,
                maxlength: 50
        	},
        	strAddressType: {
	            required: true
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
	        strPinCode: {
                required: true,
                regex: /^[0-9]+$/,
                minlength: 6,
                maxlength: 6         
            }
	        
		},
		messages: {
			strAddress1: {
	                required: "Please Enter Address1!",
	                minlength: "Minmum {0} characters required!",
	                maxlength: "Maximum {0} characters allowed!",
	                regex: "only alphanumeric allow !"
	        },
	        strAddress2: {
                required: "Please Enter Address2!",
                minlength: "Minmum {0} characters required!",
                maxlength: "Maximum {0} characters allowed!",
                regex: "only alphanumeric allow !"
        	},
        	strAddressType: {
	            required: "Required"
	        },
	        strCountryCode: {
	            required: "Required"
	        },
	        strState: {
	            required: "Required"
	        },
	        strCity: {
	            required: "Required"
	        },
	        strPinCode: {
                required: "Please Enter Pincode!",
                minlength: "Minmum {0} characters required!",
                maxlength: "Maximum {0} characters allowed!",
                regex: "only numeric allow !"
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
			<div class="col-12">
				<form:form action="addressDetailsUpdate" method="post"
					commandName="addressDetailsBean" name="addressDetailsUpdateForm"
					id="addressDetailsUpdateForm" cssClass="card">

					<div class="card-header">
						<h3 class="card-title">
							<i class="fe fe-user-plus"></i> &nbsp;&nbsp;Address Update
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
									<label class="form-label">Address 1 <b>*</b></label>
									<form:input path="strAddress1" id="strAddress1"
										cssClass="form-control" />
									<span class="error" id="strAddress1Error"><form:errors
											path="strAddress1"></form:errors></span>
								</div>
							</div>

							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Address 2 <b>*</b></label>
									<form:input path="strAddress2" id="strAddress2"
										cssClass="form-control" />
									<span class="error" id="strAddress2Error"><form:errors
											path="strAddress2"></form:errors></span>
								</div>
							</div>

							<%-- <div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Address 3 <b>*</b></label>
									<form:input path="strAddress3" id="strAddress3"
										cssClass="form-control" />
									<span class="error" id="strAddress3Error"><form:errors
											path="strAddress3"></form:errors></span>
								</div>
							</div> --%>

							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Address Type <b>*</b></label>
									<form:select path="strAddressType" id="addressType"
										cssClass="form-control custom-select">
										<c:forEach items="${addressList}" var="itr">
											<form:option value="${itr.strID}">${itr.strAddressDesc}</form:option>
										</c:forEach>
									</form:select>
									<span class="error" id="strAddressTypeError"><form:errors
											path="strAddressType"></form:errors></span>
								</div>
							</div>


							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Country Code <b>*</b></label>
									<form:select path="strCountryCode" id="strCountryCode"
										cssClass="form-control selectpicker">
										<c:forEach items="${countryList}" var="itr">
											<form:option value="${itr.strID}">${itr.strCountryName}</form:option>
										</c:forEach>
									</form:select>
									<span class="error" id="strCountryCodeError"><form:errors
											path="strCountryCode"></form:errors></span>
								</div>
							</div>

							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">State <b>*</b></label>
									<form:select path="strState" id="strState"
										cssClass="form-control selectpicker">
										<c:forEach items="${stateList}" var="itr">
											<form:option value="${itr.strID}">${itr.strStateName}</form:option>
										</c:forEach>
									</form:select>
									<span class="error" id="strStateError"><form:errors
											path="strState"></form:errors></span>
								</div>
							</div>

							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">City <b>*</b></label>
									<form:select path="strCity" id="strCity"
												cssClass="form-control selectpicker">
												<c:forEach items="${cityList}" var="itr">
											<form:option value="${itr.strID}">${itr.strCityName}</form:option>
										</c:forEach>
											</form:select>
									<span class="error" id="strCityError"><form:errors
											path="strCity"></form:errors></span>
								</div>
							</div>

							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Pin Code <b>*</b></label>
									<form:input path="strPinCode" id="strPinCode"
										cssClass="form-control" />
									<span class="error" id="strPinCodeError"><form:errors
											path="strPinCode"></form:errors></span>
								</div>
							</div>


						</div>
					</div>
					<form:hidden path="strCIFKey" />
					<form:hidden path="strCitizenID" />
					<form:hidden path="strAddressPrimaryFlag" />
					<form:hidden path="strCustomerID" />
					<form:hidden path="strParticipantID" />
					<form:hidden path="strAddFlag" />
					<input type="hidden" value="${addFlag}" id="addFlag">
					<div class="card-footer text-right">
						<div class=".d-flex1">
							<a href="addressDetailsForm" class="btn btn-secondary"><i
								class="fa fa-arrow-circle-left"></i> Address Details</a>
							<button type="submit" id="submitBtn"
								class="btn btn-primary ml-auto">Submit</button>
							<button type="reset" class="btn btn-secondary ml-auto"
								id="resetBtn">Clear</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>
