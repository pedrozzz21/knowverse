package com.example.data.chemistry

import com.example.data.math.CalculusDidacticBundle
import com.example.data.model.EnemTrailLesson
import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample
import com.example.data.model.MathExercise

/**
 * Conteúdo didático completo para o Módulo 1: Química Geral e Estrutura da Matéria (ENEM).
 * Aulas:
 * 1. quim_ger_01: Substâncias e Misturas
 * 2. quim_ger_02: Modelos Atômicos e Estrutura
 * 3. quim_ger_03: Tabela Periódica e Propriedades
 * 4. quim_ger_04: Ligações Químicas e Geometria
 * 5. quim_ger_05: Polaridade e Forças Intermoleculares
 */
object ChemistryGeneralDidactic {

    fun buildBundle(lesson: EnemTrailLesson): CalculusDidacticBundle {
        return when (lesson.id) {
            "quim_ger_01" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Substâncias, Misturas e Processos de Fracionamento no ENEM

                    No ENEM, a distinção entre substâncias puras e misturas é constantemente articulada a **gráficos de mudança de fase** e a **desafios práticos ambientais e industriais** (como o tratamento de efluentes, vazamentos de petróleo e mineração).

                    ### 1. Classificação da Matéria
                    - **Substância Pura:** Formada por unidades idênticas com composição química fixa e propriedades constantes:
                      • *Simples:* Formada por átomos de um único elemento químico (ex: O_2, O_3, N_2, Fe, C_{grafite}). Alotropia é a capacidade de um mesmo elemento formar substâncias simples distintas (O_2 vs O_3; Diamante vs Grafite vs Grafeno).
                      • *Composta:* Formada por átomos de elementos químicos distintos em proporções atômicas definidas (ex: H_2O, CO_2, NaCl).
                    - **Mistura:** Junção física de duas ou mais substâncias sem reação química, sem proporção fixa:
                      • *Homogênea (Solução):* Monofásica a olho nu e ao microscópio óptico (ex: ar atmosférico filtrado, soro fisiológico, etanol comercial 96%, ligas metálicas como o bronze e aço).
                      • *Heterogênea:* Polifásica (duas ou mais fases com propriedades distintas, ex: água + óleo, granito, sangue, leite ao microscópio).

                    ### 2. Gráficos de Mudança de Estado Físico
                    - **Substância Pura:** Apresenta **dois patamares perfeitamente constantes**: temperatura constante durante a fusão (PF) e temperatura constante durante a ebulição (PE).
                    - **Mistura Comum:** A temperatura varia tanto durante a fusão quanto durante a ebulição (sem patamares fixos).
                    - **Mistura Eutética:** Comporta-se como substância pura na fusão (**PF constante**), mas varia na ebulição (ex: solda metálica: 63% estanho + 37% chumbo, que funde a 183°C).
                    - **Mistura Azeotrópica:** Varia na fusão, mas comporta-se como substância pura na ebulição (**PE constante**). **Atenção ENEM:** O álcool comercial 96°GL (96% etanol + 4% água) é um azeótropo que ferve a 78,1°C, não podendo ser separado por destilação fracionada comum.

                    ### 3. Métodos Fundamentais de Separação de Misturas (Cobrança Clássica)
                    - **Misturas Heterogêneas:**
                      • *Filtração:* Separação sólido-líquido ou sólido-gás (filtro retém a fase sólida; ex: aspirador de pó, café, tratamento de água).
                      • *Decantação e Sedimentação:* Baseada na diferença de densidade com ação da gravidade. Em líquidos imiscíveis (água + óleo), utiliza-se o **Funil de Bromo (ou de decantação)**.
                      • *Flotação:* Adição de líquido com densidade intermediária ou injeção de microbolhas de ar que arrastam partículas insolúveis para a superfície (etapa crucial da mineração e da Estação de Tratamento de Esgoto).
                      • *Levigação:* Separação de sólidos de densidades distintas por corrente de água (ex: garimpo com bateia, separando areia do ouro).
                      • *Separação Magnética:* Separa sólidos ferromagnéticos (ferro, níquel, cobalto) por atração magnética.
                    - **Misturas Homogêneas:**
                      • *Destilação Simples:* Separação entre sólido dissolvido e líquido com pontos de ebulição muito distantes (ex: obtenção de água pura a partir de água do mar salgada).
                      • *Destilação Fracionada:* Separa líquidos miscíveis com pontos de ebulição próximos através de coluna de fracionamento (ex: refino de petróleo e separação dos componentes do ar liquefeito: N_2, O_2, Ar).
                      • *Cromatografia:* Separação baseada nas diferentes taxas de migração e interações de afinidade entre uma fase móvel e uma fase estacionária (ex: identificação de pigmentos e perícia forense).
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Ponto de Fusão (PF)", "T = constante (Pura ou Eutética)", "Mudança do estado sólido para líquido a pressão constante"),
                    FormulaOrSyntaxItem("Ponto de Ebulição (PE)", "T = constante (Pura ou Azeotrópica)", "Mudança do estado líquido para gasoso com pressão de vapor igual à pressão atmosférica"),
                    FormulaOrSyntaxItem("Densidade de Misturas", "d = m_total / V_total", "Grandeza intensiva que governa a decantação e flotação de fases")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Separação de Resíduos de Vazamento de Petróleo no Mar",
                        codeOrFormula = "Fase 1: Barreiras de contenção -> Fase 2: Skimmers (Decantação/Aspiração) -> Fase 3: Adsorventes",
                        explanation = "O petróleo é uma mistura apolar hidrofóbica com densidade média de ~0,85 g/cm³, sendo menos denso que a água do mar (~1,03 g/cm³). Por ser insolúvel e flutuar, a separação mecânica primária baseia-se na decantação e aspiração das manchas superficiais."
                    )
                ),
                goldTip = "Regra de Ouro ENEM: Se um gráfico mostrar ponto de fusão constante mas ebulição variável, a mistura é EUTÉTICA. Se a fusão for variável e a ebulição constante, é AZEOTRÓPICA!"
            )

            "quim_ger_02" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Modelos Atômicos e Distribuição Eletrônica

                    A evolução dos modelos atômicos no ENEM é cobrada sob a ótica da **história da ciência**: cada modelo surgiu para explicar experimentos e fenômenos que o modelo anterior não conseguia justificar.

                    ### 1. A Evolução Conceitual dos Modelos
                    - **1. John Dalton (1808) — A Esfera Maciça ("Bola de Bilhar"):**
                      • Átomo como partícula esférica, maciça, indivisível e indestrutível.
                      • Elementos químicos são definidos pela sua massa atômica. Átomos se recombinam em proporções fixas de números inteiros para formar compostos.
                      • *Limitação:* Não explicava a natureza elétrica da matéria nem a existência de cargas.
                    - **2. J.J. Thomson (1897) — O Átomo Divisível ("Pudim de Passas"):**
                      • Experimento dos **raios catódicos** em ampolas sob vácuo submetidas a alta voltagem: feixe desviado por campos elétricos e magnéticos em direção ao polo positivo.
                      • **Descoberta do elétron:** Primeira partícula subatômica descoberta. Átomo é uma esfera homogênea de carga positiva com elétrons incrustados, resultando em neutralidade elétrica global.
                      • *Limitação:* Não explicava a emissão de radiação alfa nem a concentração de massa.
                    - **3. Ernest Rutherford (1911) — O Modelo Planetário:**
                      • **Experimento da lâmina de ouro:** Bombardeamento de finíssima folha de ouro com partículas alfa (α, núcleos de hélio positivos). A imensa maioria atravessou sem desvio, mas algumas sofreram grandes desvios e raríssimas ricochetearam.
                      • *Conclusão Revolucionária:* O átomo é um imenso vazio. A massa e a carga positiva estão concentradas em uma região central minúscula e extremamente densa (**o núcleo**), enquanto os elétrons orbitam na **eletrosfera**.
                    - **4. Niels Bohr (1913) — Níveis de Energia Quantizados:**
                      • Baseado na física quântica de Planck para corrigir a instabilidade clássica de Rutherford (onde o elétron em órbita perderia energia espiralando até colidir com o núcleo).
                      • Os elétrons giram em órbitas circulares estáveis denominadas **camadas estacionárias quantizadas** (K, L, M, N, O, P, Q).
                      • **Salto Quântico (Aplicações Diretas no ENEM):**
                        1. O elétron absorve um pacote de energia (fóton) e salta para um nível mais externo (estado excitado).
                        2. Ao retornar ao seu nível fundamental ou intermediário mais interno, **emite um fóton de luz visível ou UV com energia exatamente igual à diferença entre os níveis (Δ E = h · f)**.
                        3. *Aplicações cotidianas:* Funcionamento de fogos de artifício (cores específicas para cada metal: estrôncio = vermelho, bário = verde, sódio = amarelo), lâmpadas fluorescentes, LEDs e o teste de chama.

                    ### 2. Distribuição Eletrônica e Diagrama de Linus Pauling
                    - Os elétrons ocupam subníveis em ordem crescente de energia:
                      ${"$"}Ordem:  1s^2 → 2s^2 → 2p^6 → 3s^2 → 3p^6 → 4s^2 → 3d^{10} → 4p^6 → 5s^2 → 4d^{10} → 5p^6 → 6s^2 \dots${"$"}
                    - **Camada de Valência:** O nível mais externo do átomo (maior número principal n).
                    - **Subnível mais energético:** O último subnível preenchido na sequência do diagrama (que nem sempre coincide com a camada de valência; ex: no Ferro Fe (Z=26), a camada de valência é 4s^2, mas o subnível mais energético é 3d^6).
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Energia do Fóton (Bohr)", "ΔE = h * f = (h * c) / λ", "h = constante de Planck, f = frequência, λ = comprimento de onda"),
                    FormulaOrSyntaxItem("Número de Massa (A)", "A = Z + n", "Z = número de prótons (número atômico), n = número de nêutrons"),
                    FormulaOrSyntaxItem("Subníveis de Energia", "s (máx 2e-), p (máx 6e-), d (máx 10e-), f (máx 14e-)", "Capacidade máxima de elétrons em cada orbital")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Fogos de Artifício e Teste de Chama no ENEM",
                        codeOrFormula = "Elétron excitado -> Retorno ao estado fundamental -> Emissão de fóton luminoso (cor característica)",
                        explanation = "A coloração amarela brilhante emitida por sais de sódio (Na^+) e a cor vermelha de sais de estrôncio (Sr^{2+}) ocorrem exclusivamente na etapa de retorno dos elétrons aos níveis menos energéticos, liberando radiação eletromagnética com comprimento de onda no espectro visível."
                    )
                ),
                goldTip = "Cuidado com pegadinhas: O elétron ABSORVE energia para se afastar do núcleo (estado excitado) e EMITE luz ao RETORNAR para o nível fundamental!"
            )

            "quim_ger_03" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Tabela Periódica Moderna e Propriedades Periódicas

                    A Tabela Periódica moderna é organizada em ordem crescente de **número atômico (Z)** (Lei de Moseley), reunindo elementos com comportamentos químicos semelhantes em colunas verticais chamadas **famílias ou grupos**.

                    ### 1. Estrutura da Tabela Periódica
                    - **Períodos (7 Linhas Horizontais):** Indicam o número de **camadas eletrônicas** que o átomo possui no estado fundamental (ex: elementos do 3º período têm elétrons distribuídos até a camada M, n=3).
                    - **Grupos ou Famílias (18 Colunas Verticais):** Elementos do mesmo grupo possuem o mesmo número de elétrons na camada de valência e, por isso, apresentam propriedades químicas análogas:
                      • Grupo 1 (Metais Alcalinos: Li, Na, K, Rb, Cs, Fr): ns^1, altíssima reatividade com água, formam cátions monovalentes (M^+).
                      • Grupo 2 (Metais Alcalino-Terrosos: Be, Mg, Ca, Sr, Ba, Ra): ns^2, formam cátions bivalentes (M^{2+}).
                      • Grupo 16 (Calcogênios: O, S, Se, Te, Po): ns^2 np^4, tendem a receber 2 elétrons (X^{2-}).
                      • Grupo 17 (Halogênios: F, Cl, Br, I, At): ns^2 np^5, os mais eletronegativos, formam ânions monovalentes (X^-).
                      • Grupo 18 (Gases Nobres: He, Ne, Ar, Kr, Xe, Rn): ns^2 np^6 (exceto He 1s^2), camada de valência completa, extrema estabilidade química e baixa reatividade.

                    ### 2. Principais Propriedades Periódicas no ENEM
                    - **1. Raio Atômico:** Distância do núcleo ao elétron mais externo:
                      • *Nos períodos:* Cresce da direita para a esquerda (menor número de prótons resulta em menor atração nuclear sobre os elétrons da mesma camada, permitindo que a nuvem eletrônica se expanda).
                      • *Nas famílias:* Cresce de cima para baixo (aumento do número de camadas eletrônicas).
                      • *Raio Iônico:* Cátion (M^+) é sempre menor que o átomo neutro correspondente (perda de elétrons reduz a repulsão eletrônica); Ânion (X^-) é sempre maior que o átomo neutro (ganho de elétrons aumenta a repulsão na camada de valência).
                    - **2. Energia de Ionização (EI):** Energia mínima necessária para remover um elétron de um átomo neutro no estado gasoso:
                      ${"$"}X_{(g)} + EI → X^+_{(g)} + e^-${"$"}
                      • Cresce de baixo para cima e da esquerda para a direita (átomos menores seguram seus elétrons com maior força nuclear). A segunda energia de ionização (EI_2) é sempre estritamente maior que a primeira (EI_1).
                    - **3. Eletronegatividade:** Capacidade de um átomo de atrair o par de elétrons compartilhado em uma ligação química covalente:
                      • Cresce de baixo para cima e da esquerda para a direita (excluindo os gases nobres).
                      • **Fila decrescente de eletronegatividade (Memorizar para o ENEM):**
                        ${"$"}F > O > N > Cl > Br > I > S > C > P > H${"$"}
                        *(Macete clássico: "Fui Ontem No Clube, Briguei I Saí Correndo Para o Hospital")*
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Fila de Eletronegatividade", "F > O > N > Cl > Br > I > S > C > P > H", "Ordem decrescente de atração por elétrons compartilhados"),
                    FormulaOrSyntaxItem("Crescimento do Raio Atômico", "← e ↓", "Aumenta para a esquerda e para baixo na tabela periódica"),
                    FormulaOrSyntaxItem("Crescimento da Eletronegatividade e EI", "→ e ↑", "Aumenta para a direita e para cima na tabela periódica")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Comparação de Raio entre Espécies Isoletrônicas",
                        codeOrFormula = "Espécies com 10 elétrons: O²⁻ (Z=8) vs F⁻ (Z=9) vs Na⁺ (Z=11) vs Mg²⁺ (Z=12)",
                        explanation = "Quanto maior o número de prótons (Z) no núcleo atraindo a mesma quantidade de elétrons, mais compacta fica a eletrosfera. Portanto: Raio do O²⁻ > F⁻ > Na⁺ > Mg²⁺. O íon Mg²⁺ é o menor porque seus 12 prótons exercem atração nuclear mais intensa sobre os 10 elétrons."
                    )
                ),
                goldTip = "O Flúor (F) é o elemento mais eletronegativo e o Frâncio (Fr) é o de maior raio atômico e menor eletronegatividade da tabela periódica!"
            )

            "quim_ger_04" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Ligações Químicas e Geometria Molecular

                    Os átomos estabelecem ligações químicas buscando atingir menor energia potencial e maior estabilidade, frequentemente associada à configuração eletrônica de gás nobre (**Regra do Octeto**: 8 elétrons na camada de valência, ou 2 no caso do hélio).

                    ### 1. Tipos de Ligações Químicas
                    - **1. Ligação Iônica (Metal + Ametal / Metal + Hidrogênio):**
                      • Diferença de eletronegatividade muito acentuada (Δ E > 1,7).
                      • Ocorre **transferência definitiva de elétrons**: o metal doa elétrons formando cátion e o ametal recebe elétrons formando ânion.
                      • Não forma moléculas isoladas, mas sim um **retículo cristalino tridimensional** mantido por atração eletrostática intensa.
                      • *Propriedades:* Elevados pontos de fusão e ebulição, sólidos duros e quebradiços a 25°C; **conduzem corrente elétrica somente quando fundidos (líquidos) ou dissolvidos em água** (íons livres em movimento), nunca no estado sólido.
                    - **2. Ligação Covalente (Ametal + Ametal / Ametal + Hidrogênio):**
                      • Pequena ou nula diferença de eletronegatividade.
                      • Ocorre **compartilhamento de pares de elétrons**. Pode ser simples (1 par, ligação \sigma), dupla (2 pares, 1\sigma + 1\pi) ou tripla (3 pares, 1\sigma + 2\pi).
                      • Forma moléculas discretas com pontos de fusão e ebulição moderados a baixos. Em geral, são isolantes elétricos em qualquer estado (com exceção de ácidos em meio aquoso, que se ionizam).
                    - **3. Ligação Metálica (Metal + Metal):**
                      • Átomos metálicos com baixa energia de ionização perdem seus elétrons de valência para o retículo.
                      • **Teoria do Mar de Elétrons (ou Gás Eletrônico):** Cátions metálicos fixos envolvidos por uma nuvem de elétrons livres e deslocalizados.
                      • *Propriedades:* Excelente condutividade térmica e elétrica no estado sólido e líquido, maleabilidade (capacidade de formar lâminas), ductilidade (capacidade de formar fios) e brilho metálico.

                    ### 2. Teoria da Repulsão dos Pares Eletrônicos (VSEPR) e Geometrias
                    Os pares de elétrons ao redor do átomo central (ligantes e não ligantes) se repelem mutuamente e se posicionam no espaço com a maior distância angular possível:
                    - **Linear (Ângulo de 180°):**
                      • 2 nuvens eletrônicas ao redor do átomo central (ex: BeCl_2, CO_2 → O=C=O).
                    - **Trigonal Plana (Ângulo de 120°):**
                      • 3 nuvens eletrônicas e 3 ligantes, sem sobra de par isolado (ex: BF_3, SO_3, formol H_2C=O).
                    - **Angular (Ângulo < 120° ou ~104,5°):**
                      • 3 nuvens com 1 par de elétrons isolado (ex: SO_2, ângulo de 119°).
                      • 4 nuvens com 2 pares de elétrons isolados (ex: água H_2O, ângulo de 104,5° devido à forte repulsão dos elétrons não ligantes do oxigênio).
                    - **Piramidal Trigonal (Ângulo de ~107°):**
                      • 4 nuvens eletrônicas com 3 átomos ligantes e 1 par isolado (ex: amônia NH_3).
                    - **Tetraédrica (Ângulo de 109,5°):**
                      • 4 nuvens eletrônicas com 4 ligantes e nenhum par isolado (ex: metano CH_4, CCl_4, íon amônio NH_4^+).
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Geometria da Água (H2O)", "Angular (104,5°)", "2 ligações simples O-H e 2 pares de elétrons livres no oxigênio"),
                    FormulaOrSyntaxItem("Geometria do Dióxido de Carbono (CO2)", "Linear (180°)", "2 duplas ligações C=O sem elétrons livres no carbono central"),
                    FormulaOrSyntaxItem("Geometria do Metano (CH4)", "Tetraédrica (109,5°)", "4 ligações simples C-H sem elétrons livres no carbono")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Condutividade Elétrica de Compostos Iônicos e Moleculares",
                        codeOrFormula = "NaCl (sólido) -> Não conduz | NaCl (aquoso) -> Conduz | Glicose C6H12O6 (aquosa) -> Não conduz",
                        explanation = "Para haver condução elétrica em soluções, é indispensável a presença de espécies com carga elétrica dotadas de mobilidade (íons livres). O cloreto de sódio em água dissocia-se em Na⁺ e Cl⁻ móveis. Já a sacarose e glicose são moleculares: dissolvem-se sem ionização, mantendo a solução isolante."
                    )
                ),
                goldTip = "Decore para o ENEM: O CO2 e a H2O possuem 3 átomos, mas o CO2 é LINEAR (carbono não tem par livre) e a H2O é ANGULAR (oxigênio tem 2 pares livres que repelem as ligações)!"
            )

            "quim_ger_05" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Polaridade e Forças Intermoleculares: Solubilidade e Ponto de Ebulição

                    As forças intermoleculares são interações eletrostáticas que unem moléculas vizinhas. Elas são mais fracas que as ligações intramoleculares (covalentes e iônicas), mas são **as responsáveis diretas pelas propriedades físicas macroscópicas**, como o estado físico, a solubilidade e as temperaturas de fusão e ebulição.

                    ### 1. Polaridade das Moléculas
                    - Uma molécula é **polar** quando os vetores momento dipolar das ligações (\vec{\mu}) não se anulam por simetria espacial, resultando em um momento dipolo elétrico resultante diferente de zero (\vec{\mu}_R \neq 0).
                    - Uma molécula é **apolar** quando os momentos dipolares se anulam por simetria (\vec{\mu}_R = 0) ou quando é formada por átomos de mesma eletronegatividade (como O_2, N_2).
                    - *Exemplos fundamentais:*
                      • CO_2: Ligações C=O polares, mas geometria linear simétrica → \vec{\mu}_R = 0 (Apolar).
                      • H_2O: Ligações O-H polares e geometria angular assimétrica → \vec{\mu}_R \neq 0 (Altamente Polar).
                      • CH_4 e CCl_4: Geometria tetraédrica simétrica → \vec{\mu}_R = 0 (Apolar).

                    ### 2. Tipos de Forças Intermoleculares (Em Ordem Crescente de Intensidade)
                    - **1. Forças de London / Dipolo Induzido-Dipolo Induzido:**
                      • Presentes em **todas as moléculas**, mas são as **únicas forças que operam entre moléculas apolares** (ex: O_2, N_2, CO_2, hidrocarbonetos do petróleo e ceras).
                      • Flutuações instantâneas na nuvem eletrônica criam dipolos temporários efêmeros que induzem dipolos nos vizinhos.
                      • São forças relativamente fracas. Sua intensidade aumenta conforme **aumenta a massa molar e a superfície de contato** da molécula.
                    - **2. Dipolo Permanente-Dipolo Permanente (Dipolo-Dipolo):**
                      • Ocorre entre **moléculas polares** que não possuem hidrogênio ligado a F, O, N (ex: HCl, H_2S, cetonas como a acetona, aldeídos).
                      • O polo negativo de uma molécula atrai eletrostaticamente o polo positivo da molécula vizinha de forma duradoura.
                    - **3. Ligações de Hidrogênio (Pontes de Hidrogênio):**
                      • Interação dipolo-dipolo excepcionalmente forte que ocorre quando o **hidrogênio está covalentemente ligado aos três átomos mais eletronegativos e de pequeno raio atômico: Flúor (F), Oxigênio (O) ou Nitrogênio (N)**.
                      • O hidrogênio fica fortemente empobrecido de elétrons (\delta^+) e interage intensamente com o par de elétrons isolado do F, O, N vizinho.
                      • *Exemplos vitais no ENEM:* Água (H_2O), etanol (CH_3CH_2OH), ácido acético, proteínas (estrutura secundária alfa-hélice) e pareamento das bases nitrogenadas do DNA (A=T e C≡G).

                    ### 3. Consequências Práticas no ENEM
                    - **Ponto de Ebulição:**
                      ${"$"}Ligações de Hidrogênio > Dipolo-Dipolo > Dipolo Induzido (a massas comparáveis)${"$"}
                      • Entre moléculas com a mesma força intermolecular, tem maior ponto de ebulição aquela com **maior massa molar** ou **menor número de ramificações** (maior área superficial de contato).
                    - **Solubilidade e a Regra do "Semelhante Dissolve Semelhante":**
                      • Substâncias polares tendem a se dissolver em solventes polares (ex: água dissolve etanol, sacarose e sais).
                      • Substâncias apolares tendem a se dissolver em solventes apolares (ex: gasolina dissolve graxa e óleo vegetal).
                      • Água e óleo não se misturam porque as interações entre as moléculas de água (ligações de hidrogênio) são muito mais fortes do que as fracas atrações que a água conseguiria estabelecer com as moléculas apolares do óleo.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Condição da Ligação de Hidrogênio", "H ligado diretamente a F, O ou N", "Interação intermolecular mais forte entre moléculas neutras"),
                    FormulaOrSyntaxItem("Regra de Solubilidade", "Semelhante dissolve semelhante", "Polar dissolve polar; Apolar dissolve apolar"),
                    FormulaOrSyntaxItem("Fatores de Ponto de Ebulição", "Força Intermolecular > Massa Molar > Menos Ramificações", "Ordem de análise para comparar volatilidade de compostos")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Ponto de Ebulição: Etanol vs Dimetil-Éter",
                        codeOrFormula = "Etanol (CH3CH2OH, PE = 78°C) vs Éter Metílico (CH3OCH3, PE = -24°C)",
                        explanation = "Ambos possuem a mesma fórmula molecular (C2H6O) e mesma massa molar (46 g/mol). No entanto, o etanol possui hidrogênio ligado diretamente ao oxigênio (grupo -OH), estabelecendo fortes ligações de hidrogênio intermoleculares, enquanto o éter realiza apenas dipolo-dipolo moderado, sendo gás a 25°C."
                    )
                ),
                goldTip = "Cuidado com o 'H ligado a FON': Na acetona (CH3-CO-CH3), o oxigênio está ligado ao carbono, não ao hidrogênio! Logo, a acetona pura NÃO faz ligação de hidrogênio entre si, apenas dipolo-dipolo!"
            )

            else -> CalculusDidacticBundle(
                theoryMarkdown = "# Química Geral e Estrutura da Matéria\nFundamentação dos fenômenos atômicos e moleculares no ENEM.",
                formulas = emptyList(),
                solvedExamples = emptyList(),
                goldTip = "Relacione sempre as propriedades microscópicas (elétrons e ligações) às propriedades macroscópicas observáveis."
            )
        }
    }

    fun buildExercises(lesson: EnemTrailLesson): List<MathExercise> {
        return when (lesson.id) {
            "quim_ger_01" -> listOf(
                MathExercise(
                    id = "quim_ex_ger_01_a",
                    title = "ENEM: Processos de Separação em Desastres Ambientais",
                    problemStatement = "Em um grave acidente ecológico, ocorreu o rompimento de uma barragem de rejeitos de mineração, liberando uma lama espessa composta por água, argila suspensa finamente dividida e óxidos de ferro insolúveis em um rio próximo. Para clarificar a água em larga escala em uma Estação de Tratamento de Água (ETA) emergencial, quais processos sequenciais de separação física são empregados?",
                    options = listOf(
                        "Coagulação/floculação seguida de decantação dos flocos e posterior filtração em leito de areia.",
                        "Destilação fracionada com aquecimento contínuo até a vaporização total da água.",
                        "Centrifugação térmica a vácuo com extração por solventes orgânicos apolares.",
                        "Cromatografia líquida de alta eficiência seguida de cristalização fracionada.",
                        "Levigação hidráulica com bateias manuais para decantar a argila dissolvida."
                    ),
                    correctIndex = 0,
                    hint = "Rejeitos insolúveis finos precisam ser aglomerados por coagulação para formarem flocos densos que decantam sob ação da gravidade antes da retenção por filtros.",
                    stepByStepSolution = "A alternativa A está correta. A argila e os óxidos de ferro formam suspensões coloidais que não sedimentam espontaneamente em tempo hábil. A adição de coagulantes (como sulfato de alumínio) neutraliza as cargas das partículas, promovendo a floculação. Os flocos maiores e mais densos depositam-se no fundo dos tanques de decantação, e as partículas residuais menores são retidas na filtração em camadas de cascalho, areia e carvão antracito."
                ),
                MathExercise(
                    id = "quim_ex_ger_01_b",
                    title = "ENEM: Análise de Curvas de Aquecimento",
                    problemStatement = "Em um laboratório de controle de qualidade, uma amostra desconhecida foi aquecida a pressão constante de 1 atm. Observou-se que durante a fusão a temperatura variou continuamente entre 45°C e 58°C, porém, durante a ebulição, a temperatura permaneceu rigorosamente constante em 118°C até a completa vaporização do sistema. Com base nesse perfil térmico, a amostra analisada é classificada como:",
                    options = listOf(
                        "Uma mistura azeotrópica.",
                        "Uma substância pura composta.",
                        "Uma mistura eutética.",
                        "Uma substância pura simples alotrópica.",
                        "Uma mistura heterogênea coloidal."
                    ),
                    correctIndex = 0,
                    hint = "Temperatura de fusão variável + temperatura de ebulição constante = comportamento característico de mistura azeotrópica.",
                    stepByStepSolution = "A alternativa A está correta. As misturas azeotrópicas comportam-se como misturas comuns durante a fusão (apresentando intervalo variável de temperatura), mas comportam-se como substâncias puras durante a ebulição, mantendo patamar de temperatura rigorosamente constante."
                )
            )

            "quim_ger_02" -> listOf(
                MathExercise(
                    id = "quim_ex_ger_02_a",
                    title = "ENEM: Teste de Chama e Emissão de Fótons",
                    problemStatement = "O teste de chama é uma técnica analítica clássica utilizada para identificar cátions metálicos em amostras. Ao introduzir um fio de platina embebido em cloreto de bário (BaCl_2) na chama do bico de Bunsen, observa-se uma intensa coloração verde brilhante. De acordo com o modelo atômico de Bohr, a emissão dessa radiação luminosa ocorre quando:",
                    options = listOf(
                        "Elétrons previamente excitados pelo calor retornam para níveis de menor energia, emitindo fótons com frequências bem definidas.",
                        "Prótons do núcleo atômico absorvem energia térmica e saltam para a eletrosfera desocupada.",
                        "O átomo de bário se fragmenta em partículas subatômicas após colisão destrutiva com a chama.",
                        "Os elétrons de valência absorvem radiação ultravioleta e se convertem instantaneamente em nêutrons.",
                        "O núcleo atômico decai por fissão espontânea liberando partículas gama no espectro visível."
                    ),
                    correctIndex = 0,
                    hint = "A luz é emitida no RETORNO do elétron excitado para órbitas de menor energia, liberando a diferença energética em forma de fóton.",
                    stepByStepSolution = "A alternativa A está correta. Pelo modelo de Bohr, a energia térmica da chama promove elétrons para níveis mais externos e instáveis (estado excitado). Ao retornar aos níveis originais mais internos, o elétron devolve a energia absorvida na forma de fótons eletromagnéticos, cujo comprimento de onda específico no espectro visível confere a cor verde característica ao bário."
                )
            )

            "quim_ger_03" -> listOf(
                MathExercise(
                    id = "quim_ex_ger_03_a",
                    title = "ENEM: Tendências Periódicas e Eletronegatividade",
                    problemStatement = "O gás cloro (Cl_2) e o oxigênio (O_2) são amplamente utilizados em processos industriais de desinfecção e alvejamento devido ao seu elevado poder oxidante, que está intimamente relacionado à facilidade com que seus átomos atraem e capturam elétrons de outras espécies químicas. Na tabela periódica, essa propriedade periódica e a tendência geral de seu crescimento correspondem a:",
                    options = listOf(
                        "Eletronegatividade, que cresce da esquerda para a direita ao longo dos períodos e de baixo para cima nas famílias.",
                        "Raio atômico, que cresce da direita para a esquerda nos períodos e de cima para baixo nas famílias.",
                        "Eletropositividade, que cresce nos metais alcalinos localizados no canto inferior esquerdo da tabela.",
                        "Massa atômica relativa, que decresce uniformemente com o aumento do número de nêutrons.",
                        "Energia de ionização, que decresce conforme o raio atômico diminui em direção aos gases nobres."
                    ),
                    correctIndex = 0,
                    hint = "A capacidade de atrair elétrons em ligações é a eletronegatividade, maior quanto menor o raio atômico e maior a carga nuclear efetiva (canto superior direito).",
                    stepByStepSolution = "A alternativa A está correta. A eletronegatividade é a medida da força de atração exercida por um átomo sobre elétrons em uma ligação. Ela aumenta em direção ao canto superior direito da tabela periódica (maior atração nuclear por camadas mais próximas do núcleo), sendo o flúor, o oxigênio, o nitrogênio e o cloro os elementos mais eletronegativos existentes."
                )
            )

            "quim_ger_04" -> listOf(
                MathExercise(
                    id = "quim_ex_ger_04_a",
                    title = "ENEM: Geometria Molecular e Efeito Estufa",
                    problemStatement = "O gás carbônico (CO_2) e a água (H_2O) são substâncias triatômicas fundamentais no balanço térmico da Terra. No entanto, enquanto a molécula de dióxido de carbono é apolar com ângulo de ligação de 180°, a molécula de água é altamente polar e exibe ângulo de ligação de aproximadamente 104,5°. Essa disparidade geométrica é explicada pelo fato de que:",
                    options = listOf(
                        "O átomo central de oxigênio na água possui dois pares de elétrons não ligantes que repelem as ligações, enquanto o carbono no CO_2 não possui elétrons livres.",
                        "A água estabelece ligações triplas entre seus átomos, enquanto o carbono realiza apenas ligações simples coordenadas.",
                        "O átomo de carbono possui eletronegatividade superior à do oxigênio, cancelando as cargas por repulsão iônica.",
                        "A molécula de CO_2 é formada exclusivamente por ligações iônicas que forçam o arranjo rígido plano.",
                        "O oxigênio na água sofreu hibridização do tipo sp linear, enquanto o carbono no CO_2 é sp³ tetraédrico."
                    ),
                    correctIndex = 0,
                    hint = "Teoria VSEPR: pares eletrônicos isolados no átomo central exercem repulsão angular sobre os pares ligantes, curvando a molécula.",
                    stepByStepSolution = "A alternativa A está correta. No CO2, o carbono central forma duas duplas ligações (O=C=O) e não tem elétrons de valência sobrando, adotando geometria linear (180°) com momentos de dipolo que se anulam mutuamente. Na água (H2O), o oxigênio tem 4 nuvens eletrônicas (2 ligações simples e 2 pares de elétrons livres), que se repelem e forçam a geometria angular (~104,5°), tornando a molécula polar."
                )
            )

            "quim_ger_05" -> listOf(
                MathExercise(
                    id = "quim_ex_ger_05_a",
                    title = "ENEM: Forças Intermoleculares e Volatilidade de Combustíveis",
                    problemStatement = "O metano (CH_4, massa molar 16 g/mol) é o principal componente do gás natural veicular (GNV) e apresenta ponto de ebulição extremamente baixo (-161°C), sendo gás em condições ambientais. Por outro lado, a água (H_2O, massa molar 18 g/mol), com massa molar muito próxima à do metano, é líquida e ferve apenas a 100°C. Essa enorme diferença nas temperaturas de ebulição deve-se prioritariamente:",
                    options = listOf(
                        "Às fortes ligações de hidrogênio estabelecidas entre as moléculas de água, contrastando com as fracas forças de dispersão de London no metano apolar.",
                        "Ao fato de a ligação C-H no metano ser infinitamente mais fraca que a ligação iônica que une os átomos na água.",
                        "À presença de ligações metálicas deslocalizadas na estrutura cristalina da água líquida.",
                        "Às forças dipolo-dipolo que atuam no metano devido à sua estrutura tetraédrica assimétrica.",
                        "À maior facilidade do metano em se ionizar espontaneamente na atmosfera sob temperatura ambiente."
                    ),
                    correctIndex = 0,
                    hint = "Moléculas de massas parecidas: a água possui H ligado a O (ligação de hidrogênio muito forte), enquanto o metano é um hidrocarboneto apolar (dipolo induzido fraco).",
                    stepByStepSolution = "A alternativa A está correta. A água é uma substância polar que estabelece ligações de hidrogênio intermoleculares intensas entre o hidrogênio eletropositivo e o oxigênio eletronegativo de moléculas vizinhas, exigindo grande quantidade de energia térmica para passar ao estado gasoso. O metano é uma molécula apolar que interage unicamente por fracas forças de dispersão de London (dipolo induzido), exigindo pouca energia para sua vaporização."
                )
            )

            else -> emptyList()
        }
    }
}
