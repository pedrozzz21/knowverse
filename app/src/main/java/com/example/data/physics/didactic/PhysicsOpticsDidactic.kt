package com.example.data.physics.didactic

import com.example.data.math.CalculusDidacticBundle
import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample

/**
 * Conteúdo didático aprofundado de Física ENEM — Módulo 4: Óptica (Aulas fis_m5_a1 a fis_m5_a5).
 * Estrutura rigorosa em 12 seções por aula, fundamentada em Halliday & Resnick, Sears & Zemansky, Tipler e Matriz ENEM / INEP.
 */
object PhysicsOpticsDidactic {

    fun get(lessonId: String): CalculusDidacticBundle? {
        return when (lessonId) {
            "fis_m5_a1" -> bundleFisM5A1
            "fis_m5_a2" -> bundleFisM5A2
            "fis_m5_a3" -> bundleFisM5A3
            "fis_m5_a4" -> bundleFisM5A4
            "fis_m5_a5" -> bundleFisM5A5
            else -> null
        }
    }

    // =========================================================================
    // AULA fis_m5_a1: Fundamentos da Óptica Geométrica e Propagação da Luz
    // =========================================================================
    private val bundleFisM5A1 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
A Óptica Geométrica é o ramo da Física dedicado ao estudo da luz através do conceito de raios luminosos retilíneos, prescindindo em primeira aproximação dos efeitos ondulatórios intrínsecos de difração e interferência. Desde os primórdios da astronomia babilônica e grega até o desenvolvimento das primeiras câmeras escuras e a compreensão dos eclipses, a óptica geométrica explica como o ser humano interage visualmente com o ambiente à sua volta. No ENEM, este tema é explorado através de proporções geométricas, sombras, penumbras e dispositivos ópticos históricos.

## 2. Objetivos de Aprendizagem
- Classificar as fontes de luz em **primárias** (luminosas) e **secundárias** (iluminadas), e em pontuais e extensas.
- Diferenciar meios ópticos **transparentes**, **translúcidos** e **opacos**.
- Dominar os três **Princípios Fundamentais da Óptica Geométrica**:
  1. Princípio da Propagação Retilínea da Luz.
  2. Princípio da Independência dos Raios Luminosos.
  3. Princípio da Reversibilidade dos Raios Luminosos.
- Compreender a formação geométrica de **Sombra** e **Penumbra** a partir de fontes extensas.
- Explicar os mecanismos físicos dos **Eclipses Solar e Lunar**.
- Modelar matematicamente a **Câmara Escura de Orifício** através da semelhança de triângulos: \( \frac{i}{o} = \frac{p'}{p} \).

## 3. Pré-requisitos
- Geometria Plana: Semelhança de Triângulos e Teorema de Tales (Matemática Básica, Aula mb_l06).
- Ondas eletromagnéticas e luz visível (Física, Aula fis_m4_a1).

## 4. Explicação Teórica Aprofundada

### 1. Fontes de Luz e Meios Ópticos
- **Fontes Primárias (Corpos Luminosos):** Emitem luz própria decorrente de processos térmicos (filamento incandescente, Sol, chama de vela) ou de luminescência (lâmpadas LED, tubos fluorescentes, vaga-lumes).
- **Fontes Secundárias (Corpos Iluminados):** Não geram luz própria; apenas refletem difusamente a luz recebida de fontes primárias (a Lua, planetas, livros, paredes, pessoas).
- **Meios de Propagação:**
  - *Transparente:* A luz propaga-se segundo trajetórias regulares e previsíveis com baixíssima absorção, permitindo a visão nítida de objetos através dele (ar límpido, vácuo, lâmina fina de vidro).
  - *Translúcido:* A luz propaga-se segundo trajetórias irregulares e difusas devido a múltiplos espalhamentos internos; a luz passa, mas não permite a visão nítida de contornos (vidro fosco, papel vegetal, neblina).
  - *Opaco:* A luz não se propaga através do meio, sendo totalmente absorvida ou refletida na superfície (madeira, tijolo, chapa metálica).

### 2. Os Três Princípios da Óptica Geométrica
1. **Propagação Retilínea:** Em meios homogêneos, isotrópicos e transparentes, a luz propaga-se em linha reta. As trajetórias da energia são representadas por segmentos orientados denominados **raios de luz**.
2. **Independência dos Raios de Luz:** Quando dois feixes de luz se cruzam no espaço, cada um prossegue sua propagação como se o outro não existisse. Eles não se chocam, não se desviam e não se destroem mutuamente.
3. **Reversibilidade dos Raios:** O caminho percorrido pela luz entre dois pontos A e B independe do sentido de propagação: se você enxerga os olhos de um motorista pelo espelho retrovisor do carro, ele necessariamente consegue enxergar os seus olhos pelo mesmo espelho.

### 3. Sombra, Penumbra e Eclipses
- **Fonte Pontual:** Projeta sobre um anteparo apenas uma região central totalmente escura (**Sombra**) contornada por iluminação plena.
- **Fonte Extensa:** Projeta uma região central totalmente desprovida de luz (**Sombra ou Umbra**) circundada por uma região de transição parcialmente iluminada (**Penumbra**).
- **Eclipses Astronômicos:**
  - *Eclipse Solar:* A Lua posiciona-se exatamente entre o Sol e a Terra (fase de Lua Nova). A umbra da Lua projeta uma pequena mancha na superfície da Terra onde se observa o eclipse solar total; na região de penumbra circundante, observa-se o eclipse solar parcial.
  - *Eclipse Lunar:* A Terra posiciona-se exatamente entre o Sol e a Lua (fase de Lua Cheia). A Lua mergulha no cone de sombra projetado pela Terra no espaço, adquirindo coloração avermelhada ("Lua de Sangue") decorrente da refração e espalhamento da luz solar pela atmosfera terrestre.

### 4. A Câmara Escura de Orifício
Consiste em uma caixa fechada opaca com um orifício minúsculo em uma das faces e uma tela semitransparente (ou filme fotográfico) na face oposta. Devido à propagação estritamente retilínea dos raios luminosos:
- A luz proveniente do topo do objeto atinge a base da tela interna; a luz da base atinge o topo.
- Forma-se sobre a tela uma **imagem real e invertida**.
- Pela semelhança dos triângulos formados pelos raios que cruzam o orifício:
  \[ \frac{i}{o} = \frac{p'}{p} \]
  onde \( o \) é a altura do objeto, \( i \) é a altura da imagem, \( p \) é a distância do objeto ao orifício e \( p' \) é a profundidade da câmara escura.

## 5. Definições e Conceitos
- **Ano-Luz:** Unidade de comprimento astronômica correspondente à distância percorrida pela luz no vácuo durante um ano terrestre (\( \approx 9{,}46 \times 10^{15}\text{ m} \)).
- **Feixe Cônico Divergente:** Conjunto de raios que partem de um ponto em direção a áreas maiores.
- **Feixe Cônico Convergente:** Conjunto de raios que caminham convergindo para um mesmo ponto de foco.
- **Feixe Cilíndrico Paralelo:** Conjunto de raios paralelos entre si (como os raios solares que atingem a Terra devido à imensa distância do astro).

## 6. Exemplos Resolvidos Passo a Passo

### Exemplo 1: Determinação da Altura de um Edifício pela Sombra
Em um dia ensolarado, um poste vertical de \( 3\text{ metros} \) de altura projeta no solo plano uma sombra de \( 2\text{ metros} \). No mesmo instante, a sombra projetada por um edifício vizinho mede \( 24\text{ metros} \). Determine a altura do edifício.
- **Resolução:**
  1. Como o Sol está a uma distância astronômica, os raios solares que atingem o poste e o edifício são praticamente paralelos, formando triângulos retângulos semelhantes:
     \[ \frac{H_{\text{edifício}}}{h_{\text{poste}}} = \frac{S_{\text{edifício}}}{s_{\text{poste}}} \]
  2. Substituição dos valores:
     \[ \frac{H}{3} = \frac{24}{2} = 12 \implies H = 3 \cdot 12 = 36\text{ metros} \]
  - Resposta: O edifício mede \( 36\text{ metros} \) de altura.

### Exemplo 2: Imagem na Câmara Escura de Orifício
Um objeto luminoso de \( 10\text{ cm} \) de altura está situado a \( 50\text{ cm} \) do orifício de uma câmara escura que possui profundidade de \( 20\text{ cm} \). Qual será o tamanho da imagem projetada na parede interna da câmara?
- **Resolução:**
  1. Identificação dos dados: \( o = 10\text{ cm} \), \( p = 50\text{ cm} \), \( p' = 20\text{ cm} \).
  2. Relação de proporção:
     \[ \frac{i}{o} = \frac{p'}{p} \implies \frac{i}{10} = \frac{20}{50} = \frac{2}{5} \]
     \[ i = 10 \cdot 0{,}4 = 4\text{ cm} \]
  - Resposta: A imagem formada mede \( 4\text{ cm} \) de altura e projeta-se invertida.

## 7. Aplicações Práticas e Modelagem
- **Câmeras Fotográficas Modernas (Princípio do Diafragma):** O diafragma de uma câmera controla a abertura do orifício; orifícios muito pequenos ampliam a profundidade de campo tornando quase tudo em foco, reproduzindo a geometria da câmara escura.
- **Construção Civil e Insolação:** O estudo da trajetória solar e das sombras projetadas ao longo das estações do ano determina a eficiência energética, ventilação e conforto térmico de prédios sustentáveis.

## 8. Exercícios de Fixação
1. Qual dos princípios da óptica geométrica explica satisfatoriamente a formação de sombras com contornos nítidos quando um objeto opaco é iluminado por uma fonte pontual de luz?
2. Por que durante um eclipse lunar a Lua não desaparece totalmente do céu noturno, mas adquire um tom vermelho-tijolo característico?
3. Um motorista olha pelo espelho retrovisor e enxerga o passageiro no banco traseiro. Se o passageiro olhar para o mesmo retrovisor, ele conseguirá enxergar o motorista? Qual princípio da óptica garante essa resposta?

## 9. Correção Comentada dos Exercícios
1. O **Princípio da Propagação Retilínea da Luz**. Como os raios não fazem curvas espontâneas contornando o objeto opaco, a área atrás do corpo fica totalmente privada de radiação luminosa.
2. Porque parte dos raios solares tangencia a atmosfera da Terra sofrendo **refração** e **espalhamento**. As componentes azuis de alta frequência são espalhadas pela atmosfera (espalhamento Rayleigh), enquanto os comprimentos de onda mais longos (vermelhos) sofrem refração curvando-se para dentro do cone de sombra da Terra e atingindo a Lua.
3. **Sim, com certeza**. Isso é garantido pelo **Princípio da Reversibilidade dos Raios Luminosos**: o caminho óptico seguido pela luz do passageiro ao espelho e depois ao motorista é estritamente reversível no sentido oposto.

## 10. Desafio de Domínio
Considere uma câmara escura de orifício que projeta uma imagem nítida sobre um anteparo translúcido. Se alargarmos significativamente o orifício da câmara com uma broca, o que acontecerá com o brilho (luminosidade) e com a nitidez da imagem projetada? Explique fisicamente.
*Pista:* Pense na sobreposição de múltiplas imagens formadas por cada ponto do orifício ampliado.

## 11. Resumo e Pontos-Chave
- Fontes Primárias emitem luz própria; Secundárias refletem luz.
- Meios: Transparente (luz passa reta, imagem nítida), Translúcido (luz passa difusa), Opaco (não passa).
- Princípios: Propagação Retilínea, Independência dos Raios e Reversibilidade da Luz.
- Sombra (ausência total de luz) e Penumbra (parcialmente iluminada; exclusiva de fontes extensas).
- Eclipses: Solar (Lua entre Sol e Terra) e Lunar (Terra entre Sol e Lua).
- Câmara Escura: imagem real e invertida; relação \( \frac{i}{o} = \frac{p'}{p} \).

## 12. Referências Bibliográficas
- HALLIDAY, David; RESNICK, Robert; WALKER, Jearl. *Fundamentos de Física: Óptica e Física Moderna*. Vol. 4. 10. ed. LTC, 2016.
- SEARS, F. W.; ZEMANSKY, M. W. *Física IV: Óptica e Física Moderna*. 14. ed. Pearson, 2016.
- TIPLER, Paul A.; MOSCA, Gene. *Física para Cientistas e Engenheiros*. Vol. 2. 6. ed. LTC, 2009.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Equação da Câmara Escura de Orifício",
                syntax = "\\frac{i}{o} = \\frac{p'}{p}",
                description = "Proporção geométrica direta relacionando o tamanho do objeto e da imagem com as respectivas distâncias ao orifício.",
                parametersInfo = "i: \\text{tamanho da imagem}; \\quad o: \\text{tamanho do objeto}; \\quad p': \\text{profundidade da câmara}; \\quad p: \\text{distância do objeto}"
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Semelhança de Sombras Retilíneas",
                syntax = "\\frac{H}{S} = \\frac{h}{s} = \\tan\\theta_{\\text{solar}}",
                description = "Cálculo trigonométrico de alturas acessíveis ou inacessíveis por meio do comprimento de sombras projetadas simultaneamente.",
                parametersInfo = "H, h: \\text{alturas}; \\quad S, s: \\text{comprimentos das sombras}; \\quad \\theta: \\text{ângulo de elevação solar}"
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Inversão de Imagem na Câmara Escura",
                codeOrFormula = "\\text{Orientação} = \\text{Invertida Vertical e Horizontalmente}",
                explanation = "O cruzamento retilíneo dos raios de luz ao passarem pelo orifício central inverte tanto o sentido vertical (topo vai para a base) quanto lateral."
            ),
            LessonExample(
                title = "Projeção Solar em Eclipse Parcial",
                codeOrFormula = "\\text{Região Terrestre na Penumbra} \\implies \\text{Visualização de Eclipse Solar Parcial}",
                explanation = "A penumbra corresponde à região que recebe luz de apenas uma fração da superfície do disco solar extenso."
            )
        ),
        goldTip = "Lembre-se sempre: PENUMBRA só existe quando a fonte de luz for EXTENSA! Se a fonte de luz for pontual (um ponto microscópico emitindo raios), existe apenas SOMBRA e ILUMINAÇÃO, sem qualquer zona de penumbra intermediária!"
    )

    // =========================================================================
    // AULA fis_m5_a2: Espelhos Planos e Esféricos (Côncavos e Convexos)
    // =========================================================================
    private val bundleFisM5A2 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
A reflexão regular da luz sobre superfícies polidas é o princípio de funcionamento dos espelhos. Presentes desde o espelho plano do banheiro residencial até os espelhos parabólicos de telescópios espaciais como o James Webb, esses instrumentos ópticos têm a capacidade de manipular as frentes de onda luminosas para gerar imagens com diferentes tamanhos, orientações e naturezas. No ENEM, este tema destaca-se pela formação de imagens em **Espelhos Planos** (campo visual e simetria) e pela geometria dos **Espelhos Esféricos** (côncavos em faróis de carros e espelhos de maquiagem, convexos em retrovisores de ônibus e esquinas de garagens).

## 2. Objetivos de Aprendizagem
- Caracterizar com rigor as propriedades da imagem formada em um **Espelho Plano**: virtual, direita, de mesmo tamanho, simétrica em relação ao plano e **enantiomorfa** (reversa lateralmente).
- Determinar o **Campo Visual** de um observador diante de um espelho plano.
- Calcular a translação e rotação da imagem em espelhos planos móveis (\( \Delta s_{\text{imagem}} = 2 \Delta s_{\text{espelho}} \)).
- Deduzir e aplicar a fórmula para associação angular de espelhos planos: \( N = \frac{360^\circ}{\alpha} - 1 \).
- Identificar os elementos geométricos dos **Espelhos Esféricos de Gauss**: Vértice (\( V \)), Foco principal (\( F \)), Centro de curvatura (\( C \)) e Raio (\( R = 2f \)).
- Dominar os quatro raios notáveis em espelhos côncavos e convexos.
- Construir e classificar geometricamente as imagens nos cinco casos do espelho côncavo e o caso único do espelho convexo.

## 3. Pré-requisitos
- Princípios da Óptica Geométrica e Leis da Reflexão (\( \theta_i = \theta_r \)) (Física, Aula fis_m5_a1).
- Equações do 1º grau e manipulação algébrica de frações (Matemática Básica, Aulas mb_l03 e mb_l07).

## 4. Explicação Teórica Aprofundada

### 1. Espelhos Planos
Uma superfície plana e polida onde ocorre reflexão estritamente especular (regular):
- **Propriedades da Imagem:**
  1. **Virtual:** Formada no "interior" do espelho, pela interseção dos prolongamentos geométricos dos raios refletidos (não pode ser projetada em uma tela).
  2. **Direita:** Apresenta a mesma orientação vertical do objeto (não é invertida de cabeça para baixo).
  3. **Mesmo Tamanho:** O aumento linear é unitário (\( i = o \)).
  4. **Simétrica:** A distância do objeto ao espelho é rigorosamente igual à distância da imagem ao espelho (\( p = p' \)).
  5. **Enantiomorfa:** Há reversão lateral (a mão direita do objeto corresponde à mão esquerda da imagem refletida). É por isso que a palavra **AMBULÂNCIA** é escrita espelhada na frente do veículo: para que o motorista da frente leia corretamente pelo espelho retrovisor plano!
- **Associação de Dois Espelhos Planos:**
  Quando dois espelhos planos formam entre si um ângulo \( \alpha \) (em graus), o número \( N \) de imagens formadas para um objeto luminoso posicionado entre eles é:
  \[ N = \frac{360^\circ}{\alpha} - 1 \]
  (Válido diretamente para qualquer posição do objeto se \( 360^\circ/\alpha \) for par; e se for ímpar, o objeto deve estar no plano bissetor).

### 2. Espelhos Esféricos (Condições de Gauss)
Obtidos a partir de uma calota esférica polida e espelhada:
- **Espelho Côncavo:** A superfície refletora é a face interna da calota.
- **Espelho Convexo:** A superfície refletora é a face externa da calota.
- **Condições de Nitidez de Gauss:** Para que a imagem não sofra aberração esférica e tenha nitidez pontual, o ângulo de abertura da calota deve ser pequeno (\( \alpha \le 10^\circ \)) e os raios luminosos incidentes devem ser paraxiais (próximos do eixo principal e pouco inclinados).
- Sob as condições de Gauss:
  \[ f = \frac{R}{2} \]
  onde \( f \) é a distância focal e \( R \) é o raio de curvatura da esfera.

### 3. Os Quatro Raios Notáveis
1. Todo raio que incide paralelamente ao eixo principal reflete passando pelo **Foco principal** (ou seu prolongamento).
2. Todo raio que incide passando pelo **Foco principal** reflete paralelamente ao eixo principal (princípio da reversibilidade).
3. Todo raio que incide passando pelo **Centro de Curvatura** reflete sobre si mesmo (incide com ângulo de 0° em relação à normal).
4. Todo raio que incide no **Vértice** reflete simetricamente em relação ao eixo principal (\( \theta_i = \theta_r \)).

### 4. Construção de Imagens no Espelho Côncavo (5 Casos)
1. **Objeto além do Centro de Curvatura (\( p > 2f \)):**
   - Imagem: **Real, Invertida e Menor** (localizada entre \( C \) e \( F \)).
2. **Objeto exatamente sobre o Centro de Curvatura (\( p = 2f \)):**
   - Imagem: **Real, Invertida e de Mesmo Tamanho** (localizada sob \( C \)).
3. **Objeto entre o Centro de Curvatura e o Foco (\( f < p < 2f \)):**
   - Imagem: **Real, Invertida e Maior** (localizada além de \( C \)).
4. **Objeto exatamente no Foco Principal (\( p = f \)):**
   - **Imagem Imprópria** (os raios refletem estritamente paralelos e não se cruzam, formando imagem no infinito). Aplicação: faróis de automóveis e lanternas, onde a lâmpada é posicionada no foco de um espelho côncavo para gerar um feixe de luz cilíndrico paralelo de longo alcance!
5. **Objeto entre o Foco e o Vértice (\( p < f \)):**
   - Imagem: **Virtual, Direita e Maior** (localizada atrás do espelho). Aplicação: espelhos de maquiagem, barbear e odontologia, que ampliam os detalhes do rosto ou dos dentes.

### 5. Construção de Imagens no Espelho Convexo (Caso Único)
Para qualquer posição do objeto real diante de um espelho esférico convexo:
- A imagem é **SEMPRE: Virtual, Direita e Menor** (localizada entre o Vértice e o Foco virtual, atrás do espelho).
- **Vantagem e Aplicação no ENEM:** Como a imagem é reduzida, o espelho convexo proporciona um **Campo Visual Muito Mais Amplo** que o de um espelho plano de mesmo tamanho. É amplamente utilizado em espelhos retrovisores externos de ônibus e caminhões, esquinas cegas de garagens e lojas comerciais contra furtos.

## 5. Definições e Conceitos
- **Imagem Real:** Formada pelo cruzamento direto dos próprios raios de luz refletidos. Pode ser projetada em uma parede ou anteparo e é sempre invertida em relação ao objeto.
- **Imagem Virtual:** Formada pelo prolongamento geométrico tracejado dos raios refletidos. Fica atrás do espelho, não pode ser projetada e é sempre direita.
- **Aberração Esférica:** Defeito óptico que ocorre em espelhos esféricos de grande abertura onde raios marginais não convergem para um único ponto focal nítido.

## 6. Exemplos Resolvidos Passo a Passo

### Exemplo 1: Associação Angular de Espelhos Planos
Um estudante posiciona uma vela acesa entre dois espelhos planos articulados que formam entre si um ângulo de \( 60^\circ \). Quantas imagens distintas da chama da vela o estudante conseguirá visualizar?
- **Resolução:**
  1. Fórmula do número de imagens:
     \[ N = \frac{360^\circ}{\alpha} - 1 \]
  2. Substituição do ângulo \( \alpha = 60^\circ \):
     \[ N = \frac{360^\circ}{60^\circ} - 1 = 6 - 1 = 5\text{ imagens} \]
  - Resposta: O estudante visualizará \( 5\text{ imagens} \) da chama.

### Exemplo 2: Identificação do Espelho por Suas Imagens
Um motorista de ônibus observa um passageiro sentado através de um espelho e nota que a imagem do passageiro é direita e reduzida. O mesmo motorista, ao se maquiar em casa, posiciona o rosto muito próximo de outro espelho e enxerga seus poros muito aumentados e direitos. Quais são os tipos de espelhos utilizados em cada situação?
- **Resolução:**
  1. Imagem direita e menor: característica exclusiva do **Espelho Convexo** (utilizado para ampliar o campo de visão no ônibus).
  2. Imagem direita e ampliada com objeto próximo (\( p < f \)): caso exclusivo do **Espelho Côncavo** com objeto entre o foco e o vértice.
  - Resposta: No ônibus trata-se de um espelho convexo; na maquiagem, trata-se de um espelho côncavo.

## 7. Aplicações Práticas e Modelagem
- **Usinas Termossolares de Concentração (CSP):** Milhares de espelhos côncavos ou heliostatos planos concentram a radiação solar direta sobre uma torre receptora central, aquecendo sais fundidos a mais de \( 500^\circ\text{C} \) para gerar vapor e eletricidade limpa.
- **Telescópio Espacial Hubble e James Webb:** Utilizam espelhos refletores primários côncavos gigantes para captar luz fraca de galáxias primordiais sem sofrer a aberração cromática típica das lentes refratoras.

## 8. Exercícios de Fixação
1. Um homem afasta-se de um espelho plano vertical a uma velocidade constante de \( 1{,}5\text{ m/s} \). Com qual velocidade a sua imagem se afasta do próprio homem?
2. Um espelho esférico côncavo de raio de curvatura \( R = 40\text{ cm} \) é utilizado para produzir um feixe de luz perfeitamente cilíndrico e paralelo a partir de uma pequena lâmpada incandescente. A que distância do vértice do espelho a lâmpada deve ser posicionada?
3. Cite duas características obrigatórias da imagem formada por um espelho esférico convexo para um objeto real.

## 9. Correção Comentada dos Exercícios
1. A distância entre o homem e sua imagem é \( D = 2p \). Derivando no tempo: \( v_{\text{rel}} = 2 \cdot v_{\text{homem}} = 2 \cdot 1{,}5 = 3{,}0\text{ m/s} \). A imagem afasta-se do homem a \( 3{,}0\text{ m/s} \).
2. Para que os raios reflitam paralelos, a lâmpada deve estar localizada exatamente sobre o **Foco Principal**. Como \( f = \frac{R}{2} \): \( f = \frac{40}{2} = 20\text{ cm} \). A lâmpada deve ficar a \( 20\text{ cm} \) do vértice.
3. A imagem é **sempre Virtual, Direita e Menor** que o objeto, posicionada atrás do espelho entre o vértice e o foco virtual.

## 10. Desafio de Domínio
Qual deve ser o comprimento vertical mínimo de um espelho plano preso a uma parede vertical para que uma pessoa de altura \( H = 1{,}80\text{ m} \) consiga se enxergar de corpo inteiro da cabeça aos pés, e a que altura do chão a borda inferior do espelho deve ser fixada sabendo que os olhos da pessoa estão a \( 1{,}70\text{ m} \) do solo?
*Pista:* Aplique semelhança de triângulos traçando os raios refletidos que saem dos olhos e vão até a ponta dos pés e ao topo da cabeça.

## 11. Resumo e Pontos-Chave
- Espelho Plano: imagem sempre virtual, direita, do mesmo tamanho, simétrica (\( p = p' \)) e enantiomorfa.
- Associação de espelhos planos: \( N = \frac{360^\circ}{\alpha} - 1 \).
- Espelhos de Gauss: \( f = R/2 \).
- Espelho Côncavo: 5 casos. Pode gerar imagem real (invertida) ou virtual (direita e maior, se \( p < f \)). No foco (\( p = f \)): raios paralelos (farol).
- Espelho Convexo: caso único (sempre virtual, direita e menor); amplia o campo visual (ônibus, lojas e garagens).

## 12. Referências Bibliográficas
- HALLIDAY, David; RESNICK, Robert; WALKER, Jearl. *Fundamentos de Física: Óptica e Física Moderna*. Vol. 4. 10. ed. LTC, 2016.
- SEARS, F. W.; ZEMANSKY, M. W. *Física IV: Óptica e Física Moderna*. 14. ed. Pearson, 2016.
- TIPLER, Paul A.; MOSCA, Gene. *Física para Cientistas e Engenheiros*. Vol. 2. 6. ed. LTC, 2009.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Associação Angular de Espelhos Planos",
                syntax = "N = \\frac{360^\\circ}{\\alpha} - 1",
                description = "Cálculo do número de imagens virtuais formadas por reflexões sucessivas entre dois espelhos planos formando um ângulo alfa.",
                parametersInfo = "N: \\text{número total de imagens}; \\quad \\alpha: \\text{ângulo entre as superfícies refletoras em graus}"
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Distância Focal dos Espelhos Esféricos de Gauss",
                syntax = "f = \\frac{R}{2}",
                description = "Relação para pequenas calotas paraxiais onde a distância focal é exatamente a metade do raio de curvatura.",
                parametersInfo = "f: \\text{distância focal (m ou cm)}; \\quad R: \\text{raio da calota esférica (m ou cm)}"
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Velocidade Relativa da Imagem no Espelho Móvel",
                syntax = "v_{\\text{imagem/solo}} = 2 \\cdot v_{\\text{espelho/solo}}",
                description = "Efeito de arraste multiplicador decorrente do deslocamento translacional da superfície refletora.",
                parametersInfo = "v_{\\text{espelho}}: \\text{velocidade de deslocamento do espelho em relação ao solo}"
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Campo Visual do Retrovisor Convexo",
                codeOrFormula = "\\text{Tamanho Imagem Reduzido} \\implies \\text{Maior Ângulo Sólido de Varredura Visual}",
                explanation = "A formação de imagens menores e compactas no espelho convexo permite abranger uma extensão angular drasticamente maior do tráfego traseiro."
            ),
            LessonExample(
                title = "Farol de Automóvel e Foco Côncavo",
                codeOrFormula = "p = f \\implies p' \\to \\infty \\implies \\text{Feixe Cilíndrico de Raios Paralelos}",
                explanation = "A lâmpada montada exatamente sobre o ponto focal principal reflete todos os raios luminosos paralelamente ao eixo da pista."
            )
        ),
        goldTip = "O ENEM adora perguntar: 'Por que usamos espelho convexo no retrovisor de ônibus ou esquinas?' Responda de forma direta: 'Porque ele SEMPRE produz imagens virtuais, direitas e MENORES, o que resulta em um CAMPO VISUAL MUITO MAIOR para o motorista ou vigilante!'"
    )

    // =========================================================================
    // AULA fis_m5_a3: Refração Luminosa, Lei de Snell e Reflexão Total
    // =========================================================================
    private val bundleFisM5A3 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
Quando a luz viaja através do cosmos e atinge a atmosfera terrestre, ou quando mergulha da água para o ar, ela altera sua velocidade de propagação e desvia sua trajetória geométrica. Esse fenômeno fascinante é a **Refração Luminosa**. Regida pela histórica **Lei de Snell-Descartes**, a refração é o mecanismo subjacente à dispersão prismática que dá origem ao arco-íris, à formação de miragens escaldantes no asfalto desértico e à revolução das telecomunicações contemporâneas através da **Reflexão Interna Total nas Fibras Ópticas**, um dos temas tecnológicos prediletos da Matriz de Referência do ENEM.

## 2. Objetivos de Aprendizagem
- Definir o **Índice de Refração Absoluto** de um meio óptico: \( n = \frac{c}{v} \ge 1 \).
- Deduzir e aplicar a **Lei de Snell-Descartes**: \( n_1 \cdot \operatorname{sen}\theta_1 = n_2 \cdot \operatorname{sen}\theta_2 \).
- Compreender a regra do desvio angular: do meio menos refringente para o mais refringente o raio **aproxima-se da normal**; do mais para o menos refringente **afasta-se da normal**.
- Analisar a **Dispersão Cromática da Luz Branca** em prismas, compreendendo por que a luz violeta desvia mais que a vermelha (\( n_{\text{violeta}} > n_{\text{vermelha}} \)).
- Compreender a ilusão de profundidade em **Dioptros Planos**: \( \frac{p_{\text{ap}}}{p_{\text{real}}} = \frac{n_{\text{obs}}}{n_{\text{objeto}}} \).
- Deduzir as duas condições obrigatórias para a ocorrência da **Reflexão Interna Total**:
  1. A luz deve propagar-se do meio mais refringente para o meio menos refringente (\( n_1 > n_2 \)).
  2. O ângulo de incidência deve superar o **Ângulo Limite**: \( \theta_i > \theta_L \), onde \( \operatorname{sen}\theta_L = \frac{n_{\text{menor}}}{n_{\text{maior}}} \).
- Explicar o funcionamento das Fibras Ópticas e a física das Miragens térmicas.

## 3. Pré-requisitos
- Fenômenos ondulatórios: reflexão e refração (Física, Aula fis_m4_a2).
- Trigonometria básica: função seno para ângulos notáveis (Matemática Básica, Aula mb_l10).

## 4. Explicação Teórica Aprofundada

### 1. O Índice de Refração Absoluto (\( n \))
É uma grandeza adimensional que mede o quanto a velocidade da luz é reduzida ao penetrar em um determinado meio material em comparação com o vácuo:
\[ n = \frac{c}{v} \]
- \( c \approx 3 \times 10^8\text{ m/s} \): velocidade da luz no vácuo.
- \( v \): velocidade da luz no meio material considerado (\( v \le c \)).
- Como \( v \le c \), tem-se sempre \( n \ge 1 \). No vácuo, \( n = 1 \); no ar, \( n \approx 1{,}0003 \approx 1 \); na água, \( n \approx 1{,}33 = 4/3 \); no vidro comum, \( n \approx 1{,}5 \); no diamante puro, \( n \approx 2{,}42 \).
- Quanto maior o índice \( n \), mais **refringente** é o meio e mais lenta a luz viaja através dele.

### 2. A Lei de Snell-Descartes
Relaciona os ângulos de incidência (\( \theta_1 \)) e de refração (\( \theta_2 \)), medidos **sempre em relação à reta normal** à interface de separação:
\[ n_1 \cdot \operatorname{sen}\theta_1 = n_2 \cdot \operatorname{sen}\theta_2 \]
- **Comportamento Angular:**
  - *Do Menos para o Mais Refringente (\( n_1 < n_2 \)):* A velocidade diminui (\( v_2 < v_1 \)) e o raio de luz **aproxima-se da normal** (\( \theta_2 < \theta_1 \)).
  - *Do Mais para o Menos Refringente (\( n_1 > n_2 \)):* A velocidade aumenta (\( v_2 > v_1 \)) e o raio de luz **afasta-se da normal** (\( \theta_2 > \theta_1 \)).

### 3. Dispersão Cromática da Luz
A velocidade da luz em meios materiais transparentes depende sutilmente da sua frequência (da sua cor). O índice de refração não é rigorosamente constante para todas as cores:
\[ n_{\text{violeta}} > n_{\text{azul}} > n_{\text{verde}} > n_{\text{amarelo}} > n_{\text{vermelho}} \]
- Ao penetrar em um prisma de vidro transparente a partir do ar, a luz violeta (maior frequência) sofre a maior redução de velocidade e, portanto, **sofre o maior desvio angular**. A luz vermelha (menor frequência) sofre o menor desvio.
- Esse espalhamento angular das diferentes cores componentes decompõe a luz branca em seu espectro contínuo: é a gênese do **Arco-Íris** (que envolve refração, dispersão cromática e reflexão interna nas gotículas de chuva em suspensão na atmosfera).

### 4. Dioptro Plano e Profundidade Aparente
Quando um pescador observa um peixe de fora d'água, os raios que saem do peixe sofrem refração ao passar da água (\( n \approx 1{,}33 \)) para o ar (\( n \approx 1 \)), afastando-se da normal. O prolongamento retilíneo dos raios faz com que o pescador enxergue a imagem do peixe **mais próxima da superfície do que ele realmente está**:
\[ \frac{p'}{p} = \frac{n_{\text{observador}}}{n_{\text{objeto}}} \implies p' = p \cdot \frac{n_{\text{ar}}}{n_{\text{água}}} \approx \frac{3}{4} p \]
Se um pássaro mergulhador atirar seu bico exatamente onde vê o peixe, errará o bote por cima; predadores naturais (como o martim-pescador) já trazem gravado geneticamente o ângulo de compensação refrativa!

### 5. Reflexão Interna Total e Ângulo Limite
Ao passar do meio mais refringente para o menos refringente (\( n_1 > n_2 \)), o raio afasta-se da normal. Aumentando progressivamente o ângulo de incidência \( \theta_1 \), o ângulo de refração atinge o valor extremo de \( \theta_2 = 90^\circ \) (raio rasante à interface). O ângulo de incidência correspondente é denominado **Ângulo Limite (\( \theta_L \))**:
\[ n_1 \cdot \operatorname{sen}\theta_L = n_2 \cdot \operatorname{sen}(90^\circ) \implies \operatorname{sen}\theta_L = \frac{n_2}{n_1} = \frac{n_{\text{menor}}}{n_{\text{maior}}} \]
- **Condições Mandatórias para Reflexão Interna Total:**
  1. A luz deve caminhar do **meio mais refringente para o meio menos refringente** (\( n_{\text{origem}} > n_{\text{destino}} \)).
  2. O ângulo de incidência deve ser estritamente **maior que o ângulo limite**: \( \theta_i > \theta_L \).
  Nessas condições, **nenhuma fração de luz é refratada**: 100% da energia luminosa reflete de volta para o interior do meio original sem qualquer perda!

### 6. A Física da Fibra Óptica e das Miragens
- **Fibra Óptica:** Composta por um filamento ultrafino e flexível de vidro de alta pureza estruturado em duas camadas: o **Núcleo** (com índice de refração \( n_{\text{núcleo}} \)) envolvido por uma **Casca** de menor refringência (\( n_{\text{casca}} < n_{\text{núcleo}} \)). O feixe de luz laser injetado na ponta atinge a parede interna sob um ângulo superior ao limite (\( \theta > \theta_L \)), sofrendo sucessivas reflexões totais perfeitas até a outra extremidade com atenuação desprezível, transmitindo terabits por segundo à velocidade da luz.
- **Miragens no Asfalto Quente:** Em dias de sol intenso, o asfalto aquece e cria uma camada de ar rente ao solo muito quente e, portanto, menos densa e com menor índice de refração (\( n \)). Os raios solares que descem do céu atravessam camadas de índices decrescentes até sofrerem reflexão interna total antes de tocar o chão, curvando-se para cima até atingirem os olhos do motorista. O cérebro projeta retilineamente esses raios no chão, interpretando a imagem azulada do céu refletido como se houvesse uma poça de água na pista!

## 5. Definições e Conceitos
- **Meio Refringente:** Meio material no qual a luz propaga-se com velocidade inferior à do vácuo.
- **Raio Rasante:** Raio luminoso refratado que caminha paralelamente à linha da interface divisória (\( \theta_2 = 90^\circ \)).
- **Endoscópio Médico:** Cabo flexível de fibra óptica introduzido no trato gastrointestinal ou cirúrgico para iluminar e capturar imagens em tempo real de órgãos internos por reflexão total.

## 6. Exemplos Resolvidos Passo a Passo

### Exemplo 1: Aplicação da Lei de Snell
Um raio de luz monocromática propaga-se no ar (\( n_{\text{ar}} = 1{,}0 \)) e incide na superfície de um bloco de vidro com ângulo de incidência \( \theta_1 = 30^\circ \). Sabendo que o índice de refração do vidro é \( n_{\text{vidro}} = \sqrt{3} \), determine o seno do ângulo de refração \( \theta_2 \).
- **Resolução:**
  1. Lei de Snell-Descartes:
     \[ n_{\text{ar}} \cdot \operatorname{sen}\theta_1 = n_{\text{vidro}} \cdot \operatorname{sen}\theta_2 \]
  2. Substituição dos valores:
     \[ 1{,}0 \cdot \operatorname{sen}(30^\circ) = \sqrt{3} \cdot \operatorname{sen}\theta_2 \]
     \[ 1{,}0 \cdot 0{,}5 = \sqrt{3} \cdot \operatorname{sen}\theta_2 \implies \operatorname{sen}\theta_2 = \frac{0{,}5}{\sqrt{3}} = \frac{1}{2\sqrt{3}} = \frac{\sqrt{3}}{6} \approx 0{,}288 \]
  - Resposta: O seno do ângulo de refração é \( \frac{\sqrt{3}}{6} \). Como \( n_2 > n_1 \), o raio aproximou-se da normal.

### Exemplo 2: Determinação do Ângulo Limite Água-Ar
Determine o ângulo limite de incidência para que ocorra reflexão total na fronteira entre a água (\( n_{\text{água}} = 4/3 \)) e o ar (\( n_{\text{ar}} = 1 \)).
- **Resolução:**
  1. Para que haja reflexão total, a luz deve viajar da água para o ar (\( n_{\text{água}} > n_{\text{ar}} \)).
  2. Fórmula do ângulo limite:
     \[ \operatorname{sen}\theta_L = \frac{n_{\text{menor}}}{n_{\text{maior}}} = \frac{1}{4/3} = \frac{3}{4} = 0{,}75 \]
  - Resposta: O ângulo limite é aquele cujo seno vale \( 0{,}75 \) (\( \theta_L \approx 48{,}6^\circ \)). Qualquer raio luminoso com ângulo de incidência superior a \( 48{,}6^\circ \) sofrerá reflexão total na água.

## 7. Aplicações Práticas e Modelagem
- **Brilho e Lapidação do Diamante:** O diamante possui índice de refração altíssimo (\( n \approx 2{,}42 \)), o que resulta em um ângulo limite minúsculo (\( \theta_L \approx 24^\circ \)). A lapidação em facetas brilhantes garante que quase toda a luz incidente sofra múltiplas reflexões internas totais antes de sair pelo topo, gerando seu lendário "fogo" e cintilação.
- **Prismas de Reflexão Total em Binóculos:** Binóculos de alta qualidade utilizam prismas de Porro em vez de espelhos metálicos para inverter a imagem, porque a reflexão total tem rendimento de 100% de reflexão (espelhos de alumínio absorvem cerca de 10% a 15% da luz incidente).

## 8. Exercícios de Fixação
1. Um raio de luz atinge perpendicularmente a superfície plana de separação entre o ar e a água (\( \theta_1 = 0^\circ \)). O raio sofrerá desvio angular de sua rota original? E a sua velocidade sofrerá alteração?
2. Em uma fibra óptica de telecomunicações, o índice de refração do núcleo deve ser maior, menor ou igual ao da casca circundante? Justifique com base no fenômeno da reflexão total.
3. Por que uma piscina de água límpida sempre parece ser mais rasa do que a sua profundidade real quando a observamos da borda externa?

## 9. Correção Comentada dos Exercícios
1. Pela Lei de Snell: \( n_1 \operatorname{sen}(0^\circ) = n_2 \operatorname{sen}\theta_2 \implies \operatorname{sen}\theta_2 = 0 \implies \theta_2 = 0^\circ \). O raio **NÃO sofre desvio angular**. Contudo, como houve mudança de meio (\( n_{\text{água}} > n_{\text{ar}} \)), **sua velocidade diminui obrigatoriamente** (\( v = c/n \)). (Houve refração sem desvio geométrico!).
2. O índice do núcleo deve ser **obrigatoriamente MAIOR** que o da casca (\( n_{\text{núcleo}} > n_{\text{casca}} \)). Uma das condições indispensáveis para a ocorrência da reflexão interna total é a propagação do meio mais refringente para o menos refringente.
3. Devido à **refração luminosa no dioptro plano ar-água**: os raios provenientes dos azulejos do fundo da piscina sofrem refração ao passar da água para o ar afastando-se da reta normal. O cérebro projeta retilineamente os raios criando uma imagem virtual do fundo elevada em relação à sua posição real.

## 10. Desafio de Domínio
A luz solar cruza o vácuo interplanetário e atravessa as camadas de gases da atmosfera da Terra antes de atingir nossos olhos ao entardecer. Devido à refração contínua decorrente do aumento progressivo da densidade do ar em direção ao solo, a atmosfera curva os raios solares para baixo. Quando observamos o disco solar cruzar a linha do horizonte no pôr do sol, o Sol geométrico real já está abaixo do horizonte ou ainda está acima?
*Pista:* Relacione a curvatura dos raios luminosos com a posição aparente da imagem projetada.

## 11. Resumo e Pontos-Chave
- Índice de refração absoluto: \( n = \frac{c}{v} \ge 1 \).
- Lei de Snell: \( n_1 \operatorname{sen}\theta_1 = n_2 \operatorname{sen}\theta_2 \).
- Do meio menos para o mais refringente: aproxima-se da normal (\( v \) cai). Do mais para o menos: afasta-se da normal (\( v \) sobe).
- Dispersão: \( n_{\text{violeta}} > n_{\text{vermelha}} \) (violeta desvia mais no prisma).
- Dioptro plano: imagem do objeto no fundo fica mais rasa.
- Reflexão Total: exige (1) ir do meio mais para o menos refringente e (2) \( \theta_i > \theta_L \), com \( \operatorname{sen}\theta_L = \frac{n_{\text{menor}}}{n_{\text{maior}}} \).
- Aplicação suprema: fibra óptica (núcleo mais refringente que a casca).

## 12. Referências Bibliográficas
- HALLIDAY, David; RESNICK, Robert; WALKER, Jearl. *Fundamentos de Física: Óptica e Física Moderna*. Vol. 4. 10. ed. LTC, 2016.
- SEARS, F. W.; ZEMANSKY, M. W. *Física IV: Óptica e Física Moderna*. 14. ed. Pearson, 2016.
- TIPLER, Paul A.; MOSCA, Gene. *Física para Cientistas e Engenheiros*. Vol. 2. 6. ed. LTC, 2009.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Índice de Refração Absoluto",
                syntax = "n = \\frac{c}{v}",
                description = "Razão adimensional entre a velocidade da luz no vácuo e a velocidade de fase no meio óptico.",
                parametersInfo = "c = 3 \\times 10^8\\text{ m/s}; \\quad v: \\text{velocidade no meio (m/s)}; \\quad n \\ge 1"
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Lei de Snell-Descartes",
                syntax = "n_1 \\cdot \\operatorname{sen}\\theta_1 = n_2 \\cdot \\operatorname{sen}\\theta_2",
                description = "Equação fundamental que governa a refração angular da luz entre dois meios dielétricos isotrópicos.",
                parametersInfo = "n_1, n_2: \\text{índices dos meios}; \\quad \\theta_1, \\theta_2: \\text{ângulos medidos em relação à reta normal}"
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Condição do Ângulo Limite para Reflexão Total",
                syntax = "\\operatorname{sen}\\theta_L = \\frac{n_{\\text{menor}}}{n_{\\text{maior}}} \\quad (\\theta_i > \\theta_L)",
                description = "Expressão do ângulo crítico a partir do qual ocorre reflexão interna total sem qualquer transmissão refratada.",
                parametersInfo = "\\theta_L: \\text{ângulo limite de incidência}; \\quad n_{\\text{maior}}: \\text{meio de origem}; \\quad n_{\\text{menor}}: \\text{meio externo}"
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Reflexão Interna Total em Fibra Óptica",
                codeOrFormula = "n_{\\text{núcleo}} > n_{\\text{casca}} \\implies \\operatorname{sen}\\theta_L = \\frac{n_{\\text{casca}}}{n_{\\text{núcleo}}}",
                explanation = "A contenção dos pulsos luminosos de internet de alta velocidade baseia-se na reflexão total na interface núcleo-casca."
            ),
            LessonExample(
                title = "Profundidade Aparente no Dioptro Plano",
                codeOrFormula = "h_{\\text{aparente}} = h_{\\text{real}} \\cdot \\frac{n_{\\text{ar}}}{n_{\\text{água}}} = \\frac{h}{1{,}33} \\approx 0{,}75 \\cdot h",
                explanation = "A refração dos raios emergentes faz o fundo da piscina ou o peixe parecer 25% mais próximo da superfície do que a realidade física."
            )
        ),
        goldTip = "Para o ENEM, grave as duas condições indispensáveis da REFLEXÃO TOTAL: (1) O raio deve vir obrigatoriamente do meio MAIS refringente para o MENOS refringente (ex: vidro para o ar, núcleo para a casca); (2) O ângulo de incidência deve ser MAIOR que o ângulo limite (sen L = n_menor / n_maior). Se a luz tentar ir do ar para o vidro, NUNCA haverá reflexão total!"
    )

    // =========================================================================
    // AULA fis_m5_a4: Lentes Esféricas Delgadas e Equação de Gauss
    // =========================================================================
    private val bundleFisM5A4 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
As **Lentes Esféricas Delgadas** representam uma das aplicações mais transformadoras da Física na história da civilização humana. Da correção de deficiências visuais de bilhões de indivíduos através de óculos e lentes de contato à observação de microrganismos celulares (microscópios) e galáxias distantes (telescópios refratores), o comportamento óptico das lentes é governado por duas refrações sucessivas nas interfaces esféricas que delimitam o meio transparente. No ENEM, este tema é cobrado com frequência integrando o traçado geométrico de raios, a **Equação de Gauss** e o cálculo analítico do "grau" (vergência).

## 2. Objetivos de Aprendizagem
- Classificar morfologicamente as lentes em lentes de **bordas finas** (biconvexa, plano-convexa, côncavo-convexa) e de **bordas grossas** (bicôncava, plano-côncava, convexo-côncava).
- Compreender o comportamento óptico: lentes **convergentes** vs. **divergentes** no ar (\( n_{\text{lente}} > n_{\text{meio}} \)).
- Identificar os elementos geométricos: Centro Óptico (\( O \)), Focos principais (\( F \) e \( F' \)), Pontos Antiprincipais (\( A \) e \( A' \)) e Distância Focal (\( f \)).
- Dominar os raios notáveis em lentes delgadas convergentes e divergentes.
- Construir e classificar geometricamente as imagens nos cinco casos da lente convergente e no caso único da lente divergente.
- Aplicar a **Equação de Gauss**: \( \frac{1}{f} = \frac{1}{p} + \frac{1}{p'} \).
- Calcular o **Aumento Linear Transversal**: \( A = \frac{i}{o} = -\frac{p'}{p} = \frac{f}{f - p} \).
- Definir **Vergência (Grau da Lente)** em dioptrias (\( \text{di} \)): \( V = \frac{1}{f} \).

## 3. Pré-requisitos
- Refração Luminosa e Lei de Snell-Descartes (Física, Aula fis_m5_a3).
- Espelhos Esféricos: analogia dos raios notáveis e convenção de sinais (Física, Aula fis_m5_a2).

## 4. Explicação Teórica Aprofundada

### 1. Morfologia e Comportamento Óptico
Uma lente delgada é um sistema óptico constituído por um meio transparente homogêneo delimitado por duas superfícies esféricas (ou uma esférica e uma plana), cuja espessura central é desprezível comparada aos raios de curvatura:
- **Bordas Finas (Convergentes no ar):** Mais espessas no centro do que nas bordas. Quando imersas em meio menos refringente (\( n_{\text{lente}} > n_{\text{ar}} \)), refratam feixes paralelos convergindo-os para um ponto focal real após a lente.
  - Símbolo esquemático: segmento de reta com setas apontando para fora (\( \updownarrow \)).
- **Bordas Grossas (Divergentes no ar):** Mais espessas nas bordas do que no centro. Quando imersas no ar, espalham os feixes paralelos divergindo-os a partir de um ponto focal virtual antes da lente.
  - Símbolo esquemático: segmento de reta com setas invertidas apontando para dentro.
- *Inversão de Comportamento:* Se uma lente de vidro (\( n = 1{,}5 \)) de bordas finas for mergulhada em dissulfeto de carbono (\( n = 1{,}63 \)), seu comportamento inverte-se, passando a agir como **divergente**!

### 2. Raios Notáveis em Lentes Delgadas
1. Todo raio que incide paralelamente ao eixo principal refrata passando pelo **Foco-Imagem** (\( F' \)) (ou seu prolongamento).
2. Todo raio que incide passando pelo **Foco-Objeto** (\( F \)) refrata saindo paralelamente ao eixo principal.
3. Todo raio que incide passando pelo **Centro Óptico (\( O \))** atravessa a lente **sem sofrer qualquer desvio angular**.

### 3. Formação de Imagens na Lente Convergente (5 Casos)
Idêntica à sequência do espelho côncavo:
1. **Objeto antes do Ponto Antiprincipal Objeto (\( p > 2f \)):**
   - Imagem: **Real, Invertida e Menor** (localizada entre \( F' \) e \( A' \)). Exemplo: máquina fotográfica e olho humano.
2. **Objeto sobre o Ponto Antiprincipal (\( p = 2f \)):**
   - Imagem: **Real, Invertida e de Mesmo Tamanho** (localizada sobre \( A' \)). Exemplo: copiadoras mecânicas xérox (escala 1:1).
3. **Objeto entre o Ponto Antiprincipal e o Foco (\( f < p < 2f \)):**
   - Imagem: **Real, Invertida e Maior** (localizada além de \( A' \)). Exemplo: projetores de cinema e slides.
4. **Objeto no Foco Principal (\( p = f \)):**
   - **Imagem Imprópria** (raios emergem estritamente paralelos).
5. **Objeto entre o Foco e o Centro Óptico (\( p < f \)):**
   - Imagem: **Virtual, Direita e Maior** (localizada antes da lente). Exemplo: **Lupa manual**.

### 4. Formação de Imagens na Lente Divergente (Caso Único)
Para qualquer posição de um objeto real diante de uma lente divergente:
- A imagem é **SEMPRE: Virtual, Direita e Menor** (localizada entre o Foco virtual e o Centro Óptico, do mesmo lado do objeto).
- Aplicação no cotidiano: **olho-mágico de portas** de apartamentos e lentes para correção de **Miopia**.

### 5. Equação de Gauss e Convenção de Sinais
\[ \frac{1}{f} = \frac{1}{p} + \frac{1}{p'} \]
- **Convenção Rigorosa de Sinais:**
  - \( f > 0 \): Lente **Convergente** (foco real).
  - \( f < 0 \): Lente **Divergente** (foco virtual).
  - \( p > 0 \): Objeto real.
  - \( p' > 0 \): Imagem **Real** (projetável, invertida, situada após a lente).
  - \( p' < 0 \): Imagem **Virtual** (não projetável, direita, situada antes da lente).
- **Aumento Linear Transversal (\( A \)):**
  \[ A = \frac{i}{o} = -\frac{p'}{p} = \frac{f}{f - p} \]
  - Se \( A > 0 \): Imagem Direita.
  - Se \( A < 0 \): Imagem Invertida.
  - Se \( |A| > 1 \): Imagem Ampliada; se \( |A| < 1 \): Imagem Reduzida.

### 6. Vergência ou "Grau" da Lente (\( V \))
Mede o poder de convergência ou divergência da lente. É o inverso da distância focal:
\[ V = \frac{1}{f} \]
- **Unidade no SI:** Dioptria (\( \text{di} \)), popularmente chamada de "grau".
- **Condição Obrigatória:** A distância focal \( f \) deve estar **estritamente em metros (\( \text{m} \))**!
  - Uma lente convergente com \( f = +50\text{ cm} = +0{,}5\text{ m} \) possui:
    \[ V = \frac{1}{+0{,}5} = +2{,}0\text{ dioptrias} \quad (\text{"+2 graus"}). \]
  - Uma lente divergente com \( f = -25\text{ cm} = -0{,}25\text{ m} \) possui:
    \[ V = \frac{1}{-0{,}25} = -4{,}0\text{ dioptrias} \quad (\text{"-4 graus"}). \]

## 5. Definições e Conceitos
- **Lupa (Microscópio Simples):** Lente convergente de pequena distância focal utilizada com o objeto posicionado entre o foco e a lente (\( p < f \)), gerando imagem virtual, direita e ampliada.
- **Aberração Cromática:** Defeito óptico exclusivo de lentes (não ocorre em espelhos), causado pela dispersão da luz branca: cada cor possui um índice de refração ligeiramente diferente e, portanto, converge para um foco ligeiramente distinto, gerando bordas coloridas borradas na imagem.
- **Associação de Lentes Justapostas:** A vergência equivalente é a soma algébrica das vergências: \( V_{\text{eq}} = V_1 + V_2 \).

## 6. Exemplos Resolvidos Passo a Passo

### Exemplo 1: Determinação de Imagem com Equação de Gauss
Um objeto de \( 6\text{ cm} \) de altura é posicionado a \( 30\text{ cm} \) de uma lente esférica delgada convergente de distância focal \( f = +20\text{ cm} \). Determine a posição da imagem formada (\( p' \)), seu tamanho (\( i \)) e classifique sua natureza.
- **Resolução:**
  1. Identificação dos dados: \( o = 6\text{ cm} \), \( p = 30\text{ cm} \), \( f = +20\text{ cm} \).
  2. Equação de Gauss:
     \[ \frac{1}{f} = \frac{1}{p} + \frac{1}{p'} \implies \frac{1}{20} = \frac{1}{30} + \frac{1}{p'} \]
     \[ \frac{1}{p'} = \frac{1}{20} - \frac{1}{30} = \frac{3 - 2}{60} = \frac{1}{60} \implies p' = +60\text{ cm} \]
  3. Aumento linear:
     \[ A = -\frac{p'}{p} = -\frac{60}{30} = -2 \]
  4. Tamanho da imagem:
     \[ A = \frac{i}{o} \implies -2 = \frac{i}{6} \implies i = -12\text{ cm} \]
  - Resposta: A imagem forma-se a \( 60\text{ cm} \) da lente; é **Real** (\( p' > 0 \)), **Invertida** (\( i < 0 \)) e **Maior** (\( |i| = 12\text{ cm} \)).

### Exemplo 2: Cálculo do "Grau" a partir da Distância Focal
Uma pessoa que sofre de miopia utiliza lentes divergentes cuja distância focal em módulo é de \( 40\text{ cm} \). Qual é a vergência expressa na receita oftálmica dessa pessoa?
- **Resolução:**
  1. Para lentes divergentes, a distância focal é negativa: \( f = -40\text{ cm} = -0{,}4\text{ m} \).
  2. Cálculo da vergência:
     \[ V = \frac{1}{f} = \frac{1}{-0{,}4} = -\frac{10}{4} = -2{,}5\text{ dioptrias} \]
  - Resposta: A receita médica registrará \( -2{,}50\text{ di} \) ("dois graus e meio negativos").

## 7. Aplicações Práticas e Modelagem
- **Projetores Multimídia:** Lentes convergentes posicionadas de forma que o display LCD ou DLP fique entre \( f \) e \( 2f \), gerando uma imagem real, invertida e gigantesca projetada na tela da sala de aula.
- **Fotografia de Celulares:** Conjuntos sofisticados de 5 a 7 microlentes plásticas asféricas justapostas com tratamentos antirreflexo corrigem distorções geométricas e aberrações cromáticas em sensores de alta resolução.

## 8. Exercícios de Fixação
1. Uma pessoa utiliza uma lente de aumento (lupa) para inspecionar um selo raro. Qual tipo de lente está sendo usada e onde o selo deve ser posicionado em relação ao foco da lente?
2. Uma lente de distância focal desconhecida projeta sobre uma parede uma imagem nítida e invertida quatro vezes maior que o objeto (\( A = -4 \)), situada a \( 2\text{ metros} \) da lente. Qual é a distância focal dessa lente?
3. Se um oftalmologista prescrever uma lente de \( +3{,}0\text{ di} \), qual é a distância focal dessa lente em centímetros e qual é a sua classificação (convergente ou divergente)?

## 9. Correção Comentada dos Exercícios
1. Trata-se de uma **Lente Convergente**. O selo deve ser colocado a uma distância menor que a distância focal (\( p < f \)), gerando uma imagem virtual, direita e ampliada.
2. A imagem é real projetada: \( p' = +2\text{ m} = +200\text{ cm} \).
   \( A = -\frac{p'}{p} \implies -4 = -\frac{200}{p} \implies p = 50\text{ cm} \).
   Gauss: \( \frac{1}{f} = \frac{1}{50} + \frac{1}{200} = \frac{4 + 1}{200} = \frac{5}{200} = \frac{1}{40} \implies f = +40\text{ cm} \).
3. Vergência positiva (\( +3{,}0\text{ di} \)) indica **Lente Convergente**.
   \( f = \frac{1}{V} = \frac{1}{3}\text{ m} = \frac{100}{3}\text{ cm} \approx 33{,}33\text{ cm} \).

## 10. Desafio de Domínio
Por que os astrônomos do século XVII e XVIII construíam telescópios refratores com comprimentos gigantescos de mais de 40 metros de tubo para tentar eliminar a aberração cromática, e por que a invenção do telescópio refletor por Isaac Newton (usando um espelho côncavo em vez de lentes) resolveu definitivamente esse problema?
*Pista:* Pense na dependência da refração com a cor (dispersão) e na independência absoluta da reflexão com o comprimento de onda.

## 11. Resumo e Pontos-Chave
- Bordas finas no ar: Convergentes (\( f > 0 \)). Bordas grossas no ar: Divergentes (\( f < 0 \)).
- Raios notáveis: paralelo vai ao foco; pelo foco sai paralelo; pelo centro óptico não desvia.
- Lente Convergente: 5 casos (desde real/menor até virtual/maior na lupa com \( p < f \)).
- Lente Divergente: sempre virtual, direita e menor (olho-mágico, miopia).
- Equação de Gauss: \( \frac{1}{f} = \frac{1}{p} + \frac{1}{p'} \).
- Aumento linear: \( A = \frac{i}{o} = -\frac{p'}{p} \).
- Vergência: \( V = \frac{1}{f} \) (em dioptrias, com \( f \) em metros!).

## 12. Referências Bibliográficas
- HALLIDAY, David; RESNICK, Robert; WALKER, Jearl. *Fundamentos de Física: Óptica e Física Moderna*. Vol. 4. 10. ed. LTC, 2016.
- SEARS, F. W.; ZEMANSKY, M. W. *Física IV: Óptica e Física Moderna*. 14. ed. Pearson, 2016.
- TIPLER, Paul A.; MOSCA, Gene. *Física para Cientistas e Engenheiros*. Vol. 2. 6. ed. LTC, 2009.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Equação de Gauss para Lentes Delgadas",
                syntax = "\\frac{1}{f} = \\frac{1}{p} + \\frac{1}{p'}",
                description = "Relação fundamental de conjugação entre a distância focal, a distância do objeto e a distância da imagem projetada.",
                parametersInfo = "f: \\text{distância focal}; \\quad p: \\text{distância do objeto ao centro óptico}; \\quad p': \\text{distância da imagem}"
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Aumento Linear Transversal",
                syntax = "A = \\frac{i}{o} = -\\frac{p'}{p} = \\frac{f}{f - p}",
                description = "Relação de ampliação ou redução dimensional e inversão de orientação da imagem em relação ao objeto.",
                parametersInfo = "i: \\text{tamanho da imagem}; \\quad o: \\text{tamanho do objeto}; \\quad A > 0 \\text{ (direita)}; \\quad A < 0 \\text{ (invertida)}"
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Vergência ou Grau da Lente",
                syntax = "V = \\frac{1}{f} \\quad (f \\text{ em metros})",
                description = "Capacidade de refração e convergência angular de uma lente óptica expressa na unidade internacional dioptria.",
                parametersInfo = "V: \\text{vergência em dioptrias (di)}; \\quad f: \\text{distância focal em metros (m)}"
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Funcionamento da Lupa Manual",
                codeOrFormula = "p < f \\implies p' < 0 \\implies A = \\frac{f}{f - p} > 1 \\quad (\\text{Virtual, Direita e Ampliada})",
                explanation = "Posicionar o objeto mais próximo da lente que a sua distância focal gera uma imagem virtual ampliada no mesmo lado."
            ),
            LessonExample(
                title = "Cálculo de Vergência em Receita Oftalmológica",
                codeOrFormula = "V = \\frac{1}{+0{,}25\\text{ m}} = +4{,}0\\text{ dioptrias}",
                explanation = "Uma distância focal convergente de 25 cm equivale no receituário oftálmico a uma lente positiva de 4 graus."
            )
        ),
        goldTip = "Ao calcular a Vergência (grau) de uma lente, o erro mais comum dos estudantes é esquecer de transformar a distância focal de centímetros para METROS! Lembre-se: V = 1 / f só resulta em dioptrias se 'f' estiver obrigatoriamente em metros (ex: 20 cm = 0,2 m -> V = 1/0,2 = +5 di)!"
    )

    // =========================================================================
    // AULA fis_m5_a5: Óptica da Visão e Instrumentos Ópticos
    // =========================================================================
    private val bundleFisM5A5 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
O olho humano é uma das estruturas biológicas mais extraordinárias da evolução natural, operando como uma câmera fotográfica viva de autoajuste dinâmico. O estudo da **Óptica da Visão** articula de forma primorosa a física dos sistemas de lentes com a fisiologia médica humana. No ENEM, este tema é uma das presenças mais certas e recorrentes no caderno de Ciências da Natureza: todos os anos o exame cobra a interpretação física das ametropias da visão — **Miopia**, **Hipermetropia**, **Presbiopia** e **Astigmatismo** —, seus impactos no ponto de formação da imagem retiniana e o diagnóstico das lentes corretivas adequadas.

## 2. Objetivos de Aprendizagem
- Descrever a anatomia óptica funcional do olho humano: **Córnea**, **Cristalino**, **Pupila/Íris**, **Retina** e **Fóvea**.
- Compreender o mecanismo de **Acomodação Visual** através dos músculos ciliares sobre a distância focal do cristalino.
- Definir **Ponto Próximo** (\( d_{\text{pp}} \approx 25\text{ cm} \)) e **Ponto Remoto** (\( d_{\text{pr}} \to \infty \)) de um olho emétrope (normal).
- Diagnosticar a **Miopia**: alongamento anteroposterior do globo ocular, imagem formada **antes da retina**, incapacidade de focar objetos distantes e correção com **Lentes Divergentes** (\( f_{\text{lente}} = -d_{\text{pr}} \)).
- Diagnosticar a **Hipermetropia**: encurtamento do globo ocular, imagem formada **atrás da retina**, incapacidade de focar objetos próximos e correção com **Lentes Convergentes**.
- Compreender a **Presbiopia** ("vista cansada"): perda da elasticidade e flexibilidade do cristalino pelo envelhecimento, corrigida com lentes convergentes ou multifocais.
- Compreender o **Astigmatismo**: assimetria na curvatura da córnea, corrigido com **Lentes Cilíndricas**.
- Analisar os princípios básicos de **Instrumentos Ópticos**: Microscópio Composto e Telescópio Refrator (luneta astronômica).

## 3. Pré-requisitos
- Lentes Esféricas Delgadas: Gauss e Vergência em dioptrias (\( V = 1/f \)) (Física, Aula fis_m5_a4).
- Fisiologia celular e sensorial humana (Biologia, Aula bio_fis_04).

## 4. Explicação Teórica Aprofundada

### 1. Anatomia Funcional do Olho Emétrope (Normal)
O olho humano funciona analogamente a uma câmera fotográfica:
- **Córnea e Humor Aquoso:** A superfície externa transparente e convexa que responde por cerca de \( 70\% \) do poder dióptrico refrator total do olho (\( \approx 40\text{ di} \)).
- **Íris e Pupila:** A íris é um diafragma muscular pigmentado que regula a abertura da pupila controlando o fluxo de luminosidade que penetra no olho.
- **Cristalino:** Uma lente biológica biconvexa convergente e flexível com vergência ajustável entre \( \approx 20\text{ di} \) e \( 24\text{ di} \).
- **Músculos Ciliares e Acomodação Visual:**
  - Para enxergar objetos no **infinito (visão relaxada)**: os músculos ciliares relaxam-se, o cristalino fica mais plano (maior distância focal, menor vergência).
  - Para enxergar objetos **próximos**: os músculos ciliares contraem-se, afrouxando a zônula ciliar e tornando o cristalino mais arredondado/gordinho (menor distância focal, maior vergência). Esse ajuste contínuo é a **Acomodação Visual**.
- **Retina:** O anteparo fotossensível no fundo do olho, revestido por fotorreceptores: **cones** (visão em cores de alta acuidade na fóvea central) e **bastonetes** (visão periférica monocromática e em baixa luminosidade).
- Em um olho saudável (**emétrope**), a imagem formada sobre a retina é **REAL, INVERTIDA E MENOR**; o cérebro processa o sinal e desinverte a imagem na percepção consciente.

### 2. Miopia e Correção Óptica
- **Causa Anatômica:** O globo ocular é **excessivamente longo** no sentido anteroposterior ou a córnea é muito curva (excesso de convergência).
- **Defeito Visual:** Raios paralelos provenientes de objetos distantes convergem antes de atingir a retina: **a imagem nítida forma-se ANTES DA RETINA**.
- **Sintoma:** O míope enxerga perfeitamente de perto, mas tem extrema dificuldade para focar objetos distantes. Seu Ponto Remoto não está no infinito, mas a uma distância finita \( d_{\text{pr}} \).
- **Correção:** Exige afastar os raios para trás, diminuindo a convergência total. Corrige-se com **LENTES DIVERGENTES (grau negativo)**.
- **Cálculo da Lente:** A lente corretiva deve conjugar uma imagem virtual no ponto remoto do paciente para um objeto vindo do infinito (\( p \to \infty \)):
  \[ \frac{1}{f} = \frac{1}{\infty} + \frac{1}{-d_{\text{pr}}} \implies f = -d_{\text{pr}} \implies V = -\frac{1}{d_{\text{pr}}} \]

### 3. Hipermetropia e Correção Óptica
- **Causa Anatômica:** O globo ocular é **excessivamente curto** ou o sistema de lentes tem convergência insuficiente.
- **Defeito Visual:** Para objetos próximos (como um livro a 25 cm), mesmo com a acomodação máxima do cristalino, os raios atingem a retina antes de convergir: **a imagem nítida formaria-se ATRÁS DA RETINA**.
- **Sintoma:** O hipermetrope enxerga bem de longe (embora com constante esforço de acomodação), mas cansa-se rapidamente e não consegue ler de perto. Seu Ponto Próximo fica muito além dos \( 25\text{ cm} \) padrão.
- **Correção:** Exige convergir os raios mais rapidamente para frente, antecipando o foco para a retina. Corrige-se com **LENTES CONVERGENTES (grau positivo)**.
- **Cálculo da Lente:** Para colocar um livro a \( p = 25\text{ cm} = 0{,}25\text{ m} \) e criar a imagem no ponto próximo do paciente (\( p' = -d_{\text{pp}} \)):
  \[ V = \frac{1}{f} = \frac{1}{0{,}25} - \frac{1}{d_{\text{pp}}} = 4 - \frac{1}{d_{\text{pp}}} \]

### 4. Presbiopia ("Vista Cansada")
- **Causa Fisiológica:** Com o envelhecimento natural (geralmente após os 40 a 45 anos), o cristalino perde sua elasticidade proteica e os músculos ciliares perdem tônus mecânico.
- **Consequência:** O olho perde progressivamente a capacidade de acomodação para enxergar objetos próximos; o ponto próximo afasta-se (a pessoa estica o braço para conseguir ler mensagens no celular ou a página de um livro).
- **Correção:** Utiliza **Lentes Convergentes** para leitura ou lentes multifocais/progressivas.

### 5. Astigmatismo
- **Causa Anatômica:** Assimetria de curvatura na córnea ou cristalino, que adquire formato elíptico (semelhante a uma bola de futebol americano) em vez de esférico regular.
- **Consequência:** A luz que incide em planos meridianos verticais e horizontais foca em distâncias diferentes, provocando visão borrada e distorcida tanto de perto quanto de longe.
- **Correção:** Corrige-se com **Lentes Cilíndricas** com eixo angular calibrado.

### 6. Instrumentos Ópticos de Ampliação
1. **Microscópio Óptico Composto:** Formado por duas lentes convergentes coaxiais:
   - **Objetiva (próxima do objeto):** Pequena distância focal; conjuga uma imagem intermediária **real, invertida e muito ampliada** (\( f_{\text{obj}} < p < 2f_{\text{obj}} \)).
   - **Ocular (onde encostamos o olho):** Funciona como uma lupa sobre a imagem intermediária, gerando a imagem final **virtual, invertida em relação ao objeto original e extremamente ampliada**.
2. **Luneta Astronômica (Telescópio Refrator):**
   - Utiliza uma objetiva de grande diâmetro e longa distância focal para coletar luz cósmica do infinito, formando uma imagem real reduzida sobre o foco comum da ocular, que então a amplia.

## 5. Definições e Conceitos
- **Emétrope:** Olho com visão anatomicamente perfeita, sem vícios de refração.
- **Ponto Próximo:** Menor distância da vista na qual o olho consegue produzir foco nítido em acomodação máxima (\( \approx 25\text{ cm} \) no jovem normal).
- **Ponto Remoto:** Maior distância da vista na qual o olho em repouso acomodativo foca com nitidez (infinito no olho normal).

## 6. Exemplos Resolvidos Passo a Passo

### Exemplo 1: Correção de Miopia
Uma pessoa míope não consegue enxergar com nitidez nenhum objeto situado a mais de \( 50\text{ cm} \) de seus olhos (\( d_{\text{pr}} = 0{,}5\text{ m} \)). Qual deve ser o tipo e a vergência da lente prescrita pelo médico para que ela enxergue nitidamente objetos localizados no infinito?
- **Resolução:**
  1. Para miopia, a lente é **divergente** (grau negativo).
  2. O foco da lente deve ser igual ao oposto do ponto remoto:
     \[ f = -d_{\text{pr}} = -0{,}5\text{ metros} \]
  3. Cálculo da vergência:
     \[ V = \frac{1}{f} = \frac{1}{-0{,}5} = -2{,}0\text{ dioptrias} \]
  - Resposta: O oftalmologista prescreverá lentes **divergentes** de \( -2{,}00\text{ di} \) ("dois graus negativos").

### Exemplo 2: Correção de Hipermetropia
Um jovem hipermetrope tem seu ponto próximo situado a \( 100\text{ cm} \) (\( 1{,}0\text{ m} \)) dos olhos. Desejando ler um livro confortavelmente posicionado à distância padrão de \( 25\text{ cm} \) (\( 0{,}25\text{ m} \)), qual deve ser o grau da lente utilizada?
- **Resolução:**
  1. A lente deve formar uma imagem virtual na posição do ponto próximo (\( p' = -1{,}0\text{ m} \)) para um objeto situado a \( p = 0{,}25\text{ m} \).
  2. Pela Equação de Gauss:
     \[ V = \frac{1}{f} = \frac{1}{p} + \frac{1}{p'} = \frac{1}{0{,}25} + \frac{1}{-1{,}0} \]
     \[ V = 4 - 1 = +3{,}0\text{ dioptrias} \]
  - Resposta: São necessárias lentes **convergentes** de \( +3{,}00\text{ di} \) ("três graus positivos").

## 7. Aplicações Práticas e Modelagem
- **Cirurgia Refrativa a Laser (LASIK / PRK):** Um feixe de laser ultravioleta de alta precisão (Excimer Laser) vaporiza micrometricamente camadas superficiais da córnea para alterar o seu raio de curvatura, corrigindo a miopia por aplanamento corneano sem a necessidade de óculos.
- **Implante de Lentes Intraoculares (LIO):** Na cirurgia de catarata (opacificação do cristalino decorrente da desnaturação proteica), o cristalino doente é emulsificado por ultrassom e substituído por uma lente artificial dobrável biocompatível que restaura a acuidade visual total do paciente.

## 8. Exercícios de Fixação
1. Um estudante que senta no fundo da sala de aula não consegue ler o que o professor escreve na lousa, embora consiga ler perfeitamente o caderno sobre a sua carteira. Qual é o distúrbio de refração desse estudante, onde a imagem se forma no seu olho e qual tipo de lente ele deve usar?
2. Por que a imagem formada na retina humana é real e invertida se nós temos a convicção perceptual consciente de enxergar tudo de cabeça para cima?
3. Diferencie a causa física da Hipermetropia da causa fisiológica da Presbiopia ("vista cansada").

## 9. Correção Comentada dos Exercícios
1. O estudante sofre de **Miopia**. A imagem dos objetos distantes (lousa) forma-se **antes da retina** devido ao excessivo alongamento do globo ocular. Ele deve utilizar **Lentes Divergentes** (grau negativo).
2. O sistema de lentes córnea-cristalino é convergente, projetando fisicamente sobre a retina uma imagem **real e invertida**. O cérebro humano (córtex visual primário no lobo occipital) recebe os impulsos nervosos do nervo óptico e inverte neuralmente a imagem durante a montagem do mapa perceptivo espacial.
3. A hipermetropia é uma anomalia anatômica estrutural congênita (globo ocular encurtado). A presbiopia é uma alteração fisiológica etária decorrente do envelhecimento, na qual o cristalino perde sua elasticidade física e os músculos ciliares perdem a capacidade de acomodação.

## 10. Desafio de Domínio
Por que quando abrimos os olhos embaixo d'água em uma piscina limpa sem máscara ou óculos de natação, a nossa visão fica completamente embaçada e fora de foco, mas ao colocarmos uma máscara de mergulho que mantém uma camada de ar diante dos olhos a nitidez da visão é imediatamente restabelecida?
*Pista:* Compare o índice de refração da córnea (\( n \approx 1{,}376 \)) com o do ar (\( n \approx 1{,}0 \)) e com o da água (\( n \approx 1{,}333 \)). O que acontece com o poder de convergência na interface córnea-água?

## 11. Resumo e Pontos-Chave
- Olho normal (emétrope): imagem na retina é real, invertida e menor.
- Acomodação visual: músculos ciliares contraem para enxergar de perto (cristalino mais curvo).
- Miopia: olho longo, imagem antes da retina, não foca de longe, corrige com LENTES DIVERGENTES (grau negativo).
- Hipermetropia: olho curto, imagem atrás da retina, não foca de perto, corrige com LENTES CONVERGENTES (grau positivo).
- Presbiopia ("vista cansada"): perda de elasticidade do cristalino com a idade, corrige com lentes convergentes/multifocais.
- Astigmatismo: córnea assimétrica (formato de bola de futebol americano), corrige com LENTES CILÍNDRICAS.
- Microscópio composto: Objetiva convergente (imagem real ampliada) + Ocular convergente (lupa).

## 12. Referências Bibliográficas
- HALLIDAY, David; RESNICK, Robert; WALKER, Jearl. *Fundamentos de Física: Óptica e Física Moderna*. Vol. 4. 10. ed. LTC, 2016.
- SEARS, F. W.; ZEMANSKY, M. W. *Física IV: Óptica e Física Moderna*. 14. ed. Pearson, 2016.
- GUYTON, Arthur C.; HALL, John E. *Tratado de Fisiologia Médica*. 13. ed. Elsevier, 2017.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Vergência para Correção de Miopia",
                syntax = "V = -\\frac{1}{d_{\\text{pr}}} \\quad (d_{\\text{pr}} \\text{ em metros})",
                description = "Grau negativo da lente divergente necessária para deslocar o foco de objetos no infinito para o ponto remoto do míope.",
                parametersInfo = "V: \\text{vergência da lente (di)}; \\quad d_{\\text{pr}}: \\text{distância máxima de visão nítida do paciente (m)}"
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Vergência para Correção de Hipermetropia",
                syntax = "V = \\frac{1}{0{,}25} - \\frac{1}{d_{\\text{pp}}} = 4 - \\frac{1}{d_{\\text{pp}}}",
                description = "Grau positivo da lente convergente para permitir leitura confortável na distância padrão de 25 cm.",
                parametersInfo = "V: \\text{grau da lente convergente (di)}; \\quad d_{\\text{pp}}: \\text{ponto próximo defeituoso em metros}"
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Aumento Total do Microscópio Óptico Composto",
                syntax = "A_{\\text{total}} = A_{\\text{objetiva}} \\cdot A_{\\text{ocular}}",
                description = "Ampliação combinada do sistema óptico composto de duas lentes convergentes coaxiais.",
                parametersInfo = "A_{\\text{obj}}: \\text{aumento linear transversal}; \\quad A_{\\text{oc}}: \\text{aumento angular da lente ocular}"
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Correção Óptica da Miopia",
                codeOrFormula = "\\text{Miopia} \\implies \\text{Foco Antes da Retina} \\implies \\text{Lente Divergente (Grau Negativo)}",
                explanation = "A lente divergente espalha os raios antes da córnea, fazendo com que a convergência final coincida exatamente na retina."
            ),
            LessonExample(
                title = "Correção da Hipermetropia",
                codeOrFormula = "\\text{Hipermetropia} \\implies \\text{Foco Atrás da Retina} \\implies \\text{Lente Convergente (Grau Positivo)}",
                explanation = "A lente convergente antecipa o cruzamento dos raios de luz para que o foco nítido se projete sobre o fundo do olho encurtado."
            )
        ),
        goldTip = "O ENEM NUNCA perdoa troca de lente de miopia e hipermetropia! Mnemônica definitiva: 'MIOPE NÃO VÊ LONGE -> LENTE DIVERGENTE (Grau Negativo)'. 'HIPERMETROPE NÃO VÊ PERTO -> LENTE CONVERGENTE (Grau Positivo)'. Se o problema for perda de foco perto com a idade (> 40 anos), é PRESBIOPIA (também corrigida com convergente/multifocal)!"
    )
}

