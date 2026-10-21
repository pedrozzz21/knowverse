# Knowverse — Currículo Python Expandido (Estilo FreeCodeCamp), Terminal com Pop-up Didático, Desafios Kahoot e Painel Dev

Plano de implementação revisado para elevar a profundidade do aprendizado de Python, fundir o conteúdo básico e intermediário no novo **Básico**, promover o conteúdo anterior a **Intermediário** com algoritmos e programação funcional, expandir o **Avançado** com Programação Orientada a Objetos (POO), métodos dunder, geradores e expressões regulares inspirados no **FreeCodeCamp**, aprimorar o terminal de escrita/execução com pop-up modal de diagnóstico de erros (tipo, linha e dicas pedagógicas), implementar login obrigatório com Google e criar um **Painel de Desenvolvedor** restrito para monitoramento do progresso de cada usuário do app.

---

## User Review & Critical Decisions

> [!IMPORTANT]
> As alterações solicitadas foram consolidadas nas seguintes decisões arquiteturais:

- **Decisão 1 (Renovação e Aumento de Dificuldade da Trilha Python)**:
  - **Tier Básico (Fundamentos e Estruturas de Dados)**: Variáveis, tipos de dados (`int`, `float`, `str`, `bool`), operadores aritméticos e relacionais, controle de fluxo (`if`/`elif`/`else`), laços (`for`/`while`), listas, tuplas, dicionários (`dict`), conjuntos (`set`) e funções (`def`/`return`).
  - **Tier Intermediário (Python Funcional, Manipulação Avançada e Algoritmos)**: F-strings e métodos avançados de string (Cifra de Substituição), funções lambda, `map`/`filter`/`sorted`, compreensões de listas e dicionários, tratamento robusto de exceções (`try`/`except`/`else`/`finally`/`raise`) e algoritmos clássicos (Luhn, Busca Binária, Insertion Sort).
  - **Tier Avançado (POO, Engenharia de Software e Estruturas de Dados)**: Classes e Instâncias (`__init__`, `self`), encapsulamento, herança e polimorfismo (`super()`), métodos mágicos (`__str__`, `__len__`, `__eq__`), geradores com `yield`, expressões regulares (`re`) para validação de padrões e estruturas clássicas (Pilhas, Filas e Árvores).
- **Decisão 2 (Terminal Interativo e Modal de Diagnóstico de Erros)**:
  - Editor de código e console com suporte total à digitação suave, apagar (backspace), seleção, recuo de 4 espaços e limpeza de buffer.
  - Parser inteligente de saída do interpretador Skulpt: quando ocorrer exceção, extrai automaticamente:
    1. **Tipo do erro** (ex: `SyntaxError`, `IndentationError`, `NameError`, `TypeError`, `ZeroDivisionError`, `IndexError`, `KeyError`).
    2. **Linha do erro** no código do usuário com destaque visual.
    3. **Pop-up modal didático** ("Diagnóstico Knowverse") com explicação amigável em português do que causou a falha e dicas direcionadas para resolução.
- **Decisão 3 (Painel de Desenvolvedor Restrito)**:
  - Acesso liberado para a conta de desenvolvedor (`contatopedro21g@gmail.com` ou credencial administrativa com flag `role = "developer"`).
  - Visualização exclusiva de métricas globais e listagem detalhada de todos os usuários registrados no Firestore: nome, e-mail, XP acumulado, dias de ofensiva (streak), lições completadas, pontuação nos mini-jogos Kahoot e data do último acesso.
- **Decisão 4 (Autenticação Obrigatória e Desafios Kahoot)**:
  - Acesso mantido como 100% restrito a usuários logados com Conta Google (`CredentialManager` + Firebase Auth).
  - Mini-jogo Kahoot de 30 segundos ao final de cada módulo com 4 alternativas geométricas coloridas, áudio tenso que acelera nos últimos segundos e bônus por agilidade.

---

## 1. Overview & Core Concept

