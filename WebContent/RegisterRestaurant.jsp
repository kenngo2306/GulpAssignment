<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.14.0/jquery.validate.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.14.0/jquery.validate.min.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.14.0/additional-methods.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.14.0/additional-methods.min.js"></script>
<title>Register Restaurant</title>
</head>
<body>
	
	<div class= "panle panel-primary col-sm-6 col-sm-offset-3">
		<div class ="panel-heading">
		
			New Restaurant
			
		</div>
		
		<div class="panel-body">
			${message}
			<form role="form" action="RegisterRestaurant" method = "POST">
				<div class="form-group">
					<label for="name">Name:</label>
					<input type="text" class="form-control" name="name" required/>
				</div>
				
				<div class="form-group">
					<label for="address">Address:</label>
					<input type="text" class="form-control" name="address" required/>
				</div>
				
				<div class="form-group">
					<label for="description">Description:</label>
					<input type="text" class="form-control" name="description" required/>
				</div>
				
				<div class = "form-group">
					<button type="submit" value = "submit" class= "btn btn-default" >Register</button>
					
					
				</div>

			</form>
			
		</div>
	</div>
</body>
</html>