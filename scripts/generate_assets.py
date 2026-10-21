import os
import subprocess
import math

FONT_BOLD = "/usr/share/fonts/truetype/dejavu/DejaVuSans-Bold.ttf"
FONT_REG = "/usr/share/fonts/truetype/dejavu/DejaVuSans.ttf"
OUT_DIR = "app/src/main/res/drawable"
os.makedirs(OUT_DIR, exist_ok=True)

def generate_grafico_limites_laterais():
    out_path = os.path.join(OUT_DIR, "grafico_limites_laterais.png")
    # Coordinates: W=960, H=600. Margins: left=70, right=40, top=70, bottom=60
    # X range: [0, 4], Y range: [0, 4.5]
    w, h = 960, 600
    ox, oy = 80, 530
    plot_w = 840
    plot_h = 450
    
    def to_canvas(x, y):
        cx = ox + (x / 4.0) * plot_w
        cy = oy - (y / 4.5) * plot_h
        return cx, cy

    cmds = [
        "convert", "-size", f"{w}x{h}", "xc:#ffffff",
        "-stroke", "#cbd5e1", "-strokewidth", "1",
        # Grid lines
    ]
    
    # Vertical grid
    for x_val in [0.5, 1.0, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0]:
        cx, _ = to_canvas(x_val, 0)
        cmds.extend(["-draw", f"stroke-dasharray 3 3 line {cx},{oy} {cx},{oy-plot_h}"])
        
    # Horizontal grid
    for y_val in [0.5, 1.0, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0, 4.5]:
        _, cy = to_canvas(0, y_val)
        cmds.extend(["-draw", f"stroke-dasharray 3 3 line {ox},{cy} {ox+plot_w},{cy}"])

    # Border & Axes
    cmds.extend([
        "-stroke", "#94a3b8", "-strokewidth", "2",
        "-draw", f"stroke-dasharray 1 0 line {ox},{oy} {ox+plot_w},{oy}",
        "-draw", f"stroke-dasharray 1 0 line {ox},{oy} {ox},{oy-plot_h}",
        "-draw", f"stroke-dasharray 1 0 line {ox+plot_w},{oy} {ox+plot_w},{oy-plot_h}",
        "-draw", f"stroke-dasharray 1 0 line {ox},{oy-plot_h} {ox+plot_w},{oy-plot_h}"
    ])

    # Vertical asymptote / split line at x = 2
    cx2, _ = to_canvas(2.0, 0)
    cmds.extend([
        "-stroke", "#94a3b8", "-strokewidth", "2",
        "-draw", f"stroke-dasharray 6 6 line {cx2},{oy} {cx2},{oy-plot_h}"
    ])

    # Left curve (blue): y = 1.0 + x
    left_points = []
    for step in range(0, 101):
        x = (step / 100.0) * 2.0
        y = 1.0 + x
        cx, cy = to_canvas(x, y)
        left_points.append(f"{cx:.1f},{cy:.1f}")

    cmds.extend([
        "-stroke", "#0284c7", "-strokewidth", "5",
        "-draw", f"stroke-dasharray 1 0 polyline {' '.join(left_points)}"
    ])

    # Right curve (green): y = 1.0 + (x - 2.0)**2.2
    right_points = []
    for step in range(0, 101):
        x = 2.0 + (step / 100.0) * 1.88
        y = 1.0 + (x - 2.0)**2.1
        cx, cy = to_canvas(x, y)
        right_points.append(f"{cx:.1f},{cy:.1f}")

    cmds.extend([
        "-stroke", "#16a34a", "-strokewidth", "5",
        "-draw", f"stroke-dasharray 1 0 polyline {' '.join(right_points)}"
    ])

    # Open circle at (2, 3) for left curve
    cx_hole, cy_hole = to_canvas(2.0, 3.0)
    cmds.extend([
        "-stroke", "#0284c7", "-strokewidth", "4", "-fill", "#ffffff",
        "-draw", f"circle {cx_hole},{cy_hole} {cx_hole+9},{cy_hole}"
    ])

    # Closed circle at (2, 1) for right curve
    cx_solid, cy_solid = to_canvas(2.0, 1.0)
    cmds.extend([
        "-stroke", "#16a34a", "-strokewidth", "2", "-fill", "#16a34a",
        "-draw", f"circle {cx_solid},{cy_solid} {cx_solid+9},{cy_solid}"
    ])

    # Text & Annotations
    cmds.extend([
        "-stroke", "none", "-fill", "#0f172a", "-font", FONT_BOLD, "-pointsize", "22",
        "-annotate", "+140+45", "Limites Laterais Diferentes => O Limite Global NAO Existe!",
        "-fill", "#0284c7", "-pointsize", "18",
        "-annotate", "+130+190", "x -> 2- (Esquerda)\nf(x) -> 3",
        "-fill", "#16a34a", "-pointsize", "18",
        "-annotate", "+630+330", "x -> 2+ (Direita)\nf(x) -> 1",
        # Labels x and y
        "-fill", "#334155", "-font", FONT_REG, "-pointsize", "16",
        "-annotate", f"+{ox+plot_w//2}+{oy+35}", "x",
        "-annotate", f"+25+{oy-plot_h//2}", "f(x)"
    ])

    # Arrows
    cmds.extend([
        "-stroke", "#0284c7", "-strokewidth", "3",
        "-draw", f"line 280,210 {cx_hole-15},{cy_hole-8}",
        "-stroke", "#16a34a", "-strokewidth", "3",
        "-draw", f"line 620,340 {cx_solid+15},{cy_solid+10}"
    ])

    cmds.append(out_path)
    subprocess.run(cmds, check=True)
    print("Generated:", out_path)

