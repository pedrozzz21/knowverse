package com.example.data.python.didactic

import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample
import com.example.data.python.PythonDidacticBundle

/**
 * Conteúdo didático aprofundado do Nível Básico de Python (Parte 2: Aulas py_b2_l1 a py_b3_l2).
 * Estrutura rigorosa em 12 seções por aula, fundamentada na documentação oficial de Python e CS50.
 */
object PythonBasicPart2 {

    fun get(lessonId: String): PythonDidacticBundle? {
        return when (lessonId) {
            "py_b2_l1" -> bundlePyB2L1
            "py_b2_l2" -> bundlePyB2L2
            "py_b3_l1" -> bundlePyB3L1
            "py_b3_l2" -> bundlePyB3L2
            else -> null
        }
    }

    // =========================================================================
    // AULA py_b2_l1: Operadores Aritméticos
    // =========================================================================
    private val bundlePyB2L1 = PythonDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
Desde as calculadoras mecânicas mais antigas até os supercomputadores modernos executando inteligência artificial, a aritmética computacional é o motor de qualquer processamento de dados. Python se destaca por sua sintaxe limpa e matemática direta, suportando desde adições e multiplicações simples até operações avançadas como divisão inteira pelo chão, exponenciação de precisão infinita e o operador módulo para análise de paridade e ciclos temporais.

## 2. Objetivos de Aprendizagem
- Dominar os sete operadores aritméticos do Python: `+`, `-`, `*`, `/`, `//`, `%` e `**`.
- Distinguir a divisão de ponto flutuante padrão (`/`) da divisão inteira pelo chão (`//`).
- Compreender e aplicar o operador módulo (`%`) em problemas de divisibilidade e relógios cíclicos.
- Aplicar a ordem formal de precedência de operadores (regra PEMDAS) e operadores de atribuição composta (`+=`, `-=`, etc.).

## 3. Pré-requisitos
- Tipos numéricos primitivos (`int` e `float`) (Aula py_b1_l2).
- Conversão de tipos (Aula py_b1_l3).

## 4. Explicação Teórica Aprofundada
### Os Sete Operadores Fundamentais
1. **Adição (`+`):** Soma dois operandos numéricos (`7 + 3 = 10`). Em strings e listas, atua como concatenação.
2. **Subtração (`-`):** Diferença entre operandos ou operador unário de inversão de sinal (`-x`).
3. **Multiplicação (`*`):** Produto aritmético (`4 * 5 = 20`). Em strings e listas, atua como operador de repetição (`"Ha" * 3 = "HaHaHa"`).
4. **Divisão de Ponto Flutuante (`/`):** **SEMPRE retorna um `float`**, mesmo que a divisão seja exata:
   ```python
   10 / 2  # Retorna 5.0 (e não o inteiro 5!)
   ```
5. **Divisão Inteira / Divisão pelo Chão (`//`):** Descarta o resto e retorna apenas o quociente inteiro arredondado para baixo (chão matemático):
   ```python
   7 // 2   # Retorna 3
   -7 // 2  # Retorna -4 (arredonda para o inteiro menor mais próximo!)
   ```
6. **Módulo / Resto da Divisão (`%`):** Retorna o resto da divisão euclidiana:
   ```python
   7 % 2    # Retorna 1 (pois 7 = 3 * 2 + 1)
   ```
   *Utilidade Capital:* Testar se um número \( n \) é par (`n % 2 == 0`) ou ímpar (`n % 2 != 0`).
7. **Exponenciação (`**`):** Eleva a base ao expoente:
   ```python
   2 ** 3   # 2³ = 8
   9 ** 0.5 # Raiz quadrada de 9 = 3.0
   ```
   *(Atenção: Em Python, o símbolo de circunflexo `^` NÃO é exponenciação! É o operador binário XOR bit-a-bit).*

### Ordem de Precedência (PEMDAS)
Quando múltiplos operadores coexistem em uma mesma expressão, o Python avalia na seguinte ordem hierárquica estrita:
1. **P**arênteses `()` (da camada mais interna para a mais externa).
2. **E**xponenciação `**` (avaliada da **direita para a esquerda**: `2 ** 3 ** 2` é `2 ** 9 = 512`).
3. **M**ultiplicação `*`, **D**ivisão `/`, `//`, e Módulo `%` (avaliados da esquerda para a direita).
4. **A**dição `+` e **S**ubtração `-` (avaliados da esquerda para a direita).

### Operadores de Atribuição Composta (Augmented Assignment)
Para atualizar o valor de uma variável existente de forma elegante e concisa:
- `x += 5` equivale a `x = x + 5`
- `x *= 2` equivale a `x = x * 2`
- `x //= 3` equivale a `x = x // 3`

## 5. Definições e Conceitos
- **Divisão pelo Chão (Floor Division):** Operação matemática \( \lfloor a / b \rfloor \) que arredonda para o maior inteiro menor ou igual ao resultado real.
- **Aritmética de Precisão Arbitrária:** Capacidade nativa do Python de computar potências gigantescas como `2 ** 1000` sem perder nenhum dígito.

## 6. Exemplos Resolvidos Passo a Passo
### Exemplo 1: Conversão de Tempo (Segundos para Horas, Minutos e Segundos)
Dado um tempo de 3725 segundos, converta-o para horas, minutos e segundos usando `//` e `%`.
```python
total_segundos = 3725

# 1 hora tem 3600 segundos
horas = total_segundos // 3600
resto_segundos = total_segundos % 3600

# 1 minuto tem 60 segundos
minutos = resto_segundos // 60
segundos_finais = resto_segundos % 60

print(f"{horas}h {minutos}min {segundos_finais}s")
# Saída: 1h 2min 5s
```
- **Explicação:** A divisão inteira extraiu as unidades completas de horas e minutos, enquanto o operador módulo reteve o resíduo que alimentou a próxima unidade.

### Exemplo 2: Precedência de Operadores
Avalie o valor de `resultado = 2 + 3 * 4 ** 2 / 8`.
- **Passo 1 (Exponenciação primeiro):** `4 ** 2 = 16`. A expressão vira `2 + 3 * 16 / 8`.
- **Passo 2 (Multiplicação e divisão da esquerda para a direita):**
  `3 * 16 = 48`. A expressão vira `2 + 48 / 8`.
  `48 / 8 = 6.0`. A expressão vira `2 + 6.0`.
- **Passo 3 (Adição por último):**
  `2 + 6.0 = 8.0`.

## 7. Aplicações Práticas e Modelagem
- **Criptografia e Funções Hash:** O operador módulo `%` é a espinha dorsal de algoritmos criptográficos modernos como RSA e curvas elípticas, garantindo que operações aritméticas fiquem restritas a um corpo finito restrito.
- **Física de Jogos e Gráficos 2D:** O cálculo de posições em matrizes de pixels ou tiles de mapas usa `linha = indice // largura` e `coluna = indice % largura`.

## 8. Exercícios de Fixação
1. Qual é o resultado e o tipo de dado da expressão `16 / 4`?
2. Calcule o resultado de `-10 // 3` e explique por que a resposta é `-4` e não `-3`.
3. Qual é o operador que calcula a raiz cúbica de 27 em uma única instrução?

## 9. Correção Comentada dos Exercícios
1. O resultado é `4.0` do tipo `float`. O operador `/` sempre retorna um número de ponto flutuante em Python 3.
2. A divisão real dá `-3.333...`. Como a divisão pelo chão arredonda sempre para baixo (em direção a \( -\infty \)), o inteiro imediatamente abaixo de `-3.333` é `-4`.
3. `27 ** (1/3)`. A exponenciação fracionária com parênteses calcula raízes diretamente.

## 10. Desafio de Domínio
Implemente uma expressão matemática que determine se um ano qualquer fornecido na variável `ano` é bissexto segundo as regras do calendário gregoriano (divisível por 4, exceto anos múltiplos de 100 que não sejam múltiplos de 400).

## 11. Resumo e Pontos-Chave
- Divisão comum `/` sempre resulta em `float`.
- Divisão inteira `//` descarta a parte decimal.
- O módulo `%` fornece o resto da divisão.
- `**` é o operador de exponenciação; nunca use `^` para potências.
- Respeite a ordem PEMDAS (Parênteses, Expoentes, Multiplicação/Divisão, Soma/Subtração).

## 12. Referências Bibliográficas
- PYTHON SOFTWARE FOUNDATION. *The Python Tutorial: Using Python as a Calculator*. Disponível em: https://docs.python.org/3/tutorial/introduction.html#using-python-as-a-calculator.
- MALAN, David J. *CS50's Introduction to Programming with Python*. Harvard University, 2023. Lecture 0: "Operators".
- LUTZ, Mark. *Learning Python*. 5th ed. O'Reilly Media, 2013. Chapter 5: "Numeric Operations".
        """.trimIndent(),
        functionsAndSyntax = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Operadores Aritméticos",
                syntax = "+, -, *, /, // (chão), % (módulo), ** (potência)",
                description = "Conjunto de operadores numéricos fundamentais do Python.",
                parametersInfo = "Divisão / sempre produz float; // produz int (se os operandos forem int)."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Atribuição Composta",
                syntax = "+=, -=, *=, /=, //=, %=, **=",
                description = "Atualiza a variável aplicando a operação aritmética in-place.",
                parametersInfo = "x += 1 incrementa o valor de x em uma unidade."
            )
        ),
        codeExamples = listOf(
            LessonExample(
                title = "Aritmética Euclidiana com Módulo e Chão",
                codeOrFormula = "qtd_itens = 23\ncaixas = qtd_itens // 6  # 3 caixas cheias\nsobra = qtd_itens % 6    # 5 itens avulsos\nprint(caixas, sobra)",
                explanation = "A divisão inteira calculou os lotes completos e o módulo reteve o excedente fracionário."
            )
        ),
        goldTip = "Cuidado com o acento circunflexo ^! Em Python, 2 ^ 3 NÃO é 8; é a operação lógica binária XOR que resulta em 1! Para potências, use sempre dois asteriscos: 2 ** 3!"
    )

    // =========================================================================
    // AULA py_b2_l2: Controle com if, elif e else
    // =========================================================================
    private val bundlePyB2L2 = PythonDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
Até este momento, nossos programas seguiam um fluxo linear e cego: executavam a primeira linha, depois a segunda, até a última sem qualquer poder de escolha. No entanto, a verdadeira inteligência de um software reside na sua capacidade de **tomar decisões**: escolher caminhos alternativos de execução com base no estado das variáveis e nas condições do ambiente. Em Python, as estruturas condicionais `if`, `elif` e `else` governam esse fluxo, estruturadas pela assinatura visual mais famosa da linguagem: a **Indentação Obrigatória**.

## 2. Objetivos de Aprendizagem
- Compreender e aplicar a estrutura condicional encadeada `if`, `elif` e `else`.
- Dominar os seis operadores relacionais de comparação: `==`, `!=`, `<`, `>`, `<=` e `>=`.
- Combinar condições lógicas complexas com os operadores booleanos `and`, `or` e `not`.
- Compreender o rigor da indentação (4 espaços) como delimitador oficial de blocos de código em substituição às chaves `{}`.

## 3. Pré-requisitos
- Tipos primitivos, especialmente booleanos (`bool`) (Aula py_b1_l2).
- Operadores aritméticos e expressões de avaliação (Aula py_b2_l1).

## 4. Explicação Teórica Aprofundada
### A Indentação Semântica do Python
Na maioria das linguagens de programação (C, Java, JavaScript), blocos de código são delimitados por chaves `{ ... }` e a indentação serve apenas como cosmética. Em Python, **a indentação define a estrutura hierárquica do código**.
- A convenção oficial do PEP 8 estipula rigorosamente **4 espaços em branco** para cada nível de indentação (nunca misture tabs com espaços!).
- Toda estrutura condicional termina sua declaração com dois pontos (`:`), que sinaliza o início do bloco indentado filho.

### A Anatomia do Desvio Condicional
1. **`if` (Se):** Avalia uma expressão booleana. Se a condição for `True`, executa o bloco interno; se for `False`, salta o bloco.
2. **`elif` (Senão Se):** Abreviação de *else if*. Permite testar múltiplas condições mutualmente exclusivas em sequência ordenada. Assim que uma das condições é satisfeita, seu bloco é executado e todas as subsequentes são ignoradas.
3. **`else` (Senão):** O ramo de escape final obrigatório quando **nenhuma** das condições anteriores avaliou para `True`.

### Operadores de Comparação
- `==`: Igualdade de valor (Cuidado: não confunda com `=` que é atribuição!).
- `!=`: Diferente de.
- `<` e `>`: Menor que e Maior que.
- `<=` e `>=`: Menor ou igual e Maior ou igual.
*Recurso Elegante do Python:* Comparações encadeadas matemáticas são perfeitamente válidas:
```python
if 18 <= idade < 65: # Equivalente a (18 <= idade) and (idade < 65)
```

### Operadores Lógicos Booleanos
- **`and` (E Lógico):** Retorna `True` se, e somente se, **ambas** as condições forem verdadeiras.
- **`or` (OU Lógico):** Retorna `True` se **pelo menos uma** das condições for verdadeira.
- **`not` (Negação Lógica):** Inverte o valor de verdade: transforma `True` em `False` e vice-versa.
*Curto-Circuito Lógico (Short-Circuit Evaluation):*
No operador `and`, se a primeira condição for `False`, o Python nem sequer avalia a segunda. No `or`, se a primeira for `True`, a segunda é ignorada.

## 5. Definições e Conceitos
- **Fluxo de Controle:** A ordem na qual instruções individuais são avaliadas pelo interpretador.
- **Bloco de Código:** Conjunto coeso de declarações executadas conjuntamente sob o mesmo recuo de margem.
- **Operador Ternário:** Expressão condicional inline na forma `valor_se_verdadeiro if condicao else valor_se_falso`.

## 6. Exemplos Resolvidos Passo a Passo
### Exemplo 1: Classificação de Faixa Etária Encadeada
```python
idade = 17

if idade < 12:
    categoria = "Infantil"
elif idade < 18:
    categoria = "Juvenil"
elif idade < 60:
    categoria = "Adulto"
else:
    categoria = "Idoso"

print(f"Categoria: {categoria}")
```
- **Passo a Passo:**
  1. `idade < 12` (17 < 12) avalia para `False`. Salta o primeiro bloco.
  2. `idade < 18` (17 < 18) avalia para `True`. Atribui `"Juvenil"` à variável `categoria`.
  3. Como o `elif` foi atendido, o Python encerra a estrutura imediatamente sem testar os ramos inferiores.

### Exemplo 2: Operadores Lógicos e Validação de Login
```python
usuario = "admin"
senha = "123"
autenticado = False

if usuario == "admin" and senha == "123":
    autenticado = True
    print("Acesso autorizado com privilégios de administrador.")
else:
    print("Credenciais inválidas. Tente novamente.")
```
- **Explicação:** O operador `and` exige a correspondência simultânea do nome de usuário e da senha para liberar a entrada.

## 7. Aplicações Práticas e Modelagem
- **Roteamento de Tráfego em APIs Web:** Em controladores de microsserviços, árvores condicionais inspecionam o cabeçalho `Authorization` e o método HTTP (`GET`, `POST`, `DELETE`) para despachar requisições.
- **Sistemas de Pontuação de Crédito Financeiro:** Algoritmos bancários avaliam renda mensal, histórico de pagamentos e score Serasa encadeando testes lógicos para aprovação de empréstimos.

## 8. Exercícios de Fixação
1. Qual é o erro no código: `if x = 10: print("Dez")`?
2. Se `a = True` e `b = False`, qual o resultado de `not a or b`?
3. Como reescrever `if media >= 7: situacao = "Aprovado" else: situacao = "Reprovado"` em uma única linha usando operador ternário?

## 9. Correção Comentada dos Exercícios
1. Foi utilizado o operador de atribuição simples `=` em vez do operador de comparação de igualdade `==`. O código lançará um `SyntaxError`.
2. Pela precedência lógica, o `not` é avaliado antes do `or`: `not True` vira `False`. A expressão vira `False or False`, cujo resultado final é `False`.
3. `situacao = "Aprovado" if media >= 7 else "Reprovado"`.

## 10. Desafio de Domínio
Construa uma estrutura condicional robusta que classifique um triângulo a partir de três lados informados `a`, `b` e `c`. Primeiro teste a condição de existência geométrica (\( a < b + c \), \( b < a + c \), \( c < a + b \)); se for válido, classifique-o em Equilátero, Isósceles ou Escaleno.

## 11. Resumo e Pontos-Chave
- Use `if` para a condição inicial, `elif` para condições intermediárias e `else` para a saída padrão.
- A indentação de 4 espaços define onde começa e termina cada bloco de código.
- Operador de igualdade é `==`; atribuição é `=`.
- `and` exige tudo verdadeiro; `or` exige ao menos um; `not` inverte o sinal.

## 12. Referências Bibliográficas
- PYTHON SOFTWARE FOUNDATION. *The Python Tutorial: More Control Flow Tools - if Statements*. Disponível em: https://docs.python.org/3/tutorial/controlflow.html#if-statements.
- MALAN, David J. *CS50's Introduction to Programming with Python*. Harvard University, 2023. Lecture 1: "Conditionals".
- LUTZ, Mark. *Learning Python*. 5th ed. O'Reilly Media, 2013. Chapter 12: "if Tests and Syntax Rules".
        """.trimIndent(),
        functionsAndSyntax = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Estrutura if / elif / else",
                syntax = "if cond1:\n    bloco1\nelif cond2:\n    bloco2\nelse:\n    bloco_padrao",
                description = "Desvio condicional encadeado por avaliação de predicados booleanos.",
                parametersInfo = "A indentação de 4 espaços delimita o escopo de cada bloco."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Operadores Lógicos",
                syntax = "and (conjunção), or (disjunção), not (negação)",
                description = "Combinação algébrica de expressões lógicas com avaliação em curto-circuito.",
                parametersInfo = "Possuem precedência: not > and > or."
            )
        ),
        codeExamples = listOf(
            LessonExample(
                title = "Classificação Condicional com Validação Lógica",
                codeOrFormula = "nota = 8.5\nif nota >= 9.0:\n    print('Excelente!')\nelif nota >= 7.0:\n    print('Aprovado!')\nelse:\n    print('Recuperação')",
                explanation = "A estrutura testa os limites em ordem decrescente garantindo execução do bloco correto."
            )
        ),
        goldTip = "O erro mais comum ao usar condicionais é usar = (um igual) no if. Lembre-se: = ATRIBUI valor a uma variável; para COMPARAR igualdade você DEVE usar == (dois iguais)!"
    )

    // =========================================================================
    // AULA py_b3_l1: Laço for com range()
    // =========================================================================
    private val bundlePyB3L1 = PythonDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
