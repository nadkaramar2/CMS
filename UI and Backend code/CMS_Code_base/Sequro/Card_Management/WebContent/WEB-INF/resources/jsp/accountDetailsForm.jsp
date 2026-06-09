<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<script type="text/javascript">
	
	var data;
	
	$(document).ready(function() {
		$('#accountDetailsTable').DataTable({
			 scrollX: 100,
			 scroller: true
		});
		
		$(".checkbox").change(function() {
			
			var data = $(this).val().split(/\|/);
			jsonObj = [];
			item = {}
	        item ["strParticipantID"] = $('#inPart').val();
	        item ["strFunctionType"] = "UPDPRIMARY";
	        item ["strCardNumber"] = data[2];
	        item ["strCardSeqNumber"] = data[3];
	        item ["strAccountNumber"] = data[0];
	        item ["strAccountType"] = data[1];
	        item ["strCurrencyCode"] = "";
	        item ["strAccountBranch"] = "";

		    if(this.checked) {
		    	item ["strPrimaryFlag"] = "Y";
		    }else {
		    	item ["strPrimaryFlag"] = "N";
		    }
		    
		    jsonObj.push(item);
		    
		    $.ajax({
				type : "POST",
				contentType : "application/json",
				url : "${pageContext.request.contextPath}/updateAccountFlag",
				data: JSON.stringify(jsonObj).replace("[","").replace("]",""),
				dataType : 'json',
				timeout : 100000,
				success : function(data) {
					console.log("SUCCESS: ", data);
					if(data.outResponseCode == "00"){
						$('#strError').addClass("tag tag-green");
						$('#strError').text(data.message);
						setTimeout(function(){// wait for 5 secs(2)
							var obj = { Title: '', Url: 'accountDetailsForm' };
					        history.pushState(obj, obj.Title, obj.Url);
					           location.reload(); // then reload the page.(3)
					      }, 2000); 
					}else {
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
		    
		});
		
		$('#deleteBtn').click(function() {
			jsonObj = [];
			item = {}
	        item ["strParticipantID"] = $('#inPart').val();
	        item ["strFunctionType"] = "DELINK";
	        item ["strCardNumber"] = data[2];
	        item ["strCardSeqNumber"] = data[3];
	        item ["strAccountNumber"] = data[0];
	        item ["strAccountType"] = data[1];
	        item ["strCurrencyCode"] = "";
	        item ["strAccountBranch"] = "";
			item ["strPrimaryFlag"] = data[4];
		    
		    jsonObj.push(item);
		    
		    $.ajax({
				type : "POST",
				contentType : "application/json",
				url : "${pageContext.request.contextPath}/updateAccountFlag",
				data: JSON.stringify(jsonObj).replace("[","").replace("]",""),
				dataType : 'json',
				timeout : 100000,
				success : function(data) {
					console.log("SUCCESS: ", data);
					if(data.outResponseCode == "00"){
						$('#strError').addClass("tag tag-green");
						$('#strError').text(data.message);
						$('#myModal').modal('hide');
						setTimeout(function(){// wait for 5 secs(2)
							var obj = { Title: '', Url: 'accountDetailsForm' };
					        history.pushState(obj, obj.Title, obj.Url);
					           location.reload(); // then reload the page.(3)
					      }, 2000); 
					}else {
						$('#myModal').modal('hide');
						$('#strError').addClass("tag tag-red");
						$('#strError').text(data.message);
					}
				},
				error : function(e) {
					console.log("ERROR: ", e);
					$('#strError').addClass("tag tag-red");
					$('#strError').text("Error");
					$('#myModal').modal('hide');
				},
				done : function(e) {
					console.log("DONE");
				}
			});
		});

	});
	
	function addFunction(data) {
		var cardData = data.split(/\|/);

	    var form = document.createElement("form");
	    form.setAttribute("method", "post");
	    form.setAttribute("action", "${pageContext.request.contextPath}/addAccountForm");

	    
	            var hiddenField = document.createElement("input");
	            hiddenField.setAttribute("type", "hidden");
	            hiddenField.setAttribute("name", "strCardNumber");
	            hiddenField.setAttribute("value", cardData[0]);
	            
	            var hiddenField1 = document.createElement("input");
	            hiddenField1.setAttribute("type", "hidden");
	            hiddenField1.setAttribute("name", "strCardSeqNumber");
	            hiddenField1.setAttribute("value", cardData[1]);
	            
	            var hiddenField2 = document.createElement("input");
	            hiddenField2.setAttribute("type", "hidden");
	            hiddenField2.setAttribute("name", "strPrimaryFlag");
	            hiddenField2.setAttribute("value", "Y");
	            
	            var hiddenField3 = document.createElement("input");
	            hiddenField3.setAttribute("type", "hidden");
	            hiddenField3.setAttribute("name", "strCardMask");
	            hiddenField3.setAttribute("value", cardData[2]);

	            form.appendChild(hiddenField);
	            form.appendChild(hiddenField1);
	            form.appendChild(hiddenField2);
	            form.appendChild(hiddenField3);
	            
	   		 	document.body.appendChild(form);
	    		form.submit();
	}
	
	function deleteFunction(accountData) {
		data = accountData.split(/\|/);
		$('#myModal').modal('show');
	}
	
</script>
<form action="emailUpdate" method="post" name="addService"
	id="addServiceForm">
	<div class="my-3 my-md-5">
		<div class="container">
			<div class="row">
				<div class="col-md-3">
					<jsp:include page="customerServiceLeftMenu.jsp"></jsp:include>
				</div>
				<div class="col-md-9">
					<div class="card">
						<div class="card-header">
							<h3 class="card-title">Account Details <span class="text-right badge badge-danger" id="strError"></span></h3>
						</div>
						<div class="card-body">

							<table id="accountDetailsTable"
								class="table table-striped table-bordered nowrap" style="width: 100%">
								<thead>
									<tr>
										<th>Account Number</th>
										<th>Card Number</th>
										<th>Account Type</th>
										<th>Branch</th>
										<th>Primary Flag</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="itr" items="${accountDetails}">
										<tr>
											<td>${itr.strAccountNumber}</td>
											<td>${itr.strMaskCard}</td>
											<td>${itr.strAccountType}</td>
											<td>${itr.strAccountBranch}</td>
											<td><%-- ${itr.strPrimaryFlag == 'Y' ? 'Yes' : 'No'} --%>
												<c:choose>
													<c:when test="${itr.strPrimaryFlag == 'Y'}">
														
														<label class="custom-switch">
														&nbsp;&nbsp; <input type="checkbox"
														name="custom-switch-checkbox" id="pinFlag" checked="checked" value="${itr.strAccountNumber}|${itr.strAccountType}|${itr.strCardNumber}|${itr.strCardSeqNumber}|${itr.strPrimaryFlag}"
														class="custom-switch-input checkbox"> <span
														class="custom-switch-indicator"></span>
														</label>
														
													</c:when>
													
													<c:otherwise>
														
														<label class="custom-switch">
														&nbsp;&nbsp; <input type="checkbox"
														name="custom-switch-checkbox" id="pinFlag" value="${itr.strAccountNumber}|${itr.strAccountType}|${itr.strCardNumber}|${itr.strCardSeqNumber}|${itr.strPrimaryFlag}"
														class="custom-switch-input checkbox"> <span
														class="custom-switch-indicator"></span>
														</label>
														
													</c:otherwise>
												</c:choose>
												
											</td>
											<td>
											
											<a class="icon" href="javascript:addFunction('${itr.strCardNumber}|${itr.strCardSeqNumber}|${itr.strMaskCard}')">
                              					<i class="fe fe-plus"></i>
                            				</a>
                            				
                            				&nbsp;&nbsp;&nbsp;&nbsp;
                            				
											<a class="icon" href="javascript:deleteFunction('${itr.strAccountNumber}|${itr.strAccountType}|${itr.strCardNumber}|${itr.strCardSeqNumber}|${itr.strPrimaryFlag}')">
                              					<i class="fe fe-trash"></i>
                            				</a>
                            				
                            				
								<%-- <button type="submit" id="submitBtn" name="id"
								value="${itr.strAccountNumber}|${itr.strAccountType}|${itr.strCardNumber}|${itr.strCardSeqNumber}|${itr.strPrimaryFlag}" class="btn btn-icon btn-primary btn-danger"><i class="fe fe-trash"></i></button> --%>
                          		</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>

						</div>
						<input type="hidden" value="${inPart}" id="inPart">
						<!-- <div class="card-footer text-right">
							<div class=".d-flex1">
								<button type="submit" id="submitBtn" name="strRequestBtn"
									value="1" class="btn btn-primary ml-auto">Submit</button>
							</div>
						</div> -->
						<input type="hidden" value="${leftCustomerMenuID}"
							id="leftCustomerMenuID">
					</div>
					
					<!-- Modal -->
					<div class="modal fade" id="myModal" role="dialog">
						<div class="modal-dialog modal-sm">
							<div class="modal-content">
								<div class="modal-header">
									<p class="text-capitalize">Delete Account <label id="delBinMsg"></label></p>
								</div> 
								<div class="modal-body">

									<form>
										<p class="leading-loose">Are you sure you want to delete this Account ?</p>
									</form>
								</div>

								
							<div class="modal-footer">
								 <button type="button" class="btn btn btn-red" id="deleteBtn" >Delete</button>
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Close</button>
							</div>
						</div>
					</div>
			</div>
			<!-- Modal -->
			
			
				</div>
			</div>
		</div>
	</div>
</form>