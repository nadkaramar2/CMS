<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<style>
.rowData {
	width: 100% !important;
	display: flex !important;
	margin-left: -5px !important;
	margin-right: -5px !important;
}

.columnData {
	padding: 5px !important;
}

/* .col-md-6 {
	max-width: 46% !important;
} */

#bulkTable {
	font-family: Arial, Helvetica, sans-serif;
	border-collapse: collapse;
	width: 100% !important;
}

#bulkTable td, #bulkTable th {
	border: 1px solid #ddd;
	padding: 8px;
}

#bulkTable th {
	padding-top: 12px;
	padding-bottom: 12px;
	text-align: left;
}

#bulkTable1 {
	width: 100% !important;
	/* position: absolute;
    padding-top: 38px;  */
}

#bulkTable1 td, #bulkTable1 th {
	border: 1px solid #ddd;
	padding: 8px;
}

#bulkTable1 th {
	padding-top: 12px;
	padding-bottom: 12px;
	text-align: left;
}

#bulkTable2 {
	 font-family: Arial, Helvetica, sans-serif;
	border-collapse: collapse;
	width: 100% !important;
	/* position: absolute;
	padding-top: 38px; 
	padding-left: 23px;
	*/
}

#bulkTable2 td, #bulkTable2 th {
	border: 1px solid #ddd;
	padding: 10px;
}

#bulkTable2 th {
	width: 100% !important;
	padding-top: 12px;
	padding-bottom: 12px;
	text-align: left;
}

#bulkTable1_paginate {
	display: none !important;
}
</style>


<script type="text/javascript">
var tbl;
var tbl1;
var tbl2;
	$(document).ready(function() {

		tbl = $('#bulkTable').DataTable({
		//responsive : true,
		//aaSorting : [ [ 1, 'desc' ] ]
		});

		tbl1 = $('#bulkTable1').DataTable({
			//responsive : true,
			paging : false,
			ordering : false,
			info : false,
			searching : false
		//aaSorting : [ [ 1, 'desc' ] ]
		});

		tbl2 = $('#bulkTable2').DataTable({
			//responsive : true,
			paging : false,
			ordering : false,
			info : false,
			searching : false
		//aaSorting : [ [ 1, 'desc' ] ]
		});
		table = $('#bulkDataTable').DataTable({
			scrollX : 100,
			scroller : true
		});
		table.clear();
	});
</script>

<script type="text/javascript">
function getserverData(url,type,successCallBack,errorCallback)
{
	$.ajax({
		type : type,
		contentType : "application/json",
		url : url,
		dataType : 'json',
		timeout : 100000,
		success : function(data) {
		successCallBack(data);
		},
		error : function(e) {
			console.log("ERROR: ", e);
			alert.log("There is an error in Data")
			errorCallback(e);
		}
	});
}



function addCardData(data)
{
	for (let i = 0; i < data.length; i++)
	{
		tbl.row.add([data[i].cardType,data[i].cardCount]);				
	}
	tbl.draw();

	console.log(data[0]);
	if (data[0] == null) {
		alert(" Data Not Found in Table: " + data);
	}
}

function addNetworkData(data) 
{
	for (let i = 0; i < data.length; i++)
	{			
		tbl1.row.add([data[i].network_type, data[i].network_count]);				
	}
	tbl1.draw();
	
	console.log(data[0]);
	if (data[0] == null) {
		alert(" Data Not Found in Table: " + data);

	}	
}
	
   function addTotalHostListedData(data){	 
	  tbl2.row.add([data]);
		tbl2.draw();
	
	 if(data[0] = null)
		{
		alert(" Data Not Found in Table: "+data);
		}  
	} 

