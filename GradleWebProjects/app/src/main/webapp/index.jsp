<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
	
<!-- Above the import line for the jstl which is not working here with my form tag -->
<!DOCTYPE html>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<c:out value="${ pageContext.servletContext.contextPath }"></c:out>
	<!--This is a tag of JSTL which does the same work as the tag but JSTL makes the 
									   					the scripting clearer-->
	<p>
		<c:url value="/test"></c:url>
	</p>
	<p>
		<%=new java.util.Date()%>
	</p>
	<!-- Below is the expression language of JSP to write java code and implement its classes and methods without using get or set-->
	<form action="${ pageContext.servletContext.contextPath }/users" method="post">
		<table align="center">
			<tr>
				<td>UserName:</td>
				<td><input type="text" id="name" maxlength="20" name="name"></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type="password" id="pass" name="pass" ></td>
			</tr>

			<tr>
				<td><input type="submit" value="Login"></td>
			</tr> 
		</table>
	</form>
</body>
</html>