package br.com.automacaoAPI.teste;

import org.apache.http.HttpStatus;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class DeleteSimulacaoTeste extends BaseTeste {

    @Test
    public void testSimulacaoDeletaClientePeloCPF(){

        ArrayList arrayId = new ArrayList();

        arrayId = given()
                        .when()
                            .get(EFETUA_OPERACOES_SIMULACAO)
                        .then()
                            .extract()
                            .path("id");

        String id = arrayId.get(0).toString();

        given()
                .pathParam("paramId", id)
            .when()
                .delete(EFETUA_OPERACOES_SIMULACAO + "/{paramId}")
            .then()
                .statusCode(HttpStatus.SC_OK)
                .body( is("OK"));
    }

}
