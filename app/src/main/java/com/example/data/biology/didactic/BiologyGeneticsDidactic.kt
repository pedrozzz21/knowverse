package com.example.data.biology.didactic

import com.example.data.math.CalculusDidacticBundle
import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample

/**
 * Conteúdo didático aprofundado de Biologia ENEM — Módulo 2: Genética e Biotecnologia (Aulas bio_gen_01 a bio_gen_05).
 * Estrutura em 12 seções por aula, fundamentada em Griffiths (Introdução à Genética), Alberts (Biologia Molecular da Célula), OpenStax Biology 2e e Matriz do ENEM / INEP.
 */
object BiologyGeneticsDidactic {

    fun get(lessonId: String): CalculusDidacticBundle? {
        return when (lessonId) {
            "bio_gen_01" -> bundleBioGen01
            "bio_gen_02" -> bundleBioGen02
            "bio_gen_03" -> bundleBioGen03
            "bio_gen_04" -> bundleBioGen04
            "bio_gen_05" -> bundleBioGen05
            else -> null
        }
    }

    // =========================================================================
    // AULA bio_gen_01: Conceitos Iniciais e Ácidos Nucleicos
    // =========================================================================
    private val bundleBioGen01 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
A Genética é o ramo da Biologia dedicado ao estudo da hereditariedade e da variação biológica. O entendimento moderno da transmissão genética baseia-se na identificação da molécula de DNA (Ácido Desoxirribonucleico) como o suporte universal da informação biológica. No ENEM, a genética molecular é cobrada não como memorização mecânica de bases nitrogenadas, mas na compreensão do fluxo da informação genética — o Dogma Central da Biologia Molecular —, na relação entre genótipo e fenótipo frente às variações do ambiente e na interpretação das consequências fenotípicas de mutações pontuais.

## 2. Objetivos de Aprendizagem
- Diferenciar estruturalmente os ácidos nucleicos: DNA (desoxirribose, fita dupla antiparalela, timina) e RNA (ribose, fita simples, uracila).
- Aplicar as **Regras de Chargaff** para o pareamento de bases no DNA: \( [A] = [T] \) e \( [C] = [G] \), com duas ligações de hidrogênio entre A e T e três entre C e G.
- Compreender as etapas do **Dogma Central da Biologia Molecular**: Replicação semiconservativa do DNA, Transcrição em RNA mensageiro e Tradução ribossômica em cadeias polipeptídicas.
- Caracterizar o **Código Genético**: universalidade, degeneração (redundância: múltiplos códons para o mesmo aminoácido) e ausência de sobreposição.
- Distinguir **Genótipo** (constituição alélica) de **Fenótipo** (características morfológicas, fisiológicas e comportamentais observáveis), dominando a equação fundamental: \( \text{Fenótipo} = \text{Genótipo} + \text{Ambiente} \).
- Compreender os conceitos de alelos homozigotos (\( AA, aa \)), heterozigotos (\( Aa \)), dominância, recessividade, epigenética e norma de reação.

## 3. Pré-requisitos
- Estrutura celular e núcleo eucarionte (Citologia).
- Bioquímica de proteínas, aminoácidos e ligações peptídicas.

## 4. Explicação Teórica Aprofundada

### 1. Estrutura Molecular dos Ácidos Nucleicos
![Estrutura da Dupla Hélice de DNA e Dogma Central](file:///android_asset/images/biologia/dna_dupla_helice.svg)

Os ácidos nucleicos são polímeros formados pela união linear de **nucleotídeos**, cada um constituído por:
1. **Fosfato** (grupo fosfato \( \text{PO}_4^{3-} \));
2. **Pentose** (açúcar de 5 carbonos: desoxirribose no DNA, sem oxigênio no carbono 2'; ribose no RNA, com grupo hidroxila no carbono 2');
3. **Base Nitrogenada**:
   - **Púricas** (anel duplo): Adenina (A) e Guanina (G).
   - **Pirimídicas** (anel simples): Citosina (C), Timina (T, exclusiva do DNA) e Uracila (U, exclusiva do RNA).

As fitas de DNA são unidas por ligações fosfodiéster entre o carbono 3' de uma pentose e o carbono 5' da seguinte. A dupla hélice é **antiparalela** (uma fita corre no sentido \( 5' \to 3' \) e a complementar no sentido \( 3' \to 5' \)) e estabilizada por pontes de hidrogênio e empilhamento hidrofóbico das bases no núcleo da hélice.

### 2. O Dogma Central da Biologia Molecular
- **Replicação:** Processo semiconservativo no qual a enzima DNA Polimerase sintetiza fitas complementares usando cada fita original como molde, sempre no sentido \( 5' \to 3' \).
- **Transcrição:** A RNA Polimerase reconhece uma região promotora no DNA e transcreve a fita molde em RNA mensageiro precursor (pré-mRNA). Em eucariontes, ocorre o processamento: capeamento 5', poliadenilação 3' e **splicing** (remoção de íntrons e união de éxons). O **splicing alternativo** permite que um mesmo gene gere múltiplas isoformas proteicas distintas.
- **Tradução:** No citoplasma, o ribossomo lê o mRNA códon a códon (\( 1\text{ códon} = 3\text{ nucleotídeos} \)). O RNA transportador (tRNA) carrega o aminoácido correspondente por meio de seu anticódon complementar. O códon de início padrão é \( \text{AUG} \) (Metionina), e os códons de parada (*stop codons*) são \( \text{UAA}, \text{UAG}, \text{UGA} \).

### 3. Código Genético: Características Chave
1. **Universal:** Praticamente todos os seres vivos (de bactérias a humanos) compartilham a mesma tabela de correspondência códon-aminoácido. Isso permite a existência da biotecnologia transgênica.
2. **Degenerado (Redundante):** Existem 64 códons possíveis para apenas 20 aminoácidos. Logo, diferentes trincas codificam o mesmo aminoácido (ex: \( \text{GGU}, \text{GGC}, \text{GGA}, \text{GGG} \) codificam Glicina). Mutações silenciosas (substituições que não alteram o aminoácido) decorrem dessa propriedade.
3. **Não Ambíguo:** Cada códon específico codifica rigorosamente um único aminoácido.

### 4. Genótipo, Fenótipo e Epigenética
O genótipo é imutável durante o ciclo de vida individual (salvo mutações somáticas), mas a expressão fenotípica é modulada por fatores ambientais (nutrição, radiação solar, estresse, temperatura). A **Epigenética** estuda alterações hereditárias na expressão gênica que **não alteram a sequência de bases do DNA**, tais como a metilação de citosinas (que silencia genes) e a acetilação de histonas (que descondensa a cromatina, ativando a transcrição).

## 5. Definições e Conceitos-Chave
- **Gene:** Segmento de DNA contendo a informação necessária para a síntese de uma molécula funcional (RNA ou polipeptídeo).
- **Lócus Gênico:** Posição física específica ocupada por um gene em um cromossomo.
- **Alelos:** Formas alternativas de um mesmo gene que ocupam o mesmo lócus em cromossomos homólogos.
- **Homozigoto vs. Heterozigoto:** Indivíduo com alelos idênticos (\( AA \) ou \( aa \)) versus indivíduo com alelos distintos (\( Aa \)).
- **Dominância Completa:** O fenótipo do heterozigoto (\( Aa \)) é idêntico ao do homozigoto dominante (\( AA \)).

## 6. Exemplos Resolvidos e Demonstrações

### Exemplo 1: Regras de Chargaff
**Problema:** Uma amostra de DNA bacteriano de fita dupla apresenta 28% de resíduos de Adenina. Determine as porcentagens de Timina, Guanina e Citosina nessa amostra.
**Resolução:**
1. No DNA de fita dupla: \( \%A = \%T \). Portanto, \( \%T = 28\% \).
2. A soma de \( A + T = 28\% + 28\% = 56\% \).
3. Como \( A + T + G + C = 100\% \), temos que \( G + C = 100\% - 56\% = 44\% \).
4. Como \( \%G = \%C \), temos que \( \%G = 44\% / 2 = 22\% \) e \( \%C = 22\% \).
**Resposta:** \( T = 28\% \), \( G = 22\% \), \( C = 22\% \).

### Exemplo 2: Efeito de Mutação Pontual no Código Genético
**Problema:** A fita molde de DNA de um segmento gênico contém a sequência: \( 3'-\text{TAC-CCC-AAA-ATC}-5' \). Uma mutação pontual substitui a guanina no filamento original gerando o códon de mRNA correspondente \( \text{UAA} \). Explique a consequência para a síntese proteica.
**Resolução:**
1. A sequência original transcreve o mRNA \( 5'-\text{AUG-GGG-UUU-UAG}-3' \).
2. Se ocorre mutação gerando prematuramente o códon de terminação \( \text{UAA} \) no lugar de um aminoácido funcional (mutação *nonsense* ou sem sentido), a tradução é interrompida antes do término, gerando uma proteína truncada, geralmente não funcional e degradada pela célula.

## 7. Aplicações Práticas e Contexto ENEM
- **Identificação Forense:** Uso da estabilidade química do DNA para testes periciais e de paternidade.
- **Anemia Falciforme:** Exemplo clássico do ENEM de mutação de ponto por substituição (troca de uma única adenina por timina no gene da beta-globina, substituindo o ácido glutâmico por valina na posição 6, alterando a conformação da hemoglobina sob baixa tensão de oxigênio).
- **Epigenética e Gêmeos Monozigóticos:** Gêmeos idênticos possuem genótipos 100% coincidentes ao nascimento, mas expressam fenótipos cada vez mais divergentes ao longo da vida devido a marcas epigenéticas acumuladas por hábitos de vida e dieta.

## 8. Exercícios de Fixação com Gabarito Comentado

### Questão 1 (ENEM / Autoral)
Considere uma molécula de DNA de fita dupla que contém 600 nucleotídeos no total. Sabendo que 120 desses nucleotídeos são de Citosina, a quantidade total de ligações de hidrogênio que unem as duas fitas complementares é igual a:
A) 600
B) 720
C) 780
D) 840
E) 900

