<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	var deleteBinID;
	var keyData;

$(document).ready(function() {
	
	$('#strSelectID').val("N");
	
	$('#delBinMsg').text("");
	
	$('#keyTable').DataTable({
		 responsive: true,
		 scrollX: 100,
		 scroller: true
	});
	
	$('#deleteBtn').click(function() {
		
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "${pageContext.request.contextPath}/deleteKey",
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
						var obj = { Title: '', Url: 'keyConfigForm' };
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
	
	var value = $("#embossConfig").validate({
		rules: {
			strServiceName: {
                    required: true,
                    regex: /^[a-zA-Z0-9\s]+$/,
                    minlength: 3,
                    maxlength: 30
            },
            strFormat: {
                required: true
        	}
		},
    	messages: {
    		strServiceName: {
                    required: "Please Enter Emboss Service Name!",
                    minlength: "Minmum {0} characters required!",
                    maxlength: "Maximum {0} characters allowed!",
                    regex: "only Numeric allow !"
            },
            strFormat: {
                required: "Please Enter Emboss Format!"
        	}
    	},
		submitHandler: function(form) {
			
				var data = eval('${emboss}');
				var flag = false;
				var strServiceName = form.strServiceName.value;
				
				$.each(data, function( index, value ) {
					if(strServiceName == JSON.stringify(value.strServiceName).replace("\"","").replace("\"","")) {
						flag = true;
						return false;
					}
				});
					if(flag) {
						$('#strError').text("Duplicate Key");
						$('#errorMsg').hide();
					}else {
						$('#strError').text("");
						$('#errorMsg').hide();
						form.submit();	
					}
				
		}
	});
	
	
	
});

function editFunction(bin) {     
	
    	var data = eval('${emboss}');
    	$.each(data, function( index, value ) {
			if(bin == JSON.stringify(value.strID).replace("\"","").replace("\"","")) {
				keyData = value;
				return false;
			}
		});
    	
    	$('#strCVK').val(keyData.strCVK);
    	$('#strPVK').val(keyData.strPVK);
    	$('#strMDK').val(keyData.strMDK);
    	$('#strSecKeyID').val(keyData.strSecKeyID);
    	$('#strSelectID').val("Y");
    	
  }

function deleteFunction(id) {
	$('#delBinMsg').text("");
	$('#strCVK').val("");
	$('#strPVK').val("");
	$('#strMDK').val("");
	$('#strSecKeyID').val("");
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
				<form:form action="addEmboss" method="post" commandName="embossConfigModel"
					name="embossConfig" id="embossConfig" cssClass="card">
					
					<div class="card-header">
						<h3 class="card-title"><i class="fe fe-user-plus"></i> &nbsp;&nbsp;Emboss Configuration <span class="text-right badge badge-danger" id="strError"></span></h3>
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
									<label class="form-label">Emboss Service Name <b>*</b></label>
									<form:input path="strServiceName" id="strServiceName"
										cssClass="form-control" />
									<span class="error" id="strServiceNameError"><form:errors
											path="strServiceName"></form:errors></span>
								</div>
							</div>
							
							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Format <b>*</b></label>
									<form:input path="strFormat" id="strFormat"
										cssClass="form-control" />
									<span class="error" id="strFormatError"><form:errors
											path="strFormat"></form:errors></span>
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
					<table id="keyTable" class="table table-striped table-bordered nowrap" style="width:100%">
					<thead>
						<tr>
							<th>Id</th>
							<th>Emboss Service Name</th>
							<th>Format</th>
							<!-- <th></th> -->
						</tr>
					</thead>
					<tbody>
						<c:forEach var="itr" items="${embossList}">
							<tr>
								<td>${itr.strID}</td>
								<td>${itr.strServiceName}</td>
								<td>${itr.strFormat}</td>
								<%-- <td>
									<a class="icon" href="javascript:editFunction(${itr.strID})">
                              		<i class="fe fe-edit"></i>
                            		</a>
                            		&nbsp;&nbsp;&nbsp;
                            		<a class="icon" href="javascript:deleteFunction(${itr.strID})">
                              		<i class="fe fe-trash"></i>
                            		</a>
								</td> --%>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<input type="hidden" value="${leftbinCardMenuID}"
								id=leftbinCardMenuID>
				</div>	
				
				<form:hidden path="strSelectID" id="strSelectID"/>
				
				<!-- Modal -->
					<div class="modal fade" id="myModal" role="dialog">
						<div class="modal-dialog modal-sm">
							<div class="modal-content">
								<div class="modal-header">
									<p class="text-capitalize">Delete Key <label id="delBinMsg"></label></p>
								</div> 
								<div class="modal-body">

									<form>
										<p class="leading-loose">Are you sure you want to delete this Key ?</p>
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
