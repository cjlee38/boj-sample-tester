package io.github.cjlee38.bojsampletester.repository

import io.github.cjlee38.bojsampletester.data.Problem
import io.github.cjlee38.bojsampletester.data.Sample
import io.github.cjlee38.persistence.jdbc.JdbcTemplate

class SqliteProblemRepository(private val jdbcTemplate: JdbcTemplate) : ProblemRepository {

    override fun save(problem: Problem) {
        jdbcTemplate.update("insert into problem (number, timeout) values (?, ?)", problem.number, problem.time)
        for (sample in problem.samples) {
            jdbcTemplate.update(
                "insert into sample (problem_number, input, output) values (?, ?, ?)",
                problem.number,
                sample.input,
                sample.output
            )
        }
    }

    override fun findByNumber(number: String): Problem? {
        val samples = jdbcTemplate.query("select * from sample where problem_number = ?", number) { rs, _ ->
            Sample(rs.getString("input"), rs.getString("output"))
        }
        return jdbcTemplate.queryForObject("select * from problem where number = ?", number) { rs, _ ->
            Problem(number, rs.getLong("timeout"), samples)
        }
    }

    override fun getByNumber(number: String): Problem {
        return findByNumber(number) ?: throw IllegalArgumentException("Couldn't find problem by number : $number")
    }
}
