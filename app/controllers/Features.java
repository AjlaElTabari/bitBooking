package controllers;

import com.avaje.ebean.Ebean;
import models.Feature;
import models.Room;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.editFeature;

import java.util.logging.Logger;

/**
 * Created by ajla.eltabari on 09/09/15.
 */
public class Features extends Controller {

    private static final Form<Feature> featureForm = Form.form(Feature.class);

    public Result insertFeature() {

        Form<Feature> boundForm = featureForm.bindFromRequest();

        Feature feature = boundForm.get();
        Ebean.save(feature);
        return redirect(routes.Features.insertFeature());
    }
    public Result deleteFeature(Integer id){
        Feature feature = Feature.findFeatureById(id);
        Ebean.delete(feature);

        return redirect(routes.Users.showAdminFeatures());
    }
    public Result editfeature(Integer id){
        Feature f = Feature.findFeatureById(id);
        return ok(editFeature.render(f));
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
