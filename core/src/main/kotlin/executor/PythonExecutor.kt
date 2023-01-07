package executor

import Grade
import Problem
import Grades
import Solution
import java.util.concurrent.TimeUnit

class PythonExecutor(private val solution: Solution) : Executor {

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
        return Runtime.getRuntime().exec("python3 ${solution.path}")
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
