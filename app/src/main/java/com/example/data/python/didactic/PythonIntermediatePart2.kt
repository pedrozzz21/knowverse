package com.example.data.python.didactic

import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample
import com.example.data.python.PythonDidacticBundle

/**
 * Conteúdo didático aprofundado do Nível Intermediário de Python (Parte 2: Aulas py_i2_l2 e py_i3_l1).
 * Estrutura rigorosa em 12 seções por aula, fundamentada na documentação oficial de Python e CS50.
 */
object PythonIntermediatePart2 {

    fun get(lessonId: String): PythonDidacticBundle? {
        return when (lessonId) {
            "py_i2_l2" -> bundlePyI2L2
            "py_i3_l1" -> bundlePyI3L1
            else -> null
        }
    }

    // =========================================================================
    // AULA py_i2_l2: Tratamento de Exceções com try / except
    // =========================================================================
    private val bundlePyI2L2 = PythonDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
No desenvolvimento de software profissional, os erros são inevitáveis: conexões de rede caem, arquivos no disco são deletados por outros processos, usuários digitam letras onde se esperavam números ou bancos de dados rejeitam consultas. Um programa ingênuo simplesmente entra em colapso catastrófico (crash), exibindo uma pilha de erros incompreensível (Traceback) e abandonando o usuário. Em Python, a robustez é garantida através do **Tratamento de Exceções**, permitindo interceptar falhas, mitigar danos e manter o sistema em operação estável.

## 2. Objetivos de Aprendizagem
- Compreender a filosofia fundamental do Python: EAFP (*It's Easier to Ask for Forgiveness than Permission*) versus LBYL (*Look Before You Leap*).
- Dominar a sintaxe completa dos blocos: `try`, `except`, `else` e `finally`.
- Capturar exceções específicas (como `ValueError`, `ZeroDivisionError`, `FileNotFoundError`) em vez de utilizar capturas cegas genéricas.
- Inspecionar a mensagem interna do erro utilizando `except ExceptionType as err`.
- Lançar exceções intencionalmente através da palavra-chave `raise`.

## 3. Pré-requisitos
- Tipos de dados e conversão de tipos (Aulas py_b1_l2 e py_b1_l3).
- Estruturas de controle de fluxo e condicionais (Aula py_b2_l2).

## 4. Explicação Teórica Aprofundada
### O Paradigma EAFP do Python
Em linguagens defensivas (como C), é comum testar exaustivamente todas as condições antes de realizar qualquer operação (LBYL: "Olhe antes de pular"):
```python
# Abordagem defensiva (LBYL):
if isinstance(divisor, (int, float)) and divisor != 0:
    res = 10 / divisor
```
Python adota com orgulho o estilo **EAFP** ("É mais fácil pedir perdão do que permissão"): executa-se a operação diretamente dentro de uma zona protegida (`try`) e, caso algo dê errado, captura-se a falha com elegância (`except`):
```python
try:
    res = 10 / divisor
except ZeroDivisionError:
    print("Divisão por zero tratada com sucesso!")
```
Essa abordagem é mais performática quando erros são raros e evita condições de corrida (Race Conditions) em sistemas operacionais multitarefa.

### A Estrutura Quádrupla: `try / except / else / finally`
1. **`try`:** Contém o trecho de código "perigoso" que pode potencialmente disparar uma exceção.
2. **`except Excecao as err`:** Bloco executado **apenas se** uma exceção do tipo especificado ocorrer dentro do `try`. Permite registrar logs, exibir mensagens amigáveis e aplicar valores de contingência.
3. **`else` (Execução Limpa):** Bloco executado **somente se o `try` terminar com 100% de sucesso**, sem disparar nenhum erro. Ideal para operações que dependem da conclusão bem-sucedida do passo anterior.
4. **`finally` (Execução Garantida):** Bloco que **SEMPRE será executado**, quer tenha havido erro ou não, mesmo que ocorra um comando `return` prematuro. É o local sagrado para fechamento de conexões com bancos de dados, liberação de memória e encerramento de arquivos no disco.

### A Hierarquia das Exceções e o Erro do "Except Vazio"
Todas as exceções padrão em Python herdam de `BaseException`. As exceções de aplicação herdam de `Exception`:
- `ValueError`: Tipo correto, mas valor inadequado (`int("abc")`).
- `TypeError`: Operação aplicada a tipos incompatíveis (`"5" + 5`).
- `KeyError`: Chave inexistente acessada em dicionário.
- `IndexError`: Índice fora dos limites de uma lista ou tupla.
*Regra Capital de Engenharia de Software:* **NUNCA use `except:` puro ou `except Exception:` sem propósito!**
Um `except:` sem tipo captura inclusive o sinal de teclado `KeyboardInterrupt` (Ctrl+C) e o encerramento do interpretador `SystemExit`, tornando o programa impossível de ser fechado pelo terminal!

## 5. Definições e Conceitos
- **Traceback:** O relatório detalhado de rastreamento emitido pelo Python indicando o arquivo, a linha exata e a cadeia de chamadas que culminou na falha.
- **`raise`:** Comando para disparar deliberadamente uma exceção quando uma regra de negócio é violada.
- **Cláusula Limpa (Cleanup):** Ações de desalocação e limpeza no bloco `finally`.

## 6. Exemplos Resolvidos Passo a Passo
### Exemplo 1: Tratamento com Múltiplas Exceções Específicas
```python
def dividir_valores(a_str, b_str):
    try:
        a = float(a_str)
        b = float(b_str)
        resultado = a / b
    except ValueError as e:
        print(f"Erro de formato numérico: {e}")
        return None
    except ZeroDivisionError:
        print("Erro crítico: O divisor não pode ser zero!")
        return None
    else:
        print("Cálculo realizado com total sucesso.")
        return resultado
    finally:
        print("--- Fim da rotina de divisão ---")

# Teste 1: Sucesso
print("Resultado 1:", dividir_valores("10", "2"))

# Teste 2: Entrada inválida
print("Resultado 2:", dividir_valores("dez", "2"))

# Teste 3: Divisão por zero
print("Resultado 3:", dividir_valores("10", "0"))
```
- **Explicação:** Cada categoria de erro foi isolada em seu próprio bloco `except`, garantindo respostas sob medida para cada tipo de falha sem que o programa aborte.

### Exemplo 2: Validação com Lançamento Manual (`raise`)
```python
def cadastrar_idade(idade):
    if idade < 0:
        raise ValueError(f"Idade não pode ser negativa! Valor recebido: {idade}")
    if idade > 150:
        raise ValueError(f"Idade improvável para humanos: {idade}")
    print(f"Idade {idade} registrada com sucesso.")

try:
    cadastrar_idade(-5)
except ValueError as erro:
    print("Validação recusada:", erro)
```
- **Explicação:** A instrução `raise` protege a integridade do domínio disparando um `ValueError` customizado.

## 7. Aplicações Práticas e Modelagem
- **Resiliência em Comunicações de Rede:** Clientes HTTP (Requests, httpx) envolvem chamadas de rede em `try/except requests.exceptions.RequestException` para aplicar tentativas sucessivas (retry) caso haja instabilidade no servidor remoto.
- **Operações Seguras com Arquivos:** Fechamento garantido de handles de arquivos via `finally` (ou gerenciadores de contexto `with`) prevenindo corrupção de dados no sistema operacional.

## 8. Exercícios de Fixação
1. Qual bloco de uma estrutura `try/except` é executado independentemente de erros terem ocorrido ou não?
2. Por que a prática de usar um `except:` vazio sem especificar o tipo da exceção é considerada má prática grave?
3. Como capturar o erro ocorrido e imprimir a mensagem original do sistema para o usuário?

## 9. Correção Comentada dos Exercícios
1. O bloco `finally`. Ele é executado incondicionalmente em todos os cenários.
2. Porque mascara erros inesperados de digitação (`NameError`, `SyntaxError`) e impede o encerramento do programa via teclado (`KeyboardInterrupt` / Ctrl+C), tornando a depuração um pesadelo.
3. Utilizando a cláusula `as`: `except ValueError as err: print(err)`.

## 10. Desafio de Domínio
Construa uma função de leitura segura no console `ler_inteiro(mensagem)` que utilize um laço `while True` com `try/except ValueError` para insistir repetidamente até que o usuário digite um número inteiro estritamente válido, retornando o valor convertido.

## 11. Resumo e Pontos-Chave
- Filosofia Python: EAFP ("Mais fácil pedir perdão do que permissão").
- `try`: Código a ser monitorado.
- `except TipoDeErro`: Resposta ao erro específico.
- `else`: Executa se NENHUM erro ocorreu.
- `finally`: Executa SEMPRE (limpeza de conexões e recursos).
- `raise`: Lança uma exceção manualmente.

## 12. Referências Bibliográficas
- PYTHON SOFTWARE FOUNDATION. *The Python Tutorial: Errors and Exceptions*. Disponível em: https://docs.python.org/3/tutorial/errors.html.
- MALAN, David J. *CS50's Introduction to Programming with Python*. Harvard University, 2023. Lecture 3: "Exceptions".
- RAMALHO, Luciano. *Fluent Python*. 2nd ed. O'Reilly Media, 2022. Chapter 18: "with, match, and else Blocks".
        """.trimIndent(),
        functionsAndSyntax = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Estrutura try / except / else / finally",
                syntax = "try:\n    operacao()\nexcept Excecao as err:\n    tratar()\nelse:\n    sucesso()\nfinally:\n    limpar()",
                description = "Mecanismo profissional de captura, tratamento e contenção de falhas.",
                parametersInfo = "Excecao deve ser uma subclasse de Exception. err contém os metadados do erro."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Instrução raise",
                syntax = "raise TipoDeExcecao('Mensagem explicativa')",
                description = "Dispara intencionalmente uma exceção interrompendo o fluxo normal.",
                parametersInfo = "Utilizado para validação de regras de negócio e pré-condições."
            )
        ),
        codeExamples = listOf(
            LessonExample(
                title = "Loop de Leitura Segura",
                codeOrFormula = "while True:\n    try:\n        num = int(input('Digite um número: '))\n        break\n    except ValueError:\n        print('Entrada inválida! Digite apenas números inteiros.')\nprint('Valor recebido:', num)",
                explanation = "O laço repete a pergunta infinitamente até que a conversão int() ocorra sem lançar ValueError."
            )
        ),
        goldTip = "NUNCA use um 'except:' vazio! Ele engole erros de sintaxe e até impede que você pare o programa apertando Ctrl+C no terminal. Diga sempre exatamente qual erro você está esperando: except ValueError, except KeyError, etc.!"
    )

    // =========================================================================
    // AULA py_i3_l1: Algoritmo de Busca Binária
    // =========================================================================
    private val bundlePyI3L1 = PythonDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
Se você procurar uma palavra em um dicionário físico impresso de 2.000 páginas, você começa folheando folha por folha desde a letra 'A' até a página 1.500? Obviamente não! Você abre o livro exatamente no meio; ao constatar que a página aberta está na letra 'M' e sua palavra começa com 'S', você descarta instantaneamente toda a primeira metade de 1.000 páginas e repete o processo na metade restante. Esse princípio brilhante de divisão e conquista é o algoritmo fundamental da ciência da computação: a **Busca Binária (Binary Search)**.

## 2. Objetivos de Aprendizagem
- Compreender a pré-condição indispensável da Busca Binária: a **ordenação prévia da lista**.
- Implementar o algoritmo de Busca Binária clássico utilizando os ponteiros `inicio`, `fim` e `meio`.
- Comparar a complexidade temporal da Busca Linear (\( O(n) \)) com a Busca Binária (\( O(\log n) \)).
- Compreender o impacto de escala: como buscar em 1 bilhão de elementos em apenas 30 comparações.
- Prevenir o bug histórico de cálculo do ponto médio por overflow de inteiros.

## 3. Pré-requisitos
- Laços de repetição `while` e condicionais (Aulas py_b2_l2 e py_b3_l1).
- Operações de indexação em listas e divisão inteira `//` (Aula py_b2_l1).
- Noções de logaritmos na base 2 (\( \log_2 n \)).

## 4. Explicação Teórica Aprofundada
### O Algoritmo de Divisão e Conquista
A Busca Binária busca um elemento `alvo` em uma lista estritamente ordenada mantendo dois ponteiros delimitadores de intervalo:
- `inicio`: O primeiro índice válido do espaço de busca (inicialmente `0`).
- `fim`: O último índice válido do espaço de busca (inicialmente `len(lista) - 1`).

**O Ciclo Iterativo:**
Enquanto `inicio <= fim`:
1. Calculamos o índice do elemento central do intervalo:
   \[ \text{meio} = (\text{inicio} + \text{fim}) // 2 \]
2. Comparamos o valor do meio `lista[meio]` com o `alvo`:
   - **Caso 1 (Igualdade):** `lista[meio] == alvo`. O elemento foi localizado! Retornamos imediatamente o índice `meio`.
   - **Caso 2 (Alvo é menor):** `alvo < lista[meio]`. Se o alvo for menor que o elemento central, é impossível que ele esteja na metade direita. Descartamos toda a metade direita ajustando o ponteiro final:
     \[ \text{fim} = \text{meio} - 1 \]
   - **Caso 3 (Alvo é maior):** `alvo > lista[meio]`. Descartamos toda a metade esquerda ajustando o ponteiro inicial:
     \[ \text{inicio} = \text{meio} + 1 \]
3. Se o laço terminar com `inicio > fim` sem localizar o alvo, concluímos com certeza matemática absoluta que o elemento **não existe na lista** (retorna `-1` ou `None`).

### Comparação de Complexidade Assintótica
- **Busca Linear (Sequencial):** Percorre a lista elemento por elemento. No pior cenário, se a lista tem \( n \) elementos, realiza \( n \) comparações:
  \[ \text{Complexidade: } O(n) \]
- **Busca Binária:** A cada comparação, o espaço de busca é **cortado exatamente pela metade**:
  \[ n \to \frac{n}{2} \to \frac{n}{4} \to \frac{n}{8} \to \dots \to 1 \]
  O número máximo de comparações necessárias para encontrar qualquer elemento é dado pelo logaritmo na base 2:
  \[ \text{Complexidade: } O(\log_2 n) \]

| Quantidade de Elementos (\( n \)) | Pior Caso - Busca Linear (\( O(n) \)) | Pior Caso - Busca Binária (\( O(\log n) \)) |
| :--- | :--- | :--- |
| **1.000** | 1.000 comparações | **10 comparações** |
| **1.000.000** (1 milhão) | 1.000.000 comparações | **20 comparações** |
| **1.000.000.000** (1 bilhão) | 1.000.000.000 comparações | **30 comparações** |

A Busca Binária em 1 bilhão de registros resolve a consulta em 30 passos insignificantes, enquanto a busca linear demoraria segundos ou minutos inteiros de processamento de CPU!

## 5. Definições e Conceitos
- **Pivô / Meio:** O elemento central que bissecta o espaço de busca.
- **Invariante de Laço:** A propriedade de que, se o alvo existir na coleção, ele estará estritamente contido no subintervalo `[inicio, fim]`.
- **Complexidade Logarítmica:** Comportamento de algoritmos cujo tempo de execução cresce proporcionalmente ao logaritmo do tamanho da entrada.

## 6. Exemplos Resolvidos Passo a Passo
### Exemplo Completo: Implementação Canônica da Busca Binária
```python
def busca_binaria(lista, alvo):
    inicio = 0
    fim = len(lista) - 1
    passos = 0

    while inicio <= fim:
        passos += 1
        meio = (inicio + fim) // 2
        chute = lista[meio]

        print(f"Passo {passos}: analisando índice {meio} (valor {chute}) no intervalo [{inicio}, {fim}]")

        if chute == alvo:
            print(f"-> Sucesso! Encontrado em {passos} passos.")
            return meio
        elif chute > alvo:
            # O alvo está na metade esquerda
            fim = meio - 1
        else:
            # O alvo está na metade direita
            inicio = meio + 1

    print(f"-> Elemento {alvo} não existe na lista. Total de passos: {passos}")
    return -1

# A lista DEVE estar rigorosamente ordenada!
dados = [2, 5, 8, 12, 16, 23, 38, 56, 72, 91]
indice = busca_binaria(dados, 23)
print("Índice retornado:", indice)
```
- **Rastreamento dos Passos:**
  1. `inicio = 0`, `fim = 9` \( \implies \text{meio} = 4 \), `chute = 16`. Como \( 23 > 16 \), novo `inicio = 5`.
  2. `inicio = 5`, `fim = 9` \( \implies \text{meio} = 7 \), `chute = 56`. Como \( 23 < 56 \), novo `fim = 6`.
  3. `inicio = 5`, `fim = 6` \( \implies \text{meio} = 5 \), `chute = 23`. Igualdade confirmada! Encontrado no índice 5 com apenas 3 comparações.

## 7. Aplicações Práticas e Modelagem
- **Índices de Motores de Busca e Bancos de Dados:** Motores como PostgreSQL e MySQL organizam índices em árvores balanceadas B-Tree, onde cada nó interno executa buscas binárias ultra-rápidas para localizar chaves primárias.
- **Git Bisect:** O comando `git bisect` utiliza busca binária no histórico de commits para isolar com o menor número possível de compilações em qual commit exato um bug foi introduzido no projeto.

## 8. Exercícios de Fixação
1. Qual é a condição obrigatória indispensável para que o algoritmo de busca binária funcione?
2. Em uma lista ordenada contendo 128 elementos, qual é o número máximo de comparações no pior caso para encontrar um elemento (ou comprovar que ele inexiste)?
3. Por que calculamos o novo início como `meio + 1` e o novo fim como `meio - 1`, em vez de apenas `meio`?

## 9. Correção Comentada dos Exercícios
1. A lista deve estar **rigorosamente ordenada** de forma crescente (ou decrescente). Se a lista estiver desordenada, o descarte de metades é inválido e o algoritmo falha.
2. Como \( 128 = 2^7 \), o número máximo de comparações é \( \log_2(128) = 7 \) passos.
3. Porque o elemento do índice `meio` já foi testado no `if chute == alvo` e comprovadamente não é o alvo. Incluí-lo no próximo intervalo seria inútil e causaria um laço infinito quando `inicio == fim`.

## 10. Desafio de Domínio
Implemente a versão **recursiva** da Busca Binária (`busca_binaria_recursiva(lista, alvo, inicio, fim)`) que chame a si mesma passando os novos limites até que o caso base de parada seja atingido.

## 11. Resumo e Pontos-Chave
- A busca binária corta o espaço de busca pela metade a cada iteração.
- Complexidade espetacular: \( O(\log n) \).
- A lista DEVE estar previamente ordenada.
- Controle com três índices: `inicio = 0`, `fim = len - 1`, `meio = (inicio + fim) // 2`.
- Base de índices de bancos de dados modernos e buscas indexadas.

## 12. Referências Bibliográficas
- CORMEN, Thomas H. et al. *Algoritmos: Teoria e Prática*. 3ª ed. Campus/Elsevier, 2012. Capítulo 2: Seção 2.3: "Projetando Algoritmos (Divisão e Conquista)".
- BHARGAVA, Aditya Y. *Entendendo Algoritmos: Um guia ilustrado para programadores e outros curiosos*. Novatec, 2017. Capítulo 1: "Introdução a Algoritmos - Pesquisa Binária".
- PYTHON SOFTWARE FOUNDATION. *bisect — Array bisection algorithm*. Disponível em: https://docs.python.org/3/library/bisect.html.
        """.trimIndent(),
        functionsAndSyntax = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Busca Binária (Binary Search)",
                syntax = "meio = (inicio + fim) // 2",
                description = "Algoritmo de pesquisa de divisão e conquista com complexidade O(log n).",
                parametersInfo = "Exige que a coleção esteja previamente ordenada."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Módulo bisect",
                syntax = "import bisect; bisect.bisect_left(lista, x)",
                description = "Implementação oficial ultrarrápida em C da busca binária na biblioteca padrão do Python.",
                parametersInfo = "Localiza o ponto de inserção que mantém a ordenação."
            )
        ),
        codeExamples = listOf(
            LessonExample(
                title = "Algoritmo de Busca Binária Padrão",
                codeOrFormula = "def busca(arr, x):\n    ini, fim = 0, len(arr) - 1\n    while ini <= fim:\n        m = (ini + fim) // 2\n        if arr[m] == x: return m\n        elif arr[m] < x: ini = m + 1\n        else: fim = m - 1\n    return -1",
                explanation = "A busca binária encontra qualquer elemento em listas ordenadas dividindo o problema pela metade a cada passo."
            )
        ),
        goldTip = "Nunca tente aplicar Busca Binária em uma lista que não foi ordenada previamente! Se a lista estiver desordenada, ordene-a primeiro com lista.sort() ou use busca linear comum!"
    )
}

