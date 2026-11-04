package com.example.data.python.didactic

import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample
import com.example.data.python.PythonDidacticBundle

/**
 * Conteúdo didático aprofundado do Nível Avançado de Python (Parte 2: Aulas py_a2_l1 a py_a2_l3).
 * Estrutura rigorosa em 12 seções por aula, fundamentada na documentação oficial de Python e CS50.
 */
object PythonAdvancedPart2 {

    fun get(lessonId: String): PythonDidacticBundle? {
        return when (lessonId) {
            "py_a2_l1" -> bundlePyA2L1
            "py_a2_l2" -> bundlePyA2L2
            "py_a2_l3" -> bundlePyA2L3
            else -> null
        }
    }

    // =========================================================================
    // AULA py_a2_l1: Geradores com yield
    // =========================================================================
    private val bundlePyA2L1 = PythonDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
O que acontece se você precisar processar um arquivo de log com 50 gigabytes ou iterar sobre uma sequência matemática com 100 milhões de registros em um computador que possui apenas 8 gigabytes de memória RAM? Se você tentar carregar todos os dados em uma lista convencional (`[...]`), o sistema operacional esgotará a memória imediatamente lançando um temido `MemoryError`. Para solucionar essa limitação física com extrema sofisticação, Python introduziu os **Geradores (Generators)** e a palavra-chave **`yield`**, viabilizando fluxos contínuos de dados sob demanda com consumo constante de memória na ordem de meros kilobytes.

## 2. Objetivos de Aprendizagem
- Compreender o Protocolo Iterador do Python: métodos especiais `__iter__()` e `__next__()`.
- Dominar a mecânica da instrução `yield` e o congelamento/restauração do frame de execução de funções.
- Medir e contrastar o consumo de memória RAM entre listas materializadas e objetos geradores com o módulo `sys`.
- Construir Expressões Geradoras (*Generator Expressions*) com sintaxe de parênteses: `(expr for item in iter)`.
- Gerenciar fluxos de dados infinitos sem travar o computador.

## 3. Pré-requisitos
- Funções, parâmetros e instrução `return` (Módulo Básico).
- Laço `for` e conceito de iterabilidade (Aula py_b3_l1).
- List Comprehensions (Aula py_i2_l1).

## 4. Explicação Teórica Aprofundada
### Funções Tradicionais vs. Funções Geradoras
- **Função Tradicional com `return`:** Executa seu bloco de código do início ao fim. Ao encontrar o `return`, ela calcula o resultado, devolve o valor, **destrói completamente seu frame de variáveis locais na pilha de execução** e encerra sua existência.
- **Função Geradora com `yield`:** Se uma função contiver a palavra-chave `yield`, o interpretador do Python a transforma internamente em uma fábrica de geradores. Ao ser invocada, ela **não executa nenhuma linha de código imediatamente**; ela retorna um objeto gerador (`generator object`).

### O Mecanismo do `yield` e a Execução Preguiçosa (Lazy)
Quando solicitamos o próximo valor do gerador (seja através de um laço `for` ou chamando a função embutida `next()`):
1. O código da função é executado até encontrar a primeira instrução `yield`.
2. A função produz o valor indicado à direita de `yield` e o entrega para o chamador.
3. **A mágica do Python:** A função **NÃO é destruída**. O interpretador "congela" o estado exato da função: todas as variáveis locais, o ponteiro de instrução e o estado do laço são salvos na memória.
4. Na próxima chamada de `next()`, a função "acorda" exatamente de onde parou e continua sua execução até encontrar o próximo `yield`.
5. Quando o código chega ao final natural da função sem mais yields, o Python lança automaticamente a exceção `StopIteration`, sinalizando ao laço `for` que a sequência terminou.

### Economia Massiva de Memória
Considere gerar os quadrados de 10 milhões de números:
```python
# Abordagem com Lista (Eager): aloca TODOS os 10 milhões de números na memória de uma vez!
# Consumo: ~800 Megabytes de RAM
lista_quadrados = [x**2 for x in range(10_000_000)]

# Abordagem com Gerador (Lazy): calcula 1 número de cada vez sob demanda!
# Consumo: ~120 Bytes de RAM (fixo e constante!)
gerador_quadrados = (x**2 for x in range(10_000_000))
```
O gerador consome a mesma quantidade minúscula de memória quer esteja processando 10 elementos ou 1 trilhão de elementos!

## 5. Definições e Conceitos
- **Lazy Evaluation (Avaliação Preguiçosa):** Estratégia de computação que adia a avaliação de uma expressão até que seu valor seja estritamente necessário.
- **StopIteration:** Exceção nativa lançada pelo método `__next__()` quando não há mais elementos a serem produzidos pelo iterador.
- **Generator Expression:** Sintaxe análoga à list comprehension, mas delimitada por parênteses `(...)`, que produz um gerador preguiçoso em vez de uma lista estática.

## 6. Exemplos Resolvidos Passo a Passo
### Exemplo 1: Sequência Infinita de Fibonacci com `yield`
```python
def gerador_fibonacci():
    a, b = 0, 1
    while True:
        yield a
        a, b = b, a + b

# O gerador representa uma sequência matematicamente infinita!
fib = gerador_fibonacci()

# Extraindo apenas os primeiros 7 termos sob demanda
for _ in range(7):
    print(next(fib), end=" ")
# Saída: 0 1 1 2 3 5 8
```
- **Explicação:** O laço `while True` não causa travamento da máquina porque a função congela a cada `yield`, aguardando a chamada de `next()`.

### Exemplo 2: Pipeline de Processamento de Arquivos Grandes
```python
def ler_linhas_grandes(nome_arquivo):
    with open(nome_arquivo, "r", encoding="utf-8") as f:
        for linha in f:
            yield linha.strip()

# Consumo de memória equivalente a UMA ÚNICA linha por ciclo!
for linha_limpa in ler_linhas_grandes("servidor.log"):
    if "ERROR" in linha_limpa:
        print("Alerta de falha:", linha_limpa)
        break
```
- **Explicação:** O arquivo é lido linha por linha através do buffer do gerador sem jamais ser carregado integralmente na RAM.

## 7. Aplicações Práticas e Modelagem
- **Pipelines de Big Data e Machine Learning:** Carregamento em lotes (batching) de imagens médicas em TensorFlow e PyTorch onde as imagens são lidas do disco e pré-processadas sob demanda em lotes de 32 ou 64 amostras via geradores.
- **Consumo de Eventos em Tempo Real (Event Streaming):** Processamento de tópicos Kafka e webhooks onde as mensagens chegam continuamente sem fim pré-determinado.

## 8. Exercícios de Fixação
1. Qual é a principal diferença entre uma função que usa `return` e uma que usa `yield`?
2. Como criar uma Generator Expression que gere números pares de 0 a 100?
3. O que acontece se invocarmos a função `next()` em um gerador que já atingiu o final de sua execução?

## 9. Correção Comentada dos Exercícios
1. A função com `return` encerra sua execução e destrói seu escopo local na pilha. A função com `yield` suspende a execução, entrega o valor e congela seu estado na memória para continuar de onde parou na próxima iteração.
2. `(x for x in range(101) if x % 2 == 0)`. Notar os parênteses que diferenciam o gerador de uma lista com colchetes.
3. O interpretador lançará a exceção `StopIteration`, informando que o protocolo iterador não possui mais valores a produzir.

## 10. Desafio de Domínio
Crie um gerador customizado `contador_regressivo(n)` que aceite um número inteiro positivo e faça a contagem até zero com `yield`. Demonstre como encadear esse gerador com uma segunda função geradora que dobre os valores produzidos (*Pipeline de Geradores*).

## 11. Resumo e Pontos-Chave
- Funções com `yield` retornam objetos geradores preguiçosos (*lazy*).
- O estado da função é congelado e retomado a cada chamada de `next()`.
- Economia extrema de memória RAM: gera valores um por vez.
- Permite modelar sequências de dados infinitas.
- Generator expressions usam parênteses: `(expr for item in iter)`.

## 12. Referências Bibliográficas
- PYTHON SOFTWARE FOUNDATION. *The Python Tutorial: Generators & Generator Expressions*. Disponível em: https://docs.python.org/3/tutorial/classes.html#generators.
- RAMALHO, Luciano. *Fluent Python*. 2nd ed. O'Reilly Media, 2022. Chapter 17: "Iterators, Generators, and Classic Coroutines".
- LUTZ, Mark. *Learning Python*. 5th ed. O'Reilly Media, 2013. Chapter 20: "Comprehensions and Generations".
        """.trimIndent(),
        functionsAndSyntax = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Instrução yield",
                syntax = "def gerador():\n    yield valor",
                description = "Transforma uma função comum em um gerador de valores sob demanda (lazy evaluation).",
                parametersInfo = "Pausa a execução do frame local salvando variáveis para a próxima iteração."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Função next()",
                syntax = "next(gerador[, default])",
                description = "Avança o gerador até o próximo yield e recupera o valor produzido.",
                parametersInfo = "Lança StopIteration se a sequência tiver terminado sem valor padrão configurado."
            )
        ),
        codeExamples = listOf(
            LessonExample(
                title = "Gerador Preguiçoso com yield",
                codeOrFormula = "def pares_ate(limite):\n    n = 0\n    while n <= limite:\n        yield n\n        n += 2\nfor p in pares_ate(6):\n    print(p, end=' ')  # 0 2 4 6",
                explanation = "A cada ciclo do laço for, o interpretador chama next() internamente e recebe o próximo número sem alocar lista."
            )
        ),
        goldTip = "Precisa processar milhões de registros ou ler arquivos gigantescos? Troque os colchetes [...] da sua List Comprehension por parênteses (...)! Isso transforma sua lista instantaneamente em uma Generator Expression, derrubando o uso de memória de gigabytes para meros bytes!"
    )

    // =========================================================================
    // AULA py_a2_l2: Expressões Regulares (re)
    // =========================================================================
    private val bundlePyA2L2 = PythonDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
Como validar se uma string digitada por um usuário é um endereço de e-mail perfeitamente legítimo, extrair todos os números de telefone de um documento com 500 páginas ou mascarar CPFs sensíveis para adequação à LGPD? Tentar resolver esses desafios utilizando métodos básicos de string como `.find()` e `.split()` resulta em dezenas de linhas de código frágeis e cheias de bugs. Para o processamento de texto de nível cirúrgico, a computação desenvolveu as **Expressões Regulares (RegEx)**, disponibilizadas na biblioteca padrão do Python através do poderoso módulo `re`.

## 2. Objetivos de Aprendizagem
- Compreender o conceito de autômatos finitos e casamento formal de padrões textuais com o módulo `re`.
- Dominar os metacaracteres essenciais de âncora e classes: `^` (início), `${'$'}` (fim), `.` (qualquer caractere), `\d` (dígito), `\w` (alfanumérico) e `\s` (espaço).
- Controlar a repetição com quantificadores: `+` (1 ou mais), `*` (0 ou mais), `?` (0 ou 1) e `{n, m}` (faixas).
- Capturar dados estruturados utilizando Grupos de Captura com parênteses `()`.
- Distinguir e aplicar as funções fundamentais: `re.search()`, `re.match()`, `re.findall()` e `re.sub()`.
- Utilizar Raw Strings (`r"..."`) para neutralizar conflitos de escape de barras invertidas.

## 3. Pré-requisitos
- Manipulação e métodos fundamentais de strings (Aula py_b1_l1).
- Estruturas de controle `if/else` (Aula py_b2_l2).

## 4. Explicação Teórica Aprofundada
### O Uso Obrigatório de Raw Strings (`r"..."`)
Em expressões regulares, o caractere de barra invertida `\` é o prefixo de sequências especiais (ex: `\d` para dígitos numéricos). No entanto, em strings normais do Python, `\` também é o caractere de escape (`\n` para nova linha). Para evitar que o interpretador tente interpretar os escapes antes de entregá-los ao motor de RegEx, **toda expressão regular em Python deve ser declarada como uma Raw String prefixada com `r`**:
```python
padrao = r"\d{3}\.\d{3}\.\d{3}-\d{2}" # Padrão de CPF blindado
```

### O Alfabeto dos Metacaracteres
1. **Âncoras de Posição:**
   - `^`: Exige que o padrão case exatamente no **início** do texto.
   - `${'$'}`: Exige que o padrão case exatamente no **final** do texto.
2. **Classes de Caracteres Pré-definidas:**
   - `\d`: Qualquer dígito decimal de `0` a `9` (equivalente a `[0-9]`).
   - `\D`: Qualquer caractere que **NÃO** seja dígito.
   - `\w`: Qualquer caractere alfanumérico ou sublinhado `[a-zA-Z0-9_]`.
   - `\s`: Qualquer caractere de espaço em branco (espaço, tabulação `\t`, nova linha `\n`).
   - `.`: Qualquer caractere isolado (exceto quebra de linha).
3. **Quantificadores (Repetições):**
   - `+`: Uma ou mais ocorrências (guloso).
   - `*`: Zero ou mais ocorrências.
   - `?`: Zero ou uma ocorrência (opcional).
   - `{n}`: Exatamente \( n \) ocorrências.
   - `{min, max}`: Intervalo delimitado de ocorrências.

### As Funções Fundamentais do Módulo `re`
- **`re.search(padrao, texto)`:** Varre o texto inteiro procurando pela **primeira ocorrência** do padrão. Retorna um objeto `Match` se encontrar, ou `None` se falhar.
- **`re.match(padrao, texto)`:** Casamento estrito a partir do primeiro caractere da string.
- **`re.findall(padrao, texto)`:** Varre todo o texto e retorna uma **lista contendo todas as correspondências** encontradas.
- **`re.sub(padrao, substituto, texto)`:** Localiza todas as ocorrências do padrão e as substitui pela nova string (Search & Replace avançado).

## 5. Definições e Conceitos
- **Expressão Regular (RegEx):** Sequência formal de caracteres que define um padrão de busca textual.
- **Match Object:** Objeto retornado quando uma busca tem sucesso, contendo métodos como `.group()` (o texto casado) e `.span()` (os índices de início e fim).
- **Grupos de Captura `()`:** Mecanismo para fatiar o padrão em subpartes isoladas recuperáveis individualmente com `match.group(1)`, `match.group(2)`.

## 6. Exemplos Resolvidos Passo a Passo
### Exemplo 1: Validador Completo de E-mails
```python
import re

def validar_email(email):
    # Padrão: início, letras/números/pontos, @, domínio, ponto, extensão de 2 a 4 letras, fim
    padrao = r"^[\w\.-]+@[\w\.-]+\.[a-zA-Z]{2,4}${'$'}"
    if re.search(padrao, email):
        return True
    return False

# Testes
print(validar_email("aluno.knowverse@gmail.com")) # True
print(validar_email("email_invalido@com"))         # False (falta extensão com ponto)
print(validar_email("@semusuario.com"))            # False
```
- **Explicação:** As âncoras `^` e `${'$'}` garantem que a string inteira deve casar com a regra, rejeitando entradas sujas com textos antes ou depois.

### Exemplo 2: Extração Estruturada com Grupos de Captura
Extraia o código de área (DDD) e o número de telefones no formato `(11) 98765-4321`.
```python
import re

texto = "Contatos de suporte: (11) 98765-4321 e filial (21) 91234-5678."
padrao = r"\((\d{2})\)\s+(\d{4,5}-\d{4})"

for match in re.finditer(padrao, texto):
    ddd = match.group(1)
    numero = match.group(2)
    print(f"DDD: {ddd} | Telefone: {numero}")
```
- **Saída:**
  `DDD: 11 | Telefone: 98765-4321`
  `DDD: 21 | Telefone: 91234-5678`
- **Explicação:** Os parênteses na RegEx criaram grupos de captura independentes recuperados via `group(1)` e `group(2)`.

## 7. Aplicações Práticas e Modelagem
- **Sanitização de Dados e LGPD:** Mascaramento de dados sensíveis em logs: `re.sub(r"\d{3}\.\d{3}\.\d{3}-\d{2}", "***.***.***-**", texto)`.
- **Compiladores e Análise Léxica (Lexers):** Identificação de palavras-chave, identificadores e literais numéricos em linguagens de programação baseada em tabelas de transição de RegEx.

## 8. Exercícios de Fixação
1. Por que é indispensável utilizar o prefixo `r` antes das strings de Expressões Regulares em Python?
2. O que representa o padrão `r"^\d{4}${'$'}"`?
3. Qual função do módulo `re` é utilizada para substituir todas as ocorrências de um padrão por outro texto?

## 9. Correção Comentada dos Exercícios
1. O prefixo `r` cria uma Raw String, que instrui o interpretador Python a não processar caracteres de escape como `\n` ou `\t`, permitindo que barras invertidas como `\d` ou `\w` cheguem puras ao motor de RegEx.
2. Casamento de strings que contenham **exatamente quatro dígitos numéricos** do início ao fim (como um ano ou um PIN bancário).
3. A função `re.sub(padrao, substituto, texto)`.

## 10. Desafio de Domínio
Construa uma expressão regular com grupos de captura nomeados (`(?P<nome>...)`) capaz de receber uma data no formato brasileiro `"DD/MM/AAAA"` e convertê-la via `re.sub()` para o formato internacional ISO 8601 `"AAAA-MM-DD"`.

## 11. Resumo e Pontos-Chave
- Use sempre Raw Strings: `r"padrao"`.
- `re.search()` busca a primeira ocorrência; `re.findall()` extrai todas; `re.sub()` substitui.
- `^` crava no início; `${'$'}` crava no fim.
- `\d` é dígito, `\w` é caractere de palavra, `\s` é espaço.
- Parênteses `()` definem grupos de captura estruturados.

## 12. Referências Bibliográficas
- PYTHON SOFTWARE FOUNDATION. *re — Regular expression operations*. Disponível em: https://docs.python.org/3/library/re.html.
- MALAN, David J. *CS50's Introduction to Programming with Python*. Harvard University, 2023. Lecture 7: "Regular Expressions".
- FRIEDL, Jeffrey E. F. *Mastering Regular Expressions*. 3rd ed. O'Reilly Media, 2006.
        """.trimIndent(),
        functionsAndSyntax = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "re.search() e re.findall()",
                syntax = """re.search(r'padrao', texto) \\quad | \\quad re.findall(r'padrao', texto)""",
                description = "Pesquisa e extração de padrões formais de texto.",
                parametersInfo = "Retorna um Match object (search) ou lista de strings (findall)."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Metacaracteres Principais",
                syntax = """^ (início) ${'$'} (fim) \\d (dígito) \\w (letras/números) + (1+) * (0+)""",
                description = "Blocos construtores de padrões léxicos de expressões regulares.",
                parametersInfo = "Utilize raw strings r'...' para evitar problemas de escape."
            )
        ),
        codeExamples = listOf(
            LessonExample(
                title = "Busca de Padrão com re.search",
                codeOrFormula = """import re\ntexto = 'Pedido 1042 aprovado'\nm = re.search(r'\\d+', texto)\nif m: print('Código:', m.group())  # 1042""",
                explanation = """O padrão \\d+ localizou a sequência contínua de dígitos dentro da frase."""
            )
        ),
        goldTip = "Sempre declare suas expressões regulares com o prefixo r antes das aspas: r'\\d{3}'. Se você esquecer o r, o Python pode corromper suas barras invertidas antes mesmo da RegEx começar a rodar!"
    )

    // =========================================================================
    // AULA py_a2_l3: Estrutura de Dados: Pilha (Stack)
    // =========================================================================
    private val bundlePyA2L3 = PythonDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
