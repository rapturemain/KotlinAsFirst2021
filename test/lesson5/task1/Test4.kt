package lesson5.task1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.math.pow

class Test4 {

    @Test
    fun `{averageStockPrice} this test checks if input is empty`() {
        assertTrue(averageStockPrice(emptyList()).isEmpty())
    }

    @Test
    fun `{averageStockPrice} this test checks if map contains only unique values`() {
        val stocks = listOf(
            "1" to 1.0,
            "2" to 2.0,
            "99999999.9" to 99999999.9
        )

        val result = averageStockPrice(stocks)

        assertEquals(3, result.size)
        assertEquals(1.0, result["1"])
        assertEquals(2.0, result["2"])
        assertEquals(99999999.9, result["99999999.9"])
    }

    @Test
    fun `{averageStockPrice} this test checks if some prices are negative`() {
        val stocks = listOf(
            "1" to 1.0,
            "2" to 2.0,
            "99999999.9" to 99999999.9,
            "1" to 2.0,
            "2" to -2.0
        )

        val result = averageStockPrice(stocks)

        assertEquals(3, result.size)
        assertEquals((1.0 + 2.0) / 2, result["1"])
        assertEquals((2.0 - 2.0) / 2, result["2"])
        assertEquals(99999999.9, result["99999999.9"])
    }

    @Test
    fun `{averageStockPrice} this test checks possible overflow`() {
        val stocks = listOf(
            "1" to 10.0.pow(308.0),
            "2" to 2.0,
            "99999999.9" to 99999999.9,
            "1" to 10.0.pow(308.0),
            "1" to 10.0.pow(308.0),
            "1" to 10.0.pow(308.0),
        )

        val result = averageStockPrice(stocks)

        assertEquals(3, result.size)
        assertEquals(10.0.pow(308.0), result["1"])
        assertEquals(2.0, result["2"])
        assertEquals(99999999.9, result["99999999.9"])
    }

    @Test
    fun `{findSumOfTwo} this test checks if input is empty`() {
        assertEquals(-1 to -1, findSumOfTwo(emptyList(), 0))
    }

    @Test
    fun `{findSumOfTwo} this test checks normal input`() {
        assertEquals(0 to 2, findSumOfTwo(listOf(1, 2, 3), 4))
        assertEquals(-1 to -1, findSumOfTwo(listOf(1, 2, 3), 6))
        assertEquals(1 to 2, findSumOfTwo(listOf(1, 3, 3), 6))
    }

    @Test
    fun `{findSumOfTwo} this test checks if requested number is negative`() {
        val input = listOf(
            1,
            2,
            3,
            4,
            Integer.MAX_VALUE - 3,
            Integer.MAX_VALUE - 2,
            Integer.MAX_VALUE - 1,
            Integer.MAX_VALUE
        )

        val result = findSumOfTwo(input, Integer.MIN_VALUE)

        assertEquals(-1 to -1, result)
    }
}