<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script
	src="${pageContext.request.contextPath}/resources/assets/js/limitManagement/limitManagement.js"></script>
<div class="my-3 my-md-5">
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<jsp:include page="CardDetailsLeftMenu.jsp"></jsp:include>
			</div>
			<div class="col-md-9">
				<form:form action="cardDetailUpdate" method="post"
					commandName="cardDetails" name="cardDetailsUpdate"
					id="cardDetailsUpdateForm" cssClass="card">

					<div class="card-header">
						<h3 class="card-title">Limit Management</h3>
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
									<label class="form-label">Card Number <b>*</b></label>
									<form:input path="strMaskCardNumber" id="strCardNumber"
										cssClass="form-control" readonly="true" />
									<span class="error" id="strCardNumberError"><form:errors
											path="strCardNumber"></form:errors></span>
								</div>
							</div>
							<form:hidden path="strCardNumber"/>
							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Online ATM Limit <b>*</b></label>
									<form:input path="strOnlineATMLimit" id="strOnlineATMLimit"
										cssClass="form-control" />
									<span class="error" id="strOnlineATMLimitError"><form:errors
											path="strOnlineATMLimit"></form:errors></span>
								</div>
							</div>

							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Online POS Limit <b>*</b></label>
									<form:input path="strOnlinePOSLimit" id="strOnlinePOSLimit"
										cssClass="form-control" />
									<span class="error" id="strOnlinePOSLimittError"><form:errors
											path="strOnlinePOSLimit"></form:errors></span>
								</div>
							</div>

							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Online ECOM Limit <b>*</b></label>
									<form:input path="strOnlineECOMLimit" id="strOnlineECOMLimit"
										cssClass="form-control" />
									<span class="error" id="strOnlineECOMLimitError"><form:errors
											path="strOnlineECOMLimit"></form:errors></span>
								</div>
							</div>

							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Offline Limit <b>*</b></label>
									<form:input path="strOfflineLimit" id="strOfflineLimit"
										cssClass="form-control" />
									<span class="error" id="strOfflineLimitError"><form:errors
											path="strOfflineLimit"></form:errors></span>
								</div>
							</div>

							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Monthly Limit <b>*</b></label>
									<form:input path="strMonthlyLimit" id="strMonthlyLimit"
										cssClass="form-control" />
									<span class="error" id="strMonthlyLimitError"><form:errors
											path="strMonthlyLimit"></form:errors></span>
								</div>
							</div>

							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Weekly Limit <b>*</b></label>
									<form:input path="strWeeklyLimit" id="strWeeklyLimit"
										cssClass="form-control" />
									<span class="error" id="strWeeklyLimitError"><form:errors
											path="strWeeklyLimit"></form:errors></span>
								</div>
							</div>

							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Daily Limit <b>*</b></label>
									<form:input path="strDailyLimit" id="strDailyLimit"
										cssClass="form-control" />
									<span class="error" id="strDailyLimitError"><form:errors
											path="strDailyLimit"></form:errors></span>
								</div>
							</div>

							<form:hidden path="strCardSeqNumber" />
							<input type="hidden" value="${leftCardMenuID}" id="leftCardMenuID">

						</div>

					</div>
					<div class="card-footer text-right">
						<div class="btn-list text-right">
							<button type="submit" id="submitBtn" name="strRequestBtn"
								value="1" class="btn btn-primary">Submit</button>
							<button type="submit" class="btn btn-secondary"
								name="strRequestBtn" value="2" id="resetBtn">Clear</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>