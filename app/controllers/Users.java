package controllers;

import com.avaje.ebean.Ebean;
import models.AppUser;
import models.Hotel;
import play.mvc.Result;
import play.data.Form;
import play.mvc.Controller;
import views.html.*;
import java.util.List;


/**
 * Created by ajla on 9/2/15.
 */
public class Users extends Controller {
    private static final Form<AppUser> userForm = Form.form(AppUser.class);
    private static List<Hotel> hotels = Hotel.finder.all();
    public Result insertUser() {
       Form<AppUser> boundForm = userForm.bindFromRequest();

        AppUser user = boundForm.get();
        user.hashPass();

        try {
            Ebean.save(user);
            return ok(registrationmessage.render());
        } catch (Exception e) {
            flash("error", "Email already exists.");
            return ok(register.render());
        }
    }

    public Result login() {
        Form<AppUser> boundForm = userForm.bindFromRequest();

        String email = boundForm.bindFromRequest().field("email").value();
        String password = boundForm.bindFromRequest().field("password").value();

        AppUser user = AppUser.authenticate(email, password);

        if (user == null) {
            flash("error","Incorrect email or password! Please try again!");
            return badRequest(list.render(hotels));
        } else {
            return ok(loginmessage.render());
        }
    }

    public Result editUser(){
        return ok(userProfilPage.render());
    }
    public Result showHotel(){
        return ok(hotel.render());
    }
}
