package controllers;

import com.avaje.ebean.Ebean;
import models.App_User;
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
    private static final Form<App_User> userForm = Form.form(App_User.class);
    private static List<Hotel> hotels = Hotel.finder.all();


    /*opening register form */
    public Result register(){
        return ok(register.render(userForm));
    }

    /*insert registered user into database*/
    public Result insertUser() {
        Form<App_User> boundForm = userForm.bindFromRequest();

        //getting the values from the fields
        String pass1 =boundForm.bindFromRequest().field("password").value();
        String pass2 = boundForm.bindFromRequest().field("passwordretype").value();
        String name = boundForm.bindFromRequest().field("firstname").value();
        String lastname = boundForm.bindFromRequest().field("lastname").value();
        String phone = boundForm.bindFromRequest().field("phoneNumber").value();

        //patterns and filters

        //validation of the form

        if(!pass1.equals(pass2)) {
            flash("error", "Passwords don't match!");
            return ok(register.render(boundForm));

        }else if(pass1.length() < 6 && pass2.length() < 6) {
            flash("error", "Password must be at least 6 characters long!");
            return ok(register.render(boundForm));

        }else if((!name.matches("^[a-zA-Z]+$/")) && (!lastname.matches("^[a-zA-Z]+$"))) {
            flash("error", "Name and last name must contain letters only!");
            return ok(register.render(boundForm));

        }else if(name.length() < 2 && lastname.length() < 2) {
            flash("error", "Name and last name must be at least 2 letters long!");
            return ok(register.render(boundForm));

        }else if(phone.length() > 15) {
            flash("error", "Phone number can't be more than 15 digits long!");
            return ok(register.render(boundForm));

        }else if(phone.matches("^[a-zA-Z]+$")) {
            flash("error", "Phone number must contain digits only!");
            return ok(register.render(boundForm));

        }else{
            App_User user;
            try {
                user = boundForm.get();
                user.hashPass();
                Ebean.save(user);
                return ok(registrationmessage.render());
            }catch (Exception e){
                flash("error", "Email already exist in our database, please try with another email!");
                return ok(register.render(boundForm));
            }




        }
    }


    public Result login() {
        Form<App_User> boundForm = userForm.bindFromRequest();

        String email = boundForm.bindFromRequest().field("email").value();
        String password = boundForm.bindFromRequest().field("password").value();

        App_User user = App_User.authenticate(email, password);

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
