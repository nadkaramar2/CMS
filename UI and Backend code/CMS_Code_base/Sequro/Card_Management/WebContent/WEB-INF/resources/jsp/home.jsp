	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	$(document).ready(function() {
		 
		/* $('#clientTable tfoot th').each( function () {
		        var title = $(this).text();
		        $(this).html( '<input type="text" class="form-control" placeholder="Search '+title+'" />' );
		    } );
		  */
		  
		  try 
		  {
		 	var applName = $('#applName').val();
		 	localStorage.setItem('applName',applName);
		 	console.log('setted local storage');
		  }
		  catch(e) 
		  {
			  console.log(e);
		  }
		  
		  $('#clientTable thead tr').clone(true).appendTo( '#clientTable thead' );
		    $('#clientTable thead tr:eq(1) th').each( function (i) {
		        var title = $(this).text();
		        $(this).html( '<input type="text" class="form-control" placeholder="Search '+title+'" />' );
		 
		        $( 'input', this ).on( 'keyup change', function () {
		            if ( table.column(i).search() !== this.value ) {
		                table
		                    .column(i)
		                    .search( this.value )
		                    .draw();
		            }
		        } );
		    } );
		    
		    
		    
		    var value = $("#searchClientForm").validate({
				rules: {
					strSearchType: {
		                    required: true
		            }
				},
		    	messages: {
		    		strSearchType: {
		                    required: "Required !",
		            }
		    	},
				submitHandler: function(form) {
					form.submit();	
				}
			});
			
		    
		    
		 var table = $('#clientTable').DataTable({
			 scrollX: 100,
			 scroller: true,
			 orderCellsTop: true,
		     fixedHeader: true
		 });
		 
		 /* table.columns().every( function () {
		        var that = this;
		 
		        $( 'input', this.footer() ).on( 'keyup change', function () {
		            if ( that.search() !== this.value ) {
		                that
		                    .search( this.value )
		                    .draw();
		            }
		        });
		    }); */
	});
</script>

<div class="my-3 my-md-5">
	<div class="container">
		<div class="row">
			<div class="col-12">
				<form:form action="searchClient" method="post"
					commandName="clientBean" name="searchClientForm" id="searchClientForm"
					cssClass="card">
					<div class="card-header">
						<h3 class="card-title">Search For Clients</h3>
					</div>
					<div class="card-body">
						<div class="row">
							
							<div class="col-md-6 col-md-3">
								<div class="form-group">
									<label class="form-label">Search Name</label>
									<form:input path="strCustomerName" id="strCustomerName"
										cssClass="form-control" />
								</div>
							</div>
							
							<div class="col-md-6 col-lg-3">
								<div class="form-group">
									<label class="form-label">Search By</label>
									<form:select path="strSearchType" id="strSearchType"
										cssClass="form-control selectpicker">
										<form:option value=""></form:option>
											<form:option value="1">Instant Card</form:option>
											<form:option value="2">Personalized Card</form:option>
									</form:select>
								</div>
							</div>
							
							<div class="col-md-6 col-lg-3">
								<div class="form-group">
									<label class="form-label">Card Type</label>
									<form:select path="strCardType" id="strCardType"
										cssClass="form-control selectpicker">
										<form:option value=""></form:option>
										<c:forEach items="${cardTypeList}" var="itr">
											<form:option value="${itr.strID}">${itr.strCardType} : ${itr.strCardDesc}</form:option>
										</c:forEach>
									</form:select>
								</div>
							</div>
						
							
							<div class="col-md-6 col-lg-3">
								<div class="form-group">
									<label class="form-label">Card Number</label>
									<form:input path="strCardNumber" id="strCardNumber"
										cssClass="form-control" />
								</div>
							</div>
							<div class="col-md-6 col-lg-3">
								<div class="form-group">
									<label class="form-label">Account Number</label>
									<form:input path="strAccountNumber" id="strAccountNumber"
										cssClass="form-control" />
								</div>
							</div>
							<div class="col-md-6 col-lg-3">
								<div class="form-group">
									<label class="form-label">Citizen ID</label>
									<form:input path="strCitizenID" id="strCitizenID"
										cssClass="form-control" />
								</div>
							</div>
							<div class="col-md-6 col-lg-3">
								<div class="form-group">
									<label class="form-label">CIF Key</label>
									<form:input path="strCIFKey" id="strCIFKey"
										cssClass="form-control" />
								</div>
							</div>
						</div>
					</div>
					<div class="card-footer text-right">
						<div class="d-flex">
							<button type="submit" class="btn btn-primary ml-auto">Search</button>
						</div>
					</div>

					<div class="card-body">
						<table id="clientTable" class="table table-striped table-bordered nowrap"
							style="width: 100%">
							<thead>
								<tr>
									<th>Name</th>
									<th>Card Number</th>
									<th>Account Number</th>
									<th>Citizen ID</th>
									<th>CIF Key</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="itr" items="${clientList}">
									<tr>
										<td><a
											href="clientDetailsForm?strCustomerID=${itr.strCustomerID}">${itr.strCustomerName}</a></td>
										<td><a
											href="clientDetailsForm?strCustomerID=${itr.strCustomerID}">${itr.strCardNumber}</a></td>
										<td><a
											href="clientDetailsForm?strCustomerID=${itr.strCustomerID}">${itr.strAccountNumber}</a></td>
										<td><a
											href="clientDetailsForm?strCustomerID=${itr.strCustomerID}">${itr.strCitizenID}</a></td>
										<td><a
											href="clientDetailsForm?strCustomerID=${itr.strCustomerID}">${itr.strCIFKey}</a></td>
									</tr>
								</c:forEach>
							</tbody>
							<tfoot>
								<tr>
									<th>Name</th>
									<th>Card Number</th>
									<th>Account Number</th>
									<th>Citizen ID</th>
									<th>CIF Key</th>
								</tr>
							</tfoot>
						</table>
						<input type="hidden" id="applName" value = "${appl_name}" cssClass="form-control" /><!-- Added by Sunny Soni -->
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>