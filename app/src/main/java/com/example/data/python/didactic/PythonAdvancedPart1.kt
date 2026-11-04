package com.example.data.python.didactic

import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample
import com.example.data.python.PythonDidacticBundle

/**
 * Conteúdo didático aprofundado do Nível Avançado de Python (Parte 1: Aulas py_a1_l1 a py_a1_l2).
 * Estrutura rigorosa em 12 seções por aula, fundamentada na documentação oficial de Python e CS50.
 */
object PythonAdvancedPart1 {

    fun get(lessonId: String): PythonDidacticBundle? {
        return when (lessonId) {
            "py_a1_l1" -> bundlePyA1L1
            "py_a1_l2" -> bundlePyA1L2
            else -> null
        }
    }

    // =========================================================================
    // AULA py_a1_l1: Classes, Construtor e Métodos
    // =========================================================================
    private val bundlePyA1L1 = PythonDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
Até o momento, organizamos nossos programas agrupando variáveis soltas e definindo funções procedurais. No entanto, para construir sistemas de grande porte — como plataformas financeiras, motores de jogos ou redes neurais —, precisamos de um paradigma arquitetural superior: a **Programação Orientada a Objetos (POO)**. Em Python, a POO nos permite criar nossos próprios tipos de dados customizados, encapsulando estados (atributos) e comportamentos (métodos) em entidades coesas chamadas **Classes**.

## 2. Objetivos de Aprendizagem
- Compreender a distinção fundamental entre uma **Classe** (o molde ou planta baixa) e um **Objeto** (a instância concreta na memória).
- Dominar o método construtor especial `__init__()` para inicialização dos atributos do objeto.
- Compreender o propósito e a obrigatoriedade do parâmetro explícito `self` em métodos de instância.
- Diferenciar Atributos de Instância de Atributos de Classe (variáveis compartilhadas).
- Implementar métodos especiais ("Dunder Methods") como `__str__()` e `__repr__()` para representação textual inteligente.

## 3. Pré-requisitos
- Funções, argumentos e escopo de variáveis (Módulo Básico).
- Dicionários e estruturas de dados associativas (Aula py_b3_l2).
- Compreensão do conceito de que "tudo em Python é um objeto" (Aula py_b1_l2).

## 4. Explicação Teórica Aprofundada
### Classes vs. Objetos (Instâncias)
- **Classe (`class`):** É a definição abstrata, o contrato que especifica quais propriedades e quais comportamentos os objetos daquele tipo possuirão. Convenção PEP 8: nomes de classes devem usar `PascalCase` (ex: `ContaBancaria`, `UsuarioSistema`).
- **Objeto / Instância:** É a entidade materializada na memória criada a partir da classe:
  ```python
  class Carro:
      pass

  meu_carro = Carro() # Instanciação do objeto
  ```

### O Método Construtor `__init__()` e o Papel do `self`
Em Python, métodos com dois sublinhados antes e depois do nome são chamados de **Dunder Methods** (*Double Underscore*).
O método `__init__()` é invocado automaticamente pelo interpretador CPython logo após o novo objeto ser alocado na memória. Sua missão é configurar o estado inicial daquela instância:
```python
class Aluno:
    def __init__(self, nome, nota):
        self.nome = nome  # Atributo de instância
        self.nota = nota  # Atributo de instância
```
**O que é o `self`?**
O parâmetro `self` é uma referência explícita à **instância específica do objeto** que está invocando o método no momento.
Quando você escreve `aluno1.exibir_dados()`, o interpretador traduz isso internamente para:
```python
Aluno.exibir_dados(aluno1)
```
O Python passa o próprio objeto como primeiro argumento automaticamente! Sem o prefixo `self.`, você estaria criando apenas uma variável local temporária que desapareceria assim que o método terminasse.

### Métodos de Instância vs. Atributos de Classe
- **Atributos de Instância (`self.atributo`):** São exclusivos de cada objeto. Se você altera a nota do `aluno1`, o `aluno2` permanece intacto.
- **Atributos de Classe:** São declarados diretamente no corpo da classe fora de qualquer método. Eles são **compartilhados globalmente por todas as instâncias** geradas por aquela classe (ex: um contador de usuários ativos).

### Representação Amigável com `__str__()`
Se você tentar imprimir um objeto direto no `print(meu_objeto)`, o Python exibirá por padrão algo deselegante como `<__main__.Aluno object at 0x7f8a10>`.
Para customizar como o objeto deve ser impresso para humanos, implementamos o método `__str__()`:
```python
def __str__(self):
    return f"Aluno: {self.nome} (Nota: {self.nota})"
```

## 5. Definições e Conceitos
- **Encapsulamento:** Agrupamento de dados e dos métodos que operam sobre esses dados dentro de uma mesma unidade lógica.
- **Dunder Methods (Métodos Mágicos):** Métodos especiais reservados pela linguagem Python que sobrecarregam operadores e comportamentos nativos (`__init__`, `__str__`, `__len__`, `__eq__`).
- **Estado do Objeto:** O conjunto atual de valores armazenados nos atributos de instância de um objeto em determinado instante de tempo.

## 6. Exemplos Resolvidos Passo a Passo
### Exemplo Completo: Modelagem de uma Conta Bancária
```python
class ContaBancaria:
    # Atributo de Classe compartilhado (taxa de manutenção global)
    taxa_operacao = 1.50

    def __init__(self, titular, saldo_inicial=0.0):
        # Atributos de Instância protegidos
        self.titular = titular
        self.saldo = float(saldo_inicial)

    def depositar(self, valor):
        if valor <= 0:
            print("Erro: O valor de depósito deve ser estritamente positivo.")
            return False
        self.saldo += valor
        print(f"Depósito de R${'$'} {valor:.2f} realizado com sucesso.")
        return True

    def sacar(self, valor):
        custo_total = valor + ContaBancaria.taxa_operacao
        if custo_total > self.saldo:
            print(f"Saldo insuficiente! Saldo atual: R${'$'} {self.saldo:.2f} (Custo com taxa: R${'$'} {custo_total:.2f})")
            return False
        self.saldo -= custo_total
        print(f"Saque de R${'$'} {valor:.2f} efetuado. Taxa debitada: R${'$'} {ContaBancaria.taxa_operacao:.2f}")
        return True

    def __str__(self):
        return f"Conta[{self.titular}] -> Saldo: R${'$'} {self.saldo:.2f}"

# Criando instâncias (objetos independentes)
conta_alice = ContaBancaria("Alice", 100.0)
conta_bob = ContaBancaria("Bob", 50.0)

conta_alice.depositar(50.0)
conta_alice.sacar(30.0)
print(conta_alice)
print(conta_bob)
```
- **Explicação:** `conta_alice` e `conta_bob` possuem saldos e históricos isolados, mas compartilham a mesma taxa operacional da classe `ContaBancaria`.

## 7. Aplicações Práticas e Modelagem
- **Frameworks Web (Django ORM / SQLAlchemy):** No Django, tabelas de bancos de dados relacionais são modeladas como classes Python (`class Produto(models.Model)`), e cada linha da tabela torna-se um objeto em memória.
- **Desenvolvimento de Jogos (Pygame):** Cada inimigo, projétil ou plataforma é modelado como uma classe `Sprite` contendo atributos de posição `(x, y)` e métodos `atualizar()` e `renderizar()`.

## 8. Exercícios de Fixação
1. Qual é a convenção recomendada pelo PEP 8 para nomes de classes em Python?
2. Para que serve o parâmetro `self` no primeiro argumento dos métodos de instância?
3. O que acontece se chamarmos `print(objeto)` em uma classe que implementa o método especial `__str__()`?

## 9. Correção Comentada dos Exercícios
1. A convenção oficial é `PascalCase` (iniciais de cada palavra em maiúsculas sem sublinhados, ex: `GerenciadorTarefas`).
2. O `self` representa a própria instância que chamou o método, permitindo acessar e modificar os atributos específicos daquele objeto na memória.
3. A função `print()` invoca automaticamente o método `__str__()` do objeto e exibe a string amigável retornada por ele.

## 10. Desafio de Domínio
Crie uma classe `Retangulo` que receba `largura` e `altura` no construtor. Implemente os métodos `area()`, `perimetro()` e o dunder method `__eq__()` para que dois retângulos sejam considerados iguais (`r1 == r2`) se possuírem a mesma área.

## 11. Resumo e Pontos-Chave
- Classes são moldes; objetos são instâncias concretas em memória.
- `__init__(self, ...)` é o construtor oficial do Python.
- `self` referencia a própria instância e deve ser o primeiro parâmetro de métodos de instância.
- `__str__` define a representação textual amigável do objeto.
- Nomes de classes usam `PascalCase`.

## 12. Referências Bibliográficas
- PYTHON SOFTWARE FOUNDATION. *The Python Tutorial: Classes*. Disponível em: https://docs.python.org/3/tutorial/classes.html.
- MALAN, David J. *CS50's Introduction to Programming with Python*. Harvard University, 2023. Lecture 8: "Object-Oriented Programming".
- RAMALHO, Luciano. *Fluent Python*. 2nd ed. O'Reilly Media, 2022. Chapter 11: "A Pythonic Object".
        """.trimIndent(),
        functionsAndSyntax = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Declaração de Classe e Construtor",
                syntax = "class NomeClasse:\n    def __init__(self, parametros):\n        self.atributo = valor",
                description = "Estrutura fundamental de Programação Orientada a Objetos em Python.",
                parametersInfo = "PascalCase para o nome da classe; self é obrigatório no construtor."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Método Especial __str__",
                syntax = "def __str__(self):\n    return f'Representação textual'",
                description = "Define a saída quando o objeto é convertido para string ou passado ao print().",
                parametersInfo = "Deve obrigatoriamente retornar uma string."
            )
        ),
        codeExamples = listOf(
            LessonExample(
                title = "Modelagem Orientada a Objetos",
                codeOrFormula = "class Livro:\n    def __init__(self, titulo, autor):\n        self.titulo = titulo\n        self.autor = autor\n    def __str__(self):\n        return f'{self.titulo} por {self.autor}'\nl = Livro('1984', 'George Orwell')\nprint(l)",
                explanation = "O construtor instanciou os atributos e o método __str__ customizou a exibição no terminal."
            )
        ),
        goldTip = "Nunca esqueça de colocar o 'self' como primeiro parâmetro de qualquer método de classe! Se você esquecer, ao chamar objeto.metodo() o Python reclamará que o método recebeu 1 argumento quando esperava 0!"
    )

    // =========================================================================
    // AULA py_a1_l2: Herança e Polimorfismo com super()
    // =========================================================================
    private val bundlePyA1L2 = PythonDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
No desenvolvimento de software profissional, duplicar código é o pecado capital dos programadores: torna a manutenção cara, propaga bugs e viola o princípio fundamental DRY (*Don't Repeat Yourself*). Como criar uma classe `Cachorro` e uma classe `Gato` sem reescrever todo o código de peso, idade, métodos de alimentação e sono já existentes na classe genérica `Animal`? A resposta da POO moderna é a **Herança**, que permite a uma classe filha herdar atributos e métodos de uma classe pai, combinada com o **Polimorfismo**, que permite que objetos de tipos distintos respondam à mesma mensagem com comportamentos especializados.

## 2. Objetivos de Aprendizagem
- Modelar relacionamentos hierárquicos de herança simples: `class Subclasse(Superclasse):`.
- Utilizar a função embutida `super()` para delegar a inicialização ao construtor da classe base com segurança.
- Sobrescrever métodos da classe pai (*Method Overriding*) para especialização de comportamentos.
- Compreender o conceito de **Polimorfismo** e o princípio pythônico de **Duck Typing** ("Se anda como um pato e faz quack como um pato, é um pato").
- Utilizar as funções de inspeção de tipos `isinstance()` e `issubclass()`.

## 3. Pré-requisitos
- Classes, construtor `__init__`, parâmetro `self` e métodos de instância (Aula py_a1_l1).
- Encapsulamento básico e visibilidade de atributos.

## 4. Explicação Teórica Aprofundada
### Sintaxe de Herança em Python
Para que uma classe herde de outra, basta passar a classe base entre parênteses na sua declaração:
```python
class Animal:
    def __init__(self, nome):
        self.nome = nome

    def emitir_som(self):
        pass # Método genérico a ser especializado

class Cachorro(Animal): # Cachorro herda de Animal
    pass
```
A classe `Cachorro` herda automaticamente todos os atributos e métodos de `Animal`.

### O Papel Fundamental de `super()`
Quando a subclasse precisa adicionar novos atributos em seu próprio construtor, ela **não deve reescrever o código do pai**. Ela invoca a função `super()`, que resolve a hierarquia de classes e delega a inicialização básica para a superclasse:
```python
class Cachorro(Animal):
    def __init__(self, nome, raca):
        # 1. Delega a inicialização de 'nome' para a classe pai Animal
        super().__init__(nome)
        # 2. Configura os atributos específicos do Cachorro
        self.raca = raca
```
Isso garante manutenibilidade: se o construtor de `Animal` mudar no futuro, todas as dezenas de subclasses herdeiras se adaptarão automaticamente sem quebras.

### Sobrescrita de Métodos (Method Overriding)
A subclasse pode redefinir o comportamento de qualquer método herdado simplesmente declarando um método com o mesmo nome exato:
```python
class Gato(Animal):
    def emitir_som(self):
        return "Miau!"

class Cachorro(Animal):
    def emitir_som(self):
        return "Au Au!"
```

### Polimorfismo e o Princípio de Duck Typing
Polimorfismo é a capacidade de tratar objetos de diferentes classes derivadas através da mesma interface comum da classe base:
```python
animais = [Cachorro("Rex"), Gato("Mimi"), Cachorro("Thor")]

for a in animais:
    print(f"{a.nome}: {a.emitir_som()}")
```
O laço `for` não precisa saber se o animal da vez é um gato ou um cachorro; ele simplesmente invoca `.emitir_som()`, e cada objeto responde com sua implementação polimórfica especializada!
Em Python, isso é levado ao nível supremo pelo **Duck Typing**: você não precisa nem sequer de uma herança formal rígida; basta que o objeto implemente o método com a assinatura esperada para que ele seja aceito.

### Inspeção com `isinstance()` e `issubclass()`
- `isinstance(objeto, Classe)`: Verifica se uma instância pertence àquela classe ou a qualquer uma de suas subclasses herdeiras:
  ```python
  rex = Cachorro("Rex", "Labrador")
  print(isinstance(rex, Cachorro)) # True
  print(isinstance(rex, Animal))   # True (pois Cachorro é um Animal!)
  ```
- `issubclass(Subclasse, Superclasse)`: Verifica a relação na árvore genealógica de tipos.

## 5. Definições e Conceitos
- **Superclasse (Classe Pai / Base):** A classe ancestral mais genérica que fornece as características compartilhadas.
- **Subclasse (Classe Filha / Derivada):** A classe especializada que estende ou modifica o comportamento do pai.
- **MRO (Method Resolution Order):** A ordem estrita que o interpretador CPython segue para procurar atributos e métodos através da árvore de herança (inspecionável via `Classe.__mro__`).

## 6. Exemplos Resolvidos Passo a Passo
### Exemplo Completo: Sistema de Gestão de Funcionários
```python
class Funcionario:
    def __init__(self, nome, cpf, salario_base):
        self.nome = nome
        self.cpf = cpf
        self.salario_base = float(salario_base)

    def calcular_remuneracao(self):
        return self.salario_base

    def __str__(self):
        return f"{self.nome} (CPF: {self.cpf}) - Remuneração: R${'$'} {self.calcular_remuneracao():.2f}"

class Gerente(Funcionario):
    def __init__(self, nome, cpf, salario_base, bonus_gestao):
        # Chama o construtor do pai Funcionario
        super().__init__(nome, cpf, salario_base)
        self.bonus_gestao = float(bonus_gestao)

    # Sobrescrita polimórfica do cálculo de remuneração
    def calcular_remuneracao(self):
        return self.salario_base + self.bonus_gestao

class Desenvolvedor(Funcionario):
    def __init__(self, nome, cpf, salario_base, nivel):
        super().__init__(nome, cpf, salario_base)
        self.nivel = nivel.upper()

    def calcular_remuneracao(self):
        adicional = 1000.0 if self.nivel == "SENIOR" else 500.0
        return self.salario_base + adicional

# Folha de Pagamento Polimórfica
equipe = [
    Funcionario("Carlos", "111.222.333-44", 3000.0),
    Gerente("Beatriz", "555.666.777-88", 8000.0, 2500.0),
    Desenvolvedor("Lucas", "999.888.777-66", 6000.0, "Senior")
]

custo_total = sum(membro.calcular_remuneracao() for membro in equipe)

for membro in equipe:
    print(membro)

print(f"\nCusto total da folha: R${'$'} {custo_total:.2f}")
```
- **Explicação:** A lista `equipe` contém instâncias distintas de `Funcionario`, `Gerente` e `Desenvolvedor`. A chamada `membro.calcular_remuneracao()` executa a lógica correta de cada cargo polimorficamente sem condicionais manuais.

## 7. Aplicações Práticas e Modelagem
- **Hierarquia de Exceções Customizadas:** Desenvolvedores criam suas próprias exceções de domínio herdando de `Exception`: `class SaldoInsuficienteError(Exception): pass`.
- **Interfaces Gráficas (Tkinter, PyQt):** Janelas, botões e caixas de texto são criados herdando de classes base do framework (ex: `class MinhaTela(QWidget): super().__init__()`).

## 8. Exercícios de Fixação
1. Qual a função utilizada dentro do construtor da subclasse para invocar o construtor da classe ancestral?
2. Se a classe `Carro` herda de `Veiculo` e `meu_carro = Carro()`, qual é o retorno de `isinstance(meu_carro, Veiculo)`?
3. O que é Method Overriding?

## 9. Correção Comentada dos Exercícios
1. A função `super()`, tipicamente usada como `super().__init__(...)`.
2. O retorno é `True`. Todo objeto instanciado a partir de uma subclasse é formalmente considerado uma instância válida de sua superclasse ancestral.
3. É a redefinição de um método na classe filha que possui o mesmo nome de um método já existente na classe pai, substituindo a implementação genérica por uma especializada.

## 10. Desafio de Domínio
Construa uma hierarquia de formas geométricas onde a classe base abstrata `FormaGeometrica` possua os métodos `area()` e `perimetro()`. Implemente as subclasses `Circulo` e `Retangulo`, e demonstre o polimorfismo calculando a soma das áreas de uma lista mista de formas.

## 11. Resumo e Pontos-Chave
- Sintaxe de herança: `class Subclasse(Superclasse):`.
- Use `super().__init__(...)` para reaproveitar a inicialização do pai.
- Sobrescreva métodos redefinindo-os na classe filha (Overriding).
- Polimorfismo permite tratar objetos diferentes através da mesma interface comum.
- `isinstance(obj, Classe)` valida pertencimento na árvore hierárquica.

## 12. Referências Bibliográficas
- PYTHON SOFTWARE FOUNDATION. *The Python Tutorial: Inheritance*. Disponível em: https://docs.python.org/3/tutorial/classes.html#inheritance.
- MALAN, David J. *CS50's Introduction to Programming with Python*. Harvard University, 2023. Lecture 8: "Inheritance".
- RAMALHO, Luciano. *Fluent Python*. 2nd ed. O'Reilly Media, 2022. Chapter 14: "Inheritance: For Better or for Worse".
        """.trimIndent(),
        functionsAndSyntax = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Herança e super()",
                syntax = "class Filha(Pai):\n    def __init__(self, a, b):\n        super().__init__(a)\n        self.b = b",
                description = "Mecanismo de extensão de classes e delegação ao construtor da superclasse.",
                parametersInfo = "Pai é passado entre parênteses. super() resolve a hierarquia MRO."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "isinstance() e issubclass()",
                syntax = "isinstance(objeto, Classe) \\quad | \\quad issubclass(Sub, Super)",
                description = "Funções de introspecção e checagem de tipos na hierarquia orientada a objetos.",
                parametersInfo = "Retornam True ou False."
            )
        ),
        codeExamples = listOf(
            LessonExample(
                title = "Especialização com super() e Sobrescrita",
                codeOrFormula = "class Animal:\n    def falar(self): return '...'\nclass Cachorro(Animal):\n    def falar(self): return 'Au!'\nprint(Cachorro().falar())  # Au!",
                explanation = "A subclasse Cachorro substituiu o método genérico falar() pelo comportamento especializado."
            )
        ),
        goldTip = "Sempre invoque super().__init__(...) logo na primeira linha do construtor da sua subclasse! Isso garante que todos os atributos herdados da classe pai sejam configurados antes que a filha comece a operar!"
    )
}
