package com.example.data.python.didactic

import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample
import com.example.data.python.PythonDidacticBundle

/**
 * Conteúdo didático aprofundado do Nível Intermediário de Python (Parte 1: Aulas py_i1_l1 a py_i2_l1).
 * Estrutura rigorosa em 12 seções por aula, fundamentada na documentação oficial de Python e CS50.
 */
object PythonIntermediatePart1 {

    fun get(lessonId: String): PythonDidacticBundle? {
        return when (lessonId) {
            "py_i1_l1" -> bundlePyI1L1
            "py_i1_l2" -> bundlePyI1L2
            "py_i2_l1" -> bundlePyI2L1
            else -> null
        }
    }

    // =========================================================================
    // AULA py_i1_l1: F-Strings e Formatação Numérica
    // =========================================================================
    private val bundlePyI1L1 = PythonDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
Nas versões clássicas de Python, a formatação de strings dependia do operador `%` herdado da linguagem C (`"Valor: %.2f" % v`) ou do método `.format()`, ambos verbosos e propensos a erros de correspondência de argumentos. A partir do Python 3.6 (PEP 498), a linguagem foi agraciada com a funcionalidade que revolucionou a legibilidade do código: as **f-strings (Formatted String Literals)**. Com interpolação direta de variáveis, avaliação de expressões em tempo de execução e uma poderosa mini-linguagem de formatação numérica, as f-strings são hoje o padrão ouro inquestionável da indústria.

## 2. Objetivos de Aprendizagem
- Construir literais de string formatadas com o prefixo `f` ou `F`.
- Interpolar variáveis e expressões arbitrárias embutidas entre chaves `{...}`.
- Dominar os especificadores de formatação numérica para casas decimais (`:.2f`), separadores de milhar (`:,` ou `:_`) e porcentagens (`:.1%`).
- Controlar o alinhamento de texto e preenchimento de campos (`<`, `>`, `^`).
- Utilizar o modificador de depuração `{expressao=}` introduzido no Python 3.8.

## 3. Pré-requisitos
- Tipos de dados primitivos (`int`, `float`, `str`) (Aula py_b1_l2).
- Operadores aritméticos (Aula py_b2_l1).

## 4. Explicação Teórica Aprofundada
### O Mecanismo das F-Strings
Uma f-string é criada antecedendo as aspas de uma string pela letra `f` ou `F`:
```python
nome = "Mariana"
print(f"Olá, {nome}!")
```
Em tempo de execução, o interpretador substitui qualquer expressão contida dentro de chaves `{}` pelo seu valor textual avaliado. Diferente dos métodos antigos, qualquer código Python válido pode residir dentro das chaves: chamadas de funções, cálculos aritméticos e acessos a métodos de objetos:
```python
preco = 50.0
qtd = 3
print(f"Total: R${'$'} {preco * qtd:.2f}") # Avalia a multiplicação e formata com 2 casas
```

### A Mini-Linguagem de Especificação de Formato
Após a expressão interna, inserimos dois pontos `:` seguidos de códigos de especificação:
1. **Casas Decimais de Ponto Flutuante (`:.Nf`):**
   - `:.2f`: Arredonda e exibe com exatamente duas casas decimais.
   - `:.4f`: Exibe com quatro casas decimais.
2. **Separador de Milhar:**
   - `:,`: Usa vírgula para separar milhares: `f"{1000000:,}"` resulta em `'1,000,000'`.
   - `:_`: Usa sublinhado (padrão PEP 515): `f"{1000000:_}"` resulta em `'1_000_000'`.
3. **Formatação de Porcentagem (`:.N%`):**
   - Multiplica o número por 100 automaticamente e anexa o símbolo `%`:
     `f"{0.175:.1%}"` resulta em `'17.5%'`.
4. **Alinhamento e Preenchimento:**
   - `{texto:<15}`: Alinha à esquerda em um campo de 15 caracteres.
   - `{texto:>15}`: Alinha à direita.
   - `{texto:^15}`: Centraliza o texto.
   - `{numero:0>5}`: Preenche com zeros à esquerda até atingir 5 dígitos (`00042`).

### O Atalho de Depuração `{var=}` (Python 3.8+)
Para inspecionar variáveis durante a depuração sem digitar o nome duas vezes:
```python
x = 10
y = 25
print(f"{x=}, {y=}, {x+y=}")
# Saída: x=10, y=25, x+y=35
```

## 5. Definições e Conceitos
- **Interpolação de Strings:** A substituição dinâmica de marcadores de posição pelo valor real das variáveis avaliadas.
- **Especificador de Formato:** Instrução posicional após o símbolo `:` que dita regras tipográficas de exibição do dado.

## 6. Exemplos Resolvidos Passo a Passo
### Exemplo 1: Relatório Financeiro Formatado
```python
produto = "Teclado Mecânico RGB"
preco_unitario = 249.99
quantidade = 3
taxa_imposto = 0.085

subtotal = preco_unitario * quantidade
imposto = subtotal * taxa_imposto
total = subtotal + imposto

print(f"{'Item:':<12} {produto}")
print(f"{'Subtotal:':<12} R${'$'} {subtotal:>10.2f}")
print(f"{'Imposto (' + f'{taxa_imposto:.1%}' + '):':<12} R${'$'} {imposto:>10.2f}")
print("-" * 30)
print(f"{'Total:':<12} R${'$'} {total:>10.2f}")
```
- **Explicação:** O alinhamento à esquerda `<12` e à direita `>10.2f` garante colunas financeiras perfeitamente tabuladas no console.

### Exemplo 2: Preenchimento de Código com Zeros (Padding)
```python
id_cliente = 42
codigo_nf = f"NF-{id_cliente:0>6}"
print(codigo_nf) # Saída: NF-000042
```
- **Explicação:** O especificador `:0>6` instrui o Python a empacotar o número com zeros à esquerda até atingir o comprimento total de 6 caracteres.

## 7. Aplicações Práticas e Modelagem
- **Geração de Faturas e Relatórios PDF:** Sistemas de faturamento usam f-strings para compor linhas de notas fiscais onde os números devem ter estritamente duas casas decimais alinhadas à direita.
- **Consultas Dinâmicas de Logging e Mensageria:** Frameworks de logging (`logging.info(f"Processando usuário {uid}...")`) emitem traces de observabilidade com métricas estruturadas.

## 8. Exercícios de Fixação
1. Formate o número `valor = 1234.5678` em uma f-string com separador de milhar por vírgula e apenas duas casas decimais.
2. Como exibir a fração `acertos = 45 / 50` no formato de porcentagem com zero casas decimais?
3. O que será impresso pelo código `v = 7; print(f"{v:0>4}")`?

## 9. Correção Comentada dos Exercícios
1. `f"{valor:,.2f}"`, que resulta na string `'1,234.57'` (arredondamento correto da terceira casa).
2. `f"{acertos:.0%}"`, que resulta em `'90%'`.
3. Será impresso `'0007'`. O número 7 foi alinhado à direita com preenchimento de zeros até completar 4 dígitos.

## 10. Desafio de Domínio
Construa uma f-string que receba um dicionário representando uma cotação de moedas e monte uma tabela textual de três colunas (Moeda, Cotação em Reais com 4 casas decimais e Variação Percentual com sinal explícito `+` ou `-`).

## 11. Resumo e Pontos-Chave
- F-strings usam o prefixo `f"..."` com interpolação direta em `{expressao}`.
- `:.2f` arredonda para 2 casas decimais de float.
- `:.1%` converte decimal em porcentagem com o símbolo `%`.
- `<` alinha à esquerda, `>` à direita, e `^` centraliza.
- Adicionar `=` após a variável (`{var=}`) imprime o nome e o valor para depuração rápida.

## 12. Referências Bibliográficas
- PYTHON SOFTWARE FOUNDATION. *PEP 498 – Literal String Interpolation*. 2015. Disponível em: https://peps.python.org/pep-0498/.
- PYTHON SOFTWARE FOUNDATION. *Format Specification Mini-Language*. Disponível em: https://docs.python.org/3/library/string.html#format-specification-mini-language.
- RAMALHO, Luciano. *Fluent Python*. 2nd ed. O'Reilly Media, 2022. Chapter 2: "An Array of Sequences".
        """.trimIndent(),
        functionsAndSyntax = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "f-strings",
                syntax = "f'Texto {variavel:especificador}'",
                description = "Interpolação direta e performática de expressões e variáveis em strings literais.",
                parametersInfo = "Prefixo f obrigatório antes das aspas de abertura."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Especificadores :.2f, :,, :.1%",
                syntax = "{val:.2f} (duas casas) \\quad | \\quad {val:,} (milhares) \\quad | \\quad {val:.1%} (porcentagem)",
                description = "Mini-linguagem de formatação numérica e tipográfica embutida.",
                parametersInfo = "Inserido imediatamente após os dois pontos dentro das chaves."
            )
        ),
        codeExamples = listOf(
            LessonExample(
                title = "Formatação Numérica e Depuração",
                codeOrFormula = "pi = 3.14159265\ntaxa = 0.075\nprint(f'{pi:.3f} | {taxa:.1%}')  # 3.142 | 7.5%",
                explanation = "O especificador arredondou pi para 3 casas e converteu a taxa decimal para porcentagem."
            )
        ),
        goldTip = "Para debugar rapidamente o valor de qualquer variável sem escrever texto extra, use o recurso {variavel=} nas f-strings! Exemplo: print(f'{saldo=}') exibirá 'saldo=150.0' no terminal instantaneamente!"
    )

    // =========================================================================
    // AULA py_i1_l2: Projeto: Cifra de Substituição
    // =========================================================================
    private val bundlePyI1L2 = PythonDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
Desde as campanhas militares da Roma Antiga até as modernas comunicações seguras por HTTPS, a necessidade de proteger mensagens contra olhares curiosos impulsionou o desenvolvimento da criptografia. Uma das técnicas pioneiras mais célebres da história é a **Cifra de Substituição (Cifra de César)**, onde cada caractere da mensagem é deslocado por uma quantidade fixa de posições no alfabeto. Neste projeto prático, você aplicará seus conhecimentos de strings, laços e as funções de conversão numérica `ord()` e `chr()` para construir um codificador e decodificador funcional completo.

## 2. Objetivos de Aprendizagem
- Compreender a representação de caracteres em computadores através da tabela ASCII e do padrão universal Unicode.
- Utilizar a função embutida `ord()` para obter o ponto de código numérico de um caractere.
- Utilizar a função embutida `chr()` para converter um código numérico de volta ao seu caractere correspondente.
- Aplicar o operador módulo `% 26` para implementar o fechamento cíclico do alfabeto (wrap-around de 'Z' para 'A').
- Preservar letras maiúsculas, minúsculas, pontuações e espaços sem corromper o texto original.

## 3. Pré-requisitos
- Manipulação de strings e f-strings (Aula py_i1_l1).
- Operadores aritméticos, especialmente módulo `%` (Aula py_b2_l1).
- Estruturas de controle `if/else` e laço `for` (Aulas py_b2_l2 e py_b3_l1).

## 4. Explicação Teórica Aprofundada
### O Padrão ASCII e Unicode
Computadores não compreendem letras; eles operam exclusivamente com números binários. Para que o caractere `'A'` seja exibido na tela, existe uma convenção de mapeamento internacional:
- Na tabela ASCII clássica, `'A'` corresponde ao número `65`, `'B'` a `66`, até `'Z'` correspondendo a `90`.
- As letras minúsculas ocupam outra faixa contígua: `'a'` é `97`, `'b'` é `98`, até `'z'` ser `122`.
- As funções nativas do Python para navegar nessa tabela são:
  - `ord(caractere)`: Recebe uma string de 1 caractere e retorna seu código numérico inteiro:
    `ord('A')` retorna `65`.
  - `chr(codigo_inteiro)`: Recebe um inteiro e retorna o caractere correspondente:
    `chr(65)` retorna `'A'`.

### A Mecânica Matemática da Cifra de César
Na Cifra de César, escolhemos uma chave secreta \( k \) (um número inteiro, tradicionalmente \( k = 3 \)).
Para codificar uma letra maiúscula com código original \( c \):
1. Normalizamos a letra para o índice de 0 a 25: \( \text{pos} = \text{ord}(c) - \text{ord}('A') \).
2. Adicionamos o deslocamento com a aritmética modular do alfabeto de 26 letras:
   \[ \text{nova\_pos} = (\text{pos} + k) \pmod{26} \]
   O operador \( \pmod{26} \) garante o "efeito carrossel": se a letra for `'Z'` (índice 25) e o deslocamento for 1, a nova posição será \( (25 + 1) \pmod{26} = 26 \pmod{26} = 0 \), retornando com perfeição para a letra `'A'`!
3. Reconvertemos para o código ASCII original:
   \[ \text{novo\_char} = \text{chr}(\text{nova\_pos} + \text{ord}('A')) \]

### A Operação Inversa de Decodificação
Para decifrar a mensagem, basta aplicar o mesmo algoritmo exato, subtraindo a chave \( k \) ou simplesmente deslocando pela chave complementar:
\[ k_{\text{dec}} = (26 - k) \pmod{26} \]

## 5. Definições e Conceitos
- **Criptografia Simétrica:** Método onde a mesma chave secreta é utilizada tanto para encriptar quanto para desencriptar a informação.
- **Aritmética Modular Cíclica:** Estrutura onde os números "dão a volta" após atingir um determinado valor fixo (o módulo).
- **Invariância de Caracteres Especiais:** Princípio de segurança e usabilidade onde espaços, números e pontuações não são cifrados, preservando a legibilidade da estrutura do texto.

## 6. Exemplos Resolvidos Passo a Passo
### Exemplo Completo: O Codificador de César
```python
def cifrar_cesar(texto, chave=3):
    resultado = ""
    for char in texto:
        if char.isupper():
            # Deslocamento para maiúsculas (base ord('A') = 65)
            pos_original = ord(char) - ord('A')
            nova_pos = (pos_original + chave) % 26
            resultado += chr(nova_pos + ord('A'))
        elif char.islower():
            # Deslocamento para minúsculas (base ord('a') = 97)
            pos_original = ord(char) - ord('a')
            nova_pos = (pos_original + chave) % 26
            resultado += chr(nova_pos + ord('a'))
        else:
            # Mantém espaços, números e pontuações inalterados
            resultado += char
    return resultado

# Teste de Criptografia
mensagem = "Ataque ao amanhecer, 06:00!"
cifrado = cifrar_cesar(mensagem, chave=3)
print("Texto Cifrado:", cifrado)
# Saída: Dwdtxh dr dpdqkhfhu, 06:00!

# Teste de Descriptografia (deslocando por -3)
decifrado = cifrar_cesar(cifrado, chave=-3)
print("Texto Decifrado:", decifrado)
# Saída: Ataque ao amanhecer, 06:00!
```
- **Explicação:** O código verifica a caixa da letra com `.isupper()` e `.islower()`, calcula a rotação cíclica pelo módulo 26 e preserva pontuações intactas.

## 7. Aplicações Práticas e Modelagem
- **Cifra ROT13 em Fóruns e Usenet:** A cifra ROT13 é uma variante direta de César com chave fixa \( k = 13 \), historicamente usada na internet para ocultar spoilers e soluções de quebra-cabeças sem intenção militar.
- **Fundamentos de Criptoanálise:** O estudo da cifra de substituição é a porta de entrada para a análise de frequência de letras, que quebrou cifras clássicas na Segunda Guerra Mundial (Enigma).

## 8. Exercícios de Fixação
1. Qual é o resultado de `ord('C') - ord('A')`?
2. O que acontece com a letra `'Z'` ao ser deslocada por 1 na Cifra de César com módulo 26?
3. Como decifrar uma mensagem codificada com chave 5 usando a mesma função de codificação?

## 9. Correção Comentada dos Exercícios
1. O resultado é `2` (pois 'C' é a terceira letra do alfabeto, com índice base zero: A=0, B=1, C=2).
2. O índice original de 'Z' é 25. Com deslocamento 1: \( (25 + 1) \% 26 = 26 \% 26 = 0 \). O índice 0 corresponde à letra `'A'`.
3. Basta invocar a função passando a chave negativa correspondente: `cifrar_cesar(mensagem, chave=-5)`.

## 10. Desafio de Domínio
Construa um algoritmo de **Ataque de Força Bruta (Brute-Force Cracker)** que receba uma mensagem cifrada desconhecida e imprima na tela todas as 25 decodificações possíveis numeradas de 1 a 25, permitindo que um usuário humano identifique a mensagem original em português.

## 11. Resumo e Pontos-Chave
- `ord(char)` converte caractere em código numérico ASCII/Unicode.
- `chr(codigo)` converte código numérico de volta em caractere.
- O operador `% 26` é o segredo matemático para garantir o retorno cíclico de 'Z' para 'A'.
- Sempre preserve maiúsculas, minúsculas e pontuações separadamente.

## 12. Referências Bibliográficas
- PYTHON SOFTWARE FOUNDATION. *Built-in Functions - ord(), chr()*. Disponível em: https://docs.python.org/3/library/functions.html.
- MALAN, David J. *CS50's Introduction to Computer Science*. Harvard University, 2023. Problem Set 2: "Caesar".
- SINGH, Simon. *O Livro dos Códigos: A ciência do sigilo do antigo Egito à criptografia quântica*. Record, 2000. Capítulo 1.
        """.trimIndent(),
        functionsAndSyntax = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "ord() e chr()",
                syntax = "ord(c) \\quad | \\quad chr(n)",
                description = "Conversão bidirecional entre caracteres e seus códigos numéricos Unicode/ASCII.",
                parametersInfo = "ord('A') = 65; chr(65) = 'A'."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Aritmética Modular de Cifra",
                syntax = "\\text{nova\\_pos} = (\\text{pos} + k) \\pmod{26}",
                description = "Rotação cíclica no alfabeto de 26 letras.",
                parametersInfo = "k é a chave inteira de deslocamento secreto."
            )
        ),
        codeExamples = listOf(
            LessonExample(
                title = "Conversão ASCII Bidirecional",
                codeOrFormula = "codigo = ord('K')       # 75\ncaractere = chr(codigo) # 'K'\nprint(codigo, caractere)",
                explanation = "A tabela Unicode atribui o inteiro 75 à letra maiúscula K de forma padronizada mundialmente."
            )
        ),
        goldTip = "Para calcular a posição de 0 a 25 de qualquer letra minúscula no alfabeto, basta subtrair ord('a'): pos = ord(char) - ord('a'). Para voltar à letra, basta somar ord('a') e aplicar chr()!"
    )

    // =========================================================================
    // AULA py_i2_l1: List Comprehension com Condicional
    // =========================================================================
    private val bundlePyI2L1 = PythonDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
