package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Comment extends Model {
    public static Finder<String, Comment> finder = new Finder<String, Comment>(Comment.class);
    /*
     *Comment atributes
     */
    @Id
    public Integer id;
    @ManyToOne
    public App_User user;
    @ManyToOne
    public Hotel hotel;
    public String title;
    public String content;
    public Integer rating;

    /*
     *Default constructor
     */
    public Comment(Integer id, App_User user, Hotel hotel, String title, String content, Integer rating) {
        this.id = id;
        this.user = user;
        this.hotel = hotel;
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
                ", user_id=" + user +
                ", hotel_id=" + hotel +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", rating=" + rating +
                '}';
    }
}