**Resolução:**
1. \( C = 120 \implies G = 120 \).
2. Total de pares C-G: 120 pares.
3. Como cada par C-G realiza 3 pontes de hidrogênio: \( 120 \times 3 = 360 \) pontes.
4. Total de nucleotídeos de C e G: \( 120 + 120 = 240 \).
5. Restam para A e T: \( 600 - 240 = 360 \) nucleotídeos, que correspondem a \( 360 / 2 = 180 \) de Adenina e 180 de Timina (180 pares A-T).
6. Cada par A-T realiza 2 pontes de hidrogênio: \( 180 \times 2 = 360 \) pontes.
7. Total de ligações de hidrogênio: \( 360 + 360 = 720 \).
**Gabarito:** B.

### Questão 2 (ENEM / Autoral)
O fato de o código genético ser considerado **degenerado** representa uma importante vantagem evolutiva para os organismos celulares porque:
A) permite que um único códon codifique múltiplos aminoácidos simultaneamente.
B) impede a ocorrência de deleções ou inserções cromossômicas no genoma.
C) viabiliza a síntese de proteínas mesmo na ausência de ribossomos citoplasmáticos.
D) confere tolerância a mutações neutras na terceira base de vários códons sem alterar a sequência primária do polipeptídeo.
E) possibilita que organismos transgênicos produzam proteínas bacterianas sem a necessidade de RNA mensageiro.

**Resolução:**
A redundância do código (onde múltiplos códons sinonímicos especificam o mesmo aminoácido, frequentemente diferindo apenas na base da posição 3 — hipótese do balanço) permite que mutações silenciosas de ponto não modifiquem o aminoácido inserido, preservando a função e estrutura da proteína final.
**Gabarito:** D.

## 9. Correção Comentada e Análise de Distratores
- Na Questão 1, a alternativa C (780) ou D (840) decorre de trocar o número de pontes por par (calcular 3 pontes para A-T e 2 para C-G).
- Na Questão 2, a alternativa A inverte a definição de degenerado com ambíguo. O código é não-ambíguo (1 códon = 1 aminoácido) e degenerado (vários códons = 1 aminoácido). A alternativa E ignora a obrigatoriedade da transcrição em mRNA.

## 10. Desafio de Domínio
Uma cadeia polipeptídica funcional é constituída por 150 resíduos de aminoácidos. Considerando que o gene codificador possui 2 íntrons de 90 pares de bases cada e uma região promotora não traduzida, qual é o número mínimo absoluto de nucleotídeos que o mRNA maduro (após o splicing) deve conter na sua região codificante, incluindo o códon de iniciação e o códon de parada?
*Dica:* Cada aminoácido requer 3 nucleotídeos, e o códon de parada adiciona mais uma trinca que não codifica aminoácido. \( 150 \times 3 + 3 = 453\text{ nucleotídeos} \).

## 11. Resumo Conceitual
- O DNA armazena a informação genética em fita dupla antiparalela com pareamento A-T (2 pontes) e C-G (3 pontes).
- O fluxo genético segue: Replicação \( (DNA \to DNA) \), Transcrição \( (DNA \to mRNA) \) e Tradução \( (mRNA \to \text{Proteína}) \).
- O código genético é universal, não ambíguo e degenerado.
- Fenótipo resulta da interação do genótipo com o meio ambiente e fatores epigenéticos.

## 12. Referências Bibliográficas
- Griffiths, A. J. et al. *Introdução à Genética*. 11ª ed. Guanabara Koogan, 2016.
- Alberts, B. et al. *Biologia Molecular da Célula*. 6ª ed. Artmed, 2017.
- OpenStax. *Biology 2e: Genes and Proteins*. Rice University, 2020.
- INEP. *Matriz de Referência do ENEM: Ciências da Natureza e suas Tecnologias*.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Dogma Central da Biologia Molecular",
                syntax = """\text{DNA} \xrightarrow[\text{DNA Polimerase}]{\text{Replicação}} \text{DNA} \xrightarrow[\text{RNA Polimerase}]{\text{Transcrição}} \text{mRNA} \xrightarrow[\text{Ribossomo + tRNA}]{\text{Tradução}} \text{Polipeptídeo}""",
                description = "Fluxo unidirecional da informação genética celular: replicação, transcrição e síntese proteica.",
                parametersInfo = "Em retrovírus (como o HIV), a enzima Transcriptase Reversa realiza a transcrição reversa (RNA → cDNA)."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Regras de Pareamento de Bases (Chargaff)",
                syntax = """[A] = [T] \text{ (2 Pontes de Hidrogênio)} \quad \text{e} \quad [G] \equiv [C] \text{ (3 Pontes de Hidrogênio)}""",
                description = "Estequiometria do pareamento de bases nitrogenadas em ácidos nucleicos bicatenários.",
                parametersInfo = "Quanto maior o teor de pares G≡C, maior é a estabilidade térmica e a temperatura de desnaturação (Tm) da fita dupla."
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Cálculo de Transcrição e Tradução",
                codeOrFormula = """\text{mRNA} = 5'-\text{AUG-UGG-UUC-UAA}-3' \implies 3 \text{ aminoácidos sintetizados}""",
                explanation = "O códon de parada UAA recruta fatores de terminação, liberando o peptídeo Met-Trp-Phe com 3 resíduos."
            )
        ),
        goldTip = "Lembre-se da regra mnemônica: Agnaldo Timóteo (A=T, 2 pontes) e Gal Costa (G≡C, 3 pontes)!",
        assetImagePath = "images/biologia/dna_dupla_helice.svg"
    )

    // =========================================================================
    // AULA bio_gen_02: Primeira Lei de Mendel
    // =========================================================================
    private val bundleBioGen02 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
Em 1865, o monge agostiniano Gregor Mendel apresentou à Sociedade de História Natural de Brno os princípios fundamentais da transmissão hereditária. Trabalhando com a ervilha-de-cheiro (*Pisum sativum*), Mendel superou as ideias equivocadas da época — como a "herança por mistura de fluidos" — ao demonstrar que as características são condicionadas por fatores particulados discretos que mantêm sua integridade física através das gerações. No ENEM, a **Primeira Lei de Mendel** (Lei da Segregação dos Fatores) é o alicerce para cálculos probabilísticos em heredogramas humanos, aconselhamento genético e estudos de dominância completa, codominância e alelos letais.

## 2. Objetivos de Aprendizagem
- Enunciar a **Primeira Lei de Mendel**: "Cada caráter é determinado por um par de fatores que se separam durante a formação dos gametas, indo apenas um fator do par para cada gameta".
- Associar a segregação mendeliana à separação dos **cromossomos homólogos na Anáfase I da Meiose**.
- Construir e interpretar o **Quadro de Punnett** para cruzamentos monoíbridos.
- Dominar as proporções clássicas da geração F2 mendeliana:
  - Proporção genotípica: \( 1\,AA : 2\,Aa : 1\,aa \) (\( 25\% : 50\% : 25\% \)).
  - Proporção fenotípica: \( 3 : 1 \) (\( 75\% \) dominante : \( 25\% \) recessivo).
- Interpretar o **Cruzamento-Teste** (*test-cross*) para determinar se um indivíduo com fenótipo dominante é homozigoto (\( AA \)) ou heterozigoto (\( Aa \)).
- Compreender variações da Primeira Lei:
  - **Dominância Incompleta (Ausência de Dominância):** fenótipo heterozigoto intermediário (proporção 1:2:1 em F2, ex: flores de *Mirabilis jalapa*).
  - **Codominância:** ambos os alelos se expressam plenamente no heterozigoto (ex: sistema sanguíneo MN).
  - **Alelos Letais:** homozigose de determinado alelo provoca a morte pré-natal, alterando a proporção fenotípica clássica para \( 2:1 \) (ex: herança da pelagem amarela em camundongos).

## 3. Pré-requisitos
- Meiose e formação de gametas haploides (Citologia).
- Conceitos de homozigose, heterozigose e probabilidade básica (Regra do E / Multiplicação e Regra do OU / Adição).

## 4. Explicação Teórica Aprofundada

### 1. O Experimento de Mendel e a Escolha Metodológica
Mendel obteve sucesso onde seus antecessores falharam devido ao rigor científico de sua abordagem:
- Escolha de *Pisum sativum*: ciclo de vida curto, fácil cultivo, alta descendência e flores com autofecundação natural protegida por pétalas fechadas (permitindo fecundação cruzada artificial controlada).
- Estudo de características contrastantes e discretas (sem meio-termo contínuo): sementes amarelas vs. verdes, lisas vs. rugosas.
- Uso de **linhagens puras** (geração P), obtidas após várias gerações de autofecundação sucessiva.

