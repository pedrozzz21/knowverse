#!/usr/bin/env python3
"""
Gerador do banco de questões de Programação Python (17 aulas x 10 questões = 170 questões).
Cobre todos os formatos: CODE (programação prática com testes automatizados), MULTIPLE_CHOICE,
TRUE_FALSE, FILL_BLANK, MATCHING e ESSAY.
"""
import json
import os

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
OUT = os.path.join(ROOT, "app", "src", "main", "assets", "assessment", "q_python.json")

# Especificação rica de cada uma das 17 aulas de Python
LESSONS = [
    {
        "lessonId": "py_b1_l1",
        "subtopic": "A Função print() e Saída Padrão",
        "concept": "Formatação de saída e parâmetros sep e end da função print()",
        "code1": ("saudar(nome)", "def saudar(nome):\n    # retorne 'Olá, <nome>!'\n    pass", "def saudar(nome):\n    return f'Olá, {nome}!'", [("saudar('Ana')", "'Olá, Ana!'"), ("saudar('Pedro')", "'Olá, Pedro!'"), ("saudar('Mundo')", "'Olá, Mundo!'", True)], "Construir mensagem de saudação formatada"),
        "code2": ("unir_com_sep(a, b, c, sep)", "def unir_com_sep(a, b, c, sep):\n    pass", "def unir_com_sep(a, b, c, sep):\n    return f'{a}{sep}{b}{sep}{c}'", [("unir_com_sep('2026', '11', '03', '-')", "'2026-11-03'"), ("unir_com_sep(1, 2, 3, ':')", "'1:2:3'"), ("unir_com_sep('A', 'B', 'C', ' | ')", "'A | B | C'", True)], "Simular o comportamento do parâmetro sep"),
        "code3": ("cabecalho_caixa(titulo)", "def cabecalho_caixa(titulo):\n    pass", "def cabecalho_caixa(titulo):\n    borda = '=' * len(titulo)\n    return f'{borda}\\n{titulo}\\n{borda}'", [("cabecalho_caixa('OK')", "'==\\nOK\\n=='"), ("cabecalho_caixa('PYTHON')", "'======\\nPYTHON\\n======'"), ("cabecalho_caixa('A')", "'=\\nA\\n='", True)], "Gerar cabeçalho multilinha com repetição de strings"),
        "code4": ("formatar_item_preco(item, qtd, preco)", "def formatar_item_preco(item, qtd, preco):\n    pass", "def formatar_item_preco(item, qtd, preco):\n    return f'{item} x{qtd} = R$ {qtd * preco:.2f}'", [("formatar_item_preco('Caderno', 2, 15.5)", "'Caderno x2 = R$ 31.00'"), ("formatar_item_preco('Caneta', 3, 2.0)", "'Caneta x3 = R$ 6.00'"), ("formatar_item_preco('Livro', 1, 49.9)", "'Livro x1 = R$ 49.90'", True)], "Formatar linha de recibo com duas casas decimais"),
        "mc1": ("Qual será a saída exata produzida por `print('Know', 'verse', sep='-', end='!')`?", ["Know-verse!", "Know verse!", "Know-verse\\n!", "Know!verse-", "Erro de sintaxe"], 0, ["", "Ignorou o parâmetro sep='-' e usou o espaço padrão", "Inalltou quebra de linha antes do end='!'", "Trocou a função dos parâmetros sep e end", "Achou que sep e end não são argumentos nomeados válidos"]),
        "mc2": ("Ao executar `print('A', 'B', 'C', sep='::')`, quantos caracteres `:` aparecem na saída?", ["4 caracteres", "2 caracteres", "6 caracteres", "3 caracteres", "0 caracteres"], 0, ["", "Contou apenas um separador entre três argumentos", "Adicionou o separador também após o último elemento", "Pensou que cada argumento recebe um caractere ':'", "Ignorou o argumento nomeado sep"]),
        "tf": ("Na função `print()` do Python 3, o valor padrão do parâmetro `sep` é um espaço simples `' '` e o valor padrão de `end` é a quebra de linha `'\\n'`.", True),
        "fill": ("Em Python, para evitar que a função `print('Olá', ___='')` pule para a próxima linha ao terminar, devemos preencher a lacuna com o nome do parâmetro adequado.", ["end"]),
        "match": [["print('a', 'b')", "Imprime 'a b' separado por espaço"], ["sep='-'", "Define hífen entre argumentos de print()"], ["end=''", "Suprime a quebra de linha final"], ["\\n", "Sequência de escape de nova linha"]],
        "essay": ("Explique a função dos parâmetros `sep` e `end` na função `print()` do Python e dê um exemplo de situação em que alterá-los é útil.", "O parâmetro sep define o separador inserido entre múltiplos argumentos passados para print, cujo padrão é espaço. Já o parâmetro end define o texto impresso ao final da chamada, substituindo a quebra de linha padrão. Eles são úteis para formatar datas, tabelas ou manter cursores na mesma linha.", [["sep", "separador"], ["end", "final", "quebra de linha", "nova linha"], ["print", "argumento", "padrao", "padrão"]])
    },
    {
        "lessonId": "py_b1_l2",
        "subtopic": "Declarando Variáveis",
        "concept": "Tipagem dinâmica, identificadores válidos e função type()",
        "code1": ("nome_tipo(valor)", "def nome_tipo(valor):\n    pass", "def nome_tipo(valor):\n    return type(valor).__name__", [("nome_tipo(42)", "'int'"), ("nome_tipo(3.14)", "'float'"), ("nome_tipo(True)", "'bool'", True)], "Inspecionar o tipo dinâmico de uma variável"),
        "code2": ("trocar_valores(a, b)", "def trocar_valores(a, b):\n    pass", "def trocar_valores(a, b):\n    a, b = b, a\n    return (a, b)", [("trocar_valores(1, 2)", "(2, 1)"), ("trocar_valores('x', 'y')", "('y', 'x')"), ("trocar_valores(0, 99)", "(99, 0)", True)], "Realizar desempacotamento de tuplas para permutar variáveis"),
        "code3": ("eh_identificador_valido(nome)", "def eh_identificador_valido(nome):\n    pass", "import keyword\ndef eh_identificador_valido(nome):\n    return nome.isidentifier() and not keyword.iskeyword(nome)", [("eh_identificador_valido('total_1')", "True"), ("eh_identificador_valido('1total')", "False"), ("eh_identificador_valido('for')", "False", True)], "Validar nomes de variáveis segundo as regras léxicas do Python"),
        "code4": ("resumo_variavel(nome, valor)", "def resumo_variavel(nome, valor):\n    pass", "def resumo_variavel(nome, valor):\n    return f'{nome}={repr(valor)} ({type(valor).__name__})'", [("resumo_variavel('x', 10)", "'x=10 (int)'"), ("resumo_variavel('ativo', False)", "'ativo=False (bool)'"), ("resumo_variavel('pi', 3.5)", "'pi=3.5 (float)'", True)], "Combinar nome, representação e tipo de uma variável"),
        "mc1": ("Qual dos seguintes identificadores é INVÁLIDO como nome de variável em Python?", ["2_fase_enem", "fase_2_enem", "_fase2", "Fase2Enem", "FASE_2"], 0, ["", "Sublinhado entre palavras (snake_case) é válido", "Iniciar identificador com sublinhado é permitido", "CamelCase com letras e dígitos após a primeira posição é válido", "Constantes em maiúsculas com números no final são válidas"]),
        "mc2": ("Após executar `x = 10` e em seguida `x = 'dez'`, qual é o resultado de `type(x).__name__`?", ["str", "int", "float", "TypeError", "var"], 0, ["", "Acha que a variável mantém o tipo int inicial (tipagem estática)", "Confundiu texto com ponto flutuante", "Acreditou que reatribuir tipo diferente lança exceção em Python", "Pensou que existe um tipo genérico 'var' em Python"]),
        "tf": ("Em Python, a tipagem é dinâmica e forte: uma variável pode passar a referenciar um objeto de outro tipo, mas o interpretador não soma automaticamente `'5' + 3` sem conversão explícita.", True),
        "fill": ("A convenção recomendada pela PEP 8 para nomes de variáveis e funções em Python, usando letras minúsculas separadas por sublinhado, chama-se ___.", ["snake_case", "snake case"]),
        "match": [["int", "Número inteiro como 42 ou -7"], ["float", "Número de ponto flutuante como 3.14"], ["str", "Sequência imutável de caracteres"], ["bool", "Valor lógico True ou False"]],
        "essay": ("Diferencie tipagem dinâmica de tipagem fraca e explique como o Python se comporta ao reatribuir variáveis e ao tentar somar uma string com um inteiro.", "Tipagem dinâmica significa que o tipo está associado ao objeto em tempo de execução e uma variável pode ser reatribuída para outro tipo. Já tipagem forte impede conversões implícitas arbitrárias, por isso somar string com inteiro gera TypeError.", [["dinamica", "dinâmica", "execucao", "execução"], ["forte", "implicita", "implícita", "typeerror"], ["variavel", "variável", "objeto", "tipo"]])
    },
    {
        "lessonId": "py_b1_l3",
        "subtopic": "Casting com int() e float()",
        "concept": "Conversão explícita de tipos (type casting) entre str, int, float e bool",
        "code1": ("somar_strings_numericas(a, b)", "def somar_strings_numericas(a, b):\n    pass", "def somar_strings_numericas(a, b):\n    return int(a) + int(b)", [("somar_strings_numericas('12', '8')", "20"), ("somar_strings_numericas('-5', '15')", "10"), ("somar_strings_numericas('100', '250')", "350", True)], "Converter entradas textuais em inteiros antes de somar"),
        "code2": ("converter_preco_br(texto)", "def converter_preco_br(texto):\n    pass", "def converter_preco_br(texto):\n    limpo = texto.replace('R$', '').strip().replace('.', '').replace(',', '.')\n    return round(float(limpo), 2)", [("converter_preco_br('R$ 12,50')", "12.5"), ("converter_preco_br('1.250,75')", "1250.75"), ("converter_preco_br('R$ 0,99')", "0.99", True)], "Normalizar string numérica brasileira e converter para float"),
        "code3": ("parte_inteira_e_decimal(valor_str)", "def parte_inteira_e_decimal(valor_str):\n    pass", "def parte_inteira_e_decimal(valor_str):\n    v = float(valor_str)\n    i = int(v)\n    return (i, round(v - i, 4))", [("parte_inteira_e_decimal('7.25')", "(7, 0.25)"), ("parte_inteira_e_decimal('10.0')", "(10, 0.0)"), ("parte_inteira_e_decimal('3.75')", "(3, 0.75)", True)], "Extrair parte inteira por truncamento com int()"),
        "code4": ("media_de_textos(lista_str)", "def media_de_textos(lista_str):\n    pass", "def media_de_textos(lista_str):\n    nums = [float(x) for x in lista_str]\n    return round(sum(nums) / len(nums), 2)", [("media_de_textos(['6.0', '8.0'])", "7.0"), ("media_de_textos(['5', '7', '9'])", "7.0"), ("media_de_textos(['1.5', '2.5', '3.5'])", "2.5", True)], "Converter lista de strings para float e calcular média"),
        "mc1": ("Qual é o resultado de `int(3.99)` e de `float('12')` em Python, respectivamente?", ["3 e 12.0", "4 e 12.0", "3 e '12.0'", "ValueError e 12.0", "4 e 12"], 0, ["", "Achou que int() arredonda para o inteiro mais próximo em vez de truncar", "Pensou que float() retorna string formatada", "Confundiu int(float) com int('3.99') em string", "Esqueceu que float() produz número com casa decimal"]),
        "mc2": ("O que ocorre ao avaliar diretamente `int('3.14')` em Python?", ["Lança ValueError, pois a string contém ponto decimal", "Retorna o inteiro 3 por truncamento", "Retorna o float 3.14", "Retorna o inteiro 4 por arredondamento", "Retorna None"], 0, ["", "Confundiu int('3.14') com int(float('3.14'))", "Pensou que int() devolve float quando vê ponto", "Acreditou que int() arredonda strings decimais", "Achou que falhas de conversão retornam None"]),
        "tf": ("Para converter a string `'45.8'` em um número inteiro truncado em Python, deve-se primeiro converter para `float('45.8')` e depois aplicar `int()`.", True),
        "fill": ("Em Python, a função embutida usada para converter um número inteiro `42` em sua representação textual `'42'` é ___().", ["str"]),
        "match": [["int(7.9)", "Retorna 7 (trunca a parte fracionária)"], ["float('5')", "Retorna 5.0"], ["str(100)", "Retorna '100'"], ["bool(0)", "Retorna False"]],
        "essay": ("Explique por que `'10' + '20'` resulta em `'1020'` enquanto `int('10') + int('20')` resulta em `30`, destacando o papel do type casting na leitura de dados.", "O operador + aplicado a strings realiza concatenação textual, unindo os caracteres em '1020'. Quando aplicamos o type casting com int(), convertemos as strings em números inteiros na memória, permitindo a soma aritmética que resulta em 30.", [["concatenacao", "concatenação", "string", "texto"], ["int", "casting", "conversao", "conversão"], ["soma", "aritmetica", "aritmética", "30"]])
    },
    {
        "lessonId": "py_b2_l1",
        "subtopic": "Divisão Inteira e Módulo (%)",
        "concept": "Operadores aritméticos //, %, ** e precedência de operadores",
        "code1": ("converter_segundos(total_seg)", "def converter_segundos(total_seg):\n    pass", "def converter_segundos(total_seg):\n    horas = total_seg // 3600\n    resto = total_seg % 3600\n    minutos = resto // 60\n    segundos = resto % 60\n    return (horas, minutos, segundos)", [("converter_segundos(3665)", "(1, 1, 5)"), ("converter_segundos(125)", "(0, 2, 5)"), ("converter_segundos(7200)", "(2, 0, 0)", True)], "Decompor segundos em horas, minutos e segundos usando // e %"),
        "code2": ("cedula_menor_troco(valor)", "def cedula_menor_troco(valor):\n    pass", "def cedula_menor_troco(valor):\n    n100 = valor // 100\n    valor %= 100\n    n50 = valor // 50\n    valor %= 50\n    n10 = valor // 10\n    n1 = valor % 10\n    return (n100, n50, n10, n1)", [("cedula_menor_troco(183)", "(1, 1, 3, 3)"), ("cedula_menor_troco(50)", "(0, 1, 0, 0)"), ("cedula_menor_troco(249)", "(2, 0, 4, 9)", True)], "Decompor valor inteiro em cédulas de 100, 50, 10 e 1"),
        "code3": ("eh_multiplo_simultaneo(n, a, b)", "def eh_multiplo_simultaneo(n, a, b):\n    pass", "def eh_multiplo_simultaneo(n, a, b):\n    return (n % a == 0) and (n % b == 0)", [("eh_multiplo_simultaneo(12, 3, 4)", "True"), ("eh_multiplo_simultaneo(15, 3, 4)", "False"), ("eh_multiplo_simultaneo(30, 5, 6)", "True", True)], "Verificar divisibilidade simultânea com operador módulo"),
        "code4": ("ultimo_digito_potencia(base, exp)", "def ultimo_digito_potencia(base, exp):\n    pass", "def ultimo_digito_potencia(base, exp):\n    return (base ** exp) % 10", [("ultimo_digito_potencia(2, 5)", "2"), ("ultimo_digito_potencia(7, 2)", "9"), ("ultimo_digito_potencia(3, 4)", "1", True)], "Combinar exponenciação ** e resto % 10"),
        "mc1": ("Qual é o resultado da expressão `17 // 5 + 17 % 5 * 2` em Python?", ["7", "10", "5.4", "9", "6"], 0, ["", "Somou 17 // 5 com 17 % 5 antes de multiplicar por 2 (erro de precedência)", "Usou divisão real 17 / 5 em vez de divisão inteira //", "Calculou 17 // 5 como 4 em vez de 3", "Esqueceu de multiplicar o resto por 2"]),
        "mc2": ("Para verificar se um número inteiro `n` é ímpar em Python, qual expressão booleana está correta?", ["n % 2 != 0", "n // 2 == 1", "n / 2 == 0", "n % 2 == 0", "n ** 2 == 1"], 0, ["", "Confundiu quociente inteiro com resto da divisão por 2", "Usou divisão real comparada a zero", "Escreveu a condição de número par em vez de ímpar", "Usou potenciação em vez de módulo"]),
        "tf": ("Em Python, o operador `**` (exponenciação) possui precedência maior que a multiplicação `*`, a divisão inteira `//` e a adição `+`.", True),
        "fill": ("Em Python, o operador que retorna apenas o quociente inteiro de uma divisão (descartando a parte fracionária para inteiros positivos) é ___.", ["//"]),
        "match": [["19 // 4", "Resulta em 4 (quociente inteiro)"], ["19 % 4", "Resulta em 3 (resto da divisão)"], ["2 ** 5", "Resulta em 32 (potenciação)"], ["19 / 4", "Resulta em 4.75 (divisão real)"]],
        "essay": ("Explique a diferença entre os operadores `/`, `//` e `%` em Python e mostre como `//` e `%` trabalham juntos para converter 135 minutos em horas e minutos.", "O operador / realiza a divisão em ponto flutuante, // calcula o quociente inteiro da divisão e % retorna o resto da divisão. Para 135 minutos, 135 // 60 fornece 2 horas inteiras e 135 % 60 fornece os 15 minutos restantes.", [["quociente", "inteira", "//"], ["resto", "modulo", "módulo", "%"], ["divisao", "divisão", "flutuante", "/"]])
    },
    {
        "lessonId": "py_b2_l2",
        "subtopic": "Tomada de Decisão com Indentação",
        "concept": "Estruturas condicionais if, elif e else e operadores lógicos",
        "code1": ("classificar_nota(nota)", "def classificar_nota(nota):\n    pass", "def classificar_nota(nota):\n    if nota >= 7.0:\n        return 'Aprovado'\n    elif nota >= 5.0:\n        return 'Recuperação'\n    else:\n        return 'Reprovado'", [("classificar_nota(8.5)", "'Aprovado'"), ("classificar_nota(5.5)", "'Recuperação'"), ("classificar_nota(4.9)", "'Reprovado'", True)], "Encadear if, elif e else para faixas numéricas"),
        "code2": ("eh_bissexto(ano)", "def eh_bissexto(ano):\n    pass", "def eh_bissexto(ano):\n    return (ano % 400 == 0) or (ano % 4 == 0 and ano % 100 != 0)", [("eh_bissexto(2024)", "True"), ("eh_bissexto(1900)", "False"), ("eh_bissexto(2000)", "True", True)], "Combinar condições lógicas com and, or e !="),
        "code3": ("tarifa_estacionamento(horas)", "def tarifa_estacionamento(horas):\n    pass", "def tarifa_estacionamento(horas):\n    if horas <= 0:\n        return 0\n    elif horas <= 2:\n        return 10\n    elif horas <= 5:\n        return 10 + (horas - 2) * 4\n    else:\n        return 22 + (horas - 5) * 3", [("tarifa_estacionamento(2)", "10"), ("tarifa_estacionamento(4)", "18"), ("tarifa_estacionamento(7)", "28", True)], "Calcular tarifa progressiva por faixas com elif"),
        "code4": ("tipo_triangulo(a, b, c)", "def tipo_triangulo(a, b, c):\n    pass", "def tipo_triangulo(a, b, c):\n    if a + b <= c or a + c <= b or b + c <= a:\n        return 'Inválido'\n    if a == b == c:\n        return 'Equilátero'\n    elif a == b or b == c or a == c:\n        return 'Isósceles'\n    return 'Escaleno'", [("tipo_triangulo(3, 3, 3)", "'Equilátero'"), ("tipo_triangulo(3, 4, 5)", "'Escaleno'"), ("tipo_triangulo(1, 2, 10)", "'Inválido'", True)], "Validar desigualdade triangular e classificar os lados"),
        "mc1": ("Em uma cadeia `if / elif / else`, se tanto a condição do `if` quanto a condição do `elif` forem verdadeiras para `x = 20`, o que acontece?", ["Apenas o bloco do `if` é executado e os demais são ignorados", "Ambos os blocos `if` e `elif` são executados em sequência", "Apenas o bloco do `elif` é executado por ser mais específico", "O Python lança um erro de ambiguidade", "O bloco `else` também é executado ao final"], 0, ["", "Confundiu if/elif encadeado com dois comandos if independentes", "Achou que o último ramo verdadeiro sobrescreve o primeiro", "Pensou que condições sobrepostas geram exceção", "Não compreendeu que else só roda quando todas as anteriores falham"]),
        "mc2": ("Qual erro o interpretador Python lança quando um bloco logo após `if x > 0:` não possui indentação?", ["IndentationError", "SyntaxWarning", "ValueError", "NameError", "KeyError"], 0, ["", "Achou que falta de indentação é apenas um aviso e não erro fatal", "Confundiu erro estrutural de código com erro de valor", "Confundiu indentação com variável não declarada", "Confundiu com chave inexistente em dicionário"]),
        "tf": ("Em Python, a indentação (geralmente 4 espaços por nível) não é apenas estética: ela delimita sintaticamente os blocos de código de estruturas como `if`, `elif` e `else`.", True),
        "fill": ("Em Python, a palavra-chave usada para testar uma condição intermediária adicional quando o `if` inicial resulta em `False` é ___.", ["elif"]),
        "match": [["if", "Inicia a estrutura condicional testando a primeira expressão"], ["elif", "Testa nova condição apenas se as anteriores forem falsas"], ["else", "Executa quando nenhuma condição anterior foi satisfeita"], ["and / or", "Conectivos lógicos para combinar expressões booleanas"]],
        "essay": ("Explique a diferença de comportamento entre usar vários `if` seguidos versus usar `if` seguido de `elif` e `else` ao classificar a nota de um estudante.", "Quando usamos vários blocos if independentes, todas as condições são testadas mesmo que uma anterior já tenha sido verdadeira. Ao usar if seguido de elif e else, os ramos são mutuamente exclusivos: assim que a primeira condição verdadeira é executada, os demais ramos são ignorados.", [["independente", "todos", "testad"], ["elif", "exclusiv", "ignora", "primeira"], ["else", "bloco", "condicao", "condição"]])
    },
    {
        "lessonId": "py_b3_l1",
        "subtopic": "Acumulando Valores em Sequência",
        "concept": "Laço for, função range(inicio, fim, passo) e variáveis acumuladoras",
        "code1": ("soma_multiplos_de_3(limite)", "def soma_multiplos_de_3(limite):\n    pass", "def soma_multiplos_de_3(limite):\n    total = 0\n    for n in range(0, limite + 1, 3):\n        total += n\n    return total", [("soma_multiplos_de_3(9)", "18"), ("soma_multiplos_de_3(15)", "45"), ("soma_multiplos_de_3(2)", "0", True)], "Acumular múltiplos em um intervalo usando range com passo"),
        "code2": ("fatorial_iterativo(n)", "def fatorial_iterativo(n):\n    pass", "def fatorial_iterativo(n):\n    prod = 1\n    for i in range(2, n + 1):\n        prod *= i\n    return prod", [("fatorial_iterativo(5)", "120"), ("fatorial_iterativo(0)", "1"), ("fatorial_iterativo(6)", "720", True)], "Usar acumulador multiplicativo com laço for"),
        "code3": ("contar_pares_intervalo(a, b)", "def contar_pares_intervalo(a, b):\n    pass", "def contar_pares_intervalo(a, b):\n    cont = 0\n    for x in range(a, b + 1):\n        if x % 2 == 0:\n            cont += 1\n    return cont", [("contar_pares_intervalo(1, 10)", "5"), ("contar_pares_intervalo(2, 6)", "3"), ("contar_pares_intervalo(3, 3)", "0", True)], "Contar elementos que satisfazem condição em um laço"),
        "code4": ("sequencia_regressiva(inicio, passo)", "def sequencia_regressiva(inicio, passo):\n    pass", "def sequencia_regressiva(inicio, passo):\n    return list(range(inicio, -1, -passo))", [("sequencia_regressiva(10, 2)", "[10, 8, 6, 4, 2, 0]"), ("sequencia_regressiva(9, 3)", "[9, 6, 3, 0]"), ("sequencia_regressiva(5, 2)", "[5, 3, 1]", True)], "Gerar progressão decrescente com passo negativo em range()"),
        "mc1": ("Quais números são gerados pela expressão `list(range(2, 10, 3))` em Python?", ["[2, 5, 8]", "[2, 5, 8, 10]", "[2, 3, 10]", "[3, 6, 9]", "[2, 6, 10]"], 0, ["", "Incluiu o limite superior 10, que é sempre exclusivo no range()", "Interpretou os argumentos como elementos literais da lista", "Confundiu range(2, 10, 3) com múltiplos de 3", "Somou 4 a cada passo em vez do passo 3"]),
        "mc2": ("Ao executar um laço `for i in range(5):`, qual é o primeiro e o último valor assumido pela variável `i`?", ["0 e 4", "1 e 5", "0 e 5", "1 e 4", "5 e 0"], 0, ["", "Esqueceu que range(n) começa em 0 e para em n - 1", "Incluiu o limite superior 5 na contagem", "Começou em 1 em vez de 0", "Inverteu a ordem da sequência"]),
        "tf": ("Na função `range(inicio, fim, passo)`, o valor `fim` nunca é incluído na sequência gerada.", True),
        "fill": ("Para inicializar corretamente uma variável acumuladora de produto antes de um laço `for`, devemos atribuir a ela o elemento neutro da multiplicação, que é ___.", ["1"]),
        "match": [["range(5)", "Gera 0, 1, 2, 3, 4"], ["range(1, 6)", "Gera 1, 2, 3, 4, 5"], ["range(0, 10, 2)", "Gera 0, 2, 4, 6, 8"], ["range(5, 0, -1)", "Gera 5, 4, 3, 2, 1"]],
        "essay": ("Explique os três parâmetros de `range(inicio, fim, passo)` e por que uma variável acumuladora de soma deve ser inicializada com `0` fora do laço `for`.", "Em range(inicio, fim, passo), inicio é o valor de partida inclusivo, fim é o limite de parada exclusivo e passo é o incremento entre termos. A variável acumuladora deve começar em 0 antes do laço para não ser zerada a cada iteração e porque 0 é o elemento neutro da soma.", [["inicio", "início", "fim", "exclusivo", "parada"], ["passo", "incremento"], ["acumulador", "zero", "0", "fora", "laço", "laco"]])
    },
    {
        "lessonId": "py_b3_l2",
        "subtopic": "Mapeamentos com dict",
        "concept": "Dicionários (chave: valor), acesso seguro com .get() e iteração com .items()",
        "code1": ("frequencia_palavras(lista)", "def frequencia_palavras(lista):\n    pass", "def frequencia_palavras(lista):\n    freq = {}\n    for p in lista:\n        freq[p] = freq.get(p, 0) + 1\n    return freq", [("frequencia_palavras(['a', 'b', 'a'])", "{'a': 2, 'b': 1}"), ("frequencia_palavras(['sol'])", "{'sol': 1}"), ("frequencia_palavras([])", "{}", True)], "Contar ocorrências usando dict.get(chave, 0)"),
        "code2": ("total_carrinho(precos, quantidades)", "def total_carrinho(precos, quantidades):\n    pass", "def total_carrinho(precos, quantidades):\n    return round(sum(precos.get(k, 0.0) * qtd for k, qtd in quantidades.items()), 2)", [("total_carrinho({'pao': 1.5, 'leite': 4.0}, {'pao': 4, 'leite': 1})", "10.0"), ("total_carrinho({'item': 10.0}, {'outro': 2})", "0.0"), ("total_carrinho({'a': 2.5, 'b': 3.0}, {'a': 2, 'b': 3})", "14.0", True)], "Cruzar dois dicionários com .items() e .get()"),
        "code3": ("inverter_dicionario(d)", "def inverter_dicionario(d):\n    pass", "def inverter_dicionario(d):\n    return {v: k for k, v in d.items()}", [("inverter_dicionario({'a': 1, 'b': 2})", "{1: 'a', 2: 'b'}"), ("inverter_dicionario({'BR': 'Brasil'})", "{'Brasil': 'BR'}"), ("inverter_dicionario({})", "{}", True)], "Inverter pares chave-valor de um dicionário"),
        "code4": ("aluno_maior_nota(notas)", "def aluno_maior_nota(notas):\n    pass", "def aluno_maior_nota(notas):\n    if not notas:\n        return None\n    return max(notas.items(), key=lambda par: par[1])[0]", [("aluno_maior_nota({'Ana': 9.0, 'Bia': 9.5})", "'Bia'"), ("aluno_maior_nota({'Leo': 7.0})", "'Leo'"), ("aluno_maior_nota({})", "None", True)], "Localizar a chave associada ao maior valor no dicionário"),
        "mc1": ("Dado `estoque = {'caneta': 10}`, o que acontece ao avaliar `estoque.get('lapis', 0)` versus `estoque['lapis']`?", ["`get` retorna `0` sem erro, enquanto `estoque['lapis']` lança `KeyError`", "Ambos retornam `0`", "Ambos lançam `KeyError`", "`get` adiciona `'lapis': 0` ao dicionário e `estoque['lapis']` retorna `None`", "`estoque['lapis']` retorna `0` e `get` lança `IndexError`"], 0, ["", "Achou que indexação direta com colchetes também tem valor padrão 0", "Desconhece que .get() evita KeyError retornando o padrão", "Confundiu .get() com .setdefault()", "Trocou os comportamentos e confundiu KeyError com IndexError"]),
        "mc2": ("Qual método de um dicionário `d` retorna pares `(chave, valor)` para percorrer em um laço `for k, v in ...:`?", ["d.items()", "d.keys()", "d.values()", "d.pairs()", "d.entries()"], 0, ["", "d.keys() retorna apenas as chaves", "d.values() retorna apenas os valores", "O método .pairs() não existe em dict do Python", "Confundiu com Object.entries() do JavaScript"]),
        "tf": ("As chaves de um dicionário em Python devem ser de tipos imutáveis (hashable), como `str`, `int` ou `tuple`, não sendo permitido usar uma `list` como chave.", True),
        "fill": ("Em Python, tentar acessar `d['chave_inexistente']` diretamente com colchetes lança a exceção ___.", ["KeyError", "keyerror"]),
        "match": [["d.keys()", "Visão das chaves do dicionário"], ["d.values()", "Visão dos valores armazenados"], ["d.items()", "Visão de tuplas (chave, valor)"], ["d.get(k, padrao)", "Busca segura sem lançar KeyError"]],
        "essay": ("Explique como o método `.get(chave, padrao)` evita exceções ao contar frequências em um dicionário e por que listas não podem ser usadas como chaves de `dict`.", "O método .get(chave, 0) retorna o valor associado à chave se ela existir ou 0 caso ainda não exista, evitando KeyError ao incrementar contadores. Listas não podem ser chaves porque são mutáveis e não possuem hash fixo.", [["get", "keyerror", "padrao", "padrão"], ["frequencia", "frequência", "contador", "0"], ["imutave", "imutáve", "mutave", "mutáve", "hash", "lista"]])
    },
    {
        "lessonId": "py_i1_l1",
        "subtopic": "Interpolação Moderna de Variáveis",
        "concept": "F-strings, especificadores de formato (:.2f, :04d) e métodos de string",
        "code1": ("formatar_percentual(parte, total)", "def formatar_percentual(parte, total):\n    pass", "def formatar_percentual(parte, total):\n    pct = (parte / total) * 100\n    return f'{pct:.1f}%'", [("formatar_percentual(1, 4)", "'25.0%'"), ("formatar_percentual(2, 3)", "'66.7%'"), ("formatar_percentual(3, 5)", "'60.0%'", True)], "Interpolar e arredondar percentual com :.1f em f-string"),
        "code2": ("codigo_matricula(ano, numero)", "def codigo_matricula(ano, numero):\n    pass", "def codigo_matricula(ano, numero):\n    return f'{ano}-{numero:04d}'", [("codigo_matricula(2026, 7)", "'2026-0007'"), ("codigo_matricula(2026, 125)", "'2026-0125'"), ("codigo_matricula(2025, 1000)", "'2025-1000'", True)], "Preencher inteiros com zeros à esquerda usando :04d"),
        "code3": ("normalizar_nome_completo(nome)", "def normalizar_nome_completo(nome):\n    pass", "def normalizar_nome_completo(nome):\n    partes = [p.capitalize() for p in nome.strip().split()]\n    return ' '.join(partes)", [("normalizar_nome_completo('  ana   silva ')", "'Ana Silva'"), ("normalizar_nome_completo('PEDRO COSTA')", "'Pedro Costa'"), ("normalizar_nome_completo('maria')", "'Maria'", True)], "Limpar espaços extras e capitalizar palavras"),
        "code4": ("etiqueta_alinhada(produto, valor)", "def etiqueta_alinhada(produto, valor):\n    pass", "def etiqueta_alinhada(produto, valor):\n    return f'{produto:<10}|R$ {valor:>6.2f}'", [("etiqueta_alinhada('Café', 8.5)", "'Café      |R$   8.50'"), ("etiqueta_alinhada('Pão', 12.0)", "'Pão       |R$  12.00'"), ("etiqueta_alinhada('Chá', 3.25)", "'Chá       |R$   3.25'", True)], "Alinhar texto à esquerda e números à direita em f-strings"),
        "mc1": ("Qual é o resultado da f-string `f'{3.14159:.2f} / {42:05d}'`?", ["'3.14 / 00042'", "'3.14159 / 42'", "'3.15 / 42000'", "'3.14 / 42   '", "Erro de formatação"], 0, ["", "Ignorou os especificadores após os dois-pontos", "Arredondou para cima indevidamente e pôs zeros à direita", "Confundiu preenchimento com zeros à esquerda com espaços à direita", "Achou que combinar dois especificadores na mesma f-string é inválido"]),
        "mc2": ("Para incluir um caractere de chave literal `{` dentro de uma f-string em Python, como devemos escrevê-lo?", ["Duplicando as chaves: `{{`", "Com barra invertida: `\\{`", "Com cifrão: `${`", "Não é possível exibir chaves em f-strings", "Usando `%{`"], 0, ["", "Em f-strings, barra invertida não escapa chaves delimitadoras", "Sintaxe de template strings de JavaScript, não Python", "Python suporta chaves literais duplicando-as", "Misturou formatação antiga % com f-strings"]),
        "tf": ("Em uma f-string do Python, qualquer expressão válida (como chamadas de função ou operações aritméticas) pode ser avaliada diretamente dentro das chaves `{}`.", True),
        "fill": ("Em uma f-string, o especificador `f'{valor:___}'` formata um número `float` exibindo exatamente duas casas decimais.", [".2f"]),
        "match": [[":.2f", "Formata float com 2 casas decimais"], [":04d", "Inteiro com pelo menos 4 dígitos e zeros à esquerda"], [":>10", "Alinha o valor à direita em um campo de 10 colunas"], [".strip()", "Remove espaços em branco nas extremidades"]],
        "essay": ("Explique como funcionam as f-strings em Python e compare sua legibilidade e recursos (como `:.2f` e `:04d`) com a concatenação manual usando `str()`.", "As f-strings, prefixadas por f antes das aspas, avaliam expressões entre chaves diretamente no texto e aceitam especificadores após dois-pontos, como :.2f para casas decimais e :04d para zeros à esquerda. Elas são mais legíveis, rápidas e menos propensas a erros que concatenar strings com + e str().", [["f-string", "chaves", "{}"], [".2f", "04d", "especificador", "casas decimais"], ["concatenacao", "concatenação", "str", "legibil"]])
    },
    {
        "lessonId": "py_i1_l2",
        "subtopic": "Manipulação e Criptografia Clássica",
        "concept": "Cifra de César, funções ord() e chr() e aritmética modular no alfabeto",
        "code1": ("cifra_cesar(texto, deslocamento)", "def cifra_cesar(texto, deslocamento):\n    pass", "def cifra_cesar(texto, deslocamento):\n    res = []\n    for c in texto:\n        if 'A' <= c <= 'Z':\n            res.append(chr((ord(c) - ord('A') + deslocamento) % 26 + ord('A')))\n        elif 'a' <= c <= 'z':\n            res.append(chr((ord(c) - ord('a') + deslocamento) % 26 + ord('a')))\n        else:\n            res.append(c)\n    return ''.join(res)", [("cifra_cesar('ABC', 3)", "'DEF'"), ("cifra_cesar('XYZ', 2)", "'ZAB'"), ("cifra_cesar('Oi, Z!', 1)", "'Pj, A!'", True)], "Implementar a Cifra de César preservando caixa e pontuação"),
        "code2": ("decifrar_cesar(cifrado, deslocamento)", "def decifrar_cesar(cifrado, deslocamento):\n    pass", "def decifrar_cesar(cifrado, deslocamento):\n    res = []\n    for c in cifrado:\n        if 'A' <= c <= 'Z':\n            res.append(chr((ord(c) - ord('A') - deslocamento) % 26 + ord('A')))\n        elif 'a' <= c <= 'z':\n            res.append(chr((ord(c) - ord('a') - deslocamento) % 26 + ord('a')))\n        else:\n            res.append(c)\n    return ''.join(res)", [("decifrar_cesar('DEF', 3)", "'ABC'"), ("decifrar_cesar('Bcd', 1)", "'Abc'"), ("decifrar_cesar('A', 1)", "'Z'", True)], "Reverter a Cifra de César usando deslocamento negativo módulo 26"),
        "code3": ("distancia_alfabetica(c1, c2)", "def distancia_alfabetica(c1, c2):\n    pass", "def distancia_alfabetica(c1, c2):\n    return (ord(c2.upper()) - ord(c1.upper())) % 26", [("distancia_alfabetica('A', 'D')", "3"), ("distancia_alfabetica('Z', 'B')", "2"), ("distancia_alfabetica('m', 'm')", "0", True)], "Calcular deslocamento circular entre duas letras"),
        "code4": ("rot13(texto)", "def rot13(texto):\n    pass", "def rot13(texto):\n    out = []\n    for c in texto:\n        if 'a' <= c <= 'z':\n            out.append(chr((ord(c) - 97 + 13) % 26 + 97))\n        elif 'A' <= c <= 'Z':\n            out.append(chr((ord(c) - 65 + 13) % 26 + 65))\n        else:\n            out.append(c)\n    return ''.join(out)", [("rot13('python')", "'clguba'"), ("rot13(rot13('Knowverse'))", "'Knowverse'"), ("rot13('ABC')", "'NOP'", True)], "Implementar a cifra simétrica ROT13"),
        "mc1": ("Na Cifra de César, por que usamos `% 26` na expressão `(ord(c) - ord('A') + k) % 26 + ord('A')`?", ["Para fazer o alfabeto dar a volta de `'Z'` para `'A'` sem sair do intervalo de letras", "Para converter letras minúsculas em maiúsculas", "Porque o código ASCII da letra `'A'` é 26", "Para remover espaços e sinais de pontuação da mensagem", "Para criptografar dígitos de 0 a 9 junto com as letras"], 0, ["", "A diferença entre maiúsculas e minúsculas em ASCII é 32, não módulo 26", "O código ASCII de 'A' é 65, enquanto 26 é o tamanho do alfabeto inglês", "Caracteres fora de A-Z devem ser filtrados antes com condicional", "Dígitos possuem 10 símbolos (0-9), não 26"]),
        "mc2": ("Sabendo que `ord('A')` vale `65`, qual é o retorno de `chr(ord('A') + 3)`?", ["'D'", "'C'", "68", "'E'", "TypeError"], 0, ["", "Contou 'A' como deslocamento 1 em vez de 0", "Esqueceu que chr() converte o inteiro 68 de volta em caractere", "Somou 4 posições em vez de 3", "ord() retorna int, que pode ser somado a 3 normalmente"]),
        "tf": ("Aplicar a cifra ROT13 (deslocamento 13 em um alfabeto de 26 letras) duas vezes seguidas sobre um texto restaura exatamente a mensagem original.", True),
        "fill": ("Em Python, a função embutida que retorna o código numérico Unicode/ASCII de um único caractere (como `65` para `'A'`) é ___().", ["ord"]),
        "match": [["ord('A')", "Retorna o inteiro 65"], ["chr(65)", "Retorna o caractere 'A'"], ["% 26", "Garante rotação cíclica nas 26 letras do alfabeto"], ["''.join(lista)", "Concatena lista de caracteres em uma única string"]],
        "essay": ("Explique passo a passo como as funções `ord()`, `chr()` e o operador `% 26` permitem deslocar uma letra maiúscula na Cifra de César mesmo quando o deslocamento ultrapassa a letra `'Z'`.", "Primeiro subtraímos ord('A') de ord(c) para obter um índice de 0 a 25. Em seguida somamos o deslocamento e aplicamos o módulo % 26 para que valores acima de 25 voltem ao início do alfabeto (0 = A). Por fim, somamos ord('A') e convertemos o código de volta para letra com chr().", [["ord", "65", "indice", "índice"], ["% 26", "modulo", "módulo", "volta", "ciclic"], ["chr", "caractere", "letra"]])
    },
    {
        "lessonId": "py_i2_l1",
        "subtopic": "Criação Concisa e Elegante de Listas",
        "concept": "List comprehensions com mapeamento e filtragem condicional",
        "code1": ("quadrados_pares(n)", "def quadrados_pares(n):\n    pass", "def quadrados_pares(n):\n    return [x ** 2 for x in range(1, n + 1) if x % 2 == 0]", [("quadrados_pares(6)", "[4, 16, 36]"), ("quadrados_pares(1)", "[]"), ("quadrados_pares(8)", "[4, 16, 36, 64]", True)], "Filtrar pares e elevar ao quadrado com list comprehension"),
        "code2": ("palavras_longas_maiusculas(palavras, min_tam)", "def palavras_longas_maiusculas(palavras, min_tam):\n    pass", "def palavras_longas_maiusculas(palavras, min_tam):\n    return [p.upper() for p in palavras if len(p) >= min_tam]", [("palavras_longas_maiusculas(['oi', 'python', 'sol'], 4)", "['PYTHON']"), ("palavras_longas_maiusculas(['a', 'bb', 'ccc'], 2)", "['BB', 'CCC']"), ("palavras_longas_maiusculas([], 3)", "[]", True)], "Transformar e filtrar strings em uma única compreensão"),
        "code3": ("achatamento_matriz(matriz)", "def achatamento_matriz(matriz):\n    pass", "def achatamento_matriz(matriz):\n    return [elem for linha in matriz for elem in linha]", [("achatamento_matriz([[1, 2], [3, 4]])", "[1, 2, 3, 4]"), ("achatamento_matriz([[10], [], [20]])", "[10, 20]"), ("achatamento_matriz([[1, 2, 3]])", "[1, 2, 3]", True)], "Achatar lista 2D usando compreensão com dois for"),
        "code4": ("rotular_paridade(nums)", "def rotular_paridade(nums):\n    pass", "def rotular_paridade(nums):\n    return ['par' if x % 2 == 0 else 'ímpar' for x in nums]", [("rotular_paridade([1, 2, 3])", "['ímpar', 'par', 'ímpar']"), ("rotular_paridade([0, 4])", "['par', 'par']"), ("rotular_paridade([])", "[]", True)], "Usar expressão ternária antes do for na list comprehension"),
        "mc1": ("Qual é a lista resultante da expressão `[x * 2 for x in range(5) if x % 2 == 1]`?", ["[2, 6]", "[0, 2, 4, 6, 8]", "[0, 4, 8]", "[1, 3]", "[2, 6, 10]"], 0, ["", "Ignorou o filtro if x % 2 == 1", "Filtrou os pares em vez dos ímpares", "Esqueceu de multiplicar por 2 na expressão inicial", "Incluiu o 5 no range(5), que vai apenas até 4"]),
        "mc2": ("Em uma list comprehension, qual é a diferença sintática entre filtrar elementos (descartando alguns) e transformar todos os elementos com operador ternário?", ["O filtro `if` fica após o `for`, enquanto o ternário `A if cond else B` fica antes do `for`", "O filtro `if` fica antes do `for` e o ternário fica no final", "Não é permitido usar `else` em list comprehensions", "Ambos usam a mesma posição após o `for`", "O filtro exige parênteses ao redor do `for`"], 0, ["", "Inverteu a posição da cláusula de filtragem com a expressão ternária", "É permitido usar if/else ternário na expressão à esquerda do for", "Colocar else após o for gera SyntaxError", "Parênteses externos criam uma expressão geradora (generator), não filtro"]),
        "tf": ("Toda list comprehension `[expr for item in iteravel if cond]` pode ser reescrita de forma equivalente como um laço `for` tradicional com `if` e `.append()`, produzindo a mesma lista.", True),
        "fill": ("Na compreensão `[x ** 2 for x in numeros ___ x > 0]`, a palavra-chave que filtra apenas os números positivos é ___.", ["if"]),
        "match": [["[x*2 for x in L]", "Mapeia cada elemento para o seu dobro"], ["[x for x in L if x > 0]", "Filtra apenas os elementos positivos"], ["{x: x**2 for x in L}", "Dict comprehension mapeando número ao quadrado"], ["{x % 3 for x in L}", "Set comprehension com restos únicos"]],
        "essay": ("Explique a estrutura de uma list comprehension `[expressao for item in iteravel if condicao]` e indique quando ela é preferível (ou quando deve ser evitada) em relação ao laço `for` tradicional.", "Uma list comprehension combina a expressão de transformação, a iteração for sobre um iterável e um filtro condicional if opcional em uma única linha concisa. Ela é preferível para criar listas simples de forma legível, mas deve ser evitada quando há múltiplos níveis aninhados ou efeitos colaterais complexos que prejudicam a clareza.", [["expressao", "expressão", "for", "iteravel", "iterável"], ["if", "filtro", "condicao", "condição"], ["legibil", "concis", "complex", "aninhad"]])
    },
    {
        "lessonId": "py_i2_l2",
        "subtopic": "Resiliência e Proteção contra Falhas",
        "concept": "Tratamento de exceções com try, except, else, finally e raise",
        "code1": ("divisao_segura(a, b, padrao=0.0)", "def divisao_segura(a, b, padrao=0.0):\n    pass", "def divisao_segura(a, b, padrao=0.0):\n    try:\n        return a / b\n    except ZeroDivisionError:\n        return padrao", [("divisao_segura(10, 2)", "5.0"), ("divisao_segura(10, 0, -1.0)", "-1.0"), ("divisao_segura(7, 0)", "0.0", True)], "Capturar ZeroDivisionError e retornar valor seguro"),
        "code2": ("converter_inteiro_seguro(texto, padrao=None)", "def converter_inteiro_seguro(texto, padrao=None):\n    pass", "def converter_inteiro_seguro(texto, padrao=None):\n    try:\n        return int(texto)\n    except (ValueError, TypeError):\n        return padrao", [("converter_inteiro_seguro('42')", "42"), ("converter_inteiro_seguro('abc', 0)", "0"), ("converter_inteiro_seguro(None, -1)", "-1", True)], "Tratar múltiplas exceções específicas em uma tupla"),
        "code3": ("soma_validos(lista_textos)", "def soma_validos(lista_textos):\n    pass", "def soma_validos(lista_textos):\n    total = 0\n    for item in lista_textos:\n        try:\n            total += int(item)\n        except (ValueError, TypeError):\n            continue\n    return total", [("soma_validos(['10', 'erro', '20'])", "30"), ("soma_validos(['x', 'y'])", "0"), ("soma_validos(['5', '-2', '3.5', '7'])", "10", True)], "Continuar processamento ignorando entradas inválidas com try/except"),
        "code4": ("calcular_raiz_segura(x)", "def calcular_raiz_segura(x):\n    pass", "def calcular_raiz_segura(x):\n    try:\n        if x < 0:\n            raise ValueError('Número negativo')\n        return round(x ** 0.5, 4)\n    except ValueError:\n        return 'ERRO_DOMINIO'", [("calcular_raiz_segura(9)", "3.0"), ("calcular_raiz_segura(0)", "0.0"), ("calcular_raiz_segura(-4)", "'ERRO_DOMINIO'", True)], "Lançar e tratar ValueError para entrada fora do domínio real"),
        "mc1": ("Em uma estrutura `try / except / else / finally`, quando o bloco `finally` é executado?", ["Sempre, independentemente de uma exceção ter ocorrido ou não", "Apenas quando nenhuma exceção ocorre no bloco `try`", "Apenas quando uma exceção é capturada pelo `except`", "Somente quando uma exceção NÃO é tratada pelo `except`", "Nunca se houver um comando `return` dentro do `try`"], 0, ["", "Confundiu o bloco finally com o bloco else do try", "Esse é o papel do bloco except, não do finally", "finally executa tanto com exceção tratada quanto sem exceção", "Mesmo antes de um return no try, o bloco finally é garantidamente executado"]),
        "mc2": ("Por que usar `except Exception:` ou `except:` genérico sem especificar o tipo do erro é considerado uma má prática?", ["Porque mascara erros inesperados de programação (como `NameError` ou `TypeError`) dificultando a depuração", "Porque o Python 3 proíbe mais de um bloco `except` quando não há tipo", "Porque torna o bloco `try` dez vezes mais lento mesmo sem erro", "Porque impede o uso do bloco `finally`", "Porque captura apenas erros de sintaxe (`SyntaxError`)"], 0, ["", "Python permite except específico seguido de genérico", "O custo de try sem exceção em Python é mínimo", "finally funciona normalmente com qualquer except", "SyntaxError na análise do próprio arquivo não é capturado em tempo de execução local"]),
        "tf": ("Em um bloco `try / except / else`, o código dentro da cláusula `else` só é executado se o bloco `try` terminar sem lançar nenhuma exceção.", True),
        "fill": ("A instrução do Python utilizada para sinalizar/lançar explicitamente uma exceção (por exemplo, `___ ValueError('Idade inválida')`) é ___.", ["raise"]),
        "match": [["ZeroDivisionError", "Divisão ou módulo por zero"], ["ValueError", "Tipo correto, porém conteúdo inválido (ex.: int('abc'))"], ["IndexError", "Índice fora dos limites de uma sequência"], ["finally", "Bloco executado sempre para limpeza de recursos"]],
        "essay": ("Explique o papel de cada bloco na estrutura `try / except / else / finally` e por que devemos capturar exceções específicas (como `ValueError`) em vez de um `except` genérico.", "O bloco try envolve o código que pode falhar; except trata a exceção caso ela ocorra; else executa apenas se o try teve sucesso sem erros; e finally roda sempre para liberar recursos. Capturar exceções específicas evita esconder bugs acidentais como NameError ou TypeError.", [["try", "except", "falha", "erro"], ["else", "finally", "sempre", "recurso"], ["especific", "específic", "valueerror", "mascara", "bug"]])
    },
    {
        "lessonId": "py_i3_l1",
        "subtopic": "Estratégia de Divisão e Conquista",
        "concept": "Algoritmo de Busca Binária, pré-condição de ordenação e complexidade O(log n)",
        "code1": ("busca_binaria(lista, alvo)", "def busca_binaria(lista, alvo):\n    pass", "def busca_binaria(lista, alvo):\n    esq, dir = 0, len(lista) - 1\n    while esq <= dir:\n        meio = (esq + dir) // 2\n        if lista[meio] == alvo:\n            return meio\n        elif lista[meio] < alvo:\n            esq = meio + 1\n        else:\n            dir = meio - 1\n    return -1", [("busca_binaria([2, 5, 8, 12, 16], 12)", "3"), ("busca_binaria([2, 5, 8, 12, 16], 7)", "-1"), ("busca_binaria([10], 10)", "0", True)], "Implementar busca binária clássica retornando índice ou -1"),
        "code2": ("contar_comparacoes_binaria(lista, alvo)", "def contar_comparacoes_binaria(lista, alvo):\n    pass", "def contar_comparacoes_binaria(lista, alvo):\n    esq, dir = 0, len(lista) - 1\n    passos = 0\n    while esq <= dir:\n        passos += 1\n        meio = (esq + dir) // 2\n        if lista[meio] == alvo:\n            return passos\n        elif lista[meio] < alvo:\n            esq = meio + 1\n        else:\n            dir = meio - 1\n    return passos", [("contar_comparacoes_binaria([1, 2, 3, 4, 5, 6, 7], 4)", "1"), ("contar_comparacoes_binaria([1, 2, 3, 4, 5, 6, 7], 7)", "3"), ("contar_comparacoes_binaria([1, 2, 3], 9)", "2", True)], "Contabilizar o número de divisões ao meio na busca binária"),
        "code3": ("posicao_insercao_ordenada(lista, x)", "def posicao_insercao_ordenada(lista, x):\n    pass", "def posicao_insercao_ordenada(lista, x):\n    esq, dir = 0, len(lista)\n    while esq < dir:\n        meio = (esq + dir) // 2\n        if lista[meio] < x:\n            esq = meio + 1\n        else:\n            dir = meio\n    return esq", [("posicao_insercao_ordenada([1, 3, 5, 6], 5)", "2"), ("posicao_insercao_ordenada([1, 3, 5, 6], 2)", "1"), ("posicao_insercao_ordenada([1, 3, 5, 6], 7)", "4", True)], "Encontrar o primeiro índice onde x pode ser inserido mantendo a ordem"),
        "code4": ("raiz_inteira_binaria(n)", "def raiz_inteira_binaria(n):\n    pass", "def raiz_inteira_binaria(n):\n    esq, dir = 0, n\n    ans = 0\n    while esq <= dir:\n        m = (esq + dir) // 2\n        if m * m <= n:\n            ans = m\n            esq = m + 1\n        else:\n            dir = m - 1\n    return ans", [("raiz_inteira_binaria(16)", "4"), ("raiz_inteira_binaria(20)", "4"), ("raiz_inteira_binaria(0)", "0", True)], "Aplicar busca binária no espaço de resposta para achar piso da raiz"),
        "mc1": ("Em uma lista ordenada com $1.024$ elementos ($2^{10}$), qual é a ordem de grandeza do número máximo de comparações que a busca binária realiza no pior caso?", ["Cerca de 10 a 11 comparações", "Exatamente 512 comparações", "Exatamente 1.024 comparações", "Apenas 1 comparação sempre", "100 comparações"], 0, ["", "Dividiu por 2 apenas uma vez em vez de aplicar log₂(1024) = 10", "Confundiu com a complexidade O(n) da busca linear", "1 comparação ocorre apenas no melhor caso quando o alvo está exatamente no meio", "Calculou raiz ou percentual em vez de logaritmo na base 2"]),
        "mc2": ("Qual pré-condição é indispensável para que a busca binária funcione corretamente sobre uma sequência?", ["A sequência deve estar previamente ordenada", "A sequência deve conter apenas números pares", "O tamanho da lista deve ser obrigatoriamente uma potência de 2", "Não pode haver números negativos na lista", "O elemento buscado precisa estar presente na lista"], 0, ["", "A paridade dos elementos é irrelevante para a comparação de ordem", "A divisão inteira (esq + dir) // 2 funciona para qualquer tamanho n", "Números negativos podem ser comparados normalmente com < e >", "Quando o elemento não está presente, o laço encerra com esq > dir e retorna -1"]),
        "tf": ("Na busca binária, quando `lista[meio] < alvo` em uma lista em ordem crescente, podemos descartar toda a metade esquerda e atualizar `esq = meio + 1`.", True),
        "fill": ("A complexidade de tempo de pior caso da busca binária em uma lista ordenada de $n$ elementos é denotada em notação assintótica como O(___).", ["log n", "log(n)", "log2 n", "log2(n)"]),
        "match": [["Pré-condição da busca binária", "Vetor ordenado em ordem monotônica"], ["meio = (esq + dir) // 2", "Cálculo do índice central da janela atual"], ["O(log n)", "Crescimento logarítmico: dobra n e soma 1 passo"], ["O(n)", "Complexidade da busca sequencial (linear)"]],
        "essay": ("Explique por que a busca binária exige que a lista esteja ordenada e por que sua complexidade $O(\\log n)$ é drasticamente mais rápida que a busca linear $O(n)$ para listas grandes.", "A busca binária compara o alvo com o elemento central e descarta metade da lista a cada passo; isso só é válido se a lista estiver ordenada para garantir de que lado o alvo pode estar. Como o espaço de busca cai pela metade a cada passo, o número máximo de passos cresce com log2(n), exigindo cerca de 20 passos para 1 milhão de itens contra 1 milhão na busca linear.", [["ordenad", "metade", "meio", "descarta"], ["log", "metade", "divis"], ["linear", "comparac", "comparação", "passos"]])
    },
    {
        "lessonId": "py_a1_l1",
        "subtopic": "Modelagem Orientada a Objetos",
        "concept": "Classes, construtor __init__, referência self, atributos e métodos",
        "code1": ("testar_conta(deposito, saque)", "class Conta:\n    def __init__(self, saldo=0):\n        pass\n\ndef testar_conta(deposito, saque):\n    pass", "class Conta:\n    def __init__(self, saldo=0):\n        self.saldo = saldo\n    def depositar(self, v):\n        self.saldo += v\n    def sacar(self, v):\n        if v <= self.saldo:\n            self.saldo -= v\n            return True\n        return False\n\ndef testar_conta(deposito, saque):\n    c = Conta(100)\n    c.depositar(deposito)\n    ok = c.sacar(saque)\n    return (ok, c.saldo)", [("testar_conta(50, 30)", "(True, 120)"), ("testar_conta(0, 150)", "(False, 100)"), ("testar_conta(20, 120)", "(True, 0)", True)], "Modelar classe Conta com estado encapsulado em self.saldo"),
        "code2": ("area_e_perimetro_ret(larg, alt)", "class Retangulo:\n    def __init__(self, larg, alt):\n        pass\n\ndef area_e_perimetro_ret(larg, alt):\n    pass", "class Retangulo:\n    def __init__(self, larg, alt):\n        self.larg = larg\n        self.alt = alt\n    def area(self):\n        return self.larg * self.alt\n    def perimetro(self):\n        return 2 * (self.larg + self.alt)\n\ndef area_e_perimetro_ret(larg, alt):\n    r = Retangulo(larg, alt)\n    return (r.area(), r.perimetro())", [("area_e_perimetro_ret(3, 4)", "(12, 14)"), ("area_e_perimetro_ret(5, 5)", "(25, 20)"), ("area_e_perimetro_ret(2, 10)", "(20, 24)", True)], "Definir construtor e métodos de cálculo geométrico"),
        "code3": ("repr_produto(nome, preco)", "class Produto:\n    def __init__(self, nome, preco):\n        pass\n\ndef repr_produto(nome, preco):\n    pass", "class Produto:\n    def __init__(self, nome, preco):\n        self.nome = nome\n        self.preco = preco\n    def __str__(self):\n        return f'{self.nome}: R$ {self.preco:.2f}'\n\ndef repr_produto(nome, preco):\n    return str(Produto(nome, preco))", [("repr_produto('Livro', 45.0)", "'Livro: R$ 45.00'"), ("repr_produto('Mouse', 89.9)", "'Mouse: R$ 89.90'"), ("repr_produto('Chá', 5)", "'Chá: R$ 5.00'", True)], "Implementar método especial __str__ para exibição amigável"),
        "code4": ("contador_passos(n_inc, n_reset)", "class Contador:\n    pass\n\ndef contador_passos(n_inc, n_reset):\n    pass", "class Contador:\n    def __init__(self):\n        self.valor = 0\n    def incrementar(self):\n        self.valor += 1\n    def zerar(self):\n        self.valor = 0\n\ndef contador_passos(n_inc, n_reset):\n    c = Contador()\n    for _ in range(n_inc):\n        c.incrementar()\n    if n_reset:\n        c.zerar()\n        c.incrementar()\n    return c.valor", [("contador_passos(4, False)", "4"), ("contador_passos(4, True)", "1"), ("contador_passos(0, False)", "0", True)], "Gerenciar transições de estado em instância de classe"),
        "mc1": ("Qual é o papel do primeiro parâmetro `self` nos métodos de instância de uma classe em Python?", ["Referenciar a própria instância que chamou o método, permitindo acessar seus atributos e outros métodos", "Criar uma cópia estática da classe na memória", "Tornar todos os atributos privados automaticamente", "Substituir o método `__init__` quando não há parâmetros", "Importar módulos externos automaticamente"], 0, ["", "self aponta para o objeto instanciado atual, não duplica a classe", "Visibilidade privada por convenção usa prefixo _ ou __, não self", "__init__ continua sendo o inicializador que recebe self", "Importação é feita com import, sem relação com self"]),
        "mc2": ("Quando o método especial `__init__` de uma classe é executado automaticamente?", ["No momento em que uma nova instância da classe é criada (ex.: `obj = MinhaClasse()`)", "Apenas quando o programa inteiro termina para liberar memória", "Somente quando chamamos `obj.__init__()` manualmente após criar o objeto", "Quando a classe é importada pela primeira vez, mesmo sem criar objetos", "Toda vez que qualquer método do objeto é chamado"], 0, ["", "Finalização de objeto está ligada a __del__, não __init__", "O Python invoca __init__ automaticamente na instanciação", "Na definição da classe o corpo da classe é lido, mas __init__ só roda ao instanciar", "Outros métodos não reexecutam __init__ automaticamente"]),
        "tf": ("Duas instâncias distintas da mesma classe (`a = Conta(100)` e `b = Conta(200)`) possuem seus próprios atributos de instância independentes (`a.saldo` e `b.saldo`).", True),
        "fill": ("Em Python, o método especial (dunder method) chamado automaticamente para inicializar os atributos de um novo objeto é `def ___(self, ...):`.", ["__init__"]),
        "match": [["class", "Palavra-chave que define um novo tipo/molde de objetos"], ["__init__", "Método inicializador dos atributos da instância"], ["self", "Referência explícita à instância atual do objeto"], ["__str__", "Método especial que define a conversão do objeto para str()"]],
        "essay": ("Explique a diferença entre uma classe e uma instância (objeto) em Programação Orientada a Objetos e descreva como `self` e `__init__` atuam na criação de objetos em Python.", "A classe é o molde ou definição abstrata que especifica quais atributos e métodos os objetos terão, enquanto a instância é o objeto concreto criado na memória a partir da classe. Ao instanciar, o Python chama __init__ passando a nova instância em self para inicializar seus atributos individuais.", [["classe", "molde", "modelo"], ["instancia", "instância", "objeto", "concreto", "memoria", "memória"], ["__init__", "self", "atribut"]])
    },
    {
        "lessonId": "py_a1_l2",
        "subtopic": "Reaproveitamento e Especialização de Classes",
        "concept": "Herança, sobrescrita de métodos (override), polimorfismo e super()",
        "code1": ("calcular_pagamento_gerente(salario, bonus)", "class Funcionario:\n    def __init__(self, salario):\n        self.salario = salario\n    def pagamento(self):\n        return self.salario\n\nclass Gerente(Funcionario):\n    pass\n\ndef calcular_pagamento_gerente(salario, bonus):\n    pass", "class Funcionario:\n    def __init__(self, salario):\n        self.salario = salario\n    def pagamento(self):\n        return self.salario\n\nclass Gerente(Funcionario):\n    def __init__(self, salario, bonus):\n        super().__init__(salario)\n        self.bonus = bonus\n    def pagamento(self):\n        return super().pagamento() + self.bonus\n\ndef calcular_pagamento_gerente(salario, bonus):\n    g = Gerente(salario, bonus)\n    return g.pagamento()", [("calcular_pagamento_gerente(5000, 1500)", "6500"), ("calcular_pagamento_gerente(3000, 0)", "3000"), ("calcular_pagamento_gerente(4200, 800)", "5000", True)], "Usar super().__init__() e estender método da superclasse"),
        "code2": ("areas_polimorficas(lado_q, larg_r, alt_r)", "class Forma:\n    def area(self):\n        return 0\n\ndef areas_polimorficas(lado_q, larg_r, alt_r):\n    pass", "class Forma:\n    def area(self):\n        return 0\n\nclass Quadrado(Forma):\n    def __init__(self, lado):\n        self.lado = lado\n    def area(self):\n        return self.lado ** 2\n\nclass Retangulo(Forma):\n    def __init__(self, larg, alt):\n        self.larg = larg\n        self.alt = alt\n    def area(self):\n        return self.larg * self.alt\n\ndef areas_polimorficas(lado_q, larg_r, alt_r):\n    formas = [Quadrado(lado_q), Retangulo(larg_r, alt_r)]\n    return [f.area() for f in formas]", [("areas_polimorficas(4, 3, 5)", "[16, 15]"), ("areas_polimorficas(2, 2, 2)", "[4, 4]"), ("areas_polimorficas(5, 10, 2)", "[25, 20]", True)], "Aplicar polimorfismo chamando o mesmo método em subclasses distintas"),
        "code3": ("verificar_heranca()", "class Animal:\n    pass\nclass Cachorro(Animal):\n    pass\n\ndef verificar_heranca():\n    pass", "class Animal:\n    pass\nclass Cachorro(Animal):\n    pass\n\ndef verificar_heranca():\n    rex = Cachorro()\n    return (isinstance(rex, Cachorro), isinstance(rex, Animal), issubclass(Cachorro, Animal))", [("verificar_heranca()", "(True, True, True)"), ("verificar_heranca()[0]", "True"), ("len(verificar_heranca())", "3", True)], "Verificar relações de tipo com isinstance e issubclass"),
        "code4": ("descrever_veiculo_eletrico(marca, bateria)", "class Veiculo:\n    def __init__(self, marca):\n        self.marca = marca\n    def info(self):\n        return f'Marca: {self.marca}'\n\nclass Eletrico(Veiculo):\n    pass\n\ndef descrever_veiculo_eletrico(marca, bateria):\n    pass", "class Veiculo:\n    def __init__(self, marca):\n        self.marca = marca\n    def info(self):\n        return f'Marca: {self.marca}'\n\nclass Eletrico(Veiculo):\n    def __init__(self, marca, bateria):\n        super().__init__(marca)\n        self.bateria = bateria\n    def info(self):\n        return f'{super().info()} | {self.bateria} kWh'\n\ndef descrever_veiculo_eletrico(marca, bateria):\n    return Eletrico(marca, bateria).info()", [("descrever_veiculo_eletrico('BYD', 60)", "'Marca: BYD | 60 kWh'"), ("descrever_veiculo_eletrico('Tesla', 75)", "'Marca: Tesla | 75 kWh'"), ("descrever_veiculo_eletrico('Volvo', 40)", "'Marca: Volvo | 40 kWh'", True)], "Encadear super().info() na sobrescrita de método"),
        "mc1": ("Para que serve a chamada `super().__init__(...)` dentro do construtor de uma subclasse em Python?", ["Para delegar à superclasse a inicialização dos atributos herdados, evitando duplicar código", "Para apagar os atributos da classe mãe e recriá-los do zero", "Para transformar a subclasse na superclasse permanentemente", "Para impedir que a subclasse adicione novos atributos próprios", "Para tornar todos os métodos da classe mãe estáticos"], 0, ["", "super() preserva e inicializa os atributos herdados, não os apaga", "A subclasse mantém seu próprio tipo especializado", "Após super().__init__(), a subclasse pode definir livremente novos atributos em self", "super() trabalha com métodos de instância e da hierarquia MRO, não converte em estáticos"]),
        "mc2": ("Se `class Gato(Animal):` define seu próprio método `emitir_som(self)` com o mesmo nome da classe `Animal`, como se chama esse mecanismo?", ["Sobrescrita de método (method overriding), base do polimorfismo", "Sobrecarga de operadores numéricos", "Encapsulamento estático privado", "Recursão infinita de classe", "Tipagem fraca implícita"], 0, ["", "Sobrecarga de operadores usa métodos como __add__, não redefinição simples em subclasse", "Encapsulamento trata de ocultar estado interno, não especializar comportamento herdado", "Só haveria recursão se o método chamasse a si mesmo sem condição de parada", "Polimorfismo por herança não tem relação com tipagem fraca"]),
        "tf": ("Se `cachorro` é uma instância de `Cachorro`, e `Cachorro` herda de `Animal`, então `isinstance(cachorro, Animal)` avalia para `True`.", True),
        "fill": ("Em Python, a função embutida usada dentro de uma subclasse para acessar métodos da superclasse é ___().", ["super"]),
        "match": [["class Filha(Mae):", "Sintaxe de herança em Python"], ["super().__init__()", "Invoca o construtor da superclasse"], ["Polimorfismo", "Mesma interface de método com comportamentos específicos por subclasse"], ["isinstance(obj, Classe)", "Verifica se obj pertence à Classe ou a uma subclasse dela"]],
        "essay": ("Explique os conceitos de herança e polimorfismo em Python e mostre por que o uso de `super()` no `__init__` da subclasse melhora a manutenção do código.", "A herança permite que uma subclasse reaproveite atributos e métodos de uma superclasse, especializando apenas o que muda. O polimorfismo permite que diferentes subclasses respondam ao mesmo nome de método de maneira própria. Usar super().__init__() centraliza a inicialização da classe base, evitando repetição e facilitando alterações futuras.", [["heranca", "herança", "subclasse", "superclasse", "reaproveit"], ["polimorfismo", "metodo", "método", "sobrescrita"], ["super", "__init__", "manutencao", "manutenção", "duplic"]])
    },
    {
        "lessonId": "py_a2_l1",
        "subtopic": "Iteradores Sob Demanda",
        "concept": "Funções geradoras (yield), avaliação preguiçosa (lazy evaluation) e next()",
        "code1": ("lista_pares_gerador(n)", "def gerar_pares(n):\n    pass\n\ndef lista_pares_gerador(n):\n    return list(gerar_pares(n))", "def gerar_pares(n):\n    for x in range(0, n + 1, 2):\n        yield x\n\ndef lista_pares_gerador(n):\n    return list(gerar_pares(n))", [("lista_pares_gerador(6)", "[0, 2, 4, 6]"), ("lista_pares_gerador(1)", "[0]"), ("lista_pares_gerador(10)", "[0, 2, 4, 6, 8, 10]", True)], "Criar função geradora com yield para produzir pares sob demanda"),
        "code2": ("primeiros_fibonacci(qtd)", "def gen_fib():\n    pass\n\ndef primeiros_fibonacci(qtd):\n    pass", "def gen_fib():\n    a, b = 0, 1\n    while True:\n        yield a\n        a, b = b, a + b\n\ndef primeiros_fibonacci(qtd):\n    g = gen_fib()\n    return [next(g) for _ in range(qtd)]", [("primeiros_fibonacci(5)", "[0, 1, 1, 2, 3]"), ("primeiros_fibonacci(1)", "[0]"), ("primeiros_fibonacci(7)", "[0, 1, 1, 2, 3, 5, 8]", True)], "Consumir gerador infinito com next() exatamente qtd vezes"),
        "code3": ("soma_quadrados_lazy(n)", "def soma_quadrados_lazy(n):\n    pass", "def soma_quadrados_lazy(n):\n    return sum(x * x for x in range(1, n + 1))", [("soma_quadrados_lazy(3)", "14"), ("soma_quadrados_lazy(4)", "30"), ("soma_quadrados_lazy(10)", "385", True)], "Usar generator expression dentro de sum() sem alocar lista em memória"),
        "code4": ("filtrar_positivos_gen(dados)", "def gen_positivos(dados):\n    pass\n\ndef filtrar_positivos_gen(dados):\n    return tuple(gen_positivos(dados))", "def gen_positivos(dados):\n    for item in dados:\n        if item > 0:\n            yield item\n\ndef filtrar_positivos_gen(dados):\n    return tuple(gen_positivos(dados))", [("filtrar_positivos_gen([-2, 3, 0, 5])", "(3, 5)"), ("filtrar_positivos_gen([-1, -5])", "()"), ("filtrar_positivos_gen([1, 2, 3])", "(1, 2, 3)", True)], "Pausar e retomar estado de filtragem com yield"),
        "mc1": ("Qual é a principal diferença entre `return` e `yield` dentro de uma função em Python?", ["`yield` transforma a função em um gerador que produz um valor e pausa seu estado local, enquanto `return` encerra definitivamente a execução", "`yield` calcula todos os valores de uma vez e retorna uma `list`, enquanto `return` devolve uma tupla", "`yield` só pode ser executado uma única vez por função", "`return` economiza mais memória RAM que `yield` em sequências de milhões de itens", "Não há diferença: `yield` é apenas um apelido para `return`"], 0, ["", "Quem aloca toda a lista de uma vez é uma função comum ou list comprehension, não yield", "Uma função geradora pode executar yield inúmeras vezes dentro de um laço", "yield economiza memória por gerar um item por vez (avaliação preguiçosa)", "yield retorna um objeto generator pausável, totalmente diferente de return"]),
        "mc2": ("Quando todos os elementos de um gerador `g` já foram consumidos e chamamos `next(g)` mais uma vez sem valor padrão, qual exceção é lançada?", ["StopIteration", "IndexError", "GeneratorExit", "EOFError", "ValueError"], 0, ["", "IndexError ocorre em acesso posicional por índice, o que geradores não possuem", "GeneratorExit está ligado ao método .close() do gerador", "EOFError ocorre na leitura de input() sem dados", "O protocolo de iteradores do Python usa StopIteration para sinalizar o fim"]),
        "tf": ("Um gerador produz valores sob demanda (lazy evaluation), mantendo consumo de memória constante $O(1)$ mesmo ao representar sequências de milhões de elementos.", True),
        "fill": ("Em Python, a palavra-chave que transforma uma função em uma função geradora capaz de pausar e retomar sua execução é ___.", ["yield"]),
        "match": [["yield", "Produz um valor e congela o frame da função geradora"], ["next(g)", "Solicita o próximo item do gerador g"], ["StopIteration", "Exceção que sinaliza o esgotamento do iterador"], ["(x**2 for x in R)", "Expressão geradora (generator expression)"]],
        "essay": ("Explique o conceito de avaliação preguiçosa (lazy evaluation) com `yield` em Python e por que um gerador consome muito menos memória que uma lista ao processar um arquivo de 10 milhões de linhas.", "Com yield, a função torna-se um gerador que produz um elemento por vez sob demanda, pausando sua execução entre chamadas de next() e retomando do mesmo ponto. Ao processar 10 milhões de linhas, o gerador mantém apenas a linha atual na memória em vez de carregar todas as 10 milhões de linhas simultaneamente em uma lista.", [["yield", "pausa", "retoma", "demanda", "lazy", "preguicos", "preguiços"], ["memoria", "memória", "lista", "simultaneamente", "vez"], ["next", "iterador", "gerador"]])
    },
    {
        "lessonId": "py_a2_l2",
        "subtopic": "Casamento de Padrões em Textos",
        "concept": "Módulo re, metacaracteres (\\d, \\w, +, *), re.findall, re.search e re.sub",
        "code1": ("extrair_inteiros(texto)", "import re\ndef extrair_inteiros(texto):\n    pass", "import re\ndef extrair_inteiros(texto):\n    return [int(x) for x in re.findall(r'\\d+', texto)]", [("extrair_inteiros('Item 12 custa 45 reais')", "[12, 45]"), ("extrair_inteiros('Sem numeros')", "[]"), ("extrair_inteiros('2026-11-03')", "[2026, 11, 3]", True)], "Extrair todas as sequências de dígitos com re.findall(r'\\d+')"),
        "code2": ("validar_cep(cep)", "import re\ndef validar_cep(cep):\n    pass", "import re\ndef validar_cep(cep):\n    return bool(re.fullmatch(r'\\d{5}-?\\d{3}', cep))", [("validar_cep('01310-100')", "True"), ("validar_cep('01310100')", "True"), ("validar_cep('0131-100')", "False", True)], "Validar formato exato de CEP com quantificadores e opcional ?"),
        "code3": ("ocultar_digitos(texto)", "import re\ndef ocultar_digitos(texto):\n    pass", "import re\ndef ocultar_digitos(texto):\n    return re.sub(r'\\d', '#', texto)", [("ocultar_digitos('Senha 123')", "'Senha ###'"), ("ocultar_digitos('CPF 99')", "'CPF ##'"), ("ocultar_digitos('ABC')", "'ABC'", True)], "Substituir padrões em texto com re.sub()"),
        "code4": ("extrair_dominios_email(texto)", "import re\ndef extrair_dominios_email(texto):\n    pass", "import re\ndef extrair_dominios_email(texto):\n    return re.findall(r'[\\w.+-]+@([\\w-]+\\.[\\w.-]+)', texto)", [("extrair_dominios_email('Contato: a@usp.br e b@gov.br')", "['usp.br', 'gov.br']"), ("extrair_dominios_email('nenhum email')", "[]"), ("extrair_dominios_email('x@knowverse.edu')", "['knowverse.edu']", True)], "Usar grupo de captura (...) em re.findall para extrair domínios"),
        "mc1": ("Qual é o retorno de `re.findall(r'\\d+', 'A12 B3 C456')` em Python?", ["['12', '3', '456']", "['1', '2', '3', '4', '5', '6']", "['A12', 'B3', 'C456']", "[12, 3, 456]", "'123456'"], 0, ["", "Esqueceu que o quantificador + agrupa um ou mais dígitos consecutivos", "O padrão \\d+ casa apenas dígitos, não as letras A, B e C", "re.findall retorna lista de strings, não converte para int automaticamente", "re.findall retorna uma lista de ocorrências, não uma única string concatenada"]),
        "mc2": ("Por que é recomendado usar raw strings `r'\\d+'` ao escrever expressões regulares em Python?", ["Para que o Python não interprete a barra invertida `\\` como escape de string antes de passá-la ao motor de regex", "Porque o módulo `re` lança `SyntaxError` se a string não tiver o prefixo `r`", "Para tornar a busca insensível a maiúsculas e minúsculas", "Para converter automaticamente o resultado da busca em números inteiros", "Para limitar a busca apenas à primeira linha do texto"], 0, ["", "Strings normais são aceitas se escapadas com \\\\, mas raw strings evitam esse conflito", "Insensibilidade a caixa usa a flag re.IGNORECASE", "O prefixo r afeta apenas o literal da string do padrão, não o tipo retornado", "Multilinha é controlado por flags como re.MULTILINE"]),
        "tf": ("Na sintaxe de expressões regulares do módulo `re`, os âncoras `^` e `$` representam, respectivamente, o início e o fim da string.", True),
        "fill": ("No módulo `re` do Python, a função que substitui todas as ocorrências de um padrão por outro texto em uma string é `re.___(padrao, substituto, texto)`.", ["sub"]),
        "match": [["\\d+", "Casa um ou mais dígitos numéricos consecutivos"], ["re.findall()", "Retorna lista com todas as ocorrências do padrão"], ["re.sub()", "Substitui ocorrências do padrão por outro texto"], ["^ e $", "Âncoras de início e fim da string"]],
        "essay": ("Explique o significado dos elementos `r'...'`, `\\d`, `{5}` e `?` na expressão regular `r'^\\d{5}-?\\d{3}$'` usada para validar um CEP brasileiro.", "O prefixo r cria uma raw string preservando as barras invertidas; ^ e $ exigem casamento do início ao fim da string; \\d{5} exige exatamente 5 dígitos numéricos; -? indica que o hífen é opcional (0 ou 1 vez); e \\d{3} exige os 3 dígitos finais do CEP.", [["raw", "barra", "\\d", "digito", "dígito"], ["{5}", "{3}", "quantificador", "exatamente"], ["?", "opcional", "hifen", "hífen", "^", "$"]])
    },
    {
        "lessonId": "py_a2_l3",
        "subtopic": "Implementação da Política LIFO",
        "concept": "Estrutura de dados Pilha (Stack), política LIFO, push/pop/peek e balanceamento de delimitadores",
        "code1": ("parenteses_balanceados(expr)", "def parenteses_balanceados(expr):\n    pass", "def parenteses_balanceados(expr):\n    pares = {')': '(', ']': '[', '}': '{'}\n    pilha = []\n    for c in expr:\n        if c in '([{':\n            pilha.append(c)\n        elif c in ')]}':\n            if not pilha or pilha.pop() != pares[c]:\n                return False\n    return len(pilha) == 0", [("parenteses_balanceados('([{}])')", "True"), ("parenteses_balanceados('([)]')", "False"), ("parenteses_balanceados('(((')", "False", True)], "Validar aninhamento de delimitadores usando uma pilha LIFO"),
        "code2": ("inverter_com_pilha(texto)", "def inverter_com_pilha(texto):\n    pass", "def inverter_com_pilha(texto):\n    pilha = list(texto)\n    out = []\n    while pilha:\n        out.append(pilha.pop())\n    return ''.join(out)", [("inverter_com_pilha('python')", "'nohtyp'"), ("inverter_com_pilha('AB')", "'BA'"), ("inverter_com_pilha('')", "''", True)], "Explorar a propriedade LIFO de desempilhamento para inverter sequência"),
        "code3": ("avaliar_posfixa(tokens)", "def avaliar_posfixa(tokens):\n    pass", "def avaliar_posfixa(tokens):\n    pilha = []\n    for t in tokens:\n        if t in ('+', '-', '*'):\n            b = pilha.pop()\n            a = pilha.pop()\n            pilha.append(a + b if t == '+' else (a - b if t == '-' else a * b))\n        else:\n            pilha.append(int(t))\n    return pilha[0]", [("avaliar_posfixa(['2', '3', '+', '4', '*'])", "20"), ("avaliar_posfixa(['10', '4', '-'])", "6"), ("avaliar_posfixa(['3', '5', '2', '*', '+'])", "13", True)], "Avaliar expressão em Notação Polonesa Reversa com pilha"),
        "code4": ("historico_navegador(acoes)", "def historico_navegador(acoes):\n    pass", "def historico_navegador(acoes):\n    pilha = []\n    for acao in acoes:\n        if acao == 'VOLTAR':\n            if pilha:\n                pilha.pop()\n        else:\n            pilha.append(acao)\n    return pilha[-1] if pilha else 'VAZIO'", [("historico_navegador(['home', 'trilha', 'VOLTAR'])", "'home'"), ("historico_navegador(['VOLTAR'])", "'VAZIO'"), ("historico_navegador(['a', 'b', 'c', 'VOLTAR', 'VOLTAR'])", "'a'", True)], "Simular botão Voltar do navegador com operações push e pop"),
        "mc1": ("Se empilharmos (`push`) os números `10`, `20` e `30` (nessa ordem) em uma pilha inicialmente vazia e depois executarmos um `pop()`, qual valor é removido e qual fica no topo?", ["Remove `30` e o novo topo passa a ser `20`", "Remove `10` e o novo topo passa a ser `20`", "Remove `20` e o novo topo passa a ser `30`", "Remove `30` e a pilha fica vazia", "Lança `IndexError`"], 0, ["", "Confundiu a política LIFO da pilha com a política FIFO de uma fila", "Uma pilha só remove o elemento do topo, nunca do meio", "Foram inseridos 3 elementos e removido apenas 1, restando 20 e 10", "A pilha não está vazia, portanto pop() é válido"]),
        "mc2": ("Ao usar uma `list` do Python como pilha, qual par de operações realiza `push` e `pop` no topo em tempo constante amortizado $O(1)$?", ["`pilha.append(x)` e `pilha.pop()` (no final da lista)", "`pilha.insert(0, x)` e `pilha.pop(0)` (no início da lista)", "`pilha.extend(x)` e `pilha.remove(x)`", "`pilha.sort()` e `pilha.reverse()`", "`pilha[0] = x` e `del pilha[0]`"], 0, ["", "Inserir e remover no índice 0 desloca todos os elementos e custa O(n)", "extend itera sobre x e remove busca linearmente pelo valor em O(n)", "Ordenação e reversão não são operações de pilha", "Sobrescrever o índice 0 perde elementos e del pilha[0] custa O(n)"]),
        "tf": ("Uma pilha (Stack) segue a política LIFO (Last In, First Out): o último elemento inserido no topo é sempre o primeiro a ser removido.", True),
        "fill": ("A sigla em inglês que descreve a política de acesso de uma Pilha, na qual o último elemento a entrar é o primeiro a sair, é ___.", ["LIFO", "lifo"]),
        "match": [["LIFO", "Last In, First Out (último a entrar, primeiro a sair)"], ["pilha.append(x)", "Operação push no topo em O(1)"], ["pilha.pop()", "Remove e retorna o elemento do topo em O(1)"], ["pilha[-1]", "Operação peek: consulta o topo sem remover"]],
        "essay": ("Explique o funcionamento da política LIFO em uma Pilha e descreva como uma pilha é usada para verificar se os parênteses, colchetes e chaves de uma expressão estão corretamente balanceados.", "Na política LIFO (Last In, First Out), as inserções (push) e remoções (pop) ocorrem exclusivamente na mesma extremidade chamada topo. Para validar delimitadores, empilhamos cada símbolo de abertura encontrado e, ao ler um fechamento, verificamos se a pilha não está vazia e se o símbolo desempilhado do topo corresponde ao par aberto mais recente; ao final, a pilha deve estar vazia.", [["lifo", "ultimo", "último", "primeiro", "topo"], ["empilh", "push", "append", "abertura"], ["desempilh", "pop", "fechamento", "vazia"]])
    }
]


