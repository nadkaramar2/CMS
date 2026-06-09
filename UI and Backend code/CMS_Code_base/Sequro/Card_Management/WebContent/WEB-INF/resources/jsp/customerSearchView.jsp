	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Changes By Jyoti Shirahatti Date-07-12-2022 -->
<script type="text/javascript">
	$(document).ready(function()
	{
		var value = $("#customerIdCreationConfig").validate(
		{
			rules : 
			{
				strCustId : {
					required : true
				}
			},
			messages : {
				strCustId : {
					required : "Please Enter Customer Id!"
				}
			},
			submitHandler : function(form)
			{
				//alert('search clicked....');
				form.submit();
			}
		});
	});
</script>

<div class="my-3 my-md-5">
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<jsp:include page="customerInformationLeftMenu.jsp"></jsp:include>
			</div>
			<div class="col-9">
				<form:form action="viewCustomerInfoDetails" method="POST" commandName="customerIdCreationConfig" name="customerIdCreationConfig" id="customerIdCreationConfig" cssClass="card">	
					<div class="card-header">
						<h3 class="card-title">Search Customer By Id</h3>
					</div>					
					<div class="card-body">
						<div class="row">
							<div class="col-md-6 col-lg-4">
								<div class="form-group">
									<label class="form-label">Customer Id</label>
									 <form:input path="strCustId" id="strCustId" cssClass="form-control" />
								</div>
							</div>
						</div>						
						<div class="text-right">
							<div class=".d-flex1">
								<button type="submit" id="submitBtn" class="btn btn-primary ml-auto">Search</button>
								<button type="reset" class="btn btn-primary ml-auto" id="resetBtn">Clear</button>
							</div>
						</div>
					</div>
					<input type="hidden" value="${leftCustomerInfoMenuId}" id="leftCustomerInfoMenuId">
				</form:form>
				</div>
			</div>
		</div>
	</div>
</div>