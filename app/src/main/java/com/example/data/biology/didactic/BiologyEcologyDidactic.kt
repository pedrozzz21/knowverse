package com.example.data.biology.didactic

import com.example.data.math.CalculusDidacticBundle
import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample

/**
 * Conteúdo didático aprofundado de Biologia ENEM — Módulo 5: Ecologia e Meio Ambiente (Aulas bio_eco_01 a bio_eco_07).
 * Estrutura rigorosa em 12 seções por aula, fundamentada em Odum, Ricklefs, Townsend, OpenStax Biology 2e e Matriz do ENEM / INEP.
 */
object BiologyEcologyDidactic {

    fun get(lessonId: String): CalculusDidacticBundle? {
        return when (lessonId) {
            "bio_eco_01" -> bundleBioEco01
            "bio_eco_02" -> bundleBioEco02
            "bio_eco_03" -> bundleBioEco03
            "bio_eco_04" -> bundleBioEco04
            "bio_eco_05" -> bundleBioEco05
            "bio_eco_06" -> bundleBioEco06
            "bio_eco_07" -> bundleBioEco07
            else -> null
        }
    }

    // =========================================================================
    // AULA bio_eco_01: Conceitos de Meio Ambiente e Biodiversidade
    // =========================================================================
    private val bundleBioEco01 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
A Ecologia é a ciência que investiga as intrincadas relações entre os seres vivos e o ambiente em que habitam. No Exame Nacional do Ensino Médio (ENEM), a Ecologia é historicamente a área de maior peso quantitativo e relevância social em toda a prova de Ciências da Natureza. Para compreender as grandes questões socioambientais contemporâneas, o estudante precisa dominar a hierarquia dos níveis de organização ecológica, a diferença crucial entre o "endereço" e a "profissão" de uma espécie e o conceito de *Hotspots* de biodiversidade.

## 2. Objetivos de Aprendizagem
- Diferenciar com rigor conceitual os níveis de organização em ecologia: População, Comunidade (Biocenose), Ecossistema e Biosfera.
- Distinguir com clareza matemática e biológica entre **Hábitat** (espaço físico ocupado) e **Nicho Ecológico** (papel funcional multidimensional e uso de recursos).
- Explicar os conceitos de Fatores Bióticos (seres vivos) e Fatores Abióticos (luz, temperatura, pH, umidade, solo).
- Compreender o que são *Ecótonos* (zonas de transição ecológica com alta diversidade).
- Definir *Hotspots de Biodiversidade*, identificando os dois biomas brasileiros classificados nessa categoria global (Mata Atlântica e Cerrado).

## 3. Pré-requisitos
- Classificação básica dos seres vivos em reinos e domínios.
- Conceitos fundamentais de evolução e adaptação ao meio (Módulo 3: Evolução).

## 4. Explicação Teórica Aprofundada

### 1. Níveis de Organização Ecológica
1. **Espécie:** Conjunto de indivíduos morfologicamente semelhantes capazes de cruzar naturalmente entre si e gerar descendentes férteis.
2. **População:** Conjunto de indivíduos da **mesma espécie** que vivem e interagem em uma determinada área geográfica no mesmo intervalo de tempo.
3. **Comunidade (Biocenose):** Conjunto de todas as populações de **espécies diferentes** que coexistem e interagem em uma mesma área.
4. **Ecossistema:** Unidade funcional integrada formada pela interação recíproca entre a comunidade biológica (biocenose / fatores bióticos) e os elementos físicos e químicos do meio ambiente (biótopo / fatores abióticos):
   \[ \text{Ecossistema} = \text{Fatores Bióticos} + \text{Fatores Abióticos} \]
5. **Bioma:** Amplo conjunto de ecossistemas continentais com clima regional, vegetação clímax e fauna característicos.
6. **Biosfera:** A esfera da vida em nosso planeta — engloba todas as regiões habitadas da Terra.

### 2. A Metáfora Clássica: Hábitat vs. Nicho Ecológico
O ecólogo Eugene Odum consagrou uma analogia pedagógica definitiva:
- **Hábitat (O "Endereço"):** É o local físico, espaço geográfico ou ambiente particular onde uma espécie pode ser naturalmente encontrada.
- **Nicho Ecológico (A "Profissão"):** É o conjunto complexo e multidimensional de todas as atividades, interações, hábitos comportamentais, horários de atividade, regime alimentar, tolerâncias térmicas e formas de reprodução de uma espécie no ecossistema.
  - *Princípio de Gause (Exclusão Competitiva):* Duas espécies com nichos ecológicos estritamente idênticos **não podem coexistir indefinidamente** no mesmo hábitat se os recursos forem limitantes. Mais cedo ou mais tarde, uma espécie superará a outra, levando-a à extinção local ou forçando uma diferenciação adaptativa de nicho.

### 3. Zonas de Transição e Hotspots
- **Ecótono:** Zona de transição entre dois ecossistemas ou biomas adjacentes (ex.: transição entre a Caatinga e a Amazônia). Os ecótonos frequentemente exibem altíssima biodiversidade agregando espécies de ambos os biomas vizinhos.
- **Hotspots de Biodiversidade:** Áreas planetárias prioritárias de conservação emergencial que atendem simultaneamente a dois critérios:
  1. Conter pelo menos **1.500 espécies de plantas vasculares endêmicas**.
  2. Ter **perdido no mínimo 70% de sua vegetação nativa original** devido à degradação antrópica.
  - **No Brasil, há dois Hotspots Mundiais:**
    - A **Mata Atlântica** (já perdeu mais de 88% de sua cobertura florestal original).
    - O **Cerrado** (já perdeu mais de 50% de sua área devido ao avanço da agropecuária intensiva).

## 5. Definições e Conceitos
- **Biótopo:** Componente físico e químico inanimado do ecossistema que serve de suporte à vida.
- **Endemismo:** Espécie cuja ocorrência geográfica é estritamente restrita a um único local ou bioma específico.
- **Efeito de Borda:** Alterações microclimáticas (mais luz, menos umidade, vento) e biológicas nas margens de fragmentos de matas.

## 6. Exemplos Resolvidos Passo a Passo
### Exemplo 1: Hábitat vs. Nicho em Duas Aves no Mesmo Lago
Duas espécies de garças (Garça-branca-grande e Garça-vaqueira) vivem na mesma lagoa. A primeira alimenta-se de peixes caçados em águas rasas pela manhã; a segunda caminha pelo pasto comendo gafanhotos e carrapatos espantados pelo gado.
a) Elas compartilham o mesmo hábitat?
b) Elas ocupam o mesmo nicho ecológico? Explique se haverá exclusão competitiva entre elas.
- **Passo 1 (Hábitat):** Sim, ambas compartilham o mesmo hábitat (a lagoa e suas margens imediatas).
- **Passo 2 (Nicho Ecológico):** Não, elas ocupam nichos ecológicos amplamente diferentes, pois possuem dietas distintas (peixes aquáticos vs. insetos terrestres) e comportamentos diferentes.
- **Passo 3 (Conclusão):** Como seus nichos não se sobrepõem nos recursos limitantes, não haverá exclusão competitiva; as duas espécies podem coexistir pacificamente.

## 7. Aplicações Práticas e Modelagem
- **Criação de Corredores Ecológicos:** Conexão entre fragmentos isolados de floresta nativa para permitir o fluxo gênico entre populações, combatendo a endogamia e a extinção local.
- **Zoneamento Ecológico-Econômico (ZEE):** Planejamento territorial que delimita áreas para conservação integral e áreas para atividades agropecuárias sustentáveis.

## 8. Exercícios de Fixação
1. Qual a diferença fundamental entre comunidade biológica e ecossistema?
2. Enuncie com suas palavras o Princípio de Exclusão Competitiva de Gause.
3. Quais são os dois únicos biomas brasileiros classificados internacionalmente como Hotspots de Biodiversidade?

## 9. Correção Comentada dos Exercícios
1. A comunidade é composta exclusivamente pelas populações de seres vivos de diferentes espécies (fatores bióticos). O ecossistema inclui a comunidade somada às variáveis físicas e abióticas do ambiente (luz, água, solo, temperatura).
2. Afirma que duas espécies não conseguem coexistir em equilíbrio estável no mesmo hábitat se ocuparem exatamente o mesmo nicho ecológico disputando os mesmos recursos limitantes.
3. A **Mata Atlântica** e o **Cerrado**.

## 10. Desafio de Domínio
Explique por que a introdução de uma espécie exótica invasora (como o mexilhão-dourado ou o caramujo-africano) frequentemente causa a destruição da fauna nativa, utilizando os conceitos de nicho ecológico, ausência de predadores naturais e competição.

