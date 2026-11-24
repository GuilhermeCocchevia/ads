# Diário de bordo — construção do conhecimento com IA

Registro de como usamos IA generativa (Claude e Claude Code no VS Code) para desenvolver o projeto.
Cada entrada traz o que perguntamos, o que aprendemos e como aplicamos no código.

---

## 1. Planejamento e configuração

### 1.1 — Início do projeto e configuração do VS Code
**O que perguntamos:**
Iniciamos pedindo à IA que se contextualizasse sobre o enunciado do Tema 08 (Controle de Eventos
e Venda de Ingressos) e seus requisitos. Em seguida, perguntamos como preconfigurar o VS Code
para programar em Java puro, sem Maven ou Gradle.

**O que aprendemos:**
- O VS Code precisa do *Extension Pack for Java* para reconhecer, compilar e executar projetos Java.
- Em um projeto Java sem ferramenta de build, é preciso informar ao VS Code onde ficam os
  códigos-fonte (`src/`) e para onde vão os arquivos compilados (`bin/`), o que é feito no
  arquivo `.vscode/settings.json`.
- A pasta `bin/` é gerada a cada compilação e não deve ir para o repositório.

**Como aplicamos:**
- Criamos o `.vscode/settings.json` apontando `src` como pasta de fontes e `bin` como saída.
- Adotamos a organização em pacotes proposta pela IA a partir dos requisitos do enunciado:
  `modelo`, `interfaces`, `excecoes`, `servico` e `visao`.
- Adicionamos `bin/` e `*.class` ao `.gitignore`.
- Compilamos pelo terminal com `javac -d bin -sourcepath src src/ingressos/Principal.java`.

## 2. Interface gráfica — Swing e eventos

### 2.1 — Escolha entre inteira e meia-entrada na tela
**O que perguntamos:**
Como o usuário deveria escolher entre ingresso inteira e meia-entrada na tela de venda.

**O que a IA sugeriu:**
Duas opções: um `JCheckBox` "Meia-entrada" ou dois `JRadioButton` ("Inteira" e "Meia")
agrupados em um `ButtonGroup`.

**O que escolhemos e por quê:**
Escolhemos o `JCheckBox`, para evitar uma classe a mais (`ButtonGroup`) na tela. Além disso,
o método `isSelected()` do checkbox já devolve `true` ou `false`, que é exatamente o parâmetro
`boolean meia` do método `vender` da `Bilheteria`.