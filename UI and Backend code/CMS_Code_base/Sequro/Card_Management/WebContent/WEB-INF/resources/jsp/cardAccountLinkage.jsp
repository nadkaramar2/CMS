<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	var deleteBinID;
	var cardAccountData;
	
	$(document).ready(function()
	{
		$('#delBinMsg').text("");		 
		$("b").css({"display":"contents", "color":"red"});
		
		$('#cardAccountlinkTable').DataTable({
			 responsive: true
		});
		
		$.validator.addMethod("regex", function(value, element, regexp) 
		{
    		var check = false;
    		return this.optional(element) || regexp.test(value);
		},"ss");
		
		var value = $("#cardAccountLinkage").validate(
		{
			rules: 
			{
				strAccountType: 
				{
                    required: true,
                    regex: /^[a-zA-Z0-9\s.\-]+$/,
                    maxlength: 20
	            },
	            strAccountNumber: 
	            {
	                required: true,
	                regex: /^[a-zA-Z0-9\s.\-]+$/,
	                maxlength: 20         
	            },	            
	            strCardType: 
	            {
	                required: true,
	            },
	            strCardNumber: 
	            {
	                required: true,
	                regex: /^[0-9]+$/,
	                minlength: 14,
	                maxlength: 20         
	            }
			},
	    	messages: 
	    	{
	    		strAccountType: 
	    		{
	                    required: "Required!",
	                    minlength: "Minmum {0} characters required!",
	                    maxlength: "Maximum {0} characters allowed!"
	            },
	            strAccountNumber: 
	            {
	                required: "Please Enter Account Number!",
	                minlength: "Minmum {0} characters required!",
	                maxlength: "Maximum {0} characters allowed!"
	            },
	            strCardType: 
	            {
	                required: "Required!",
	            },
	            strCardNumber:
	            {
	                required: "Please Enter Card Number!",
	                minlength: "Minmum {0} characters required!",
	                maxlength: "Maximum {0} characters allowed!"
	            }
	    	},
		});
	});
</script>

<div class="my-3 my-md-5">
	<div class="container">
		<div class="row">
			<!-- <div class="col-lg-12"> -->
			<div class="col-md-3">
				<jsp:include page="linkCardLeftMenu.jsp"></jsp:include>
			</div>
			<div class="col-lg-9">
				<form:form action="cardAccountLinkageForm" method="POST" commandName="cardAccountLinkage" name="cardAccountLinkage"
					id="cardAccountLinkage" cssClass="card">

					<div class="card-header">
						<h3 class="card-title">
							<i class="fe fe-user-plus"></i> &nbsp;&nbsp;Linkage Card and Account 
							<span class="text-right badge badge-danger" id="strError"></span>
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
									<label class="form-label">Account Number<b>*</b></label>
									<form:input path="strAccountNumber" id="strAccountNumber" cssClass="form-control" />
									<span class="error" id="strAccountNumberError">
										<form:errors path="strAccountNumber"></form:errors>
									</span>
								</div>
							</div>
							
							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">CardType<b>*</b></label>
									<form:select path="strCardType" id="strCardType" cssClass="form-control selectpicker">
										<form:option value="">Select</form:option>
										<c:forEach items="${cardTypeList}" var="itr">
											<form:option value="${itr.strID}">${itr.strCardType} - ${itr.strCardDesc}</form:option>
										</c:forEach>
									</form:select>
								</div>
							</div>
							
							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Card Number<b>*</b>
									</label>
									<form:input path="strCardNumber" id="strCardNumber" cssClass="form-control" />
									<span class="error" id="strCardNumberError">
										<form:errors path="strCardNumber"></form:errors>
									</span>
								</div>
							</div>
						</div>
					</div>
					<div class="card-footer text-right">
						<div class=".d-flex1">
							<button type="submit" id="submitBtn" class="btn btn-primary ml-auto">Submit</button>
							<button type="reset" class="btn btn-primary ml-auto" id="resetBtn">Clear</button>
						</div>
					</div>

					<%-- <div class="modal-body">
							<div class="card-body">
							<table id="cardAccountlinkTable" class="table table-striped table-bordered nowrap" style="width: 100%">
								<thead>
									<tr>
										<th>Account Type</th>
										<th>Account Number</th>
										<th>Card Type</th>
										<th>Card Number</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="itr" items="${cardAccountLinkList}">
										<tr>
											<td>${itr.strAccountType}</td>
											<td>${itr.strAccountNumber}</td>
											<td>${itr.strCardType}</td>
											<td>${itr.strCardNumber}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div> --%>
							
					<%-- <input type="hidden" value="${leftAccountMenuID}" id="leftAccountMenuID"> --%>					
					<input type="hidden" value="${leftLinkCardMenuId}" id="leftLinkCardMenuId">
				</form:form>
			</div>
		</div>
	</div>
</div>
