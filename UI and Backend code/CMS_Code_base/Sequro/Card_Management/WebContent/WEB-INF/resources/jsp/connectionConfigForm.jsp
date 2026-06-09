<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	var deleteBinID;
	var branchData;

$(document).ready(function() {
	
	$('#strSelectID').val("N");
	
	$('#delBinMsg').text("");
	
	$('#branchTable').DataTable({
		 responsive: true
	});
	
	$('#serviceType').change(function() {
		if(this.value === "server") {
			$('#ipDiv').hide();
		}else {
			$('#ipDiv').show();
		}
	})
	
	$('#deleteBtn').click(function() {
		
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "${pageContext.request.contextPath}/deleteBranch",
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
						var obj = { Title: '', Url: 'branchConfigForm' };
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
	
	var value = $("#connConfigForm").validate({
		rules: {
			name: {
                    required: true,
                    regex: /^[a-zA-Z0-9]+$/,
                    minlength: 2,
                    maxlength: 15
            },
            port: {
                required: true,
                regex: /^[0-9]+$/,
                minlength: 4,
                maxlength: 6         
            }
		},
    	messages: {
    		name: {
                    required: "Please Enter Connection Name!",
                    minlength: "Minmum {0} characters required!",
                    maxlength: "Maximum {0} characters allowed!",
                    regex: "only alphanumeric allow !"
            },
            port: {
                required: "Please Enter Port!",
                minlength: "Minmum {0} characters required!",
                maxlength: "Maximum {0} characters allowed!",
                regex: "only alphanumeric allow !"
            }
    	},
		submitHandler: function(form) {
			
			if($('#strSelectID').val() == "N") {
			
				var data = eval('${branch}');
				var flag = false;
				var binVal = form.strBranchName.value;
				
				$.each(data, function( index, value ) {
					if(binVal == JSON.stringify(value.strBranchName).replace("\"","").replace("\"","")) {
						flag = true;
						return false;
					}
				});
					if(flag) {
						$('#strError').text("Duplicate Branch");
						$('#errorMsg').hide();
					}else {
						$('#strError').text("");
						$('#errorMsg').hide();
						form.submit();	
					}
			}else {
				branchData.strBranchDesc = form.strBranchDesc.value;
				$.ajax({
					type : "POST",
					contentType : "application/json",
					url : "${pageContext.request.contextPath}/updateBranch",
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
								var obj = { Title: '', Url: 'branchConfigForm' };
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
	
    	var data = eval('${branch}');
    
    	$.each(data, function( index, value ) {
			if(bin == JSON.stringify(value.strBranchName).replace("\"","").replace("\"","")) {
				branchData = value;
				return false;
			}
		});
    	
    	$('#strBranchName').val(branchData.strBranchName);
    	$('#strBranchName').attr('readonly', true);
    	$('#strBranchDesc').val(branchData.strBranchDesc);
    	$('#strSelectID').val("Y");
    	
  }

function deleteFunction(id) {
	$('#delBinMsg').text("");
	$('#strBranchName').val("");
	$('#strBranchDesc').val("");
	deleteBinID = id;
	$('#myModal').modal('show');
  }
</script>

<div class="my-3 my-md-5">
	<div class="container">
		<div class="row">
		<div class="col-md-3">
				<jsp:include page="participantConfigLeftMenu.jsp"></jsp:include>
			</div>
			<div class="col-md-9">
				<form:form action="addConnConfig" method="post" commandName="connConfigModel"
					name="connConfigForm" id="connConfigForm" cssClass="card">
					
					<div class="card-header">
						<h3 class="card-title"><i class="fe fe-user-plus"></i> &nbsp;&nbsp;Connection Configuration <span class="text-right badge badge-danger" id="strError"></span></h3>
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
					<div class="card-body">
						<div class="row">
						
							<div class="col-md-6 col-lg-4">
								<div class="form-group">
									<label class="form-label">Name <b>*</b></label>
									<form:input path="name" id="name"
										cssClass="form-control" />
									<span class="error" id="nameError"><form:errors
											path="name"></form:errors></span>
								</div>
							</div>
							
							<div class="col-md-6 col-lg-4">
								<div class="form-group">
									<label class="form-label">Connection Type <b>*</b></label>
									<form:select path="connType" id="connType"
										cssClass="form-control">
											<form:option value="primary">Primary</form:option>
											<form:option value="secondary">Secondary</form:option>
										</form:select>
									<span class="error" id="connTypeError"><form:errors
											path="connType"></form:errors></span>
								</div>
							</div>
							
							<div class="col-md-6 col-lg-4">
								<div class="form-group">
									<label class="form-label">Service Type <b>*</b></label>
									<form:select path="serviceType" id="serviceType"
										cssClass="form-control">
											<form:option value="client">Client</form:option>
											<form:option value="server">Server</form:option>
										</form:select>
									<span class="error" id="serviceTypeError"><form:errors
											path="serviceType"></form:errors></span>
								</div>
							</div>
							
							<div class="col-md-6 col-lg-4">
								<div class="form-group">
									<label class="form-label">Participant <b>*</b></label>
									<form:select path="participantId" id="participantId" cssClass="form-control">
										<c:forEach var="itr" items="${participantList}">
											<form:option value="${itr.lkpkey}">${itr.lkpvalue}</form:option>
										</c:forEach>
									</form:select>
									<span class="error" id="participantIdError"><form:errors
											path="participantId"></form:errors></span>
								</div>
							</div>
							
							<div class="col-md-6 col-lg-4" id="ipDiv">
								<div class="form-group">
									<label class="form-label">IP <b>*</b></label>
									<form:input path="ip" id="ip"
										cssClass="form-control" />
									<span class="error" id="ipError"><form:errors
											path="ip"></form:errors></span>
								</div>
							</div>
							
							<div class="col-md-6 col-lg-4">
								<div class="form-group">
									<label class="form-label">Port <b>*</b></label>
									<form:input path="port" id="port"
										cssClass="form-control" />
									<span class="error" id="portError"><form:errors
											path="port"></form:errors></span>
								</div>
							</div>
							
						</div>
					</div>
					
					<div class="card-footer text-right">
						<div class=".d-flex1">
							<button type="submit" id="submitBtn" class="btn btn-primary ml-auto">Submit</button>
							<button type="reset" class="btn btn-primary ml-auto" id="resetBtn">Clear</button>
						</div>
					</div>
					
					<div class="card-body">
					<table id="branchTable" class="table table-striped table-bordered nowrap" style="width:100%">
					<thead>
						<tr>
							<!-- <th class="text-center w-1">Select</th> -->
							<th>Name</th>
							<th>Connection Type</th>
							<th>Service Type</th>
							<th>Participant</th>
							<th>IP</th>
							<th>Port</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="itr" items="${connectionList}">
							<tr>
								<td>${itr.name}</td>
								<td>${itr.connType}</td>
								<td>${itr.serviceType}</td>
								<td>${itr.participantId}</td>
								<td>${itr.ip}</td>
								<td>${itr.port}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<input type="hidden" value="${leftpartipantMenuID}"
								id="leftpartipantMenuID">
				</div>	
				
				<!-- Modal -->
					<div class="modal fade" id="myModal" role="dialog">
						<div class="modal-dialog modal-sm">
							<div class="modal-content">
								<div class="modal-header">
									<p class="text-capitalize">Delete Branch <label id="delBinMsg"></label></p>
								</div> 
								<div class="modal-body">

									<form>
										<p class="leading-loose">Are you sure you want to delete this Branch ?</p>
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
				</form:form>
			</div>
		</div>
	</div>
</div>
