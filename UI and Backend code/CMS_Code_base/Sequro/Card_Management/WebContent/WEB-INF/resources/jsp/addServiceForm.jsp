<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	$(document).ready(function() {

		if ($('#inCardIssurance').val() == "1") {
			$('#NEWCARD').show();
			$('#cardList').hide();
			$("#flagHotList").val("N");
			$('#instCard').hide();
			$('#inCodeFlag').hide();
		} else if ($('#inCardIssurance').val() == "2") {
			$('#NEWCARD').show();
			$('#cardList').show();
			$("#flagHotList").val("Y");
			$("#chargeType").val("CRC");
			$('#instCard').hide();
			$('#inCodeFlag').show();
		} else if ($('#inCardIssurance').val() == "3") {
			$('#NEWCARD').hide();
			$('#cardList').show();
			$("#flagHotList").val("N");
			$("#chargeType").val("CRN");
			$('#instCard').hide();
			$('#inCodeFlag').hide();
		} else if ($('#inCardIssurance').val() == "4") {
			$('#NEWCARD').show();
			$('#cardList').show();
			$("#flagHotList").val("N");
			$("#chargeType").val("CRN");
			$('#instCard').hide();
			$('#inCodeFlag').hide();
		}

		$('#inCardIssurance').change(function() 
		{
			if ($(this).val() == "1") {
				$('#NEWCARD').show();
				$('#cardList').hide();
				$("#flagHotList").val("N");
				$('#instCard').hide();
				
				$('#inCodeFlag').hide();
			} else if ($(this).val() == "2") {
				$('#NEWCARD').show();
				$('#cardList').show();
				$("#flagHotList").val("Y");
				$("#chargeType").val("CRC");
				$('#instCard').hide();
				$('#inCodeFlag').show();
			} else if ($(this).val() == "3") {
				$('#NEWCARD').hide();
				$('#cardList').show();
				$("#flagHotList").val("N");
				$("#chargeType").val("CRN");
				$('#instCard').hide();
				$('#inCodeFlag').hide();
			} else if ($(this).val() == "4") {
				$('#NEWCARD').show();
				$('#cardList').show();
				$("#flagHotList").val("N");
				$("#chargeType").val("CRN");
				$('#instCard').hide();
				$('#inCodeFlag').hide();
			} 
		});
		
		$('#emboss1').keyup(function(){
			var txt = $('#emboss1').val().replace(/(?:^|)\w/g, function(match) {
		        return match.toUpperCase();
		     });
			$('#emboss1').val(txt);
		});
		
		$('#emboss2').keyup(function(){
			var txt = $('#emboss2').val().replace(/(?:^|)\w/g, function(match) {
		        return match.toUpperCase();
		     });
			$('#emboss2').val(txt);
		});
		
		$('#first').keyup(function(){
			var txt = $('#first').val().replace(/(?:^|)\w/g, function(match) {
		        return match.toUpperCase();
		     });
			$('#first').val(txt);
		});
		
		$('#middle').keyup(function(){
			var txt = $('#middle').val().replace(/(?:^|)\w/g, function(match) {
		        return match.toUpperCase();
		     });
			$('#middle').val(txt);
		});
		
		$('#last').keyup(function(){
			var txt = $('#last').val().replace(/(?:^|)\w/g, function(match) {
		        return match.toUpperCase();
		     });
			$('#last').val(txt);
		});
		
	});
</script>

