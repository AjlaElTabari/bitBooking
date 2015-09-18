package controllers;

import com.avaje.ebean.Ebean;
import models.Feature;
import models.Image;
import models.Room;
import org.apache.commons.io.FileUtils;
import play.Play;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import views.html.editFeature;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;


public class Features extends Controller {

    private static final Form<Feature> featureForm = Form.form(Feature.class);

    public Result insertFeature() {

        Form<Feature> boundForm = featureForm.bindFromRequest();
        Feature feature = boundForm.get();

        Http.MultipartFormData body = request().body().asMultipartFormData();
        List<Http.MultipartFormData.FilePart> pictures = body.getFiles();

        if (pictures != null) {
            for (Http.MultipartFormData.FilePart picture : pictures) {
                String fileName = picture.getFilename();
                File file = picture.getFile();

                try {
                    FileUtils.moveFile(file, new File(Play.application().path() + "/public/images/" + fileName));
                    feature.image = fileName;
                } catch (IOException ex) {
                    play.Logger.info("Could not move file. " + ex.getMessage());
                    flash("error", "Could not move file.");
                }
            }
        }
        Ebean.save(feature);
        return redirect(routes.Features.insertFeature());
    }
    public Result deleteFeature(Integer id){
        Feature feature = Feature.findFeatureById(id);
        Ebean.delete(feature);

        return redirect(routes.Users.showAdminFeatures());
    }
    public Result editfeature(Integer id){
        Feature feature = Feature.findFeatureById(id);
        return ok(editFeature.render(feature));
    }

    public Result updateFeature(Integer id){
        Form<Feature> boundForm = featureForm.bindFromRequest();
        Feature feature = Feature.findFeatureById(id);

        String name = boundForm.bindFromRequest().field("name").value();

        feature.name = name;

        Ebean.update(feature);

        return redirect(routes.Users.showAdminFeatures());
    }

}
