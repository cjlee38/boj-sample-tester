package io.github.cjlee38.persistence.jdbc

import io.github.cjlee38.persistence.jdbc.execution.Execution
import javax.sql.DataSource

class JdbcConnector(private val dataSource: DataSource) {
    fun <T> execute(execution: Execution<T>): T {
        dataSource.connection.use { connection ->
            connection.prepareStatement(execution.sql).use {
                return execution.execute(it)
            }
        }
    }
}
