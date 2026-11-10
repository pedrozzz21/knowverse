package com.example.data.lore

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.example.R
import com.example.ui.theme.AccentCyan
import com.example.ui.theme.GoldXp
import com.example.ui.theme.PrimaryViolet
import com.example.ui.theme.StreakFire
import com.example.ui.theme.SuccessEmerald

/**
 * Modelo de Personagem / Mentor da Lore oficial do Knowverse.
 * Cada personagem representa uma área do conhecimento, com identidade,
 * personalidade, citações pedagógicas e insígnia ilustrada autêntica.
 */
data class KnowverseCharacter(
    val id: String,
    val name: String,
    val species: String,
    val title: String,
    val domain: String,
    val lore: String,
    val philosophy: String,
    val tips: List<String>,
    @DrawableRes val drawableRes: Int,
    val accentColor: Color,
    val badgeLabel: String
) {
    /**
     * Retorna uma dica aleatória ou contextual do mentor.
     */
    fun getRandomTip(): String = tips.random()
}

/**
 * Catálogo e repositório oficial dos Mentores do Knowverse.
 */
object KnowverseLoreRepository {

    val PIPA = KnowverseCharacter(
        id = "pipa_hipo",
        name = "Pipa",
        species = "Hipopótamo",
        title = "Mentora de Tecnologia & Algoritmos",
        domain = "Python & Lógica Computacional",
        lore = "Curiosa e incansável, Pipa desmistifica linhas de código e estruturas abstratas. Para ela, programar é a arte de transformar ideias em soluções reais.",
        philosophy = "Todo grande sistema começou com um simples 'print()'.",
        tips = listOf(
            "Divida problemas complexos em funções menores e modulares.",
            "Erros no terminal não são falhas, são pistas precisas para a solução.",
            "A indentação limpa reflete a clareza do seu pensamento lógico."
        ),
        drawableRes = R.drawable.char_pipa_hipo,
        accentColor = PrimaryViolet,
        badgeLabel = "Tecnologia & Dev"
    )

    val THEO = KnowverseCharacter(
        id = "theo_coelho",
        name = "Theo",
        species = "Coelho",
        title = "Especialista em Matemática & Raciocínio Ágil",
        domain = "Matemática Básica, Funções & Cálculo",
        lore = "Com reflexos velozes e raciocínio afiado, Theo domina padrões numéricos e frações. Sempre de mochila pronta, ensina que a matemática é o idioma da agilidade mental.",
        philosophy = "A velocidade vem com a clareza absoluta dos fundamentos.",
        tips = listOf(
            "Antes de calcular, observe as ordens de grandeza e simplifique termos.",
            "Geometria se resolve enxergando triângulos e simetrias escondidas.",
            "Pratique estimativas rápidas para ganhar tempo nas provas do ENEM."
        ),
        drawableRes = R.drawable.char_theo_coelho,
        accentColor = StreakFire,
        badgeLabel = "Matemática & Lógica"
    )

    val CLARA = KnowverseCharacter(
        id = "clara_boto",
        name = "Clara",
        species = "Boto-Cor-de-Rosa",
        title = "Mestra de Redação & Linguagens",
        domain = "Linguagens, Literatura, Redação & Artes",
        lore = "Navegando pelas águas da cultura e literatura, Clara é elegante e eloquente. Seu chapéu e cachecol guardam repertórios legítimos para a nota 1000 na Redação.",
        philosophy = "Palavras bem escolhidas têm o poder de transformar realidades.",
        tips = listOf(
            "Conectores argumentativos dão fluidez e coerência à sua dissertação.",
            "Um repertório legítimo e produtivo ancora a sua tese com autoridade.",
            "Leia os textos motivadores para extrair o recorte temático exato."
        ),
        drawableRes = R.drawable.char_clara_boto,
        accentColor = GoldXp,
        badgeLabel = "Linguagens & Redação"
    )

