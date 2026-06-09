<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="my-3 my-md-5">
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<jsp:include page="CardDetailsLeftMenu.jsp"></jsp:include>
			</div>
			<div class="col-md-9">
				<form:form action="pinManagement" method="post"
					commandName="cardBean" name="cardBean" id="cardBean"
					cssClass="card">

					<div class="card-header">
						<h3 class="card-title">Plastic Management</h3>
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
									<label class="form-label">Card Number</label>
									<form:input path="strCardNumber" id="strCardNumber"
										cssClass="form-control" readonly="true" />
									<span class="error" id="strCardNumberError"><form:errors
											path="strCardNumber"></form:errors></span>
								</div>
							</div>

							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Service Code</label>
									<form:input path="strServiceCode" id="strServiceCode"
										cssClass="form-control" readonly="true" />
									<span class="error" id="strServiceCodeError"><form:errors
											path="strServiceCode"></form:errors></span>
								</div>
							</div>

							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Emboss Line 1</label>
									<form:input path="strEmbossLine1" id="strEmbossLine1"
										cssClass="form-control" readonly="true" />
									<span class="error" id="strEmbossLine1Error"><form:errors
											path="strEmbossLine1"></form:errors></span>
								</div>
							</div>

							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Emboss Line 2</label>
									<form:input path="strEmbossLine2" id="strEmbossLine2"
										cssClass="form-control" readonly="true" />
									<span class="error" id="strEmbossLine2Error"><form:errors
											path="strEmbossLine2"></form:errors></span>
								</div>
							</div>

							<div class="col-md-6 col-lg-4">
								<div class="form-group">
									<label class="form-label">First Name</label>
									<form:input path="strEncodeFirstName" id="strEncodeFirstName"
										cssClass="form-control" readonly="true" />
									<span class="error" id="strEncodeFirstNameError"><form:errors
											path="strEncodeFirstName"></form:errors></span>
								</div>
							</div>

							<div class="col-md-6 col-lg-4">
								<div class="form-group">
									<label class="form-label">Middle Name</label>
									<form:input path="strEncodeMiddleName" id="strEncodeMiddleName"
										cssClass="form-control" readonly="true" />
									<span class="error" id="strEncodeMiddleNameError"><form:errors
											path="strEncodeMiddleName"></form:errors></span>
								</div>
							</div>

							<div class="col-md-6 col-lg-4">
								<div class="form-group">
									<label class="form-label">Last Name</label>
									<form:input path="strEncodeLastName" id="strEncodeLastName"
										cssClass="form-control" readonly="true" />
									<span class="error" id="strEncodeLastNameError"><form:errors
											path="strEncodeLastName"></form:errors></span>
								</div>
							</div>

						</div>
					</div>
					<input type="hidden" value="${leftCardMenuID}" id="leftCardMenuID">
				</form:form>
			</div>
		</div>
	</div>
</div>