package controllers;

import models.Feature;
import models.Hotel;
import play.mvc.*;
import views.html.*;


import java.util.List;

public class Application extends Controller {

    /*opening a front page on start*/
    public Result index() {
        List<Hotel> hotels = Hotel.finder.all();
        return ok(list.render(hotels));
    }
    public Result registerHotel(){
        List<Feature> features = Hotels.featureFinder.all();
        return ok(addhotel.render(features));
    }

    public Result insertFeature(){
        return ok(feature.render());
    }

}
