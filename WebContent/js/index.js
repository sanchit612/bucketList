//global variables which shall be initialized in functions
var cityName, countryName, temp, conditions, longitude, latitude;

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
			longitude = result.coord.lon;
			latitude = result.coord.lat;
			// html for shoeing result
			var html = "<div id = \"card\" class=\"card\" style=\"width: 20rem;\">"
					+ "<div class=\"card-body\">"
					+ "<h4 class=\"card-title\">"
					+ cityName
					+ ", "
					+ countryName
					+ "</h5>"
					+ "<p class=\"card-text\">Temperature : "
					+ temp
					+ "<br>Conditions : "
					+ conditions
					+ "<br>Longitude : "
					+ longitude
					+ "<br>Latitude : "
					+ latitude
					+ "</p>"
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
			+ temp + "&conditions=" + conditions + "&longitude="
			+ longitude + "&latitude=" + latitude;
	xmlhttp.open('GET', "http://localhost:8080/bucketList/favorite?" + values,true);
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
			+ temp + "&conditions=" + conditions + "&longitude="
			+ longitude + "&latitude=" + latitude;
	xmlhttp.open('GET', "http://localhost:8080/bucketList/readjson", true);
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
	//passing the value of the index
	xmlhttp.open('POST', "http://localhost:8080/bucketList/deletejson?value="+i, true);
	xmlhttp.send();
}
