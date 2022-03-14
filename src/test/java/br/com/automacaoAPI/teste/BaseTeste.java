package br.com.automacaoAPI.teste;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.*;
import org.junit.BeforeClass;
import static io.restassured.RestAssured.*;

public class BaseTeste {

    protected static final String EFETUA_OPERACOES_SIMULACAO = "/v1/simulacoes";
    protected static final String CONSULTA_RESTICAO_CPF = "/v1/restricoes";

    @BeforeClass
    public static void setup(){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        baseURI = "http://localhost";
        port = 8080;
        basePath = "/api";

        requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .build();

    }
}
