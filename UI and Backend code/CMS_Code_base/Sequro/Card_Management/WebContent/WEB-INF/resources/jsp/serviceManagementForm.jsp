<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<style>
.custom-header1 {
    text-align: center;
    padding: 3px;
    background: #1E90FF;
    color: #fff;
}
</style>

<script type="text/javascript">
	$(document).ready(function() {
		$('#pre-selected-options').multiSelect({
			  selectableHeader: "<div class='custom-header1'>Service List</div>",
			  selectionHeader: "<div class='custom-header1'>Service Assigned To Card</div>",
			  selectableFooter: "<div class='custom-header1'></div>",
			  selectionFooter: "<div class='custom-header1'></div>"
		});
		
		$('#submitBtn').click(function() {
			$('#NCMCServiceList').val($('#pre-selected-options').val());
		})
	});
</script>


<div class="my-3 my-md-5">
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<jsp:include page="CardDetailsLeftMenu.jsp"></jsp:include>
			</div>
			<div class="col-md-9">
				<form:form action="serviceManagement" method="post" commandName="ncmcRequest"
					name="ncmcForm" id="ncmcForm" cssClass="card">
					<div class="card-header">
						<h3 class="card-title">NCMC Service Management</h3>
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

					<div class="card-body" align="center">


						<select id='pre-selected-options' multiple='multiple'>
							<c:forEach var="itr" items="${serviceList}">
								<option value="${itr.lkpkey}" ${itr.lkpvalue}>${itr.lkpkey}</option>
							</c:forEach>
						</select>


					</div>

					<div class="card-footer text-right">
						<div class="btn-list text-right">
							<button type="submit" id="submitBtn" name="strRequestBtn"
								value="1" class="btn btn-primary">Submit</button>
							<button type="submit" class="btn btn-secondary"
								name="strRequestBtn" value="2" id="resetBtn">Clear</button>
						</div>
					</div>
					
					<input type="hidden" value="${cardDetails.strTokenCard}" name="strCardNo">
					<input type="hidden" value="${cardDetails.strCardSeqNumber}" name="strMemberNo">
					<input type="hidden" value="${cardDetails.strPartID}" name="strParticipantID">
					<input type="hidden" value="" name="ncmcList" id="NCMCServiceList">
						
					<input type="hidden" value="${leftCardMenuID}" id="leftCardMenuID">
				</form:form>
			</div>
		</div>
	</div>
</div>

