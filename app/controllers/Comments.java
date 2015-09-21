
package controllers;

import com.avaje.ebean.Ebean;
import models.App_User;
import models.Comment;
import models.Hotel;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;


public class Comments extends Controller {

    private static final Form<Comment> commentForm = Form.form(Comment.class);

    public Result insertComment(Integer hotelId) {
        Form<Comment> boundForm = commentForm.bindFromRequest();


        Comment comment = boundForm.get();

        comment.hotel = Hotel.findHotelById(hotelId);
        App_User user = App_User.getUserByEmail(request().cookies().get("email").value());
        Hotel hotel = Hotel.findHotelById(hotelId);
        comment.user = user;

        Ebean.save(comment);
        return redirect(routes.Hotels.showHotel(comment.hotel.id));
    }



    public Result deleteComment(Integer id) {
        Comment comment = Comment.findCommentById(id);
        comment.delete();

        return redirect(routes.Hotels.showHotel(comment.hotel.id));
    }

}

