//global variables which shall be initialized in functions
var cityName, countryName, temp, conditions, description, longitude, latitude;

function getData() {
	// getting value of the field in var name
	var name = document.getElementById("name").value;
	var xhttp = new XMLHttpRequest();
	// link to get json from api
	var link = "http://api.openweathermap.org/data/2.5/weather?q=" + name + "&units=metric&APPID=1fece17149b11bf41a66f24302098d20";
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var result = JSON.parse(this.responseText);
			// values to be displayed
			cityName = result.name;
			countryName = result.sys.country;
			temp = result.main.temp;
			conditions = result.weather[0].main;
			description = result.weather[0].description;
			longitude = result.coord.lon;
			latitude = result.coord.lat;
			// html for shoeing result
			var html = "<div id = \"card\" class=\"card\">"
					+ "<div class=\"card-body\">"
					+ "<h4 class=\"card-title\">"
					+ cityName
					+ ", "
					+ countryName
					+ "</h5> <hr>"
					+ "<center><table class = \"table\"><tr><td class = \"tableData\"><b>Temperature</b></td><td class = \"tableData\">"
					+ temp
					+ "</td></tr><tr><td class = \"tableData\"><b>Conditions</b></td><td class = \"tableData\">"
					+ conditions
					+ "</td></tr><tr><td class = \"tableData\"><b>Description</b></td><td class = \"tableData\">"
					+ description
					+ "</td></tr><tr><td class = \"tableData\"><b>Longitude</b></td><td class = \"tableData\">"
					+ longitude
					+ "</td></tr><tr><td class = \"tableData\"><b>Latitude</b></td><td class = \"tableData\">"
					+ latitude
					+ "</td></tr></table></center><hr>"
					+ "<button class=\"btn btn-primary\" onclick = \"addFav()\" type = \"button\">Add to favorites</button>"
					+ "</div>" + "</div>";

			// content to be replaced
			document.getElementById("data").innerHTML = html;
			//favorites list to be hidden
			document.getElementById("fav").innerHTML = "";
		}
	};
	xhttp.open("GET", link, true);
	xhttp.send();
}

function addFav() {
	var xmlhttp = new XMLHttpRequest();
	

	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			document.getElementById("fav").innerHTML = xmlhttp.responseText;
		}
	};
	// values to be sent to servlet
	var values = "city=" + cityName + "&country=" + countryName + "&temperature="
			+ temp + "&conditions=" + conditions + "&description=" + description + "&longitude="
			+ longitude + "&latitude=" + latitude;
	var PATH = "http://localhost:8080/bucketList/favorite?";
	xmlhttp.open('GET', PATH + values,true);
	xmlhttp.send();
}

function getFavourites() {
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			document.getElementById("fav").innerHTML = xmlhttp.responseText;
			document.getElementById("card").innerHTML = "";
		}
	};
	// values to be sent to servlet
	var values = "city=" + cityName + "&country=" + countryName + "&temperature="
			+ temp + "&conditions=" + conditions + "&description=" + description + "&longitude="
			+ longitude + "&latitude=" + latitude;
	var PATH = "http://localhost:8080/bucketList/viewSaved";
	xmlhttp.open('GET', PATH, true);
	xmlhttp.send();
}

// i is the index of the json object to be deleted
function removeFav(i){
	
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			document.getElementById("fav").innerHTML = xmlhttp.responseText;
		}
	};
	var PATH = "http://localhost:8080/bucketList/deleteValue?value=";
	//passing the value of the index
	xmlhttp.open('POST', PATH + i, true);
	xmlhttp.send();
}
