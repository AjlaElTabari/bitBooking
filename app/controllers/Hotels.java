package controllers;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Model;
import models.Feature;
import models.Hotel;
import play.Logger;
import models.Image;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.editHotel;

import java.util.*;

import views.html.hotel;
import views.html.list;
import views.html.main;
import play.mvc.Http.MultipartFormData;

import java.io.File;
import java.util.List;
import play.Logger;

public class Hotels extends Controller {

    private static final Form<Hotel> hotelForm = Form.form(Hotel.class);
    private static Model.Finder<String, Hotel> finder = new Model.Finder<>(Hotel.class);
    public static Model.Finder<String, Feature> featureFinder = new Model.Finder<>(Feature.class);


    public Result insertHotel() {

        Form<Hotel> boundForm = hotelForm.bindFromRequest();
        Hotel hotel = boundForm.get();

        List<Feature> features = listOfFeatures();
        //Getting values from checkboxes
        List<String> checkBoxValues = new ArrayList<>();
        for (int i = 0; i < features.size(); i++) {
            String feature = boundForm.bindFromRequest().field(features.get(i).name).value();

            if (feature != null) {
                checkBoxValues.add(feature);
            }
        }

        List<Feature> featuresForHotel = new ArrayList<Feature>();

        for (int i = 0; i < checkBoxValues.size(); i++) {
            for (int j = 0; j < features.size(); j++) {
                if (features.get(j).name.equals(checkBoxValues.get(i))) {
                    featuresForHotel.add(features.get(i));
                }
            }
        }

        Logger.debug(featuresForHotel.toString());

        //Removing null elements from list
        //checkBoxValues
        features.removeAll(Collections.singleton(null));

        hotel.features = featuresForHotel;

        MultipartFormData body = request().body().asMultipartFormData();
        MultipartFormData.FilePart filePart = body.getFile("image");
        if(filePart != null){
            Logger.debug("Content type: " + filePart.getContentType());
            Logger.debug("Key: " + filePart.getKey());
            File file = filePart.getFile();
            Image image = Image.create(file);
            hotel.images.add(image);
        }
        Ebean.save(hotel);
        return redirect(routes.Application.index());
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

        return redirect(routes.Hotels.showHotel(hotel.id));
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