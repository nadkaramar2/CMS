<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
.c-schedule {
	margin: 1em;
	padding: .5em;
	background-color: #efefef;
	display: flex;
}

.c-schedule-type {
	padding-right: .5em;
	border-right: 1px solid #666;
	min-width: 85px;
}

.c-schedule-options {
	padding-left: .5em;
}

ul {
	list-style-type: none;
	padding-left: 0;
}
</style>

<div class="my-3 my-md-5">
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<jsp:include page="tokenConfigLeftMenu.jsp"></jsp:include>
			</div>
			<div class="col-md-9">
				<form:form action="#" method="post" commandName="cardTokenModel"
					name="cardTokenConfigForm" id="cardTokenConfigForm" cssClass="card">

					<div class="card-header">
						<h3 class="card-title">
							<i class="fe fe-user-plus"></i> &nbsp;&nbsp;Card Token
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
							<div class="col-md-6 col-lg-4">
								<div class="form-group">
									<label class="form-label">Reissuance Token <b>*</b></label> <select
										name="type" id="type" class="form-control selectpicker">
										<option value="C">Card</option>
										<option value="CT">Card Type</option>
									</select>
								</div>
							</div>
						</div>
						<br>

						<div>
							<div class="row" id="cardDiv">
								<div class="col-md-6 col-lg-4">
									<div class="form-group">
										<label class="form-label">Card No <b>*</b></label>
										<form:input path="cardNo" id="cardNo" cssClass="form-control" />
										<span class="error" id="cardNoError"></span>
									</div>
								</div>
							</div>
							<div class="row" id="cardTypeDiv">
								<div class="col-md-6 col-lg-6">
									<div class="form-group">
										<label class="form-label">Card Type <b>*</b></label>
										<form:select path="strCardType" id="strCardType"
											cssClass="form-control selectpicker">
											<form:option value=""></form:option>
											<c:forEach items="${cardTypeList}" var="itr">
												<form:option value="${itr.strID}">${itr.strCardType}</form:option>
											</c:forEach>
										</form:select>
										<span class="error" id="strCardTypeError"></span>
									</div>
								</div>

								<div class="col-md-6 col-lg-4">
									<div class="form-group">
										<label class="form-label">Token Length <b>*</b></label>
										<form:select path="strLength" id="strLength"
											cssClass="form-control selectpicker">
											<form:option value="12">12 Digit</form:option>
											<form:option value="12">16 Digit</form:option>
										</form:select>
										<span class="error" id="strLengthError"><form:errors
												path="strLength"></form:errors></span>
									</div>
								</div>

								<div class="col-md-6 col-lg-6">
									<div class="form-group">
										<label class="form-label">Token On Demand <b>*</b></label>
										<form:select path="strDemandFlag" id="strDemandFlag"
											cssClass="form-control selectpicker">
											<form:option value="1">Yes</form:option>
											<form:option value="0">No</form:option>
										</form:select>
										<span class="error" id="strDemandFlagError"><form:errors
												path="strDemandFlag"></form:errors></span>
									</div>
								</div>

								<div class="col-md-6 col-lg-6">
									<div class="form-group">
										<form:hidden path="strDemondCronExpr" id="strDemondCronExpr"
											cssClass="form-control" readonly="true" />
										<span class="error"><form:errors
												path="strDemondCronExpr"></form:errors></span>
									</div>
								</div>

								<div class="col-md-6 col-lg-6">
									<div class="form-group">
										<input type="hidden" id="enDesc" name="enDesc"
											Class="form-control" readonly="readonly" /> <span
											class="error" id="enDesclabel"></span>
									</div>
								</div>

								<span style="margin-left: -47.5%; color: red;" id="cronError"></span>
								<div class="col-md-6 col-lg-12" id="schedulerForm">
									<div id="Schedule" class="c-schedule"></div>
								</div>

							</div>
						</div>
					</div>
					<div class="card-footer text-right">
						<div class=".d-flex1">
							<!-- <button type="submit" id="submitBtn"
										class="btn btn-primary ml-auto read">Generate Cron</button> -->
							<button type="button" id="submitBtn"
								class="btn btn-primary ml-auto">Submit</button>
							<button type="reset" class="btn btn-primary ml-auto"
								id="resetBtn">Clear</button>
						</div>
					</div>

					<input type="hidden" name="newData" id="newData" value="Y">

					<div class="card-body" style="width: 100%;" align="left">
						<table id="binTable"
							class="table table-striped table-bordered nowrap">
							<thead>
								<tr>
									<!-- <th>Select</th> -->
									<th>Card Type</th>
									<th>Length</th>
									<!-- <th>On Demand</th> -->
									<!-- <th>Cron Expression</th> -->
									<th>Description</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="itr" items="${cfgCardToken}">
									<tr>
										<td>${itr.strCardType}</td>
										<td>${itr.strLength}</td>
										<%-- <td>${itr.strDemandFlag == 1 ? 'Yes' : 'No'}</td> --%>
										<%-- <td>${itr.strDemondCronExpr}</td> --%>
										<td>${itr.enDesc}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<input type="hidden" value="${lefttokenMenuID}"
								id="lefttokenMenuID">
						
					</div>

					<!-- Modal -->
					<div class="modal fade" id="myModal" role="dialog">
						<div class="modal-dialog modal-lg">
							<div class="modal-content">
								<div class="modal-header">
									<h4 class="modal-title">Are you sure to submit</h4>
								</div>
								<div class="modal-body">

									<form>
										<div class="form-group" id="pwdText">
											<label for="recipient-name" class="col-form-label">Password:</label>
											<input type="password" name="senPwd" id="senPwd"
												class="form-control">
										</div>
										<span id="confirmDesc"></span>
									</form>
								</div>


								<div class="modal-footer">
									<button type="button" class="btn btn-primary" id="pwdSubmitBtn">Submit</button>
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

