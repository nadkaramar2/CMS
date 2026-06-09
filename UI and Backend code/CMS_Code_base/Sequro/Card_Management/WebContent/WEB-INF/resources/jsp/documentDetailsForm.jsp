<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">
	
	jsonObj = [];
	item = {}
	var type;
	
	$(document).ready(function() {
		$('#cardDetailsTable').DataTable();
		
		$('#docSubmitBtn').click(function() {
			item ["strDocumentNumber"] = $('#documentNo').val();
			
			if(type == null) {
	        	item ["strDocumentType"] = $('#docType').val();
			}else {
				item ["strDocumentType"] = type;
			}
			
	        jsonObj.push(item);
	        
			$.ajax({
				type : "POST",
				contentType : "application/json",
				url : "${pageContext.request.contextPath}/addDocument",
				data: JSON.stringify(jsonObj).replace("[","").replace("]",""),
				dataType : 'json',
				timeout : 100000,
				success : function(data) {
					console.log("SUCCESS: ", data);
					if(data.outResponseCode == "00"){
						$('#strError').addClass("tag tag-green");
						$('#strError').text(data.message);
						setTimeout(function(){// wait for 5 secs(2)
							var obj = { Title: '', Url: 'documentDetailsForm' };
					        history.pushState(obj, obj.Title, obj.Url);
					           location.reload(); // then reload the page.(3)
					      }, 2000); 
						$('#myModal').modal('hide');
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
	
	function addFunction() {
		
		item.lenth=0;
		
		$('#docText').text('Add Document');
		$('#myModal').modal('show');
		
		$('#documentNo').val("");
		$('#docType').val("");
		
        item ["strCIFKey"] = "";
        item ["strCitizenID"] = "";
        item ["strFunction"] = "ADD";
        item ["strParticipantID"] = $('#partID').val();
        item ["strCustomerID"] = $('#custID').val();
        item ["strDocumentID"] = ""; 
	    
	}
	
	function editFunction(data) {
		item.lenth=0;
		var accData = data.split(/\|/);
		
		$('#docText').text('Edit Document');
		$('#myModal').modal('show');
		$('#docType').val(accData[1]);
		$('#docType option:not(:selected)').attr('disabled', true);
		$('#documentNo').val(accData[0]);
		
		
        item ["strCIFKey"] = "";
        item ["strCitizenID"] = "";
        item ["strFunction"] = "UPDATE";
        item ["strParticipantID"] = $('#partID').val();
        item ["strCustomerID"] = $('#custID').val();
        item ["strDocumentID"] = ""; 
	    
	}
	
	function deleteFunction(id) {
		var data = id.split(/\|/);
		
		item.lenth=0;
		$('#docText').text('Delete Document');
		$('#myModal').modal('show');
		$('#docBody').hide();
		$('.modal-body').text("Are you sure you want to delete this Document ?");
        item ["strCIFKey"] = "";
        item ["strCitizenID"] = "";
        item ["strFunction"] = "DELETE";
        item ["strParticipantID"] = $('#partID').val();
        item ["strCustomerID"] = $('#custID').val();
        item ["strDocumentID"] = data[0];
        type = data[1];
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
							<h3 class="card-title">Document Details <span class="text-right badge badge-danger" id="strError"></span></h3>
							<h2 id="errorMsg" class="tag tag-red"></h2>
						</div>
						<div class="card-body">

							<table id="cardDetailsTable"
								class="table table-striped table-bordered" style="width: 100%">
								<thead>
									<tr>
										<th>Document Number</th>
										<th>Document Type</th>
										<th></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="itr" items="${documentDetails}">
										<tr>
											<td>${itr.strDocumentNumber}</td>
											<td>${itr.strDocumentTypeDesc}</td>
											<td>
												<!-- <a class="icon" href="javascript:addFunction()">
                              					<i class="fe fe-plus"></i>
                            				</a>
                            				
                            				&nbsp;&nbsp;&nbsp;&nbsp; -->
                            				
											<a class="icon" href="javascript:editFunction('${itr.strDocumentNumber}|${itr.strDocumentType}|${itr.strDocumentID}')">
                              					<i class="fe fe-edit"></i>
                            				</a>
                            				
                            				&nbsp;&nbsp;&nbsp;&nbsp;
                            				
											<a class="icon" href="javascript:deleteFunction('${itr.strDocumentID}|${itr.strDocumentType}')">
                              					<i class="fe fe-trash"></i>
                            				</a>
                            				</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>

						</div>
						<div class="card-footer text-right">
							<div class="btn-list text-right">
								<button type="button" id="addBtn" name="addBtn"
									value="0" class="btn btn-secondary" onclick="addFunction();">Add</button>
								<button type="submit" id="submitBtn" name="strRequestBtn"
									value="1" class="btn btn-primary">Submit</button>
							</div>
						</div>
					</div>
					<input type="hidden" value="${leftCustomerMenuID}"
						id="leftCustomerMenuID">
					
					<input type="hidden" value="${customerID}" id="custID">
					
					<input type="hidden" value="${partID}" id="partID">
					
					
					<!-- Modal -->
					<div class="modal fade" id="myModal" role="dialog">
						<div class="modal-dialog modal-lg">
							<div class="modal-content">
								<div class="modal-header">
									<h4 class="modal-title" id="docText"></h4>
								</div>
								<div class="modal-body" >
									<div id="docBody">
									<form name="docForm" id="docForm">
										<div class="form-group">
											<label for="recipient-name" class="col-form-label">Document Number:</label>
											<input type="text" name="documentNo" id="documentNo" class="form-control">
										</div>
										<div class="form-group">
											<span id="pwdError"></span>
										</div>
										
										<div class="form-group">
											<label for="recipient-name" class="col-form-label">Document Type:</label>
											<select name="docType" id="docType" class="form-control selectpicker">
												<c:forEach items="${documentType}" var="itr">
													<option value="${itr.lkpkey}">${itr.lkpvalue}</option>
												</c:forEach>
											</select>
										</div>
										<div class="form-group">
											<span id="pwdError"></span>
										</div>
										
									</form>
								</div>
								</div>
								
							<div class="modal-footer">
								 <button type="button" class="btn btn-primary" id="docSubmitBtn" >Submit</button>
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