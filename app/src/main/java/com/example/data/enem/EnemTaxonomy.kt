package com.example.data.enem

/**
 * Informações estruturadas de módulo da taxonomia do ENEM.
 */
data class EnemTaxonomyModule(
    val id: String,
    val title: String,
    val discipline: String,
    val subtopics: List<String>
)

/**
 * Taxonomia Oficial do ENEM para todas as quatro áreas do conhecimento:
 * 1. Matemática e suas Tecnologias
 * 2. Ciências da Natureza: Física
 * 3. Ciências da Natureza: Biologia
 * 4. Linguagens, Códigos e suas Tecnologias
 * 5. Ciências Humanas: História
 *
 * Mapeia cada módulo das trilhas do Knowverse aos seus respectivos tópicos,
 * permitindo filtragem precisa de questões da API do ENEM.
 */
object EnemTaxonomy {

    // =========================================================================
    // 1. MATEMÁTICA E SUAS TECNOLOGIAS
    // =========================================================================
    val TAXONOMIA_MATEMATICA: Map<String, List<String>> = linkedMapOf(
        "Matemática Básica" to listOf(
            "Operações Básicas (Adição, Subtração, Multiplicação, Divisão)",
            "Frações e Números Decimais",
            "Múltiplos e Divisores (MMC e MDC)",
            "Razão e Proporção",
            "Regra de Três (Simples e Composta)",
            "Porcentagem"
        ),
        "Matemática Financeira" to listOf(
            "Juros Simples",
            "Juros Compostos",
            "Sistemas de Amortização"
        ),
        "Estatística" to listOf(
            "Leitura e Interpretação de Gráficos e Tabelas",
            "Medidas de Tendência Central (Média Aritmética, Ponderada, Moda e Mediana)",
            "Medidas de Dispersão (Desvio Padrão e Variância)"
        ),
        "Geometria Plana" to listOf(
            "Ângulos e Polígonos",
            "Relações Métricas no Triângulo Retângulo (Teorema de Pitágoras)",
            "Semelhança e Congruência de Triângulos",
            "Áreas de Figuras Planas",
            "Círculo e Circunferência"
        ),
        "Geometria Espacial" to listOf(
            "Características dos Poliedros (Relação de Euler)",
            "Prismas e Cilindros (Áreas e Volumes)",
            "Pirâmides e Cones (Áreas e Volumes)",
            "Esferas (Área e Volume)",
            "Projeção Ortogonal"
        ),
        "Funções" to listOf(
            "Conceitos Básicos e Plano Cartesiano",
            "Função Afim (1º Grau)",
            "Função Quadrática (2º Grau)",
            "Interpretação de Gráficos de Funções"
        ),
        "Álgebra" to listOf(
            "Expressões Algébricas",
            "Produtos Notáveis e Fatoração",
            "Equações e Inequações do 1º e 2º Grau"
        ),
        "Análise Combinatória" to listOf(
            "Princípio Fundamental da Contagem (PFC)",
            "Permutação (Simples e com Repetição)",
            "Arranjo",
            "Combinação"
        ),
        "Probabilidade" to listOf(
            "Conceitos Iniciais (Espaço Amostral e Evento)",
            "Probabilidade Simples",
            "Probabilidade Condicional",
            "Eventos Independentes e Mutuamente Exclusivos"
        ),
        "Sequências" to listOf(
            "Padrões Numéricos",
            "Progressão Aritmética (PA)",
            "Progressão Geométrica (PG)"
        ),
        "Logaritmos" to listOf(
            "Conceito de Exponencial",
            "Definição e Propriedades dos Logaritmos",
            "Equações Exponenciais e Logarítmicas"
        ),
        "Trigonometria" to listOf(
            "Relações Trigonométricas no Triângulo Retângulo (Seno, Cosseno, Tangente)",
            "Ciclo Trigonométrico",
            "Funções Trigonométricas",
            "Lei dos Senos e Lei dos Cossenos"
        ),
        "Geometria Analítica" to listOf(
            "Distância entre Dois Pontos e Ponto Médio",
            "Estudo da Reta (Equação Geral e Reduzida)",
            "Estudo da Circunferência"
        ),
        "Matrizes e Sistemas Lineares" to listOf(
            "Definição e Operações com Matrizes",
            "Determinantes",
            "Resolução e Discussão de Sistemas Lineares"
        )
    )