def build_questions():
    out = []
    for order_idx, spec in enumerate(LESSONS):
        lid = spec["lessonId"]
        sub = spec["subtopic"]
        concept = spec["concept"]
        # 10 questões por aula:
        # order_idx par:   3 EASY, 5 MEDIUM, 2 HARD
        # order_idx ímpar: 3 EASY, 4 MEDIUM, 3 HARD
        # Na lista global lesson_index.json, py_b1_l1 começa em order = 138 (par).
        global_order = 138 + order_idx
        diffs = (
            ["EASY", "EASY", "EASY", "MEDIUM", "MEDIUM", "MEDIUM", "MEDIUM", "MEDIUM", "HARD", "HARD"]
            if global_order % 2 == 0
            else ["EASY", "EASY", "EASY", "MEDIUM", "MEDIUM", "MEDIUM", "MEDIUM", "HARD", "HARD", "HARD"]
        )

        # Q1: TRUE_FALSE (EASY)
        tf_stmt, tf_ans = spec["tf"]
        out.append({
            "id": f"{lid}_n01",
            "lessonId": lid,
            "format": "TRUE_FALSE",
            "difficulty": diffs[0],
            "skill": f"Analisar afirmações conceituais sobre {sub}",
            "concept": concept,
            "statement": tf_stmt,
            "explanation": f"Afirmação {'verdadeira' if tf_ans else 'falsa'}: {tf_stmt} Esse comportamento faz parte da especificação oficial do Python 3 em {sub}.",
            "retryGuidance": f"Revise o comportamento padrão da linguagem em {sub} e teste pequenos exemplos no interpretador.",
            "source": "AUTORAL",
            "answerBool": tf_ans,
        })

        # Q2: FILL_BLANK (EASY)
        fb_stmt, fb_ans = spec["fill"]
        out.append({
            "id": f"{lid}_n02",
            "lessonId": lid,
            "format": "FILL_BLANK",
            "difficulty": diffs[1],
            "skill": f"Identificar a sintaxe exata de {sub} em Python",
            "concept": concept,
            "statement": fb_stmt,
            "explanation": f"O termo correto para completar a lacuna é '{fb_ans[0]}', elemento essencial de {sub} em Python.",
            "retryGuidance": f"Consulte o quadro de sintaxe da aula sobre {sub} e verifique o nome exato da palavra-chave, operador ou método.",
            "source": "AUTORAL",
            "answers": fb_ans,
        })

        # Q3: MULTIPLE_CHOICE 1 (EASY)
        mc1_stmt, mc1_opts, mc1_idx, mc1_dist = spec["mc1"]
        # Rotate answer index deterministically so correct answers vary across 0..4
        shift1 = (order_idx * 2 + 1) % 5
        opts1 = mc1_opts[shift1:] + mc1_opts[:shift1]
        dist1 = mc1_dist[shift1:] + mc1_dist[:shift1]
        ans1 = (mc1_idx - shift1) % 5
        out.append({
            "id": f"{lid}_n03",
            "lessonId": lid,
            "format": "MULTIPLE_CHOICE",
            "difficulty": diffs[2],
            "skill": f"Rastrear a execução de código envolvendo {sub}",
            "concept": concept,
            "statement": mc1_stmt,
            "explanation": f"A alternativa correta é '{opts1[ans1]}'. Ao avaliar passo a passo as regras de {sub} em Python, obtemos exatamente esse comportamento.",
            "retryGuidance": f"Simule mentalmente cada operação de {sub} na ordem em que o interpretador Python a executa.",
            "source": "AUTORAL",
            "options": opts1,
            "answerIndex": ans1,
            "distractorConcepts": dist1,
        })

        # Q4: MATCHING (MEDIUM)
        out.append({
            "id": f"{lid}_n04",
            "lessonId": lid,
            "format": "MATCHING",
            "difficulty": diffs[3],
            "skill": f"Relacionar construções de código de {sub} aos seus efeitos",
            "concept": concept,
            "statement": f"Associe cada elemento ou expressão de {sub} em Python à sua descrição correta:",
            "explanation": "Cada construção à esquerda desempenha um papel específico em Python; associar sintaxe à semântica evita erros de lógica e exceções.",
            "retryGuidance": f"Comece pelos itens mais familiares de {sub} e compare o tipo de retorno de cada expressão.",
            "source": "AUTORAL",
            "pairs": spec["match"],
        })

        # Q5: MULTIPLE_CHOICE 2 (MEDIUM)
        mc2_stmt, mc2_opts, mc2_idx, mc2_dist = spec["mc2"]
        shift2 = (order_idx * 3 + 2) % 5
        opts2 = mc2_opts[shift2:] + mc2_opts[:shift2]
        dist2 = mc2_dist[shift2:] + mc2_dist[:shift2]
        ans2 = (mc2_idx - shift2) % 5
        out.append({
            "id": f"{lid}_n05",
            "lessonId": lid,
            "format": "MULTIPLE_CHOICE",
            "difficulty": diffs[4],
            "skill": f"Prever o comportamento e casos de erro em {sub}",
            "concept": concept,
            "statement": mc2_stmt,
            "explanation": f"A resposta correta é '{opts2[ans2]}', pois respeita rigorosamente a semântica de {sub} no Python 3.",
            "retryGuidance": f"Verifique os tipos de dados envolvidos e as regras de avaliação de {sub} antes de escolher a alternativa.",
            "source": "AUTORAL",
            "options": opts2,
            "answerIndex": ans2,
            "distractorConcepts": dist2,
        })

        # Q6..Q9: 4 questões CODE com testes automatizados (MEDIUM / HARD)
        for c_idx, key in enumerate(["code1", "code2", "code3", "code4"], start=6):
            sig, starter, ref, raw_tests, skill_desc = spec[key]
            tests = [
                {"call": t[0], "expected": t[1], **({"hidden": True} if len(t) > 2 and t[2] else {})}
                for t in raw_tests
            ]
            ex0 = raw_tests[0]
            out.append({
                "id": f"{lid}_n{c_idx:02d}",
                "lessonId": lid,
                "format": "CODE",
                "difficulty": diffs[c_idx - 1],
                "skill": f"{skill_desc} ({sub})",
                "concept": concept,
                "statement": f"Implemente em Python a solução solicitada para `{sig}` aplicando os conceitos de {sub}. Por exemplo, a chamada `{ex0[0]}` deve produzir `{ex0[1]}`.",
                "explanation": f"Solução de referência:\n```python\n{ref}\n```\nEla aplica diretamente {concept.lower()} e trata todos os casos de teste, incluindo casos de borda.",
                "retryGuidance": f"Mantenha a assinatura exata `{sig.split('(')[0]}`, retorne o valor calculado com `return` (não apenas `print`) e teste casos limites.",
                "source": "AUTORAL",
                "code": {
                    "starter": starter,
                    "reference": ref,
                    "tests": tests,
                },
            })

        # Q10: ESSAY com rubrica explícita (HARD)
        es_stmt, es_model, es_kw = spec["essay"]
        rubric = [
            {"criterion": f"Explica o funcionamento principal em {sub}", "points": 4, "keywords": [es_kw[0]]},
            {"criterion": "Detalha o mecanismo técnico ou sintático envolvido", "points": 3, "keywords": [es_kw[1]]},
            {"criterion": "Contextualiza com aplicação prática ou comparação correta", "points": 3, "keywords": [es_kw[2]]},
        ]
        out.append({
            "id": f"{lid}_n10",
            "lessonId": lid,
            "format": "ESSAY",
            "difficulty": diffs[9],
            "skill": f"Argumentar tecnicamente sobre {sub} em Python",
            "concept": concept,
            "statement": es_stmt,
            "explanation": f"Uma resposta completa deve abordar os três pilares avaliados na rubrica: {es_model}",
            "retryGuidance": f"Certifique-se de mencionar explicitamente os conceitos-chave de {sub}, a sintaxe utilizada e o motivo técnico do comportamento.",
            "source": "AUTORAL",
            "modelAnswer": es_model,
            "passScore": 0.6,
            "rubric": rubric,
        })

    return out


if __name__ == "__main__":
    qs = build_questions()
    os.makedirs(os.path.dirname(OUT), exist_ok=True)
    with open(OUT, "w", encoding="utf-8") as f:
        json.dump(qs, f, ensure_ascii=False, indent=2)
    print(f"Geradas {len(qs)} questões em {OUT}")
