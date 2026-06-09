<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
$(document).ready(function() {
    
	var table = $('#emailDetailsTable').DataTable({
		responsive: true,
		 scrollX:        200,
		 scroller:       true
	});
	
    
    $('#phoneDetailsTable').DataTable({
    	responsive: true,
    	scrollX:        200,
		 scroller:       true
	});
    
    $("#submitBtn1").click(function() {     
        if($('input[type=radio][name=strRequestData]:checked').length == 0)
        {
           $('#errorEmailMsg').text("Please select atleast one");
           return false;
        }
        else
        {
            return true;
        }      
      });
    
    $("#submitBtn2").click(function() {     
        if($('input[type=radio][name=strRequestData]:checked').length == 0)
        {
           $('#errorPhnMsg').text("Please select atleast one");
           return false;
        }
        else
        {
            return true;
        }      
      });
} );

$(document).ready(function() {
    
} );
</script>

<div class="my-3 my-md-5">
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<jsp:include page="customerServiceLeftMenu.jsp"></jsp:include>
			</div>
			<div class="col-md-9">
				<div class="card">
					<form:form action="emailDetailsForm" method="post"
						commandName="emailBean" name="emailDetails" id="emailDetailsForm">
						<div class="card-header">
							<h3 class="card-title">Email Details</h3> <h2 id="errorEmailMsg" class="tag tag-red"></h2>
						</div>
						<div class="card-body">

							<table id="emailDetailsTable"
								class="table table-striped table-bordered nowrap" style="width: 100%">
								<thead>
									<tr>
										<th>Select</th>
										<th>Email ID</th>
										<th>Type</th>
										<!-- <th>Primary Flag</th> -->
									</tr>
								</thead>
								<tbody>
									<c:forEach var="itr" items="${emailDetails}">
										<tr>
											<td><label class="custom-control custom-radio">
											<input type="radio" name="strRequestData" class="custom-control-input approveID"
														value="${itr.strEmailID}|${itr.strEmailPrimaryFlag}|${itr.strEmailType}" id="approveID">
													<div class="custom-control-label"></div>
											</label></td>
											<td>${itr.strEmailID}</td>
											<td>${itr.strEmailTypeDesc}</td>
											<%-- <td>${itr.strEmailPrimaryFlag}</td> --%>
										</tr>
									</c:forEach>
								</tbody>
							</table>

						</div>
						<div class="card-footer text-right">
							<div class=".d-flex1">
								<button type="submit" id="addBtn1" name="addBtn"
									value="3" class="btn btn-secondary ml-auto">Add</button>
								<button type="submit" id="submitBtn1" name="strRequestBtn"
									value="1" class="btn btn-primary ml-auto">Submit</button>
							</div>
						</div>
					</form:form>
				</div>

				<div class="card">
					<form:form action="phoneDetailsForm" method="post"
						commandName="emailBean" name="emailDetails" id="emailDetailsForm">
						<div class="card-header">
							<h3 class="card-title">Phone Details</h3> <h2 id="errorPhnMsg" class="tag tag-red"></h2>
						</div>
						<div class="card-body">

							<table id="phoneDetailsTable"
								class="table table-striped table-bordered nowrap" style="width: 100%">
								<thead>
									<tr>
										<th>Select</th>
										<th>Phone Number</th>
										<!-- <th>Primary Flag</th> -->
									</tr>
								</thead>
								<tbody>
									<c:forEach var="itr" items="${phoneDetails}">
										<tr>
											<td><label class="custom-control custom-radio">
											<input type="radio" name="strRequestData" class="custom-control-input approveID"
														value="${itr.strPhoneNo}|${itr.strPhonePrimaryFlag}" id="approveID">
													<div class="custom-control-label"></div>
											</label></td>
											<td>${itr.strPhoneNo}</td>
											<%-- <td>${itr.strPhonePrimaryFlag}</td> --%>
										</tr>
									</c:forEach>
								</tbody>
							</table>

						</div>
						<div class="card-footer text-right">
							<div class=".d-flex1">
								<button type="submit" id="addBtn2" name="addBtn"
									value="4" class="btn btn-secondary ml-auto">Add</button>
								<button type="submit" id="submitBtn2" name="strRequestBtn"
									value="1" class="btn btn-primary ml-auto">Submit</button>
							</div>
						</div>
						<input type="hidden" value="${leftCustomerMenuID}"
							id="leftCustomerMenuID">
					</form:form>
				</div>
			</div>

		</div>
	</div>
</div>
