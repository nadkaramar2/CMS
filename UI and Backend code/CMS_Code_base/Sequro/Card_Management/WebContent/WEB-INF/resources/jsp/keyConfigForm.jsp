<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	var deleteBinID;
	var keyData;

$(document).ready(function() 
{
	$('#strSelectID').val("N");
	
	$('#delBinMsg').text("");
	
	$('#keyTable').DataTable({
		 responsive: true
	});
	
	$('.cmsConfig').hide();
	$('.otherConfig').hide();
	$('#participantId').val("").change();
	$('#participantId').change(function () 
	{
		let type = $(this).find(':selected').data('type');
		$('#strSelectID').val(type);
		if(type != undefined && type == 'CMS') 
		{
			$('.cmsConfig').show();
			$('.otherConfig').hide();
		}
		else if(type != undefined && type != 'CMS') 
		{
			$('.cmsConfig').hide();
			$('.otherConfig').show();
		}
		else 
		{
			$('.cmsConfig').hide();
			$('.otherConfig').hide();
		}
	})
	
	$('#deleteBtn').click(function() 
	{
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "${pageContext.request.contextPath}/deleteKey",
			data: "id="+deleteBinID,
			dataType : 'json',
			contentType : "application/x-www-form-urlencoded",
			timeout : 100000,
			success : function(data) 
			{
				console.log("SUCCESS: ", data);
				if(data.outResponseCode == "00"){
					$('#delBinMsg').addClass("tag tag-green");
					$('#delBinMsg').text(data.message);
					setTimeout(function(){// wait for 5 secs(2)
						var obj = { Title: '', Url: 'keyConfigForm' };
				        history.pushState(obj, obj.Title, obj.Url);
				           location.reload(); // then reload the page.(3)
				      }, 2000); 
				}
				else 
				{
					$('#delBinMsg').addClass("tag tag-red");
					$('#delBinMsg').text(data.message);
				} 
			},
			error : function(e) 
			{
				console.log("ERROR: ", e);
				$('#delBinMsg').addClass("tag tag-red");
				$('#delBinMsg').text("Error");
			},
			done : function(e) 
			{
				console.log("DONE");
			}
		});
		
		return false;
	})
	
	
		$.validator.addMethod(
			"regex",
			function(value, element, regexp) 
			{
    		var check = false;
    		return this.optional(element) || regexp.test(value);
		},
		"ss"
	);
	
	var value = $("#keyConfig").validate({
		rules: {
			strCVK: 
			{
                    required: true,
                    regex: /^[0-9]+$/,
                    minlength: 3,
                    maxlength: 50
            },
            strPVK: {
                required: true,
                regex: /^[0-9]+$/,
                minlength: 3,
                maxlength: 50
        	},
        	strMDK: {
                required: true,
                regex: /^[0-9]+$/,
                minlength: 3,
                maxlength: 50
        	},
        	strSecKeyID: {
                required: true,
                regex: /^[0-9]+$/,
                minlength: 1,
                maxlength: 10
        	},
		},
    	messages: 
    	{
    		strCVK: 
    		{
                    required: "Please Enter CVK Key!",
                    minlength: "Minmum {0} characters required!",
                    maxlength: "Maximum {0} characters allowed!",
                    regex: "only Numeric allow !"
            },
            strPVK: 
            {
                required: "Please Enter PVK Key!",
                minlength: "Minmum {0} characters required!",
                maxlength: "Maximum {0} characters allowed!",
                regex: "only Numeric allow !"
        	},
        	strMDK: 
        	{
                required: "Please Enter MDK Key!",
                minlength: "Minmum {0} characters required!",
                maxlength: "Maximum {0} characters allowed!",
                regex: "only Numeric allow !"
        	},
        	strSecKeyID: 
        	{
                required: "Please Enter Security Key!",
                minlength: "Minmum {0} characters required!",
                maxlength: "Maximum {0} characters allowed!",
                regex: "only Numeric allow !"
        	},
    	},
		submitHandler: function(form) 
		{
			if($('#strSelectID').val() == "N") 
			{
				var data = eval('${key}');
				var flag = false;
				var strCVK = form.strCVK.value;
				var strPVK = form.strPVK.value;
				var strMDK = form.strMDK.value;
				var strSecKeyID = form.strSecKeyID.value;
				$.each(data, function( index, value ) 
				{
					if(strCVK == JSON.stringify(value.strCVK).replace("\"","").replace("\"","") 
						&& strPVK == JSON.stringify(value.strPVK).replace("\"","").replace("\"","")
						&& strMDK == JSON.stringify(value.strMDK).replace("\"","").replace("\"","")
						&& strSecKeyID == JSON.stringify(value.strSecKeyID).replace("\"","").replace("\"","")) {
						flag = true;
						return false;
					}
				});
					if(flag) 
					{
						$('#strError').text("Duplicate Key");
						$('#errorMsg').hide();
					}
					else 
					{
						$('#strError').text("");
						$('#errorMsg').hide();
						form.submit();	
					}
			}
			else 
			{
				keyData.strCVK = form.strCVK.value;
				keyData.strPVK = form.strPVK.value;
				keyData.strMDK = form.strMDK.value;
				keyData.strSecKeyID = form.strSecKeyID.value;
				$.ajax({
					type : "POST",
					contentType : "application/json",
					url : "${pageContext.request.contextPath}/updateKey",
					data: JSON.stringify(keyData),
					dataType : 'json',
					contentType : "application/json",
					timeout : 100000,
					success : function(data) 
					{
						console.log("SUCCESS: ", data);
						if(data.outResponseCode == "00")
						{
							$('#strError').addClass("tag tag-green");
							$('#strError').text(data.message);
							setTimeout(function(){// wait for 5 secs(2)
								var obj = { Title: '', Url: 'keyConfigForm' };
						        history.pushState(obj, obj.Title, obj.Url);
	 				           location.reload(); // then reload the page.(3)
	 				      }, 2000); 
						}
						else 
						{
							$('#strError').addClass("tag tag-red");
							$('#strError').text(data.message);
						} 
					},
					error : function(e) 
					{
						console.log("ERROR: ", e);
						$('#strError').addClass("tag tag-red");
						$('#strError').text("Error");
					},
					done : function(e) 
					{
						console.log("DONE");
					}
				});
			}		
		}
	});
});

