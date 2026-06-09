<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	
	var deleteBinID;
	var branchData;

$(document).ready(function() {
	
	$('#strSelectID').val("N");
	
	$('#delBinMsg').text("");
	
	$('#branchCodeTable').DataTable({
		 responsive: true
	});
	
	$('#deleteBtn').click(function() {
		
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "${pageContext.request.contextPath}/deleteBranchCode",
			data: "id="+deleteBinID,
			dataType : 'json',
			contentType : "application/x-www-form-urlencoded",
			timeout : 100000,
			success : function(data) {
				console.log("SUCCESS: ", data);
				if(data.outResponseCode == "00"){
					$('#delBinMsg').addClass("tag tag-green");
					$('#delBinMsg').text(data.message);
					setTimeout(function(){// wait for 5 secs(2)
						var obj = { Title: '', Url: 'branchCodeConfigForm' };
				        history.pushState(obj, obj.Title, obj.Url);
				           location.reload(); // then reload the page.(3)
				      }, 2000); 
				}else {
					$('#delBinMsg').addClass("tag tag-red");
					$('#delBinMsg').text(data.message);
				} 
			},
			error : function(e) {
				console.log("ERROR: ", e);
				$('#delBinMsg').addClass("tag tag-red");
				$('#delBinMsg').text("Error");
			},
			done : function(e) {
				console.log("DONE");
			}
		});
		
		return false;
	})
	
	
		$.validator.addMethod(
			"regex",
			function(value, element, regexp) {
    		var check = false;
    		return this.optional(element) || regexp.test(value);
		},
		"ss"
	);
	
	var value = $("#branchCodeConfig").validate({
		rules: {
			strBranchCode: {
                    required: true,
                    regex: /^[0-9]+$/,
                    minlength: 3,
                    maxlength: 5
            },
            strBranchAddress: {
                required: true,
                regex: /^[a-zA-Z0-9\s.\-]+$/,
                minlength: 4,
                maxlength: 50         
            },
            strBranchType: {
                required: true,
            },
            strExtID: {
                required: true,
                regex: /^[0-9]+$/,
                minlength: 1,
                maxlength: 3         
            }
		},
    	messages: {
    		strBranchCode: {
                    required: "Please Enter Branch Code!",
                    minlength: "Minmum {0} characters required!",
                    maxlength: "Maximum {0} characters allowed!",
                    regex: "only numeric allow !"
            },
            strBranchAddress: {
                required: "Please Enter Branch Address!",
                minlength: "Minmum {0} characters required!",
                maxlength: "Maximum {0} characters allowed!",
                regex: "only alphanumeric allow !"
            },
            strBranchType: {
                required: "Required",
            },
            strExtID: {
                required: "Please Enter Ext ID!",
                minlength: "Minmum {0} characters required!",
                maxlength: "Maximum {0} characters allowed!",
                regex: "only numeric allow !"
            }
    	},
		submitHandler: function(form) {
			
			if($('#strSelectID').val() == "N") {
			
				var data = eval('${branchCode}');
				var flag = false;
				var binVal = form.strBranchCode.value;
				
				$.each(data, function( index, value ) {
					if(binVal == JSON.stringify(value.strBranchCode).replace("\"","").replace("\"","")) {
						flag = true;
						return false;
					}
				});
					if(flag) {
						$('#strError').text("Duplicate Branch Code");
						$('#errorMsg').hide();
					}else {
						$('#strError').text("");
						$('#errorMsg').hide();
						form.submit();	
					}
			}else {
				branchData.strBranchDesc = form.strBranchAddress.value; 
				branchData.strBranchType = form.strBranchType.value;    
				branchData.strExtID = form.strExtID.value;
				$.ajax({
					type : "POST",
					contentType : "application/json",
					url : "${pageContext.request.contextPath}/updateBranchCode",
					data: JSON.stringify(branchData),
					dataType : 'json',
					contentType : "application/json",
					timeout : 100000,
					success : function(data) {
						console.log("SUCCESS: ", data);
						if(data.outResponseCode == "00"){
							$('#strError').addClass("tag tag-green");
							$('#strError').text(data.message);
							setTimeout(function(){// wait for 5 secs(2)
								var obj = { Title: '', Url: 'branchCodeConfigForm' };
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
			}		
		}
	});
	
	
	
});

function editFunction(bin) {     
	
    	var data = eval('${branchCode}');
    
    	$.each(data, function( index, value ) {
			if(bin == JSON.stringify(value.strBranchCode).replace("\"","").replace("\"","")) {
				branchData = value;
				return false;
			}
		});
    	
    	$('#strBranchCode').val(branchData.strBranchCode);
    	$('#strBranchCode').attr('readonly', true);
    	$('#strBranchAddress').val(branchData.strBranchAddress);
    	$('#strBranchType').val(branchData.strBranchType);
    	$('#strExtID').val(branchData.strExtID);
    	$('#strSelectID').val("Y");
    	
  }

function deleteFunction(id) {
	$('#delBinMsg').text("");
	$('#strBranchCode').val("");
	$('#strBranchAddress').val("");
	$('#strExtID').val("");
	deleteBinID = id;
	$('#myModal').modal('show');
  }
  
</script>

<div class="my-3 my-md-5">
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<jsp:include page="branchConfigLeftMenu.jsp"></jsp:include>
			</div>
			<div class="col-md-9">
				<form:form action="branchCodeConfig" method="post"
					commandName="branchCodeModel" name="branchCodeConfig" id="branchCodeConfig"
					cssClass="card">

					<div class="card-header">
						<h3 class="card-title">
							<i class="fe fe-user-plus"></i> &nbsp;&nbsp;Branch Code Configuration <span class="text-right badge badge-danger" id="strError"></span>
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
									<label class="form-label">Branch Code <b>*</b></label>
									<form:input path="strBranchCode" id="strBranchCode"
										cssClass="form-control" />
									<span class="error" id="strBranchCodeError"><form:errors
											path="strBranchCode"></form:errors></span>
								</div>
							</div>

							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Branch Address <b>*</b></label>
									<form:input path="strBranchAddress" id="strBranchAddress"
										cssClass="form-control" />
									<span class="error" id="strBranchAddressError"><form:errors
											path="strBranchAddress"></form:errors></span>
								</div>
							</div>

							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Branch Type <b>*</b></label>
									<form:select path="strBranchType" id="strBranchType"
										cssClass="form-control selectpicker">
										<form:option value=""></form:option>
										<c:forEach items="${branchList}" var="itr">
											<form:option value="${itr.strID}">${itr.strBranchName}</form:option>
										</c:forEach>
									</form:select>
									<span class="error" id="strBranchTypeError"><form:errors
											path="strBranchType"></form:errors></span>
								</div>
							</div>
							
							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Ext ID <b>*</b></label>
									<form:input path="strExtID" id="strExtID"
										cssClass="form-control" />
									<span class="error" id="strExtIDError"><form:errors
											path="strExtID"></form:errors></span>
								</div>
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
						<table id="branchCodeTable"
							class="table table-striped table-bordered nowrap"
							style="width: 100%">
							<thead>
								<tr>
									<!-- <th>Select</th> -->
									<th>Branch Code</th>
									<th>Address</th>
									<th>Branch Type</th>
									<th>Ext ID</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="itr" items="${branchCodeList}">
									<tr>
										<%-- <td><label class="custom-control custom-radio"> <form:radiobutton
													path="strSelectID"
													cssClass="custom-control-input approveID"
													value="${itr.strID}" id="strSelectID" />
												<div class="custom-control-label"></div>
										</label></td> --%>
										<td>${itr.strBranchCode}</td>
										<td>${itr.strBranchAddress}</td>
										<td>${itr.strBranchType}</td>
										<td>${itr.strExtID}</td>
										<td><a class="icon" href="javascript:editFunction('${itr.strBranchCode}')">
												<i class="fe fe-edit"></i>
										</a> &nbsp;&nbsp;&nbsp; <a class="icon"
											href="javascript:deleteFunction(${itr.strID})"> <i
												class="fe fe-trash"></i>
										</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<input type="hidden" value="${leftbranchMenuID}"
								id="leftbranchMenuID">
					</div>
					<!-- Modal -->
					<div class="modal fade" id="myModal" role="dialog">
						<div class="modal-dialog modal-sm">
							<div class="modal-content">
								<div class="modal-header">
									<p class="text-capitalize">Delete Branch Code <label id="delBinMsg"></label></p>
								</div> 
								<div class="modal-body">

									<form>
										<p class="leading-loose">Are you sure you want to delete this Branch Code ?</p>
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
				<form:hidden path="strSelectID" id="strSelectID"/>
				</form:form>
			</div>
		</div>
	</div>
</div>
