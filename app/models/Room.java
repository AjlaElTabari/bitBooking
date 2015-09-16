package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by Zeljko Miljevic on 9/14/2015.
 */
@Entity
public class Room extends Model {

    /*
     *Room atributes
     */
    @Id
    public Integer id;
    public Integer numberOfBeds;
    public String description;
    @ManyToOne
    public Hotel hotel;

    /*
     *Default constructor
     */
    public Room(Integer id, Integer numberOfBeds, String description, Hotel hotel) {
        this.id = id;
        this.numberOfBeds = numberOfBeds;
        this.description = description;
        this.hotel = hotel;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", numberOfBeds=" + numberOfBeds +
                ", description='" + description + '\'' +
                ", hotel=" + hotel +
                '}';
    }
}
