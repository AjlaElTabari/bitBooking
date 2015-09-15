package controllers;

import com.avaje.ebean.Ebean;
import models.Feature;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

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
}
