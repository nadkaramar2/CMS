<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<script type="text/javascript">
	
$(document).ready(function() {
	
	
	$.validator.addMethod(
			"regex",
			function(value, element, regexp) {
    		var check = false;
    		return this.optional(element) || regexp.test(value);
		},
		"ss"
	);
	
	var value = $("#addInstantCards").validate({
		rules: {
			inEmbossLine1: {
                    required: true,
                    regex: /^[a-zA-Z0-9\s]+$/,
                    minlength: 1,
                    maxlength: 25
            },
            inEncodeFirstName: {
                required: true,
                regex: /^[a-zA-Z0-9]+$/,
                minlength: 1,
                maxlength: 25         
            },
            inEncodeLastName: {
                required: true,
                regex: /^[a-zA-Z0-9]+$/,
                minlength: 1,
                maxlength: 25         
            }
		},
    	messages: {
    		inEmbossLine1: {
                    required: "Eboss Line 1 Can't Be Blank !",
                    minlength: "Minmum {0} characters required!",
                    maxlength: "Maximum {0} characters allowed!",
                    regex: "only alphanumeric allow !"
            },
            inEncodeFirstName: {
                required: "Encode First Name Can't Be Blank !",
                minlength: "Minmum {0} characters required!",
                maxlength: "Maximum {0} characters allowed!",
                regex: "only alphanumeric allow !"
            },
            inEncodeLastName: {
                required: "Encode Last Name Can't Be Blank !",
                minlength: "Minmum {0} characters required!",
                maxlength: "Maximum {0} characters allowed!",
                regex: "only alphanumeric allow !"
            }
    	},
		submitHandler: function(form) {
			form.submit();	
		}
	});
	
	
	$('#inEmbossLine1').keyup(function(){
		var txt = $('#inEmbossLine1').val().replace(/(?:^|)\w/g, function(match) {
	        return match.toUpperCase();
	     });
		$('#inEmbossLine1').val(txt);
	});
	
	$('#inEmbossLine2').keyup(function(){
		var txt = $('#inEmbossLine2').val().replace(/(?:^|)\w/g, function(match) {
	        return match.toUpperCase();
	     });
		$('#inEmbossLine2').val(txt);
	});
	
	$('#inEncodeFirstName').keyup(function(){
		var txt = $('#inEncodeFirstName').val().replace(/(?:^|)\w/g, function(match) {
	        return match.toUpperCase();
	     });
		$('#inEncodeFirstName').val(txt);
	});
	
	$('#inEncodeMiddleName').keyup(function(){
		var txt = $('#inEncodeMiddleName').val().replace(/(?:^|)\w/g, function(match) {
	        return match.toUpperCase();
	     });
		$('#inEncodeMiddleName').val(txt);
	});
	
	$('#inEncodeLastName').keyup(function(){
		var txt = $('#inEncodeLastName').val().replace(/(?:^|)\w/g, function(match) {
	        return match.toUpperCase();
	     });
		$('#inEncodeLastName').val(txt);
	});
});

</script>

<div class="my-3 my-md-5">
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<jsp:include page="InstantCardLeftMenu.jsp"></jsp:include>
			</div>
			<div class="col-md-9">
				<form:form action="addInstantCards" method="post"
					commandName="serviceBean" name="addInstantCards"
					id="addInstantCards" cssClass="card">

					<div class="card-header">
						<h3 class="card-title">Instant Cards Generate</h3>
						<div class="text-right" style="display: ${display};" id="errorMsg">
							<c:if test="${not empty exception}">
								<div class="text-right badge badge-danger"
									style="display: ${display};">
									<c:out value="${exception}"></c:out>
								</div>
							</c:if>
							<c:if test="${not empty success}">
								<div class="text-right badge badge-success"
									style="display: ${display};">
									<c:out value="${success}"></c:out>
								</div>
							</c:if>
							<c:if test="${not empty error}">
								<div class="text-right badge badge-danger"
									style="display: ${display};">
									<c:out value="${error}"></c:out>
								</div>
							</c:if>
						</div>

					</div>
					<div class="card-body">
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label class="form-label">Type</label>
									<form:select path="inBulkCardType" id="inBulkCardType"
										cssClass="form-control selectpicker">
										<c:forEach items="${cardTypeList}" var="itr">
											<form:option value="${itr.strID}">${itr.strCardType} : ${itr.strCardDesc}</form:option>
										</c:forEach>
									</form:select>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label class="form-label">Branch Code</label>
									<form:select path="inBranchCode" id="inBranchCode"
										cssClass="form-control selectpicker">
										<c:forEach items="${branchCodeList}" var="itr">
											<form:option value="${itr.strBranchCode}">${itr.strBranchCode}</form:option>
										</c:forEach>
									</form:select>
								</div>
							</div>

							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Quantity</label>
									<form:input path="inQty" id="inQty" cssClass="form-control" />
								</div>
							</div>

							<div class="col-md-3">
								<div class="form-group">
									<label class="form-label">Emboss Line 1</label>
									<form:input path="inEmbossLine1" id="inEmbossLine1"
										cssClass="form-control" />
									<span class="error" id="inEmbossLine1Error"><form:errors
											path="inEmbossLine1"></form:errors></span>	
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label class="form-label">Emboss Line 2</label>
									<form:input path="inEmbossLine2" id="inEmbossLine2"
										cssClass="form-control" />
								</div>
							</div>

							<div class="col-md-3">
								<div class="form-group">
									<label class="form-label">Encode First Name</label>
									<form:input path="inEncodeFirstName" id="inEncodeFirstName"
										cssClass="form-control" />
									<span class="error" id="inEncodeFirstNameError"><form:errors
											path="inEncodeFirstName"></form:errors></span>	
								</div>
							</div>

							<div class="col-md-3">
								<div class="form-group">
									<label class="form-label">Encode Middle Name</label>
									<form:input path="inEncodeMiddleName" id="inEncodeMiddleName"
										cssClass="form-control" />
								</div>
							</div>

							<div class="col-md-3">
								<div class="form-group">
									<label class="form-label">Encode Last Name</label>
									<form:input path="inEncodeLastName" id="inEncodeLastName"
										cssClass="form-control" />
									<span class="error" id="inEncodeLastNameError"><form:errors
											path="inEncodeLastName"></form:errors></span>	
								</div>
							</div>

							<input type="hidden" value="${leftCardMenuID}"
								id="leftCardMenuID">

						</div>

					</div>
					<div class="card-footer text-right">
						<div class="btn-list text-right">
							<button type="submit" id="submitBtn" name="strRequestBtn"
								value="1" class="btn btn-primary">Submit</button>
							<button type="reset" class="btn btn-secondary"
								name="strRequestBtn" value="2" id="resetBtn">Clear</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>