Em linguagens tradicionais, para criar uma nova lista contendo os quadrados dos números pares de uma sequência existente, você precisa inicializar uma lista vazia, abrir um laço `for`, escrever um bloco condicional `if` indentado e invocar manualmente o método `.append()`. Em Python, essa tarefa de 5 linhas é condensada em uma única expressão elegante, altamente expressiva e matematicamente inspirada na notação de teoria dos conjuntos: a **List Comprehension (Compreensão de Listas)**.

## 2. Objetivos de Aprendizagem
- Escrever e interpretar a sintaxe idiomática da List Comprehension básica: `[expressao for item in iteravel]`.
- Filtrar dados aplicando cláusulas condicionais `if` ao final da compreensão: `[expr for item in iter if cond]`.
- Utilizar operadores ternários para mapeamento condicional (*if-else* antes do `for`).
- Compreender a superioridade de performance das compreensões em CPython comparadas ao método `.append()` em laços manuais.
- Evitar excessos de aninhamento para preservar a legibilidade do código (Zen do Python).

## 3. Pré-requisitos
- Laço `for` com `range()` e iteração (Aula py_b3_l1).
- Operadores relacionais e lógica booleana (Aula py_b2_l2).
- Operações elementares com listas em Python (`[]`, `.append()`).

## 4. Explicação Teórica Aprofundada
### Origem Matemática: Notação de Construtor de Conjuntos
Na teoria dos conjuntos, definimos o conjunto dos quadrados dos números pares como:
\[ S = \{ x^2 \mid x \in \mathbb{N}, \; x \text{ é par} \} \]
A sintaxe da List Comprehension do Python reflete essa exata notação matemática:
```python
s = [x**2 for x in numeros if x % 2 == 0]
```
A leitura é intuitiva: "Calcule `x**2` para cada `x` presente em `numeros`, desde que `x % 2 == 0` seja verdadeiro".

