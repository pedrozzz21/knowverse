import urllib.request
import json
import time
import re
import os

# 14 Tópicos e Subtópicos do Usuário
TOPICS_TAXONOMY = {
    "Matemática Básica": [
        "Operações Básicas (Adição, Subtração, Multiplicação, Divisão)",
        "Frações e Números Decimais",
        "Múltiplos e Divisores (MMC e MDC)",
        "Razão e Proporção",
        "Regra de Três (Simples e Composta)",
        "Porcentagem"
    ],
    "Matemática Financeira": [
        "Juros Simples",
        "Juros Compostos",
        "Sistemas de Amortização"
    ],
    "Estatística": [
        "Leitura e Interpretação de Gráficos e Tabelas",
        "Medidas de Tendência Central (Média Aritmética, Ponderada, Moda e Mediana)",
        "Medidas de Dispersão (Desvio Padrão e Variância)"
    ],
    "Geometria Plana": [
        "Ângulos e Polígonos",
        "Relações Métricas no Triângulo Retângulo (Teorema de Pitágoras)",
        "Semelhança e Congruência de Triângulos",
        "Áreas de Figuras Planas",
        "Círculo e Circunferência"
    ],
    "Geometria Espacial": [
        "Características dos Poliedros (Relação de Euler)",
        "Prismas e Cilindros (Áreas e Volumes)",
        "Pirâmides e Cones (Áreas e Volumes)",
        "Esferas (Área e Volume)",
        "Projeção Ortogonal"
    ],
    "Funções": [
        "Conceitos Básicos e Plano Cartesiano",
        "Função Afim (1º Grau)",
        "Função Quadrática (2º Grau)",
        "Interpretação de Gráficos de Funções"
    ],
    "Álgebra": [
        "Expressões Algébricas",
        "Produtos Notáveis e Fatoração",
        "Equações e Inequações do 1º e 2º Grau"
    ],
    "Análise Combinatória": [
        "Princípio Fundamental da Contagem (PFC)",
        "Permutação (Simples e com Repetição)",
        "Arranjo",
        "Combinação"
    ],
    "Probabilidade": [
        "Conceitos Iniciais (Espaço Amostral e Evento)",
        "Probabilidade Simples",
        "Probabilidade Condicional",
        "Eventos Independentes e Mutuamente Exclusivos"
    ],
    "Sequências": [
        "Padrões Numéricos",
        "Progressão Aritmética (PA)",
        "Progressão Geométrica (PG)"
    ],
    "Logaritmos": [
        "Conceito de Exponencial",
        "Definição e Propriedades dos Logaritmos",
        "Equações Exponenciais e Logarítmicas"
    ],
    "Trigonometria": [
        "Relações Trigonométricas no Triângulo Retângulo (Seno, Cosseno, Tangente)",
        "Ciclo Trigonométrico",
        "Funções Trigonométricas",
        "Lei dos Senos e Lei dos Cossenos"
    ],
    "Geometria Analítica": [
        "Distância entre Dois Pontos e Ponto Médio",
        "Estudo da Reta (Equação Geral e Reduzida)",
        "Estudo da Circunferência"
    ],
    "Matrizes e Sistemas Lineares": [
        "Definição e Operações com Matrizes",
        "Determinantes",
        "Resolução e Discussão de Sistemas Lineares"
    ]
}

