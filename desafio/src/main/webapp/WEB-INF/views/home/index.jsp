<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body> 
Inclua um usuario para acessar serviços Rest:
<form:form role="form" commandName="usuario" servletRelativeAction="/" method="POST">
	Username: <form:input path="username"/>
	</br>
	Password: <form:password path="password"/>
	</br>
	<button type="submit" class="btn btn-primary">Submit</button>
</form:form>

</form>
</body>
</html>