Imagine a necessidade de processar uma lista com 100.000 clientes, enviar relatórios automáticos diariamente ou treinar um modelo de Machine Learning por 500 épocas. Seria impensável copiar e colar o mesmo trecho de código milhares de vezes. Os **Laços de Repetição (Loops)** são as estruturas que concedem poder sobre-humano aos programas, executando tarefas repetitivas em frações de milissegundo com precisão cirúrgica. Em Python, o laço `for` atua como um iterador universal de alto nível, operando em perfeita harmonia com o gerador aritmético `range()`.

## 2. Objetivos de Aprendizagem
- Compreender o paradigma de iteração do laço `for` em Python como um percurso sobre objetos iteráveis.
- Dominar o construtor `range(start, stop, step)` com um, dois e três argumentos, entendendo o intervalo semiaberto `[start, stop)`.
- Controlar o fluxo interno do laço utilizando as instruções `break` (interrupção abrupta) e `continue` (salto de ciclo).
- Compreender a cláusula especial `else` associada a laços de repetição.
- Iterar sobre sequências numéricas diretas e regressivas (passos negativos).

## 3. Pré-requisitos
- Tipos de dados e variáveis (Aula py_b1_l2).
- Estruturas condicionais `if` e `else` (Aula py_b2_l2).

## 4. Explicação Teórica Aprofundada
### O Laço `for` em Python é um "For-Each"
Diferente de linguagens como C ou Java clássico, onde o laço `for` opera controlando um contador manual (`for(int i=0; i<n; i++)`), em Python o laço `for` é concebido para percorrer os elementos de qualquer coleção iterável diretamente:
```python
for item in colecao:
    processar(item)
```
Em cada ciclo, a variável de controle `item` recebe automaticamente a referência do próximo elemento da sequência até que todos tenham sido consumidos.

