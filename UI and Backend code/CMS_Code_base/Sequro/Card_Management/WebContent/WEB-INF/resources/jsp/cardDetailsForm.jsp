<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<style>
       #senPwd{
           -webkit-text-security:disc;
       }
</style>

<script type="text/javascript">
	var globalData;
	$(document).ready(function() {
		$('#cardDetailsTable').DataTable();
		$("#submitBtn").click(function() {
			if ($('input[type=radio][name=strCardNumber]:checked').length == 0) {
				$('#errorMsg').text("Please select atleast one");
					return false;
			} else {
					return true;
				}	
			});
		
			$('#pwdSubmitBtn').click(function() {
				var array = globalData.toString().split("|");
				var pwd = $('#senPwd').val();
				jsonObj = [];
				$('#pwdError').text("");
				item = {}
		        item ["userID"] = array[0];
		        item ["password"] = pwd;
		        item ["tokenCard"] = array[1];
		        jsonObj.push(item);
		        
				$.ajax({
					type : "POST",
					contentType : "application/json",
					url : "${pageContext.request.contextPath}/getCardNo",
					data: JSON.stringify(jsonObj).replace("[","").replace("]",""),
					dataType : 'json',
					timeout : 100000,
					success : function(data) {
						console.log("SUCCESS: ", data.responseDesc);
						if(data.responseCode == "00")
						{
							var responseData;
							$('#pwdError').removeClass("tag tag-red");
							if(data.tokenCard == undefined || data.tokenCard == null || data.tokenCard == '') {
								responseData = "<p>Card Number : "+ data.cardNo + " </p>" ;
						}
							else 
							{
								responseData = "<p>Card Number : "+ data.cardNo + " </p> <br> <p>Token Card  : " + data.tokenCard + " </p> <br> <p> Created Date : " + data.createdDate + " </p>" ;
							}
							$('#pwdError').html(responseData);
						}else {
							$('#pwdError').addClass("tag tag-red");
							$('#pwdError').text(data.responseDesc);
						}
					},
					error : function(e) {
						console.log("ERROR: ", e);
						$('#pwdError').addClass("tag tag-red");
						$('#pwdError').text("Error");
					},
					done : function(e) {
						console.log("DONE");
					}
				});
				
				return false;
			})
			
		});

	function showCardNOFunction(data) {
		globalData = data;
		$('#senPwd').val("");
		$('#pwdError').text("");
	}
</script>

<div class="my-3 my-md-5">
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<jsp:include page="customerServiceLeftMenu.jsp"></jsp:include>
			</div>
			<div class="col-md-9">
				<form:form action="cardDetails" method="post" commandName="cardList"
					name="cardDetails" id="cardDetails">
					<div class="card">
						<div class="card-header">
							<h3 class="card-title">Card Details</h3>
							<h2 id="errorMsg" class="tag tag-red"></h2>
						</div>
						<div class="card-body">

							<table id="cardDetailsTable"
								class="table table-striped table-bordered" style="width: 100%">
								<thead>
									<tr>
										<th>Select</th>
										<th>Card Type</th>
										<th>Card Number</th>
										<th>Emboss Line</th>
										<th>Card Status</th>
										<th>Issue Date</th>
										<th>Expiry Date (yy-MM)</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="itr" items="${cardDetails}">
										<tr>
											<td><label class="custom-control custom-radio">
													<input type="radio" name="strCardNumber"
													class="custom-control-input approveID"
													value="${itr.strCardNumber}" id="approveID">
													<div class="custom-control-label"></div>
											</label></td>
											<td>${itr.strCardType}</td>
											<c:choose>
												<c:when test="${user.sensitive_date == '1'}">
													<td data-toggle="modal" data-target="#myModal"><a
														href="javascript:showCardNOFunction('${user.userid}|${itr.strCardNumber}')">
															${itr.strMaskCardNumber} </a></td>
												</c:when>
												<c:otherwise>
													<td>${itr.strCardNumber}</td>
												</c:otherwise>
											</c:choose>
											<td>${itr.strEmbossLine1}</td>
											<td>${itr.strCardStatus.replaceFirst("^0+(?!$)", "") == 1 ? 'Active' : 'Deactive' }</td>
											<td>${itr.strCardIssueDate}</td>
											<td><fmt:parseDate value="${itr.strExpiryDate}"
													var="expDate" pattern="yyyy-MM-dd" /> <fmt:formatDate
													pattern="yy/MM" value="${expDate}" /></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>

						</div>
						<div class="card-footer text-right">
							<div class="btn-list text-right">
								<button type="submit" id="submitBtn" name="strRequestBtn"
									value="1" class="btn btn-primary">Submit</button>
							</div>
						</div>
					</div>
					<input type="hidden" value="${leftCustomerMenuID}"
						id="leftCustomerMenuID">

					<!-- Modal -->
					<div class="modal fade" id="myModal" role="dialog">
						<div class="modal-dialog modal-lg">
							<div class="modal-content">
								<div class="modal-header">
									<h4 class="modal-title">Show Clear Card Number</h4>
								</div>
								<div class="modal-body">

									<form>
										<div class="form-group">
											<label for="recipient-name" class="col-form-label">Password:</label>
											<input type="password" name="senPwd" id="senPwd" class="form-control">
										</div>
										<div class="form-group">
											<span id="pwdError"></span>
										</div>
									</form>
								</div>

								
							<div class="modal-footer">
								 <button type="button" class="btn btn-primary" id="pwdSubmitBtn" >Submit</button>
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