## 11. Resumo e Pontos-Chave
- População = mesma espécie no mesmo local e tempo.
- Comunidade = conjunto de populações de espécies diferentes.
- Ecossistema = Biocenose (fatores bióticos) + Biótopo (fatores abióticos).
- Hábitat = onde o ser vivo mora (endereço). Nicho Ecológico = como ele vive e se relaciona (profissão).
- Princípio de Gause: nichos idênticos no mesmo hábitat provocam exclusão competitiva.
- Hotspots: > 1.500 plantas endêmicas e > 70% de destruição. No Brasil: Mata Atlântica e Cerrado.

## 12. Referências Bibliográficas
- ODUM, E. P.; BARRETT, G. W. *Fundamentos de Ecologia*. 5ª ed. São Paulo: Cengage Learning, 2011.
- RICKLEFS, R. E. *A Economia da Natureza*. 7ª ed. Rio de Janeiro: Guanabara Koogan, 2016.
- OPENSTAX. *Biology 2e*. Rice University: OpenStax, 2020.
- INEP. *Matriz de Referência para o ENEM: Ciências da Natureza e suas Tecnologias*. Brasília: MEC/INEP.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Equação Conceitual do Ecossistema",
                syntax = "\\text{Ecossistema} = \\text{Fatores Bióticos (Biocenose)} + \\text{Fatores Abióticos (Biótopo)}",
                description = "Expressa a interação integrada entre a vida e o meio físico-químico.",
                parametersInfo = "Base estrutural de toda a análise ecológica contemporânea."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Critério de Hotspot Global de Biodiversidade",
                syntax = "\\text{Plantas Endêmicas} \\ge 1.500 \\quad \\land \\quad \\text{Destruição do Hábitat} \\ge 70\\%",
                description = "Parâmetros internacionais para definir áreas de conservação prioritária emergencial.",
                parametersInfo = "No Brasil: Mata Atlântica e Cerrado atendem simultaneamente a ambos os critérios."
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Diferenciação de Hábitat vs. Nicho Ecológico",
                codeOrFormula = "\\text{Hábitat: Endereço geográfico} \\quad | \\quad \\text{Nicho: Papel funcional e uso de recursos}",
                explanation = "Espécies diferentes podem compartilhar o mesmo hábitat desde que seus nichos ecológicos sejam suficientemente distintos para evitar a exclusão competitiva."
            )
        ),
        goldTip = "No ENEM: Se a questão falar onde o bicho mora, é HÁBITAT. Se falar o que ele come, que horas sai da toca e como se reproduz, é NICHO ECOLÓGICO!"
    )

    // =========================================================================
    // AULA bio_eco_02: Cadeias e Teias Alimentares
    // =========================================================================
    private val bundleBioEco02 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
Em qualquer ecossistema da Terra, a vida só se mantém graças a um fluxo contínuo e unidirecional de energia e à ciclagem ininterrupta de matéria. As relações tróficas representam a arquitetura funcional da natureza: plantas e algas convertem fótons solares em energia química através da fotossíntese, alimentando herbívoros, carnívoros e, por fim, decompositores. No ENEM, as questões exploram exaustivamente a perda calórica ao longo dos níveis tróficos, as pirâmides ecológicas invertidas no ambiente marinho e o fenômeno perigoso da **Biomagnificação Trófica (Bioacumulação)** de poluentes persistentes.

## 2. Objetivos de Aprendizagem
- Diferenciar os níveis tróficos: Produtores (autótrofos), Consumidores Primários, Secundários, Terciários e Decompositores (saprófagos).
- Compreender por que o **fluxo de energia é estritamente unidirecional e decrescente** ao longo da cadeia alimentar (Segunda Lei da Termodinâmica).
- Construir e interpretar Pirâmides Ecológicas: Números, Biomassa (compreendendo a exceção marinha invertida) e Energia (que **NUNCA** pode ser invertida).
- Explicar a diferença conceitual e quantitativa entre Produtividade Primária Bruta (PPB) e Produtividade Primária Líquida (PPL).
- Analisar o mecanismo de **Bioacumulação e Biomagnificação Trófica** de toxinas lipossolúveis (DDT, mercúrio, microplásticos), compreendendo por que o topo da cadeia alimentar é a posição mais vulnerável.

## 3. Pré-requisitos
- Fotossíntese e Respiração Celular (Módulo 1: Citologia e Bioquímica).
- Conceitos de ecossistema e comunidade (Aula bio_eco_01).

## 4. Explicação Teórica Aprofundada

### 1. Estrutura da Cadeia e Teia Alimentar
- **Cadeia Alimentar:** Sequência linear de transferência de matéria e energia alimentar entre organismos:
  \[ \text{Produtor (Autótrofo)} \to \text{Consumidor Primário (Herbívoro)} \to \text{Consumidor Secundário} \to \text{Consumidor Terciário} \]
- **Teia Alimentar:** Conjunto complexo de cadeias alimentares interligadas em uma comunidade real, onde um mesmo animal pode ocupar múltiplos níveis tróficos simultaneamente.
- **Decompositores (Fungos e Bactérias heterótrofas):** Nível trófico universal que atua sobre a matéria orgânica morta de **todos os outros níveis tróficos**, transformando macromoléculas em minerais simples que retornam ao solo e à água.

### 2. O Fluxo de Energia: Unidirecional e Decrescente
- A energia entra no planeta majoritariamente via radiação solar e é convertida em ligações químicas na fotossíntese.
- Em cada nível trófico, **apenas cerca de 10% da energia útil** é incorporada na biomassa e transferida para o nível seguinte (Regra dos 10% de Lindeman).
- Os 90% restantes são dissipados na forma de **calor metabólico** ou eliminados em excretas e fezes.
- Consequência: Como a energia diminui drasticamente a cada elo, as cadeias alimentares raramente ultrapassam 4 ou 5 níveis tróficos!

### 3. Produtividade Primária: Bruta vs. Líquida
- **Produtividade Primária Bruta (PPB):** Total absoluto de biomassa e energia química sintetizada por unidade de área e tempo.
- **Produtividade Primária Líquida (PPL):** A matéria vegetal real que sobra para crescimento e que fica disponível para os herbívoros:
  \[ \text{PPL} = \text{PPB} - R \]

### 4. Pirâmides Ecológicas
1. **Pirâmide de Números:** Quantidade de indivíduos em cada nível. Pode ser invertida (uma árvore sustenta 10.000 lagartas).
2. **Pirâmide de Biomassa:** Quantidade de matéria orgânica seca. Pode ser **invertida no ambiente marinho aberto**: a biomassa instantânea de fitoplâncton é menor que a de zooplâncton, compensada pela velocidade de reprodução altíssima do fitoplâncton.
3. **Pirâmide de Energia:** Quantidade de joules ou calorias por área e tempo. **NUNCA PODE SER INVERTIDA!** Por imposição da termodinâmica, a energia sempre decresce ao longo dos níveis.

### 5. Bioacumulação vs. Biomagnificação Trófica
- Poluentes sintéticos ou minerais com três características perigosas:
  1. São **não biodegradáveis**.
  2. São **lipossolúveis** (acumulam-se na gordura corporal).
  3. São **persistentes** (DDT, mercúrio metilado, bifenilas policloradas, microplásticos).
- **Biomagnificação Trófica:** A concentração do contaminante por quilo de peso corporal **AUMENTA progressivamente a cada nível trófico**.
- Conclusão trágica: Os organismos do **topo da cadeia alimentar** (gaviões, orcas, tubarões e seres humanos) sofrem a maior intoxicação crônica acumulada.

## 5. Definições e Conceitos
- **Autótrofo:** Organismo capaz de sintetizar matéria orgânica a partir de precursores inorgânicos e energia externa.
- **Biomagnificação Trófica:** Efeito cumulativo em que a concentração de toxinas não degradáveis cresce ao longo dos elos sucessivos da teia trófica.
- **Turnover de Fitoplâncton:** Taxa vertiginosa de renovação e duplicação celular das microalgas marinhas.

## 6. Exemplos Resolvidos Passo a Passo
### Exemplo 1: Biomagnificação de Mercúrio no ENEM
Em um rio com garimpo ilegal com mercúrio (Hg), mediram-se as concentrações: Espécie A = 0,02 mg/kg; B = 1,80 mg/kg; C = 0,15 mg/kg; D = 14,50 mg/kg. Ordene a cadeia alimentar e explique a concentração da espécie D.
- **Passo 1 (Ordenação Trófica):** Como o mercúrio sofre biomagnificação crescente, maior concentração significa nível trófico superior:
  \[ \text{Espécie A (0,02)} \to \text{Espécie C (0,15)} \to \text{Espécie B (1,80)} \to \text{Espécie D (14,50)} \]
  A é o Produtor (fitoplâncton), C é Consumidor Primário, B é Consumidor Secundário e D é o Topo de Cadeia (peixe carnívoro).
