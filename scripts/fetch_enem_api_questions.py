import urllib.request
import json
import re
import os
import time

# Keywords for classification between Física, Biologia, and Química
KEYWORDS_FISICA = [
    r'\bvelocidade\b', r'\bacelera[çc][aã]o\b', r'\bfor[çc]a\b', r'\bnewton\b', r'\batrito\b',
    r'\benergia cin[eé]tica\b', r'\benergia potencial\b', r'\btrabalho\b', r'\bpot[eê]ncia el[eé]trica\b',
    r'\bcorrente el[eé]trica\b', r'\bresistor\b', r'\bvoltagem\b', r'\btens[aã]o el[eé]trica\b',
    r'\bcircuito\b', r'\bonda\b', r'\bfrequ[eê]ncia\b', r'\bcomprimento de onda\b', r'\bhertz\b',
    r'\bluz\b', r'\brefra[çc][aã]o\b', r'\breflex[aã]o\b', r'\bespelho\b', r'\blente\b',
    r'\bcalor\b', r'\btemperatura\b', r'\bcalorimetria\b', r'\btermodin[aâ]mica\b',
    r'\bpress[aã]o\b', r'\bempuxo\b', r'\barquimedes\b', r'\bgravita[çc][aã]o\b', r'\bmassa\b',
    r'\bquilos\b', r'\bquilograma\b', r'\bcolis[aã]o\b', r'\bimpulso\b', r'\bóptica\b',
    r'\bdecib[eé]is\b', r'\befeito doppler\b', r'\bradioatividade\b', r'\bfóton\b', r'\bcinem[aá]tica\b'
]

KEYWORDS_BIOLOGIA = [
    r'\bc[eé]lula\b', r'\bcelular\b', r'\bmembrana\b', r'\bmitoc[oô]ndria\b', r'\bdna\b', r'\brna\b',
    r'\bgene\b', r'\bgen[eé]tic[ao]\b', r'\bmuta[çc][aã]o\b', r'\bcromossomo\b', r'\bmendel\b',
    r'\balelo\b', r'\bheredit[aá]ri[ao]\b', r'\bprote[ií]na\b', r'\benzima\b', r'\bamino[aá]cido\b',
    r'\bvírus\b', r'\bbact[eé]ria\b', r'\bfungo\b', r'\bprotozo[aá]rio\b', r'\bparasita\b',
    r'\bdoen[çc]a\b', r'\bvacina\b', r'\bimunidade\b', r'\banticorpo\b', r'\bant[ií]geno\b',
    r'\becossistema\b', r'\becologia\b', r'\bbiodiversidade\b', r'\bcadeia alimentar\b',
    r'\bteia alimentar\b', r'\btr[oó]fic[ao]\b', r'\bnicho\b', r'\bhabitat\b', r'\bmutualismo\b',
    r'\bpreda[çc][aã]o\b', r'\bparasitismo\b', r'\bcompet[ií][çc][aã]o\b', r'\beutrofiza[çc][aã]o\b',
    r'\befuito estufa\b', r'\baquecimento global\b', r'\bpolui[çc][aã]o\b', r'\bsustentabilidade\b',
    r'\bfotoss[ií]ntese\b', r'\brespira[çc][aã]o celular\b', r'\bfermenta[çc][aã]o\b', r'\bclorofila\b',
    r'\bcloroplasto\b', r'\bevolu[çc][aã]o\b', r'\bdarwin\b', r'\bsele[çc][aã]o natural\b',
    r'\blamarck\b', r'\besp[eé]cie\b', r'\bpopula[çc][aã]o\b', r'\bvegetal\b', r'\bplanta\b',
    r'\banimal\b', r'\bfisiologia\b', r'\bdigest[aã]o\b', r'\bcircula[çc][aã]o\b', r'\bexcre[çc][aã]o\b',
    r'\brim\b', r'\bcora[çc][aã]o\b', r'\bpulm[aã]o\b', r'\bneur[oô]nio\b', r'\bhorm[oô]nio\b'
]

