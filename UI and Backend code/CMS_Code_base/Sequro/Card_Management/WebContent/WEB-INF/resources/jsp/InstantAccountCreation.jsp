<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">
$(document).ready(function() 
{
      //Added  by Abhishek T to blank form Start
		var exceptionDivVal =  $('#exceptionDiv').text();
		var successDivVal =  $('#successDiv').text();
		var errorDivVal =  $('#errorDiv').text();
		
		if (successDivVal)
		{
			console.log('Success Called............')
			 $("#strAccountType").val(""); 
			$("#strQuantity").val("");
			$("#strAccountName").val("");
		}
		else if(errorDivVal || exceptionDivVal)
		{
			console.log('Error or Exception Called............');

		}
		//Added  by Abhishek T to blank form End
			
      $("b").css({"display":"contents", "color":"red"});
	
		$.validator.addMethod("regex", function(value, element, regexp) 
		{
			var check = false;
			return this.optional(element) || regexp.test(value);
		}, "ss");
			
		var value = $("#instantAccountCreation").validate(
		{
			rules : 
			{
				strAccountType : {
					required : true
				},
				strQuantity : {
					required : true,
					regex : /^[0-9]+$/,
				}
			 },
			 messages : 
			 {
				strAccountType : {
					required : "Please Select Account Type!"
				},
				strQuantity : {
					required : "Please Enter Quantity!",
					regex : "only Numeric allow !"
				}
			 },
			submitHandler : function(form) 
			{
				form.submit();				
			}
	});
});				
</script>

<div class="my-3 my-md-5">
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<jsp:include page="customerAccountLeftMenu.jsp"></jsp:include>
			</div>
			<div class="col-md-9">
				<form:form action="addInstantAccountCreation" method="Post" commandName="instantAccountCreation" name="instantAccountCreation" id="instantAccountCreation" cssClass="card">
					<div class="card-header">
						<h3 class="card-title">Instant Account Generation Form</h3>
						<div class="text-right" style="display: ${display};" id="errorMsg">
							<c:if test="${not empty exception}">
								<div class="text-right badge badge-danger"
									style="display: ${display};" id="exceptionDiv">
									<c:out value="${exception}"></c:out>
								</div>
							</c:if>
							<c:if test="${not empty success}">
								<div class="text-right badge badge-success"
									style="display: ${display};" id="successDiv">
									<c:out value="${success}"></c:out>
								</div>
							</c:if>
							<c:if test="${not empty error}">
								<div class="text-right badge badge-danger"
									style="display: ${display};" id="errorDiv">
									<c:out value="${error}"></c:out>
								</div>
							</c:if>
						</div>
					</div>
					<div class="card-body">
						<div class="row">
							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Account Type <b>*</b></label>
									<form:select path="strAccountType" id="strAccountType" cssClass="form-control selectpicker">
										<form:option value="">Select</form:option>
										<c:forEach items="${accountType}" var="itr">
											<form:option value="${itr.strAccountType}">${itr.strAccountType} - ${itr.strDescription}</form:option>
										</c:forEach>
									</form:select>
								</div>
							</div>						
							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Quantity <b>*</b></label>
									<form:input path="strQuantity" id="strQuantity" cssClass="form-control" />
								</div>
							</div>							
							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Instant Account Name</label>
									<form:input path="strAccountName" id="strAccountName" cssClass="form-control" />
									<span class="error" id="strAccountNameError"><form:errors path="strAccountName"></form:errors></span>
								</div>
							</div>
							<input type="hidden" value="${leftCustomerAccountMenuID}" id="leftCustomerAccountMenuID">
						</div>
					</div>					
					<div class="card-footer text-right">
						<div class="btn-list text-right">
							<button type="submit" id="submitBtn" name="strRequestBtn" value="1" class="btn btn-primary">Submit</button>
							<button type="reset" class="btn btn-secondary" name="strRequestBtn" value="2" id="resetBtn">Clear</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>