    // =========================================================================
    // 2. FÍSICA ENEM
    // =========================================================================
    val TAXONOMIA_FISICA: Map<String, List<String>> = linkedMapOf(
        "Mecânica" to listOf(
            "Cinemática Escalar (MRU e MRUV)",
            "Leis de Newton e Aplicações de Força",
            "Trabalho, Energia Mecânica e Potência",
            "Quantidade de Movimento e Colisões",
            "Gravitação Universal e Leis de Kepler",
            "Estática e Hidrostática (Pressão e Empuxo)"
        ),
        "Eletricidade e Magnetismo" to listOf(
            "Eletrostática (Carga e Campo Elétrico)",
            "Circuitos Elétricos e Lei de Ohm",
            "Potência e Consumo de Energia Elétrica",
            "Campo Magnético e Indução Eletromagnética"
        ),
        "Termologia" to listOf(
            "Termometria e Dilatação Térmica",
            "Calorimetria (Calor Sensível e Latente)",
            "Processos de Propagação do Calor",
            "Termodinâmica e Máquinas Térmicas"
        ),
        "Ondulatória" to listOf(
            "Características das Ondas e Equação Fundamental",
            "Fenômenos Ondulatórios (Reflexão, Refração, Difração, Polarização e Interferência)",
            "Acústica (Qualidades do Som e Efeito Doppler)"
        ),
        "Óptica" to listOf(
            "Óptica Geométrica (Espelhos Planos e Esféricos)",
            "Refração da Luz e Lentes Esféricas",
            "Instrumentos Ópticos e Olho Humano"
        ),
        "Física Moderna" to listOf(
            "Efeito Fotoelétrico",
            "Dualidade Onda-Partícula",
            "Física Nuclear e Radioatividade"
        )
    )

    // =========================================================================
    // 3. BIOLOGIA ENEM
    // =========================================================================
    val TAXONOMIA_BIOLOGIA: Map<String, List<String>> = linkedMapOf(
        "Ecologia" to listOf(
            "Cadeias e Teias Alimentares",
            "Ciclos Biogeoquímicos (Carbono, Nitrogênio e Água)",
            "Relações Ecológicas (Harmônicas e Desarmônicas)",
            "Dinâmica de Populações e Sucessão Ecológica",
            "Impactos Ambientais (Efeito Estufa, Eutrofização, Poluição)"
        ),
        "Fisiologia Humana" to listOf(
            "Sistema Digestório e Nutrição",
            "Sistema Circulatório e Transporte",
            "Sistema Respiratório e Trocas Gasosas",
            "Sistema Excretor e Osmorregulação",
            "Sistema Nervoso e Endócrino",
            "Sistema Imunológico (Vacinas e Soros)"
        ),
        "Genética e Biotecnologia" to listOf(
            "Primeira e Segunda Lei de Mendel",
            "Herança Ligada ao Sexo e Grupos Sanguíneos (ABO e Rh)",
            "Engenharia Genética, Transgênicos e CRISPR",
            "Clonagem e Terapia Gênica"
        ),
        "Citologia e Bioquímica Celular" to listOf(
            "Estrutura Celular e Organelas Citoplasmáticas",
            "Membrana Plasmática e Transportes",
            "Respiração Celular, Fermentação e Fotossíntese",
            "Divisão Celular (Mitose e Meiose)",
            "Ácidos Nucleicos e Síntese Proteica"
        ),
        "Evolução" to listOf(
            "Teorias Evolutivas (Lamarckismo, Darwinismo e Neodarwinismo)",
            "Evidências da Evolução",
            "Especiação e Genética de Populações"
        )
    )

