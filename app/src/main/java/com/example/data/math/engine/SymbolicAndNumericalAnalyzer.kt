package com.example.data.math.engine

import com.example.ui.components.BinaryOperator
import com.example.ui.components.CompiledMathExpression
import com.example.ui.components.CriticalPointType
import com.example.ui.components.MathAstNode
import com.example.ui.components.MathCriticalPoint
import com.example.ui.components.MathExpressionEvaluator
import com.example.ui.components.MathFunction1
import com.example.ui.components.MathFunction2
import com.example.ui.components.RiemannType
import java.util.Locale
import kotlin.math.*

/**
 * Métodos numéricos suportados para localização de raízes reais em intervalos definidos.
 */
enum class RootFindingMethod(val label: String) {
    BISECTION("Bisseção (Bolzano)"),
    NEWTON_RAPHSON("Newton-Raphson"),
    SECANT("Método da Secante"),
    HYBRID_SAFE("Híbrido (Bisseção + Secante/Newton)")
}

/**
 * Resultado detalhado de uma raiz encontrada, diferenciando solução analítica exata de aproximação numérica.
 */
data class DetailedRootResult(
    val root: Double,
    val intervalInvestigated: Pair<Double, Double>,
    val functionValueAtRoot: Double,
    val estimatedError: Double,
    val iterations: Int,
    val methodUsed: RootFindingMethod,
    val converged: Boolean,
    val isAnalyticalExact: Boolean,
    val exactLabel: String? = null
)

/**
 * Etapa pedagógica de derivação ou transformação simbólica.
 */
data class MathResolutionStep(
    val originalExpression: String,
    val ruleApplied: String,
    val resultingExpression: String,
    val graphicalInterpretation: String
)

/**
 * Resultado da derivação de uma função f(x).
 * Prioriza derivação simbólica exata via AST; quando a função contém operadores não diferenciáveis
 * simbolicamente (ex.: floor, ceil, step, condicional), sinaliza claramente o fallback numérico.
 */
data class SymbolicDerivativeResult(
    val originalExpression: String,
    val derivativeExpression: String,
    val compiledDerivative: CompiledMathExpression?,
    val isSymbolicExact: Boolean,
    val fallbackReason: String? = null,
    val steps: List<MathResolutionStep> = emptyList()
) {
    fun evaluateAt(x: Double): Double {
        if (isSymbolicExact && compiledDerivative != null && compiledDerivative.isValid) {
            return compiledDerivative.evaluate(x)
        }
        return MathExpressionEvaluator.derivative(originalExpression, x)
    }
}

/**
 * Intervalo de monotonicidade (crescimento ou decrescimento) de f(x).
 */
data class MonotonicityInterval(
    val xStart: Double,
    val xEnd: Double,
    val isIncreasing: Boolean,
    val sampleDerivative: Double
)

/**
 * Classificação de assíntotas (vertical, horizontal ou oblíqua).
 */
enum class AsymptoteKind(val label: String) {
    VERTICAL("Assíntota Vertical"),
    HORIZONTAL("Assíntota Horizontal"),
    OBLIQUE("Assíntota Oblíqua")
}

data class DetectedAsymptote(
    val kind: AsymptoteKind,
    val equation: String,
    val xPosition: Double? = null,
    val slope: Double? = null,
    val intercept: Double? = null
)

/**
 * Descontinuidade detectada no domínio investigado.
 */
enum class DiscontinuityKind(val label: String) {
    REMOVABLE("Descontinuidade Removível (Buraco)"),
    JUMP("Descontinuidade de Salto"),
    INFINITE_POLE("Descontinuidade Infinita (Pólo)")
}

data class DetectedDiscontinuity(
    val x: Double,
    val leftLimit: Double,
    val rightLimit: Double,
    val kind: DiscontinuityKind
)

/**
 * Relatório completo de integração definida e aproximações numéricas.
 */
data class IntegrationComparisonReport(
    val expression: String,
    val lowerBound: Double,
    val upperBound: Double,
    val subdivisions: Int,
    val signedAreaSimpson: Double,
    val geometricAreaSimpson: Double,
    val simpsonErrorEstimate: Double,
    val trapezoidalValue: Double,
    val trapezoidalErrorEstimate: Double,
    val riemannLeft: Double,
    val riemannRight: Double,
    val riemannMidpoint: Double,
    val symbolicAntiderivative: String?,
    val isSymbolicSupported: Boolean
)

/**
 * Analisador Matemático Simbólico e Numérico (Subetapas 3.1 e 3.3).
 * Opera diretamente sobre a AST de [MathExpressionEvaluator], mantendo separação limpa
 * entre parsing, avaliação, análise matemática e interface.
 */
object SymbolicAndNumericalAnalyzer {

    // =========================================================================
    // 1. DERIVAÇÃO SIMBÓLICA POR AST COM SIMPLIFICAÇÃO ALGÉBRICA E PASSOS
    // =========================================================================

    fun differentiate(expression: String): SymbolicDerivativeResult {
        val compiled = MathExpressionEvaluator.compile(expression)
        val root = compiled.rootNode
        if (!compiled.isValid || root == null) {
            return SymbolicDerivativeResult(
                originalExpression = expression,
                derivativeExpression = "Indefinida",
                compiledDerivative = null,
                isSymbolicExact = false,
                fallbackReason = "Expressão original inválida."
            )
        }

        val steps = mutableListOf<MathResolutionStep>()
        return try {
            val rawDerivativeAst = diffNode(root, steps)
            val simplifiedAst = simplifyAst(rawDerivativeAst)
            val formatted = formatAst(simplifiedAst)
            val compiledDeriv = MathExpressionEvaluator.compile(formatted)
            steps.add(
                MathResolutionStep(
                    originalExpression = formatAst(root),
                    ruleApplied = "Simplificação Algébrica da Derivada",
                    resultingExpression = "f'(x) = $formatted",
                    graphicalInterpretation = "A função derivada f'(x) fornece a inclinação da reta tangente ao gráfico de f(x) em cada abscissa x."
                )
            )
            SymbolicDerivativeResult(
                originalExpression = expression,
                derivativeExpression = formatted,
                compiledDerivative = compiledDeriv,
                isSymbolicExact = true,
                steps = steps
            )
        } catch (e: UnsupportedSymbolicOperationException) {
            SymbolicDerivativeResult(
                originalExpression = expression,
                derivativeExpression = "Aproximação Numérica O(h⁴)",
                compiledDerivative = null,
                isSymbolicExact = false,
                fallbackReason = e.message,
                steps = listOf(
                    MathResolutionStep(
                        originalExpression = expression,
                        ruleApplied = "Diferenças Finitas Centradas de 5 Pontos O(h⁴)",
                        resultingExpression = "f'(x) ≈ (-f(x+2h) + 8f(x+h) - 8f(x-h) + f(x-2h)) / (12h)",
                        graphicalInterpretation = "Como a expressão contém ${e.message}, a taxa de variação instantânea é estimada numericamente na vizinhança de x."
                    )
                )
            )
        }
    }

    private class UnsupportedSymbolicOperationException(message: String) : Exception(message)

