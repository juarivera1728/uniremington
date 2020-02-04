var map;
var geocoder;

Barvill = {};
Barvill.Module = {};
Barvill.View = {};
Barvill.Widgets = {};

Barvill.Module.initMap = function() {
  map = new google.maps.Map(document.getElementById('map'), {
    center: {lat: 5.0686969, lng: -75.5186625},
    zoom: 15
  });
  geocoder = new google.maps.Geocoder();
};

Barvill.Module.initControls = function() {
  Barvill.Widgets.listBars      = $("#listBars");
  Barvill.Widgets.templateBars  = $("#bar-each");
  Barvill.Widgets.lstBares      = $("#lstBares"); 
  Barvill.Widgets.detailLink    = $(".detailLink"); 
  Barvill.Widgets.barData       = $(".barData"); 
  Barvill.Widgets.detail        = {}; 
  Barvill.Widgets.detail.nombre = $("[data-name='nombre']"); ; 
  Barvill.Widgets.detail.descripcion = $("[data-name='descripcion']"); ; 
  Barvill.Widgets.detail.promocion = $("[data-name='promocion']"); ; 
  
};

Barvill.Module.setMarkerMap = function(barItem) {  
    geocoder.geocode({'address': barItem.direccion + " Manizales, Caldas"}, function(results, status) {
      if (status === 'OK') {
        //map.setCenter(results[0].geometry.location);
        var marker = new google.maps.Marker({
          map: map,
          position: results[0].geometry.location,
          icon: "https://i.imgur.com/dGZZbDE.png"
        });

        marker.info = new google.maps.InfoWindow({
          content: '<h6><b>Bar:</b> ' + barItem.nombre + ' </h6>'
        });

        marker.info.open(map, marker);

        google.maps.event.addListener(marker, 'click', function() {
          marker.info.open(map, marker);
        });

      } else {
        console.log('Geocode was not successful for the following reason: ' + status);
      }
  }); 
};

Barvill.Module.loadMarkers = function() {
    var bars = Barvill.Widgets.listBars.val();
    var ListBars = Barvill.Module.parseBarList(bars);
    
    ListBars.forEach(function(barItem){
        Barvill.Module.setMarkerMap(barItem);
    });
};

Barvill.Module.loadTemplateBars = function(templateId){
    return _.template(Barvill.Widgets.templateBars.html());
};

Barvill.Module.parseBar = function(bar){
    var currentBar = bar.split("|");
    return {
        id: currentBar[0],
        nombre: currentBar[1],
        direccion: currentBar[2],
        telefono: currentBar[3],
        descripcion: currentBar[4],
        imagen : currentBar[5],
        promocion : currentBar[6]
    };
}

Barvill.Module.parseBarList = function(BarList){
    var currentData = JSON.parse(BarList).map(function(bar){
        return Barvill.Module.parseBar(bar);
    });
    return currentData;
};

Barvill.View.loadDetail = function(){
    Barvill.Module.initControls();
    var data = /data=(.+)/.exec(location.href);
    if(data){
        var match = data[1];
        var detail = decodeURIComponent(match);
        var Bar = Barvill.Module.parseBar(detail);
        
        Barvill.Widgets.detail.nombre.html(Bar.nombre);
        Barvill.Widgets.detail.descripcion.html(Bar.descripcion);
        Barvill.Widgets.detail.promocion.html(Bar.promocion);
        
        Barvill.Module.setMarkerMap(Bar);
    }
}

Barvill.View.LoadBars = function(){
    Barvill.Module.initControls();
    var bars = Barvill.Widgets.listBars.val();
    var ListBars = Barvill.Module.parseBarList(bars);
    var compiled = Barvill.Module.loadTemplateBars();
    ListBars.forEach(function(barItem){
        var template = compiled(barItem);
        Barvill.Widgets.lstBares.append(template);
    });
    Barvill.Module.initControls();
    Barvill.Widgets.detailLink.click(function(e){
        e.preventDefault();
        var html = $(this).parent().find(".barData").html();
        var encodeUri = encodeURIComponent(html);
        location.href =  $(this).attr('href') + "?data=" + encodeUri;
    })
};