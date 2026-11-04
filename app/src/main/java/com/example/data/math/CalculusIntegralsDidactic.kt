package com.example.data.math

import com.example.data.math.didactic.calculus.CalculusIntegralsPart1
import com.example.data.math.didactic.calculus.CalculusIntegralsPart2

/**
 * Provedor Didático do Módulo 3 de Cálculo I: Integrais e o Teorema Fundamental do Cálculo (Aulas 19 a 26).
 * Delega o conteúdo estruturado em 12 seções para as partes modulares:
 * - [CalculusIntegralsPart1]: Aulas 19 a 22 (Conceito de Primitiva, Regra da Potência, Transcendentais, Linearidade)
 * - [CalculusIntegralsPart2]: Aulas 23 a 26 (Substituição, Integração por Partes, Frações Parciais/Subst. Trigonométrica, TFC e Áreas)
 */
object CalculusIntegralsDidactic {

    fun get(lessonId: String): CalculusDidacticBundle? {
        return CalculusIntegralsPart1.get(lessonId)
            ?: CalculusIntegralsPart2.get(lessonId)
    }
}
