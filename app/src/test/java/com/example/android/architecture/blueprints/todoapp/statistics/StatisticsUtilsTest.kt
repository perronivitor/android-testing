package com.example.android.architecture.blueprints.todoapp.statistics

import com.example.android.architecture.blueprints.todoapp.data.Task
import junit.framework.Assert.assertEquals
import org.junit.Test

class StatisticsUtilsTest {

    @Test
    fun getActiveAndCompletedStats_noCompleted_returnsHundredZero() {
        // criando cenario de teste
        val tasks = listOf<Task>(
            Task("title", "desc", isCompleted = false)
        )

        // executando ação esperada
        val result = getActiveAndCompletedStats(tasks)

        // testando resultado esperado
        assertEquals(result.completedTasksPercent, 0f)
        assertEquals(result.activeTasksPercent, 100f)

        // testando resultado esperado com melhoria de apresentação apra usuario
        // assertThat(result.completedTasksPercent, `is`(100f))
        // assertThat(result.activeTasksPercent, `is`(0f))
    }

    @Test
    fun deve_DevolverCompletedTasksPercent100eActiveTasksPercent0_QuandoUmaTarefaConcluidaENenhumaAtiva() {
        // criando cenario de teste
        val tasks = listOf<Task>(
            Task("title", "desc", isCompleted = true)
        )

        // executando ação esperada
        val result = getActiveAndCompletedStats(tasks)

        assertEquals(result.activeTasksPercent, 0f)
        assertEquals(result.completedTasksPercent, 100f)
    }

    @Test
    fun deve_DevolverCompletedTaskPercent40EActiveTaskPercent60_QuandoDuasTarefasConcluidasETresAtivas() {

        val tasks = listOf<Task>(
            Task("title", "desc", isCompleted = true),
            Task("title", "desc", isCompleted = true),
            Task("title", "desc", isCompleted = false),
            Task("title", "desc", isCompleted = false),
            Task("title", "desc", isCompleted = false)
        )

        val result = getActiveAndCompletedStats(tasks)

        assertEquals(result.completedTasksPercent, 40f)
        assertEquals(result.activeTasksPercent, 60f)
    }

    @Test
    fun deve_DevolverCompletedTaskPercent0EActiveTasksPercent0_QuandoListaTarefasForVazia(){
        val tasks = listOf<Task>()

        val result = getActiveAndCompletedStats(tasks)

        assertEquals(result.activeTasksPercent, 0f)
        assertEquals(result.completedTasksPercent, 0f)
    }

}