</script>
<script type="text/javascript">
	function compareDate(startDate, endDate) {
		if (endDate > startDate) {
			return false;
		} else {
			return true;

		}

	}
	
	function searchbtn() 
	{
		tbl.clear();
		tbl1.clear();
		tbl2.clear();
		var fromDate = document.getElementById("fromDate").value;
		if (fromDate == null || fromDate == '')
		{
			alert('Please Enter From Date');
			return false;
		}

		var toDate = document.getElementById("toDate").value;
		if (toDate == null || toDate == '') {
			alert('Please Enter To Date');
			return false;
		}
		
		if (compareDate(fromDate, toDate)) 
		{
			alert('toDate Should be greater than fromdate')
		} 
		else
		{			
			getserverData("${pageContext.request.contextPath}/getHotListingMIS/"+ fromDate + "/" + toDate, "POST", function(data) 
			{				
				addCardData(data);
				getserverData("${pageContext.request.contextPath}/hotListingMISNetwork/"+ fromDate + "/" + toDate, "POST", function(resData) 
				{
					addNetworkData(resData);
					
					
					getserverData("${pageContext.request.contextPath}/totalHotListingMIS/"+ fromDate + "/" + toDate, "POST", function(data) 
							
							{
					console.log("addTotalHostListedData"+data)
					addTotalHostListedData(data)
							
				},
				function(err)
				{
					console.log("network error Shows"+err);
				});

			},
			function(error)
			{
				console.log("error Shows"+error);
			});
		
			},	
			function(error1)
			{
				console.log("error Shows"+error);
			});
			
		} 		
		return false;
	}
</script>


<div class="my-3 my-md-5">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<form:form action="cardHotListingMIS" method="get"
					commandName="cardHotListing" name="cardHotListing"
					id="cardHotListing" enctype="multipart/form-data" cssClass="card"
					onSubmit="return false">
					<div class="card-header">
						<h3 class="card-title">Card Hot Listing MIS</h3>
						<h2 id="errorMsg" class="tag tag-red"></h2>
						<div class="messages" style="display: ${display};" id="errorMsg">
							<c:if test="${not empty exception}">
								<div class="badge badge-danger" style="display: ${display};">
									<c:out value="${exception}"></c:out>
								</div>
							</c:if>
							<c:if test="${not empty success}">
								<div class="badge badge-success" style="display: ${display};">
									<c:out value="${success}"></c:out>
								</div>
							</c:if>
							<c:if test="${not empty error}">
								<div class="badge badge-danger" style="display: ${display};">
									<c:out value="${error}"></c:out>
								</div>
							</c:if>
						</div>
					</div>

					<div class="card-body">
						<div class="row">
							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">From Date <b>*</b></label>
									<div class='input-group date'>
										<form:input path="" id="fromDate" cssClass="form-control"
											readonly="false" type="date" />
										<span class="input-group-addon input-group-append"
											id="basic-addon2"> <span class="input-group-text">
												<!-- <i	class="fe fe-calendar"></i> -->
										</span>
										</span>
									</div>
								</div>
							</div>
							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">To Date <b>*</b></label>
									<div class='input-group date'>
										<form:input path="" id="toDate" cssClass="form-control"
											readonly="false" type="date" />
										<span class="input-group-addon input-group-append"
											id="basic-addon2"> <span class="input-group-text">
												<!-- <i
												class="fe fe-calendar"></i> -->
										</span>
										</span>
									</div>
								</div>
							</div>
							<div>
							<div class="col-md-6 col-lg-6">
								<div class=".d-flex1">
									<button type="search" class="btn btn-primary ml-auto"
										style="text-align right:" id="searchBtn" onClick="searchbtn();">Search</button>
								</div>
								</div>
							</div>
							
						</div>
						<div style=" height: 20px;"></div>
						<div class="rowData">							
							<div style="width: 70% !important; padding-right: 4px;">
								<table id="bulkTable1">
									<thead>
										<tr>
											<th>Network</th>
											<th>Count</th>
										</tr>
									</thead>
								</table>
							</div>
							<div style="width: 30% !important;">
								<table id="bulkTable2">
									<thead>
										<tr>
											<th>Hot Listing Count</th>
										</tr>
									</thead>
								</table>
							</div>
						</div>
						<div class="columnData" style="width: 70% !important;">
								<table id="bulkTable">
									<thead>
										<tr>
											<th>Card Type</th>
											<th>Count</th>
										</tr>
									</thead>									
								</table>
							</div>
						
						
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>