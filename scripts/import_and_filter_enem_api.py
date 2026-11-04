import urllib.request
import json
import time
import os
import re

ASSETS_DIR = "app/src/main/assets"

def clean_text(s):
    if not s:
        return ""
    # Normalize markdown bold markers or excessive spaces
    s = s.replace("\r", "")
    return s.strip()

def classify_chemistry_module(text):
    t = text.lower()
    # Check if Chemistry
    chem_keywords = [
        'reação', 'reacao', 'química', 'quimica', 'átomo', 'atomo', 'elétron', 'eletron', 'molécula', 'molecula',
        'ácido', 'acido', 'base ', 'sais', 'óxido', 'oxido', 'mol ', 'mols', 'molar', 'estequiometr',
        'oxidação', 'oxidacao', 'redução', 'reducao', 'pilha', 'eletrólise', 'eletrolise', 'ânodo', 'anodo', 'cátodo', 'catodo',
        'isomeria', 'hidrocarboneto', 'álcool', 'alcool', 'éter', 'eter', 'aldeído', 'aldeido', 'cetona', 'éster', 'ester',
        'amina', 'amida', 'solução', 'solucao', 'solubilidade', 'entalpia', 'catalisador', 'ph ', 'poh ', 'le chatelier',
        'destilação', 'destilacao', 'decantação', 'decantacao', 'floculação', 'floculacao', 'biodiesel', 'petróleo', 'petroleo'
    ]
    matches = sum(1 for w in chem_keywords if w in t)
    if matches < 2 and not any(k in t for k in ['estequiometr', 'pilha', 'eletrólise', 'isomeria', 'hidrocarboneto', 'entalpia', 'catalisador']):
        return None

    # Módulo 3: Química Orgânica
    if any(k in t for k in [
        'hidrocarboneto', 'álcool', 'alcool', 'fenol', 'éter', 'eter', 'aldeído', 'aldeido', 'cetona',
        'ácido carboxílico', 'acido carboxilico', 'éster', 'ester', 'amina', 'amida', 'isomeria',
        'quiral', 'enantiômer', 'cadeia carbônica', 'cadeia carbonica', 'iupac', 'esterificação',
        'saponificação', 'saponificacao', 'triglicerídeo', 'polímero', 'polimeriz'
    ]):
        return ("mod_quim_03_organica", "Módulo 3: Química Orgânica", "Química Orgânica", "Reconhecimento de Funções Orgânicas e Reações")

    # Módulo 4: Química Ambiental e do Cotidiano
    if any(k in t for k in [
        'tratamento de água', 'tratamento de agua', 'floculação', 'floculacao', 'chuva ácida', 'chuva acida',
        'camada de ozônio', 'camada de ozonio', 'cfc', 'petróleo', 'petroleo', 'biodiesel', 'biocombustível',
        'biocombustivel', 'sabão', 'sabao', 'detergente', 'micela', 'tensão superficial', 'reciclagem',
        'efeito estufa', 'aquecimento global'
    ]):
        return ("mod_quim_04_ambiental", "Módulo 4: Química Ambiental e do Cotidiano", "Química Ambiental e do Cotidiano", "Química Ambiental e Sustentabilidade")

    # Módulo 2: Físico-Química
    if any(k in t for k in [
        'estequiometr', 'molar', 'mol ', 'solução', 'solucao', 'concentração', 'concentracao', 'diluição',
        'diluicao', 'entalpia', 'termoquímica', 'termoquimica', 'cinética', 'cinetica', 'catalisador',
        'equilíbrio químico', 'equilibrio quimico', 'le chatelier', 'ph ', 'poh', 'pilha', 'eletrólise',
        'eletrolise', 'oxirredução', 'oxirreducao', 'potencial padrão', 'potencial padrao'
    ]):
        return ("mod_quim_02_fisico", "Módulo 2: Físico-Química", "Físico-Química", "Estequiometria, Físico-Química e Equilíbrios")

    # Módulo 1: Geral
    return ("mod_quim_01_geral", "Módulo 1: Química Geral e Estrutura da Matéria", "Química Geral e Estrutura da Matéria", "Substâncias, Misturas e Estrutura Atômica")

