package controllers;

import models.Hotel;
import play.*;
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
    /*opening register form */
    public Result register(){
        return ok(register.render());
    }

    public Result registerHotel(){
        List<Hotel> hotels = Hotel.finder.all();
        return ok(hotel.render(hotels));
    }
}
