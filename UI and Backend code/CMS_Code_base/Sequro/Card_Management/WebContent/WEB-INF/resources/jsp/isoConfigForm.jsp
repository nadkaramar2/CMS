<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
.c-schedule {
	margin: 1em;
	padding: .5em;
	background-color: #efefef;
	display: flex;
}

.c-schedule-type {
	padding-right: .5em;
	border-right: 1px solid #666;
	min-width: 85px;
}

.c-schedule-options {
	padding-left: .5em;
}

ul {
	list-style-type: none;
	padding-left: 0;
}
</style>

<div class="my-3 my-md-5">
	<div class="container">
		<div class="row">
		<div class="col-md-3">
				<jsp:include page="participantConfigLeftMenu.jsp"></jsp:include>
			</div>
			<div class="col-md-9">
				<form:form action="addISOConfigForm" method="post"
					commandName="isoConfig" name="addIsoConfigForm"
					id="addIsoConfigForm" cssClass="card" autocomplete="off">

					<div class="card-header">
						<h3 class="card-title">
							<i class="fe fe-user-plus"></i> &nbsp;&nbsp;ISO Configuration <span
								class="text-right badge badge-danger" id="strError"></span>
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
									<label class="form-label">Name <b>*</b></label>
									<form:input path="name" id="name" cssClass="form-control"
										autocomplete="off" />
									<span class="error" id="nameError"></span>
								</div>
							</div>

							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Connection Name <b>*</b></label>
									<form:select path="connectionName" id="connectionName"
										cssClass="form-control">
										<c:forEach var="itr" items="${connectionList}">
											<form:option value="${itr.name}">${itr.name}</form:option>
										</c:forEach>
									</form:select>
									<span class="error" id="connectionNameError"></span>
								</div>
							</div>

							<div class="col-md-6 col-lg-12">
								<div class="form-group">
									<label class="form-label">Data <b>*</b> <i
										style="cursor: pointer;" data-toggle="modal"
										data-target="#jsonEditor" id="dataEditor"
										onclick="viewData(null)" class="fa fa-pencil"></i></label>
									<form:textarea path="data" readonly="true"
										style="height: 250px;" id="data" cssClass="form-control"
										autocomplete="off" />
									<span class="error" id="dataError"></span>
								</div>
							</div>


						</div>

					</div>
					<div class="card-footer text-right">
						<div class=".d-flex1">
							<!-- <button type="submit" id="submitBtn"
										class="btn btn-primary ml-auto read">Generate Cron</button> -->
							<button type="submit" id="submitBtn"
								class="btn btn-primary ml-auto">Submit</button>
							<button type="reset" class="btn btn-secondary ml-auto"
								id="resetBtn">Clear</button>
						</div>
					</div>

					<input type="hidden" name="newData" id="newData" value="Y">

					<div class="card-body" style="width: 100%;" align="left">
						<table id="binTable"
							class="table table-striped table-bordered nowrap">
							<thead>
								<tr>
									<th>Name</th>
									<th>Connection Name</th>
									<th>View</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="itr" items="${isoConfigList}">
									<tr>
										<td>${itr.name}</td>
										<td>${itr.connectionName}</td>
										<td onclick='viewData(${itr.data})'><i
											style="cursor: pointer;" class="fa fa-eye" aria-hidden="true"
											data-toggle="modal" data-target="#jsonEditor"></i></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<input type="hidden" value="${leftpartipantMenuID}"
								id="leftpartipantMenuID">
					</div>

				</form:form>

				<div class="modal fade" id="jsonEditor" role="dialog">
					<div class="modal-dialog">
						<!-- Modal content-->
						<div class="modal-content">
							<div class="modal-header">ISO Editor</div>
							<div class="modal-body">
								<div id="jsoneditor" style="width: 470px; height: 400px;"></div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Close</button>
								<button type="button" class="btn btn-primary" id="saveBtn"
									data-dismiss="modal">Save</button>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
</div>

<script>
var editor;
$(document).ready(function() {
	
	const container = document.getElementById('jsoneditor');
	const options = {};
	editor = new JSONEditor(container, options);
	viewData(null);
	$('#saveBtn').click(function() {
		$('#data').text(JSON.stringify(editor.get(), null, 4));
	})
	
	$('#submitBtn').click(function() {
		
	var value = $("#addIsoConfigForm").validate({
		rules: {
			name: {
                    required: true,
                    regex: /^[a-zA-Z]+$/,
                    minlength: 2,
                    maxlength: 30
            },
		},
    	messages: {
    		name: {
                    required: "Please Enter Name!",
                    minlength: "Minmum {0} characters required!",
                    maxlength: "Maximum {0} characters allowed!",
                    regex: "only Char allow !"
            }
    	},
		submitHandler: function(form) {
			form.submit();	
		}
    	
	});
	});
});
function viewData(data) 
{
	if(data === null || data === undefined) 
	{		
		$.getJSON("${pageContext.request.contextPath}/resources/assets/json/iso.json", function(json) {
			editor.update(json);
			$('#data').text(JSON.stringify(json, null, 4))
		});
	}
	else 
	{
		editor.update(data);	
	}
}
</script>
