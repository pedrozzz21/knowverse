import os
from PIL import Image, ImageDraw, ImageFont

# Set up paths for both Web and Android
DEST_DIRS = [
    r"c:\Users\phcco\OneDrive\Área de Trabalho\knowverse-web\public\assets\images\ecologia",
    r"c:\Users\phcco\OneDrive\Área de Trabalho\knowverse\app\src\main\assets\images\ecologia"
]

for d in DEST_DIRS:
    os.makedirs(d, exist_ok=True)

# Colors tailored for Knowverse dark mode
BG_COLOR = (15, 18, 26)
CARD_BG = (23, 28, 41)
BORDER_COLOR = (42, 51, 74)
PRIMARY_GREEN = (34, 197, 94)
ACCENT_CYAN = (56, 189, 248)
ACCENT_AMBER = (251, 191, 36)
ACCENT_RED = (248, 113, 113)
ACCENT_PURPLE = (192, 132, 252)
TEXT_WHITE = (248, 250, 252)
TEXT_MUTED = (148, 163, 184)

def get_font(size, bold=False):
    # Try Windows system fonts
    font_names = ["segoeui.ttf", "arial.ttf", "calibri.ttf"]
    if bold:
        font_names = ["segoeuib.ttf", "arialbd.ttf", "calibrib.ttf"]
    for name in font_names:
        try:
            return ImageFont.truetype(name, size)
        except:
            continue
    return ImageFont.load_default()

def save_image(img, filename):
    for d in DEST_DIRS:
        path = os.path.join(d, filename)
        img.save(path, format="PNG")
    print(f"Saved {filename} to all destinations.")

# 1. BIODIVERSIDADE / NÍVEIS ECOLÓGICOS (biodiversidade.png)
def make_biodiversidade():
    w, h = 900, 520
    img = Image.new("RGB", (w, h), BG_COLOR)
    draw = ImageDraw.Draw(img)
    
    # Header
    draw.text((40, 25), "NÍVEIS DE ORGANIZAÇÃO EM ECOLOGIA", fill=PRIMARY_GREEN, font=get_font(24, bold=True))
    draw.text((40, 60), "Do organismo individual ao conjunto de toda a vida na Terra (Biosfera)", fill=TEXT_MUTED, font=get_font(14))
    
    levels = [
        ("Organismo", "Indivíduo de uma única espécie com metabolismo próprio.", ACCENT_CYAN),
        ("População", "Conjunto de indivíduos da mesma espécie no mesmo local e tempo.", PRIMARY_GREEN),
        ("Comunidade (Biocenose)", "Conjunto de todas as diferentes populações interagindo.", ACCENT_AMBER),
        ("Ecossistema", "Interação entre a comunidade (biótico) e o ambiente físico (abiótico).", ACCENT_PURPLE),
        ("Biosfera", "A união de todos os ecossistemas do planeta Terra habitados por seres vivos.", (236, 72, 153))
    ]
    
    y = 100
    for i, (title, desc, color) in enumerate(levels):
        box_w = 820 - (i * 20)
        draw.rounded_rectangle([40 + (i * 10), y, 40 + (i * 10) + box_w, y + 68], radius=8, fill=CARD_BG, outline=color, width=2)
        # Level indicator circle
        draw.ellipse([55 + (i * 10), y + 16, 90 + (i * 10), y + 51], fill=color)
        draw.text((68 + (i * 10), y + 21), str(i + 1), fill=BG_COLOR, font=get_font(18, bold=True))
        
        draw.text((105 + (i * 10), y + 14), title, fill=TEXT_WHITE, font=get_font(17, bold=True))
        draw.text((105 + (i * 10), y + 38), desc, fill=TEXT_MUTED, font=get_font(13))
        y += 80
        
    save_image(img, "biodiversidade.png")