    private fun diffNode(node: MathAstNode, steps: MutableList<MathResolutionStep>): MathAstNode {
        if (!containsVariableX(node)) {
            return MathAstNode.NumberLiteral(0.0)
        }
        return when (node) {
            is MathAstNode.NumberLiteral -> MathAstNode.NumberLiteral(0.0)
            is MathAstNode.VariableX -> MathAstNode.NumberLiteral(1.0)
            is MathAstNode.UnaryMinus -> {
                val du = diffNode(node.operand, steps)
                MathAstNode.UnaryMinus(du)
            }
            is MathAstNode.BinaryOp -> {
                val u = node.left
                val v = node.right
                when (node.op) {
                    BinaryOperator.ADD -> {
                        val du = diffNode(u, steps)
                        val dv = diffNode(v, steps)
                        val res = MathAstNode.BinaryOp(BinaryOperator.ADD, du, dv)
                        steps.add(
                            MathResolutionStep(
                                originalExpression = formatAst(node),
                                ruleApplied = "Regra da Soma: (u + v)' = u' + v'",
                                resultingExpression = formatAst(simplifyAst(res)),
                                graphicalInterpretation = "A taxa de variação da soma de dois termos equivale à soma de suas taxas individuais."
                            )
                        )
                        res
                    }
                    BinaryOperator.SUB -> {
                        val du = diffNode(u, steps)
                        val dv = diffNode(v, steps)
                        val res = MathAstNode.BinaryOp(BinaryOperator.SUB, du, dv)
                        steps.add(
                            MathResolutionStep(
                                originalExpression = formatAst(node),
                                ruleApplied = "Regra da Diferença: (u - v)' = u' - v'",
                                resultingExpression = formatAst(simplifyAst(res)),
                                graphicalInterpretation = "A derivada preserva a operação linear de subtração entre funções."
                            )
                        )
                        res
                    }
                    BinaryOperator.MUL -> {
                        val uHasX = containsVariableX(u)
                        val vHasX = containsVariableX(v)
                        if (!uHasX) {
                            val dv = diffNode(v, steps)
                            val res = MathAstNode.BinaryOp(BinaryOperator.MUL, u, dv)
                            steps.add(
                                MathResolutionStep(
                                    originalExpression = formatAst(node),
                                    ruleApplied = "Regra da Multiplicação por Constante: (c · v)' = c · v'",
                                    resultingExpression = formatAst(simplifyAst(res)),
                                    graphicalInterpretation = "O fator constante escala verticalmente a inclinação da curva."
                                )
                            )
                            res
                        } else if (!vHasX) {
                            val du = diffNode(u, steps)
                            MathAstNode.BinaryOp(BinaryOperator.MUL, v, du)
                        } else {
                            val du = diffNode(u, steps)
                            val dv = diffNode(v, steps)
                            val leftTerm = MathAstNode.BinaryOp(BinaryOperator.MUL, du, v)
                            val rightTerm = MathAstNode.BinaryOp(BinaryOperator.MUL, u, dv)
                            val res = MathAstNode.BinaryOp(BinaryOperator.ADD, leftTerm, rightTerm)
                            steps.add(
                                MathResolutionStep(
                                    originalExpression = formatAst(node),
                                    ruleApplied = "Regra do Produto (Leibniz): (u · v)' = u'·v + u·v'",
                                    resultingExpression = formatAst(simplifyAst(res)),
                                    graphicalInterpretation = "Combina o crescimento simultâneo de ambos os fatores dependentes de x."
                                )
                            )
                            res
                        }
                    }
                    BinaryOperator.DIV -> {
                        val du = diffNode(u, steps)
                        val dv = diffNode(v, steps)
                        val numLeft = MathAstNode.BinaryOp(BinaryOperator.MUL, du, v)
                        val numRight = MathAstNode.BinaryOp(BinaryOperator.MUL, u, dv)
                        val numerator = MathAstNode.BinaryOp(BinaryOperator.SUB, numLeft, numRight)
                        val denominator = MathAstNode.BinaryOp(BinaryOperator.POW, v, MathAstNode.NumberLiteral(2.0))
                        val res = MathAstNode.BinaryOp(BinaryOperator.DIV, numerator, denominator)
                        steps.add(
                            MathResolutionStep(
                                originalExpression = formatAst(node),
                                ruleApplied = "Regra do Quociente: (u / v)' = (u'·v - u·v') / v²",
                                resultingExpression = formatAst(simplifyAst(res)),
                                graphicalInterpretation = "Avalia a taxa de variação da razão onde v(x) ≠ 0."
                            )
                        )
                        res
                    }
                    BinaryOperator.POW -> {
                        val uHasX = containsVariableX(u)
                        val vHasX = containsVariableX(v)
                        if (uHasX && !vHasX) {
                            // Regra do Tombo / Potência Generalizada: (u^c)' = c * u^(c-1) * u'
                            val cVal = v.eval(0.0)
                            val du = diffNode(u, steps)
                            val newExp = MathAstNode.NumberLiteral(cVal - 1.0)
                            val uPow = MathAstNode.BinaryOp(BinaryOperator.POW, u, newExp)
                            val coeffTimesPow = MathAstNode.BinaryOp(BinaryOperator.MUL, MathAstNode.NumberLiteral(cVal), uPow)
                            val res = MathAstNode.BinaryOp(BinaryOperator.MUL, coeffTimesPow, du)
                            steps.add(
                                MathResolutionStep(
                                    originalExpression = formatAst(node),
                                    ruleApplied = "Regra da Potência: (u^n)' = n · u^(n-1) · u'",
                                    resultingExpression = formatAst(simplifyAst(res)),
                                    graphicalInterpretation = "Reduz o grau polinomial em uma unidade e aplica a Regra da Cadeia."
                                )
                            )
                            res
                        } else if (!uHasX && vHasX) {
                            // Exponencial de base constante: (a^v)' = a^v * ln(a) * v'
                            val dv = diffNode(v, steps)
                            val lnA = MathAstNode.UnaryFunction(MathFunction1.LN, u)
                            val aPowVTimesLnA = MathAstNode.BinaryOp(BinaryOperator.MUL, node, lnA)
                            val res = MathAstNode.BinaryOp(BinaryOperator.MUL, aPowVTimesLnA, dv)
                            steps.add(
                                MathResolutionStep(
                                    originalExpression = formatAst(node),
                                    ruleApplied = "Regra Exponencial: (a^v)' = a^v · ln(a) · v'",
                                    resultingExpression = formatAst(simplifyAst(res)),
                                    graphicalInterpretation = "A taxa de variação exponencial é proporcional ao próprio valor da função."
                                )
                            )
                            res
                        } else {
                            // Caso geral u(x)^v(x) = u^v * (v' * ln(u) + v * u' / u)
                            val du = diffNode(u, steps)
                            val dv = diffNode(v, steps)
                            val term1 = MathAstNode.BinaryOp(BinaryOperator.MUL, dv, MathAstNode.UnaryFunction(MathFunction1.LN, u))
                            val term2 = MathAstNode.BinaryOp(BinaryOperator.MUL, v, MathAstNode.BinaryOp(BinaryOperator.DIV, du, u))
                            MathAstNode.BinaryOp(BinaryOperator.MUL, node, MathAstNode.BinaryOp(BinaryOperator.ADD, term1, term2))
                        }
                    }
                    else -> throw UnsupportedSymbolicOperationException("operadores relacionais/lógicos (${node.op})")
                }
            }
            is MathAstNode.UnaryFunction -> {
                val u = node.argNode
                val du = diffNode(u, steps)
                val outerDeriv: MathAstNode = when (node.func) {
                    MathFunction1.SIN -> MathAstNode.UnaryFunction(MathFunction1.COS, u)
                    MathFunction1.COS -> MathAstNode.UnaryMinus(MathAstNode.UnaryFunction(MathFunction1.SIN, u))
                    MathFunction1.TAN -> MathAstNode.BinaryOp(
                        BinaryOperator.POW,
                        MathAstNode.UnaryFunction(MathFunction1.SEC, u),
                        MathAstNode.NumberLiteral(2.0)
                    )
                    MathFunction1.SEC -> MathAstNode.BinaryOp(
                        BinaryOperator.MUL,
                        MathAstNode.UnaryFunction(MathFunction1.SEC, u),
                        MathAstNode.UnaryFunction(MathFunction1.TAN, u)
                    )
                    MathFunction1.CSC -> MathAstNode.UnaryMinus(
                        MathAstNode.BinaryOp(
                            BinaryOperator.MUL,
                            MathAstNode.UnaryFunction(MathFunction1.CSC, u),
                            MathAstNode.UnaryFunction(MathFunction1.COT, u)
                        )
                    )
                    MathFunction1.COT -> MathAstNode.UnaryMinus(
                        MathAstNode.BinaryOp(
                            BinaryOperator.POW,
                            MathAstNode.UnaryFunction(MathFunction1.CSC, u),
                            MathAstNode.NumberLiteral(2.0)
                        )
                    )
                    MathFunction1.ASIN -> MathAstNode.BinaryOp(
                        BinaryOperator.DIV,
                        MathAstNode.NumberLiteral(1.0),
                        MathAstNode.UnaryFunction(
                            MathFunction1.SQRT,
                            MathAstNode.BinaryOp(
                                BinaryOperator.SUB,
                                MathAstNode.NumberLiteral(1.0),
                                MathAstNode.BinaryOp(BinaryOperator.POW, u, MathAstNode.NumberLiteral(2.0))
                            )
                        )
                    )
                    MathFunction1.ACOS -> MathAstNode.UnaryMinus(
                        MathAstNode.BinaryOp(
                            BinaryOperator.DIV,
                            MathAstNode.NumberLiteral(1.0),
                            MathAstNode.UnaryFunction(
                                MathFunction1.SQRT,
                                MathAstNode.BinaryOp(
                                    BinaryOperator.SUB,
                                    MathAstNode.NumberLiteral(1.0),
                                    MathAstNode.BinaryOp(BinaryOperator.POW, u, MathAstNode.NumberLiteral(2.0))
                                )
                            )
                        )
                    )
                    MathFunction1.ATAN -> MathAstNode.BinaryOp(
                        BinaryOperator.DIV,
                        MathAstNode.NumberLiteral(1.0),
                        MathAstNode.BinaryOp(
                            BinaryOperator.ADD,
                            MathAstNode.NumberLiteral(1.0),
                            MathAstNode.BinaryOp(BinaryOperator.POW, u, MathAstNode.NumberLiteral(2.0))
                        )
                    )
                    MathFunction1.SINH -> MathAstNode.UnaryFunction(MathFunction1.COSH, u)
                    MathFunction1.COSH -> MathAstNode.UnaryFunction(MathFunction1.SINH, u)
                    MathFunction1.TANH -> MathAstNode.BinaryOp(
                        BinaryOperator.SUB,
                        MathAstNode.NumberLiteral(1.0),
                        MathAstNode.BinaryOp(
                            BinaryOperator.POW,
                            MathAstNode.UnaryFunction(MathFunction1.TANH, u),
                            MathAstNode.NumberLiteral(2.0)
                        )
                    )
                    MathFunction1.EXP -> MathAstNode.UnaryFunction(MathFunction1.EXP, u)
                    MathFunction1.LN -> MathAstNode.BinaryOp(BinaryOperator.DIV, MathAstNode.NumberLiteral(1.0), u)
                    MathFunction1.LOG10 -> MathAstNode.BinaryOp(
                        BinaryOperator.DIV,
                        MathAstNode.NumberLiteral(1.0),
                        MathAstNode.BinaryOp(BinaryOperator.MUL, u, MathAstNode.NumberLiteral(ln(10.0)))
                    )
                    MathFunction1.LOG2 -> MathAstNode.BinaryOp(
                        BinaryOperator.DIV,
                        MathAstNode.NumberLiteral(1.0),
                        MathAstNode.BinaryOp(BinaryOperator.MUL, u, MathAstNode.NumberLiteral(ln(2.0)))
                    )
                    MathFunction1.SQRT -> MathAstNode.BinaryOp(
                        BinaryOperator.DIV,
                        MathAstNode.NumberLiteral(1.0),
                        MathAstNode.BinaryOp(
                            BinaryOperator.MUL,
                            MathAstNode.NumberLiteral(2.0),
                            MathAstNode.UnaryFunction(MathFunction1.SQRT, u)
                        )
                    )
                    MathFunction1.CBRT -> MathAstNode.BinaryOp(
                        BinaryOperator.DIV,
                        MathAstNode.NumberLiteral(1.0),
                        MathAstNode.BinaryOp(
                            BinaryOperator.MUL,
                            MathAstNode.NumberLiteral(3.0),
                            MathAstNode.BinaryOp(
                                BinaryOperator.POW,
                                MathAstNode.UnaryFunction(MathFunction1.CBRT, u),
                                MathAstNode.NumberLiteral(2.0)
                            )
                        )
                    )
                    MathFunction1.ABS -> MathAstNode.BinaryOp(
                        BinaryOperator.DIV,
                        u,
                        MathAstNode.UnaryFunction(MathFunction1.ABS, u)
                    )
                    MathFunction1.SIGN, MathFunction1.STEP, MathFunction1.FLOOR, MathFunction1.CEIL, MathFunction1.ROUND -> {
                        throw UnsupportedSymbolicOperationException("função em degrau/discreta (${node.func.name.lowercase()})")
                    }
                }
                val res = MathAstNode.BinaryOp(BinaryOperator.MUL, outerDeriv, du)
                steps.add(
                    MathResolutionStep(
                        originalExpression = formatAst(node),
                        ruleApplied = "Regra da Cadeia em ${node.func.name.lowercase()}(u): f'(g(x)) · g'(x)",
                        resultingExpression = formatAst(simplifyAst(res)),
                        graphicalInterpretation = "Multiplica a derivada da função externa pela taxa de variação do argumento interno u(x)."
                    )
                )
                res
            }
            is MathAstNode.BinaryFunction -> {
                when (node.func) {
                    MathFunction2.POW -> diffNode(MathAstNode.BinaryOp(BinaryOperator.POW, node.firstArg, node.secondArg), steps)
                    MathFunction2.ROOT -> {
                        val nNode = node.firstArg
                        val xNode = node.secondArg
                        val invN = MathAstNode.BinaryOp(BinaryOperator.DIV, MathAstNode.NumberLiteral(1.0), nNode)
                        diffNode(MathAstNode.BinaryOp(BinaryOperator.POW, xNode, invN), steps)
                    }
                    MathFunction2.LOG_BASE -> {
                        val lnArg = MathAstNode.UnaryFunction(MathFunction1.LN, node.firstArg)
                        val lnBase = MathAstNode.UnaryFunction(MathFunction1.LN, node.secondArg)
                        diffNode(MathAstNode.BinaryOp(BinaryOperator.DIV, lnArg, lnBase), steps)
                    }
                    MathFunction2.MIN, MathFunction2.MAX -> {
                        throw UnsupportedSymbolicOperationException("função não suave (${node.func.name.lowercase()})")
                    }
                }
            }
            is MathAstNode.ConditionalIf -> {
                throw UnsupportedSymbolicOperationException("expressão condicional por partes (if)")
            }
        }
    }

