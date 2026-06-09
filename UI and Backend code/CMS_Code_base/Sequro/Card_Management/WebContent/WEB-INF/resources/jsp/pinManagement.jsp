<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script
	src="${pageContext.request.contextPath}/resources/assets/js/pinManagement/pinManagementForm.js"></script>
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
						<h3 class="card-title">Pin Management</h3>
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
									<label class="form-label">Pin Mailer IssueDate</label>
									<form:input path="strCardIssueDate" id="strCardIssueDate"
										cssClass="form-control" readonly="true" />
									<span class="error" id="strCardIssueDateError"><form:errors
											path="strCardIssueDate"></form:errors></span>
								</div>
							</div>

							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Daily Pin Count</label>
									<form:input path="strDailyPinRetryCount"
										id="strDailyPinRetryCount" cssClass="form-control"
										readonly="true" />
									<span class="error" id="strDailyPinRetryCountError"><form:errors
											path="strDailyPinRetryCount"></form:errors></span>
								</div>
							</div>
							
							<form:hidden path="strTokenCard"/>

							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Consecutive Pin Retry Count</label>
									<form:input path="strConsecutivePinRetryCount"
										id="strConsecutivePinRetryCount" cssClass="form-control"
										readonly="true" />
									<span class="error" id="strConsecutivePinRetryCountError"><form:errors
											path="strConsecutivePinRetryCount"></form:errors></span>
								</div>
							</div>

							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Daily Pin Limit</label>
									<form:input path="strDailyPinRetryLimit"
										id="strDailyPinRetryLimit" cssClass="form-control" />
									<span class="error" id="strDailyPinRetryLimitError"><form:errors
											path="strDailyPinRetryLimit"></form:errors></span>
								</div>
							</div>

							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Consecutive Pin Retry Limit</label>
									<form:input path="strConsecutivePinRetryLimit"
										id="strConsecutivePinRetryLimit" cssClass="form-control" />
									<span class="error" id="strConsecutivePinRetryLimitError"><form:errors
											path="strConsecutivePinRetryLimit"></form:errors></span>
								</div>
							</div>

							<div class="col-md-6 col-lg-6">
								<label class="custom-switch"> <span
									class="custom-switch-description">Pin Mailer Flag</span>
									&nbsp;&nbsp; <input type="checkbox"
									name="custom-switch-checkbox" id="pinFlag"
									class="custom-switch-input"> <span
									class="custom-switch-indicator"></span>

								</label>
							</div>
						</div>

						<form:hidden id="pinRetryFlag" path="strPinRetryFlag" />
						<form:hidden id="pinMailerUpdateFlag"
							path="strPinMailerUpdateFlag" />
						<form:hidden id="pinFlagTemp" path="strPinMailerIssueFlag" />
						<form:hidden id="strCardSeqNumber" path="strCardSeqNumber" />



					</div>
					<div class="card-footer text-right">
						<div class="btn-list text-right">
							<button type="submit" id="submitBtn" name="strRequestBtn"
								value="1" class="btn btn-primary">Submit</button>
							<button type="submit" class="btn btn-secondary"
								name="strRequestBtn" value="2" id="resetBtn">Clear</button>
						</div>
					</div>

					<input type="hidden" value="${leftCardMenuID}" id="leftCardMenuID">
				</form:form>
			</div>
		</div>
	</div>
</div>