package com.example.data.biology.didactic

import com.example.data.math.CalculusDidacticBundle
import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample

/**
 * Conteúdo didático aprofundado de Biologia ENEM — Módulo 4: Fisiologia e Anatomia Humana (Aulas bio_fis_01 a bio_fis_04).
 * Estrutura rigorosa em 12 seções por aula, fundamentada em Guyton & Hall, Silverthorn, OpenStax Biology 2e e Matriz do ENEM / INEP.
 */
object BiologyPhysiologyDidactic {

    fun get(lessonId: String): CalculusDidacticBundle? {
        return when (lessonId) {
            "bio_fis_01" -> bundleBioFis01
            "bio_fis_02" -> bundleBioFis02
            "bio_fis_03" -> bundleBioFis03
            "bio_fis_04" -> bundleBioFis04
            else -> null
        }
    }

    // =========================================================================
    // AULA bio_fis_01: Sistemas do Corpo Humano e Anatomia Integrada
    // =========================================================================
    private val bundleBioFis01 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
O corpo humano funciona como uma metrópole biológica perfeitamente coordenada de mais de 30 trilhões de células vivas. Para que cada neurônio no cérebro ou miócito no pé receba glicose, oxigênio e mantenha seu pH constante, os sistemas digestório, respiratório, circulatório e excretor operam de maneira totalmente integrada. No ENEM, as questões de fisiologia nunca cobram apenas memorização de nomes de ossos ou enzimas isoladas, mas sim a interconexão funcional entre esses sistemas durante o exercício físico, altitude, estresse ou patologias.

## 2. Objetivos de Aprendizagem
- Compreender a integração fisiológica entre digestão de macronutrientes, trocas gasosas, transporte cardiovascular e homeostase osmótica e renal.
- Analisar a digestão e absorção dos alimentos ao longo do trato gastrointestinal (boca, estômago, duodeno e intestino delgado).
- Explicar a mecânica ventilatória pulmonar, o transporte de gases sanguíneos (oxi-hemoglobina e íons bicarbonato) e o controle pelo bulbo.
- Descrever o ciclo cardíaco, circulação dupla e completa e o papel dos capilares na troca de nutrientes e excretas.
- Detalhar a formação da urina no néfron (filtração glomerular, reabsorção e secreção tubular) e o controle hormonal por ADH e aldosterona.

## 3. Pré-requisitos
- Transporte através da membrana plasmática: difusão, osmose e transporte ativo (Aula bio_cit_02).
- Bioquímica celular: carboidratos, proteínas, lipídios e ATP (Aula bio_cit_01).

## 4. Explicação Teórica Aprofundada

### 1. Sistema Digestório: Da Ingestão à Absorção
![Integração dos Grandes Sistemas do Corpo Humano](file:///android_asset/images/biologia/fisiologia_sistemas_integrados.svg)

O trato gastrointestinal quebra macromoléculas insolúveis em monômeros absorvíveis:
- **Cavidade Oral (pH ~ 7,0):** Mastigação e insalivação. A amilase salivar (ptialina) inicia a hidrólise do amido em maltose.
- **Estômago (pH ~ 1,5 a 2,0):** O suco gástrico contém HCl secretado pelas células parietais (que desnatura proteínas e ativa o pepsinogênio em pepsina) e muco protetor. A pepsina quebra proteínas em peptídeos menores.
- **Intestino Delgado (Duodeno, pH ~ 8,0):**
  - O quimo ácido é neutralizado pelo bicarbonato do **suco pancreático**.
  - O fígado secreta a **bile** (armazenada na vesícula biliar), que **emulsifica gorduras** através de sais biliares, funcionando como detergente que aumenta a área de contato sem possuir enzimas digestivas.
  - Enzimas pancreáticas (tripsina, quimiotripsina, lipase pancreática, amilase pancreática, nucleases) e entéricas (maltase, sacarase, lactase, peptidases) completam a digestão química.
- **Absorção (Jejuno e Íleo):** Milhões de microvilosidades aumentam a área superficial em até 600 vezes. Monossacarídeos e aminoácidos entram nos capilares e vão direto para o fígado via veia porta hepática; lipídios (quilomícrons) entram nos vasos linfáticos (lactíferos).
- **Intestino Grosso:** Reabsorção de água e eletrólitos, formação de fezes e síntese de vitaminas K e B12 pela microbiota bacteriana.

### 2. Sistema Respiratório e Hematose
- **Hematose Pulmonar:** Ocorre nos alvéolos pulmonares por difusão passiva simples (a favor do gradiente de pressão parcial). O \( O_2 \) sai do alvéolo para o sangue capilar e o \( CO_2 \) sai do sangue para o alvéolo.
- **Transporte de Gases no Sangue:**
  - **Oxigênio (\( O_2 \)):** 98,5% ligado à **hemoglobina** dentro das hemácias (\( \text{Hb} + \text{O}_2 \rightleftharpoons \text{HbO}_2 \) - oxi-hemoglobina) e 1,5% dissolvido no plasma.
  - **Gás Carbônico (\( CO_2 \)):** Cerca de 70% é transportado na forma de **íon bicarbonato** (\( \text{HCO}_3^- \)) no plasma, 23% ligado à hemoglobina (carboemoglobina) e 7% dissolvido no plasma.
  - **Equilíbrio Ácido-Base e Bulbo:** A enzima anidrase carbônica nas hemácias catalisa o equilíbrio do tampão bicarbonato:
    \[ \text{CO}_2 + \text{H}_2\text{O} \rightleftharpoons \text{H}_2\text{CO}_3 \rightleftharpoons \text{H}^+ + \text{HCO}_3^- \]
    Durante atividade física intensa, o acúmulo de \( CO_2 \) eleva \( H^+ \) (acidose respiratória, queda no pH). O bulbo raquidiano detecta o pH ácido e acelera imediatamente a frequência respiratória (hiperventilação compensatória).

### 3. Sistema Cardiovascular Integrado
- **Coração Humano:** Quatro câmaras (dois átrios e dois ventrículos), circulação dupla e completa (não há mistura de sangue arterial e venoso).
- **Pequena Circulação (Pulmonar):** Ventrículo direito -> tronco e artérias pulmonares (sangue venoso) -> pulmões (hematose) -> veias pulmonares (sangue arterial) -> átrio esquerdo.
- **Grande Circulação (Sistêmica):** Ventrículo esquerdo -> artéria aorta (sangue arterial oxigenado) -> tecidos do corpo -> veias cavas superior e inferior (sangue venoso) -> átrio direito.
- **Diferenciação Anatômica:** Artérias transportam sangue que sai do coração (pressão elevada, paredes espessas e elásticas); Veias trazem sangue de volta ao coração (baixa pressão, válvulas semilunares para evitar refluxo contra a gravidade).

### 4. Sistema Excretor e a Fisiologia Renal
![Ultraestrutura do Néfron e Formação da Urina](file:///android_asset/images/biologia/nefron_ultraestrutura.svg)

Os rins filtram o plasma sanguíneo, eliminam resíduos nitrogenados metabólicos (a ureia em mamíferos) e regulam a volemia (volume de sangue) e osmolalidade:
- **Néfron (Unidade Funcional):**
  1. **Filtração Glomerular:** No glomérulo de Malpighi para a cápsula de Bowman. Passam água, glicose, aminoácidos, sais e ureia. Células sanguíneas e proteínas plasmáticas grandes NÃO passam no filtrado normal.
  2. **Reabsorção Tubular:** No túbulo contorcido proximal e alça de Henle, mais de 99% da água, 100% da glicose e aminoácidos retornam aos capilares peritubulares.
  3. **Secreção Tubular:** No túbulo contorcido distal, íons \( H^+ \), \( K^+ \) e toxinas são secretados ativamente do sangue para a luz tubular.
- **Regulação Hormonal Renal:**
  - **Hormônio Antidiurético (ADH ou Vasopressina):** Produzido no hipotálamo e liberado pela neuro-hipófise em resposta à desidratação (aumento da osmolaridade plasmática). O ADH insere canais de aquaporina nos ductos coletores, promovendo **reabsorção maciça de água**, concentrando a urina e reduzindo o volume urinário.
  - O consumo de **álcool etílico inibe a secreção de ADH**, gerando urina diluída e profusa, levando à desidratação e ressaca.
  - **Aldosterona:** Secretada pelo córtex suprarrenal, estimula a reabsorção ativa de sódio (\( Na^+ \)) e a eliminação de \( K^+ \), retendo água por osmose e aumentando a pressão arterial.

## 5. Definições e Conceitos
- **Homeostase:** Capacidade de manutenção do equilíbrio e estabilidade do meio interno corporal frente a flutuações externas.
- **Hematose:** Troca gasosa por difusão através da barreira alvéolo-capilar pulmonar.
- **Néfron:** Unidade morfofuncional dos rins responsável por filtração, reabsorção e secreção.
- **Acidose Respiratória:** Queda do pH plasmático decorrente do acúmulo de dióxido de carbono dissolvido no sangue.
- **Emulsificação:** Fragmentação mecânica de gotas lipídicas em microgotículas pela ação dos sais biliares anfipáticos.

## 6. Exemplos Resolvidos Passo a Passo
### Exemplo 1: Adaptação a Grandes Altitudes no ENEM
Um time de futebol brasileiro viaja para jogar na altitude de 3.600 metros em La Paz (Bolívia). Explique:
a) A alteração respiratória imediata nos primeiros minutos de jogo.
b) A alteração hematológica adaptativa após 3 semanas de aclimatação.
- **Passo 1 (Resposta Imediata):** Na altitude de 3.600 m, a pressão atmosférica total é muito menor, diminuindo a pressão parcial de oxigênio (\( pO_2 \)). Para compensar a menor saturação de oxi-hemoglobina, o indivíduo entra em taquipneia (hiperventilação imediata) para captar mais ar por minuto.
- **Passo 2 (Resposta Crônica a Médio Prazo):** A hipóxia tecidual prolongada estimula as células renais a secretarem o hormônio **eritropoetina (EPO)**. A EPO atua na medula óssea vermelha estimulando a diferenciação de eritroblastos em hemácias (eritropoiese), elevando o hematócrito (número de glóbulos vermelhos por volume de sangue), aumentando a capacidade total de transporte de \( O_2 \).

## 7. Aplicações Práticas e Modelagem
- **Insuficiência Renal e Hemodiálise:** Quando os néfrons perdem a capacidade de filtração, o paciente acumula ureia e potássio no sangue, necessitando de uma membrana de diálise semipermeável artificial para purificar o sangue semanalmente.
- **Pressão Arterial e Sistema Renina-Angiotensina-Aldosterona (SRAA):** Medicamentos anti-hipertensivos amplamente prescritos no SUS (como enalapril e losartana) agem inibindo a conversão de angiotensina I em angiotensina II ou bloqueando seus receptores, impedindo a vasoconstrição excessiva.

## 8. Exercícios de Fixação
1. Qual é a principal enzima digestiva presente no estômago e qual o pH ideal para sua atividade catalítica?
2. Por que o consumo excessivo de bebidas alcoólicas provoca aumento rápido no volume de urina expelido e sensação de sede intensa?
3. Explique por que a bile não é considerada um suco enzimático, detalhando sua verdadeira função física na digestão de gorduras.

## 9. Correção Comentada dos Exercícios
1. É a **pepsina**, uma protease que quebra proteínas em peptídeos menores. Seu pH ótimo é extremamente ácido, em torno de 1,5 a 2,0, gerado pela presença de ácido clorídrico (HCl).
2. O álcool atua no sistema nervoso central inibindo diretamente a secreção de ADH (hormônio antidiurético) pela glândula hipófise. Sem ADH, os ductos coletores renais não reabsorvem água livre de volta para o sangue; toda essa água é eliminada na urina (poliúria), resultando em desidratação e sede compensatória.
3. Porque a bile produzida pelo fígado não contém nenhuma enzima digestiva (não hidrolisa ligações químicas). Ela contém sais biliares que atuam como detergentes anfipáticos biológicos, emulsificando os lipídios em gotículas minúsculas para aumentar brutalmente a área de contato acessível às lipases pancreáticas.

## 10. Desafio de Domínio
Descreva a viagem completa de uma molécula de oxigênio desde a cavidade nasal até o citoplasma de um miócito do quadríceps na perna, citando em ordem todas as estruturas anatômicas, cavidades cardíacas e vasos sanguíneos percorridos.

## 11. Resumo e Pontos-Chave
- Digestão: Boca (amido, ptialina, pH 7) -> Estômago (pepsina, proteínas, HCl, pH 2) -> Duodeno (bile emulsifica lipídios, suco pancreático neutraliza e digere tudo, pH 8) -> Intestino delgado absorve por vilosidades.
- Respiração e Hematose: Alvéolos pulmonares por difusão simples. \( O_2 \) transportado por hemoglobina; \( CO_2 \) transportado majoritariamente como bicarbonato (\( HCO_3^- \)). Acidose por \( CO_2 \) estimula o bulbo.
- Circulação: Dupla e completa. Pequena circulação (Ventrículo D -> Pulmão -> Átrio E); Grande circulação (Ventrículo E -> Corpo -> Átrio D).
- Excreção Renal: Néfron filtra sangue na cápsula de Bowman; reabsorve água, sais e nutrientes; ADH poupa água concentrando a urina; aldosterona reabsorve sódio e água.

## 12. Referências Bibliográficas
- GUYTON, A. C.; HALL, J. E. *Tratado de Fisiologia Médica*. 13ª ed. Rio de Janeiro: Elsevier, 2016.
- SILVERTHORN, D. U. *Fisiologia Humana: Uma Abordagem Integrada*. 7ª ed. Porto Alegre: Artmed, 2017.
- OPENSTAX. *Biology 2e*. Rice University: OpenStax, 2020.
- INEP. *Matriz de Referência para o ENEM: Ciências da Natureza e suas Tecnologias*. Brasília: MEC/INEP.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Equilíbrio do Tampão Bicarbonato no Sangue",
                syntax = """\text{CO}_2 + \text{H}_2\text{O} \rightleftharpoons \text{H}_2\text{CO}_3 \rightleftharpoons \text{H}^+ + \text{HCO}_3^-""",
                description = "Equilíbrio químico dinâmico catalisado pela anidrase carbônica no interior das hemácias e no plasma.",
                parametersInfo = "A hipoventilação acumula CO2, deslocando a reação para a direita e provocando acidose respiratória (estímulo do bulbo)."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Controle Neuroendócrino da Osmorregulação Renal (ADH)",
                syntax = """\text{Desidratação (Alta Osmolaridade Sanguínea)} \xrightarrow{\text{Neuro-hipófise}} \text{ADH} \xrightarrow{\text{Ducto Coletor}} \text{Aquaporinas} \to \text{Reabsorção H}_2\text{O}""",
                description = "Mecanismo homeostático hipofisário que preserva água corporal reduzindo o volume urinário e aumentando sua concentração.",
                parametersInfo = "O álcool inibe a secreção de ADH na hipófise, gerando diurese desmedida e desidratação (ressaca)."
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "O Trajeto do Sangue no Ciclo Cardíaco",
                codeOrFormula = """\text{Veias Cavas} \to \text{Átrio D} \to \text{Ventrículo D} \to \text{Artérias Pulmonares} \to \text{Pulmões} \to \text{Veias Pulmonares} \to \text{Átrio E} \to \text{Ventrículo E} \to \text{Aorta}""",
                explanation = "O sangue venoso pobre em oxigênio entra no átrio direito, passa ao ventrículo direito, vai aos pulmões onde sofre hematose e retorna arterial pelo átrio esquerdo, de onde o ventrículo esquerdo o impulsiona pela aorta para todo o corpo."
            )
        ),
        goldTip = "Para o ENEM, lembre-se: Toda ARTÉRIA sai do coração (seja com sangue arterial ou venoso, como a artéria pulmonar). Toda VEIA chega ao coração (seja com sangue venoso ou arterial, como as veias pulmonares)!",
        assetImagePath = "images/biologia/fisiologia_sistemas_integrados.svg"
    )

    // =========================================================================
    // AULA bio_fis_02: Saúde e Doenças (Imunologia: Vacinas e Soros)
    // =========================================================================
    private val bundleBioFis02 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
