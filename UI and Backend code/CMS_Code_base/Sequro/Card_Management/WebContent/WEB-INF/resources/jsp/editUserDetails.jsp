<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script
	src="${pageContext.request.contextPath}/resources/assets/js/user/addUser.js"></script>

<div class="my-3 my-md-5">
	<div class="container">
		<div class="row">
			<div class="col-12">
				<form:form action="editUser" method="post" commandName="userBean"
					name="editUser" id="addUserForm" cssClass="card">

					<div class="card-header">
						<h3 class="card-title"><i class="fe fe-user-plus"></i> &nbsp;&nbsp;Edit User </h3>
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
									<label class="form-label">User Name <b>*</b></label>
									<form:input path="strUserName" id="strUserName"
										cssClass="form-control" />
									<span class="error" id="strUserNameError"><form:errors
											path="strUserName"></form:errors></span>
								</div>
							</div>
							
							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Password <b>*</b></label>
									<form:password path="strPassword" id="strPassword"
										cssClass="form-control" />
									<span class="error" id="strPasswordError"><form:errors
											path="strPassword"></form:errors></span>
								</div>
							</div>
							
							<%-- <div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Role <b>*</b></label>
									<form:select path="strRoleID" id="strRoleID"
										cssClass="form-control custom-select">
										<c:forEach items="${roleList}" var="itr">
											<form:option value="${itr.lkpkey}">${itr.lkpvalue}</form:option>
										</c:forEach>
									</form:select>
									<span class="error" id="strRoleIDError"><form:errors
											path="strRoleID"></form:errors></span>
								</div>
							</div> --%>
							
							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Group <b>*</b></label>
									<form:select path="strGroupID" id="strGroupID"
										cssClass="form-control custom-select">
										<c:forEach items="${groupList}" var="itr">
											<form:option value="${itr.lkpkey}">${itr.lkpvalue}</form:option>
										</c:forEach>
									</form:select>
									<span class="error" id="strGroupIDError"><form:errors
											path="strGroupID"></form:errors></span>
								</div>
							</div>
							
							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">First Name <b>*</b></label>
									<form:input path="strFirstName" id="strFirstName"
										cssClass="form-control" />
									<span class="error" id="strFirstNameError"><form:errors
											path="strFirstName"></form:errors></span>
								</div>
							</div>
							
							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Last Name <b>*</b></label>
									<form:input path="strLastName" id="strLastName"
										cssClass="form-control" />
									<span class="error" id="strLastNameError"><form:errors
											path="strLastName"></form:errors></span>
								</div>
							</div>
							
							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Mobile Number <b>*</b></label>
									<form:input path="strMobileNo" id="strMobileNo"
										cssClass="form-control" />
									<span class="error" id="strMobileNoError"><form:errors
											path="strMobileNo"></form:errors></span>
								</div>
							</div>
							
							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Email <b>*</b></label>
									<form:input path="strEmailID" id="strEmailID"
										cssClass="form-control" />
									<span class="error" id="strEmailIDError"><form:errors
											path="strEmailID"></form:errors></span>
								</div>
							</div>
							
							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Access Type <b>*</b></label>
									<form:select path="strUserAccessType" id="strUserAccessType"
										cssClass="form-control custom-select">
											<form:option value="DB">DB User</form:option>
											<form:option value="Ldap">Ldap User</form:option>
									</form:select>
									<span class="error" id="strUserAccessTypeError"><form:errors
											path="strUserAccessType"></form:errors></span>
								</div>
							</div>
							
							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Sensitive Data <b>*</b></label>
									<form:select path="strSensitiveFlag" id="strSensitiveFlag"
										cssClass="form-control custom-select">
											<form:option value="Y">Yes</form:option>
											<form:option value="N">No</form:option>
									</form:select>
									<span class="error" id="strSensitiveFlagError"><form:errors
											path="strSensitiveFlag"></form:errors></span>
								</div>
							</div>
							
							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">User Status <b>*</b></label>
									<form:select path="strUserStatusID" id="strUserStatusID"
										cssClass="form-control custom-select">
											<form:option value="1">Active</form:option>
											<form:option value="2">Inactive</form:option>
									</form:select>
									<span class="error" id="strUserStatusIDError"><form:errors
											path="strUserStatusID"></form:errors></span>
								</div>
							</div>
							
							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Secret Question <b>*</b></label>
									<form:select path="strSecQueID" id="strSecQueID"
										cssClass="form-control custom-select">
										<c:forEach items="${secQuestList}" var="itr">
											<form:option value="${itr.lkpkey}">${itr.lkpvalue}</form:option>
										</c:forEach>
									</form:select>
									<span class="error" id="strSecQueIDError"><form:errors
											path="strSecQueID"></form:errors></span>
								</div>
							</div>
							
							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Secret Question Answer <b>*</b></label>
									<form:input path="strSecQueAns" id="strSecQueAns"
										cssClass="form-control" />
									<span class="error" id="strSecQueAnsError"><form:errors
											path="strSecQueAns"></form:errors></span>
								</div>
							</div>
							
						</div>
					</div>
					<form:hidden path="strUserID" />
					<c:if test="${flag eq 'true'}">
					<div class="card-footer text-right">
						<div class=".d-flex1">
							<button type="submit" id="submitBtn" class="btn btn-primary ml-auto">Submit</button>
							<button type="reset" class="btn btn-primary ml-auto" id="resetBtn">Clear</button>
						</div>
					</div>
					</c:if>
				</form:form>
			</div>
		</div>
	</div>
</div>
