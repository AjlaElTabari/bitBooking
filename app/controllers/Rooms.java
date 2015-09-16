package controllers;

import com.avaje.ebean.Ebean;
import models.Room;
import play.data.Form;
import play.mvc.Controller;

import javax.xml.transform.Result;

/**
 * Created by Zeljko Miljevic on 9/16/2015.
 */
public class Rooms extends Controller {

    private static final Form<Room> roomForm = Form.form(Room.class);

    public Result insertRoom() {
        Form<Room> boundRoom = roomForm.bindFromRequest();
        Room room = boundForm.get();

        Ebean.save(room);
        return redirect(routes.Room.insertRoom());
    }

    public Result editRoom(Integer id) {
        Room room = Room.findRoomById(id);
        return ok(editRoom.render(room));
    }

    public Result deleteRoom(Integer id) {
        Room room = Room.findRoomById(id);
        Ebean.delete(room);

        return redirect(routes.Room.deleteRoom());
    }
}
