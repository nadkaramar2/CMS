<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	var deleteBinID;
	var addressData;

$(document).ready(function() {
	
	$('#strSelectID').val("N");
	
	$('#delBinMsg').text("");
	
	$('#strAddressDesc').keyup(function(){
		var txt = $('#strAddressDesc').val().replace(/(?:^|)\w/g, function(match) {
	        return match.toUpperCase();
	     });
		$('#strAddressDesc').val(txt);
	});
	
	
	
	$('#addressTable').DataTable({
		 responsive: true
	});
	
	$('#deleteBtn').click(function() {
		
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "${pageContext.request.contextPath}/deleteAddress",
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
						var obj = { Title: '', Url: 'addressConfigForm' };
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
	
	var value = $("#addressConfig").validate({
		rules: {
			strAddressDesc: {
                    required: true,
                    regex: /^[a-zA-Z]+$/,
                    minlength: 2,
                    maxlength: 15
            },
            strExt: {
                required: true,
                regex: /^[0-9]+$/,
                minlength: 1,
                maxlength: 5         
            }
		},
    	messages: {
    		strAddressDesc: {
                    required: "Please Enter Address Type!",
                    minlength: "Minmum {0} characters required!",
                    maxlength: "Maximum {0} characters allowed!",
                    regex: "only Char allow !"
            },
            strExt: {
                required: "Please Enter Ext id!",
                minlength: "Minmum {0} characters required!",
                maxlength: "Maximum {0} characters allowed!",
                regex: "only numeric allow !"
            }
    	},
		submitHandler: function(form) {
			
			if($('#strSelectID').val() == "N") {
			
				var data = eval('${address}');
				var flag = false;
				var binVal = form.strAddressDesc.value;
				$.each(data, function( index, value ) {
					if(binVal == JSON.stringify(value.strAddressDesc).replace("\"","").replace("\"","")) {
						flag = true;
						return false;
					}
				});
					if(flag) {
						$('#strError').text("Duplicate Address Type");
						$('#errorMsg').hide();
					}else {
						$('#strError').text("");
						$('#errorMsg').hide();
						form.submit();	
					}
			}else {
				addressData.strExt = form.strExt.value;
				$.ajax({
					type : "POST",
					contentType : "application/json",
					url : "${pageContext.request.contextPath}/updateAddress",
					data: JSON.stringify(addressData),
					dataType : 'json',
					contentType : "application/json",
					timeout : 100000,
					success : function(data) {
						console.log("SUCCESS: ", data);
						if(data.outResponseCode == "00"){
							$('#strError').addClass("tag tag-green");
							$('#strError').text(data.message);
							setTimeout(function(){// wait for 5 secs(2)
								var obj = { Title: '', Url: 'addressConfigForm' };
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
	
    	var data = eval('${address}');
    	$.each(data, function( index, value ) {
			if(bin == JSON.stringify(value.strAddressDesc).replace("\"","").replace("\"","")) {
				addressData = value;
				return false;
			}
		});
    	$('#strAddressDesc').val(addressData.strAddressDesc);
    	$('#strAddressDesc').attr('readonly', true);
    	$('#strExt').val(addressData.strExt);
    	$('#strSelectID').val("Y");
    	
  }

function deleteFunction(id) {
	$('#delBinMsg').text("");
	$('#strAddressDesc').val("");
	$('#strExt').val("");
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
				<form:form action="addressConfig" method="post" commandName="addressTypeModel"
					name="addressConfig" id="addressConfig" cssClass="card">
					
					<div class="card-header">
						<h3 class="card-title"><i class="fe fe-user-plus"></i> &nbsp;&nbsp;Address Configuration <span class="text-right badge badge-danger" id="strError"></span></h3>
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
						
							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Address Type <b>*</b></label>
									<form:input path="strAddressDesc" id="strAddressDesc"
										cssClass="form-control" />
									<span class="error" id="strAddressDescError"><form:errors
											path="strAddressDesc"></form:errors></span>
								</div>
							</div>
							
							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Ext <b>*</b></label>
									<form:input path="strExt" id="strExt"
										cssClass="form-control" />
									<span class="error" id="strExtError"><form:errors
											path="strExt"></form:errors></span>
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
					<table id="addressTable" class="table table-striped table-bordered nowrap" style="width:100%">
					<thead>
						<tr>
							<th>Address Type</th>
							<th>Ext</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="itr" items="${addressList}">
							<tr>
								<td>${itr.strAddressDesc}</td>
								<td>${itr.strExt}</td>
								<td>
									<a class="icon" href="javascript:editFunction('${itr.strAddressDesc}')">
                              		<i class="fe fe-edit"></i>
                            		</a>
                            		&nbsp;&nbsp;&nbsp;
                            		<a class="icon" href="javascript:deleteFunction(${itr.strID})">
                              		<i class="fe fe-trash"></i>
                            		</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<input type="hidden" value="${leftbranchMenuID}"
								id="leftbranchMenuID">
				</div>	
				<form:hidden path="strSelectID" id="strSelectID"/>
				
				<!-- Modal -->
					<div class="modal fade" id="myModal" role="dialog">
						<div class="modal-dialog modal-sm">
							<div class="modal-content">
								<div class="modal-header">
									<p class="text-capitalize">Delete Address Type <label id="delBinMsg"></label></p>
								</div> 
								<div class="modal-body">

									<form>
										<p class="leading-loose">Are you sure you want to delete this Address Type ?</p>
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
