package io.github.cjlee38.bojsampletester.executor

import io.github.cjlee38.bojsampletester.data.Grades
import io.github.cjlee38.bojsampletester.data.Problem

interface Executor {
    fun execute(problem: Problem): Grades
}