<script type="text/javascript">
	var cardToken = {};
	var globalData;
	
	$(document)
			.ready(
					function() {
						var $s = $('#Schedule');
						$s.jsCronUI({});
						function updateDebug() {
							try {
								$('#cronError').text('');
								$('#strDemondCronExpr').val(
										$s.jsCronUI('getCron', true));
								$('#enDesc')
										.val($s.jsCronUI('toEnglishString'));
							} catch (e) {
								$('#cronError').text(e.message);
								$('#cron').text('');
								$('#description').text('');
								return false;
							}
							return true;
						}

						$('.read').on('click', function() {
							updateDebug();
							return false;
						});

					

						$('#strDemandFlag').change(function() {
							if ($(this).val() == 1) {
								$('#schedulerForm').hide();
							} else {
								$('#schedulerForm').show();
							}
						});

						showHideDiv($('#type').val());

						$('#type').change(function() {
							showHideDiv($(this).val());
						});

						$('#pwdSubmitBtn').click(function() {
							if ($('#type').val() == "C") {
								pwdApiCall();
							} else {
								tokenApiCall();
							}

							$('#myModal').modal('hide');
						})

						$('#submitBtn')
								.click(
										function() {
											var flag = false;
											if ($('#type').val() == "C") {
												$('#pwdText').show();
												if ($('#cardNo').val() == null
														|| $('#cardNo').val() == '') {
													errorShow('cardNoError');
												} else {
													errorHide('cardNoError');
													flag = true;
												}
											} else {
												$('#pwdText').hide();
												$('#cardNo').val('');
												if ($('#strCardType').val() == null
														|| $('#strCardType')
																.val() == '') {
													errorShow('strCardTypeError');
												} else {
													errorHide('strCardTypeError');
													flag = true;
												}

												if ($('#strDemandFlag').val() == '0') {
													flag = updateDebug();
												}
											}

											if (flag) {
												cardToken.strCardType = $(
														'#strCardType').val();
												cardToken.strLength = $(
														'#strLength').val();
												cardToken.strDemandFlag = $(
														'#strDemandFlag').val();
												cardToken.strDemondCronExpr = $(
														'#strDemondCronExpr')
														.val();
												cardToken.enDesc = $('#enDesc')
														.val();
												cardToken.newData = $(
														'#newData').val();
												cardToken.cardNo = $('#cardNo')
														.val();
												$('#confirmDesc').text(
														$('#enDesc').val());
												$('#myModal').modal('show');
											}
											return false;
										});

					});
	
	
	$('#binTable').DataTable({
		responsive : true
	});
	
	
	function errorShow(id) {
		$('#' + id).text('Required');
	}

	function errorHide(id) {
		$('#' + id).text('');
	}

	function pwdApiCall() {
		var pwd = $('#senPwd').val();
		jsonObj = [];
		$('#pwdError').text("");
		item = {}
		item["userID"] = $
		{
			user.userid
		}
		;
		item["password"] = pwd;
		item["tokenCard"] = $('#cardNo').val();
		jsonObj.push(item);

		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "${pageContext.request.contextPath}/getCardNo",
			data : JSON.stringify(jsonObj).replace("[", "").replace("]", ""),
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				console.log("SUCCESS: ", data.responseDesc);
				if (data.responseCode == "00") {
					//$('#pwdError').removeClass("tag tag-red");
					//$('#pwdError').text("Card Number : "+data.cardNo);
					tokenApiCall();
				} else {
					$('#strError').addClass("tag tag-red");
					$('#strError').text(data.responseDesc);
				}
			},
			error : function(e) {
				console.log("ERROR: ", e);
				$('#strError').addClass("tag tag-red");
				$('#strError').text("Error");
			},
			done : function(e) {
				console.log("DONE");
			}
		});
	}

	function tokenApiCall() {
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "${pageContext.request.contextPath}/addCardTokenConfig",
			data : JSON.stringify(cardToken),
			dataType : 'json',
			contentType : "application/json",
			timeout : 100000,
			success : function(data) {
				console.log("SUCCESS: ", data);
				if (data.outResponseCode == "00") {
					$('#strError').addClass("tag tag-green");
					$('#strError').text(data.message);
					setTimeout(function() {// wait for 5 secs(2)
						var obj = {
							Title : '',
							Url : 'addCardTokenConfigForm'
						};
						history.pushState(obj, obj.Title, obj.Url);
						location.reload(); // then reload the page.(3)
					}, 2000);
				} else {
					$('#strError').addClass("tag tag-red");
					$('#strError').text(data.message);
				}
			},
			error : function(e) {
				console.log("ERROR: ", e);
				$('#strError').addClass("tag tag-red");
				$('#strError').text("Error");
			},
			done : function(e) {
				console.log("DONE");
			}
		});
	}

	function showHideDiv(value) {
		if (value == 'C') {
			$('#cardTypeDiv').hide();
			$('#cardDiv').show();
		} else {
			$('#cardTypeDiv').show();
			$('#cardDiv').hide();
		}
	}
</script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/multiple-select/1.2.0/multiple-select.css"
	integrity="sha384-FEJDgmakB48QSUodBJUzvYF3ZerlNUtPYWLmI8/+in2t80RkFJlySlzBi6PtKRgE"
	crossorigin="anonymous">
<script
	src="${pageContext.request.contextPath}/resources/assets/js/cron/jsCronUI.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/assets/js/cron/jsCronUI-template.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/multiple-select/1.2.0/multiple-select.js"
	integrity="sha384-DOF7n50GdJ8//TemY651wKD1ZkF8xCj2UXZJnx46mOxrkezVi4CyCZJzL7Q4nNPE"
	crossorigin="anonymous"></script>
