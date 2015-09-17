import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import models.App_User;
import org.junit.*;

import controllers.routes;
import play.mvc.*;
import play.test.*;
import play.data.DynamicForm;
import play.data.validation.ValidationError;
import play.data.validation.Constraints.RequiredValidator;
import play.i18n.Lang;
import play.libs.F;
import play.libs.F.*;
import play.twirl.api.Content;

import static play.test.Helpers.*;
import static org.junit.Assert.*;
import static org.fest.assertions.Assertions.*;


/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class ApplicationTest {

    // Ajla

    @Before
    public void configureDatabase() {
        fakeApplication(inMemoryDatabase());
    }

    @Test
    public void testDB() {

        App_User u = new App_User();
        u.email = "ajla@tabari.me";
        u.firstname = "Ajla";
        u.lastname = "Tabari";
        u.password = "nekiheahovanipasskojisenemozehackovati";
        u.userTypeId = 3;

        u.save();

        assertNotNull(u);
    }

    @Test
    public void testSavingInDatabase() {
        App_User u = new App_User();
        u.email = "ajla@tabari.me";
        u.firstname = "Ajla";
        u.lastname = "Tabari";
        u.password = "nekiheahovanipasskojisenemozehackovati";
        u.userTypeId = 3;

        u.save();
    }

    @Test
    public void testIfHotelDoesNotExist(){
        App_User u = App_User.getUserByEmail("nepostojeci@mail.ba");

        assertNull(u);
    }

    @Test
    public void testNonexistantUser() {
        App_User u = App_User.existsInDB("ajla@tabari.me");

        assertNull(u);
    }

    // Alen

    @Test
    public void testHotelThatDontExist(){
        Hotel h = Hotel.findHotelById(333);

        assertNull(h);
    }


    @Test
    public void testInsertHotel(){
        Hotel h = new Hotel();
        h.name = "HOtel1";
        h.location = "Location1";
        h.description = "description 1";
        h.save();


    }


    @Test
    public void testDeleteHotel(){
        Hotel h = new Hotel();
        h.name = "HOtel1";
        h.location = "Location1";
        h.description = "description 1";
        h.save();

        Hotel h1 = Hotel.findHotelById(1);
        h1.delete();
    }

    @Test
    public void testDatabase(){
        Hotel h = new Hotel();
        h.name = "HOtel1";
        h.location = "Location1";
        h.description = "description 1";
        h.save();

        List<Hotel> list = Hotel.finder.all();

        assertNotNull(list);
    }

    // Gordan

    @Test
<<<<<<< HEAD
    public void nonExistantFeature(){
        Feature f = Feature.findFeaturelById(-3);

        assertNull(f);
    }

    @Test
    public void testSavingFeatureInDatabase() {
        Feature f = new Feature();
        f.hotel = new Hotel(151,"Emiran","Aleja bb","Fantazija","48.1512","26.1654",null);
        f.name = "Ping pong";
        //f.icon = null;
        f.id = -3;
        f.save();
=======
    public void checkSavingIntoBase(){

        running(fakeApplication(), new Runnable() {
            @Override
            public void run() {
                App_User user = new App_User("Ajla", "El Tamburi", "ajla@tamburica.com", "ajla555", "546545");

                user.save();
            }
        });

    }

    @Test
    public void checkReadingFromTheBase(){

        running(fakeApplication(), new Runnable() {
            @Override
            public void run() {
                App_User user = App_User.existsInDB("ajla@tamburica.com");
                assertNull(user);
            }
        });

    }

    @Test
    public void testIndex() {
        running(fakeApplication(), () -> {
            Result result = route(routes.Application.index());
            assertThat(result.status()).isEqualTo(OK);
            //assertThat(result.redirectLocation()).isEqualTo("");
        });
>>>>>>> fixed inserting features issue
    }

    @Test
    public void testingSavingAndLoadingFeature(){
        Feature f = new Feature();
        f.hotel = new Hotel(151,"Emiran","Aleja bb","Fantazija","48.1512","26.1654",null);
        f.name = "Ping pong";
        //f.icon = null;
        f.id = -3;
        f.save();

        Feature f1 = Feature.findFeaturelById(-3);
        assertNotNull(f1);
    }


   /* @Test
    public void testSavingAndLoading() {
        App_User u = new App_User();
        u.email = "ajla@tabari.me";
        u.firstname = "Ajla";
        u.lastname = "Tabari";
        u.password = "nekiheahovanipasskojisenemozehackovati";
        u.userTypeId = 3;

        u.save();

        App_User u1 = App_User.authenticate("ajla@tabari.me", "nekiheahovanipasskojisenemozehackovati");

        assertNotNull(u1);
    }*/

}
