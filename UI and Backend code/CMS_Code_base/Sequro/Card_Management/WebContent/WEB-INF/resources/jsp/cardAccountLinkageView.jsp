	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Changes By Jyoti Shirahatti Date-07-12-2022 -->
<script type="text/javascript">

	var table;
	$(document).ready(function()
	{
		showHideDiv($('#type').val());
		$('#type').change(function() 
		{
			showHideDiv($(this).val());
		});
		 
		table = $('#clientTable').DataTable(
		{
			scrollX: 100,
			scroller: true,
			orderCellsTop: true,
		    fixedHeader: true
		 });
	});
	
	//Changes By Jyoti Shirahatti Date-01-12-2022
	function showHideDiv(value) 
	{
		if(value == "select")
		{
			$('#accountDiv').hide();
			$('#cardDiv').hide();
		}
		else if (value == 'Card') 
		{
			$('#cardDiv').show();
			$('#accountDiv').hide();
		} 
		else 
		{
			$('#cardDiv').hide();
			$('#accountDiv').show();
		}
	}
	
	function searchbtn() 
	{
		$("#searchClientForm").submit(function(e) 
		{
		    e.preventDefault();
		});
		
		console.log(document.getElementById("type").value);
		if(document.getElementById("type").value == "Account")
		{
			var accountType = document.getElementById("strSearchType").value;
			var accountNumber = document.getElementById("strAccountNumber").value;
			
			if (accountType == null || accountType == '') 
			{
				alert('Please Select Account Type');
				return false;
			}
	
			if (accountNumber == null || accountNumber == '') 
			{
				alert('Please Enter Account Number');
				return false;
			}
			
			table.clear();
			
			var jsonRequestData = {
					strAccountType:accountType,
					strAccountNumber:accountNumber
			};
			
			$.ajax(
			{
				type : "POST",
				contentType : "application/json",
				//url : "${pageContext.request.contextPath}/getAccountLinkageData/" + accountType + "/" + accountNumber,
				url : "${pageContext.request.contextPath}/getAccountLinkageData",
				data: JSON.stringify(jsonRequestData),
				dataType : 'json',
				timeout : 100000,
				success : function(data) 
				{
					if (!data) 
					{
						alert('No Data Found.');
						return false;
					}
					if(data.length == 0)
					{
						alert('No Data Found.');
						return false;
					}
					for (let i = 0; i < data.length; i++) 
					{
	                       console.log(data[i]);
						table.row.add(["<a href =viewAccountDetailsForm?strAccountNumber="+data[i].strAccountNumber+"&strCardNumber="+data[i].strCardNumber+">"+data[i].strCardNumber+"</a>",
								"<a href = viewAccountDetailsForm?strAccountNumber="+data[i].strAccountNumber+"&strCardNumber="+data[i].strCardNumber+">"+data[i].strAccountNumber+"</a>"]);
					}
					table.draw();
				},
				error : function(e) {
					console.log("ERROR: ", e);
					alert("There is an error in Data")
				},
				done : function(e) {
					console.log("DONE");
				}
	
			});
		} 
		else 
		{
			var cardType = document.getElementById("strCardType").value;
			var cardNumber = document.getElementById("strCardNumber").value;
			if (cardType == null || cardType == '') 
			{
				alert('Please Select Card Type');
				return false;
			}

			if (cardNumber == null || cardNumber == '') 
			{
				alert('Please Enter Card Number');
				return false;
			}
		
			var jsonReq = {
					strCardType:cardType,
					strCardNumber:cardNumber
			};
			
			$.ajax(
			{
					type : "POST",
					contentType : "application/json",
					//url : "${pageContext.request.contextPath}/getAccountLinkageDataCard/"+ cardType + "/" + cardNumber,
					url : "${pageContext.request.contextPath}/getAccountLinkageDataCard",
					data: JSON.stringify(jsonReq),
					dataType : 'json',
					timeout : 100000,
					success : function(data)
					{
						if (!data) 
						{
							alert('No Data Found.');
							return false;
						}
						if(data.length == 0)
						{
							alert('No Data Found.');
							return false;
						}
						
						for (let i = 0; i < data.length; i++)
						{
							/* table.row.add(["<a href = viewAccountDetailsForm>"+data[i].strCardNumber+"</a>",
									"<a href = viewAccountDetailsForm>"+data[i].strAccountNumber+"</a>"]); */
							
							table.row.add(["<a href =viewAccountDetailsForm?strAccountNumber="+data[i].strAccountNumber+"&strCardNumber="+data[i].strCardNumber+">"+data[i].strCardNumber+"</a>",
								"<a href = viewAccountDetailsForm?strAccountNumber="+data[i].strAccountNumber+"&strCardNumber="+data[i].strCardNumber+">"+data[i].strAccountNumber+"</a>"]);
						}
						table.draw();
						
						$('#mainTableDiv').fadeIn();
						$('#headerDiv').fadeIn();
					},
					error : function(e) 
					{
						console.log("ERROR: ", e);
						alert("There is an error in Data")
					},
					done : function(e) 
					{
						console.log("DONE");
					}
				});
		}
		return false;
	}
