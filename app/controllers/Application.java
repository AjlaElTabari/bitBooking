package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public Result index() {
        return ok(frontpage.render());
    }
    public Result loginmessage() {
        return ok(loginmessage.render());
    }
    public Result registrationmessage() {
        return ok(registrationmessage.render());
    }
    public Result register(){
        return ok(register.render());
    }
}