<div class="my-3 my-md-5">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<form:form action="addService" method="post"
					commandName="serviceBean" name="addService" id="addServiceForm"
					cssClass="card">

					<div class="card-header">
						<h3 class="card-title">Generate Cards</h3>
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
											<label class="form-label">Card Issuance Request </label>
											<form:select path="inCardIssurance" id="inCardIssurance"
												cssClass="form-control selectpicker">
												<form:option value="1">New Card</form:option>
												<c:if test="${cardList.size() > 0}">
													<form:option value="2">Card Replacement</form:option>
													<form:option value="3">Renewal With Same Card</form:option>
													<form:option value="4">Renewal With New Card</form:option>
												</c:if>
											</form:select>
										</div>
									</div>

									<div class="col-md-3" id="cardList">
										<div class="form-group">
											<label class="form-label">Card List</label>
											<form:select path="inCard" id="inCard"
												cssClass="form-control selectpicker">
												<c:forEach items="${cardList}" var="itr">
													<form:option value="${itr.lkpkey}">${itr.lkpvalue}</form:option>
												</c:forEach>
											</form:select>
										</div>
									</div>

									<div class="col-md3">
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
																cssClass="form-control" readonly="true" />
										</div>
									</div>

								</div>
								<div id="NEWCARD">

									<h5 class="card-title">Select Service to Add</h5>
									<div class="row">
										<div class="custom-controls-stacked">
											&nbsp;&nbsp;&nbsp; <label
												class="custom-control custom-radio custom-control-inline">
												<input type="radio" class="custom-control-input"
												name="cardFlag" value="1" checked> <span
												class="custom-control-label">Debit Card</span>
											</label> <label
												class="custom-control custom-radio custom-control-inline">
												<input type="radio" class="custom-control-input"
												name="cardFlag" value="2"> <span
												class="custom-control-label">Prepaid Card</span>
											</label>
										</div>
									</div>

									<div class="row">
										<div class="col-md-6">
											<div class="card-body">
												<h3 class="card-title">Select Card Type</h3>
												<div class="row">
													<div class="col-md-6">
														<div class="form-group">
															<label class="form-label">Type</label>
															<form:select path="inCardType" id="cardType"
																cssClass="form-control selectpicker">
																<c:forEach items="${cardTypeList}" var="itr">
																	<form:option value="${itr.strID}">${itr.strCardDesc}</form:option>
																</c:forEach>
															</form:select>
														</div>
													</div>
													<div class="col-md-6">
														<div class="form-group">
															<label class="form-label">Record Type</label>
															<form:select path="inCardIssueCode" id="inCardIssueCode"
																cssClass="form-control selectpicker">
																<form:option value="P">Physical Card</form:option>
																<form:option value="V">Virtual Card</form:option>
															</form:select>
														</div>
													</div>
												</div>
											</div>
										</div>

										<div class="col-md-6">
											<div class="card-body">
												<h3 class="card-title">Emboss Names</h3>
												<div class="row">
													<div class="col-md-6 col-lg-6">
														<div class="form-group">
															<label class="form-label">Emboss Line 1</label>
															<form:input path="inEmbossLine1" id="emboss1"
																cssClass="form-control" />
														</div>
													</div>
													<div class="col-md-6 col-lg-6">
														<div class="form-group">
															<label class="form-label">Emboss Line 2</label>
															<form:input path="inEmbossLine2" id="emboss2"
																cssClass="form-control" />
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>

									<div class="card-body">
										<h3 class="card-title">Encode Name</h3>
										<div class="row">

											<div class="col-md-3">
												<div class="form-group">
													<label class="form-label">Encode First Name</label>
													<form:input path="inEncodeFirstName" id="first"
														cssClass="form-control" />
												</div>
											</div>

											<div class="col-md-3">
												<div class="form-group">
													<label class="form-label">Encode Middle Name</label>
													<form:input path="inEncodeMiddleName" id="middle"
														cssClass="form-control" />
												</div>
											</div>

											<div class="col-md-3">
												<div class="form-group">
													<label class="form-label">Encode Last Name</label>
													<form:input path="inEncodeLastName" id="last"
														cssClass="form-control" />
												</div>
											</div>
											
											<div class="col-md-3" id="inCodeFlag">
												<div class="form-group">
													<label class="form-label">Reason</label>
													<form:input path="inCode" id="inCode"
														cssClass="form-control" />
													<span class="tag">existing card will be blocked permanently. Please Enter reason for block the card. </span>
												</div>
											</div>

										</div>
									</div>
								</div>
								<input type="hidden" id="flagHotList" name="inHotListFlag">
								<input type="hidden" id="chargeType" name="inChargeType">
								<%-- <form:hidden path="citizenID" />
								<form:hidden path="cifKey" /> --%>
								<input type="hidden" name="inInstantFlag" id="inInstantFlag"
									value="N">
								<div class="card-footer text-right">
									<div class="d-flex">
										<button type="submit" class="btn btn-primary ml-auto">Continue</button>
									</div>
								</div>
							</div>
						</c:otherwise>
					</c:choose>
				</form:form>
			</div>
		</div>
	</div>
</div>
