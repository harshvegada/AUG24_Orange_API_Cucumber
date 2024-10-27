package base;

import constants.FilePaths;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.junit.Assert;
import utility.PropertyFileReader;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class CommonServices extends BaseServices {

    private static String csrfToken;
    public static Map<String, String> cookies;
    static PropertyFileReader propertyFileReader = new PropertyFileReader(FilePaths.END_POINT_PROPERTIES);

    private static String getAccessToken(String username, String password) {
        getCSRFToken();
        validateCredentials(username, password);

        Response response = RestAssured.given()
                .baseUri(propertyFileReader.getValue("BASE_URI"))
                .cookies(cookies)
                .when()
                .get(propertyFileReader.getValue("GET_LOGGED_IN_ACCOUNT_TOKEN"))
                .then()
                .extract().response();

        accessToken = response.jsonPath().getString("token.access_token");
        Assert.assertNotNull(accessToken);
        Assert.assertEquals(response.statusCode(), 200);
        return accessToken;
    }

    private static void validateCredentials(String username, String password) {
        Response response = RestAssured.given()
                .baseUri(propertyFileReader.getValue("BASE_URI"))
                .cookies(cookies)
                .formParam("login[_csrf_token]", csrfToken)
                .formParam("hdnUserTimeZoneOffset", 5.5)
                .formParam("txtUsername", username)
                .formParam("txtPassword", password)
                .when()
                .post(propertyFileReader.getValue("VALIDATE_CREDENTIALS"))
                .then()
                .extract().response();
        cookies = response.getCookies();
        Assert.assertEquals(response.statusCode(), 200);
    }

    private static void getCSRFToken() {

        Response response = RestAssured.given()
                .baseUri(propertyFileReader.getValue("BASE_URI"))
                .when()
                .get(propertyFileReader.getValue("CSRF_TOKEN"))
                .then().extract().response();

        cookies = response.getCookies();
        Document document = Jsoup.parse(response.asString());
        csrfToken = document.getElementById("login__csrf_token").attr("value");
        System.out.println("CSRF Token : " + csrfToken);
        Assert.assertNotNull(csrfToken);
        System.out.println(response.getTimeIn(TimeUnit.SECONDS));

        Assert.assertEquals(response.statusCode(), 200);
    }

    public static String generateTokenFor(String userName, String password) {
        return getAccessToken(userName, password);
    }

}
