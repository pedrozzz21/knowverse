package com.example.data.python

import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample

/**
 * Conteúdo didático para o Nível Intermediário de Python (Aulas py_i1_l1 até py_i3_l1).
 * Estrutura: Matéria (Fundamentos) -> Sintaxe e Funções (O que cada método faz) -> Exemplos Comentados Passo a Passo.
 */
object PythonIntermediateDidactic {

    fun get(lessonId: String): PythonDidacticBundle? {
        return when (lessonId) {
            "py_i1_l1" -> PythonDidacticBundle(
                theoryMarkdown = """
                    Até agora trabalhamos com variáveis que guardam um único valor de cada vez. Mas e se precisarmos armazenar as notas de 100 alunos, a lista de compras ou o histórico de preços de uma ação?
                    
                    Para isso existem as LISTAS (lists): sequências ordenadas, dinâmicas e mutáveis de elementos.
                    
                    • INDEXAÇÃO ZERO-BASED: O primeiro item de qualquer lista em Python está no índice 0, o segundo no 1, e assim por diante. Além disso, Python tem indexação negativa: o índice -1 acessa o ÚLTIMO elemento da lista diretamente!
                    • FATIAMENTO (SLICING): Permite extrair fatias ou pedaços inteiros usando a notação lista[início:fim:passo].
                """.trimIndent(),
                functionsAndSyntax = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "append()",
                        syntax = "lista.append(elemento)",
                        description = "Insere um novo elemento no final da lista. Modifica a lista no próprio local (in-place) e retorna None.",
                        parametersInfo = "O tamanho da lista aumenta em 1 elemento."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "remove() e pop()",
                        syntax = "lista.remove(valor)  |  lista.pop([indice])",
                        description = "remove() busca e apaga a primeira ocorrência do valor indicado; pop() remove e RETORNA o item do índice (ou o último elemento se o índice for omitido).",
                        parametersInfo = "Se o elemento não existir, remove() lança ValueError."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "len()",
                        syntax = "len(colecao)",
                        description = "Função nativa que retorna o número total de elementos presentes na lista, texto ou qualquer coleção.",
                        parametersInfo = "Executa em tempo constante O(1), pois Python armazena o tamanho internamente."
                    )
                ),
                codeExamples = listOf(
                    LessonExample(
                        title = "Exemplo: Manipulação Completa de Lista",
                        codeOrFormula = "frutas = ['maçã', 'banana', 'laranja']\nfrutas.append('uva')\nprint('Última fruta:', frutas[-1])\nprint('Total de frutas:', len(frutas))\nfrutas.remove('banana')\nprint('Lista atualizada:', frutas)",
                        explanation = """
                            Linha 1: Cria a lista com 3 strings nos índices 0, 1 e 2.
                            Linha 2: frutas.append('uva') adiciona 'uva' na 4ª posição (índice 3).
                            Linha 3: frutas[-1] acessa o último elemento: 'uva'.
                            Linha 4: len(frutas) retorna 4.
                            Linha 5: frutas.remove('banana') exclui 'banana' da lista.
                            Linha 6: Exibe ['maçã', 'laranja', 'uva'].
                        """.trimIndent()
                    )
                ),
                goldTip = "Para criar uma cópia independente de uma lista sem alterar a original, use fatiamento vazio: copia = lista[:] ou copia = lista.copy()!"
            )