def classify_question(text):
    t = text.lower()
    
    # 2. Financeira
    if any(k in t for k in ["juros", "taxa de juro", "rendimento", "montante", "amortiza", "capital aplicado", "investimento"]):
        if "compost" in t:
            return "Matemática Financeira", "Juros Compostos"
        if "amortiza" in t:
            return "Matemática Financeira", "Sistemas de Amortização"
        return "Matemática Financeira", "Juros Simples"

    # 3. Estatística
    if any(k in t for k in ["mediana", "média", "media", "moda", "desvio padrão", "desvio-padrão", "variância", "gráfico", "grafico", "tabela"]):
        if any(k in t for k in ["desvio padrão", "desvio-padrão", "variância"]):
            return "Estatística", "Medidas de Dispersão (Desvio Padrão e Variância)"
        if any(k in t for k in ["mediana", "moda", "média aritmética", "media aritmetica", "ponderada"]):
            return "Estatística", "Medidas de Tendência Central (Média Aritmética, Ponderada, Moda e Mediana)"
        return "Estatística", "Leitura e Interpretação de Gráficos e Tabelas"

    # 5. Geometria Espacial
    if any(k in t for k in ["cilindro", "cone", "esfera", "prisma", "pirâmide", "piramide", "cubo", "paralelepípedo", "tronco de cone", "projeção ortogonal", "projecao ortogonal", "poliedro"]):
        if "projeção ortogonal" in t or "projecao ortogonal" in t or "sombra" in t:
            return "Geometria Espacial", "Projeção Ortogonal"
        if "esfera" in t or "esféric" in t:
            return "Geometria Espacial", "Esferas (Área e Volume)"
        if "cone" in t or "pirâmide" in t or "piramide" in t:
            return "Geometria Espacial", "Pirâmides e Cones (Áreas e Volumes)"
        if "poliedro" in t or "vértice" in t and "face" in t:
            return "Geometria Espacial", "Características dos Poliedros (Relação de Euler)"
        return "Geometria Espacial", "Prismas e Cilindros (Áreas e Volumes)"

    # 4. Geometria Plana
    if any(k in t for k in ["triângulo", "triangulo", "quadrado", "retângulo", "retangulo", "trapézio", "circunferência", "circunferencia", "raio", "diâmetro", "diametro", "área", "area", "perímetro", "perimetro", "pitágoras", "pitagoras"]):
        if "circunferência" in t or "círculo" in t or "circulo" in t:
            return "Geometria Plana", "Círculo e Circunferência"
        if "pitágoras" in t or "pitagoras" in t or "hipotenusa" in t:
            return "Geometria Plana", "Relações Métricas no Triângulo Retângulo (Teorema de Pitágoras)"
        if "semelhança" in t or "semelhante" in t:
            return "Geometria Plana", "Semelhança e Congruência de Triângulos"
        if "área" in t or "area" in t or "perímetro" in t:
            return "Geometria Plana", "Áreas de Figuras Planas"
        return "Geometria Plana", "Ângulos e Polígonos"

    # 8. Análise Combinatória
    if any(k in t for k in ["combinação", "combinacao", "arranjo", "permutação", "permutacao", "quantas maneiras", "de quantas formas", "possibilidades de escolha", "comissão", "agrupamento"]):
        if "combina" in t:
            return "Análise Combinatória", "Combinação"
        if "permuta" in t or "anagrama" in t:
            return "Análise Combinatória", "Permutação (Simples e com Repetição)"
        if "arranjo" in t:
            return "Análise Combinatória", "Arranjo"
        return "Análise Combinatória", "Princípio Fundamental da Contagem (PFC)"

    # 9. Probabilidade
    if any(k in t for k in ["probabilidade", "sorteado", "aleatoriamente", "chance de", "espaço amostral"]):
        if "dado que" in t or "sabendo que" in t:
            return "Probabilidade", "Probabilidade Condicional"
        if "independentes" in t:
            return "Probabilidade", "Eventos Independentes e Mutuamente Exclusivos"
        return "Probabilidade", "Probabilidade Simples"

    # 10. Sequências
    if any(k in t for k in ["progressão aritmética", "progressao aritmetica", " p.a.", "pa ", "progressão geométrica", "progressao geometrica", " p.g.", "pg ", "termo geral da sequência"]):
        if "geométrica" in t or "geometrica" in t or " p.g." in t:
            return "Sequências", "Progressão Geométrica (PG)"
        if "aritmética" in t or "aritmetica" in t or " p.a." in t:
            return "Sequências", "Progressão Aritmética (PA)"
        return "Sequências", "Padrões Numéricos"

    # 11. Logaritmos
    if any(k in t for k in ["logaritmo", "log ", "log(", "exponencial", "escala richter", "ph "]):
        if "log" in t:
            return "Logaritmos", "Definição e Propriedades dos Logaritmos"
        return "Logaritmos", "Conceito de Exponencial"

    # 12. Trigonometria
    if any(k in t for k in ["seno", "cosseno", "tangente", "radianos", "ciclo trigonométrico", "trigonométric"]):
        if "lei dos senos" in t or "lei dos cossenos" in t:
            return "Trigonometria", "Lei dos Senos e Lei dos Cossenos"
        if "ciclo" in t or "radiano" in t:
            return "Trigonometria", "Ciclo Trigonométrico"
        return "Trigonometria", "Relações Trigonométricas no Triângulo Retângulo (Seno, Cosseno, Tangente)"

    # 13. Geometria Analítica
    if any(k in t for k in ["plano cartesiano", "ponto médio", "distância entre dois pontos", "coeficiente angular", "equação da reta", "equação da circunferência"]):
        if "distância" in t or "ponto médio" in t:
            return "Geometria Analítica", "Distância entre Dois Pontos e Ponto Médio"
        if "circunferência" in t:
            return "Geometria Analítica", "Estudo da Circunferência"
        return "Geometria Analítica", "Estudo da Reta (Equação Geral e Reduzida)"

    # 14. Matrizes e Sistemas Lineares
    if any(k in t for k in ["matriz", "determinante", "sistema linear", "linhas e colunas"]):
        if "determinante" in t:
            return "Matrizes e Sistemas Lineares", "Determinantes"
        if "sistema" in t:
            return "Matrizes e Sistemas Lineares", "Resolução e Discussão de Sistemas Lineares"
        return "Matrizes e Sistemas Lineares", "Definição e Operações com Matrizes"

    # 6. Funções
    if any(k in t for k in ["f(x)", "função", "funcao", "parábola", "parabola", "ponto de máximo", "ponto de mínimo", "raiz da função"]):
        if "quadrática" in t or "2º grau" in t or "parábola" in t or "vértice" in t:
            return "Funções", "Função Quadrática (2º Grau)"
        if "afim" in t or "1º grau" in t:
            return "Funções", "Função Afim (1º Grau)"
        return "Funções", "Conceitos Básicos e Plano Cartesiano"

    # 7. Álgebra
    if any(k in t for k in ["equação", "equacao", "inequação", "inequacao", "fatoração", "fatoracao", "polinômio"]):
        if "fatoração" in t or "produtos notáveis" in t:
            return "Álgebra", "Produtos Notáveis e Fatoração"
        return "Álgebra", "Equações e Inequações do 1º e 2º Grau"

    # 1. Matemática Básica (Padrão)
    if any(k in t for k in ["porcentagem", "%", "por cento"]):
        return "Matemática Básica", "Porcentagem"
    if any(k in t for k in ["regra de três", "regra de tres", "proporcionalmente", "diretamente proporcional", "inversamente"]):
        return "Matemática Básica", "Regra de Três (Simples e Composta)"
    if any(k in t for k in ["razão", "razao", "proporção", "proporcao", "escala"]):
        return "Matemática Básica", "Razão e Proporção"
    if any(k in t for k in ["mmc", "mdc", "múltiplo", "divisor", "divisores"]):
        return "Matemática Básica", "Múltiplos e Divisores (MMC e MDC)"
    if any(k in t for k in ["fração", "fracao", "decimal", "decimais"]):
        return "Matemática Básica", "Frações e Números Decimais"

    return "Matemática Básica", "Operações Básicas (Adição, Subtração, Multiplicação, Divisão)"