Como o botão "Desfazer" (Ctrl+Z) do seu editor de texto reverte suas ações exatamente na ordem inversa em que foram executadas? Como o interpretador de uma linguagem gerencia as chamadas de funções aninhadas na memória (Call Stack) ou como o navegador web retorna à página anterior ao clicar no botão "Voltar"? Todas essas funcionalidades cruciais da ciência da computação são sustentadas por uma das estruturas de dados lineares mais elegantes e fundamentais: a **Pilha (Stack)**.

## 2. Objetivos de Aprendizagem
- Compreender o princípio operacional **LIFO (Last In, First Out — O Último que Entra é o Primeiro que Sai)**.
- Implementar uma classe `Pilha` em Python encapsulando as operações fundamentais: `push()`, `pop()`, `peek()` e `is_empty()`.
- Analisar a complexidade temporal das operações de pilha (\( O(1) \) amortizado com lista nativa e garantido com `collections.deque`).
- Aplicar a estrutura de Pilha para resolver o problema clássico de **Validação de Parênteses e Delimitadores Balanceados** em compiladores.

## 3. Pré-requisitos
- Programação Orientada a Objetos: criação de classes e métodos (Aula py_a1_l1).
- Tratamento de exceções com `raise` (Aula py_i2_l2).
- Métodos nativos de listas em Python (`.append()` e `.pop()`).

