# Test-API-Rest-assured

Rodar primeiramente a API Rest disponibilizado com as suas intruções.

## Para rodar os testes deve selecionar as classes para executa-los.

Interessante executar primeiramente as classes de testes conforme a seguir:

- *POST*: 
  Classe: PostInsereNovaSimulacaoTeste
  Testes:
          - testeSimulacaoInsereClienteJaExistente()
          - testeSimulacaoInsereNovoCliente()
          - testeSimulacaoInsereClienteFaltandoCPF()
 
 - *GETs*:
   Classe: GetRetornaSimulacaoTeste
   Testes:
          - testeSimulacaoRetornaTodosCPFExistentes()
          - testeSimulacaRetornaClientePeloCPF()
          - testeSimulacaoComCPFNaoExistente()
  Classe: GetConsultaRestricaoTeste
  Testes:
          - testeCPFPossuiRestricao()
          - testeCPFNaoPossuiRestricao()

- *PUT*:
  Classe: PutAtualizaSimulacaoPeloCPF
          - testeSimulacaoAtualizaClientePeloCPF()
          - testeSimulacaoAtualizaClienteComCPFNaoExistente()
          - testeSimulacaoAtualizaParcelaClienteParaUm()
- *DELETE*
  Classe: DeleteSimulacaoTeste
          - testeSimulacaoDeletaClientePeloCPF()

