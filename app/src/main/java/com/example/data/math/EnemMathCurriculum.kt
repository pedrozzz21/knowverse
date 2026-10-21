package com.example.data.math

import com.example.data.model.EnemTrailDiscipline
import com.example.data.model.EnemTrailLesson
import com.example.data.model.EnemTrailModule
import com.example.data.model.EnemTrailSimulado

/**
 * Repositório e Taxonomia Oficial da Trilha de Matemática ENEM para o Knowverse.
 *
 * Hierarquia Arquitetural:
 * Disciplina -> Módulo (Tópico) -> Aula Sequencial (Subtópico) -> Simulado Final
 *
 * Estrutura preparada para receber o conteúdo didático e exercícios de cada aula
 * à medida que o material for fornecido.
 */
object EnemMathCurriculum {

    val discipline: EnemTrailDiscipline by lazy {
        EnemTrailDiscipline(
            id = "matematica_enem",
            name = "Matemática ENEM",
            description = "Trilha oficial com 14 módulos estruturados e 81 aulas sequenciais focadas nas competências e habilidades da Matriz de Referência do ENEM.",
            modules = listOf(
                // ========================================================
                // MÓDULO 1: Matemática Básica
                // ========================================================
                EnemTrailModule(
                    id = "mod_01_matematica_basica",
                    moduleNumber = 1,
                    title = "Matemática Básica",
                    summary = "Operações fundamentais, frações, decimais, MMC, potenciação, unidades de medida, proporções, regras de três e porcentagem.",
                    lessons = listOf(
                        EnemTrailLesson(
                            id = "enem_m1_a1",
                            moduleId = "mod_01_matematica_basica",
                            lessonIndex = 1,
                            title = "Aula 1: Operações com números inteiros, fracionários e decimais",
                            subtopic = "Operações Fundamentais & Frações",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "enem_m1_a2",
                            moduleId = "mod_01_matematica_basica",
                            lessonIndex = 2,
                            title = "Aula 2: Múltiplos e divisores (MMC e MDC)",
                            subtopic = "Aritmética: MMC e MDC",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "enem_m1_a3",
                            moduleId = "mod_01_matematica_basica",
                            lessonIndex = 3,
                            title = "Aula 3: Potenciação e radiciação",
                            subtopic = "Potências e Raízes",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "enem_m1_a4",
                            moduleId = "mod_01_matematica_basica",
                            lessonIndex = 4,
                            title = "Aula 4: Notação científica e ordem de grandeza",
                            subtopic = "Notação Científica",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "enem_m1_a5",
                            moduleId = "mod_01_matematica_basica",
                            lessonIndex = 5,
                            title = "Aula 5: Sistema métrico decimal e conversão de unidades (comprimento, área, volume, massa, capacidade, tempo)",
                            subtopic = "Conversão de Unidades",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "enem_m1_a6",
                            moduleId = "mod_01_matematica_basica",
                            lessonIndex = 6,
                            title = "Aula 6: Razão e proporção",
                            subtopic = "Razão e Proporção",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "enem_m1_a7",
                            moduleId = "mod_01_matematica_basica",
                            lessonIndex = 7,
                            title = "Aula 7: Grandezas diretamente e inversamente proporcionais",
                            subtopic = "Grandezas Proporcionais",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "enem_m1_a8",
                            moduleId = "mod_01_matematica_basica",
                            lessonIndex = 8,
                            title = "Aula 8: Regra de três simples e composta",
                            subtopic = "Regra de Três",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "enem_m1_a9",
                            moduleId = "mod_01_matematica_basica",
                            lessonIndex = 9,
                            title = "Aula 9: Escalas (lineares, de área e volumétricas)",
                            subtopic = "Escalas Cartográficas",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "enem_m1_a10",
                            moduleId = "mod_01_matematica_basica",
                            lessonIndex = 10,
                            title = "Aula 10: Porcentagem (aumentos, descontos e variações sucessivas)",
                            subtopic = "Porcentagem e Variações",
                            xpReward = 30
                        )
                    ),
                    finalSimulado = EnemTrailSimulado(
                        id = "simulado_mod_01",
                        moduleId = "mod_01_matematica_basica"
                    )
                ),

                // ========================================================
                // MÓDULO 2: Matemática Financeira
                // ========================================================
                EnemTrailModule(
                    id = "mod_02_matematica_financeira",
                    moduleNumber = 2,
                    title = "Matemática Financeira",
                    summary = "Juros simples, juros compostos, tabelas de amortização, financiamentos e tomada de decisões econômicas no ENEM.",
                    lessons = listOf(
                        EnemTrailLesson(
                            id = "enem_m2_a1",
                            moduleId = "mod_02_matematica_financeira",
                            lessonIndex = 1,
                            title = "Aula 1: Juros simples",
                            subtopic = "Juros Simples & Montante",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "enem_m2_a2",
                            moduleId = "mod_02_matematica_financeira",
                            lessonIndex = 2,
                            title = "Aula 2: Juros compostos",
                            subtopic = "Juros Compostos & Exponencial",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "enem_m2_a3",
                            moduleId = "mod_02_matematica_financeira",
                            lessonIndex = 3,
                            title = "Aula 3: Interpretação de tabelas de amortização e financiamento",
                            subtopic = "Amortização e Financiamento",
                            xpReward = 30
                        )
                    ),
                    finalSimulado = EnemTrailSimulado(
                        id = "simulado_mod_02",
                        moduleId = "mod_02_matematica_financeira"
                    )
                ),

                // ========================================================
                // MÓDULO 3: Estatística
                // ========================================================
                EnemTrailModule(
                    id = "mod_03_estatistica",
                    moduleNumber = 3,
                    title = "Estatística",
                    summary = "Interpretação de gráficos, média simples, média ponderada, moda, mediana e medidas de dispersão (variância e desvio padrão).",
                    lessons = listOf(
                        EnemTrailLesson(
                            id = "enem_m3_a1",
                            moduleId = "mod_03_estatistica",
                            lessonIndex = 1,
                            title = "Aula 1: Leitura e interpretação de tabelas e gráficos (barras, linhas, setores, pictogramas)",
                            subtopic = "Interpretação Gráfica",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "enem_m3_a2",
                            moduleId = "mod_03_estatistica",
                            lessonIndex = 2,
                            title = "Aula 2: Média aritmética simples",
                            subtopic = "Média Aritmética Simples",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "enem_m3_a3",
                            moduleId = "mod_03_estatistica",
                            lessonIndex = 3,
                            title = "Aula 3: Média aritmética ponderada",
                            subtopic = "Média Ponderada",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "enem_m3_a4",
                            moduleId = "mod_03_estatistica",
                            lessonIndex = 4,
                            title = "Aula 4: Moda",
                            subtopic = "Moda Estatística",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "enem_m3_a5",
                            moduleId = "mod_03_estatistica",
                            lessonIndex = 5,
                            title = "Aula 5: Mediana",
                            subtopic = "Mediana & Posição Central",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "enem_m3_a6",
                            moduleId = "mod_03_estatistica",
                            lessonIndex = 6,
                            title = "Aula 6: Medidas de dispersão (variância e desvio padrão)",
                            subtopic = "Variância & Desvio Padrão",
                            xpReward = 30
                        )
                    ),
                    finalSimulado = EnemTrailSimulado(
                        id = "simulado_mod_03",
                        moduleId = "mod_03_estatistica"
                    )
                ),

                // ========================================================
                // MÓDULO 4: Geometria Plana
                // ========================================================
                EnemTrailModule(
                    id = "mod_04_geometria_plana",
                    moduleNumber = 4,
                    title = "Geometria Plana",
                    summary = "Pontos, retas, ângulos, polígonos, triângulos, semelhança, Teorema de Tales, Pitágoras, perímetros, áreas e circunferência.",
                    lessons = listOf(
                        EnemTrailLesson(
                            id = "enem_m4_a1",
                            moduleId = "mod_04_geometria_plana",
                            lessonIndex = 1,
                            title = "Aula 1: Conceitos fundamentais (ponto, reta, plano, ângulos)",
                            subtopic = "Fundamentos da Geometria Plana",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "enem_m4_a2",
                            moduleId = "mod_04_geometria_plana",
                            lessonIndex = 2,
                            title = "Aula 2: Polígonos (soma de ângulos internos e externos, diagonais)",
                            subtopic = "Polígonos & Ângulos",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "enem_m4_a3",
                            moduleId = "mod_04_geometria_plana",
                            lessonIndex = 3,
                            title = "Aula 3: Triângulos (classificação, condição de existência, pontos notáveis)",
                            subtopic = "Triângulos & Pontos Notáveis",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "enem_m4_a4",
                            moduleId = "mod_04_geometria_plana",
                            lessonIndex = 4,
                            title = "Aula 4: Semelhança e congruência de triângulos",
                            subtopic = "Semelhança de Triângulos",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "enem_m4_a5",
                            moduleId = "mod_04_geometria_plana",
                            lessonIndex = 5,
                            title = "Aula 5: Teorema de Tales",
                            subtopic = "Teorema de Tales",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "enem_m4_a6",
                            moduleId = "mod_04_geometria_plana",
                            lessonIndex = 6,
                            title = "Aula 6: Teorema de Pitágoras e relações métricas no triângulo retângulo",
                            subtopic = "Pitágoras & Relações Métricas",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "enem_m4_a7",
                            moduleId = "mod_04_geometria_plana",
                            lessonIndex = 7,
                            title = "Aula 7: Cálculo de perímetros",
                            subtopic = "Perímetros de Figuras Planas",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "enem_m4_a8",
                            moduleId = "mod_04_geometria_plana",
                            lessonIndex = 8,
                            title = "Aula 8: Cálculo de áreas (triângulos, quadriláteros, polígonos regulares)",
                            subtopic = "Cálculo de Áreas Planas",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "enem_m4_a9",
                            moduleId = "mod_04_geometria_plana",
                            lessonIndex = 9,
                            title = "Aula 9: Círculo e circunferência (comprimento da circunferência, área do círculo, coroa e setor circular)",
                            subtopic = "Círculo, Setor & Coroa Circular",
                            xpReward = 30
                        )
                    ),
                    finalSimulado = EnemTrailSimulado(
                        id = "simulado_mod_04",
                        moduleId = "mod_04_geometria_plana"
                    )
                ),

                // ========================================================
                // MÓDULO 5: Geometria Espacial
                // ========================================================
                EnemTrailModule(
                    id = "mod_05_geometria_espacial",
                    moduleNumber = 5,
                    title = "Geometria Espacial",
                    summary = "Projeções ortogonais, planificação de sólidos, Relação de Euler, prismas, cilindros, pirâmides, cones, esferas e troncos.",
                    lessons = listOf(
                        EnemTrailLesson(
                            id = "enem_m5_a1",
                            moduleId = "mod_05_geometria_espacial",
                            lessonIndex = 1,
                            title = "Aula 1: Visão espacial (projeções ortogonais e vistas frontais, laterais e superiores)",
                            subtopic = "Projeções Ortogonais & Vistas",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "enem_m5_a2",
                            moduleId = "mod_05_geometria_espacial",
                            lessonIndex = 2,
                            title = "Aula 2: Planificação de sólidos geométricos",
                            subtopic = "Planificação de Sólidos",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "enem_m5_a3",
                            moduleId = "mod_05_geometria_espacial",
                            lessonIndex = 3,
                            title = "Aula 3: Poliedros e Relação de Euler",
                            subtopic = "Poliedros & Relação de Euler",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "enem_m5_a4",
                            moduleId = "mod_05_geometria_espacial",
                            lessonIndex = 4,
                            title = "Aula 4: Prismas (elementos, área da superfície e cálculo de volume)",
                            subtopic = "Prismas: Superfície e Volume",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "enem_m5_a5",
                            moduleId = "mod_05_geometria_espacial",
                            lessonIndex = 5,
                            title = "Aula 5: Cilindros (elementos, área da superfície e cálculo de volume)",
                            subtopic = "Cilindros: Superfície e Volume",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "enem_m5_a6",
                            moduleId = "mod_05_geometria_espacial",
                            lessonIndex = 6,
                            title = "Aula 6: Pirâmides (elementos, área da superfície e cálculo de volume)",
                            subtopic = "Pirâmides: Superfície e Volume",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "enem_m5_a7",
                            moduleId = "mod_05_geometria_espacial",
                            lessonIndex = 7,
                            title = "Aula 7: Cones (elementos, área da superfície e cálculo de volume)",
                            subtopic = "Cones: Superfície e Volume",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "enem_m5_a8",
                            moduleId = "mod_05_geometria_espacial",
                            lessonIndex = 8,
                            title = "Aula 8: Esferas (área da superfície e cálculo de volume)",
                            subtopic = "Esferas: Superfície e Volume",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "enem_m5_a9",
                            moduleId = "mod_05_geometria_espacial",
                            lessonIndex = 9,
                            title = "Aula 9: Troncos de pirâmide e de cone",
                            subtopic = "Troncos de Sólidos",
                            xpReward = 30
                        )
                    ),
                    finalSimulado = EnemTrailSimulado(
                        id = "simulado_mod_05",
                        moduleId = "mod_05_geometria_espacial"
                    )
                ),

                // ========================================================
                // MÓDULO 6: Funções
                // ========================================================
                EnemTrailModule(
                    id = "mod_06_funcoes",
                    moduleNumber = 6,
                    title = "Funções",
                    summary = "Plano cartesiano, domínio, contradomínio, imagem, funções afins, funções quadráticas (máximo e mínimo), exponenciais e logarítmicas.",
                    lessons = listOf(
                        EnemTrailLesson(
                            id = "enem_m6_a1",
                            moduleId = "mod_06_funcoes",
                            lessonIndex = 1,
                            title = "Aula 1: Plano cartesiano e pares ordenados",
                            subtopic = "Plano Cartesiano",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "enem_m6_a2",
                            moduleId = "mod_06_funcoes",
                            lessonIndex = 2,
                            title = "Aula 2: Conceito de função (domínio, contradomínio e imagem)",
                            subtopic = "Definição de Função",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "enem_m6_a3",
                            moduleId = "mod_06_funcoes",
                            lessonIndex = 3,
                            title = "Aula 3: Função afim ou de 1º grau (gráficos, raízes, coeficiente angular e linear, taxa de variação)",
                            subtopic = "Função Afim (1º Grau)",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "enem_m6_a4",
                            moduleId = "mod_06_funcoes",
                            lessonIndex = 4,
                            title = "Aula 4: Função quadrática ou de 2º grau (parábola, raízes por Bhaskara, vértices para pontos de máximo e mínimo)",
                            subtopic = "Função Quadrática & Vértices",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "enem_m6_a5",
                            moduleId = "mod_06_funcoes",
                            lessonIndex = 5,
                            title = "Aula 5: Função exponencial (crescimento e decaimento exponencial)",
                            subtopic = "Função Exponencial",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "enem_m6_a6",
                            moduleId = "mod_06_funcoes",
                            lessonIndex = 6,
                            title = "Aula 6: Função logarítmica (gráficos e comportamento)",
                            subtopic = "Função Logarítmica",
                            xpReward = 30
                        )
                    ),
                    finalSimulado = EnemTrailSimulado(
                        id = "simulado_mod_06",
                        moduleId = "mod_06_funcoes"
                    )
                ),

                // ========================================================
                // MÓDULO 7: Álgebra e Equações
                // ========================================================
                EnemTrailModule(
                    id = "mod_07_algebra_equacoes",
                    moduleNumber = 7,
                    title = "Álgebra e Equações",
                    summary = "Expressões algébricas, valor numérico, produtos notáveis, fatoração, equações e inequações do 1º e 2º grau e sistemas 2x2.",
                    lessons = listOf(
                        EnemTrailLesson(
                            id = "enem_m7_a1",
                            moduleId = "mod_07_algebra_equacoes",
                            lessonIndex = 1,
                            title = "Aula 1: Expressões algébricas e valor numérico",
                            subtopic = "Expressões Algébricas",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "enem_m7_a2",
                            moduleId = "mod_07_algebra_equacoes",
                            lessonIndex = 2,
                            title = "Aula 2: Produtos notáveis e fatoração algébrica",
                            subtopic = "Produtos Notáveis & Fatoração",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "enem_m7_a3",
                            moduleId = "mod_07_algebra_equacoes",
                            lessonIndex = 3,
                            title = "Aula 3: Equações e inequações do 1º grau",
                            subtopic = "Equações do 1º Grau",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "enem_m7_a4",
                            moduleId = "mod_07_algebra_equacoes",
                            lessonIndex = 4,
                            title = "Aula 4: Equações e inequações do 2º grau",
                            subtopic = "Equações do 2º Grau",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "enem_m7_a5",
                            moduleId = "mod_07_algebra_equacoes",
                            lessonIndex = 5,
                            title = "Aula 5: Sistemas de equações do 1º grau (2x2)",
                            subtopic = "Sistemas Lineares 2x2",
                            xpReward = 30
                        )
                    ),
                    finalSimulado = EnemTrailSimulado(
                        id = "simulado_mod_07",
                        moduleId = "mod_07_algebra_equacoes"
                    )
                ),

                // ========================================================
                // MÓDULO 8: Análise Combinatória
                // ========================================================
                EnemTrailModule(
                    id = "mod_08_analise_combinatoria",
                    moduleNumber = 8,
                    title = "Análise Combinatória",
                    summary = "Princípio Fundamental da Contagem (PFC), fatorial, permutações simples e com repetição, arranjos simples e combinações.",
                    lessons = listOf(
                        EnemTrailLesson(
                            id = "enem_m8_a1",
                            moduleId = "mod_08_analise_combinatoria",
                            lessonIndex = 1,
                            title = "Aula 1: Princípio Fundamental da Contagem (PFC) ou Princípio Multiplicativo",
                            subtopic = "Princípio Fundamental da Contagem",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "enem_m8_a2",
                            moduleId = "mod_08_analise_combinatoria",
                            lessonIndex = 2,
                            title = "Aula 2: Fatorial",
                            subtopic = "Fatorial & Simplificações",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "enem_m8_a3",
                            moduleId = "mod_08_analise_combinatoria",
                            lessonIndex = 3,
                            title = "Aula 3: Permutação simples e permutação com repetição",
                            subtopic = "Permutações",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "enem_m8_a4",
                            moduleId = "mod_08_analise_combinatoria",
                            lessonIndex = 4,
                            title = "Aula 4: Arranjo simples",
                            subtopic = "Arranjos Simples",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "enem_m8_a5",
                            moduleId = "mod_08_analise_combinatoria",
                            lessonIndex = 5,
                            title = "Aula 5: Combinação simples",
                            subtopic = "Combinações Simples",
                            xpReward = 30
                        )
                    ),
                    finalSimulado = EnemTrailSimulado(
                        id = "simulado_mod_08",
                        moduleId = "mod_08_analise_combinatoria"
                    )
                ),

                // ========================================================
                // MÓDULO 9: Probabilidade
                // ========================================================
                EnemTrailModule(
                    id = "mod_09_probabilidade",
                    moduleNumber = 9,
                    title = "Probabilidade",
                    summary = "Espaço amostral, eventos, definição clássica, união de eventos, intersecção de eventos independentes, probabilidade condicional e evento complementar.",
                    lessons = listOf(
                        EnemTrailLesson(
                            id = "enem_m9_a1",
                            moduleId = "mod_09_probabilidade",
                            lessonIndex = 1,
                            title = "Aula 1: Experimento aleatório, espaço amostral e evento",
                            subtopic = "Espaço Amostral & Eventos",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "enem_m9_a2",
                            moduleId = "mod_09_probabilidade",
                            lessonIndex = 2,
                            title = "Aula 2: Definição clássica de probabilidade",
                            subtopic = "Probabilidade Clássica",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "enem_m9_a3",
                            moduleId = "mod_09_probabilidade",
                            lessonIndex = 3,
                            title = "Aula 3: Probabilidade da união de eventos (eventos mutuamente exclusivos)",
                            subtopic = "União de Eventos",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "enem_m9_a4",
                            moduleId = "mod_09_probabilidade",
                            lessonIndex = 4,
                            title = "Aula 4: Probabilidade da intersecção de eventos (eventos independentes)",
                            subtopic = "Intersecção de Eventos",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "enem_m9_a5",
                            moduleId = "mod_09_probabilidade",
                            lessonIndex = 5,
                            title = "Aula 5: Probabilidade condicional",
                            subtopic = "Probabilidade Condicional",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "enem_m9_a6",
                            moduleId = "mod_09_probabilidade",
                            lessonIndex = 6,
                            title = "Aula 6: Probabilidade do evento complementar",
                            subtopic = "Evento Complementar",
                            xpReward = 30
                        )
                    ),
                    finalSimulado = EnemTrailSimulado(
                        id = "simulado_mod_09",
                        moduleId = "mod_09_probabilidade"
                    )
                ),

                // ========================================================
                // MÓDULO 10: Sequências e Progressões
                // ========================================================
                EnemTrailModule(
                    id = "mod_10_sequencias_progressoes",
                    moduleNumber = 10,
                    title = "Sequências e Progressões",
                    summary = "Padrões lógicos e numéricos, Progressão Aritmética (PA) com termo geral e soma, e Progressão Geométrica (PG) com termo geral e soma.",
                    lessons = listOf(
                        EnemTrailLesson(
                            id = "enem_m10_a1",
                            moduleId = "mod_10_sequencias_progressoes",
                            lessonIndex = 1,
                            title = "Aula 1: Identificação de padrões lógicos, numéricos e figurativos",
                            subtopic = "Padrões Lógicos & Sequências",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "enem_m10_a2",
                            moduleId = "mod_10_sequencias_progressoes",
                            lessonIndex = 2,
                            title = "Aula 2: Progressão Aritmética (PA): fórmula do termo geral e soma dos termos",
                            subtopic = "Progressão Aritmética (PA)",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "enem_m10_a3",
                            moduleId = "mod_10_sequencias_progressoes",
                            lessonIndex = 3,
                            title = "Aula 3: Progressão Geométrica (PG): fórmula do termo geral e soma dos termos",
                            subtopic = "Progressão Geométrica (PG)",
                            xpReward = 30
                        )
                    ),
                    finalSimulado = EnemTrailSimulado(
                        id = "simulado_mod_10",
                        moduleId = "mod_10_sequencias_progressoes"
                    )
                ),

                // ========================================================
                // MÓDULO 11: Logaritmos
                // ========================================================
                EnemTrailModule(
                    id = "mod_11_logaritmos",
                    moduleNumber = 11,
                    title = "Logaritmos",
                    summary = "Definição, condições de existência, propriedades operatórias (produto, quociente, potência e mudança de base) e equações exponenciais aplicadas.",
                    lessons = listOf(
                        EnemTrailLesson(
                            id = "enem_m11_a1",
                            moduleId = "mod_11_logaritmos",
                            lessonIndex = 1,
                            title = "Aula 1: Definição e condição de existência",
                            subtopic = "Definição de Logaritmo",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "enem_m11_a2",
                            moduleId = "mod_11_logaritmos",
                            lessonIndex = 2,
                            title = "Aula 2: Propriedades operatórias (logaritmo do produto, do quociente, da potência e mudança de base)",
                            subtopic = "Propriedades dos Logaritmos",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "enem_m11_a3",
                            moduleId = "mod_11_logaritmos",
                            lessonIndex = 3,
                            title = "Aula 3: Resolução de equações exponenciais utilizando logaritmos",
                            subtopic = "Equações Exponenciais & Log",
                            xpReward = 30
                        )
                    ),
                    finalSimulado = EnemTrailSimulado(
                        id = "simulado_mod_11",
                        moduleId = "mod_11_logaritmos"
                    )
                ),

                // ========================================================
                // MÓDULO 12: Trigonometria
                // ========================================================
                EnemTrailModule(
                    id = "mod_12_trigonometria",
                    moduleNumber = 12,
                    title = "Trigonometria",
                    summary = "Seno, cosseno e tangente no triângulo retângulo, ângulos notáveis, Lei dos Senos e dos Cossenos, ciclo trigonométrico e funções trigonométricas.",
                    lessons = listOf(
                        EnemTrailLesson(
                            id = "enem_m12_a1",
                            moduleId = "mod_12_trigonometria",
                            lessonIndex = 1,
                            title = "Aula 1: Relações trigonométricas no triângulo retângulo (seno, cosseno e tangente)",
                            subtopic = "Trigonometria no Triângulo Retângulo",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "enem_m12_a2",
                            moduleId = "mod_12_trigonometria",
                            lessonIndex = 2,
                            title = "Aula 2: Ângulos notáveis (30º, 45º e 60º)",
                            subtopic = "Ângulos Notáveis (30°, 45°, 60°)",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "enem_m12_a3",
                            moduleId = "mod_12_trigonometria",
                            lessonIndex = 3,
                            title = "Aula 3: Trigonometria em triângulos quaisquer (Lei dos Senos e Lei dos Cossenos)",
                            subtopic = "Lei dos Senos & Cossenos",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "enem_m12_a4",
                            moduleId = "mod_12_trigonometria",
                            lessonIndex = 4,
                            title = "Aula 4: Ciclo trigonométrico (conversão entre graus e radianos)",
                            subtopic = "Ciclo Trigonométrico & Radianos",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "enem_m12_a5",
                            moduleId = "mod_12_trigonometria",
                            lessonIndex = 5,
                            title = "Aula 5: Funções trigonométricas (análise de gráficos, período e amplitude de seno e cosseno)",
                            subtopic = "Funções Periódicas (Seno & Cosseno)",
                            xpReward = 30
                        )
                    ),
                    finalSimulado = EnemTrailSimulado(
                        id = "simulado_mod_12",
                        moduleId = "mod_12_trigonometria"
                    )
                ),

                // ========================================================
                // MÓDULO 13: Geometria Analítica
                // ========================================================
                EnemTrailModule(
                    id = "mod_13_geometria_analitica",
                    moduleNumber = 13,
                    title = "Geometria Analítica",
                    summary = "Distância entre dois pontos, ponto médio, alinhamento, equação geral e reduzida da reta, posições relativas e equação da circunferência.",
                    lessons = listOf(
                        EnemTrailLesson(
                            id = "enem_m13_a1",
                            moduleId = "mod_13_geometria_analitica",
                            lessonIndex = 1,
                            title = "Aula 1: Distância entre dois pontos no plano cartesiano",
                            subtopic = "Distância Entre Dois Pontos",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "enem_m13_a2",
                            moduleId = "mod_13_geometria_analitica",
                            lessonIndex = 2,
                            title = "Aula 2: Ponto médio de um segmento de reta",
                            subtopic = "Ponto Médio",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "enem_m13_a3",
                            moduleId = "mod_13_geometria_analitica",
                            lessonIndex = 3,
                            title = "Aula 3: Condição de alinhamento de três pontos",
                            subtopic = "Alinhamento de Pontos",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "enem_m13_a4",
                            moduleId = "mod_13_geometria_analitica",
                            lessonIndex = 4,
                            title = "Aula 4: Estudo da reta (equação reduzida, equação geral, coeficientes angular e linear)",
                            subtopic = "Equações da Reta",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "enem_m13_a5",
                            moduleId = "mod_13_geometria_analitica",
                            lessonIndex = 5,
                            title = "Aula 5: Posições relativas entre retas (paralelas e perpendiculares)",
                            subtopic = "Retas Paralelas & Perpendiculares",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "enem_m13_a6",
                            moduleId = "mod_13_geometria_analitica",
                            lessonIndex = 6,
                            title = "Aula 6: Equação reduzida e geral da circunferência",
                            subtopic = "Equação da Circunferência",
                            xpReward = 30
                        )
                    ),
                    finalSimulado = EnemTrailSimulado(
                        id = "simulado_mod_13",
                        moduleId = "mod_13_geometria_analitica"
                    )
                ),

                // ========================================================
                // MÓDULO 14: Matrizes e Sistemas Lineares
                // ========================================================
                EnemTrailModule(
                    id = "mod_14_matrizes_sistemas",
                    moduleNumber = 14,
                    title = "Matrizes e Sistemas Lineares",
                    summary = "Lei de formação e operações elementares com matrizes, determinantes de ordens 2 e 3 e resolução de sistemas lineares.",
                    lessons = listOf(
                        EnemTrailLesson(
                            id = "enem_m14_a1",
                            moduleId = "mod_14_matrizes_sistemas",
                            lessonIndex = 1,
                            title = "Aula 1: Lei de formação e operações elementares com matrizes (adição, subtração, multiplicação)",
                            subtopic = "Matrizes & Operações",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "enem_m14_a2",
                            moduleId = "mod_14_matrizes_sistemas",
                            lessonIndex = 2,
                            title = "Aula 2: Determinantes (ordens 2 e 3)",
                            subtopic = "Determinantes (Ordem 2 e 3)",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "enem_m14_a3",
                            moduleId = "mod_14_matrizes_sistemas",
                            lessonIndex = 3,
                            title = "Aula 3: Resolução e classificação de sistemas lineares simples",
                            subtopic = "Sistemas Lineares & Classificação",
                            xpReward = 30
                        )
                    ),
                    finalSimulado = EnemTrailSimulado(
                        id = "simulado_mod_14",
                        moduleId = "mod_14_matrizes_sistemas"
                    )
                )
            )
        )
    }

    /**
     * Retorna a lista completa dos 14 módulos.
     */
    fun getAllModules(): List<EnemTrailModule> = discipline.modules

    /**
     * Retorna um módulo específico pelo seu identificador ou número.
     */
    fun getModule(moduleId: String): EnemTrailModule? =
        discipline.modules.firstOrNull { it.id == moduleId }

    fun getModuleByNumber(number: Int): EnemTrailModule? =
        discipline.modules.firstOrNull { it.moduleNumber == number }

    /**
     * Retorna todas as 81 aulas da trilha sequencial.
     */
    fun getAllLessons(): List<EnemTrailLesson> =
        discipline.modules.flatMap { it.lessons }

    /**
     * Busca uma aula pelo ID único.
     */
    fun getLessonById(lessonId: String): EnemTrailLesson? =
        getAllLessons().firstOrNull { it.id == lessonId }
}
