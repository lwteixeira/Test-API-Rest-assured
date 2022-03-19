package br.com.automacaoAPI.teste;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.hamcrest.CoreMatchers.*;

import static io.restassured.RestAssured.*;

public class GetConsultaRestricaoTeste extends BaseTeste {


    ArrayList arrayCPF;
    String cpfRetricao;
    Random rand = new Random();

    @Test
    public void testeCPFPossuiRestricao(){

        cpfRetricao = retornaCPFResticao();

        given()
            .when()
                .get(CONSULTA_RESTICAO_CPF + "/"+ cpfRetricao)
            .then()
                .statusCode(HttpStatus.SC_OK)
                .body("mensagem", is("O CPF " + cpfRetricao + " tem problema"));
    }

    @Test
    public void testeCPFNaoPossuiRestricao(){

        Random rand = new Random();
        arrayCPF = new ArrayList();

        arrayCPF = given()
                        .when()
                            .get(EFETUA_OPERACOES_SIMULACAO)
                        .then()
                            .extract()
                            .path("cpf");

        given()
                .pathParam("paramCpf", arrayCPF.get(rand.nextInt(arrayCPF.size())).toString())
            .when()
                .get(CONSULTA_RESTICAO_CPF + "/{paramCpf}")
            .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    public String retornaCPFResticao(){

        ArrayList<String> listCpfRetricao = new ArrayList<>();

        listCpfRetricao.add("97093236014");
        listCpfRetricao.add("60094146012");
        listCpfRetricao.add("84809766080");
        listCpfRetricao.add("62648716050");
        listCpfRetricao.add("26276298085");
        listCpfRetricao.add("01317496094");
        listCpfRetricao.add("55856777050");
        listCpfRetricao.add("19626829001");
        listCpfRetricao.add("24094592008");
        listCpfRetricao.add("58063164083");

        String cpfComRestricao = listCpfRetricao.get(rand.nextInt(listCpfRetricao.size())).toString();

        return cpfComRestricao;
    }

}