## 4. Explicação Teórica Aprofundada
### A Metáfora da Pilha de Pratos e o Princípio LIFO
Imagine uma pilha física de pratos em uma cozinha:
1. Quando você lava um novo prato limpo, você o coloca **no topo da pilha**.
2. Quando alguém precisa de um prato para o jantar, retira o prato que está **no topo da pilha**.
Você jamais retira o prato de baixo sem desmontar toda a estrutura. Em ciência da computação, essa disciplina de acesso é formalizada como **LIFO (Last In, First Out)**: o elemento mais recente inserido é obrigatoriamente o primeiro a ser removido.

### A Interface Clássica de uma Pilha (TAD - Tipo Abstrato de Dados)
Uma pilha padronizada deve fornecer exatamente as seguintes operações:
- **`push(item)` (Empilhar):** Insere um novo elemento no topo da pilha. Complexidade: \( O(1) \).
- **`pop()` (Desempilhar):** Remove e retorna o elemento atualmente no topo da pilha. Se a pilha estiver vazia, lança um erro (Underflow). Complexidade: \( O(1) \).
- **`peek()` / `top()` (Espiar):** Retorna o valor do topo da pilha **sem removê-lo**, permitindo consultar o estado atual. Complexidade: \( O(1) \).
- **`is_empty()`:** Retorna um booleano informando se a pilha não possui elementos.
- **`size()`:** Retorna a quantidade de itens empilhados.