</script>

<div class="my-3 my-md-5">
	<div class="container">
		<div class="row">
			<!-- <div class="col-12"> -->
			<div class="col-md-3">
				<jsp:include page="linkCardLeftMenu.jsp"></jsp:include>
			</div>
			<div class="col-9">
				<%-- <form:form action="cardAccountLinkageView" method="post" commandName="clientBean" name="searchClientForm" id="searchClientForm" cssClass="card" enctype="multipart/form-data" onSubmit="return false"> --%>
				<form:form action="viewAccountDetailsForm" method="post" commandName="clientBean" name="clientBean" id="searchClientForm" cssClass="card">	
					<div class="card-header">
						<h3 class="card-title">Card Account Linkage View</h3>
					</div>					
					<div class="card-body">
					<!-- Changes By Jyoti Shirahatti date-01-12-2022 -->
						<div class="row">
							<div class="col-md-6 col-lg-4">
								<div class="form-group">
									<label class="form-label">Search By <b>*</b></label> 
										<select name="type" id="type" class="form-control selectpicker">
											<option value="select">Select</option>
											<option value="Card">Card</option>
											<option value="Account">Account</option>
									</select>
								</div>
							</div>
						</div>
						<!-- <br> -->
						<div class="row" id="accountDiv">
							<div class="col-md-6 col-lg-3">
								<div class="form-group">
									<label class="form-label">Select Account Type</label>
									<form:select path="strSearchType" id="strSearchType" cssClass="form-control selectpicker">
										<form:option value="">Select</form:option>
										<c:forEach items="${accountType}" var="itr">
											<form:option value="${itr.strAccountType}">${itr.strAccountType} - ${itr.strDescription}</form:option>
										</c:forEach>
									</form:select>
								</div>
							</div>
							<div class="col-md-6 col-lg-3">
								<div class="form-group">
									<label class="form-label">Account Number</label>
									<form:input path="strAccountNumber" id="strAccountNumber" cssClass="form-control" />
								</div>
							</div>
							</div>
							<div class="row" id="cardDiv">
							<div class="col-md-6 col-lg-3">
								<div class="form-group">
									<label class="form-label">Select Card Type</label>
									<form:select path="strCardType" id="strCardType" cssClass="form-control selectpicker">
										<form:option value=""></form:option>
										<c:forEach items="${cardTypeList}" var="itr">
											<form:option value="${itr.strCardType}">${itr.strCardType} : ${itr.strCardDesc}</form:option>
										</c:forEach>
									</form:select>
								</div>
							</div>
							<div class="col-md-6 col-lg-3">
								<div class="form-group">
									<label class="form-label">Card Number</label>
									<form:input path="strCardNumber" id="strCardNumber" cssClass="form-control" />
								</div>
							</div>
							</div>							
						</div>			   
					<div class="text-right" style="margin-bottom: 14px; margin-right: 14px;">
						<div class=".d-flex1">
							<button type="search" class="btn btn-primary ml-auto" style="text-align: right:" id="searchBtn" onClick="searchbtn()">Search</button>
						</div>
					</div>
					<div class="card-header" style="min-height: 0px !important; margin-bottom: 14px !important;" ></div>
					<div class="card-body">
						<table id="clientTable" class="table table-striped table-bordered nowrap" style="width: 100%">
							<thead>
								<tr>
									<th>Card Number</th>
									<th>Account Number</th>
								</tr>
							</thead>							
						</table>
						<input type="hidden" value="${leftLinkCardMenuId}" id="leftLinkCardMenuId">	
					</div>
				</form:form>
				</div>
			</div>
		</div>
	</div>
</div>