def generate_grafico_furo_indeterminacao():
    out_path = os.path.join(OUT_DIR, "grafico_furo_indeterminacao.png")
    w, h = 960, 600
    ox, oy = 80, 530
    plot_w = 840
    plot_h = 450

    def to_canvas(x, y):
        cx = ox + (x / 4.0) * plot_w
        cy = oy - ((y - 1.0) / 5.0) * plot_h
        return cx, cy

    cmds = [
        "convert", "-size", f"{w}x{h}", "xc:#ffffff",
        "-stroke", "#cbd5e1", "-strokewidth", "1",
    ]

    # Grid
    for x_val in [0.5, 1.0, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0]:
        cx, _ = to_canvas(x_val, 1.0)
        cmds.extend(["-draw", f"stroke-dasharray 3 3 line {cx},{oy} {cx},{oy-plot_h}"])
    for y_val in [1.0, 2.0, 3.0, 4.0, 5.0, 6.0]:
        _, cy = to_canvas(0, y_val)
        cmds.extend(["-draw", f"stroke-dasharray 3 3 line {ox},{cy} {ox+plot_w},{cy}"])

    # Border
    cmds.extend([
        "-stroke", "#94a3b8", "-strokewidth", "2",
        "-draw", f"stroke-dasharray 1 0 line {ox},{oy} {ox+plot_w},{oy}",
        "-draw", f"stroke-dasharray 1 0 line {ox},{oy} {ox},{oy-plot_h}",
        "-draw", f"stroke-dasharray 1 0 line {ox+plot_w},{oy} {ox+plot_w},{oy-plot_h}",
        "-draw", f"stroke-dasharray 1 0 line {ox},{oy-plot_h} {ox+plot_w},{oy-plot_h}"
    ])

    # Dashed lines at x=2 and y=4
    cx2, _ = to_canvas(2.0, 1.0)
    _, cy4 = to_canvas(0, 4.0)
    cmds.extend([
        "-stroke", "#94a3b8", "-strokewidth", "2",
        "-draw", f"stroke-dasharray 5 5 line {cx2},{oy} {cx2},{oy-plot_h}",
        "-draw", f"stroke-dasharray 5 5 line {ox},{cy4} {ox+plot_w},{cy4}"
    ])

    # Continuous red line: y = x + 2
    cx_start, cy_start = to_canvas(0.0, 2.0)
    cx_end, cy_end = to_canvas(4.0, 6.0)
    cmds.extend([
        "-stroke", "#dc2626", "-strokewidth", "5",
        "-draw", f"stroke-dasharray 1 0 line {cx_start},{cy_start} {cx_end},{cy_end}"
    ])

    # Hole at (2, 4)
    cx_h, cy_h = to_canvas(2.0, 4.0)
    cmds.extend([
        "-stroke", "#dc2626", "-strokewidth", "5", "-fill", "#ffffff",
        "-draw", f"circle {cx_h},{cy_h} {cx_h+12},{cy_h}"
    ])

    # Callout Box
    cmds.extend([
        "-stroke", "#ef4444", "-strokewidth", "2", "-fill", "#fef2f2",
        "-draw", "roundrectangle 140,160 410,250 10,10",
        "-stroke", "none", "-fill", "#b91c1c", "-font", FONT_BOLD, "-pointsize", "16",
        "-annotate", "+155+190", "Furo em (2, 4)",
        "-annotate", "+155+215", "f(2) nao existe,",
        "-annotate", "+155+240", "mas lim_{x->2} f(x) = 4 !",
        # Arrow from box to hole
        "-stroke", "#dc2626", "-strokewidth", "3",
        "-draw", f"line 410,225 {cx_h-18},{cy_h-4}",
        # Title & Axis labels
        "-stroke", "none", "-fill", "#0f172a", "-font", FONT_BOLD, "-pointsize", "22",
        "-annotate", "+90+45", "Indeterminacao 0/0: O limite existe mesmo onde a funcao 'falha'!",
        "-fill", "#334155", "-font", FONT_REG, "-pointsize", "16",
        "-annotate", f"+{ox+plot_w//2}+{oy+35}", "x",
        "-annotate", f"+25+{oy-plot_h//2}", "y"
    ])

    cmds.append(out_path)
    subprocess.run(cmds, check=True)
    print("Generated:", out_path)

