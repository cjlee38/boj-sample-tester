package io.github.cjlee38.bojsampletester.executor

import io.github.cjlee38.bojsampletester.Problem
import io.github.cjlee38.bojsampletester.Sample
import io.github.cjlee38.bojsampletester.Solution
import io.kotest.core.spec.style.StringSpec

class JavaExecutorTest : StringSpec({

    "test" {
        val executor = JavaExecutor(
            Solution(
                "/Users/cjlee/Desktop/workspace/boj-sample-tester/core/src/test/kotlin/io/github/cjlee38/bojsampletester/executor/JavaSolution.java",
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
        println(grades)
    }
})
