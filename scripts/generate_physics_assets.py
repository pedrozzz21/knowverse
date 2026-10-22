import os
from PIL import Image, ImageDraw, ImageFont

# Cores do Knowverse
BG_DARK = (17, 20, 27)
BORDER_SUBTLE = (35, 40, 52)
TEXT_PRIMARY = (248, 250, 252)
TEXT_SECONDARY = (148, 163, 184)
TEXT_TERTIARY = (100, 116, 139)
ACCENT_BLUE = (59, 130, 246)
ACCENT_GREEN = (16, 185, 129)
ACCENT_GOLD = (234, 179, 8)
ACCENT_RED = (239, 68, 68)
GRID_LINE = (27, 32, 43)

def create_base_canvas(width=900, height=500, title="", subtitle=""):
    img = Image.new("RGBA", (width, height), BG_DARK)
    draw = ImageDraw.Draw(img)
    
    # Borda externa
    draw.rectangle([0, 0, width-1, height-1], outline=BORDER_SUBTLE, width=2)
    
    # Header
    draw.text((30, 25), title, fill=TEXT_PRIMARY)
    draw.text((30, 55), subtitle, fill=TEXT_SECONDARY)
    draw.line([(30, 85), (width - 30, 85)], fill=BORDER_SUBTLE, width=1)
    
    return img, draw

# -------------------------------------------------------------
# 1. CINEMÁTICA: MRU vs MRUV (Velocidade x Tempo)
# -------------------------------------------------------------
def generate_cinematica():
    img, draw = create_base_canvas(900, 520, "CINEMÁTICA: COMPARAÇÃO DOS GRÁFICOS (v × t)", "Movimento Retilíneo Uniforme (MRU) vs Movimento Uniformemente Variado (MRUV)")
    
    # --- Painel Esquerdo: MRU ---
    # Eixos
    draw.line([(100, 420), (400, 420)], fill=TEXT_SECONDARY, width=2) # Eixo t
    draw.line([(100, 420), (100, 150)], fill=TEXT_SECONDARY, width=2) # Eixo v
    draw.text((410, 412), "Tempo t (s)", fill=TEXT_SECONDARY)
    draw.text((70, 130), "Velocidade v (m/s)", fill=TEXT_SECONDARY)
    draw.text((120, 120), "MRU (Velocidade Constante)", fill=ACCENT_BLUE)
    
    # Reta horizontal do MRU
    draw.line([(100, 260), (380, 260)], fill=ACCENT_BLUE, width=4)
    draw.text((70, 252), "v₀", fill=ACCENT_BLUE)
    
    # Área sombreada ΔS
    draw.rectangle([(140, 260), (340, 420)], fill=(59, 130, 246, 40), outline=ACCENT_BLUE, width=1)
    draw.text((190, 330), "Área = Deslocamento (ΔS)", fill=TEXT_PRIMARY)
    draw.text((210, 360), "ΔS = v · Δt", fill=ACCENT_GOLD)
    draw.text((140, 430), "t₁", fill=TEXT_TERTIARY)
    draw.text((340, 430), "t₂", fill=TEXT_TERTIARY)
    
    # --- Painel Direito: MRUV ---
    draw.line([(550, 420), (850, 420)], fill=TEXT_SECONDARY, width=2) # Eixo t
    draw.line([(550, 420), (550, 150)], fill=TEXT_SECONDARY, width=2) # Eixo v
    draw.text((860, 412), "Tempo t (s)", fill=TEXT_SECONDARY)
    draw.text((520, 130), "Velocidade v (m/s)", fill=TEXT_SECONDARY)
    draw.text((570, 120), "MRUV (Aceleração Constante)", fill=ACCENT_GREEN)
    
    # Reta inclinada do MRUV
    draw.line([(550, 340), (830, 180)], fill=ACCENT_GREEN, width=4)
    draw.text((525, 332), "v₀", fill=ACCENT_GREEN)
    
    # Trapézio da área
    draw.polygon([(590, 420), (590, 317), (790, 203), (790, 420)], fill=(16, 185, 129, 40), outline=ACCENT_GREEN)
    draw.text((630, 330), "Área do Trapézio = ΔS", fill=TEXT_PRIMARY)
    draw.text((640, 360), "v = v₀ + a·t", fill=ACCENT_GOLD)
    draw.text((590, 430), "t₁", fill=TEXT_TERTIARY)
    draw.text((790, 430), "t₂", fill=TEXT_TERTIARY)
    
    # Rodapé
    draw.text((280, 485), "Dica ENEM: No gráfico v × t, a ÁREA sempre representa o deslocamento ΔS!", fill=ACCENT_GOLD)
    return img