def classify_text(text):
    text_lower = text.lower()
    score_fis = 0
    score_bio = 0
    for pat in KEYWORDS_FISICA:
        if re.search(pat, text_lower):
            score_fis += 1
    for pat in KEYWORDS_BIOLOGIA:
        if re.search(pat, text_lower):
            score_bio += 1
    
    if score_fis > score_bio:
        return 'Física'
    elif score_bio > score_fis:
        return 'Biologia'
    elif score_fis > 0:
        return 'Física'
    elif score_bio > 0:
        return 'Biologia'
    else:
        return 'Ciências da Natureza'

def fetch_exam_questions(year):
    # Nature questions are at index 91-135
    url = f'https://api.enem.dev/v1/exams/{year}/questions?limit=50&offset=90'
    req = urllib.request.Request(url, headers={'User-Agent': 'Mozilla/5.0'})
    try:
        with urllib.request.urlopen(req, timeout=10) as resp:
            data = json.loads(resp.read().decode('utf-8'))
            return data.get('questions', [])
    except Exception as e:
        print(f"Error fetching {year}:", e)
        return []

def main():
    print("Mapeando questões do ENEM via api.enem.dev...")
    all_mapped = []
    
    # Process from recent exams (2023 back to 2017)
    years = [2023, 2022, 2021, 2020, 2019, 2018, 2017]
    for yr in years:
        print(f"Buscando ENEM {yr}...")
        questions = fetch_exam_questions(yr)
        nature_questions = [q for q in questions if 91 <= q.get('index', 0) <= 135]
        
        for q in nature_questions:
            idx = q.get('index')
            context = q.get('context') or ''
            intro = q.get('alternativesIntroduction') or ''
            statement = f"{context}\n\n{intro}".strip()
            
            # Alternatives list
            raw_alts = q.get('alternatives') or []
            options = []
            correct_index = 0
            correct_letter = q.get('correctAlternative') or 'A'
            
            for alt_idx, alt in enumerate(raw_alts):
                opt_text = alt.get('text') or ''
                options.append(opt_text)
                if alt.get('letter') == correct_letter or alt.get('isCorrect'):
                    correct_index = alt_idx
            
            if not statement or len(options) < 4:
                continue
            
            # Clean up broken image tags from api.enem.dev if present
            statement = statement.replace('![](https://enem.dev/broken-image.svg)', '').strip()
            
            # Classify discipline
            disc = classify_text(statement)
            
            # Only keep Física, Biologia or general Natureza
            item = {
                "id": f"enem_{yr}_q{idx}",
                "year": yr,
                "exam": f"ENEM {yr}",
                "index": idx,
                "disciplina": disc,
                "statement": statement,
                "options": options,
                "correctIndex": correct_index,
                "correctLetter": correct_letter,
                "explanation": f"Gabarito Oficial do ENEM ({correct_letter}). Esta questão avalia as competências da matriz de Ciências da Natureza do ENEM."
            }
            all_mapped.append(item)
        
        time.sleep(0.3)
        
    print(f"Total de questões de Ciências da Natureza mapeadas: {len(all_mapped)}")
    fisica_count = sum(1 for q in all_mapped if q['disciplina'] == 'Física')
    biologia_count = sum(1 for q in all_mapped if q['disciplina'] == 'Biologia')
    natureza_count = sum(1 for q in all_mapped if q['disciplina'] == 'Ciências da Natureza')
    print(f"Física: {fisica_count} | Biologia: {biologia_count} | Natureza/Química: {natureza_count}")
    
    # Save to both Web and Android public assets
    out_paths = [
        r"c:\Users\phcco\OneDrive\Área de Trabalho\knowverse-web\public\questoes_enem_natureza.json",
        r"c:\Users\phcco\OneDrive\Área de Trabalho\knowverse\app\src\main\assets\questoes_enem_natureza.json"
    ]
    
    for p in out_paths:
        os.makedirs(os.path.dirname(p), exist_ok=True)
        with open(p, "w", encoding="utf-8") as f:
            json.dump(all_mapped, f, ensure_ascii=False, indent=2)
        print(f"Salvo com sucesso em: {p}")

if __name__ == "__main__":
    main()
