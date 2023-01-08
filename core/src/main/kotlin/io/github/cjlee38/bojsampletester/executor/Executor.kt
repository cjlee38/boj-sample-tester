package io.github.cjlee38.bojsampletester.executor

import io.github.cjlee38.bojsampletester.Grades
import io.github.cjlee38.bojsampletester.Problem

interface Executor {
    fun execute(problem: Problem): Grades
}