### A Anatomia Estrutural da Compreensão
Existem duas estruturas fundamentais que nunca devem ser confundidas:
1. **Filtro de Elementos (`if` no final):**
   ```python
   [expressao for item in iteravel if condicao]
   ```
   O `if` atua como uma peneira: somente os itens onde a condição avalia para `True` passam para o cálculo da expressão final. Itens falsos são descartados da lista resultante.
2. **Transformação Condicional Ternária (`if-else` no início):**
   ```python
   [valor_se_true if condicao else valor_se_false for item in iteravel]
   ```
   Aqui, **todos os itens** permanecem na lista, mas seu valor transformado depende do teste lógico:
   ```python
   rotulos = ["Par" if n % 2 == 0 else "Ímpar" for n in range(5)]
   # Resultado: ['Par', 'Ímpar', 'Par', 'Ímpar', 'Par']
   ```

### Por que List Comprehensions são Mais Rápidas?
Além de mais sucinta, a List Comprehension é consideravelmente mais performática em CPython do que um laço `for` tradicional com `.append()`. O motivo reside no nível do bytecode:
- No laço tradicional, o Python precisa fazer a resolução de atributo em tempo de execução para encontrar o método `.append` do objeto lista a cada ciclo e emitir chamadas de função custosas na pilha de execução.
- Na List Comprehension, o compilador aloca o buffer interno da lista em código C nativo otimizado e executa a inserção diretamente via instrução especializada de máquina `LIST_APPEND`.

