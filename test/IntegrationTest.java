import models.App_User;
import org.junit.*;

import play.mvc.*;
import play.test.*;
import play.libs.F.*;

import static play.test.Helpers.*;
import static org.junit.Assert.*;

import static org.fluentlenium.core.filter.FilterConstructor.*;

public class IntegrationTest {

    /**
     * add your integration test here
     * in this example we just check if the welcome page is being shown
     */

    @Test
    public void test() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                browser.goTo("http://localhost:3333/register");

                browser.fill("#email").with("ajla@tabari.me");
                browser.fill("#password").with("password");
                browser.fill("#password-retype").with("password");
                browser.fill("#name").with("Ajla");
                browser.fill("#last-name").with("Tabari");
                browser.fill("#phone-number").with("061333222");
                browser.submit("#submit");

            }
        });
    }

}
