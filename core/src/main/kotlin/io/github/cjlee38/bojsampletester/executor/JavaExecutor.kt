package io.github.cjlee38.bojsampletester.executor

import io.github.cjlee38.bojsampletester.data.Grade
import io.github.cjlee38.bojsampletester.data.GradeStatus
import io.github.cjlee38.bojsampletester.data.Grades
import io.github.cjlee38.bojsampletester.data.Problem
import io.github.cjlee38.bojsampletester.data.Solution
import io.github.cjlee38.bojsampletester.executor.engine.EngineStatus
import io.github.cjlee38.bojsampletester.executor.engine.ProcessCommand
import io.github.cjlee38.bojsampletester.executor.engine.ProcessEngine

class JavaExecutor(private val solution: Solution) : Executor {

    override fun execute(problem: Problem): Grades {
        val engine = ProcessEngine(ProcessCommand.JAVA, solution.path, problem.time)
        return problem.samples
            .map {
                val run = engine.run(it.input)
                val gradeStatus = if (run.status == EngineStatus.FINISHED) {
                    GradeStatus.SUCCEEDED
                } else {
                    GradeStatus.FAILED
                }
                Grade(gradeStatus, it.input, it.output, run.output)
            }
            .let { Grades(it) }
    }
}
