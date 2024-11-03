package steps;

import com.networknt.schema.ValidationMessage;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.json.JSONException;
import org.junit.Assert;
import services.EmergenciaService;
import services.LoginUsuarioService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EmergenciaSteps {

    EmergenciaService emergenciaService = new EmergenciaService();
    LoginUsuarioService loginService = new LoginUsuarioService();

    String token;

    @Dado("que eu tenha os seguintes dados para efetuar o login")
    public void queEuTenhaOsSeguintesDadosParaEfetuarOLogin(List<Map<String, String>> rows) {
        for(Map<String, String> columns: rows) {
            loginService.setFieldsDelivey(columns.get("key"), columns.get("value"));
        }
    }

    @Quando("enviar a requisição para endpoint {string} de login de usuário")
    public void enviarARequisiçãoParaEndpointDeLoginDeUsuário(String enpoint) {
        loginService.createDelivery(enpoint);
        token = loginService.retrieveTokenDelivery();
    }

    @Então("status code da resposta deve ser {int}")
    public void statusCodeDaRespostaDeveSer(int statuscode) {
        Assert.assertEquals(statuscode, loginService.response.statusCode());
    }
    @Dado("que eu tenha realizado o login e recebido um token")
    public void queEuTenhaRealizadoOLoginERecebidoUmToken() {
        emergenciaService.restoreLoginToken(token);
    }

    @E("eu tenha os seguintes dados da emergência")
    public void euTenhaOsSeguintesDadosDaEmergência(List<Map<String, String>> rows) {
        for(Map<String, String> columns: rows) {
            emergenciaService.setFieldsDelivery(columns.get("key"), columns.get("value"));
        }
    }

    @Quando("enviar a requisição para o endpoint {string} com o token")
    public void enviarARequisiçãoParaOEndpointComOToken(String endpoint) {
        emergenciaService.createDelivery(endpoint);
    }

    @Então("status code da resposta deve ser o {int}")
    public void statusCodeDaRespostaDeveSerO(int statuscode) {
        Assert.assertEquals(statuscode, emergenciaService.response.statusCode());
    }

    @E("que o arquivo de contrato esperado para emergência {string}")
    public void queOArquivoDeContratoEsperadoParaEmergência(String contract)  throws JSONException, IOException {
        emergenciaService.setContract(contract);
    }

    @Então("resposta da requisição deve estar em conformidade com o contrato")
    public void respostaDaRequisiçãoDeveEstarEmConformidadeComOContrato() throws JSONException, IOException {
        Set<ValidationMessage> validateResponse = emergenciaService.validateResponseAgainstSchema();
        Assert.assertTrue("O contrato está inválido. Erros encontrados: " + validateResponse, validateResponse.isEmpty());
    }

    @Dado("que eu recupere o ID do emergência criado no contexto")
    public void queEuRecupereOIDDoEmergênciaCriadoNoContexto() {
        emergenciaService.retrieveIdDelivery();
    }

    @Quando("eu enviar a requisição com o Id da emergência para o endpoint {string} de deleção")
    public void euEnviarARequisiçãoComOIdDaEmergênciaParaOEndpointDeDeleção(String enpoint) {
        emergenciaService.deleteDelivery(enpoint);
    }

}
