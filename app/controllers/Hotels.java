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

    public Result updateHotel(Integer id) {

        Hotel hotel = Hotel.findHotelById(id);
        Form<Hotel> hotelForm1 = hotelForm.bindFromRequest();

        String name = hotelForm1.bindFromRequest().field("name").value();
        String location = hotelForm1.bindFromRequest().field("location").value();
        String description = hotelForm1.bindFromRequest().field("description").value();

        hotel.name = name;
        hotel.location = location;
        hotel.description = description;

        Ebean.update(hotel);

        return ok(hotel.toString());
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

    /*This method allows hotel manager to delete hotels*/
    public Result deleteHotel(Integer id) {
        Hotel hotel = Hotel.findHotelById(id);
        Ebean.delete(hotel);

        return redirect(routes.Users.showManagerHotels());
    }

    /*This method allows admin to delete hotels*/
    public Result deleteHotelAdmin(Integer id) {
        Hotel hotel = Hotel.findHotelById(id);
        Ebean.delete(hotel);

        return redirect(routes.Users.showAdminHotels());
    }



    public List<Hotel> listOfHotels() {
        List<Hotel> hotels = finder.all();
        return hotels;
    }
}