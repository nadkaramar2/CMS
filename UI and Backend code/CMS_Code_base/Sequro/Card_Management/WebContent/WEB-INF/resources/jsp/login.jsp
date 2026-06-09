<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<meta http-equiv="Content-Language" content="en" />
<meta name="msapplication-TileColor" content="#2d89ef">
<meta name="theme-color" content="#4188c9">
<meta name="apple-mobile-web-app-status-bar-style"
	content="black-translucent" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="mobile-web-app-capable" content="yes">
<meta name="HandheldFriendly" content="True">
<meta name="MobileOptimized" content="320">
<!-- Generated: 2018-04-06 16:27:42 +0200 -->
<title>Login</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,300i,400,400i,500,500i,600,600i,700,700i&amp;subset=latin-ext">
<!-- Login Validation -->
<script
	src="${pageContext.request.contextPath}/resources/assets/js/vendors/jquery-3.2.1.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/assets/js/jquery.validate.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/assets/js/lib/aes.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/assets/js/lib/pbkdf2.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/assets/js/AesUtil.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/assets/js/login/login.js"></script>

<script
	src="${pageContext.request.contextPath}/resources/assets/js/require.min.js"></script>
<script>
	requirejs.config({
		baseUrl : '.'
	});
	
	
	document.addEventListener('keyup',(e)=>{
		navigator.clipboard.writeText('');
		console.log("Screenshot Disabled");
	});

	document.onkeydown= function(e)
	{
		if (event.keyCode==123)
			{
				return false;
			}
		if(e.ctrlKey && e.shiftKey && e.keyCode == 'I'.charCodeAt(0))
			{
			return false;
			}
		if(e.ctrlKey && e.shiftKey && e.keyCode == 'J'.charCodeAt(0))
			{
			return false;
			}
		if(e.ctrlKey && e.keyCode == 'U'.charCodeAt(0))
		{
		return false;
		}
	}
	
	
	 document.addEventListener("contextmenu", (event) => 
	 {
         event.preventDefault();
      });
	
</script>
<!-- Dashboard Core -->
<!-- <script>
$(document).ready(function() 
{
	var value = $("#loginForm").validate(
	{
		rules : 
		{
			institutionID : {
				required : true
			}
		 },
		 messages : 
		 {
			 strGLAccountType : {
				required : "Please Select Participant!"
			}
		 },
		submitHandler : function(form) 
		{
			alert('Submit clicked...');
			//form.submit();
		}
	});
});
</script> -->
<link
	href="${pageContext.request.contextPath}/resources/assets/css/dashboard.css"
	rel="stylesheet" />
<script
	src="${pageContext.request.contextPath}/resources/assets/js/dashboard.js"></script>
<!-- c3.js Charts Plugin -->
<link
	href="${pageContext.request.contextPath}/resources/assets/plugins/charts-c3/plugin.css"
	rel="stylesheet" />
<script
	src="${pageContext.request.contextPath}/resources/assets/plugins/charts-c3/plugin.js"></script>
<!-- Google Maps Plugin -->
<link
	href="${pageContext.request.contextPath}/resources/assets/plugins/maps-google/plugin.css"
	rel="stylesheet" />
<script
	src="${pageContext.request.contextPath}/resources/assets/plugins/maps-google/plugin.js"></script>
<!-- Input Mask Plugin -->
<script
	src="${pageContext.request.contextPath}/resources/assets/plugins/input-mask/plugin.js"></script>
<style>
.mb-6, .my-6 {
    margin-bottom: -2rem !important;
}
</style>
</head>
<body class="">
	<div class="page">
		<div class="page-single">
			<div class="container">
				<div class="row">
					<div class="col col-login mx-auto">
						
						<form:form method="post" cssClass="card" commandName="login" action="${pageContext.request.contextPath}/loginForm" name="loginForm" id="loginForm">
							<div class="card-body p-6">
								
								<div class="text-center">
								 <%-- <img src="${pageContext.request.contextPath}/resources/assets/images/logo.jpg" width="60%" height="50%" alt=""><label class="form-label" text-align = "right">(MCMS v3.0)</label></div> --%>
								 <img src="${pageContext.request.contextPath}/resources/assets/images/logo_sequro.jpg" width="60%" height="50%" alt=""><label class="form-label" text-align = "right">(CMS v3.0)</label></div> 
								<div class="card-title">Login to your account</div>
								<c:if test="${not empty error}">
									<div class="error">${error}</div>
								</c:if>
								<c:if test="${not empty msg}">
									<div class="error">${msg}</div>
								</c:if>
								<div class="form-group">
									<label class="form-label">User Name *</label> 
									<input type="text" class="form-control" id="username" name="username" aria-describedby="emailHelp" placeholder="User Name">
								</div>
								<div class="form-group">
									<label class="form-label"> Password * </label>
									 <input type="password" class="form-control" id="password" placeholder="Password" name="password">
								</div>
								<div class="form-group">
									<label class="custom-control custom-checkbox"> 
										<input type="checkbox" class="custom-control-input" checked="checked" />
										<span class="custom-control-label">Remember me</span>
									</label>
								</div>
								<div class="form-group">
									<label class="form-label">Participant</label>
									<form:select path="institutionID" cssClass="form-control custom-select" id="institutionID">
										<form:option value="">Select</form:option>
										<c:forEach items="${instList}" var="itr">
											<form:option value="${itr.lkpkey}">${itr.lkpvalue}</form:option>
										</c:forEach>
									</form:select>
								</div>
								<div class="form-footer">
									<button type="submit" class="btn btn-primary btn-block">Sign in</button>
								</div>
							</div>
						</form:form>
						<!-- <div class="text-center text-muted">
							Don't have account yet? <a href="./register.html">Sign up</a>
						</div> -->
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>