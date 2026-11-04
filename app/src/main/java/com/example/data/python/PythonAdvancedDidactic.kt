package com.example.data.python

import com.example.data.python.didactic.PythonAdvancedPart1
import com.example.data.python.didactic.PythonAdvancedPart2

/**
 * Provedor Didático do Nível Avançado de Python (Aulas py_a1_l1 até py_a2_l3).
 * Delega o conteúdo estruturado em 12 seções para as partes modulares:
 * - [PythonAdvancedPart1]: Aulas py_a1_l1 e py_a1_l2 (POO Classes/Construtor/Métodos, Herança e Polimorfismo com super())
 * - [PythonAdvancedPart2]: Aulas py_a2_l1 a py_a2_l3 (Geradores com yield, Expressões Regulares re, Estrutura de Dados Pilha Stack)
 */
object PythonAdvancedDidactic {

    fun get(lessonId: String): PythonDidacticBundle? {
        return PythonAdvancedPart1.get(lessonId)
            ?: PythonAdvancedPart2.get(lessonId)
    }
}