- **Passo 2 (Explicação):** A espécie de topo acumula o mercúrio de milhares de presas consumidas ao longo da vida, sem conseguir excretá-lo.

## 7. Aplicações Práticas e Modelagem
- **Desastre de Minamata:** Despejo industrial de mercúrio no Japão gerou envenenamento neurológico em pescadores por consumo de peixes de topo de cadeia.
- **Proibição do DDT:** O livro "Primavera Silenciosa" de Rachel Carson documentou que o DDT enfraquecia as cascas dos ovos de águias e falcões por biomagnificação.

## 8. Exercícios de Fixação
1. Por que a pirâmide de energia nunca pode ser invertida na ecologia?
2. Como se explica uma pirâmide de biomassa marinha invertida na base?
3. Diferencie o comportamento da energia do comportamento da matéria na teia trófica.

## 9. Correção Comentada dos Exercícios
1. Porque a transferência de energia é sempre unidirecional e decrescente pela Segunda Lei da Termodinâmica; a cada nível, cerca de 90% é perdido como calor metabólico.
2. Porque o fitoplâncton possui taxa reprodutiva e de renovação tão acelerada que uma pequena biomassa instantânea consegue alimentar uma massa maior de zooplâncton.
3. A energia possui fluxo **unidirecional e aberto** (dissipa-se como calor); a matéria possui fluxo **cíclico e fechado** (reciclada infinitamente pelos decompositores).

## 10. Desafio de Domínio
Justifique ecologicamente por que uma dieta humana baseada predominantemente em vegetais consegue alimentar uma população mundial muito maior na mesma área agricultável do que uma dieta rica em carnes bovinas.

## 11. Resumo e Pontos-Chave
- Produtores -> Consumidores 1º -> 2º -> 3º. Decompositores atuam sobre todos.
- Fluxo de energia: Unidirecional, decrescente (apenas ~10% passa ao próximo nível).
- Pirâmides: Números e Biomassa podem ser invertidas. Pirâmide de Energia NUNCA pode ser invertida.
- PPL = PPB - R.
- Biomagnificação: Toxinas lipossolúveis e persistentes (mercúrio, DDT) atingem pico no topo da cadeia.

## 12. Referências Bibliográficas
- ODUM, E. P.; BARRETT, G. W. *Fundamentos de Ecologia*. 5ª ed. São Paulo: Cengage Learning, 2011.
- CARSON, R. *Primavera Silenciosa*. São Paulo: Gaia, 2010.
- OPENSTAX. *Biology 2e*. Rice University: OpenStax, 2020.
- INEP. *Matriz de Referência para o ENEM: Ciências da Natureza e suas Tecnologias*. Brasília: MEC/INEP.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Equação da Produtividade Primária Líquida",
                syntax = "\\text{PPL} = \\text{PPB} - R_{\\text{produtores}}",
                description = "Saldo de biomassa vegetal disponível para os consumidores primários herbívoros.",
                parametersInfo = "PPB é a fotossíntese bruta; R é a respiração gasta pelo vegetal."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Regra dos 10% de Eficiência Trófica",
                syntax = "E_{n+1} \\approx 0{,}10 \\times E_n",
                description = "Cerca de 90% da energia útil é perdida sob forma de calor e entropia a cada nível.",
                parametersInfo = "Explica por que cadeias alimentares raramente ultrapassam 4 ou 5 níveis tróficos."
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Pirâmide de Energia vs. Biomassa",
                codeOrFormula = "\\text{Pirâmide de Energia: Sempre ereta (base larga)} \\\\ \\text{Pirâmide de Biomassa Marinha: Pode ser invertida temporariamente}",
                explanation = "A termodinâmica proíbe que a pirâmide de energia se inverta em qualquer ecossistema da biosfera."
            )
        ),
        goldTip = "Caiu no ENEM perguntando quem tem mais veneno por quilo acumulado na cadeia alimentar? É sempre o animal do TOPO DA CADEIA (o predador carnívoro final)!"
    )

    // =========================================================================
    // AULA bio_eco_03: Relações Ecológicas (Harmônicas e Desarmônicas)
    // =========================================================================
    private val bundleBioEco03 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
Na natureza, nenhum ser vivo é uma ilha isolada. A coexistência de populações em uma comunidade biológica estabelece uma complexa rede de interações biológicas conhecidas como **Relações Ecológicas**. Essas interações são os motores fundamentais que moldam o tamanho das populações, direcionam a seleção natural e mantêm a estabilidade dos ecossistemas. No ENEM, as questões abordam constantemente o reconhecimento e a classificação dessas relações em situações agrícolas, médicas, evolutivas e de conservação da biodiversidade.

## 2. Objetivos de Aprendizagem
- Classificar as relações ecológicas segundo o critério de parentesco: **Intraespecíficas** (mesma espécie) vs. **Interespecíficas** (espécies diferentes).
- Classificar as interações quanto ao benefício ou prejuízo: **Harmônicas / Positivas** (nenhum participante é prejudicado) vs. **Desarmônicas / Negativas** (pelo menos um é prejudicado).
- Dominar as relações harmônicas: Sociedade, Colônia, Mutualismo (obrigatório), Protocooperação (facultativo), Comensalismo, Inquilinismo e Epifitismo.
- Dominar as relações desarmônicas: Competição (intra e interespecífica), Predatismo, Parasitismo, Amensalismo (antibiose) e Esclavagismo.
- Compreender aplicações práticas no agronegócio e na saúde humana, como o **Controle Biológico de Pragas** e a microbiota humana.

## 3. Pré-requisitos
- Conceito de população e comunidade biológica (Aula bio_eco_01).
- Seleção natural e adaptações anatômicas e comportamentais (Módulo 3: Evolução).

## 4. Explicação Teórica Aprofundada

### 1. Relações Intraespecíficas (Mesma Espécie)
- **Sociedade (+/+):** Indivíduos cooperam com divisão de trabalho, mas são **anatomicamente separados e independentes** (ex.: abelhas, formigas, cupins).
- **Colônia (+/+):** Indivíduos da mesma espécie unidos **anatomicamente e fisicamente**, formando uma unidade estrutural inseparável:
  - *Isomorfas:* Indivíduos com formas e funções idênticas (ex.: corais pétreos construtores de recifes).
  - *Heteromorfas:* Indivíduos com funções e formas anatômicas diferenciadas (ex.: caravela-portuguesa).
- **Competição Intraespecífica (-/-):** Disputa entre membros da mesma espécie por território, fêmeas ou alimento.
- **Canibalismo (+/-):** Um organismo mata e alimenta-se de outro indivíduo da mesma espécie.

### 2. Relações Interespecíficas Harmônicas
- **Mutualismo (+/+):** Associação estreita benéfica para ambas as espécies com **dependência obrigatória**:
  - *Líquens:* Fungo + Alga/Cianobactéria.
  - *Micorrizas:* Fungos associados a raízes de plantas.
  - *Bactérias no Rúmen:* Digerem celulose para animais ruminantes.
- **Protocooperação (+/+):** Interação mútua benéfica, mas **NÃO há dependência obrigatória**; ambos vivem perfeitamente isolados:
  - Pássaro-palito e crocodilo.
  - Peixe-palhaço e anêmona-do-mar.
  - Polinização de flores por abelhas silvestres.
- **Comensalismo (+/0):** Uma espécie se beneficia (geralmente aproveitando restos alimentares) sem prejudicar a outra (ex.: rêmora e tubarão).
- **Inquilinismo e Epifitismo (+/0):** Uma espécie utiliza outra exclusivamente como suporte físico ou abrigo, **sem retirar nenhuma seiva ou nutriente**:
  - *Epifitismo:* Orquídeas e bromélias sobre galhos de árvores altas (não são parasitas!).

### 3. Relações Interespecíficas Desarmônicas
- **Predatismo (+/-):** O predador mata e devora a presa de espécie distinta.
- **Parasitismo (+/-):** O parasita instala-se no hospedeiro e drena seus nutrientes. Geralmente não tem o objetivo evolutivo de matar rapidamente o hospedeiro.
  - *Hemiparasitas:* Sugam a **seiva bruta** (água e sais) via haustórios, mas fazem fotossíntese (ex.: erva-de-passarinho).
  - *Holoparasitas:* Perderam a clorofila; sugam a **seiva elaborada** (açúcares) do floema da árvore hospedeira (ex.: cipó-chumbo).