### 2. O Cruzamento Monoíbrido
![Primeira Lei de Mendel e Segregação Meiótica](file:///android_asset/images/biologia/primeira_lei_mendel.svg)

- **Geração Parental (P):** Amarela Pura (\( AA \)) \( \times \) Verde Pura (\( aa \)).
- **Geração F1:** 100% Amarela Heterozigota (\( Aa \)). A característica verde parecia ter desaparecido.
- **Autofecundação de F1 (\( Aa \times Aa \)):**
  - Gametas masculinos: \( \frac{1}{2} A \), \( \frac{1}{2} a \).
  - Gametas femininos: \( \frac{1}{2} A \), \( \frac{1}{2} a \).
- **Geração F2:**
  - \( \frac{1}{4} AA \) (Amarela)
  - \( \frac{2}{4} Aa \) (Amarela)
  - \( \frac{1}{4} aa \) (Verde)
  - Proporção fenotípica resultante: \( 3\text{ Amarelas} : 1\text{ Verde} \). A característica recessiva reaparece inalterada em F2, demonstrando que os fatores não se misturam.

### 3. A Base Citológica da Primeira Lei
Quando Mendel postulou que os fatores se segregam, os cromossomos e a meiose ainda não eram conhecidos. Hoje sabemos que os alelos residem em cromossomos homólogos idênticos. Durante a **Anáfase I da Meiose**, os cromossomos homólogos duplicados se separam para polos opostos da célula. Assim, cada gameta formado contém apenas uma das cromátides de cada par original, carregando rigorosamente um único alelo de cada gene.

### 4. Variações Alélicas
1. **Dominância Incompleta:** Não há alelo dominante absoluto. O heterozigoto exibe fenótipo intermediário. Exemplo clássico da flor maravilha (*Mirabilis jalapa*):
   - \( VV \): flor vermelha;
   - \( BB \): flor branca;
   - \( VB \): flor rosa (heterozigoto). No cruzamento \( VB \times VB \), obtém-se \( 1\text{ Vermelha} : 2\text{ Rosas} : 1\text{ Branca} \).
2. **Alelos Letais:** Em 1905, Lucien Cuénot cruzou camundongos amarelos entre si e sempre obteve a proporção de 2 amarelos para 1 cinza, nunca obtendo a proporção 3:1 nem camundongos amarelos puros. Demonstrou-se que o genótipo \( YY \) provoca morte embrionária precoce. Assim, entre os indivíduos nascidos vivos, a proporção observada é \( \frac{2}{3} Yy \) (amarelos) e \( \frac{1}{3} yy \) (cinzas).

## 5. Definições e Conceitos-Chave
- **Cruzamento-Teste (*Test-cross*):** Cruzamento de um indivíduo de fenótipo dominante e genótipo desconhecido (\( A\_ \)) com um indivíduo homozigoto recessivo (\( aa \)). Se nascer qualquer descendente recessivo (\( aa \)), o testado é heterozigoto (\( Aa \)). Se 100% dos descendentes forem dominantes, o testado é homozigoto (\( AA \)).
- **Regra do E (Produto):** A probabilidade de ocorrência simultânea de dois eventos independentes é o produto das probabilidades isoladas: \( P(A \cap B) = P(A) \times P(B) \).
- **Regra do OU (Soma):** A probabilidade de ocorrência de um ou outro de dois eventos mutuamente exclusivos é a soma das probabilidades: \( P(A \cup B) = P(A) + P(B) \).

## 6. Exemplos Resolvidos e Demonstrações

### Exemplo 1: Probabilidade em Heredograma de Doença Autossômica Recessiva
**Problema:** Um casal fenotipicamente normal tem um primeiro filho com albinismo (anomalia condicionada por alelo recessivo \( a \)). Qual é a probabilidade de esse mesmo casal gerar uma menina fenotipicamente normal?
**Resolução:**
1. Se o casal é normal e teve um filho albino (\( aa \)), ambos os pais são obrigatoriamente heterozigotos portadores (\( Aa \)).
2. No cruzamento \( Aa \times Aa \), os descendentes possíveis são: \( \frac{1}{4} AA, \frac{2}{4} Aa, \frac{1}{4} aa \).
3. A probabilidade de a criança ser fenotipicamente normal (\( AA \) ou \( Aa \)) é \( \frac{3}{4} \).
4. A probabilidade de nascer uma menina (sexo feminino) é \( \frac{1}{2} \).
5. Como os eventos "ser normal" e "ser menina" são independentes (Regra do E):
   \[ P = \frac{3}{4} \times \frac{1}{2} = \frac{3}{8} = 37,5\% \]

### Exemplo 2: Alelos Letais em População
**Problema:** Em uma espécie de planta, o alelo recessivo \( d \) em homozigose é letal no estágio de plântula. Uma população resultante do cruzamento de plantas heterozigotas (\( Dd \times Dd \)) produz sementes. Entre as plantas adultas que sobrevivem, qual é a fração esperada de plantas heterozigotas?
**Resolução:**
1. Cruzamento: \( Dd \times Dd \implies 1\,DD : 2\,Dd : 1\,dd \).
2. O genótipo \( dd \) morre no estágio embrionário/plântula e não chega à fase adulta.
3. O espaço amostral dos adultos sobreviventes restringe-se a: \( 1\,DD + 2\,Dd = 3 \).
4. Logo, a proporção de heterozigotos entre os sobreviventes é de \( \frac{2}{3} \) (aproximadamente \( 66,7\% \)).

## 7. Aplicações Práticas e Contexto ENEM
- **Aconselhamento Genético Pré-nupcial:** Cálculo do risco de casais consanguíneos gerarem descendentes com fibrose cística, fenilcetonúria ou anemia falciforme.
- **Melhoramento Genético Vegetal:** Uso do cruzamento-teste para fixar linhagens agronômicas homozigotas de alta produtividade.

## 8. Exercícios de Fixação com Gabarito Comentado

### Questão 1 (ENEM / Autoral)
A fenilcetonúria (PKU) é um erro inato do metabolismo de herança autossômica recessiva, diagnosticado precocemente pelo Teste do Pezinho. Um homem com PKU casa-se com uma mulher normal, filha de pai com PKU e mãe homozigota dominante sem histórico da doença. O casal planeja ter dois filhos. A probabilidade de ambos os filhos nascerem com a doença é de:
A) 1/2
B) 1/4
C) 1/8
D) 1/16
E) zero

**Resolução:**
1. O homem tem PKU: genótipo \( aa \).
2. A mulher é normal, mas filha de pai albino/PKU (\( aa \)), portanto herdou obrigatoriamente um alelo \( a \): genótipo \( Aa \).
3. Cruzamento do casal: \( aa \times Aa \).
   - Descendentes possíveis: \( \frac{1}{2} Aa \) (normais portadores) e \( \frac{1}{2} aa \) (afetados com PKU).
4. Probabilidade de o primeiro filho ter PKU: \( \frac{1}{2} \).
5. Probabilidade de o segundo filho ter PKU: \( \frac{1}{2} \).
6. Probabilidade de ambos terem a doença (eventos independentes):
   \[ P = \frac{1}{2} \times \frac{1}{2} = \frac{1}{4} \]
**Gabarito:** B.

### Questão 2 (ENEM / Autoral)
Em uma determinada raça canina, o alelo dominante \( M \) condiciona pelagem preta, enquanto o alelo recessivo \( m \) condiciona pelagem marrom. No entanto, embriões com o genótipo \( MM \) sofrem reabsorção uterina e morrem na gestação. Se dois cães pretos heterozigotos são cruzados e geram uma ninhada de 6 filhotes sobreviventes, o número mais provável de filhotes de pelagem preta nessa ninhada é:
A) 2
B) 3
C) 4
D) 5
E) 6

**Resolução:**
1. Cruzamento de dois pretos heterozigotos: \( Mm \times Mm \).
2. Proporção teórica de fertilização: \( 1\,MM : 2\,Mm : 1\,mm \).
3. Como \( MM \) é letal embrionário, os filhotes que nascem vivos distribuem-se em: \( \frac{2}{3} Mm \) (pretos) e \( \frac{1}{3} mm \) (marrons).
4. Em uma ninhada de 6 filhotes vivos:
   \[ \text{Pretos esperados} = 6 \times \frac{2}{3} = 4\text{ filhotes} \]
**Gabarito:** C.

## 9. Correção Comentada e Análise de Distratores
- Na Questão 2, marcar B (3 filhotes) decorre de aplicar a proporção de 50% de heterozigose sem eliminar os mortos embrionários. A alternativa D ou E ignora a existência de recessivos viáveis.

## 10. Desafio de Domínio
Um casal fenotipicamente normal para a pigmentação da pele e sem histórico familiar de nanismo acondroplásico (anomalia autossômica dominante condicionada pelo alelo \( A \), em que o homozigoto \( AA \) é letal) consulta um geneticista. Se ambos forem comprovadamente heterozigotos para albinismo (\( Aa \)), qual a chance de terem uma criança do sexo masculino que seja simultaneamente albina e acondroplásica, considerando que o nanismo não está presente na genealogia?
*Reflexão:* Como a acondroplasia é dominante e nenhum dos pais possui a anomalia, ela só poderia surgir por mutação de novo (evento raríssimo com probabilidade próxima a zero nas condições da genética clássica mendeliana sem mutação explícita). Portanto, a resposta formal é 0.

## 11. Resumo Conceitual
- A Primeira Lei fundamenta-se na segregação dos alelos na Anáfase I da Meiose.
- Cruzamento monoíbrido clássico resulta na proporção genotípica 1:2:1 e fenotípica 3:1.
- O cruzamento-teste revela o genótipo de indivíduos com fenótipo dominante ao cruzá-los com homozigotos recessivos.
- Alelos letais alteram a proporção fenotípica observada para 2:1 devido à morte pré-natal dos homozigotos.

