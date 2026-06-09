<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style>
       #senPwd
       {
           -webkit-text-security:disc;
       }
</style>

<script type="text/javascript">
	var data;
	var jsonReq;
	
	$(document).ready(function() 
	{
		$('#keyTable').DataTable
		({
			 responsive: true
		});
		
		document.getElementById('keyTable').style.textAlign = "center";
		$("#CUSTODIAN2_DIV").hide();
		$("#viewCustodianKeys").hide();
		
		$.validator.addMethod("regex", function(value, element, regexp) 
		{
			var check = false;
			return this.optional(element) || regexp.test(value);
		}, "Please enter a valid input.");
		
		$("#encryptKeyModel").validate({
			rules : 
			{
				 custodian1Password : 
				 {
					required : true,
					minlength : 8,
					maxlength : 20
				},
				custodian2Password : 
				{
					required : true,
					regex: /^\S*$/,
					minlength : 8,
					maxlength : 20
				},
				key : 
				{
					required : true,
					regex: /^\S*$/,
					minlength : 16,
					maxlength : 16
				},
				value : 
				{
					required : true,
					regex: /^\S*$/,
					minlength : 16,
					maxlength : 16
				}
			},
			messages : 
			{
				custodian1Password : 
				{
					required : "Please Enter Custodian 1 Password!",
					minlength : "Minmum {8} characters required!",
					maxlength : "Maximum {20} characters allowed!",
					regex: "Password must to be at least 8 Chars, 1 Number, 1 upper case, 1Lower Case, 1 SpecialChar, No Spaces !"
				},
				custodian2Password : 
				{
					required : "Please Enter Custodian 2 Password!",
					minlength : "Minmum {8} characters required!",
					maxlength : "Maximum {20} characters allowed!",
					regex: "Password must to be at least 8 Chars, 1 Number, 1 upper case, 1Lower Case, 1 SpecialChar, No Spaces !"
				},
				key : 
				{
					required : "Please Enter Custodian 1 Key!",
					regex: "Key must not contain spaces.",
					minlength : "Minmum {16} characters required!",
					maxlength : "Maximum {16} characters allowed!"
					
				},
				value : 
				{
					required : "Please Enter Custodian Key 2!",
					regex: "Value must not contain spaces.",
					minlength : "Minmum {16} characters required!",
					maxlength : "Maximum {16} characters allowed!"
				}
			},
			submitHandler : function(form) 
			{
				form.submit();
			}
		});
		
		$('#pwdBtnForCustodian1').click(function() 
			{
				var pwd = $('#custodian1Password').val();
				jsonObj = [];
				$('#cust1passError').text("");
				item = {}
				item ["custodian1Password"] = pwd;
				jsonObj.push(item);
				$.ajax({
					type : "POST",
					contentType : "application/json",
					url : "${pageContext.request.contextPath}/reEnterKeyValidateCustPassword",
					data: JSON.stringify(jsonObj).replace("[","").replace("]",""),
					dataType : 'json',
					timeout : 100000,
					success : function(data) 
					{
						console.log("SUCCESS: ", data.responseDesc);
						if(data.responseCode == "00")
						{
							$("#submit1_div").show();
							$('#cust1passError').addClass("tag tag-green");
							$('#cust1passError').text(data.message);
							$("#cust1password_div").hide();
							//$('#custodian1Password').clear();
							
						}
						else {
							$('#cust1passError').addClass("tag tag-red");
							$('#cust1passError').text(data.responseDesc);
						}
					},
					error : function(e) {
						console.log("ERROR: ", e);
						$('#cust1passError').addClass("tag tag-red");
						$('#cust1passError').text("Error");
					},
					done : function(e) {
						console.log("DONE");
					}
				});
				
				return false;
			})
			
			
			$('#pwdBtnForCustodian2').click(function() 
			{
				var pwd = $('#custodian2Password').val();
				jsonObj = [];
				$('#cust2passError').text("");
				item = {}
				item ["custodian2Password"] = pwd;
				jsonObj.push(item);
				$.ajax({
					type : "POST",
					contentType : "application/json",
					url : "${pageContext.request.contextPath}/reEnterKeyValidateCustPassword",
					data: JSON.stringify(jsonObj).replace("[","").replace("]",""),
					dataType : 'json',
					timeout : 100000,
					success : function(data) 
					{
						console.log("SUCCESS: ", data.responseDesc);
						if(data.responseCode == "00")
						{
							$("#submit2_div").show();
							$('#cust2passError').addClass("tag tag-green");
							$('#cust2passError').text(data.message);
							$("#cust2password_div").hide();
						}else {
							$('#cust2passError').addClass("tag tag-red");
							$('#cust2passError').text(data.responseDesc);
						}
					},
					error : function(e) {
						console.log("ERROR: ", e);
						$('#cust2passError').addClass("tag tag-red");
						$('#cust2passError').text("Error");
					},
					done : function(e) {
						console.log("DONE");
					}
				});
				
				return false;
			})
		
			
			//Save Custodian Key 1 
				$('#savekeyCustodian1').click(function() 
			    {
				  var key = $('#key').val();
				  jsonObj = [];
				  $('#keypart1error').text("");
					item = {}
					item ["key"] = key;
					jsonObj.push(item);
				  $.ajax({
				    type: "POST",
				    contentType: "application/json",
				    url: "${pageContext.request.contextPath}/saveCustodianReEnterKey1",
				    data: JSON.stringify(jsonObj).replace("[","").replace("]",""), // Send the data as JSON
				    dataType: 'json',
				    timeout:  10000, // Adjusted timeout value
				    success: function(data) { // 'data' parameter added
				      console.log("SUCCESS: ", data.responseDesc);
				      if (data.responseCode == "00") 
				      {
				    	$('#strBinError').addClass("tag tag-green");
					    $('#strBinError').text(data.message);
					    $('#myModal1').modal('hide');
					    $("#CUSTODIAN1_DIV").hide();
					    $("#CUSTODIAN2_DIV").show();
				      }
				      else 
				      {
				        $('#keypart1error').addClass("tag tag-red");
				        $('#keypart1error').text(data.responseDesc);
				      }
				    },
				    error: function(e) 
				    {
				      console.log("ERROR: ", e.responseText); // Log the response text for more details
				      $('#keypart1error').addClass("tag tag-red");
				      $('#keypart1error').text("Error");
				    }
				  });

				  return false; // Prevent the default action of the click event
				})
				
				
				$('#savekeyCustodian2').click(function() 
			    {
				  var value = $('#value').val();
				  jsonObj = [];
				  $('#keypart2error').text("");
					item = {}
					item ["value"] = value;
					jsonObj.push(item);
				  $.ajax({
				    type: "POST",
				    contentType: "application/json",
				    url: "${pageContext.request.contextPath}/saveCustodianReEnterKey2",
				    data: JSON.stringify(jsonObj).replace("[","").replace("]",""), // Send the data as JSON
				    dataType: 'json',
				    timeout:  10000, // Adjusted timeout value
				    success: function(data) { // 'data' parameter added
				      console.log("SUCCESS: ", data.responseDesc);
				      if (data.responseCode == "00") 
				      {
				  		//$('#keypart2error').addClass("tag tag-green");
					    //$('#keypart2error').text(data.message);
					    $('#myModal2').modal('hide');
					    $("#submit2_div").hide();
					    $("#CUSTODIAN2_DIV").hide();
					    $("#viewCustodianKeys").show();
				      }
				      else 
				      {
				        $('#keypart2error').addClass("tag tag-red");
				        $('#keypart2error').text(data.responseDesc);
				      }
				    },
				    error: function(e) {
				      console.log("ERROR: ", e.responseText); // Log the response text for more details
				      $('#keypart2error').addClass("tag tag-red");
				      $('#keypart2error').text("Error");
				    }
				  });

				  return false; // Prevent the default action of the click event
				})
				
				
				//For MaskKey
			$('#pwdSubmitBtn').click(function() 
			{
				var pwd = $('#senPwd').val();
				console.log("Cust 1 Password::::"+pwd);
				jsonObj = [];
				$('#pwdError').text("");
				item = {}
				item ["senPwd"] = pwd;
				jsonObj.push(item);
				$.ajax({
					type : "POST",
					contentType : "application/json",
					url : "${pageContext.request.contextPath}/getClearKeyValue",
					data: JSON.stringify(jsonObj).replace("[","").replace("]",""),
					dataType : 'json',
					timeout : 100000,
					success : function(data) 
					{
						console.log("SUCCESS: ", data.responseDesc);
						if(data.responseCode == "00")
						{
							var responseData;
							$('#pwdError').removeClass("tag tag-red");
							if(data.key == undefined || data.key == null || data.key == '') 
							{
								responseData = "<p>Key1 : No key provided</p>";
							}else 
							{
								responseData = "<p>Key1 : "+ data.key + " </p> <br> <p> Created Date : " + data.createdDate + " </p>";
								
							}
							$('#pwdError').html(responseData);
						}else {
							$('#pwdError').addClass("tag tag-red");
							$('#pwdError').text(data.responseDesc);
						}
					},
					error : function(e) {
						console.log("ERROR: ", e);
						$('#pwdError').addClass("tag tag-red");
						$('#pwdError').text("Error");
					},
					done : function(e) {
						console.log("DONE");
					}
				});
				
				return false;
			})
			
			
			
			$('#pwdSubmitBtnForVal').click(function() 
			{
				var pswd = $('#password').val();
				jsonObj = [];
				$('#pwdsError').text("");
				item = {}
				item ["password"] = pswd;
				jsonObj.push(item);
				$.ajax({
					type : "POST",
					contentType : "application/json",
					url : "${pageContext.request.contextPath}/getClearKeyValue",
					data: JSON.stringify(jsonObj).replace("[","").replace("]",""),
					dataType : 'json',
					timeout : 100000,
					success : function(data) 
					{
						console.log("SUCCESS: ", data.responseDesc);
						if(data.responseCode == "00")
						{
							var responseData;
							$('#pwdsError').removeClass("tag tag-red");
							if(data.value == undefined || data.value == null || data.value == '') 
							{
								responseData = "<p>Key2 : No Value provided</p>";
							}
							else 
							{
								responseData = "<p>Key2 : "+ data.value + " </p> <br> <p> Created Date : " + data.createdDate + " </p>";
							}
							$('#pwdsError').html(responseData);
						}
						else 
						{
							$('#pwdsError').addClass("tag tag-red");
							$('#pwdsError').text(data.responseDesc);
						}
					},
					error : function(e) 
					{
						console.log("ERROR: ", e);
						$('#pwdsError').addClass("tag tag-red");
						$('#pwdsError').text("Error");
					},
					done : function(e) {
						console.log("DONE");
					}
				});
				
				return false;
			})
				
				
				
	});
	
	function showModelCustodian1()
	{
		 $('#myModal1').modal('show');
		 $('#submit1_div').hide();
		 event.preventDefault()
	}
	
	function showModelCustodian2()
	{
		 $('#myModal2').modal('show');
		 $('#submit2_div').hide();
		 event.preventDefault()
	}
	
	
	function showKeyFunction(data) 
	{
		$('#senPwd').val("");
		$('#password').val("");
		$('#pwdError').text("");
		$('#pwdsError').text("");
	}
	
