package services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.EmergenciaModel;

import static io.restassured.RestAssured.given;

public class CadastroEmergenciaService {
    EmergenciaModel emergenciaModel = new EmergenciaModel();
    public final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    String baseUrl = "http://localhost:8080/";
    public Response response;
    public String token;

    public void setFieldsDelivery(String field, String value) {
        switch (field) {
            case "emergencyId" -> emergenciaModel.setEmergencyId(Integer.parseInt(value));
            case "requesterEmail" -> emergenciaModel.setRequesterEmail(value);
            case "address" -> emergenciaModel.setAddress(value);
            case "type" -> emergenciaModel.setType(value);
            case "title" -> emergenciaModel.setTitle(value);
            case "description" -> emergenciaModel.setDescription(value);
            case "status" -> emergenciaModel.setStatus(value);
            default -> throw new IllegalStateException("Unexpected field: " + field);
        }
    }

    public void createDelivery(String endpoint) {
        String url = baseUrl + endpoint;
        String body = gson.toJson(emergenciaModel);

        response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + this.token) // Adiciona o Bearer Token ao cabeçalho
                .body(body)
                .when()
                .post(url)
                .then()
                .extract()
                .response();
    }

    public void restoreLoginToken(String token) {
        this.token = token;
    }
}
