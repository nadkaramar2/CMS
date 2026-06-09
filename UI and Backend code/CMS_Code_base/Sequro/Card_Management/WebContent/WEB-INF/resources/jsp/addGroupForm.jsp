<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	$(document).ready(function() {

		$.validator.addMethod("regex", function(value, element, regexp) {
			var check = false;
			return this.optional(element) || regexp.test(value);
		}, "ss");
		
		var value = $("#groupForm").validate({
			rules : {
				strName : {
					required : true,
					regex : /^[a-zA-Z0-9\s]+$/,
					minlength : 2,
					maxlength : 50
				},
				strDescription : {
					required : true,
					regex : /^[a-zA-Z0-9\s]+$/,
					minlength : 2,
					maxlength : 100
				}
			},
			messages : {
				strName : {
					required : "Please Enter Group Name!",
					minlength : "Minmum {0} characters required!",
					maxlength : "Maximum {0} characters allowed!",
					regex : "only alphanumeric allow !"
				},
				strDescription : {
					required : "Please Enter Group Description!",
					minlength : "Minmum {0} characters required!",
					maxlength : "Maximum {0} characters allowed!",
					regex : "only alphanumeric allow !"
				}
			},
			submitHandler : function(form) {
		
				jsonObj = [];
				item = {}
		        item ["strName"] = $('#strName').val();
		        item ["strDescription"] = $('#strDescription').val();
		        item ["strCreatedBy"] = $('#strCreatedBy').val();
		        item ["strParticipantID"] = $('#strParticipantID').val();

		        jsonObj.push(item);
		        
		        $.ajax({
					type : "POST",
					contentType : "application/json",
					url : "${pageContext.request.contextPath}/addGroup",
					data: JSON.stringify(jsonObj).replace("[","").replace("]",""),
					dataType : 'json',
					timeout : 100000,
					success : function(data) {
						console.log("SUCCESS: ", data);
						if(data.outResponseCode == "00"){
							$('#strError').addClass("tag tag-green");
							$('#strError').text(data.message);
							setTimeout(function(){// wait for 5 secs(2)
								var obj = { Title: '', Url: 'addGroupForm' };
						        history.pushState(obj, obj.Title, obj.Url);
						           location.reload(); // then reload the page.(3)
						      }, 2000); 
						}else {
							$('#strError').addClass("tag tag-red");
							$('#strError').text(data.message);
						}
					},
					error : function(e) {
						console.log("ERROR: ", e);
						$('#strError').addClass("tag tag-red");
						$('#strError').text("Error");
					},
					done : function(e) {
						console.log("DONE");
					}
				});
		        return false;
			}
		});

	});
</script>

<div class="my-3 my-md-5">
	<div class="container">
		<div class="row">
			<div class="col-12">
				<form:form action="#" method="post" commandName="groupBean"
					name="groupForm" id="groupForm" cssClass="card">

					<div class="card-header">
						<h3 class="card-title">
							<i class="fe fe-user-plus"></i> &nbsp;&nbsp;Add Role Group <span class="text-right badge badge-danger" id="strError"></span>
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
						<div class="row">

							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Group Name <b>*</b></label>
									<form:input path="strName" id="strName" cssClass="form-control" />
									<span class="error" id="strNameError"><form:errors
											path="strName"></form:errors></span>
								</div>
							</div>

							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Description <b>*</b></label>
									<form:input path="strDescription" id="strDescription"
										cssClass="form-control" />
									<span class="error" id="strDescriptionError"><form:errors
											path="strDescription"></form:errors></span>
								</div>
							</div>

							<form:hidden path="strParticipantID" id="strParticipantID" />
							<form:hidden path="strCreatedBy" id="strCreatedBy" />

						</div>
					</div>

					<div class="card-footer text-right">
						<div class=".d-flex1">
							<button type="submit" id="submitBtn"
								class="btn btn-primary ml-auto">Submit</button>
							<button type="reset" class="btn btn-primary ml-auto"
								id="resetBtn">Clear</button>
						</div>
					</div>

				</form:form>
			</div>
		</div>
	</div>
</div>
