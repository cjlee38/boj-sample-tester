package io.github.cjlee38.persistence

import io.github.cjlee38.bojsampletester.data.Problem
import io.github.cjlee38.bojsampletester.data.Sample
import io.github.cjlee38.persistence.jdbc.JdbcTemplate
import io.kotest.core.spec.style.StringSpec
import org.sqlite.SQLiteConfig
import org.sqlite.SQLiteDataSource
import java.util.Properties

class ProblemRepositoryTest : StringSpec({

    beforeEach {

    }
    "test" {
        val properties = Properties()
        val config = SQLiteConfig(properties)
        val dataSource = SQLiteDataSource(config)
        dataSource.url = "jdbc:sqlite:sqlite.db"
        val jdbcTemplate = JdbcTemplate(dataSource)

        jdbcTemplate.update("drop table if exists problem")
        jdbcTemplate.update("drop table if exists sample")

        jdbcTemplate.update(
            """CREATE TABLE IF NOT EXISTS problem
            (
            id      INTEGER PRIMARY KEY AUTOINCREMENT,
            number  TEXT NOT NULL UNIQUE,
            timeout INT  NOT NULL
            );""".trim())
        jdbcTemplate.update(
        """CREATE TABLE IF NOT EXISTS sample
            (
                id             INTEGER PRIMARY KEY AUTOINCREMENT,
                problem_number INTEGER NOT NULL,
                input          TEXT    NOT NULL,
                output         TEXT    NOT NULL
            );
            """
        )

        val repository = SqliteProblemRepository(jdbcTemplate)
        repository.save(Problem("1234", 3L, listOf(Sample("hello", "world"), Sample("foo", "bar"))))
        val problem = repository.findByNumber("1234")
        println(problem)

        jdbcTemplate.update("drop table if exists problem")
        jdbcTemplate.update("drop table if exists sample")
    }
})