# 2. TEIA ALIMENTAR E FLUXO DE ENERGIA (teia_alimentar.png)
def make_teia_alimentar():
    w, h = 900, 540
    img = Image.new("RGB", (w, h), BG_COLOR)
    draw = ImageDraw.Draw(img)
    
    draw.text((40, 25), "FLUXO DE ENERGIA E NÍVEIS TRÓFICOS", fill=ACCENT_CYAN, font=get_font(24, bold=True))
    draw.text((40, 60), "A energia flui de forma UNIDIRECIONAL e DECRESCENTE a cada nível trófico", fill=TEXT_MUTED, font=get_font(14))
    
    # Pyramid levels
    levels = [
        ("Consumidores Terciários (Topo)", "Gavião, Onça (Menor energia disponível / Maior bioacumulação)", ACCENT_RED, 260),
        ("Consumidores Secundários", "Cobras, Sapos (Carnívoros predadores de herbívoros)", ACCENT_AMBER, 420),
        ("Consumidores Primários", "Insetos, Roedores, Cervos (Herbívoros)", ACCENT_CYAN, 580),
        ("Produtores (1º Nível Trófico)", "Plantas, Algas, Fitoplâncton (Autótrofos fotossintetizantes - 100% Energia)", PRIMARY_GREEN, 740)
    ]
    
    y = 100
    for title, desc, color, width in levels:
        x_left = (w - width) // 2
        x_right = x_left + width
        draw.rounded_rectangle([x_left, y, x_right, y + 68], radius=8, fill=CARD_BG, outline=color, width=2)
        draw.text((x_left + 20, y + 14), title, fill=color, font=get_font(16, bold=True))
        draw.text((x_left + 20, y + 38), desc, fill=TEXT_WHITE, font=get_font(13))
        y += 82
        
    # Decomposers box below
    draw.rounded_rectangle([80, y + 10, w - 80, y + 75], radius=8, fill=(30, 27, 46), outline=ACCENT_PURPLE, width=2)
    draw.text((100, y + 20), "DECOMPOSITORES (Fungos e Bactérias) - Reciclam a matéria de TODOS os níveis", fill=ACCENT_PURPLE, font=get_font(15, bold=True))
    draw.text((100, y + 44), "A matéria orgânica é reciclada (cíclica), enquanto a energia se dissipa na forma de calor.", fill=TEXT_MUTED, font=get_font(13))
    
    save_image(img, "teia_alimentar.png")

# 3. RELAÇÕES ECOLÓGICAS (relacoes_ecologicas.png)
def make_relacoes_ecologicas():
    w, h = 900, 560
    img = Image.new("RGB", (w, h), BG_COLOR)
    draw = ImageDraw.Draw(img)
    
    draw.text((40, 25), "CLASSIFICAÇÃO DAS RELAÇÕES ECOLÓGICAS", fill=ACCENT_AMBER, font=get_font(24, bold=True))
    draw.text((40, 60), "Interações biológicas interespecíficas e intraespecíficas (Cobrança direta no ENEM)", fill=TEXT_MUTED, font=get_font(14))
    
    # Left column: Harmônicas (+/+ e +/0)
    draw.rounded_rectangle([40, 100, 435, 520], radius=10, fill=CARD_BG, outline=PRIMARY_GREEN, width=2)
    draw.text((60, 115), "RELAÇÕES HARMÔNICAS (+)", fill=PRIMARY_GREEN, font=get_font(18, bold=True))
    draw.text((60, 140), "Nenhum indivíduo sofre prejuízo", fill=TEXT_MUTED, font=get_font(13))
    
    harm_items = [
        ("Mutualismo (+ / +)", "Obrigatória. Ambos se beneficiam.\nEx: Líquenes (alga + fungo), micorrizas."),
        ("Protocooperação (+ / +)", "Não obrigatória. Ambas lucram.\nEx: Pássaro-palito e crocodilo, anêmona e caranguejo."),
        ("Comensalismo (+ / 0)", "Benefício alimentar sem prejudicar.\nEx: Rêmora e tubarão."),
        ("Inquilinismo / Epifitismo (+ / 0)", "Abrigo ou suporte mecânico sem dano.\nEx: Orquídeas vivendo sobre árvores.")
    ]
    
    yh = 175
    for title, desc in harm_items:
        draw.text((60, yh), title, fill=TEXT_WHITE, font=get_font(15, bold=True))
        draw.text((60, yh + 20), desc, fill=TEXT_MUTED, font=get_font(12))
        yh += 80
        
    # Right column: Desarmônicas (+/- e -/-)
    draw.rounded_rectangle([465, 100, 860, 520], radius=10, fill=CARD_BG, outline=ACCENT_RED, width=2)
    draw.text((485, 115), "RELAÇÕES DESARMÔNICAS (-)", fill=ACCENT_RED, font=get_font(18, bold=True))
    draw.text((485, 140), "Pelo menos um dos envolvidos é prejudicado", fill=TEXT_MUTED, font=get_font(13))
    
    des_items = [
        ("Competição (- / -)", "Disputa por nicho ecológico, luz, água ou parceiro.\nCausa gasto energético prejudicial a ambos."),
        ("Predação (+ / -)", "Predador captura, mata e devora a presa.\nEx: Leão e zebra, gavião e rato."),
        ("Parasitismo (+ / -)", "Parasita retira nutrientes do hospedeiro.\nEx: Lombriga no intestino, carrapato, tênia."),
        ("Amensalismo / Antibiose (+ / -)", "Uma espécie secreta toxina inibidora.\nEx: Fungo Penicillium mata bactérias (penicilina).")
    ]
    
    yd = 175
    for title, desc in des_items:
        draw.text((485, yd), title, fill=TEXT_WHITE, font=get_font(15, bold=True))
        draw.text((485, yd + 20), desc, fill=TEXT_MUTED, font=get_font(12))
        yd += 80
        
    save_image(img, "relacoes_ecologicas.png")