# -------------------------------------------------------------
# 2. DINÂMICA: PLANO INCLINADO E DECOMPOSIÇÃO DE FORÇAS
# -------------------------------------------------------------
def generate_dinamica():
    img, draw = create_base_canvas(900, 520, "DINÂMICA: FORÇAS NO PLANO INCLINADO", "Decomposição do Peso (Px e Py), Força Normal, Tração e Força de Atrito")
    
    # Desenho da Rampa
    ramp_pts = [(100, 420), (750, 420), (750, 140)]
    draw.polygon(ramp_pts, fill=(27, 32, 43), outline=BORDER_SUBTLE)
    draw.line([(100, 420), (750, 140)], fill=TEXT_SECONDARY, width=3) # Superfície inclinada
    
    # Ângulo theta
    draw.arc([160, 380, 260, 460], 180, 210, fill=ACCENT_GOLD, width=2)
    draw.text((230, 395), "Ângulo θ", fill=ACCENT_GOLD)
    
    # Bloco na rampa
    # Posição central do bloco (420, 280)
    bx, by = 420, 280
    
    # Vetores de Força (Português claro e setas)
    # 1. Peso P (vertical para baixo)
    draw.line([(bx, by), (bx, by + 130)], fill=ACCENT_GREEN, width=3)
    draw.text((bx + 10, by + 120), "Peso (P = m · g)", fill=ACCENT_GREEN)
    
    # 2. Py (perpendicular à rampa para baixo)
    draw.line([(bx, by), (bx + 60, by + 100)], fill=ACCENT_BLUE, width=2)
    draw.text((bx + 70, by + 95), "Py = P · cos(θ)", fill=ACCENT_BLUE)
    
    # 3. Px (paralelo à rampa para baixo)
    draw.line([(bx, by), (bx - 90, by + 40)], fill=ACCENT_BLUE, width=2)
    draw.text((bx - 180, by + 35), "Px = P · sen(θ)", fill=ACCENT_BLUE)
    
    # 4. Normal N (perpendicular para cima, anula Py)
    draw.line([(bx, by), (bx - 60, by - 100)], fill=ACCENT_BLUE, width=3)
    draw.text((bx - 160, by - 110), "Normal (N = Py)", fill=ACCENT_BLUE)
    
    # 5. Força de Atrito Fat ou Tração T (paralela para cima)
    draw.line([(bx, by), (bx + 100, by - 45)], fill=ACCENT_RED, width=3)
    draw.text((bx + 110, by - 55), "Tração (T) ou Atrito (Fat)", fill=ACCENT_RED)
    
    # Caixa de resumo das fórmulas
    draw.rectangle([(50, 110), (330, 230)], fill=(23, 27, 36), outline=BORDER_SUBTLE)
    draw.text((65, 120), "EQUAÇÕES DA DINÂMICA:", fill=ACCENT_GOLD)
    draw.text((65, 145), "• Na vertical da rampa: N = P · cos(θ)", fill=TEXT_PRIMARY)
    draw.text((65, 170), "• Na descida da rampa: Px = P · sen(θ)", fill=TEXT_PRIMARY)
    draw.text((65, 195), "• Força de Atrito: Fat = μ · N", fill=TEXT_PRIMARY)
    
    draw.text((260, 485), "No ENEM: Se o bloco desce em velocidade constante, a resultante é zero (Px = Fat)!", fill=ACCENT_GOLD)
    return img

