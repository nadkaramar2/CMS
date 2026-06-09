<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	var deleteBinID;
	var cardData;

	$(document)
			.ready(
					function() {

						$('#strSelectID').val("N");

						$('#delBinMsg').text("");

						$('#cardTempTable').DataTable({
							scrollX : 100,
							scroller : true,
							orderCellsTop : true,
							responsive : true,
						});

						networkScheme($('#strCardType').val());

						$('#strCardType').change(function() {
							networkScheme($(this).val());
						});

						$('#deleteBtn')
								.click(
										function() {

											$
													.ajax({
														type : "POST",
														contentType : "application/json",
														url : "${pageContext.request.contextPath}/deleteCardTemplate",
														data : "id="
																+ deleteBinID,
														dataType : 'json',
														contentType : "application/x-www-form-urlencoded",
														timeout : 100000,
														success : function(data) {
															console
																	.log(
																			"SUCCESS: ",
																			data);
															if (data.outResponseCode == "00") {
																$('#delBinMsg')
																		.addClass(
																				"tag tag-green");
																$('#delBinMsg')
																		.text(
																				data.message);
																setTimeout(
																		function() {// wait for 5 secs(2)
																			var obj = {
																				Title : '',
																				Url : 'cardTemplateConfigForm'
																			};
																			history
																					.pushState(
																							obj,
																							obj.Title,
																							obj.Url);
																			location
																					.reload(); // then reload the page.(3)
																		}, 2000);
															} else {
																$('#delBinMsg')
																		.addClass(
																				"tag tag-red");
																$('#delBinMsg')
																		.text(
																				data.message);
															}
														},
														error : function(e) {
															console.log(
																	"ERROR: ",
																	e);
															$('#delBinMsg')
																	.addClass(
																			"tag tag-red");
															$('#delBinMsg')
																	.text(
																			"Error");
														},
														done : function(e) {
															console.log("DONE");
														}
													});

											return false;
										})

						$.validator.addMethod("regex",
								function(value, element, regexp) {
									var check = false;
									return this.optional(element)
											|| regexp.test(value);
								}, "ss");

						var value = $("#cardTemplateConfigForm")
								.validate(
										{
											rules : {
												strCardType : {
													required : true,
												},
												strServiceCode : {
													required : true,
													regex : /^[0-9]+$/,
													minlength : 3,
													maxlength : 3
												},
												strCardMailerFlag : {
													required : true
												},
												strTempLimitFlag : {
													required : true
												},
												strCardMailerFlag : {
													required : true
												},
												strDailyPinLimit : {
													required : true,
													regex : /^[0-9]+$/,
													minlength : 1,
													maxlength : 3
												},
												strConPinLimit : {
													required : true,
													regex : /^[0-9]+$/,
													minlength : 1,
													maxlength : 3
												},
												strOnlineAtmLimit : {
													required : true,
													regex : /^[0-9]+$/,
													minlength : 1,
													maxlength : 20
												},
												strOnlinePosLimit : {
													required : true,
													regex : /^[0-9]+$/,
													minlength : 1,
													maxlength : 20
												},
												strOnlineEcomLimit : {
													required : true,
													regex : /^[0-9]+$/,
													minlength : 1,
													maxlength : 20
												},
												strOfflineLimit : {
													required : true,
													regex : /^[0-9]+$/,
													minlength : 1,
													maxlength : 20
												},
												strMonthlyLimit : {
													required : true,
													regex : /^[0-9]+$/,
													minlength : 1,
													maxlength : 20
												},
												strWeeklyLimit : {
													required : true,
													regex : /^[0-9]+$/,
													minlength : 1,
													maxlength : 20
												},
												strDailyLimit : {
													required : true,
													regex : /^[0-9]+$/,
													minlength : 1,
													maxlength : 20
												},
												strExpiryType : {
													required : true
												},
												strExpireValue : {
													required : true,
													regex : /^[0-9]+$/,
													minlength : 1,
													maxlength : 2
												}
											},
											messages : {
												strCardType : {
													required : "Required !",
												},
												strServiceCode : {
													required : "Please Enter Service Code!",
													minlength : "Minmum {0} characters required!",
													maxlength : "Maximum {0} characters allowed!",
													regex : "Only Numeric allow !"
												},
												strCardMailerFlag : {
													required : "Required !",
												},
												strPinMailerFlag : {
													required : "Required !",
												},
												strTempLimitFlag : {
													required : "Required !",
												},
												strDailyPinLimit : {
													required : "Please Enter Daily Pin Limit!",
													minlength : "Minmum {0} characters required!",
													maxlength : "Maximum {0} characters allowed!",
													regex : "Only Numeric allow !"
												},
												strConPinLimit : {
													required : "Please Enter Consecutive Pin Limit!",
													minlength : "Minmum {0} characters required!",
													maxlength : "Maximum {0} characters allowed!",
													regex : "Only Numeric allow !"
												},
												strOnlineAtmLimit : {
													required : "Please Enter Online ATM Limit!",
													minlength : "Minmum {0} characters required!",
													maxlength : "Maximum {0} characters allowed!",
													regex : "Only Numeric allow !"
												},
												strOnlinePosLimit : {
													required : "Please Enter Online POS Limit!",
													minlength : "Minmum {0} characters required!",
													maxlength : "Maximum {0} characters allowed!",
													regex : "Only Numeric allow !"
												},
												strOnlineEcomLimit : {
													required : "Please Enter Online ECOM Limit!",
													minlength : "Minmum {0} characters required!",
													maxlength : "Maximum {0} characters allowed!",
													regex : "Only Numeric allow !"
												},
												strOfflineLimit : {
													required : "Please Enter Offline Limit!",
													minlength : "Minmum {0} characters required!",
													maxlength : "Maximum {0} characters allowed!",
													regex : "Only Numeric allow !"
												},
												strMonthlyLimit : {
													required : "Please Enter Monthly Limit!",
													minlength : "Minmum {0} characters required!",
													maxlength : "Maximum {0} characters allowed!",
													regex : "Only Numeric allow !"
												},
												strWeeklyLimit : {
													required : "Please Enter Weekly Limit!",
													minlength : "Minmum {0} characters required!",
													maxlength : "Maximum {0} characters allowed!",
													regex : "Only Numeric allow !"
												},
												strDailyLimit : {
													required : "Please Enter Daily Limit!",
													minlength : "Minmum {0} characters required!",
													maxlength : "Maximum {0} characters allowed!",
													regex : "Only Numeric allow !"
												},
												strExpiryType : {
													required : "Required !"
												},
												strExpireValue : {
													required : "Please Enter Expiry Value!",
													minlength : "Minmum {0} characters required!",
													maxlength : "Maximum {0} characters allowed!",
													regex : "Only Numeric allow !"
												}
											},
											submitHandler : function(form) {

												var status = false;
												var monthLimit = new Number($(
														'#strMonthlyLimit')
														.val());
												var weeklyLimit = new Number($(
														'#strWeeklyLimit')
														.val());
												var dailyLimit = new Number($(
														'#strDailyLimit').val());

												if (weeklyLimit < dailyLimit) {
													$('#strDailyLimitError')
															.text(
																	"Daily Limit Greater than Monthly Limit");
													status = false;
												} else {
													$('#strDailyLimitError')
															.text("");
													status = true;
												}

												if (status) {
													if (monthLimit < weeklyLimit) {
														$(
																'#strWeeklyLimitError')
																.text(
																		"Weekly Limit Greater than Monthly Limit");
														status = false;
													} else {
														$(
																'#strWeeklyLimitError')
																.text("");
														status = true;
													}
												}
												if (!status) {
													return status;
												} else {

													if ($('#strSelectID').val() == "N") {

														var data = eval('${cardTemplate}');
														var flag = false;
														var binVal = form.strCardType.value;

														$
																.each(
																		data,
																		function(
																				index,
																				value) {
																			if (binVal == JSON
																					.stringify(
																							value.strCardType)
																					.replace(
																							"\"",
																							"")
																					.replace(
																							"\"",
																							"")) {
																				flag = true;
																				return false;
																			}
																		});
														if (flag) {
															$('#strError')
																	.text(
																			"Duplicate Card Template");
															$('#errorMsg')
																	.hide();
														} else {
															$('#strError')
																	.text("");
															$('#errorMsg')
																	.hide();
															form.submit();
														}
													} else {
														cardData.strServiceCode = form.strServiceCode.value;
														cardData.strCardMailerFlag = form.strCardMailerFlag.value;
														cardData.strPinMailerFlag = form.strPinMailerFlag.value;
														cardData.strTempLimitFlag = form.strTempLimitFlag.value;
														cardData.strDailyPinLimit = form.strDailyPinLimit.value;
														cardData.strConPinLimit = form.strConPinLimit.value;
														cardData.strOnlineAtmLimit = form.strOnlineAtmLimit.value;
														cardData.strOnlinePosLimit = form.strOnlinePosLimit.value;
														cardData.strOnlineEcomLimit = form.strOnlineEcomLimit.value;
														cardData.strOfflineLimit = form.strOfflineLimit.value;
														cardData.strMonthlyLimit = form.strMonthlyLimit.value;
														cardData.strWeeklyLimit = form.strWeeklyLimit.value;
														cardData.strDailyLimit = form.strDailyLimit.value;
														cardData.strExpireValue = form.strExpireValue.value;
														cardData.strExpiryType = form.strExpiryType.value;
														$
																.ajax({
																	type : "POST",
																	contentType : "application/json",
																	url : "${pageContext.request.contextPath}/updateCardTemplate",
																	data : JSON
																			.stringify(cardData),
																	dataType : 'json',
																	contentType : "application/json",
																	timeout : 100000,
																	success : function(
																			data) {
																		console
																				.log(
																						"SUCCESS: ",
																						data);
																		if (data.outResponseCode == "00") {
																			$(
																					'#strError')
																					.addClass(
																							"tag tag-green");
																			$(
																					'#strError')
																					.text(
																							data.message);
																			setTimeout(
																					function() {// wait for 5 secs(2)
																						var obj = {
																							Title : '',
																							Url : 'cardTemplateConfigForm'
																						};
																						history
																								.pushState(
																										obj,
																										obj.Title,
																										obj.Url);
																						location
																								.reload(); // then reload the page.(3)
																					},
																					2000);
																		} else {
																			$(
																					'#strError')
																					.addClass(
																							"tag tag-red");
																			$(
																					'#strError')
																					.text(
																							data.message);
																		}
																	},
																	error : function(
																			e) {
																		console
																				.log(
																						"ERROR: ",
																						e);
																		$(
																				'#strError')
																				.addClass(
																						"tag tag-red");
																		$(
																				'#strError')
																				.text(
																						"Error");
																	},
																	done : function(
																			e) {
																		console
																				.log("DONE");
																	}
																});
													}
												}
											}
										});

					});

	function editFunction(bin) {

		var data = eval('${cardTemplate}');
		$.each(data, function(index, value) {
			if (bin == JSON.stringify(value.strCardType).replace("\"", "")
					.replace("\"", "")) {
				cardData = value;
				return false;
			}
		});
		networkScheme(cardData.strCardType);
		$('#strCardType').val(cardData.strCardType);
		$('#strCardType').attr('readonly', true);
		$('#strCardType option:not(:selected)').attr('disabled', true);
		$('#strServiceCode').val(cardData.strServiceCode);
		$('#strCardMailerFlag').val(cardData.strCardMailerFlag);
		$('#strPinMailerFlag').val(cardData.strPinMailerFlag);
		$('#strTempLimitFlag').val(cardData.strTempLimitFlag);
		$('#strDailyPinLimit').val(cardData.strDailyPinLimit);
		$('#strConPinLimit').val(cardData.strConPinLimit);
		$('#strOnlineAtmLimit').val(cardData.strOnlineAtmLimit);
		$('#strOnlinePosLimit').val(cardData.strOnlinePosLimit);
		$('#strOnlineEcomLimit').val(cardData.strOnlineEcomLimit);
		$('#strOfflineLimit').val(cardData.strOfflineLimit);
		$('#strMonthlyLimit').val(cardData.strMonthlyLimit);
		$('#strWeeklyLimit').val(cardData.strWeeklyLimit);
		$('#strDailyLimit').val(cardData.strDailyLimit);
		$('#strExpireValue').val(cardData.strExpireValue);
		$('#strExpiryType').val(cardData.strExpiryType);
		$('#strSelectID').val("Y");
		$('#strExpiryType').val(cardData.strExpiryType);
		$('#strExpireValue').val(cardData.strExpireValue);

	}

	function deleteFunction(id) {
		$('#delBinMsg').text("");
		$('#strCardType').val("");
		$('#strServiceCode').val("");
		$('#strCardMailerFlag').val("");
		$('#strPinMailerFlag').val("");
		$('#strTempLimitFlag').val("");
		$('#strDailyPinLimit').val("");
		$('#strConPinLimit').val("");
		$('#strOnlineAtmLimit').val("");
		$('#strOnlinePosLimit').val("");
		$('#strOnlineEcomLimit').val("");
		$('#strOfflineLimit').val("");
		$('#strMonthlyLimit').val("");
		$('#strWeeklyLimit').val("");
		$('#strDailyLimit').val("");
		deleteBinID = id;
		$('#myModal').modal('show');
	}

	function networkScheme(bin) {

		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "${pageContext.request.contextPath}/getNetworkScheme",
			data : "id=" + bin,
			dataType : 'json',
			contentType : "application/x-www-form-urlencoded",
			timeout : 100000,
			success : function(data) {
				console.log("SUCCESS: ", data);
				if (data.outResponseCode == "00") {
					$('#networkScheme').val(data.message);
				}
			},
			error : function(e) {
				console.log("ERROR: ", e);
			},
			done : function(e) {
				console.log("DONE");
			}
		});

		return false;

	}
