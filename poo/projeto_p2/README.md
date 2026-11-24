# Controle de Eventos e Venda de Ingressos

Trabalho Prático de Programação Orientada a Objetos — Prof. Maromo — Fatec Mogi Mirim.
Tema 08: Sistema orientado a objetos em memória (Swing + Java Collections).

## Dupla
| Nome | RA |
|------|----|
| Maurício Jampani de Souza | (RA) |
| Guilherme Rocha Cocchevia | (RA) |

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

### Portaria e relatório (Guilherme)
- **`Validavel`** (interface): método `default` `gerarComprovanteAcesso()`, que devolve
  "ACESSO LIBERADO - " seguido dos dados do ingresso. Todo ingresso herda esse método pronto.
- **`Ingresso`**: `validarAcessoCatraca()` barra o ingresso já utilizado ou cancelado. Se ele
  ainda estiver vendido, dá baixa (status `UTILIZADO`).
- **`IngressoMeia`**: sobrescreve `validarAcessoCatraca()` com a regra extra da meia (sem
  documento comprovante, não entra) e depois chama o `super` para as regras de todo ingresso.
- **`Bilheteria`**:
  - `validarNaCatraca(codigo)`: pesquisa o ingresso e chama `validarAcessoCatraca()`. Não precisa
    saber se é inteira ou meia, porque cada uma valida do seu jeito (polimorfismo).
  - `cancelar(codigo)`: só cancela ingresso `VENDIDO`. Se já foi utilizado ou já está cancelado,
    lança `IngressoInvalidoException`.
  - `relatorioPublico()`: lista os ingressos e mostra quantos estão vendidos, utilizados e
    cancelados, e a arrecadação (ingresso cancelado não entra na soma).
- **`TelaIngressos`**: botões Processar Operação (mostra o comprovante de acesso), Cancelar (pede
  confirmação antes) e Relatório. Os erros aparecem numa caixa de mensagem (`JOptionPane`).

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