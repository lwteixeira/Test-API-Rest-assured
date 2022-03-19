package br.com.automacaoAPI.teste;

import org.apache.http.HttpStatus;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;


import java.util.ArrayList;
import java.util.Random;

import static io.restassured.RestAssured.*;

public class GetRetornaSimulacaoTeste extends BaseTeste {

    ArrayList arrayCPF;
    Random rand = new Random();
    static String cpfNotExisting = "00000000000";

    @Test
    public void testeSimulacaoRetornaTodosCPFExistentes(){

        given()
                .when()
                    .get(EFETUA_OPERACOES_SIMULACAO)
                .then()
                    .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void testeSimulacaRetornaClientePeloCPF(){
        arrayCPF = new ArrayList();

        arrayCPF = given()
                        .when()
                            .get(EFETUA_OPERACOES_SIMULACAO)
                        .then()
                            .extract()
                            .path("cpf");

        int numRand = rand.nextInt(arrayCPF.size());

        given()
                .pathParam("paramCpf", arrayCPF.get(numRand).toString())
            .when()
                .get(EFETUA_OPERACOES_SIMULACAO +  "/{paramCpf}")
            .then()
                .statusCode(HttpStatus.SC_OK)
                .body("cpf", is(arrayCPF.get(numRand).toString()));
    }

    @Test
    public void testeSimulacaoComCPFNaoExistente(){

        given()
                .pathParam("paramCpf", cpfNotExisting)
            .when()
                .get(EFETUA_OPERACOES_SIMULACAO + "/{paramCpf}")
            .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .body("mensagem", is("CPF " + cpfNotExisting + " não encontrado"));
    }


}