- **Amensalismo ou Antibiose (0/-):** Uma espécie inibe ou mata outra liberando substâncias químicas no meio:
  - Fungo *Penicillium* produz penicilina que inibe bactérias.
  - *Maré Vermelha:* Proliferação de dinoflagelados que liberam toxinas na água.
- **Competição Interespecífica (-/-):** Espécies diferentes disputam os mesmos recursos do meio.

## 5. Definições e Conceitos
- **Mimetismo Batesiano:** Espécie inofensiva imita a coloração de uma espécie perigosa ou tóxica (ex.: cobra falsa-coral).
- **Aposematismo:** Coloração de advertência chamativa (vermelho, amarelo com preto) que alerta predadores sobre a presença de veneno potente.
- **Haustório:** Raiz especializada modificada de plantas parasitas para perfurar tecidos vasculares.

## 6. Exemplos Resolvidos Passo a Passo
### Exemplo 1: Orquídeas vs. Cipó-Chumbo no ENEM
Classifique a interação com a árvore de: Planta 1 (orquídea florida nos galhos) e Planta 2 (fios amarelos sem folhas verdes perfurando o tronco).
- **Passo 1 (Planta 1):** A orquídea realiza **Epifitismo (+/0)**. Usa a árvore apenas como suporte mecânico para captar sol; não perfura a casca nem retira seiva da árvore.
- **Passo 2 (Planta 2):** O cipó-chumbo é **Holoparasita (+/-)**. Sem clorofila, perfura o floema com haustórios para sugar a seiva elaborada rica em açúcares, debilitando a árvore hospedeira.

## 7. Aplicações Práticas e Modelagem
- **Controle Biológico de Pragas:** Uso de vespas parasitoides e joaninhas predadoras para combater lagartas e pulgões sem agrotóxicos químicos no campo.
- **Microbiota Intestinal:** Bactérias mutualísticas no intestino produzem vitamina K e protegem contra patógenos.

## 8. Exercícios de Fixação
1. Qual a diferença fundamental entre sociedade e colônia?
2. Por que a relação entre líquens é mutualismo obrigatório e não protocooperação?
3. O que caracteriza o epifitismo de orquídeas e bromélias?

## 9. Correção Comentada dos Exercícios
1. Na sociedade os organismos são separados fisicamente com divisão social de trabalho; na colônia os indivíduos estão fisicamente unidos e fundidos anatomicamente.
2. Porque a dependência é vital e indispensável: isolados na natureza, o fungo e a alga não sobrevivem sozinhos.
3. As plantas epífitas usam a árvore unicamente como suporte físico para alcançar a luz solar na copa das matas, sem sugar seiva nem causar lesão.

## 10. Desafio de Domínio
Diferencie plantas hemiparasitas de holoparasitas citando os tecidos vasculares vegetais (xilema e floema) acessados pelos haustórios de cada uma delas.

## 11. Resumo e Pontos-Chave
- Harmônicas (+/+ ou +/0): Sociedade, Colônia, Mutualismo, Protocooperação, Comensalismo, Epifitismo.
- Desarmônicas: Competição (-/-), Predatismo (+/-), Parasitismo (+/-), Amensalismo (0/-).
- Sociedade: Separados. Colônia: Unidos fisicamente.
- Epifitismo: Só suporte físico, não é parasita!
- Hemiparasita suga seiva bruta (xilema); Holoparasita suga seiva elaborada (floema).

## 12. Referências Bibliográficas
- ODUM, E. P.; BARRETT, G. W. *Fundamentos de Ecologia*. 5ª ed. São Paulo: Cengage Learning, 2011.
- RICKLEFS, R. E. *A Economia da Natureza*. 7ª ed. Rio de Janeiro: Guanabara Koogan, 2016.
- OPENSTAX. *Biology 2e*. Rice University: OpenStax, 2020.
- INEP. *Matriz de Referência para o ENEM: Ciências da Natureza e suas Tecnologias*. Brasília: MEC/INEP.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Classificação Algébrica das Relações",
                syntax = "\\text{Harmônica} \\implies (+,+) \\text{ ou } (+,0) \\quad ; \\quad \\text{Desarmônica} \\implies (+,-) \\text{ ou } (-,-) \\text{ ou } (0,-)",
                description = "Convenção internacional dos sinais ecológicos de custo e benefício adaptativo.",
                parametersInfo = "+ representa ganho biológico, - representa prejuízo e 0 representa neutralidade."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Tipos de Parasitismo Vegetal",
                syntax = "\\text{Hemiparasita} \\to \\text{Xilema (Seiva Bruta)} \\quad | \\quad \\text{Holoparasita} \\to \\text{Floema (Seiva Elaborada)}",
                description = "Distinção anatômica baseada no vaso condutor vegetal perfurado pelos haustórios.",
                parametersInfo = "Erva-de-passarinho é hemiparasita; cipó-chumbo é holoparasita."
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Distinção entre Mutualismo e Protocooperação",
                codeOrFormula = "\\text{Mutualismo: (+/+) Obrigatório para sobrevivência} \\\\ \\text{Protocooperação: (+/+) Facultativo (vivem bem separados)}",
                explanation = "A obrigatoriedade biológica da convivência é o fator que separa conceitualmente o mutualismo da protocooperação."
            )
        ),
        goldTip = "Pegadinha clássica do ENEM: Orquídea e bromélia em árvore NÃO são parasitas! São epífitas (inquilinismo vegetal), não sugam uma gota de seiva da árvore!"
    )

    // =========================================================================
    // AULA bio_eco_04: Ciclos Biogeoquímicos (Água, Carbono e Nitrogênio)
    // =========================================================================
    private val bundleBioEco04 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
A matéria que compõe o corpo de um ser humano moderno já integrou, em eras geológicas passadas, o corpo de um dinossauro, as águas de um oceano primordial ou a atmosfera vulcânica remota. Ao contrário da energia, que escapa do planeta como calor, os elementos químicos fundamentais para a vida circulam ininterruptamente entre os seres vivos e o reservatório inanimado da Terra. O **Ciclo do Nitrogênio** e o **Ciclo do Carbono** são recordistas absolutos de cobrança no ENEM devido à sua ligação íntima com a agricultura e as mudanças climáticas globais.

## 2. Objetivos de Aprendizagem
- Compreender o conceito de ciclo biogeoquímico e seus reservatórios.
- Analisar o Ciclo da Água (pequeno e grande ciclo) e a importância da evapotranspiração da Floresta Amazônica ("Rios Voadores").
- Descrever o Ciclo do Carbono e Oxigênio, balanceando os processos biológicos de fotossíntese, respiração celular, decomposição e a queima de combustíveis fósseis.
- Dominar com maestria todas as etapas do **Ciclo do Nitrogênio**: Fixação Biológica, Amonização, Nitrificação (Nitrosação e Nitratação) e Desnitrificação.
- Explicar a importância da adubação verde e da rotação de culturas com leguminosas na agricultura sustentável.

## 3. Pré-requisitos
- Bioquímica de aminoácidos, proteínas e ácidos nucleicos ricos em nitrogênio (Aula bio_cit_01).
- Estrutura de bactérias procariontes e respiração celular (Módulo 1: Citologia).

## 4. Explicação Teórica Aprofundada

### 1. O Ciclo da Água
- **Pequeno Ciclo (Abiótico):** Evaporação solar de rios e oceanos -> condensação em nuvens -> precipitação (chuva) -> escoamento e infiltração.
- **Grande Ciclo (Biótico):** Envolve a transpiração e consumo dos seres vivos (evapotranspiração vegetal).
- **Rios Voadores da Amazônia:** Árvores amazônicas transpiram bilhões de litros de vapor d'água por dia, transportados pelos ventos alísios para gerar as chuvas que irrigam o Centro-Oeste, Sudeste e Sul do Brasil.

### 2. O Ciclo do Carbono e do Oxigênio
- **Sequestro:** Produtores fotossintetizantes absorvem \( CO_2 \) atmosférico e o convertem em glicose orgânica.
- **Devolução:** Respiração celular de todos os seres vivos, decomposição microbiana e combustão de combustíveis fósseis e queimadas.

### 3. O Ciclo do Nitrogênio: A Obra das Bactérias
O gás nitrogênio (\( N_2 \)) compõe 78% do ar, mas possui tripla ligação covalente que impede a absorção direta por animais e plantas. As etapas bacterianas essenciais são:
1. **Fixação Biológica:** Bactérias do gênero ***Rhizobium*** em simbiose com as raízes de **plantas leguminosas** (soja, feijão, ervilha) e cianobactérias convertem \( N_2 \) em amônia (\( NH_3 \)).
2. **Amonização:** Decompositores quebram proteínas e excretas nitrogenadas liberando amônia no solo.
3. **Nitrificação (Processo Quimiossintetizante em 2 Fases):**
   - **Fase A - Nitrosação:** Bactérias ***Nitrosomonas*** oxidam a amônia em **nitrito** (\( NO_2^- \)).
   - **Fase B - Nitratação:** Bactérias ***Nitrobacter*** oxidam o nitrito em **nitrato** (\( NO_3^- \)).
   - O nitrato é a forma prontamente absorvida e assimilada pelas raízes vegetais.