## 5. Definições e Conceitos
- **List Comprehension:** Construção sintática concisa para criar novas listas baseadas em iteráveis existentes.
- **Código Pythônico (Pythonic):** Código que abraça as convenções, idioms e recursos exclusivos da linguagem Python priorizando clareza e elegância.
- **Efeito Colateral (Side Effect):** Compreensões devem ser puras (construir dados); nunca use List Comprehensions para executar ações que não retornem valor (como chamar `print()` apenas pelo efeito).

## 6. Exemplos Resolvidos Passo a Passo
### Exemplo 1: Filtragem e Transformação de Preços
Dada uma lista de preços em dólares, filtre apenas os produtos que custam mais de ${'$'} 50 e converta-os para reais com cotação de R${'$'} 5,20.
```python
precos_dolar = [15.0, 80.0, 45.0, 120.0, 50.0, 200.0]
cotacao = 5.20

# Resolução Tradicional Imperativa (4 linhas)
precos_reais_trad = []
for p in precos_dolar:
    if p > 50.0:
        precos_reais_trad.append(round(p * cotacao, 2))

# Resolução Elegante com List Comprehension (1 linha)
precos_reais = [round(p * cotacao, 2) for p in precos_dolar if p > 50.0]

print("Resultado:", precos_reais)
# Saída: [416.0, 624.0, 1040.0]
```
- **Explicação:** A cláusula `if p > 50.0` descartou os valores 15, 45 e 50. Os remanescentes foram multiplicados pela cotação e arredondados diretamente no buffer.

