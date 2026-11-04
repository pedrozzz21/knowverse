package com.example.data.math

import com.example.data.math.didactic.BasicMathDidacticPart1
import com.example.data.math.didactic.BasicMathDidacticPart2
import com.example.data.math.didactic.BasicMathDidacticPart3

/**
 * Provedor Didático Oficial do Ciclo de Matemática Básica do Knowverse.
 * Delega o carregamento para os submódulos especializados estruturados
 * com os 12 pilares pedagógicos de excelência:
 * - Parte 1: Aulas 1 a 4 (Conjuntos, Frações, Divisibilidade, Potências e Notação Científica)
 * - Parte 2: Aulas 5 a 8 (Unidades, Proporções, Regra de Três e Escalas)
 * - Parte 3: Aulas 9 a 12 (Porcentagem, Álgebra/Equações, Estatística e Geometria Essencial)
 */
object BasicMathDidactic {

    fun get(lessonId: String): CalculusDidacticBundle? {
        return BasicMathDidacticPart1.get(lessonId)
            ?: BasicMathDidacticPart2.get(lessonId)
            ?: BasicMathDidacticPart3.get(lessonId)
    }
}