def classify_geography_module(text):
    t = text.lower()
    # Check if Geography
    geo_score = sum(1 for w in [
        'relevo', 'clima', 'bacia', 'hidrografia', 'solo', 'bioma', 'cerrado', 'caatinga', 'amazônia', 'amazonia',
        'agrário', 'agrario', 'campo', 'latifúndio', 'agronegócio', 'agronegocio', 'soja', 'reforma agrária',
        'urbanização', 'urbanizacao', 'cidade', 'metrópole', 'metropole', 'conurbação', 'migração', 'migracao',
        'êxodo rural', 'exodo rural', 'demografia', 'população', 'populacao', 'taxa de fecundidade',
        'globalização', 'globalizacao', 'capitalismo', 'comércio exterior', 'divisão internacional do trabalho',
        'dit ', 'industrialização', 'fordismo', 'taylorismo', 'toyotismo', 'geopolítica', 'geopolitica', 'guerra fria',
        'fronteira', 'bloco econômico', 'conflito'
    ] if w in t)

    if geo_score < 2:
        return None

    # Módulo 1: Física e Meio Ambiente
    if any(k in t for k in ['relevo', 'geomorfologia', 'clima', 'pluviométrico', 'massa de ar', 'el niño', 'la niña', 'bacia hidrográfica', 'aquífero', 'cartografia', 'escala', 'fuso horário', 'desmatamento', 'assoreamento']):
        return ("mod_geo_01_fisica", "Módulo 1: Geografia Física e Meio Ambiente", "Geografia Física e Meio Ambiente", "Geomorfologia, Clima e Bacias")

    # Módulo 2: Agrária
    if any(k in t for k in ['agrário', 'agraria', 'campo', 'latifúndio', 'agronegócio', 'agronegocio', 'soja', 'reforma agrária', 'agricultura familiar', 'revolução verde', 'arco do desmatamento', 'grileiro']):
        return ("mod_geo_02_agraria", "Módulo 2: Geografia Agrária", "Geografia Agrária (O Espaço Rural)", "Estrutura Fundiária e Agronegócio")

    # Módulo 3: Urbana e Demografia
    if any(k in t for k in ['urbanização', 'urbanizacao', 'metrópole', 'metropole', 'megacidade', 'conurbação', 'segregação socioespacial', 'periferização', 'favelização', 'saneamento', 'migração', 'êxodo rural', 'pirâmide etária', 'transição demográfica']):
        return ("mod_geo_03_urbana_demografia", "Módulo 3: Geografia Urbana e Demografia", "Geografia Urbana e Demografia", "Urbanização e Dinâmica Populacional")

    # Módulo 4: Econômica e Globalização
    if any(k in t for k in ['globalização', 'globalizacao', 'fases do capitalismo', 'dit', 'divisão internacional do trabalho', 'taylorismo', 'fordismo', 'toyotismo', 'multinacional', 'transnacional', 'substituição de importações']):
        return ("mod_geo_04_economica", "Módulo 4: Geografia Econômica e Globalização", "Geografia Econômica e Globalização", "Globalização e Modelos Produtivos")

    # Módulo 5: Geopolítica
    return ("mod_geo_05_geopolitica", "Módulo 5: Geopolítica e Ordem Mundial", "Geopolítica e Ordem Mundial", "Geopolítica e Relações Internacionais")

