package com.example.data.chemistry

import com.example.data.math.CalculusDidacticBundle
import com.example.data.model.EnemTrailLesson
import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample
import com.example.data.model.MathExercise

/**
 * Conteúdo didático completo para o Módulo 3: Química Orgânica (ENEM).
 * Aulas:
 * 1. quim_org_01: Introdução e Cadeias Carbônicas
 * 2. quim_org_02: Reconhecimento de Funções Orgânicas (TODAS as funções detalhadas visualmente)
 * 3. quim_org_03: Nomenclatura Orgânica IUPAC
 * 4. quim_org_04: Isomeria Plana e Espacial
 * 5. quim_org_05: Reações Orgânicas Clássicas
 */
object ChemistryOrganicDidactic {

    fun buildBundle(lesson: EnemTrailLesson): CalculusDidacticBundle {
        return when (lesson.id) {
            "quim_org_01" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Introdução à Química Orgânica e Cadeias Carbônicas

                    A Química Orgânica é a química dos compostos de carbono. No ENEM, a interpretação das fórmulas estruturais e da geometria das ligações é o alicerce para compreender desde a octanagem dos combustíveis até a ação de fármacos.

                    ### 1. Os Três Postulados de Kekulé
                    - **1. Tetravalência Constante:** O átomo de carbono possui 4 elétrons na camada de valência e **sempre estabelece 4 ligações covalentes** para atingir a estabilidade do octeto.
                    - **2. As Quatro Valências são Iguais:** A posição de um substituinte ao redor do carbono com 4 ligações simples é equivalente em qualquer vértice do tetraedro (o clorometano CH_3Cl é uma molécula única, independente de onde o cloro seja desenhado no papel).
                    - **3. Capacidade de Formar Cadeias:** Átomos de carbono ligam-se diretamente entre si formando cadeias curtas, longas, ramificadas, cíclicas e anéis aromáticos, gerando milhões de compostos conhecidos.

                    ### 2. Hibridização do Carbono e Geometria das Ligações
                    O carbono no estado fundamental possui configuração 1s^2 2s^2 2p^2 (apenas 2 orbitais semipreenchidos). Para fazer 4 ligações, sofre **hibridização** (fusão de orbitais):
                    ```
                    Hibridização   Ligações Realizadas     Geometria Espacial    Ângulo de Ligação
                    -------------------------------------------------------------------------------
                    sp³            4 ligações simples (σ)  Tetraédrica           109°28' (~109,5°)
                    sp²            1 dupla (σ+π) + 2 simp  Trigonal Plana        120°
                    sp             1 tripla (σ+2π) + 1 simp Linear                180°
                                   ou 2 duplas (2σ + 2π)   Linear                180°
                    ```
                    - *Ligação Sigma (\sigma):* Frontal, no mesmo eixo nuclear. É forte e permite rotação livre da ligação simples.
                    - *Ligação Pi (\pi):* Lateral, entre orbitais p puros paralelos não hibridizados. É mais frágil e impede a livre rotação (origem da isomeria cis-trans).

                    ### 3. Classificação das Cadeias Carbônicas
                    - **Quanto ao fechamento da cadeia:**
                      • *Aberta (Acíclica ou Alifática):* Possui extremidades livres.
                      • *Fechada (Cíclica):* Átomos formam um ciclo fechado. Se possuir anel benzênico com elétrons \pi deslocalizados por ressonância, é **Aromática**; caso contrário, é **Alicíclica**.
                    - **Quanto à disposição dos carbonos:**
                      • *Normal (Linear):* Apenas carbonos primários e secundários (duas pontas).
                      • *Ramificada:* Possui ramificações contendo carbonos terciários ou quaternários.
                    - **Quanto à saturação (Atenção Máxima ENEM):**
                      • *Saturada:* Apresenta **apenas ligações simples entre carbonos** (C - C). Gorduras saturadas (manteiga, banha) têm cadeias lineares que empacotam facilmente, sendo sólidas a 25°C.
                      • *Insaturada:* Apresenta ao menos uma ligação dupla (C = C) ou tripla (C \equiv C) **entre carbonos**. Óleos vegetais possuem insaturações que criam dobras na cadeia, impedindo o empacotamento denso e tornando-os líquidos.
                    - **Quanto à natureza dos átomos na cadeia:**
                      • *Homogênea:* Formada exclusivamente por átomos de carbono na cadeia principal.
                      • *Heterogênea:* Apresenta um **heteroátomo** (átomo diferente de carbono e hidrogênio, comumente O, N, S) **posicionado estritamente ENTRE dois carbonos** (ex: éter CH_3-O-CH_3 ou amina secundária CH_3-NH-CH_3).
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Carbono sp³", "4 ligações simples (σ)", "Geometria tetraédrica (109,5°)"),
                    FormulaOrSyntaxItem("Carbono sp²", "1 ligação dupla (1σ + 1π) e 2 simples (σ)", "Geometria trigonal plana (120°)"),
                    FormulaOrSyntaxItem("Carbono sp", "1 ligação tripla (1σ + 2π) ou 2 duplas", "Geometria linear (180°)")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Classificação de Carbonos em Fármacos",
                        codeOrFormula = "Primário (1 C vizinho) | Secundário (2 C vizinhos) | Terciário (3 C vizinhos) | Quaternário (4 C vizinhos)",
                        explanation = "A classificação de um átomo de carbono depende exclusivamente de quantos OUTROS ÁTOMOS DE CARBONO estão ligados diretamente a ele. Um carbono ligado a 3 hidrogênios e 1 oxigênio (como no metanol H3C-OH) é classificado como primário, pois liga-se a zero outros carbonos."
                    )
                ),
                goldTip = "Cuidado com o conceito de Heteroátomo: O oxigênio do etanol (CH3-CH2-OH) NÃO é heteroátomo porque está na ponta da cadeia ligado a H, e não ENTRE carbonos!"
            )

            "quim_org_02" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Reconhecimento Visual de TODAS as Funções Orgânicas

                    O reconhecimento de grupos funcionais é o assunto de Química Orgânica **MAIS cobrado na história do ENEM**, aparecendo em questões sobre princípios ativos de medicamentos, feromônios, pesticidas e aditivos alimentares.

                    Abaixo está o guia visual definitivo de cada ligação e grupo funcional:

                    ---

                    ### 1. Hidrocarbonetos
                    Compostos constituídos **exclusivamente por átomos de Carbono e Hidrogênio** (C e H). São apolares e insolúveis em água.
                    - **Alcano:** Apenas ligações simples: R-CH_2-CH_3
                    - **Alceno:** Possui ligação dupla entre carbonos: R-CH=CH-R'
                    - **Alcino:** Possui ligação tripla entre carbonos: R-C\equivC-R'
                    - **Aromático:** Anel de 6 carbonos com elétrons pi ressonantes (Benzeno).

                    ---

                    ### 2. Funções Oxigenadas

                    #### A) Álcool
                    - **Grupo Funcional:** Hidroxila (-OH) ligada a **carbono saturado (sp³)**.
                    - **Estrutura Visual:**
                      ```
                           H
                           |
                      R — C — O — H       (Grupo Hidroxila em carbono sp³)
                           |
                           H
                      ```
                    - **Exemplo Clássico:** Etanol (CH_3-CH_2-OH).

                    #### B) Fenol
                    - **Grupo Funcional:** Hidroxila (-OH) ligada **DIRETAMENTE ao anel aromático**.
                    - **Estrutura Visual:**
                      ```
                            OH
                           /
                        (Anel Benzênico)   (Possui caráter ácido moderado)
                      ```
                    - **Diferença Crítica:** Álcool em anel alicíclico (cicloexanol) NÃO é fenol! Para ser fenol, o anel precisa ser benzênico aromático.

                    #### C) Enol
                    - **Grupo Funcional:** Hidroxila (-OH) ligada a carbono com **dupla ligação (sp²)** não aromático:
                      ```
                      R — CH = CH — OH    (Instável; sofre tautomeria ceto-enólica)
                      ```

                    #### D) Éter
                    - **Grupo Funcional:** Átomo de Oxigênio como **heteroátomo entre dois carbonos**:
                      ```
                      R — O — R'          (Ex: Éter etílico CH3-CH2-O-CH2-CH3)
                      ```

                    #### E) Aldeído
                    - **Grupo Funcional:** Carbonila (C=O) na **EXTREMIDADE da cadeia** ligada a hidrogênio:
                      ```
                           O
                          //
                      R — C — H           (Representado também por R-CHO)
                      ```
                    - **Exemplo Clássico:** Metanal / Formol (H-CHO), aromatizantes de baunilha (vanilina).

                    #### F) Cetona
                    - **Grupo Funcional:** Carbonila (C=O) **ENTRE DOIS CARBONOS** (no meio da cadeia):
                      ```
                           O
                          //
                      R — C — R'          (Ex: Propanona / Acetona CH3-CO-CH3)
                      ```

                    #### G) Ácido Carboxílico
                    - **Grupo Funcional:** Carboxila (Carbonila + Hidroxila no mesmo carbono de extremidade):
                      ```
                           O
                          //
                      R — C — O — H       (Representado por R-COOH)
                      ```
                    - **Exemplo Clássico:** Ácido etanoico / Ácido acético do vinagre (CH_3COOH).

                    #### H) Éster
                    - **Grupo Funcional:** Derivado de ácido carboxílico com oxigênio ligado a cadeia carbônica:
                      ```
                           O
                          //
                      R — C — O — R'      (Representado por R-COO-R')
                      ```
                    - **Papel no Cotidiano:** Responsável pelo aroma e sabor de frutas e doces (flavorizantes) e estrutura do biodiesel.

                    ---

                    ### 3. Funções Nitrogenadas

                    #### A) Amina
                    - **Grupo Funcional:** Nitrogênio ligado a cadeias carbônicas (derivado da amônia NH_3).
                    - **Caráter Químico:** **BÁSICO** (o par de elétrons livre do nitrogênio recebe prótons H^+).
                    - **Estruturas Visuais:**
                      ```
                      Amina Primária:    R — NH₂
                      Amina Secundária:  R — NH — R'
                      Amina Terciária:   R — N(R') — R''
                      ```

                    #### B) Amida
                    - **Grupo Funcional:** Nitrogênio ligado **DIRETAMENTE a um grupo carbonila (C=O)**:
                      ```
                           O
                          //
                      R — C — NH₂         (Ligação peptídica das proteínas e do nylon)
                      ```

                    #### C) Nitrila
                    - **Grupo Funcional:** Carbono com ligação tripla ao Nitrogênio: R-C\equivN.

                    ---

                    ### 4. Haletos Orgânicos
                    - **Grupo Funcional:** Carbono ligado a halogênio: R-X (onde X = F, Cl, Br, I). Presente em solventes e nos pesticidas clorados (como o DDT).
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Ácido Carboxílico", "R - C(=O) - OH", "Carbonila + Hidroxila no mesmo carbono de ponta"),
                    FormulaOrSyntaxItem("Éster", "R - C(=O) - O - R'", "Oxigênio entre carbonila e radical carbônico"),
                    FormulaOrSyntaxItem("Amida", "R - C(=O) - NR2", "Nitrogênio vizinho direto da carbonila"),
                    FormulaOrSyntaxItem("Amina", "R - NR2 (sem carbonila)", "Caráter básico; forma sais solúveis com ácidos")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Identificação de Funções na Molécula do Paracetamol",
                        codeOrFormula = "Fórmula: HO - C6H4 - NH - C(=O) - CH3",
                        explanation = "A molécula do paracetamol apresenta duas funções orgânicas fundamentais: 1. FENOL (hidroxila -OH ligada diretamente ao anel aromático de benzeno) e 2. AMIDA (nitrogênio -NH- ligado diretamente à carbonila C=O). Não é amina nem álcool!"
                    )
                ),
                goldTip = "Diferença fatal no ENEM: Nitrogênio sozinho ligado a carbono simples é AMINA (básica). Nitrogênio vizinho de carbonila C=O é AMIDA (neutra)!"
            )

            "quim_org_03" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Nomenclatura Orgânica Oficial IUPAC

                    A IUPAC (União Internacional de Química Pura e Aplicada) padronizou regras universais para que cada estrutura orgânica tenha um nome único e inconfundível. No ENEM, compreender a nomenclatura permite desenhar a molécula e identificar seu comportamento.

                    ### 1. A Estrutura Básica do Nome IUPAC
                    ${"$"}Nome = PREFIXO (Nº de carbonos) + INFIXO (Tipo de ligação) + SUFIXO (Função orgânica)${"$"}

                    ### 2. Tabela de Prefixos (Número de Carbonos na Cadeia Principal)
                    - 1 C → MET
                    - 2 C → ET
                    - 3 C → PROP
                    - 4 C → BUT
                    - 5 C → PENT
                    - 6 C → HEX
                    - 7 C → HEPT
                    - 8 C → OCT
                    - 9 C → NON
                    - 10 C → DEC

                    ### 3. Tabela de Infixos (Tipo de Saturação entre Carbonos)
                    - Apenas ligações simples (C - C) → AN (ex: Propano)
                    - Uma ligação dupla (C = C) → EN (ex: Propeno)
                    - Duas ligações duplas (2 × C = C) → DIEN (ex: Buta-1,3-dieno)
                    - Uma ligação tripla (C \equiv C) → IN (ex: Etino / Acetileno)

                    ### 4. Tabela de Sufixos (Identidade da Função Química)
                    - Hidrocarboneto → -O (ex: Metano, Butano)
                    - Álcool → -OL (ex: Etanol, Propan-2-ol)
                    - Aldeído → -AL (ex: Metanal, Etanal)
                    - Cetona → -ONA (ex: Propanona)
                    - Ácido Carboxílico → Ácido ... -OICO (ex: Ácido metanoico / Fórmico, Ácido etanoico / Acético)
                    - Éster → ... -OATO de ... -ILA (ex: Etanoato de etila)
                    - Amina → -AMINA (ex: Metilamina, Fenilamina)
                    - Amida → -AMIDA (ex: Etanamida)

                    ### 5. Regras de Numeração da Cadeia Principal
                    Quando houver necessidade de indicar a posição de grupos ou insaturações, a numeração da cadeia carbônica principal inicia pela extremidade que confere os **menores números possíveis**, respeitando a estrita ordem de prioridade:
                    ${"$"}1º GRUPO FUNCIONAL > 2º INSATURAÇÕES (Dupla e Tripla) > 3º RAMIFICAÇÕES ALQUILA${"$"}
                    - *Ramificações comuns:* Metil (CH_3-), Etil (CH_3CH_2-), Isopropil ((CH_3)_2CH-).
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Fórmula Geral do Nome", "Prefixo + Infixo + Sufixo", "Ex: But (4C) + an (simples) + ol (-OH) = Butan-1-ol"),
                    FormulaOrSyntaxItem("Prioridade de Numeração", "Função > Insaturação > Ramificação", "Comece a numerar pela ponta mais próxima da função")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Nomenclatura do Álcool em Gel e Álcool Comercial",
                        codeOrFormula = "CH3 - CH(OH) - CH3  ->  Propan-2-ol (Álcool Isopropílico)",
                        explanation = "Cadeia de 3 carbonos (prop), apenas ligações simples entre carbonos (an), e hidroxila de álcool no carbono 2 (-2-ol). Trata-se do propan-2-ol, amplamente utilizado na higienização de eletrônicos e formulações hospitalares."
                    )
                ),
                goldTip = "No ENEM: Se houver empate na numeração, a ligação dupla tem prioridade numérica sobre a ligação tripla!"
            )

            "quim_org_04" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Isomeria Plana e Espacial (Geométrica e Óptica)

                    Isômeros são compostos distintos que compartilham a **mesma fórmula molecular**, mas apresentam arranjos estruturais e propriedades físico-químicas diferentes.

                    ### 1. Isomeria Plana (Constitucional)
                    Compostos diferenciados pela sua fórmula estrutural plana:
                    - **1. Isomeria de Função:** Pertencem a funções orgânicas diferentes:
                      • *Álcool vs Éter:* C_2H_6O → Etanol  (CH_3CH_2OH)  vs Metoximetano  (CH_3OCH_3).
                      • *Aldeído vs Cetona:* C_3H_6O → Propanal  vs Propanona (acetona).
                      • *Ácido Carboxílico vs Éster:* C_2H_4O_2 → Ácido etanoico  vs Metanoato de metila.
                    - **2. Isomeria de Cadeia:** Mesma função, mas esqueletos de cadeia distintos (ex: Butano linear vs 2-Metilpropano ramificado).
                    - **3. Isomeria de Posição:** Mudança de posição de insaturação, ramificação ou grupo funcional na mesma cadeia (ex: Butan-1-ol vs Butan-2-ol).
                    - **4. Isomeria de Compensação (Metameria):** Mudança na posição do **heteroátomo** entre os carbonos da cadeia (ex: Metoxipropano CH_3-O-C_3H_7 vs Etoxietano C_2H_5-O-C_2H_5).
                    - **5. Tautomeria:** Caso especial de isomeria dinâmica onde os isômeros coexistem em equilíbrio químico (ex: Aldeído ⇌ Enol ou Cetona ⇌ Enol).

                    ### 2. Isomeria Espacial Geométrica (Cis-Trans / E-Z)
                    - **Condição de Ocorrência:**
                      1. Cadeia aberta com ligação dupla (C=C) ou anel fechado com rotação impedida.
                      2. Os dois ligantes de cada carbono da dupla devem ser **diferentes entre si**:
                         ${"$"}No carbono 1:  R_1 \neq R_2 \quad e no carbono 2:  R_3 \neq R_4${"$"}
                    - **Configurações:**
                      • **Cis (ou Z):** Os ligantes de maior massa encontram-se do mesmo lado do plano da dupla ligação.
                      • **Trans (ou E):** Os ligantes encontram-se em lados opostos do plano.
                      • *Aplicação vital no ENEM:* **Gorduras Trans**. A hidrogenação industrial parcial de óleos vegetais converte a conformação natural *cis* (que tem dobras e é líquida) em *trans* (cadeia retilínea que se acumula em placas ateroscleróticas nas artérias).

                    ### 3. Isomeria Espacial Óptica e Carbono Quiral
                    - Ocorre quando a molécula é assimétrica (não sobreponível à sua imagem especular no espelho plano).
                    - **Carbono Quiral (ou Assimétrico, marcado com C^*):** Carbono saturado (sp³) que possui **quatro ligantes totalmente diferentes entre si**:
                      ${"$"}C^*  com  -R_1 \neq -R_2 \neq -R_3 \neq -R_4${"$"}
                    - **Enantiômeros:** Par de isômeros ópticos que são imagens especulares um do outro. Desviam o plano da luz polarizada em sentidos opostos com o mesmo ângulo:
                      • *Dextrogiro (d ou +):* Desvia para a direita.
                      • *Levogiro (l ou -):* Desvia para a esquerda.
                      • *Mistura Racêmica:* Mistura equimolar (50% dextrogiro + 50% levogiro) opticamente inativa por compensação externa.
                    - **A Tragédia da Talidomida (Clássico do ENEM):** Fármaco prescrito para gestantes nos anos 1950. Enquanto o enantiômero (R) atuava como sedativo seguro contra enjoos, o enantiômero (S) era teratogênico, causando malformações congênitas nos bebês.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Condição Isomeria Cis-Trans", "C=C com R1 ≠ R2 e R3 ≠ R4", "Impedimento de rotação e ligantes distintos em cada carbono"),
                    FormulaOrSyntaxItem("Condição Isomeria Óptica", "Presença de Carbono Quiral (C*)", "Carbono sp³ ligado a 4 grupos distintos"),
                    FormulaOrSyntaxItem("Número de Isômeros Ópticos", "N = 2^n (n = número de carbonos quirais)", "Fórmula de van 't Hoff para enantiômeros ativos")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Identificação de Carbono Quiral no Ácido Lático",
                        codeOrFormula = "Fórmula: CH3 - C*H(OH) - COOH",
                        explanation = "O carbono central está ligado a: 1. Grupo Metil (-CH3), 2. Átomo de Hidrogênio (-H), 3. Grupo Hidroxila (-OH), 4. Grupo Carboxila (-COOH). Como os quatro grupos são completamente distintos entre si, o carbono é quiral (assimétrico) e o composto apresenta atividade óptica com dois enantiômeros."
                    )
                ),
                goldTip = "Macete rápido para o ENEM: Carbono com ligação dupla (C=) ou tripla (C≡) NUNCA é quiral! Carbono com dois hidrogênios (-CH2-) NUNCA é quiral!"
            )

            "quim_org_05" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Reações Orgânicas Clássicas no Cotidiano e Indústria

                    As reações orgânicas no ENEM conectam **síntese industrial, biotecnologia e produtos caseiros**.

                    ### 1. Esterificação de Fischer e Flavorizantes
                    - Reação de equilíbrio reversível entre um **Ácido Carboxílico** e um **Álcool**, catalisada por ácido forte (H_2SO_4), produzindo **Éster** e **Água**:
                      ${"$"}Ácido Carboxílico + Álcool \xrightleftharpoons[Δ]{H^+} Éster + Água${"$"}
                    - *Aplicações no ENEM:* Produção de aromatizantes artificiais de alimentos (ex: etanoato de butila tem aroma de maçã; butanoato de etila tem aroma de abacaxi).
                    - A reação inversa (quebra do éster por água em meio ácido) é a **Hidrólise Ácida**.

                    ### 2. Saponificação (Hidrólise Alcalina de Triglicerídeos)
                    - Reação de um triéster de ácidos graxos (**óleo vegetal ou gordura animal**) com uma base inorgânica forte (NaOH ou KOH):
                      ${"$"}Triglicerídeo + 3 NaOH \xrightarrow{Δ} Glicerol (Glicerina) + 3 Sabões (Sais de Ácido Graxo)${"$"}
                    - NaOH produz **sabão duro** em barra (sal de sódio); KOH produz **sabão líquido** ou cremoso (sal de potássio).
                    - Subproduto vital: **Glicerol (Glicerina)**, um poliálcool com 3 hidroxilas amplamente utilizado na indústria cosmética como umectante hidratante.

                    ### 3. Reações de Oxidação de Álcoois e o Bafômetro
                    - **Álcool Primário:** Oxida a **Aldeído** e, em oxidação enérgica, a **Ácido Carboxílico** (ex: azedamento do vinho em vinagre por bactérias acéticas):
                      ${"$"}Etanol \xrightarrow{[O]} Etanal \xrightarrow{[O]} Ácido Etanoico${"$"}
                    - **Álcool Secundário:** Oxida unicamente a **Cetona**:
                      ${"$"}Propan-2-ol \xrightarrow{[O]} Propanona (Acetona)${"$"}
                    - **Álcool Terciário:** **NÃO sofre oxidação** em condições brandas/médias (não possui hidrogênio ligado ao carbono que carrega o grupo -OH).
                    - *O Bafômetro Químico Clássico:* O etanol do hálito do motorista reduz o dicromato de potássio (K_2Cr_2O_7, cor **laranja**, com cromo Cr^{+6}) a íons sulfato de cromo (Cr^{+3}, cor **verde**), atestando a embriaguez ao volante.

                    ### 4. Polimerização por Adição e Condensação
                    - **Polimerização por Adição:** Monômeros com ligação dupla (C=C) sofrem quebra da ligação pi e somam-se em longuíssimas cadeias macromoleculares sem perda de átomos (ex: Polietileno PE, Polipropileno PP, PVC).
                    - **Polimerização por Condensação:** Monômeros bifuncionais reagem eliminando uma pequena molécula (normalmente água H_2O ou HCl) a cada ligação peptídica ou éster formada (ex: PET, Nylon-6,6 e Proteínas).
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Esterificação de Fischer", "R-COOH + R'-OH <=> R-COO-R' + H2O", "Ácido + Álcool <=> Éster + Água (catalisada por H2SO4)"),
                    FormulaOrSyntaxItem("Saponificação", "Óleo/Gordura + 3 NaOH -> Glicerol + 3 Sabões", "Hidrólise básica de éster gerando sais de ácidos graxos"),
                    FormulaOrSyntaxItem("Oxidação do Bafômetro", "Cr(+6) [Laranja] + Etanol -> Cr(+3) [Verde] + Ácido Acético", "Redução do dicromato acompanhada da oxidação do álcool")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Mecanismo da Saponificação de Óleo de Fritura Usado",
                        codeOrFormula = "Triglicerídeo + 3 NaOH -> C3H5(OH)3 (Glicerina) + 3 R-COO⁻ Na⁺ (Sabão)",
                        explanation = "A reciclagem de óleo de cozinha usado para fabricação de sabão caseiro promove a sustentabilidade ao evitar o descarte na rede de esgoto (onde 1 litro de óleo contamina até 1 milhão de litros de água). O óleo sofre hidrólise alcalina na presença de soda cáustica (NaOH), convertendo os ésteres em sais sódicos anfifílicos de ácidos graxos."
                    )
                ),
                goldTip = "Memorize para o teste do bafômetro: Álcool primário vira aldeído e depois ácido carboxílico; álcool secundário vira cetona; álcool terciário NÃO reage!"
            )

            else -> CalculusDidacticBundle(
                theoryMarkdown = "# Química Orgânica ENEM\nEstrutura, funções, nomenclatura e reatividade do carbono.",
                formulas = emptyList(),
                solvedExamples = emptyList(),
                goldTip = "Identifique os grupos funcionais com rapidez sublinhando heteroátomos (O, N) e insaturações."
            )
        }
    }

    fun buildExercises(lesson: EnemTrailLesson): List<MathExercise> {
        return when (lesson.id) {
            "quim_org_01" -> listOf(
                MathExercise(
                    id = "quim_ex_org_01_a",
                    title = "ENEM: Hibridização e Geometria do Grafeno e Nanotubos",
                    problemStatement = "O grafeno é um nanomaterial revolucionário formado por uma única camada bidimensional de átomos de carbono dispostos em uma malha hexagonal contínua semelhante a favos de mel. Devido à sua altíssima condutividade elétrica e extrema resistência mecânica, é apontado como o material do futuro na eletrônica. Em cada folha de grafeno, todos os átomos de carbono apresentam hibridização e geometria molecular do tipo:",
                    options = listOf(
                        "Hibridização sp² com geometria trigonal plana e ângulos de ligação de 120°.",
                        "Hibridização sp³ com geometria tetraédrica e ângulos de 109,5°.",
                        "Hibridização sp com geometria linear e ligações triplas alternadas.",
                        "Hibridização sp³d² com geometria octaédrica tridimensional.",
                        "Nenhuma hibridização, estabelecendo apenas ligações iônicas puras."
                    ),
                    correctIndex = 0,
                    hint = "Uma folha plana em hexágonos: cada carbono liga-se a outros 3 carbonos por 3 ligações sigma no plano e possui 1 elétron pi deslocalizado.",
                    stepByStepSolution = "A alternativa A está correta. No grafeno (assim como no grafite), cada átomo de carbono realiza 3 ligações sigma com 3 carbonos vizinhos em um arranjo plano bidimensional de hexágonos regulares, o que caracteriza a hibridização sp² com geometria trigonal plana (ângulos de 120°). O quarto elétron de cada carbono ocupa um orbital 'p' puro deslocalizado em uma nuvem pi conjugada condutora, responsável pela excepcional condução de eletricidade do grafeno."
                )
            )

            "quim_org_02" -> listOf(
                MathExercise(
                    id = "quim_ex_org_02_a",
                    title = "ENEM: Identificação de Funções no Ácido Acetilsalicílico (Aspirina)",
                    problemStatement = "O ácido acetilsalicílico (conhecido popularmente como aspirina) é um dos analgésicos e anti-inflamatórios mais consumidos no mundo. Sua estrutura química é sintetizada a partir da reação de esterificação do ácido salicílico e apresenta a fórmula molecular C9H8O4. Analisando a fórmula estrutural plana da aspirina, quais funções orgânicas oxigenadas estão presentes em sua molécula?",
                    options = listOf(
                        "Ácido carboxílico e éster.",
                        "Fenol e aldeído.",
                        "Álcool e cetona.",
                        "Éter e anidrido.",
                        "Amida e fenol."
                    ),
                    correctIndex = 0,
                    hint = "A aspirina possui um grupo -COOH (carboxila) e um grupo -O-C(=O)-CH3 ligado ao anel aromático.",
                    stepByStepSolution = "A alternativa A está correta. A molécula de aspirina possui um grupo carboxila (-COOH) ligado diretamente ao anel aromático, configurando a função ÁCIDO CARBOXÍLICO. Além disso, a antiga hidroxila fenólica do ácido salicílico foi acetilada, formando um grupamento acetoxi (-O-CO-CH3), que caracteriza a função ÉSTER."
                )
            )

            "quim_org_03" -> listOf(
                MathExercise(
                    id = "quim_ex_org_03_a",
                    title = "ENEM: Nomenclatura IUPAC do Combustível de Referência (Isoctano)",
                    problemStatement = "O índice de octanagem mede a resistência da gasolina à detonação prematura no motor de combustão interna. O padrão de referência de máxima eficiência antidetonante (100 octanas) é o composto orgânico conhecido usualmente como 'isoctano', cuja cadeia carbônica ramificada é constituída por 8 átomos de carbono e apresenta a fórmula estrutural plana: (CH3)3C - CH2 - CH(CH3)2. De acordo com as regras oficiais de nomenclatura da IUPAC, o nome correto desse hidrocarboneto é:",
                    options = listOf(
                        "2,2,4-Trimetilpentano.",
                        "2,4,4-Trimetilpentano.",
                        "2,2-Dimetil-4-metilpentano.",
                        "Octano normal.",
                        "2-Metilisopentano."
                    ),
                    correctIndex = 0,
                    hint = "A cadeia principal tem 5 carbonos (pentano). Numere pela extremidade que confere os menores números aos radicais metil.",
                    stepByStepSolution = "A alternativa A está correta. A cadeia carbônica principal mais longa contém 5 carbonos contínuos (pentano). Numerando a partir da ponta esquerda (mais ramificada, com dois grupos metil no carbono 2 e um grupo metil no carbono 4), obtemos os menores números possíveis para os substituintes: 2, 2 e 4. Portanto, o nome oficial IUPAC é 2,2,4-trimetilpentano."
                )
            )

            "quim_org_04" -> listOf(
                MathExercise(
                    id = "quim_ex_org_04_a",
                    title = "ENEM: Isomeria Óptica e Atividade de Fármacos",
                    problemStatement = "O ibuprofeno é um anti-inflamatório não esteroidal muito utilizado para alívio de febres e dores musculares. Sua estrutura contém a fórmula molecular C13H18O2 e exibe atividade óptica, de modo que apenas o enantiômero (S)-ibuprofeno é biologicamente ativo no organismo, enquanto o enantiômero (R) é inativo. A existência desses dois enantiômeros decorre da presença de:",
                    options = listOf(
                        "Um átomo de carbono quiral (assimétrico) sp³ ligado a quatro substituintes diferentes.",
                        "Uma ligação dupla entre carbonos que impede a rotação espacial dos radicais.",
                        "Uma ligação peptídica formada pela união de aminoácidos com radicais livres.",
                        "Um anel aromático benzenoide contendo elétrons pi deslocalizados por ressonância.",
                        "Um heteroátomo de oxigênio que inverte a polaridade da luz polarizada."
                    ),
                    correctIndex = 0,
                    hint = "Isomeria óptica e enantiômeros são gerados por centros de assimetria molecular (carbonos quirais com 4 grupos distintos).",
                    stepByStepSolution = "A alternativa A está correta. A enantiomeria (isomeria óptica) manifesta-se quando uma molécula não possui plano de simetria e não se sobrepõe à sua imagem especular. Isso ocorre tipicamente devido à presença de pelo menos um carbono assimétrico ou quiral (C*), que é um carbono tetraédrico sp³ ligado a quatro ligantes quimicamente distintos entre si."
                )
            )

            "quim_org_05" -> listOf(
                MathExercise(
                    id = "quim_ex_org_05_a",
                    title = "ENEM: Reação de Saponificação e Química do Sabão",
                    problemStatement = "O descarte inadequado de óleo de cozinha usado nas redes de esgoto doméstico causa graves entupimentos nas tubulações e impermeabilização dos leitos de rios. Um projeto comunitário coleta esse óleo residual e o submete a aquecimento moderado na presença de uma solução aquosa concentrada de hidróxido de sódio (NaOH), obtendo barras de sabão e um subproduto solúvel em água de alto valor cosmético. Essa reação química e o subproduto formado são, respectivamente:",
                    options = listOf(
                        "Saponificação (hidrólise alcalina de ésteres) e glicerol (glicerina).",
                        "Esterificação de Fischer e ácido sulfúrico concentrado.",
                        "Oxidação catalítica branda e propanona anidra.",
                        "Polimerização por adição e polietileno de alta densidade.",
                        "Fermentação acética anaeróbia e etanol absoluto."
                    ),
                    correctIndex = 0,
                    hint = "Óleo (triglicerídeo) + Soda Cáustica (NaOH) -> Sabão (sal de ácido graxo) + Glicerol (triálcool).",
                    stepByStepSolution = "A alternativa A está correta. Os óleos e gorduras são triglicerídeos (triésteres derivados do glicerol). Ao reagirem com soda cáustica (NaOH) sob aquecimento, ocorre a quebra das ligações éster por hidrólise alcalina (reação de saponificação), liberando os sais sódicos dos ácidos graxos (os sabões) e o glicerol (glicerina, propan-1,2,3-triol), um poliálcool amplamente empregado na indústria farmacêutica e cosmética."
                )
            )

            else -> emptyList()
        }
    }
}
