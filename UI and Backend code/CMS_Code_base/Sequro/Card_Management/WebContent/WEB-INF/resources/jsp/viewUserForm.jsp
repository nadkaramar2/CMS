<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
$(document).ready(function() {
    $('#viewUserTable').DataTable({
		 scrollX: 100,
		 scroller: true
	});
} );
</script>

<div class="my-3 my-md-5">
	<div class="container">
		<div class="row">
			<div class="col-12">
				<form:form action="#" method="post" commandName="userBean"
					name="viewUser" id="viewUserForm" cssClass="card">

					<div class="card-header">
						<h3 class="card-title"><i class="fe fe-users"></i> &nbsp;&nbsp;View User </h3>
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
						<table id="viewUserTable" class="table table-striped table-bordered" style="width:100%">
					<thead>
						<tr>
							<th>User Name</th>
							<th>First Name</th>
							<th>Last Name</th>
							<th>Mobile Number</th>
							<th>Email ID</th>
							<th>Sensitive Data</th>
							<th>Role Name</th>
							<th>Created By</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="itr" items="${viewUserList}">
							<tr>
								<td>${itr.strUserName}</td>
								<td>${itr.strFirstName}</td>
								<td>${itr.strLastName}</td>
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
					
				</form:form>
			</div>
		</div>
	</div>
</div>