A imunologia e as doenças infecciosas constituem historicamente o tema de saúde pública com maior incidência na prova de Ciências da Natureza do ENEM. Diante de epidemias globais de vírus emergentes, reaparecimento de doenças preveníveis por vacinação e o desafio dos acidentes com animais peçonhentos, a compreensão da distinção conceitual e clínica entre **Imunização Ativa (Vacina)** e **Imunização Passiva (Soro)** é fundamental para a formação cidadã e o sucesso acadêmico.

## 2. Objetivos de Aprendizagem
- Diferenciar com clareza conceitual **Antígenos** (moléculas estranhas indutoras de resposta) e **Anticorpos** (imunoglobulinas neutralizadoras específicas).
- Comparar os mecanismos, finalidades, duração e memória biológica de **Vacinas** (imunização ativa, preventiva) versus **Soros Terapêuticos** (imunização passiva, curativa de emergência).
- Compreender a dinâmica da resposta imune primária e secundária, mediada por Linfócitos B de memória e Linfócitos T auxiliadores (\( CD4^+ \)) e citotóxicos (\( CD8^+ \)).
- Caracterizar as principais infecções humanas causadas por vírus, bactérias, protozoários e vermes (helmintos), identificando vetores e medidas profiláticas.
- Explicar o fenômeno da imunidade de rebanho (coletiva) e as consequências da desinformação antivacina.

