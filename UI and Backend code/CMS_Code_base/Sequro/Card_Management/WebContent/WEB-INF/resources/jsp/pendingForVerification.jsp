<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<script type="text/javascript">
$(document).ready(function() 
{
	$('#pendingForVerification').DataTable({
		 responsive: true
	});
	
	});


</script>

<div class="my-3 my-md-5">
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<jsp:include page="bulkTransferLeftMenu.jsp"></jsp:include>
			</div>
			<div class="col-9">
				<form:form action="pendingForVerification" method="GET" commandName="bulkTransfer" name="bulkTransfer" id="bulkTransfer" cssClass="card">

					<div class="card-header">
						<h3 class="card-title">
							<i class="fe fe-user-plus"></i> &nbsp;&nbsp;Authorize Accounts
						</h3>
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
					<div class="card-header"> 
								<h3 class="card-title">Pending For Verification List</h3>
							</div>
							
							<div class="card-body" style="width: 100%;" align="left">
							
							<table id="pendingForVerification" class="table table-striped table-bordered nowrap">
								<thead>
									<tr>
										<th>Transaction ID</th>
										<th>Date</th>
										<th>Time</th>
										<th>Amount</th>
										<th>Interface Reference</th>
									</tr>
								</thead>
								
								
							</table>
					
						
					</div>
					<div class="card-footer text-right">
						<div class=".d-flex1">
							<button type="submit" class="btn btn-primary ml-auto" id="submitBtn">Submit</button>
							<button type="reset" class="btn btn-primary ml-auto" id="resetBtn">Clear</button>
						</div>
					</div>
					<input type="hidden" value="${leftbulkMenuID}" id="leftbulkMenuID">
		
				</form:form>
			</div>
		</div>
	</div>
</div>