### A Função `range()` e o Intervalo Semiaberto
Quando desejamos repetir uma ação um número pré-determinado de vezes com base em contagens numéricas, usamos a função embutida `range()`.
O objeto `range` gera uma sequência aritmética imutável sob demanda (lazy evaluation), consumindo quantidade insignificante de memória RAM.
Suas três formas de invocação são:
1. **`range(stop)` (Um argumento):** Começa em 0 e incrementa de 1 em 1 até `stop - 1`:
   - `range(5)` gera: `0, 1, 2, 3, 4` (o valor final 5 **NÃO é incluído**!).
2. **`range(start, stop)` (Dois argumentos):** Começa no valor inicial `start` e vai até `stop - 1`:
   - `range(2, 6)` gera: `2, 3, 4, 5`.
3. **`range(start, stop, step)` (Três argumentos):** Permite configurar o passo de incremento:
   - `range(1, 10, 2)` gera ímpares: `1, 3, 5, 7, 9`.
   - **Contagem Regressiva:** Se o passo for negativo, percorre ao contrário:
     `range(5, 0, -1)` gera: `5, 4, 3, 2, 1`.

### Instruções de Controle: `break` e `continue`
- **`break`:** Interrompe a execução do laço imediatamente, transferindo o controle do programa para a primeira linha fora do loop.
- **`continue`:** Aborta apenas a iteração atual, ignorando todas as linhas subsequentes dentro do bloco e avançando imediatamente para o próximo ciclo.
- **A Cláusula `for ... else`:** O bloco `else` associado a um laço só é executado se o loop terminar **naturalmente** por esgotamento dos itens. Se o laço for interrompido prematuramente por um `break`, o `else` é ignorado. Ideal para buscas lineares!

