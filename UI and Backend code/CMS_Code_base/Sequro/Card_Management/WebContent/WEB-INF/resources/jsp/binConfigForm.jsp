<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">

	var deleteBinID;
	var binData;
	
	$(document).ready(function() 
	{
		$('#otherTxt').hide();
		$('#strNetworkScheme').change(function() 
		{
			if($(this).val() == "Other") 
			{
				$('#otherTxt').show();
			}else {
				$('#otherTxt').hide();
			}
		})
		
		$('#strSelectID').val("N");
		
		$('#Binflag').val("N");
		
		
		$('#delBinMsg').text("");
		
		$('#binTable').DataTable({
			 responsive: true
		});
		
		$('#deleteBtn').click(function() {
			
			$.ajax({
				type : "POST",
				contentType : "application/json",
				url : "${pageContext.request.contextPath}/deleteBin",
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
							var obj = { Title: '', Url: 'binConfigForm' };
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
		
		
		var value = $("#binConfig").validate({
			rules: {
				strBin: {
	                    required: true,
	                    regex: /^[\d\s]+$/,
	                    minlength: 6,
	                    maxlength: 6
	            },
	            strBinDesc: {
	                required: true,
	                regex: /^[a-zA-Z0-9\s.\-]+$/,
	                minlength: 4,
	                maxlength: 20         
	            }
			},
	    	messages: {
	    		strBin: {
	                    required: "Please Enter Bin!",
	                    minlength: "Minmum {0} characters required!",
	                    maxlength: "Maximum {0} characters allowed!",
	                    regex: "only numeric allow !"
	            },
	            strBinDesc: {
	                required: "Please Enter Bin Desc!",
	                minlength: "Minmum {0} characters required!",
	                maxlength: "Maximum {0} characters allowed!",
	                regex: "only alphanumeric allow !"
	            }
	    	},
			submitHandler: function(form) {
				
				
				if($('#strSelectID').val() == "N") {
				
					if($("#Binflag").prop('checked') == true){
						$('#Binflag').val("Y");
					}else {
						$('#Binflag').val("N");
					}
					
					var data = eval('${bin}');
					var flag = false;
					var binVal = form.strBin.value;
					
					$.each(data, function( index, value ) {
						if(binVal == JSON.stringify(value.strBin).replace("\"","").replace("\"","")) {
							flag = true;
							return false;
						}
					});
						if(flag) {
							$('#strBinError').text("Duplicate Bin");
							$('#errorMsg').hide();
						}else {
							$('#strBinError').text("");
							$('#errorMsg').hide();
							form.submit();	
						}
				}else {
					binData.strBinDesc = form.strBinDesc.value;
					if(form.strNetworkScheme.value == "Other") {
						binData.strNetworkScheme = form.otherDesc.value;
					}else {
						binData.strNetworkScheme = form.strNetworkScheme.value;	
					}
					if($("#Binflag").prop('checked') == true){
						binData.flag = "Y";
					}else {
						binData.flag = "N";
					}
					
					$.ajax({
						type : "POST",
						contentType : "application/json",
						url : "${pageContext.request.contextPath}/updateBin",
						data: JSON.stringify(binData),
						dataType : 'json',
						contentType : "application/json",
						timeout : 100000,
						success : function(data) {
							console.log("SUCCESS: ", data);
							if(data.outResponseCode == "00"){
								$('#strBinError').addClass("tag tag-green");
								$('#strBinError').text(data.message);
								setTimeout(function(){// wait for 5 secs(2)
									var obj = { Title: '', Url: 'binConfigForm' };
							        history.pushState(obj, obj.Title, obj.Url);
		 				           location.reload(); // then reload the page.(3)
		 				      }, 2000); 
							}else {
								$('#strBinError').addClass("tag tag-red");
								$('#strBinError').text(data.message);
							} 
						},
						error : function(e) {
							console.log("ERROR: ", e);
							$('#strBinError').addClass("tag tag-red");
							$('#strBinError').text("Error");
						},
						done : function(e) {
							console.log("DONE");
						}
					});
				}		
			}
		});
		
		
		
	});
	
	function editFunction(bin) 
	{     
        	var data = eval('${bin}');
        
        	$.each(data, function( index, value ) {
				if(bin == JSON.stringify(value.strBin).replace("\"","").replace("\"","")) {
					binData = value;
					return false;
				}
			});
        	
        	$('#strBin').val(binData.strBin);
        	$('#strBin').attr('readonly', true);
        	$('#strBinDesc').val(binData.strBinDesc);
        	$('#strSelectID').val("Y");
        	
        	if (binData.flag == "Y") {
        		$('#Binflag').attr("checked", true);
        	}else {
        		$('#Binflag').attr("checked", false);
        	}
        	
        	
        	$("#strNetworkScheme").val(binData.strNetworkScheme);
        	/* $("#strNetworkScheme > option").each(function() {
        		if(binData.strNetworkScheme != this.value) {
        			$("#strNetworkScheme").val("Other");	        			
        		}
        	}); */
        	
        	if($("#strNetworkScheme").val() == null) {
    			$("#strNetworkScheme").val("Other");	        			
    		}else {
    			$("#otherTxt").hide();
    		}
        	
        	if($("#strNetworkScheme").val() == "Other") {
        		$("#otherTxt").show();
        		$("#otherDesc").val(binData.strNetworkScheme);
        	}
        	
      }
	
	function deleteFunction(id) {
		$('#delBinMsg').text("");
		$('#strBin').val("");
		$('#strBinDesc').val("");
		deleteBinID = id;
		$('#myModal').modal('show');
      }
	
</script>

<div class="my-3 my-md-5">
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<jsp:include page="binCardConfigLeftMenu.jsp"></jsp:include>
			</div>
			<div class="col-md-9">
				<form:form action="binConfig" method="post" commandName="binModel"
					name="binConfig" id="binConfig" cssClass="card">

					<div class="card-header">
						<h3 class="card-title">
							<i class="fe fe-user-plus"></i> &nbsp;&nbsp;Bin Configuration <span
								class="text-right badge badge-danger" id="strBinError"></span>
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
									<label class="form-label">Participant <b>*</b></label> <input
										type="text" name="strPartID" value="${user.participantDesc}"
										class="form-control" id="institutionCode" disabled="disabled">
								</div>
							</div>

							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Bin <b>*</b></label>
									<form:input path="strBin" id="strBin" cssClass="form-control" />
									<span class="error" id="strBinError"><form:errors
											path="strBin"></form:errors></span>
								</div>
							</div>

							<div class="col-md-6 col-lg-4">
								<div class="form-group">
									<label class="form-label">Bin Description <b>*</b></label>
									<form:input path="strBinDesc" id="strBinDesc"
										cssClass="form-control" />
									<span class="error" id="strBinDescError"><form:errors
											path="strBinDesc"></form:errors></span>
								</div>
							</div>

							<div class="col-md-4 col-lg-2">
								<div class="form-group">
									<label class="form-label">Network Scheme <b>*</b></label>
									<form:select path="strNetworkScheme" id="strNetworkScheme"
										cssClass="form-control selectpicker">
										<form:option value=""></form:option>
										<form:option value="Visa">Visa</form:option>
										<form:option value="Master">Master</form:option>
										<form:option value="Rupay">Rupay</form:option>
										<form:option value="Other">Other</form:option>
									</form:select>
									<span class="error" id="strNetworkSchemeIDError"><form:errors
											path="strNetworkScheme"></form:errors></span>
								</div>
							</div>

							<div class="col-md-4 col-lg-2">
								<div class="form-group">
									<label class="form-label">Add Bin Suffix <b>*</b></label> <label
										class="custom-control custom-checkbox custom-control-inline">
										<input type="checkbox" class="custom-control-input"
										name="flag" id="Binflag"> <span
										class="custom-control-label">Add Bin Suffix</span>
									</label>
								</div>
							</div>

							<%-- <div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Add Bin Suffix  <b>*</b></label>
									<form:select path="flag" id="flag"
										cssClass="form-control selectpicker">
											<form:option value=""></form:option>
											<form:option value="Visa">Visa</form:option>
											<form:option value="Master">Master</form:option>
											<form:option value="Rupay">Rupay</form:option>
											<form:option value="Other">Other</form:option>
									</form:select>
									<span class="error" id="strNetworkSchemeIDError"><form:errors
											path="strNetworkScheme"></form:errors></span>
								</div>
							</div> --%>



							<div class="col-md-6 col-lg-6" id="otherTxt">
								<div class="form-group">
									<label class="form-label">Other Description <b>*</b></label> <input
										type="text" name="otherDesc" id="otherDesc"
										class="form-control" maxlength="10"> <span
										class="error" id="otherDescError"></span>
								</div>
							</div>
						</div>
					</div>

					<div class="card-footer text-right">
						<div class=".d-flex1">
							<button type="submit" id="submitBtn"
								class="btn btn-primary ml-auto">Submit</button>
							<button type="reset" class="btn btn btn-secondary ml-auto"
								id="resetBtn">Clear</button>
						</div>
					</div>

					<div class="card-body" style="width: 100%;" align="left">
						<table id="binTable"
							class="table table-striped table-bordered nowrap">
							<thead>
								<tr>
									<!-- <th>Select</th> -->
									<th>BIN</th>
									<th>Description</th>
									<th>Network Scheme</th>
									<th>Flag</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="itr" items="${binList}">
									<tr>
										<%-- <td>
									<label class="custom-control custom-radio">
									<form:radiobutton path="strSelectID"
											cssClass="custom-control-input approveID"
											value="${itr.strID}"
											id="strSelectID" />
                            <div class="custom-control-label"></div>
                          </label>
								</td> --%>
										<td>${itr.strBin}</td>
										<td>${itr.strBinDesc}</td>
										<td>${itr.strNetworkScheme}</td>
										<td>${itr.flag}</td>
										<td><a class="icon"
											href="javascript:editFunction(${itr.strBin})"> <i
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

					<form:hidden path="strSelectID" id="strSelectID" />

					<!-- Modal -->
					<div class="modal fade" id="myModal" role="dialog">
						<div class="modal-dialog modal-sm">
							<div class="modal-content">
								<div class="modal-header">
									<p class="text-capitalize">
										Delete Bin <label id="delBinMsg"></label>
									</p>
								</div>
								<div class="modal-body">

									<form>
										<p class="leading-loose">Are you sure you want to delete
											this bin ?</p>
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