## 12. Referências Bibliográficas
- Griffiths, A. J. et al. *Introdução à Genética*. 11ª ed. Guanabara Koogan, 2016.
- Pierce, B. A. *Genética: Um Enfoque Conceitual*. 5ª ed. Guanabara Koogan, 2016.
- INEP. *Provas e Gabaritos do ENEM (2010–2023)*.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Segregação Alélica e Proporção F2",
                syntax = """P: \; AA \times aa \implies F_1: \; Aa \; (100\%) \xrightarrow{\text{Autofecundação}} F_2: \; 1\,AA : 2\,Aa : 1\,aa \implies 3\text{ Dominantes} : 1\text{ Recessivo}""",
                description = "Segregação dos cromossomos homólogos na Anáfase I meiótica determinando as proporções mendelianas clássicas.",
                parametersInfo = "Decorre da separação física dos alelos nos gametas. A proporção genotípica é 1:2:1 e a fenotípica é 3:1."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Distorção Fenotípica por Alelos Letais",
                syntax = """Mm \times Mm \implies 1\,MM \text{ (Morte Embrionária)} : 2\,Mm \text{ (Vivo)} : 1\,mm \text{ (Vivo)} \implies \text{Proporção Observada: } 2 : 1""",
                description = "Eliminação do homozigoto letal do espaço amostral dos indivíduos nascidos vivos.",
                parametersInfo = "No cálculo de probabilidades no ENEM, o denominador de indivíduos viáveis torna-se 3 (2/3 heterozigotos e 1/3 homozigotos viáveis)."
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Cruzamento-Teste (*Test-cross*)",
                codeOrFormula = """\text{Se } A\_ \times aa \to \text{todos normais: Genótipo é } AA; \quad \text{se surgir } aa \to \text{Genótipo é } Aa""",
                explanation = "O surgimento de um único indivíduo recessivo prova incontestavelmente que o genitor dominante portava o alelo recessivo."
            )
        ),
        goldTip = "Atenção máxima ao enunciado do ENEM: quando a questão perguntar 'qual a probabilidade de o casal ter uma MENINA ALBINA', multiplique a probabilidade genética (ex: 1/4) por 1/2 pelo sexo biológico!",
        assetImagePath = "images/biologia/primeira_lei_mendel.svg"
    )

    // =========================================================================
    // AULA bio_gen_03: Segunda Lei de Mendel e Linkage
    // =========================================================================
    private val bundleBioGen03 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
Após desvendar o comportamento de pares de fatores isolados, Gregor Mendel investigou como se comportavam dois pares de características simultaneamente (diibridismo). Em seu famoso experimento cruzando sementes amarelas/lisas com verdes/rugosas, formulou a **Segunda Lei de Mendel** (Lei da Segregação Independente). Décadas mais tarde, Thomas Hunt Morgan demonstrou com a mosca-das-frutas (*Drosophila melanogaster*) que a segregação independente só ocorre se os genes estiverem situados em **cromossomos não-homólogos distintos**. Quando dois genes residem no mesmo cromossomo, eles tendem a ser transmitidos em bloco — fenômeno denominado **Ligação Gênica** (*Linkage*) —, sendo separados apenas pelo evento de **Crossing-over** (permutação) durante a prófase I meiótica.

## 2. Objetivos de Aprendizagem
- Enunciar a **Segunda Lei de Mendel**: "Os fatores para dois ou mais caracteres segregam-se de forma independente durante a formação dos gametas".
- Demonstrar as proporções fenotípicas clássicas do diibridismo mendeliano em F2: \( 9 : 3 : 3 : 1 \).
- Calcular o número de gametas formados por um poli-híbrido usando a fórmula \( 2^n \), onde \( n \) é o número de pares heterozigotos.
- Identificar as condições físicas para a validade da 2ª Lei: genes em cromossomos diferentes ou genes muito distantes no mesmo cromossomo.
- Conceituar **Ligação Gênica (Linkage)**: ligação completa (sem permutação) vs. ligação incompleta (com permutação).
- Interpretar as configurações cromossômicas dos heterozigotos:
  - **Configuração Cis** (acoplamento): alelos dominantes no mesmo cromossomo (\( AB / ab \)).
  - **Configuração Trans** (repulsão): alelo dominante e recessivo no mesmo cromossomo (\( Ab / aB \)).
- Calcular a **Taxa de Recombinação (Frequência de Recombinação - FR)** e construir **Mapas Genéticos** utilizando a unidade centimorgan (cM) ou unidade de recombinação (UR).

## 3. Pré-requisitos
- Primeira Lei de Mendel e regra da multiplicação de probabilidades.
- Prófase I da Meiose: pareamento de homólogos e quiasmas (Citologia).

## 4. Explicação Teórica Aprofundada

### 1. O Experimento do Diibridismo de Mendel
Mendel cruzou linhagens puras para duas características de sementes de ervilha:
- **P:** Amarela Lisa (\( VVRR \)) \( \times \) Verde Rugosa (\( vvrr \)).
- **F1:** 100% Amarela Lisa (\( VvRr \)).
- **Autofecundação de F1 (\( VvRr \times VvRr \)):**
  Como a segregação de \( V/v \) é independente de \( R/r \), cada indivíduo F1 produz 4 tipos de gametas em proporções iguais (\( 25\% \) cada): \( VR, Vr, vR, vr \).
  O cruzamento resulta em 16 combinações no Quadro de Punnett com a clássica proporção fenotípica:
  - \( \frac{9}{16} \) Amarelas Lisas (\( V\_R\_ \))
  - \( \frac{3}{16} \) Amarelas Rugosas (\( V\_rr \))
  - \( \frac{3}{16} \) Verdes Lisas (\( vvR\_ \))
  - \( \frac{1}{16} \) Verde Rugosa (\( vvrr \))

### 2. A Fórmula Geral dos Gametas
Para um genótipo com \( n \) pares de alelos em heterozigose segregando independentemente:
- Número de classes de gametas diferentes = \( 2^n \).
- Exemplo: O genótipo \( AaBbCcDD \) possui 3 pares heterozigotos (\( Aa, Bb, Cc \)) e 1 homozigoto (\( DD \)). Logo, produz \( 2^3 = 8 \) gametas diferentes em proporções iguais (\( 1/8 \) cada).

### 3. Ligação Gênica (Linkage) e Experimentos de Morgan
![2ª Lei de Mendel vs. Ligação Gênica (Linkage) e Crossing-Over](file:///android_asset/images/biologia/segunda_lei_linkage.svg)

Quando dois genes estão localizados no mesmo cromossomo sintênico:
- **Linkage Total (sem permutação):** Se a distância entre os genes for insignificante, os alelos não sofrem crossing-over e migram juntos. Um indivíduo cis (\( AB/ab \)) produz apenas dois tipos de gametas: 50% \( AB \) e 50% \( ab \).
- **Linkage Parcial com Crossing-Over:** Durante a subfase diplóteno da Prófase I da Meiose, cromátides não-irmãs trocam fragmentos equivalentes nos quiasmas. São gerados gametas parentais (sem permutação, em maior frequência) e gametas recombinantes (com permutação, em menor frequência).

### 4. Cálculo da Taxa de Recombinação e Mapa Genético
A frequência de recombinação é proporcional à distância física entre os dois locos gênicos:
\[ \text{Taxa de Recombinação (FR)} = \frac{\text{Total de Indivíduos Recombinantes}}{\text{Total Geral de Descendentes}} \times 100\% \]
Convencionou-se que:
\[ 1\% \text{ de Recombinação} = 1\text{ centimorgan (cM)} = 1\text{ Unidade de Mapa / Recombinação (UR)} \]
A taxa máxima observável de recombinação entre dois genes é de 50%, ponto a partir do qual eles se comportam estatisticamente como se estivessem em cromossomos diferentes (segregação independente).

## 5. Definições e Conceitos-Chave
- **Gametas Parentais:** Gametas que mantêm a mesma combinação alélica presente nos cromossomos dos genitores (soma sempre maior que 50% em linkage).
- **Gametas Recombinantes:** Gametas resultantes da troca de pedaços de cromátides durante o crossing-over (soma sempre menor que 50% em linkage).
- **Centimorgan (cM):** Medida de distância relativa em cromossomos correspondente a 1% de frequência de crossing-over.

## 6. Exemplos Resolvidos e Demonstrações

### Exemplo 1: Determinação de Gametas e Configuração Alélica
**Problema:** Um macho heterozigoto para dois genes ligados \( A \) e \( B \) é submetido a um cruzamento-teste com uma fêmea \( ab/ab \). A descendência obtida é:
- 420 indivíduos com fenótipo \( A B \)
- 420 indivíduos com fenótipo \( a b \)
- 80 indivíduos com fenótipo \( A b \)
- 80 indivíduos com fenótipo \( a B \)
Determine a configuração dos alelos no macho heterozigoto e a distância entre os genes \( A \) e \( B \).
**Resolução:**
1. Os descendentes mais frequentes refletem os gametas parentais: \( AB \) (420) e \( ab \) (420).
2. Como os alelos dominantes estão juntos no mesmo cromossomo parental, o heterozigoto está na **configuração CIS** (\( AB / ab \)).
3. Os descendentes menos frequentes são os recombinantes: \( Ab \) (80) e \( aB \) (80).
   - Total de recombinantes = \( 80 + 80 = 160 \).
   - Total geral de descendentes = \( 420 + 420 + 80 + 80 = 1000 \).
4. Taxa de Recombinação:
   \[ \text{FR} = \frac{160}{1000} \times 100\% = 16\% \]
5. Distância gênica = \( 16\text{ cM} \) (ou 16 UR).

### Exemplo 2: Mapeamento Genético Linear
**Problema:** Três genes ligados \( X, Y, Z \) apresentaram as seguintes frequências de recombinação:
- Entre \( X \) e \( Y \): 8%
- Entre \( Y \) e \( Z \): 15%
- Entre \( X \) e \( Z \): 23%
Determine a ordem relativa desses genes no cromossomo.
**Resolução:**
1. A maior distância observada é entre \( X \) e \( Z \) (23 cM), indicando que esses dois locos estão nas extremidades.
2. O gene \( Y \) deve estar localizado no intervalo intermediário:
   - Distância \( X-Y = 8\text{ cM} \);
   - Distância \( Y-Z = 15\text{ cM} \);
   - Soma: \( 8 + 15 = 23\text{ cM} \).
3. A ordem gênica linear no cromossomo é: **X — Y — Z** (ou Z — Y — X).

## 7. Aplicações Práticas e Contexto ENEM
- **Mapeamento do Genoma:** Princípio histórico utilizado para construir o primeiro mapa cromossômico em *Drosophila* por Alfred Sturtevant, base conceitual do Projeto Genoma Humano.
- **Transgênese e Seleção Assistida por Marcadores:** Identificação de marcadores moleculares estreitamente ligados a genes de resistência à seca em soja e milho.

## 8. Exercícios de Fixação com Gabarito Comentado

### Questão 1 (ENEM / Autoral)
Considere um indivíduo com o genótipo \( AaBb \). Se esses dois pares de alelos estiverem localizados em pares de cromossomos homólogos distintos, a porcentagem esperada de gametas do tipo \( Ab \) produzida por esse indivíduo é de:
A) 100%
B) 50%
C) 25%
D) 12,5%
E) 6,25%

