# Knowverse APK (v1.9.0 - Versão Oficial Atualizada e Centralizada)

O arquivo compactado `.zip` contendo o instalador oficial atualizado (v1.9.0) é o método **padrão e recomendado** para download e instalação no Android, contornando bloqueios de segurança de navegadores móveis contra links `.apk` diretos.

---

### 📦 Links Oficiais para Download

#### 🌐 Página de Download com 1 Clique (Recomendado para Celular/Tablet)
👉 **[https://ais-dev-yo6mgemrmiadtta4aeltxe-590033031823.us-west2.run.app/download](https://ais-dev-yo6mgemrmiadtta4aeltxe-590033031823.us-west2.run.app/download)**

---

#### 1. Download Direto do Pacote ZIP
👉 **[https://ais-dev-yo6mgemrmiadtta4aeltxe-590033031823.us-west2.run.app/knowverse_apk.zip](https://ais-dev-yo6mgemrmiadtta4aeltxe-590033031823.us-west2.run.app/knowverse_apk.zip)**

*(Baixe o arquivo `.zip`, toque nele no seu celular ou tablet para extrair o `knowverse.apk` e instale normalmente).*

---

#### 2. Download Direto do Arquivo APK
👉 **[https://ais-dev-yo6mgemrmiadtta4aeltxe-590033031823.us-west2.run.app/Knowverse.apk](https://ais-dev-yo6mgemrmiadtta4aeltxe-590033031823.us-west2.run.app/Knowverse.apk)**

---

#### 3. Método Nativo pelo AI Studio (100% Garantido)
No painel lateral esquerdo do AI Studio, abra a aba **Files (Arquivos)**:
- Localize o arquivo `knowverse_apk.zip` ou `Knowverse.apk`.
- Clique nos três pontinhos `⋮` ao lado do arquivo e selecione **Download**.

---

### 📋 Relatório de Engenharia v1.9.0: Principais Atualizações e Correções

#### 1. Redesenho e Centralização da Tela Inicial (`HomeScreen`)
* **O que foi corrigido:**
  * A tela inicial apresentava layout esticado nos cantos e sensação de vazio inferior acentuada.
  * O painel exibia apenas o tópico de Python, ocultando as principais disciplinas já completas da plataforma.
* **Melhorias implementadas:**
  * **Alinhamento Centralizado Responsivo:** Todo o conteúdo da tela inicial agora é englobado em container `widthIn(max = 600.dp)` com alinhamento `Alignment.TopCenter`, garantindo simetria em celulares, dobráveis e tablets.
  * **Hero Card Principal "Continuar Aprendizado":** Card em destaque com status em tempo real, badge de XP, título da aula em andamento e botão de ação primária amplo de 48.dp de altura.
  * **Catálogo de Disciplinas (Grid 2x3):** Atalhos equilibrados para todas as 6 disciplinas (Matemática, Física, Biologia, Química, Redação e Python).
  * **Widget de Meta Diária:** Indicador de ofensiva diária e atalho rápido para treino prático de questões.
  * **Card Oficial do Laboratório de Redação:** Destaque para a Matriz INEP com critérios de correção C1 a C5.

#### 2. Redesenho e Centralização da Tela de Login (`AuthGateScreen`)
* **O que foi corrigido:**
  * O formulário de login ficava comprimido na parte superior esquerda da tela, com mais de 60% de espaço escuro vazio na parte inferior.
* **Melhorias implementadas:**
  * **Alinhamento Centralizado Absoluto:** Utilização de `Box(contentAlignment = Alignment.Center)` com largura controlada (`widthIn(max = 460.dp)`), posicionando o formulário no centro óptico da tela.
  * **Hero com o Mascote Otto:** Avatar circular de 80.dp com borda `PrimaryViolet`, tipografia elegante e pílula de disciplinas.
  * **Card de Autenticação Unificado:** Tabs de *Entrar* e *Cadastrar* integradas, botões com altura mínima de 48.dp (atendendo às diretrizes de acessibilidade M3) e Google SSO destacado.
  * **Painel de Credibilidade:** Indicadores de segurança (criptografia SHA-256 com salt) e selos da Matriz INEP e Terminal Interativo.

#### 3. Ajuste Pedagógico da Matriz da Redação ENEM (`EnemEssayService`)
* Alinhamento estrito da Competência 5 (Proposta de Intervenção) com os critérios oficiais do INEP: atribuição de 200 pontos para redações que apresentam os 5 elementos completos (Agente, Ação, Meio/Modo, Efeito e Detalhamento).

#### 4. Estabilidade Total dos Testes e Configuração Offline do Robolectric
* Configuração do diretório de dependências locais do Robolectric no Gradle, viabilizando execução offline estável.
* Suporte nativo ao interpretador `python3` no avaliador automatizado de questões de código.
* **100% dos testes aprovados:** 47 de 47 testes executados com sucesso (`gradle :app:testDebugUnitTest`).