### Exemplo 2: Limpeza e Normalização de Strings
```python
entradas_sujas = ["  ana ", "CARLOS\n", " beatriz  ", "  "]

# Remove espaços em branco com .strip() e converte para título se não for vazio
nomes_limpos = [nome.strip().title() for nome in entradas_sujas if nome.strip()]
print(nomes_limpos)
# Saída: ['Ana', 'Carlos', 'Beatriz']
```
- **Explicação:** O `if nome.strip()` elimina strings vazias (avaliadas como falsy), enquanto `strip().title()` padroniza os nomes válidos.

## 7. Aplicações Práticas e Modelagem
- **Extração e Tratamento em Data Science:** Limpeza de colunas de texto em dataframes, remoção de valores nulos e extração de metadados em pipelines de ETL.
- **Processamento de Linguagem Natural (NLP):** Tokenização de textos onde se filtram stopwords (palavras irrelevantes como artigos e preposições) em uma única linha declarativa: `[w for w in palavras if w not in stopwords]`.

## 8. Exercícios de Fixação
1. Crie uma list comprehension que gere os cubos de todos os números ímpares entre 1 e 10.
2. Dada a lista de nomes `["Alice", "Bob", "Amanda", "Bruno"]`, filtre apenas os nomes que começam com a letra `"A"`.
3. Qual é a diferença fundamental entre `[x for x in lista if x > 0]` e `[x if x > 0 else 0 for x in lista]`?

