<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	$(document).ready(function() {
		
		$.validator.addMethod(
				"regex",
				function(value, element, regexp) {
	    		var check = false;
	    		return this.optional(element) || regexp.test(value);
			},
			"ss"
		);
	var value = $("#phoneBean").validate({
		rules: {
			strPhoneNo: {
	                required: true,
	                regex: /^[0-9]+$/,
	                minlength: 10,
	                maxlength: 10
	        }
		},
		messages: {
			strPhoneNo: {
	                required: "Please Enter Phone No!",
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
				<form:form action="phoneUpdate" method="post" commandName="phoneBean"
					name="phoneBean" id="phoneBean" cssClass="card">

					<div class="card-header">
						<h3 class="card-title"><i class="fe fe-user-plus"></i> &nbsp;&nbsp;Phone Update </h3>
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
									<label class="form-label">Phone Number <b>*</b></label>
									<form:input path="strPhoneNo" id="strPhoneNo"
										cssClass="form-control" />
									<span class="error" id="strPhoneNoError"><form:errors
											path="strPhoneNo"></form:errors></span>
								</div>
							</div>
							
							<%-- <div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Type</label>
									<form:select path="strPhoneType" id="strPhoneType" cssClass="form-control custom-select">
										<form:option value="1">Home</form:option>
										<form:option value="2">Office</form:option>
									</form:select>
									<span class="error" id="strPhoneTypeError"><form:errors
											path="strPhoneType"></form:errors></span>
								</div>
							</div> --%>
						</div>
					</div>
					<form:hidden path="strCIFKey" />
					<form:hidden path="strCitizenID" />
					<form:hidden path="strPhonePrimaryFlag" />
					<form:hidden path="strCustomerID" />
					<form:hidden path="strParticipantID" />
					<form:hidden path="strPhnFlag" />
					<div class="card-footer text-right">
						<div class=".d-flex1">
							<a href="contactDetailsForm" class="btn btn-secondary"><i
									class="fa fa-arrow-circle-left"></i> Contact Details</a>
							<button type="submit" id="submitBtn" class="btn btn-primary ml-auto">Submit</button>
							<button type="reset" class="btn btn-secondary ml-auto" id="resetBtn">Clear</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>
