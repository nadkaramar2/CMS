<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- addressDetailsUpdateForm -->
<script type="text/javascript">
	$(document).ready(function() {
		$('#addressDetailsTable').DataTable();
		
		 $("#submitBtn").click(function() {     
		        if($('input[type=radio][name=strRequestData]:checked').length == 0)
		        {
		           $('#errorMsg').text("Please select atleast one");
		           return false;
		        }
		        else
		        {
		            return true;
		        }      
		      });
		 
		 $('#addBtn').click(function() {
			 $('#addressDetailsForm').attr('action', "addressDetailsUpdateForm");
			    $('#addressDetailsForm').submit();
		})
		
	});
</script>


<div class="my-3 my-md-5">
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<jsp:include page="customerServiceLeftMenu.jsp"></jsp:include>
			</div>
			<div class="col-md-9">
				<form:form action="addressDetailsUpdateForm" method="post"
					commandName="addressDetailsBean" name="addressDetails"
					id="addressDetailsForm">
					<div class="card">
						<div class="card-header">
							<h3 class="card-title">Address Details</h3>  <h2 id="errorMsg" class="tag tag-red"></h2>
						</div>
						<div class="card-body">

							<table id="addressDetailsTable"
								class="table table-striped table-bordered" style="width: 100%">
								<thead>
									<tr>
										<th>Select</th>
										<th>Address Type</th>
										<th>Address 1</th>
										<th>Address 2</th>
										<th>City</th>
										<th>State</th>
										<th>Country Code</th>
										<th>Pin Code</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="itr" items="${addressDetails}">
										<tr>
											<td><label class="custom-control custom-radio">
											<input type="radio" name="strRequestData" class="custom-control-input approveID"
														value="${itr.strAddress1}|${itr.strAddress2}|${itr.strAddress3}|${itr.strAddressPrimaryFlag}|${itr.strAddressType}|${itr.strCity}|${itr.strCountryCode}|${itr.strPinCode}|${itr.strState}" id="approveID">
													<div class="custom-control-label"></div>
											</label></td>
											<td>${itr.strAddressTypeDesc}</td>
											<td>${itr.strAddress1}</td>
											<td>${itr.strAddress2}</td>
											<td>${itr.strCity}</td>
											<td>${itr.strState}</td>
											<td>${itr.strCountryCode}</td>
											<td>${itr.strPinCode}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>

						</div>
						<div class="card-footer text-right">
							<div class=".d-flex1">
								<button type="submit" id="addBtn" name="addBtn"
									value="0" class="btn btn-secondary ml-auto">Add</button>
								<button type="submit" id="submitBtn" name="strRequestBtn"
									value="1" class="btn btn-primary ml-auto">Submit</button>
							</div>
						</div>
					</div>
					<input type="hidden" value="${leftCustomerMenuID}"
						id="leftCustomerMenuID">
				</form:form>
			</div>
		</div>
	</div>
</div>