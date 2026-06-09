<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
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
<title>Card Management</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,300i,400,400i,500,500i,600,600i,700,700i&amp;subset=latin-ext">
<script
	src="${pageContext.request.contextPath}/resources/assets/js/vendors/jquery-3.2.1.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/assets/js/jquery.dataTables.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/assets/js/dataTables.bootstrap4.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/assets/js/jquery.validate.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/assets/js/jquery.multi-select.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/assets/js/bootstrap-datepicker.min.js"></script>
<link
	href="${pageContext.request.contextPath}/resources/assets/css/jsoneditor.css"
	rel="stylesheet" />	
<script
	src="${pageContext.request.contextPath}/resources/assets/js/jsoneditor.js"></script>	
<script
	src="${pageContext.request.contextPath}/resources/assets/js/require.min.js"></script>
<script>
      requirejs.config({
          baseUrl: '.'
      });

        document.addEventListener('keyup',(e)=>{
  		navigator.clipboard.writeText('');
  		console.log("Screenshot Disabled");
  	});
  	
  	
  	/* document.onkeydown= function(e)
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
 */      
    </script>
<!-- Dashboard Core -->
<%-- <link href="${pageContext.request.contextPath}/resources/assets/css/bootstrap.css" rel="stylesheet" /> --%>
<link
	href="${pageContext.request.contextPath}/resources/assets/css/dataTables.bootstrap4.min.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/resources/assets/css/bootstrap-datepicker.min.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/resources/assets/css/multi-select.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/resources/assets/css/responsive.bootstrap.min.css"
	rel="stylesheet" />
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
</head>
<body>
	<div class="page">
		<div class="page-main">
			<header id="header">
				<tiles:insertAttribute name="header" />
			</header>

			<section id="menu">
				<tiles:insertAttribute name="menu" />
			</section>

			<section id="site-content">
				<tiles:insertAttribute name="body" />
			</section>
		</div>
	</div>
	<footer id="footer">
		<tiles:insertAttribute name="footer" />
	</footer>

	<%
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		httpServletResponse.setHeader("Pragma", "no-cache");
		httpServletResponse.setDateHeader("Expires", 0);

		String cookie1 = "mycookie=test; Secure; HttpOnly";
		response.addHeader("Set-Cookie", cookie1);
		response.addHeader("X-XSS-Protection", "1; mode=block");
		Cookie cookie = new Cookie("mycookie", "test");
		cookie.setSecure(true);
		response.addHeader("X-Frame-Options", "DENY");
	%>

</body>
</html>