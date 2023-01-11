package io.github.cjlee38.persistence.jdbc.execution

import io.github.cjlee38.persistence.jdbc.RowMapper
import io.github.cjlee38.persistence.jdbc.execution.support.ArgumentsSetter
import java.sql.PreparedStatement
import java.sql.ResultSet

class QueryExecution<T>(
    override val sql: String,
    private val rowMapper: RowMapper<T>,
    private vararg val arguments: Any
) : Execution<List<T>> {
    override fun execute(statement: PreparedStatement): List<T> {
        ArgumentsSetter.setArguments(statement, *arguments)
        return mapRows(statement.executeQuery())
    }

    private fun mapRows(resultSet: ResultSet): List<T> {
        val results = mutableListOf<T>()
        while (resultSet.next()) {
            results.add(rowMapper.rowMap(resultSet, resultSet.row))
        }
        return results
    }
}
