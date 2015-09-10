package controllers;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Model;
import models.Feature;
import models.Hotel;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.editHotel;
import views.html.hotel;
import views.html.list;
import views.html.main;

import java.util.List;


public class Hotels extends Controller {

    private static final Form<Hotel> hotelForm = Form.form(Hotel.class);
    private static Model.Finder<String, Hotel> finder = new Model.Finder<>(String.class, Hotel.class);
    public static Model.Finder<String, Feature> featureFinder = new Model.Finder<>(String.class,Feature.class);

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

    public List<Feature> listOfFeatures(){
        List<Feature> features = featureFinder.all();
        return features;
    }

    public Result showHotel(Integer id) {
        Hotel hotel = Hotel.findHotelById(id);
        return ok(views.html.hotel.render(hotel));
    }

    public Result editHotel(Integer id) {
        Hotel hotel = Hotel.findHotelById(id);
        return ok(editHotel.render(hotel));
    }
}