    // =========================================================================
    // 4. LINGUAGENS E CÓDIGOS ENEM
    // =========================================================================
    val TAXONOMIA_LINGUAGENS: Map<String, List<String>> = linkedMapOf(
        "Interpretação de Texto e Semântica" to listOf(
            "Gêneros e Tipologias Textuais",
            "Intertextualidade e Interdiscursividade",
            "Figuras de Linguagem",
            "Ambiguidade, Duplo Sentido e Humor em Tirinhas/Charges",
            "Estratégias Argumentativas e Tese"
        ),
        "Linguística e Comunicação" to listOf(
            "Funções da Linguagem de Roman Jakobson",
            "Variação Linguística e Preconceito Linguístico",
            "Tecnologia, Mídias e Comunicação Digital",
            "Coesão e Coerência Textual"
        ),
        "Literatura Brasileira" to listOf(
            "Barroco e Arcadismo",
            "Romantismo (Poesia e Prosa)",
            "Realismo e Naturalismo (Machado de Assis)",
            "Parnasianismo e Simbolismo",
            "Pré-Modernismo",
            "Modernismo de 1922 (1ª Fase)",
            "Modernismo de 1930 (2ª Fase)",
            "Geração de 1945 e Literatura Contemporânea"
        ),
        "Gramática Aplicada ao Texto" to listOf(
            "Concordância Verbal e Nominal",
            "Regência Verbal, Nominal e Crase",
            "Colocação Pronominal",
            "Pontuação e Expressividade",
            "Conectivos e Operadores Argumentativos"
        ),
        "Artes e Expressão Corporal" to listOf(
            "Artes Visuais e Vanguardas Europeias",
            "Arte Brasileira Contemporânea",
            "Patrimônio Histórico e Cultural",
            "Cultura Corporal, Esporte, Dança e Saúde"
        )
    )

    // =========================================================================
    // 5. HISTÓRIA (CIÊNCIAS HUMANAS) ENEM
    // =========================================================================
    val TAXONOMIA_HISTORIA: Map<String, List<String>> = linkedMapOf(
        "História do Brasil: Colônia" to listOf(
            "A Conquista e os Povos Originários",
            "Economia e Sociedade Colonial (Açúcar e Mineração)",
            "A Escravidão Negra e as Resistências (Quilombos)",
            "Revoltas Coloniais e Crise do Sistema Colonial"
        ),
        "História do Brasil: Império" to listOf(
            "Processo de Independência e Primeiro Reinado",
            "Período Regencial e as Revoltas Populares",
            "Segundo Reinado e a Economia Cafeeira",
            "A Crise do Império e o Fim da Escravidão"
        ),
        "História do Brasil: República" to listOf(
            "República Velha (1889–1930) e Revoltas Populares",
            "Era Vargas (1930–1945) e o Trabalhismo",
            "República Populista (1945–1964) e JK",
            "Ditadura Civil-Militar (1964–1985) e Resistência",
            "Nova República e a Constituição Cidadã de 1988"
        ),
        "História Geral: Antiguidade e Idade Média" to listOf(
            "Grécia Antiga e a Invenção da Democracia",
            "Roma Antiga (República, Império e Direito Romano)",
            "Mundo Medieval e o Feudalismo",
            "O Mundo Islâmico e as Rotas Comerciais"
        ),
        "História Geral: Idade Moderna" to listOf(
            "Renascimento Cultural e Científico",
            "Reformas Religiosas e Contrarreforma",
            "Absolutismo Monárquico e Mercantilismo",
            "Expansão Marítima Europeia e Conquistas"
        ),
        "História Geral: Idade Contemporânea" to listOf(
            "Iluminismo e a Era das Revoluções",
            "Revoluções Industriais e Movimento Operário",
            "Imperialismo e Neocolonialismo no Século XIX",
            "As Guerras Mundiais e os Totalitarismos",
            "Guerra Fria e Processos de Descolonização"
        )
    )

