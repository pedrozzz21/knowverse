package com.example.data.python

import com.example.data.python.didactic.PythonBasicPart1
import com.example.data.python.didactic.PythonBasicPart2

/**
 * Provedor Didático do Nível Básico de Python (Aulas py_b1_l1 até py_b3_l2).
 * Delega o conteúdo estruturado em 12 seções para as partes modulares:
 * - [PythonBasicPart1]: Aulas py_b1_l1 a py_b1_l3 (print(), Variáveis/Tipagem Dinâmica, Type Casting)
 * - [PythonBasicPart2]: Aulas py_b2_l1 a py_b3_l2 (Operadores Aritméticos, if/elif/else, for/range(), Dicionários)
 */
object PythonBasicDidactic {

    fun get(lessonId: String): PythonDidacticBundle? {
        return PythonBasicPart1.get(lessonId)
            ?: PythonBasicPart2.get(lessonId)
    }
}
