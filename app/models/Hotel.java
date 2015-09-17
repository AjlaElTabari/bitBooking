package models;


import com.avaje.ebean.Model;

import java.util.List;
import javax.persistence.*;

import java.util.ArrayList;

/**
 * Created by Edvin on 9/6/2015.
 */
@Entity
public class Hotel extends Model {

    private static Finder<String, Hotel> finder = new Finder<>(Hotel.class);

    @Id
    public Integer id;
    public String name;
    public String location;
    public String description;
    public String coordinateX;
    public String coordinateY;

    @ManyToMany
    public List<Feature> features;

    @OneToMany
    public List<Image> images;

    @OneToMany(mappedBy = "hotel")
    private List<Comment> comments;

    public Hotel(Integer id, String name, String location, String description, String coordinateX, String coordinateY, List<Feature> feature,List<Image> images,List<Comment> comments){

        this.id = id;
        this.name = name;
        this.location = location;
        this.description = description;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.features = feature;
        this.images = images;
        this.comments = comments;
    }

    public static Hotel findHotelById(Integer id) {
        Hotel hotel = finder.where().eq("id", id).findUnique();

        return hotel;
    }

    public static List<Hotel> allHotels() {
        return Hotel.finder.all();
    }

    @Override
    public String toString()
    {
        return (id.toString() + " " + name );
    }

}