# 4. CICLO DO NITROGÊNIO (ciclo_nitrogenio.png)
def make_ciclo_nitrogenio():
    w, h = 900, 520
    img = Image.new("RGB", (w, h), BG_COLOR)
    draw = ImageDraw.Draw(img)
    
    draw.text((40, 25), "CICLO BIOGEOQUÍMICO DO NITROGÊNIO", fill=PRIMARY_GREEN, font=get_font(24, bold=True))
    draw.text((40, 60), "Etapas bacterianas essenciais para a assimilação de Nitrogênio pelas plantas", fill=TEXT_MUTED, font=get_font(14))
    
    # Top box: N2 atmosférico
    draw.rounded_rectangle([250, 100, 650, 160], radius=8, fill=CARD_BG, outline=ACCENT_CYAN, width=2)
    draw.text((270, 112), "NITROGÊNIO ATMOSFÉRICO (N2 - 78%)", fill=ACCENT_CYAN, font=get_font(16, bold=True))
    draw.text((270, 134), "Inerte para plantas e animais: exige ação de bactérias!", fill=TEXT_MUTED, font=get_font(12))
    
    steps = [
        ("1. FIXAÇÃO", "Bactérias Rhizobium nas raízes de leguminosas fixam N2 gerando Amônia (NH3 / NH4+)", PRIMARY_GREEN, 100, 200, 480, 310),
        ("2. NITRITAÇÃO", "Bactérias Nitrosomonas oxidam Amônia em Nitrito (NO2- altamente tóxico)", ACCENT_AMBER, 520, 200, 850, 310),
        ("3. NITRATAÇÃO", "Bactérias Nitrobacter oxidam Nitrito em Nitrato (NO3- absorvível pelas raízes)", ACCENT_CYAN, 100, 340, 480, 450),
        ("4. DESNITRIFICAÇÃO", "Bactérias Pseudomonas devolvem N2 gasoso para fechar o ciclo na atmosfera", ACCENT_PURPLE, 520, 340, 850, 450)
    ]
    
    for title, desc, col, x1, y1, x2, y2 in steps:
        draw.rounded_rectangle([x1, y1, x2, y2], radius=8, fill=CARD_BG, outline=col, width=2)
        draw.text((x1 + 15, y1 + 14), title, fill=col, font=get_font(16, bold=True))
        # multi line wrap
        draw.text((x1 + 15, y1 + 42), desc, fill=TEXT_WHITE, font=get_font(12))
        
    draw.text((40, 485), "Dica ENEM: A adubação verde (rotação com soja/feijão) enriquece o solo com Nitrato natural sem químicos!", fill=ACCENT_AMBER, font=get_font(13, bold=True))
    
    save_image(img, "ciclo_nitrogenio.png")

# 5. EUTROFIZAÇÃO (eutrofizacao.png)
def make_eutrofizacao():
    w, h = 900, 520
    img = Image.new("RGB", (w, h), BG_COLOR)
    draw = ImageDraw.Draw(img)
    
    draw.text((40, 25), "ETAPAS DO PROCESSO DE EUTROFIZAÇÃO", fill=ACCENT_RED, font=get_font(24, bold=True))
    draw.text((40, 60), "Despejo de matéria orgânica/fertilizantes e o colapso do ecossistema aquático", fill=TEXT_MUTED, font=get_font(14))
    
    chain = [
        ("1. Aporte de Nutrientes", "Despejo de esgoto doméstico não tratado ou fertilizantes agrícolas ricos em N e P.", ACCENT_AMBER),
        ("2. Floração de Algas", "Proliferação maciça de algas e cianobactérias na superfície da água formando uma camada verde.", PRIMARY_GREEN),
        ("3. Bloqueio da Luz Solar", "A camada superficial impede a entrada de luz; vegetação submersa não faz fotossíntese e morre.", ACCENT_CYAN),
        ("4. Explosão de Decompositores", "Bactérias aeróbicas decompõem a matéria morta e esgotam todo o Oxigênio Dissolvido (O2).", ACCENT_PURPLE),
        ("5. Anóxia e Morte de Peixes", "A asfixia provoca a morte em massa da fauna aquática e proliferação de anaeróbicos tóxicos.", ACCENT_RED)
    ]
    
    y = 100
    for i, (title, desc, col) in enumerate(chain):
        draw.rounded_rectangle([60, y, 840, y + 66], radius=8, fill=CARD_BG, outline=col, width=2)
        draw.ellipse([80, y + 16, 114, y + 50], fill=col)
        draw.text((92, y + 20), str(i + 1), fill=BG_COLOR, font=get_font(16, bold=True))
        
        draw.text((130, y + 12), title, fill=col, font=get_font(15, bold=True))
        draw.text((130, y + 36), desc, fill=TEXT_WHITE, font=get_font(12))
        y += 78
        
    save_image(img, "eutrofizacao.png")

