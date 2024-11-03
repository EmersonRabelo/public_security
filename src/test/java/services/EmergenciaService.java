package services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.EmergenciaModel;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

import static io.restassured.RestAssured.given;

public class EmergenciaService {
    EmergenciaModel emergenciaModel = new EmergenciaModel();
    public final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    String baseUrl = "http://localhost:8080/";
    public Response response;
    public String token;
    String schemasPath = "src/test/resources/schemas/";
    JSONObject jsonSchema;
    private final ObjectMapper mapper = new ObjectMapper();
    String emergencyIDDelivery;

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

    private JSONObject loadJsonFromFile(String filePath) throws IOException, JSONException {
        try (InputStream inputStream = Files.newInputStream(Paths.get(filePath))) {
            String content = new String(inputStream.readAllBytes());
            return new JSONObject(new JSONTokener(content));
        }
    }

    public void setContract(String contract) throws JSONException, IOException {
        switch (contract) {
            case "Cadastro bem-sucedido de emergência" -> jsonSchema = loadJsonFromFile(schemasPath+"cadastro-bem-sucedido-de-emergencia.json");
            default ->throw new IllegalStateException("Unexpected contract" + contract);
        }
    }

    public Set<ValidationMessage> validateResponseAgainstSchema() throws IOException, JSONException {

        // Obter o corpo da resposta como String e converter para JSONObject
        JSONObject jsonResponse = new JSONObject(response.getBody().asString());
        // Configurar o JsonSchemaFactory e criar o JsonSchema
        JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        JsonSchema schema = schemaFactory.getSchema(jsonSchema.toString());
        // Converter o JSON de resposta para JsonNode
        JsonNode jsonResponseNode = mapper.readTree(jsonResponse.toString());
        // Validar o JSON de resposta contra o esquema
        Set<ValidationMessage> schemaValidationErrors = schema.validate(jsonResponseNode);

        return schemaValidationErrors;

    }

    public void retrieveIdDelivery(){
        emergencyIDDelivery = String.valueOf(gson.fromJson(response.jsonPath().prettify(), EmergenciaModel.class).getEmergencyId());
    }

    public void restoreLoginToken(String token) {
        this.token = token;
    }

    public void deleteDelivery(String endPoint) {
        String url = String.format("%s%s/%s", baseUrl, endPoint, emergencyIDDelivery);
        response = given()
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + this.token) // Adiciona o Bearer Token ao cabeçalho
                .when()
                .delete(url)
                .then()
                .extract()
                .response();
    }

}
