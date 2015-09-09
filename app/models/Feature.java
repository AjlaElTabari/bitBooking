
package models;

import com.avaje.ebean.Model;

import javax.persistence.*;

/**
 * Created by Edvin on 9/6/2015.
 */
@Entity
public class Feature extends Model {
    @Id
    public Integer id;
    public String name;

    @ManyToMany(cascade=CascadeType.ALL)
    public Hotel hotel;

    public Feature(Integer id, String name){
        this.id = id;
        this.name = name;

    }

}