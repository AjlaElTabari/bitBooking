package controllers;

import models.App_User;
import play.mvc.Controller;
import play.mvc.Http;

/**
 * Created by ajla.eltabari on 09/09/15.
 */
public class SessionHelper extends Controller {
    public static App_User getCurrentUser(Http.Context context) {
        String email = session("email");
        if (email == null) {
            return null;
        } else if (App_User.existsInDB(email) == null) {
            return null;
        } else {
            return App_User.existsInDB(email);
        }
    }
}
