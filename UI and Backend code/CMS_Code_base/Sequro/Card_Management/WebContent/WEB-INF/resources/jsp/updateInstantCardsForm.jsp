<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">
$(document).ready(function() {
	$('#cardTable').DataTable();
	$("#updateBtn").click(function() {
		if ($('input[type=radio][name=strSelectID]:checked').length == 0) {
			$('#errorMsg').text("Please select atleast one");
				return false;
		} else {
				$('#updateInstantCards').attr('action', 'updateClientForm');
				//$("#updateInstantCards").attr("method", "get");
				return true;
			}	
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
				<form:form action="updateInstantCards" method="post"
					commandName="serviceBean" name="updateInstantCards" id="updateInstantCards"
					cssClass="card">

					<div class="card-header">
						<h3 class="card-title">Update Instant Cards &nbsp;&nbsp;</h3> <h2 id="errorMsg" class="tag tag-red"></h2>
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

							<div class="col-md-8">
										<div class="col-md-8 col-lg-8">
										<div class="form-group">
											<label class="form-label">Card Number</label>
											<form:input path="inCard" id="inCard"
												cssClass="form-control" />
										</div>
										</div>
									</div>

							<input type="hidden" value="${leftCardMenuID}" id="leftCardMenuID">
							
						</div>
							<c:if test="${cardList.size() > 0 }">  
								<div class="card-body" style="width: 100%;" align="left">
					<table id="cardTable" class="table table-striped table-bordered nowrap">
					<thead>
						<tr>
							<th>Select</th>
							<th>Card Number</th>
							<th>Card Type</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="itr" items="${cardList}">
							<tr>
								<td>
									<label class="custom-control custom-radio">
									<form:radiobutton path="strSelectID"
											cssClass="custom-control-input approveID"
											value="${itr.cardFlag}"
											id="strSelectID"  />
                            <div class="custom-control-label"></div>
                          </label>
								</td>
								<td>${itr.inCard}</td>
								<td>${itr.inCardType}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				</div>	  
							</c:if>
					</div>
					<div class="card-footer text-right">
						<div class="btn-list text-right">
							<button type="submit" id="submitBtn" name="strRequestBtn"
								value="1" class="btn btn-primary">Search</button>
								<c:if test="${cardList.size() > 0 }">  
							<button type="submit" id="updateBtn" name="updateBtn"
								value="0" class="btn btn-secondary">Update</button>
								</c:if>	
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>