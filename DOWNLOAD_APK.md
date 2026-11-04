# Knowverse APK (v1.8.6 - Versão Oficial Atualizada e Blindada)

O arquivo compactado `.zip` contendo o instalador oficial atualizado (v1.8.6) é o método **padrão e recomendado** para download e instalação no Android, contornando bloqueios de segurança de navegadores móveis contra links `.apk` diretos.

---

### 📦 Link de Download Padrão (Recomendado)

👉 **[https://ais-dev-yo6mgemrmiadtta4aeltxe-590033031823.us-west2.run.app/knowverse_apk.zip](https://ais-dev-yo6mgemrmiadtta4aeltxe-590033031823.us-west2.run.app/knowverse_apk.zip)**

*(Baixe o arquivo `.zip`, toque nele no seu celular ou tablet para extrair o `knowverse.apk` e instale normalmente).*

---

### 📋 Relatório de Engenharia v1.8.6: O que foi mudado, como e o que estava sendo afetado

#### 1. Correção Integral da Matéria de Cálculo 1 (Limites, Derivadas e Integrais)
* **O que estava sendo afetado:**
  * Ao abrir aulas de Cálculo 1 (por exemplo, *Aula 3: Propriedades Operatórias e Limites no Infinito*), o aplicativo exibia de forma errônea o conteúdo de **"Química Geral e Estrutura da Matéria"**.
  * Além disso, as abas de fórmulas e exemplos resolvidos de Cálculo apareciam vazias ou corrompidas.
* **Causa Raiz:**
  * O método `CalculusDidacticContent.getContentForLesson(lessonId)` delegava para `EnemChemistryDidacticProvider.getDidacticBundle(lessonId)` sem verificar o prefixo de disciplina. Dentro de `EnemChemistryDidacticProvider`, havia um `else -> ChemistryGeneralDidactic.buildBundle(lesson)` que capturava qualquer ID de aula do aplicativo como se fosse Química Geral.
* **Como foi corrigido:**
  * Implementada verificação estrita de prefixo em `EnemChemistryDidacticProvider` (`if (!lessonId.startsWith("quim_")) return null`), `EnemGeographyDidacticProvider` e `EnemHistoryDidacticProvider`.
  * Reformulado `CalculusDidacticContent` com roteamento estrito e prioritário para os módulos reais de Cálculo I (`CalculusLimitsDidactic`, `CalculusDerivativesDidactic`, `CalculusIntegralsDidactic`).
  * Todas as 28 aulas de Cálculo 1 agora exibem suas teorias, fórmulas exatas, exemplos resolvidos com passo a passo e gráficos diagnósticos corretos.

#### 2. Visualizador Matemático Nativo em Canvas 120 FPS (Substituição de GeoGebra WebView)
* **O que estava sendo afetado:**
  * Os gráficos matemáticos interativos ficavam como uma caixa preta vazia ou falhavam ao carregar offline nos dispositivos móveis (`Screenshot_20261101_162816_Knowverse_fee60fb2.png`).
* **Causa Raiz:**
  * A dependência anterior de um WebView Android carregando bundles pesados e remotos do GeoGebra falhava devido a restrições de rede, falta de aceleração gráfica em WebViews embutidas e problemas de CORS/sandbox.
* **Como foi corrigido:**
  * Desenvolvido um motor nativo Kotlin (`MathExpressionEvaluator.kt`) com analisador sintático completo (suporte a operadores `+`, `-`, `*`, `/`, `^`, funções trigonométricas `sin`, `cos`, `tan`, logaritmos `ln`, `log`, valor absoluto `abs`, constantes `pi`, `e` e derivação numérica diferencial).
  * Criado o componente nativo Compose `NativeMathGraphCanvas` com aceleração por GPU (120 FPS), grade milimetrada adaptativa, arrasto e pan contínuo (`detectDragGestures`), zoom dinâmico com botões `+`, `-` e `Reset`, traçado de assíntotas verticais/horizontais, detecção de descontinuidades, reta tangente interativa com slider e pontos críticos.

#### 3. Reformulação Pedagógica das Matérias sem Fórmulas (Linguagens, Humanas, Biologia)
* **O que estava sendo afetado:**
  * Aulas de matérias conceituais e discursivas (como Interpretação de Texto, Literatura, Linguística, História do Brasil, Geografia e Filosofia) usavam o mesmo formato de matemática ("Fórmulas & Regras", "Passo Analítico"), o que empobrecia o conteúdo e causava estranheza visual.
* **Como foi corrigido:**
  * Implementado modo pedagógico adaptativo `isNonFormulaSubject` em `CalculusLessonScreen.kt`:
    1. **Navegação em 4 Etapas Adaptativa:** `1. Teoria` | `2. Conceitos` | `3. Análises` | `4. Treino`.
    2. **Etapa 2 (Conceitos-Chave e Fundamentos Teóricos):** Substituição de quadros de equações por cards de análise conceitual com ícones de marcador, bloco de texto didático em destaque editorial e campo de **Aplicação no ENEM**.
    3. **Etapa 3 (Textos Didáticos & Análises Comentadas):** Substituição do quadro de cálculo por container de **Fonte / Trecho Didático** com citação em itálico e comentários detalhados sobre a intencionalidade discursiva e pegadinhas do ENEM.

#### 4. Destaque Dinâmico de Trilhas (Apenas na Trilha Ativa do Usuário)
* **O que estava sendo afetado:**
  * A disciplina **Matemática ENEM** ficava permanentemente com borda verde neon e destaque fixo na aba "Trilhas", independentemente de qual trilha o usuário estava de fato cursando ou selecionando.
* **Como foi corrigido:**
  * Removido o destaque estático hardcoded de Matemática ENEM em `LearnTrailScreen.kt`.
  * Introduzido o estado inteligente `activeFollowTrail`, sincronizado com o histórico de aulas e a trilha ativa do usuário.
  * O destaque visual com borda colorida e badge `Trilha Selecionada` agora é aplicado **exclusivamente na trilha que o estudante escolheu seguir**.

#### 5. Correção de Textos Grandes em Caixas Pequenas e Sobreposições
* **O que estava sendo afetado:**
  * No card de indicação (*Convidar Colegas de Estudo*), o botão "Compartilhar" ficava espremido verticalmente letra por letra (`Co m pa rti lha r`).
  * Na barra superior das aulas, títulos longos como *"Aula 3: Propriedades Operatórias e Limites no Infinito"* eram cortados com reticências prematuras (*"Aula 3: Propriedades e"*).
  * No Bento Grid da tela inicial, nomes de lições e subtítulos ficavam truncados de forma ilegível.
  * Na tela de Perfil, o botão "Verificar Atualizações" sobrepunha o texto explicativo em telas com fontes ampliadas.
* **Como foi corrigido:**
  * `GamificationWidgets.kt`: Adicionado `Modifier.weight(1f)` à coluna de texto e `softWrap = false` com `wrapContentWidth()` ao botão de compartilhamento.
  * `CalculusLessonScreen.kt`: A coluna de títulos recebeu `weight(1f)` e `maxLines = 2` com entrelinha otimizada, permitindo que títulos completos sejam lidos com clareza.
  * `ActionBentoGrid.kt`: Ajustado `nextLesson.title` para `maxLines = 2` e subtítulos compactos bem distribuídos.
  * `ProfileScreen.kt`: O container de atualizações recebeu distribuição balanceada com peso na descrição e largura sob medida no botão de ação.