    // =========================================================================
    // 6. GEOGRAFIA (CIÊNCIAS HUMANAS) ENEM
    // =========================================================================
    val TAXONOMIA_GEOGRAFIA: Map<String, List<String>> = linkedMapOf(
        "Geografia Física e Meio Ambiente" to listOf(
            "Cartografia e Orientação (Escalas e Projeções)",
            "Geomorfologia e Relevo Brasileiro",
            "Climatologia e Fenômenos Climáticos (El Niño/La Niña)",
            "Hidrografia e Bacias Hidrográficas Brasileiras",
            "Questões Ambientais e Sustentabilidade"
        ),
        "Geografia Agrária (O Espaço Rural)" to listOf(
            "Estrutura Fundiária e Reforma Agrária",
            "Sistemas Agrícolas (Familiar x Agronegócio)",
            "A Revolução Verde e Transgênicos",
            "Fronteiras Agrícolas e Conflitos no Campo"
        ),
        "Geografia Urbana e Demografia" to listOf(
            "Dinâmica Populacional e Transição Demográfica",
            "Movimentos Migratórios e Refugiados",
            "Processo de Urbanização e Metropolização",
            "Problemas Sociais e Infraestrutura Urbana"
        ),
        "Geografia Econômica e Globalização" to listOf(
            "Fases do Capitalismo",
            "Globalização e Redes Globais",
            "Divisão Internacional do Trabalho (DIT)",
            "Modelos Produtivos e Industrialização Brasileira"
        ),
        "Geopolítica e Ordem Mundial" to listOf(
            "A Ordem Mundial (Guerra Fria à Multipolaridade)",
            "Blocos Econômicos Regionais",
            "Conflitos e Tensões Contemporâneas",
            "Disputas Territoriais e Recursos Estratégicos"
        )
    )

    // =========================================================================
    // 7. QUÍMICA (CIÊNCIAS DA NATUREZA) ENEM
    // =========================================================================
    val TAXONOMIA_QUIMICA: Map<String, List<String>> = linkedMapOf(
        "Química Geral e Estrutura da Matéria" to listOf(
            "Substâncias e Misturas (Gráficos e Separação)",
            "Modelos Atômicos e Distribuição Eletrônica",
            "Tabela Periódica e Propriedades Periódicas",
            "Ligações Químicas e Geometria Molecular",
            "Polaridade e Forças Intermoleculares"
        ),
        "Físico-Química" to listOf(
            "Estequiometria e Cálculos com Rendimento/Pureza",
            "Soluções, Concentrações e Diluição",
            "Termoquímica (Entalpia e Lei de Hess)",
            "Cinética Química e Catalisadores",
            "Equilíbrio Químico e Cálculo de pH",
            "Eletroquímica (Pilhas e Eletrólise)"
        ),
        "Química Orgânica" to listOf(
            "Cadeias Carbônicas e Hibridização",
            "Reconhecimento de Funções Orgânicas",
            "Nomenclatura Orgânica IUPAC",
            "Isomeria Plana e Espacial (Cis-Trans e Óptica)",
            "Reações Orgânicas (Esterificação, Saponificação, Polímeros)"
        ),
        "Química Ambiental e do Cotidiano" to listOf(
            "Química da Água e Saneamento (ETA)",
            "Química Atmosférica (Chuva Ácida, Ozônio, Efeito Estufa)",
            "Combustíveis e Matrizes Energéticas",
            "Ação de Sabões e Detergentes",
            "Polímeros e Reciclagem"
        )
    )