def classify_history_module(text):
    t = text.lower()
    if any(k in t for k in ['capitania', 'sesmaria', 'açúcar', 'engenho', 'quilombo', 'palmares', 'bandeirante', 'mineração', 'ouro preto', 'inconfidência', 'marquês de pombal']):
        return ("mod_hist_01_colonia", "Módulo 1: Brasil Colônia", "História do Brasil: Colônia", "Brasil Colonial e Resistência")
    if any(k in t for k in ['dom pedro', 'd. pedro', 'regência', 'balaiada', 'cabanagem', 'farroupilha', 'segundo reinado', 'café com leite', 'guerra do paraguai', 'abolição da escravidão', 'leis abolicionistas', 'princesa isabel']):
        return ("mod_hist_02_imperio", "Módulo 2: Brasil Império", "História do Brasil: Império", "Primeiro e Segundo Reinado")
    if any(k in t for k in ['república velha', 'republica velha', 'coronelismo', 'canudos', 'chibata', 'revolta da vacina', 'tenentismo', 'era vargas', 'estado novo', 'clt', 'juscelino', 'jk', 'brasília', 'ditadura militar', 'ai-5', 'diretas já', 'constituição de 1988', 'plano real']):
        return ("mod_hist_03_republica", "Módulo 3: Brasil República", "História do Brasil: República", "Era Vargas e Ditadura Civil-Militar")
    if any(k in t for k in ['grécia', 'atenas', 'esparta', 'democracia ateniense', 'roma', 'senado romano', 'plebeu', 'patrício', 'direito romano', 'feudalismo', 'suserania', 'vassalagem', 'igreja católica', 'cruzadas', 'mundo islâmico']):
        return ("mod_hist_04_antiguidade_medieval", "Módulo 4: Antiguidade e Medieval", "História Geral: Antiguidade e Idade Média", "Grécia, Roma e Idade Média")
    if any(k in t for k in ['renascimento', 'humanismo', 'antropocentrismo', 'lutero', 'calvino', 'reforma protestante', 'contrarreforma', 'inquisição', 'absolutismo', 'mercantilismo', 'expansão marítima', 'grandes navegações']):
        return ("mod_hist_05_moderna", "Módulo 5: Idade Moderna", "História Geral: Idade Moderna", "Renascimento e Reformas Religiosas")
    return ("mod_hist_06_contemporanea", "Módulo 6: Idade Contemporânea", "História Geral: Idade Contemporânea", "Iluminismo, Revoluções e Guerras")

def classify_languages_module(text):
    t = text.lower()
    if any(k in t for k in ['barroco', 'arcadismo', 'romantismo', 'realismo', 'machado de assis', 'naturalismo', 'parnasianismo', 'simbolismo', 'modernismo', 'semana de 22', 'drummond', 'clarice lispector', 'guimarães rosa']):
        return ("mod_ling_03_literatura", "Módulo 3: Literatura Brasileira", "Literatura Brasileira", "Escolas Literárias e Poesia")
    if any(k in t for k in ['variação linguística', 'preconceito linguístico', 'função da linguagem', 'função emotiva', 'função conativa', 'metalinguística', 'jakobson', 'coesão', 'coerência']):
        return ("mod_ling_02_linguistica", "Módulo 2: Linguística e Comunicação", "Linguística e Comunicação", "Funções da Linguagem e Variação")
    if any(k in t for k in ['quadro', 'pintura', 'tarsila do amaral', 'vanguardas europeias', 'cubismo', 'surrealismo', 'dança', 'esporte', 'cultura corporal', 'expressão corporal', 'patrimônio cultural']):
        return ("mod_ling_05_artes", "Módulo 5: Artes e Expressão Corporal", "Artes e Expressão Corporal", "Artes Visuais e Cultura Corporal")
    if any(k in t for k in ['concordância', 'regência', 'crase', 'pronome', 'colocação pronominal', 'conectivo', 'conjunção']):
        return ("mod_ling_04_gramatica", "Módulo 4: Gramática no Texto", "Gramática Aplicada ao Texto", "Morfossintaxe e Norma Padrão")
    return ("mod_ling_01_interpretacao", "Módulo 1: Interpretação e Semântica", "Interpretação de Texto e Semântica", "Gêneros Textuais e Intertextualidade")

def fetch_exam_questions_api(year, offset, limit=50):
    url = f"https://api.enem.dev/v1/exams/{year}/questions?limit={limit}&offset={offset}"
    req = urllib.request.Request(url, headers={"User-Agent": "Mozilla/5.0 (Knowverse ENEM Importer)"})
    try:
        with urllib.request.urlopen(req, timeout=12) as resp:
            data = json.loads(resp.read().decode('utf-8'))
            return data.get('questions', [])
    except Exception as e:
        print(f"  [API] Erro ao buscar {year} offset {offset}: {e}")
        return []

