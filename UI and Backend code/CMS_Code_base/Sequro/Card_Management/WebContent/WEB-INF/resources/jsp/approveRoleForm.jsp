<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
$(document).ready(function() {
	var data = eval('${roleList}');
    $('#approveRoleTable').DataTable({
		 scrollX: 100,
		 scroller: true,
		 "aaData" : data,
			"aoColumns" : [ {mRender: function (data, type, row)
             {
					return '<label class="custom-control custom-radio"> <input type="checkbox" name="strRoleTempID" class="custom-control-input" value = "'+ row.strRoleTempID + "|" + row.iroleID +'"> <input type="hidden" name="roleID" value = "'+ row.iroleID +'">  <div class="custom-control-label"></div> </label>'
          	},},
         {
				
				"mData" : "strRoleName"
			}, {
				"mData" : "strDescription"
			}, {
				"mData" : "strCreatedBy"
			}, {
				"mData" : "strCreatedDate"
			}, {
				"mData" : "strNewOrModified"
			} ]
	});
} );
</script>

<div class="my-3 my-md-5">
	<div class="container">
		<div class="row">
			<div class="col-12">
				<form:form action="approveRole" method="post" commandName="roleBean"
					name="approveRole" id="approveRoleForm" cssClass="card">

					<div class="card-header">
						<h3 class="card-title"><i class="fe fe-user-check"></i> &nbsp;&nbsp;Approve Role </h3>
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
						<table id="approveRoleTable" class="table table-striped table-bordered" style="width:100%">
					<thead>
						<tr>
							<th>Select</th>
							<th>Role Name</th>
							<th>Role Description</th>
							<th>Created By</th>
							<th>Created Date</th>
							<th>New / Modified</th>
						</tr>
					</thead>
				</table>
					</div>
					
					<div class="card-footer text-right">
						<div class=".d-flex1">
							<button type="submit" id="submitBtn" name="iApprovalStatusID" value="1"  class="btn btn-primary ml-auto">Approve</button>
							<button type="submit" class="btn btn-primary ml-auto" name="iApprovalStatusID" value="2"  id="resetBtn">Reject</button>
						</div>
					</div>
					
				</form:form>
			</div>
		</div>
	</div>
</div>
