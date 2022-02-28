import org.junit.Test

import org.junit.Assert.*

class MainKtTest {

    val TYPE_MASTERCARD = "mastercard"
    val TYPE_MAESTRO = "maestro"
    val TYPE_VISA = "visa"
    val TYPE_MIR = "mir"
    val TYPE_VKPAY = "vk_pay"

    @Test
    fun tax() {
        val amount1 = 4_000
        val type1 = TYPE_MASTERCARD

        val amount2 = 7_650_000
        val type2 = TYPE_MAESTRO

        val amount3 = 15_000_000
        val type3 = TYPE_VISA

        val amount4 = 4_000
        val type4 = TYPE_MIR

        val amount5 = 10_000
        val type5 = TYPE_VKPAY

        val amountError = 100_000
        val typeError = "error"

        val result1 = tax(amount1, type1)
        val result2 = tax(amount2, type2)
        val result3 = tax(amount3, type3)
        val result4 = tax(amount4, type4)
        val result5 = tax(amount5, type5)
        val exception = assertThrows(IllegalStateException::class.java) {
            tax(amountError, typeError)
        }

        assertEquals(result1, 0)
        assertEquals(result2, 47_900)
        assertEquals(result3, 112_500)
        assertEquals(result4, 3_500)
        assertEquals(result5, 0)
        assertEquals(exception.message, "К сожелению, мы не поддерживаем данный тип карты")
    }

    @Test
    fun taxMastercardMaestro_shouldCalculateCommissionForMastercardMaestro() {
        val amount1 = 4_000
        val amount2 = 7_650_000

        val result1 = taxMastercardMaestro(amount1)
        val result2 = taxMastercardMaestro(amount2)

        assertEquals(result1, 0)
        assertEquals(result2, 47_900)
    }

    @Test
    fun taxVisaMir_shouldCalculateCommissionForVisaMir() {
        val amount1 = 15_000_000
        val amount2 = 400_000

        val result1 = taxVisaMir(amount1)
        val result2 = taxVisaMir(amount2)

        assertEquals(result1, 112_500)
        assertEquals(result2, 3_500)
    }

    @Test
    fun conversionCurrency_shouldTranslateIntoPennies() {
        val amount = 100

        val result = conversionCurrency(amount)

        assertEquals(10000, result)
    }
}