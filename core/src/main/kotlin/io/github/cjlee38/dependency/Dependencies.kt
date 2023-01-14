package io.github.cjlee38.dependency

import io.github.cjlee38.bojsampletester.repository.CompositeProblemRepository
import io.github.cjlee38.bojsampletester.repository.ProblemRepository
import io.github.cjlee38.bojsampletester.repository.RestProblemRepository
import io.github.cjlee38.bojsampletester.repository.SqliteProblemRepository
import io.github.cjlee38.bojsampletester.request.JsoupRequestClient
import io.github.cjlee38.bojsampletester.request.RequestClient
import io.github.cjlee38.persistence.jdbc.JdbcTemplate
import org.sqlite.SQLiteDataSource
import javax.sql.DataSource

// todo: utilize pico container ?
fun getProblemRepository(basePath: String): ProblemRepository {
    return CompositeProblemRepository(
        SqliteProblemRepository(JdbcTemplate(getDataSource(basePath))),
        RestProblemRepository(getRequestClient())
    )
}

fun getRequestClient(): RequestClient {
    return JsoupRequestClient()
}

fun getDataSource(basePath: String): DataSource {
    val dataSource = SQLiteDataSource()
    println("basePath = ${basePath}")
    dataSource.url = "jdbc:sqlite:${basePath}/boj.db"
    return dataSource
}
