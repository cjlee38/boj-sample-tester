package io.github.cjlee38.bojsampletester.executor

import io.github.cjlee38.bojsampletester.data.Grade
import io.github.cjlee38.bojsampletester.data.Grades
import io.github.cjlee38.bojsampletester.data.Problem
import io.github.cjlee38.bojsampletester.data.Solution
import io.github.cjlee38.bojsampletester.executor.engine.ProcessEngine
import mu.KotlinLogging

private val log = KotlinLogging.logger {}

class JavaExecutor(private val solution: Solution) : Executor {

    override fun execute(problem: Problem): Grades {
        log.trace { "Executor java was selected, problem : $problem" }
        val engine = ProcessEngine()
        val grades = problem.samples
            .map {
                val actual = engine.run("java " + solution.path, it.input, problem.time)
                Grade(it, actual)
            }
        return Grades(grades)
    }
}
