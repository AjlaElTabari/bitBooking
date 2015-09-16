package models;


import com.avaje.ebean.Model;
import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import play.Logger;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import play.Play;

@Entity
public class Image extends Model {

    @Id
    public Integer id;

    public String public_id;

    public String image_url;

    public String secret_image_url;

    @ManyToOne
    public Hotel hotel;

    public static Cloudinary cloudinary;


    public static Finder<Integer, Image> find = new Finder<Integer, Image>(Image.class);

    public static Image createImage(String public_id, String image_url, String secret_image_url) {
        Image i = new Image();
        i.public_id = public_id;
        i.image_url = image_url;
        i.secret_image_url = secret_image_url;
        i.save();
        return i;
    }

    public static Image create(File image) {
        Map result;
        cloudinary = new Cloudinary("cloudinary://"+ Play.application().configuration().getString("cloudinary.string"));
        try {
            result = cloudinary.uploader().upload(image, null);
            return create(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Image create(Map uploadResult) {
        Image i = new Image();

        i.public_id = (String) uploadResult.get("public_id");
        Logger.debug(i.public_id);
        i.image_url = (String) uploadResult.get("url");
        Logger.debug(i.image_url);
        i.secret_image_url = (String) uploadResult.get("secure_url");
        Logger.debug(i.secret_image_url);

        i.save();
        return i;
    }


    public String getSize(int width, int height) {

        String url = cloudinary.url().format("jpg")
                .transformation(new Transformation().width(width).height(height).crop("fit").effect("sepia"))
                .generate(public_id);

        return url;
    }

    public String getThumbnail(){
        String url = cloudinary.url().format("png")
                .transformation(
                        new Transformation().width(150).height(150).crop("thumb").gravity("face").radius("max")
                )
                .generate(public_id);
        return url;
    }

    public void deleteImage() {

        try {
            cloudinary.uploader().destroy(public_id, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