## 3. Pré-requisitos
- Estrutura proteica e reconhecimento molecular (Aula bio_cit_01).
- Diferenças entre vírus, bactérias procariontes e células eucariontes (Aula bio_cit_02).

## 4. Explicação Teórica Aprofundada

### 1. Antígenos vs. Anticorpos
- **Antígeno (Ag):** Qualquer substância química (proteína de capsídeo viral, polissacarídeo bacteriano, toxina de serpente ou pólen) que, ao ser introduzida em um organismo vertebrado, é reconhecida como não própria e induz uma resposta imunológica específica.
- **Anticorpo (Ac ou Imunoglobulina - Ig):** Glicoproteínas plasmáticas em forma de 'Y' secretadas por plasmócitos (linfócitos B diferenciados), cujas pontas das hastes possuem sítios de ligação variáveis perfeitamente complementares ao epítopo do antígeno.

### 2. A Grande Distinção: Vacina vs. Soro
![Imunização: Vacina (Ativa) vs. Soro (Passivo)](file:///android_asset/images/biologia/vacina_vs_soro.svg)

Esta é a comparação mais clássica e recorrente do ENEM:

| Característica | Vacina | Soro Terapêutico |
| :--- | :--- | :--- |
| **Tipo de Imunização** | **Ativa** (o próprio corpo do paciente produz a resposta) | **Passiva** (anticorpos prontos inoculados) |
| **Conteúdo Injetado** | **Antígenos** atenuados, inativados, fragmentos proteicos ou RNA mensageiro | **Anticorpos prontos** purificados (obtidos de cavalos hiperimunizados) |
| **Finalidade** | **Preventiva** (profilática) — administrada ANTES do contato com o patógeno | **Curativa / Emergencial** — administrada APÓS o contato ou intoxicação aguda |
| **Tempo de Resposta** | Lenta (demora dias ou semanas para produzir títulos protetores) | Imediata e fulminante (neutralização instantânea) |
| **Duração da Proteção** | Duradoura ou permanente (anos ou décadas) | Curta e passageira (os anticorpos degradam em poucas semanas) |
| **Células de Memória?** | **SIM** (cria linfócitos B e T de memória biológica) | **NÃO** (não induz formação de células de memória) |
| **Exemplos Típicos** | Tríplice viral, BCG, Poliomielite, COVID-19, HPV, Febre Amarela | Soro antiofídico (picada de cobra), antiescorpiônico, antirrábico |

### 3. Dinâmica Imunológica: Resposta Primária vs. Secundária
- **Resposta Imune Primária:** Primeiro contato com o antígeno (seja pela 1ª dose da vacina ou por infecção natural). Há um período de latência de 7 a 14 dias enquanto macrófagos apresentam antígenos para linfócitos T auxiliadores (\( CD4^+ \)), que ativam linfócitos B. A produção de anticorpos (iniciada por IgM e depois IgG) é lenta e com títulos moderados. Formam-se **células de memória B e T**.
- **Resposta Imune Secundária:** Segundo contato com o mesmo antígeno (ou infecção real posterior). As células de memória reconhecem o invasor quase imediatamente. Em menos de 24 a 48 horas, produzem-se gigantescas concentrações de IgG altamente específicas, destruindo o patógeno antes que cause doença sintomática.

### 4. Panorama de Patógenos no ENEM
- **Doenças Virais (Viroses):** Dengue, Zika, Chikungunya, Febre Amarela (todas transmitidas pelo mosquito *Aedes aegypti*); Gripe (Influenza), COVID-19 (SARS-CoV-2); Raiva; Sarampo; Poliomielite; AIDS (HIV, que ataca linfócitos \( CD4^+ \)).
- **Doenças Bacterianas (Bacterioses):** Tuberculose, Hanseníase, Cólera (água contaminada), Tétano (toxina bacteriana anaeróbia), Sífilis (IST por *Treponema pallidum*), Leptospirose (urina de rato em enchentes). Combatidas com **antibióticos**.
- **Doenças por Protozoários (Protozooses):**
  - **Doença de Chagas:** Causada pelo *Trypanosoma cruzi*, transmitida pelas fezes do barbeiro (*Triatoma infestans*) contaminando a ferida da picada, ou por ingestão de açaí ou caldo de cana contaminados.
  - **Malária:** Causada pelo *Plasmodium*, transmitida pela fêmea do mosquito-prego (*Anopheles*). Provoca acessos periódicos de febre alta devido ao rompimento sincronizado de hemácias.
  - **Leishmaniose:** Transmitida pelo mosquito-palha (*Lutzomyia*).
- **Verminoses (Helmintíases):**
  - **Esquistossomose (Barriga d'água):** Causada pelo platelminto *Schistosoma mansoni*. Cercárias liberadas pelo caramujo d'água doce (*Biomphalaria*) penetram ativamente na pele de pessoas em lagoas contaminadas.
  - **Teníase vs. Cisticercose:** Teníase (ingestão de carne malcozida de porco ou boi contendo cisticercos); Cisticercose (ingestão acidental de ovos de *Taenia solium* da água ou vegetais, desenvolvendo cistos no cérebro ou músculos).
  - **Ascaridíase e Ancilostomíase (Amarelão):** Ingestão de ovos ou penetração ativa de larvas pelo solo descalço.

## 5. Definições e Conceitos
- **Imunidade de Rebanho:** Proteção coletiva indireta conferida a indivíduos vulneráveis ou não vacinados quando uma alta porcentagem da população (geralmente > 80-95%) está imunizada, interrompendo as cadeias de transmissão.
- **Macrófagos e Células Dendríticas:** Células fagocíticas apresentadoras de antígeno (APCs) que processam peptídeos estranhos e os apresentam aos linfócitos no complexo MHC.
- **Profilaxia:** Conjunto de medidas médicas e sanitárias adotadas para prevenir o surgimento ou disseminação de doenças em uma população.

## 6. Exemplos Resolvidos Passo a Passo
### Exemplo 1: Conduta Médica Frente a Acidente Ofídico no ENEM
Um agricultor é picado no pé por uma serpente jararaca (*Bothrops*) no interior rural. Ele comparece ao posto de saúde apresentando dor intensa, edema e risco de hemorragias sistêmicas causadas pela peçonha. Qual produto biológico deve ser administrado imediatamente: vacina ou soro? Justifique do ponto de vista cinético e imunológico.
- **Passo 1 (Análise do Tempo Crítico):** A peçonha botrópica contém enzimas proteolíticas e toxinas coagulantes que destroem tecidos em poucas horas. A administração de uma vacina estimularia o sistema imune do paciente a produzir anticorpos próprios, o que levaria no mínimo 10 a 14 dias para gerar títulos significativos. Nesse prazo, o agricultor já teria sofrido necrose grave, insuficiência renal ou morte.
- **Passo 2 (Solução Imunológica):** Deve-se injetar imediatamente o **SORO ANTIBOTRÓPICO** (imunização passiva com anticorpos hiperimunes de cavalo prontos). Esses anticorpos ligam-se instantaneamente às toxinas circulantes da serpente, neutralizando sua ação tóxica de forma imediata antes que causem lesão irreversível.

## 7. Aplicações Práticas e Modelagem
- **Vacinas de mRNA (Tecnologia Revolucionária):** As vacinas modernas contra a COVID-19 injetam uma molécula sintética de mRNA envolvida em nanopartículas lipídicas contendo o código para a proteína Spike do vírus. As próprias células do receptor traduzem a proteína e ensinam o sistema imune a reconhecê-la sem qualquer risco de infecção pelo patógeno vivo.
- **Controle da Dengue e Wolbachia:** Liberação de mosquitos *Aedes aegypti* infectados com a bactéria intracelular *Wolbachia*, que bloqueia biologicamente a replicação do vírus da dengue, febre amarela e zika dentro do mosquito, reduzindo o contágio humano sem uso de pesticidas agressivos.

## 8. Exercícios de Fixação
1. Qual a diferença fundamental quanto ao conteúdo injetado entre uma vacina e um soro medicinal?
2. Por que a picada pelo mosquito barbeiro, por si só, não transmite a Doença de Chagas se o indivíduo não coçar o local?
3. O que são linfócitos de memória e por que uma pessoa que já teve sarampo uma vez raramente adoece de sarampo novamente ao longo de sua vida?

## 9. Correção Comentada dos Exercícios
1. A vacina injeta **antígenos** (partículas virais inativadas, atenuadas ou proteínas) para forçar o organismo a criar seus próprios anticorpos e memória; o soro injeta **anticorpos prontos** para neutralização imediata em situações de emergência sem gerar memória.
2. Porque o parasita *Trypanosoma cruzi* não está presente na saliva do barbeiro, mas sim nas fezes que ele defeca na pele da vítima enquanto suga sangue. O protozoário penetra na circulação sanguínea apenas quando a pessoa coça o local da picada, arrastando as fezes contaminadas para a ferida da pele ou mucosas.
3. São clones de linfócitos B e T de vida longa que persistem em órgãos linfoides após a primeira exposição ao vírus do sarampo. Quando ocorre uma nova invasão pelo mesmo patógeno, essas células desencadeiam uma resposta secundária ultra-rápida e massiva de anticorpos neutralizantes antes que o vírus consiga produzir sintomas clínicos.

## 10. Desafio de Domínio
Diferencie o ciclo de contaminação e as medidas profiláticas entre a Teníase e a Cisticercose humana causadas pela *Taenia solium*, explicando por que uma pessoa vegetariana estrita pode desenvolver cisticercose cerebral mesmo sem comer nenhum pedaço de carne na vida.

## 11. Resumo e Pontos-Chave
- Vacina = Imunização Ativa, Preventiva, Antígeno, Gera Memória Imunológica Duradoura.
- Soro = Imunização Passiva, Curativa Emergencial, Anticorpos Prontos, Sem Memória Biológica.
- Resposta Primária: Lenta (dias/semanas), títulos baixos de anticorpos. Resposta Secundária: Instantânea (horas/dias), títulos gigantescos de IgG protetora.
- Principais protozooses cobradas: Chagas (barbeiro, fezes), Malária (*Anopheles*, febres cíclicas por lise de hemácias), Leishmaniose (mosquito-palha).
- Esquistossomose: Cercária penetra na pele; caramujo *Biomphalaria* é hospedeiro intermediário. Saneamento básico é a chave profilática.

## 12. Referências Bibliográficas
- ABBAS, A. K.; LICHTMAN, A. H.; PILLAI, S. *Imunologia Celular e Molecular*. 9ª ed. Rio de Janeiro: Elsevier, 2019.
- BRASIL. Ministério da Saúde. *Guia de Vigilância em Saúde*. Brasília: Ministério da Saúde, 2022.
- OPENSTAX. *Biology 2e*. Rice University: OpenStax, 2020.
- INEP. *Matriz de Referência para o ENEM: Ciências da Natureza e suas Tecnologias*. Brasília: MEC/INEP.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Rota da Imunização Ativa (Vacina)",
                syntax = """\text{Vacina (Antígeno)} \xrightarrow{\text{Apresentação por APCs}} \text{Ativação B e T} \to \text{Anticorpos Próprios} + \text{Memória Imunológica}""",
                description = "Gera imunidade duradoura e preventiva através da resposta clonal própria mediada por plasmócitos e linfócitos de memória.",
                parametersInfo = "Essencial em políticas de saúde pública preventivas e imunidade coletiva (de rebanho)."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Rota da Imunização Passiva (Soro Terapêutico)",
                syntax = """\text{Soro (Anticorpos Heterólogos)} \xrightarrow{\text{Inoculação Parenteral}} \text{Neutralização Imediata In Vivo} \quad [\text{Sem Memória}]""",
                description = "Fornece imunoglobulinas prontas para emergências clínicas agudas (peçonhas ofídicas ou toxinas bacterianas letais).",
                parametersInfo = "Não estimula clones de memória; a meia-vida das proteínas séricas limita a proteção a poucas semanas."
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Comparação da Resposta Primária vs. Secundária",
                codeOrFormula = """\text{Resposta Primária: Latência 10 dias, Pico moderado} \\ \text{Resposta Secundária: Latência < 48h, Pico 100x maior}""",
                explanation = "A presença prévia de clones de linfócitos de memória permite que a produção de anticorpos da classe IgG dispare de maneira quase instantânea no segundo contato."
            )
        ),
        goldTip = "Regra prática definitiva do ENEM: 'Vacina começa com V de Vai prevenir. Soro começa com S de Socorro imediato!'",
        assetImagePath = "images/biologia/vacina_vs_soro.svg"
    )

    // =========================================================================
    // AULA bio_fis_03: Reprodução e Gravidez (Ciclo Menstrual e Embriogênese)
    // =========================================================================
    private val bundleBioFis03 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
