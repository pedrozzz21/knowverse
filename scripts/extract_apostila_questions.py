import os
import re
import json
import pymupdf

def find_options_sequence(lines):
    """
    Identifica com máxima robustez a sequência das 5 alternativas (A, B, C, D, E).
    Coleta o texto completo de cada alternativa (incluindo quebras de linha entre blocos)
    e remove divisores fleuron (❦ ❧ ❦).
    """
    opt_letters = ['A', 'B', 'C', 'D', 'E']
    
    # 1. Delimitador explícito: A), A., A-
    regex_delim = re.compile(r'^([A-E])\s*[\)\.\-]\s*(.*)$')
    matches = []
    for i, (b_idx, l) in enumerate(lines):
        m = regex_delim.match(l.strip())
        if m:
            matches.append((i, b_idx, m.group(1).upper(), m.group(2).strip(), i))
    if len(matches) >= 5:
        for i in range(len(matches) - 5, -1, -1):
            sub = matches[i:i+5]
            if [m[2] for m in sub] == opt_letters:
                result_opts = []
                for k in range(5):
                    content_start_idx = sub[k][4]
                    next_opt_line_idx = sub[k+1][0] if k < 4 else len(lines)
                    first_text = sub[k][3]
                    cont_texts = [
                        lines[idx][1].strip()
                        for idx in range(content_start_idx + 1, next_opt_line_idx)
                        if lines[idx][1].strip() and '❦' not in lines[idx][1]
                    ]
                    full_text = (first_text + ' ' + ' '.join(cont_texts)).strip()
                    result_opts.append(f"{opt_letters[k]}) {full_text}")
                return result_opts, sub[0][1]

    # 2. Letra em linha isolada (A \n texto) ou com espaço simples (A texto)
    matches_2 = []
    i = 0
    while i < len(lines):
        b_idx, l = lines[i]
        l_str = l.strip()
        m_bare = re.match(r'^([A-E])\s+(.+)$', l_str)
        if m_bare:
            matches_2.append((i, b_idx, m_bare.group(1).upper(), m_bare.group(2).strip(), i))
        elif re.match(r'^([A-E])$', l_str) and i + 1 < len(lines):
            next_b_idx, next_l = lines[i+1]
            matches_2.append((i, b_idx, l_str.upper(), next_l.strip(), i + 1))
            i += 1
        i += 1
        
    if len(matches_2) >= 5:
        for i in range(len(matches_2) - 5, -1, -1):
            sub = matches_2[i:i+5]
            if [m[2] for m in sub] == opt_letters:
                result_opts = []
                for k in range(5):
                    content_start_idx = sub[k][4]
                    next_opt_line_idx = sub[k+1][0] if k < 4 else len(lines)
                    first_text = sub[k][3]
                    cont_texts = [
                        lines[idx][1].strip()
                        for idx in range(content_start_idx + 1, next_opt_line_idx)
                        if lines[idx][1].strip() and '❦' not in lines[idx][1]
                    ]
                    full_text = (first_text + ' ' + ' '.join(cont_texts)).strip()
                    result_opts.append(f"{opt_letters[k]}) {full_text}")
                return result_opts, sub[0][1]

    return None, None

