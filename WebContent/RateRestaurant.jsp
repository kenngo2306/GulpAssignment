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
<title>Rate Restaurant</title>
</head>
<body>
	<div class="panel panel-primary col-sm-6 col-sm-offset-3">
		<div class="panel-heading">
			${restaurant_info}
		</div>
		<div class="panel-body">
			<form action="AddReview" method ="POST">
				<div class="form-group">
					<input type="hidden" name="restaurant_id" value="<%= request.getParameter("restaurant_id") %>"/>
					<label for="stars">Stars:</label>
					<select name="stars" class="form-control">
						<option value = "1">&#9733;</option>
						
						<option value = "2">&#9733;&#9733;</option>
											
						<option value = "3">&#9733;&#9733;&#9733;</option>
											
						<option value = "4">&#9733;&#9733;&#9733;&#9733;</option>
											
						<option value = "5">&#9733;&#9733;&#9733;&#9733;&#9733;</option>
					</select>
				</div>
				<div class="form-group">
					<label for="description">Feedback/Comments:</label>
					<br>
					<textarea name="description" class="form-control" rows="5" cols="50"></textarea>
				</div>
				
				<br>
				<input type="submit" class="button btn-primary" value= "submit"/>  
			</form>
		</div>
	</div>
</body>
</html>