    /**
     * Módulos estruturantes do ENEM com identificadores únicos para mapeamento de dados.
     */
    val MODULES_REGISTRY: List<EnemTaxonomyModule> = listOf(
        // Matemática
        EnemTaxonomyModule("mod_mat_01_basica", "Módulo 1: Matemática Básica", "Matemática", TAXONOMIA_MATEMATICA["Matemática Básica"] ?: emptyList()),
        EnemTaxonomyModule("mod_mat_02_funcoes_algebra", "Módulo 2: Funções e Álgebra", "Matemática", TAXONOMIA_MATEMATICA["Funções"] ?: emptyList()),
        EnemTaxonomyModule("mod_mat_03_geometria", "Módulo 3: Geometria Plana e Espacial", "Matemática", TAXONOMIA_MATEMATICA["Geometria Plana"] ?: emptyList()),
        EnemTaxonomyModule("mod_mat_04_estatistica_probabilidade", "Módulo 4: Estatística e Probabilidade", "Matemática", TAXONOMIA_MATEMATICA["Estatística"] ?: emptyList()),

        // Física
        EnemTaxonomyModule("mod_fis_01_mecanica", "Módulo 1: Mecânica", "Física", TAXONOMIA_FISICA["Mecânica"] ?: emptyList()),
        EnemTaxonomyModule("mod_fis_02_eletricidade", "Módulo 2: Eletricidade e Magnetismo", "Física", TAXONOMIA_FISICA["Eletricidade e Magnetismo"] ?: emptyList()),
        EnemTaxonomyModule("mod_fis_03_termologia", "Módulo 3: Termologia", "Física", TAXONOMIA_FISICA["Termologia"] ?: emptyList()),
        EnemTaxonomyModule("mod_fis_04_ondulatoria", "Módulo 4: Ondulatória", "Física", TAXONOMIA_FISICA["Ondulatória"] ?: emptyList()),
        EnemTaxonomyModule("mod_fis_05_optica", "Módulo 5: Óptica Geométrica", "Física", TAXONOMIA_FISICA["Óptica"] ?: emptyList()),
        EnemTaxonomyModule("mod_fis_06_moderna", "Módulo 6: Física Moderna", "Física", TAXONOMIA_FISICA["Física Moderna"] ?: emptyList()),

        // Biologia
        EnemTaxonomyModule("mod_bio_01_ecologia", "Módulo 1: Ecologia", "Biologia", TAXONOMIA_BIOLOGIA["Ecologia"] ?: emptyList()),
        EnemTaxonomyModule("mod_bio_02_fisiologia", "Módulo 2: Fisiologia Humana", "Biologia", TAXONOMIA_BIOLOGIA["Fisiologia Humana"] ?: emptyList()),
        EnemTaxonomyModule("mod_bio_03_genetica", "Módulo 3: Genética e Biotecnologia", "Biologia", TAXONOMIA_BIOLOGIA["Genética e Biotecnologia"] ?: emptyList()),
        EnemTaxonomyModule("mod_bio_04_citologia", "Módulo 4: Citologia e Bioquímica", "Biologia", TAXONOMIA_BIOLOGIA["Citologia e Bioquímica Celular"] ?: emptyList()),
        EnemTaxonomyModule("mod_bio_05_evolucao", "Módulo 5: Evolução Biológica", "Biologia", TAXONOMIA_BIOLOGIA["Evolução"] ?: emptyList()),

        // Química
        EnemTaxonomyModule("mod_quim_01_geral", "Módulo 1: Química Geral e Estrutura da Matéria", "Química", TAXONOMIA_QUIMICA["Química Geral e Estrutura da Matéria"] ?: emptyList()),
        EnemTaxonomyModule("mod_quim_02_fisico", "Módulo 2: Físico-Química", "Química", TAXONOMIA_QUIMICA["Físico-Química"] ?: emptyList()),
        EnemTaxonomyModule("mod_quim_03_organica", "Módulo 3: Química Orgânica", "Química", TAXONOMIA_QUIMICA["Química Orgânica"] ?: emptyList()),
        EnemTaxonomyModule("mod_quim_04_ambiental", "Módulo 4: Química Ambiental e do Cotidiano", "Química", TAXONOMIA_QUIMICA["Química Ambiental e do Cotidiano"] ?: emptyList()),

        // Linguagens
        EnemTaxonomyModule("mod_ling_01_interpretacao", "Módulo 1: Interpretação e Semântica", "Linguagens", TAXONOMIA_LINGUAGENS["Interpretação de Texto e Semântica"] ?: emptyList()),
        EnemTaxonomyModule("mod_ling_02_linguistica", "Módulo 2: Linguística e Comunicação", "Linguagens", TAXONOMIA_LINGUAGENS["Linguística e Comunicação"] ?: emptyList()),
        EnemTaxonomyModule("mod_ling_03_literatura", "Módulo 3: Literatura Brasileira", "Linguagens", TAXONOMIA_LINGUAGENS["Literatura Brasileira"] ?: emptyList()),
        EnemTaxonomyModule("mod_ling_04_gramatica", "Módulo 4: Gramática no Texto", "Linguagens", TAXONOMIA_LINGUAGENS["Gramática Aplicada ao Texto"] ?: emptyList()),
        EnemTaxonomyModule("mod_ling_05_artes", "Módulo 5: Artes e Expressão Corporal", "Linguagens", TAXONOMIA_LINGUAGENS["Artes e Expressão Corporal"] ?: emptyList()),

        // História
        EnemTaxonomyModule("mod_hist_01_colonia", "Módulo 1: Brasil Colônia", "História", TAXONOMIA_HISTORIA["História do Brasil: Colônia"] ?: emptyList()),
        EnemTaxonomyModule("mod_hist_02_imperio", "Módulo 2: Brasil Império", "História", TAXONOMIA_HISTORIA["História do Brasil: Império"] ?: emptyList()),
        EnemTaxonomyModule("mod_hist_03_republica", "Módulo 3: Brasil República", "História", TAXONOMIA_HISTORIA["História do Brasil: República"] ?: emptyList()),
        EnemTaxonomyModule("mod_hist_04_antiguidade_medieval", "Módulo 4: Antiguidade e Medieval", "História", TAXONOMIA_HISTORIA["História Geral: Antiguidade e Idade Média"] ?: emptyList()),
        EnemTaxonomyModule("mod_hist_05_moderna", "Módulo 5: Idade Moderna", "História", TAXONOMIA_HISTORIA["História Geral: Idade Moderna"] ?: emptyList()),
        EnemTaxonomyModule("mod_hist_06_contemporanea", "Módulo 6: Idade Contemporânea", "História", TAXONOMIA_HISTORIA["História Geral: Idade Contemporânea"] ?: emptyList()),

        // Geografia
        EnemTaxonomyModule("mod_geo_01_fisica", "Módulo 1: Geografia Física e Meio Ambiente", "Geografia", TAXONOMIA_GEOGRAFIA["Geografia Física e Meio Ambiente"] ?: emptyList()),
        EnemTaxonomyModule("mod_geo_02_agraria", "Módulo 2: Geografia Agrária", "Geografia", TAXONOMIA_GEOGRAFIA["Geografia Agrária (O Espaço Rural)"] ?: emptyList()),
        EnemTaxonomyModule("mod_geo_03_urbana_demografia", "Módulo 3: Geografia Urbana e Demografia", "Geografia", TAXONOMIA_GEOGRAFIA["Geografia Urbana e Demografia"] ?: emptyList()),
        EnemTaxonomyModule("mod_geo_04_economica", "Módulo 4: Geografia Econômica e Globalização", "Geografia", TAXONOMIA_GEOGRAFIA["Geografia Econômica e Globalização"] ?: emptyList()),
        EnemTaxonomyModule("mod_geo_05_geopolitica", "Módulo 5: Geopolítica e Ordem Mundial", "Geografia", TAXONOMIA_GEOGRAFIA["Geopolítica e Ordem Mundial"] ?: emptyList())
    )

