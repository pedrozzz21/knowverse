package com.example.data.python.didactic

import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample
import com.example.data.python.PythonDidacticBundle

/**
 * Conteúdo didático aprofundado do Nível Básico de Python (Parte 1: Aulas py_b1_l1 a py_b1_l3).
 * Estrutura rigorosa em 12 seções por aula, fundamentada na documentação oficial de Python e CS50.
 */
object PythonBasicPart1 {

    fun get(lessonId: String): PythonDidacticBundle? {
        return when (lessonId) {
            "py_b1_l1" -> bundlePyB1L1
            "py_b1_l2" -> bundlePyB1L2
            "py_b1_l3" -> bundlePyB1L3
            else -> null
        }
    }

    // =========================================================================
    // AULA py_b1_l1: Primeiros Passos com print()
    // =========================================================================
    private val bundlePyB1L1 = PythonDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
A jornada na ciência da computação começa com a capacidade de um programa de se comunicar com o mundo exterior. Em Python, um programa é uma sequência ordenada de instruções executadas por um interpretador linha por linha. O primeiro ato de qualquer desenvolvedor é dominar a saída padrão (stdout) — a tela do terminal —, transformando dados internos armazenados na memória em mensagens inteligíveis para seres humanos por meio da função nativa `print()`.

## 2. Objetivos de Aprendizagem
- Compreender o ciclo de execução interpretada em Python e o conceito de fluxo de saída padrão (`sys.stdout`).
- Utilizar a função embutida `print()` para exibir strings literais, números e expressões avaliadas.
- Customizar os parâmetros nomeados `sep` (separador) e `end` (terminador de linha) para controle estético do console.
- Distinguir strings delimitadas por aspas simples (`'...'`), aspas duplas (`"..."`) e aspas triplas (`${'$'}{"\"\"\""}...${'$'}{"\"\"\""}`).

## 3. Pré-requisitos
- Noções fundamentais de lógica e algoritmos lineares.
- Conhecimento básico sobre o terminal / console de comandos.

## 4. Explicação Teórica Aprofundada
### O Interpretador e o Fluxo de Saída
O interpretador CPython compila o código fonte em bytecode (`.pyc`) e o executa em uma máquina virtual (PVM). Quando a instrução `print()` é executada:
1. Os argumentos fornecidos são avaliados da esquerda para a direita.
2. Cada objeto é convertido internamente para texto através do método especial `__str__()`.
3. Os caracteres são gravados no buffer do fluxo de saída padrão do sistema operacional.

### Assinatura Formal da Função `print()`
Na biblioteca padrão do Python, a função é definida como:
```python
print(*objects, sep=' ', end='\n', file=sys.stdout, flush=False)
```
- `*objects`: Uma quantidade arbitrária de argumentos separados por vírgula.
- `sep`: String inserida entre cada objeto. Por padrão, é um único espaço (`' '`).
- `end`: String anexada ao final após a impressão de todos os objetos. Por padrão, é a quebra de linha (`'\n'`).
- `flush`: Booleano que força o esvaziamento imediato do buffer de saída sem aguardar nova linha.

### Strings e Caracteres de Escape
Textos literais são instâncias do tipo `str` (sequências imutáveis de caracteres Unicode). Para incluir quebras de linha ou aspas dentro de um texto:
- `\n`: Nova linha (Line Feed).
- `\t`: Tabulação horizontal (Tab).
- `\\`: Barra invertida literal.
- `\'` ou `\"`: Aspas literais sem encerrar a delimitação da string.

## 5. Definições e Conceitos
- **stdout (Standard Output):** Canal de saída padrão do sistema operacional onde mensagens regulares são exibidas.
- **Literal de String:** Sequência de caracteres cercada por aspas que representa diretamente um valor de texto.
- **Case-Sensitive:** Característica da linguagem Python onde letras maiúsculas e minúsculas representam identificadores completamente distintos (`print` é válido; `Print` gera `NameError`).

## 6. Exemplos Resolvidos Passo a Passo
### Exemplo 1: Impressão Simples e Quebra de Linha
```python
print("Olá, Knowverse!")
print("Segunda linha de execução.")
```
- **Explicação:** O primeiro `print` envia os caracteres para a tela e anexa automaticamente o terminador `\n`. O segundo comando inicia a escrita obrigatoriamente na linha subsequente.

### Exemplo 2: Manipulação de `sep` e `end`
```python
print("2026", "11", "03", sep="-", end=" -> ")
print("Status: Concluído")
```
- **Saída no Terminal:**
  `2026-11-03 -> Status: Concluído`
- **Explicação:** O separador padrão (espaço) foi substituído por hífen (`-`), unindo os três argumentos em formato de data ISO. O `end=" -> "` substituiu a quebra de linha por uma seta, fazendo com que o print seguinte continuasse na mesmíssima linha.

## 7. Aplicações Práticas e Modelagem
- **Logging de Servidores e Microsserviços:** Em scripts de automação, impressões formatadas em console informam o status operacional, timestamps e diagnósticos em tempo real.
- **Interfaces de Linha de Comando (CLI):** Softwares de terminal dependem do alinhamento cuidadoso de caracteres e tabulações para exibir tabelas e barras de progresso ao usuário.

## 8. Exercícios de Fixação
1. Qual será a saída do comando `print("A", "B", "C", sep="*")`?
2. Como imprimir dois comandos `print("Carregando...")` e `print("Pronto!")` na mesma linha separados por um espaço?
3. O que acontece ao executar `Print("Teste")`? Explique o erro retornado.

## 9. Correção Comentada dos Exercícios
1. A saída será `A*B*C`. Os três argumentos foram concatenados utilizando o asterisco como delimitador entre eles.
2. Basta definir `end=" "` no primeiro comando: `print("Carregando...", end=" ")` seguido de `print("Pronto!")`.
3. O Python lançará `NameError: name 'Print' is not defined`, pois Python é estritamente case-sensitive e todas as funções embutidas essenciais são escritas com letras minúsculas.

## 10. Desafio de Domínio
Construa um único comando `print()` que desenhe na tela um triângulo de três linhas feito com caracteres `#` utilizando caracteres de escape `\n` e sem usar repetições manuais de print.

## 11. Resumo e Pontos-Chave
- `print()` exibe informações no console (`stdout`).
- Múltiplos argumentos são separados por padrão com um espaço (`sep=' '`).
- Cada `print()` quebra a linha por padrão (`end='\n'`).
- Python diferencia maiúsculas de minúsculas com rigor absoluto.

## 12. Referências Bibliográficas
- PYTHON SOFTWARE FOUNDATION. *The Python Standard Library: Built-in Functions - print()*. Disponível em: https://docs.python.org/3/library/functions.html#print.
- MALAN, David J. *CS50's Introduction to Programming with Python*. Harvard University, 2023. Lecture 0: "Functions, Variables".
- LUTZ, Mark. *Learning Python*. 5th ed. O'Reilly Media, 2013. Chapter 11: "Print Operations".
        """.trimIndent(),
        functionsAndSyntax = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "print()",
                syntax = "print(*objects, sep=' ', end='\\n')",
                description = "Envia a representação textual de múltiplos objetos para a saída padrão.",
                parametersInfo = "*objects: dados a exibir; sep: separador entre itens; end: finalizador após o último item."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Caracteres de Escape",
                syntax = "\\n (quebra de linha) \\t (tabulação) \\\\ (barra)",
                description = "Sequências especiais que representam caracteres não-imprimíveis dentro de strings.",
                parametersInfo = "Precedidos obrigatoriamente por barra invertida."
            )
        ),
        codeExamples = listOf(
            LessonExample(
                title = "Controle de Separador e Terminador",
                codeOrFormula = "print('Dia', 1, sep=': ', end=' | '); print('Sucesso!')",
                explanation = "O separador customizado formata o rótulo e o terminador encadeia os dois comandos na mesma linha."
            )
        ),
        goldTip = "Lembre-se sempre: Python é Case-Sensitive! Escrever Print() com 'P' maiúsculo gerará um NameError imediato!"
    )

    // =========================================================================
    // AULA py_b1_l2: Variáveis e Tipagem Dinâmica
    // =========================================================================
    private val bundlePyB1L2 = PythonDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
Programas de computador não existem apenas para exibir mensagens estáticas; sua razão de ser é processar, calcular e transformar dados dinâmicos. Como armazenar a idade de um usuário, a velocidade de uma partícula ou o saldo de uma conta bancária para uso posterior? Em Python, as **variáveis** operam como etiquetas inteligentes que apontam para objetos na memória do computador. Graças ao modelo de **Tipagem Dinâmica e Forte**, Python combina flexibilidade de escrita com segurança de integridade dos dados.

## 2. Objetivos de Aprendizagem
- Compreender o modelo de variáveis em Python como referências para objetos na memória heap.
- Identificar e utilizar os quatro tipos de dados primitivos fundamentais: `int`, `float`, `str` e `bool`.
- Aplicar as regras e convenções oficiais do PEP 8 para nomenclatura de identificadores (`snake_case`).
- Compreender a semântica da tipagem dinâmica (reatribuição de tipos) e da tipagem forte (proibição de operações implícitas incoerentes).

## 3. Pré-requisitos
- Uso básico de `print()` e literais de string (Aula py_b1_l1).
- Noções de números inteiros, decimais e lógica booleana (Verdadeiro/Falso).

## 4. Explicação Teórica Aprofundada
### Tudo em Python é um Objeto
Diferente de linguagens como C ou Java, onde variáveis são "caixas" de tamanho fixo com tipo estático declarado previamente, em Python **todas as entidades são objetos de primeira classe alocados na memória**.
Quando escrevemos:
```python
x = 42
```
O interpretador realiza três passos nos bastidores:
1. Cria um objeto do tipo inteiro (`int`) com valor 42 na memória.
2. Cria o identificador `x`.
3. Associa a etiqueta `x` à referência de memória do objeto 42.

### Os Quatro Tipos Primitivos Básicos
1. **`int` (Inteiros):** Representa números inteiros sem parte decimal (\( \dots, -2, -1, 0, 1, 2, \dots \)). Em Python 3, os inteiros possuem precisão arbitrária: eles crescem dinamicamente de acordo com a memória RAM disponível, sem risco de estouro de bits (*integer overflow*).
2. **`float` (Ponto Flutuante):** Representa números reais com casas decimais (ex: `3.14159`, `-0.001`, `2.0`). Segue o padrão internacional IEEE 754 de 64 bits (dupla precisão).
3. **`str` (String):** Cadeia imutável de caracteres textuais codificada em UTF-8.
4. **`bool` (Booleano):** Tipo lógico que admite apenas dois valores exclusivos: `True` (Verdadeiro) ou `False` (Falso). Notar a inicial maiúscula obrigatória.

### Tipagem Dinâmica vs. Tipagem Forte
- **Dinâmica:** O tipo é associado ao **objeto na memória**, não à variável. Uma variável pode apontar para um número inteiro em uma linha e ser reatribuída para uma string na linha seguinte sem erro de compilação:
  ```python
  dado = 10      # dado aponta para int
  dado = "Texto" # dado agora aponta para str (perfeitamente legal)
  ```
- **Forte:** O interpretador **não faz coerção implícita perigosa de tipos incompatíveis**. Se você tentar somar um inteiro com uma string:
  ```python
  resultado = 5 + "10" # TypeError: unsupported operand type(s)
  ```
  Python recusa a operação, exigindo conversão explícita pelo programador.

### Convenções de Nomenclatura (PEP 8)
- Nomes de variáveis devem usar letras minúsculas separadas por sublinhado: `snake_case` (ex: `velocidade_inicial`, `preco_total`).
- Não podem começar com números nem conter espaços ou caracteres especiais (exceto `_`).
- Palavras reservadas da linguagem (`def`, `class`, `if`, `for`, `return`) não podem ser usadas como identificadores.

## 5. Definições e Conceitos
- **Identificador:** O nome atribuído a uma variável, função ou classe.
- **Função `type()`:** Função embutida que inspeciona e retorna o tipo exato do objeto referenciado.
- **Garbage Collector (Coletor de Lixo):** Mecanismo automático do Python que libera a memória de objetos que perderam todas as suas referências.

## 6. Exemplos Resolvidos Passo a Passo
### Exemplo 1: Declaração e Inspeção de Tipos
```python
idade = 25
altura = 1.78
nome = "Carlos"
ativo = True

print(type(idade))   # <class 'int'>
print(type(altura))  # <class 'float'>
print(type(nome))    # <class 'str'>
print(type(ativo))   # <class 'bool'>
```
- **Explicação:** Cada atribuição instancia um objeto correspondente na memória; `type()` revela a classe de cada variável.

### Exemplo 2: Atribuição Múltipla e Troca de Variáveis
```python
# Atribuição múltipla em uma única linha
x, y = 10, 20
print("Antes:", x, y)

# Troca pythônica de valores sem variável auxiliar temporária
x, y = y, x
print("Depois:", x, y)
```
- **Explicação:** O lado direito avalia a tupla `(20, 10)` e desempacota os valores simultaneamente em `x` e `y`.

## 7. Aplicações Práticas e Modelagem
- **Engenharia de Dados:** Pipelines em Pandas e PySpark carregam tabelas com milhões de linhas cujas colunas são inferidas e validadas por seus tipos primitivos (`int64`, `float64`, `object`).
- **Simulações Físicas:** Constantes universais como gravidade (`g = 9.81`) e velocidade da luz (`c = 299792458`) são instanciadas como floats e inteiros para equacionamento de trajetórias.

## 8. Exercícios de Fixação
1. Identifique o tipo de cada uma das seguintes variáveis: `a = 15`, `b = "15"`, `c = 15.0`, `d = (5 > 2)`.
2. Qual das seguintes nomenclaturas viola as convenções do Python? `total_vendas`, `2total`, `totalVendas`, `_total`.
3. O que ocorrerá se executarmos o código `valor = 10; print(valor + "reais")`?

## 9. Correção Comentada dos Exercícios
1. `a` é `int`; `b` é `str` (envolvida por aspas); `c` é `float` (possui ponto decimal); `d` é `bool` (resultado da comparação lógica 5 > 2, que avalia para `True`).
2. `2total` é estritamente ilegal e causa `SyntaxError` porque identificadores em Python jamais podem começar com dígitos numéricos. `totalVendas` (camelCase) funciona, mas contraria a convenção oficial PEP 8 (`snake_case`).
3. Lançará um `TypeError: unsupported operand type(s) for +: 'int' and 'str'`. Python é fortemente tipado e não concatena automaticamente números com texto.

## 10. Desafio de Domínio
Explique detalhadamente como o Python gerencia internamente a memória quando executamos:
```python
a = [1, 2, 3]
b = a
b.append(4)
print(a)
```
Por que a lista `a` também foi modificada, e qual a diferença entre atribuir um valor primitivo e uma referência a um objeto mutável?

## 11. Resumo e Pontos-Chave
- Variáveis são etiquetas que referenciam objetos na memória.
- Tipos fundamentais: `int`, `float`, `str`, `bool`.
- Tipagem Dinâmica: O tipo pertence ao objeto, permitindo reatribuição.
- Tipagem Forte: Não há coerção mágica entre tipos incompatíveis.
- Siga a PEP 8: nomes de variáveis sempre em `snake_case`.

## 12. Referências Bibliográficas
- PYTHON SOFTWARE FOUNDATION. *The Python Tutorial: Informal Introduction to Python - Numbers, Strings*. Disponível em: https://docs.python.org/3/tutorial/introduction.html.
- VAN ROSSUM, Guido; WARSAW, Barry. *PEP 8 – Style Guide for Python Code*. 2001. Disponível em: https://peps.python.org/pep-0008/.
- LUTZ, Mark. *Learning Python*. 5th ed. O'Reilly Media, 2013. Chapter 4: "Introducing Python Object Types" e Chapter 6: "The Dynamic Typing Model".
        """.trimIndent(),
        functionsAndSyntax = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "type()",
                syntax = "type(objeto)",
                description = "Retorna a classe/tipo de dado do objeto em memória.",
                parametersInfo = "objeto: qualquer variável, valor literal ou expressão a ser inspecionada."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Atribuição Simples e Múltipla",
                syntax = "x = valor \\quad | \\quad a, b = val1, val2",
                description = "Vincula um identificador simbólico à referência de um objeto.",
                parametersInfo = "O lado direito é avaliado completamente antes da associação das variáveis."
            )
        ),
        codeExamples = listOf(
            LessonExample(
                title = "Troca Elegante de Variáveis (Swap)",
                codeOrFormula = "x, y = 10, 20\nx, y = y, x\nprint(x, y)  # Exibe: 20 10",
                explanation = "Python empacota e desempacota tuplas nos bastidores dispensando variáveis temporárias auxiliares."
            )
        ),
        goldTip = "Em Python, o tipo pertence ao OBJETO e não à variável! A variável é apenas uma etiqueta colada em um objeto da memória RAM."
    )

    // =========================================================================
    // AULA py_b1_l3: Conversão de Tipos (Type Casting)
    // =========================================================================
    private val bundlePyB1L3 = PythonDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