A perpetuação biológica da espécie humana depende de uma complexa orquestração hormonal entre o sistema nervoso central (hipotálamo e hipófise anterior) e os órgãos reprodutores. O ciclo menstrual feminino, longe de ser apenas um evento mensal de sangramento uterino, é um mecanismo de feedback endocrinológico altamente refinado projetado para maturar um oócito e preparar o endométrio para uma possível gestação. O ENEM cobra intensamente a leitura de gráficos hormonais (FSH, LH, estrogênio e progesterona), a fecundação, nidação e os anexos embrionários.

## 2. Objetivos de Aprendizagem
- Interpretar com precisão os gráficos do ciclo ovariano e uterino ao longo dos 28 dias do ciclo menstrual padrão.
- Identificar as glândulas produtoras, os alvos biológicos e os efeitos do FSH, LH, estrógeno e progesterona.
- Compreender os mecanismos de feedback negativo e feedback positivo na ovulação.
- Explicar a trajetória gamética: capacitação espermática, fecundação na tuba uterina, clivagens e nidação do blastocisto.
- Detalhar as funções biológicas da placenta e dos anexos embrionários (âmnio, córion, saco vitelínico e alantoide) e a ação do hCG.

## 3. Pré-requisitos
- Meiose e gametogênese: ovogênese vs. espermatogênese (Aula bio_cit_05).
- Sinalização celular e receptores hormonais proteicos e lipídicos esteroides (Aula bio_cit_01).

