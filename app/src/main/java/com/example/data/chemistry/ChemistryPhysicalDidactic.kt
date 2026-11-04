package com.example.data.chemistry

import com.example.data.math.CalculusDidacticBundle
import com.example.data.model.EnemTrailLesson
import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample
import com.example.data.model.MathExercise

/**
 * Conteúdo didático completo para o Módulo 2: Físico-Química: A Matemática da Química (ENEM).
 * Aulas:
 * 1. quim_fis_01: Estequiometria e Cálculos Químicos
 * 2. quim_fis_02: Soluções e Concentrações
 * 3. quim_fis_03: Termoquímica
 * 4. quim_fis_04: Cinética Química
 * 5. quim_fis_05: Equilíbrio Químico e pH
 * 6. quim_fis_06: Eletroquímica: Pilhas e Eletrólise
 */
object ChemistryPhysicalDidactic {

    fun buildBundle(lesson: EnemTrailLesson): CalculusDidacticBundle {
        return when (lesson.id) {
            "quim_fis_01" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Estequiometria e Relações Quantitativas no ENEM

                    O cálculo estequiométrico no ENEM não é mero malabarismo aritmético: ele sempre avalia **a viabilidade econômica, o rendimento industrial e a mitigação de poluentes** (como a emissão de CO_2 por litro de combustível ou neutralização de rejeitos ácidos).

                    ### 1. As Leis Ponderais Fundamentais
                    - **Lei de Lavoisier (Conservação das Massas):** Em um sistema fechado, a massa total dos reagentes é rigorosamente igual à massa total dos produtos. *"Na natureza nada se cria, nada se perde, tudo se transforma."* Exige que a equação química esteja devidamente **balanceada**.
                    - **Lei de Proust (Proporções Definidas):** Uma determinada substância composta é sempre formada pela combinação de seus elementos em uma proporção em massa fixa e constante. Ao dobrar a massa de um reagente, a massa de produto gerada também dobra proporcionalmente.

                    ### 2. Grandezas Químicas e Unidades
                    - **1 Mol de qualquer entidade:** Contém 6,02 × 10^{23} unidades elementares (Constante de Avogadro).
                    - **Massa Molar (M):** Massa em gramas correspondente a 1 mol de partículas (g/mol). Calculada somando as massas atômicas da fórmula química (ex: H_2O = 2(1) + 16 = 18 g/mol; CaCO_3 = 40 + 12 + 3(16) = 100 g/mol).
                    - **Volume Molar de Gases nas CNTP (0°C e 1 atm):** 22,4 L/mol (ou 22,7 L/mol na convenção IUPAC moderna de 1 bar).

                    ### 3. Casos Especiais Recorrentes no ENEM
                    - **1. Reagente Limitante e Reagente em Excesso:**
                      • Ocorre quando os reagentes não são adicionados na proporção estequiométrica exata.
                      • **Reagente Limitante:** É consumido integralmente primeiro e determina a quantidade máxima de produto que pode ser formada.
                      • **Reagente em Excesso:** Sobra sem reagir ao término do processo.
                      • *Método de resolução:* Divida a quantidade em mols fornecida de cada reagente pelo seu respectivo coeficiente estequiométrico. O menor valor obtido indica quem é o reagente limitante!
                    - **2. Grau de Pureza da Amostra:**
                      • Minérios, calcários e reagentes industriais raramente são 100% puros.
                      • Calcule primeiro a massa de substância pura antes de montar a regra de três estequiométrica:
                        ${"$"}m_{pura} = m_{amostra} × \frac{\% pureza}{100}${"$"}
                    - **3. Rendimento da Reação (\eta):**
                      • Perdas por reações paralelas, vazamentos ou equilíbrio químico reduzem a produção real em relação ao máximo teórico (100%):
                        ${"$"}Rendimento (\%) = \frac{m_{real}}{m_{teorica}} × 100${"$"}
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Constante de Avogadro", "1 mol = 6,02 * 10²³ partículas", "Número de átomos, moléculas ou íons contidos em 1 mol"),
                    FormulaOrSyntaxItem("Relação Número de Mols", "n = m / M", "n = quantidade de matéria (mol), m = massa (g), M = massa molar (g/mol)"),
                    FormulaOrSyntaxItem("Volume Molar nas CNTP", "V = n * 22,4 L", "Válido para gases ideais a 0°C e 1 atm de pressão")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Queima do Carvão e Emissão de CO2",
                        codeOrFormula = "C(s) + O2(g) -> CO2(g) | 12g C -> 44g CO2",
                        explanation = "A combustão completa de 1 tonelada (1000 kg) de carvão com 90% de pureza em carbono consome 900 kg de carbono puro. Pela estequiometria: 12 kg de C produzem 44 kg de CO2. Logo, 900 kg produzem (900 * 44) / 12 = 3.300 kg (3,3 toneladas) de CO2 lançados na atmosfera."
                    )
                ),
                goldTip = "Passo a passo infalível: 1. Escreva a equação -> 2. Balanceie os coeficientes -> 3. Identifique o reagente limitante -> 4. Aplique a pureza na entrada e o rendimento na saída!"
            )

            "quim_fis_02" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Soluções, Solubilidade e Cálculos de Concentração

                    Soluções são misturas homogêneas de duas ou mais substâncias compostas por um **soluto** (o que se dissolve) e um **solvente** (o dispersante, geralmente a água, considerada solvente universal).

                    ### 1. Coeficiente de Solubilidade e Curvas de Solubilidade
                    - **Coeficiente de Solubilidade (C_s):** Quantidade máxima de soluto capaz de se dissolver em uma quantidade fixa de solvente (normalmente 100 g de H_2O) a uma dada temperatura.
                    - **Classificação das Soluções:**
                      • *Insaturada:* Contém quantidade de soluto inferior ao limite de saturação.
                      • *Saturada:* Contém exatamente a quantidade limite de soluto dissolvido. Se adicionado mais soluto, forma-se o **corpo de fundo (precipitado)**.
                      • *Supersaturada:* Solução instável que contém mais soluto dissolvido do que o coeficiente de saturação permite naquela temperatura (obtida por resfriamento lento e repouso absoluto).
                    - **Curvas de Solubilidade:**
                      • *Dissolução Endotérmica:* A solubilidade aumenta com a elevação da temperatura (a maioria dos sais; curva ascendente).
                      • *Dissolução Exotérmica:* A solubilidade diminui com o aumento da temperatura (ex: sulfato de cério Ce_2(SO_4)_3 e gases dissolvidos em líquidos como o O_2 e CO_2). **Atenção ENEM:** O aquecimento térmico de rios por usinas nucleares e indústrias reduz o O_2 dissolvido, provocando a morte de peixes por asfixia.

                    ### 2. Expressões de Concentração no ENEM
                    - **1. Concentração Comum (C):**
                      ${"$"}C = \frac{m_1}{V} \quad (em g/L)${"$"}
                      Onde m_1 é a massa de soluto em gramas e V é o volume da solução em litros.
                    - **2. Concentração em Quantidade de Matéria / Molaridade (M ou \mathfrak{M}):**
                      ${"$"}M = \frac{n_1}{V} = \frac{m_1}{M_1 · V} \quad (em mol/L)${"$"}
                      Onde n_1 é o número de mols de soluto e M_1 é sua massa molar em g/mol.
                    - **3. Título em Massa (\tau) e Porcentagem em Massa:**
                      ${"$"}\tau = \frac{m_1}{m_{solucao}} = \frac{m_1}{m_1 + m_2} \quad \implies \quad \% = \tau × 100${"$"}
                    - **4. Partes por Milhão (ppm):** Utilizada para poluentes e solutos traço em baixíssimas concentrações:
                      ${"$"}1 ppm = \frac{1 g de soluto}{10^6 g de solução} = \frac{1 mg de soluto}{1 kg ou 1 L de água}${"$"}

                    ### 3. Diluição de Soluções
                    - Diluir significa adicionar solvente puro a uma solução existente. **A massa e o número de mols de soluto permanecem rigorosamente inalterados (n_{inicial} = n_{final})**:
                      ${"$"}C_1 · V_1 = C_2 · V_2 \quad ou \quad M_1 · V_1 = M_2 · V_2${"$"}
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Molaridade (mol/L)", "M = m1 / (M1 * V)", "m1 em g, M1 em g/mol, V em Litros"),
                    FormulaOrSyntaxItem("Fórmula Geral de Diluição", "C1 * V1 = C2 * V2", "A quantidade de soluto inicial é idêntica à final"),
                    FormulaOrSyntaxItem("Partes por Milhão (ppm)", "1 ppm = 1 mg / L de água", "Relação fundamental para controle ambiental e toxicologia")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Cálculo de Diluição de Ácido em Laboratório",
                        codeOrFormula = "M1 * V1 = M2 * V2 | (6 mol/L) * (50 mL) = (0,5 mol/L) * V2",
                        explanation = "Para preparar uma solução diluída a partir de 50 mL de ácido clorídrico 6 mol/L: V2 = (6 * 50) / 0,5 = 600 mL. Como o volume final é 600 mL e já havia 50 mL de ácido, o volume de água destilada que deve ser adicionado é 600 - 50 = 550 mL."
                    )
                ),
                goldTip = "Regra de laboratório e segurança: NUNCA jogue água sobre ácido concentrado! Sempre adicione o ÁCIDO LENTAMENTE SOBRE A ÁGUA para evitar projeções térmicas violentas!"
            )

            "quim_fis_03" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Termoquímica: Calor de Reação e Variação de Entalpia (ΔH)

                    A Termoquímica estuda a troca de energia térmica entre o sistema reacional e a vizinhança. No ENEM, o conceito central é a **Entalpia (H)**, grandeza que mede a energia química contida nas ligações e estruturas das substâncias a pressão constante.

                    ### 1. Processos Endotérmicos vs Exotérmicos
                    - **Processo Exotérmico (Δ H < 0):**
                      • Libera calor para o ambiente. A vizinhança aquece.
                      • A entalpia dos produtos é menor que a dos reagentes (H_{produtos} < H_{reagentes}).
                      • *Exemplos:* Todas as reações de combustão (queima de madeira, gasolina, gás de cozinha), condensação e solidificação.
                    - **Processo Endotérmico (Δ H > 0):**
                      • Absorve calor da vizinhança. A vizinhança resfria.
                      • A entalpia dos produtos é maior que a dos reagentes (H_{produtos} > H_{reagentes}).
                      • *Exemplos:* Fotossíntese (6CO_2 + 6H_2O + luz → C_6H_{12}O_6 + 6O_2), bolsas de gelo instantâneo com dissolução de nitrato de amônio (NH_4NO_3), fusão e vaporização.

                    ### 2. Três Métodos para Calcular o ΔH de uma Reação no ENEM
                    - **1. Por Entalpias Padrão de Formação (Δ H_f^\circ):**
                      • A entalpia de formação de qualquer substância simples no estado padrão e forma alotrópica mais estável é zero por convenção (Δ H_f^\circ = 0 para O_{2(g)}, C_{grafite}, Fe_{(s)}, H_{2(g)}).
                      ${"$"}Δ H_{reacao} = \sum Δ H_{f(produtos)}^\circ - \sum Δ H_{f(reagentes)}^\circ${"$"}
                    - **2. Por Energia de Ligação:**
                      • Quebrar ligações químicas é sempre um processo **endotérmico** (absorve energia, + Δ H).
                      • Formar novas ligações químicas é sempre um processo **exotérmico** (libera energia, - Δ H).
                      ${"$"}Δ H = \sum (Energia das ligações QUEBRADAS nos reagentes) - \sum (Energia das ligações FORMADAS nos produtos)${"$"}
                    - **3. Pela Lei de Hess (Soma de Equações Termoquímicas):**
                      • A variação de entalpia de uma reação química depende exclusivamente dos estados inicial e final, sendo independente do caminho ou do número de etapas intermediárias.
                      • *Regras operatórias:*
                        1. Ao inverter uma equação química, o sinal do seu Δ H inverte obrigatoriamente.
                        2. Ao multiplicar ou dividir os coeficientes de uma equação por um fator n, o valor de seu Δ H deve ser multiplicado ou dividido pelo mesmo fator n.
                        3. Some as equações manipuladas cancelando intermediários até obter a equação global desejada e some algebricamente os Δ H.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Variação de Entalpia (ΔH)", "ΔH = H_produtos - H_reagentes", "ΔH < 0: Exotérmica | ΔH > 0: Endotérmica"),
                    FormulaOrSyntaxItem("Lei de Hess", "ΔH_global = ΔH1 + ΔH2 + ... + ΔHn", "A entalpia é uma função de estado independente do caminho"),
                    FormulaOrSyntaxItem("ΔH por Energia de Ligação", "ΔH = Σ(Quebradas) - Σ(Formadas)", "Quebra consome energia (+) e formação libera energia (-)")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Combustão Completa do Etanol",
                        codeOrFormula = "C2H5OH(l) + 3 O2(g) -> 2 CO2(g) + 3 H2O(l) | ΔH = -1368 kJ/mol",
                        explanation = "A reação libera 1368 kJ por mol de etanol queimado. Como a massa molar do etanol é 46 g/mol, seu poder calorífico específico é de 1368 / 46 ≈ 29,7 kJ por grama. Comparar o poder calorífico por grama ou por mol de etanol vs gasolina é clássico no ENEM."
                    )
                ),
                goldTip = "Lembre-se sempre: Substância simples no estado padrão tem entalpia de formação ZERO (ex: O2 gás, H2 gás, C grafite). Mas C diamante NÃO é zero porque não é a forma alotrópica mais estável!"
            )

            "quim_fis_04" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Cinética Química: Velocidade das Reações e Fatores de Aceleração

                    A Cinética Química estuda a rapidez com que reagentes se convertem em produtos e os mecanismos moleculares envolvidos. No ENEM, o foco está na **conservação de alimentos, conversores catalíticos automotivos e enzimas biológicas**.

                    ### 1. Condições para Ocorrência de uma Reação Química
                    - **1. Contato entre reagentes:** As espécies devem colidir espacialmente.
                    - **2. Afinidade química:** Propensão eletrônica intrínseca de reagir.
                    - **3. Colisões Efetivas (ou Eficazes):** Nem todo choque gera reação. A colisão só é eficaz se satisfizer dois critérios simultâneos:
                      • *Geometria de colisão favorável:* Orientação espacial correta entre os átomos ligantes.
                      • *Energia Cinética \ge Energia de Ativação (E_a):* Energia mínima que as moléculas devem possuir no momento do choque para romper as ligações antigas e atingir o **Complexo Ativado** (estado de transição transitório de mais alta energia potencial).

                    ### 2. Os Quatro Fatores que Alteram a Velocidade da Reação
                    - **1. Temperatura:**
                      • Ao aumentar a temperatura, aumenta a energia cinética média das moléculas (E_c \propto T).
                      • Uma fração significativamente maior de moléculas atinge a Energia de Ativação necessária, aumentando drasticamente a frequência de colisões eficazes.
                      • *Aplicação cotidiana:* Guardamos alimentos na geladeira/congelador para diminuir a temperatura e retardar as reações químicas de decomposição e proliferação bacteriana.
                    - **2. Superfície de Contato (Para reagentes em fases heterogêneas):**
                      • Quanto mais fragmentado/moído o sólido, maior a área exposta para colisões.
                      • *Aplicação:* Um comprimido efervescente triturado dissolve-se e reage muito mais rápido que o comprimido inteiro. A serragem queima instantaneamente enquanto um tronco de madeira queima lentamente.
                    - **3. Concentração de Reagentes (e Pressão para Gases):**
                      • Maior número de partículas por unidade de volume resulta em maior probabilidade e frequência de choques entre as moléculas, acelerando a reação.
                    - **4. Ação Vital dos Catalisadores:**
                      • Substâncias que **aumentam a velocidade da reação criando uma rota alternativa com menor Energia de Ativação (E_a)**.
                      • **Três Propriedades Cruciais dos Catalisadores no ENEM:**
                        1. O catalisador **NÃO altera o Δ H** da reação (o calor liberado ou absorvido continua rigorosamente o mesmo).
                        2. O catalisador **NÃO altera o rendimento final nem a constante de equilíbrio (K_c)**, apenas faz o sistema atingir o equilíbrio em menos tempo.
                        3. O catalisador é regenerado integralmente ao final do processo, não sendo consumido.
                        4. *Exemplos práticos:* Enzimas (catalisadores biológicos proteicos) e o conversor catalítico de automóveis (platina e paládio que aceleram a oxidação de CO tóxico e NO_x em CO_2 e N_2).
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Velocidade Média da Reação", "Vm = |Δ[Reagente]| / (coef * Δt) = Δ[Produto] / (coef * Δt)", "Taxa de consumo de reagentes ou produção por unidade de tempo"),
                    FormulaOrSyntaxItem("Efeito do Catalisador", "Ea (com catalisador) < Ea (sem catalisador)", "Diminui a barreira energética sem alterar a entalpia final ΔH")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Uso de Panela de Pressão e Cinética Química",
                        codeOrFormula = "Pressão aumentada -> Ponto de Ebulição da água sobe para ~120°C -> Cozimento 3x mais rápido",
                        explanation = "Na panela de pressão fechada, a água ferve a cerca de 120°C em vez de 100°C. O aumento da temperatura faz com que as moléculas de água e dos alimentos colidam com energia cinética muito maior, acelerando exponencialmente as reações de desnaturação e amaciamento dos alimentos."
                    )
                ),
                goldTip = "Pegadinha clássica do ENEM: Dizer que o catalisador aumenta o rendimento ou muda o ΔH da reação. FALSO! O catalisador apenas diminui a Energia de Ativação e poupa tempo!"
            )

            "quim_fis_05" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Equilíbrio Químico, Princípio de Le Chatelier e Cálculo de pH

                    Em reações reversíveis (aA + bB ⇌ cC + dD), o equilíbrio químico é atingido quando **a velocidade da reação direta se iguala à velocidade da reação inversa (v_{dir} = v_{inv})**. Nesse estado, as concentrações de todos os reagentes e produtos permanecem **constantes no tempo**, tratando-se de um equilíbrio dinâmico e nunca estático.

                    ### 1. A Constante de Equilíbrio em Concentração (K_c)
                    ${"$"}K_c = \frac{[C]^c · [D]^d}{[A]^a · [B]^b}${"$"}
                    - **Regra Fundamental de Fase:** **Sólidos puros e o solvente líquido puro NÃO participam da expressão de K_c** (suas concentrações/atividades são constantes). Apenas espécies gasosas e solutos aquosos entram na fórmula.
                    - O valor de K_c é alterado **única e exclusivamente pela TEMPERATURA**. Catalisadores, pressão e concentração não modificam a constante K_c.

                    ### 2. O Princípio de Le Chatelier (Deslocamento do Equilíbrio)
                    *"Quando uma perturbação externa é imposta a um sistema em equilíbrio, o sistema desloca-se no sentido que tenda a anular ou minimizar essa perturbação."*
                    - **1. Efeito da Concentração:**
                      • Adicionar uma substância desloca o equilíbrio para o **lado oposto** (para consumi-la).
                      • Retirar uma substância desloca o equilíbrio para o **mesmo lado** da substância retirada (para repô-la).
                    - **2. Efeito da Pressão (Apenas para sistemas com gases):**
                      • Aumentar a pressão total desloca o equilíbrio para o lado com **menor volume gasoso (menor número de mols de gás)**.
                      • Diminuir a pressão desloca para o lado com **maior volume gasoso**.
                      • Se o número de mols gasosos for igual em ambos os lados (Δ n_{gas} = 0), a pressão não causa deslocamento.
                    - **3. Efeito da Temperatura:**
                      • O aumento da temperatura favorece o sentido **endotérmico** (Δ H > 0, que absorve o calor excedente).
                      • A diminuição da temperatura favorece o sentido **exotérmico** (Δ H < 0).

                    ### 3. Equilíbrio Iônico da Água, pH e pOH
                    - A autoionização da água pura a 25°C é expressa por:
                      ${"$"}H_2O_{(l)} ⇌ H^+_{(aq)} + OH^-_{(aq)} \quad \implies \quad K_w = [H^+][OH^-] = 10^{-14}${"$"}
                    - **Escala Logarítmica de pH e pOH:**
                      ${"$"}pH = -\log[H^+] \quad e \quad pOH = -\log[OH^-]${"$"}
                      ${"$"}pH + pOH = 14 \quad (a 25°C)${"$"}
                    - *Classificação da solução a 25°C:*
                      • Ácida: [H^+] > 10^{-7} M \implies pH < 7
                      • Neutra: [H^+] = [OH^-] = 10^{-7} M \implies pH = 7
                      • Básica/Alcalina: [H^+] < 10^{-7} M \implies pH > 7
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Constante Kc", "Kc = [Produtos]^coef / [Reagentes]^coef", "Apenas gases e espécies aquosas entram; sólidos e líquidos puros não entram"),
                    FormulaOrSyntaxItem("Cálculo de pH", "pH = -log[H+] | [H+] = 10^(-pH)", "Escala logarítmica: variação de 1 unidade de pH equivale a 10 vezes em concentração"),
                    FormulaOrSyntaxItem("Relação pH e pOH a 25°C", "pH + pOH = 14", "Kw = [H+] * [OH-] = 10^(-14)")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Acidificação dos Oceanos e Le Chatelier",
                        codeOrFormula = "CO2(g) + H2O(l) <=> H2CO3(aq) <=> H+(aq) + HCO3-(aq)",
                        explanation = "O aumento da queima de combustíveis fósseis eleva o CO2 atmosférico. Pelo Princípio de Le Chatelier, o excesso de CO2 dissolve-se no oceano deslocando o equilíbrio para a direita, gerando mais ácido carbônico e íons H+. O aumento de [H+] reduz o pH oceânico e dissolve o carbonato de cálcio (CaCO3) que compõe as conchas e esqueletos de corais."
                    )
                ),
                goldTip = "Atenção com a escala logarítmica: Uma chuva com pH 4 é DEZ VEZES mais ácida que uma chuva com pH 5, e CEM VEZES mais ácida que uma chuva com pH 6!"
            )

            "quim_fis_06" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Eletroquímica: Pilhas, Corrosão e Eletrólise

                    A Eletroquímica estuda a interconversão entre **energia química e energia elétrica** através de reações de transferência de elétrons (oxirredução).

                    ### 1. Conceitos de Oxirredução e Número de Oxidação (Nox)
                    - **Oxidação:** Perda de elétrons \implies Aumento do Nox. Quem sofre oxidação é o **Agente Redutor**.
                    - **Redução:** Ganho de elétrons \implies Redução do Nox. Quem sofre redução é o **Agente Oxidante**.
                    - *Macete mnemônico:* **DADOR** (Dá elétrons = Oxida = Agente Redutor) | **RECEBO** (Recebe elétrons = Reduz = Agente Oxidante).

                    ### 2. Pilhas Eletroquímicas (Processos Espontâneos: ΔE° > 0, ΔG < 0)
                    - Dispositivos que convertem energia química em corrente elétrica contínua.
                    - **A Pilha de Daniell (Zn / Cu):**
                      • **Ânodo (Polo Negativo -):** Ocorre **Oxidação** (Zn_{(s)} → Zn^{2+}_{(aq)} + 2e^-). A lâmina de zinco é corroída e perde massa; a concentração de íons na solução aumenta.
                      • **Cátodo (Polo Positivo +):** Ocorre **Redução** (Cu^{2+}_{(aq)} + 2e^- → Cu_{(s)}). Íons cobre depositam-se na lâmina metálica, aumentando sua massa.
                      • **Fluxo de Elétrons:** Sempre caminha **do Ânodo para o Cátodo** pelo fio condutor externo (do - para o +).
                      • **Ponte Salina:** Mantém o equilíbrio eletrostático permitindo o trânsito de íons entre as duas semicélulas.
                      • *Macete clássico:* **CRAO** (Cátodo = Redução | Ânodo = Oxidação).
                    - **Força Eletromotriz / Potencial Padrão (Δ E^\circ):**
                      ${"$"}Δ E^\circ = E_{redução maior}^\circ - E_{redução menor}^\circ \quad (> 0)${"$"}

                    ### 3. Proteção Contra a Corrosão e Metal de Sacrifício
                    - A ferrugem do ferro (Fe) ocorre pela oxidação por O_2 e umidade (Fe → Fe^{2+} → Fe_2O_3 · nH_2O).
                    - **Metal de Sacrifício (Ânodo de Sacrifício):** Conecta-se ao ferro um metal com **menor potencial de redução (maior tendência de se oxidar)**, como o Zinco ou Magnésio. O metal de sacrifício corrói-se preferencialmente, poupando a estrutura de aço de navios, oleodutos e pontes (processo de galvanização).

                    ### 4. Eletrólise (Processos Não Espontâneos: Forçado por Gerador)
                    - Converte energia elétrica de uma fonte externa em energia química:
                      • **Eletrólise Ígnea:** Realizada com composto iônico fundido a quente, na ausência total de água (ex: obtenção de sódio metálico Na e gás cloro Cl_2 a partir de NaCl fundido).
                      • **Eletrólise Aquosa:** Realizada com o composto dissolvido em água. Há competição de descarga entre os íons do soluto e os íons da autoionização da água (H^+ e OH^-).
                      • *Prioridade de descarga no Cátodo (Redução):* Metais nobres (Au, Ag, Cu) > H^+ > Metais alcalinos, alcalino-terrosos e alumínio.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Potencial da Pilha (ddp)", "ΔE° = E°(redução cátodo) - E°(redução ânodo)", "Pilha espontânea sempre apresenta ΔE° > 0"),
                    FormulaOrSyntaxItem("Regra CRAO", "Cátodo = Redução | Ânodo = Oxidação", "Válido universalmente tanto para pilhas quanto para eletrólise"),
                    FormulaOrSyntaxItem("Fluxo de Elétrons na Pilha", "Ânodo (-) -> Fio condutor -> Cátodo (+)", "Elétrons partem do eletrodo que oxida para o que reduz")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Cálculo da ddp da Pilha de Daniell",
                        codeOrFormula = "E°(Zn²⁺/Zn) = -0,76V | E°(Cu²⁺/Cu) = +0,34V | ΔE° = +0,34 - (-0,76) = +1,10V",
                        explanation = "O cobre tem maior potencial de redução (+0,34V), logo sofre redução no cátodo (+). O zinco tem menor potencial (-0,76V), logo oxida no ânodo (-). A diferença de potencial gerada em condições padrão é de exatamente +1,10V."
                    )
                ),
                goldTip = "O macete CRAO nunca falha: Consoante com Consoante (Cátodo/Redução) e Vogal com Vogal (Ânodo/Oxidação)! Na pilha o ânodo é negativo, na eletrólise ele é positivo."
            )

            else -> CalculusDidacticBundle(
                theoryMarkdown = "# Físico-Química ENEM\nFundamentação estequiométrica, termodinâmica e eletroquímica.",
                formulas = emptyList(),
                solvedExamples = emptyList(),
                goldTip = "Atenção permanente às unidades de medida (mol, grama, Litro, kJ e Volts) nas questões de cálculo."
            )
        }
    }

    fun buildExercises(lesson: EnemTrailLesson): List<MathExercise> {
        return when (lesson.id) {
            "quim_fis_01" -> listOf(
                MathExercise(
                    id = "quim_ex_fis_01_a",
                    title = "ENEM: Estequiometria e Reagente Limitante na Síntese da Amônia",
                    problemStatement = "O processo Haber-Bosch é responsável pela síntese industrial da amônia (NH_3), matéria-prima essencial para a fabricação de fertilizantes agrícolas que alimentam bilhões de pessoas: N2(g) + 3 H2(g) -> 2 NH3(g). Em um reator estequiométrico alimentado com 280 kg de gás nitrogênio (N_2, massa molar 28 g/mol) e 90 kg de gás hidrogênio (H_2, massa molar 2 g/mol), sabendo que a reação opera com rendimento de 80%, qual é a massa aproximada de amônia produzida?",
                    options = listOf(
                        "272 kg de amônia.",
                        "340 kg de amônia.",
                        "170 kg de amônia.",
                        "425 kg de amônia.",
                        "510 kg de amônia."
                    ),
                    correctIndex = 0,
                    hint = "Calcule o número de mols de cada reagente. Verifique a proporção 1:3. O N2 e H2 estão na proporção exata de 10.000 mols de N2 para 45.000 mols de H2? Não, então verifique o limitante!",
                    stepByStepSolution = "A alternativa A está correta. Mols de N2: 280.000 g / 28 g/mol = 10.000 mols. Mols de H2: 90.000 g / 2 g/mol = 45.000 mols. Pela proporção 1 N2 : 3 H2, 10.000 mols de N2 requerem 30.000 mols de H2. Como dispomos de 45.000 mols de H2, o N2 é o reagente limitante! A quantidade teórica de NH3 é 2 * 10.000 mols = 20.000 mols de NH3 (massa molar 17 g/mol) = 340.000 g (340 kg). Aplicando o rendimento de 80%: 340 kg * 0,80 = 272 kg de amônia."
                )
            )

            "quim_fis_02" -> listOf(
                MathExercise(
                    id = "quim_ex_fis_02_a",
                    title = "ENEM: Preparo de Soro Caseiro e Concentração Molar",
                    problemStatement = "O soro caseiro é uma solução aquosa hipotônica recomendada pela Organização Mundial da Saúde (OMS) para evitar a desidratação infantil decorrente de diarreias agudas. Para preparar 1 litro dessa solução, dissolvem-se 3,5 g de cloreto de sódio (NaCl, massa molar 58,5 g/mol) e 20 g de sacarose (C_{12}H_{22}O_{11}, massa molar 342 g/mol) em água potável fervida. A concentração em quantidade de matéria (molaridade) aproximada de íons sódio (Na^+) nessa solução hidratante é de:",
                    options = listOf(
                        "0,060 mol/L.",
                        "0,600 mol/L.",
                        "0,035 mol/L.",
                        "0,120 mol/L.",
                        "1,000 mol/L."
                    ),
                    correctIndex = 0,
                    hint = "Concentração molar M = m1 / (M1 * V). 1 mol de NaCl dissocia-se em 1 mol de Na+.",
                    stepByStepSolution = "A alternativa A está correta. Molaridade de NaCl = 3,5 g / (58,5 g/mol * 1 L) ≈ 0,0598 mol/L ≈ 0,060 mol/L. Como o NaCl é um eletrólito forte que se dissocia totalmente em água produzindo 1 íon Na+ para cada fórmula unitária dissolvida, a concentração resultante de cátions Na+ na solução é de 0,060 mol/L."
                )
            )

            "quim_fis_03" -> listOf(
                MathExercise(
                    id = "quim_ex_fis_03_a",
                    title = "ENEM: Termoquímica e Comparação Energética de Biocombustíveis",
                    problemStatement = "A busca por matrizes de menor impacto de carbono intensificou a utilização do bioetanol (C_2H_5OH, 46 g/mol) em substituição à gasolina automotiva representada pelo isoctano (C_8H_{18}, 114 g/mol). Dadas as entalpias padrão de combustão completa: C2H5OH(l) = -1368 kJ/mol e C8H18(l) = -5472 kJ/mol. Comparando a energia térmica liberada exclusivamente por unidade de massa (em kJ por grama de combustível queimado), conclui-se que:",
                    options = listOf(
                        "A gasolina libera aproximadamente 48 kJ/g, enquanto o etanol libera aproximadamente 30 kJ/g, justificando o maior consumo em volume dos veículos a etanol.",
                        "O etanol libera o dobro de energia por grama em relação à gasolina, devido à presença do oxigênio em sua molécula.",
                        "Ambos os combustíveis liberam rigorosamente a mesma energia específica por grama, pois ambos derivam do carbono.",
                        "O etanol apresenta reação de combustão endotérmica, enquanto a gasolina queima por processo exotérmico espontâneo.",
                        "A gasolina libera menos energia por grama que o etanol, exigindo maiores tanques de abastecimento."
                    ),
                    correctIndex = 0,
                    hint = "Divida o ΔH de combustão pela respectiva massa molar de cada combustível (kJ/g).",
                    stepByStepSolution = "A alternativa A está correta. Para o etanol: 1368 kJ / 46 g ≈ 29,7 kJ/g (~30 kJ/g). Para a gasolina (isoctano): 5472 kJ / 114 g ≈ 48,0 kJ/g. A gasolina fornece cerca de 60% a mais de energia por grama consumida do que o etanol, razão pela qual motores flex exigem maior vazão volumétrica de etanol para produzir a mesma potência mecânica."
                )
            )

            "quim_fis_04" -> listOf(
                MathExercise(
                    id = "quim_ex_fis_04_a",
                    title = "ENEM: Conversores Catalíticos e Energia de Ativação",
                    problemStatement = "Os conversores catalíticos instalados no escapamento dos veículos automotores utilizam metais nobres como platina (Pt), paládio (Pd) e ródio (Rh) finamente dispersos em uma cerâmica porosa para catalisar a oxidação do monóxido de carbono (CO), um gás tóxico e letal, a dióxido de carbono (CO_2): 2 CO(g) + O2(g) -> 2 CO2(g). O papel primordial do conversor catalítico nessa transformação é:",
                    options = listOf(
                        "Diminuir a energia de ativação da reação química sem alterar a variação de entalpia (ΔH) do processo.",
                        "Aumentar a entalpia dos produtos para transformar o monóxido de carbono em oxigênio respirável.",
                        "Consumir-se integralmente durante a viagem para fornecer elétrons livres aos gases residuais.",
                        "Aumentar a energia de ativação para desacelerar a emissão de gases estufa pelo cano de descarga.",
                        "Deslocar o equilíbrio químico no sentido endotérmico absorvendo calor dos gases de combustão."
                    ),
                    correctIndex = 0,
                    hint = "Catalisadores fornecem um caminho reacional alternativo com menor barreira de energia de ativação, sem alterar a entalpia final.",
                    stepByStepSolution = "A alternativa A está correta. Os catalisadores heterogêneos adsorvem as moléculas gasosas em sua superfície metálica, enfraquecendo as ligações químicas e reduzindo consideravelmente a Energia de Ativação (Ea) necessária para a ocorrência da colisão eficaz. Como consequência, a conversão ocorre em frações de segundo à temperatura do escapamento, sem qualquer modificação no ΔH e sem que o catalisador seja consumido."
                )
            )

            "quim_fis_05" -> listOf(
                MathExercise(
                    id = "quim_ex_fis_05_a",
                    title = "ENEM: Efeito da Hiperventilação no pH do Sangue Humano",
                    problemStatement = "O pH do sangue humano é estritamente tamponado em torno de 7,35 a 7,45 pelo sistema de equilíbrio ácido-base: CO2(dissolvido) + H2O(l) <=> H2CO3(aq) <=> H+(aq) + HCO3-(aq). Durante uma crise de ansiedade ou pânico com hiperventilação respiratória acelerada, o indivíduo elimina excessivamente gás carbônico (CO_2) pelos pulmões. Pelo Princípio de Le Chatelier, essa hiperventilação provoca:",
                    options = listOf(
                        "Deslocamento do equilíbrio para a esquerda, diminuindo a concentração de íons H+ e elevando o pH sanguíneo (alcalose respiratória).",
                        "Deslocamento do equilíbrio para a direita, aumentando a concentração de H+ e reduzindo o pH (acidose respiratória).",
                        "Aumento da pressão osmótica nas artérias sem qualquer alteração na concentração de íons hidrogênio.",
                        "Solidificação imediata do bicarbonato dissolvido nas hemácias devido à elevação da temperatura.",
                        "Inversão completa do pH do sangue para valores inferiores a 2,0, destruindo as proteínas plasmáticas."
                    ),
                    correctIndex = 0,
                    hint = "Se o CO2 é retirado do sistema (eliminado na respiração), o equilíbrio desloca-se para a esquerda para repor o CO2 perdido, consumindo H+.",
                    stepByStepSolution = "A alternativa A está correta. A eliminação excessiva de CO2 reduz a concentração do reagente no lado esquerdo. Em resposta, o equilíbrio químico desloca-se para a esquerda para repor o CO2. Esse deslocamento consome prótons H+ do meio sanguíneo. Com a diminuição de [H+], o pH do sangue se eleva acima de 7,45, quadro clínico conhecido como alcalose respiratória (frequentemente contornado respirando-se em um saco de papel para reinalar o CO2 expirado)."
                )
            )

            "quim_fis_06" -> listOf(
                MathExercise(
                    id = "quim_ex_fis_06_a",
                    title = "ENEM: Proteção Catódica e Metal de Sacrifício em Cascos de Navios",
                    problemStatement = "Para evitar que o casco de aço de navios e plataformas petrolíferas (composto predominantemente por ferro, Fe) seja danificado pela corrosão marinha provocada pelo oxigênio e salinidade da água do mar, placas de zinco (Zn) ou magnésio (Mg) são parafusadas diretamente à estrutura submersa. Sabendo que os potenciais de redução padrão são E°(Fe²⁺/Fe) = -0,44V, E°(Zn²⁺/Zn) = -0,76V e E°(Mg²⁺/Mg) = -2,37V, a utilização dessas placas protege o ferro porque:",
                    options = listOf(
                        "O zinco e o magnésio possuem menores potenciais de redução que o ferro, oxidando-se preferencialmente no lugar da estrutura de aço.",
                        "O zinco atua como cátodo da pilha, recebendo elétrons do ferro e impedindo o contato da água com o metal.",
                        "O ferro atua como ânodo sacrificial, desgastando-se para proteger quimicamente as placas metálicas externas.",
                        "As placas liberam íons que neutralizam a água do mar tornando o oceano perfeitamente alcalino ao redor do navio.",
                        "A diferença de potencial torna-se negativa, invertendo o sentido da gravidade sobre a ferrugem."
                    ),
                    correctIndex = 0,
                    hint = "Metal de sacrifício tem MENOR potencial de redução (ou seja, MAIOR facilidade de oxidação) e se desgasta protegendo o ferro.",
                    stepByStepSolution = "A alternativa A está correta. O zinco (-0,76V) e o magnésio (-2,37V) possuem potenciais de redução mais baixos do que o ferro (-0,44V), o que significa que possuem maior potencial de oxidação (maior tendência eletroquímica a doar elétrons). Em contato elétrico com o casco, o magnésio ou zinco atua como ânodo de sacrifício, sofrendo corrosão eletroquímica e mantendo o ferro na forma metálica reduzida."
                )
            )

            else -> emptyList()
        }
    }
}
