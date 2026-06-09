<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
.custom-control-label 
{
	position: static;
	margin-bottom: 0;
}

.card-title 
{
	font-size: 1.0rem;
	line-height: 1.0;
	font-weight: 400;
	margin-bottom: 1.5rem;
}
</style>
<div class="my-3 my-md-5">
	<div class="container">
		<div class="row">
			<div class="col-12">
				<form:form action="addRole" method="post" commandName="roleBean"
					name="addRole" id="addRoleForm" cssClass="card">

					<div class="card-header">
						<h3 class="card-title">
							<i class="fe fe-user-plus"></i> &nbsp;&nbsp;Add Role
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

							<div class="col-md-4 col-lg-4">
								<div class="form-group">
									<label class="form-label">Role Name <b>*</b></label>
									<form:input path="strRoleName" id="strRoleName"
										cssClass="form-control" />
									<span class="error" id="strRoleNameError"><form:errors
											path="strRoleName"></form:errors></span>
								</div>
							</div>

							<div class="col-md-4 col-lg-4">
								<div class="form-group">
									<label class="form-label">Role Description <b>*</b></label>
									<form:input path="strDescription" id="strDescription"
										cssClass="form-control" />
									<span class="error" id="strDescriptionError"><form:errors
											path="strDescription"></form:errors></span>
								</div>
							</div>

							<div class="col-md-4 col-lg-4">
								<div class="form-group">
									<label class="form-label">Role Group Name <b>*</b></label>
									<form:select path="strGroupID" id="strGroupID"
										cssClass="form-control custom-select">
										<c:forEach items="${groupList}" var="itr">
											<form:option value="${itr.lkpkey}">${itr.lkpvalue}</form:option>
										</c:forEach>
									</form:select>
									<span class="error" id="strGroupIDError"><form:errors
											path="strGroupID"></form:errors></span>
								</div>
							</div>

							&nbsp;&nbsp;&nbsp;&nbsp;
							<h6>
								<i class="fa fa-check"></i> Privileges
							</h6>
							<c:forEach items="${roleBean.parentMenuList}" var="parentMenu">
								<div class="col-lg-12">
									<div class="card card-collapsed">
										<div class="card-header">
											<h6 class="card-title">
												<c:out value="${parentMenu.parentMenuName}" />
											</h6>
											<div class="card-options">
												<a href="#" class="card-options-collapse"
													data-toggle="card-collapse"><i class="fe fe-chevron-up"></i></a>
											</div>
										</div>
										<c:forEach items="${roleBean.subMenuList}" var="subParentMenu">
											<c:if
												test="${parentMenu.parentMenuId == subParentMenu.parentMenuId}">
												<div class="card-body">
													<c:out value="${subParentMenu.subMenuName}" />
													<br> <br>
													<c:forEach items="${roleBean.menuList}" var="menu">
														<c:if
															test="${subParentMenu.parentSubMenuId == menu.parentSubMenuId}">
															<label
																class="custom-control custom-checkbox custom-control-inline">
																<form:checkbox path="strMenuIds"
																	class="custom-control-input ${subParentMenu.parentSubMenuId}"
																	value="${menu.menuId}"
																	onclick="updateParentCheck(${subParentMenu.parentSubMenuId})" />
																<span class="custom-control-label"><c:out
																		value="${menu.menuName}" /></span>
															</label>
														</c:if>
													</c:forEach>
												</div>
											</c:if>
										</c:forEach>
									</div>
								</div>
							</c:forEach>
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
