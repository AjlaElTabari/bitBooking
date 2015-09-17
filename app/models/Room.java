package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;

/**
 * Created by User on 9/16/2015.
 */
@Entity
public class Room extends Model {
    public static Finder<String, Room> finder = new Finder<String, Room>(Room.class);

    @Id
    public Integer id;
    public String description;
    public  Integer numberOfBeds;
    @ManyToMany
    public List<Feature> features;

    @ManyToOne
    public Hotel hotel;

    public Room(){

    }
    public Room (Integer id, String description, List<Feature> features, Hotel hotel, Integer numberOfBeds){
        this.id = id;
        this.description= description;
        this.features = features;
        this.hotel = hotel;
        this.numberOfBeds= numberOfBeds;
    }
    public static Room findRoomById(Integer id) {
        Room room = finder.where().eq("id", id).findUnique();

        return room;

    }
}