### Implementação em Python: Lista vs. `collections.deque`
Em Python, uma lista nativa já suporta operações de pilha de forma muito eficiente se usarmos exclusivamente o final da lista:
- `lista.append(item)` atua como `push()`.
- `lista.pop()` atua como `pop()`.
Ambas operam no final da lista com complexidade de tempo constante amortizada \( O(1) \).
*(Atenção: Usar `lista.pop(0)` ou `lista.insert(0, item)` no início da lista destrói o desempenho, pois o Python é forçado a deslocar todos os outros elementos na memória, gerando complexidade ineficiente \( O(n) \)).*
Para aplicações de missão crítica de altíssima performance, a biblioteca padrão oferece `collections.deque` (fila duplamente encadeada em C), garantindo \( O(1) \) estrito em todas as operações.

## 5. Definições e Conceitos
- **LIFO:** Acrônimo para *Last In, First Out*.
- **Stack Overflow:** Estouro de pilha que ocorre quando se empilha além da capacidade máxima de memória.
- **Stack Underflow:** Erro gerado ao tentar desempilhar (`pop`) de uma pilha que se encontra vazia.
- **Call Stack (Pilha de Chamadas):** Estrutura interna que o processador e o interpretador utilizam para registrar os endereços de retorno de funções executadas.