function editFunction(bin) 
{     
    	var data = eval('${key}');
    	$.each(data, function( index, value ) 
    	{
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

function deleteFunction(id) 
{
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
			<div class="col-12">
			
				<form:form action="keyConfig" method="post" commandName="keyConfigModel"
					name="keyConfig" id="keyConfig" cssClass="card">
					
					<div class="card-header">
						<h3 class="card-title"><i class="fe fe-user-plus"></i> &nbsp;&nbsp;Key Configuration <span class="text-right badge badge-danger" id="strError"></span></h3>
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
							<div class="col-md-6 col-lg-12">
								<div class="form-group">
									<label class="form-label">Participant <b>*</b></label>
									<form:select path="participantId" id="participantId" cssClass="form-control">
										<form:option value="" data-type="">--- Select Participant ---</form:option>
										<c:forEach var="itr" items="${participantList}">
										<c:if test="${itr.flag == 'Y'}">
												<form:option value="${itr.id}" data-type="${itr.participantType}">${itr.participantName} :  ${itr.description}</form:option>
										</c:if>
										</c:forEach>
									</form:select>
									<span class="error" id="participantIdError"><form:errors
											path="participantId"></form:errors></span>
								</div>
							</div>

							<div class="col-md-6 col-lg-6 cmsConfig">
								<div class="form-group">
									<label class="form-label">CVK Key <b>*</b></label>
									<form:input path="strCVK" id="strCVK"
										cssClass="form-control" />
									<span class="error" id="strCVKError"><form:errors
											path="strCVK"></form:errors></span>
								</div>
							</div>
							
							<div class="col-md-6 col-lg-6 cmsConfig">
								<div class="form-group">
									<label class="form-label">PVK Key <b>*</b></label>
									<form:input path="strPVK" id="strPVK"
										cssClass="form-control" />
									<span class="error" id="strPVKError"><form:errors
											path="strPVK"></form:errors></span>
								</div>
							</div>
							
							<div class="col-md-6 col-lg-6 cmsConfig">
								<div class="form-group">
									<label class="form-label">MDK Key <b>*</b></label>
									<form:input path="strMDK" id="strMDK"
										cssClass="form-control" />
									<span class="error" id="strMDKError"><form:errors
											path="strMDK"></form:errors></span>
								</div>
							</div>
							
							<div class="col-md-6 col-lg-6 cmsConfig">
								<div class="form-group">
									<label class="form-label">Security Key ID <b>*</b></label>
									<form:input path="strSecKeyID" id="strSecKeyID"
										cssClass="form-control" />
									<span class="error" id="strSecKeyIDError"><form:errors
											path="strSecKeyID"></form:errors></span>
								</div>
							</div>
							
							
							<div class="col-md-6 col-lg-6 otherConfig">
								<div class="form-group">
									<label class="form-label">ZPK <b>*</b></label>
									<form:input path="strZPK" id="strZPK"
										cssClass="form-control" />
									<span class="error" id="strZPKError"><form:errors
											path="strZPK"></form:errors></span>
								</div>
							</div>
							
							
							<div class="col-md-6 col-lg-6 otherConfig">
								<div class="form-group">
									<label class="form-label">ZPK CV <b>*</b></label>
									<form:input path="strZPK_CV" id="strZPK_CV"
										cssClass="form-control" />
									<span class="error" id="strZPK_CVError"><form:errors
											path="strZPK_CV"></form:errors></span>
								</div>
							</div>
							
							
							<div class="col-md-6 col-lg-6 otherConfig">
								<div class="form-group">
									<label class="form-label">ZMK <b></b></label>
									<form:input path="strZMK" id="strZMK"
										cssClass="form-control" />
									<span class="error" id="strZMKError"><form:errors
											path="strZMK"></form:errors></span>
								</div>
							</div>
							
							
							<div class="col-md-6 col-lg-6 otherConfig">
								<div class="form-group">
									<label class="form-label">ZMK CV <b></b></label>
									<form:input path="strZMK_CV" id="strZMK_CV"
										cssClass="form-control" />
									<span class="error" id="strZMK_CVError"><form:errors
											path="strZMK_CV"></form:errors></span>
								</div>
							</div>
							
							
							<div class="col-md-6 col-lg-6 otherConfig">
								<div class="form-group">
									<label class="form-label">MAC Key <b></b></label>
									<form:input path="strMAC_KEY" id="strMAC_KEY"
										cssClass="form-control" />
									<span class="error" id="strMAC_KEYError"><form:errors
											path="strMAC_KEY"></form:errors></span>
								</div>
							</div>
							
							
							<div class="col-md-6 col-lg-6 otherConfig">
								<div class="form-group">
									<label class="form-label">MAC CV <b></b></label>
									<form:input path="strMAC_CV" id="strMAC_CV"
										cssClass="form-control" />
									<span class="error" id="strMAC_CVError"><form:errors
											path="strMAC_CV"></form:errors></span>
								</div>
							</div>
							
							<div class="col-md-6 col-lg-6 otherConfig">
								<div class="form-group">
									<label class="form-label">MAC Fields <b></b></label>
									<form:input path="strMAC_FIELDS" id="strMAC_FIELDS"
										cssClass="form-control" />
									<span class="error" id="strMAC_FIELDSError"><form:errors
											path="strMAC_FIELDS"></form:errors></span>
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
					
					<div class="card-body" style="display:none">
					<table id="keyTable" class="table table-striped table-bordered nowrap" style="width:100%">
					<thead>
						<tr>
							<th>CVK Key</th>
							<th>PVK Key</th>
							<th>MDK Key</th>
							<th>Security Key</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="itr" items="${keyList}">
							<tr>
								<td>${itr.strCVK}</td>
								<td>${itr.strPVK}</td>
								<td>${itr.strMDK}</td>
								<td>${itr.strSecKeyID}</td>
								<td>
									<%-- <a class="icon" href="javascript:editFunction(${itr.strID})">
                              		<i class="fe fe-edit"></i>
                            		</a> --%>
                            		&nbsp;&nbsp;&nbsp;
                            		<a class="icon" href="javascript:deleteFunction(${itr.strID})">
                              		<i class="fe fe-trash"></i>
                            		</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
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