4. **Desnitrificação:** Em solos anóxicos, bactérias desnitrificantes (como ***Pseudomonas***) convertem nitrato de volta em gás \( N_2 \), fechando o ciclo.

## 5. Definições e Conceitos
- **Fixação Biológica de Nitrogênio:** Redução de nitrogênio gasoso em amônia catalisada pela enzima bacteriana nitrogenase.
- **Nitrato (\( NO_3^- \)):** Principal composto inorgânico nitrogenado assimilado pelos vegetais.
- **Adubação Verde:** Cultivo de leguminosas para incorporar nitrogênio natural ao solo agrícola.

## 6. Exemplos Resolvidos Passo a Passo
### Exemplo 1: Rotação de Culturas Milho-Soja no ENEM
Por que os agrônomos recomendam alternar safras de milho com safras de soja?
- **Passo 1:** O milho consome nitrato do solo sem repor nitrogênio.
- **Passo 2:** A soja abriga bactérias *Rhizobium* em nódulos radiculares que fixam \( N_2 \) do ar.
- **Passo 3:** Após a colheita da soja, os resíduos radiculares se decompõem e enriquecem o solo com nitrato natural, fertilizando-o para o próximo plantio de milho.

## 7. Aplicações Práticas e Modelagem
- **Processo Haber-Bosch:** Síntese industrial química de amônia a partir de nitrogênio e hidrogênio, base dos fertilizantes sintéticos globais.
- **Desmatamento e Secas:** A perda de floresta amazônica enfraquece os rios voadores e agrava secas agrícolas no Sudeste brasileiro.

## 8. Exercícios de Fixação
1. Por que os seres humanos não conseguem aproveitar o \( N_2 \) do ar que respiram?
2. Quais são as duas etapas da nitrificação e as bactérias envolvidas?
3. Qual o papel das bactérias desnitrificantes no ciclo?

## 9. Correção Comentada dos Exercícios
1. Porque não possuímos enzimas capazes de quebrar a tripla ligação estável da molécula de \( N_2 \); obtemos nitrogênio ingerindo aminoácidos dos alimentos.
2. Nitrosação (amônia a nitrito por *Nitrosomonas*) e Nitratação (nitrito a nitrato por *Nitrobacter*).
3. Transformam nitrato do solo em gás \( N_2 \), devolvendo-o à atmosfera e impedindo o esgotamento do reservatório gasoso.

## 10. Desafio de Domínio
Explique por que a enzima nitrogenase do *Rhizobium* é sensível ao oxigênio livre e qual o papel da proteína vegetal leg-hemoglobina nos nódulos radiculares.

## 11. Resumo e Pontos-Chave
- Água: Pequeno ciclo (abiótico) e Grande ciclo (evapotranspiração, Rios Voadores).
- Carbono: Fotossíntese sequestra \( CO_2 \); respiração, decomposição e queima fóssil liberam \( CO_2 \).
- Nitrogênio: Fixação (*Rhizobium* em leguminosas) -> Amonização -> Nitrificação (*Nitrosomonas* e *Nitrobacter*) -> Absorção pelas raízes -> Desnitrificação (*Pseudomonas*).

## 12. Referências Bibliográficas
- ODUM, E. P.; BARRETT, G. W. *Fundamentos de Ecologia*. 5ª ed. São Paulo: Cengage Learning, 2011.
- RAVEN, P. H. et al. *Biologia Vegetal*. 8ª ed. Rio de Janeiro: Guanabara Koogan, 2014.
- OPENSTAX. *Biology 2e*. Rice University: OpenStax, 2020.
- INEP. *Matriz de Referência para o ENEM: Ciências da Natureza e suas Tecnologias*. Brasília: MEC/INEP.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Etapas Químicas da Nitrificação",
                syntax = "NH_3 \\xrightarrow{\\text{Nitrosomonas}} NO_2^- \\xrightarrow{\\text{Nitrobacter}} NO_3^-",
                description = "Conversão quimiossintetizante da amônia no ânion nitrato absorvível.",
                parametersInfo = "Nitrosação gera nitrito; Nitratação gera nitrato solúvel para as plantas."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Equilíbrio Global do Ciclo do Carbono",
                syntax = "6\\,CO_2 + 6\\,H_2O \\underset{\\text{Respiração}}{\\overset{\\text{Fotossíntese}}{\\rightleftharpoons}} C_6H_{12}O_6 + 6\\,O_2",
                description = "Acoplamento bioenergético planetário entre produtores e heterótrofos.",
                parametersInfo = "A queima de combustíveis fósseis desequilibra essa balança injetando carbono fóssil antigo."
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "O Papel das Leguminosas na Agricultura",
                codeOrFormula = "\\text{Leguminosas} + \\textit{Rhizobium} \\implies \\text{Fixação Biológica de } N_2 \\implies \\text{Adubação Verde Natural}",
                explanation = "A simbiose radicular com bactérias fixadoras dispensa o uso excessivo de adubos químicos solúveis."
            )
        ),
        goldTip = "Sequência mnemônica do Ciclo do Nitrogênio para o ENEM: 'Fixa Amônia (Rhizobium), Nitrosa Nitrito (Nitrosomonas), Nitrata Nitrato (Nitrobacter) e Desnitrifica N2 (Pseudomonas)'!"
    )

    // =========================================================================
    // AULA bio_eco_05: Impactos Ambientais e Poluição
    // =========================================================================
    private val bundleBioEco05 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
A aceleração da atividade industrial, a expansão urbana desordenada e o agronegócio intensivo sem salvaguardas ecológicas têm provocado graves perturbações no equilíbrio dos ecossistemas. No ENEM, a avaliação de impactos ambientais e poluição é um dos eixos mais cobrados na competência de Ciências da Natureza. O estudante precisa dominar detalhadamente as reações em cadeia físico-químicas e ecológicas por trás de fenômenos como a **Eutrofização Cultural**, a **Chuva Ácida**, a **Inversão Térmica** e o descarte de resíduos em aterros sanitários.

## 2. Objetivos de Aprendizagem
- Detalhar passo a passo todas as fases da **Eutrofização Artificial** de corpos d'água até a anóxia e proliferação anaeróbia.
- Explicar a gênese química e os impactos ecológicos da **Chuva Ácida** por óxidos de enxofre e nitrogênio.
- Analisar a dinâmica meteorológica e os riscos respiratórios provocados pela **Inversão Térmica** urbana no inverno.
- Compreender a poluição por lixo urbano: lixões a céu aberto vs. aterros sanitários de engenharia.
- Explicar os impactos do chorume e o aproveitamento do biogás metano (\( CH_4 \)).

## 3. Pré-requisitos
- Relações ecológicas, teias tróficas e fluxo de energia (Aulas bio_eco_02 e bio_eco_03).
- Ciclos biogeoquímicos (Aula bio_eco_04).

## 4. Explicação Teórica Aprofundada

### 1. Eutrofização Cultural: O Passo a Passo do ENEM
A eutrofização ocorre quando um corpo de água parada recebe excesso de **nitratos e fosfatos** (esgoto ou adubos agrícolas):
1. **Aporte de Nutrientes:** Despejo massivo de nitrogênio e fósforo.
2. **Floração de Algas:** Algas microscópicas superficiais multiplicam-se rapidamente formando uma camada verde espessa.
3. **Bloqueio de Luz:** A camada de algas impede a penetração da luz solar nas camadas inferiores.
4. **Morte de Vegetais Subaquáticos:** Sem luz, plantas do fundo deixam de fazer fotossíntese e morrem.
5. **Proliferação de Decompositores Aeróbios:** Bactérias aeróbias multiplicam-se consumindo a matéria morta.
6. **Esgotamento do Oxigênio Dissolvido (\( O_2 \)):** As bactérias aeróbias consomem todo o oxigênio da água (anóxia severa).
7. **Morte em Massa de Peixes:** Peixes e animais aquáticos morrem sufocados por falta de oxigênio.
8. **Proliferação de Bactérias Anaeróbias:** Sem oxigênio, proliferam microrganismos anaeróbios gerando gases fétidos (\( H_2S \) - sulfeto de hidrogênio e \( CH_4 \)).