    private fun containsVariableX(node: MathAstNode): Boolean = when (node) {
        is MathAstNode.NumberLiteral -> false
        is MathAstNode.VariableX -> true
        is MathAstNode.UnaryMinus -> containsVariableX(node.operand)
        is MathAstNode.BinaryOp -> containsVariableX(node.left) || containsVariableX(node.right)
        is MathAstNode.UnaryFunction -> containsVariableX(node.argNode)
        is MathAstNode.BinaryFunction -> containsVariableX(node.firstArg) || containsVariableX(node.secondArg)
        is MathAstNode.ConditionalIf -> containsVariableX(node.condition) || containsVariableX(node.trueBranch) || containsVariableX(node.falseBranch)
    }

    internal fun simplifyAst(node: MathAstNode): MathAstNode {
        return when (node) {
            is MathAstNode.NumberLiteral, is MathAstNode.VariableX -> node
            is MathAstNode.UnaryMinus -> {
                val inner = simplifyAst(node.operand)
                when {
                    inner is MathAstNode.NumberLiteral -> MathAstNode.NumberLiteral(if (abs(inner.value) < 1e-12) 0.0 else -inner.value)
                    inner is MathAstNode.UnaryMinus -> inner.operand
                    else -> MathAstNode.UnaryMinus(inner)
                }
            }
            is MathAstNode.BinaryOp -> {
                val l = simplifyAst(node.left)
                val r = simplifyAst(node.right)
                if (l is MathAstNode.NumberLiteral && r is MathAstNode.NumberLiteral) {
                    val v = MathAstNode.BinaryOp(node.op, l, r).eval(0.0)
                    if (v.isFinite()) return MathAstNode.NumberLiteral(if (abs(v) < 1e-12) 0.0 else v)
                }
                when (node.op) {
                    BinaryOperator.ADD -> {
                        if (isZeroLiteral(l)) return r
                        if (isZeroLiteral(r)) return l
                        if (r is MathAstNode.UnaryMinus) {
                            return MathAstNode.BinaryOp(BinaryOperator.SUB, l, r.operand)
                        }
                        MathAstNode.BinaryOp(BinaryOperator.ADD, l, r)
                    }
                    BinaryOperator.SUB -> {
                        if (isZeroLiteral(r)) return l
                        if (isZeroLiteral(l)) return simplifyAst(MathAstNode.UnaryMinus(r))
                        MathAstNode.BinaryOp(BinaryOperator.SUB, l, r)
                    }
                    BinaryOperator.MUL -> {
                        if (isZeroLiteral(l) || isZeroLiteral(r)) return MathAstNode.NumberLiteral(0.0)
                        if (isOneLiteral(l)) return r
                        if (isOneLiteral(r)) return l
                        if (isMinusOneLiteral(l)) return simplifyAst(MathAstNode.UnaryMinus(r))
                        if (isMinusOneLiteral(r)) return simplifyAst(MathAstNode.UnaryMinus(l))
                        // Associatividade de constantes: c1 * (c2 * u) -> (c1*c2) * u
                        if (l is MathAstNode.NumberLiteral && r is MathAstNode.BinaryOp && r.op == BinaryOperator.MUL && r.left is MathAstNode.NumberLiteral) {
                            return simplifyAst(
                                MathAstNode.BinaryOp(
                                    BinaryOperator.MUL,
                                    MathAstNode.NumberLiteral(l.value * r.left.value),
                                    r.right
                                )
                            )
                        }
                        MathAstNode.BinaryOp(BinaryOperator.MUL, l, r)
                    }
                    BinaryOperator.DIV -> {
                        if (isZeroLiteral(l)) return MathAstNode.NumberLiteral(0.0)
                        if (isOneLiteral(r)) return l
                        MathAstNode.BinaryOp(BinaryOperator.DIV, l, r)
                    }
                    BinaryOperator.POW -> {
                        if (isZeroLiteral(r)) return MathAstNode.NumberLiteral(1.0)
                        if (isOneLiteral(r)) return l
                        if (isZeroLiteral(l)) return MathAstNode.NumberLiteral(0.0)
                        if (isOneLiteral(l)) return MathAstNode.NumberLiteral(1.0)
                        MathAstNode.BinaryOp(BinaryOperator.POW, l, r)
                    }
                    else -> MathAstNode.BinaryOp(node.op, l, r)
                }
            }
            is MathAstNode.UnaryFunction -> {
                val arg = simplifyAst(node.argNode)
                MathAstNode.UnaryFunction(node.func, arg)
            }
            is MathAstNode.BinaryFunction -> {
                MathAstNode.BinaryFunction(node.func, simplifyAst(node.firstArg), simplifyAst(node.secondArg))
            }
            is MathAstNode.ConditionalIf -> {
                MathAstNode.ConditionalIf(simplifyAst(node.condition), simplifyAst(node.trueBranch), simplifyAst(node.falseBranch))
            }
        }
    }