## 6. Exemplos Resolvidos Passo a Passo
### Exemplo 1: Implementação Orientada a Objetos da Pilha
```python
class Pilha:
    def __init__(self):
        # Usamos uma lista privada como estrutura de armazenamento subjacente
        self._itens = []

    def push(self, item):
        self._itens.append(item)

    def pop(self):
        if self.is_empty():
            raise IndexError("Stack Underflow: A pilha está completamente vazia!")
        return self._itens.pop()

    def peek(self):
        if self.is_empty():
            raise IndexError("A pilha está vazia para visualização.")
        return self._itens[-1]

    def is_empty(self):
        return len(self._itens) == 0

    def size(self):
        return len(self._itens)

    def __str__(self):
        return f"Pilha(topo -> {self._itens[::-1]})"

# Teste das operações
minha_pilha = Pilha()
minha_pilha.push("Página 1")
minha_pilha.push("Página 2")
minha_pilha.push("Página 3")

print("Topo atual:", minha_pilha.peek()) # Página 3
print("Removendo:", minha_pilha.pop())   # Remove Página 3
print("Novo topo:", minha_pilha.peek())  # Página 2
```
- **Explicação:** A classe encapsula a lista interna garantindo que o acesso ocorra estritamente pelo topo via disciplina LIFO.

