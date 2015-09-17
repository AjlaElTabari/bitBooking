package controllers;

import com.avaje.ebean.Ebean;
import models.App_User;
import models.Comment;
import models.Hotel;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.logging.Logger;

/**
 * Created by Zeljko Miljevic on 9/13/2015.
 */
public class Comments extends Controller {

    private static final Form<Comment> commentForm = Form.form(Comment.class);

    public Result insertComment(Integer hotelId) {
        Form<Comment> boundForm = commentForm.bindFromRequest();


        Comment comment = boundForm.get();

        comment.hotel = Hotel.findHotelById(hotelId);
        App_User user = App_User.getUserByEmail(request().cookies().get("email").value());

        comment.user = user;



        Ebean.save(comment);
        return ok("Radi");
    }
//
//    public Result editComment(Integer id) {
//        Comment comment = Comment.findCommenotById(id);
//        return ok(editComment.render(comment));
//    }
//
//    public Result deleteComment(Integer id) {
//        Comment comment = Comment.findCommentById(id);
//        Ebean.delete(comment);
//
//        return redirect(routes.Comment.deleteComment());
//    }
}
