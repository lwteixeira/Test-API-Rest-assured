package br.com.automacaoAPI.teste;

import org.apache.http.HttpStatus;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class DeleteSimulacaoTeste extends BaseTeste {

    @Test
    public void testeSimulacaoDeletaClientePeloCPF(){

        ArrayList arrayId = new ArrayList();
        Random rand = new Random();

        arrayId = given()
                        .when()
                            .get(EFETUA_OPERACOES_SIMULACAO)
                        .then()
                            .extract()
                            .path("id");



        String id = arrayId.get(rand.nextInt(arrayId.size())).toString();

        given()
                .pathParam("paramId", id)
            .when()
                .delete(EFETUA_OPERACOES_SIMULACAO + "/{paramId}")
            .then()
                .statusCode(HttpStatus.SC_OK)
                .body( is("OK"));
    }

}
