package controllers;

import com.avaje.ebean.Ebean;
import models.Comment;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by Zeljko Miljevic on 9/13/2015.
 */
public class Comments extends Controller {

    private static final Form<Comment> commentForm = Form.form(Comment.class);

//    public Result insertComment() {
//        Form<Comment> boundForm = commentForm.bindFromRequest();
//        Comment comment = boundForm.get();
//
//        Ebean.save(comment);
//        return redirect(routes.Comment.insertComment());
//    }
//
//    public Result editComment(Integer id) {
//        Comment comment = Comment.findCommentById(id);
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
