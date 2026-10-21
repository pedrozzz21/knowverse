package com.example.data.python

import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample

/**
 * Conteúdo didático para o Nível Avançado de Python (Aulas py_a1_l1 até py_a2_l3).
 * Estrutura: Matéria (Arquitetura & Algoritmos) -> Sintaxe e Funções -> Exemplos Comentados Passo a Passo.
 */
object PythonAdvancedDidactic {

    fun get(lessonId: String): PythonDidacticBundle? {
        return when (lessonId) {
            "py_a1_l1" -> PythonDidacticBundle(
                theoryMarkdown = """
                    A Programação Orientada a Objetos (POO) é o paradigma fundamental utilizado em grandes sistemas corporativos, jogos, frameworks web (como Django e FastAPI) e ciência de dados.
                    
                    A analogia clássica é a planta arquitetônica de uma casa:
                    • A CLASSE (class) é a planta de construção ou o molde: define quais características (atributos) e quais ações (métodos) toda casa terá.
                    • O OBJETO (ou instância) é a casa física real construída no mundo real a partir daquela planta! Você pode criar centenas de casas com cores e proprietários diferentes a partir do mesmo molde.
                    
                    O método especial __init__ é o CONSTRUTOR: ele é disparado automaticamente no segundo exato em que um novo objeto é criado. E o parâmetro self é a referência ao próprio objeto sendo manipulado.
                """.trimIndent(),
                functionsAndSyntax = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "class e __init__",
                        syntax = "class NomeClasse:\n    def __init__(self, arg1, arg2):\n        self.atributo1 = arg1\n        self.atributo2 = arg2",
                        description = "Define a classe e inicializa os atributos de instância no momento da instanciação.",
                        parametersInfo = "self é sempre o primeiro parâmetro de qualquer método de instância em Python."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Métodos de Instância",
                        syntax = "def metodo(self, outros_args):\n    return self.atributo1",
                        description = "Comportamentos e ações que o objeto sabe executar utilizando seu próprio estado interno.",
                        parametersInfo = "Acessam e modificam variáveis do objeto através de self.nome_atributo."
                    )
                ),
                codeExamples = listOf(
                    LessonExample(
                        title = "Exemplo: Modelando uma Conta Bancária",
                        codeOrFormula = "class ContaBancaria:\n    def __init__(self, titular, saldo_inicial=0.0):\n        self.titular = titular\n        self.saldo = saldo_inicial\n\n    def depositar(self, valor):\n        self.saldo += valor\n        return self.saldo\n\n# Criando objetos\nconta1 = ContaBancaria('Carlos', 1000.0)\nconta1.depositar(250.0)\nprint('Titular:', conta1.titular, '| Saldo:', conta1.saldo)",
                        explanation = """
                            1. class ContaBancaria cria o molde.
                            2. __init__ recebe titular e saldo_inicial e grava em self.titular e self.saldo.
                            3. conta1 = ContaBancaria('Carlos', 1000.0) instancia o objeto real na memória.
                            4. conta1.depositar(250.0) soma 250 ao saldo, resultando em 1250.0.
                        """.trimIndent()
                    )
                ),
                goldTip = "Classes em Python usam a convenção PascalCase (iniciais maiúsculas, ex: ContaCorrente, ProcessadorDados), enquanto variáveis e métodos usam snake_case!"
            )

