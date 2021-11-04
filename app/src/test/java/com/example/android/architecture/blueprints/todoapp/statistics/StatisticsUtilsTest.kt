/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.architecture.blueprints.todoapp.statistics

import com.example.android.architecture.blueprints.todoapp.data.Task
import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertThat
import org.junit.Test

/**
 * Unit tests for [getActiveAndCompletedStats].
 */
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