Vimos na aula anterior que o Python é uma linguagem de tipagem forte: ele se recusa a somar um número com um texto. No entanto, em sistemas reais, os dados chegam frequentemente como texto puro — seja quando um usuário digita algo no terminal através da função `input()`, seja ao ler linhas de um arquivo CSV ou receber payloads JSON de uma API na web. Como transformar a string `"19.90"` em um número decimal apto a cálculos financeiros? O mecanismo responsável por essa ponte é a **Conversão de Tipos (Type Casting)**.

## 2. Objetivos de Aprendizagem
- Diferenciar com clareza a Coerção Implícita automática da Conversão Explícita intencional.
- Utilizar com maestria as funções construtoras embutidas: `int()`, `float()`, `str()` e `bool()`.
- Capturar dados do usuário com a função `input()` e convertê-los com segurança.
- Compreender a regra de "Truthiness" (valores considerados falsos e verdadeiros no casting para booleano).
- Tratar e prevenir erros de conversão do tipo `ValueError`.

## 3. Pré-requisitos
- Tipos de dados primitivos (`int`, `float`, `str`, `bool`) (Aula py_b1_l2).
- Operadores e concatenação básica de strings.

## 4. Explicação Teórica Aprofundada
### Coerção Implícita vs. Conversão Explícita
- **Coerção Implícita (Automática):** O Python converte tipos automaticamente apenas quando a operação é segura matematicamente e não há perda de precisão. Por exemplo, ao somar um inteiro com um float:
  ```python
  resultado = 5 + 2.5 # 5 é promovido internamente para 5.0 -> resultado é 7.5 (float)
  ```