    private fun isZeroLiteral(node: MathAstNode): Boolean =
        node is MathAstNode.NumberLiteral && abs(node.value) < 1e-12

    private fun isOneLiteral(node: MathAstNode): Boolean =
        node is MathAstNode.NumberLiteral && abs(node.value - 1.0) < 1e-12

    private fun isMinusOneLiteral(node: MathAstNode): Boolean =
        node is MathAstNode.NumberLiteral && abs(node.value + 1.0) < 1e-12

    fun formatAst(node: MathAstNode): String {
        return when (node) {
            is MathAstNode.NumberLiteral -> formatNumber(node.value)
            is MathAstNode.VariableX -> "x"
            is MathAstNode.UnaryMinus -> {
                val inner = formatAst(node.operand)
                if (node.operand is MathAstNode.BinaryOp && (node.operand.op == BinaryOperator.ADD || node.operand.op == BinaryOperator.SUB)) {
                    "-($inner)"
                } else {
                    "-$inner"
                }
            }
            is MathAstNode.BinaryOp -> {
                val lStr = wrapIfLowerPrecedence(node.left, node.op, isRightChild = false)
                val rStr = wrapIfLowerPrecedence(node.right, node.op, isRightChild = true)
                val opStr = when (node.op) {
                    BinaryOperator.ADD -> " + "
                    BinaryOperator.SUB -> " - "
                    BinaryOperator.MUL -> "*"
                    BinaryOperator.DIV -> "/"
                    BinaryOperator.MOD -> "%"
                    BinaryOperator.POW -> "^"
                    BinaryOperator.LT -> " < "
                    BinaryOperator.LE -> " <= "
                    BinaryOperator.GT -> " > "
                    BinaryOperator.GE -> " >= "
                    BinaryOperator.EQ -> " == "
                    BinaryOperator.NE -> " != "
                    BinaryOperator.AND -> " && "
                    BinaryOperator.OR -> " || "
                }
                "$lStr$opStr$rStr"
            }
            is MathAstNode.UnaryFunction -> {
                val fnName = when (node.func) {
                    MathFunction1.SIN -> "sin"
                    MathFunction1.COS -> "cos"
                    MathFunction1.TAN -> "tan"
                    MathFunction1.SEC -> "sec"
                    MathFunction1.CSC -> "csc"
                    MathFunction1.COT -> "cot"
                    MathFunction1.ASIN -> "asin"
                    MathFunction1.ACOS -> "acos"
                    MathFunction1.ATAN -> "atan"
                    MathFunction1.SINH -> "sinh"
                    MathFunction1.COSH -> "cosh"
                    MathFunction1.TANH -> "tanh"
                    MathFunction1.SQRT -> "sqrt"
                    MathFunction1.CBRT -> "cbrt"
                    MathFunction1.ABS -> "abs"
                    MathFunction1.LN -> "ln"
                    MathFunction1.LOG10 -> "log10"
                    MathFunction1.LOG2 -> "log2"
                    MathFunction1.EXP -> "exp"
                    MathFunction1.SIGN -> "sign"
                    MathFunction1.STEP -> "step"
                    MathFunction1.FLOOR -> "floor"
                    MathFunction1.CEIL -> "ceil"
                    MathFunction1.ROUND -> "round"
                }
                "$fnName(${formatAst(node.argNode)})"
            }
            is MathAstNode.BinaryFunction -> {
                val fnName = when (node.func) {
                    MathFunction2.MIN -> "min"
                    MathFunction2.MAX -> "max"
                    MathFunction2.POW -> "pow"
                    MathFunction2.ROOT -> "root"
                    MathFunction2.LOG_BASE -> "log"
                }
                "$fnName(${formatAst(node.firstArg)}, ${formatAst(node.secondArg)})"
            }
            is MathAstNode.ConditionalIf -> {
                "if(${formatAst(node.condition)}, ${formatAst(node.trueBranch)}, ${formatAst(node.falseBranch)})"
            }
        }
    }

