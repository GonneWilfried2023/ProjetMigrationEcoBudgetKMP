package com.example

import com.example.model.YearMonth
import com.example.viewmodel.EcoBudgetUiState
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class EcoBudgetTest {

    @Test
    fun pourcentageConsomme() {
        val state = EcoBudgetUiState(monthlyBudget = 500000.0, totalSpent = 125000.0)
        assertEquals(25, state.budgetUsagePercentage)
    }

    @Test
    fun navigationDecembreJanvier() {
        assertEquals(YearMonth(2027, 0), YearMonth(2026, 11).next())
        assertEquals(YearMonth(2025, 11), YearMonth(2026, 0).previous())
    }

    @Test
    fun timestampDansLeBonMois() {
        val ym = YearMonth(2026, 0) // janvier : valide le "+ 1" de monthNumber
        assertTrue(ym.containsTimestamp(ym.toMillis(15, 12)))
    }
}