            "py_a1_l2" -> PythonDidacticBundle(
                theoryMarkdown = """
                    No mundo real, as coisas possuem hierarquias: um 'Desenvolvedor' e um 'Gerente' são ambos especializações do conceito mais amplo de 'Funcionário'.
                    
                    A HERANÇA permite que uma classe-filha (subclasse) herde automaticamente todos os atributos e métodos de uma classe-mãe (superclasse), evitando duplicação desnecessária de código.
                    
                    O POLIMORFISMO permite que a classe-filha personalize ou sobrescreva (override) o comportamento de um método herdado para atender à sua especialidade.
                    A função nativa super() é usada para chamar a implementação original da superclasse.
                """.trimIndent(),
                functionsAndSyntax = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Herança de Classes",
                        syntax = "class SubClasse(SuperClasse):\n    def __init__(self, args):\n        super().__init__(args)\n        self.novo_atributo = ...",
                        description = "Estabelece a relação 'é um tipo de', herdando toda a estrutura da classe base.",
                        parametersInfo = "super().__init__() executa o construtor da classe-mãe garantindo inicialização correta."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Polimorfismo (Sobrescrita de Método)",
                        syntax = "def metodo_existente(self):\n    # nova implementação específica",
                        description = "Substitui a lógica do método da classe-mãe pela lógica especializada da classe-filha.",
                        parametersInfo = "Permite que diferentes objetos respondam à mesma chamada de método de maneiras distintas."
                    )
                ),
                codeExamples = listOf(
                    LessonExample(
                        title = "Exemplo: Hierarquia de Funcionários e Bonificação",
                        codeOrFormula = "class Funcionario:\n    def __init__(self, nome, salario):\n        self.nome = nome\n        self.salario = salario\n\n    def calcular_bonus(self):\n        return self.salario * 0.10  # 10% padrão\n\nclass Gerente(Funcionario):\n    def calcular_bonus(self):\n        # Polimorfismo: Gerente recebe 25%\n        return self.salario * 0.25\n\nf = Funcionario('Pedro', 4000)\ng = Gerente('Mariana', 8000)\nprint(f.nome, 'Bônus:', f.calcular_bonus())\nprint(g.nome, 'Bônus:', g.calcular_bonus())",
                        explanation = """
                            Funcionario define o bônus padrão de 10% (R$ 400).
                            Gerente herda de Funcionario, mas sobrescreve calcular_bonus para 25% (R$ 2000)!
                        """.trimIndent()
                    )
                ),
                goldTip = "Use sempre super().__init__(...) ao criar o construtor da subclasse para não perder a inicialização dos atributos definidos na classe-mãe!"
            )

            "py_a2_l1" -> PythonDidacticBundle(
                theoryMarkdown = """
                    Textos do mundo real não são perfeitos: emails, CPFs, telefones, códigos postais e URLs vêm em múltiplos formatos e com erros de digitação.
                    
                    As EXPRESSÕES REGULARES (RegEx) são uma poderosa mini-linguagem de busca de padrões textuais embutida no Python através do módulo integrado 're'.
                    
                    Com uma única linha de RegEx, você pode validar se um email é autêntico, extrair todos os números de telefone de um documento de 500 páginas ou substituir termos censurados instantaneamente.
                """.trimIndent(),
                functionsAndSyntax = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "re.search()",
                        syntax = "re.search(padrao, texto)",
                        description = "Varre a string inteira procurando a primeira correspondência que atenda ao padrão. Retorna um objeto Match ou None.",
                        parametersInfo = "Use match.group() para extrair o texto exato capturado."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "re.findall()",
                        syntax = "re.findall(padrao, texto)",
                        description = "Retorna uma LISTA com todas as ocorrências não sobrepostas do padrão encontradas no texto.",
                        parametersInfo = "Se nada for encontrado, retorna uma lista vazia []."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Meta-caracteres Comuns",
                        syntax = "\\d (dígito 0-9) | \\w (letra/número) | + (1 ou mais) | * (0 ou mais) | ^ (início) | $ (fim)",
                        description = "Tokens especiais de correspondência de padrões.",
                        parametersInfo = "Sempre use raw-strings r'padrao' para evitar conflito com barras invertidas."
                    )
                ),
                codeExamples = listOf(
                    LessonExample(
                        title = "Exemplo: Extraindo Todos os Números de um Texto",
                        codeOrFormula = "import re\n\ntexto = 'Pedido 4821 confirmado no valor de 350 reais com entrega em 3 dias.'\n# Busca sequências de um ou mais dígitos numéricos\nnumeros = re.findall(r'\\d+', texto)\nprint('Números encontrados:', numeros)",
                        explanation = """
                            O padrão r'\\d+' significa:
                            \\d = qualquer caractere que seja dígito (0 a 9).
                            +  = um ou mais dígitos consecutivos.
                            re.findall extrai ['4821', '350', '3'].
                        """.trimIndent()
                    )
                ),
                goldTip = "Sempre declare padrões de Expressões Regulares usando raw-strings (com o prefixo 'r' na frente das aspas: r'\\d+'), para que as barras invertidas não sejam interpretadas como caracteres de escape!"
            )

            "py_a2_l2" -> PythonDidacticBundle(
                theoryMarkdown = """
                    Quando um programa termina sua execução, todos os dados guardados em variáveis na memória RAM são perdidos instantaneamente.
                    
                    Para que os dados persistam entre sessões, precisamos gravá-los no disco rígido ou SSD em ARQUIVOS DE TEXTO (como logs, relatórios ou arquivos .txt/.csv).
                    
                    • O RISCO DO LEAK DE MEMÓRIA: Se você abrir um arquivo e esquecer de fechá-lo (ou se o programa travar no meio), o arquivo pode ficar corrompido ou travado pelo sistema operacional.
                    • A SOLUÇÃO ELEGANTE: O Context Manager com a instrução with open(...) as f. Ele garante que o arquivo será fechado com segurança 100% das vezes, mesmo se ocorrer um erro grave!
                """.trimIndent(),
                functionsAndSyntax = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "with open(...) as f",
                        syntax = "with open('arquivo.txt', 'modo', encoding='utf-8') as f:\n    conteudo = f.read()",
                        description = "Gerenciador de contexto para abertura segura de arquivos com fechamento automático garantido.",
                        parametersInfo = "Modos comuns: 'r' (leitura), 'w' (escrita - sobrescreve), 'a' (append - adiciona no fim)."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "read(), readline() e write()",
                        syntax = "f.read() (tudo)  |  f.readline() (uma linha)  |  f.write('texto')",
                        description = "Métodos para consumir ou gravar dados no fluxo de I/O do arquivo.",
                        parametersInfo = "write() grava a string sem adicionar quebra de linha automática (adicione '\\n' manualmente se necessário)."
                    )
                ),
                codeExamples = listOf(
                    LessonExample(
                        title = "Exemplo: Escrevendo e Lendo Arquivo",
                        codeOrFormula = "# 1. Gravar relatório\nwith open('dados.txt', 'w', encoding='utf-8') as f:\n    f.write('Sistema Iniciado\\nStatus: Operacional\\n')\n\n# 2. Ler conteúdo gravado\nwith open('dados.txt', 'r', encoding='utf-8') as f:\n    conteudo = f.read()\n\nprint(conteudo)",
                        explanation = """
                            1. Abre 'dados.txt' no modo 'w' (write), grava duas linhas e fecha o arquivo automaticamente ao sair da indentação.
                            2. Abre no modo 'r' (read), lê todo o texto para a variável 'conteudo' e fecha o arquivo com segurança.
                        """.trimIndent()
                    )
                ),
                goldTip = "Sempre especifique encoding='utf-8' ao abrir arquivos em Python para evitar problemas com acentos e caracteres especiais em diferentes sistemas operacionais!"
            )

            "py_a2_l3" -> PythonDidacticBundle(
                theoryMarkdown = """
                    Duas das ferramentas mais importantes da Ciência da Computação para resolver problemas complexos com velocidade extrema são a Recursão e a Busca Binária.
                    
                    • RECURSÃO: Uma função que chama a si mesma para resolver uma versão menor do mesmo problema. Todo algoritmo recursivo DEVE ter:
                      1. CASO BASE (critério de parada): sem ele, a função chamará a si mesma infinitamente até estourar a memória (RecursionError: maximum recursion depth exceeded).
                      2. CASO RECURSIVO: onde a função divide o problema e chama a si mesma com dados reduzidos.
                      
                    • BUSCA BINÁRIA: Enquanto uma busca linear examina elemento por elemento em uma lista de 1 bilhão de itens (levando até 1 bilhão de operações), a Busca Binária corta o espaço de busca pela metade a cada passo, encontrando qualquer elemento em apenas 30 comparações! Sua complexidade é O(log n).
                """.trimIndent(),
                functionsAndSyntax = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Estrutura Canônica da Recursão",
                        syntax = "def recursiva(n):\n    if n <= 1: return caso_base\n    return n * recursiva(n - 1)",
                        description = "Resolve o problema quebrando-o em subproblemas idênticos de menor escala.",
                        parametersInfo = "O caso base deve ser alcançado em um número finito de passos."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Busca Binária (Binary Search)",
                        syntax = "inicio = 0; fim = len(arr) - 1\nmeio = (inicio + fim) // 2",
                        description = "Algoritmo de divisão e conquista de complexidade logarítmica O(log n).",
                        parametersInfo = "PRÉ-REQUISITO ABSOLUTO: A lista DEVE estar previamente ordenada!"
                    )
                ),
                codeExamples = listOf(
                    LessonExample(
                        title = "Exemplo: Fatorial Recursivo e Busca Binária",
                        codeOrFormula = "def fatorial(n):\n    # Caso Base\n    if n <= 1:\n        return 1\n    # Caso Recursivo\n    return n * fatorial(n - 1)\n\nprint('5! =', fatorial(5))  # 5 * 4 * 3 * 2 * 1 = 120",
                        explanation = """
                            fatorial(5) chama 5 * fatorial(4).
                            fatorial(4) chama 4 * fatorial(3).
                            ... até fatorial(1), que atinge o caso base e retorna 1.
                            A pilha desce multiplicando: 1 * 2 * 3 * 4 * 5 = 120!
                        """.trimIndent()
                    )
                ),
                goldTip = "Em qualquer entrevista técnica ou problema de algoritmos, sempre desenhe o 'Caso Base' primeiro antes de escrever a chamada recursiva!"
            )

            else -> null
        }
    }
}