    val NESTOR = KnowverseCharacter(
        id = "nestor_jabuti",
        name = "Prof. Nestor",
        species = "Jabuti Catedrático",
        title = "Decano de Ciências da Natureza",
        domain = "Biologia, Física & Química",
        lore = "Com séculos de observação dos ecossistemas e leis físicas, o Prof. Nestor guia com paciência e método. Seu capelo acadêmico simboliza o saber forjado na perseverança.",
        philosophy = "Na natureza, nada se perde; na ciência, tudo se compreende.",
        tips = listOf(
            "Em ecologia, sempre rastreie o fluxo de energia e os ciclos biogeoquímicos.",
            "A termodinâmica e a conservação de energia explicam quase todos os fenômenos.",
            "Atenção minuciosa às unidades de medida evita equívocos em física e química."
        ),
        drawableRes = R.drawable.char_nestor_jabuti,
        accentColor = SuccessEmerald,
        badgeLabel = "Ciências da Natureza"
    )

    val DANTE = KnowverseCharacter(
        id = "dante_tamandua",
        name = "Dante",
        species = "Tamanduá-Bandeira",
        title = "Pesquisador de História & Humanidades",
        domain = "História do Brasil, Geografia & Geopolítica",
        lore = "Com seu sobretudo e olhar investigativo, Dante fareja documentos históricos, contradições sociais e cartografias. Compreender o passado é a bússola para transformar o presente.",
        philosophy = "A história não se decora: compreendem-se os processos sociais.",
        tips = listOf(
            "Analise os períodos históricos relacionando economia, sociedade e política.",
            "Mapas e gráficos populacionais revelam a dinâmica viva do espaço geográfico.",
            "Observe sempre a intencionalidade e o contexto de cada fonte primária."
        ),
        drawableRes = R.drawable.char_dante_tamandua,
        accentColor = Color(0xFFFF5252),
        badgeLabel = "Ciências Humanas"
    )

    val OTTO = KnowverseCharacter(
        id = "otto_lontra",
        name = "Otto",
        species = "Lontra",
        title = "Guia da Jornada & Hábitos de Estudo",
        domain = "Geral & Consistência de Aprendizado",
        lore = "O mentor pioneiro do Knowverse. Alegre e focado, Otto nada pelas trilhas e lembra você de que consistência diária supera a intensidade esporádica.",
        philosophy = "Um pouco todo dia constrói um universo de saber.",
        tips = listOf(
            "Mantenha sua sequência ativa resolvendo pelo menos 3 questões por dia.",
            "Revise seus erros no histórico para fixar o aprendizado na memória.",
            "Alterne entre teoria e prática no terminal para máxima retenção."
        ),
        drawableRes = R.drawable.img_mascot_otter_boy,
        accentColor = AccentCyan,
        badgeLabel = "Guia da Jornada"
    )

    /**
     * Lista completa dos 6 mentores oficiais.
     */
    val ALL_CHARACTERS: List<KnowverseCharacter> = listOf(
        PIPA,
        THEO,
        CLARA,
        NESTOR,
        DANTE,
        OTTO
    )

    /**
     * Busca um mentor pelo seu ID, ou retorna Otto por padrão.
     */
    fun findById(id: String?): KnowverseCharacter {
        if (id.isNullOrBlank()) return OTTO
        return ALL_CHARACTERS.firstOrNull { it.id.equals(id, ignoreCase = true) }
            ?: when {
                id.contains("pipa") || id.contains("hipo") -> PIPA
                id.contains("theo") || id.contains("coelho") -> THEO
                id.contains("clara") || id.contains("boto") -> CLARA
                id.contains("nestor") || id.contains("jabuti") || id.contains("tartaruga") -> NESTOR
                id.contains("dante") || id.contains("tamandua") -> DANTE
                else -> OTTO
            }
    }

    /**
     * Retorna o mentor especializado para uma determinada disciplina.
     */
    fun getMentorForDiscipline(discipline: String): KnowverseCharacter {
        val d = discipline.lowercase()
        return when {
            d.contains("python") || d.contains("program") || d.contains("código") || d.contains("algoritmo") -> PIPA
            d.contains("matemática") || d.contains("mat") || d.contains("cálculo") || d.contains("geometria") -> THEO
            d.contains("redação") || d.contains("ling") || d.contains("literatura") || d.contains("português") -> CLARA
            d.contains("natureza") || d.contains("bio") || d.contains("fís") || d.contains("quím") -> NESTOR
            d.contains("história") || d.contains("geo") || d.contains("hum") || d.contains("filo") || d.contains("socio") -> DANTE
            else -> OTTO
        }
    }
}
