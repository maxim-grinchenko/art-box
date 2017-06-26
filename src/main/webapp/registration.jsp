<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ArtBox</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body class="body_register">
	<div class="general_container">
		<div class="register_page">
			<h1 class="title_register">Registration</h1>
			  <div class="formholder">
				<div class="randompad">
					<form action="registration" method="POST" class="form_registration">
							<label>Email</label> 
							<input type="email" name="email" value="" placeholder="example@example.com" />
							<p class="${type_reg_email}">${message_reg_email}</p>
							<label>Password</label> 
							<input type="password" name="pass" value="" placeholder="input your password" /> 
							<p class="${type_register_pass}">${message_reg_pass}</p>
							<label>Confirm password</label> 
							<input type="password" name="conf_pass" value="" placeholder="repeate your password" /> 
							<p class="${type_register_pass}">${message_reg_pass}</p>
							<input type="submit" class="section" value="Registration" />
					</form>
				</div>
			  </div>
		</div>
	</div>
</body>
</html>