**Resolução:**
Estando em cromossomos homólogos distintos, aplica-se a Segunda Lei de Mendel (Segregação Independente). O heterozigoto \( AaBb \) produz 4 tipos de gametas em proporções rigorosamente iguais: \( 25\% AB \), \( 25\% Ab \), \( 25\% aB \), \( 25\% ab \).
**Gabarito:** C.

### Questão 2 (ENEM / Autoral)
Em tomates, a cor do fruto (vermelho \( R \) ou amarelo \( r \)) e o formato da folha (normal \( F \) ou recortada \( f \)) são determinados por genes situados no mesmo cromossomo, distantes entre si por 20 unidades de recombinação (UR). Uma planta di-híbrida de frutos vermelhos e folhas normais com configuração **TRANS** (\( Rf / rF \)) é autofecundada. A porcentagem esperada de gametas portadores simultaneamente dos alelos recessivos \( rf \) formados por essa planta é de:
A) 40%
B) 20%
C) 10%
D) 5%
E) 2,5%

**Resolução:**
1. A planta é TRANS: seus cromossomos são \( Rf \) e \( rF \).
2. Gametas parentais (sem crossing): \( Rf \) e \( rF \).
3. Gametas recombinantes (com crossing): \( RF \) e \( rf \).
4. A distância é de 20 UR, o que significa uma Taxa de Recombinação total de 20%.
5. A soma dos gametas recombinantes é igual à taxa de recombinação:
   \[ \%RF + \%rf = 20\% \]
6. Como os dois tipos recombinantes surgem na mesma frequência:
   \[ \%rf = \frac{20\%}{2} = 10\% \]
**Gabarito:** C.

## 9. Correção Comentada e Análise de Distratores
- Na Questão 2, a alternativa B (20%) confunde a taxa total de permutação com a frequência de uma única classe de gameta recombinante. A alternativa A (40%) corresponde à frequência de cada gameta parental (\((100 - 20)/2 = 40\%\)).

## 10. Desafio de Domínio
Se uma planta com genótipo \( \frac{Ab}{aB} \) for submetida a um cruzamento-teste com um duplo recessivo \( \frac{ab}{ab} \), e a distância entre os genes for de 14 cM, qual será a proporção percentual de descendentes com fenótipo duplo dominante (\( A\_B\_ \))?
*Resposta:* O fenótipo \( A\_B\_ \) requer o gameta recombinante \( AB \) do indivíduo duplo-heterozigoto. Como a taxa de recombinação é 14%, o gameta \( AB \) representa \( 14\% / 2 = 7\% \). Como o genitor recessivo só fornece \( ab \), a porcentagem de descendentes \( AaBb \) será de exatamente 7%.

## 11. Resumo Conceitual
- A 2ª Lei aplica-se a genes em cromossomos diferentes (proporção 9:3:3:1 em F2).
- Genes no mesmo cromossomo apresentam Ligação Gênica (Linkage).
- O crossing-over na Prófase I produz combinações recombinantes.
- A taxa de recombinação é diretamente proporcional à distância física em centimorgans (máximo 50%).

## 12. Referências Bibliográficas
- Griffiths, A. J. et al. *Introdução à Genética*. 11ª ed. Guanabara Koogan, 2016.
- Snustad, D. P. & Simmons, M. J. *Fundamentos de Genética*. 7ª ed. Guanabara Koogan, 2017.
- INEP. *Matriz de Referência do ENEM: Ciências da Natureza*.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Taxa de Recombinação e Distância Gênica",
                syntax = """\text{FR} = \frac{\sum \text{Recombinantes}}{\text{Total de Descendentes}} \times 100\% \iff 1\% \text{ Recombinação} = 1\text{ cM (Centimorgan)}""",
                description = "Determinação da distância física relativa entre dois locos gênicos ligados no mesmo cromossomo.",
                parametersInfo = "Varia entre 0% (ligação gênica completa / ausência de crossing) e 50% (segregação independente de Mendel)."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Gametas em Ligação Gênica (Configuração Cis)",
                syntax = """\frac{AB}{ab} \implies \text{Parentais: } AB, ab \left(\frac{100 - \text{FR}}{2}\right) \quad | \quad \text{Recombinantes: } Ab, aB \left(\frac{\text{FR}}{2}\right)""",
                description = "Distribuição percentual dos gametas parentais e recombinantes gerados por permutação na prófase I meiótica.",
                parametersInfo = "Na configuração Trans (Ab/aB), os parentais tornam-se Ab e aB, enquanto os recombinantes passam a ser AB e ab."
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Diferença entre Configuração Cis e Trans",
                codeOrFormula = """\text{Cis: } \frac{AB}{ab} \implies \text{Parentais } AB, ab; \quad \text{Trans: } \frac{Ab}{aB} \implies \text{Parentais } Ab, aB""",
                explanation = "Parentais são sempre a maioria (>50% somados); recombinantes surgem pela troca no quiasma meiótico."
            )
        ),
        goldTip = "Identifique os parentais procurando sempre os dois maiores grupos de descendentes no cruzamento-teste. Os dois menores grupos são os recombinantes!",
        assetImagePath = "images/biologia/segunda_lei_linkage.svg"
    )

    // =========================================================================
    // AULA bio_gen_04: Doenças Genéticas e Sistema ABO
    // =========================================================================
    private val bundleBioGen04 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
A genética humana aplicada à medicina é um dos eixos mais cobrados na prova de Ciências da Natureza do ENEM. O estudo dos grupos sanguíneos (sistemas ABO e Rh), das heranças ligadas ao sexo biológico (localizadas na porção não-homóloga do cromossomo X) e das anomalias cromossômicas numéricas e estruturais conecta conceitos moleculares a situações cotidianas como transfusões sanguíneas de emergência, incompatibilidade materno-fetal e aconselhamento genético pré-natal.

## 2. Objetivos de Aprendizagem
- Compreender a genética do **Sistema ABO**: alelos múltiplos (polialelia) com alelos codominantes \( I^A \) e \( I^B \) e alelo recessivo \( i \).
- Mapear a relação entre genótipos, **Aglutinogênios** (antígenos eritrocitários na membrana) e **Aglutininas** (anticorpos no plasma):
  - Sangue A: aglutinogênio A, aglutinina anti-B.
  - Sangue B: aglutinogênio B, aglutinina anti-A.
  - Sangue AB: aglutinogênios A e B, sem aglutininas (Receptor Universal).
  - Sangue O: sem aglutinogênios, aglutininas anti-A e anti-B (Doador Universal).
- Analisar a herança do **Fator Rh** (antígeno D) e a fisiopatologia da **Eritroblastose Fetal** (Doença Hemolítica do Recém-Nascido - DHRN), incluindo profilaxia com imunoglobulina anti-Rh.
- Diferenciar os tipos de herança ligada ao cromossomo sexual:
  - **Herança Ligada ao X Recessiva**: Daltonismo e Hemofilia (mais frequentes em homens \( X^d Y \) por serem hemizigotos).
  - **Herança Holândrica (Ligada ao Y)**: restrita exclusivamente ao sexo masculino (genes da porção não homóloga de Y, ex: gene *SRY*).
- Identificar as principais anomalias cromossômicas humanas decorrentes de **não-disjunção meiótica**:
  - Aneuploidias autossômicas: Síndrome de Down (Trissomia do 21).
  - Aneuploidias sexuais: Síndrome de Turner (\( 45, X0 \)) e Síndrome de Klinefelter (\( 47, XXY \)).

## 3. Pré-requisitos
- Primeira Lei de Mendel e Codominância.
- Sistema imunológico: antígenos, anticorpos e aglutinação (Fisiologia).
- Meiose e cariótipo humano padrão (\( 46, XX \) ou \( 46, XY \)).

## 4. Explicação Teórica Aprofundada

