package io.github.cjlee38.persistence

import io.github.cjlee38.bojsampletester.data.Problem

interface ProblemRepository {
    fun save(problem: Problem)
    fun findByNumber(number: String): Problem
}
