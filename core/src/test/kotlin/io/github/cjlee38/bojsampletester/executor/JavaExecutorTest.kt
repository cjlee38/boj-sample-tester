package io.github.cjlee38.bojsampletester.executor

import io.github.cjlee38.bojsampletester.data.Problem
import io.github.cjlee38.bojsampletester.data.Sample
import io.github.cjlee38.bojsampletester.data.Solution
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.booleans.shouldBeTrue

class JavaExecutorTest : StringSpec({

    "run java code" {
        val executor = JavaExecutor(
            Solution(
                "/Users/cjlee/Desktop/workspace/boj-sample-tester/core/src/test/kotlin/io/github/cjlee38/bojsampletester/executor/solutions/JavaSolution.java",
                ""
            )
        )
        val grades = executor.execute(
            Problem(
                "1000",
                1000,
                listOf(
                    Sample("1 2", "3"),
                    Sample("100 200", "300"),
                    Sample("2147483647 1", "-2147483648")
                )
            )
        )
        grades.grades.forAll { it.isCorrect.shouldBeTrue() }
    }
})
