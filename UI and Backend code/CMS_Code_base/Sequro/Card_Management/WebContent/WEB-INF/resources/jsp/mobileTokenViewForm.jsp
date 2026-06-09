<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
.c-schedule{
  margin: 1em;
  padding: .5em;
  background-color: #efefef;
  display: flex;
}

.c-schedule-type{
  padding-right: .5em;
  border-right: 1px solid #666;
  min-width: 85px;
}

.c-schedule-options{
  padding-left: .5em;
}

ul{
  list-style-type: none;
  padding-left: 0;
}
</style>

<div class="my-3 my-md-5">
	<div class="container">
		<div class="row">
			<div class="col-12">

				<form:form action="mobileTokenView" method="post"
					commandName="mobileTokenModel" name="mobileTokenConfigForm"
					id="mobileTokenConfigForm" cssClass="card" autocomplete="off" >

					<div class="card-header">
						<h3 class="card-title">
							<i class="fe fe-user-plus"></i> &nbsp;&nbsp;Mobile Token
							View <span class="text-right badge badge-danger"
								id="strError"></span>
						</h3>
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
							
							
						<div class="row" id="cardTypeDiv">	
							<div class="col-md-6 col-lg-6">
								<div class="form-group">
										<label class="form-label">Mobile No <b>*</b></label>
										<form:input path="mobile" id="mobile" cssClass="form-control"  />
									<span class="error" id="mobileError"><form:errors
											path="mobile"></form:errors></span>
								</div>
							</div>
							</div>
							
				<div class="card-footer text-right">
								<div class=".d-flex1">
									<!-- <button type="submit" id="submitBtn"
										class="btn btn-primary ml-auto read">Generate Cron</button> -->
									<button type="submit" id="submitBtn"
										class="btn btn-primary ml-auto">Submit</button>
								</div>
							</div>
							
							<input type="hidden" name="newData" id="newData" value="Y">
							
							<div class="card-body" style="width: 100%;" align="left">
						<table id="binTable"
							class="table table-striped table-bordered nowrap">
							<thead>
								<tr>
									<th>Mobile Token</th>
									<th>Created Date</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="itr" items="${mobileTokenList}">
									<tr>
										<td>${itr.mobileToken}</td>
										<td>${itr.createdDate}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				
				</div>
							
				</form:form>
				
							
			</div>
		</div>
</div>
</div>
<script type="text/javascript">

 $(document).ready(function() {
     $('#mobile').val('');
     $('#binTable').DataTable({
		 responsive: true
	});
     $.validator.addMethod("regex", function(value, element, regexp) {
			var check = false;
			return this.optional(element) || regexp.test(value);
		}, "ss");
     var value = $("#mobileTokenConfigForm").validate({
			rules : {
				mobile : {
					required : true,
					regex : /^[0-9]+$/,
					maxlength : 12
				}
			},
			messages : {
				strAccountNumber : {
					required : "Please Enter Mobile Number!",
					maxlength : "Maximum {0} characters allowed!",
					regex : "only numeric allow !"
				}
			},
			submitHandler : function(form) {
				form.submit();
			}
		});
 });

 
</script>
