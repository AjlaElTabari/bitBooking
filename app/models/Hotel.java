package models;


import com.avaje.ebean.Model;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

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

    @ManyToMany(cascade=CascadeType.ALL)
    public List<Feature> feature;


    public Hotel(Integer id, String name, String location, String description, List<Feature> feature){
        this.id = id;
        this.name = name;
        this.location = location;
        this.description = description;
        this.feature = feature;
    }




}