    private fun wrapIfLowerPrecedence(child: MathAstNode, parentOp: BinaryOperator, isRightChild: Boolean): String {
        val raw = formatAst(child)
        if (child is MathAstNode.UnaryMinus && parentOp == BinaryOperator.POW) {
            return "($raw)"
        }
        if (child !is MathAstNode.BinaryOp) return raw
        val pChild = precedenceOf(child.op)
        val pParent = precedenceOf(parentOp)
        val needsParens = pChild < pParent || (isRightChild && pChild == pParent && (parentOp == BinaryOperator.SUB || parentOp == BinaryOperator.DIV))
        return if (needsParens) "($raw)" else raw
    }

    private fun precedenceOf(op: BinaryOperator): Int = when (op) {
        BinaryOperator.OR -> 1
        BinaryOperator.AND -> 2
        BinaryOperator.LT, BinaryOperator.LE, BinaryOperator.GT, BinaryOperator.GE, BinaryOperator.EQ, BinaryOperator.NE -> 3
        BinaryOperator.ADD, BinaryOperator.SUB -> 4
        BinaryOperator.MUL, BinaryOperator.DIV, BinaryOperator.MOD -> 5
        BinaryOperator.POW -> 6
    }

    private fun formatNumber(v: Double): String {
        if (abs(v - round(v)) < 1e-9) {
            return round(v).toLong().toString()
        }
        return String.format(Locale.US, "%.4f", v).trimEnd('0').trimEnd('.')
    }

    // =========================================================================
    // 2. MÉTODOS NUMÉRICOS DE RAÍZES (BISSEÇÃO, NEWTON-RAPHSON, SECANTE, HÍBRIDO)
    // =========================================================================

    fun findDetailedRoots(
        expression: String,
        xMin: Double,
        xMax: Double,
        preferredMethod: RootFindingMethod = RootFindingMethod.HYBRID_SAFE,
        tolerance: Double = 1e-9,
        subdivisions: Int = 240
    ): List<DetailedRootResult> {
        val compiled = MathExpressionEvaluator.compile(expression)
        if (!compiled.isValid || xMax <= xMin) return emptyList()

        val results = mutableListOf<DetailedRootResult>()
        val dx = (xMax - xMin) / subdivisions

        fun registerCandidate(res: DetailedRootResult) {
            val cleanR = if (abs(res.root) < 1e-8) 0.0 else res.root
            if (cleanR in (xMin - 1e-5)..(xMax + 1e-5)) {
                if (results.none { abs(it.root - cleanR) < 1e-3 }) {
                    val exactInfo = detectKnownExactValue(cleanR)
                    results.add(
                        res.copy(
                            root = cleanR,
                            isAnalyticalExact = exactInfo != null && abs(res.functionValueAtRoot) < 1e-11,
                            exactLabel = exactInfo
                        )
                    )
                }
            }
        }

        var prevX = xMin
        var prevY = compiled.evaluate(prevX)
        var prevD = MathExpressionEvaluator.derivative(expression, prevX)

        for (i in 1..subdivisions) {
            val curX = xMin + i * dx
            val curY = compiled.evaluate(curX)
            val curD = MathExpressionEvaluator.derivative(expression, curX)

            if (!prevY.isNaN() && !curY.isNaN()) {
                // Raiz exata em ponto da grade
                if (abs(prevY) < 1e-11) {
                    registerCandidate(
                        DetailedRootResult(
                            root = prevX,
                            intervalInvestigated = prevX to curX,
                            functionValueAtRoot = prevY,
                            estimatedError = 0.0,
                            iterations = 1,
                            methodUsed = preferredMethod,
                            converged = true,
                            isAnalyticalExact = true
                        )
                    )
                } else if (prevY * curY < 0.0 && abs(curY - prevY) < (xMax - xMin) * 25.0) {
                    // Mudança de sinal (Bolzano)
                    val solved = solveBracketedRoot(compiled, expression, prevX, curX, preferredMethod, tolerance)
                    if (solved.converged && abs(solved.functionValueAtRoot) < 1e-3) {
                        registerCandidate(solved)
                    }
                } else if (!prevD.isNaN() && !curD.isNaN() && prevD * curD <= 0.0) {
                    // Raiz tangente de multiplicidade par (ex.: x^2 = 0, (x-2)^2 = 0)
                    var left = prevX
                    var right = curX
                    var dLeft = prevD
                    var iter = 0
                    for (b in 0..28) {
                        iter++
                        val mid = 0.5 * (left + right)
                        val dMid = MathExpressionEvaluator.derivative(expression, mid)
                        if (dMid.isNaN() || abs(dMid) < tolerance) {
                            left = mid
                            right = mid
                            break
                        }
                        if (dLeft * dMid < 0.0) right = mid else {
                            left = mid
                            dLeft = dMid
                        }
                    }
                    val rootX = 0.5 * (left + right)
                    val yAtRoot = compiled.evaluate(rootX)
                    if (!yAtRoot.isNaN() && abs(yAtRoot) < 1e-4) {
                        registerCandidate(
                            DetailedRootResult(
                                root = rootX,
                                intervalInvestigated = prevX to curX,
                                functionValueAtRoot = yAtRoot,
                                estimatedError = abs(right - left) * 0.5,
                                iterations = iter,
                                methodUsed = RootFindingMethod.HYBRID_SAFE,
                                converged = true,
                                isAnalyticalExact = false
                            )
                        )
                    }
                }
            }
            prevX = curX
            prevY = curY
            prevD = curD
        }
        return results.sortedBy { it.root }
    }

