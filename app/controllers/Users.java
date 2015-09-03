package controllers;

import com.avaje.ebean.Ebean;
import models.App_User;
import play.    mvc.Result;
import play.data.Form;
import play.mvc.Controller;
import views.html.frontpage;
import views.html.loginmessage;
import views.html.registrationmessage;
import views.html.register;

/**
 * Created by ajla on 9/2/15.
 */
public class Users extends Controller {
    private static final Form<App_User> userForm = Form.form(App_User.class);

    public Result insertUser() {
       Form<App_User> boundForm = userForm.bindFromRequest();

        App_User user = boundForm.get();
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
        Form<App_User> boundForm = userForm.bindFromRequest();

        String email = boundForm.bindFromRequest().field("email").value();
        String password = boundForm.bindFromRequest().field("password").value();

        App_User user = App_User.authenticate(email, password);

        if (user == null) {
            return ok(frontpage.render());
        } else {
            return ok(loginmessage.render());
        }
    }
}
