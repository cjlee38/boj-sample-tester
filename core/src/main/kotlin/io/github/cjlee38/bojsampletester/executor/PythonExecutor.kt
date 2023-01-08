package io.github.cjlee38.bojsampletester.executor

import io.github.cjlee38.bojsampletester.Grade
import io.github.cjlee38.bojsampletester.Grades
import io.github.cjlee38.bojsampletester.Problem
import io.github.cjlee38.bojsampletester.Solution
import io.github.cjlee38.bojsampletester.executor.engine.ProcessEngine

class PythonExecutor(private val solution: Solution) : Executor {

    override fun execute(problem: Problem): Grades {
        val engine = ProcessEngine()
        val grades = problem.samples
            .map {
                val actual = engine.run("python3 " + solution.path, it.input, problem.time)
                Grade(it, actual)
            }
        return Grades(grades)
    }
}