    fun solveBracketedRoot(
        compiled: CompiledMathExpression,
        expression: String,
        a: Double,
        b: Double,
        method: RootFindingMethod,
        tolerance: Double = 1e-9,
        maxIterations: Int = 40
    ): DetailedRootResult {
        return when (method) {
            RootFindingMethod.BISECTION -> solveBisection(compiled, a, b, tolerance, maxIterations)
            RootFindingMethod.NEWTON_RAPHSON -> {
                val nr = solveNewtonRaphson(compiled, expression, a, b, tolerance, maxIterations)
                if (nr.converged) nr else solveBisection(compiled, a, b, tolerance, maxIterations)
            }
            RootFindingMethod.SECANT -> {
                val sec = solveSecant(compiled, a, b, tolerance, maxIterations)
                if (sec.converged) sec else solveBisection(compiled, a, b, tolerance, maxIterations)
            }
            RootFindingMethod.HYBRID_SAFE -> solveHybridSafe(compiled, expression, a, b, tolerance, maxIterations)
        }
    }

    private fun solveBisection(
        compiled: CompiledMathExpression,
        a: Double,
        b: Double,
        tolerance: Double,
        maxIterations: Int
    ): DetailedRootResult {
        var left = a
        var right = b
        var fLeft = compiled.evaluate(left)
        var iterations = 0
        for (i in 1..maxIterations) {
            iterations = i
            val mid = 0.5 * (left + right)
            val fMid = compiled.evaluate(mid)
            if (fMid.isNaN()) {
                return DetailedRootResult(mid, a to b, Double.NaN, Double.NaN, iterations, RootFindingMethod.BISECTION, false, false)
            }
            if (abs(fMid) < tolerance || (right - left) * 0.5 < tolerance) {
                return DetailedRootResult(mid, a to b, fMid, (right - left) * 0.5, iterations, RootFindingMethod.BISECTION, true, false)
            }
            if (fLeft * fMid < 0.0) {
                right = mid
            } else {
                left = mid
                fLeft = fMid
            }
        }
        val mid = 0.5 * (left + right)
        val fMid = compiled.evaluate(mid)
        return DetailedRootResult(mid, a to b, fMid, (right - left) * 0.5, iterations, RootFindingMethod.BISECTION, !fMid.isNaN() && abs(fMid) < 1e-4, false)
    }

    private fun solveNewtonRaphson(
        compiled: CompiledMathExpression,
        expression: String,
        a: Double,
        b: Double,
        tolerance: Double,
        maxIterations: Int
    ): DetailedRootResult {
        var x = 0.5 * (a + b)
        var prevX = x
        for (i in 1..maxIterations) {
            val fx = compiled.evaluate(x)
            if (fx.isNaN()) return DetailedRootResult(x, a to b, Double.NaN, Double.NaN, i, RootFindingMethod.NEWTON_RAPHSON, false, false)
            if (abs(fx) < tolerance) {
                return DetailedRootResult(x, a to b, fx, abs(x - prevX), i, RootFindingMethod.NEWTON_RAPHSON, true, false)
            }
            val dfx = MathExpressionEvaluator.derivative(expression, x)
            if (dfx.isNaN() || abs(dfx) < 1e-11) {
                return DetailedRootResult(x, a to b, fx, abs(b - a), i, RootFindingMethod.NEWTON_RAPHSON, false, false)
            }
            val nextX = x - fx / dfx
            if (nextX < minOf(a, b) - 0.5 || nextX > maxOf(a, b) + 0.5) {
                return DetailedRootResult(x, a to b, fx, abs(b - a), i, RootFindingMethod.NEWTON_RAPHSON, false, false)
            }
            val err = abs(nextX - x)
            prevX = x
            x = nextX
            if (err < tolerance) {
                val fFinal = compiled.evaluate(x)
                return DetailedRootResult(x, a to b, fFinal, err, i, RootFindingMethod.NEWTON_RAPHSON, !fFinal.isNaN() && abs(fFinal) < 1e-4, false)
            }
        }
        return DetailedRootResult(x, a to b, compiled.evaluate(x), abs(x - prevX), maxIterations, RootFindingMethod.NEWTON_RAPHSON, false, false)
    }

    private fun solveSecant(
        compiled: CompiledMathExpression,
        a: Double,
        b: Double,
        tolerance: Double,
        maxIterations: Int
    ): DetailedRootResult {
        var x0 = a
        var x1 = b
        var f0 = compiled.evaluate(x0)
        var f1 = compiled.evaluate(x1)
        for (i in 1..maxIterations) {
            if (f0.isNaN() || f1.isNaN()) {
                return DetailedRootResult(x1, a to b, Double.NaN, Double.NaN, i, RootFindingMethod.SECANT, false, false)
            }
            if (abs(f1) < tolerance) {
                return DetailedRootResult(x1, a to b, f1, abs(x1 - x0), i, RootFindingMethod.SECANT, true, false)
            }
            val denom = f1 - f0
            if (abs(denom) < 1e-12) {
                return DetailedRootResult(x1, a to b, f1, abs(x1 - x0), i, RootFindingMethod.SECANT, false, false)
            }
            val x2 = x1 - f1 * (x1 - x0) / denom
            if (x2 < minOf(a, b) - 0.5 || x2 > maxOf(a, b) + 0.5) {
                return DetailedRootResult(x1, a to b, f1, abs(x1 - x0), i, RootFindingMethod.SECANT, false, false)
            }
            x0 = x1
            f0 = f1
            x1 = x2
            f1 = compiled.evaluate(x1)
            if (abs(x1 - x0) < tolerance && !f1.isNaN() && abs(f1) < 1e-4) {
                return DetailedRootResult(x1, a to b, f1, abs(x1 - x0), i, RootFindingMethod.SECANT, true, false)
            }
        }
        return DetailedRootResult(x1, a to b, f1, abs(x1 - x0), maxIterations, RootFindingMethod.SECANT, false, false)
    }

    private fun solveHybridSafe(
        compiled: CompiledMathExpression,
        expression: String,
        a: Double,
        b: Double,
        tolerance: Double,
        maxIterations: Int
    ): DetailedRootResult {
        var left = minOf(a, b)
        var right = maxOf(a, b)
        var fLeft = compiled.evaluate(left)
        var x = 0.5 * (left + right)
        for (i in 1..maxIterations) {
            val fx = compiled.evaluate(x)
            if (fx.isNaN()) break
            if (abs(fx) < tolerance || (right - left) * 0.5 < tolerance) {
                return DetailedRootResult(x, a to b, fx, (right - left) * 0.5, i, RootFindingMethod.HYBRID_SAFE, true, false)
            }
            if (fLeft * fx < 0.0) {
                right = x
            } else {
                left = x
                fLeft = fx
            }
            val dfx = MathExpressionEvaluator.derivative(expression, x)
            val nrCandidate = if (!dfx.isNaN() && abs(dfx) > 1e-9) x - fx / dfx else Double.NaN
            x = if (!nrCandidate.isNaN() && nrCandidate > left && nrCandidate < right) {
                nrCandidate
            } else {
                0.5 * (left + right)
            }
        }
        return solveBisection(compiled, a, b, tolerance, maxIterations)
    }