- **Conversão Explícita (Type Casting):** É o processo pelo qual o programador invoca explicitamente o construtor da classe alvo para forçar a transformação do dado:
  - `int(x)`: Converte para inteiro.
  - `float(x)`: Converte para ponto flutuante.
  - `str(x)`: Converte qualquer objeto para sua representação textual.
  - `bool(x)`: Avalia o valor de verdade lógico do objeto.

### A Armadilha Clássica da Função `input()`
A função nativa `input(prompt)` pausa a execução do programa, aguarda o usuário digitar uma resposta e pressionar Enter, e **SEMPRE retorna uma string (`str`)**, independentemente do que foi digitado:
```python
idade = input("Digite sua idade: ") # Se o usuário digitar 20, idade recebe a string "20"
proximo_ano = idade + 1             # ERRO: TypeError! Não é possível somar str com int!
```
Para realizar operações aritméticas, o casting explícito imediato é obrigatório:
```python
idade = int(input("Digite sua idade: ")) # idade agora é int(20)
proximo_ano = idade + 1                  # Sucesso: 21
```

### O Truncamento na Conversão `int(float)`
Ao converter um número de ponto flutuante para inteiro usando `int()`, o Python **não realiza arredondamento matemático**; ele simplesmente descarta toda a parte fracionária (**truncamento em direção a zero**):
- `int(7.99)` resulta em `7` (e não 8!).
- `int(-3.8)` resulta em `-3`.
Para arredondamento convencional, deve-se usar a função embutida `round()`.

