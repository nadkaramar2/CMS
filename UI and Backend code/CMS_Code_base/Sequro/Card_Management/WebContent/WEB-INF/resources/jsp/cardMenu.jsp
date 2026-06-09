<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="my-3 my-md-5">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<form:form action="addClient" method="post" commandName="client"
					name="client" id="addInstForm" cssClass="card">
					
					<div class="card-header">
						<h3 class="card-title">Add Client</h3>
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
						<h3 class="card-title">Client Information</h3>
						<div class="row">
							<div class="col-md-6 col-lg-12">
								<div class="form-group">
									<label class="form-label">Participant </label>
									<input type="text" name="strPartID" value="${user.participantDesc}" class="form-control" id="institutionCode" disabled="disabled">
									<%-- <form:input path="strPartID" id="institutionCode" 
										cssClass="form-control" /> --%>
								</div>
							</div>
							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Citizen ID</label>
									<form:input path="strCitizenID" id="citizenID"
										cssClass="form-control" />
								</div>
							</div>
							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">CIF Key</label>
									<form:input path="strCIFKey" id="cifKey"
										cssClass="form-control" />
								</div>
							</div>
							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">First Name</label>
									<form:input path="strFirstName" id="firstName"
										cssClass="form-control" />
								</div>
							</div>
							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Middle Name</label>
									<form:input path="strMiddleName" id="middleName"
										cssClass="form-control" />
								</div>
							</div>
							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Last Name</label>
									<form:input path="strLastName" id="lastName"
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
									<form:input path="strAddress1" id="address1"
										cssClass="form-control" />
								</div>
							</div>
							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Address Line 2</label>
									<form:input path="strAddress2" id="address2"
										cssClass="form-control" />
								</div>
							</div>
							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">City</label>
									<form:input path="strCity" id="city"
										cssClass="form-control" />
								</div>
							</div>
							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">State</label>
									<form:input path="strState" id="state"
										cssClass="form-control" />
								</div>
							</div>
							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Country</label>
									<form:input path="strCountryCode" id="countryCode"
										cssClass="form-control" />
								</div>
							</div>
							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Postal Code</label>
									<form:input path="strPinCode" id="strPinCode"
										cssClass="form-control" />
								</div>
							</div>
							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Type</label>
									<form:select path="strAddressType" id="addressType" cssClass="form-control custom-select">
										<form:option value="1">Home</form:option>
										<form:option value="2">Office</form:option>
									</form:select>
								</div>
							</div>
						</div>
					</div>
					</div>
					</div>
					
					<div class="row">
					<div class="col-md-6">
					<div class="card-body">
						<h3 class="card-title">Phone</h3>
						<div class="row">
							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Phone Number</label>
									<form:input path="strPhoneNo" id="phoneNo" 
										cssClass="form-control" />
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label class="form-label">Type</label>
									<form:select path="strPhoneType" id="phoneType" cssClass="form-control custom-select">
										<form:option value="1">Home</form:option>
										<form:option value="2">Office</form:option>
									</form:select>
								</div>
							</div>
						</div>
					</div>
					</div>
					
					<div class="col-md-6">
					<div class="card-body">
						<h3 class="card-title">Email</h3>
						<div class="row">
							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Email ID</label>
									<form:input path="strEmailID" id="emailID" 
										cssClass="form-control" />
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label class="form-label">Type</label>
									<form:select path="strEmailType" id="emailType" cssClass="form-control custom-select">
										<form:option value="1">Home</form:option>
										<form:option value="2">Office</form:option>
									</form:select>
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
									<label class="form-label">Gender</label>
									<form:select path="strGender" id="gender" cssClass="form-control custom-select">
										<form:option value="">Male</form:option>
										<form:option value="">Female</form:option>
									</form:select>
								</div>
							</div>
							<div class="col-md-6 col-lg-3">
								<div class="form-group">
									<label class="form-label">Date Of Birth</label>
									<form:input path="strDOB" id="dob"
										cssClass="form-control" />
								</div>
							</div>
							<div class="col-md-6 col-lg-3">
								<div class="form-group">
									<label class="form-label">Mother Maiden Name</label>
									<form:input path="strMotherMaidenName" id="motherMaidenName"
										cssClass="form-control" />
								</div>
							</div>
							<div class="col-md-6 col-lg-3">
								<div class="form-group">
									<label class="form-label">Document Type</label>
									<form:select path="strDocumentType" id="documentType" cssClass="form-control custom-select">
										<form:option value="1">Aadhar card</form:option>
										<form:option value="2">PAN</form:option>
										<form:option value="3">Driving License</form:option>
									</form:select>
								</div>
							</div>
							<div class="col-md-6 col-lg-3">
								<div class="form-group">
									<label class="form-label">Document Value</label>
									<form:input path="strDocumentNumber" id="documentValue"
										cssClass="form-control" />
								</div>
							</div>
						</div>
					</div>
					
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