### 2. Chuva Ácida: Química e Danos
- Toda chuva natural é levemente ácida (pH ~ 5,6) pelo ácido carbônico (\( H_2CO_3 \)).
- **Chuva Ácida Antrópica (pH < 4,5):**
  - Queima de combustíveis ricos em enxofre libera dióxido de enxofre (\( SO_2 \)), que forma **ácido sulfúrico (\( H_2SO_4 \))**.
  - Motores veiculares produzem óxidos de nitrogênio (\( NO_x \)), formando **ácido nítrico (\( HNO_3 \))**.
- **Consequências:** Acidificação de lagos e solos, queima química de folhas em florestas e corrosão de monumentos históricos de mármore (\( CaCO_3 \)).

### 3. Inversão Térmica
- **Condição Normal:** Ar quente próximo ao solo sobe por convecção, dispersando poluentes para a atmosfera superior.
- **Na Inversão Térmica (Madrugadas e manhãs frias de inverno):**
  - O solo resfria intensamente à noite, gerando uma camada de **ar frio denso no chão**.
  - Uma camada de **ar quente menos denso sobrepõe-se** a esse ar frio basal.
  - O ar frio pesado não consegue subir e o ar quente não desce. Cria-se um bloqueio atmosférico estagnado.
  - A poluição veicular e industrial fica retida junto ao solo, provocando *smog* e crises de asma e bronquite.

### 4. Lixão vs. Aterro Sanitário
- **Lixão a Céu Aberto:** Despejo sem impermeabilização. O **chorume** (líquido escuro e tóxico da decomposição) contamina o lençol freático e atrai vetores de doenças. Proibido por lei.
- **Aterro Sanitário (Engenharia Sustentável):** Solo impermeabilizado com PEAD, captação e tratamento do chorume, e dutos de drenagem de gás metano (\( CH_4 \)) para queima ou geração elétrica limpa.

## 5. Definições e Conceitos
- **Demanda Bioquímica de Oxigênio (DBO):** Quantidade de oxigênio consumida por bactérias para degradar a matéria orgânica em amostra de água.
- **Chorume:** Líquido poluente escuro e fétido gerado pela decomposição anaeróbia de resíduos orgânicos úmidos.
- **Anóxia Aquática:** Ausência total de oxigênio dissolvido na água.

## 6. Exemplos Resolvidos Passo a Passo
### Exemplo 1: DBO e Oxigênio Dissolvido em Rio com Esgoto
Como variam as curvas de DBO e de Oxigênio Dissolvido (OD) logo após o lançamento de esgoto clandestino?
- **Passo 1:** No ponto de descarte de esgoto, a DBO dispara para o pico máximo devido à enorme carga orgânica a ser degradada.
- **Passo 2:** A curva de Oxigênio Dissolvido (OD) despenca bruscamente para quase zero, pois as bactérias aeróbias consomem o gás na decomposição.
- **Passo 3:** Conforme o rio se autodepura quilômetros adiante, a DBO cai e o oxigênio volta a subir.

## 7. Aplicações Práticas e Modelagem
- **Estação de Tratamento de Esgoto (ETE):** Processos de aeração forçada e lodos ativados para digerir matéria orgânica antes do descarte.
- **Geração Elétrica com Biogás:** Queima de metano de aterros em motogeradores elétricos para evitar emissão direta de gás estufa.

## 8. Exercícios de Fixação
1. Qual o evento biológico causador da morte de peixes na eutrofização?
2. Quais ácidos fortes provocam a chuva ácida antrópica?
3. Por que a inversão térmica ocorre com maior frequência em manhãs de inverno?

## 9. Correção Comentada dos Exercícios
1. A proliferação descontrolada de bactérias decompositoras aeróbias, que consomem todo o oxigênio livre da água ao digerirem algas e plantas mortas.
2. Ácido sulfúrico (\( H_2SO_4 \)) e ácido nítrico (\( HNO_3 \)).
3. Porque no inverno as noites são longas e frias, resfriando rapidamente o solo e aprisionando uma camada de ar frio denso sob o ar quente.

## 10. Desafio de Domínio
Explique detalhadamente por que um aterro sanitário moderno precisa conter drenos para queima controlada do metano, comparando o poder de efeito estufa do metano com o do gás carbônico.

## 11. Resumo e Pontos-Chave
- Eutrofização: Nutrientes -> Algas cobrem a água -> Falta luz no fundo -> Plantas morrem -> Bactérias aeróbias consomem \( O_2 \) -> Peixes morrem asfixiados -> Bactérias anaeróbias produzem mau cheiro.
- Chuva Ácida: Óxidos de enxofre e nitrogênio geram ácidos sulfúrico e nítrico (pH < 4,5).
- Inversão Térmica: Ar frio denso fica preso sob ar quente no chão no inverno, acumulando poluição.
- Aterro Sanitário: Manta impermeabilizante, dreno de chorume e duto de queima de biogás.

## 12. Referências Bibliográficas
- ODUM, E. P.; BARRETT, G. W. *Fundamentos de Ecologia*. 5ª ed. São Paulo: Cengage Learning, 2011.
- BRAGA, B. et al. *Introdução à Engenharia Ambiental*. 2ª ed. São Paulo: Pearson, 2005.
- OPENSTAX. *Biology 2e*. Rice University: OpenStax, 2020.
- INEP. *Matriz de Referência para o ENEM: Ciências da Natureza e suas Tecnologias*. Brasília: MEC/INEP.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Química da Chuva Ácida",
                syntax = "SO_3 + H_2O \\to H_2SO_4 \\quad ; \\quad 2\\,NO_2 + H_2O \\to HNO_3 + HNO_2",
                description = "Reação de óxidos de queima com vapor de água formando ácidos inorgânicos fortes.",
                parametersInfo = "Reduz o pH da água das chuvas para patamares inferiores a 4,5."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Relação Crítica na Eutrofização",
                syntax = "\\uparrow [\\text{Esgoto/Adubo}] \\implies \\uparrow \\text{DBO} \\implies \\downarrow [O_2\\text{ dissolvido}] \\implies \\text{Asfixia de Peixes}",
                description = "Cadeia causal de eventos biológicos na poluição orgânica aquática.",
                parametersInfo = "A proliferação de decompositores aeróbios esgota o oxigênio da água."
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Perfil Gráfico da Eutrofização",
                codeOrFormula = "\\text{Ponto de Descarte} \\implies \\text{Pico de DBO} + \\text{Queda do Oxigênio Dissolvido (OD)}",
                explanation = "O aumento da matéria orgânica demanda mais oxigênio das bactérias, derrubando o oxigênio livre para os peixes."
            )
        ),
        goldTip = "Na prova do ENEM: Eutrofização NÃO mata peixes por envenenamento químico direto; mata peixes por ASFIXIA (as bactérias sugam todo o oxigênio dissolvido da água)!"
    )

    // =========================================================================
    // AULA bio_eco_06: Efeito Estufa e Aquecimento Global
    // =========================================================================
    private val bundleBioEco06 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
O aquecimento global antropogênico e as mudanças climáticas constituem um dos maiores desafios existenciais da humanidade no século XXI. No entanto, é fundamental não confundir o **Efeito Estufa Natural** (um fenômeno físico indispensável que tornou a Terra habitável) com a sua **intensificação descontrolada** promovida pela queima voraz de combustíveis fósseis e o desmatamento desde a Revolução Industrial. No ENEM, a física dos gases estufa, a subida do nível do mar e o trágico fenômeno biológico do **Branqueamento de Corais** por acidificação oceânica são temas de frequência quase obrigatória.

## 2. Objetivos de Aprendizagem
- Distinguir com rigor físico entre o **Efeito Estufa Natural** (benéfico e vital) e o **Aquecimento Global Antropogênico** (prejudicial).
- Identificar os principais Gases de Efeito Estufa (GEEs): Dióxido de Carbono (\( CO_2 \)), Metano (\( CH_4 \)), Óxido Nitroso (\( N_2O \)) e Vapor d'Água.
- Explicar o mecanismo físico de absorção e reemissão de radiação infravermelha térmica pelas moléculas dos gases estufa.
- Analisar a relação direta entre aumento de \( CO_2 \) dissolvido e a **Acidificação dos Oceanos**, explicando a destruição de conchas calcárias e o **Branqueamento de Corais**.
- Avaliar as consequências ecológicas globais: degelo continental, subida do nível do mar e perda de produtividade agrícola.

## 3. Pré-requisitos
- Ciclo do carbono e fotossíntese (Aula bio_eco_04).
- Relações ecológicas de mutualismo (corais e zooxantelas - Aula bio_eco_03).

## 4. Explicação Teórica Aprofundada