def generate_grafico_assintotas():
    out_path = os.path.join(OUT_DIR, "grafico_assintotas.png")
    w, h = 960, 600
    ox, oy = 80, 530
    plot_w = 840
    plot_h = 450

    # x in [-5, 7], y in [-6, 8]
    def to_canvas(x, y):
        cx = ox + ((x - (-5.0)) / 12.0) * plot_w
        cy = oy - ((y - (-6.0)) / 14.0) * plot_h
        return cx, cy

    cmds = [
        "convert", "-size", f"{w}x{h}", "xc:#ffffff",
        "-stroke", "#cbd5e1", "-strokewidth", "1",
    ]

    # Grid
    for x_val in range(-4, 7, 2):
        cx, _ = to_canvas(x_val, -6.0)
        cmds.extend(["-draw", f"stroke-dasharray 3 3 line {cx},{oy} {cx},{oy-plot_h}"])
    for y_val in range(-4, 9, 2):
        _, cy = to_canvas(-5.0, y_val)
        cmds.extend(["-draw", f"stroke-dasharray 3 3 line {ox},{cy} {ox+plot_w},{cy}"])

    # Border
    cmds.extend([
        "-stroke", "#94a3b8", "-strokewidth", "2",
        "-draw", f"stroke-dasharray 1 0 line {ox},{oy} {ox+plot_w},{oy}",
        "-draw", f"stroke-dasharray 1 0 line {ox},{oy} {ox},{oy-plot_h}",
        "-draw", f"stroke-dasharray 1 0 line {ox+plot_w},{oy} {ox+plot_w},{oy-plot_h}",
        "-draw", f"stroke-dasharray 1 0 line {ox},{oy-plot_h} {ox+plot_w},{oy-plot_h}"
    ])

    # Vertical Asymptote at x = 1 (Orange dashed)
    cx1, _ = to_canvas(1.0, 0)
    cmds.extend([
        "-stroke", "#ea580c", "-strokewidth", "3",
        "-draw", f"stroke-dasharray 8 6 line {cx1},{oy} {cx1},{oy-plot_h}"
    ])

    # Horizontal Asymptote at y = 2 (Cyan dashed)
    _, cy2 = to_canvas(0, 2.0)
    cmds.extend([
        "-stroke", "#06b6d4", "-strokewidth", "3",
        "-draw", f"stroke-dasharray 8 6 line {ox},{cy2} {ox+plot_w},{cy2}"
    ])

    # Left branch: f(x) = (2x+1)/(x-1) for x in [-5, 0.85]
    left_pts = []
    steps = 100
    for s in range(steps + 1):
        x = -5.0 + (s / float(steps)) * 5.85
        y = (2.0 * x + 1.0) / (x - 1.0)
        cx, cy = to_canvas(x, max(-6.0, min(8.0, y)))
        left_pts.append(f"{cx:.1f},{cy:.1f}")

    cmds.extend([
        "-stroke", "#9333ea", "-strokewidth", "4",
        "-draw", f"stroke-dasharray 1 0 polyline {' '.join(left_pts)}"
    ])

    # Right branch: f(x) = (2x+1)/(x-1) for x in [1.15, 7.0]
    right_pts = []
    for s in range(steps + 1):
        x = 1.15 + (s / float(steps)) * 5.85
        y = (2.0 * x + 1.0) / (x - 1.0)
        cx, cy = to_canvas(x, max(-6.0, min(8.0, y)))
        right_pts.append(f"{cx:.1f},{cy:.1f}")

    cmds.extend([
        "-stroke", "#9333ea", "-strokewidth", "4",
        "-draw", f"stroke-dasharray 1 0 polyline {' '.join(right_pts)}"
    ])

    # Legend in top right
    cmds.extend([
        "-stroke", "#cbd5e1", "-strokewidth", "1", "-fill", "#f8fafc",
        "-draw", "roundrectangle 600,60 930,170 8,8",
        # Purple legend item
        "-stroke", "#9333ea", "-strokewidth", "4",
        "-draw", "line 620,85 660,85",
        "-stroke", "none", "-fill", "#1e293b", "-font", FONT_BOLD, "-pointsize", "14",
        "-annotate", "+670+90", "f(x) = (2x + 1)/(x - 1)",
        # Orange legend item
        "-stroke", "#ea580c", "-strokewidth", "3",
        "-draw", "stroke-dasharray 6 4 line 620,115 660,115",
        "-stroke", "none", "-fill", "#1e293b", "-font", FONT_REG, "-pointsize", "14",
        "-annotate", "+670+120", "Assintota Vertical (x = 1)",
        # Cyan legend item
        "-stroke", "#06b6d4", "-strokewidth", "3",
        "-draw", "stroke-dasharray 6 4 line 620,145 660,145",
        "-stroke", "none", "-fill", "#1e293b", "-font", FONT_REG, "-pointsize", "14",
        "-annotate", "+670+150", "Assintota Horizontal (y = 2)",
        # Title
        "-stroke", "none", "-fill", "#0f172a", "-font", FONT_BOLD, "-pointsize", "22",
        "-annotate", "+80+40", "Limites Infinitos e no Infinito: As Barreiras do Grafico (Assintotas)",
        "-fill", "#334155", "-font", FONT_REG, "-pointsize", "16",
        "-annotate", f"+{ox+plot_w//2}+{oy+35}", "x",
        "-annotate", f"+25+{oy-plot_h//2}", "f(x)"
    ])

    cmds.append(out_path)
    subprocess.run(cmds, check=True)
    print("Generated:", out_path)

