package io.github.cjlee38.bojsampletester.executor

import io.github.cjlee38.bojsampletester.Grade
import io.github.cjlee38.bojsampletester.Problem
import io.github.cjlee38.bojsampletester.Sample
import io.github.cjlee38.bojsampletester.Solution
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

class PythonExecutorTest : StringSpec({
    "run python" {
        val executor = PythonExecutor(
            Solution(
                path = "/Users/cjlee/Desktop/workspace/boj-sample-tester/core/src/test/kotlin/io/github/cjlee38/bojsampletester/executor/solutions/pythonSolution.py",
                code = ""
            )
        )
        val grades = executor.execute(
            Problem(
                "1000",
                1000,
                listOf(
                    Sample("1 2", "3"),
                    Sample("100 200", "300"),
                    Sample("2147483647 1", "2147483648")
                )
            )
        )
        grades.grades.forAll { it.isCorrect.shouldBeTrue() }
    }
})