    private fun detectKnownExactValue(x: Double): String? {
        if (abs(x - round(x)) < 1e-7) return round(x).toLong().toString()
        val knownConstants = listOf(
            PI to "π",
            -PI to "-π",
            PI / 2.0 to "π/2",
            -PI / 2.0 to "-π/2",
            PI / 4.0 to "π/4",
            -PI / 4.0 to "-π/4",
            sqrt(2.0) to "√2",
            -sqrt(2.0) to "-√2",
            sqrt(3.0) to "√3",
            -sqrt(3.0) to "-√3",
            E to "e"
        )
        for ((v, label) in knownConstants) {
            if (abs(x - v) < 1e-6) return label
        }
        return null
    }

    // =========================================================================
    // 3. MONOTONICIDADE, ASSÍNTOTAS (VERTICAIS, HORIZONTAIS, OBLÍQUAS) E DESCONTINUIDADES
    // =========================================================================

    fun analyzeMonotonicity(expression: String, xMin: Double, xMax: Double, samples: Int = 120): List<MonotonicityInterval> {
        val compiled = MathExpressionEvaluator.compile(expression)
        if (!compiled.isValid || xMax <= xMin) return emptyList()

        val intervals = mutableListOf<MonotonicityInterval>()
        val dx = (xMax - xMin) / samples
        var segStart = xMin
        var currentInc: Boolean? = null
        var sumSlope = 0.0
        var countSlope = 0

        for (i in 0..samples) {
            val x = xMin + i * dx
            val d = MathExpressionEvaluator.derivative(expression, x)
            if (d.isNaN() || abs(d) < 1e-5 || abs(d) > 1e4) continue
            val isInc = d > 0.0
            if (currentInc == null) {
                segStart = x
                currentInc = isInc
                sumSlope = d
                countSlope = 1
            } else if (currentInc == isInc) {
                sumSlope += d
                countSlope++
            } else {
                intervals.add(
                    MonotonicityInterval(
                        xStart = segStart,
                        xEnd = x,
                        isIncreasing = currentInc,
                        sampleDerivative = sumSlope / countSlope.coerceAtLeast(1)
                    )
                )
                segStart = x
                currentInc = isInc
                sumSlope = d
                countSlope = 1
            }
        }
        if (currentInc != null && xMax > segStart) {
            intervals.add(
                MonotonicityInterval(
                    xStart = segStart,
                    xEnd = xMax,
                    isIncreasing = currentInc,
                    sampleDerivative = sumSlope / countSlope.coerceAtLeast(1)
                )
            )
        }
        return intervals
    }