### A Regra de "Truthiness" em `bool()`
Ao converter qualquer objeto para `bool`, o Python aplica uma regra simples e universal:
- **Valores Falsy (convertem para `False`):**
  - O número zero de qualquer tipo numérico: `0`, `0.0`.
  - Sequências e coleções vazias: `""` (string vazia), `[]` (lista vazia), `()` (tupla vazia), `{}` (dicionário vazio).
  - A constante nula `None` e o próprio `False`.
- **Valores Truthy (convertem para `True`):**
  - Qualquer número diferente de zero (mesmo números negativos como `-1` ou `0.0001`).
  - Qualquer string que contenha ao menos um caractere (inclusive `" "` contendo apenas um espaço ou `"False"`).
  - Qualquer coleção não-vazia.

## 5. Definições e Conceitos
- **Type Casting:** Conversão explícita do tipo de um dado para outra classe.
- **ValueError:** Erro gerado quando uma função recebe um argumento com o tipo correto, mas valor inadequado para a conversão (ex: `int("abacaxi")`).
- **Truncamento:** Eliminação pura das casas decimais sem aproximação estatística.

## 6. Exemplos Resolvidos Passo a Passo
### Exemplo 1: Conversão Segura de Entrada de Usuário
```python
peso_str = "68.5"
altura_str = "1.75"

# Conversão explícita para float
peso = float(peso_str)
altura = float(altura_str)

# Cálculo do IMC (Índice de Massa Corporal)
imc = peso / (altura ** 2)
print("IMC calculado:", round(imc, 2))
```
- **Explicação:** As strings literais foram convertidas para floats válidos, viabilizando as operações de exponenciação e divisão matemática.

