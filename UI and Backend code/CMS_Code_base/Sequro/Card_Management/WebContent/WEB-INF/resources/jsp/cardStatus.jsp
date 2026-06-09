<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">
$(document).ready(function() {
	var value = $("#cardStatusForm").validate({
		rules: {
			strDescription: {
                    required: true,
            },
		},
    	messages: {
    		strDescription: {
                    required: "Please Enter Comment!"
            }
    	},
		submitHandler: function(form) {
				return true;
				form.submit();	
		}
	});
	
	$('#emailDetailsTable').DataTable({
		 scrollX: 100,
		 scroller: true
	});
	
});
</script>

<div class="my-3 my-md-5">
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<jsp:include page="CardDetailsLeftMenu.jsp"></jsp:include>
			</div>
			<div class="col-md-9">
				<form:form action="cardStatus" method="post"
					commandName="cardStatus" name="cardStatus" id="cardStatusForm"
					cssClass="card">

					<div class="card-header">
						<h3 class="card-title">Status Management</h3>
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
									<label class="form-label">Status Code</label>
									<form:select path="strCardStatus" id="strCardStatus"
										cssClass="form-control selectpicker">
										<c:forEach items="${cardStatusList}" var="itr">
											<form:option value="${itr.lkpkey}">${itr.lkpkey} - ${itr.lkpvalue}</form:option>
										</c:forEach>
									</form:select>
								</div>
							</div>

							<%-- <div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Issue Date</label>
									<form:input path="strCardIssueDate" id="strCardIssueDate"
										cssClass="form-control" readonly="true" />
									<span class="error" id="strCardIssueDateError"><form:errors
											path="strCardIssueDate"></form:errors></span>
								</div>
							</div> --%>

							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Expiry Date </label>
									<form:input path="strExpiryDate" id="strExpiryDate"
										cssClass="form-control" readonly="true" />
									<span class="error" id="strExpiryDateError"><form:errors
											path="strExpiryDate"></form:errors></span>
								</div>
							</div>

							<%-- <div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Created User</label>
									<form:input path="strCardIssuedUser" id="strCardIssuedUser"
										cssClass="form-control" readonly="true" />
									<span class="error" id="strCardIssuedUserError"><form:errors
											path="strCardIssuedUser"></form:errors></span>
								</div>
							</div> --%>

							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Status Change Reason *</label>
									<form:input path="strDescription" id="strDescription"
										cssClass="form-control" maxlength="100" />
									<span class="error" id="strDescriptionError"><form:errors
											path="strDescription"></form:errors></span>
								</div>
							</div>

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
					<input type="hidden" value="${leftCardMenuID}" id="leftCardMenuID">
					<form:hidden path="strCardSeqNumber" />



					<div class="card-header">
						<h3 class="card-title">Card Status History</h3>
						<h2 id="errorEmailMsg" class="tag tag-red"></h2>
					</div>
					<div class="card-body">

						<table id="emailDetailsTable"
							class="table table-striped table-bordered nowrap" style="width: 100%">
							<thead>
								<tr>
									<th>Card Number</th>
									<th>Status Description</th>
									<th>Status Change Reason</th>
									<th>Change Date</th>
									<th>Change User</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="itr" items="${cardStatusDataList}">
									<tr>
										<td>${itr.strCardNumber}</td>
										<td>${itr.strCardStatusCode}</td>
										<td>${itr.strCardStatusDescription}</td>
										<td>${itr.strCardStatusChangeDate}</td>
										<td>${itr.strCardStatusChangeUser}</td>
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