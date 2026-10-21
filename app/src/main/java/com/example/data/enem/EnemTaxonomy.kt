package com.example.data.enem

/**
 * Taxonomia Oficial do ENEM para Matemática.
 * Organiza os tópicos principais e seus respectivos subtópicos detalhados
 * para alimentar filtros de estudo, simulados e trilhas gamificadas no Knowverse.
 */
object EnemTaxonomy {

    val TAXONOMIA_MATEMATICA: Map<String, List<String>> = linkedMapOf(
        "Matemática Básica" to listOf(
            "Operações com números inteiros, fracionários e decimais",
            "Múltiplos e divisores (MMC e MDC)",
            "Potenciação e radiciação",
            "Notação científica e ordem de grandeza",
            "Sistema métrico decimal e conversão de unidades (comprimento, área, volume, massa, capacidade, tempo)",
            "Razão e proporção",
            "Grandezas diretamente e inversamente proporcionais",
            "Regra de três simples e composta",
            "Escalas (lineares, de área e volumétricas)",
            "Porcentagem (aumentos, descontos e variações sucessivas)"
        ),
        "Matemática Financeira" to listOf(
            "Juros simples",
            "Juros compostos",
            "Interpretação de tabelas de amortização e financiamento"
        ),
        "Estatística" to listOf(
            "Leitura e interpretação de tabelas e gráficos (barras, linhas, setores, pictogramas)",
            "Média aritmética simples",
            "Média aritmética ponderada",
            "Moda",
            "Mediana",
            "Medidas de dispersão (variância e desvio padrão)"
        ),
        "Geometria Plana" to listOf(
            "Conceitos fundamentais (ponto, reta, plano, ângulos)",
            "Polígonos (soma de ângulos internos e externos, diagonais)",
            "Triângulos (classificação, condição de existência, pontos notáveis)",
            "Semelhança e congruência de triângulos",
            "Teorema de Tales",
            "Teorema de Pitágoras e relações métricas no triângulo retângulo",
            "Cálculo de perímetros",
            "Cálculo de áreas (triângulos, quadriláteros, polígonos regulares)",
            "Círculo e circunferência (comprimento da circunferência, área do círculo, coroa e setor circular)"
        ),
        "Geometria Espacial" to listOf(
            "Visão espacial (projeções ortogonais e vistas frontais, laterais e superiores)",
            "Planificação de sólidos geométricos",
            "Poliedros e Relação de Euler",
            "Prismas (elementos, área da superfície e cálculo de volume)",
            "Cilindros (elementos, área da superfície e cálculo de volume)",
            "Pirâmides (elementos, área da superfície e cálculo de volume)",
            "Cones (elementos, área da superfície e cálculo de volume)",
            "Esferas (área da superfície e cálculo de volume)",
            "Troncos de pirâmide e de cone"
        ),
        "Funções" to listOf(
            "Plano cartesiano e pares ordenados",
            "Conceito de função (domínio, contradomínio e imagem)",
            "Função afim ou de 1º grau (gráficos, raízes, coeficiente angular e linear, taxa de variação)",
            "Função quadrática ou de 2º grau (parábola, raízes por Bhaskara, vértices para pontos de máximo e mínimo)",
            "Função exponencial (crescimento e decaimento exponencial)",
            "Função logarítmica (gráficos e comportamento)"
        ),
        "Álgebra e Equações" to listOf(
            "Expressões algébricas e valor numérico",
            "Produtos notáveis e fatoração algébrica",
            "Equações e inequações do 1º grau",
            "Equações e inequações do 2º grau",
            "Sistemas de equações do 1º grau (2x2)"
        ),
        "Análise Combinatória" to listOf(
            "Princípio Fundamental da Contagem (PFC) ou Princípio Multiplicativo",
            "Fatorial",
            "Permutação simples e permutação com repetição",
            "Arranjo simples",
            "Combinação simples"
        ),
        "Probabilidade" to listOf(
            "Experimento aleatório, espaço amostral e evento",
            "Definição clássica de probabilidade",
            "Probabilidade da união de eventos (eventos mutuamente exclusivos)",
            "Probabilidade da intersecção de eventos (eventos independentes)",
            "Probabilidade condicional",
            "Probabilidade de eventos sucessivos"
        ),
        "Trigonometria" to listOf(
            "Trigonometria no triângulo retângulo (seno, cosseno e tangente)",
            "Ângulos notáveis (30°, 45°, 60°)",
            "Lei dos senos e lei dos cossenos",
            "Ciclo trigonométrico e arcos côngruos",
            "Funções trigonométricas (seno e cosseno)"
        )
    )

    fun getAllTopics(): List<String> = TAXONOMIA_MATEMATICA.keys.toList()

    fun getSubtopicsForTopic(topic: String): List<String> =
        TAXONOMIA_MATEMATICA[topic] ?: emptyList()
}