# -------------------------------------------------------------
# 3. ENERGIA: TRANSFORMAÇÃO NA MONTANHA-RUSSA
# -------------------------------------------------------------
def generate_energia():
    img, draw = create_base_canvas(900, 520, "CONSERVAÇÃO DA ENERGIA MECÂNICA", "Transformação Contínua de Energia Potencial Gravitacional em Cinética")
    
    # Trilho da montanha russa (curva suave)
    track_pts = [
        (80, 160), (200, 160), (450, 420), (600, 260), (700, 420), (850, 420)
    ]
    draw.line([(80, 160), (220, 170)], fill=TEXT_SECONDARY, width=4)
    # Loop e quedas
    draw.arc([150, 160, 550, 680], 180, 360, fill=TEXT_SECONDARY, width=4)
    
    # Ponto 1: Topo Máximo (Pico)
    draw.ellipse([140, 150, 160, 170], fill=ACCENT_GOLD)
    draw.text((120, 115), "PONTO 1 (Altura Máxima h)", fill=ACCENT_GOLD)
    draw.rectangle([(80, 200), (250, 275)], fill=(23, 27, 36), outline=BORDER_SUBTLE)
    draw.text((90, 210), "Energia Potencial: MÁXIMA", fill=ACCENT_GOLD)
    draw.text((90, 230), "Ep = m · g · h", fill=TEXT_PRIMARY)
    draw.text((90, 250), "Energia Cinética: Ec = 0 (Repouso)", fill=TEXT_SECONDARY)
    
    # Ponto 2: Ponto Mais Baixo (Velocidade Máxima)
    draw.ellipse([440, 410, 460, 430], fill=ACCENT_GREEN)
    draw.text((370, 445), "PONTO 2 (Nível do Solo: h = 0)", fill=ACCENT_GREEN)
    draw.rectangle([(340, 320), (530, 395)], fill=(23, 27, 36), outline=BORDER_SUBTLE)
    draw.text((350, 330), "Energia Cinética: MÁXIMA", fill=ACCENT_GREEN)
    draw.text((350, 350), "Ec = (m · v²) / 2", fill=TEXT_PRIMARY)
    draw.text((350, 370), "Energia Potencial: Ep = 0", fill=TEXT_SECONDARY)
    
    # Ponto 3: Ponto Médio
    draw.ellipse([640, 270, 660, 290], fill=ACCENT_BLUE)
    draw.text((630, 240), "PONTO 3 (Subida Intermediária)", fill=ACCENT_BLUE)
    draw.rectangle([(620, 310), (840, 385)], fill=(23, 27, 36), outline=BORDER_SUBTLE)
    draw.text((630, 320), "Energia Mecânica Conservada:", fill=TEXT_PRIMARY)
    draw.text((630, 340), "Emec = Ep + Ec = Constante", fill=ACCENT_BLUE)
    draw.text((630, 360), "Toda Ep perdida virou Ec!", fill=TEXT_SECONDARY)
    
    draw.text((200, 485), "Princípio da Conservação: Na ausência de atrito, Emec(topo) = Emec(base)!", fill=ACCENT_GOLD)
    return img