            "py_i1_l2" -> PythonDidacticBundle(
                theoryMarkdown = """
                    Listas são ótimas para dados ordenados numericamente por posição (0, 1, 2...). Mas como representar os dados de um cliente (nome, idade, email) de forma clara?
                    
                    Os DICIONÁRIOS (dict) são estruturas de dados associativas baseadas em pares CHAVE-VALOR (key-value pairs). Ao invés de acessar por índice numérico, você busca o valor diretamente pelo nome da chave!
                    
                    Internamente, os dicionários usam tabelas de dispersão (Hash Tables), o que significa que buscar um dado pelo nome da chave é absurdamente rápido (tempo constante O(1)), mesmo com milhões de registros!
                """.trimIndent(),
                functionsAndSyntax = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Dicionário Literal {}",
                        syntax = "dicionario = {'chave_1': valor_1, 'chave_2': valor_2}",
                        description = "Cria um mapa associativo delimitado por chaves e dois-pontos.",
                        parametersInfo = "Chaves devem ser imutáveis (geralmente strings ou inteiros) e únicas."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "dict.get()",
                        syntax = "dicionario.get(chave[, valor_padrao])",
                        description = "Acessa com segurança o valor associado à chave. Se a chave não existir, retorna None ou o valor_padrao especificado, SEM quebrar o programa!",
                        parametersInfo = "Muito superior ao acesso direto dicionario[chave], que lança KeyError se a chave não existir."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "keys(), values() e items()",
                        syntax = "dict.keys()  |  dict.values()  |  dict.items()",
                        description = "Visualizações dinâmicas: keys() retorna as chaves, values() os valores, e items() retorna pares tuplas (chave, valor) perfeitos para laços for.",
                        parametersInfo = "Exemplo: for k, v in dict.items(): print(k, '->', v)."
                    )
                ),
                codeExamples = listOf(
                    LessonExample(
                        title = "Exemplo: Cadastro de Usuário e Busca Segura",
                        codeOrFormula = "aluno = {\n    'nome': 'Ana Silva',\n    'curso': 'Ciência da Computação',\n    'periodo': 3\n}\n\n# Acesso seguro\ncidade = aluno.get('cidade', 'Não informada')\nprint(aluno['nome'], 'faz', aluno['curso'])\nprint('Cidade:', cidade)",
                        explanation = """
                            O dicionário aluno possui 3 chaves: 'nome', 'curso' e 'periodo'.
                            aluno.get('cidade', 'Não informada') tenta buscar 'cidade'. Como não existe, retorna 'Não informada' suavemente sem erro!
                        """.trimIndent()
                    )
                ),
                goldTip = "Sempre prefira usar dicionario.get(chave, padrao) ao invés de colchetes dicionario[chave] para evitar erros do tipo KeyError em produção!"
            )

            "py_i2_l1" -> PythonDidacticBundle(
                theoryMarkdown = """
                    Um dos princípios mais sagrados da engenharia de software é o princípio DRY (Don't Repeat Yourself - Não se Repita). Se você precisar calcular o imposto de 50 produtos diferentes, você jamais deve copiar e colar a mesma fórmula 50 vezes!
                    
                    As FUNÇÕES personalizadas são blocos de código nomeados e reutilizáveis criados com a palavra-chave def. Elas recebem dados de entrada (parâmetros/argumentos), processam as instruções internamente e retornam um resultado com a instrução return.
                    
                    Funções tornam o código modular, fácil de testar, documentado e extremamente limpo.
                """.trimIndent(),
                functionsAndSyntax = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "def (Definição de Função)",
                        syntax = "def nome_funcao(parametro_1, parametro_2=valor_padrao):\n    \"\"\"Docstring explicativa\"\"\"\n    instrucoes\n    return resultado",
                        description = "Cria uma nova função em Python com seus parâmetros de entrada e escopo local.",
                        parametersInfo = "Parâmetros com valores default são opcionais na chamada da função."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "return",
                        syntax = "return valor_retorno",
                        description = "Encerra a execução da função imediatamente e envia o valor resultante de volta para quem a chamou.",
                        parametersInfo = "Se a função terminar sem return explícito, ela retorna automaticamente o valor especial None."
                    )
                ),
                codeExamples = listOf(
                    LessonExample(
                        title = "Exemplo: Função com Parâmetro Opcional",
                        codeOrFormula = "def calcular_preco_final(valor, desconto=0.10):\n    \"\"\"Aplica percentual de desconto ao valor.\"\"\"\n    preco = valor * (1 - desconto)\n    return round(preco, 2)\n\n# Chamada com desconto padrão de 10%\np1 = calcular_preco_final(100.0)\n# Chamada com desconto especial de 30%\np2 = calcular_preco_final(100.0, 0.30)\nprint('Preço normal:', p1, '| Preço promoção:', p2)",
                        explanation = """
                            A função recebe 'valor' obrigatório e 'desconto' com valor padrão 0.10.
                            Na primeira chamada: calcular_preco_final(100.0) usa 0.10 => 90.0.
                            Na segunda chamada: calcular_preco_final(100.0, 0.30) usa 0.30 => 70.0.
                        """.trimIndent()
                    )
                ),
                goldTip = "Lembre-se: 'print()' apenas mostra na tela, enquanto 'return' realmente entrega o dado para ser guardado em variáveis e reutilizado em outros cálculos!"
            )

            "py_i2_l2" -> PythonDidacticBundle(
                theoryMarkdown = """
                    Python é mundialmente aclamado pela sua expressividade e elegância sintática. Duas ferramentas que elevam qualquer programador a um nível profissional são as Funções Lambda e a List Comprehension.
                    
                    • FUNÇÕES LAMBDA: São funções anônimas (sem nome) de uma única linha criadas rapidamente para operações curtas, muito usadas em ordenações personalizadas (com key=lambda x: ...) e transformações de dados.
                    
                    • LIST COMPREHENSION: Uma sintaxe incrivelmente compacta e veloz para criar novas listas a partir de iteráveis existentes, aplicando filtros e transformações em uma única linha legível!
                """.trimIndent(),
                functionsAndSyntax = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Função Lambda (Anônima)",
                        syntax = "lambda argumentos: expressao",
                        description = "Cria uma função inline simples que avalia uma expressão e retorna seu resultado automaticamente.",
                        parametersInfo = "Não utiliza a palavra 'return'; o retorno é implícito."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "List Comprehension",
                        syntax = "[expressao for item in iteravel if condicao]",
                        description = "Gera uma nova lista transformando cada item do iterável que satisfaça o filtro if opcional.",
                        parametersInfo = "É consideravelmente mais rápida e pythônica que um loop for tradicional com append()."
                    )
                ),
                codeExamples = listOf(
                    LessonExample(
                        title = "Exemplo: Filtrando e Elevando ao Quadrado",
                        codeOrFormula = "numeros = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]\n\n# List Comprehension: apenas pares ao quadrado\nquadrados_pares = [x ** 2 for x in numeros if x % 2 == 0]\nprint('Quadrados pares:', quadrados_pares)\n\n# Lambda para dobrar\ndobro = lambda x: x * 2\nprint('Dobro de 15:', dobro(15))",
                        explanation = """
                            Na List Comprehension:
                            1. Itera sobre cada x em numeros.
                            2. if x % 2 == 0 filtra apenas os pares: [2, 4, 6, 8, 10].
                            3. x ** 2 eleva cada um ao quadrado: [4, 16, 36, 64, 100].
                            Tudo isso em uma única linha cristalina!
                        """.trimIndent()
                    )
                ),
                goldTip = "Use List Comprehension sempre que estiver transformando ou filtrando dados: além de economizar 5 linhas de código, ela roda direto em C no interpretador!"
            )

            "py_i3_l1" -> PythonDidacticBundle(
                theoryMarkdown = """
                    Em sistemas de produção, coisas imprevistas SEMPRE acontecem: o usuário pode digitar letras onde se esperava um número, tentar dividir por zero, abrir um arquivo que não existe no disco ou perder a conexão de rede.
                    
                    Se o programa não estiver preparado, ele lança uma EXCEÇÃO (Crash) e o software fecha na cara do usuário com uma tela vermelha assustadora!
                    
                    Para criar aplicações robustas e tolerantes a falhas, usamos a estrutura defensiva try / except. Ela tenta executar o código perigoso e, se der erro, 'captura' a exceção e executa um plano de contingência elegante.
                """.trimIndent(),
                functionsAndSyntax = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Bloco try / except / else / finally",
                        syntax = "try:\n    codigo_perigoso\nexcept TipoDoErro as e:\n    tratamento_falha\nelse:\n    se_der_certo\nfinally:\n    sempre_executa",
                        description = "Estrutura completa de contenção e recuperação de erros em tempo de execução.",
                        parametersInfo = "finally é garantido de rodar sempre (mesmo com erros ou returns), ideal para fechar conexões e arquivos."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "raise",
                        syntax = "raise ValueError('Mensagem descritiva')",
                        description = "Provoca intencionalmente uma exceção quando uma regra de negócio for violada.",
                        parametersInfo = "Permite criar validações rigorosas em APIs e bibliotecas."
                    )
                ),
                codeExamples = listOf(
                    LessonExample(
                        title = "Exemplo: Divisão Segura contra Zero e Tipos Inválidos",
                        codeOrFormula = "def divisao_segura(a, b):\n    try:\n        resultado = a / b\n    except ZeroDivisionError:\n        return 'Erro: Divisão por zero não permitida!'\n    except TypeError:\n        return 'Erro: Ambos os valores devem ser numéricos!'\n    else:\n        return resultado\n\nprint(divisao_segura(10, 2))\nprint(divisao_segura(10, 0))",
                        explanation = """
                            Ao dividir 10 / 2: o try é bem-sucedido e o bloco else retorna 5.0.
                            Ao dividir 10 / 0: ocorre ZeroDivisionError; o bloco except captura o erro e retorna uma mensagem amigável sem derrubar o programa!
                        """.trimIndent()
                    )
                ),
                goldTip = "Nunca use um 'except:' nu sem especificar o tipo do erro! Sempre capture a exceção específica (ex: except ValueError:) para não mascarar bugs ocultos!"
            )

            else -> null
        }
    }
}