## 5. Definições e Conceitos
- **Iterável (Iterable):** Qualquer objeto em Python capaz de retornar seus membros um por vez (ex: strings, listas, tuplas, ranges).
- **Intervalo Semiaberto \( [a, b) \):** Intervalo matemático que inclui o limite inferior \( a \), mas exclui o limite superior \( b \).
- **Avaliação Tardia (Lazy):** Geração de valores sob demanda sem pré-alocação de espaço total na memória.

## 6. Exemplos Resolvidos Passo a Passo
### Exemplo 1: Somatório com `range()`
Calcule a soma de todos os números inteiros de 1 a 100.
```python
soma_total = 0

# Para ir de 1 a 100, o stop deve ser 101!
for n in range(1, 101):
    soma_total += n

print("A soma de 1 a 100 é:", soma_total)
# Saída: A soma de 1 a 100 é: 5050
```
- **Explicação:** A variável acumuladora `soma_total` recebe sucessivamente cada elemento gerado pelo range. A fórmula de Gauss confirma que \( \frac{100 \times 101}{2} = 5050 \).

### Exemplo 2: Busca Linear com `break` e `else`
Verifique se um número existe em uma sequência e informe caso não seja encontrado.
```python
alvo = 7
numeros = range(1, 6) # Gera 1, 2, 3, 4, 5

for num in numeros:
    if num == alvo:
        print(f"Número {alvo} encontrado!")
        break
else:
    print(f"O número {alvo} NÃO está na lista.")
```
- **Explicação:** Como o número 7 nunca é atingido dentro do range de 1 a 5, o loop encerra naturalmente e o bloco `else` é disparado com precisão.

