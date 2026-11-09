package com.example.data.transpetro

import com.example.data.math.CalculusDidacticBundle
import com.example.data.model.MathExercise

object TranspetroInfDidactic {

    fun getBundle(lessonId: String): CalculusDidacticBundle? {
        return when (lessonId) {
            "tp_inf_m1_a1" -> bundleM1A1()
            "tp_inf_m1_a2" -> bundleM1A2()
            "tp_inf_m2_a1" -> bundleM2A1()
            "tp_inf_m2_a2" -> bundleM2A2()
            "tp_inf_m3_a1" -> bundleM3A1()
            "tp_inf_m3_a2" -> bundleM3A2()
            "tp_inf_m3_a3" -> bundleM3A3()
            "tp_inf_m3_a4" -> bundleM3A4()
            "tp_inf_m4_a1" -> bundleM4A1()
            "tp_inf_m4_a2" -> bundleM4A2()
            "tp_inf_m5_a1" -> bundleM5A1()
            "tp_inf_m5_a2" -> bundleM5A2()
            "tp_inf_m5_a3" -> bundleM5A3()
            else -> null
        }
    }

    fun getExercises(lessonId: String): List<MathExercise> {
        return when (lessonId) {
            "tp_inf_m1_a1" -> exercisesM1A1()
            "tp_inf_m1_a2" -> exercisesM1A2()
            "tp_inf_m2_a1" -> exercisesM2A1()
            "tp_inf_m2_a2" -> exercisesM2A2()
            "tp_inf_m3_a1" -> exercisesM3A1()
            "tp_inf_m3_a2" -> exercisesM3A2()
            "tp_inf_m3_a3" -> exercisesM3A3()
            "tp_inf_m3_a4" -> exercisesM3A4()
            "tp_inf_m4_a1" -> exercisesM4A1()
            "tp_inf_m4_a2" -> exercisesM4A2()
            "tp_inf_m5_a1" -> exercisesM5A1()
            "tp_inf_m5_a2" -> exercisesM5A2()
            "tp_inf_m5_a3" -> exercisesM5A3()
            else -> emptyList()
        }
    }

    // =========================================================================
    // MÓDULO 1: FUNDAMENTOS DE INFORMÁTICA
    // =========================================================================

    private fun bundleM1A1() = TranspetroDidacticProvider.buildBundle(
        lessonId = "tp_inf_m1_a1",
        intro = """
            A compreensão da arquitetura básica de computadores é a base para operar sistemas corporativos de controle administrativo e logístico. Nas provas da Fundação Cesgranrio, este tópico exige a distinção precisa entre os componentes internos da Unidade Central de Processamento (CPU), a hierarquia de memórias (velocidade versus capacidade e volatilidade) e a classificação dos periféricos de entrada, saída e mistos (híbridos).
        """.trimIndent(),
        theory = """
            • Unidade Central de Processamento (CPU / Processador): É o "cérebro" do computador, dividido internamente em:
              - ULA (Unidade Lógica e Aritmética): executa cálculos matemáticos e comparações lógicas.
              - UC (Unidade de Controle): coordena o fluxo de dados e decodifica instruções.
              - Registradores: pequenas porções de memória de altíssima velocidade localizadas dentro do próprio núcleo da CPU para armazenar dados em processamento imediato.
            • Hierarquia de Memórias:
              - Memória Cache (L1, L2, L3): memória estática (SRAM), volátil, rapidíssima e de baixa capacidade, situada entre a CPU e a RAM para guardar os dados mais requisitados pelo processador.
              - Memória Principal (RAM - Random Access Memory): memória dinâmica (DRAM), volátil (perde os dados na ausência de energia elétrica), onde ficam carregados o sistema operacional e os programas em execução.
              - Memória ROM (Read-Only Memory) / Flash (BIOS/UEFI): memória não volátil, gravada de fábrica para inicializar o hardware (processo de boot e autoteste POST).
            • Periféricos de Entrada, Saída e Híbridos:
              - Entrada (Input): enviam dados do usuário para a máquina (teclado, mouse, scanner, leitor de código de barras óptico, microfone, webcam).
              - Saída (Output): exibem dados processados da máquina para o usuário (monitor tradicional, impressora simples, caixa de som, plotter).
              - Entrada e Saída / Híbridos (I/O): realizam ambas as funções (monitor touchscreen, impressora multifuncional, placa de rede, modem, pen drive).
        """.trimIndent(),
        practicalApplication = """
            Em um terminal aquaviário ou base administrativa da Transpetro, quando um técnico abre simultaneamente o sistema ERP corporativo, múltiplas planilhas de controle de estoque no Excel e o navegador web, o consumo de memória RAM aumenta consideravelmente. Se a RAM for insuficiente, o sistema operacional recorre à memória virtual (paginação em disco/SSD), reduzindo drasticamente o desempenho. Além disso, coletores de dados com tela sensível ao toque (touchscreen) utilizados no almoxarifado atuam como periféricos híbridos (entrada e saída).
        """.trimIndent(),
        summary = """
            • Registradores > Cache > RAM > SSD > HD (ordem decrescente de velocidade e custo por bit; ordem crescente de capacidade).
            • Memórias Voláteis: Registradores, Cache e RAM (dependem de energia elétrica).
            • Memórias Não Voláteis: ROM (BIOS/UEFI), SSD, HD e Pen Drive (mantêm os dados desligados).
            • Periférico Híbrido (E/S): tela touchscreen, multifuncional e interfaces de rede.
        """.trimIndent(),
        formulaName = "Hierarquia de Memórias (Velocidade vs. Capacidade)",
        formulaSyntax = "Velocidade: Registradores > Cache (L1/L2/L3) > RAM > Armazenamento Secundário (SSD > HD)",
        formulaTerms = listOf(
            "Registradores e Cache: Voláteis, internos à CPU, altíssima velocidade, capacidade em KB/MB",
            "RAM (Memória Principal): Volátil, leitura/escrita de programas abertos, capacidade em GB",
            "ROM (BIOS/UEFI): Não volátil, somente leitura na rotina comum, armazena o firmware de inicialização",
            "Periféricos E/S: Entrada (teclado, scanner), Saída (monitor, plotter), Misto (touchscreen, multifuncional)"
        ),
        formulaWhenToUse = "Utilize para resolver questões que comparam tempo de acesso, volatilidade de memórias ou classificação de periféricos.",
        exampleTitle = "Análise de Volatilidade e Desempenho de Hardware",
        exampleProblem = "Um assistente administrativo percebe que, ao ocorrer uma queda brusca de energia em sua estação de trabalho sem nobreak, o relatório que estava sendo digitado e ainda não havia sido salvo foi perdido, mas o sistema operacional Windows iniciou normalmente quando a energia voltou. Explique quais memórias atuaram em cada situação.",
        exampleSteps = listOf(
            "1. O documento em edição antes de ser salvo estava alocado na memória principal (RAM) e na memória Cache do processador.",
            "2. Como a RAM e a Cache são memórias voláteis, a interrupção de energia elétrica apagou seu conteúdo instantaneamente.",
            "3. A rotina de partida da placa-mãe está gravada na memória ROM/Flash (BIOS/UEFI) e o Windows instalado reside na memória secundária (SSD/HD).",
            "4. Por serem memórias não voláteis, tanto a ROM quanto o SSD preservaram seus dados intactos após o desligamento."
        ),
        exampleAnswer = "O documento não salvo perdeu-se porque estava na memória RAM (volátil); o boot e o sistema operacional mantiveram-se preservados por residirem na ROM e no SSD/HD (não voláteis).",
        goldTip = "Pegadinha clássica da Cesgranrio: a memória Cache NÃO serve para aumentar o espaço de armazenamento de arquivos do usuário; sua função exclusiva é acelerar o acesso da CPU às instruções e dados mais frequentes da RAM!"
    )

