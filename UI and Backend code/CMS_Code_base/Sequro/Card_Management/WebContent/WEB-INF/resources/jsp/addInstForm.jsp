<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="${pageContext.request.contextPath}/resources/assets/js/institution/addInstitution.js"></script>
	
<div class="my-3 my-md-5">
	<div class="container">
		<div class="row">
			<div class="col-12">
				<form:form action="addInst" method="post"
					commandName="institutionBean" name="addInst" id="addInstForm"
					cssClass="card">
					
					<div class="card-header">
						<h3 class="card-title">Add Institution</h3>
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
					<div class="card-body">
						<div class="row">
							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Institution Code</label>
									<form:input path="institutionCode" id="institutionCode"
										cssClass="form-control" />
										<span class="error" id="institutionCodeError"><form:errors
											path="institutionCode"></form:errors></span>
								</div>
							</div>
							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Institution Description</label>
									<form:input path="institutionDesc" id="institutionDesc"
										cssClass="form-control" />
										<span class="error" id="institutionDescError"><form:errors
											path="institutionDesc"></form:errors></span>
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