### Exemplo 2: Algoritmo de Validação de Parênteses Balanceados
Como um compilador valida se os delimitadores `()`, `[]` e `{}` de uma expressão matemática estão corretamente fechados e aninhados?
```python
def validar_delimitadores(expressao):
    pilha = []
    pares = {")": "(", "]": "[", "}": "{"}

    for char in expressao:
        if char in "([{":
            # Abre delimitador: empilha no topo
            pilha.append(char)
        elif char in ")]}":
            # Fecha delimitador: verifica se o topo é o par correspondente
            if not pilha:
                return False # Tentou fechar sem nenhum aberto!
            topo = pilha.pop()
            if topo != pares[char]:
                return False # Fechou com o tipo de delimitador errado!

    # Se a pilha estiver vazia no final, está perfeitamente balanceada!
    return len(pilha) == 0

# Testes de Compilador
print(validar_delimitadores("{[2 * (3 + 1)] - 5}")) # True (Válido)
print(validar_delimitadores("([)]"))                 # False (Cruzamento inválido)
print(validar_delimitadores("((()"))                 # False (Faltou fechar)
```
- **Explicação:** Cada abertura é empilhada; cada fechamento desempilha o elemento mais recente e verifica a correspondência no dicionário.

## 7. Aplicações Práticas e Modelagem
- **Mecanismos de Undo/Redo (Desfazer/Refazer):** Editores de imagem como Photoshop mantêm uma pilha de ações executadas; o Ctrl+Z desempilha a última ação e a reverte.
- **Avaliação de Notação Polonesa Reversa (RPN):** Calculadoras científicas Hewlett-Packard (HP) e motores de cálculo de expressões avaliam fórmulas usando pilhas sem necessidade de parênteses.