# -------------------------------------------------------------
# 4. IMPULSO E COLISÕES: TEOREMA DO IMPULSO E AIRBAG
# -------------------------------------------------------------
def generate_colisoes():
    img, draw = create_base_canvas(900, 520, "IMPULSO E COLISÕES: O SEGREDO DO AIRBAG", "Como o aumento do tempo de impacto (Δt) reduz drasticamente a Força (F)")
    
    # Gráficos Força x Tempo (Sem Airbag vs Com Airbag)
    # Eixos
    draw.line([(100, 420), (820, 420)], fill=TEXT_SECONDARY, width=2) # Tempo t
    draw.line([(100, 420), (100, 130)], fill=TEXT_SECONDARY, width=2) # Força F
    draw.text((830, 412), "Tempo (t)", fill=TEXT_SECONDARY)
    draw.text((70, 110), "Força (F)", fill=TEXT_SECONDARY)
    
    # Curva Vermelha: Sem Airbag (Pico alto e estreito, tempo curto)
    pts_sem = [(150, 420), (220, 150), (290, 420)]
    draw.line([(150, 420), (220, 150), (290, 420)], fill=ACCENT_RED, width=4)
    draw.polygon([(150, 420), (220, 150), (290, 420)], fill=(239, 68, 68, 50))
    draw.text((150, 160), "SEM AIRBAG (Tempo curto Δt₁)", fill=ACCENT_RED)
    draw.text((150, 185), "Força de Pico MORTAL (F₁)", fill=ACCENT_RED)
    
    # Curva Azul: Com Airbag (Pico baixo e largo, tempo longo)
    pts_com = [(150, 420), (350, 300), (580, 420)]
    draw.line([(150, 420), (350, 300), (580, 420)], fill=ACCENT_BLUE, width=4)
    draw.polygon([(150, 420), (350, 300), (580, 420)], fill=(59, 130, 246, 50))
    draw.text((360, 280), "COM AIRBAG (Tempo longo Δt₂)", fill=ACCENT_BLUE)
    draw.text((360, 305), "Força de Impacto REDUZIDA (F₂)", fill=ACCENT_BLUE)
    
    # Painel explicativo
    draw.rectangle([(550, 130), (860, 250)], fill=(23, 27, 36), outline=BORDER_SUBTLE)
    draw.text((565, 140), "TEOREMA DO IMPULSO:", fill=ACCENT_GOLD)
    draw.text((565, 165), "I = F_média · Δt = ΔQ", fill=TEXT_PRIMARY)
    draw.text((565, 190), "Como a variação de momento ΔQ é fixa:", fill=TEXT_SECONDARY)
    draw.text((565, 215), "Maior Tempo (Δt)  =>  Menor Força (F)!", fill=ACCENT_GREEN)
    
    draw.text((220, 485), "Aplicações ENEM: Airbags, para-choques deformáveis, luvas de boxe e calçados com amortecedor!", fill=ACCENT_GOLD)
    return img

# -------------------------------------------------------------
# 5. ESTÁTICA: ALAVANCAS E TORQUE
# -------------------------------------------------------------
def generate_estatica():
    img, draw = create_base_canvas(900, 520, "ESTÁTICA: MOMENTO DE UMA FORÇA (TORQUE) E ALAVANCAS", "Condição de Equilíbrio: Por que a maçaneta fica longe da dobradiça?")
    
    # Porta vista de cima com dobradiça
    # Dobradiça (Ponto Fixo / Eixo de Rotação)
    draw.rectangle([(100, 180), (130, 360)], fill=(100, 116, 139), outline=TEXT_SECONDARY)
    draw.text((80, 150), "Eixo / Dobradiça (Polo)", fill=ACCENT_GOLD)
    
    # Folha da porta
    draw.rectangle([(130, 250), (650, 290)], fill=(27, 32, 43), outline=TEXT_PRIMARY, width=2)
    draw.text((350, 265), "Folha da Porta (Braço da Alavanca)", fill=TEXT_SECONDARY)
    
    # Força 1 (Perto da dobradiça - Errada / Muito esforço)
    draw.line([(260, 290), (260, 390)], fill=ACCENT_RED, width=4)
    draw.text((210, 400), "F₁ = Força Enorme", fill=ACCENT_RED)
    draw.text((210, 420), "(Braço d₁ muito curto)", fill=TEXT_TERTIARY)
    
    # Força 2 (Na maçaneta / Longe da dobradiça - Correta)
    draw.line([(620, 290), (620, 350)], fill=ACCENT_GREEN, width=4)
    draw.text((560, 365), "F₂ = Força Mínima", fill=ACCENT_GREEN)
    draw.text((560, 385), "(Braço d₂ longo)", fill=TEXT_TERTIARY)
    
    # Caixa de fórmulas
    draw.rectangle([(520, 110), (860, 220)], fill=(23, 27, 36), outline=BORDER_SUBTLE)
    draw.text((535, 120), "EQUAÇÃO DO TORQUE (MOMENTO):", fill=ACCENT_GOLD)
    draw.text((535, 145), "Torque = Força · Distância (M = F · d)", fill=TEXT_PRIMARY)
    draw.text((535, 170), "Para o mesmo torque de giro:", fill=TEXT_SECONDARY)
    draw.text((535, 195), "Maior Distância (d)  =>  Menor Força (F)!", fill=ACCENT_GREEN)
    
    draw.text((200, 485), "Tipos de Alavanca no ENEM: Interfixa (tesoura), Inter-resistente (quebra-nozes) e Interpotente (pinça)!", fill=ACCENT_GOLD)
    return img

