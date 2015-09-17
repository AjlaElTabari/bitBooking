package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Comment extends Model {

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
    public String commentDate;
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

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", user=" + user.firstname +
                ", hotel=" + hotel.name +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", date='" + commentDate + '\'' +
                ", rating=" + rating +
                '}';
    }
}
