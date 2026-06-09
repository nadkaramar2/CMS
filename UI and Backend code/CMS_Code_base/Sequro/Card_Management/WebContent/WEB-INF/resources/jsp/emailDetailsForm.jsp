<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">

	$(document).ready(function() {
		
		if($('#strEmailFlag').val() != "Y") {
			$("#strEmailType").attr("readonly", true);	
			$('#strEmailType option:not(:selected)').attr('disabled', true);
		}
		
		$.validator.addMethod(
				"regex",
				function(value, element, regexp) {
	    		var check = false;
	    		return this.optional(element) || regexp.test(value);
			},
			"ss"
		);
	var value = $("#emailBeanForm").validate({
		rules: {
			strEmailID: {
	                required: true,
	                email: true,
	                minlength: 5,
	                maxlength: 100
	        },
	        strEmailType: {
	            required: true
	        }
		},
		messages: {
			strEmailID: {
	                required: "Please Enter Email ID!",
	                minlength: "Minmum {0} characters required!",
	                maxlength: "Maximum {0} characters allowed!",
	                regex: "only alphanumeric allow !"
	        },
	        strEmailType: {
	            required: "Required"
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
				<form:form action="emailUpdate" method="post"
					commandName="emailBean" name="emailBeanForm" id="emailBeanForm"
					cssClass="card">

					<div class="card-header">
						<h3 class="card-title">
							<i class="fe fe-user-plus"></i> &nbsp;&nbsp;Email Update
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
									<label class="form-label">Email ID <b>*</b></label>
									<form:input path="strEmailID" id="strEmailID"
										cssClass="form-control" />
									<span class="error" id="strEmailIDError"><form:errors
											path="strEmailID"></form:errors></span>
								</div>
							</div>

							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Type</label>
									<form:select path="strEmailType" id="strEmailType"
										cssClass="form-control custom-select" >
										<c:forEach items="${emailList}" var="itr">
											<form:option value="${itr.strID}">${itr.strEmail}</form:option>
										</c:forEach>
									</form:select>
									<span class="error" id="strEmailTypeError"><form:errors
											path="strEmailType"></form:errors></span>
								</div>
							</div>
						</div>
					</div>
					<form:hidden path="strCIFKey" />
					<form:hidden path="strCitizenID" />
					<form:hidden path="strEmailPrimaryFlag" />
					<form:hidden path="strCustomerID" />
					<form:hidden path="strParticipantID" />
					<form:hidden path="strEmailFlag" id="strEmailFlag" />
					<div class="card-footer text-right">
						<div class=".d-flex1">
							<a href="contactDetailsForm" class="btn btn-secondary"><i
								class="fa fa-arrow-circle-left"></i> Contact Details</a>
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
