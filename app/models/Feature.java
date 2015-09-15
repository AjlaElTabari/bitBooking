
package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Edvin on 9/6/2015.
 */
@Entity
public class Feature extends Model {
    public static Finder<String, Feature> finder = new Finder<>(String.class, Feature.class);

    @Id
    public Integer id;
    public String name;

    @ManyToMany(mappedBy = "features")
    public List<Hotel> hotels;

    public Feature(Integer id, String name, List<Hotel> hotels){
        this.id = id;
        this.name = name;
        this.hotels = new LinkedList<Hotel>(hotels);
        for(Hotel h: hotels){
            h.features.add(this);
        }
    }

    public static Feature findFeatureById(Integer id) {
        Feature feature = finder.where().eq("id", id).findUnique();

        return feature;
    }

}
