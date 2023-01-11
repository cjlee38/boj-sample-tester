package io.github.cjlee38.persistence.jdbc

import io.github.cjlee38.persistence.jdbc.execution.QueryExecution
import io.github.cjlee38.persistence.jdbc.execution.UpdateExecution
import javax.sql.DataSource

class JdbcTemplate(private val connector: JdbcConnector) {
    constructor(dataSource: DataSource) : this(JdbcConnector(dataSource))

    fun update(sql: String, vararg arguments: Any) {
        connector.execute(UpdateExecution(sql, *arguments))
    }

    fun <T> query(sql: String, vararg arguments: Any, rowMapper: RowMapper<T>): List<T> {
        return connector.execute(QueryExecution(sql, rowMapper, *arguments))
    }

    fun <T> queryForObject(sql: String, vararg arguments: Any, rowMapper: RowMapper<T>,): T {
        val results = connector.execute(QueryExecution(sql, rowMapper, *arguments))
        if (results.isEmpty()) throw IllegalArgumentException("Failed to find result.")
        if (results.size > 1) throw IllegalArgumentException("The result of query isn't single : ${results.size}")
        return results.first()
    }
}