- **O que faz**: Oferece uma formação completa em Python, desde o primeiro `print` até orientação a objetos avançada e algoritmos clássicos do FreeCodeCamp. Inclui ambiente interativo de desenvolvimento no dispositivo móvel com assistência inteligente a erros e painel administrativo para acompanhar a jornada de cada aluno.
- **Público-alvo**: Desenvolvedores e estudantes que desejam aprender lógica e Python de verdade, enfrentando desafios reais com feedback imediato.
- **Valor Principal**: Dificuldade progressiva real (sem superficialidade), terminal funcional com depuração pedagógica, gamificação estimulante (Kahoot de 30s) e rastreabilidade total do aprendizado na nuvem.

---

## 2. User Experience & Visual Design

### Fluxos Principais

1. **Gate de Autenticação Obrigatório**:
   - Tela de abertura futurista com botão oficial "Entrar com Google". Nenhuma tela interna é acessível sem login.
2. **Nova Trilha de Conhecimento Python (3 Fases com Dificuldade Real)**:
   - **Básico**: 4 módulos (Sintaxe & Operadores, Condicionais, Laços & Estruturas de Dados, Funções Modulares) + Desafio Kahoot.
   - **Intermediário**: 4 módulos (F-Strings & Cifras, Lambdas & Compreensões, Exceções Robustas, Algoritmos & Busca) + Desafio Kahoot.
   - **Avançado**: 4 módulos (Classes & Objetos, Herança & Polimorfismo, Dunder Methods & Geradores, Regex & Estruturas de Dados) + Desafio Kahoot Final.
3. **Ambiente de Código e Pop-up de Erro**:
   - Editor responsivo com botões de atalho (`:`, `()`, `""`, `[]`, `tab`, `def`, `class`).
   - Botão "Executar Código" e "Enviar Desafio".
   - Ao detectar erro de sintaxe ou execução, exibição imediata do **Modal de Diagnóstico**:
     - Badge com tipo de erro (ex: `IndentationError`).
     - "Encontrado na linha X".
     - Explicação conceitual clara do motivo.
     - Dica prática de como corrigir antes de tentar de novo.
4. **Painel Dev / Admin (Acesso Restrito)**:
   - Se o usuário logado for `contatopedro21g@gmail.com` (ou possuir role dev), uma aba/card especial **"Modo Desenvolvedor"** fica acessível.
   - Painel com contadores (Total de Alunos, Média de XP, Taxa de Conclusão) e lista pesquisável de todos os alunos cadastrados no Firestore.
   - Card individual com drill-down do progresso de cada estudante.

### Identidade Visual

- Tema Dark Moderno: `#090D16` (Deep Navy) e `#101624` (Obsidian Surface).
- Cores de Feedback de Erro: Coral Vermelho (`#EF4444`) para avisos e Amarelo Alerta (`#F59E0B`) para dicas.
- Cores dos Cartões Kahoot: Vermelho (`#EF4444`), Azul (`#3B82F6`), Amarelo (`#F59E0B`), Verde (`#10B981`).
- Painel Dev: Acento em Ouro e Ciano Tecnológico (`#38BDF8`) com indicadores de telemetria.

---

## 3. Technical Architecture & Data Strategy

### Diagrama do Sistema