### 1. Sistema Sanguíneo ABO: Polialelia e Codominância
![Sistema Sanguíneo ABO, Fator Rh e Doença Hemolítica](file:///android_asset/images/biologia/sistema_abo_rh.svg)

A determinação do tipo sanguíneo ABO depende de uma série de 3 alelos de um único gene no cromossomo 9:
- Relação de dominância: \( I^A = I^B > i \).
- Genótipos e Fenótipos:
  - **Grupo A:** \( I^A I^A \) ou \( I^A i \);
  - **Grupo B:** \( I^B I^B \) ou \( I^B i \);
  - **Grupo AB:** \( I^A I^B \) (Codominância pura: ambos os antígenos são expressos);
  - **Grupo O:** \( ii \) (Homozigoto recessivo: ausência de antígenos A e B funcionais).

Nas transfusões sanguíneas de hemácias concentradas, a regra de ouro é: **o anticorpo do receptor não pode atacar o antígeno da hemácia do doador**, sob risco de aglutinação maciça, hemólise intravascular e insuficiência renal aguda.

### 2. O Sistema Rh e a Eritroblastose Fetal (DHRN)
- O sistema Rh é determinado por herança autossômica monogênica:
  - \( Rh^+ \) (possui antígeno D): genótipos \( RR \) ou \( Rr \).
  - \( Rh^- \) (não possui antígeno D): genótipo \( rr \). O indivíduo \( Rh^- \) não nasce com anticorpo anti-Rh no plasma; ele só produz anticorpos se for previamente sensibilizado pelo contato com sangue \( Rh^+ \).
- **Condições para Ocorrência da Eritroblastose Fetal:**
  1. Mãe obrigatoriamente \( Rh^- \) (\( rr \));
  2. Feto obrigatoriamente \( Rh^+ \) (\( Rr \), herdado do pai \( Rh^+ \));
  3. Sensibilização materna prévia (geralmente durante o parto de uma primeira gestação \( Rh^+ \), abortamento ou transfusão inadvertida).
  - Em uma gestação subsequente de feto \( Rh^+ \), anticorpos maternos da classe **IgG** (que atravessam a barreira placentária) destroem as hemácias do feto, gerando anemia profunda, icterícia grave (acúmulo de bilirrubina, com risco de *kernicterus*) e eritropoiese compensatória com liberação de hemácias imaturas (eritroblastos).
  - **Profilaxia:** Administração de soro com anticorpos anti-Rh (imunoglobulina anti-D) na mãe \( Rh^- \) até 72 horas após o parto de filho \( Rh^+ \), destruindo as hemácias fetais antes que o sistema imune materno seja ativado.

### 3. Heranças Ligadas ao Sexo (Cromossomo X)
O cromossomo X possui centenas de genes essenciais que não têm correspondentes no minúsculo cromossomo Y (região não-homóloga de X).
- Homens são **hemizigotos** (\( X Y \)): basta herdar um único alelo recessivo da mãe para manifestar a condição.
- Mulheres (\( XX \)) só manifestam se herdarem o alelo em dose dupla (\( X^d X^d \)), recebendo um do pai afetado e outro da mãe portadora ou afetada.
- Exemplo do Daltonismo e Hemofilia A:
  - Mulher normal não-portadora: \( X^D X^D \);
  - Mulher normal portadora: \( X^D X^d \);
  - Mulher daltônica/hemofílica: \( X^d X^d \);
  - Homem normal: \( X^D Y \);
  - Homem daltônico/hemofílico: \( X^d Y \).

### 4. Anomalias Cromossômicas (Aneuploidias)
Originam-se por falha na separação dos cromossomos homólogos na Anáfase I ou das cromátides-irmãs na Anáfase II da meiose parental (**não-disjunção meiótica**):
- **Síndrome de Down:** Trissomia do cromossomo 21 (\( 47, XX, +21 \) ou \( 47, XY, +21 \)), fortemente correlacionada com a idade materna avançada.
- **Síndrome de Turner:** Monossomia do cromossomo X (\( 45, X0 \)). Indivíduos fenotipicamente femininos com baixa estatura, pescoço alado, amenorreia primária e esterilidade.
- **Síndrome de Klinefelter:** Trissomia sexual (\( 47, XXY \)). Indivíduos fenotipicamente masculinos, alta estatura, hipogonadismo, ginecomastia e esterilidade.

## 5. Definições e Conceitos-Chave
- **Aglutinogênio:** Glicoproteína de membrana das hemácias que funciona como antígeno.
- **Aglutinina:** Anticorpo natural solúvel no plasma (anti-A e anti-B são da classe IgM; anti-Rh é da classe IgG).
- **Efeito Bombaim (Falso O):** Indivíduos recessivos \( hh \) não produzem a substância precursora H e, mesmo tendo genes \( I^A \) ou \( I^B \), expressam o fenótipo funcional de sangue O.
- **Hemizigose:** Condição genética dos machos mamíferos para os locos situados no cromossomo X.

## 6. Exemplos Resolvidos e Demonstrações

### Exemplo 1: Probabilidade Genética no Sistema ABO
**Problema:** Um homem do grupo sanguíneo A, filho de pai do grupo O, casa-se com uma mulher do grupo B, filha de mãe do grupo O. Qual é a probabilidade de o casal gerar uma criança do grupo O?
**Resolução:**
1. O homem é do grupo A. Como seu pai é do grupo O (\( ii \)), ele recebeu obrigatoriamente um alelo \( i \). Genótipo do homem: \( I^A i \).
2. A mulher é do grupo B. Como sua mãe é do grupo O (\( ii \)), ela recebeu obrigatoriamente um alelo \( i \). Genótipo da mulher: \( I^B i \).
3. Cruzamento: \( I^A i \times I^B i \).
   - Descendentes possíveis:
     - \( \frac{1}{4} I^A I^B \) (Grupo AB)
     - \( \frac{1}{4} I^A i \) (Grupo A)
     - \( \frac{1}{4} I^B i \) (Grupo B)
     - \( \frac{1}{4} ii \) (Grupo O)
4. A probabilidade de terem uma criança do grupo O é de \( \frac{1}{4} \) (25%).

### Exemplo 2: Herança do Daltonismo em Família
**Problema:** Uma mulher de visão normal, cujo pai era daltônico (\( X^d Y \)), casa-se com um homem de visão normal (\( X^D Y \)). Se o casal tiver um filho do sexo masculino, qual é a probabilidade de ele ser daltônico?
**Resolução:**
1. O pai da mulher era daltônico (\( X^d Y \)). Logo, a mulher herdou obrigatoriamente o alelo \( X^d \). Como tem visão normal, seu genótipo é \( X^D X^d \) (portadora).
2. O homem é normal: \( X^D Y \).
3. Cruzamento: \( X^D X^d \times X^D Y \).
4. Descendentes masculinos possíveis (recebem o cromossomo Y do pai):
   - \( \frac{1}{2} X^D Y \) (filho homem normal)
   - \( \frac{1}{2} X^d Y \) (filho homem daltônico)
5. Como a pergunta restringiu o espaço amostral aos filhos do sexo masculino ("se o casal tiver um filho do sexo masculino"), a resposta é \( \frac{1}{2} \) (50%).

## 7. Aplicações Práticas e Contexto ENEM
- **Testes de Paternidade em Disputas Judiciais:** Exclusão rápida de paternidade biológica pelo sistema ABO (ex: pai AB jamais pode ser o pai biológico de criança O legítima).
- **Campanhas de Doação de Sangue:** A importância estratégica dos estoques de sangue O negativo para atendimento imediato a vítimas de politraumatismo grave.

## 8. Exercícios de Fixação com Gabarito Comentado

### Questão 1 (ENEM / Autoral)
Em um hospital militar, um soldado acidentado do grupo sanguíneo \( B^- \) necessita urgentemente de transfusão de hemácias concentradas. No banco de sangue da unidade, estão disponíveis apenas bolsas dos seguintes tipos sanguíneos:
1. \( A^+ \)
2. \( B^+ \)
3. \( AB^- \)
4. \( B^- \)
5. \( O^- \)
Considerando as regras imunológicas de compatibilidade eritrocitária, quais bolsas podem ser transfundidas com segurança para esse paciente?
A) Apenas a bolsa 4.
B) As bolsas 2 e 4.
C) As bolsas 4 e 5.
D) As bolsas 2, 4 e 5.
E) Todas as bolsas exceto a 1.

**Resolução:**
1. O paciente receptor é \( B^- \): possui aglutinogênio B e anticorpos anti-A e pode produzir anticorpos anti-Rh se receber sangue com fator Rh.
2. Portanto:
   - Não pode receber sangue com fator Rh positivo (elimina bolsas 1 e 2).
   - Não pode receber sangue com aglutinogênio A (elimina bolsas 1 e 3).
3. Bolsas compatíveis:
   - Bolsa 4 (\( B^- \)): idêntica ao receptor.
   - Bolsa 5 (\( O^- \)): doador universal sem antígenos A, B ou Rh nas hemácias.
**Gabarito:** C.

### Questão 2 (ENEM / Autoral)
A profilaxia padrão da eritroblastose fetal consiste na injeção intramuscular de anticorpos anti-Rh na puérpera logo após o parto. A eficácia desse procedimento baseia-se no fato de que esses anticorpos exógenos administrados:
A) imunizam ativamente a mãe contra futuras gestações de fetos com fator Rh positivo.
B) neutralizam as células produtoras de linfócitos T na medula óssea materna.
C) destroem prontamente as hemácias fetais Rh positivas presentes na circulação materna antes do desencadeamento da resposta imune primária.
D) estimulam a síntese imediata de antígenos do sistema ABO na placenta para bloquear a passagem de imunoglobulinas.
E) induzem a tolerância imunológica permanente do sistema imune fetal contra o sangue materno.

**Resolução:**
A injeção de imunoglobulina anti-D é uma imunização passiva. Os anticorpos injetados ligam-se às hemácias fetais \( Rh^+ \) que invadiram a corrente sanguínea materna durante o parto e promovem sua rápida fagocitose e lise, impedindo que os linfócitos B da mãe reconheçam o antígeno D e formem plasmócitos e células de memória de longa duração.
**Gabarito:** C.

## 9. Correção Comentada e Análise de Distratores
- Na Questão 2, a alternativa A erra ao afirmar que se trata de "imunização ativa". A injeção de soro com anticorpos prontos é **imunização passiva**.

## 10. Desafio de Domínio
Um casal daltônico tem uma filha que apresenta cariótipo com 45 cromossomos e diagnóstico de Síndrome de Turner (\( 45, X0 \)). A menina possui visão perfeitamente normal para as cores. Sabendo que o daltonismo é condicionado por alelo recessivo ligado ao cromossomo X, determine em qual dos genitores ocorreu o erro de não-disjunção meiótica.
*Resolução detalhada:*
1. Pai daltônico: genótipo \( X^d Y \). Mãe daltônica: genótipo \( X^d X^d \).
2. Se a filha tem visão normal, ela possui obrigatoriamente um alelo dominante \( X^D \).
3. Porém, nem o pai nem a mãe possuem \( X^D \)! Para uma mulher Turner ter visão normal, ela precisaria de \( X^D \), o que só ocorreria se recebesse o alelo por mutação nova, ou se o diagnóstico de visão foi equivocado, ou caso um dos pais biológicos alegados não seja o verdadeiro genitor. Pelo raciocínio genético estrito de não-disjunção, se a criança Turner fosse \( X^d 0 \), ela seria daltônica!

