<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(document).ready(function() {
		$.validator.addMethod("regex", function(value, element, regexp) {
			var check = false;
			return this.optional(element) || regexp.test(value);
		}, "ss");
		var value = $("#accountForm").validate({
			rules : {
				strAccountNumber : {
					required : true,
					regex : /^[0-9]+$/,
					maxlength : 20
				},
				strAccountType : {
					required : true
				},
				strCurrencyCode : {
					required : true
				},
				strAccountBranch : {
					required : true
				}
			},
			messages : {
				strAccountNumber : {
					required : "Please Enter Account Number!",
					maxlength : "Maximum {0} characters allowed!",
					regex : "only numeric allow !"
				},
				strAccountType : {
					required : "Required"
				},
				strCurrencyCode : {
					required : "Required"
				},
				strAccountBranch : {
					required : "Required"
				}
			},
			submitHandler : function(form) {
				form.submit();
			}
		});
	});
</script>
<div class="my-3 my-md-5">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<form:form action="addAccount" method="post"
					commandName="accountBean" name="accountForm" id="accountForm"
					cssClass="card">

					<div class="card-header">
						<h3 class="card-title">Add Account</h3>
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
					<c:choose>
						<c:when test="${empty serviceBean.inCustomerID}">
							<div class="col-md-6">
								<div class="card-body">No Service available. Please Search
									Client</div>
							</div>
						</c:when>
						<c:otherwise>
							<div class="card-body">
								<div class="row">
								
								<div class="col-md-3">
										<div class="form-group">
											<label class="form-label">Card Number</label>
											<form:input path="strCardMask" id="strCardMask"
												cssClass="form-control" readonly="true" />
										</div>
									</div>
									
									<div class="col-md-3">
										<div class="form-group">
											<label class="form-label">Citizen ID</label>
											<form:input path="citizenID" id="citizenID"
												cssClass="form-control" readonly="true" />
										</div>
									</div>
									
									<div class="col-md-3">
										<div class="form-group">
											<label class="form-label">CIF Key</label>
											<form:input path="cifKey" id="cifKey"
												cssClass="form-control" readonly="true"/>
										</div>
									</div>
									
								</div>
							</div>

							<form:hidden path="strCardNumber" />
							<div class="card-body">
								<h3 class="card-title">Account Details</h3>
								<div class="row">

									<div class="col-md-3">
										<div class="form-group">
											<label class="form-label">Account Number</label>
											<form:input path="strAccountNumber" id="accountNo"
												cssClass="form-control" />
										</div>
									</div>

									<div class="col-md-3">
										<div class="form-group">
											<label class="form-label">Type</label>
											<form:select path="strAccountType" id="accountType"
												cssClass="form-control selectpicker">
												<c:forEach items="${accountList}" var="itr">
													<form:option value="${itr.strID}">${itr.strAccountDesc}</form:option>
												</c:forEach>
											</form:select>
										</div>
									</div>

									<div class="col-md-3">
										<div class="form-group">
											<label class="form-label">Currency Code</label>
											<form:select path="strCurrencyCode" id="currencyCode"
												cssClass="form-control selectpicker">
												<c:forEach items="${currencyList}" var="itr">
													<c:choose>
														<c:when test="${itr.lkpvalue eq 'INR'}">
															<form:option value="${itr.lkpkey}" selected="selected">${itr.lkpvalue}</form:option>
														</c:when>
														<c:otherwise>
															<form:option value="${itr.lkpkey}">${itr.lkpvalue}</form:option>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</form:select>
										</div>
									</div>

									<div class="col-md-3">
										<div class="form-group">
											<label class="form-label">Branch</label>
											<form:select path="strAccountBranch" id="branch"
												cssClass="form-control selectpicker">
												<c:forEach items="${branchCodeList}" var="itr">
													<form:option value="${itr.strBranchCode}">${itr.strBranchAddress}</form:option>
												</c:forEach>
											</form:select>
										</div>
									</div>

								</div>
							</div>
							<form:hidden path="citizenID" />
							<form:hidden path="cifKey" />
							<input type="hidden" name="strPrimaryFlag"
								value="${accountBean.strPrimaryFlag}">
							<input type="hidden" name="strCardSeqNumber"
								value="${accountBean.strCardSeqNumber}">
							<div class="card-footer text-right">
								<div class="d-flex">
									<button type="submit" class="btn btn-primary ml-auto">Submit</button>
								</div>
							</div>
						</c:otherwise>
					</c:choose>
				</form:form>
			</div>
		</div>
	</div>
</div>