## 8. Exercícios de Fixação
1. Qual é o princípio fundamental de ordenação que rege o funcionamento de uma pilha?
2. Em uma pilha inicialmente vazia, executamos as operações: `push(10)`, `push(20)`, `pop()`, `push(30)`, `push(40)`, `pop()`. Qual elemento está atualmente no topo?
3. Por que a operação `pop()` deve ser executada no final da lista (`lista.pop()`) e nunca no início (`lista.pop(0)`) ao implementar uma pilha com listas nativas de Python?

## 9. Correção Comentada dos Exercícios
1. O princípio **LIFO (Last In, First Out)**: o último elemento inserido é sempre o primeiro a ser retirado.
2. Vamos rastrear a pilha:
   - `push(10)` -> [10]
   - `push(20)` -> [10, 20]
   - `pop()` -> remove 20 -> [10]
   - `push(30)` -> [10, 30]
   - `push(40)` -> [10, 30, 40]
   - `pop()` -> remove 40 -> [10, 30]
   O elemento que está no topo é o **30**.
3. Porque em listas de Python, remover do final (`lista.pop()`) leva tempo constante \( O(1) \), enquanto remover do início (`lista.pop(0)`) exige que todos os \( n-1 \) elementos restantes sejam deslocados na memória RAM, gerando complexidade linear ineficiente \( O(n) \).