## 9. Correção Comentada dos Exercícios
1. `[x**3 for x in range(1, 11) if x % 2 != 0]`, que produz `[1, 27, 125, 343, 729]`.
2. `[nome for nome in nomes if nome.startswith("A")]`, que produz `['Alice', 'Amanda']`.
3. A primeira expressão é um **filtro**: a lista resultante terá tamanho menor ou igual à original, contendo apenas números positivos. A segunda expressão é uma **transformação**: a lista resultante terá exatamente o mesmo tamanho da original, mantendo os positivos e substituindo os negativos por zero.

## 10. Desafio de Domínio
Dada uma matriz bidimensional (lista de listas):
```python
matriz = [[1, 2, 3], [4, 5, 6], [7, 8, 9]]
```
Escreva uma List Comprehension com laço duplo aninhado que achate (flatten) essa matriz em uma única lista unidimensional `[1, 2, 3, 4, 5, 6, 7, 8, 9]`.

## 11. Resumo e Pontos-Chave
- Sintaxe canônica: `[expressao for item in iteravel if condicao]`.
- Se tiver `else`, a estrutura condicional deve vir **antes** do `for`: `[A if cond else B for item in iter]`.
- É mais rápida que laços tradicionais devido à otimização em bytecode C.
- Mantenha a legibilidade: se a compreensão passar de duas linhas ou ficar confusa, prefira um laço tradicional (Zen do Python: "A legibilidade conta").