## 11. Resumo Conceitual
- Sistema ABO: codominância entre \( I^A \) e \( I^B \), ambos dominantes sobre \( i \).
- Sangue O é doador universal de hemácias; AB é receptor universal.
- Eritroblastose fetal: mãe \( Rh^- \), pai \( Rh^+ \), feto \( Rh^+ \) com sensibilização prévia.
- Daltonismo e Hemofilia: herança ligada ao X recessiva, com maior prevalência em homens.

## 12. Referências Bibliográficas
- Griffiths, A. J. et al. *Introdução à Genética*. 11ª ed. Guanabara Koogan, 2016.
- Guyton, A. C. & Hall, J. E. *Tratado de Fisiologia Médica*. 13ª ed. Elsevier, 2017.
- INEP. *Matriz de Referência do ENEM: Ciências da Natureza*.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Regra de Compatibilidade Transfusional de Hemácias",
                syntax = """\text{Antígenos da Hemácia Doadora} \cap \text{Anticorpos do Plasma Receptor} = \emptyset""",
                description = "Critério imunológico para transfusão de hemácias concentradas sem risco de hemólise ou aglutinação intravascular.",
                parametersInfo = "O sangue O Rh- não possui aglutinogênios A, B nem Rh (Doador Universal); AB Rh+ não possui anticorpos anti-A, anti-B nem anti-Rh (Receptor Universal)."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Mecanismo Fisiopatológico da Eritroblastose Fetal",
                syntax = """\text{Mãe } Rh^- \text{ sensibilizada} \xrightarrow{\text{Gestação Subsequente}} \text{IgG Materna anti-Rh} \xrightarrow{\text{Placenta}} \text{Hemólise Fetal } Rh^+""",
                description = "Destruição imunológica das hemácias fetais por anticorpos maternos da classe IgG capazes de transpor a barreira placentária.",
                parametersInfo = "Profilaxia com imunoglobulina anti-Rh (imunização passiva) até 72 horas pós-parto neutraliza as hemácias fetais antes da sensibilização materna."
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Compatibilidade Transfusional de Hemácias",
                codeOrFormula = """\text{Doa hemácias se: Antígeno do Doador } \cap \text{ Anticorpo do Receptor} = \emptyset""",
                explanation = "Hemácias O não possuem antígenos A ou B; logo, não reagem com anticorpos do receptor."
            )
        ),
        goldTip = "Em transfusão de hemácias, olhe sempre para os ANTÍGENOS do doador e para os ANTICORPOS do receptor!",
        assetImagePath = "images/biologia/sistema_abo_rh.svg"
    )

    // =========================================================================
    // AULA bio_gen_05: Biotecnologia e Transgênicos
    // =========================================================================
    private val bundleBioGen05 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
A Biotecnologia contemporânea representa a convergência entre a biologia molecular, a genética e a engenharia para desenvolver produtos e processos biológicos aplicados à medicina, agricultura e conservação ambiental. No ENEM, a biotecnologia é uma das temáticas mais recorrentes, com destaque absoluto para os organismos geneticamente modificados (transgênicos), o teste de paternidade por perfil de DNA (*DNA fingerprinting*), a reação em cadeia da polimerase (PCR), as técnicas de clonagem e a edição genética por CRISPR-Cas9. As questões exigem compreensão precisa das etapas técnicas, dos papéis enzimáticos e das implicações éticas e socioambientais dessas inovações.

## 2. Objetivos de Aprendizagem
- Diferenciar **Organismo Geneticamente Modificado (OGM)** de **Transgênico**: todo transgênico é um OGM, mas um transgênico recebe obrigatoriamente material genético funcional de **outra espécie distinta**.
- Descrever a técnica do **DNA Recombinante**: ação das **Enzimas de Restrição** (endonucleases que cortam sítios palindrômicos específicos gerando pontas coesivas) e da **DNA Ligase** (que sela as ligações fosfodiéster).
- Explicar a produção de insulina humana recombinante em bactérias (*Escherichia coli*).
- Dominar o princípio da **PCR (Reação em Cadeia da Polimerase)**: desnaturação térmica (\( \sim 95^\circ\text{C} \)), anelamento de primers (\( \sim 55^\circ\text{C} \)) e extensão pela *Taq* Polimerase (\( \sim 72^\circ\text{C} \)).
- Interpretar corridas de **Eletroforese em Gel de Agarose**: migração de fragmentos de DNA (carga negativa do fosfato em direção ao polo positivo / ânodo), onde fragmentos menores migram mais rapidamente.
- Diferenciar **Clonagem Reprodutiva** (geração de um indivíduo completo idêntico, ex: Ovelha Dolly por transferência nuclear de célula somática) de **Clonagem Terapêutica** (geração de tecidos e órgãos a partir de células-tronco embrionárias pluripotentes).
- Caracterizar o sistema de edição gênica **CRISPR-Cas9**: endonuclease guiada por RNA guia complementar que realiza cortes cirúrgicos de precisão no genoma.

## 3. Pré-requisitos
- Estrutura do DNA, replicação e código genético universal (Aula bio_gen_01).
- Estrutura bacteriana e plasmídeos (Citologia).

## 4. Explicação Teórica Aprofundada

### 1. Tecnologia do DNA Recombinante e Transgênicos
![Biotecnologia: DNA Recombinante, Ciclos da PCR e Eletroforese](file:///android_asset/images/biologia/clonagem_dna_recombinante.svg)

A construção de um organismo transgênico baseia-se na universalidade do código genético:
1. **Identificação e Isolamento:** O gene de interesse (ex: gene da insulina humana ou gene da toxina *Bt* da bactéria *Bacillus thuringiensis*) é identificado no genoma doador.
2. **Clivagem com Enzimas de Restrição:** Endonucleases bacterianas reconhecem sequências palindrômicas específicas no DNA (ex: \( 5'-\text{GAATTC}-3' \) da enzima *EcoRI*) e clivam ambas as fitas de forma desalinhada, criando "extremidades coesivas" (*sticky ends*).
3. **Uso de Vetor:** Um plasmídeo bacteriano (anel extracromossômico de DNA) é clivado com a mesma enzima de restrição.
4. **Ligação:** O gene de interesse e o plasmídeo aberto são incubados juntos. A enzima **DNA Ligase** catalisa a formação das ligações fosfodiéster, gerando o plasmídeo recombinante.
5. **Transformação e Expressão:** O plasmídeo recombinante é inserido em bactérias hospedeiras, que passam a traduzir a proteína humana em biorreatores industriais.

*Atenção Biológica:* Como genes eucarióticos possuem íntrons e as bactérias não possuem maquinário de *splicing*, os cientistas utilizam a enzima viral **Transcriptase Reversa** sobre o mRNA maduro humano para produzir um DNA complementar livre de íntrons (**cDNA**).

### 2. A Reação em Cadeia da Polimerase (PCR)
Desenvolvida por Kary Mullis (Prêmio Nobel em 1993), a PCR permite amplificar milhões de cópias de uma sequência-alvo de DNA a partir de quantidades mínimas:
- **Componentes:** DNA molde, primers específicos (iniciadores de oligonucleotídeos), desoxirribonucleotídeos livres (dNTPs), tampão e **Taq Polimerase** (DNA polimerase termorresistente isolada da bactéria termófila *Thermus aquaticus*).
- **Ciclo Térmico:**
  1. *Desnaturação (\( 94-96^\circ\text{C} \)):* As pontes de hidrogênio são rompidas pelo calor, separando as duas fitas de DNA.
  2. *Anelamento (\( 50-65^\circ\text{C} \)):* Os primers ligam-se por complementaridade às extremidades da região alvo.
  3. *Extensão (\( 72^\circ\text{C} \)):* A Taq Polimerase sintetiza as fitas complementares no sentido \( 5' \to 3' \).
  A cada ciclo de 3 minutos, a quantidade de DNA dobra exponencialmente: \( N = N_0 \times 2^n \).

### 3. Eletroforese em Gel e Perfil de DNA (*DNA Fingerprinting*)
O teste de DNA forense e de paternidade baseia-se na análise de regiões não codificantes do genoma que contêm repetições curtas em tandem (**STRs** - *Short Tandem Repeats*), cujo número de repetições é altamente polimórfico entre indivíduos:
- O DNA fragmentado por enzimas ou amplificado por PCR é aplicado em poços de um gel de agarose submerso em tampão condutor.
- Como o grupo fosfato confere **carga elétrica líquida negativa** ao DNA em pH neutro, ao ligar a corrente elétrica contínua, as moléculas migram em direção ao **polo positivo (ânodo)**.
- O gel atua como uma peneira molecular: fragmentos curtos atravessam os poros com facilidade e migram mais longe; fragmentos longos sofrem atrito estérico e ficam retidos mais próximos do poço de partida.
- No teste de paternidade, **todas as bandas de DNA do filho que não foram herdadas da mãe biológica devem obrigatoriamente estar presentes no perfil do pai biológico**.

### 4. Clonagem: Reprodutiva vs. Terapêutica
- **Ovelha Dolly (1996):** Primeiro mamífero clonado a partir de célula adulta somática.
  - Procedimento: O núcleo diploide (\( 2n \)) de uma célula da glândula mamária de uma ovelha A foi transferido para um óvulo anucleado de uma ovelha B (**Transferência Nuclear de Célula Somática**). O embrião reconstituído foi implantado no útero de uma mãe de aluguel C. A ovelha Dolly nascida era geneticamente idêntica à doadora do núcleo (ovelha A), exceto pelo DNA mitocondrial, herdado do óvulo da ovelha B.
- **Células-Tronco:**
  - *Totipotentes:* Capazes de formar todo o organismo e os anexos embrionários (zigoto até mórula).
  - *Pluripotentes:* Capazes de originar todos os tecidos dos três folhetos embrionários, mas não a placenta (massa celular interna do blastocisto).

## 5. Definições e Conceitos-Chave
- **Transgênico:** Organismo que teve incorporado ao seu genoma um gene exógeno proveniente de outra espécie filogenética.
- **Enzima de Restrição:** "Tesoura molecular" biológica de origem bacteriana que cliva DNA em sítios palindrômicos específicos.
- **DNA Ligase:** "Cola molecular" que catalisa a formação da ligação éster entre nucleotídeos contíguos.
- **CRISPR-Cas9:** Sistema imune adaptativo procariótico reconfigurado para edição genética direcionada por RNA guia.

## 6. Exemplos Resolvidos e Demonstrações

### Exemplo 1: Interpretação de Teste de Paternidade por Eletroforese
**Problema:** Em uma investigação de paternidade, foram analisadas três bandas de DNA de uma criança. A mãe apresenta as bandas nas posições 1 e 4. A criança apresenta as bandas 1, 3 e 5. Dois homens (Pai 1 e Pai 2) são os supostos pais. O Pai 1 apresenta bandas nas posições 2, 3 e 5. O Pai 2 apresenta bandas nas posições 2, 4 e 6. Qual dos homens é o pai biológico da criança?
**Resolução:**
1. A criança tem as bandas 1, 3 e 5.
2. A banda 1 foi herdada da mãe (a mãe possui 1 e 4).
3. As bandas 3 e 5 da criança não estão presentes na mãe, portanto devem ter sido obrigatoriamente transmitidas pelo pai biológico.
4. O Pai 1 possui as bandas 3 e 5.
5. O Pai 2 não possui nem a banda 3 nem a banda 5.
**Conclusão:** O Pai 1 é o único compatível com a paternidade biológica da criança.

### Exemplo 2: Herança Genética na Ovelha Dolly
**Problema:** A ovelha Dolly foi produzida utilizando o núcleo da célula mamária de uma ovelha da raça Finn Dorset (branca), o óvulo enucleado de uma ovelha Scottish Blackface (cabeça preta) e o útero de uma terceira ovelha Scottish Blackface. Com qual das três ovelhas Dolly compartilhou o seu DNA nuclear e seu DNA mitocondrial?
**Resolução:**
1. O DNA nuclear residia no núcleo transplantado da ovelha Finn Dorset (branca). Logo, o fenótipo e o genoma nuclear de Dolly são 100% idênticos à Finn Dorset.
2. O citoplasma do óvulo que continha as mitocôndrias pertencia à ovelha doadora do óvulo (Scottish Blackface). Logo, o DNA mitocondrial de Dolly foi herdado da doadora do óvulo.
3. A mãe de aluguel apenas forneceu o ambiente uterino para gestação, sem contribuir com herança genética nuclear ou citoplasmática.

## 7. Aplicações Práticas e Contexto ENEM
- **Milho e Soja *Bt*:** Plantas transgênicas que expressam a proteína inseticida Cry do *Bacillus thuringiensis*, reduzindo o uso de agrotóxicos químicos contra lagartas.
- **Vacinas de mRNA (COVID-19):** Nanopartículas lipídicas contendo mRNA que codifica a proteína Spike do vírus, instruindo as células humanas a sintetizarem temporariamente o antígeno para desencadear imunidade protetora sem risco de infecção.

## 8. Exercícios de Fixação com Gabarito Comentado

### Questão 1 (ENEM / Autoral)
A técnica de clonagem molecular para produção de insulina humana em larga escala utiliza a bactéria *Escherichia coli*. No entanto, ao tentar inserir diretamente o gene da insulina extraído do núcleo de uma célula pancreática humana na bactéria, a bactéria falha em produzir a insulina correta porque:
A) as bactérias utilizam um código genético completamente diferente do código eucariótico.
B) o maquinário celular bacteriano não é capaz de processar os íntrons presentes no gene humano através do splicing.
C) a parede celular bacteriana de peptidoglicano degrada imediatamente qualquer proteína de origem animal.
D) as enzimas de restrição bacterianas clivam o mRNA humano logo após a sua tradução ribossômica.
E) os ribossomos bacterianos leem os códons no sentido 3' para 5', sintetizando a proteína invertida.