## 7. Aplicações Práticas e Modelagem
- **Treinamento de Redes Neurais em PyTorch:** Laços `for epoch in range(num_epochs):` iteram sobre dados de treinamento atualizando pesos sinápticos via gradiente descendente.
- **Automação Web e Web Scraping:** Bots percorrem páginas numeradas de e-commerces gerando URLs com `range(1, total_paginas + 1)` para extrair preços de produtos.

## 8. Exercícios de Fixação
1. Quais números são gerados por `range(3, 12, 3)`?
2. Como configurar um `range` para gerar a contagem regressiva de 10 até 1 inclusive?
3. Qual é a saída do código:
   ```python
   for i in range(5):
       if i == 2:
           continue
       print(i, end=" ")
   ```

## 9. Correção Comentada dos Exercícios
1. Os números gerados são: `3, 6, 9`. Começa em 3, salta de 3 em 3 e para antes de 12.
2. `range(10, 0, -1)`. O start é 10, o stop é 0 (para parar em 1) e o step é -1.
3. A saída será `0 1 3 4 `. Quando `i == 2`, o comando `continue` pulou a instrução `print` daquele ciclo, omitindo o número 2.

## 10. Desafio de Domínio
Construa um algoritmo que determine se um número inteiro positivo fornecido na variável `n` é **primo** utilizando um laço `for` com `range()` e a estrutura `for ... else`.

