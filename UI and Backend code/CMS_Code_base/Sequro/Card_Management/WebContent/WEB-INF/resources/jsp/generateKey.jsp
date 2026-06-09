<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
		$("#CUSTODIAN1_DIV").hide();
		$("#CUSTODIAN2_DIV").hide();
		
		$('#generateKeyBtn').click(function() 
		{
			$.ajax({
				type : "POST",
				contentType : "application/json",
				url : "${pageContext.request.contextPath}/generateCardEncDecKey",
				dataType : 'json',
				contentType : "application/x-www-form-urlencoded",
				timeout : 100000,
				success : function(data) 
				{
					console.log("SUCCESS: ", data);
					if(data.outResponseCode == "00")
					{
						$("#CUSTODIAN1_DIV").show();
						$("#CUSTODIAN2_DIV").hide();
						$("#generateKey_div").hide();
						$('#strBinError').removeClass("tag tag-red");
						//$('#strBinError').text(data.message);
						setTimeout(function()
						{// wait for 5 secs(2)
							var obj = { Title: '', Url: 'generateKey' };
					        history.pushState(obj, obj.Title, obj.Url);
 				           location.reload(); // then reload the page.(3)
 				      }, 100000); 
					}
					else 
					{
						$('#strBinError').addClass("tag tag-red");
						$('#strBinError').text(data.message);
					} 
				},
				error : function(e) 
				{
					console.log("ERROR: ", e);
					$('#strBinError').addClass("tag tag-red");
					$('#strBinError').text("Error");
				},
				done : function(e) 
				{
					console.log("DONE");
				}
			});
			return false;
		})
		
		
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
					url : "${pageContext.request.contextPath}/getGeneratedKeyValue",
					data: JSON.stringify(jsonObj).replace("[","").replace("]",""),
					dataType : 'json',
					timeout : 100000,
					success : function(data) 
					{
						console.log("SUCCESS: ", data.responseDesc);
						if(data.responseCode == "00")
						{
							var responseData;
							responseData = data.key;
							console.log("Custodian Key Part 1::::"+responseData);
							$("#cust1password_div").hide();
							$('#cust1passError').text(data.message);
							$("#submit1_div").show();
							$('#key').val(responseData);
							
						}else {
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
					url : "${pageContext.request.contextPath}/getGeneratedKeyValue",
					data: JSON.stringify(jsonObj).replace("[","").replace("]",""),
					dataType : 'json',
					timeout : 100000,
					success : function(data) 
					{
						console.log("SUCCESS: ", data.responseDesc);
						if(data.responseCode == "00")
						{
							var responseData;
							responseData = data.value;
							$("#cust2password_div").hide();
							$('#cust2passError').text(data.message);
							$("#submit2_div").show();
							$('#value').val(responseData);
						}
						else 
						{
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
		
			
			$('#savekeyCustodian1').click(function() 
			{
				  var keyValue = $('#key').val();
				  console.log("Inside savekeyCustodian1");
				  var dataToSend = 
				  {
				    key: keyValue
				  };

				  $.ajax({
				    type: "POST",
				    contentType: "application/json",
				    url: "${pageContext.request.contextPath}/saveCustodianKey1",
				    data: JSON.stringify(dataToSend), // Send the data as JSON
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
				        $('#strBinError').addClass("tag tag-red");
				        $('#strBinError').text(data.responseDesc);
				      }
				    },
				    error: function(e) {
				      console.log("ERROR: ", e.responseText); // Log the response text for more details
				      $('#strBinError').addClass("tag tag-red");
				      $('#strBinError').text("Error");
				    }
				  });

				  return false; // Prevent the default action of the click event
				})
				
				
				$('#savekeyCustodian2').click(function() 
				{
				    var Value = $('#value').val();
				    console.log("Inside Key Submit Part  2" + Value);
				    var dataToSend = 
				    {
				        value: Value
				    };

				    $.ajax({
				        type: "POST",
				        contentType: "application/json",
				        url: "${pageContext.request.contextPath}/saveCustodianKey2",
				        data: JSON.stringify(dataToSend), // Send the data as JSON
				        dataType: 'json',
				        timeout:  10000, // Adjusted timeout value
				        success: function(data) { // 'data' parameter added
				            console.log("SUCCESS: ", data.responseDesc);
				            if (data.responseCode == "00") {
				                $('#strBinError').addClass("tag tag-green");
				                $('#strBinError').text(data.message);
				                $("#CUSTODIAN1_DIV").hide();
				                $("#CUSTODIAN2_DIV").hide();
				                $("#generateKey_div").hide();
				                
				                $('#myModal2').modal('hide'); // Close the modal
				            } else {
				                $('#strBinError').addClass("tag tag-red");
				                $('#strBinError').text(data.responseDesc);
				            }
				        },
				        error: function(e) {
				            console.log("ERROR: ", e.responseText); // Log the response text for more details
				            $('#strBinError').addClass("tag tag-red");
				            $('#strBinError').text("Error");
				        }
				    });

				    return false; // Prevent the default action of the click event
				});

	});
	
	
	function showModelCustodian1()
	{
		 $('#myModal1').modal('show');
		 $("#submit1_div").hide();
		 event.preventDefault()
	}
	
	function showModelCustodian2()
	{
		 $('#myModal2').modal('show');
		 $("#submit2_div").hide('show');
		 event.preventDefault()
	}
	
</script>

<div class="my-3 my-md-5">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<form:form action="#" method="GET" commandName="encryptKeyModel"
					name="encryptKeyModel" id="encryptKeyModel" cssClass="card">
						<div class="card-header">
						<h3 class="card-title">
							<i class="fe fe-user-plus"></i> &nbsp;&nbsp;Generate Key Configuration <span
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
						
							<div id = "generateKey_div">
							<div class="modal-header">
							<div class="btn-list text-left">
								<button type="submit" id="generateKeyBtn" name="strRequestBtn"
									value="1" class="btn btn-primary">Generate Key</button>
							</div>
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

							<div class="modal-body" id = "submit1_div">
							<form>
								<div class="form-group">
											<label for="recipient-name" class="col-form-label">Custodian Key 1</label>
											<input type="text" name="key" id="key" value = "key" readonly="readonly"
											 class="form-control">
										</div>
							<div class="modal-footer">
								 <button type="button" class="btn btn-primary" id="savekeyCustodian1" >Submit</button>
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Close</button>
							</div>
							<div class="form-group">
								<span id="strBinError"></span>
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
									<h4 class="modal-title">Show Clear Key 2</h4>
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
							<form>
							
								<div class="form-group">
											<label for="recipient-name" class="col-form-label">Custodian Key 2</label>
											<input type="text" name="value" id="value" readonly="readonly" class="form-control">
										</div>
							<div class="modal-footer">
								 <button type="button" class="btn btn-primary" id="savekeyCustodian2" >Submit</button>
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Close</button>
							</div>
							<div class="form-group">
								<span id="strBinError"></span>
									</div>
							</form>
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