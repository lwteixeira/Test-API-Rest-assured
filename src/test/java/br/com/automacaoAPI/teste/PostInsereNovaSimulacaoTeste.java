package br.com.automacaoAPI.teste;

import br.com.automacaoAPI.model.Cliente;
import org.apache.http.HttpStatus;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class PostInsereNovaSimulacaoTeste extends BaseTeste  {

    Random rand = new Random();

    @Test
    public void testeSimulacaoInsereClienteJaExistente(){

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
    public void testeSimulacaoInsereNovoCliente(){

        String nome = obtemNomeAleatorio();
        String cpf = geraCPFAleatorio();
        Cliente simula = new Cliente(
                nome,
                cpf,
                 nome + "@gmail.com",
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
    public void testeSimulacaoInsereClienteFaltandoCPF(){

        String nome = obtemNomeAleatorio();

        Cliente simula = new Cliente(
                nome,
                nome + "@gmail.com",
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

    public String obtemNomeAleatorio(){

        List<String> nomes = new ArrayList<>();
        nomes.add("Joao");
        nomes.add("Pedro");
        nomes.add("Silvio");
        nomes.add("Felipe");
        nomes.add("Carlos");

        String nomeAleatorio = nomes.get(rand.nextInt(nomes.size())).toString();

        return nomeAleatorio;
    }

    public String geraCPFAleatorio(){
        String aleat = "";
        for (int i = 0; i < 11; i++) {
            int num = rand.nextInt(10);
            aleat = aleat + num;
        }

        String cpfaleatorio = aleat;
        return cpfaleatorio;
    }
}
