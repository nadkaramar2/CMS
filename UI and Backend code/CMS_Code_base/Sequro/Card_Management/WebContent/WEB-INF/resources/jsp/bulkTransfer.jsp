<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<style>

#tableAccount{

}

input[type="checkbox"][readonly] {
  pointer-events: none;
}

</style>
<script>

var fromAccountData = [];
var elementId = 1;

$(document).ready(function() 
{
	
	/* $('.strAccountNo').focusout(function(){
	    alert('left input!');
	  }); */
	
	function uploadExcel() {
		console.log("Inside the upload funtion");
		var formData = new FormData();
		formData.append('file', $('#fileInput')[0].files[0]);
		console.log($('#fileInput')[0].files[0]);
		$.ajax({
				  type : "POST",
				  contentType : false,
				  url : "${pageContext.request.contextPath}/uploadAccountExcelFile",
				  data : formData,
				  dataType : 'json',
				  timeout : 100000,
				  processData: false, // add this line
				  success : function(data) {
				    console.log("File uploaded successfully");
				    console.log(data);
				  },
				  error: function(xhr, status, error) {
					    console.log("Error: " + error);
					    console.log(xhr.responseText);
					  },
				  done : function(e) {
				    console.log("DONE");
				  }
			});
		  /* alert('File uploaded successfully'); */
		}	
	
	$('#fileUpload').on('click', uploadExcel);
	
	var id = 1;
	function addRow(accountType, accountNo, amount) 
	{
		let acnNo = "accountNo_" + elementId;
		let acnHolderName = "accountHolderName_" + elementId;
		let acnAmount = "amount_" + elementId;
		elementId = elementId + 1;
		
		var newRow = $("<tr>");
		var cols = "";
		/* cols += '<td><input type="checkbox" readonly></td>'; */
		cols += '<td><input type="text" name="strAccountNo" id="'+acnNo+'" class="strToAccountNo"></td>';
		cols += '<td><input type="text" name="Account Holder Name" id="'+acnHolderName+'"></td>';
		cols += '<td><input type="text" name="amount" id="'+acnAmount+'" class="amountCss"></td>';
		cols +=	'<td><input type="button" id="validate" class = "validates" value="Add Row"></td>';
		newRow.append(cols);
		$("#accountTable").append(newRow);

			
			console.log("Inside the Add Row funtion");
		
	}
	
	
	
	//$('#addRow').on('click', addRow);
	$("#accountTable").on("click", ".validates", function() 
	{
		var row = $(this).closest("tr");
		var id = row.find("td:eq(0) input").val();
		var accountNo = row.find("td:eq(1) input").val();
		var amount = row.find("td:eq(2) input").val();
		console.log("ID: " + id + ", Account No: " + accountNo + ", Amount: " + amount);
		/* if (!accountNo) 
		{
			alert('Account No Cannot be blank.');
			return false;
		}
		if (!amount) 
		{
			alert('amount Cannot be blank.');
			return false;
		} */
		
		addRow('GEN', accountNo, amount);
		
	});

	$("#accountTable").on("blur", ".amountCss", function() {
		var row = $(this).closest("tr");
		var accountNo = row.find("td:eq(0) input").val();
		var accountHName = row.find("td:eq(1) input").val();
		var amount = row.find("td:eq(2) input").val();
		console.log("ID: " + id + ", Account No: " + accountNo + ", Amount: " + amount);
		if (!accountNo) 
		{
			alert('Account No Cannot be blank.');
			return false;
		}
		if (!amount) 
		{
			alert('amount Cannot be blank.');
			return false;
		}
		
		var fromAccData = accountNo +"="+amount;
		
		fromAccountData.push(fromAccData);
		console.log(fromAccountData);
		
		$('#strToAccountNumberId').val(fromAccountData);
	});
	
	
	function getAccountHolderName(strAccountNo) 
	{
		var strGLAccountType = $('#strToAccountType').val();
		
		var jsonObj = {
				strGLAccountType:strGLAccountType
		};
		
		
		$.ajax(
		{
			type : "POST",
			contentType : "application/json",
			url : "${pageContext.request.contextPath}/getGLDescription",
			data : JSON.stringify(jsonObj),
			dataType : 'json',
			timeout : 100000,
			success : function(data) 
			{			
				/* $('#strToAccountDescription').val(data.strGLAccountDescription);
				$('#strToAccountNumber').val(data.strAccountNumber); */
			},
			error : function(e) 
			{
				console.log("ERROR: ", e);
				$('#strError').addClass("tag tag-red");
				$('#strError').text("Invalid Account Type and Account No");
				$("#strAccountType").blur(function()
				{
					$('#strError').hide();
				});
			},
			done : function(e) 
			{
				console.log("DONE");
			}
		});
	}
	/* Using the Blur Function
	var accountNoInput = document.getElementById("accountNo");
	accountNoInput.addEventListener("blur", function() {
        console.log("Inside Blur Function");
		var accountNo = accountNoInput.value;
    	console.log(accountNo);
	}); */
	
	/* might be in use Later 
	cols +=	'<td><input type="button" id="remove" class = "remove" value="remove"> </td>'; 
	cols += '<td><input type="text" name="id" value="' + id++ + '" readonly></td>';
	cols +=	'<td><input type="button" id="validate" class = "validate" value="Validate"> </td>';*/
	
	
	/* var table = document.getElementById("accountTable");
	console.log(table);
	var row = table.insertRow(-1);
	var cell1 = row.insertCell(0);
	var cell2 = row.insertCell(1);
	var cell3 = row.insertCell(2);
	var cell4 = row.insertCell(3);
	cell1.innerHTML = "<input type='text' name='id' value='" + id++ + "' readonly>";
	cell2.innerHTML = "<input type='text' name='accountNo'>";
	cell3.innerHTML = "<input type='text' name='amount'>";
	cell4.innerHTML = "<input type='button' id='validate' value='Validate'>"; */
	
	
	<%-- <c:forEach items="${bulkTransfer.accountCreationList}" var="account">
	  <script>
	    function addRows() {
	      var newRow = $("<tr>");
	      var cols = "";
	      cols += '<td><input type="checkbox" readonly></td>';
	      cols += '<td><input type="text" name="strAccountNo" class="strAccountNo" value="${account.strAccountNumber}"></td>';
	      cols += '<td><input type="text" name="Account Holder Name" value="${account.strAccountHolderName}"></td>';
	      cols += '<td><input type="text" name="amount" value="${account.strAmount}"></td>';
	      newRow.append(cols);
	      $("#accountTable").append(newRow);
	      console.log("Inside the Add Row function");
	    }
	  </script>
	</c:forEach> --%>
	
});
</script>
<div class="my-3 my-md-5">
	<div class="container">
		<div class="row">
		
			<div class="col-md-3">
				<jsp:include page="bulkTransferLeftMenu.jsp"></jsp:include>
			</div>
			
			<div class="col-9">
				<form:form action="bulkTransfer" method="POST" commandName="bulkTransfer" name="bulkTransfer" id="bulkTransfer" cssClass="card">
					<div class="card-header">
						<h3 class="card-title">
							<i class="fe fe-user-plus"></i> &nbsp;&nbsp;Bulk Transfer
								<span class="text-right badge badge-danger" id="strError"></span> 
								<span class="text-right badge badge-danger" id="strSuccess"></span>
						</h3>
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
									<label class="form-label">Account Type<b>*</b></label>
									 <form:select path="" id="" cssClass="form-control custom-select" onchange="labelValue(this.value);">
										 <form:option value="">Select</form:option> 
										 <c:forEach items="${accAccountType}" var="accountTypes">
												<form:option value="${accountTypes.strAccountType}">${accountTypes.strAccountType}</form:option>
										 </c:forEach>
									</form:select> 
								</div>
							</div>
							
						</div>
						
						<div class="row">
                              <div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Upload Excel<b>*</b></label>
									 <form:input type="file" path="" id="fileInput" cssClass="form-control" />
  									<input type="button" id="fileUpload" value="Upload"  />
								</div>
							</div>
							
						</div>
						
						<div class="row">
							<table id="accountTable"  class="table table-striped table-bordered display nowrap" style="width: 96%">
								<thead>
									<tr>
										<!-- <th>Valid</th> -->
										<th>Account Number</th>
										<th>Account Holder Name</th>
										<th>Amount To transfer</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody>
								
								<c:forEach items="${bulkTransfer.accountCreationList}" var="account" varStatus="status">
      								<tr>
      									<td><c:out value="${status.index + 1}" /></td>
        								<td><form:input path="accountCreationList[${status.index}].strAccountNo" /></td>
        								<td><form:input path="" /></td>
        								<td><form:input path="accountCreationList[${status.index}].strAmount" /></td>
        								<td><form:input path="" type="button" id="validate" class = "validates" value="Add Row" /></td>
      								</tr>
    							</c:forEach>
								
								
								<%-- <c:forEach items="${bulkTransfer.accountCreationList}" var="account">
  								<input type="text" name="bulkTransfer.accountCreationList[${account.index}].strAccountNumber" value="${account.strAccountNumber}" />
  								<input type="text" name="bulkTransfer.accountCreationList[${account.index}].strAccountHolderName" value="" />
  								<input type="text" name="bulkTransfer.accountCreationList[${account.index}].strAmount" value="${account.strAmount}" />
  								<c:out value="${account.strAccountNumber}" />
								</c:forEach> --%>
									<%-- <%-- <c:forEach items="${accountCreationList}" var="account">
  										<input type="text" name="strAccountNumber" value="${account.strAccountNumber}" />
  										<input type="text" name="strAccountHolderName" value="" />
  										<input type="text" name="strAmount" value="${account.strAmount}" />
  										<c:out value="${account.strAccountNumber}" />
									</c:forEach>  --%> 
									<!-- Dynamically Adding the body to the Datbase -->
									
									<td><input type="text" path="" name="strAccountNo" id = "accountNo" class="strAccountNo"></td>
									<td><input type="text" name="Account Holder Name" id="accountHolderName"></td>
									<td><input type="text" name="amount" id="amount" class="amountCss"></td> 
									<td><input type="button" id="validate" class = "validates" value="Add Row"></td> 
								</tbody>
								
							</table>
							<!-- <button id="addRow">Add Row</button> -->
							<!-- <input type="button" id="addRow" value="Add Row"  />  -->
						</div>
					</div>
					
		
					<div class="card-footer text-right">
						<div class=".d-flex1">
							<button type="submit" class="btn btn-primary ml-auto" id="submitBtn">Submit</button>
							<button type="reset" class="btn btn-primary ml-auto" id="resetBtn">Clear</button>
						</div>
					</div>
					<input type="hidden" value="${leftbulkMenuID}" id="leftbulkMenuID">
				
				<form:input type="hidden" path="strToAccountNumber" id="strToAccountNumberId" cssClass="form-control" />
				</form:form>
				
			</div>
			
		</div>
	</div>
</div>