# 6. EFEITO ESTUFA E AQUECIMENTO GLOBAL (efeito_estufa.png)
def make_efeito_estufa():
    w, h = 900, 520
    img = Image.new("RGB", (w, h), BG_COLOR)
    draw = ImageDraw.Draw(img)
    
    draw.text((40, 25), "EFEITO ESTUFA NATURAL vs. AQUECIMENTO GLOBAL", fill=ACCENT_AMBER, font=get_font(24, bold=True))
    draw.text((40, 60), "Diferença vital cobrada no ENEM: O efeito estufa é benéfico; o perigo é a intensificação antrópica", fill=TEXT_MUTED, font=get_font(14))
    
    # Left box: Fenômeno Natural
    draw.rounded_rectangle([50, 100, 430, 480], radius=10, fill=CARD_BG, outline=ACCENT_CYAN, width=2)
    draw.text((70, 120), "FENÔMENO NATURAL", fill=ACCENT_CYAN, font=get_font(18, bold=True))
    draw.text((70, 150), "• Radiação solar atravessa a atmosfera.\n• Superfície da Terra absorve calor e\n  emite radiação infravermelha.\n• Gases do Efeito Estufa (GEE) retêm\n  parte do calor na atmosfera.\n• Mantém a Terra aquecida (média de 15°C).\n• SEM ele: o planeta seria congelado a -18°C!", fill=TEXT_WHITE, font=get_font(13))
    
    # Right box: Intensificação Antrópica
    draw.rounded_rectangle([470, 100, 850, 480], radius=10, fill=CARD_BG, outline=ACCENT_RED, width=2)
    draw.text((490, 120), "AQUECIMENTO GLOBAL (ANTROPOGÊNICO)", fill=ACCENT_RED, font=get_font(18, bold=True))
    draw.text((490, 150), "• Queima massiva de combustíveis fósseis.\n• Desmatamento e queimadas florestais.\n• Emissão descontrolada de CO2 e Metano (CH4).\n• Retenção excessiva de radiação térmica.\n• Consequências: Derretimento polar, subida\n  dos mares, desertificação e branqueamento\n  de corais marinhos.", fill=TEXT_WHITE, font=get_font(13))
    
    save_image(img, "efeito_estufa.png")

# 7. SUSTENTABILIDADE E TRANSIÇÃO ECOLÓGICA (sustentabilidade.png)
def make_sustentabilidade():
    w, h = 900, 520
    img = Image.new("RGB", (w, h), BG_COLOR)
    draw = ImageDraw.Draw(img)
    
    draw.text((40, 25), "PILARESS DE SUSTENTABILIDADE E BIOTECNOLOGIA VERDE", fill=PRIMARY_GREEN, font=get_font(24, bold=True))
    draw.text((40, 60), "Tecnologias ecológicas mais cobradas nas provas de Ciências da Natureza do ENEM", fill=TEXT_MUTED, font=get_font(14))
    
    pillars = [
        ("ENERGIAS RENOVÁVEIS", "Solar, eólica, biomassa e biogás (aproveitamento do CH4 de aterros e fezes de gado).", ACCENT_AMBER, 50, 100, 430, 270),
        ("BIORREMEDIAÇÃO", "Uso de microrganismos (bactérias e fungos) ou plantas para degradar poluentes e petróleo.", PRIMARY_GREEN, 470, 100, 850, 270),
        ("CONTROLE BIOLÓGICO", "Emprego de predadores naturais e parasitoides contra pragas agrícolas, substituindo agrotóxicos.", ACCENT_CYAN, 50, 300, 430, 470),
        ("ECONOMIA CIRCULAR E LOGÍSTICA REVERSA", "Retorno de embalagens ao ciclo produtivo, reciclagem e redução drástica de rejeitos em aterros.", ACCENT_PURPLE, 470, 300, 850, 470)
    ]
    
    for title, desc, col, x1, y1, x2, y2 in pillars:
        draw.rounded_rectangle([x1, y1, x2, y2], radius=10, fill=CARD_BG, outline=col, width=2)
        draw.text((x1 + 20, y1 + 20), title, fill=col, font=get_font(15, bold=True))
        draw.text((x1 + 20, y1 + 55), desc, fill=TEXT_WHITE, font=get_font(13))
        
    save_image(img, "sustentabilidade.png")

if __name__ == "__main__":
    make_biodiversidade()
    make_teia_alimentar()
    make_relacoes_ecologicas()
    make_ciclo_nitrogenio()
    make_eutrofizacao()
    make_efeito_estufa()
    make_sustentabilidade()
    print("All ecology diagrams created in Portuguese successfully!")
