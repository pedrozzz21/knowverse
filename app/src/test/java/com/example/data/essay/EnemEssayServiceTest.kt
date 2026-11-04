package com.example.data.essay

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class EnemEssayServiceTest {

    private val sampleTopic = "Invisibilidade e registro civil: garantia de acesso à cidadania no Brasil"

    // 1. Redação bem estruturada, mas com repertório citado incorretamente / improdutivo
    @Test
    fun testStructuredEssayWithUnproductiveRepertoire() {
        val essay = """
            Na sociedade contemporânea, discute-se a temática da documentação pessoal. Segundo o sociólogo Zygmunt Bauman, a sociedade é líquida e as relações são frágeis. Outrossim, a Constituição Cidadã de 1988 assegura que todos são iguais perante a lei. Além disso, a ONU defende os direitos humanos universais. Desse modo, o problema persiste no país e gera exclusão.

            Em primeira análise, cabe pontuar a inoperância de setores públicos. Mesmo com a Constituição assegurando prerrogativas, muitos indivíduos continuam desprovidos de certidão de nascimento. Essa conjuntura corrobora a perpetuação de mazelas históricas, inviabilizando que cidadãos exerçam plenamente seus deveres civis.

            Ademais, a indiferença social agrava esse cenário deplorável. Como disse Bauman sobre a liquidez moderna, as pessoas não se importam com os vulneráveis. Essa falta de alteridade compromete a coesão coletiva e silencia as reivindicações de grupos marginalizados, convertendo desigualdades em fatos naturalizados.

            Portanto, medidas são urgentes para mitigar o problema. Cabe ao Ministério da Justiça, por meio de mutirões itinerantes de emissão de certidão, garantir o registro civil gratuito para famílias em vulnerabilidade, com o fito de restaurar a cidadania plena e combater a invisibilidade social.
        """.trimIndent()

        val result = EnemEssayService.evaluateHeuristicPedagogical(sampleTopic, essay)

        assertTrue("Deve ser identificado como diagnóstico preliminar offline", result.isPreliminaryDiagnosis)
        assertEquals("Tipo de avaliação deve ser OFFLINE_HEURISTIC", "OFFLINE_HEURISTIC", result.evaluationType)

        val c2 = result.competencies.first { it.number == 2 }
        // C2 não deve atribuir nota 200 cegamente; deve alertar sobre repertório descolado / validação semântica
        assertTrue("C2 não deve ultrapassar 160 sem validação semântica", c2.score <= 160)
        assertTrue("Limitação de C2 deve explicar que citação de nomes não comprova repertório produtivo",
            c2.limitations.contains("NÃO comprova") || c2.limitations.contains("produtivo"))

        // Integridade matemática
        assertEquals(result.competencies.sumOf { it.score }, result.totalScore)
        assertTrue(result.totalScore in 0..1000)
    }

    // 2. Redação com muitos conectivos, mas sem progressão argumentativa (circularidade)
    @Test
    fun testExcessiveConnectorsWithoutArgumentativeProgression() {
        val essay = """
            Portanto, desse modo, com efeito, além disso, cabe salientar que o registro civil é importante. Outrossim, todavia, contudo, entretanto, visto que a certidão é necessária. Dessa forma, assim, por conseguinte, a certidão faz falta para a pessoa.

            Em primeira análise, ademais, outrossim, porque o governo é importante, visto que o registro é bom. Nesse sentido, sob esse prisma, dessa maneira, pois a certidão é o documento básico. Desse modo, visto que todos precisam, portanto é fundamental.

            Além disso, por outro lado, contudo, entretanto, a sociedade precisa da certidão. Assim, pois, porque sem ela não há como viver. Dessa forma, desse modo, por conseguinte, tudo continua igual no país.

            Portanto, em suma, finalmente, o governo deve fazer coisas por meio de ações para que tudo melhore desse modo com certeza.
        """.trimIndent()

        val result = EnemEssayService.evaluateHeuristicPedagogical(sampleTopic, essay)

        val c4 = result.competencies.first { it.number == 4 }
        // C4 deve alertar expressamente que quantidade de conectivos não comprova qualidade da coesão
        assertTrue("Limitação de C4 deve alertar sobre quantidade bruta vs qualidade",
            c4.limitations.contains("quantidade bruta") || c4.limitations.contains("qualidade"))

        val c3 = result.competencies.first { it.number == 3 }
        assertTrue("Limitação de C3 deve alertar que marcadores não comprovam consistência lógica",
            c3.limitations.contains("não comprova") || c3.limitations.contains("marcadores"))

        assertEquals(result.competencies.sumOf { it.score }, result.totalScore)
    }

    // 3. Redação com os cinco elementos lexicais da intervenção, mas sem uma proposta coerente
    @Test
    fun testInterventionLexicalElementsWithoutCoherentProposal() {
        val essay = """
            A problemática do registro civil no Brasil contemporâneo demanda atenção rigorosa. A ausência de certidão de nascimento impede que milhares de brasileiros acessem direitos fundamentais básicos garantidos pela legislação nacional.

            Em primeiro plano, a falta de cartórios em regiões periféricas dificulta o acesso ao documento. Cidadãos em situação de extrema vulnerabilidade não dispõem de recursos financeiros para se deslocar até centros urbanos, o que aprofunda a marginalização.

            Ademais, a desinformação contribui para a inércia social. Muitas famílias desconhecem a gratuidade da primeira via da certidão de nascimento assegurada pela Lei Federal, perpetuando o ciclo de invisibilidade perante o Estado.

            Portanto, o governo deve implementar por meio de para que com o intuito de detalhando especialmente coisas diversas no país inteiro sem sentido articulado.
        """.trimIndent()

        val result = EnemEssayService.evaluateHeuristicPedagogical(sampleTopic, essay)

        val c5 = result.competencies.first { it.number == 5 }
        // Não deve receber nota máxima (200) apenas pela presença mecânica de palavras-chave
        assertTrue("C5 não deve receber 200 pontos sem verificação semântica de exequibilidade", c5.score <= 160)
        assertTrue("Limitação de C5 deve alertar que palavras isoladas não comprovam intervenção completa",
            c5.limitations.contains("NÃO comprova") || c5.limitations.contains("isoladas"))

        assertEquals(result.competencies.sumOf { it.score }, result.totalScore)
    }

    // 4. Redação fora do tema (Fuga ao tema / tangenciamento)
    @Test
    fun testOffTopicEssay() {
        val offTopicTheme = "Desafios para a valorização de comunidades e povos tradicionais no Brasil"
        val essay = """
            A exploração espacial e a astronomia moderna revelam os mistérios das galáxias distantes. Telescópios orbitais como o James Webb capturam imagens inéditas do universo primordial, permitindo compreender a formação de estrelas e planetas extrassolares com precisão matemática impressionante.

            Em primeiro lugar, a astrofísica computacional revolucionou a análise de dados cósmicos. Algoritmos de aprendizado de máquina processam terabytes de radiação de fundo em micro-ondas, revelando flutuações quânticas originadas instantes após o Big Bang.

            Ademais, a propulsão iônica e as sondas interestelares abrem novos horizontes para a exploração de luas geladas como Europa e Encélado. A busca por oceanos subterrâneos e potenciais bioassinaturas impulsiona investimentos bilionários de agências internacionais.

            Portanto, as agências espaciais devem investir em radiotelescópios mediante financiamento internacional para que a humanidade compreenda a física dos buracos negros e a expansão cósmica de forma detalhada e sustentável.
        """.trimIndent()

        val result = EnemEssayService.evaluateHeuristicPedagogical(offTopicTheme, essay)

        val c2 = result.competencies.first { it.number == 2 }
        // Fuga ao tema deve receber nota severamente rebaixada em C2
        assertEquals("C2 deve ser 40 ou menos por ausência de vocábulos temáticos centrais", 40, c2.score)
        assertTrue("Evidência deve apontar falta de palavras do tema",
            c2.identifiedEvidence.contains("Poucos ou nenhum") || c2.identifiedEvidence.contains("tema"))

        assertEquals(result.competencies.sumOf { it.score }, result.totalScore)
    }

    // 5. Redação curta (Extensão insuficiente: até 7 linhas / < 40 palavras - Edital do INEP)
    @Test
    fun testShortEssayInsufficientLengthZeroScore() {
        val shortEssay = """
            O registro civil é muito importante para todo cidadão brasileiro ter cidadania e direitos no país.
            Sem ele as pessoas ficam invisíveis para o governo.
        """.trimIndent()

        val result = EnemEssayService.evaluateHeuristicPedagogical(sampleTopic, shortEssay)

        assertTrue("Deve ser identificada como extensão insuficiente", result.isInsufficientLength)
        assertEquals("Nota total DEVE ser 0 no critério oficial do INEP para até 7 linhas", 0, result.totalScore)

        for (comp in result.competencies) {
            assertEquals("Cada competência deve ser 0 na anulação por extensão insuficiente", 0, comp.score)
        }
        assertTrue("Feedback geral deve citar nota ZERO e edital do INEP",
            result.generalDiagnosis.contains("ZERO") && result.generalDiagnosis.contains("INEP"))
    }

    // 6. Redação sem divisão em parágrafos (Bloco único / Monoparágrafo)
    @Test
    fun testSingleParagraphEssayWithoutSegmentation() {
        val singleParagraphEssay = """
            A certidão de nascimento constitui a porta de entrada para o exercício pleno da cidadania no Brasil hodierno e sua ausência condena milhares de indivíduos à invisibilidade social completa. Diante disso constata-se a inércia governamental como propulsora desse drama humano visto que a carência de cartórios em áreas remotas e a desinformação de populações vulneráveis inviabilizam o acesso aos direitos civis fundamentais consagrados na Carta Magna de 1988 gerando exclusão crônica em relação aos serviços públicos essenciais de saúde educação e previdência social. Ademais a negligência social naturaliza o sofrimento desses sujeitos sem registro formal comprometendo a solidariedade e a dignidade coletiva. Portanto medidas coordenadas entre os ministérios competentes e as prefeituras locais devem viabilizar unidades móveis de registro para garantir a certidão de forma gratuita e tempestiva a todos os brasileiros necessitados.
        """.trimIndent()

        val result = EnemEssayService.evaluateHeuristicPedagogical(sampleTopic, singleParagraphEssay)

        assertTrue("Deve registrar observação de monoparágrafo",
            result.structuralObservations.any { it.contains("monoparágrafo") || it.contains("bloco único") })

        val c4 = result.competencies.first { it.number == 4 }
        assertEquals("C4 deve ser penalizada (40 pontos) pela ausência de coesão interparágrafo", 40, c4.score)

        val c2 = result.competencies.first { it.number == 2 }
        assertTrue("C2 deve ser penalizada por ausência de divisão dissertativa", c2.score <= 80)

        assertEquals(result.competencies.sumOf { it.score }, result.totalScore)
    }

    // 7. Redação com linguagem formal e informal misturadas
    @Test
    fun testMixedFormalAndInformalLanguage() {
        val mixedEssay = """
            Na sociedade brasileira, a certidão de nascimento é muito importante, mas vc sabe que muita gente tá sem documento pq os cartórios cobram caro ou ficam longe pra caramba. Desse modo, o Estado precisa ver isso urgente.

            Em primeiro lugar, o governo tá nem aí pras famílias pobres do interior. A constituição fala de igualdade, mas na real isso não acontece né, dai as pessoas não conseguem nem matricular o filho na escola pública.

            Ademais, a sociedade tbm é preconceituosa com quem não tem certidão de nascimento. O cidadão vira um fantasma e não pode trabalhar de carteira assinada nem nada.

            Portanto, o governo tem que se mexer pra resolver isso logo criando cartórios móveis em todo lugar pro povo ter cidadania de verdade e sair dessa miséria.
        """.trimIndent()

        val result = EnemEssayService.evaluateHeuristicPedagogical(sampleTopic, mixedEssay)

        val c1 = result.competencies.first { it.number == 1 }
        // C1 deve acusar marcas de informalidade e não dar 160/200
        assertTrue("C1 deve detectar marcas de informalidade e pontuar <= 120", c1.score <= 120)
        assertTrue("Evidência de C1 deve citar termos coloquiais encontrados",
            c1.identifiedEvidence.contains("informal") || c1.identifiedEvidence.contains("vc") || c1.identifiedEvidence.contains("pq"))

        assertEquals(result.competencies.sumOf { it.score }, result.totalScore)
    }

    // 8. Falha da API (API indisponível ou sem chave ativa)
    @Test
    fun testApiFailureActivatesOfflinePreliminaryDiagnosis() = runBlocking {
        // Chamada sem chave e com allowOfflineFallback = true (comportamento padrão resiliente)
        val resultFallback = EnemEssayService.correctEssay(
            topic = sampleTopic,
            essayText = "A questão do registro civil no Brasil contemporâneo exige reflexão crítica sobre a cidadania.",
            apiKeyOverride = "",
            allowOfflineFallback = true
        )

        assertTrue("Resultado com fallback offline deve ser sucesso", resultFallback.isSuccess)
        val data = resultFallback.getOrThrow()
        assertTrue("Deve estar sinalizado como diagnóstico preliminar", data.isPreliminaryDiagnosis)
        assertEquals("Tipo de avaliação deve ser OFFLINE_HEURISTIC", "OFFLINE_HEURISTIC", data.evaluationType)

        // Chamada sem chave e com allowOfflineFallback = false (modo estrito para teste de erro)
        val resultStrict = EnemEssayService.correctEssay(
            topic = sampleTopic,
            essayText = "A questão do registro civil no Brasil contemporâneo exige reflexão crítica sobre a cidadania.",
            apiKeyOverride = "",
            allowOfflineFallback = false
        )
        assertTrue("Com fallback desabilitado, deve retornar failure", resultStrict.isFailure)
    }

    // 9. Resposta inválida da API
    @Test
    fun testInvalidApiResponseHandling() {
        val invalidMarkdown = "Resposta corrompida sem nenhum formato padrão de nota ou competências."
        val essay = "Texto de teste para validação de integridade do parser."

        val result = EnemEssayService.parseCorrectionResult(
            topic = sampleTopic,
            essayText = essay,
            markdown = invalidMarkdown
        )

        assertNotNull(result)
        assertEquals("Todas as 5 competências devem estar presentes", 5, result.competencies.size)
        // Mesmo em resposta sem notas, o parser atribui pontuação padrão válida em múltiplos de 40
        for (comp in result.competencies) {
            assertTrue("Nota de cada competência deve ser múltiplo de 40", comp.score % 40 == 0)
            assertTrue("Nota de cada competência deve estar entre 0 e 200", comp.score in 0..200)
        }
        assertEquals("Nota total deve ser a soma exata das competências",
            result.competencies.sumOf { it.score }, result.totalScore)
        assertTrue("Nota total deve estar entre 0 e 1000", result.totalScore in 0..1000)
    }

    // 10. Resposta incompleta da API (Ex: apenas 2 competências retornadas)
    @Test
    fun testIncompleteApiResponseHandling() {
        val incompleteMarkdown = """
            # NOTA FINAL: 320

            ## Tabela de Competências
            | Competência | Nota | Justificativa |
            | Competência 1 | 160 | Bom domínio da norma culta |
            | Competência 2 | 160 | Repertório pertinente |
        """.trimIndent()

        val result = EnemEssayService.parseCorrectionResult(
            topic = sampleTopic,
            essayText = "Texto de redação em teste.",
            markdown = incompleteMarkdown
        )

        assertEquals("Devem existir sempre as 5 competências mesmo em resposta parcial", 5, result.competencies.size)
        assertEquals("C1 deve ser 160", 160, result.competencies[0].score)
        assertEquals("C2 deve ser 160", 160, result.competencies[1].score)
        // C3, C4, C5 devem receber fallback pedagógico válido múltiplo de 40
        assertTrue("C3 deve ser múltiplo de 40", result.competencies[2].score % 40 == 0)
        assertTrue("C4 deve ser múltiplo de 40", result.competencies[3].score % 40 == 0)
        assertTrue("C5 deve ser múltiplo de 40", result.competencies[4].score % 40 == 0)

        // Verificação obrigatória: totalScore == sum of competencies
        assertEquals("A nota final deve corresponder à soma exata das 5 competências",
            result.competencies.sumOf { it.score }, result.totalScore)
        assertTrue(result.totalScore in 0..1000)
    }

    // 11. Verificação de integridade matemática e limites
    @Test
    fun testMathematicalIntegrityAndBounds() {
        val sampleEssays = listOf(
            "Texto mínimo de teste.",
            "Um texto com palavras normais em um parágrafo.",
            """
                Introdução com tese sobre o problema da sociedade.
                Desenvolvimento um com dados estatísticos e fatos.
                Desenvolvimento dois com argumentos adicionais.
                Conclusão com proposta de intervenção para o governo.
            """.trimIndent()
        )

        for (text in sampleEssays) {
            val eval = EnemEssayService.evaluateHeuristicPedagogical("Tema genérico", text)
            // Soma exata
            val sum = eval.competencies.sumOf { it.score }
            assertEquals("totalScore deve bater com a soma das competências", sum, eval.totalScore)
            // Limites 0..1000
            assertTrue("totalScore não pode exceder 1000", eval.totalScore <= 1000)
            assertTrue("totalScore não pode ser negativo", eval.totalScore >= 0)
            // Limites de cada competência 0..200 e múltiplos de 40
            for (comp in eval.competencies) {
                assertTrue("Competência deve estar entre 0 e 200", comp.score in 0..200)
                assertTrue("Competência deve ser múltiplo de 40", comp.score % 40 == 0)
            }
        }
    }

    // 12. Exigência estrita de conexão online e verificação de helper de rede
    @Test
    fun testStrictOnlineRequirementAndNetworkCheck() = runBlocking {
        // Verifica que o helper é seguro e não lança exceção em ambiente JVM
        assertTrue("isNetworkAvailable com context nulo deve ser seguro", EnemEssayService.isNetworkAvailable(null))

        // Sem apiKey e com o novo padrão (allowOfflineFallback = false), a correção online deve falhar com explicação clara
        val callWithoutKey = EnemEssayService.correctEssay(
            topic = sampleTopic,
            essayText = "Texto de redação para validação do requisito obrigatório de internet.",
            apiKeyOverride = "",
            allowOfflineFallback = false
        )

        assertTrue("Chamada sem chave e sem fallback deve falhar", callWithoutKey.isFailure)
        val exNoKey = callWithoutKey.exceptionOrNull()
        assertNotNull(exNoKey)
        assertTrue("Mensagem deve explicar ausência de chave para avaliação online",
            exNoKey?.message?.contains("chave") == true || exNoKey?.message?.contains("online") == true)
    }
}