### Exemplo 2: O Perigo da String com Espaço no Booleano
```python
texto_vazio = ""
texto_com_espaco = " "

print(bool(texto_vazio))       # False (string vazia)
print(bool(texto_com_espaco))  # True (possui 1 caractere de espaço!)
```
- **Explicação:** Para o Python, qualquer caractere — inclusive espaço em branco — torna a string preenchida, convertendo para `True`.

## 7. Aplicações Práticas e Modelagem
- **Processamento de Formulários Web:** Requisições HTTP em frameworks como Django e FastAPI recebem todos os dados dos campos como strings no corpo da requisição; validadores automáticos (Pydantic) executam o casting tipado para os tipos do banco de dados.
- **Leitura de Sensores IoT:** Dados transmitidos por portas seriais via protocolo Arduino/ESP32 chegam como bytes ou strings textuais ASCII; o software receptor converte os valores para inteiros ou floats calibrados.

## 8. Exercícios de Fixação
1. Qual o resultado exato de `int(14.85)`?
2. O que resultará a conversão `bool("False")`? Justifique.
3. Como converter a string `"100"` para float e depois concatenar com o texto `" reais"`?

## 9. Correção Comentada dos Exercícios
1. O resultado é `14`. A função `int()` trunca o número descartando as casas decimais sem arredondamento.
2. Resultará em `True`. A string `"False"` não é vazia: ela contém 5 caracteres, portanto é avaliada como Truthy.
3. `str(float("100")) + " reais"`, que produz `"100.0 reais"`.