    private fun exercisesM1A1() = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-INF-SUP-01",
            lessonId = "tp_inf_m1_a1",
            statement = "Durante a especificação técnica de estações de trabalho para o setor de suprimentos de uma estatal, o analista avaliou as características das memórias internas do computador. Considerando a arquitetura de hardware moderna, assinale a afirmação correta.",
            options = listOf(
                "A) A memória ROM é uma memória volátil de alta capacidade onde os aplicativos em execução são temporariamente alocados.",
                "B) A memória Cache é uma memória volátil e de altíssima velocidade, interposta entre o processador (CPU) e a memória principal (RAM) para reduzir o tempo médio de acesso aos dados.",
                "C) Os registradores possuem capacidade de armazenamento na casa dos Terabytes e mantêm os dados gravados mesmo após o desligamento do computador.",
                "D) A memória RAM preserva permanentemente os arquivos do usuário na ausência de energia elétrica, dispensando o uso de SSD ou disco rígido.",
                "E) A Unidade Lógica e Aritmética (ULA) é um periférico externo de saída responsável pela impressão de relatórios gerenciais."
            ),
            correctIndex = 1,
            steps = listOf(
                "A) Incorreta: a ROM é não volátil e de baixa capacidade; quem aloca aplicativos em execução é a RAM.",
                "B) Correta: a memória Cache (SRAM) é volátil, muito rápida e atua exatamente entre a CPU e a RAM para diminuir o gargalo de velocidade.",
                "C) Incorreta: registradores são voláteis e têm capacidade mínima (bits/bytes).",
                "D) Incorreta: a RAM é volátil e perde todo o conteúdo quando a energia é cortada.",
                "E) Incorreta: a ULA é um componente interno da CPU."
            ),
            finalComment = "Gabarito: B. A memória Cache reduz a latência entre a altíssima frequência da CPU e a velocidade inferior da memória RAM."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-INF-SUP-02",
            lessonId = "tp_inf_m1_a1",
            statement = "No almoxarifado de uma base operacional, os técnicos utilizam três dispositivos conectados ao computador: (I) um leitor óptico de código de barras simples, (II) uma impressora térmica exclusiva para emissão de etiquetas e (III) um monitor com tecnologia sensível ao toque (touchscreen). Quanto à classificação de periféricos, os dispositivos I, II e III são, respectivamente:",
            options = listOf(
                "A) de saída, de entrada e de entrada/saída.",
                "B) de entrada, de entrada e de saída.",
                "C) de entrada, de saída e de entrada/saída.",
                "D) de entrada/saída, de saída e de entrada.",
                "E) de entrada, de entrada/saída e de saída."
            ),
            correctIndex = 2,
            steps = listOf(
                "1. O leitor óptico de código de barras (I) captura o código físico e envia os dados para o computador: periférico de entrada.",
                "2. A impressora exclusiva de etiquetas (II) recebe os dados processados e os imprime no papel: periférico de saída.",
                "3. O monitor touchscreen (III) exibe imagens (saída) e recebe o toque como comando (entrada): periférico misto ou de entrada/saída."
            ),
            finalComment = "Gabarito: C. Leitor de código de barras = Entrada; Impressora simples = Saída; Monitor Touchscreen = Entrada e Saída (híbrido)."
        )
    )

    private fun bundleM1A2() = TranspetroDidacticProvider.buildBundle(
        lessonId = "tp_inf_m1_a2",
        intro = """
            Além dos componentes de processamento, o funcionamento de uma estação de trabalho exige a integração entre o Software (parte lógica) e os Dispositivos de Armazenamento Secundário (memória de massa). A Cesgranrio cobra com frequência a classificação de softwares, a diferença tecnológica entre discos rígidos magnéticos (HDD) e unidades de estado sólido (SSD), bem como as grandezas de armazenamento digital.
        """.trimIndent(),
        theory = """
            • Classificação de Software:
              - Software Básico / de Sistema: prepara e gerencia o hardware, servindo de plataforma para os demais programas. Inclui o Sistema Operacional (Windows, Linux), firmware (BIOS/UEFI), drivers de dispositivos e utilitários de sistema.
              - Software Aplicativo: voltado diretamente para resolver tarefas do usuário final (Microsoft Word, Excel, PowerPoint, navegadores web, sistemas ERP de gestão logística).
            • Dispositivos de Armazenamento Secundário (Não Voláteis):
              - HDD (Hard Disk Drive / Disco Rígido): armazenamento magnético composto por pratos móveis giratórios e cabeçote mecânico de leitura/escrita. É mais lento, consome mais energia, produz ruído e é sensível a quedas e impactos físicos.
              - SSD (Solid State Drive / Unidade de Estado Sólido): armazenamento eletrônico baseado em chips de memória Flash (sem partes móveis mecânicas). É silencioso, muito mais rápido na leitura e gravação (especialmente em padrões NVMe/M.2) e mais resistente a choques físicos.
            • Unidades de Medida de Informação:
              - Bit (b): menor unidade de informação (0 ou 1).
              - Byte (B): conjunto de 8 bits (representa um caractere no padrão ASCII).
              - Múltiplos: Kilobyte (KB ≈ 10³ ou 1.024 Bytes), Megabyte (MB), Gigabyte (GB), Terabyte (TB) e Petabyte (PB). Atenção: taxa de transmissão de rede costuma ser medida em Megabits por segundo (Mbps), enquanto tamanho de arquivo é medido em Megabytes (MB), onde 1 Byte = 8 bits.
        """.trimIndent(),
        practicalApplication = """
            Quando o setor de TI substitui os antigos discos rígidos (HDD) por unidades de estado sólido (SSD) nos notebooks dos analistas administrativos, o tempo de inicialização do Windows 11 e o carregamento de planilhas pesadas caem de minutos para poucos segundos, além de proteger os dados em campo contra vibrações e transporte nas instalações operacionais.
        """.trimIndent(),
        summary = """
            • Software de Sistema: Windows, Linux, Drivers (controlam o hardware).
            • Software Aplicativo: Excel, Word, Chrome, SAP (realizam tarefas do usuário).
            • HDD (Magnético, partes mecânicas móveis) vs. SSD (Eletrônico/Flash, sem partes móveis, muito mais veloz).
            • 1 Byte = 8 bits | Ordem: Byte < KB < MB < GB < TB < PB.
        """.trimIndent(),
        formulaName = "Conversão entre Bits, Bytes e Múltiplos de Armazenamento",
        formulaSyntax = "1 Byte (B) = 8 bits (b)  |  1 KB = 1.024 B (ou 10³ B)  |  1 GB = 1.024 MB  |  1 TB = 1.024 GB",
        formulaTerms = listOf(
            "Bit (b minúsculo): Dígito binário (0 ou 1), usado em velocidades de conexão (ex: 100 Mbps)",
            "Byte (B maiúsculo): Agrupamento de 8 bits, usado para tamanho de arquivos e memórias (ex: 50 MB)",
            "SSD (Memória Flash): Leitura/escrita eletrônica sem partes móveis mecânicas",
            "HDD (Magnético): Discos giratórios mecânicos sujeitos a fragmentação física"
        ),
        formulaWhenToUse = "Utilize para calcular tempo de transferência de arquivos na rede ou comparar capacidade de mídias de armazenamento.",
        exampleTitle = "Cálculo de Tempo de Transferência e Capacidade",
        exampleProblem = "Um técnico precisa transferir um pacote de relatórios de auditoria com tamanho total de 200 Megabytes (MB) através de uma rede interna cuja taxa efetiva de transferência é de 80 Megabits por segundo (Mbps). Em quantos segundos a transferência será concluída?",
        exampleSteps = listOf(
            "1. Observe as unidades: o arquivo está em Megabytes (MB) e a velocidade da rede está em Megabits por segundo (Mbps).",
            "2. Converta o tamanho do arquivo para Megabits multiplicando por 8 (pois 1 Byte = 8 bits): 200 MB × 8 = 1.600 Megabits (Mb), ou converta a velocidade para Megabytes por segundo dividindo por 8: 80 Mbps ÷ 8 = 10 MB/s.",
            "3. Divida o tamanho total pela taxa de transferência na mesma unidade: Tempo = 200 MB ÷ 10 MB/s = 20 segundos."
        ),
        exampleAnswer = "A transferência será concluída em 20 segundos (pois 80 Mbps equivalem a 10 MB/s).",
        goldTip = "Nunca confunda 'b' minúsculo (bit) com 'B' maiúsculo (Byte): uma conexão de 100 Mbps baixa, no máximo teórico, 12,5 MB por segundo (100 ÷ 8)!"
    )

    private fun exercisesM1A2() = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-INF-SUP-03",
            lessonId = "tp_inf_m1_a2",
            statement = "As estações de trabalho modernas substituíram progressivamente os discos rígidos tradicionais (HDD) pelas unidades de estado sólido (SSD). Em relação às características técnicas que diferenciam o SSD do HDD, verifica-se que o SSD:",
            options = listOf(
                "A) utiliza pratos magnéticos que giram em alta rotação acionados por um motor elétrico.",
                "B) é uma memória volátil que apaga todos os arquivos caso o notebook fique sem bateria.",
                "C) armazena dados em circuitos integrados de memória Flash, não possuindo partes mecânicas móveis, o que lhe confere maior velocidade e resistência a impactos.",
                "D) possui tempo de acesso muito superior (mais lento) ao do HDD devido à necessidade de desfragmentação diária.",
                "E) funciona exclusivamente como memória ROM de fábrica, não permitindo a gravação de novos arquivos pelo usuário."
            ),
            correctIndex = 2,
            steps = listOf(
                "A) Incorreta: pratos magnéticos giratórios são característica exclusiva do HDD.",
                "B) Incorreta: tanto o SSD quanto o HDD são memórias secundárias não voláteis.",
                "C) Correta: o SSD baseia-se em memória Flash (semicondutores), sem partes móveis, oferecendo menor tempo de acesso, silêncio e resistência física.",
                "D) Incorreta: o tempo de acesso do SSD é muito menor (mais rápido) e não se deve desfragmentar SSDs.",
                "E) Incorreta: o SSD permite leitura e escrita constantes pelo sistema e pelo usuário."
            ),
            finalComment = "Gabarito: C. O SSD utiliza memória Flash (eletrônica), eliminando o atraso mecânico de movimentação de cabeçotes do HD magnético."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-INF-SUP-04",
            lessonId = "tp_inf_m1_a2",
            statement = "Na organização lógica de um computador corporativo, os programas são divididos entre softwares básicos (de sistema) e softwares aplicativos. Assinale a alternativa que apresenta apenas softwares aplicativos voltados às rotinas administrativas:",
            options = listOf(
                "A) Microsoft Windows 11, Linux Ubuntu e BIOS.",
                "B) Microsoft Excel, Microsoft Word e Adobe Acrobat Reader.",
                "C) Driver de impressora, Gerenciador de Dispositivos e Kernel do Linux.",
                "D) Windows Defender Firewall, UEFI e NTFS.",
                "E) Sistema Operacional Android, macOS e Prompt de Comando."
            ),
            correctIndex = 1,
            steps = listOf(
                "1. Softwares de sistema controlam o hardware e dão suporte operacional (Windows, Linux, macOS, Android, BIOS/UEFI, drivers).",
                "2. Softwares aplicativos executam tarefas específicas para o usuário final (edição de textos, planilhas, leitura de PDF).",
                "3. Apenas a alternativa B traz exclusivamente softwares aplicativos: Excel, Word e Acrobat Reader."
            ),
            finalComment = "Gabarito: B. Excel, Word e leitores de PDF são softwares aplicativos utilizados diretamente nas atividades de escritório."
        )
    )

    // =========================================================================
    // MÓDULO 2: SISTEMAS OPERACIONAIS (WINDOWS)
    // =========================================================================

    private fun bundleM2A1() = TranspetroDidacticProvider.buildBundle(
        lessonId = "tp_inf_m2_a1",
        intro = """
            O Sistema Operacional (SO) atua como intermediário entre o usuário, os softwares aplicativos e o hardware do computador. Nas provas da Cesgranrio, o foco recai sobre o Microsoft Windows 10 e o Windows 11, abrangendo seus elementos de interface gráfica (Área de Trabalho, Barra de Tarefas, Visão de Tarefas), ferramentas administrativas (Gerenciador de Tarefas) e recursos nativos de segurança e configuração.
        """.trimIndent(),
        theory = """
            • Funções do Sistema Operacional:
              - Gerenciamento de processos (multitarefa preemptiva), gerenciamento de memória (RAM e memória virtual), gerenciamento de sistemas de arquivos (NTFS, FAT32, exFAT) e controle de dispositivos de Entrada/Saída via drivers.
            • Interface do Windows 10 e Windows 11:
              - Área de Trabalho (Desktop): espaço principal onde ficam ícones, atalhos e janelas abertas.
              - Barra de Tarefas (Taskbar): exibe o botão Iniciar, busca, programas fixados/abertos e a Área de Notificação (Bandeja do Sistema / System Tray, com relógio, rede, som e idioma). No Windows 11, os ícones da Barra de Tarefas vêm centralizados por padrão, mas podem ser alinhados à esquerda.
              - Visão de Tarefas (Win + Tab): permite visualizar todas as janelas abertas e criar Múltiplas Áreas de Trabalho Virtuais para organizar fluxos de trabalho distintos.
            • Ferramentas de Controle e Segurança Nativa:
              - Gerenciador de Tarefas (Ctrl + Shift + Esc): monitora o desempenho em tempo real (CPU, Memória, Disco, Rede), finaliza aplicativos travados, gerencia os programas de Inicialização (Startup) e exibe usuários conectados.
              - Segurança do Windows (Windows Security): central nativa que reúne o Microsoft Defender Antivírus, o Firewall (com perfis de rede Domínio, Particular e Público) e o controle de aplicativos.
              - Windows Hello: autenticação biométrica (reconhecimento facial, impressão digital) ou PIN local.
              - BitLocker: ferramenta nativa de criptografia de unidade de disco (disponível nas edições Pro/Enterprise) para proteger dados contra acesso físico não autorizado.
        """.trimIndent(),
        practicalApplication = """
            Se o sistema de controle logístico travar durante o fechamento mensal, o técnico não precisa desligar o computador no botão físico: basta acionar diretamente o Gerenciador de Tarefas com `Ctrl + Shift + Esc`, localizar o processo que "Não está respondendo" e clicar em "Finalizar tarefa", preservando os demais aplicativos abertos.
        """.trimIndent(),
        summary = """
            • `Ctrl + Shift + Esc`: abre diretamente o Gerenciador de Tarefas.
            • `Ctrl + Alt + Del`: abre a tela de opções de segurança (Bloquear, Trocar usuário, Sair, Gerenciador de Tarefas).
            • `Win + Tab`: abre a Visão de Tarefas e gerencia Áreas de Trabalho Virtuais.
            • `Win + L`: bloqueia imediatamente a estação de trabalho.
            • `Win + I`: abre o painel de Configurações do Windows.
        """.trimIndent(),
        formulaName = "Atalhos de Gerenciamento do Sistema Windows 10 / 11",
        formulaSyntax = "Ctrl + Shift + Esc (Gerenciador de Tarefas) | Win + L (Bloquear) | Win + I (Configurações) | Win + D (Área de Trabalho)",
        formulaTerms = listOf(
            "Ctrl + Shift + Esc: Abre o Gerenciador de Tarefas diretamente sem passar pela tela de segurança",
            "Win + L (Lock): Bloqueia a sessão do usuário imediatamente (essencial para segurança corporativa)",
            "Win + D (Desktop): Minimiza/restaura todas as janelas para exibir a Área de Trabalho",
            "Win + V: Abre o Histórico da Área de Transferência (permite colar múltiplos itens copiados)"
        ),
        formulaWhenToUse = "Utilize para responder questões sobre operação rápida do Windows, resolução de travamentos e segurança de sessão.",
        exampleTitle = "Uso de Áreas de Trabalho Virtuais e Histórico da Área de Transferência",
        exampleProblem = "Um analista administrativo precisa copiar cinco códigos de contratos diferentes de um documento PDF para colar de uma só vez em uma planilha, além de separar as janelas de comunicação das janelas de auditoria financeira. Quais recursos nativos do Windows 10/11 atendem a essas duas demandas?",
        exampleSteps = listOf(
            "1. O comando padrão Ctrl + C / Ctrl + V armazena apenas o último item copiado na Área de Transferência comum.",
            "2. Ao ativar o Histórico da Área de Transferência pelo atalho Win + V, o usuário visualiza a lista dos últimos itens copiados e pode colar qualquer um deles individualmente.",
            "3. Para separar os conjuntos de janelas abertas em telas independentes sem precisar de dois monitores físicos, utiliza-se a Visão de Tarefas (Win + Tab) clicando em 'Nova área de trabalho' (ou pelo atalho Win + Ctrl + D)."
        ),
        exampleAnswer = "Histórico da Área de Transferência (Win + V) para múltiplos recortes e Áreas de Trabalho Virtuais na Visão de Tarefas (Win + Tab) para organizar as janelas.",
        goldTip = "A Cesgranrio adora cobrar a diferença entre `Ctrl + V` (cola o último item copiado) e `Win + V` (abre o Histórico da Área de Transferência para escolher entre vários itens copiados anteriormente)!"
    )

    private fun exercisesM2A1() = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-INF-SUP-05",
            lessonId = "tp_inf_m2_a1",
            statement = "Um empregado da Transpetro precisa se ausentar de sua mesa por alguns minutos para buscar documentos na impressora do corredor. Seguindo a política de segurança da informação da companhia para evitar que terceiros visualizem ou utilizem sua sessão aberta no Windows 11, qual combinação de teclas ele deve pressionar para bloquear imediatamente a estação de trabalho?",
            options = listOf(
                "A) Tecla do logotipo do Windows + E",
                "B) Tecla do logotipo do Windows + L",
                "C) Ctrl + Shift + Esc",
                "D) Alt + F4",
                "E) Tecla do logotipo do Windows + R"
            ),
            correctIndex = 1,
            steps = listOf(
                "A) Incorreta: Win + E abre o Explorador de Arquivos.",
                "B) Correta: Win + L (de Lock) bloqueia instantaneamente a tela do computador, exigindo senha ou biometria para retornar.",
                "C) Incorreta: Ctrl + Shift + Esc abre o Gerenciador de Tarefas.",
                "D) Incorreta: Alt + F4 fecha a janela ativa ou abre a caixa de desligamento.",
                "E) Incorreta: Win + R abre a caixa de diálogo Executar."
            ),
            finalComment = "Gabarito: B. O atalho Win + L bloqueia a sessão ativa imediatamente, sendo uma prática obrigatória de mesa limpa e segurança."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-INF-SUP-06",
            lessonId = "tp_inf_m2_a1",
            statement = "No sistema operacional Windows 10/11, quando um aplicativo apresenta lentidão extrema ou para de responder, o usuário pode abrir diretamente a ferramenta que exibe os processos em execução, o percentual de uso de CPU, memória RAM, disco e rede, além de controlar os programas que iniciam junto com o sistema. Essa ferramenta e seu atalho de acesso direto são, respectivamente:",
            options = listOf(
                "A) Painel de Controle — Win + I",
                "B) Explorador de Arquivos — Win + E",
                "C) Gerenciador de Tarefas — Ctrl + Shift + Esc",
                "D) Visão de Tarefas — Alt + Tab",
                "E) Desfragmentador de Unidades — Ctrl + Alt + Del"
            ),
            correctIndex = 2,
            steps = listOf(
                "1. A ferramenta que monitora processos, uso de CPU/RAM/Disco/Rede e a aba Inicializar é o Gerenciador de Tarefas.",
                "2. Enquanto Ctrl + Alt + Del abre a tela intermediária de segurança, o atalho que abre o Gerenciador de Tarefas diretamente é Ctrl + Shift + Esc."
            ),
            finalComment = "Gabarito: C. O Gerenciador de Tarefas (Ctrl + Shift + Esc) é o utilitário nativo para diagnóstico de desempenho e encerramento de processos."
        )
    )

    private fun bundleM2A2() = TranspetroDidacticProvider.buildBundle(
        lessonId = "tp_inf_m2_a2",
        intro = """
            O gerenciamento eficiente de documentos digitais depende do domínio do Explorador de Arquivos (File Explorer) do Windows. A banca Cesgranrio cobra com rigor as regras de nomenclatura de arquivos, o comportamento de operações de arrastar e soltar entre pastas, a diferença entre exclusão temporária (Lixeira) e permanente, e o reconhecimento das principais extensões de arquivos corporativos.
        """.trimIndent(),
        theory = """
            • Explorador de Arquivos (`Win + E`):
              - Organiza o armazenamento em uma estrutura hierárquica de árvore (diretórios/pastas e subpastas) a partir de unidades lógicas (ex: `C:\`).
              - Caminhos de Diretório: no Windows, o separador de pastas no caminho é a barra invertida `\`, como em `C:\Transpetro\Contratos\Relatorio_2026.pdf`.
            • Regras de Nomenclatura no Windows:
              - Nomes de arquivos e pastas NÃO podem conter os 9 caracteres reservados: `\ / : * ? " < > |` (dica visual: o bonequinho assustado).
              - O Windows não diferencia maiúsculas de minúsculas nos nomes (case-insensitive): `Planilha.xlsx` e `planilha.xlsx` não podem coexistir na mesma pasta.
            • Operações de Copiar, Mover, Atalhos e Exclusão:
              - Arrastar com o botão esquerdo no MESMO drive (ex: de `C:\Pasta1` para `C:\Pasta2`): MOVE o arquivo.
              - Arrastar com o botão esquerdo para um drive DIFERENTE (ex: de `C:\` para o pen drive `D:\`): COPIA o arquivo.
              - Pressionar `Ctrl` ao arrastar: sempre COPIA. Pressionar `Shift` ao arrastar: sempre MOVE. Pressionar `Alt` (ou `Ctrl + Shift`) ao arrastar: cria um ATALHO (`.lnk`).
              - `Delete`: envia o arquivo do disco local para a Lixeira (pode ser restaurado).
              - `Shift + Delete`: exclui permanentemente sem passar pela Lixeira. (Atenção: arquivos apagados de unidades removíveis como pen drives ou de pastas de rede também não vão para a Lixeira local!).
            • Extensões de Arquivos Essenciais:
              - Documentos de texto: `.docx` (Word), `.odt` (LibreOffice Writer), `.rtf`, `.txt`.
              - Planilhas: `.xlsx` (Excel), `.xlsm` (Excel com macros), `.ods` (Calc), `.csv` (valores separados por delimitador).
              - Apresentações: `.pptx` (PowerPoint), `.ppsx` (apresentação direta de slides).
              - Portáveis, Compactados e Executáveis: `.pdf` (documento portável), `.zip` / `.rar` (compactados), `.exe` / `.msi` / `.bat` (executáveis/instaladores — exigem cautela de segurança).
        """.trimIndent(),
        practicalApplication = """
            Ao organizar os arquivos de uma licitação na rede da Transpetro, o técnico tenta salvar uma ata com o nome `Ata 01/2026: Suprimentos.docx`. O Windows bloqueará a gravação porque os caracteres `/` e `:` são proibidos em nomes de arquivos, devendo o usuário substituí-los por hífen ou sublinhado, como `Ata_01-2026_Suprimentos.docx`.
        """.trimIndent(),
        summary = """
            • Caracteres proibidos em nomes no Windows: `\ / : * ? " < > |`.
            • `Delete` → Lixeira | `Shift + Delete` → Exclusão permanente | `F2` → Renomear item selecionado.
            • Arrastar no mesmo disco = Move | Arrastar entre discos diferentes = Copia.
            • Atalho (`.lnk`): é apenas um ponteiro; apagar o atalho NÃO apaga o arquivo original.
        """.trimIndent(),
        formulaName = "Regras do Explorador de Arquivos e Atalhos de Manipulação",
        formulaSyntax = "Proibidos: \\ / : * ? \" < > |   |   F2 (Renomear)   |   Shift + Delete (Exclusão Direta)",
        formulaTerms = listOf(
            "Win + E: Abre uma nova janela do Explorador de Arquivos",
            "F2: Renomeia o arquivo ou pasta atualmente selecionado",
            "Shift + Delete: Apaga o item definitivamente sem enviá-lo para a Lixeira",
            "Extensões: .docx (Word), .xlsx (Excel), .pptx (PowerPoint), .csv (Dados tabulares em texto)"
        ),
        formulaWhenToUse = "Utilize para julgar se um nome de arquivo é válido no Windows ou prever o resultado de ações no Explorador de Arquivos.",
        exampleTitle = "Comportamento de Atalhos, Exclusão e Movimentação",
        exampleProblem = "Um assistente possui o arquivo `C:\\Logistica\\Estoque.xlsx` e cria na Área de Trabalho um atalho para esse arquivo. Em seguida, ele seleciona o atalho na Área de Trabalho e pressiona `Shift + Delete`. O que acontece com a planilha `Estoque.xlsx`?",
        exampleSteps = listOf(
            "1. Um atalho no Windows (extensão .lnk) é apenas um pequeno arquivo de referência que aponta para o endereço do arquivo original.",
            "2. Ao selecionar o atalho na Área de Trabalho e pressionar Shift + Delete, apenas o atalho é excluído permanentemente.",
            "3. O arquivo original `C:\\Logistica\\Estoque.xlsx` permanece intacto em sua pasta de origem."
        ),
        exampleAnswer = "Apenas o atalho é removido definitivamente da Área de Trabalho; o arquivo original `Estoque.xlsx` permanece intacto na pasta `C:\\Logistica`.",
        goldTip = "Cuidado com esta pegadinha da Cesgranrio: se você apagar o arquivo ORIGINAL, o atalho continua existindo, mas fica 'quebrado' (sem destino); se você apagar o ATALHO, nada acontece com o arquivo original!"
    )

    private fun exercisesM2A2() = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-INF-SUP-07",
            lessonId = "tp_inf_m2_a2",
            statement = "No Explorador de Arquivos do Windows 11, um assistente administrativo precisa salvar um relatório gerencial em formato PDF. Considerando as regras do sistema de arquivos NTFS do Windows, qual dos nomes abaixo é VÁLIDO para nomear esse arquivo?",
            options = listOf(
                "A) Relatorio_Mensal:Outubro.pdf",
                "B) Relatorio/Outubro/2026.pdf",
                "C) Relatorio_Mensal_Outubro-2026 (Revisao_1).pdf",
                "D) Relatorio*Outubro_2026*.pdf",
                "E) Relatorio_Outubro?2026.pdf"
            ),
            correctIndex = 2,
            steps = listOf(
                "1. O Windows proíbe o uso de 9 caracteres em nomes de arquivos e pastas: \\ / : * ? \" < > |.",
                "2. A alternativa A usa dois-pontos (:); a B usa barra (/); a D usa asterisco (*); a E usa ponto de interrogação (?).",
                "3. A alternativa C utiliza apenas letras, números, sublinhado (_), hífen (-), espaço e parênteses, que são totalmente permitidos."
            ),
            finalComment = "Gabarito: C. Parênteses, hifens, espaços e sublinhados são válidos; já os caracteres \\ / : * ? \" < > | são bloqueados pelo Windows."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-INF-SUP-08",
            lessonId = "tp_inf_m2_a2",
            statement = "Utilizando o Explorador de Arquivos do Windows 10 em sua configuração padrão, um usuário seleciona o arquivo `Controle.xlsx` localizado na pasta `C:\\Relatorios` e o arrasta com o botão esquerdo do mouse (sem pressionar nenhuma tecla do teclado) para a pasta `C:\\Auditoria`, situada na mesma partição do disco local. Em seguida, ele seleciona outro arquivo na pasta `C:\\Relatorios` e pressiona a tecla `F2`. Essas duas ações têm por efeito, respectivamente:",
            options = listOf(
                "A) copiar o primeiro arquivo para `C:\\Auditoria` e excluir o segundo arquivo.",
                "B) mover o primeiro arquivo para `C:\\Auditoria` e habilitar a edição do nome (renomear) do segundo arquivo.",
                "C) criar um atalho do primeiro arquivo em `C:\\Auditoria` e abrir a janela de propriedades do segundo arquivo.",
                "D) mover o primeiro arquivo para `C:\\Auditoria` e enviar o segundo arquivo para a Lixeira.",
                "E) copiar o primeiro arquivo para `C:\\Auditoria` e atualizar a visualização da pasta."
            ),
            correctIndex = 1,
            steps = listOf(
                "1. Arrastar um arquivo com o botão principal do mouse entre duas pastas da MESMA unidade lógica (`C:`) realiza a operação de MOVER.",
                "2. Selecionar um arquivo ou pasta e pressionar a tecla de função `F2` permite RENOMEAR o item imediatamente."
            ),
            finalComment = "Gabarito: B. Arrastar dentro da mesma unidade move o arquivo; a tecla F2 ativa a função Renomear."
        )
    )

    // =========================================================================
    // MÓDULO 3: PACOTE MICROSOFT OFFICE
    // =========================================================================

    private fun bundleM3A1() = TranspetroDidacticProvider.buildBundle(
        lessonId = "tp_inf_m3_a1",
        intro = """
            O Microsoft Word é a ferramenta padrão para elaboração de ofícios, relatórios técnicos, editais e contratos corporativos. Nas provas da Cesgranrio, as questões exigem o conhecimento prático das guias da Faixa de Opções (Ribbon), formatação de fonte e parágrafo, uso de Estilos para geração de Sumário Automático, Quebras de Página e de Seção, além do recurso de Mala Direta.
        """.trimIndent(),
        theory = """
            • Formatação de Fonte e Parágrafo (Guia Página Inicial):
              - Fonte: Negrito (`Ctrl + N`), Itálico (`Ctrl + I`), Sublinhado (`Ctrl + S`), Tachado, Subscrito (H₂O) e Sobrescrito (m²).
              - Parágrafo: Alinhamentos à Esquerda (`Ctrl + Q`), Centralizado (`Ctrl + E`), à Direita (`Ctrl + G`) e Justificado (`Ctrl + J`), além de recuos, espaçamento entre linhas e marcadores/numeração.
              - Pincel de Formatação (`Ctrl + Shift + C` para copiar formatação e `Ctrl + Shift + V` para aplicá-la): copia o estilo visual de um trecho para outro (um clique aplica uma vez; duplo clique mantém o pincel ativo para várias aplicações).
            • Estilos e Sumário Automático (Guia Página Inicial e Guia Referências):
              - Aplicar estilos hierárquicos (`Título 1`, `Título 2`, `Título 3`) aos tópicos do documento é o pré-requisito para que o Word construa e atualize automaticamente o Sumário na guia Referências.
            • Quebras de Página vs. Quebras de Seção (Guia Layout / Inserir):
              - Quebra de Página (`Ctrl + Enter`): encerra a página atual e passa o cursor para o topo da página seguinte, mantendo a mesma configuração de layout.
              - Quebra de Seção (Próxima Página ou Contínuo): divide o documento em partes independentes, permitindo que uma única página esteja em orientação Paisagem enquanto as demais estão em Retrato, ou que cada seção tenha cabeçalhos e numerações de página distintos (desmarcando "Vincular ao Anterior").
            • Mala Direta (Guia Correspondências) e Controle de Alterações (Guia Revisão):
              - Mala Direta: mescla um documento principal (carta, contrato, etiqueta) com uma fonte de dados (ex: planilha Excel com nomes e endereços) para gerar documentos personalizados em lote.
              - Controle de Alterações: registra todas as inserções, exclusões e comentários feitos pelos revisores de uma minuta contratual.
        """.trimIndent(),
        practicalApplication = """
            Ao redigir um relatório técnico na Transpetro que possui 20 páginas em formato Retrato, mas exige na página 12 um cronograma largo em formato Paisagem, o analista insere uma Quebra de Seção antes e outra depois da página 12, alterando a orientação apenas daquela seção intermediária sem afetar o restante do documento.
        """.trimIndent(),
        summary = """
            • Atalhos do Word em Português: `Ctrl + N` (Negrito), `Ctrl + I` (Itálico), `Ctrl + S` (Sublinhado), `Ctrl + B` (Salvar), `Ctrl + T` (Selecionar Tudo), `Ctrl + U` (Substituir), `Ctrl + L` (Localizar), `Ctrl + J` (Justificar), `Ctrl + E` (Centralizar).
            • Quebra de Seção: indispensável para misturar Retrato e Paisagem ou mudar cabeçalhos no mesmo arquivo.
            • Sumário Automático: depende da aplicação prévia dos estilos `Título 1`, `Título 2`, etc.
        """.trimIndent(),
        formulaName = "Atalhos Clássicos do Microsoft Word (Versão em Português)",
        formulaSyntax = "Ctrl+N (Negrito) | Ctrl+B (Salvar) | Ctrl+T (Selecionar Tudo) | Ctrl+U (Substituir) | Ctrl+Enter (Quebra de Página)",
        formulaTerms = listOf(
            "Ctrl + B (no Word/Excel PT-BR): Salvar o documento (diferente do inglês, onde Save é Ctrl+S)",
            "Ctrl + S (no Word PT-BR): Aplica formatação Sublinhado ao texto selecionado",
            "Ctrl + T (no Word PT-BR): Seleciona Todo o conteúdo do documento",
            "Quebra de Seção (Guia Layout): Permite configurar margens, orientação e cabeçalhos independentes"
        ),
        formulaWhenToUse = "Utilize para responder questões de atalhos em português e recursos avançados de paginação no Word.",
        exampleTitle = "Configuração de Orientação Mista e Sumário Automático",
        exampleProblem = "Um técnico precisa: (1) gerar um sumário automático com os capítulos de uma norma interna e (2) fazer com que a numeração de páginas comece apenas a partir da página 3 (Introdução), deixando a Capa e o Sumário sem número no cabeçalho. Como realizar esses dois procedimentos no Word?",
        exampleSteps = listOf(
            "1. Para o Sumário Automático: selecionar os títulos dos capítulos, aplicar os estilos 'Título 1' e 'Título 2' na guia Página Inicial e, depois, clicar em Referências > Sumário.",
            "2. Para isolar os cabeçalhos: posicionar o cursor no final da página 2 (Sumário) e ir em Layout > Quebras > Quebras de Seção > Próxima Página.",
            "3. Dar duplo clique no cabeçalho da página 3, desativar o botão 'Vincular ao Anterior' na guia Cabeçalho e Rodapé e inserir o Número de Página apenas a partir da segunda seção."
        ),
        exampleAnswer = "Aplicar estilos de Título (Título 1/2) para o Sumário Automático; inserir Quebra de Seção e desmarcar 'Vincular ao Anterior' para numerar apenas a partir da página 3.",
        goldTip = "Na versão em português do Microsoft Office, `Ctrl + B` SALVA o arquivo e `Ctrl + S` SUBLINHA! Essa é uma das perguntas mais recorrentes de provas brasileiras."
    )

    private fun exercisesM3A1() = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-INF-SUP-09",
            lessonId = "tp_inf_m3_a1",
            statement = "Durante a elaboração de um contrato padrão no Microsoft Word (versão em português), o analista administrativo precisa manter todas as páginas do texto na orientação Retrato, exceto a página que contém a planilha orçamentária detalhada, que deve ficar na orientação Paisagem dentro do mesmo arquivo `.docx`. Além disso, ele deseja enviar esse mesmo contrato para 80 fornecedores cadastrados em uma planilha do Excel, preenchendo automaticamente a Razão Social e o CNPJ de cada um no cabeçalho do texto. Os dois recursos do Word que atendem a essas necessidades são, respectivamente:",
            options = listOf(
                "A) Quebra de Página e Pincel de Formatação.",
                "B) Quebra de Seção e Mala Direta.",
                "C) Quebra de Coluna e Controle de Alterações.",
                "D) Referência Cruzada e Sumário Automático.",
                "E) Margens Personalizadas e Validação de Dados."
            ),
            correctIndex = 1,
            steps = listOf(
                "1. Para configurar orientações diferentes (Retrato e Paisagem) em páginas de um mesmo documento do Word, é obrigatório dividir o documento por meio de Quebras de Seção.",
                "2. Para integrar um documento-base do Word com uma lista de destinatários do Excel e gerar cartas/contratos personalizados em massa, utiliza-se o recurso Mala Direta (guia Correspondências)."
            ),
            finalComment = "Gabarito: B. A Quebra de Seção permite layouts distintos no mesmo documento e a Mala Direta automatiza o preenchimento de dados variáveis."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-INF-SUP-10",
            lessonId = "tp_inf_m3_a1",
            statement = "No Microsoft Word em português (Office 365 / 2019), um usuário selecionou todo o texto de um documento por meio de um atalho de teclado, aplicou o alinhamento justificado e, em seguida, salvou as alterações feitas no arquivo. As combinações de teclas que executam, correta e respectivamente, as ações de Selecionar Tudo, Justificar e Salvar no Word em português são:",
            options = listOf(
                "A) Ctrl + A, Ctrl + J e Ctrl + S.",
                "B) Ctrl + T, Ctrl + J e Ctrl + B.",
                "C) Ctrl + T, Ctrl + E e Ctrl + S.",
                "D) Ctrl + A, Ctrl + Q e Ctrl + B.",
                "E) Ctrl + S, Ctrl + J e Ctrl + G."
            ),
            correctIndex = 1,
            steps = listOf(
                "1. No Word em português, Selecionar Tudo é acionado por `Ctrl + T` (Tudo).",
                "2. Justificar o parágrafo é acionado por `Ctrl + J`.",
                "3. Salvar o documento é acionado por `Ctrl + B` (já que `Ctrl + S` aplica Sublinhado)."
            ),
            finalComment = "Gabarito: B. No Word PT-BR: Ctrl + T (Selecionar Tudo), Ctrl + J (Justificar) e Ctrl + B (Salvar)."
        )
    )

    private fun bundleM3A2() = TranspetroDidacticProvider.buildBundle(
        lessonId = "tp_inf_m3_a2",
        intro = """
            O Microsoft Excel é o tema campeão de questões de Informática nas provas da Fundação Cesgranrio. Antes de resolver funções avançadas, é indispensável dominar a estrutura da pasta de trabalho, a diferença entre os operadores de referência (`:` e `;`), a ordem de precedência aritmética e, principalmente, o comportamento das referências relativas, absolutas e mistas com o símbolo cifrão (`${'$'}`) ao copiar e colar fórmulas.
        """.trimIndent(),
        theory = """
            • Estrutura e Operadores de Referência:
              - Uma Pasta de Trabalho (`.xlsx`) contém uma ou mais Planilhas (abas), formadas por Colunas (letras: A, B, C...) e Linhas (números: 1, 2, 3...). O cruzamento define o endereço da Célula (ex: `B4`).
              - Dois-pontos (`:` — Operador de Intervalo / "ATÉ"): referencia todas as células compreendidas em um bloco retangular contínuo. Ex: `A1:B3` abrange 6 células (`A1, A2, A3, B1, B2, B3`).
              - Ponto e vírgula (`;` — Operador de União / "E"): separa argumentos de uma função ou une células isoladas. Ex: `=SOMA(A1;B3)` soma apenas 2 células (`A1` + `B3`).
            • Ordem de Precedência dos Operadores Aritméticos:
              - 1º Parênteses `()` → 2º Negação `-` / Porcentagem `%` → 3º Exponenciação `^` → 4º Multiplicação `*` e Divisão `/` (na ordem em que aparecerem) → 5º Adição `+` e Subtração `-` → 6º Comparação (`=`, `>`, `<`, `<>`).
            • Referências Relativas, Absolutas e Mistas (Uso do `${'$'}`):
              - Referência Relativa (`A1`): livre de cifrão. Ao copiar a fórmula para o lado ou para baixo, tanto a coluna quanto a linha se ajustam proporcionalmente ao deslocamento.
              - Referência Absoluta (`${'$'}A${'$'}1`): coluna e linha travadas (atalho `F4`). Ao copiar a fórmula para qualquer célula da planilha, ela continuará apontando exatamente para `${'$'}A${'$'}1`.
              - Referência Mista com Coluna Fixa (`${'$'}A1`): o cifrão está apenas antes da letra `A`. A coluna `A` nunca muda ao arrastar para os lados, mas a linha `1` varia se arrastada para cima ou para baixo.
              - Referência Mista com Linha Fixa (`A${'$'}1`): o cifrão está apenas antes do número `1`. A coluna `A` varia ao arrastar para os lados, mas a linha `1` permanece travada se arrastada verticalmente.
        """.trimIndent(),
        practicalApplication = """
            Em uma planilha de conversão de custos logísticos da Transpetro, a cotação do dólar está na célula `C1` e os valores em dólares de 50 contratos estão no intervalo `A4:A53`. Para converter todos os contratos na coluna `B`, o analista digita em `B4` a fórmula `=A4*${'$'}C${'$'}1` e arrasta a alça de preenchimento até `B53`. O termo `A4` mudará para `A5`, `A6`..., enquanto `${'$'}C${'$'}1` permanecerá fixo na célula da cotação.
        """.trimIndent(),
        summary = """
            • `A1:A5` = intervalo de A1 ATÉ A5 (5 células) | `A1;A5` = apenas A1 E A5 (2 células).
            • Precedência: `()` → `%` → `^` (potência) → `*` e `/` → `+` e `-`.
            • Regra do Cifrão (`${'$'}`): o `${'$'}` trava apenas o elemento que vem IMEDIATAMENTE à sua direita (`${'$'}Coluna` ou `${'$'}Linha`).
            • Deslocamento: mudar de linha soma/subtrai números; mudar de coluna avança/retrocede letras no alfabeto.
        """.trimIndent(),
        formulaName = "Regra de Deslocamento de Referências com Cifrão (${'$'})",
        formulaSyntax = "Relativa: A1 (tudo muda) | Absoluta: ${'$'}A${'$'}1 (nada muda) | Mistas: ${'$'}A1 (coluna fixa) e A${'$'}1 (linha fixa)",
        formulaTerms = listOf(
            "Dois-pontos (:): Intervalo contínuo ('até'). Número de células = (Linhas × Colunas)",
            "Ponto e vírgula (;): União de referências discretas ('e') ou separador de argumentos",
            "Circunflexo (^): Operador de potenciação no Excel (executado antes de * e /)",
            "Cifrão (${'$'}): Congela a coordenada (letra da coluna ou número da linha) na cópia com Ctrl+C / Ctrl+V"
        ),
        formulaWhenToUse = "Utilize sempre que a questão perguntar qual fórmula ou resultado aparecerá após copiar uma célula para outra posição.",
        exampleTitle = "Rastreamento de Cópia de Fórmulas Mistas na Cesgranrio",
        exampleProblem = "Na célula `B2` de uma planilha do Excel, um técnico inseriu a fórmula `=${'$'}A2 + B${'$'}1 + ${'$'}C${'$'}3`. Em seguida, copiou a célula `B2` (Ctrl + C) e colou na célula `D5` (Ctrl + V). Qual fórmula passará a constar na célula `D5`?",
        exampleSteps = listOf(
            "1. Calcule o deslocamento de `B2` para `D5`: da coluna B para D avançamos +2 colunas (B → C → D); da linha 2 para 5 descemos +3 linhas (2 + 3 = 5).",
            "2. Analise a 1ª parcela (`${'$'}A2`): a coluna `${'$'}A` está travada pelo cifrão (continua `${'$'}A`); a linha `2` está livre e soma +3, virando `5` → resultado: `${'$'}A5`.",
            "3. Analise a 2ª parcela (`B${'$'}1`): a coluna `B` está livre e avança +2 colunas, virando `D`; a linha `${'$'}1` está travada pelo cifrão (continua `${'$'}1`) → resultado: `D${'$'}1`.",
            "4. Analise a 3ª parcela (`${'$'}C${'$'}3`): tanto a coluna quanto a linha estão travadas → permanece `${'$'}C${'$'}3`."
        ),
        exampleAnswer = "A fórmula resultante na célula D5 será `=${'$'}A5+D${'$'}1+${'$'}C${'$'}3`.",
        goldTip = "Grave o método infalível para provas da Cesgranrio: descubra primeiro quantas colunas e quantas linhas a fórmula andou; depois, aplique essa soma APENAS nas letras e números que NÃO tiverem o símbolo `${'$'}` colado à esquerda!"
    )

    private fun exercisesM3A2() = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-INF-SUP-11",
            lessonId = "tp_inf_m3_a2",
            statement = "Em uma planilha do Microsoft Excel, as células apresentam os seguintes valores: `A1 = 2`, `A2 = 3`, `A3 = 4`, `B1 = 5`, `B2 = 6` e `B3 = 10`. Se um analista digitar na célula `C1` a fórmula `=SOMA(A1:B2;B3) + A3^A1/2`, qual será o valor numérico exibido em `C1`?",
            options = listOf(
                "A) 34",
                "B) 29",
                "C) 30",
                "D) 24",
                "E) 38"
            ),
            correctIndex = 0,
            steps = listOf(
                "1. Primeiro avaliamos a função SOMA(A1:B2;B3): o intervalo A1:B2 soma A1 + A2 + B1 + B2 = 2 + 3 + 5 + 6 = 16.",
                "2. O segundo argumento (;B3) soma o valor de B3 (10) ao resultado anterior: 16 + 10 = 26.",
                "3. Agora calculamos a expressão aritmética `A3^A1/2`, onde A3 = 4 e A1 = 2, ou seja, `4^2/2`.",
                "4. Pela precedência do Excel, a potenciação (`^`) vem antes da divisão (`/`): `4^2 = 16`, e `16 / 2 = 8`.",
                "5. Somando os dois termos: 26 + 8 = 34."
            ),
            finalComment = "Gabarito: A. SOMA(A1:B2;B3) = (2+3+5+6) + 10 = 26; e 4^2/2 = 16/2 = 8. Total: 26 + 8 = 34."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-INF-SUP-12",
            lessonId = "tp_inf_m3_a2",
            statement = "Considere uma planilha do Microsoft Excel 365 na qual a célula `C3` contém a fórmula `=${'$'}B3*C${'$'}1`. Se o usuário copiar o conteúdo da célula `C3` (Ctrl + C) e colá-lo na célula `E6` (Ctrl + V), a fórmula gravada em `E6` será:",
            options = listOf(
                "A) `=${'$'}B6*E${'$'}1`",
                "B) `=${'$'}D6*E${'$'}4`",
                "C) `=${'$'}B3*C${'$'}1`",
                "D) `=${'$'}B6*C${'$'}4`",
                "E) `=${'$'}E6*E${'$'}1`"
            ),
            correctIndex = 0,
            steps = listOf(
                "1. Deslocamento de C3 para E6: a coluna avança 2 posições (C → D → E) e a linha aumenta 3 posições (3 → 6).",
                "2. Em `${'$'}B3`, a coluna B está travada pelo cifrão (`${'$'}B`) e a linha 3 é relativa (3 + 3 = 6), resultando em `${'$'}B6`.",
                "3. Em `C${'$'}1`, a coluna C é relativa (C + 2 colunas = E) e a linha 1 está travada pelo cifrão (`${'$'}1`), resultando em `E${'$'}1`.",
                "4. Portanto, a fórmula em E6 será `=${'$'}B6*E${'$'}1`."
            ),
            finalComment = "Gabarito: A. O cifrão fixa a coluna em `${'$'}B3` (vira `${'$'}B6`) e fixa a linha em `C${'$'}1` (vira `E${'$'}1`)."
        )
    )

    private fun bundleM3A3() = TranspetroDidacticProvider.buildBundle(
        lessonId = "tp_inf_m3_a3",
        intro = """
            O domínio das funções estatísticas, condicionais e de pesquisa do Microsoft Excel permite automatizar conciliações financeiras, controle de contratos e análise de estoques. A Cesgranrio exige atenção redobrada à sintaxe em português (argumentos separados por ponto e vírgula `;`) das funções `SOMA`, `MÉDIA`, `MÁXIMO`, `MÍNIMO`, `MAIOR`, `MENOR`, `CONT.NÚM`, `CONT.VALORES`, `CONT.SE`, `SOMASE`, `SE` e `PROCV` / `PROCX`.
        """.trimIndent(),
        theory = """
            • Funções Matemáticas e Estatísticas:
              - `=SOMA(intervalo)` e `=MÉDIA(intervalo)`: somam e calculam a média aritmética dos valores numéricos.
              - `=MÁXIMO(intervalo)` e `=MÍNIMO(intervalo)`: retornam o maior e o menor valor numérico de um intervalo.
              - `=MAIOR(matriz; k)` e `=MENOR(matriz; k)`: retornam o k-ésimo maior ou menor valor (ex: `=MAIOR(A1:A10; 2)` retorna o 2º maior valor, mesmo que haja empate no 1º!).
              - `=CONT.NÚM(intervalo)`: conta quantas células contêm apenas NÚMEROS (datas e horas também são contadas como números no Excel!).
              - `=CONT.VALORES(intervalo)`: conta todas as células NÃO VAZIAS (números, textos, erros).
            • Funções Condicionais (`SE`, `CONT.SE`, `SOMASE`):
              - `=SE(teste_lógico; valor_se_verdadeiro; [valor_se_falso])`: avalia uma condição (frequentemente combinada com `E(cond1; cond2)` ou `OU(cond1; cond2)`) e retorna o 2º argumento se verdadeira ou o 3º se falsa.
              - `=CONT.SE(intervalo; critério)`: conta quantas células atendem ao critério (ex: `=CONT.SE(B2:B20; ">=100")`).
              - `=SOMASE(intervalo_critério; critério; [intervalo_soma])`: avalia o critério no 1º intervalo e soma os valores correspondentes do 3º intervalo (se o 3º argumento for omitido, soma o próprio 1º intervalo). Para múltiplos critérios, usa-se `=SOMASES(intervalo_soma; interv_crit1; crit1; ...)`, onde o intervalo de soma vem PRIMEIRO!
            • Funções de Pesquisa (`PROCV` e `PROCX`) e Gráficos:
              - `=PROCV(valor_procurado; matriz_tabela; núm_índice_coluna; [procurar_intervalo])`: procura o valor na 1ª coluna da matriz_tabela e retorna o dado da mesma linha na coluna indicada por `núm_índice_coluna`. O 4º argumento `0` ou `FALSO` exige correspondência EXATA; `1` ou `VERDADEIRO` (ou omitido) faz busca aproximada (exige 1ª coluna em ordem crescente).
              - `=PROCX(pesquisa_valor; pesquisa_matriz; matriz_retorno)`: substitui o PROCV com vantagem de poder buscar tanto para a direita quanto para a esquerda e ter busca exata como padrão.
              - Gráficos: Colunas/Barras (comparação entre categorias), Linhas (evolução temporal/tendência), Pizza/Rosca (proporção de partes de um único todo).
        """.trimIndent(),
        practicalApplication = """
            Em uma planilha de controle de abastecimento da Transpetro, a coluna A lista a Base Operacional (`"Santos"`, `"Suape"`, `"Angra"`), a coluna B o tipo de combustível e a coluna C o volume movimentado. Para somar todo o volume apenas da base de `"Santos"`, o analista utiliza `=SOMASE(A2:A500; "Santos"; C2:C500)`. E para consultar a descrição de um item a partir do seu código na tabela de materiais, utiliza `=PROCV(E2; A2:D500; 3; FALSO)`.
        """.trimIndent(),
        summary = """
            • `MÁXIMO(A1:A5)` tem 1 argumento | `MAIOR(A1:A5; 1)` exige o argumento `k` (posição).
            • `SOMASE(interv_critério; critério; interv_soma)` vs. `SOMASES(interv_soma; interv_crit1; crit1; ...)`.
            • `SE(E(cond1; cond2); verd; falso)`: `E` exige todas verdadeiras; `OU` exige pelo menos uma verdadeira.
            • `PROCV(valor; matriz; col; 0)`: o `0` (ou `FALSO`) garante busca exata.
        """.trimIndent(),
        formulaName = "Sintaxe das Funções SE, SOMASE e PROCV no Excel",
        formulaSyntax = "=SE(teste; se_verd; se_falso) | =SOMASE(interv_crit; critério; interv_soma) | =PROCV(valor; matriz; índice_col; 0)",
        formulaTerms = listOf(
            "Critério no SOMASE/CONT.SE: Expressões relacionais devem vir entre aspas duplas (ex: \">50\", \"<>Cancelado\")",
            "núm_índice_coluna no PROCV: Número inteiro da coluna dentro da matriz_tabela (1 é a 1ª coluna da matriz selecionada)",
            "procurar_intervalo no PROCV: 0 ou FALSO para busca exata; 1 ou VERDADEIRO para busca aproximada",
            "MAIOR vs MÁXIMO: Se os valores forem {10, 20, 20}, MÁXIMO é 20, MAIOR(interv; 1) é 20 e MAIOR(interv; 2) também é 20"
        ),
        formulaWhenToUse = "Utilize para resolver questões de cálculo condicional e busca vertical em tabelas do Excel.",
        exampleTitle = "Resolução Combinada de SOMASE, SE e PROCV",
        exampleProblem = "Considere uma planilha onde `A1:A4` contém os setores `{\"ADM\", \"LOG\", \"ADM\", \"FIN\"}` e `B1:B4` contém os gastos `{100, 250, 300, 150}`. Qual será o resultado da fórmula `=SE(SOMASE(A1:A4; \"ADM\"; B1:B4) >= 400; \"Limite Atingido\"; \"Dentro da Meta\")`?",
        exampleSteps = listOf(
            "1. Avalie primeiro a função interna `SOMASE(A1:A4; \"ADM\"; B1:B4)`.",
            "2. No intervalo `A1:A4`, o texto `\"ADM\"` aparece nas linhas 1 (`A1`) e 3 (`A3`).",
            "3. Somamos os valores correspondentes em `B1` e `B3`: 100 + 300 = 400.",
            "4. Agora avaliamos o teste lógico da função `SE`: `400 >= 400` é VERDADEIRO.",
            "5. Como o teste é verdadeiro, a função `SE` retorna seu segundo argumento: `\"Limite Atingido\"`."
        ),
        exampleAnswer = "O resultado exibido na célula será `Limite Atingido`.",
        goldTip = "Pegadinha clássica da Cesgranrio com a função `MAIOR`: se a faixa `A1:A4` tiver os números `{15, 30, 30, 10}`, a fórmula `=MAIOR(A1:A4; 2)` retorna `30` (e não `15`!), porque o Excel ordena `{30, 30, 15, 10}` e pega o elemento da 2ª posição!"
    )

    private fun exercisesM3A3() = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-INF-SUP-13",
            lessonId = "tp_inf_m3_a3",
            statement = "Em uma planilha de controle de estoques no Microsoft Excel (versão em português), o intervalo `A1:C4` contém os seguintes dados:\n• Linha 1: `A1 = \"Tubulação\"`, `B1 = 120`, `C1 = 10`\n• Linha 2: `A2 = \"Válvula\"`, `B2 = 80`, `C2 = 25`\n• Linha 3: `A3 = \"Tubulação\"`, `B3 = 200`, `C3 = 15`\n• Linha 4: `A4 = \"Bomba\"`, `B4 = 200`, `C4 = 30`\n\nCaso um analista insira na célula `D1` a fórmula `=SOMASE(A1:A4; \"Tubulação\"; C1:C4) + MAIOR(B1:B4; 2)`, o resultado numérico retornado será:",
            options = listOf(
                "A) 145",
                "B) 225",
                "C) 320",
                "D) 215",
                "E) 105"
            ),
            correctIndex = 1,
            steps = listOf(
                "1. Cálculo de `SOMASE(A1:A4; \"Tubulação\"; C1:C4)`: procura \"Tubulação\" na coluna A (encontrado nas linhas 1 e 3) e soma os respectivos valores da coluna C (`C1 + C3` = 10 + 15 = 25).",
                "2. Cálculo de `MAIOR(B1:B4; 2)`: os valores em `B1:B4` são {120, 80, 200, 200}. Em ordem decrescente: 1º maior = 200; 2º maior = 200; 3º maior = 120; 4º maior = 80. Logo, `MAIOR(B1:B4; 2)` = 200.",
                "3. Soma final: 25 + 200 = 225."
            ),
            finalComment = "Gabarito: B. SOMASE retorna 10 + 15 = 25; MAIOR(B1:B4; 2) retorna o segundo elemento da lista decrescente {200, 200, 120, 80}, que é 200. Total: 225."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-INF-SUP-14",
            lessonId = "tp_inf_m3_a3",
            statement = "Um assistente administrativo organizou um catálogo de fornecedores no intervalo `B2:E100` do Microsoft Excel, onde a coluna `B` contém o Código do Fornecedor, a coluna `C` a Razão Social, a coluna `D` a UF e a coluna `E` a Situação Cadastral. Para que a célula `H2` exiba exatamente a Razão Social do fornecedor cujo código foi digitado na célula `G2` (retornando erro caso o código não exista), qual fórmula deve ser utilizada em `H2`?",
            options = listOf(
                "A) `=PROCV(G2; B2:E100; 2; FALSO)`",
                "B) `=PROCV(G2; B2:E100; 3; VERDADEIRO)`",
                "C) `=PROCH(G2; B2:E100; 2; 0)`",
                "D) `=SOMASE(B2:E100; G2; C2:C100)`",
                "E) `=PROCV(B2:E100; G2; 2; 1)`"
            ),
            correctIndex = 0,
            steps = listOf(
                "1. Como a tabela está disposta em colunas verticais, utiliza-se a função `PROCV`.",
                "2. O 1º argumento é o valor procurado (`G2`); o 2º argumento é a matriz da tabela (`B2:E100`, onde o código está na 1ª coluna da matriz, que é a coluna B).",
                "3. Dentro da matriz `B2:E100`, a coluna B é a 1ª coluna e a coluna C (Razão Social) é a 2ª coluna (`núm_índice_coluna = 2`).",
                "4. Para exigir correspondência exata, o 4º argumento deve ser `FALSO` (ou `0`)."
            ),
            finalComment = "Gabarito: A. Em `=PROCV(G2; B2:E100; 2; FALSO)`, busca-se G2 na 1ª coluna da matriz (B), retorna-se a 2ª coluna da matriz (C) com busca exata (FALSO)."
        )
    )

    private fun bundleM3A4() = TranspetroDidacticProvider.buildBundle(
        lessonId = "tp_inf_m3_a4",
        intro = """
            O Microsoft PowerPoint é a ferramenta do pacote Office voltada à criação de apresentações executivas, treinamentos de segurança operacional e reuniões de resultados. A Cesgranrio concentra suas questões nos Modos de Exibição, na função estratégica do Slide Mestre, na diferença conceitual entre Transições e Animações, e nos atalhos de condução da apresentação.
        """.trimIndent(),
        theory = """
            • Modos de Exibição (Guia Exibir):
              - Normal: modo padrão de edição, exibindo o painel de miniaturas à esquerda, o slide atual ao centro e o painel de Anotações abaixo.
              - Classificação de Slides: exibe miniaturas de todos os slides lado a lado, ideal para reordenar, duplicar ou excluir slides rapidamente e visualizar o tempo de transição.
              - Página de Anotações: permite editar as notas do orador que não aparecem no projetor para a plateia (usadas no Modo de Exibição do Apresentador).
              - Modo de Exibição de Leitura: executa a apresentação dentro de uma janela redimensionável com barra de status.
            • Slide Mestre (Guia Exibir > Modos de Exibição Mestres):
              - Armazena as informações de tema, fontes, cores, plano de fundo, posicionamento de caixas de texto e logotipos institucionais que devem se repetir em toda a apresentação. Qualquer alteração feita no Slide Mestre superior reflete automaticamente em todos os layouts subordinados, garantindo padronização visual.
            • Transições vs. Animações:
              - Transição (Guia Transições): é o efeito visual que ocorre na PASSAGEM de um slide para o próximo (aplica-se ao slide inteiro).
              - Animação (Guia Animações): é o efeito de movimento aplicado a OBJETOS individuais dentro de um slide (textos, gráficos, formas), classificados em Entrada, Ênfase, Saída e Caminhos de Animação.
            • Atalhos Essenciais de Apresentação:
              - `F5`: inicia a apresentação de slides desde o PRIMEIRO slide (do começo).
              - `Shift + F5`: inicia a apresentação a partir do SLIDE ATUAL.
              - `Ctrl + M`: insere um NOVO SLIDE na apresentação (atenção: `Ctrl + O` cria uma nova apresentação em branco!).
              - `Esc`: encerra a exibição da apresentação de slides. Durante a apresentação, pressionar `E` (ou `.`) escurece a tela (preta) e `C` (ou `,`) clareia a tela (branca).
        """.trimIndent(),
        practicalApplication = """
            Para preparar uma apresentação de resultados trimestrais da Transpetro com 45 slides, o analista insere o logotipo oficial da companhia e define a fonte padrão diretamente no Slide Mestre (guia Exibir). Assim, todos os 45 slides recebem o logotipo exatamente na mesma posição sem precisar copiar e colar manualmente em cada um.
        """.trimIndent(),
        summary = """
            • `F5` = Apresentar do início | `Shift + F5` = Apresentar do slide atual | `Ctrl + M` = Novo Slide.
            • Slide Mestre (Guia Exibir): padroniza logotipo, fontes e layout globalmente.
            • Transição = efeito entre slides | Animação = efeito em objetos internos do slide.
            • Classificação de Slides = visão em grade de miniaturas para reorganizar a sequência.
        """.trimIndent(),
        formulaName = "Atalhos e Recursos-Chave do Microsoft PowerPoint",
        formulaSyntax = "F5 (Início) | Shift + F5 (Slide Atual) | Ctrl + M (Novo Slide) | Slide Mestre (Padronização Global)",
        formulaTerms = listOf(
            "Slide Mestre: Controla o design global (logotipos, fontes, rodapés) de todos os slides de uma só vez",
            "Transição: Efeito visual na troca de um slide para outro",
            "Animação: Efeito visual em elementos específicos (título, imagem, gráfico) dentro do slide",
            "Ctrl + M vs Ctrl + D: Ctrl + M insere um novo slide em branco/layout; Ctrl + D duplica o slide/objeto selecionado"
        ),
        formulaWhenToUse = "Utilize para diferenciar Transição de Animação, identificar o uso do Slide Mestre ou responder atalhos do PowerPoint.",
        exampleTitle = "Padronização com Slide Mestre e Execução de Slides",
        exampleProblem = "Um analista está revisando o slide número 28 de uma apresentação de 50 slides no PowerPoint. Ele deseja: (1) testar imediatamente a exibição em tela cheia começando exatamente do slide 28 e (2) inserir um novo slide logo abaixo do slide 28. Quais atalhos de teclado ele deve utilizar?",
        exampleSteps = listOf(
            "1. Se ele pressionasse apenas F5, a apresentação começaria do slide 1.",
            "2. Para iniciar a apresentação em tela cheia a partir do slide selecionado (slide 28), o atalho correto é Shift + F5.",
            "3. Para inserir um novo slide na apresentação, o atalho padrão do PowerPoint é Ctrl + M."
        ),
        exampleAnswer = "Deve pressionar `Shift + F5` para apresentar a partir do slide 28 e `Ctrl + M` para inserir um novo slide.",
        goldTip = "Não caia na armadilha do `Ctrl + M`: no PowerPoint, `Ctrl + M` cria um NOVO SLIDE! Já para criar um novo arquivo de apresentação do zero no Office em português, o atalho é `Ctrl + O`."
    )

    private fun exercisesM3A4() = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-INF-SUP-15",
            lessonId = "tp_inf_m3_a4",
            statement = "Um assistente administrativo está elaborando uma apresentação institucional no Microsoft PowerPoint e recebeu a orientação de inserir a marca d'água e o logotipo da Transpetro no canto superior direito de todos os slides, além de padronizar a fonte de todos os títulos, de forma que qualquer novo slide adicionado herde automaticamente essas características. Para realizar essa tarefa de maneira centralizada e eficiente, ele deve acessar a guia Exibir e utilizar o recurso:",
            options = listOf(
                "A) Classificação de Slides.",
                "B) Slide Mestre.",
                "C) Painel de Animação.",
                "D) Modo de Exibição de Leitura.",
                "E) Transição de Slides."
            ),
            correctIndex = 1,
            steps = listOf(
                "A) Incorreta: Classificação de Slides serve para visualizar miniaturas e reordenar a sequência.",
                "B) Correta: o Slide Mestre controla os estilos de fonte, cores, planos de fundo e objetos fixos (como logotipos) que se repetem em toda a apresentação.",
                "C) Incorreta: Painel de Animação gerencia efeitos de entrada/saída de objetos de um slide.",
                "D) Incorreta: Modo de Leitura apenas exibe a apresentação em janela.",
                "E) Incorreta: Transição aplica efeitos visuais na mudança entre um slide e outro."
            ),
            finalComment = "Gabarito: B. O Slide Mestre é o recurso do PowerPoint projetado para aplicar identidade visual, logotipos e fontes padronizadas a todos os slides."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-INF-SUP-16",
            lessonId = "tp_inf_m3_a4",
            statement = "No Microsoft PowerPoint (versão em português), ao editar o 15º slide de uma apresentação, o usuário deseja adicionar um 16º slide logo em seguida e, depois de editá-lo, iniciar a apresentação em tela cheia começando diretamente desse 16º slide. Os atalhos de teclado que executam essas duas ações, respectivamente, são:",
            options = listOf(
                "A) Ctrl + N e F5.",
                "B) Ctrl + M e Shift + F5.",
                "C) Ctrl + O e Shift + F5.",
                "D) Ctrl + M e F5.",
                "E) Ctrl + D e Alt + F5."
            ),
            correctIndex = 1,
            steps = listOf(
                "1. No PowerPoint, o atalho para inserir um Novo Slide é `Ctrl + M`.",
                "2. O atalho `F5` inicia a apresentação do primeiro slide, enquanto `Shift + F5` inicia a apresentação a partir do slide atual."
            ),
            finalComment = "Gabarito: B. Ctrl + M insere um novo slide e Shift + F5 inicia a apresentação a partir do slide em edição."
        )
    )

    // =========================================================================
    // MÓDULO 4: INTERNET, REDES E CORREIO ELETRÔNICO
    // =========================================================================

    private fun bundleM4A1() = TranspetroDidacticProvider.buildBundle(
        lessonId = "tp_inf_m4_a1",
        intro = """
            A comunicação corporativa moderna apoia-se na infraestrutura de redes de computadores e nos protocolos da pilha TCP/IP. Nas provas da Cesgranrio, é fundamental diferenciar a abrangência geográfica das redes (LAN, MAN, WAN), os conceitos de Internet, Intranet, Extranet e VPN, além da função exata dos principais protocolos de aplicação (HTTP, HTTPS, DNS, FTP, DHCP).
        """.trimIndent(),
        theory = """
            • Classificação Geográfica das Redes:
              - PAN (Personal Area Network): alcance pessoal de poucos metros (ex: Bluetooth).
              - LAN (Local Area Network / Rede Local): restrita a uma sala, andar, edifício ou campus (ex: Ethernet cabeada e Wi-Fi de um escritório da Transpetro).
              - MAN (Metropolitan Area Network): interliga unidades dentro de uma mesma cidade ou região metropolitana.
              - WAN (Wide Area Network): abrange grandes distâncias geográficas (países/continentes). A Internet é a maior WAN pública do mundo.
            • Internet vs. Intranet vs. Extranet vs. VPN:
              - Internet: rede mundial pública baseada na suíte de protocolos TCP/IP.
              - Intranet: rede corporativa PRIVADA que utiliza exatamente os MESMOS protocolos da Internet (TCP/IP, HTTP/HTTPS, navegadores web), porém de acesso restrito aos colaboradores internos da organização. (Atenção: uma Intranet NÃO exige estar desconectada da Internet, bastando controle de acesso e firewall!).
              - Extranet: extensão controlada de parte da Intranet para usuários externos autorizados (fornecedores, clientes, parceiros logísticos) mediante autenticação.
              - VPN (Virtual Private Network): cria um "túnel" lógico criptografado sobre uma rede pública (Internet), permitindo que um colaborador em trabalho remoto acesse a Intranet com segurança.
            • Principais Protocolos de Rede (Suíte TCP/IP):
              - HTTP (porta 80) e HTTPS (porta 443): transferência de páginas web (hipertexto). O HTTPS adiciona camada de criptografia TLS/SSL, garantindo confidencialidade, integridade e autenticidade do servidor.
              - DNS (Domain Name System - porta 53): traduz nomes de domínio amigáveis (ex: `www.transpetro.com.br`) em endereços IP numéricos (ex: `186.202.15.10`) e vice-versa.
              - DHCP: atribui endereços IP automaticamente aos dispositivos que se conectam à rede.
        """.trimIndent(),
        practicalApplication = """
            Quando um analista da Transpetro trabalha remotamente e precisa acessar o portal interno de recursos humanos (Intranet), ele se conecta primeiro à VPN corporativa, que estabelece um túnel criptografado pela Internet. Já quando um fornecedor externo acessa o portal de compras da companhia com login e senha próprios para enviar notas fiscais, esse acesso caracteriza o uso de uma Extranet.
        """.trimIndent(),
        summary = """
            • LAN (local) < MAN (metropolitana) < WAN (longa distância/global).
            • Intranet = mesmos protocolos da Internet (TCP/IP, HTTP), mas restrita ao público interno.
            • Extranet = acesso externo autorizado (parceiros/fornecedores) a recursos da Intranet.
            • DNS = converte nomes de domínio (URL) em endereços IP.
            • HTTPS = HTTP + Criptografia TLS/SSL (cadeado no navegador).
        """.trimIndent(),
        formulaName = "Quadro Comparativo de Redes e Protocolos Web",
        formulaSyntax = "Intranet (Interna) | Extranet (Parceiros Externos) | VPN (Túnel Criptografado) | DNS (Nome ↔ IP) | HTTPS (HTTP + TLS)",
        formulaTerms = listOf(
            "Intranet: Rede privada corporativa que usa a pilha TCP/IP; restrita a funcionários",
            "Extranet: Parte da Intranet acessível a parceiros/fornecedores externos via login",
            "DNS (Domain Name System): Serviço de resolução de nomes em endereços IP",
            "HTTPS: Protocolo seguro de hipertexto que cifra os dados trafegados com TLS/SSL"
        ),
        formulaWhenToUse = "Utilize para distinguir os ambientes de rede corporativos e identificar o papel dos protocolos TCP/IP.",
        exampleTitle = "Identificação de Intranet, Extranet, VPN e DNS",
        exampleProblem = "Uma empresa estatal possui um portal interno baseado em páginas web acessível apenas por seus empregados. Para que empresas terceirizadas de manutenção consultem ordens de serviço nesse portal pela Internet, a estatal liberou credenciais específicas e exigiu conexão via túnel criptografado. Como se classificam o portal para os terceirizados e a tecnologia de tunelamento?",
        exampleSteps = listOf(
            "1. O portal interno para os empregados é uma Intranet.",
            "2. Quando parte dessa Intranet é disponibilizada de forma controlada para parceiros externos (empresas terceirizadas), temos o conceito de Extranet.",
            "3. A tecnologia que cria um túnel seguro e criptografado através da Internet pública é a VPN (Rede Privada Virtual)."
        ),
        exampleAnswer = "O acesso concedido aos parceiros terceirizados caracteriza uma Extranet, protegida no tráfego público por uma conexão VPN.",
        goldTip = "Pegadinha clássica da Cesgranrio: se uma questão afirmar que 'a Intranet utiliza protocolos proprietários diferentes do TCP/IP' ou que 'computadores em uma Intranet não podem ter acesso à Internet', marque como ERRADA imediatamente!"
    )

    private fun exercisesM4A1() = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-INF-SUP-17",
            lessonId = "tp_inf_m4_a1",
            statement = "Sobre os conceitos de Internet, Intranet e Extranet aplicados ao ambiente corporativo de uma empresa pública, assinale a afirmativa correta segundo o padrão cobrado pela Fundação Cesgranrio.",
            options = listOf(
                "A) A Intranet é uma rede privada que não utiliza o conjunto de protocolos TCP/IP, exigindo navegadores exclusivos desenvolvidos pela própria empresa.",
                "B) Para que uma rede local seja considerada uma Intranet, é obrigatório que todos os seus computadores estejam fisicamente desconectados da Internet.",
                "C) A Extranet consiste na extensão controlada de serviços da Intranet de uma organização para usuários externos autorizados, como fornecedores e clientes parceiros.",
                "D) O protocolo HTTP garante, por padrão, a criptografia ponta a ponta de todas as senhas digitadas na Intranet, dispensando o uso do HTTPS.",
                "E) O protocolo DNS tem como única função bloquear ataques de vírus entre a Internet e a Intranet."
            ),
            correctIndex = 2,
            steps = listOf(
                "A) Incorreta: a Intranet baseia-se exatamente nos mesmos protocolos da Internet (TCP/IP, HTTP/HTTPS, SMTP) e usa navegadores comuns.",
                "B) Incorreta: uma Intranet pode perfeitamente estar conectada à Internet (protegida por Firewall/Proxy).",
                "C) Correta: a Extranet permite que parceiros externos (fornecedores, clientes) acessem de forma autenticada parte da Intranet.",
                "D) Incorreta: o HTTP trafega dados em texto claro; quem provê criptografia (TLS/SSL) é o HTTPS.",
                "E) Incorreta: o DNS traduz nomes de domínio em endereços IP; quem filtra tráfego é o Firewall."
            ),
            finalComment = "Gabarito: C. A Extranet estende o acesso a recursos selecionados da Intranet para parceiros externos mediante autenticação."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-INF-SUP-18",
            lessonId = "tp_inf_m4_a1",
            statement = "Quando um usuário digita o endereço `https://www.transpetro.com.br` na barra de endereços do navegador, dois protocolos fundamentais entram em ação: o primeiro converte o nome de domínio textual no respectivo endereço IP numérico do servidor, e o segundo estabelece a transferência das páginas web com camada de criptografia TLS/SSL. Esses dois protocolos são, respectivamente:",
            options = listOf(
                "A) DHCP e FTP.",
                "B) DNS e HTTPS.",
                "C) SMTP e HTTP.",
                "D) POP3 e SSH.",
                "E) IMAP e DNS."
            ),
            correctIndex = 1,
            steps = listOf(
                "1. O serviço responsável por converter nomes de domínio (URLs) em endereços IP numéricos é o DNS (Domain Name System).",
                "2. O protocolo que transfere páginas de hipertexto utilizando criptografia TLS/SSL é o HTTPS."
            ),
            finalComment = "Gabarito: B. O DNS resolve o nome em endereço IP e o HTTPS realiza a comunicação web criptografada."
        )
    )

    private fun bundleM4A2() = TranspetroDidacticProvider.buildBundle(
        lessonId = "tp_inf_m4_a2",
        intro = """
            Os navegadores web (Google Chrome, Microsoft Edge, Mozilla Firefox) e o correio eletrônico (e-mail) são as ferramentas cotidianas de comunicação administrativa. A Cesgranrio cobra intensamente o funcionamento da Navegação Anônima (Privada), a diferença entre Cache e Cookies, o comportamento dos campos de destinatários de e-mail (`Para`, `Cc`, `Cco`) e os protocolos de envio e recebimento (`SMTP`, `POP3` e `IMAP`).
        """.trimIndent(),
        theory = """
            • Navegadores Web (Browsers):
              - Cache: armazena localmente arquivos estáticos das páginas (imagens, folhas de estilo CSS, scripts) para que o site carregue mais rápido em visitas futuras.
              - Cookies: pequenos arquivos de texto salvos pelo site no computador do usuário para guardar preferências, manter sessões de login ativas ou rastrear hábitos de navegação.
              - Navegação Anônima / InPrivate (`Ctrl + Shift + N` no Chrome/Edge; `Ctrl + Shift + P` no Firefox): ao fechar todas as janelas anônimas, o navegador APAGA do computador local o histórico de navegação, os cookies da sessão e os dados de formulários. ATENÇÃO: a janela anônima NÃO oculta a navegação do provedor de internet, do administrador da rede da empresa nem dos sites visitados, e NÃO apaga arquivos baixados (Downloads) ou Favoritos adicionados!
              - Operadores de Busca (Google/Bing): `"termo exato"` (aspas duplas buscam a frase exata), `site:gov.br` (restringe ao domínio), `filetype:pdf` (busca arquivos de formato específico), `-palavra` (exclui resultados com o termo).
            • Correio Eletrônico (E-mail):
              - Campos de Envio: `Para` (destinatários principais, visíveis a todos), `Cc` / Com Cópia (destinatários secundários, visíveis a todos) e `Cco` / Com Cópia Oculta (destinatários cujos endereços ficam OCULTOS para os demais participantes de `Para`, `Cc` e outros `Cco`).
              - Responder vs. Responder a Todos vs. Encaminhar: "Responder" envia apenas ao remetente; "Responder a Todos" envia ao remetente e a todos de `Para` e `Cc` (mas nunca para quem estava em `Cco`).
              - Protocolos de E-mail: `SMTP` (exclusivo para ENVIO de mensagens), `POP3` (RECEBIMENTO que, por padrão, baixa os e-mails para a máquina local e os apaga do servidor) e `IMAP` (RECEBIMENTO e gerenciamento que mantém as mensagens sincronizadas diretamente no servidor para acesso por múltiplos dispositivos).
        """.trimIndent(),
        practicalApplication = """
            Quando o setor de Compras da Transpetro precisa enviar um comunicado circular para 30 fornecedores concorrentes sem que um fornecedor veja o endereço de e-mail dos demais, o analista preenche os 30 endereços no campo `Cco` (Com Cópia Oculta), protegendo o sigilo comercial e a privacidade (LGPD).
        """.trimIndent(),
        summary = """
            • Janela Anônima (`Ctrl + Shift + N`): não salva histórico nem cookies locais ao fechar, MAS mantém downloads, favoritos e visibilidade na rede da empresa.
            • `Cco` (Cópia Oculta): ninguém em `Para`, `Cc` ou `Cco` vê os outros destinatários de `Cco`.
            • `SMTP` = Envio ("S"ua "M"ensagem "T"á "P"artindo) | `IMAP` = Sincroniza no servidor | `POP3` = Baixa e apaga do servidor por padrão.
        """.trimIndent(),
        formulaName = "Visibilidade no E-mail (Para, Cc, Cco) e Protocolos (SMTP / IMAP / POP3)",
        formulaSyntax = "Para e Cc (Visíveis a todos) | Cco (Invisível a todos os outros) | Envio: SMTP | Recebimento: IMAP / POP3",
        formulaTerms = listOf(
            "Cco (Bcc): Destinatário oculto; ele vê quem está em 'Para' e 'Cc', mas ninguém vê que ele recebeu",
            "SMTP (Simple Mail Transfer Protocol): Protocolo padrão de saída/transmissão de e-mails",
            "IMAP (Internet Message Access Protocol): Sincroniza pastas e status de leitura no servidor (múltiplos aparelhos)",
            "POP3 (Post Office Protocol v3): Faz download das mensagens para o cliente local"
        ),
        formulaWhenToUse = "Utilize para resolver questões sobre quem enxerga quem em um e-mail, escolha de protocolos ou janela anônima.",
        exampleTitle = "Análise de Destinatários e Resposta no Correio Eletrônico",
        exampleProblem = "João enviou um e-mail preenchendo os campos: `Para: ana@empresa.com`, `Cc: bruno@empresa.com` e `Cco: carlos@empresa.com; daniel@empresa.com`. Ao receber a mensagem, Bruno clicou em 'Responder a Todos'. Quais pessoas sabem que Carlos recebeu o e-mail original e quem receberá a resposta de Bruno?",
        exampleSteps = listOf(
            "1. Como Carlos foi incluído no campo `Cco` (Cópia Oculta), apenas o remetente (João) e o próprio Carlos sabem que ele recebeu a mensagem (nem Ana, nem Bruno, nem mesmo Daniel, que também está em Cco, enxergam o endereço de Carlos).",
            "2. Quando Bruno clica em 'Responder a Todos', o cliente de e-mail envia a resposta apenas para o remetente original (João) e para os destinatários visíveis nos campos `Para` (Ana) e `Cc`.",
            "3. Portanto, a resposta de Bruno será enviada apenas para João e Ana (Carlos e Daniel não recebem)."
        ),
        exampleAnswer = "Apenas João e o próprio Carlos sabem do envio a Carlos; a resposta de Bruno será enviada apenas para João e Ana.",
        goldTip = "Atenção à pegadinha: se houver duas pessoas no campo `Cco` (Carlos e Daniel), uma NÃO vê o endereço da outra! O campo `Cco` isola cada destinatário oculto individualmente."
    )

    private fun exercisesM4A2() = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-INF-SUP-19",
            lessonId = "tp_inf_m4_a2",
            statement = "Um analista administrativo abriu uma janela de Navegação Anônima no Google Chrome (`Ctrl + Shift + N`) em seu computador do escritório, acessou um portal governamental, adicionou a página aos Favoritos, fez o download de um edital em formato `.pdf` para a pasta Downloads e, em seguida, fechou a janela anônima. Sobre o efeito dessa sessão anônima após seu encerramento, é correto afirmar que:",
            options = listOf(
                "A) o arquivo `.pdf` baixado na pasta Downloads e o Favorito criado foram automaticamente excluídos ao fechar a janela.",
                "B) o administrador da rede corporativa e o provedor de internet ficaram impedidos de identificar que o computador acessou o portal governamental.",
                "C) o histórico das páginas visitadas e os cookies gerados durante a sessão anônima não ficaram salvos no navegador local, mas o arquivo baixado e o favorito foram mantidos.",
                "D) a navegação anônima criptografou o disco rígido da estação de trabalho, substituindo a função do antivírus.",
                "E) os cookies da sessão anônima permanecem gravados permanentemente no navegador até que o sistema operacional seja formatado."
            ),
            correctIndex = 2,
            steps = listOf(
                "A) Incorreta: arquivos baixados (Downloads) e Favoritos criados em janela anônima SÃO mantidos no computador.",
                "B) Incorreta: a navegação anônima não esconde o tráfego do administrador de rede, do firewall da empresa nem do provedor.",
                "C) Correta: ao fechar todas as janelas anônimas, o navegador descarta o histórico de navegação local, cookies e dados de formulário daquela sessão, mantendo downloads e favoritos.",
                "D) e E) Incorretas: não criptografa o disco nem mantém os cookies da sessão."
            ),
            finalComment = "Gabarito: C. A navegação privada limpa histórico e cookies locais da sessão ao fechar, mas preserva downloads e favoritos."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-INF-SUP-20",
            lessonId = "tp_inf_m4_a2",
            statement = "No gerenciamento de correio eletrônico corporativo, um coordenador enviou uma mensagem com a seguinte configuração: `De: coordenador@transpetro.com.br`, `Para: Equipe_A`, `Cc: Equipe_B` e `Cco: Auditor_1; Auditor_2`. Além disso, o coordenador utiliza um protocolo de recebimento de e-mails que mantém todas as mensagens e pastas sincronizadas diretamente no servidor, permitindo que ele leia os mesmos e-mails tanto pelo notebook quanto pelo smartphone. Sobre esse cenário, assinale a alternativa correta:",
            options = listOf(
                "A) `Auditor_1` consegue visualizar que `Auditor_2` também recebeu a mensagem, e o protocolo utilizado para sincronização no servidor é o POP3.",
                "B) Nem `Equipe_A`, nem `Equipe_B`, nem `Auditor_2` conseguem ver que `Auditor_1` recebeu a mensagem, e o protocolo de recebimento que mantém os e-mails sincronizados no servidor é o IMAP.",
                "C) Se `Equipe_A` clicar em 'Responder a Todos', a resposta será enviada automaticamente para `Auditor_1` e `Auditor_2`.",
                "D) `Auditor_1` não consegue visualizar quem está nos campos `Para` e `Cc`, e o protocolo de recebimento utilizado é o SMTP.",
                "E) Os destinatários em `Cc` ficam ocultos para `Equipe_A`, e o protocolo responsável pelo envio da mensagem é o IMAP."
            ),
            correctIndex = 1,
            steps = listOf(
                "1. Destinatários listados em `Cco` ficam ocultos para todos os demais destinatários (inclusive para outros listados em `Cco`). Logo, ninguém além do remetente e do próprio `Auditor_1` vê que `Auditor_1` recebeu o e-mail.",
                "2. O protocolo de recebimento que mantém as mensagens armazenadas e sincronizadas no servidor para acesso em múltiplos dispositivos é o IMAP (enquanto o SMTP é de envio e o POP3 baixa e remove do servidor por padrão)."
            ),
            finalComment = "Gabarito: B. O campo Cco oculta cada destinatário até mesmo dos outros em Cco, e o protocolo IMAP sincroniza a caixa postal diretamente no servidor."
        )
    )

    // =========================================================================
    // MÓDULO 5: SEGURANÇA DA INFORMAÇÃO E LGPD
    // =========================================================================

    private fun bundleM5A1() = TranspetroDidacticProvider.buildBundle(
        lessonId = "tp_inf_m5_a1",
        intro = """
            A Segurança da Informação protege os ativos informacionais da organização contra acessos indevidos, alterações não autorizadas e interrupções operacionais. A Fundação Cesgranrio cobra com altíssima recorrência os pilares fundamentais da segurança (CID + Autenticidade e Irretratabilidade), a Autenticação Multifator (MFA), o Princípio do Menor Privilégio e a distinção exata entre os tipos de Backup (Completo, Incremental e Diferencial).
        """.trimIndent(),
        theory = """
            • Princípios da Segurança da Informação:
              - Confidencialidade (Sigilo): garante que a informação seja acessada APENAS por pessoas ou sistemas devidamente autorizados (mecanismo principal: criptografia e controle de acesso).
              - Integridade: garante que a informação não foi alterada, corrompida ou falsificada de forma não autorizada durante o trânsito ou armazenamento (mecanismo principal: função Hash / resumo criptográfico).
              - Disponibilidade: garante que a informação e os sistemas estejam acessíveis sempre que os usuários autorizados precisarem (mecanismos principais: redundância, nobreak, clusters, backup e mitigação contra DDoS).
              - Autenticidade: comprova a identidade de quem produziu ou enviou a informação (mecanismo: login/MFA, assinatura digital).
              - Irretratabilidade (Não Repúdio): impede que o autor negue a autoria de uma transação ou documento assinado digitalmente com certificado ICP-Brasil.
            • Controle de Acesso: MFA e Menor Privilégio:
              - Autenticação Multifator (MFA / 2FA): exige a combinação de pelo menos dois fatores de naturezas DIFERENTES: (1) Algo que você sabe (senha/PIN), (2) Algo que você tem (token físico, código no smartphone, smartcard) e (3) Algo que você é (biometria: impressão digital, reconhecimento facial).
              - Princípio do Menor Privilégio: todo usuário ou processo deve receber apenas as permissões estritamente necessárias para desempenhar suas atribuições funcionais.
            • Rotinas de Backup (Cópia de Segurança):
              - Backup Completo (Normal / Full): copia TODOS os arquivos selecionados e LIMPA (desmarca) o atributo de arquivamento.
              - Backup Incremental: copia apenas os arquivos criados ou modificados desde o ÚLTIMO backup (seja ele completo ou incremental) e LIMPA o atributo de arquivamento. É o mais rápido de fazer, mas exige na restauração o último Completo + TODOS os Incrementais em sequência.
              - Backup Diferencial: copia todos os arquivos modificados desde o ÚLTIMO BACKUP COMPLETO e NÃO limpa o atributo de arquivamento (é cumulativo). Na restauração, exige apenas 2 peças: o último Completo + o último Diferencial.
        """.trimIndent(),
        practicalApplication = """
            Nos servidores de controle logístico da Transpetro, executa-se um Backup Completo aos domingos à noite e Backups Diferenciais de segunda a sexta-feira. Caso ocorra uma falha crítica na sexta-feira, a equipe de TI restaura o sistema rapidamente utilizando apenas o Backup Completo de domingo e o último Backup Diferencial de quinta-feira.
        """.trimIndent(),
        summary = """
            • Pilares CID: Confidencialidade (sigilo/criptografia), Integridade (exatidão/hash), Disponibilidade (acesso contínuo/backup).
            • MFA: exige fatores de categorias distintas (ex: Senha + Biometria ou Senha + Token). Duas senhas NÃO são MFA!
            • Backup Incremental: limpa o atributo; restaura com Full + TODOS os incrementais.
            • Backup Diferencial: NÃO limpa o atributo; restaura com Full + APENAS o último diferencial.
        """.trimIndent(),
        formulaName = "Comparativo de Restauração: Backup Incremental vs. Diferencial",
        formulaSyntax = "Restauração Incremental = Último Full + Todos os Incrementais | Restauração Diferencial = Último Full + Último Diferencial",
        formulaTerms = listOf(
            "Confidencialidade vs Integridade vs Disponibilidade: Sigilo (C), Não alteração (I), Estar no ar quando preciso (D)",
            "MFA (Autenticação Multifator): Combina 'o que sabe' (senha) + 'o que tem' (token) + 'o que é' (biometria)",
            "Backup Incremental: Mais rápido para gravar, porém mais trabalhoso para restaurar",
            "Backup Diferencial: Acumula alterações desde o último Full; restauração rápida com apenas 2 arquivos"
        ),
        formulaWhenToUse = "Utilize para diferenciar os princípios CID, validar se um login caracteriza MFA ou escolher o tipo de backup descrito no enunciado.",
        exampleTitle = "Identificação de MFA e Estratégia de Backup",
        exampleProblem = "Um analista faz login no portal financeiro digitando sua senha alfanumérica e, em seguida, validando sua impressão digital no leitor biométrico. Além disso, o servidor realiza uma cópia diária que grava apenas os arquivos alterados desde o último backup completo, sem desmarcar o atributo de arquivamento. Classifique o método de login e o tipo de backup.",
        exampleSteps = listOf(
            "1. O login combina 'algo que o usuário sabe' (senha) com 'algo que o usuário é' (biometria por impressão digital): são dois fatores de naturezas distintas, configurando Autenticação Multifator (MFA).",
            "2. O backup copia tudo que mudou desde o último backup completo e não limpa o atributo de arquivamento, acumulando as modificações diárias: trata-se do Backup Diferencial."
        ),
        exampleAnswer = "O login utiliza Autenticação Multifator (MFA) e a rotina descrita corresponde ao Backup Diferencial.",
        goldTip = "Pegadinha clássica da Cesgranrio: exigir que o usuário digite a 'senha alfanumérica' E o 'ano de nascimento da mãe' (ou um PIN numérico) NÃO é Autenticação Multifator, pois ambos pertencem ao mesmo fator ('algo que você sabe')!"
    )

    private fun exercisesM5A1() = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-INF-SUP-21",
            lessonId = "tp_inf_m5_a1",
            statement = "A equipe de infraestrutura de TI de uma estatal definiu uma política de cópias de segurança na qual o Backup Completo (Normal) é realizado todos os domingos e, de segunda a sábado, é executado um tipo de backup que copia apenas os arquivos criados ou modificados desde o último backup (seja ele completo ou do próprio tipo diário), desmarcando o atributo de arquivamento após cada execução. Esse backup diário mais rápido de ser gerado, mas que exige na restauração o último backup completo mais toda a cadeia sequencial de backups diários, é denominado:",
            options = listOf(
                "A) Backup Diferencial.",
                "B) Backup Incremental.",
                "C) Backup Espelhado.",
                "D) Backup Completo Cumulativo.",
                "E) Ponto de Restauração Volátil."
            ),
            correctIndex = 1,
            steps = listOf(
                "1. O backup que copia apenas o que mudou desde o último backup de qualquer tipo (completo ou incremental) e limpa o atributo de arquivamento é o Backup Incremental.",
                "2. Para restaurá-lo, é necessário o último Backup Completo mais todos os Backups Incrementais subsequentes na ordem exata."
            ),
            finalComment = "Gabarito: B. O Backup Incremental desmarca o atributo de arquivamento e exige toda a cadeia sequencial para restauração."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-INF-SUP-22",
            lessonId = "tp_inf_m5_a1",
            statement = "Relacione os princípios fundamentais da Segurança da Informação às situações práticas descritas a seguir:\n(I) Um relatório contábil armazenado no servidor foi protegido por resumo criptográfico (Hash) para garantir que nenhum valor numérico fosse adulterado sem detecção.\n(II) Um ataque de negação de serviço (DDoS) derrubou o portal de suprimentos, impedindo que os fornecedores cadastrados enviassem propostas dentro do prazo.\n\nNa situação (I), buscou-se preservar o princípio da __________, enquanto na situação (II) foi violado diretamente o princípio da __________.",
            options = listOf(
                "A) Confidencialidade — Integridade",
                "B) Integridade — Disponibilidade",
                "C) Disponibilidade — Confidencialidade",
                "D) Autenticidade — Irretratabilidade",
                "E) Integridade — Confidencialidade"
            ),
            correctIndex = 1,
            steps = listOf(
                "1. Em (I), garantir que os dados não sofram alteração não autorizada e manter sua exatidão por meio de Hash corresponde ao princípio da Integridade.",
                "2. Em (II), quando um sistema sai do ar (ex: por ataque DDoS) e deixa de estar acessível aos usuários autorizados, viola-se a Disponibilidade."
            ),
            finalComment = "Gabarito: B. Integridade protege contra alterações indevidas (Hash); Disponibilidade garante que o serviço esteja acessível quando necessário."
        )
    )

    private fun bundleM5A2() = TranspetroDidacticProvider.buildBundle(
        lessonId = "tp_inf_m5_a2",
        intro = """
            As organizações enfrentam diariamente ameaças cibernéticas que combinam códigos maliciosos (Malware) com técnicas psicológicas de manipulação humana (Engenharia Social). Na prova da Fundação Cesgranrio, é essencial saber distinguir o comportamento técnico de Vírus, Worm, Trojan, Spyware e Ransomware, bem como identificar ataques de Phishing e os mecanismos de defesa correspondentes.
        """.trimIndent(),
        theory = """
            • Principais Tipos de Malware (Códigos Maliciosos - Cartilha CERT.br):
              - Vírus: precisa de um arquivo HOSPEDEIRO (ex: `.exe`, `.docm`) e exige a AÇÃO do usuário (executar o arquivo infectado) para se ativar e se espalhar.
              - Worm (Verme): NÃO precisa de arquivo hospedeiro e se propaga AUTOMATICAMENTE pela rede explorando vulnerabilidades de segurança em sistemas desatualizados, consumindo grande largura de banda.
              - Trojan (Cavalo de Troia): apresenta-se como um programa legítimo e útil (ex: um suposto gerador de boletos ou protetor de tela), mas executa funções ocultas maliciosas ao ser instalado pelo usuário (como abrir portas para invasão). Não se replica sozinho.
              - Ransomware: sequestra os dados do computador ou servidor aplicando CRIPTOGRAFIA forte nos arquivos (ou bloqueando o sistema) e exige o pagamento de um RESGATE financeiro (geralmente em criptomoedas como Bitcoin) para devolver a chave de descriptografia.
              - Spyware: monitora as atividades do usuário sem seu consentimento. Subtipos clássicos: `Keylogger` (captura tudo o que é digitado no teclado) e `Screenlogger` (fotografa a tela ao redor do clique do mouse).
              - Bot / Botnet: programa que permite o controle remoto da máquina pelo invasor (computador "zumbi"); uma rede de computadores zumbis forma uma `Botnet`, usada para disparar Spam ou ataques `DDoS` (Negação de Serviço Distribuída).
            • Engenharia Social e Phishing:
              - Engenharia Social: arte de enganar e persuadir pessoas (explorando urgência, medo, autoridade ou curiosidade) para obter informações confidenciais.
              - Phishing: envio de mensagens eletrônicas fraudulentas (e-mail, SMS/`Smishing`, WhatsApp) que imitam bancos, órgãos públicos ou o suporte de TI da empresa, contendo links para páginas falsas com o objetivo de pescar senhas e credenciais. (`Spear Phishing`: ataque altamente direcionado a um alvo ou cargo específico).
        """.trimIndent(),
        practicalApplication = """
            Se um colaborador da Transpetro receber um e-mail urgente em nome do "Departamento de RH" solicitando que clique em um link externo para recadastrar sua senha de rede sob pena de bloqueio imediato do pagamento, trata-se de um ataque clássico de Phishing baseado em Engenharia Social. A conduta correta é nunca clicar no link, verificar o remetente real e reportar o incidente à equipe de Segurança da Informação.
        """.trimIndent(),
        summary = """
            • Vírus (precisa de hospedeiro e execução humana) vs. Worm (propaga-se sozinho pela rede sem hospedeiro).
            • Trojan (disfarçado de programa legítimo) vs. Spyware (`Keylogger` = teclado; `Screenlogger` = tela).
            • Ransomware = sequestro de dados por criptografia + cobrança de resgate (defesa primordial: Backup offline/imutável + atualização de sistemas).
            • Phishing = página/mensagem falsa que imita instituição confiável para roubar credenciais.
        """.trimIndent(),
        formulaName = "Mapa de Identificação Rápida de Ameaças Digitais (Padrão CERT.br / Cesgranrio)",
        formulaSyntax = "Vírus (Hospedeiro) | Worm (Autorreplicação em Rede) | Ransomware (Criptografia + Resgate) | Phishing (Isca Falsa)",
        formulaTerms = listOf(
            "Ransomware: Sequestra arquivos via criptografia e cobra resgate (afeta diretamente a Disponibilidade)",
            "Worm: Propaga-se autonomamente pelas redes explorando falhas de software sem precisar de hospedeiro",
            "Keylogger (Spyware): Registra as teclas digitadas pelo usuário para furtar senhas",
            "Phishing / Spear Phishing: Fraude que usa iscas eletrônicas falsas para induzir a vítima a entregar dados"
        ),
        formulaWhenToUse = "Utilize para associar as palavras-chave do enunciado da Cesgranrio ao tipo exato de ameaça cibernética.",
        exampleTitle = "Diagnóstico de Incidente com Ransomware e Phishing",
        exampleProblem = "Um funcionário clicou em um anexo malicioso recebido em um e-mail que imitava uma fatura de fornecedor. Minutos depois, todas as planilhas e bancos de dados da estação de trabalho tiveram suas extensões alteradas e tornaram-se inacessíveis por criptografia, surgindo um aviso na tela exigindo transferência em criptomoedas para liberar os arquivos. Quais foram as duas ameaças envolvidas?",
        exampleSteps = listOf(
            "1. O e-mail fraudulento que simulava uma fatura legítima para enganar o funcionário funcionou como isca de Engenharia Social / Phishing (ou Trojan no anexo).",
            "2. O código malicioso que criptografou os arquivos da estação e exigiu pagamento de resgate em criptomoedas para restabelecer o acesso é, por definição, um Ransomware."
        ),
        exampleAnswer = "A abordagem inicial utilizou Engenharia Social/Phishing e a infecção que criptografou os arquivos exigindo resgate foi um Ransomware.",
        goldTip = "Nas provas da Cesgranrio, falou em 'criptografar arquivos da vítima e exigir pagamento de resgate (geralmente em criptomoedas)', marque `Ransomware` sem hesitar! Falou em 'propagar-se automaticamente pela rede sem precisar de programa hospedeiro', marque `Worm`!"
    )

    private fun exercisesM5A2() = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-INF-SUP-23",
            lessonId = "tp_inf_m5_a2",
            statement = "As equipes de resposta a incidentes de segurança monitoram constantemente diferentes categorias de códigos maliciosos (malwares). Considerando as definições da Cartilha de Segurança para Internet (CERT.br) adotadas pela Fundação Cesgranrio, assinale a alternativa que diferencia corretamente o *Worm* do *Ransomware*:",
            options = listOf(
                "A) O Worm exige um arquivo hospedeiro em Word para funcionar, enquanto o Ransomware apenas exibe propagandas indesejadas no navegador.",
                "B) O Worm propaga-se automaticamente através de redes explorando vulnerabilidades sem depender de um programa hospedeiro, enquanto o Ransomware torna inacessíveis os dados armazenados no equipamento, geralmente usando criptografia, e exige pagamento de resgate.",
                "C) O Worm é um equipamento físico de proteção de rede (Firewall), enquanto o Ransomware é um protocolo de envio de e-mails seguros.",
                "D) O Worm captura exclusivamente a posição do cursor na tela, enquanto o Ransomware corrige falhas do sistema operacional Windows.",
                "E) Ambos necessitam que o usuário clique em um arquivo `.mp3` para apagar fisicamente a memória ROM da placa-mãe."
            ),
            correctIndex = 1,
            steps = listOf(
                "1. O Worm (verme) caracteriza-se pela propagação automática entre computadores na rede, explorando falhas de segurança sem precisar infectar um arquivo hospedeiro.",
                "2. O Ransomware sequestra os dados da vítima aplicando criptografia e exige pagamento de resgate (ransom) para devolver o acesso."
            ),
            finalComment = "Gabarito: B. Worm = autorreplicação autônoma pela rede sem hospedeiro; Ransomware = sequestro de dados por criptografia mediante resgate."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-INF-SUP-24",
            lessonId = "tp_inf_m5_a2",
            statement = "Um assistente administrativo recebeu uma mensagem de texto e um e-mail com o logotipo idêntico ao do banco onde recebe seu salário, informando que sua conta seria bloqueada em 2 horas caso ele não acessasse o link fornecido e digitasse sua senha e o código do token. Ao clicar no link, foi aberta uma página web visualmente igual à do banco, mas hospedada em um endereço falso. Esse tipo de golpe, que utiliza iscas eletrônicas para induzir o usuário a revelar dados sensíveis, é conhecido como:",
            options = listOf(
                "A) Defacement (Desfiguração de página).",
                "B) Phishing.",
                "C) Sniffing de pacotes.",
                "D) Força Bruta (Brute Force).",
                "E) Spoofing de endereço MAC."
            ),
            correctIndex = 1,
            steps = listOf(
                "1. O envio de mensagens fraudulentas (e-mail, SMS) que se passam por instituições confiáveis usando senso de urgência (Engenharia Social) e links para páginas falsas com o intuito de 'pescar' senhas é a definição exata de Phishing."
            ),
            finalComment = "Gabarito: B. O Phishing combina engenharia social com páginas e comunicações falsas para capturar credenciais da vítima."
        )
    )

    private fun bundleM5A3() = TranspetroDidacticProvider.buildBundle(
        lessonId = "tp_inf_m5_a3",
        intro = """
            A Lei Geral de Proteção de Dados Pessoais — LGPD (Lei nº 13.709/2018) regulamenta o tratamento de dados pessoais (em meios físicos e digitais) por pessoas naturais ou jurídicas de direito público ou privado. Nas provas da Cesgranrio, é indispensável dominar as definições legais do art. 5º (Dado Pessoal, Dado Pessoal Sensível, Dado Anonimizado), os papéis dos Agentes de Tratamento (Controlador, Operador) e do Encarregado (DPO), além das bases legais e da atuação da ANPD.
        """.trimIndent(),
        theory = """
            • Conceitos Fundamentais (Art. 5º da Lei nº 13.709/2018):
              - Dado Pessoal: qualquer informação relacionada a pessoa natural (física) identificada ou identificável (ex: nome, CPF, RG, endereço residencial, e-mail pessoal, endereço IP associado ao indivíduo). Atenção: dados exclusivamente de Pessoa Jurídica (CNPJ empresarial) NÃO são protegidos pela LGPD!
              - Dado Pessoal Sensível: dado pessoal sobre origem racial ou étnica, convicção religiosa, opinião política, filiação a sindicato ou a organização de caráter religioso, filosófico ou político, dado referente à saúde ou à vida sexual, dado genético ou biométrico, quando vinculado a uma pessoa natural.
              - Dado Anonimizado: dado relativo a titular que NÃO possa ser identificado, considerando a utilização de meios técnicos razoáveis e disponíveis na ocasião de seu tratamento (não é considerado dado pessoal, salvo se o processo for revertido).
            • Sujeitos e Agentes de Tratamento na LGPD:
              - Titular: pessoa natural a quem se referem os dados pessoais que são objeto de tratamento.
              - Controlador: pessoa natural ou jurídica, de direito público ou privado, a quem competem as DECISÕES referentes ao tratamento de dados pessoais.
              - Operador: pessoa natural ou jurídica, de direito público ou privado, que realiza o tratamento de dados pessoais EM NOME DO CONTROLADOR (seguindo suas diretrizes).
              - Encarregado (DPO - Data Protection Officer): pessoa indicada pelo controlador e operador para atuar como CANAL DE COMUNICAÇÃO entre o controlador, os titulares dos dados e a Autoridade Nacional de Proteção de Dados (ANPD). (Atenção: o Encarregado NÃO é classificado pela lei como Agente de Tratamento; os Agentes de Tratamento são apenas o Controlador e o Operador!).
            • Bases Legais e Exceções (Arts. 4º e 7º):
              - O consentimento do titular é apenas UMA das 10 bases legais; o tratamento também pode ocorrer sem consentimento para cumprimento de obrigação legal ou regulatória, execução de políticas públicas, execução de contrato, exercício regular de direitos em processo, proteção da vida ou tutela da saúde.
              - A LGPD NÃO se aplica ao tratamento realizado por pessoa natural para fins exclusivamente particulares e não econômicos, para fins exclusivamente jornalísticos/artísticos, acadêmicos ou de segurança pública, defesa nacional e investigação penal.
        """.trimIndent(),
        practicalApplication = """
            Quando a Transpetro coleta dados biométricos (impressão digital) e exames médicos ocupacionais de seus empregados para controle de acesso às bases e cumprimento de normas trabalhistas, a companhia atua como Controladora tratando Dados Pessoais Sensíveis amparada na base legal de cumprimento de obrigação legal/regulatória e prevenção à fraude/segurança do titular.
        """.trimIndent(),
        summary = """
            • Dado Pessoal = pessoa natural identificada ou identificável.
            • Dado Sensível = raça/etnia, religião, opinião política, sindicato, saúde, vida sexual, genético ou biométrico.
            • Agentes de Tratamento = Controlador (toma as decisões) e Operador (executa em nome do controlador).
            • Encarregado (DPO) = canal de comunicação entre Controlador, Titulares e ANPD.
        """.trimIndent(),
        formulaName = "Papéis e Classificação de Dados na LGPD (Lei nº 13.709/2018)",
        formulaSyntax = "Agentes = Controlador (Decide) + Operador (Executa) | Encarregado = Canal (Titular ↔ Controlador ↔ ANPD)",
        formulaTerms = listOf(
            "Dado Pessoal Sensível: Raça/etnia, religião, política, sindicato, saúde, vida sexual, genética e biometria",
            "Controlador: Quem define a finalidade e toma as decisões sobre o tratamento dos dados",
            "Operador: Quem processa os dados em nome e segundo as instruções do Controlador",
            "Encarregado (DPO): Canal de comunicação com os titulares e a ANPD (não é agente de tratamento)"
        ),
        formulaWhenToUse = "Utilize para distinguir dado comum de dado sensível e identificar quem é Controlador, Operador ou Encarregado.",
        exampleTitle = "Identificação de Controlador, Operador e Dado Sensível",
        exampleProblem = "A Transpetro decidiu implantar um sistema de reconhecimento facial nas catracas de um terminal e contratou uma empresa terceirizada de tecnologia em nuvem apenas para processar e armazenar os registros sob as ordens estritas da estatal. À luz da LGPD, classifique o tipo de dado coletado, o papel da Transpetro e o papel da empresa terceirizada.",
        exampleSteps = listOf(
            "1. O reconhecimento facial envolve dado biométrico vinculado a uma pessoa natural: segundo o art. 5º, II, da LGPD, trata-se de Dado Pessoal Sensível.",
            "2. A Transpetro tomou a decisão de coletar os dados e definiu a finalidade do tratamento: portanto, a Transpetro é a Controladora.",
            "3. A empresa de nuvem contratada realiza o tratamento em nome da Transpetro seguindo suas instruções: portanto, a terceirizada é a Operadora."
        ),
        exampleAnswer = "O dado biométrico é um Dado Pessoal Sensível; a Transpetro atua como Controladora e a empresa terceirizada atua como Operadora.",
        goldTip = "Duas pegadinhas favoritas da Cesgranrio sobre LGPD: (1) salário, CPF e score de crédito são dados pessoais comuns, e NÃO dados pessoais sensíveis (o rol de dados sensíveis é taxativo: raça, religião, política, sindicato, saúde, vida sexual, genética e biometria); (2) o consentimento NÃO é a única forma de tratar dados pessoais, pois obrigações legais e contratos dispensam consentimento!"
    )

    private fun exercisesM5A3() = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-INF-SUP-25",
            lessonId = "tp_inf_m5_a3",
            statement = "Nos termos da Lei Geral de Proteção de Dados Pessoais — LGPD (Lei nº 13.709/2018), a pessoa natural ou jurídica, de direito público ou privado, a quem competem as decisões referentes ao tratamento de dados pessoais, e a pessoa indicada para atuar como canal de comunicação entre a instituição, os titulares dos dados e a Autoridade Nacional de Proteção de Dados (ANPD) denominam-se, respectivamente:",
            options = listOf(
                "A) Operador e Controlador.",
                "B) Controlador e Encarregado.",
                "C) Encarregado e Operador.",
                "D) Titular e Agente de Segurança.",
                "E) Fiscal de Dados e Ouvidor Digital."
            ),
            correctIndex = 1,
            steps = listOf(
                "1. Segundo o art. 5º, VI, da LGPD, quem toma as decisões referentes ao tratamento de dados pessoais é o Controlador.",
                "2. Segundo o art. 5º, VIII, da LGPD, quem atua como canal de comunicação entre o controlador, os titulares dos dados e a ANPD é o Encarregado (DPO)."
            ),
            finalComment = "Gabarito: B. O Controlador detém o poder de decisão sobre o tratamento; o Encarregado é o canal de comunicação com titulares e a ANPD."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-INF-SUP-26",
            lessonId = "tp_inf_m5_a3",
            statement = "O departamento de Recursos Humanos de uma estatal mantém em seu banco de dados diversas informações cadastrais de seus empregados. De acordo com a classificação estabelecida no art. 5º, inciso II, da Lei nº 13.709/2018 (LGPD), assinale a alternativa que apresenta APENAS exemplos de Dados Pessoais Sensíveis:",
            options = listOf(
                "A) Número do CPF, endereço residencial e valor da remuneração mensal.",
                "B) Filiação a sindicato, dado biométrico (impressão digital) e dado referente à saúde do trabalhador.",
                "C) Nome completo, endereço de e-mail corporativo e cargo ocupado na empresa.",
                "D) Número da Carteira Nacional de Habilitação (CNH), telefone celular e histórico escolar.",
                "E) CNPJ da empresa prestadora de serviços, agência bancária e número da conta-corrente."
            ),
            correctIndex = 1,
            steps = listOf(
                "1. O art. 5º, II, da LGPD define como dado pessoal sensível aquele sobre origem racial ou étnica, convicção religiosa, opinião política, filiação a sindicato, saúde ou vida sexual, dado genético ou biométrico.",
                "2. CPF, nome, endereço, salário, telefone e dados bancários são dados pessoais comuns (não sensíveis).",
                "3. Apenas a alternativa B apresenta exclusivamente dados pessoais sensíveis: filiação a sindicato, biometria e dados de saúde."
            ),
            finalComment = "Gabarito: B. Filiação sindical, biometria e dados de saúde integram o rol legal de dados pessoais sensíveis da LGPD."
        )
    )
}

