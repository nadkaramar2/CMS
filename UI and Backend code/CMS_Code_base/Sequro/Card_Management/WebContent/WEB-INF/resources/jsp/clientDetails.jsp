<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	$(document).ready(function() {
		
		$('#strDOB').datepicker({
	    	format: "yyyy/mm/dd"
	    });
		
		
		if($('#strCitizenID').val() == "") {
			$('#addCardBtn').hide();
		}
		
		$('#submitBtn').hide();
		
		$('#editBtn').click(function () {
			$("#strCitizenID").attr("readonly", false);	
			$("#strCIFKey").attr("readonly", false);	
			$("#strFirstName").attr("readonly", false);	
			$("#strMiddleName").attr("readonly", false);	
			$("#strLastName").attr("readonly", false);	
			$("#strGender").attr("readonly", false);
			$("#strMotherMaidenName").attr("readonly", false);
			$('#submitBtn').show();
			return false;
		});
		
		$('#addCardBtn').click(function() {
			$('#customerDetailsForm').attr('action', 'addServiceForm');
		})
		
	});
</script>

<form:form action="clientDetails" method="post"
	commandName="customerDetails" name="customerDetailsForm"
	id="customerDetailsForm">
	<div class="my-3 my-md-5">
		<div class="container">
			<div class="row">
				<div class="col-md-3">
					<jsp:include page="customerServiceLeftMenu.jsp"></jsp:include>
				</div>
				<div class="col-md-9">
					<div class="card">
						<div class="card-header">
							<h3 class="card-title">Customer Details</h3>
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
								<div class="col-md-3 col-lg-4">
									<div class="form-group">
										<label class="form-label">Customer ID</label>
										<form:input path="strCustomerID" id="strCustomerID"
											cssClass="form-control"
											value="${clientDetails.strCustomerID}" readonly="true" />
									</div>
								</div>

								<div class="col-md-3 col-lg-4">
									<div class="form-group">
										<label class="form-label">Citizen ID</label>
										<form:input path="strCitizenID" id="strCitizenID"
											cssClass="form-control" value="${clientDetails.strCitizenID}"
											readonly="true" />
									</div>
								</div>

								<div class="col-md-3 col-lg-4">
									<div class="form-group">
										<label class="form-label">CIF Key</label>
										<form:input path="strCIFKey" id="strCIFKey"
											cssClass="form-control" value="${clientDetails.strCIFKey}"
											readonly="true" />
									</div>
								</div>

								<div class="col-md-6 col-lg-4">
									<div class="form-group">
										<label class="form-label">First Name</label>
										<form:input path="strFirstName" id="strFirstName"
											cssClass="form-control" readonly="true"
											value="${clientDetails.strFirstName}" />
									</div>
								</div>

								<div class="col-md-6 col-lg-4">
									<div class="form-group">
										<label class="form-label">Middle Name</label>
										<form:input path="strMiddleName" id="strMiddleName"
											cssClass="form-control"
											value="${clientDetails.strMiddleName}" readonly="true" />
									</div>
								</div>

								<div class="col-md-6 col-lg-4">
									<div class="form-group">
										<label class="form-label">Last Name</label>
										<form:input path="strLastName" id="strLastName"
											cssClass="form-control" value="${clientDetails.strLastName}"
											readonly="true" />
									</div>
								</div>

								<div class="col-md-6 col-lg-4">
									<div class="form-group">
										<label class="form-label">Gender</label>
										<form:select path="strGender" id="strGender"
										cssClass="form-control selectpicker" readonly="true">
										<form:option value="M">Male</form:option>
										<form:option value="F">Female</form:option>
									</form:select>
									</div>
								</div>

								<div class="col-md-6 col-lg-4">
									<div class="form-group">
										<label class="form-label">Date Of Birth</label>
										<div class='input-group date' id='datetimepicker1'>
										<form:input path="strDOB" id="strDOB" cssClass="form-control"
											value="${clientDetails.strDOB}" readonly="true" />
										<span
											class="input-group-addon input-group-append" id="basic-addon2"> <span
											class="input-group-text"><i class="fe fe-calendar"></i></span>
										</span>
									</div>
										
									</div>
								</div>

								<div class="col-md-6 col-lg-4">
									<div class="form-group">
										<label class="form-label">Mother Maiden Name</label>
										<form:input path="strMotherMaidenName"
											id="strMotherMaidenName" cssClass="form-control"
											value="${clientDetails.strMotherMaidenName}" readonly="true" />
									</div>
								</div>

							</div>
							
							<div class="card-footer text-right">
									<div class=".d-flex1">
										<button type="submit" id="addCardBtn" name="strRequestBtn"
											value="1" class="btn btn-primary ml-auto">Manage Personalized Cards</button>
										<button type="submit" id="editBtn" name="strEditBtn"
											value="0" class="btn btn-secondary ml-auto">Edit</button>
										<button type="submit" id="submitBtn" name="strRequestBtn"
											value="1" class="btn btn-primary ml-auto">Submit</button>
									</div>
								</div>
								
							<input type="hidden" value="${leftCustomerMenuID}"
								id="leftCustomerMenuID">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>