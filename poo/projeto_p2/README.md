# Controle de Eventos e Venda de Ingressos

Trabalho Prático de Programação Orientada a Objetos — Prof. Maromo — Fatec Mogi Mirim.
Tema 08: Sistema orientado a objetos em memória (Swing + Java Collections).

## Dupla
| Nome | RA |
|------|----|
| Maurício Jampani de Souza | (RA) |
| Guilherme | (RA) |

## O sistema
Aplicação desktop em Java para controlar a venda de ingressos de um evento: venda de inteira e
meia-entrada, conferência na portaria com baixa do bilhete, cancelamento e relatório de público
com a arrecadação. Escolhemos o tema por ser um processo do dia a dia com regras claras (lotação
máxima, meia com comprovante, ingresso que não pode ser usado duas vezes), que se encaixa bem nos
conceitos da disciplina. Todos os dados ficam em memória enquanto o programa está aberto.

## Arquitetura das classes
O código está organizado em pacotes por responsabilidade:

```
src/ingressos/
├── Principal.java   ponto de entrada
├── modelo/          Evento, Ingresso, IngressoInteira, IngressoMeia, StatusIngresso
├── interfaces/      Validavel
├── excecoes/        IngressoInvalidoException
├── servico/         Bilheteria (ingressos em memória e regras de negócio)
└── visao/           TelaIngressos (janela Swing)
```

A descrição de cada classe é completada conforme a implementação avança.

## Divisão do trabalho
A dupla dividiu o sistema por funcionalidade: cada integrante é responsável pela sua parte em
todas as camadas (modelo, regras de negócio e tela).

| Camada | Maurício — venda e consulta | Guilherme — portaria e relatório |
|--------|-----------------------------|----------------------------------|
| Modelo | `Ingresso`: `equals`, `hashCode`, `compareTo`, `toString` | `Ingresso`: `validarAcessoCatraca`; `IngressoMeia`: validação do documento; `Validavel`: método `default` |
| Regras (`Bilheteria`) | `vender`, `pesquisar`, `ordenar` | `validarNaCatraca`, `cancelar`, `relatorioPublico` |
| Tela (`TelaIngressos`) | Layout da janela; botões Cadastrar, Pesquisar, Ordenar e Sair | Botões Processar Operação, Cancelar e Relatório |

O desenvolvimento seguiu três fases (modelo → regras de negócio → tela), testando o sistema com as duas partes integradas ao fim de cada uma. O Guilherme contribuiu por meio de um fork e Pull Requests, revisados antes do merge no repositório principal.

## Como executar
Requisitos: JDK 17 ou superior.

```bash
cd poo/projeto_p2
javac -d bin -sourcepath src src/ingressos/Principal.java
java -cp bin ingressos.Principal
```

No VS Code, com o Extension Pack for Java, também dá para abrir `Principal.java` e clicar em **Run**.

## Histórico de prompts de IA
O registro completo está em [docs/diario-de-bordo.md](docs/diario-de-bordo.md).