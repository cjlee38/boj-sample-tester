package io.github.cjlee38.persistence.jdbc.execution.support

import java.sql.PreparedStatement

object ArgumentsSetter {
    fun setArguments(statement: PreparedStatement, vararg arguments: Any) {
        arguments.withIndex().forEach { (index, value) ->
            statement.setObject(index + 1, value)
        }
    }
}
