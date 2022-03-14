package br.com.automacaoAPI.teste;

import br.com.automacaoAPI.model.Cliente;
import org.apache.http.HttpStatus;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class PostInsereNovaSimulacaoTeste extends BaseTeste  {

    @Test
    public void testSimulacaoInsereClienteJaExistente(){

        ArrayList arrayCPF = new ArrayList();

        arrayCPF = given()
                    .when()
                        .get(EFETUA_OPERACOES_SIMULACAO)
                    .then()
                        .extract()
                        .path("cpf");

        Cliente simula = new Cliente(
                "Deltrano",
                arrayCPF.get(0).toString(),
                "deltrano@gmail.com",
                new BigDecimal(20000) ,
                5,
                false);

        given()
                .body(simula)
            .when()
                .post(EFETUA_OPERACOES_SIMULACAO)
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("mensagem", is("CPF duplicado"));
    }



    @Test
    public void testSimulacaoInsereNovoCliente(){

        Cliente simula = new Cliente(
                "Jose Pai",
                "98745632123",
                "JosePai@gmail.com",
                new BigDecimal(10000) ,
                5,
                false);

        given()
                .body(simula)
            .when()
                .post(EFETUA_OPERACOES_SIMULACAO)
            .then()
                .statusCode(HttpStatus.SC_CREATED)
                .body("cpf", is(simula.getCpf()));
    }

    @Test
    public void testSimulacaoInsereClienteFaltandoCPF(){

        Cliente simula = new Cliente(
                "Jose Paii",
                "JosePaii@gmail.com",
                new BigDecimal(10000) ,
                5,
                false);
        given()
                .body(simula)
            .when()
                .post(EFETUA_OPERACOES_SIMULACAO)
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("erros.cpf", is("CPF não pode ser vazio"));
    }
}
