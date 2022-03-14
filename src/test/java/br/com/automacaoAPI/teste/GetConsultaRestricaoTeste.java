package br.com.automacaoAPI.teste;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.*;

import static io.restassured.RestAssured.*;

public class GetConsultaRestricaoTeste extends BaseTeste {


    ArrayList arrayCPF;

    String cpfRetricao = "24094592008";

    @Test
    public void testCPFPossuiRestricao(){
        given()
            .when()
                .get(CONSULTA_RESTICAO_CPF + "/"+ cpfRetricao)
            .then()
                .statusCode(HttpStatus.SC_OK)
                .body("mensagem", is("O CPF " + cpfRetricao + " tem problema"));
    }

    @Test
    public void testCPFNaoPossuiRestricao(){

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
                .get(CONSULTA_RESTICAO_CPF + "/{paramCpf}")
            .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

}