def main():
    print("=" * 60)
    print("INICIANDO IMPORTAÇÃO E FILTRAGEM DO BANCO OFICIAL ENEM")
    print("=" * 60)

    # 1. Carregar questões já existentes para reclassificação inteligente
    existing_natureza = []
    try:
        with open(os.path.join(ASSETS_DIR, "questoes_enem_natureza.json"), "r", encoding="utf-8") as f:
            existing_natureza = json.load(f)
    except Exception as e:
        print("Aviso ao carregar questoes_enem_natureza:", e)

    existing_humanas = []
    try:
        with open(os.path.join(ASSETS_DIR, "questoes_enem_humanas.json"), "r", encoding="utf-8") as f:
            existing_humanas = json.load(f)
    except Exception as e:
        print("Aviso ao carregar questoes_enem_humanas:", e)

    existing_geografia = []
    try:
        with open(os.path.join(ASSETS_DIR, "questoes_enem_geografia.json"), "r", encoding="utf-8") as f:
            existing_geografia = json.load(f)
    except Exception as e:
        print("Aviso ao carregar questoes_enem_geografia:", e)

    existing_linguagens = []
    try:
        with open(os.path.join(ASSETS_DIR, "questoes_enem_linguagens.json"), "r", encoding="utf-8") as f:
            existing_linguagens = json.load(f)
    except Exception as e:
        print("Aviso ao carregar questoes_enem_linguagens:", e)

    # Conjuntos de separação por disciplina
    quimica_list = []
    natureza_filtered = [] # Física e Biologia
    geografia_list = list(existing_geografia)
    humanas_list = list(existing_humanas)
    linguagens_list = list(existing_linguagens)

    seen_ids = set()
    for q in geografia_list: seen_ids.add(q["id"])
    for q in humanas_list: seen_ids.add(q["id"])
    for q in linguagens_list: seen_ids.add(q["id"])

    # Separar Química do arquivo existente de Natureza
    print("Filtrando e isolando Química de questoes_enem_natureza.json...")
    for q in existing_natureza:
        full_text = q.get("statement", "") + " " + " ".join(q.get("options", []))
        chem_info = classify_chemistry_module(full_text)
        if chem_info is not None:
            mod_id, mod_title, topic_name, subtopic_name = chem_info
            quim_item = dict(q)
            quim_item["disciplina"] = "Química"
            quim_item["topic"] = topic_name
            quim_item["subtopic"] = subtopic_name
            quim_item["moduleId"] = mod_id
            quim_item["moduleTitle"] = mod_title
            quim_item["explanation"] = f"Resolução comentada ENEM ({q.get('year', 2023)}): Análise de conceitos químicos em {mod_title}. Alternativa correta: {q.get('correctLetter', 'A')}."
            quimica_list.append(quim_item)
            seen_ids.add(quim_item["id"])
        else:
            natureza_filtered.append(q)
            seen_ids.add(q["id"])

    print(f"  -> Isoladas {len(quimica_list)} questões de Química do banco local.")
    print(f"  -> Restantes {len(natureza_filtered)} questões de Física/Biologia em Natureza.")

    # 2. Consultar a API oficial do ENEM para buscar e enriquecer com exames adicionais
    years_to_query = [2023, 2022, 2021, 2020, 2019, 2018]
    print("\nBuscando questões frescas na API enem.dev...")

    for yr in years_to_query:
        print(f"\n--- Exame ENEM {yr} ---")
        
        # 2.1 Natureza (Offset 90..135)
        nature_api_questions = fetch_exam_questions_api(yr, offset=90, limit=46)
        print(f"  [Natureza {yr}] Recebidas {len(nature_api_questions)} questões da API.")
        
        for q in nature_api_questions:
            q_id = f"enem_api_{yr}_q{q.get('index', 0)}"
            if q_id in seen_ids:
                continue

            ctx = clean_text(q.get("context") or "")
            intro = clean_text(q.get("alternativesIntroduction") or "")
            statement = f"{ctx}\n\n{intro}".strip() if intro else ctx
            
            raw_alts = q.get("alternatives") or []
            if len(raw_alts) < 4 or not statement:
                continue

            options = []
            correct_letter = q.get("correctAlternative") or "A"
            for alt in raw_alts:
                txt = clean_text(alt.get("text") or "")
                options.append(f"{alt.get('letter', 'A')}) {txt}")

            full_text = statement + " " + " ".join(options)
            chem_info = classify_chemistry_module(full_text)

            if chem_info is not None:
                mod_id, mod_title, topic_name, subtopic_name = chem_info
                quimica_list.append({
                    "id": q_id,
                    "year": yr,
                    "disciplina": "Química",
                    "topic": topic_name,
                    "subtopic": subtopic_name,
                    "moduleId": mod_id,
                    "moduleTitle": mod_title,
                    "statement": statement,
                    "options": options,
                    "correctLetter": correct_letter,
                    "explanation": f"Resolução comentada ENEM {yr} (Questão {q.get('index')}): Análise de conceitos químicos em {mod_title}. Alternativa correta: {correct_letter}.",
                    "difficulty": "Média"
                })
                seen_ids.add(q_id)
            else:
                # Física ou Biologia
                is_physics = any(k in full_text.lower() for k in ['velocidade', 'força', 'aceleração', 'circuito', 'resistor', 'luz', 'calor', 'energia mecânica', 'newton'])
                disc_name = "Física" if is_physics else "Biologia"
                mod_id = "mod_fis_01_mecanica" if is_physics else "mod_bio_01_ecologia"
                mod_title = "Módulo 1: Mecânica" if is_physics else "Módulo 1: Ecologia"
                natureza_filtered.append({
                    "id": q_id,
                    "year": yr,
                    "disciplina": disc_name,
                    "topic": f"Ciências da Natureza • {disc_name}",
                    "subtopic": mod_title,
                    "moduleId": mod_id,
                    "moduleTitle": mod_title,
                    "statement": statement,
                    "options": options,
                    "correctLetter": correct_letter,
                    "explanation": f"Resolução comentada ENEM {yr} (Questão {q.get('index')}): Conceitos fundamentais de {disc_name}. Alternativa correta: {correct_letter}.",
                    "difficulty": "Média"
                })
                seen_ids.add(q_id)

        # 2.2 Humanas (Offset 45..90) - Split História e Geografia
        humanas_api = fetch_exam_questions_api(yr, offset=45, limit=46)
        print(f"  [Humanas {yr}] Recebidas {len(humanas_api)} questões da API.")
        
        for q in humanas_api:
            q_id = f"enem_api_{yr}_q{q.get('index', 0)}"
            if q_id in seen_ids:
                continue

            ctx = clean_text(q.get("context") or "")
            intro = clean_text(q.get("alternativesIntroduction") or "")
            statement = f"{ctx}\n\n{intro}".strip() if intro else ctx
            raw_alts = q.get("alternatives") or []
            if len(raw_alts) < 4 or not statement:
                continue

            options = []
            correct_letter = q.get("correctAlternative") or "A"
            for alt in raw_alts:
                txt = clean_text(alt.get("text") or "")
                options.append(f"{alt.get('letter', 'A')}) {txt}")

            full_text = statement + " " + " ".join(options)
            geo_info = classify_geography_module(full_text)

            if geo_info is not None:
                mod_id, mod_title, topic_name, subtopic_name = geo_info
                geografia_list.append({
                    "id": q_id,
                    "year": yr,
                    "disciplina": "Geografia",
                    "topic": topic_name,
                    "subtopic": subtopic_name,
                    "moduleId": mod_id,
                    "moduleTitle": mod_title,
                    "statement": statement,
                    "options": options,
                    "correctLetter": correct_letter,
                    "explanation": f"Resolução comentada ENEM {yr} (Questão {q.get('index')}): Processos socioespaciais e geográficos em {mod_title}. Alternativa correta: {correct_letter}.",
                    "difficulty": "Média"
                })
                seen_ids.add(q_id)
            else:
                mod_id, mod_title, topic_name, subtopic_name = classify_history_module(full_text)
                humanas_list.append({
                    "id": q_id,
                    "year": yr,
                    "disciplina": "História",
                    "topic": topic_name,
                    "subtopic": subtopic_name,
                    "moduleId": mod_id,
                    "moduleTitle": mod_title,
                    "statement": statement,
                    "options": options,
                    "correctLetter": correct_letter,
                    "explanation": f"Resolução comentada ENEM {yr} (Questão {q.get('index')}): Contextualização histórica e cidadania em {mod_title}. Alternativa correta: {correct_letter}.",
                    "difficulty": "Média"
                })
                seen_ids.add(q_id)

        # 2.3 Linguagens (Offset 0..45)
        ling_api = fetch_exam_questions_api(yr, offset=0, limit=45)
        print(f"  [Linguagens {yr}] Recebidas {len(ling_api)} questões da API.")
        
        for q in ling_api:
            q_id = f"enem_api_{yr}_q{q.get('index', 0)}"
            if q_id in seen_ids:
                continue

            ctx = clean_text(q.get("context") or "")
            intro = clean_text(q.get("alternativesIntroduction") or "")
            statement = f"{ctx}\n\n{intro}".strip() if intro else ctx
            raw_alts = q.get("alternatives") or []
            if len(raw_alts) < 4 or not statement:
                continue

            options = []
            correct_letter = q.get("correctAlternative") or "A"
            for alt in raw_alts:
                txt = clean_text(alt.get("text") or "")
                options.append(f"{alt.get('letter', 'A')}) {txt}")

            full_text = statement + " " + " ".join(options)
            mod_id, mod_title, topic_name, subtopic_name = classify_languages_module(full_text)
            linguagens_list.append({
                "id": q_id,
                "year": yr,
                "disciplina": "Linguagens",
                "topic": topic_name,
                "subtopic": subtopic_name,
                "moduleId": mod_id,
                "moduleTitle": mod_title,
                "statement": statement,
                "options": options,
                "correctLetter": correct_letter,
                "explanation": f"Resolução comentada ENEM {yr} (Questão {q.get('index')}): Leitura crítica e análise textual em {mod_title}. Alternativa correta: {correct_letter}.",
                "difficulty": "Média"
            })
            seen_ids.add(q_id)

        time.sleep(0.5)

    # 3. Salvar os arquivos JSON estruturados por matéria
    print("\nSalvando arquivos de banco de dados do ENEM atualizados...")

    # 3.1 Química
    quimica_path = os.path.join(ASSETS_DIR, "questoes_enem_quimica.json")
    with open(quimica_path, "w", encoding="utf-8") as f:
        json.dump(quimica_list, f, ensure_ascii=False, indent=2)
    print(f"  -> Salvo: {quimica_path} ({len(quimica_list)} questões de Química categorizadas em 4 módulos)")

    # 3.2 Natureza (Física e Biologia puras)
    natureza_path = os.path.join(ASSETS_DIR, "questoes_enem_natureza.json")
    with open(natureza_path, "w", encoding="utf-8") as f:
        json.dump(natureza_filtered, f, ensure_ascii=False, indent=2)
    print(f"  -> Salvo: {natureza_path} ({len(natureza_filtered)} questões de Física/Biologia)")

    # 3.3 Geografia
    geografia_path = os.path.join(ASSETS_DIR, "questoes_enem_geografia.json")
    with open(geografia_path, "w", encoding="utf-8") as f:
        json.dump(geografia_list, f, ensure_ascii=False, indent=2)
    print(f"  -> Salvo: {geografia_path} ({len(geografia_list)} questões de Geografia em 5 módulos)")

    # 3.4 História
    humanas_path = os.path.join(ASSETS_DIR, "questoes_enem_humanas.json")
    with open(humanas_path, "w", encoding="utf-8") as f:
        json.dump(humanas_list, f, ensure_ascii=False, indent=2)
    print(f"  -> Salvo: {humanas_path} ({len(humanas_list)} questões de História em 6 módulos)")

    # 3.5 Linguagens
    linguagens_path = os.path.join(ASSETS_DIR, "questoes_enem_linguagens.json")
    with open(linguagens_path, "w", encoding="utf-8") as f:
        json.dump(linguagens_list, f, ensure_ascii=False, indent=2)
    print(f"  -> Salvo: {linguagens_path} ({len(linguagens_list)} questões de Linguagens em 5 módulos)")

    print("\n" + "=" * 60)
    print("IMPORTAÇÃO E FILTRAGEM FINALIZADAS COM SUCESSO!")
    print(f"Total Geral Mapeado:")
    print(f"  • Química:    {len(quimica_list)} questões")
    print(f"  • Geografia:  {len(geografia_list)} questões")
    print(f"  • História:   {len(humanas_list)} questões")
    print(f"  • Linguagens: {len(linguagens_list)} questões")
    print(f"  • Física/Bio: {len(natureza_filtered)} questões")
    print("=" * 60)

if __name__ == "__main__":
    main()
