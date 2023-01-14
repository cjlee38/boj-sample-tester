package io.github.cjlee38.bojsampletester.repository

import io.github.cjlee38.bojsampletester.data.Problem

interface ProblemRepository {
    fun save(problem: Problem)
    fun findByNumber(number: String): Problem?
    fun getByNumber(number: String): Problem
}
