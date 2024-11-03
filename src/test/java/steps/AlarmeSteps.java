package steps;

import com.networknt.schema.ValidationMessage;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.json.JSONException;
import org.junit.Assert;
import services.AlarmService;
import services.LoginUsuarioService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AlarmeSteps {

    LoginUsuarioService loginService = new LoginUsuarioService();
    AlarmService alarmService = new AlarmService();

    String token;

    @Dado("que eu tenha os seguintes dados para o login")
    public void queEuTenhaOsSeguintesDadosParaOLogin(List<Map<String, String>> rows) {
        for(Map<String, String> columns: rows) {
            loginService.setFieldsDelivey(columns.get("key"), columns.get("value"));
        }
    }

    @Quando("enviar a requisição para endpoint {string} de login")
    public void enviarARequisiçãoParaEndpointDeLogin(String enpoint) {
        loginService.createDelivery(enpoint);
        token = loginService.retrieveTokenDelivery();
    }

    @Então("status code da resposta {int}")
    public void statusCodeDaResposta(int statuscode) {
        Assert.assertEquals(statuscode, loginService.response.statusCode());
    }

    @Dado("que tenha realizado o login e recebido um token")
    public void queTenhaRealizadoOLoginERecebidoUmToken() {
        alarmService.restoreLoginToken(token);
    }

    @E("eu tenha os seguintes dados da alarme")
    public void euTenhaOsSeguintesDadosDaAlarme(List<Map<String, String>> rows) {
        for(Map<String, String> columns: rows) {
            alarmService.setFieldsDelivery(columns.get("key"), columns.get("value"));
        }
    }

    @Quando("enviar requisição para o endpoint {string} com o token")
    public void enviarRequisiçãoParaOEndpointComOToken(String endpoint) {
        alarmService.createDelivery(endpoint);
    }

    @Então("o status code da resposta deve ser, {int}")
    public void oStatusCodeDaRespostaDeveSer(int statuscode) {
        Assert.assertEquals(statuscode, alarmService.response.statusCode());
    }

    @E("que o arquivo de contrato esperado {string}")
    public void queOArquivoDeContratoEsperado(String contract) throws JSONException, IOException {
        alarmService.setContract(contract);
    }

    @Então("a resposta da requisição deve estar em conformidade com o contrato")
    public void aRespostaDaRequisiçãoDeveEstarEmConformidadeComOContrato() throws JSONException, IOException {
        Set<ValidationMessage> validateResponse = alarmService.validateResponseAgainstSchema();
        Assert.assertTrue("O contrato está inválido. Erros encontrados: " + validateResponse, validateResponse.isEmpty());
    }

    @Dado("que não tenha realizado o login e recebido um token")
    public void queNãoTenhaRealizadoOLoginERecebidoUmToken() {
        token = "";
    }

    @Dado("que eu recupere o ID do alarme criado no contexto")
    public void queEuRecupereOIDDoAlarmeCriadoNoContexto() {
        alarmService.retrieveIdDelivery();
    }

    @Quando("eu enviar a requisição com o ID para o endpoint {string} de deleção")
    public void euEnviarARequisiçãoComOIDParaOEndpointDeDeleção(String enpoint) {
        alarmService.deleteDelivery(enpoint);
    }
}
