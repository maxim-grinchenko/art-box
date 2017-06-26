<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ArtBox</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="general_container">
		<h1 class="title">ArtBox</h1>

		<div class="menu">
			<%@include file="menu.jsp"%>
		</div>

		<div class="discription">
<!-- 		<button id="myBtn">Open Modal</button> -->

		<div class="autorisation">
			<%@include file="registration.jsp"%>
		</div>
			<p>Description ArtBox web-service:</p>
			<p>The service is used for create, delete and search positions goods (further positions).</p>
			<p>The page "<span class="uppercase">add</span>" is used to added positions.</p>

			<ul>
				<li>field "<b>Title</b>" - name positions, can compose of letters, numbers, signs;</li>
				<li>field "<b>Age</b>" - recommended age of use, should compose only of numbers;</li>
				<li>field "<b>Cost</b>" - price, should compose only of numbers;</li>
			</ul>
			
			<p>Button "<b>Save</b>" - saves the inputed positions to the database. </p>

			<p>The page "<span class="uppercase">dashboard</span>" is used to search for positions by title (name). </p>

			<p>When the search query matches, the result is displayed under the search panel, where the sequence number in the
				database, name, age, cost is displayed.</p>

			<p>The "<b>delete</b>" button (last column) removes the position from the database.</p>

			<hr>
			<p>Terms and conditions of use:</p>

			<p class="price">
				Trial period - 14 days <br> The full version - $ 650.00
			</p>
			<hr>
			<p>If you want to purchase this web-service, you need to send your money by email: 
				<a href="mailto:maxima@artbox.com?subject=Purchase ArtBox">maxima@artbox.com</a><br> 
				<span class="note">*All money from the sale goes to charity </span></p>
		</div>
	</div>
	
<!-- 	<div id="myModal" class="modal"> -->
<!--   <div class="modal-content"> -->
<!-- 		<span class="close">&times;</span> -->
<!--   <div id="navthing"> -->
<!--       <h2><a href="#" id="loginform">Login</a> or <a href="#" id="registerform">Register</a></h2> -->
<!-- 		<div class="login"> -->
<!-- 		  <div class="formholder"> -->
<!-- 			<div class="randompad"> -->
<!-- 			   <fieldset> -->
<!-- 				 <label name="email">Email</label> -->
<!-- 				 <input type="email" value="" placeholder="example@example.com"/> -->
<!-- 				 <label name="password">Password</label> -->
<!-- 				 <input type="password" placeholder="input your password"/> -->
<!-- 				 <input type="submit" value="Login" /> -->
<!-- 			   </fieldset> -->
<!-- 			</div> -->
<!-- 		  </div> -->
<!-- 		</div> -->
	
<!-- 		<div class="register"> -->
<!-- 		  <div class="formholder"> -->
<!-- 			<div class="randompad"> -->
<!-- 			   <fieldset> -->
<!-- 				 <label name="email">Email</label> -->
<!-- 				 <input type="email" value="" placeholder="example@example.com"/> -->
<!-- 				 <label name="password">Password</label> -->
<!-- 				 <input type="password" placeholder="input your password"/> -->
<!-- 				 <input type="password" placeholder="confirm your password"/> -->
<!-- 				 <input type="submit" value="Registration" /> -->
<!-- 			   </fieldset> -->
<!-- 			</div> -->
<!-- 		  </div> -->
<!-- 		</div> -->
<!-- 	</div> -->
<!--   </div> -->
<!-- </div>  -->

	<div class="footer">
		<p class="copyright">&#169; GM ArtBoxSystems (Brovary)</p>
	</div>
	


<!-- <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script> -->
<!-- <script src="js/index.js"></script> -->
<!-- <script src="js/script_popup.js"></script> -->
</body>
</html>