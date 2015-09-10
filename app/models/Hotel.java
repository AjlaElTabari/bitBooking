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
    public static Finder<String, Hotel> finder = new Finder<>(String.class, Hotel.class);

    @Id
    public Integer id;
    public String name;
    public String location;
    public String description;
    public String coordinateX;
    public String coordinateY;

    @ManyToMany(cascade=CascadeType.ALL)
    public List<Feature> feature;


    public Hotel(Integer id, String name, String location, String description, String coordinateX, String coordinateY, List<Feature> feature){
        this.id = id;
        this.name = name;
        this.location = location;
        this.description = description;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.feature = feature;
    }

    public static Hotel findHotelById(Integer id) {
        Hotel hotel = finder.where().eq("id", id).findUnique();

        return hotel;
    }
    @Override
    public String toString()
    {
        return (id.toString() + " " + name + " " + feature);
    }

}