### 1. O Efeito Estufa Natural: Essencial para a Vida
- A radiação solar (luz visível e UV) atinge a Terra; a superfície se aquece e reemite essa energia na forma de **radiação infravermelha (ondas longas de calor)**.
- Gases estufa na atmosfera absorvem o infravermelho e o reemitem de volta para a Terra.
- **Importância:** Sem o efeito estufa natural, a Terra teria temperatura média de cerca de **-18 ºC** (congelada e estéril). Com o efeito estufa natural, a temperatura média é de cerca de **+15 ºC**!

### 2. O Aquecimento Global Antropogênico
A partir da Revolução Industrial, a queima massiva de petróleo, carvão e gás e o desmatamento elevaram o \( CO_2 \) atmosférico de 280 ppm para mais de **420 ppm**, retendo calor infravermelho em excesso.
- **Dióxido de Carbono (\( CO_2 \)):** Responsável pelo maior volume total de emissões antrópicas.
- **Metano (\( CH_4 \)):** Potencial de aquecimento cerca de **28 a 36 vezes superior ao do \( CO_2 \)**. Origens: rebanhos bovinos (fermentação entérica), cultivo de arroz inundado e aterros de lixo.
- **Óxido Nitroso (\( N_2O \)):** Cerca de 300 vezes mais potente que o \( CO_2 \), emitido pelo uso intensivo de adubos nitrogenados agrícolas no solo.

### 3. Acidificação dos Oceanos e Branqueamento de Corais
Os oceanos absorvem cerca de 25% a 30% do \( CO_2 \) antrópico:
1. O \( CO_2 \) reage com a água do mar formando ácido carbônico (\( H_2CO_3 \)).
2. O ácido dissocia-se liberando íons hidrogênio (\( H^+ \)).
3. O excesso de \( H^+ \) reage com os íons carbonato livres (\( CO_3^{2-} \)), transformando-os em bicarbonato.
4. **Impacto:** Faltam íons carbonato livres no mar. Animais de carapaça calcária (corais, moluscos, crustáceos) não conseguem construir suas estruturas de **carbonato de cálcio (\( CaCO_3 \))** ou sofrem dissolução das suas conchas!
- **Branqueamento de Corais:**
  - Os corais vivem em **mutualismo obrigatório com algas microscópicas (zooxantelas)** em seus tecidos, que conferem cor e fornecem açúcares fotossintetizados.
  - Com o aquecimento e acidificação da água, o coral sob estresse térmico **expulsa as zooxantelas**.
  - O coral perde a cor, expondo o esqueleto branco de calcário (o "branqueamento"). Se o calor persistir, o coral morre de inanição, destruindo o recife inteiro.

## 5. Definições e Conceitos
- **Albedo:** Fração da luz solar refletida por uma superfície (o gelo polar tem alto albedo; a água do mar escura tem baixo albedo).
- **Potencial de Aquecimento Global (GWP):** Capacidade de retenção de calor de um gás em comparação com a mesma massa de \( CO_2 \).
- **Zooxantelas:** Microalgas dinoflageladas mutualistas presentes nos tecidos de corais recifais.

## 6. Exemplos Resolvidos Passo a Passo
### Exemplo 1: Mecanismo de Branqueamento de Corais no ENEM
Por que o recife de coral perde suas cores vivas quando a água do mar se aquece?
- **Passo 1:** As cores vivas são conferidas pelas microalgas simbióticas chamadas **zooxantelas**.
- **Passo 2:** A água quente causa estresse celular e produção de toxinas pelas algas, forçando o coral a expulsá-las para a água.
- **Passo 3:** Sem as algas pigmentadas, revela-se a cor branca do esqueleto mineral de calcário subjacente do cnidário (branqueamento).

## 7. Aplicações Práticas e Modelagem
- **Acordo de Paris:** Compromisso internacional para limitar o aquecimento global a menos de 2 ºC acima dos níveis pré-industriais.
- **Transição Energética:** Descarbonização via matriz solar fotovoltaica, eólica e biocombustíveis sustentáveis.

## 8. Exercícios de Fixação
1. Qual a diferença entre o efeito estufa natural e o aquecimento antropogênico?
2. Por que o metano é tão preocupante no efeito estufa se seu volume é menor que o do gás carbônico?
3. Como a acidificação marinha prejudica animais com conchas calcárias?

## 9. Correção Comentada dos Exercícios
1. O efeito estufa natural mantém a Terra aquecida a +15 ºC permitindo a vida; o aquecimento global antropogênico é a intensificação desregulada provocada por ações humanas.
2. Porque possui poder de retenção de calor infravermelho de 28 a 36 vezes superior ao do gás carbônico por quilograma.
3. Porque o excesso de íons H+ consome o carbonato livre da água do mar, impedindo que moluscos e corais precipitem carbonato de cálcio para suas conchas.

## 10. Desafio de Domínio
Explique como o degelo das banquisas polares reduz o albedo da superfície oceânica e acelera o aquecimento em um ciclo de retroalimentação positiva.

## 11. Resumo e Pontos-Chave
- Efeito Estufa Natural: Vital (mantém +15 ºC em vez de -18 ºC).
- Aquecimento Antropogênico: Excesso de queima fóssil e queimadas -> \( CO_2 \) subiu de 280 para > 420 ppm.
- Gases: \( CO_2 \) (maior volume), \( CH_4 \) (28x mais potente), \( N_2O \) (300x mais potente).
- Acidificação Marinha: \( CO_2 \) consome carbonato livre -> destrói conchas de \( CaCO_3 \).
- Branqueamento: Calor estressa o coral, que expulsa as zooxantelas mutualistas e morre de inanição.

## 12. Referências Bibliográficas
- IPCC. *Climate Change 2023: Synthesis Report*. Genebra: IPCC, 2023.
- ODUM, E. P.; BARRETT, G. W. *Fundamentos de Ecologia*. 5ª ed. São Paulo: Cengage Learning, 2011.
- OPENSTAX. *Biology 2e*. Rice University: OpenStax, 2020.
- INEP. *Matriz de Referência para o ENEM: Ciências da Natureza e suas Tecnologias*. Brasília: MEC/INEP.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Química da Acidificação Oceânica",
                syntax = "CO_2 + H_2O \\rightleftharpoons H_2CO_3 \\rightleftharpoons H^+ + HCO_3^- \\quad ; \\quad H^+ + CO_3^{2-} \\rightleftharpoons HCO_3^-",
                description = "O excesso de íons H+ consome os íons carbonato livres do mar.",
                parametersInfo = "Impede a síntese e biomineralização de esqueletos calcários de CaCO3."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Potencial de Aquecimento Comparado (GWP)",
                syntax = "\\text{GWP}(CO_2) = 1 \\quad | \\quad \\text{GWP}(CH_4) \\approx 28\\text{-}36 \\quad | \\quad \\text{GWP}(N_2O) \\approx 300",
                description = "Capacidade relativa de retenção térmica infravermelha de cada gás na atmosfera.",
                parametersInfo = "Gases como metano e óxido nitroso possuem eficiência radiativa muito mais agressiva."
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "O Fenômeno do Branqueamento de Corais",
                codeOrFormula = "\\text{Água Quente} \\implies \\text{Coral expulsa Zooxantelas} \\implies \\text{Esqueleto Branco Exposto} \\implies \\text{Inanição}",
                explanation = "A perda das algas fotossintetizantes mutualistas impede o coral de obter glicose para sua sobrevivência."
            )
        ),
        goldTip = "Para o ENEM: O efeito estufa natural NÃO é um vilão ecológico; sem ele a Terra seria uma bola de gelo a -18 ºC. O vilão é a INTENSIFICAÇÃO antrópica desmedida!"
    )

    // =========================================================================
    // AULA bio_eco_07: Sustentabilidade e Tecnologias Ambientais
    // =========================================================================
    private val bundleBioEco07 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
A sobrevivência das futuras gerações e a preservação do patrimônio biológico da Terra dependem da transição imperativa do modelo de economia linear predatória ("extrair, produzir, consumir e descartar") para uma **Economia Circular e Sustentável**. No ENEM, a sustentabilidade não é tratada apenas como um conceito abstrato ou slogan publicitário, mas sim como a integração técnica entre conservação biológica, tecnologias de remediação de danos ambientais, fontes renováveis de energia e a mensuração de impactos por meio da **Pegada Ecológica**.

## 2. Objetivos de Aprendizagem
- Definir com precisão conceitual o princípio do **Desenvolvimento Sustentável** segundo o Relatório Brundtland ("Nosso Futuro Comum").
- Compreender a metodologia e as variáveis que compõem o cálculo da **Pegada Ecológica** e a Biocapacidade do planeta.
- Analisar as tecnologias de **Biorremediação** e fitorremediação aplicadas à limpeza de áreas contaminadas por petróleo e metais pesados.
- Explicar as vantagens ecológicas e agronômicas do **Controle Biológico de Pragas** em relação aos agrotóxicos convencionais.
- Comparar as matrizes energéticas renováveis (solar fotovoltaica, eólica, etanol de cana, biogás e hidrogênio verde), avaliando seus balanços de carbono.