</script>

<div class="my-3 my-md-5">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<form:form action="saveCustodianKey" method="POST" commandName="encryptKeyModel"
					name="encryptKeyModel" id="encryptKeyModel" cssClass="card">
						<div class="card-header">
						<h3 class="card-title">
							<i class="fe fe-user-plus"></i> &nbsp;&nbsp;Re-Enter Key Configuration <span
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
						
							<div id="CUSTODIAN1_DIV">
							<div class="card-body">
								<div class="row">
									<div class="col-md-4 col-lg-4">
										<div class="btn-list text-left">
								<button type="submit" id="Custodian1" name="custodian1" onclick="showModelCustodian1()"
									value="1" class="btn btn-primary">Custodian 1</button>
								</div>
									</div>
									</div>
							</div>
							</div>
							
							
							<div id="CUSTODIAN2_DIV">
							<div class="card-body">
								<div class="row">
									<div class="col-md-4 col-lg-4">
										<div class="btn-list text-left">
								<button type="submit" id="btncustodian1" name="custodian2" onclick="showModelCustodian2()"
									value="1" class="btn btn-primary">Custodian 2</button>
								</div>
									</div>
									</div>
							</div>
							</div>
					
				  <div id="viewCustodianKeys">
                 <div class="card-body" style="width: 100%;" align="left">
                <table id="keyTable" class="table table-striped table-bordered nowrap">
            <thead>
                <tr>
                    <th>Key1</th>
                    <th>Key2</th>
                </tr>
            </thead>
            <tbody>
                <tr> <!-- Each row of data should be wrapped in a <tr> tag -->
                    <td> 
                      <c:out value="${encryptKeyModel.key}" escapeXml="true"/>
                    </td>
                    <td>
                      <c:out value="${encryptKeyModel.value}" escapeXml="true"/>
                    </td>
                </tr> <!-- End of <tr> -->
            </tbody>
        </table>
    </div>
