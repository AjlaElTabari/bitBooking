package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by Zeljko Miljevic on 9/13/2015.
 */
@Entity
public class Comment extends Model {
    public static Finder<String, Comment> finder = new Finder<String, Comment>(Comment.class);
    /*
     *Comment atributes
     */
    @Id
    public Integer id;
    @ManyToOne
    public Integer user_id;
    @ManyToOne
    public Integer hotel_id;
    public String title;
    public String content;
    public Integer rating;

    /*
     *Default constructor
     */
    public Comment(Integer id, Integer user_id, Integer hotel_id, String title, String content, Integer rating) {
        this.id = id;
        this.user_id = user_id;
        this.hotel_id = hotel_id;
        this.title = title;
        this.content = content;
        this.rating = rating;
    }

    public static Comment findCommentById (Integer id){
        Comment comment = finder.where().eq("id", id).findUnique();
        return comment;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", hotel_id=" + hotel_id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", rating=" + rating +
                '}';
    }
}
