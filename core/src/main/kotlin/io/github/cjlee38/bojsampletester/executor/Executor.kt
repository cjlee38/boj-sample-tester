package io.github.cjlee38.bojsampletester.executor

import io.github.cjlee38.bojsampletester.Problem
import io.github.cjlee38.bojsampletester.Grades

interface Executor {
    fun execute(problem: Problem): Grades
}
