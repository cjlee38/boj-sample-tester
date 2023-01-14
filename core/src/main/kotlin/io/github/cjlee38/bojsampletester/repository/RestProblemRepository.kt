package io.github.cjlee38.bojsampletester.repository

import io.github.cjlee38.bojsampletester.data.Problem
import io.github.cjlee38.bojsampletester.request.RequestClient

class RestProblemRepository(private val client: RequestClient): ProblemRepository {
    override fun save(problem: Problem): Nothing {
        throw UnsupportedOperationException()
    }

    override fun findByNumber(number: String): Problem? {
        return client.request(number)
    }

    override fun getByNumber(number: String): Problem {
        return client.request(number)
    }
}