    fun analyzeAllAsymptotes(expression: String, xMin: Double, xMax: Double): List<DetectedAsymptote> {
        val list = mutableListOf<DetectedAsymptote>()
        val verticals = MathExpressionEvaluator.findVerticalAsymptotes(expression, xMin, xMax)
        for (vx in verticals) {
            list.add(
                DetectedAsymptote(
                    kind = AsymptoteKind.VERTICAL,
                    equation = String.format(Locale.US, "x = %.2f", vx),
                    xPosition = vx
                )
            )
        }

        val compiled = MathExpressionEvaluator.compile(expression)
        if (!compiled.isValid) return list

        // Investiga limite em +∞ e -∞ para assíntotas horizontais ou oblíquas
        for (dir in listOf(1.0, -1.0)) {
            val x1 = dir * 200.0
            val x2 = dir * 500.0
            val y1 = compiled.evaluate(x1)
            val y2 = compiled.evaluate(x2)
            if (y1.isFinite() && y2.isFinite()) {
                if (abs(y2 - y1) < 0.05 && abs(y2) < 1e4) {
                    val hVal = if (abs(y2 - round(y2)) < 1e-2) round(y2) else y2
                    val eq = String.format(Locale.US, "y = %.2f", hVal)
                    if (list.none { it.kind == AsymptoteKind.HORIZONTAL && it.equation == eq }) {
                        list.add(
                            DetectedAsymptote(
                                kind = AsymptoteKind.HORIZONTAL,
                                equation = eq,
                                slope = 0.0,
                                intercept = hVal
                            )
                        )
                    }
                } else {
                    // Verifica assíntota oblíqua y = m*x + b
                    val m1 = y1 / x1
                    val m2 = y2 / x2
                    if (abs(m2 - m1) < 0.02 && abs(m2) in 0.05..100.0) {
                        val mClean = if (abs(m2 - round(m2)) < 1e-2) round(m2) else m2
                        val b1 = y1 - mClean * x1
                        val b2 = y2 - mClean * x2
                        if (abs(b2 - b1) < 0.1 && abs(b2) < 1e4) {
                            val bClean = if (abs(b2 - round(b2)) < 1e-2) round(b2) else b2
                            val signStr = if (bClean >= 0) "+" else "-"
                            val eq = String.format(Locale.US, "y = %.2fx %s %.2f", mClean, signStr, abs(bClean))
                            if (list.none { it.kind == AsymptoteKind.OBLIQUE && it.equation == eq }) {
                                list.add(
                                    DetectedAsymptote(
                                        kind = AsymptoteKind.OBLIQUE,
                                        equation = eq,
                                        slope = mClean,
                                        intercept = bClean
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
        return list
    }

    fun detectDiscontinuities(expression: String, xMin: Double, xMax: Double, steps: Int = 300): List<DetectedDiscontinuity> {
        val compiled = MathExpressionEvaluator.compile(expression)
        if (!compiled.isValid || xMax <= xMin) return emptyList()

        val result = mutableListOf<DetectedDiscontinuity>()
        val dx = (xMax - xMin) / steps

        // 1. Verifica pontos inteiros notáveis onde podem ocorrer buracos removíveis 0/0 (ex.: (x^2-1)/(x-1) em x=1)
        val startInt = ceil(xMin).toInt()
        val endInt = floor(xMax).toInt()
        for (k in startInt..endInt) {
            val xk = k.toDouble()
            val yExact = compiled.evaluate(xk)
            if (yExact.isNaN()) {
                val leftLim = compiled.evaluate(xk - 1e-5)
                val rightLim = compiled.evaluate(xk + 1e-5)
                if (leftLim.isFinite() && rightLim.isFinite()) {
                    if (abs(leftLim - rightLim) < 1e-2 && abs(leftLim) < 1e3) {
                        result.add(DetectedDiscontinuity(xk, leftLim, rightLim, DiscontinuityKind.REMOVABLE))
                    } else if (abs(leftLim) > 50.0 || abs(rightLim) > 50.0) {
                        result.add(DetectedDiscontinuity(xk, leftLim, rightLim, DiscontinuityKind.INFINITE_POLE))
                    }
                }
            }
        }

        // 2. Verifica descontinuidades de salto na malha
        var prevX = xMin
        var prevY = compiled.evaluate(prevX)
        for (i in 1..steps) {
            val curX = xMin + i * dx
            val curY = compiled.evaluate(curX)
            if (prevY.isFinite() && curY.isFinite()) {
                val jump = abs(curY - prevY)
                val midX = 0.5 * (prevX + curX)
                val midSlope = abs(MathExpressionEvaluator.derivative(expression, midX, h = dx * 0.25))
                if (jump > 0.5 && jump < 50.0 && midSlope > jump / dx * 1.8) {
                    val leftLim = compiled.evaluate(midX - 1e-4)
                    val rightLim = compiled.evaluate(midX + 1e-4)
                    if (leftLim.isFinite() && rightLim.isFinite() && abs(rightLim - leftLim) > 0.4) {
                        if (result.none { abs(it.x - midX) < dx * 2 }) {
                            result.add(DetectedDiscontinuity(midX, leftLim, rightLim, DiscontinuityKind.JUMP))
                        }
                    }
                }
            }
            prevX = curX
            prevY = curY
        }
        return result.sortedBy { it.x }
    }

    // =========================================================================
    // 4. INTEGRAÇÃO DEFINIDA, ÁREA GEOMÉTRICA, ERRO ESTIMADO E PRIMITIVAS
    // =========================================================================

    fun compareIntegrationMethods(
        expression: String,
        a: Double,
        b: Double,
        subdivisions: Int = 20
    ): IntegrationComparisonReport {
        val safeN = subdivisions.coerceIn(2, 500)
        val evenN = if (safeN % 2 == 0) safeN else safeN + 1

        val simpsonN = MathExpressionEvaluator.definiteIntegral(expression, a, b, intervals = evenN)
        val simpson2N = MathExpressionEvaluator.definiteIntegral(expression, a, b, intervals = evenN * 2)
        val simpsonErr = if (!simpsonN.isNaN() && !simpson2N.isNaN()) abs(simpson2N - simpsonN) / 15.0 else Double.NaN

        val geometricArea = MathExpressionEvaluator.definiteIntegral("abs($expression)", a, b, intervals = evenN * 2)

        val trapN = MathExpressionEvaluator.calculateRiemannSum(expression, a, b, safeN, RiemannType.TRAPEZOIDAL)
        val trap2N = MathExpressionEvaluator.calculateRiemannSum(expression, a, b, safeN * 2, RiemannType.TRAPEZOIDAL)
        val trapErr = if (!trapN.isNaN() && !trap2N.isNaN()) abs(trap2N - trapN) / 3.0 else Double.NaN

        val rLeft = MathExpressionEvaluator.calculateRiemannSum(expression, a, b, safeN, RiemannType.LEFT)
        val rRight = MathExpressionEvaluator.calculateRiemannSum(expression, a, b, safeN, RiemannType.RIGHT)
        val rMid = MathExpressionEvaluator.calculateRiemannSum(expression, a, b, safeN, RiemannType.MIDPOINT)

        val symbolicAntideriv = trySymbolicAntiderivative(expression)

        return IntegrationComparisonReport(
            expression = expression,
            lowerBound = a,
            upperBound = b,
            subdivisions = safeN,
            signedAreaSimpson = simpson2N,
            geometricAreaSimpson = geometricArea,
            simpsonErrorEstimate = simpsonErr,
            trapezoidalValue = trapN,
            trapezoidalErrorEstimate = trapErr,
            riemannLeft = rLeft,
            riemannRight = rRight,
            riemannMidpoint = rMid,
            symbolicAntiderivative = symbolicAntideriv,
            isSymbolicSupported = symbolicAntideriv != null
        )
    }

    /**
     * Calcula a primitiva simbólica F(x) + C para casos elementares suportados (polinômios, 1/x,
     * exponenciais e trigonométricas simples). Quando não suportado, retorna null explicitamente.
     */
    fun trySymbolicAntiderivative(expression: String): String? {
        val compiled = MathExpressionEvaluator.compile(expression)
        val root = compiled.rootNode ?: return null
        val antiNode = integrateNodeSymbolically(root) ?: return null
        return "${formatAst(simplifyAst(antiNode))} + C"
    }

    private fun integrateNodeSymbolically(node: MathAstNode): MathAstNode? {
        if (!containsVariableX(node)) {
            return MathAstNode.BinaryOp(BinaryOperator.MUL, node, MathAstNode.VariableX)
        }
        return when (node) {
            is MathAstNode.VariableX -> {
                // ∫ x dx = x^2 / 2
                MathAstNode.BinaryOp(
                    BinaryOperator.DIV,
                    MathAstNode.BinaryOp(BinaryOperator.POW, MathAstNode.VariableX, MathAstNode.NumberLiteral(2.0)),
                    MathAstNode.NumberLiteral(2.0)
                )
            }
            is MathAstNode.UnaryMinus -> {
                val inner = integrateNodeSymbolically(node.operand) ?: return null
                MathAstNode.UnaryMinus(inner)
            }
            is MathAstNode.BinaryOp -> {
                when (node.op) {
                    BinaryOperator.ADD -> {
                        val l = integrateNodeSymbolically(node.left) ?: return null
                        val r = integrateNodeSymbolically(node.right) ?: return null
                        MathAstNode.BinaryOp(BinaryOperator.ADD, l, r)
                    }
                    BinaryOperator.SUB -> {
                        val l = integrateNodeSymbolically(node.left) ?: return null
                        val r = integrateNodeSymbolically(node.right) ?: return null
                        MathAstNode.BinaryOp(BinaryOperator.SUB, l, r)
                    }
                    BinaryOperator.MUL -> {
                        if (!containsVariableX(node.left)) {
                            val r = integrateNodeSymbolically(node.right) ?: return null
                            MathAstNode.BinaryOp(BinaryOperator.MUL, node.left, r)
                        } else if (!containsVariableX(node.right)) {
                            val l = integrateNodeSymbolically(node.left) ?: return null
                            MathAstNode.BinaryOp(BinaryOperator.MUL, node.right, l)
                        } else null
                    }
                    BinaryOperator.DIV -> {
                        if (!containsVariableX(node.left) && node.right is MathAstNode.VariableX) {
                            // ∫ c/x dx = c * ln(|x|)
                            MathAstNode.BinaryOp(
                                BinaryOperator.MUL,
                                node.left,
                                MathAstNode.UnaryFunction(MathFunction1.LN, MathAstNode.UnaryFunction(MathFunction1.ABS, MathAstNode.VariableX))
                            )
                        } else null
                    }
                    BinaryOperator.POW -> {
                        if (node.left is MathAstNode.VariableX && !containsVariableX(node.right)) {
                            val nVal = node.right.eval(0.0)
                            if (abs(nVal + 1.0) < 1e-12) {
                                MathAstNode.UnaryFunction(MathFunction1.LN, MathAstNode.UnaryFunction(MathFunction1.ABS, MathAstNode.VariableX))
                            } else {
                                MathAstNode.BinaryOp(
                                    BinaryOperator.DIV,
                                    MathAstNode.BinaryOp(BinaryOperator.POW, MathAstNode.VariableX, MathAstNode.NumberLiteral(nVal + 1.0)),
                                    MathAstNode.NumberLiteral(nVal + 1.0)
                                )
                            }
                        } else null
                    }
                    else -> null
                }
            }
            is MathAstNode.UnaryFunction -> {
                if (node.argNode is MathAstNode.VariableX) {
                    when (node.func) {
                        MathFunction1.SIN -> MathAstNode.UnaryMinus(MathAstNode.UnaryFunction(MathFunction1.COS, MathAstNode.VariableX))
                        MathFunction1.COS -> MathAstNode.UnaryFunction(MathFunction1.SIN, MathAstNode.VariableX)
                        MathFunction1.EXP -> MathAstNode.UnaryFunction(MathFunction1.EXP, MathAstNode.VariableX)
                        MathFunction1.SQRT -> MathAstNode.BinaryOp(
                            BinaryOperator.MUL,
                            MathAstNode.NumberLiteral(2.0 / 3.0),
                            MathAstNode.BinaryOp(BinaryOperator.POW, MathAstNode.VariableX, MathAstNode.NumberLiteral(1.5))
                        )
                        else -> null
                    }
                } else null
            }
            else -> null
        }
    }
}
