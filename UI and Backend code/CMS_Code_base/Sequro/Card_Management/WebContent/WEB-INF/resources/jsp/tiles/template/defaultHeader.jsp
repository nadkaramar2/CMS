<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="header py-4">
	<div class="container">
		<div class="d-flex">
			<%--   <a class="header-brand" href="./index.html"> <img src="${pageContext.request.contextPath}/resources/assets/images/logo.jpg" class="header-brand-img" alt="">
			 </a>  --%>
			<a class="header-brand" href="./index.html"> <img src="${pageContext.request.contextPath}/resources/assets/images/logo_sequro.jpg" class="header-brand-img" alt="">
			 </a>
			 
			<div class="d-flex order-lg-2 ml-auto">
				Last Login : ${user.last_successful_logon}
				<div class="dropdown">
					<a href="#" class="nav-link pr-0 leading-none"
						data-toggle="dropdown"> <span class="avatar"
						style="background-image: url(${pageContext.request.contextPath}/resources/assets/images/user.png)"></span>
						<span class="ml-2 d-none d-lg-block"> <span
							class="text-default">${user.username}</span> <small
							class="text-muted d-block mt-1">${user.participantDesc}</small>
					</span>
					</a>
					<div class="dropdown-menu dropdown-menu-right dropdown-menu-arrow">
						<a class="dropdown-item" href="#"> <i
							class="dropdown-icon fe fe-user"></i> Profile
						</a>
						
						<a class="dropdown-item" href="resetPassword"> <i
							class="dropdown-icon fe fe-user"></i> Change Password
						</a>
						
						<!-- <a class="dropdown-item" href="loginForm?logout">  -->
						<div class="dropdown-divider"></div>
						 <a class="dropdown-item" href="logout"> <i
							class="dropdown-icon fe fe-log-out"></i> Sign out
						</a>
					</div>
				</div>
			</div>
			<a href="#" class="header-toggler d-lg-none ml-3 ml-lg-0"
				data-toggle="collapse" data-target="#headerMenuCollapse"> <span
				class="header-toggler-icon"></span>
			</a>
		</div>
	</div>
</div> 

<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import = "java.io.*,java.util.*"%>
<%
Date lastaccessTime = new Date(session.getLastAccessedTime());
%>
<div class="header">
	<c:url value="/logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
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
				
			</script>
<meta http-equiv="refresh" content="<%=(session.getMaxInactiveInterval())%>; url=/Card_Management/" />

<div id="demo" style="position:absolute; font-weight:bold; color:#9aa0ac; right:225px; top:56px; font-size: 13px; "></div> --%>