def generate_sealion_mascot():
    out_path = os.path.join(OUT_DIR, "img_mascot_sealion.png")
    # 512x512 clean graphic of sea lion with glasses, smile and tablet
    w, h = 512, 512
    cmds = [
        "convert", "-size", f"{w}x{h}", "xc:#0f172a",
        # Decorative glow circle background
        "-stroke", "none", "-fill", "#1e293b",
        "-draw", "circle 256,256 256,460",
        "-fill", "#334155",
        "-draw", "circle 256,256 256,410",
        # Sea lion body (sleek navy/slate rounded form)
        "-fill", "#475569",
        "-draw", "ellipse 256,360 140,110 0,360",
        # Belly patch (light creamy slate)
        "-fill", "#94a3b8",
        "-draw", "ellipse 256,375 80,75 0,360",
        # Left flipper
        "-fill", "#334155",
        "-draw", "ellipse 140,380 45,25 30,390",
        # Right flipper holding tablet
        "-draw", "ellipse 370,380 45,25 150,510",
        # Head (friendly round shape)
        "-fill", "#64748b",
        "-draw", "circle 256,210 256,315",
        # Snout / muzzle (chubby cream shape)
        "-fill", "#cbd5e1",
        "-draw", "ellipse 256,245 55,40 0,360",
        # Whiskers
        "-stroke", "#334155", "-strokewidth", "3",
        "-draw", "line 200,240 140,230",
        "-draw", "line 200,250 135,255",
        "-draw", "line 312,240 372,230",
        "-draw", "line 312,250 377,255",
        # Cute black button nose
        "-stroke", "none", "-fill", "#0f172a",
        "-draw", "ellipse 256,228 16,11 0,360",
        # Cute smiling mouth
        "-stroke", "#0f172a", "-strokewidth", "3", "-fill", "none",
        "-draw", "arc 238,238 256,260 0,180",
        "-draw", "arc 256,238 274,260 0,180",
        # Blushing pink cheeks
        "-stroke", "none", "-fill", "#f43f5e",
        "-draw", "circle 195,245 195,255",
        "-draw", "circle 317,245 317,255",
        # Big friendly eyes
        "-fill", "#0f172a",
        "-draw", "circle 215,180 215,200",
        "-draw", "circle 297,180 297,200",
        # Eye shine highlights
        "-fill", "#ffffff",
        "-draw", "circle 210,175 210,181",
        "-draw", "circle 292,175 292,181",
        "-draw", "circle 220,186 220,189",
        "-draw", "circle 302,186 302,189",
        # Nerd glasses frame (cyan glowing tech glasses)
        "-stroke", "#38bdf8", "-strokewidth", "5", "-fill", "none",
        "-draw", "roundrectangle 185,155 245,205 10,10",
        "-draw", "roundrectangle 267,155 327,205 10,10",
        "-draw", "line 245,180 267,180",
        # Glasses temples
        "-draw", "line 185,175 160,180",
        "-draw", "line 327,175 352,180",
        # Cute glowing tablet in hands
        "-stroke", "#6366f1", "-strokewidth", "3", "-fill", "#1e1b4b",
        "-draw", "roundrectangle 210,380 302,460 8,8",
        # Tablet screen glow
        "-stroke", "none", "-fill", "#818cf8",
        "-draw", "roundrectangle 218,390 294,450 4,4",
        # Code symbol < / > on tablet
        "-stroke", "#ffffff", "-strokewidth", "3", "-fill", "none",
        "-draw", "polyline 235,412 225,420 235,428",
        "-draw", "polyline 277,412 287,420 277,428",
        "-draw", "line 262,408 250,432",
        # Cute small ears
        "-stroke", "none", "-fill", "#475569",
        "-draw", "ellipse 170,145 15,20 130,490",
        "-draw", "ellipse 342,145 15,20 50,410"
    ]
    cmds.append(out_path)
    subprocess.run(cmds, check=True)
    print("Generated mascot:", out_path)

if __name__ == "__main__":
    generate_grafico_limites_laterais()
    generate_grafico_furo_indeterminacao()
    generate_grafico_assintotas()
    generate_sealion_mascot()