## 3. Pré-requisitos
- Ciclos biogeoquímicos e fluxo de energia (Aulas bio_eco_02 e bio_eco_04).
- Impactos ambientais e poluição antrópica (Aula bio_eco_05).

## 4. Explicação Teórica Aprofundada

### 1. O Conceito de Desenvolvimento Sustentável
Consagrado pela Comissão Mundial sobre Meio Ambiente e Desenvolvimento da ONU em 1987 (Relatório Brundtland):
> *"Desenvolvimento sustentável é aquele que atende às necessidades das gerações presentes sem comprometer a capacidade das gerações futuras de atenderem às suas próprias necessidades."*
- **O Tripé da Sustentabilidade:**
  1. **Ambiental:** Não degradar recursos além da sua taxa de regeneração natural.
  2. **Econômico:** Gerar renda e viabilidade produtiva duradoura.
  3. **Social:** Reduzir desigualdades e garantir justiça social para todas as comunidades.

### 2. Pegada Ecológica e Biocapacidade
- **Pegada Ecológica:** Indicador internacional (em **hectares globais - gha**) que estima a quantidade de terra e água biologicamente produtivas necessária para gerar todos os recursos consumidos por uma população e absorver todos os seus resíduos gerados.
- **Biocapacidade:** Capacidade de regeneração biológica e absorção oferecida pelos ecossistemas locais.
- **Dia da Sobrecarga da Terra:** Data do ano em que a demanda coletiva humana ultrapassa a biocapacidade anual da Terra, operando em "déficit ecológico cumulativo" (a humanidade consome o equivalente a 1,7 planeta por ano).

### 3. Biorremediação: A Vida Restaurando o Ambiente
- **Biorremediação:** Uso de microrganismos vivos (bactérias e fungos) para degradar ou neutralizar poluentes no solo e água:
  - *Bactérias hidrocarbonoclásticas:* Degradam petróleo transformando hidrocarbonetos em água e \( CO_2 \).
  - *Bioestimulação:* Adição de nutrientes limitantes (como nitrogênio e fósforo) para acelerar a ação das bactérias nativas que degradam petróleo.
- **Fitorremediação:** Uso de espécies vegetais hiperacumuladoras para absorver e estocar metais pesados (chumbo, níquel, cádmio) do solo em seus tecidos vegetais, purificando a área.

### 4. Controle Biológico de Pragas
Uso de inimigos naturais (predadores ou parasitoides) para combater pragas agrícolas:
- **Vantagens:** Alta especificidade (não mata polinizadores como abelhas), não polui o lençol freático, não sofre biomagnificação trófica e evita a seleção rápida de mutantes resistentes que ocorre com agrotóxicos químicos.

### 5. Matrizes Energéticas Renováveis e Biocombustíveis
- **Etanol de Cana e Biodiesel:** Balanço de carbono neutro: o \( CO_2 \) emitido na queima no motor foi previamente absorvido pela própria planta durante a fotossíntese no campo.
- **Biogás:** Metano gerado pela biodigestão anaeróbia de esterco suíno ou bovino, gerando energia limpa a partir de passivos agrícolas.
- **Solar Fotovoltaica e Eólica:** Eletricidade limpa sem emissão de gases estufa durante a operação.

## 5. Definições e Conceitos
- **Economia Circular:** Modelo econômico regenerativo onde resíduos e produtos são reaproveitados continuamente.
- **Fitorremediação:** Uso de plantas hiperacumuladoras para descontaminar solos com metais pesados.
- **Biocapacidade:** Estoque biológico produtivo de regeneração de um território.

## 6. Exemplos Resolvidos Passo a Passo
### Exemplo 1: Limpeza de Óleo por Biorremediação no ENEM
Por que adicionar adubo contendo nitrogênio e fósforo ajuda a limpar uma praia arenosa com derramamento de petróleo?
- **Passo 1:** No petróleo há carbono em abundância extrema, mas a areia quase não possui nitrogênio e fósforo.
- **Passo 2:** As bactérias degradadoras de petróleo têm seu crescimento limitado pela falta de N e P para construir suas proteínas e DNA.
- **Passo 3:** A adição de adubo (bioestimulação) remove o nutriente limitante, acelerando a multiplicação bacteriana e a degradação biológica do petróleo.

## 7. Aplicações Práticas e Modelagem
- **Zonas Úmidas Construídas (*Wetlands*):** Lagos com plantas aquáticas cujas raízes e biofilmes depuram esgoto doméstico de forma biológica e barata.
- **Certificação FSC e Créditos de Carbono:** Mecanismos que incentivam conservação florestal e neutralização de emissões corporativas.

## 8. Exercícios de Fixação
1. Quais são os três pilares que compõem o tripé da sustentabilidade?
2. Em que consiste a fitorremediação de solos contaminados por metais pesados?
3. Por que o etanol de cana é considerado um combustível de balanço de carbono neutro?

## 9. Correção Comentada dos Exercícios
1. Os pilares **Ambiental**, **Econômico** e **Social**.
2. No plantio de espécies vegetais que absorvem metais tóxicos do solo por meio de suas raízes e os concentram em suas folhas para posterior colheita e incineração segura.
3. Porque o gás carbônico liberado na queima nos veículos automotores foi absorvido da atmosfera pela própria cana-de-açúcar durante sua fotossíntese na safra anterior.

## 10. Desafio de Domínio
Explique por que um país pode ter alta biocapacidade em seu território devido a florestas nativas e ainda assim apresentar cidadãos individuais com pegada ecológica insustentável.

## 11. Resumo e Pontos-Chave
- Desenvolvimento Sustentável: Atende ao presente sem comprometer o futuro. Tripé: Ambiental, Econômico e Social.
- Pegada Ecológica: Estima os hectares globais (gha) demandados pelo consumo de uma população.
- Biorremediação: Bactérias limpam petróleo e toxinas; Bioestimulação adiciona nutrientes limitantes (N e P).
- Fitorremediação: Plantas absorvem metais pesados do solo.
- Controle Biológico: Predadores e parasitoides eliminam pragas sem resíduos tóxicos ou biomagnificação.
- Etanol: Ciclo de carbono neutro via fotossíntese na lavoura.

## 12. Referências Bibliográficas
- COMISSÃO MUNDIAL SOBRE MEIO AMBIENTE E DESENVOLVIMENTO. *Nosso Futuro Comum* (Relatório Brundtland). Rio de Janeiro: FGV, 1991.
- GLOBAL FOOTPRINT NETWORK. *National Footprint Accounts*. 2023.
- ODUM, E. P.; BARRETT, G. W. *Fundamentos de Ecologia*. 5ª ed. São Paulo: Cengage Learning, 2011.
- INEP. *Matriz de Referência para o ENEM: Ciências da Natureza e suas Tecnologias*. Brasília: MEC/INEP.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Balanço de Sustentabilidade Ecológica",
                syntax = "\\Delta_{\\text{Ecológica}} = \\text{Biocapacidade Territorial} - \\text{Pegada Ecológica}",
                description = "Quando o consumo ultrapassa a regeneração, opera-se em déficit ecológico.",
                parametersInfo = "Mensurado anualmente pelo Global Footprint Network em hectares globais (gha)."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Ciclo Neutro dos Biocombustíveis",
                syntax = "CO_2^{\\text{ar}} \\xrightarrow{\\text{Fotossíntese}} \\text{Biomassa de Cana} \\xrightarrow{\\text{Combustão}} CO_2^{\\text{ar}} \\quad (\\Delta C \\approx 0)",
                description = "Ciclagem rápida do carbono contemporâneo sem adição líquida de carbono fóssil antigo.",
                parametersInfo = "Principal vantagem climática do etanol sobre a gasolina fóssil."
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Mecanismo da Bioestimulação Petrolífera",
                codeOrFormula = "\\text{Petróleo (Excesso de Carbono)} + \\text{Adição de Nitrogênio e Fósforo} \\implies \\text{Multiplicação Bacteriana Rápida}",
                explanation = "A remoção do nutriente limitante (N e P) permite que a microbiota nativa degrade os hidrocarbonetos aceleradamente."
            )
        ),
        goldTip = "Para o ENEM: Biorremediação usa BACTÉRIAS e microrganismos para digerir petróleo; Fitorremediação usa PLANTAS verdes para absorver metais pesados do solo!"
    )
}

