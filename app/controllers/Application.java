package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    /*opening a front page on start*/
    public Result index() {
        return ok(frontpage.render());
    }
    /*opening a login in message*/
    public Result loginmessage() {
        return ok(loginmessage.render());
    }
    /*opening a refistration message*/
    public Result registrationmessage() {
        return ok(registrationmessage.render());
    }
    /*opening register form */
    public Result register(){
        return ok(register.render());
    }
}
