<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	var tbl;
	var hotlistingDataJson;
	
	$(document).ready(function() {
		networkScheme($('#strCardType').val());
		$('#strCardType').change(function() 
		{
			networkScheme($(this).val());
		});
		tbl = $('#bulkTable').DataTable({
			 responsive : true
			//aaSorting : [ [ 1, 'desc' ] ]
		
		});
		
		
		$('#pdfButton')
		.click(
				function() {
					console.log('Downloading PDF File...............');
					
					$('#download_spinner').css({
						"display" : "contents"
					});
					$('#download_message').html('Downloading pdf file...');
					var _url = "${pageContext.request.contextPath}/cms-reports/downloadHotListingReport/";
					
					console.log(hotlistingDataJson);
					try {
						$.ajax({
							type : "POST",
							contentType : "application/json",
							url : _url,
							data : JSON.stringify(hotlistingDataJson),
							dataType : 'json',
							contentType : "application/json",
							timeout : 100000,
							success : function(data) {
								console.log("SUCCESS: ", data);
								var link = document.createElement('a');
								link.href = data.message;
								//$('#download_spinner').fadeOut();
								$('#download_spinner').css({
									"display" : "none"
								});
								link.click();
							},
							error : function(e) {
								console.log("ERROR: ", e);
							},
							done : function(e) {
								console.log("DONE");
							}
						});
					} 
					catch (e) 
					{
						console.log('Exception while get file path::' + e);
					}
				});

$('#excelButton')
		.click(
				function() 
				{
					console.log('Downloading Excel File...............');
					//$('#download_spinner').fadeIn();
					$('#download_spinner').css({
						"display" : "contents"
					});
					$('#download_message').html('Downloading excel file...');
					var _url = "${pageContext.request.contextPath}/cms-reports/downloadExcelHotListingReport/";
					console.log(hotlistingDataJson);
					try {
						$.ajax({
							type : "POST",
							contentType : "application/json",
							url : _url,
							data : JSON.stringify(hotlistingDataJson),
							dataType : 'json',
							contentType : "application/json",
							timeout : 100000,
							success : function(data) {
								console.log("SUCCESS: ", data);
								var link = document.createElement('a');
								link.href = data.message;
								//$('#download_spinner').fadeOut();
								$('#download_spinner').css({
									"display" : "none"
								});
								link.click();
							},
							error : function(e) {
								console.log("ERROR: ", e);
							},
							done : function(e) {
								console.log("DONE");
							}
						});
					} catch (e) {
						console.log('Exception while get file path::' + e);
					}
				});
	});


	function networkScheme(bin) 
	{
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "${pageContext.request.contextPath}/getNetworkScheme",
			data : "id=" + bin,
			dataType : 'json',
			contentType : "application/x-www-form-urlencoded",
			timeout : 100000,
			success : function(data) {
				console.log("SUCCESS: ", data);
				if (data.outResponseCode == "00") {
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

	function compareDate(startDate, endDate) 
	{
		if (endDate > startDate) 
		{
			return false;
		} 
		else 
		{
			return true;

		}

	}

	function searchbtn() 
	{
		tbl.clear();

		var fromDate = document.getElementById("fromDate").value;

		if (fromDate == null || fromDate == '')
		{
			alert('Please Enter From Date');
			return false;
		}

		var toDate = document.getElementById("toDate").value;

		if (toDate == null || toDate == '') 
		{
			alert('Please Enter To Date');
			return false;
		}
		console.log("search fromdate::" + fromDate + " toDate ::" + toDate);

		if (compareDate(fromDate, toDate)) {
			alert('toDate Should be greater than fromdate')
		} else {
			$.ajax({
				type : "POST",
				contentType : "application/json",
				url : "${pageContext.request.contextPath}/getCardHotListing/"
						+ fromDate + "/" + toDate,
				dataType : 'json',
				timeout : 100000,
				success : function(data) 
				{
					hotlistingDataJson = data;
					for (let i = 0; i < data.length; i++) 
					{
						tbl.row.add([ 
							data[i].card_issue_date,
							data[i].cardType,
							data[i].card_number,
							data[i].card_status_changed_date,
							data[i].card_status_changed_time,
							data[i].card_status_description,
							data[i].status_change_user ]);

					}
					console.log(data[0]);
					if (data[0] == null) {
						alert(" Data Not Found in Table: " + data);
						tbl.clear();
					}
				},
				error : function(e) {
					console.log("ERROR: ", e);
					alert.log("There is an error in Data")
				},
				done : function(e) {
					console.log("DONE");
				}

			});

		}
		return false;
	}
</script>




<div class="my-3 my-md-5">
	<div class="container">
	<div id="download_spinner" style="display:none;">
			<button class="btn btn-primary" style="width: 100%; margin-bottom: 20px;" disabled>
	    		<span id="download_message" class="spinner-border spinner-border-sm" style = "font-size: 16px; font-weight: bold;"></span>
	  		</button>
  		</div>
		<div class="row">
			<div class="col-lg-12">
				<form:form action="cardHotListing" method="GET"
					commandName="cardHotListing" name="cardHotListing"
					id="cardHotListing" enctype="multipart/form-data" cssClass="card"
					onSubmit="return false">
					<div class="card-header">
						<h3 class="card-title">Card Hot Listing</h3>
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
												<!-- <i
												class="fe fe-calendar"></i> -->
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
							</div>
						</div>
							
							<div class="text-right"
						style="margin-bottom: 14px; margin-right: 14px;">
						<div class=".d-flex1">
							<button type="search" id="searchBtn"
								class="btn btn-primary ml-auto" onclick="searchbtn()">Search</button>
							<button type="reset" class="btn btn-primary ml-auto"
								id="resetBtn">Clear</button>
						</div>
					</div>
						<div class="card-body">
							<table id="bulkTable"
								class="table table-striped table-bordered nowrap">
								<thead>
									<tr>
									<th>Card Issue Date</th>
										<th>Card Type</th>
										<th>Card Number</th>
										<!-- <th>Card Bin</th> -->
										<th>Card Hot Listed Date</th>
										<th>Card Hot Listed Time</th>
										<th>Card Hot Listed Reason</th>
										<th>Card Hot Listed By</th>
									</tr>
								</thead>
								</table>

							<div class="card-footer text-center">
								<div class=".d-flex1">
									<button type="button" id="pdfButton"
										class="btn btn-primary ml-auto">Download PDF</button>
									<button type="button" id="excelButton"
										class="btn btn-primary ml-auto">Download Excel</button>
								</div>
							</div>
						</div>
				</form:form>
			</div>
		</div>
	</div>
</div>
