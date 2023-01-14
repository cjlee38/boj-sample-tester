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
fun getProblemRepository(): ProblemRepository {
    return CompositeProblemRepository(
        SqliteProblemRepository(JdbcTemplate(getDataSource())),
        RestProblemRepository(getRequestClient())
    )
}

fun getRequestClient(): RequestClient {
    return JsoupRequestClient()
}

fun getDataSource(): DataSource {
    val dataSource = SQLiteDataSource()
    dataSource.url = "jdbc:sqlite:sqlite.db"
    return dataSource
}
