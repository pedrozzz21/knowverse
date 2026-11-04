package com.example.data.math

import com.example.data.math.didactic.calculus.CalculusDerivativesPart1
import com.example.data.math.didactic.calculus.CalculusDerivativesPart2

/**
 * Provedor Didático do Módulo 2 de Cálculo I: Derivadas e Aplicações (Aulas 11 a 18).
 * Delega o conteúdo estruturado em 12 seções para as partes modulares:
 * - [CalculusDerivativesPart1]: Aulas 11 a 14 (Conceito, Regras Básicas, Produto/Quociente/Cadeia, Implícita/Logarítmica)
 * - [CalculusDerivativesPart2]: Aulas 15 a 18 (Linearização/Diferenciais, Estudo de Funções/Gráficos, L'Hôpital, Otimização)
 */
object CalculusDerivativesDidactic {

    fun get(lessonId: String): CalculusDidacticBundle? {
        return CalculusDerivativesPart1.get(lessonId)
            ?: CalculusDerivativesPart2.get(lessonId)
    }
}