```
┌────────────────────────────────────────────────────────────────────────┐
│                               Knowverse                                │
└───────────────────────────────────┬────────────────────────────────────┘
                                    │
                     ┌──────────────┴──────────────┐
                     ▼                             ▼
        ┌─────────────────────────┐   ┌─────────────────────────────┐
        │        AuthGate         │   │   Firebase Authentication   │
        │ (CredentialManager API) │◄──┤   (Google Sign-In Provider) │
        └────────────┬────────────┘   └─────────────────────────────┘
                     │ Authenticated
                     ▼
        ┌────────────────────────────────────────────────────────────┐
        │                       MainAppScreen                        │
        │  ┌──────────┬──────────────┬──────────┬────────┬────────┐  │
        │  │  Básico  │Intermediário │ Avançado │ Perfil │ Dev UI*│  │
        │  └────┬─────┴──────┬───────┴────┬─────┴───┬────┴───┬────┘  │
        └───────┼────────────┼────────────┼─────────┼────────┼───────┘
                ▼            ▼            ▼         │        │
       ┌──────────────────────────────────────┐     │        │
       │           PythonCurriculum           │     │        │
       │  - FreeCodeCamp Inspired Modules     │     │        │
       │  - Básico: Fundamentos & Estruturas  │     │        │
       │  - Intermediário: Lambdas & Algoritm │     │        │
       │  - Avançado: POO, Dunder, Regex      │     │        │
       └──────────────────┬───────────────────┘     │        │
                          │                         ▼        ▼
       ┌──────────────────┴─────────────────┐  ┌─────────────────────┐
       │   CodeEditor & Terminal Engine     │  │  Firestore Backend  │
       │  - Skulpt Local Runtime Engine     │  │  /users/{uid}       │
       │  - Interactive Terminal I/O        │  │  - User Progress    │
       │  - Error Parser & Pop-up Inspector │  │  - Dev Dashboard    │
       │    (ErrorType, Line, Didactic Tip) │  │    (All Users Query)│
       └──────────────────┬─────────────────┘  └─────────────────────┘
                          │
       ┌──────────────────┴─────────────────┐
       │       Arena Kahoot 30s & Áudio     │
       │  - Efeitos Sonoros com AudioTrack  │
       │  - Contagem Regressiva Tensa       │
       └────────────────────────────────────┘
```

### Estrutura do Documento no Firestore (`/users/{userId}`)

```json
{
  "userId": "string",
  "email": "contatopedro21g@gmail.com",
  "displayName": "Pedro Dev",
  "isDev": true,
  "totalXp": 4500,
  "level": 25,
  "currentStreak": 14,
  "currentTier": "ADVANCED",
  "completedLessonIds": ["py_b1_l1", "py_i1_l1", "py_a1_l1"],
  "completedKahootModules": {
    "basic_quiz_1": { "score": 950, "timeBonus": 300 },
    "advanced_quiz_1": { "score": 990, "timeBonus": 450 }
  },
  "lastActiveDate": "2026-10-15",
  "updatedAt": "ServerTimestamp"
}
```

---

## 4. Plano de Execução Técnica

1. **Reestruturação Completa do `PythonCurriculum.kt`**:
   - Criar os módulos do novo **Básico** (Fundamentos, Estruturas e Funções).
   - Desenvolver o novo **Intermediário** (Strings, Cifras, Lambdas, Compreensões, Algoritmos).
   - Implementar o novo **Avançado** (POO Completo, Herança, Métodos Mágicos, Geradores, Regex e Estruturas de Dados clássicas).
2. **Aprimoramento do Terminal e Sistema de Erros com Pop-up Didático**:
   - Reforçar o `CodeEditorView.kt` e `TerminalView.kt` para garantir inserção, deleção/backspace e rolagem perfeitas.
   - Atualizar `CodeEvaluator.kt` e `PythonEngine.kt` para capturar com precisão a linha e classe de exceção do Skulpt.
   - Criar componente `ErrorDiagnosticDialog` em Jetpack Compose com badge do erro, linha indicada e explicação pedagógica do erro.
3. **Mecanismo do Kahoot e Áudio Tenso de 30 Segundos**:
   - Atualizar os questionários ao fim de cada módulo dos novos níveis.
   - Garantir sintetizador dinâmico de batidas tensas acelerando nos últimos 10s.
4. **Painel do Desenvolvedor (`DevDashboardScreen.kt`)**:
   - Detectar e-mail do usuário logado (`contatopedro21g@gmail.com`) ou chave de acesso dev.
   - Implementar consulta no Firestore para exibir todos os alunos cadastrados e seus respectivos progressos.