## 10. Desafio de Domínio
Por que a instrução `int("3.14")` gera imediatamente um `ValueError` em Python, enquanto `int(float("3.14"))` executa sem nenhum erro? Explique a mecânica de parsing do interpretador CPython.

## 11. Resumo e Pontos-Chave
- `input()` sempre retorna uma string (`str`).
- Use `int()`, `float()`, `str()` e `bool()` para conversão explícita.
- `int(float)` trunca as casas decimais sem arredondar.
- Zero, string vazia `""` e coleções vazias convertem para `False`; todo o resto converte para `True`.

## 12. Referências Bibliográficas
- PYTHON SOFTWARE FOUNDATION. *Built-in Types - Truth Value Testing & Numeric Types*. Disponível em: https://docs.python.org/3/library/stdtypes.html#truth-value-testing.
- MALAN, David J. *CS50's Introduction to Programming with Python*. Harvard University, 2023. Lecture 0: "Variables and Types".
- LUTZ, Mark. *Learning Python*. 5th ed. O'Reilly Media, 2013. Chapter 5: "Numeric Types".
        """.trimIndent(),
        functionsAndSyntax = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "int(), float(), str(), bool()",
                syntax = "int(x) \\quad | \\quad float(x) \\quad | \\quad str(x) \\quad | \\quad bool(x)",
                description = "Funções construtoras para conversão explícita de tipos.",
                parametersInfo = "x: valor a ser convertido para a classe de destino."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "input()",
                syntax = "input(prompt)",
                description = "Captura uma linha digitada pelo usuário no terminal na forma de string pura.",
                parametersInfo = "prompt: mensagem textual instrutiva exibida no console."
            )
        ),
        codeExamples = listOf(
            LessonExample(
                title = "Casting Seguro de Entrada",
                codeOrFormula = "qtd = int('15')\npreco = float('9.50')\ntotal = qtd * preco\nprint(f'Total: R${'$'} {total:.2f}')",
                explanation = "A conversão prévia de string para int e float permitiu a multiplicação aritmética correta."
            )
        ),
        goldTip = "A função input() SEMPRE retorna uma string! Se você quer ler um número, envolva o input imediatamente: int(input('...')) ou float(input('...'))!"
    )
}
