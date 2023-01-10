package io.github.cjlee38.bojsampletester.executor

import createProblem
import io.github.cjlee38.bojsampletester.data.Sample
import io.github.cjlee38.bojsampletester.data.Solution
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.booleans.shouldBeTrue

private const val BASE_PATH = "src/test/java/solutions/python/"

class PythonExecutorTest : StringSpec({
    "run python code" {
        val executor = PythonExecutor(Solution(BASE_PATH + "normal.py"))
        val grades = executor.execute(
            createProblem(
                samples = listOf(
                    Sample("1 2", "3"),
                    Sample("100 200", "300"),
                    Sample("2147483647 1", "2147483648")
                )
            )
        )
        grades.grades.forAll { it.isCorrect.shouldBeTrue() }
    }

    "return grade with fail on exception caught" {
        val path = BASE_PATH + "exception.py"
        val executor = PythonExecutor(Solution(path))
        executor.execute(createProblem())
    }

    "return grade with fail on error caught" {
        val path = BASE_PATH + "exception.py"
        val executor = PythonExecutor(Solution(path, ""))
        executor.execute(createProblem())
    }
})