## 12. Referências Bibliográficas
- PYTHON SOFTWARE FOUNDATION. *The Python Tutorial: List Comprehensions*. Disponível em: https://docs.python.org/3/tutorial/datastructures.html#list-comprehensions.
- RAMALHO, Luciano. *Fluent Python*. 2nd ed. O'Reilly Media, 2022. Chapter 2: "Listcomprehensions and Generator Expressions".
- LUTZ, Mark. *Learning Python*. 5th ed. O'Reilly Media, 2013. Chapter 14: "List Comprehensions and Functional Basics".
        """.trimIndent(),
        functionsAndSyntax = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "List Comprehension com Filtro",
                syntax = "[expressao for item in iteravel if condicao]",
                description = "Geração declarativa de lista filtrada por predicado booleano.",
                parametersInfo = "Apenas elementos onde condicao é True são avaliados e inseridos."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "List Comprehension com Operador Ternário",
                syntax = "[expr_true if cond else expr_false for item in iteravel]",
                description = "Mapeamento condicional com tratamento alternativo de ramos.",
                parametersInfo = "Preserva a totalidade dos elementos da sequência de entrada."
            )
        ),
        codeExamples = listOf(
            LessonExample(
                title = "List Comprehension Filtrada e Transformada",
                codeOrFormula = "numeros = [1, 2, 3, 4, 5, 6]\npares_ao_quadrado = [x**2 for x in numeros if x % 2 == 0]\nprint(pares_ao_quadrado)  # [4, 16, 36]",
                explanation = "A condição if x % 2 == 0 selecionou os pares (2, 4, 6) e elevou-os ao quadrado em uma única instrução."
            )
        ),
        goldTip = "Regra de ouro de posicionamento: se o seu if for apenas um FILTRO (descartar itens), ele fica no FINAL. Se o seu if tiver um ELSE (trocar o valor do item), ele fica OBRIGATORIAMENTE no INÍCIO, antes do for!"
    )
}
