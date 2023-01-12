package io.github.cjlee38.bojsampletester

import io.github.cjlee38.bojsampletester.data.Grades
import io.github.cjlee38.bojsampletester.data.Problem
import io.github.cjlee38.bojsampletester.data.Solution
import io.github.cjlee38.bojsampletester.executor.Executor
import io.github.cjlee38.bojsampletester.executor.JavaExecutor
import io.github.cjlee38.bojsampletester.executor.PythonExecutor

class SampleTester {
    fun run(solution: Solution, problem: Problem): Grades {
        val executor = determineExecutor(solution)
        return executor.execute(problem)
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