def fetch_enem_api():
    all_questions = []
    seen_ids = set()

    for year in range(2023, 2014, -1):
        print(f"Buscando ENEM {year}...")
        for offset in [85, 130, 135]:
            url = f"https://api.enem.dev/v1/exams/{year}/questions?limit=50&offset={offset}"
            try:
                req = urllib.request.Request(url, headers={"User-Agent": "Mozilla/5.0"})
                with urllib.request.urlopen(req, timeout=12) as resp:
                    data = json.loads(resp.read().decode('utf-8'))
                    for q in data.get("questions", []):
                        if q.get("discipline") != "matematica":
                            continue
                        
                        correct = q.get("correctAlternative")
                        if not correct:
                            continue

                        q_id = f"enem_{year}_q{q.get('index')}"
                        if q_id in seen_ids:
                            continue
                        seen_ids.add(q_id)

                        context = q.get("context") or ""
                        intro = q.get("alternativesIntroduction") or ""
                        full_title = f"{context}\n\n{intro}".strip() if intro else context.strip()

                        # Ignora questões vazias ou quebradas
                        if len(full_title) < 20:
                            continue

                        alts = []
                        raw_alts = q.get("alternatives") or []
                        for alt in raw_alts:
                            txt = alt.get("text") or alt.get("letter") or ""
                            alts.append(txt)

                        if len(alts) < 4:
                            continue

                        topic, subtopic = classify_question(full_title)

                        all_questions.append({
                            "id": q_id,
                            "year": year,
                            "topic": topic,
                            "subtopic": subtopic,
                            "title": full_title,
                            "alternatives": alts[:5],
                            "correctAnswer": correct
                        })
            except Exception as e:
                print(f"Erro ao buscar offset {offset} do ano {year}: {e}")
            time.sleep(0.2)

    return all_questions

if __name__ == "__main__":
    questions = fetch_enem_api()
    print(f"Total de questões coletadas da API ENEM: {len(questions)}")
    
    # Salva no arquivo final
    target_path = "app/src/main/assets/banco_questoes.json"
    with open(target_path, "w", encoding="utf-8") as f:
        json.dump(questions, f, ensure_ascii=False, indent=2)
    print(f"Banco de questoes salvo com sucesso em {target_path}!")
