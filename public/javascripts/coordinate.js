/**
 * Created by zeljko.miljevic on 10/09/15.
 */
var geocoder = new google.maps.Geocoder();

//this function is determining geocode position
function geocodePosition(pos) {
    geocoder.geocode({
        latLng: pos
    }, function(responses) {
        if (responses && responses.length > 0) {
            updateMarkerAddress(responses[0].formatted_address);
        } else {
            updateMarkerAddress('Cannot determine address at this location.');
        }
    });
}

//this function is updating marker status
function updateMarkerStatus(str) {
    document.getElementById('markerStatus').innerHTML = str;
}

//this function is updating marker position
function updateMarkerPosition(latLng) {
    document.getElementById('latitude').innerHTML = [
        latLng.lat(),
        latLng.lng()
    ].join(', ');
    document.getElementById('longitude').value = latLng.lat();
    document.getElementById('latitude').value = latLng.lng();
}

//this function is updating coordinate
function updateCoordinate(latLng) {
    document.getElementById('latitude').value = latlng.lat;
    document.getElementById('longitude').value = latlng.lng;
}

//this function is initializing the map
function initialize() {
    var latLng = new google.maps.LatLng(43.8534183, 18.3780840);
    var map = new google.maps.Map(document.getElementById('map'), {
        zoom: 12,
        center: latLng,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    });

    var marker = new google.maps.Marker({
        position: latLng,
        map: map,
        animation:google.maps.Animation.BOUNCE,
        title:'Click to zoom',
        draggable: true
    });


    google.maps.event.addListener(marker,'click',function() {
        map.setZoom(16);
        map.setCenter(marker.getPosition());
        infowindow.open(map,marker);
    });

    //create circle options
    var circleOptions = {
        fillColor: 'white',
        map: map,
        center: latLng,
        radius: 1000
    };

    //create circle
    myCircle = new google.maps.Circle(circleOptions);

    //when marker has completed the drag event
    //recenter the circle on the marker.
    google.maps.event.addListener(marker, 'dragend', function(){
        myCircle.setCenter(this.position);
    });

    // Update current position info.
    updateMarkerPosition(latLng);
    geocodePosition(latLng);


    google.maps.event.addListener(marker, 'drag', function() {
        updateMarkerPosition(marker.getPosition());
    });
}

// Onload handler to start the app.
google.maps.event.addDomListener(window, 'load', initialize);