<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	var deleteBinID;
	var cardData;

$(document).ready(function() {
	
	$('#strSelectID').val("N");
	
	$('#delBinMsg').text("");
	
	networkScheme($('#strCardType').val());
	
	$('#strCardType').change(function() {
		networkScheme($(this).val());
	});
	
	$('#cardPlasticTable').DataTable({
		 responsive: true,
		 scrollX: 100,
		 scroller: true
	});
	
	$('#deleteBtn').click(function() {
		
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "${pageContext.request.contextPath}/deleteCardPlastic",
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
						var obj = { Title: '', Url: 'cardPlasticConfigForm' };
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
	
	var value = $("#cardPlasticConfig").validate({
		rules: {
			strCardType: {
                    required: true,
            },
            strVendorID: {
                required: true     
            },
            strCVV: {
            	required: true,
                regex: /^[0-9]+$/,
                minlength: 1,
                maxlength: 4
            },
            strServicePos: {
            	required: true,
                regex: /^[0-9]+$/,
                minlength: 1,
                maxlength: 4
            },
            strExpPos: {
            	required: true,
                regex: /^[0-9]+$/,
                minlength: 1,
                maxlength: 4
            }/* ,
            strSeqPos: {
            	required: true,
                regex: /^[0-9]+$/,
                minlength: 1,
                maxlength: 4
            }, */
		},
    	messages: {
    		strCardType: {
                    required: "Required !",
            },
            strCVV: {
                required: "Please Enter CVV Position !",
                minlength: "Minmum {0} characters required!",
                maxlength: "Maximum {0} characters allowed!",
                regex: "Only Numeric allow !"
            },
            strServicePos: {
                required: "Please Enter Service Position!",
                minlength: "Minmum {0} characters required!",
                maxlength: "Maximum {0} characters allowed!",
                regex: "Only Numeric allow !"
            },
            strExpPos: {
                required: "Please Enter Expiry Date Position!",
                minlength: "Minmum {0} characters required!",
                maxlength: "Maximum {0} characters allowed!",
                regex: "Only Numeric allow !"
            }/* ,
            strSeqPos: {
                required: "Please Enter Card Sequence Position!",
                minlength: "Minmum {0} characters required!",
                maxlength: "Maximum {0} characters allowed!",
                regex: "Only Numeric allow !"
            } */
    	},
		submitHandler: function(form) {
			
			if($('#strSelectID').val() == "N") 
			{
				var flag = false;
				if(form.strCVV.value < 17 || form.strCVV.value > 37) {
					$('#strError').text("CVV Position should be greater than 16 and less than 37");
					flag = true;
				}else if(form.strServicePos.value < 17 || form.strServicePos.value > 37) {
					$('#strError').text("Service Position should be greater than 16 and less than 37");
					flag = true;
				}else if(form.strExpPos.value < 17 || form.strExpPos.value > 37) {
					$('#strError').text("Expiry Position should be greater than 16 and less than 37");
					flag = true;
				}
				if(!flag) 
				{
				var data = eval('${crdPlastic}');
				
				var binVal = form.strCardType.value;
				
				var allValueArray = $('input[type=text]').map(function() {
					if ($(this).val() != '')

					return $(this).val()
					}).get();
				
				var duplicateValueArray = allValueArray.filter(function(element, pos) {
					if(allValueArray.indexOf(element) != pos){
					return true;
					}
					else{
					return false;
					}

					});

				$.each(data, function( index, value ) {
					if(binVal == JSON.stringify(value.strCardType).replace("\"","").replace("\"","")) {
						flag = true;
						return false;
					}
				});
					if(flag) {
						$('#strError').text("Duplicate Card Plastic");
						$('#errorMsg').hide();
					}else {
						if (duplicateValueArray.length > 0){
							$('#strError').text("Duplicate Position !");
							$('#errorMsg').hide();
						}else {
							$('#strError').text("");
							$('#errorMsg').hide();
							form.submit();							
						}	
							
					}
				}
			}else {
				
				var flag = false;
				
				if(form.strCVV.value < 17 || form.strCVV.value > 37) {
					$('#strError').text("CVV Position should be greater than 16 and less than 37");
					flag = true;
				}else if(form.strServicePos.value < 17 || form.strServicePos.value > 37) {
					$('#strError').text("Service Position should be greater than 16 and less than 37");
					flag = true;
				}else if(form.strExpPos.value < 17 || form.strExpPos.value > 37) {
					$('#strError').text("Expiry Position should be greater than 16 and less than 37");
					flag = true;
				}
				if(!flag) 
				{
				cardData.strVendorID = form.strVendorID.value;
				cardData.strCVV = form.strCVV.value;
				cardData.strServicePos = form.strServicePos.value;
				cardData.strExpPos = form.strExpPos.value;
				cardData.strSeqPos = form.strSeqPos.value;

				$.ajax({
					type : "POST",
					contentType : "application/json",
					url : "${pageContext.request.contextPath}/updateCardPlastic",
					data: JSON.stringify(cardData),
					dataType : 'json',
					contentType : "application/json",
					timeout : 100000,
					success : function(data) {
						console.log("SUCCESS: ", data);
						console.log("INSIDE UPDATE CALL: ", data);
						if(data.outResponseCode == "00"){
							$('#strError').addClass("tag tag-green");
							$('#strError').text(data.message);
							setTimeout(function(){// wait for 5 secs(2)
								var obj = { Title: '', Url: 'cardPlasticConfigForm' };
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
				}else {
					return false;
				}
			}		
		
		}
	});
	
});

function editFunction(bin) {     
	
    	var data = eval('${crdPlastic}');
    	$.each(data, function( index, value ) 
    	{
			if(bin == JSON.stringify(value.strCardType).replace("\"","").replace("\"","")) {
				cardData = value;
				return false;
			}
		});
    	networkScheme(cardData.strCardType);
    	$('#strCardType').val(cardData.strCardType);
    	$('#strCardType').attr('readonly', true);
    	$('#strCardType option:not(:selected)').attr('disabled', true);
    	$('#strVendorID').val(cardData.strVendorID);
    	$('#strCVV').val(cardData.strCVV);
    	$('#strServicePos').val(cardData.strServicePos);
    	$('#strExpPos').val(cardData.strExpPos);
    	$('#strSeqPos').val(cardData.strSeqPos);
    	$('#strSelectID').val("Y");
    	
  }

function networkScheme(bin) {
	
	  $.ajax({
			type : "POST",
			contentType : "application/json",
			url : "${pageContext.request.contextPath}/getNetworkScheme",
			data: "id="+bin,
			dataType : 'json',
			contentType : "application/x-www-form-urlencoded",
			timeout : 100000,
			success : function(data) {
				console.log("SUCCESS: ", data);
				if(data.outResponseCode == "00"){
					$('#networkScheme').val(data.message);
				}
			},
			error : function(e) {
				console.log("ERROR: ", e);
			},
			done : function(e) {
				console.log("DONE");
			}
		});
		
		return false;
	  
	}
function deleteFunction(id) {
	$('#delBinMsg').text("");
	$('#strCardType').val("");
	$('#strVendorID').val("");
	$('#strCVV').val("");
	$('#strServicePos').val("");
	$('#strExpPos').val("");
	$('#strSeqPos').val("");
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
				<form:form action="cardPlasticConfig" method="post"
					commandName="cardPlastic" name="cardPlasticConfig"
					id="cardPlasticConfig" cssClass="card">

					<div class="card-header">
						<h3 class="card-title">
							<i class="fe fe-user-plus"></i> &nbsp;&nbsp;Card Plastic
							Configuration <span class="text-right badge badge-danger" id="strError"></span>
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
									<label class="form-label">Card Type <b>*</b></label>
									<form:select path="strCardType" id="strCardType"
										cssClass="form-control selectpicker">
										<form:option value="">Select</form:option>
										<c:forEach items="${cardTypeList}" var="itr">
											<form:option value="${itr.strID}">${itr.strCardType} : ${itr.strCardDesc}</form:option>
										</c:forEach>
									</form:select>
									<span class="error" id="strCardTypeError"><form:errors
											path="strCardType"></form:errors></span>
								</div>
							</div>

							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Emboss Vendor Name <b>*</b></label>
									<form:select path="strVendorID" id="strVendorID"
										cssClass="form-control selectpicker">
										<form:option value=""></form:option>
										<c:forEach items="${embossName}" var="itr">
											<form:option value="${itr.strID}">${itr.strServiceName}</form:option>
										</c:forEach>
									</form:select>
									<span class="error" id="strVendorIDError"><form:errors
											path="strVendorID"></form:errors></span>
								</div>
							</div>

							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">CVV Position <b>*</b></label>
									<form:input path="strCVV" id="strCVV"
										cssClass="form-control" />
									<span class="error" id="strCVVError"><form:errors
											path="strCVV"></form:errors></span>
								</div>
							</div>

							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Service Code Position <b>*</b></label>
									<form:input path="strServicePos" id="strServicePos"
										cssClass="form-control" />
									<span class="error" id="strServicePosError"><form:errors
											path="strServicePos"></form:errors></span>
								</div>
							</div>

							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Expiry Date Position <b>*</b></label>
									<form:input path="strExpPos" id="strExpPos"
										cssClass="form-control" />
									<span class="error" id="strExpPosError"><form:errors
											path="strExpPos"></form:errors></span>
								</div>
							</div>
							
							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Network Scheme</label>
									<input type="text" name="networkScheme" class="form-control" id="networkScheme" readonly="readonly">
									<span class="error" id=""networkScheme"Error"></span>
								</div>
							</div>

							<%-- <div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Card Sequence Position <b>*</b></label>
									<form:input path="strSeqPos" id="strSeqPos"
										cssClass="form-control" />
									<span class="error" id="strSeqPosError"><form:errors
											path="strSeqPos"></form:errors></span>
								</div>
							</div> --%>
							
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
						<table id="cardPlasticTable"
							class="table table-striped table-bordered nowrap"
							style="width: 100%">
							<thead>
								<tr>
									<th>Card Type</th>
									<th>Vendor ID</th>
									<th>CVV Position</th>
									<th>Service Code Position</th>
									<th>Expiry Date Position</th>
									<!-- <th>Card Sequence Position</th> -->
									<th></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="itr" items="${cardPlasticList}">
									<tr>
										<td >${itr.typeDesc}</td>
										<td>${itr.strVendorID}</td>
										<td>${itr.strCVV}</td>
										<td>${itr.strServicePos}</td>
										<td>${itr.strExpPos}</td>
										<%-- <td>${itr.strSeqPos}</td> --%>
										<td><a class="icon" href="javascript:editFunction(${itr.strCardType})">
												<i class="fe fe-edit"></i>
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
					
						<form:hidden path="strSelectID" id="strSelectID"/>
				
				<!-- Modal -->
					<div class="modal fade" id="myModal" role="dialog">
						<div class="modal-dialog modal-sm">
							<div class="modal-content">
								<div class="modal-header">
									<p class="text-capitalize">Delete Card Plastic <label id="delBinMsg"></label></p>
								</div> 
								<div class="modal-body">

									<form>
										<p class="leading-loose">Are you sure you want to delete this Card Plastic ?</p>
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
