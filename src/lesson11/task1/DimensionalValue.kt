@file:Suppress("UNUSED_PARAMETER")

package lesson11.task1

import org.junit.Test
import java.lang.IllegalArgumentException

/**
 * Класс "Величина с размерностью".
 *
 * Предназначен для представления величин вроде "6 метров" или "3 килограмма"
 * Общая сложность задания - средняя, общая ценность в баллах -- 18
 * Величины с размерностью можно складывать, вычитать, делить, менять им знак.
 * Их также можно умножать и делить на число.
 *
 * В конструктор передаётся вещественное значение и строковая размерность.
 * Строковая размерность может:
 * - либо строго соответствовать одной из abbreviation класса Dimension (m, g)
 * - либо соответствовать одной из приставок, к которой приписана сама размерность (Km, Kg, mm, mg)
 * - во всех остальных случаях следует бросить IllegalArgumentException
 */
class DimensionalValue(value: Double, dimension: String) : Comparable<DimensionalValue> {

    private val primaryValue = value
    private val primaryDimension = dimension

    /**
     * Величина с БАЗОВОЙ размерностью (например для 1.0Kg следует вернуть результат в граммах -- 1000.0)
     */
    val value: Double
        get() {
            var multiplier = 1.0
            if (primaryDimension.length == 2) {
                val primaryPrefix = primaryDimension.first().toString()
                for (value in DimensionPrefix.values()) if (primaryPrefix == value.abbreviation) {
                    multiplier = value.multiplier
                    break
                }
            }
            return primaryValue * multiplier
        }

    /**
     * БАЗОВАЯ размерность (опять-таки для 1.0Kg следует вернуть GRAM)
     */
    val dimension: Dimension
        get() {
            var searchName: String? = null
            for (value in Dimension.values()) if (primaryDimension.last().toString() == value.abbreviation) {
                searchName = value.name
                break
            }
            if (searchName is String) return Dimension.valueOf(searchName) else throw IllegalArgumentException()
        }

    /**
     * Конструктор из строки. Формат строки: значение пробел размерность (1 Kg, 3 mm, 100 g и так далее).
     */
    constructor(s: String) : this(s.substringBefore(" ").toDouble(), s.substringAfter(" "))

    /**
     * Сложение с другой величиной. Если базовая размерность разная, бросить IllegalArgumentException
     * (нельзя складывать метры и килограммы)
     */
    operator fun plus(other: DimensionalValue): DimensionalValue =
        if (other.dimension == dimension) DimensionalValue(value + other.value, dimension.abbreviation)
        else throw IllegalArgumentException()

    /**
     * Смена знака величины
     */
    operator fun unaryMinus(): DimensionalValue = DimensionalValue(-value, dimension.abbreviation)

    /**
     * Вычитание другой величины. Если базовая размерность разная, бросить IllegalArgumentException
     */
    operator fun minus(other: DimensionalValue): DimensionalValue =
        if (other.dimension == dimension) DimensionalValue(value - other.value, dimension.abbreviation)
        else throw IllegalArgumentException()

    /**
     * Умножение на число
     */
    operator fun times(other: Double): DimensionalValue = DimensionalValue(value * other, dimension.abbreviation)

    /**
     * Деление на число
     */
    operator fun div(other: Double): DimensionalValue = DimensionalValue(value / other, dimension.abbreviation)

    /**
     * Деление на другую величину. Если базовая размерность разная, бросить IllegalArgumentException
     */
    operator fun div(other: DimensionalValue): Double = if (dimension == other.dimension) value / other.value
    else throw IllegalArgumentException()

    /**
     * Сравнение на равенство
     */
    override fun equals(other: Any?): Boolean = other is DimensionalValue && value == other.value &&
            dimension == other.dimension

    override fun hashCode(): Int {
        var result = value.hashCode()
        result = 31 * result + dimension.hashCode()
        return result
    }

    /**
     * Сравнение на больше/меньше. Если базовая размерность разная, бросить IllegalArgumentException
     */
    override fun compareTo(other: DimensionalValue): Int =
        if (dimension == other.dimension) value.compareTo(other.value) else throw IllegalArgumentException()
}

/**
 * Размерность. В этот класс можно добавлять новые варианты (секунды, амперы, прочие), но нельзя убирать
 */
enum class Dimension(val abbreviation: String) {
    METER("m"),
    GRAM("g");
}

/**
 * Приставка размерности. Опять-таки можно добавить новые варианты (деци-, санти-, мега-, ...), но нельзя убирать
 */
enum class DimensionPrefix(val abbreviation: String, val multiplier: Double) {
    KILO("K", 1000.0),
    MILLI("m", 0.001);
}