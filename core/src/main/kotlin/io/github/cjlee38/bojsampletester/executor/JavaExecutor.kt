package io.github.cjlee38.bojsampletester.executor

import io.github.cjlee38.bojsampletester.Grade
import io.github.cjlee38.bojsampletester.Grades
import io.github.cjlee38.bojsampletester.Problem
import io.github.cjlee38.bojsampletester.Solution
import io.github.cjlee38.bojsampletester.executor.engine.ProcessEngine

class JavaExecutor(private val solution: Solution) : Executor {
    override fun execute(problem: Problem): Grades {
        val engine = ProcessEngine()
        val grades = mutableListOf<Grade>()
        for (sample in problem.samples) {
            val output = engine.run("java " + solution.path, sample.input, problem.time)
            val isCorrect = output.trim() == sample.output.trim()
            grades.add(Grade(isCorrect))
        }
        return Grades(grades)
    }
}