    fun getAllDisciplines(): List<String> = listOf(
        "Matemática",
        "Física",
        "Biologia",
        "Química",
        "Linguagens",
        "História",
        "Geografia"
    )

    fun getModulesForDiscipline(discipline: String): List<EnemTaxonomyModule> {
        val d = discipline.lowercase()
        return MODULES_REGISTRY.filter {
            it.discipline.lowercase().contains(d) ||
            (d.contains("natureza") && (it.discipline == "Física" || it.discipline == "Biologia" || it.discipline == "Química")) ||
            (d.contains("humanas") && (it.discipline == "História" || it.discipline == "Geografia"))
        }
    }

    fun getModuleById(moduleId: String): EnemTaxonomyModule? {
        return MODULES_REGISTRY.find { it.id.equals(moduleId, ignoreCase = true) }
    }

    fun getAllTopics(): List<String> =
        TAXONOMIA_MATEMATICA.keys.toList() +
        TAXONOMIA_FISICA.keys.toList() +
        TAXONOMIA_BIOLOGIA.keys.toList() +
        TAXONOMIA_QUIMICA.keys.toList() +
        TAXONOMIA_LINGUAGENS.keys.toList() +
        TAXONOMIA_HISTORIA.keys.toList() +
        TAXONOMIA_GEOGRAFIA.keys.toList()

    fun getSubtopicsForTopic(topic: String): List<String> =
        TAXONOMIA_MATEMATICA[topic] ?:
        TAXONOMIA_FISICA[topic] ?:
        TAXONOMIA_BIOLOGIA[topic] ?:
        TAXONOMIA_QUIMICA[topic] ?:
        TAXONOMIA_LINGUAGENS[topic] ?:
        TAXONOMIA_HISTORIA[topic] ?:
        TAXONOMIA_GEOGRAFIA[topic] ?:
        emptyList()
}