</div>
					
					<!--  <div id="viewCustodianKeys">
                 <div class="card-body" style="width: 100%;" align="left">
                <table id="keyable" class="table table-striped table-bordered nowrap">
            <thead>
                <tr>
                    <th>Key1</th>
                    <th>Key2</th>
                </tr>
            </thead>
            <tbody>
                <tr> 
                    <td data-toggle="modal" data-target="#myModal3">
                        <a href="javascript:showKeyFunction()">
                            <c:out value="${encryptKeyModel.key}" escapeXml="true"/>
                        </a>
                    </td>
                    <td data-toggle="modal" data-target="#myModal4">
                        <a href="javascript:showKeyFunction()">
                            <c:out value="${encryptKeyModel.value}" escapeXml="true"/>
                        </a>
                    </td>
                </tr> 
            </tbody>
        </table>
    </div>
</div>  -->
					
					
					<!-- Modal -->
					<div class="modal fade" id="myModal1" role="dialog">
						<div class="modal-dialog modal-lg">
							<div class="modal-content">
								<div class="modal-header">
									<h4 class="modal-title">Show Clear Key 1</h4>
								</div>
								<div class="modal-body" id = "cust1password_div">

									<form>
										<div class="form-group">
											<label for="recipient-name" class="col-form-label">Password:</label>
											<input type="password" name="custodian1Password" id="custodian1Password" class="form-control">
										</div>
									<div class="modal-footer">
								 <button type="button" class="btn btn-primary" id="pwdBtnForCustodian1" >Submit</button>
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Close</button>
									</div>
										<div class="form-group">
											<span id="cust1passError"></span>
										</div>
									</form>
								</div>

							<div class="modal-body" id ="submit1_div">
							<form id ="encryptKeyModel">
								<div class="form-group">
											<label for="recipient-name" class="col-form-label">Enter Key Part 1</label>
											<input type="text" name="key" id="key" class="form-control">
										</div>
							<div class="modal-footer">
								 <button type="button" class="btn btn-primary" id="savekeyCustodian1" >Submit</button>
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Close</button>
							</div>
							<div class="form-group">
								<span id="keypart1error"></span>
									</div>
							</form>
							</div>
						</div>
					</div>
			</div>
			<!-- Modal -->
			
			<!-- Modal2 -->
					<div class="modal fade" id="myModal2" role="dialog">
						<div class="modal-dialog modal-lg">
							<div class="modal-content">
								<div class="modal-header">
									<h4 class="modal-title">Show Clear Key 1</h4>
								</div>
								<div class="modal-body" id = "cust2password_div">
									<form>
										<div class="form-group">
											<label for="recipient-name" class="col-form-label">Password:</label>
											<input type="password" name="custodian2Password" id="custodian2Password" class="form-control">
										</div>
									<div class="modal-footer">
								 <button type="button" class="btn btn-primary" id="pwdBtnForCustodian2" >Submit</button>
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Close</button>
									</div>
										<div class="form-group">
											<span id="cust2passError"></span>
										</div>
									</form>
								</div>

							<div class="modal-body" id = "submit2_div">
							<form id ="encryptKeyModel">
								<div class="form-group">
											<label for="recipient-name" class="col-form-label">Enter Key Part 2</label>
											<input type="text" name="value" id="value" class="form-control">
										</div>
							<div class="modal-footer">
								 <button type="button" class="btn btn-primary" id="savekeyCustodian2" >Submit</button>
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Close</button>
							</div>
							<div class="form-group">
								<span id="keypart2error"></span>
									</div>
							</form>
							</div>
						</div>
					</div>
			</div>
			<!-- Modal2 -->	
			
			
			<!-- Modal -->
					<div class="modal fade" id="myModal3" role="dialog">
						<div class="modal-dialog modal-lg">
							<div class="modal-content">
								<div class="modal-header">
									<h4 class="modal-title">Show Clear Key 1</h4>
								</div>
								<div class="modal-body">

									<form>
										<div class="form-group">
											<label for="recipient-name" class="col-form-label">Password:</label>
											<input type="password" name="senPwd" id="senPwd" class="form-control">
										</div>
										<div class="form-group">
											<span id="pwdError"></span>
										</div>
									</form>
								</div>

								
							<div class="modal-footer">
								 <button type="button" class="btn btn-primary" id="pwdSubmitBtn" >Submit</button>
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Close</button>
							</div>
						</div>
					</div>
			</div>
			<!-- Modal -->
			
			<!-- Modal2 -->
					<div class="modal fade" id="myModal4" role="dialog">
						<div class="modal-dialog modal-lg">
							<div class="modal-content">
								<div class="modal-header">
									<h4 class="modal-title">Show Clear Key 2</h4>
								</div>
								<div class="modal-body">

									<form>
										<div class="form-group">
											<label for="recipient-name" class="col-form-label">Password:</label>
											<input type="password" name="password"  id="password" class="form-control">
										</div>
										<div class="form-group">
											<span id="pwdsError"></span>
										</div>
									</form>
								</div>

								
							<div class="modal-footer">
								 <button type="button" class="btn btn-primary" id="pwdSubmitBtnForVal">Submit</button>
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Close</button>
							</div>
						</div>
					</div>
			</div>
			<!-- Modal2 -->		
			
			
			
			</form:form>
			</div>
		</div>
	</div>
</div>