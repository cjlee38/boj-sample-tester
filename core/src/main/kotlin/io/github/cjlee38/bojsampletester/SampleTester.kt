package io.github.cjlee38.bojsampletester

import io.github.cjlee38.bojsampletester.executor.PythonExecutor
import io.github.cjlee38.bojsampletester.request.JsoupRequestClient

class SampleTester {
    fun run(solution: Solution, problemNumber: String): Grades {
        val client = JsoupRequestClient()
        val problem = client.request(problemNumber)
        val executor = PythonExecutor(solution) // todo: determine executor
        val grades = executor.execute(problem)
        return grades
    }
}
