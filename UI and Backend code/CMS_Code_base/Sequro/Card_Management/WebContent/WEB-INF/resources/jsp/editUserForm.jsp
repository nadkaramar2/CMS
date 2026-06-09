<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
$(document).ready(function() {
    $('#editUserTable').DataTable({
		 scrollX: 100,
		 scroller: true
	});
} );
</script>

<div class="my-3 my-md-5">
	<div class="container">
		<div class="row">
			<div class="col-12">
				<form:form action="editUserDetails" method="post" commandName="userBean"
					name="approveUser" id="approveUserForm" cssClass="card">

					<div class="card-header">
						<h3 class="card-title"><i class="fe fe-user"></i> &nbsp;&nbsp;Edit User </h3>
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
						<table id="editUserTable" class="table table-striped table-bordered" style="width:100%">
					<thead>
						<tr>
							<th>Select</th>
							<th>User Name</th>
							<th>Full Name</th>
							<th>Mobile Number</th>
							<th>Email ID</th>
							<th>Sensitive Data</th>
							<th>Role Name</th>
							<th>Created By</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="itr" items="${editUserForm}">
							<tr>
								<td>
									<label class="custom-control custom-radio">
									<form:radiobutton path="strUserID"
											cssClass="custom-control-input approveID"
											value="${itr.strUserID}"
											id="approveID" />
                            <div class="custom-control-label"></div>
                          </label>
								</td>
								<td>${itr.strUserName}</td>
								<td>${itr.strFirstName}</td>
								<td>${itr.strMobileNo}</td>
								<td>${itr.strEmailID}</td>
								<td>${itr.strSensitiveFlag}</td>
								<td>${itr.strRoleID}</td>
								<td>${itr.strCreatedBy}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
					</div>
					
					
					<div class="card-footer text-right">
						<div class=".d-flex1">
							<button type="submit" id="editBtn" name="editBtn" value="1"  class="btn btn-primary ml-auto">Edit</button>
						</div>
					</div>
					
				</form:form>
			</div>
		</div>
	</div>
</div>