**Resolução:**
Os genes de células eucarióticas são divididos em éxons (codificantes) e íntrons (não-codificantes). Células bacterianas (procariontes) não possuem sistema de endomembranas nem espliceossomos para realizar o *splicing*. Se um gene eucariótico bruto for inserido em uma bactéria, ela traduzirá os íntrons como se fossem códons codificantes, gerando uma proteína aberrantemente longa e inativa. Por essa razão, a biotecnologia utiliza o cDNA gerado a partir do mRNA já processado pela transcriptase reversa.
**Gabarito:** B.

### Questão 2 (ENEM / Autoral)
Na eletroforese em gel de agarose utilizada para separar fragmentos de DNA obtidos por PCR, a migração das moléculas ocorre em direção ao polo positivo (ânodo). Essa migração direcional deve-se primordialmente ao fato de que:
A) as bases nitrogenadas adenina e timina possuem cargas positivas concentradas em seus anéis aromáticos.
B) os grupos fosfato do esqueleto fosfodiéster conferem ao DNA uma carga elétrica líquida negativa constante.
C) a desoxirribose sofre oxidação no polo negativo, gerando íons hidroxila que empurram a molécula.
D) as ligações de hidrogênio entre as fitas complementares polarizam o campo magnético do gel.
E) os primers adicionados durante a reação de PCR neutralizam as regiões hidrofílicas da molécula.

**Resolução:**
O esqueleto do DNA é formado pela alternância de pentoses e grupos fosfato (\( \text{PO}_4^{3-} \)). Em pH biológico neutro e no tampão de corrida, cada grupo fosfato perde prótons, carregando a molécula com uma densidade uniforme de carga negativa. Sob o campo elétrico, o DNA negativo é repelido pelo polo negativo (cátodo) e atraído pelo polo positivo (ânodo).
**Gabarito:** B.

## 9. Correção Comentada e Análise de Distratores
- Na Questão 1, a alternativa A está errada porque o código genético é praticamente universal (o mesmo códon traduz o mesmo aminoácido em humanos e bactérias). Essa universalidade é justamente o que torna a transgenia viável.
- Na Questão 2, a alternativa A erra a carga (DNA não é positivo).

## 10. Desafio de Domínio
Considere uma reação de PCR iniciada com apenas 5 moléculas de DNA fita dupla alvo. Desprezando perdas ou saturação de reagentes, quantas moléculas de DNA dupla-fita serão produzidas após 30 ciclos completos de amplificação?
*Cálculo:* \( N = N_0 \times 2^n = 5 \times 2^{30} \approx 5 \times 1,073 \times 10^9 \approx 5,36 \times 10^9 \) moléculas (mais de 5 bilhões de cópias em poucas horas).

## 11. Resumo Conceitual
- Transgênicos contêm genes funcionais de outra espécie inseridos artificialmente.
- O DNA recombinante usa enzimas de restrição (corte) e DNA ligase (união).
- A PCR multiplica sequências de DNA em ciclos térmicos com a enzima Taq Polimerase.
- Na eletroforese, o DNA negativo migra para o polo positivo, e fragmentos menores migram mais longe.
- A clonagem reprodutiva envolve transferência nuclear para óvulo anucleado.

## 12. Referências Bibliográficas
- Griffiths, A. J. et al. *Introdução à Genética*. 11ª ed. Guanabara Koogan, 2016.
- Nelson, D. L. & Cox, M. M. *Princípios de Bioquímica de Lehninger*. 7ª ed. Artmed, 2018.
- OpenStax. *Biology 2e: Biotechnology and Genomics*. Rice University, 2020.
- INEP. *Matriz de Referência do ENEM: Ciências da Natureza*.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Rota Bioquímica do DNA Recombinante",
                syntax = """\text{mRNA Maduro} \xrightarrow{\text{Transcriptase Reversa}} \text{cDNA} \xrightarrow[\text{DNA Ligase}]{\text{Enzima de Restrição}} \text{Plasmídeo Recombinante} \to \text{Bactéria Hospedeira}""",
                description = "Engenharia genética para expressão de proteínas eucarióticas em bactérias sem interferência de íntrons.",
                parametersInfo = "A universalidade do código genético viabiliza a tradução da proteína humana pelos ribossomos bacterianos."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Ciclo Térmico da Reação em Cadeia da Polimerase (PCR)",
                syntax = """\text{Desnaturação (95}^\circ\text{C)} \xrightarrow{\text{Primers}} \text{Anelamento (55}^\circ\text{C)} \xrightarrow{\text{Taq Polimerase}} \text{Extensão (72}^\circ\text{C)} \implies N = N_0 \times 2^n""",
                description = "Amplificação exponencial in vitro de fragmentos de DNA por ciclos térmicos sequenciais.",
                parametersInfo = "A enzima Taq Polimerase (de Thermus aquaticus) resiste a sucessivos ciclos de aquecimento a 95°C sem desnaturar."
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Construção de cDNA por Transcriptase Reversa",
                codeOrFormula = """\text{mRNA maduro (sem íntrons)} \xrightarrow{\text{Transcriptase Reversa}} \text{cDNA} \xrightarrow{\text{DNA Ligase}} \text{Plasmídeo}""",
                explanation = "Fundamental para expressar proteínas humanas em bactérias, superando a incapacidade procariótica de realizar splicing."
            )
        ),
        goldTip = "Em eletroforese no ENEM: a banda que correu mais longe é a MENOR em tamanho de pares de bases! O DNA sempre corre em direção ao polo POSITIVO (+).",
        assetImagePath = "images/biologia/clonagem_dna_recombinante.svg"
    )
}
