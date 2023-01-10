package io.github.cjlee38.bojsampletester.executor

import createProblem
import io.github.cjlee38.bojsampletester.data.Sample
import io.github.cjlee38.bojsampletester.data.Solution
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.booleans.shouldBeTrue
import solutions.java.EmptySolution
import solutions.java.ExceptionSolution
import solutions.java.NormalSolution
import solutions.java.StackoverflowErrorSolution

private const val BASE_PATH = "src/test/java/solutions/java/"

class JavaExecutorTest : StringSpec({

    "run java code" {
        val path = NormalSolution().javaClass.simpleName.formatJavaPath()
        val executor = JavaExecutor(Solution(path, ""))
        val grades = executor.execute(
            createProblem(
                samples = listOf(
                    Sample("1 2", "3"),
                    Sample("100 200", "300"),
                    Sample("2147483647 1", "-2147483648")
                )
            )
        )
        grades.grades.forAll { it.isCorrect.shouldBeTrue() }
    }

    "run without main" {
        val path = EmptySolution().javaClass.simpleName.formatJavaPath()
        val executor = JavaExecutor(Solution(path, ""))
        executor.execute(createProblem())
    }

    "catch exception" {
        val path = ExceptionSolution().javaClass.simpleName.formatJavaPath()
        val executor = JavaExecutor(Solution(path, ""))
        executor.execute(createProblem())
    }

    "catch stackoverflow error" {
        val path = StackoverflowErrorSolution().javaClass.simpleName.formatJavaPath()
        val executor = JavaExecutor(Solution(path, ""))
        executor.execute(createProblem())
    }
})

private fun String.formatJavaPath(): String {
    return BASE_PATH + this + ".java"
}
