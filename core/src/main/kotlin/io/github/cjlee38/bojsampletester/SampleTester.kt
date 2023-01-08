package io.github.cjlee38.bojsampletester

import io.github.cjlee38.bojsampletester.executor.Executor
import io.github.cjlee38.bojsampletester.executor.JavaExecutor
import io.github.cjlee38.bojsampletester.executor.PythonExecutor
import io.github.cjlee38.bojsampletester.request.JsoupRequestClient

class SampleTester {
    fun run(solution: Solution, problemNumber: String): Grades {
        val client = JsoupRequestClient()
        val problem = client.request(problemNumber)

        val executor = determineExecutor(solution)
        val grades = executor.execute(problem)
        return grades
    }

    private fun determineExecutor(solution: Solution): Executor {
        if (solution.path.endsWith(".java")) {
            return JavaExecutor(solution)
        }
        if (solution.path.endsWith(".py")) {
            return PythonExecutor(solution)
        }
        throw IllegalArgumentException("undefined extension for executor : ${solution.path}")
    }
}
