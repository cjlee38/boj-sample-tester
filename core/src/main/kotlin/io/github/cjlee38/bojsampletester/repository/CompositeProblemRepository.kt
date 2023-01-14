package io.github.cjlee38.bojsampletester.repository

import io.github.cjlee38.bojsampletester.data.Problem

class CompositeProblemRepository(
    private val sqliteProblemRepository: SqliteProblemRepository,
    private val restProblemRepository: RestProblemRepository
) : ProblemRepository {
    override fun save(problem: Problem) {
        sqliteProblemRepository.save(problem)
    }

    override fun findByNumber(number: String): Problem? {
        val problem = sqliteProblemRepository.findByNumber(number)
        return if (problem != null) {
            problem
        } else {
            val findByNumber1 = restProblemRepository.getByNumber(number)
            save(findByNumber1)
            findByNumber1
        }
    }

    override fun getByNumber(number: String): Problem {
        return findByNumber(number) ?: throw IllegalArgumentException("Couldn't find problem by number : $number")
    }
}