# -------------------------------------------------------------
# 6. HIDROSTÁTICA: PRENSA HIDRÁULICA E EMPUXO
# -------------------------------------------------------------
def generate_hidrostatica():
    img, draw = create_base_canvas(900, 520, "HIDROSTÁTICA: PRINCÍPIO DE PASCAL E ARQUIMEDES", "Prensa Hidráulica (Multiplicação de Força) e Princípio do Empuxo")
    
    # Tubo em U da Prensa Hidráulica
    # Pistão menor à esquerda
    draw.rectangle([(120, 240), (220, 420)], fill=(27, 32, 43), outline=BORDER_SUBtle if 'BORDER_SUBtle' in globals() else BORDER_SUBTLE)
    draw.rectangle([(120, 240), (220, 270)], fill=ACCENT_BLUE) # Êmbolo 1
    draw.text((135, 215), "Força F₁", fill=ACCENT_RED)
    draw.line([(170, 190), (170, 235)], fill=ACCENT_RED, width=3) # Seta F1
    draw.text((135, 280), "Área A₁", fill=TEXT_PRIMARY)
    
    # Conexão horizontal
    draw.rectangle([(120, 360), (480, 420)], fill=(59, 130, 246, 60))
    
    # Pistão maior à direita
    draw.rectangle([(320, 180), (480, 420)], fill=(27, 32, 43), outline=BORDER_SUBTLE)
    draw.rectangle([(320, 240), (480, 270)], fill=ACCENT_BLUE) # Êmbolo 2
    draw.text((370, 215), "Força F₂ (Elevada)", fill=ACCENT_GREEN)
    draw.line([(400, 235), (400, 180)], fill=ACCENT_GREEN, width=3) # Seta F2
    draw.text((370, 280), "Área A₂ (Maior)", fill=TEXT_PRIMARY)
    
    # Líquido nos tubos
    draw.rectangle([(120, 270), (220, 420)], fill=(14, 165, 233, 40))
    draw.rectangle([(320, 270), (480, 420)], fill=(14, 165, 233, 40))
    
    # Painel do Princípio de Pascal
    draw.rectangle([(520, 110), (860, 240)], fill=(23, 27, 36), outline=BORDER_SUBTLE)
    draw.text((535, 120), "PRINCÍPIO DE PASCAL (PRENSA):", fill=ACCENT_GOLD)
    draw.text((535, 145), "Pressão P₁ = Pressão P₂", fill=TEXT_PRIMARY)
    draw.text((535, 170), "F₁ / A₁ = F₂ / A₂", fill=ACCENT_BLUE)
    draw.text((535, 195), "Se A₂ é 10x maior que A₁,", fill=TEXT_SECONDARY)
    draw.text((535, 215), "a Força F₂ é multiplicada por 10!", fill=ACCENT_GREEN)
    
    # Painel do Empuxo (Arquimedes)
    draw.rectangle([(520, 260), (860, 420)], fill=(23, 27, 36), outline=BORDER_SUBTLE)
    draw.text((535, 275), "PRINCÍPIO DE ARQUIMEDES (EMPUXO):", fill=ACCENT_GOLD)
    draw.text((535, 305), "E = d_líquido · V_deslocado · g", fill=ACCENT_BLUE)
    draw.text((535, 335), "• Se Peso > Empuxo: O corpo AFUNDA", fill=ACCENT_RED)
    draw.text((535, 360), "• Se Peso = Empuxo: O corpo FLUTUA", fill=ACCENT_GREEN)
    draw.text((535, 385), "• Peso Aparente = Peso Real - Empuxo", fill=TEXT_PRIMARY)
    
    draw.text((220, 485), "Por que navios de aço flutuam? Porque o grande volume submerso gera empuxo igual ao peso!", fill=ACCENT_GOLD)
    return img

