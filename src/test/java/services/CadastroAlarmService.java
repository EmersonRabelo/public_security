package services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.AlarmModel;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

import static io.restassured.RestAssured.given;

public class CadastroAlarmService {

    AlarmModel model = new AlarmModel();
    public final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    String baseUrl = "http://localhost:8080/";
    public Response response;
    public String token;
    String schemasPath = "src/test/resources/schemas/";
    JSONObject jsonSchema;
    private final ObjectMapper mapper = new ObjectMapper();
    public void setFieldsDelivery(String field, String value) {
        switch (field) {
            case "alarmId" -> model.setAlarmId(Integer.parseInt(value));
            case "type" -> model.setType(value);
            case "status" -> model.setStatus(value);
            case "alarmDate" -> model.setAlarmDate(value);
            default -> throw new IllegalStateException("Unexpected field: " + field);
        }
    }
    public void createDelivery(String endpoint) {
        String url = baseUrl + endpoint;
        String body = gson.toJson(model);

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
            case "Cadastro bem-sucedido de alarme" -> jsonSchema = loadJsonFromFile(schemasPath+"cadastro-bem-sucedido-de-alarm.json");
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

    public void restoreLoginToken(String token) {
        this.token = token;
    }


}