## 4. Explicação Teórica Aprofundada

### 1. O Eixo Hipotálamo-Hipófise-Ovário
![Ciclo Menstrual e Flutuações Hormonais de 28 Dias](file:///android_asset/images/biologia/ciclo_menstrual_hormonios.svg)

O ciclo menstrual dura em média 28 dias (dividido em Fase Folicular, Ovulação e Fase Lútea):
1. **Fase Folicular (Dias 1 a 13):**
   - No início do ciclo (Dia 1, primeiro dia da menstruação), os níveis de estrogênio e progesterona estão baixos.
   - O hipotálamo secreta GnRH, que induz a adenohipófise a secretar **FSH (Hormônio Folículo-Estimulante)**.
   - O FSH estimula o crescimento e maturação de folículos no ovário.
   - Conforme o folículo ovariano cresce, suas células foliculares passam a secretar crescentes quantidades de **Estrogênio (Estradiol)**.
   - O estrogênio induz a proliferação e reconstrução do **endométrio** (parede interna vascularizada do útero).
2. **Ovulação (Dia 14):**
   - O pico elevado e sustentado de estrogênio no final da fase folicular gera um mecanismo excepcional de **feedback positivo** sobre a hipófise.
   - Esse feedback provoca uma descarga massiva de **LH (Pico de Hormônio Luteinizante)** e uma elevação de FSH.
   - O pico agudo de LH rompe a parede do folículo ovariano maduro de Graaf, expelindo o **oócito secundário** (estacionado na metáfase II da meiose) em direção às fímbrias da tuba uterina: este evento é a **ovulação**.
3. **Fase Lútea / Secretora (Dias 15 a 28):**
   - As células do folículo rompido que permaneceram no ovário transformam-se, sob estímulo do LH, no **corpo lúteo (ou corpo amarelo)**.
   - O corpo lúteo torna-se uma glândula endócrina temporária que secreta grandes quantidades de **Progesterona** e doses moderadas de estrogênio.
   - A progesterona prepara o endométrio para a gravidez: as glândulas endometriais tornam-se espessas, ricas em glicogênio e altamente vascularizadas (endométrio secretor).
   - Altas taxas de progesterona e estrogênio exercem **feedback negativo** bloqueando a secreção de FSH e LH na hipófise, impedindo que novos folículos amadureçam durante esse período.

### 2. O Destino: Menstruação ou Gravidez?
- **Se NÃO houver Fecundação:**
  - Pela falta continuada de LH, o corpo lúteo degenera por volta do dia 26 e transforma-se em uma cicatriz fibrosa esbranquiçada (*corpus albicans*).
  - Sem corpo lúteo, os níveis de **progesterona e estrogênio despencam bruscamente**.
  - A queda drástica de progesterona provoca a constrição das artérias espiraladas do útero, levando à necrose e descamação do endométrio: é a **menstruação** (marcando o início de um novo ciclo no Dia 1).
- **Se HOUVER Fecundação:**
  - A fecundação ocorre no terço distal da **tuba uterina**.
  - O zigoto inicia sucessivas clivagens mitóticas (zigoto -> mórula -> blastocisto) enquanto desce em direção ao útero.
  - Cerca de 6 a 7 dias após a fecundação, a estrutura em estágio de **blastocisto** implanta-se na parede endometrial: processo denominado **nidação**.
  - As células externas do blastocisto (o trofoblasto, futuro córion embrionário) passam a secretar o hormônio **hCG (Gonadotrofina Coriônica Humana)**.
  - **Ação vital do hCG:** O hCG atua nos mesmos receptores do LH, impedindo a degeneração do corpo lúteo! O corpo lúteo gravídico permanece ativo e continua secretando progesterona e estrogênio em níveis elevados, mantendo o endométrio íntegro e bloqueando qualquer menstruação.
  - **Testes de Gravidez:** Detectam a presença do hormônio hCG na urina ou sangue materno logo após a nidação.
  - A partir do 3º mês de gestação, a **placenta madura** assume a produção autônoma de progesterona e estrogênio, permitindo que o corpo lúteo regrida sem interromper a gravidez.

### 3. Anexos Embrionários e Placenta
- **Âmnio (Bolsa Amniótica):** Membrana repleta de líquido amniótico que circunda o embrião, protegendo-o contra dessecação e choques mecânicos e mantendo a temperatura constante.
- **Córion:** Membrana mais externa que forma vilosidades coriônicas integradas ao endométrio, compondo a face fetal da placenta.
- **Saco Vitelínico:** Em mamíferos placentários é atrofiado (pouco vitelo), mas dá origem às primeiras células hematopoiéticas (sangue fetal) e células germinativas primordiais.
- **Alantoide:** Armazena resíduos nitrogenados em aves e répteis; em mamíferos dá origem aos vasos do **cordão umbilical** (duas artérias umbilicais desoxigenadas e uma veia umbilical oxigenada).
- **Placenta Humana:** Órgão misto (materno-fetal) que realiza trocas gasosas, aporte de nutrientes, excreção, imunidade passiva natural (passagem transplacentária de anticorpos maternos da classe IgG) e síntese endócrina de hormônios gestacionais. **Importante:** O sangue da mãe e o sangue do feto NÃO se misturam fisicamente; as trocas ocorrem por difusão através dos capilares das vilosidades coriônicas.

## 5. Definições e Conceitos
- **Feedback Negativo:** Mecanismo homeostático em que o produto final de uma via endócrina inibe os estímulos iniciais de sua própria produção.
- **Nidação:** Fixação do blastocisto na mucosa endometrial uterina, marcando o início biológico da gravidez.
- **Corpo Lúteo:** Estrutura formada a partir das células do folículo rompido após a ovulação, principal fonte de progesterona.
- **Líquido Amniótico:** Fluido estéril que amortece traumas mecânicos e permite os movimentos e o desenvolvimento articular e muscular do feto.

## 6. Exemplos Resolvidos Passo a Passo
### Exemplo 1: Interpretação de Gráficos de Hormônios no ENEM
Em uma questão do ENEM, é apresentado um gráfico contendo quatro curvas de hormônios sanguíneos femininos ao longo de 28 dias: Curva 1 (pico agudo estrondoso no 14º dia); Curva 2 (sobe gradativamente até o 13º dia e tem segundo pico moderado no 21º dia); Curva 3 (permanece basal até o dia 14, atinge valor máximo no dia 21 e desaba no dia 27); Curva 4 (apresenta pico discreto no início e acompanha o pico do dia 14). Identifique cada hormônio.
- **Passo 1 (Curva 1):** O pico agudo e de curta duração exatamente nas 24-36 horas que antecedem a liberação do oócito (dia 14) só pode ser o **LH (Hormônio Luteinizante)**, gatilho da ovulação.
- **Passo 2 (Curva 3):** O hormônio que só se eleva na segunda metade do ciclo (após o surgimento do corpo lúteo no dia 14), mantendo-se alto no platô da fase lútea (dia 21) e caindo antes da menstruação, é a **Progesterona**.
- **Passo 3 (Curva 2):** O hormônio com pico elevado pré-ovulatório (dia 13) e nova elevação moderada pela secreção combinada do corpo lúteo é o **Estrogênio (Estradiol)**.
- **Passo 4 (Curva 4):** O hormônio que estimula o crescimento dos folículos no início do ciclo e faz um pico paralelo discreto junto ao LH é o **FSH**.

## 7. Aplicações Práticas e Modelagem
- **Reprodução Assistida e Fertilização In Vitro (FIV):** Injeção de doses farmacológicas exógenas de FSH para estimular múltiplos folículos ovarianos em um único ciclo, seguida de coleta ovular por punção, fecundação em laboratório e implantação dos embriões.
- **Testes de Farmácia de hCG:** Utilizam tiras de imunocromatografia com anticorpos monoclonais marcados que capturam a subunidade beta do hCG presente na urina, fornecendo diagnóstico precoce com altíssima especificidade.

## 8. Exercícios de Fixação
1. Qual hormônio é responsável por provocar a ruptura do folículo ovariano na ovulação do 14º dia do ciclo padrão?
2. Por que a menstruação não ocorre se uma mulher engravidar após a nidação do blastocisto?
3. O sangue da mãe se mistura diretamente com o sangue do feto dentro do sistema circulatório da placenta? Explique.

## 9. Correção Comentada dos Exercícios
1. É o pico de **LH (Hormônio Luteinizante)** secretado pela adenoipófise em resposta ao feedback positivo estrogênico.
2. Porque as células trofoblásticas do embrião recém-nidado passam a produzir **hCG**, que estimula a persistência e atividade contínua do corpo lúteo. Com isso, os níveis de progesterona permanecem elevados e o endométrio não se descama, impossibilitando a menstruação.
3. Não! O sangue materno e fetal nunca se misturam diretamente; há uma barreira hematoplacentária. As trocas de oxigênio, nutrientes, ureia e anticorpos ocorrem por processos de transporte celular através das paredes dos capilares fetais imersos nos lagos sanguíneos maternos.

## 10. Desafio de Domínio
Explique detalhadamente por que uma mulher com insuficiência lútea (degeneração prematura do corpo lúteo com incapacidade de sustentar a progesterona) tem facilidade para engravidar na tuba uterina, mas sofre abortos espontâneos recorrentes na primeira semana após a fertilização.

## 11. Resumo e Pontos-Chave
- Fase Folicular: FSH estimula folículo -> Folículo produz Estrogênio -> Estrogênio reconstrói endométrio.
- Ovulação: Pico de LH (dia 14) rompe folículo e libera oócito secundário.
- Fase Lútea: Corpo lúteo produz Progesterona -> Prepara endométrio secretor.
- Sem fecundação: Corpo lúteo atrofia -> Queda de progesterona -> Menstruação.
- Com fecundação: Embrião produz hCG -> Mantém corpo lúteo -> Progesterona alta -> Sem menstruação.
- Anexos: Âmnio (hidratação e choque), Placenta (trocas e progesterona tardia), Cordão umbilical (vasos alantóideos).

## 12. Referências Bibliográficas
- MOORE, K. L.; PERSAUD, T. V. N.; TORCHIA, M. G. *Embriologia Clínica*. 10ª ed. Rio de Janeiro: Elsevier, 2016.
- GUYTON, A. C.; HALL, J. E. *Tratado de Fisiologia Médica*. 13ª ed. Rio de Janeiro: Elsevier, 2016.
- OPENSTAX. *Biology 2e*. Rice University: OpenStax, 2020.
- INEP. *Matriz de Referência para o ENEM: Ciências da Natureza e suas Tecnologias*. Brasília: MEC/INEP.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Rota Hormonal da Ovulação e Fase Lútea",
                syntax = """\text{Pico de Estrogênio (Dia 13)} \xrightarrow{\text{Feedback Positivo}} \text{Surto de LH (Dia 14)} \to \text{Ovulação} \to \text{Corpo Lúteo (Progesterona)}""",
                description = "Sincronização neuroendócrina ovariana e uterina responsável pelo amadurecimento folicular e ovulação.",
                parametersInfo = "A progesterona secretada pelo corpo lúteo inibe FSH e LH por feedback negativo, mantendo o endométrio secretor íntegro."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Sinalização Bioquímica e Manutenção Gravídica",
                syntax = """\text{Nidação do Blastocisto} \xrightarrow{\text{hCG}} \text{Manutenção do Corpo Lúteo} \implies \text{Progesterona Alta} \to \text{Bloqueio da Menstruação}""",
                description = "O trofoblasto embrionário secreta gonadotrofina coriônica (hCG) que impede a regressão do corpo lúteo.",
                parametersInfo = "Base biológica dos testes de gravidez de urina e sangue nas primeiras semanas gestacionais."
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Identificação das Curvas Hormonais",
                codeOrFormula = """\text{FSH: sobe no início} \\ \text{LH: pico estrondoso no dia 14} \\ \text{Estrogênio: pico antes da ovulação} \\ \text{Progesterona: platô pós-ovulação}""",
                explanation = "A progesterona é o marcador absoluto da fase lútea (segunda metade do ciclo menstrual)."
            )
        ),
        goldTip = "Gráfico de hormônio feminino no ENEM: Se a curva só cresce na segunda metade (após o dia 14), é PROGESTERONA. Se tem um pico gigante agudo bem no meio (dia 14), é LH!",
        assetImagePath = "images/biologia/ciclo_menstrual_hormonios.svg"
    )

    // =========================================================================
    // AULA bio_fis_04: Métodos Contraceptivos e Infecções Sexualmente Transmissíveis (ISTs)
    // =========================================================================
    private val bundleBioFis04 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
