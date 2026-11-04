package com.example.data.python

import com.example.data.python.didactic.PythonIntermediatePart1
import com.example.data.python.didactic.PythonIntermediatePart2

/**
 * Provedor Didático do Nível Intermediário de Python (Aulas py_i1_l1 até py_i3_l1).
 * Delega o conteúdo estruturado em 12 seções para as partes modulares:
 * - [PythonIntermediatePart1]: Aulas py_i1_l1 a py_i2_l1 (F-Strings, Cifra de César, List Comprehension)
 * - [PythonIntermediatePart2]: Aulas py_i2_l2 a py_i3_l1 (Exceções try/except, Busca Binária O(log n))
 */
object PythonIntermediateDidactic {

    fun get(lessonId: String): PythonDidacticBundle? {
        return PythonIntermediatePart1.get(lessonId)
            ?: PythonIntermediatePart2.get(lessonId)
    }
}
