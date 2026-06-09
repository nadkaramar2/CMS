<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>New JSP</title>

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<!--  <link href="css/style.css" rel="stylesheet" type="text/css" />-->
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.12.1/css/all.css"
	crossorigin="anonymous">
<script type="text/javascript" language="javascript">
	$(function() {
		$("#datepicker").datepicker({
			showOn : "both",
			//buttonImage : "image.jpg",
			dateFormat : "yy-mm-dd",
			buttonImageOnly : false,
			minDate : +0, //you do not want to show previous date.
			maxDate : +0
		// you do not want to show next day.
		});
	});
</script>
</head>
<body>


<script src="https://code.jquery.com/jquery-3.6.0.min.js"
		integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>
	
	<!-- - <script src="js/my.js" type="text/javascript"></script>-->

	<div class="card-header">
		<h3 class="card-title">
			<!-- <i class="fas fa-info-circle"></i>
			</ion-icon>
			USER Transaction <span class="text-right badge badge-danger"></span> -->
		</h3>
		<!-- <input type="text" name="calendar" id="datepicker"> -->
		<!-- table content start -->
		<div class="my-3 my-md-5">
			<div class="container">
				<div class="row">
					<div class="col-12">
						<div class="card-body">
							<table id="tabletrans" cellspacing="0" cellpadding="0"
								class="table table-striped table-bordered" style="width: 100%">
								<thead>
									<tr>
										<th>Date</th>
										<th>Card No</th>
										<th>Amount</th>
										<th>Terminal ID</th>
										<th>RRN</th>
										<th>TIN</th>
										<th>Response</th>

									</tr>
								
								</thead>
								<tbody>
									<tr>
										<th>16/06/2022</th>
										<th>2222222222</th>
										<th>2000</th>
										<th>2090909</th>
										<th>111111</th>
										<th>37487484</th>
										<th>success</th>

									</tr>
								</tbody>
								<!-- <tbody>
									<tr>
										<th>Date</th>
										<th>Card No</th>
										<th>Amount</th>
										<th>Terminal ID</th>
										<th>RRN</th>
										<th>TIN</th>
										<th>Response</th>

									</tr>
								</tbody>
								<tbody>
									<tr>
										<th>Date</th>
										<th>Card No</th>
										<th>Amount</th>
										<th>Terminal ID</th>
										<th>RRN</th>
										<th>TIN</th>
										<th>Response</th>

									</tr>
								</tbody>
								<tbody>
									<tr>
										<th>Date</th>
										<th>Card No</th>
										<th>Amount</th>
										<th>Terminal ID</th>
										<th>RRN</th>
										<th>TIN</th>
										<th>Response</th>

									</tr>
								</tbody>
								<tbody>
									<tr>
										<th>Date</th>
										<th>Card No</th>
										<th>Amount</th>
										<th>Terminal ID</th>
										<th>RRN</th>
										<th>TIN</th>
										<th>Response</th>

									</tr>
								</tbody>
 -->							</table>
							<input type="button" class="btn btn-success" id="btnExport"
								value="Download Pdf" />
							<script type="text/javascript"
								src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
							<script type="text/javascript"
								src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.22/pdfmake.min.js"></script>
							<script type="text/javascript"
								src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/0.4.1/html2canvas.min.js"></script>



							<script type="text/javascript">
								var d = new Date().toString();
								var index = d.lastIndexOf(':') + 3
								var name = "transaction";
								var datetime = d.substring(0, index)
										.replaceAll(' ', '')
								var exten = ".pdf"
								var result = name.concat(datetime, exten);
								console.log(result)

								$("body").on(
										"click",
										"#btnExport",
										function() {
											html2canvas($('#tabletrans')[0], {
												onrendered : function(canvas) {
													var data = canvas
															.toDataURL();
													var docDefinition = {
														content : [ {
															image : data,
															width : 500
														} ]
													};
													pdfMake.createPdf(
															docDefinition)
															.download(result);
												}
											});
										});
							</script>
							<script
								src="//cdn.rawgit.com/rainabba/jquery-table2excel/1.1.0/dist/jquery.table2excel.min.js">
								
							</script>

							<input class="btn btn-primary" type="button" id="btnexcel"
								value="Download Excel" />
							<script>
								var d = new Date().toString();
								var index = d.lastIndexOf(':') + 3
								var name = "transaction";
								var datetime = d.substring(0, index)
										.replaceAll(' ', '')
								var exten = ".xls"
								var result = name.concat(datetime, exten);
								console.log(result)

								$("body").on("click", "#btnexcel", function() {
									$("#tabletrans").table2
									excel({
										filename : result
									});
								});
							</script>
						</div>
						<nav aria-label="Page navigation example">
							<ul class="pagination justify-content-end">
								<li class="page-item"><a class="page-link" href="#"
									aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
										<span class="sr-only">Previous</span>
								</a></li>
								<li class="page-item"><a class="page-link" href="#">1</a></li>
								<li class="page-item"><a class="page-link" href="#">2</a></li>
								<li class="page-item"><a class="page-link" href="#">3</a></li>
								<li class="page-item"><a class="page-link" href="#"
									aria-label="Next"> <span aria-hidden="true">&raquo;</span>
										<span class="sr-only">Next</span>
								</a></li>
							</ul>
						</nav>

					</div>
				</div>
			</div>
		</div>
	</div>


</body>
</html>

