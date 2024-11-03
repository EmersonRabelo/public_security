package steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;
import services.CadastroEmergenciaService;
import services.CadastroUsuarioService;
import services.LoginUsuarioService;

import java.util.List;
import java.util.Map;

public class CadastroEmergenciaSteps {

    CadastroEmergenciaService service = new CadastroEmergenciaService();
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
        service.restoreLoginToken(token);
    }

    @E("eu tenha os seguintes dados da emergência")
    public void euTenhaOsSeguintesDadosDaEmergência(List<Map<String, String>> rows) {
        for(Map<String, String> columns: rows) {
            service.setFieldsDelivery(columns.get("key"), columns.get("value"));
        }
    }

    @Quando("enviar a requisição para o endpoint {string} com o token")
    public void enviarARequisiçãoParaOEndpointComOToken(String endpoint) {
        service.createDelivery(endpoint);
    }

    @Então("status code da resposta deve ser o {int}")
    public void statusCodeDaRespostaDeveSerO(int statuscode) {
        Assert.assertEquals(statuscode, service.response.statusCode());
    }

}