# -------------------------------------------------------------
# 7. GRAVITAÇÃO: LEIS DE KEPLER E NEWTON
# -------------------------------------------------------------
def generate_gravitacao():
    img, draw = create_base_canvas(900, 520, "GRAVITAÇÃO UNIVERSAL E LEIS DE KEPLER", "Órbitas Elípticas, Áreas Iguais em Tempos Iguais e Lei da Gravitação")
    
    # Órbita Elíptica
    draw.ellipse([100, 180, 800, 420], outline=BORDER_SUBTLE, width=2)
    
    # Foco 1: O Sol
    draw.ellipse([300, 280, 340, 320], fill=ACCENT_GOLD)
    draw.text((305, 330), "SOL (Foco)", fill=ACCENT_GOLD)
    
    # Planeta no Periélio (Mais perto do Sol, Mais Rápido)
    draw.ellipse([100, 290, 120, 310], fill=ACCENT_BLUE)
    draw.text((80, 320), "PERIÉLIO", fill=ACCENT_BLUE)
    draw.text((60, 340), "Velocidade MÁXIMA", fill=ACCENT_GREEN)
    
    # Planeta no Afélio (Mais longe do Sol, Mais Lento)
    draw.ellipse([780, 290, 800, 310], fill=ACCENT_BLUE)
    draw.text((770, 320), "AFÉLIO", fill=ACCENT_BLUE)
    draw.text((750, 340), "Velocidade MÍNIMA", fill=ACCENT_RED)
    
    # Painel explicativo das 3 Leis de Kepler + Lei de Newton
    draw.rectangle([(420, 100), (860, 250)], fill=(23, 27, 36), outline=BORDER_SUBTLE)
    draw.text((435, 110), "RESUMO DAS LEIS DA GRAVITAÇÃO:", fill=ACCENT_GOLD)
    draw.text((435, 135), "1ª Lei (Órbitas): Os planetas giram em elipses com o Sol em um foco.", fill=TEXT_PRIMARY)
    draw.text((435, 160), "2ª Lei (Áreas): Áreas iguais em tempos iguais (mais rápido perto do Sol).", fill=TEXT_PRIMARY)
    draw.text((435, 185), "3ª Lei (Períodos): T² / R³ = constante (quanto mais longe, maior o ano).", fill=TEXT_PRIMARY)
    draw.text((435, 210), "Lei de Newton: F = G · (M · m) / d² (inverso do quadrado da distância)", fill=ACCENT_BLUE)
    
    draw.text((240, 485), "Dica ENEM: Se a distância entre a Terra e a Lua dobrar, a força gravitacional cai 4 vezes!", fill=ACCENT_GOLD)
    return img

def main():
    generators = {
        "cinematica.png": generate_cinematica,
        "dinamica.png": generate_dinamica,
        "energia.png": generate_energia,
        "colisoes.png": generate_colisoes,
        "estatica.png": generate_estatica,
        "hidrostatica.png": generate_hidrostatica,
        "gravitacao.png": generate_gravitacao
    }
    
    # Pastas de destino
    android_dir = r"c:\Users\phcco\OneDrive\Área de Trabalho\knowverse\app\src\main\assets\images\fisica"
    web_dir = r"c:\Users\phcco\OneDrive\Área de Trabalho\knowverse-web\public\assets\images\fisica"
    
    os.makedirs(android_dir, exist_ok=True)
    os.makedirs(web_dir, exist_ok=True)
    
    for filename, gen_fn in generators.items():
        img = gen_fn()
        android_path = os.path.join(android_dir, filename)
        web_path = os.path.join(web_dir, filename)
        img.save(android_path, "PNG")
        img.save(web_path, "PNG")
        print(f"[OK] Gerada imagem em português: {filename}")

if __name__ == "__main__":
    main()
