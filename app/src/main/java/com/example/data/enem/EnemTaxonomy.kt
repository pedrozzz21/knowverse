package com.example.data.enem

/**
 * Taxonomia Oficial do ENEM para Matemática.
 * Organiza os tópicos principais e seus respectivos subtópicos detalhados
 * para alimentar filtros de estudo, simulados e trilhas gamificadas no Knowverse.
 */
object EnemTaxonomy {

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

    fun getAllTopics(): List<String> = TAXONOMIA_MATEMATICA.keys.toList()

    fun getSubtopicsForTopic(topic: String): List<String> =
        TAXONOMIA_MATEMATICA[topic] ?: emptyList()
}
