package models;


import com.avaje.ebean.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;
import javax.persistence.*;

import java.util.ArrayList;

/**
 * Created by Edvin on 9/6/2015.
 */
@Entity
public class Hotel extends Model {
    public static Finder<String, Hotel> finder = new Finder<>(Hotel.class);

    @Id
    public Integer id;
    public String name;
    public String location;
    public String description;
    public String coordinateX;
    public String coordinateY;
    public Integer sellerId;

    @ManyToMany
    public List<Feature> features;

    @OneToMany
    public List<Image> images;
    public List<Room> rooms;
    public Hotel(Integer id, String name, String location, String description, String coordinateX, String coordinateY,
                 Integer sellerId, List<Feature> features ,List<Image> images, List<Room> rooms){


        this.id = id;
        this.name = name;
        this.location = location;
        this.description = description;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.sellerId = sellerId;

        this.features = features;
        this.images = images;

        this.features = features;
        this.rooms = rooms;


    }

    public static Hotel findHotelById(Integer id) {
        Hotel hotel = finder.where().eq("id", id).findUnique();

        return hotel;
    }

    @Override
    public String toString() {
        return (id.toString() + " " + name);
    }

}