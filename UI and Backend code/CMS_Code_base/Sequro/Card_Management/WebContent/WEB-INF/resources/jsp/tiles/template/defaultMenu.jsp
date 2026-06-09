<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@ page import = "java.io.*,java.util.*"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%
Date lastaccessTime = new Date(session.getLastAccessedTime());
%>
	<c:url value="/logout" var="logoutUrl" />
	<form:form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form:form>
	<script>
				function formSubmit() {
					document.getElementById("logoutForm").submit();
				}
				
				$(document).ready(function(){
				 var countdownDate=new Date(<%=(System.currentTimeMillis()+session.getMaxInactiveInterval()*1000)%>).getTime();
				 var x=setInterval(function(){
					 var now=new Date();
				 var time=now.getTime();
			 		var distance=countdownDate-time;
					var minutes=Math.floor((distance%(1000 * 60 * 60))/(1000*60));
					var seconds=Math.floor((distance%(1000 * 60 ))/1000);
					document.getElementById("demo").innerHTML="<b>Session expires in "+minutes+" min "+seconds+"  secs</b>";
					if(distance<0){
						clearInterval(x);
						document.getElementById("demo").innerHTML="";
						//document.location.href="sessionout";
					}
				},1000);
				});
				
			</script> --%>
<div class="header collapse d-lg-flex p-0" id="headerMenuCollapse">
	<div class="container">
		<div class="row align-items-center">
		<div class="text-right" id="demo">
		</div>
			
			<div class="col-lg order-lg-first">
				<c:forEach items="${parentMenuList}" var="parentMenu">
				<ul class="nav nav-tabs border-0 flex-column flex-lg-row">
					<li class="nav-item"><a href="home"	
						class="nav-link active"><i class="fe fe-home"></i> Home</a></li>
					<c:forEach items="${subParentMenuList}" var="subParentMenu">
						<c:if
							test="${parentMenu.parentMenuID == subParentMenu.parentMenuID}">
						<li class="nav-item"><a href="javascript:void(0)"
						class="nav-link" data-toggle="dropdown"><i class="fe fe-box"></i>
							<c:out value="${subParentMenu.subMenuName}" /></a>
						<div class="dropdown-menu dropdown-menu-arrow">
							<c:forEach items="${menuList}" var="menu">
							<c:if test="${subParentMenu.parentSubMenuID == menu.parentSubMenuID}">
							<a href="<c:out value="${menu.menuAction}" />" class="dropdown-item ">
							<c:out value="${menu.menuName}" /></a>
							</c:if>
							</c:forEach>
						</div>
						</li>	
					</c:if>
					</c:forEach>			
				</ul>
				</c:forEach>
			</div>
		</div>
	</div>
</div>