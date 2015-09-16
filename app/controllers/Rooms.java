package controllers;

import com.avaje.ebean.Ebean;

import com.avaje.ebean.Model;
import models.Feature;
import models.Hotel;
import models.Room;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.editRoom;
import views.html.sellerpanel;

import java.util.List;

/**
 * Created by User on 9/16/2015.
 */
public class Rooms extends Controller {

    public static final Form<Room> roomForm = Form.form(Room.class);
    public static Model.Finder<String, Room> finder = new Model.Finder<String, Room>(Room.class);
    public static Model.Finder<String, Feature> featureFinder = new Model.Finder<String, Feature>(Feature.class);
    public static Model.Finder<String, Hotel> hotelFinder = new Model.Finder<String, Hotel>(Hotel.class);


    public Result insertRoom() {

        Form<Room> boundForm = roomForm.bindFromRequest();
        Room room = boundForm.get();

        Ebean.save(room);
        return redirect(routes.Application.index());
    }

    public Result updateRoom(Integer id) {

        Room room = Room.findRoomById(id);
        Form<Room> roomForm1 = roomForm.bindFromRequest();

        String description = roomForm1.bindFromRequest().field("description").value();

        room.description = description;

        Ebean.update(room);

        return redirect(routes.Rooms.showRoom(room.id));
    }
    public Result deleteRoom(Integer id){
        Room room = Room.findRoomById(id);
        Ebean.delete(room);
        return ok("Neka poruka");
    }

    public Result editRoom(Integer id) {
        Room room = Room.findRoomById(id);
        return ok(editRoom.render(room));
    }


    public Result showRoom (Integer id) {
        Room room = Room.findRoomById(id);
        return ok(views.html.room.render(room));
    }
    public Result showSellerHotels() {
        List<Hotel> hotels = hotelFinder.all();
        return ok(sellerpanel.render(hotels));

    }
}
