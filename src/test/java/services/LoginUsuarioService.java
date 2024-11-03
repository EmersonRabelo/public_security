package services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.LoginModel;

import static io.restassured.RestAssured.given;
public class LoginUsuarioService {
    final LoginModel loginModel = new LoginModel();
    public final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    String baseUrl = "http://localhost:8080/";
    public Response response;

    public void setFieldsDelivey(String field, String value) {
        switch (field){
            case "email" -> loginModel.setEmail(value);
            case "password" -> loginModel.setPassword(value);
            default -> throw new IllegalStateException("Unexpected feld" + field);
        }
    }

    public void createDelivery(String endpoint) {
        String url = baseUrl+endpoint;
        String body = gson.toJson(loginModel);

        response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(body)
                .when()
                .post(url)
                .then()
                .extract()
                .response();
        retrieveTokenDelivery();
    }

    public String retrieveTokenDelivery() {
        String responseBody = response.body().asString();
        if (responseBody != null && !responseBody.isEmpty()) {
            JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();
            if (jsonObject.has("token")) {
                return jsonObject.get("token").getAsString();
            }
        }
        return "";
    }

}