</script>

<div class="my-3 my-md-5">
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<jsp:include page="binCardConfigLeftMenu.jsp"></jsp:include>
			</div>
			<div class="col-md-9">
				<form:form action="cardTemplateConfig" method="post"
					commandName="cardTemplateModel" name="cardTemplateConfigForm"
					id="cardTemplateConfigForm" cssClass="card">

					<div class="card-header">
						<h3 class="card-title">
							<i class="fe fe-user-plus"></i> &nbsp;&nbsp;Card Template
							Configuration <span class="text-right badge badge-danger"
								id="strError"></span>
						</h3>
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
									<label class="form-label">Card Type <b>*</b></label>
									<form:select path="strCardType" id="strCardType"
										cssClass="form-control selectpicker">
										<form:option value=""></form:option>
										<c:forEach items="${cardTypeList}" var="itr">
											<form:option value="${itr.strID}">${itr.strCardType} : ${itr.strCardDesc}</form:option>
										</c:forEach>
									</form:select>
									<span class="error" id="strCardTypeError"><form:errors
											path="strCardType"></form:errors></span>
								</div>
							</div>

							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Network Scheme</label> <input
										type="text" name="networkScheme" class="form-control"
										id="networkScheme" readonly="readonly"> <span
										class="error" id=""networkScheme"Error"></span>
								</div>
							</div>
							
							</div>
							
							<div class="row">
							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Service Code <b>*</b></label>
									<form:input path="strServiceCode" id="strServiceCode"
										cssClass="form-control" />
									<span class="error" id="strServiceCodeError"><form:errors
											path="strServiceCode"></form:errors></span>
								</div>
							</div>

							<%-- <div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Card Issue Type <b>*</b></label>
									<form:input path="strCardIssue" id="strCardIssue"
										cssClass="form-control" />
									<span class="error" id="strCardIssueError"><form:errors
											path="strCardIssue"></form:errors></span>
								</div>
							</div> --%>

							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Card Mailer Issue Flag <b>*</b></label>
									<form:select path="strCardMailerFlag" id="strCardMailerFlag"
										cssClass="form-control selectpicker">
										<form:option value=""></form:option>
										<form:option value="Y">Yes</form:option>
										<form:option value="N">No</form:option>
									</form:select>
									<span class="error" id="strCardMailerFlagError"><form:errors
											path="strCardIssue"></form:errors></span>
								</div>
							</div>
							</div>
							
							
							<div class="row">
							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Pin Mailer Issue Flag <b>*</b></label>
									<form:select path="strPinMailerFlag" id="strPinMailerFlag"
										cssClass="form-control selectpicker">
										<form:option value=""></form:option>
										<form:option value="Y">Yes</form:option>
										<form:option value="N">No</form:option>
										<form:option value="G">Green Pin</form:option>
									</form:select>
									<span class="error" id="strPinMailerFlagError"><form:errors
											path="strPinMailerFlag"></form:errors></span>
								</div>
							</div>

							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Temporary Limit Activation
										Flag <b>*</b>
									</label>
									<form:select path="strTempLimitFlag" id="strTempLimitFlag"
										cssClass="form-control selectpicker">
										<form:option value=""></form:option>
										<form:option value="Y">Yes</form:option>
										<form:option value="N">No</form:option>
									</form:select>
									<span class="error" id="strTempLimitFlagError"><form:errors
											path="strTempLimitFlag"></form:errors></span>
								</div>
							</div>
							</div>
							
							<div class="row">

							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Daily Pin Limit <b>*</b></label>
									<form:input path="strDailyPinLimit" id="strDailyPinLimit"
										cssClass="form-control" />
									<span class="error" id="strDailyPinLimitError"><form:errors
											path="strDailyPinLimit"></form:errors></span>
								</div>
							</div>

							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Consecutive Pin Limit <b>*</b></label>
									<form:input path="strConPinLimit" id="strConPinLimit"
										cssClass="form-control" />
									<span class="error" id="strConPinLimitError"><form:errors
											path="strConPinLimit"></form:errors></span>
								</div>
							</div>
							</div>
							
							<div class="row">
							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Online ATM Limit <b>*</b></label>
									<form:input path="strOnlineAtmLimit" id="strOnlineAtmLimit"
										cssClass="form-control" />
									<span class="error" id="strOnlineAtmLimitError"><form:errors
											path="strOnlineAtmLimit"></form:errors></span>
								</div>
							</div>

							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Online POS Limit <b>*</b></label>
									<form:input path="strOnlinePosLimit" id="strOnlinePosLimit"
										cssClass="form-control" />
									<span class="error" id="strOnlinePosLimitError"><form:errors
											path="strOnlinePosLimit"></form:errors></span>
								</div>
							</div>
							</div>
							
							<div class="row">
							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Online Ecom Limit <b>*</b></label>
									<form:input path="strOnlineEcomLimit" id="strOnlineEcomLimit"
										cssClass="form-control" />
									<span class="error" id="strOnlineEcomLimitError"><form:errors
											path="strOnlineEcomLimit"></form:errors></span>
								</div>
							</div>

							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Offline Limit <b>*</b></label>
									<form:input path="strOfflineLimit" id="strOfflineLimit"
										cssClass="form-control" />
									<span class="error" id="strOfflineLimitError"><form:errors
											path="strOfflineLimit"></form:errors></span>
								</div>
							</div>
							</div>
							
							
							<div class="row">
							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Monthly Limit <b>*</b></label>
									<form:input path="strMonthlyLimit" id="strMonthlyLimit"
										cssClass="form-control" />
									<span class="error" id="strMonthlyLimitError"><form:errors
											path="strMonthlyLimit"></form:errors></span>
								</div>
							</div>

							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Weekly Limit <b>*</b></label>
									<form:input path="strWeeklyLimit" id="strWeeklyLimit"
										cssClass="form-control" />
									<span class="error" id="strWeeklyLimitError"><form:errors
											path="strWeeklyLimit"></form:errors></span>
								</div>
							</div>
							</div>
							
							
							<div class="row">
							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Daily Limit <b>*</b></label>
									<form:input path="strDailyLimit" id="strDailyLimit"
										cssClass="form-control" />
									<span class="error" id="strDailyLimitError"><form:errors
											path="strDailyLimit"></form:errors></span>
								</div>
							</div>

							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Expiry Type <b>*</b></label>
									<form:select path="strExpiryType" id="strExpiryType"
										cssClass="form-control selectpicker">
										<form:option value=""></form:option>
										<form:option value="M">Month</form:option>
										<form:option value="Y">Year</form:option>
									</form:select>
									<span class="error" id="strExpiryTypeError"><form:errors
											path="strExpiryType"></form:errors></span>
								</div>
							</div>
							</div>
							
							<div class="row">
							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Expiry Value <b>*</b></label>
									<form:input path="strExpireValue" id="strExpireValue"
										cssClass="form-control" />
									<span class="error" id="strExpireValueError"><form:errors
											path="strExpireValue"></form:errors></span>
								</div>
							</div>
							</div>
						
							<div class="card-footer text-right">
						<div class=".d-flex1">
							<button type="submit" id="submitBtn"
								class="btn btn-primary ml-auto">Submit</button>
							<button type="reset" class="btn btn-primary ml-auto"
								id="resetBtn">Clear</button>
						</div>
					</div>
					
					

					<div class="card-body">
						<table id="cardTempTable"
							class="table table-striped table-bordered nowrap"
							style="width: 100%">
							<thead>
								<tr>
									<th>Card Type</th>
									<th>Service Code</th>
									<!-- <th>Card Issue</th> -->
									<th>Card Mailer Flag</th>
									<!-- <th>Pin Mailer Flag</th>
									<th>Temporary Limit Flag</th>
									<th>Daily Pin Limit</th>
									<th>Consecutive Pin Limit</th> -->
									<!-- <th>Online ATM Limit</th>
									<th>Online POS Limit</th>
									<th>Online ECOM Limit</th>
									<th>Offline Limit</th>
									<th>Monthly Limit</th>
									<th>Weekly Limit</th>
									<th>Daily Limit</th> -->
									<th>Expiry Type</th>
									<th>Expiry Value</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="itr" items="${cardTempList}">
									<tr>
										<td>${itr.typeDesc}</td>
										<td>${itr.strServiceCode}</td>
										<%-- <td>${itr.strCardIssue}</td> --%>
										<td>${itr.strCardMailerFlag}</td>
										<%-- <td>${itr.strPinMailerFlag}</td>
										<td>${itr.strTempLimitFlag}</td>
										<td>${itr.strDailyPinLimit}</td>
										<td>${itr.strConPinLimit}</td> --%>
									<%-- 	<td>${itr.strOnlineAtmLimit}</td>
										<td>${itr.strOnlinePosLimit}</td>
										<td>${itr.strOnlineEcomLimit}</td>
										<td>${itr.strOfflineLimit}</td>
										<td>${itr.strMonthlyLimit}</td>
										<td>${itr.strWeeklyLimit}</td>
										<td>${itr.strDailyLimit}</td> --%>
										<td>${itr.strExpiryType == 'M' ? 'Month' : 'Year'}</td>
										<td>${itr.strExpireValue}</td>
										<td><a class="icon"
											href="javascript:editFunction(${itr.strCardType})"> <i
												class="fe fe-edit"></i>
										</a> &nbsp;&nbsp;&nbsp; <a class="icon"
											href="javascript:deleteFunction(${itr.strID})"> <i
												class="fe fe-trash"></i>
										</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<input type="hidden" value="${leftbinCardMenuID}"
								id="leftbinCardMenuID">
					</div>
					
						</div>
					</div>
					
					<form:hidden path="strSelectID" id="strSelectID" />

					<!-- Modal -->
					<div class="modal fade" id="myModal" role="dialog">
						<div class="modal-dialog modal-sm">
							<div class="modal-content">
								<div class="modal-header">
									<p class="text-capitalize">
										Delete Card Template <label id="delBinMsg"></label>
									</p>
								</div>
								<div class="modal-body">

									<form>
										<p class="leading-loose">Are you sure you want to delete
											this Card Template ?</p>
									</form>
								</div>


								<div class="modal-footer">
									<button type="button" class="btn btn btn-red" id="deleteBtn">Delete</button>
									<button type="button" class="btn btn-default"
										data-dismiss="modal">Close</button>
								</div>
							</div>
						</div>
					</div>
					<!-- Modal -->


				</form:form>
			</div>
		</div>
	</div>
</div>
