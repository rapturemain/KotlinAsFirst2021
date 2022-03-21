package lesson7.task1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File
import java.io.IOException
import java.util.*

class Test4 {

    private val chooseLongestChaoticWord =
        { inputName: String, outputName: String -> chooseLongestChaoticWord(inputName, outputName) }

    private fun assertFileMethodEquals(
        fileContent: String,
        expectedResult: String,
        call: (inputName: String, outputName: String) -> Unit
    ) {
        val inputName = UUID.randomUUID().toString()
        val outputName = UUID.randomUUID().toString()

        try {
            File(inputName).bufferedWriter().apply {
                write(fileContent)
                close()
            }

            call(inputName, outputName)

            val result = File(outputName).readLines().joinToString("\n")

            assertEquals(expectedResult, result)
        } catch (e: IOException) {

        } finally {
            File(inputName).delete()
            File(outputName).delete()
        }
    }

    @Test
    fun `{chooseLongestChaoticWord} this test checks if input is empty`() {
        assertFileMethodEquals("", "", chooseLongestChaoticWord)
    }

    @Test
    fun `{chooseLongestChaoticWord} this test checks normal input`() {
        assertFileMethodEquals(
            """
            Карминовый
            Боязливый
            Некрасивый
            Остроумный
            БелогЛазый
            ФиолетОвый
            """.trimIndent(),
            "Карминовый, Некрасивый",
            chooseLongestChaoticWord
        )

        assertFileMethodEquals(
            """
            qwertyu
            qWertyw
            """.trimIndent(),
            "qwertyu",
            chooseLongestChaoticWord
        )

        assertFileMethodEquals(
            """
            
            あいう
            あいうえか
            あいうえあ
            あいうえお
            """.trimIndent(),
            "あいうえか, あいうえお",
            chooseLongestChaoticWord
        )
    }
}