<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	$(document).ready(function() {
		$('#phoneTable').DataTable({
			 scrollX: 100,
			 scroller: true
		});
	});
	
	function editFunction() {     
        if($('input[type=radio][name=strSelectID]:checked').length == 0)
        {
           alert("Please select atleast one");
           return false;
        }
        else
        {
            return true;
        }      
      }
	
	function deleteFunction() {     
        if($('input[type=radio][name=strSelectID]:checked').length == 0)
        {
        	alert("Please select atleast one");
           return false;
        }
        else
        {
            return true;
        }      
      }
</script>

<div class="my-3 my-md-5">
	<div class="container">
		<div class="row">
			<div class="col-12">
			
				<form:form action="phoneConfig" method="post" commandName="phoneTypeModel"
					name="phoneConfig" id="phoneConfig" cssClass="card">
					
					<div class="card-header">
						<h3 class="card-title"><i class="fe fe-user-plus"></i> &nbsp;&nbsp;Phone Type Configuration </h3>
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
									<label class="form-label">Phone Type <b>*</b></label>
									<form:input path="strPhone" id="strPhone"
										cssClass="form-control" />
									<span class="error" id="strPhoneError"><form:errors
											path="strPhone"></form:errors></span>
								</div>
							</div>
							
							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Ext <b>*</b></label>
									<form:input path="strExt" id="strExt"
										cssClass="form-control" />
									<span class="error" id="strExtError"><form:errors
											path="strExt"></form:errors></span>
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
					
					<div class="card-body">
					<table id="phoneTable" class="table table-hover table-outline table-vcenter text-nowrap card-table" style="width:100%">
					<thead>
						<tr>
							<th class="text-center w-1">Select</th>
							<th>Phone Type</th>
							<th>Ext</th>
							<th width="3%"></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="itr" items="${phoneList}">
							<tr>
								<td>
									<label class="custom-control custom-radio">
									<form:radiobutton path="strSelectID"
											cssClass="custom-control-input approveID"
											value="${itr.strID}"
											id="strSelectID" />
                            <div class="custom-control-label"></div>
                          </label>
								</td>
								<td>${itr.strPhone}</td>
								<td>${itr.strExt}</td>
								<td>
									<a class="icon" href="javascript:editFunction()">
                              		<i class="fe fe-edit"></i>
                            		</a>
                            		&nbsp;&nbsp;&nbsp;
                            		<a class="icon" href="javascript:deleteFunction()">
                              		<i class="fe fe-trash"></i>
                            		</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				</div>	
				</form:form>
			</div>
		</div>
	</div>
</div>
