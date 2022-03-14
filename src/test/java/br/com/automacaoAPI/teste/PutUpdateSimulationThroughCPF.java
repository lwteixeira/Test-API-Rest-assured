package br.com.automacaoAPI.teste;

import br.com.automacaoAPI.model.Cliente;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

public class PutUpdateSimulationThroughCPF extends BaseTeste {


    @Test
    public void testSimulacaoAtualizaClientePeloCPF(){



        Cliente simula = new Cliente(
                "Jose Pai",
                retornaCPFClienteExistente(),
                "JosePai@gmail.com",
                new BigDecimal(10000) ,
                4,
                true);

        given()
                .pathParam("paramCpf", simula.getCpf())
                .body(simula)
            .when()
                .put(EFETUA_OPERACOES_SIMULACAO + "/{paramCpf}")
            .then()
                .statusCode(HttpStatus.SC_OK)
                .body("nome", is(simula.getNome()))
                .body("cpf", is(simula.getCpf()))
                .body("email", is(simula.getEmail()))
                .body("parcelas", is(simula.getParcelas()))
                .body("seguro", is(simula.isSeguro()));
    }

    @Test
    public void testSimulacaoAtualizaClienteComCPFNaoExistente(){
        Cliente simula = new Cliente(
                "Jose Pai",
                "99999999900",
                "JosePai@gmail.com",
                new BigDecimal(10000) ,
                2,
                true);

        given()
                .pathParam("paramCpf", simula.getCpf())
                .body(simula)
            .when()
                .put(EFETUA_OPERACOES_SIMULACAO + "/{paramCpf}")
            .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .body("mensagem", equalTo("CPF "+ simula.getCpf() +" não encontrado"));
    }

    @Test
    public void testSimulacaoAtualizaParcelaClienteParaUm() {
        Cliente simula = new Cliente(
                "Jose Pai",
                retornaCPFClienteExistente(),
                "JosePai@gmail.com",
                new BigDecimal(10000),
                1,
                true);

        given()
                .pathParam("paramCpf", simula.getCpf())
                .body(simula)
            .when()
                .put(EFETUA_OPERACOES_SIMULACAO + "/{paramCpf}")
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("erros.parcelas", is("Parcelas deve ser igual ou maior que 2"));
    }

    public String retornaCPFClienteExistente(){
        ArrayList arrayCPF = new ArrayList();

        arrayCPF = given()
                        .when()
                            .get(EFETUA_OPERACOES_SIMULACAO)
                        .then()
                            .extract()
                            .path("cpf");

        return arrayCPF.get(0).toString();
    }

}
