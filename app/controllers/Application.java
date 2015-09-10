package controllers;

import models.Feature;
import models.Hotel;
import play.mvc.*;
import views.html.*;


import java.util.List;

public class Application extends Controller {
  public static  List<Hotel> hotels = Hotel.finder.all();

    /*opening a front page on start*/
    public Result index() {
        return ok(list.render(hotels));
    }
    /*opening a login in message*/
    public Result loginmessage() {
        return ok(loginmessage.render());
    }
    /*opening a refistration message*/
    public Result registrationmessage() {
        return ok(registrationmessage.render());
    }
//    /*opening register form */
//    public Result register(){
//        return ok(register.render(userForm));
//    }

    public Result registerHotel(){
        List<Feature> features = Hotels.featureFinder.all();
        return ok(addhotel.render(features));
    }

    public Result insertFeature(){
        return ok(feature.render());
    }
}
