package steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;
import services.LoginUsuarioService;

import java.util.List;
import java.util.Map;

public class LoginUsuarioSteps {

    LoginUsuarioService service = new LoginUsuarioService();

    @Quando("enviar a requisição para o endpoint {string} de login de usuário")
    public void enviarARequisiçãoParaOEndpointDeLoginDeUsuário(String enpoint) {
        service.createDelivery(enpoint);
    }

    @Dado("que eu tenha os seguintes dados de login")
    public void queEuTenhaOsSeguintesDadosDeLogin(List<Map<String, String>> rows) {
        for(Map<String, String> columns: rows) {
            service.setFieldsDelivey(columns.get("key"), columns.get("value"));
        }
    }

    @Então("o status code da resposta deve ser o {int}")
    public void oStatusCodeDaRespostaDeveSerO(int statusCode) {
        Assert.assertEquals(statusCode, service.response.statusCode());
    }
}
