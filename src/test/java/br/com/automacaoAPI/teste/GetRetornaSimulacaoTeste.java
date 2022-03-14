package br.com.automacaoAPI.teste;

import org.apache.http.HttpStatus;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;


import java.util.ArrayList;

import static io.restassured.RestAssured.*;

public class GetRetornaSimulacaoTeste extends BaseTeste {

    ArrayList arrayCPF;

    static String cpfNotExisting = "00000000000";

    @Test
    public void testSimulacaoRetornaTodosCPFExistentes(){

        given()
                .when()
                    .get(EFETUA_OPERACOES_SIMULACAO)
                .then()
                    .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void testSimulacaRetornaClientePeloCPF(){
        arrayCPF = new ArrayList();

        arrayCPF = given()
                        .when()
                            .get(EFETUA_OPERACOES_SIMULACAO)
                        .then()
                            .extract()
                            .path("cpf");

        given()
                .pathParam("paramCpf", arrayCPF.get(0).toString())
            .when()
                .get(EFETUA_OPERACOES_SIMULACAO +  "/{paramCpf}")
            .then()
                .statusCode(HttpStatus.SC_OK)
                .body("cpf", is(arrayCPF.get(0).toString()));
    }

    @Test
    public void testSimulacaoComCPFNaoExistente(){

        given()
                .pathParam("paramCpf", cpfNotExisting)
            .when()
                .get(EFETUA_OPERACOES_SIMULACAO + "/{paramCpf}")
            .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .body("mensagem", is("CPF " + cpfNotExisting + " não encontrado"));
    }


}