def main():
    apostila_dir = "apostila"
    output_json_path = os.path.join("app", "src", "main", "assets", "questoes_apostila.json")
    output_images_dir = os.path.join("app", "src", "main", "assets", "images", "apostila")
    os.makedirs(output_images_dir, exist_ok=True)

    # Limpeza preventiva de imagens antigas
    for f in os.listdir(output_images_dir):
        fp = os.path.join(output_images_dir, f)
        if os.path.isfile(fp):
            try:
                os.remove(fp)
            except Exception:
                pass

    booklets = [
        ("APOSTAS EDCLEY ENEM 2026 - pt.1.pdf", "pt1", "Parte 1"),
        ("APOSTAS EDCLEY ENEM 2026 - pt.2.pdf", "pt2", "Parte 2"),
        ("APOSTAS EDCLEY ENEM 2026 - pt.3.pdf", "pt3", "Parte 3"),
        ("APOSTAS EDCLEY ENEM 2026 - pt.5.pdf", "pt5", "Parte 5"),
        ("APOSTAS-DE-EDCLEY-TEIXEIRA-ENEM-2026-PARTE 15 (1).pdf", "pt15", "Parte 15"),
        ("APOSTAS-DE-EDCLEY-TEIXEIRA-ENEM-2026-PARTE-10.pdf", "pt10", "Parte 10"),
        ("APOSTAS-EDCLEY-TEIXEIRA---PARTE-11_261004_203032.pdf", "pt11", "Parte 11"),
        ("APOSTAS_EDCLEY---Ultima-parte-.pdf", "ultima", "Revisão Final"),
        ("APOSTAS_EDCLEY-TEIXEIRA_PARTE-12_261004_203044.pdf", "pt12", "Parte 12"),
        ("APOSTAS_ENEM_2026_PARTE-9.pdf", "pt9", "Parte 9"),
    ]

    img_strict_patterns = [
        r'\bna\s+figura\b', r'\bda\s+figura\b', r'\bna\s+imagem\b', r'\bda\s+imagem\b',
        r'\bna\s+tirinha\b', r'\bda\s+tirinha\b', r'\bna\s+charge\b', r'\bda\s+charge\b',
        r'\bno\s+gr[aá]fico\b', r'\bdo\s+gr[aá]fico\b', r'\bno\s+mapa\b', r'\bdo\s+mapa\b',
        r'\bna\s+fotografia\b', r'\bda\s+foto\b', r'\ba\s+seguir\s+ilustra\b',
        r'\bobservando\s+a\s+figura\b', r'\bconforme\s+a\s+figura\b', r'\bconforme\s+o\s+gr[aá]fico\b'
    ]
    img_strict_regex = re.compile('|'.join(img_strict_patterns), re.I)

    caption_regex = re.compile(
        r'^(Corte|Perfil|À esquerda|À direita|Cada segmento|Figura|Gr[aá]fico|Tabela|Esquema|Representa|Fonte:|Disponível)',
        re.I
    )

    prompt_regex = re.compile(
        r'^(Considerando|Com base|Com relação|A partir|Nessas condições|Nesse sentido|Sabendo|Suponha|De acordo|O |A |Qual|Determine|Calcule|Um dos|Para )',
        re.I
    )

    all_questions = []
    seen_statements = set()

    total_images_saved = 0
    total_ignored = 0
    total_accepted = 0

    for filename, tag, booklet_title in booklets:
        pdf_path = os.path.join(apostila_dir, filename)
        if not os.path.exists(pdf_path):
            print(f"Skipping {filename} (not found)")
            continue

        doc = pymupdf.open(pdf_path)
        print(f"\nProcessing {filename} ({len(doc)} pages)...")

        # 1. Localiza a página do Gabarito
        gab_page = -1
        for pnum in range(len(doc)):
            txt = doc[pnum].get_text()
            clean = re.sub(r'[\s\xa0]+', '', txt).upper()
            if 'GABARITO' in clean and ('CHAVEDECORRECAO' in clean or 'CHAVEDECORREÇÃO' in clean or 'PARTEII' in clean):
                gab_page = pnum
                break

        if gab_page == -1:
            print(f"  WARNING: Gabarito page not found in {filename}!")
            gab_page = len(doc)

        # 2. Extrai o Gabarito oficial
        gabarito = {}
        for pnum in range(gab_page, min(gab_page + 3, len(doc))):
            gtxt = doc[pnum].get_text()
            clean_txt = re.sub(r'Q\s*\.\s*(\d+)\s+(\d+)', r'Q.\1\2', gtxt)
            clean_txt = re.sub(r'Q\s*\.\s*(\d+)', r'Q.\1', clean_txt)
            lines = [l.strip() for l in clean_txt.splitlines() if l.strip()]
            for i, l in enumerate(lines):
                m = re.match(r'^Q\.(\d+)$', l)
                if m and i + 1 < len(lines):
                    next_l = lines[i+1].strip()
                    if re.match(r'^[A-E]$', next_l):
                        gabarito[int(m.group(1))] = next_l.upper()
                m2 = re.match(r'^Q\.(\d+)[\s:\-]+([A-E])$', l)
                if m2:
                    gabarito[int(m2.group(1))] = m2.group(2).upper()

        print(f"  Gabarito: {len(gabarito)} respostas mapeadas (Pág {gab_page+1})")

        # 3. Extrai Comentários Interpretativos
        explanations = {}
        current_exp_q = None
        current_exp_lines = []

        for pnum in range(gab_page, len(doc)):
            blocks = sorted(doc[pnum].get_text('blocks'), key=lambda b: (b[1], b[0]))
            page_txt = '\n'.join([b[4] for b in blocks])
            for l in page_txt.splitlines():
                str_l = l.strip()
                clean_l = re.sub(r'[\s\xa0]+', '', str_l).upper().replace('Ã','A')
                if 'VOLTAR' in clean_l:
                    continue
                m = re.match(r'^QUESTAO(\d+)', clean_l)
                if m:
                    if current_exp_q is not None and current_exp_lines:
                        explanations[current_exp_q] = '\n'.join(current_exp_lines).strip()
                    current_exp_q = int(m.group(1))
                    current_exp_lines = []
                elif current_exp_q is not None:
                    if not re.match(r'^LETRA\s*[A-E]?$', clean_l) and 'PARTEII' not in clean_l and 'COMENTARIOS' not in clean_l and '❦' not in str_l:
                        current_exp_lines.append(str_l)

        if current_exp_q is not None and current_exp_lines:
            explanations[current_exp_q] = '\n'.join(current_exp_lines).strip()

        print(f"  Comentários: {len(explanations)} resoluções comentadas mapeadas")

        # 4. Extrai Questões e Diagramas Visuais Completos
        booklet_accepted = 0
        booklet_ignored = 0
        booklet_images = 0

        for pnum in range(1, gab_page):
            page = doc[pnum]
            blocks = sorted(page.get_text('blocks'), key=lambda b: (b[1], b[0]))

            q_header_indices = []
            for i, b in enumerate(blocks):
                clean = re.sub(r'[\s\xa0]+', '', b[4]).upper().replace('Ã','A')
                if re.search(r'QUESTAO\d+', clean):
                    q_header_indices.append(i)

            fleuron_ys = [b[3] for b in blocks if '❦' in b[4]]

            for idx_h, h_idx in enumerate(q_header_indices):
                h_b = blocks[h_idx]
                clean_h = re.sub(r'[\s\xa0]+', '', h_b[4]).upper().replace('Ã','A')
                m_num = re.search(r'QUESTAO(\d+)', clean_h)
                q_num = int(m_num.group(1)) if m_num else (booklet_accepted + 1)

                h_upper = h_b[4].upper()
                subject = "Matemática"
                if 'FÍS' in h_upper or 'FIS' in h_upper: subject = "Física"
                elif 'BIOL' in h_upper: subject = "Biologia"
                elif 'QUÍM' in h_upper or 'QUIM' in h_upper: subject = "Química"
                elif 'LING' in h_upper: subject = "Linguagens"
                elif 'HUMAN' in h_upper: subject = "Ciências Humanas"
                elif 'GEOG' in h_upper: subject = "Geografia"
                elif 'HIST' in h_upper: subject = "História"

                q_top_y = h_b[3]
                next_h_y = blocks[q_header_indices[idx_h+1]][1] if idx_h + 1 < len(q_header_indices) else page.rect.height
                f_ys = [fy for fy in fleuron_ys if q_top_y < fy <= next_h_y + 10]
                q_bottom_y = f_ys[0] if f_ys else next_h_y

                q_blocks = [b for b in blocks if q_top_y <= b[1] and b[3] <= q_bottom_y + 5 and '❦' not in b[4]]
                if not q_blocks:
                    continue

                lines = []
                for b_idx, b in enumerate(q_blocks):
                    for l in b[4].splitlines():
                        if l.strip():
                            lines.append((b_idx, l.strip()))

                options, first_opt_b_idx = find_options_sequence(lines)
                if not options or first_opt_b_idx is None:
                    continue

                first_opt_block = q_blocks[first_opt_b_idx]
                opt_y = first_opt_block[1]

                body_blocks = q_blocks[:first_opt_b_idx]

                # Desenhos vetoriais válidos e raster images estritamente acima das alternativas
                v_drawings = [
                    d['rect'] for d in page.get_drawings()
                    if d['rect'].width < 550 and d['rect'].height < 700
                    and d['rect'].y0 >= q_top_y + 10 and d['rect'].y1 <= opt_y - 8
                    and d['rect'].width >= 28 and d['rect'].height >= 20
                ]
                img_rects = []
                for info in page.get_images(full=True):
                    try:
                        bb = page.get_image_bbox(info)
                        if bb.y0 >= q_top_y + 10 and bb.y1 <= opt_y - 8 and bb.width >= 28 and bb.height >= 28:
                            img_rects.append(bb)
                    except Exception:
                        pass

                visuals = v_drawings + img_rects
                saved_image_rel_path = None

                intro_blocks = []
                prompt_blocks = []
                diag_blocks = []

                if visuals:
                    vy0 = min(r.y0 for r in visuals)
                    vy1 = max(r.y1 for r in visuals)

                    for i_bb, bb in enumerate(body_blocks):
                        by0, by1, btext = bb[1], bb[3], bb[4].strip()
                        if by1 <= vy0 - 12:
                            intro_blocks.append(bb)
                        elif by0 < vy1 + 12:
                            diag_blocks.append(bb)
                        else:
                            is_caption = bool(caption_regex.search(btext)) or (len(btext) < 45 and not prompt_regex.search(btext))
                            if is_caption and i_bb < len(body_blocks) - 1:
                                diag_blocks.append(bb)
                            else:
                                prompt_blocks.append(bb)

                    crop_y0 = max(q_top_y + 4, min(vy0, min((b[1] for b in diag_blocks), default=vy0)) - 8)
                    crop_y1 = min(opt_y - 6, max(vy1, max((b[3] for b in diag_blocks), default=vy1)) + 8)
                    crop_x0 = 45.0
                    crop_x1 = 545.0

                    clip_rect = pymupdf.Rect(crop_x0, crop_y0, crop_x1, crop_y1)
                    img_filename = f"apostila_{tag}_q{q_num:02d}.png"
                    img_out_path = os.path.join(output_images_dir, img_filename)

                    try:
                        pix = page.get_pixmap(clip=clip_rect, dpi=200)
                        pix.save(img_out_path)
                        saved_image_rel_path = f"images/apostila/{img_filename}"
                        total_images_saved += 1
                        booklet_images += 1
                    except Exception as e:
                        print(f"    Error saving visual for {tag} Q{q_num}: {e}")
                else:
                    intro_blocks = body_blocks

                intro_text = '\n\n'.join([b[4].strip() for b in intro_blocks if b[4].strip()])
                prompt_text = '\n\n'.join([b[4].strip() for b in prompt_blocks if b[4].strip()])

                clean_intro = re.sub(r'Q\s*U\s*E\s*S\s*T\s*Ã\s*O\s*\d+.*?\n', '', intro_text, flags=re.I).strip()
                clean_intro = re.sub(r'VER\s*RESOLUÇÃO\s*↓', '', clean_intro, flags=re.I).strip()

                if saved_image_rel_path:
                    parts_stmt = []
                    if clean_intro:
                        parts_stmt.append(clean_intro)
                    parts_stmt.append(f"![Esquema Didático Ilustrativo]({saved_image_rel_path})")
                    if prompt_text:
                        parts_stmt.append(prompt_text)
                    statement = '\n\n'.join(parts_stmt).strip()
                else:
                    statement = clean_intro

                needs_img = bool(img_strict_regex.search(statement))
                if needs_img and not saved_image_rel_path:
                    booklet_ignored += 1
                    total_ignored += 1
                    continue

                if len(statement) < 25:
                    continue

                norm_stmt = re.sub(r'\s+', ' ', statement).strip()
                if norm_stmt in seen_statements:
                    continue
                seen_statements.add(norm_stmt)

                correct_alt = gabarito.get(q_num, "A")
                explanation = explanations.get(q_num, "")

                q_obj = {
                    "id": f"apostila_{tag}_q{q_num:02d}",
                    "year": 2026,
                    "title": f"Apostila Edcley 2026 - {booklet_title} - Questão {q_num:02d}",
                    "statement": statement,
                    "options": options,
                    "correctAlternative": correct_alt,
                    "subject": subject,
                    "difficulty": "Médio",
                    "explanation": explanation if explanation else f"Gabarito oficial: Letra {correct_alt}.",
                    "image": saved_image_rel_path
                }

                all_questions.append(q_obj)
                booklet_accepted += 1
                total_accepted += 1

        print(f"  Resultados {tag}: {booklet_accepted} aceitas ({booklet_images} com diagrama completo), {booklet_ignored} ignoradas por falta de imagem")

    print(f"\n==========================================")
    print(f"TOTAL: {total_accepted} questões importadas com sucesso!")
    print(f"TOTAL DE DIAGRAMAS COMPLETOS SALVOS: {total_images_saved}")
    print(f"TOTAL DE QUESTÕES IGNORADAS (sem imagem): {total_ignored}")
    print(f"Salvando em {output_json_path}...")

    with open(output_json_path, 'w', encoding='utf-8') as f:
        json.dump(all_questions, f, ensure_ascii=False, indent=2)

    print("Concluído com sucesso!")

if __name__ == "__main__":
    main()
