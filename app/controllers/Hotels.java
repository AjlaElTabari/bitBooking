package controllers;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Model;
import models.Hotel;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.hotel;

import java.util.ArrayList;
import java.util.List;


public class Hotels extends Controller {

    private static final Form<Hotel> hotelForm = Form.form(Hotel.class);
    private static Model.Finder<String, Hotel> finder = new Model.Finder<>(String.class, Hotel.class);

    public Result insertHotel() {

        Form<Hotel> boundForm = hotelForm.bindFromRequest();
        Hotel hotel = boundForm.get();

        Ebean.save(hotel);
            return ok(hotel.toString());
    }

    public Result deleteHotel(Hotel hotel) {
        Ebean.delete(hotel);

        return ok("Nesto");
    }

    public List<Hotel> listOfHotels() {
        List<Hotel> hotels = finder.all();
            return hotels;
    }

}