A saúde reprodutiva e a prevenção de Infecções Sexualmente Transmissíveis (ISTs) constituem diretrizes prioritárias da BNCC e do ENEM. O planejamento familiar contemporâneo dispõe de um amplo arsenal de métodos contraceptivos: de barreira, hormonais orais e injetáveis, dispositivos intrauterinos (DIUs), métodos comportamentais e cirúrgicos definitivos. No entanto, o ENEM cobra constantemente a compreensão de que a imensa maioria dos métodos anticoncepcionais altamente eficazes contra a gravidez **NÃO confere nenhuma proteção biológica contra as ISTs**, sendo a camisinha masculina e feminina a única tecnologia de barreira com dupla proteção.

## 2. Objetivos de Aprendizagem
- Classificar e comparar os métodos contraceptivos: barreira, hormonais combinados, dispositivos intrauterinos (DIU de cobre vs. hormonal), comportamentais e cirúrgicos (vasectomia e laqueadura tubária).
- Compreender o mecanismo fisiológico da pílula anticoncepcional combinada (bloqueio da ovulação por feedback negativo contínuo de estrogênio e progesterona sintéticos sobre a hipófise).
- Explicar as repercussões reprodutivas e anatômicas da vasectomia masculina e da laqueadura feminina.
- Caracterizar as principais ISTs (HIV/AIDS, HPV, Sífilis, Gonorreia, Herpes genital e Hepatite B), identificando agentes etiológicos, sintomas e formas de prevenção.
- Diferenciar os tratamentos profiláticos de urgência contra o HIV: PrEP (Pré-Exposição) e PEP (Pós-Exposição).

