<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
$(document).ready(function() {
    $('#approveUserTable').DataTable({
		 scrollX: 100,
		 scroller: true
	});
} );
</script>

<div class="my-3 my-md-5">
	<div class="container">
		<div class="row">
			<div class="col-12">
				<form:form action="approveUser" method="post" commandName="userBean"
					name="approveUser" id="approveUserForm" cssClass="card">

					<div class="card-header">
						<h3 class="card-title"><i class="fe fe-user-check"></i> &nbsp;&nbsp;Approve User </h3>
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
						<table id="approveUserTable" class="table table-striped table-bordered" style="width:100%">
					<thead>
						<tr>
							<th>Select</th>
							<th>User Name</th>
							<th>Full Name</th>
							<th>Mobile Number</th>
							<th>Email ID</th>
							<th>Sensitive Data</th>
							<!-- <th>Role Name</th> -->
							<th>Created By</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="itr" items="${userList}">
							<tr>
								<td>
									<label class="custom-control custom-radio">
									<form:checkbox path="strRequestData"
											cssClass="custom-control-input approveID"
											value="${itr.strUserTempID}|${itr.strUserID}|${itr.strUserName}|${itr.strEmailID}|${itr.strGroupID}"
											id="approveID" />
                            <div class="custom-control-label"></div>
                          </label>
								</td>
								<td><a data-toggle="modal" id="btnUsr" data-target="#modal" onclick="getUserData(${itr.strUserID});">${itr.strUserName}</a></td>
								<td>${itr.strFirstName}</td>
								<td>${itr.strMobileNo}</td>
								<td>${itr.strEmailID}</td>
								<td>${itr.strSensitiveFlag}</td>
								<%-- <td>${itr.strRoleID}</td> --%>
								<td>${itr.strCreatedBy}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
					</div>
					
					<div class="card-footer text-right">
						<div class=".d-flex1">
							<button type="submit" id="submitBtn" name="strRequestBtn" value="1"  class="btn btn-primary ml-auto">Approve</button>
							<button type="submit" class="btn btn-primary ml-auto" name="strRequestBtn" value="2"  id="resetBtn">Reject</button>
						</div>
					</div>
					
				</form:form>
			</div>
		</div>
	</div>
</div>
