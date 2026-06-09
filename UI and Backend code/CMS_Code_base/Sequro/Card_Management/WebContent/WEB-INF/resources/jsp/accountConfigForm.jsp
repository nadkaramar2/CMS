<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	var deleteBinID;
	var accountData;

$(document).ready(function() {
	
	$('#strSelectID').val("N");
	
	$('#delBinMsg').text("");
	
	$('#strAccountDesc').keyup(function(){
		var txt = $('#strAccountDesc').val().replace(/(?:^|)\w/g, function(match) {
	        return match.toUpperCase();
	     });
		$('#strAccountDesc').val(txt);
	});
	
	
	
	$('#accountTable').DataTable({
		 responsive: true
	});
	
	$('#deleteBtn').click(function() {
		
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "${pageContext.request.contextPath}/deleteAccount",
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
						var obj = { Title: '', Url: 'accountConfigForm' };
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
	
	var value = $("#accountConfig").validate({
		rules: {
			strAccountDesc: {
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
    		strAccountDesc: {
                    required: "Please Enter Account Type!",
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
			//console.log('strSelectID value '+$('#strSelectID').val());
			if($('#strSelectID').val() == "N") {
			
				var data = eval('${account}');
				var flag = false;
				var binVal = form.strAccountDesc.value;
				
				$.each(data, function( index, value) {
					if(binVal == JSON.stringify(value.strAccountDesc).replace("\"","").replace("\"","")) {
						flag = true;
						return false;
					}
				});
					if(flag) 
					{
						$('#strError').text("Duplicate Account Type");
						$('#errorMsg').hide();
					}else {
						$('#strError').text("");
						$('#errorMsg').hide();
						form.submit();	
					}
			}else {
				accountData.strExt = form.strExt.value;
				$.ajax({
					type : "POST",
					contentType : "application/json",
					url : "${pageContext.request.contextPath}/updateAccount",
					data: JSON.stringify(accountData),
					dataType : 'json',
					contentType : "application/json",
					timeout : 100000,
					success : function(data) {
						console.log("SUCCESS: ", data);
						if(data.outResponseCode == "00"){
							$('#strError').addClass("tag tag-green");
							$('#strError').text(data.message);
							setTimeout(function(){// wait for 5 secs(2)
								var obj = { Title: '', Url: 'accountConfigForm' };
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
	
    	var data = eval('${account}');
    	$.each(data, function( index, value ) {
			if(bin == JSON.stringify(value.strAccountDesc).replace("\"","").replace("\"","")) {
				accountData = value;
				return false;
			}
		});
    	$('#strAccountDesc').val(accountData.strAccountDesc);
    	$('#strAccountDesc').attr('readonly', true);
    	$('#strExt').val(accountData.strExt);
    	$('#strSelectID').val("Y");
    	
  }

function deleteFunction(id) {
	$('#delBinMsg').text("");
	$('#strAccountDesc').val("");
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
				<form:form action="accountConfig" method="post" commandName="accountTypeModel"
					name="accountConfig" id="accountConfig" cssClass="card">
					
					<div class="card-header">
						<h3 class="card-title"><i class="fe fe-user-plus"></i> &nbsp;&nbsp;Account Configuration <span class="text-right badge badge-danger" id="strError"></span></h3>
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
									<label class="form-label">Account Type <b>*</b></label>
									<form:input path="strAccountDesc" id="strAccountDesc"
										cssClass="form-control" />
									<span class="error" id="strAccountDescError"><form:errors
											path="strAccountDesc"></form:errors></span>
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
					<table id="accountTable" class="table table-striped table-bordered nowrap" style="width:100%">
					<thead>
						<tr>
							<th>Account Type</th>
							<th>Ext</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="itr" items="${accountList}">
							<tr>
								<td>${itr.strAccountDesc}</td>
								<td>${itr.strExt}</td>
								<td>
									<a class="icon" href="javascript:editFunction('${itr.strAccountDesc}')">
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
									<p class="text-capitalize">Delete Account Type <label id="delBinMsg"></label></p>
								</div> 
								<div class="modal-body">

									<form>
										<p class="leading-loose">Are you sure you want to delete this Account Type ?</p>
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
