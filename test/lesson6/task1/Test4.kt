package lesson6.task1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class Test4 {

    @Test
    fun `{mostExpensive} this tests checks if input is empty`() {
        assertTrue(mostExpensive("").isEmpty())
    }

    @Test
    fun `{mostExpensive} this tests checks if input is normal`() {
        assertEquals("Курица", mostExpensive("Хлеб 39.9; Молоко 62.5; Курица 184.0; Конфеты 89.9"))
        assertEquals("Вино", mostExpensive("Вино 255.0"))

        assertEquals("Курица", mostExpensive("Хлеб 399; Молоко 625; Курица 1840; Конфеты 899"))
        assertEquals("Вино", mostExpensive("Вино 2550"))

        assertEquals("Курица", mostExpensive("Хлеб 39,9; Молоко 62,5; Курица 184,0; Конфеты 89,9"))
        assertEquals("Вино", mostExpensive("Вино 255,0"))
    }

    @Test
    fun `{mostExpensive} this tests checks if huge or small number is used`() {
        assertEquals("Молоко", mostExpensive("Хлеб 200000000000000000; Молоко 20000000000000001"))
        assertEquals("Хлеб", mostExpensive("Хлеб 200000000000000001; Молоко 20000000000000000"))

        assertEquals("Молоко", mostExpensive("Хлеб 1.000000000000000000000000001; Молоко 1.000000000000000000000000002"))
        assertEquals("Хлеб", mostExpensive("Хлеб 1.000000000000000000000000002; Молоко 1.000000000000000000000000001"))
    }

    @Test
    fun `{mostExpensive} this tests checks if input format is wrong`() {
        assertEquals("", mostExpensive("Хлеб 39.9;Молоко 62.5; Курица 184.0; Конфеты 89.9"))
        assertEquals("", mostExpensive("Хлеб 39.9; Молоко 62.5; Курица 184.0; Конфеты"))
        assertEquals("", mostExpensive("Хлеб 39.9; Молоко ; Курица 184.0; Конфеты 89.9"))
        assertEquals("", mostExpensive("Хлеб 39.9 Молоко 62.5; Курица 184.0; Конфеты 89.9"))
    }
}