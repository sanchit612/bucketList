<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- meta tags -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- bootstrap cdn stylesheet -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<!-- css file -->
<link rel="stylesheet" href="css/index.css">
<!-- google font -->
<link href="https://fonts.googleapis.com/css?family=Muli" rel="stylesheet">

<!-- font awesome -->
<script defer
	src="https://use.fontawesome.com/releases/v5.0.7/js/all.js"></script>

<title>Weather Bucket List</title>
</head>
<body>
	
	<nav class="navbar navbar-dark bg-default" id = "navig"> 
	<button onclick = "location.href='http://localhost:8080/bucketList/index.jsp';" class="btn btn-secondary my-2 my-sm-0" id = "homeButton" type="button">Home</button>
	<a id="title" class="navbar-brand" href="index.jsp">Weather Bucket List</a>
	
		<button onclick = "getFavourites()" class="btn btn-secondary my-2 my-sm-0" id = "savedButton" type="button">Show Saved</button>
		</nav>

<!-- hero image and form in front -->
	<div class="hero-image">
		<div class="hero-text">
		<p id = "welcomeText">
			Welcome to Weather Bucket List <br>
			Add any city to your watch list<br> Track it for the instantaneous weather
			</p>			
			<form id="search" >
				<input id="name" class="form-control mr-sm-2" type="text"
					placeholder="Enter Location" aria-label="Search">
					<br>
				<button onclick="getData()"
					class="btn btn-outline-light my-2 my-sm-0" id = "searchButton" type="button">Go!</button>
			</form>
		</div>
	</div>


	<div id="data"></div>


	<div id="fav"></div>

	<!-- bootstrap javascript -->
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>
	<!-- my javascript file -->
	<script src="js/index.js"></script>
</body>
</html>