## 3. Pré-requisitos
- O ciclo menstrual e os hormônios hipofisários FSH e LH (Aula bio_fis_03).
- Imunologia básica: vírus, bactérias e anticorpos (Aula bio_fis_02).

## 4. Explicação Teórica Aprofundada

### 1. Métodos Contraceptivos e seus Mecanismos
![Classificação e Mecanismos dos Métodos Contraceptivos e ISTs](file:///android_asset/images/biologia/metodos_contraceptivos.svg)

#### A. Métodos de Barreira
- **Preservativo Masculino e Feminino (Camisinha):** Feitos de látex ou poliuretano. Impedem fisicamente o contato entre os fluidos genitais e o depósito de sêmen na vagina. **Vantagem exclusiva:** São os **ÚNICOS métodos que protegem concomitantemente contra ISTs e gravidez**.
- **Diafragma:** Cúpula de silicone flexível inserida no colo do útero antes da relação, usualmente combinada com espermicida. Bloqueia a subida dos espermatozoides, mas não previne ISTs de transmissão por contato de mucosa ou pele.

#### B. Métodos Hormonais
- **Pílula Anticoncepcional Combinada:** Contém doses diárias de derivados sintéticos de estrogênio e progestina.
  - **Mecanismo Fisiológico de Ação:** O fornecimento contínuo desses hormônios na circulação mantém uma alça constante de **feedback negativo sobre a adenohipófise**, inibindo a secreção de **FSH e LH**. Sem o surto de FSH, os folículos ovarianos não amadurecem; sem o pico de LH no 14º dia, **A OVULAÇÃO NÃO OCORRE**. Além disso, a progestina torna o muco cervical espesso e hostil à passagem espermática.
- **Pílula do Dia Seguinte (Contracepção de Emergência):** Dose única massiva de levonorgestrel (progestina sintética de alta potência). Usada exclusivamente em emergências (rompimento da camisinha ou estupro). Se tomada antes da ovulação, atrasa ou bloqueia a liberação do oócito; se tomada após, altera o muco cervical e a motilidade tubária. **Não é abortiva**, pois não interrompe uma gestação já implantada (nidação consumada).
- **Implantes Subdérmicos e Injeções Trimestrais:** Liberação lenta e contínua de hormônios esteroides no organismo com altíssima taxa de eficácia.

#### C. Dispositivos Intrauterinos (DIU)
- **DIU de Cobre (Não Hormonal):** Artefato plástico envolvido por fios de cobre inserido na cavidade uterina pelo ginecologista. Libera íons de cobre (\( Cu^{2+} \)) que produzem uma reação inflamatória citotóxica asséptica local no endométrio e muco, imobilizando e destruindo os espermatozoides e impedindo a fecundação. Possui duração de 5 a 10 anos sem interferir nos hormônios naturais da mulher.
- **DIU Hormonal (SIU de Levonorgestrel):** Libera microdoses de progesterona localmente no endométrio, atrofia a mucosa uterina e espessa o muco cervical.

#### D. Métodos Cirúrgicos Definitivos
- **Vasectomia (Masculina):** Secção e ligadura cirúrgica bilateral dos **canais deferentes** (ductos deferentes) na bolsa escrotal.
  - **Repercussão Biológica:** Os testículos continuam produzindo testosterona normalmente (que cai diretamente no sangue pelos vasos sanguíneos, mantendo libido, voz e ereção inalteradas) e continuam produzindo espermatozoides (que são reabsorvidos por macrófagos no epidídimo). O líquido seminal continua sendo expelido na ejaculação (produzido pelas vesículas seminais e próstata), porém totalmente **isento de espermatozoides** (azoospermia).
- **Laqueadura Tubária (Feminina):** Secção e ligadura cirúrgica das **tubas uterinas**. Os ovários continuam funcionando e ovulando normalmente, mas os gametas feminino e masculino não conseguem se encontrar fisicamente na tuba uterina para que ocorra a fecundação.

### 2. Infecções Sexualmente Transmissíveis (ISTs) no ENEM

#### A. Infecções Causadas por Vírus
- **HIV / AIDS:** O vírus da imunodeficiência humana infecta e destrói especificamente os **linfócitos T CD4+** (maestros da imunidade celular). O paciente desenvolve imunodeficiência grave com infecções oportunistas fatais (pneumonia por *Pneumocystis*, sarcoma de Kaposi, tuberculose). Transmitido por sexo sem proteção, transfusão sanguínea contaminada, agulhas compartilhadas e transmissão vertical (parto e amamentação).
  - **PrEP (Profilaxia Pré-Exposição):** Uso contínuo de antirretrovirais por indivíduos com alto risco de exposição ao vírus antes de qualquer contato sexual de risco.
  - **PEP (Profilaxia Pós-Exposição):** Uso de antirretrovirais por 28 dias iniciado em até 72 horas após acidente perfurocortante ou relação desprotegida para abortar a infecção viral inicial.
- **HPV (Papilomavírus Humano):** Provoca condilomas acuminados (verrugas genitais) e está diretamente associado a quase 100% dos casos de **câncer de colo de útero**, câncer de pênis e ânus. Prevenção fundamental: **Vacina contra o HPV** distribuída gratuitamente pelo SUS para adolescentes de 9 a 14 anos, e exame preventivo papanicolau.
- **Herpes Genital (HSV):** Vesículas dolorosas e recidivantes na pele e mucosas genitais. Permanece latente nos gânglios nervosos.
- **Hepatite B:** Vírus que ataca o tecido hepático, podendo evoluir para cirrose e hepatocarcinoma. Possui vacina eficaz no calendário infantil do SUS.

#### B. Infecções Causadas por Bactérias
- **Sífilis (*Treponema pallidum*):**
  - Fase Primária: Cancro duro (úlcera genital indolor de bordas endurecidas que cicatriza espontaneamente sem deixar cicatriz, enganando o paciente).
  - Fase Secundária: Manchas vermelhas na pele, palmas das mãos e solas dos pés.
  - Fase Terciária (anos depois): Lesões neurológicas graves (neurossífilis), demência, aneurismas aórticos.
  - **Sífilis Congênita:** Transmissão transplacentária da mãe infectada não tratada para o feto, provocando malformações ósseas, cegueira, surdez ou óbito fetal. Tratamento padrão: Penicilina Benzatina (Benzetacil).
- **Gonorreia (*Neisseria gonorrhoeae*):** Corrimento uretral purulento e dor miccional intensa. Pode causar Doença Inflamatória Pélvica (DIP) e esterilidade feminina se não tratada precocemente com antibióticos.

## 5. Definições e Conceitos
- **Método de Barreira:** Dispositivo físico que obstaculiza a passagem do sêmen e do contato entre fluidos e mucosas genitais.
- **Feedback Negativo Hormonal:** Supressão das gonadotrofinas hipofisárias FSH e LH induzida por esteroides exógenos, impedindo a ovulação.
- **Azoospermia:** Ausência total de espermatozoides vivos no fluido seminal ejaculado.
- **Transmissão Vertical:** Passagem de um agente infeccioso da mãe para o concepto durante a gestação, parto ou amamentação.

## 6. Exemplos Resolvidos Passo a Passo
### Exemplo 1: Vasectomia e Função Endócrina no ENEM
Um homem adulto expressa receio de realizar a cirurgia de vasectomia porque teme ficar impotente, perder a voz grave ou parar de ejacular durante o ato sexual. Analisando a anatomia e a endocrinologia reprodutiva masculina, esclareça por que cada um desses três receios é fisiologicamente infundado.
- **Passo 1 (Libido e Caracteres Secundários):** A testosterona é sintetizada pelas células intersticiais de Leydig nos testículos e é lançada diretamente na corrente sanguínea através dos vasos capilares testiculares, e não pelos canais deferentes. Como os vasos sanguíneos permanecem intocados na cirurgia, as taxas de testosterona circulante continuam normais, preservando a libido, a voz e a capacidade erétil peniana.
- **Passo 2 (Volume e Aparência da Ejaculação):** Os espermatozoides compõem apenas cerca de 1% a 5% do volume total do sêmen ejaculado. Mais de 95% do volume do sêmen é constituído pelas secreções líquidas da próstata, glândulas seminais e bulbouretrais, cujos ductos desembocam na uretra bem acima do ponto de corte da cirurgia. O homem continua ejaculando exatamente o mesmo volume de fluido com a mesma consistência, porém sem espermatozoides.

## 7. Aplicações Práticas e Modelagem
- **Vacinação Escolar contra o HPV:** Campanhas de vacinação em massa de meninos e meninas antes do início da vida sexual reduzem em mais de 90% a incidência futura de lesões pré-cancerosas e carcinoma cervical invasor.
- **Campanhas de Testagem Rápida no SUS:** Testes imunocromatográficos capilares rápidos que detectam anticorpos anti-HIV e anti-sífilis a partir de uma gota de sangue em 15 minutos em unidades básicas de saúde, permitindo início de tratamento no mesmo dia.

## 8. Exercícios de Fixação
1. Qual é o mecanismo de ação biológico através do qual as pílulas anticoncepcionais combinadas tradicionais evitam a gestação?
2. Por que a pílula anticoncepcional comum ou o DIU de cobre não substituem o uso de preservativo nas relações sexuais?
3. O que acontece com os espermatozoides produzidos nos testículos de um homem vasectomizado após a cirurgia?

## 9. Correção Comentada dos Exercícios
1. Os hormônios sintéticos mantêm concentrações constantes no sangue, exercendo feedback negativo contínuo sobre a hipófise. Isso inibe a liberação de FSH e LH, impedindo a maturação folicular e o pico ovulatório no ovário.
2. Porque tanto as pílulas quanto o DIU atuam estritamente sobre a fertilidade mecânica ou hormonal da mulher, sem criar nenhuma barreira física contra a troca de vírus e bactérias presentes em secreções e lesões mucosas (HIV, HPV, sífilis, herpes, etc.). Apenas o preservativo oferece proteção contra ISTs.
3. Eles continuam sendo produzidos nos túbulos seminíferos, mas acumulam-se no epidídimo e na porção proximal fechada do ducto deferente, onde envelhecem, morrem e são naturalmente fagocitados e digeridos por macrófagos corporais sem causar prejuízo ao indivíduo.

## 10. Desafio de Domínio
Explique por que um indivíduo recém-infectado pelo vírus HIV pode testar falso-negativo em um teste sorológico convencional durante a chamada "janela imunológica" e qual a diferença de detecção se utilizarmos uma técnica de biologia molecular do tipo PCR.

## 11. Resumo e Pontos-Chave
- Pílula Combinada: Bloqueia FSH e LH por feedback negativo -> Inibe ovulação e espessa o muco.
- DIU de Cobre: Não hormonal, ação espermicida e anti-implantação local inflamatória.
- Vasectomia e Laqueadura: Métodos cirúrgicos definitivos de interrupção do trajeto gamético; não afetam hormônios, libido ou potência.
- ISTs virais: HIV (destrói Linfócitos T CD4+), HPV (câncer de colo de útero, vacina profilática), Hepatite B.
- ISTs bacterianas: Sífilis (*Treponema pallidum*, cancro duro e lesões sistêmicas, curável com penicilina), Gonorreia.
- Preservativo: Único método de barreira que protege simultaneamente contra ISTs e gravidez indesejada.

## 12. Referências Bibliográficas
- BRASIL. Ministério da Saúde. *Protocolo Clínico e Diretrizes Terapêuticas para Atenção Integral às Pessoas com Infecções Sexualmente Transmissíveis (IST)*. Brasília: Ministério da Saúde, 2022.
- GUYTON, A. C.; HALL, J. E. *Tratado de Fisiologia Médica*. 13ª ed. Rio de Janeiro: Elsevier, 2016.
- OPENSTAX. *Biology 2e*. Rice University: OpenStax, 2020.
- INEP. *Matriz de Referência para o ENEM: Ciências da Natureza e suas Tecnologias*. Brasília: MEC/INEP.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Mecanismo Fisiológico da Pílula Combinada",
                syntax = """\text{Estrogênio + Progestina Exógenos} \xrightarrow{\text{Feedback Negativo na Hipófise}} \downarrow \text{FSH e LH} \implies \text{Bloqueio da Ovulação}""",
                description = "Bloqueio hormonal contínuo da maturação folicular e supressão do surto ovulatório de LH.",
                parametersInfo = "Também altera a viscosidade do muco cervical tornando-o impermeável à ascensão dos espermatozoides."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Métodos Cirúrgicos Definitivos e Preservação Endócrina",
                syntax = """\text{Vasectomia (Corte de Deferentes)} \quad \longleftrightarrow \quad \text{Laqueadura (Corte de Tubas)} \quad [\text{Hormônios e Libido Preservados}]""",
                description = "Interrupção anatômica definitiva das vias condutoras de gametas sem qualquer impacto na produção de testosterona ou estrogênio.",
                parametersInfo = "Na vasectomia, a ejaculação persiste com mais de 95% do volume original derivado da próstata e glândulas seminais."
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Perfil da Ejaculação Pós-Vasectomia",
                codeOrFormula = """\text{Sêmen} = \text{Fluido Prostático/Seminal (95-99\%)} + \text{Espermatozoides (1-5\%)}""",
                explanation = "A vasectomia elimina apenas os espermatozoides; o homem ejacula o mesmo volume de líquido seminal normalmente."
            )
        ),
        goldTip = "Para o ENEM: O preservativo masculino ou feminino é o ÚNICO método contraceptivo que previne simultaneamente a gravidez e as Infecções Sexualmente Transmissíveis (ISTs)!",
        assetImagePath = "images/biologia/metodos_contraceptivos.svg"
    )
}