## 10. Desafio de Domínio
Implemente uma função `inverter_string(texto)` que receba uma string qualquer e utilize exclusivamente a estrutura de `Pilha` para invertê-la, desempilhando os caracteres até reconstruir a palavra ao contrário.

## 11. Resumo e Pontos-Chave
- Pilha opera em regime LIFO (o último que entra é o primeiro que sai).
- Quatro operações principais: `push()`, `pop()`, `peek()`, `is_empty()`.
- Complexidade ótima de inserção e remoção: \( O(1) \).
- Base da pilha de chamadas do processador e do botão Desfazer (Ctrl+Z).
- Algoritmo essencial para validação de expressões e parsers de compiladores.

## 12. Referências Bibliográficas
- CORMEN, Thomas H. et al. *Algoritmos: Teoria e Prática*. 3ª ed. Campus/Elsevier, 2012. Capítulo 10: Seção 10.1: "Pilhas e Filas".
- BHARGAVA, Aditya Y. *Entendendo Algoritmos: Um guia ilustrado para programadores e outros curiosos*. Novatec, 2017. Capítulo 3: "Recursão - A Pilha de Chamada".
- PYTHON SOFTWARE FOUNDATION. *Using Lists as Stacks*. Disponível em: https://docs.python.org/3/tutorial/datastructures.html#using-lists-as-stacks.
        """.trimIndent(),
        functionsAndSyntax = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Pilha (Stack) e Princípio LIFO",
                syntax = """pilha.append(item) \\; (push) \\quad | \\quad item = pilha.pop() \\; (pop)""",
                description = "Estrutura de dados linear com acesso exclusivo pelo topo (Last-In, First-Out).",
                parametersInfo = "Operações no topo possuem complexidade temporal O(1)."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "collections.deque",
                syntax = "from collections import deque; d = deque()",
                description = "Fila e pilha duplamente encadeada da biblioteca padrão otimizada em C.",
                parametersInfo = "Garante O(1) rigoroso sem realocação amortizada de arrays."
            )
        ),
        codeExamples = listOf(
            LessonExample(
                title = "Uso de Lista como Pilha LIFO",
                codeOrFormula = "stack = []\nstack.append('A')\nstack.append('B')\ntopo = stack.pop()  # Retorna 'B'\nprint(topo, stack)    # 'B' ['A']",
                explanation = "O método .append() empilha no final e .pop() desempilha o último adicionado com complexidade O(1)."
            )
        ),
        goldTip = "Ao implementar uma pilha usando listas comuns em Python, use SEMPRE append() e pop() sem argumentos! Nunca use insert(0, ...) ou pop(0), pois operar no início da lista obriga o Python a mover todos os elementos da memória!"
    )
}
