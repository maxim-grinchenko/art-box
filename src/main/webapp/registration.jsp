<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ArtBox</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>

	<form action="registration" method="POST" class="form_registration">
		<label name="email">Email</label> 
		<input type="email" name="email" value="test@test.com" placeholder="example@example.com" />
		<br/>
		<label name="password">Password</label> 
		<input type="password" name="pass_v1" value="123" placeholder="input your password" /> 
		<br/>
		<label name="password">Repeate password</label> 
		<input type="password" name="pass_v2" value="123" placeholder="repeate your password" /> 
		<input type="submit" value="Register" />
	</form>

</body>
</html>