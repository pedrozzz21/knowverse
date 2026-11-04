package com.example.data.math

import com.example.data.math.didactic.calculus.CalculusLimitsPart1
import com.example.data.math.didactic.calculus.CalculusLimitsPart2

/**
 * Provedor Didático do Módulo 1 de Cálculo I: Limites e Continuidade (Aulas 1 a 10).
 * Delega o conteúdo estruturado em 12 seções para as partes modulares:
 * - [CalculusLimitsPart1]: Aulas 1 a 5 (Intuição, Laterais, Propriedades, 0/0, Fatoração/Racionalização)
 * - [CalculusLimitsPart2]: Aulas 6 a 10 (Continuidade/TVI, Infinitos, No Infinito, Confronto, Secante à Tangente)
 */
object CalculusLimitsDidactic {

    fun get(lessonId: String): CalculusDidacticBundle? {
        return CalculusLimitsPart1.get(lessonId)
            ?: CalculusLimitsPart2.get(lessonId)
    }
}
