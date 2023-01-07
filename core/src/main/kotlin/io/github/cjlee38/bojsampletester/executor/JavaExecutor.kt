package io.github.cjlee38.bojsampletester.executor

import io.github.cjlee38.bojsampletester.Grade
import io.github.cjlee38.bojsampletester.Grades
import io.github.cjlee38.bojsampletester.Problem
import io.github.cjlee38.bojsampletester.Solution
import java.util.concurrent.TimeUnit

class JavaExecutor(private val solution: Solution) : Executor {
    override fun execute(problem: Problem): Grades {
        val grades = mutableListOf<Grade>()

        for (sample in problem.samples) {
            val process = createProcess()
            process.write(sample.input)
            process.waitFor(problem.time, TimeUnit.SECONDS)
            val read = process.read()
            val isCorrect = read.trim() == sample.output.trim()
            grades.add(Grade(isCorrect))
        }
        return Grades(grades)
    }

    private fun createProcess(): Process {
        return Runtime.getRuntime().exec("java " + solution.path)
    }
}

private fun Process.write(input: String) {
    outputStream.write(input.toByteArray())
    outputStream.flush()
    outputStream.close()
}

private fun Process.read(): String {
    return String(inputStream.readAllBytes())
}