## 11. Resumo e Pontos-Chave
- `for item in iteravel:` é a forma pythônica padrão de repetição.
- `range(start, stop, step)` nunca inclui o valor de `stop`.
- `break` mata o laço; `continue` pula para a próxima iteração.
- O `else` do laço roda apenas se o loop terminar sem sofrer `break`.

## 12. Referências Bibliográficas
- PYTHON SOFTWARE FOUNDATION. *The Python Tutorial: The range() Function*. Disponível em: https://docs.python.org/3/tutorial/controlflow.html#the-range-function.
- MALAN, David J. *CS50's Introduction to Programming with Python*. Harvard University, 2023. Lecture 2: "Loops".
- LUTZ, Mark. *Learning Python*. 5th ed. O'Reilly Media, 2013. Chapter 13: "while and for Loops".
        """.trimIndent(),
        functionsAndSyntax = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "range()",
                syntax = "range(stop) \\quad | \\quad range(start, stop[, step])",
                description = "Gera uma sequência aritmética imutável de inteiros no intervalo semiaberto [start, stop).",
                parametersInfo = "stop é estritamente exclusivo. step define o incremento ou decremento."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "break e continue",
                syntax = "break (interrupção total) \\quad | \\quad continue (salto de ciclo)",
                description = "Comandos de controle de fluxo interno em laços de repetição.",
                parametersInfo = "Utilizados dentro de estruturas condicionais no corpo do loop."
            )
        ),
        codeExamples = listOf(
            LessonExample(
                title = "Iteração Filtrada com Range e Passo",
                codeOrFormula = "for par in range(0, 10, 2):\n    print(par, end=' ')\n# Saída: 0 2 4 6 8",
                explanation = "O step=2 faz o contador avançar de dois em dois números diretamente pelo gerador range."
            )
        ),
        goldTip = "O limite final do range() NUNCA é incluído! Se você quer que o laço execute de 1 até 10, o segundo parâmetro deve ser OBRIGATORIAMENTE 11: range(1, 11)!"
    )

    // =========================================================================
    // AULA py_b3_l2: Dicionários (Chave e Valor)
    // =========================================================================
    private val bundlePyB3L2 = PythonDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
Em listas e tuplas, os dados são organizados sequencialmente através de índices numéricos inteiros (`0, 1, 2, ...`). Mas como modelar entidades ricas do mundo real onde as informações são identificadas por rótulos significativos — como o cadastro de um usuário com nome, e-mail, CPF e saldo bancário? A estrutura de dados mais venerada e onipresente em todo o ecossistema Python é o **Dicionário (`dict`)**. Implementado sobre uma tabela hash ultraotimizada, ele permite recuperar, inserir e atualizar dados em tempo constante instantâneo.

## 2. Objetivos de Aprendizagem
- Compreender a estrutura de mapeamento Chave-Valor (*Key-Value Pair*) e a sintaxe com chaves `{}`.
- Reconhecer os requisitos de imutabilidade (*hashability*) para as chaves de um dicionário.
- Dominar as operações fundamentais: inserção, leitura segura com `.get()`, atualização e remoção (`pop()`, `del`).
- Iterar sobre dicionários utilizando os métodos `.keys()`, `.values()` e o desempacotamento de pares com `.items()`.

## 3. Pré-requisitos
- Tipos primitivos básicos e imutabilidade de strings e números (Aula py_b1_l2).
- Laço `for` de repetição (Aula py_b3_l1).

## 4. Explicação Teórica Aprofundada
### O Modelo Chave-Valor e Tabelas Hash
Um dicionário em Python (`dict`) é uma coleção mutável de pares **chave: valor**.
A genialidade do dicionário está no fato de que ele não precisa varrer todos os elementos da memória para encontrar um dado (como faria uma lista). O Python aplica uma função matemática chamada **função hash** (`hash(chave)`) que transforma a chave em um índice de memória direto, alcançando tempo de busca de complexidade \( O(1) \) (tempo constante).

### A Regra de Ouro das Chaves: Imutabilidade
- **Chaves (`keys`):** Devem ser obrigatoriamente objetos **imutáveis e hasheáveis** (ex: `str`, `int`, `float`, `tuple`). **Listas e dicionários NUNCA podem ser usados como chaves** porque seu conteúdo pode mudar, o que destruiria a integridade da tabela hash.
- **Valores (`values`):** Podem ser absolutamente qualquer tipo de dado do Python: números, strings, listas, funções ou até mesmo outros dicionários aninhados.

### Leitura com Colchetes vs. Método `.get()`
Se tentarmos ler uma chave que não existe no dicionário usando a notação direta de colchetes:
```python
cliente = {"nome": "Ana", "idade": 28}
print(cliente["email"]) # CRASH: KeyError: 'email'
```
O programa é interrompido imediatamente com um erro fatídico `KeyError`.
Para contornar esse risco com elegância profissional, utiliza-se o método `.get()`:
```python
# Retorna None caso a chave não exista, sem quebrar o programa:
email = cliente.get("email")

# Ou fornece um valor padrão de fallback:
email = cliente.get("email", "não informado")
```

### Métodos Essenciais de Iteração
- `dicionario.keys()`: Retorna uma visão iterável contendo todas as chaves.
- `dicionario.values()`: Retorna uma visão contendo todos os valores armazenados.
- `dicionario.items()`: Retorna pares ordenados `(chave, valor)`, permitindo o desempacotamento limpo no laço `for`:
  ```python
  for chave, valor in dicionario.items():
      print(f"{chave} -> {valor}")
  ```

## 5. Definições e Conceitos
- **Tabela Hash:** Estrutura de dados que mapeia chaves a valores usando uma função matemática de endereçamento direto.
- **JSON (JavaScript Object Notation):** O formato de intercâmbio de dados mais universal da internet, cuja sintaxe é praticamente idêntica à de um dicionário Python.
- **Dicionário Aninhado:** Dicionário cujos valores contêm outros dicionários internos, ideal para representar estruturas hierárquicas.

## 6. Exemplos Resolvidos Passo a Passo
### Exemplo 1: CRUD Básico em Dicionário
```python
# Criação do dicionário
produto = {
    "codigo": 1042,
    "nome": "Notebook Pro",
    "preco": 4500.00,
    "em_estoque": True
}

# 1. Leitura
print("Preço:", produto["preco"])

# 2. Atualização
produto["preco"] = 4250.00 # Concede desconto

# 3. Inserção de nova chave
produto["categoria"] = "Informática"

# 4. Remoção de chave
categoria_removida = produto.pop("categoria")

print("Dicionário final:", produto)
```
- **Explicação:** O acesso por chave é feito de forma imediata tanto para consulta quanto para mutação.

### Exemplo 2: Contagem de Frequência de Palavras
```python
frase = "o saber e a busca constante pelo saber"
palavras = frase.split()
frequencia = {}

for p in palavras:
    # Se a palavra já existe, soma 1; se não, inicializa com 0 e soma 1
    frequencia[p] = frequencia.get(p, 0) + 1

for termo, qtd in frequencia.items():
    print(f"'{termo}': {qtd}x")
```
- **Saída:**
  `'o': 1x`, `'saber': 2x`, `'e': 1x`, `'a': 1x`, `'busca': 1x`, `'constante': 1x`, `'pelo': 1x`.
- **Explicação:** O padrão idiomático `d.get(p, 0) + 1` constrói histogramas e contadores com máxima eficiência e legibilidade.

## 7. Aplicações Práticas e Modelagem
- **Consumo de APIs RESTful:** Respostas enviadas por serviços como GitHub, Twitter ou OpenAI retornam dados em formato JSON que o método `response.json()` converte diretamente em dicionários do Python.
- **Bancos de Dados NoSQL:** Documentos armazenados no MongoDB e Cassandra são essencialmente dicionários BSON/JSON indexados por identificadores únicos.

## 8. Exercícios de Fixação
1. Por que uma lista `[1, 2]` não pode ser usada como chave de um dicionário, mas uma tupla `(1, 2)` pode?
2. Dado `usuario = {"nome": "Leo"}`, o que ocorre ao executar `usuario.get("telefone", "Sem número")`?
3. Como iterar sobre um dicionário exibindo simultaneamente a chave e o valor em cada linha?

## 9. Correção Comentada dos Exercícios
1. Listas são objetos mutáveis (podem ter itens adicionados ou removidos); objetos mutáveis não possuem código hash fixo (são unhashable). Tuplas são imutáveis e possuem hash constante garantido.
2. O método retornará a string `"Sem número"`. Como a chave `"telefone"` inexiste, o valor padrão especificado no segundo argumento é retornado em vez de lançar `KeyError`.
3. Utilizando o método `.items()` no laço: `for chave, valor in usuario.items(): print(chave, valor)`.

## 10. Desafio de Domínio
Crie um cadastro de turma escolar onde a chave principal seja o nome do aluno e o valor seja uma lista de notas `[float]`. Em seguida, itere sobre o dicionário calculando a média aritmética de cada aluno e exiba quem foi aprovado (média \( \ge 7.0 \)).

## 11. Resumo e Pontos-Chave
- Dicionários armazenam associações `chave: valor` com busca \( O(1) \).
- Chaves devem ser obrigatoriamente imutáveis (`str`, `int`, `tuple`).
- Use `.get(chave, padrao)` para evitar quebras por `KeyError`.
- Use `.items()` para percorrer chaves e valores juntos no laço `for`.

## 12. Referências Bibliográficas
- PYTHON SOFTWARE FOUNDATION. *The Python Tutorial: Dictionaries*. Disponível em: https://docs.python.org/3/tutorial/datastructures.html#dictionaries.
- MALAN, David J. *CS50's Introduction to Programming with Python*. Harvard University, 2023. Lecture 2: "Dictionaries".
- LUTZ, Mark. *Learning Python*. 5th ed. O'Reilly Media, 2013. Chapter 8: "Dictionaries".
        """.trimIndent(),
        functionsAndSyntax = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "dict e Sintaxe {}",
                syntax = "d = {'chave': valor, ...} \\quad | \\quad d = dict()",
                description = "Coleção associativa mutável de pares chave-valor indexada por hashing.",
                parametersInfo = "As chaves devem ser tipos imutáveis (strings, números, tuplas)."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = ".get() e .items()",
                syntax = "d.get(chave, default) \\quad | \\quad for k, v in d.items():",
                description = "Métodos para acesso seguro sem KeyError e iteração estruturada de pares.",
                parametersInfo = "default é retornado caso a chave não esteja presente no dicionário."
            )
        ),
        codeExamples = listOf(
            LessonExample(
                title = "Acesso Seguro e Iteração em Dicionário",
                codeOrFormula = "aluno = {'nome': 'Bia', 'nota': 9.2}\nprint(aluno.get('turma', 'A-10'))\nfor k, v in aluno.items():\n    print(f'{k}: {v}')",
                explanation = "O método .get() evitou erro de chave inexistente e .items() desempacotou chave e valor no loop."
            )
        ),
        goldTip = "Evite acessar chaves de dicionários diretamente com colchetes d['chave'] se você não tiver 100% de certeza de que ela existe. Prefira sempre d.get('chave', valor_padrao) para blindar seu código contra KeyErrors inesperados!"
    )
}

