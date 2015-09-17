package controllers;

import models.App_User;
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
        List<App_User> users = App_User.getUsersByUserTypeId(5);
        return ok(addhotel.render(features, users));
    }

    public Result insertFeature(){
        return ok(feature.render());
    }

}
