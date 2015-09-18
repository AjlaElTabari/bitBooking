package models;

import com.avaje.ebean.Model;
import com.avaje.ebeaninternal.server.lib.util.Str;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Image extends Model{
    private static Finder<String, Image> finder = new Finder<>(String.class, Image.class);
    @Id
    public Integer id;

    public String path;

    @ManyToOne(cascade= CascadeType.ALL)
    public Hotel hotel;

    public Room room;

    public Image(String path, Hotel hotel, Room room){
        this.path=path;
        this.hotel = hotel;
        this.room = room;
    }

    public static List<String> getPaths(Hotel hotel){
        List<Image> images = finder.where().eq("hotel",hotel).findList();
        List<String> paths = new ArrayList<String>();
        for(Image image: images){
            paths.add(image.path);
        }
        return paths;
    }

    public String toString() {
        return path + "\n";
    }
}