var temperature;
var state_of_units = "kelvin";
// A $( document ).ready() block.
$( document ).ready(function () {
    getLocation();
    $("#temp").click(function() {
        var tempString;
        var newTemp = temperature;
        if(state_of_units === "kelvin"){
            newTemp = newTemp - 273.15;
            tempString = Number(newTemp).toFixed(1) + String.fromCharCode(176) + "C";
            $("#temp p").text(tempString);
            state_of_units = "celsius";
        }
        else if(state_of_units === "celsius"){
            newTemp = newTemp * 9.0 / 5.0 - 459.67;
            tempString = Number(newTemp).toFixed(1) + String.fromCharCode(176) + "F";
            $("#temp p").text(tempString);
            state_of_units = "fahrenheit";
        }
        else{
            tempString = temperature.toString() + "K";
            $("#temp p").text(tempString);
            state_of_units = "kelvin";
        }
    });
});
function getLocation(){
if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(getCity);
    } else { 
        alert("Geolocation is not supported by this browser.");
    }
};
function getCity(posObj){
    var latitude = posObj.coords.latitude;
    var longitude = posObj.coords.longitude;
    var url = "http://maps.googleapis.com/maps/api/geocode/json?latlng=" + latitude + ',' + longitude;
    $.getJSON( url, function( data ) {
      var cityName = data.results[1].address_components[0].long_name;
    var countryCode = data.results[1].address_components[3].short_name;
        countryCode;
        getWeather(cityName, countryCode);
    });  
}
function getWeather(city, country){
    var api_key = "cf3d18b7c4ee1842030a7c88db1ab8d7"
    var url = "http://api.openweathermap.org/data/2.5/weather?q={0},{1}&appid={2}".format(city,country, api_key);
    $.getJSON( url, function( data ) {
        var weather = data.weather[0].main;
        console.log(weather);
        var weather_description = data.weather[0].description;
        temperature = data.main.temp;
        updateGraphics(city, country, weather, weather_description, temperature);
    });
}
function updateGraphics(city, country, weather_short, weather_desc, temp){
    var location_text = "{0},{1}".format(city, country);
    $("#location").text(location_text);
    $("#weatherIcon img").attr("src",linkToSVG(weather_short));
    $("#weatherDesc").text(weather_desc);
    var tempString = temp.toString() + "K";
    $("#temp p").text(tempString);
}
function linkToSVG(weather_name){
    if(weather_name === "Clouds"){
        return "http://res.cloudinary.com/arash-outadi/image/upload/v1474248369/clouds.svg";
    }
    else if(weather_name === "Rain"){
        return "http://res.cloudinary.com/arash-outadi/image/upload/v1474248369/rain.svg";
    }
    else{
        alert("The weather is: " + weather_name + " but I don't have an image for that.");
        return "No image sorz";
    }
}


//Code snippet from stack exchange to make inserting strings into other strings WAY easier like python
String.prototype.format = function(i, safe, arg) {

  function format() {
    var str = this, len = arguments.length+1;

    // For each {0} {1} {n...} replace with the argument in that position.  If 
    // the argument is an object or an array it will be stringified to JSON.
    for (i=0; i < len; arg = arguments[i++]) {
      safe = typeof arg === 'object' ? JSON.stringify(arg) : arg;
      str = str.replace(RegExp('\\{'+(i-1)+'\\}', 'g'), safe);
    }
    return str;
  }

  // Save a reference of what may already exist under the property native.  
  // Allows for doing something like: if("".format.native) { /* use native */ }
  format.native = String.prototype.format;

  // Replace the prototype property
  return format;

}();